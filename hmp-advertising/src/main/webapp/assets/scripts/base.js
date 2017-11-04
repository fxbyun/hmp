$(function () {
    // 基础路径
    window.ctxPath = $("meta[name='ctx']").attr('content');
    // ajax基本配置
    $.ajaxSetup({
        error: function (jqXHR, textStatus, errorThrown) {
            if (jqXHR.status === 404) {
                Alert.error("请求的页面未找到");
            }
            if (jqXHR.status === 500) {
                Alert.error("请求的页面出现错误,请联系管理员");
            }
            if (jqXHR.status === 400) {
                Alert.error("参数错误,请重试");
            }
        }
    });
    // 日期选择器 语言设置

    initModule($("[data-select-search]"), "select2");
    // toast
    if ($("#toast-content").length < 1) {
        $("body").prepend("<div id='toast-content' style='top:12px;position: absolute !important;'></div>");
        //alert("页面上未找到[id='toast-content']元素,无法弹出提示框");
    }
    $(window).scroll(function () {
        var headerHeight = $("header").height();
        var scrollTop = $(document).scrollTop();
        if (scrollTop >= headerHeight) {
            $("#toast-content").css("top", "32px");
        } else {
            $("#toast-content").css("top", 30 + 2 + "px");
        }
    });


    // 异常捕捉
    window.onerror = function (errorMessage, scriptURI, lineNumber, columnNumber, error) {

        //没有URL不上报！上报也不知道错误
        if (errorMessage != "Script error." && !scriptURI) {
            return true;
        }
        //采用异步的方式
        //我遇到过在window.onunload进行ajax的堵塞上报
        //由于客户端强制关闭webview导致这次堵塞上报有Network Error
        //我猜测这里window.onerror的执行流在关闭前是必然执行的
        //而离开文章之后的上报对于业务来说是可丢失的
        //所以这里的执行流放到异步事件去执行
        setTimeout(function () {
            var data = {};
            //不一定所有浏览器都支持columnNumber参数
            columnNumber = columnNumber || (window.event && window.event.errorCharacter) || 0;
            data.scriptURI = scriptURI;
            data.lineNumber = lineNumber;
            data.columnNumber = columnNumber;
            if (!!error && !!error.stack) {
                //如果浏览器有堆栈信息
                //直接使用
                data.errorMessage = error.stack.toString();
            } else if (!!arguments.callee) {
                //尝试通过callee拿堆栈信息
                var ext = [];
                var f = arguments.callee.caller, c = 3;
                //这里只拿三层堆栈信息
                while (f && (--c > 0)) {
                    ext.push(f.toString());
                    if (f === f.caller) {
                        break;//如果有环
                    }
                    f = f.caller;
                }
                ext = ext.join(",");
                data.errorMessage = ext;
            }
            //把data上报到后台！
            $.post("/common/log/js/error", data);
        }, 0);
        // return false 让控制台继续报错
        return false;
    };


});
//
///**
// * 初始化组建
// * @param obj    需要初始化的对象
// * @param type   对象类型
// */
window.initModule = function (obj, type) {

    //if (type == "select2") {
    //    var placeholder = $(obj).attr("[data-select-search-placeholder]");
    //    // 下拉选择框
    //    $(obj).select2({
    //        language: "zh-CN",
    //        placeholder: placeholder,
    //        width: 'resolve',
    //        matcher: function (params, data) {
    //            return $.fn.select2.extMather(params, data);
    //        }
    //    });
    //}
};


// alert 提示组件
window.Alert = {};
Alert.common = function () {
    if ($("#toast-content").length < 1) {
        $("body").prepend("<div id='toast-content' style='top:12px;position: absolute !important;'></div>");
        //alert("页面上未找到[id='toast-content']元素,无法弹出提示框");
    }
    var headerHeight = $("header").height();
    var nowHeight = $(window).scrollTop();
    $(".toast-error").attr("style", "display: block;");
    if (nowHeight < headerHeight) {
        // 如果当前滚动的位置还没超过header的高度,就显示在header下面
        $("#toast-content").css("top", 30 + 2 + "px");
    } else {
        // 当前滚动的位置超过了header的高度,显示顶部
        $("#toast-content").css("top", "32px");
    }
};
Alert.error = function (message, title) {
    Alert.common();
    if (title == null) {
        title = '错误提示';
    }
    return toastr['error'](message, title);
};

Alert.warning = function (message, title) {
    Alert.common();
    if (title == null) {
        title = '警告提示';
    }
    return toastr['warning'](message, title);
};

Alert.success = function (message, title) {
    Alert.common();
    if (title == null) {
        title = '成功提示';
    }
    return toastr['success'](message, title);
};
Alert.info = function (message, title) {
    Alert.common();
    if (title == null) {
        title = '提示';
    }
    return toastr["info"](message, title);
};

/**
 *
 * @param obj
 * @returns {string}
 */
function isObj(obj) {
    var tmpMsg = "";
    if (typeof obj == "object") {
        for (x in obj) {
            if (typeof obj[x] == "object") {
                tmpMsg += (x + ":{\n")
                for (j in obj[x]) {
                    tmpMsg += (j + ":" + obj[x][j] + "\n");
                }
                tmpMsg += ( "}\n")
            } else {
                tmpMsg += (x + ":" + obj[x] + "\n");
            }
        }
    } else {
        tmpMsg = obj;
    }
    return tmpMsg;
}

Alert.debguger = function (message, title) {
    Alert.common();

    var tmpMsg = "";
    tmpMsg = isObj(message);
    return toastr["info"](tmpMsg, title);
};

/**
 * 调试模式打出 返回的对象详细
 * 提示窗口和Console.info()都会有
 * @param msg
 */
function hmp(dateObj, time) {
    if (!time) {
        time = 0;
    }
    console.info(dateObj);
    var tmp = toastr.options["timeOut"];
    toastr.options["timeOut"] = time;
    var d = new Date();
    var seconds = d.getSeconds();
    if (parseInt(seconds) < 10) {
        seconds = "0" + seconds;
    }
    Alert.debguger(dateObj, "调试信息" + d.getHours() + ":" + d.getMinutes() + ":" + seconds);
    toastr.options["timeOut"] = tmp;
}

/**
 * 获取客户端的当前地址
 */
var originImgSrc = 'cnbogs-logo.gif';


function getCity() {
    var url = "http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json";
    // $.jsonp({
    //     url: url,
    //     data: {},
    //     callbackParameter: "callback",
    //     success: function (json) {
    //         console.log(json);
    //     },
    //     error: function (xOptions, textStatus) {
    //     }
    // });
    $.ajax({
        url: url,
        dataType: 'JSON',
        processData: false,
        type: 'get',
        success: function (data) {
            console.info(data)
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.info(XMLHttpRequest.status);
            console.info(XMLHttpRequest.readyState);
            console.info(textStatus);
        }
    });
}
