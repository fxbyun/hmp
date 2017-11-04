$(function () {
    // 基础路径
    window.ctxPath = $("meta[name='ctx']").attr('content');
    // ajax基本配置
    $.ajaxSetup({
        // error: function (jqXHR, textStatus, errorThrown) {
        //     if (jqXHR.status === 404) {
        //         Alert.error("请求的页面未找到");
        //     }
        //     if (jqXHR.status === 500) {
        //         Alert.error("请求的页面出现错误,请联系管理员");
        //     }
        //     if (jqXHR.status === 400) {
        //         Alert.error("参数错误,请重试");
        //     }
        // }
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
 * Created by IntelliJ IDEA 2016.4
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 2016/10/18 0018
 * Time 11:03
 */
/**
 *  Map 操作 工具
 * @constructor
 */
function Map() {
    this.elements = new Array();
    //获取MAP元素个数
    this.size = function () {
        return this.elements.length;
    };
    //判断MAP是否为空
    this.isEmpty = function () {
        return (this.elements.length < 1);
    };
    //删除MAP所有元素
    this.clear = function () {
        this.elements = new Array();
    };
    //向MAP中增加元素（key, value)
    this.put = function (_key, _value) {
        this.elements.push({
            key: _key,
            value: _value
        });
    };
    //删除指定KEY的元素，成功返回True，失败返回False
    this.remove = function (_key) {
        var bln = false;
        try {
            for (i = 0; i < this.elements.length; i++) {
                if (this.elements[i].key == _key) {
                    this.elements.splice(i, 1);
                    return true;
                }
            }
        } catch (e) {
            bln = false;
        }
        return bln;
    };
    //获取指定KEY的元素值VALUE，失败返回NULL
    this.get = function (_key) {
        try {
            for (i = 0; i < this.elements.length; i++) {
                if (this.elements[i].key == _key) {
                    return this.elements[i].value;
                }
            }
        } catch (e) {
            return null;
        }
    };
    //获取指定索引的元素（使用element.key，element.value获取KEY和VALUE），失败返回NULL
    this.element = function (_index) {
        if (_index < 0 || _index >= this.elements.length) {
            return null;
        }
        return this.elements[_index];
    };
    //判断MAP中是否含有指定KEY的元素
    this.containsKey = function (_key) {
        var bln = false;
        try {
            for (i = 0; i < this.elements.length; i++) {
                if (this.elements[i].key == _key) {
                    bln = true;
                }
            }
        } catch (e) {
            bln = false;
        }
        return bln;
    };
    //判断MAP中是否含有指定VALUE的元素
    this.containsValue = function (_value) {
        var bln = false;
        try {
            for (i = 0; i < this.elements.length; i++) {
                if (this.elements[i].value == _value) {
                    bln = true;
                }
            }
        } catch (e) {
            bln = false;
        }
        return bln;
    };
    //获取MAP中所有VALUE的数组（ARRAY）
    this.values = function () {
        var arr = new Array();
        for (i = 0; i < this.elements.length; i++) {
            arr.push(this.elements[i].value);
        }
        return arr;
    };
    //获取MAP中所有KEY的数组（ARRAY）
    this.keys = function () {
        var arr = new Array();
        for (i = 0; i < this.elements.length; i++) {
            arr.push(this.elements[i].key);
        }
        return arr;
    };
}


// $(function(){
//     var map = new Map();
//     map.put("key", "value");
//     map.put("key1", "value1");
//     map.put("key2", "value2");
//     map.put("key3", "value3");
//     var val = map.get("key2")
//     alert(val);
// });

/**
 * 调试模式打出 返回的对象详细
 * 提示窗口和Console.info()都会有
 * @param msg
 */
function hmp(dateObj) {
    console.info(dateObj);
    var tmp = toastr.options["timeOut"];
    toastr.options["timeOut"] = 0;
    Alert.debguger(dateObj, "调试信息");
    toastr.options["timeOut"] = tmp;
}
$(function () {
    //初始化插件
    //只能输入数字
    $("[hmp-input-number]").keyup(function () {
        $(this).val($(this).val().replace(/\D/g, ''));

    });
    //只能输入数字+小数
    // $("[hmp-input-double]").keyup(function () {
    //     // $(this).val($(this).val().replace(/[^\d.]/g, ''));
    //     $(this).val($(this).val().replace(/[^\d.]/g, ''));
    //     try {
    //         var reg = /\.\d*\./g;
    //         if ($(this).val().match(reg).length > 0) {
    //             $(this).val($(this).val().substr(0, $(this).val().length - 1));
    //         }
    //     } catch (e) {
    //     }
    // });

    $("[hmp-input-price]").keyup(function () {
        var price = $(this).val();
        var decimalReg = /^[+-]?\d*\.?\d{1,2}$/;
        if (price != "" && price.test(decimalReg)) {
            $(this).val(price);
        } else {
            $(this).val("");
        }
    });

    try {
        $.each($("input[type='number']"), function (index, obj) {
            $(obj).keyup(function () {
                if ($(this).val().length > 8) {
                    $(this).val($(this).val().slice(0, 7));
                }
            })

        })


    } catch (e) {

    }


    try {
        //初始化时间
        $('.form_date').datetimepicker({
            language: 'zh-CN',
            format: 'yyyy/mm/dd hh:mm',
            weekStart: 1,
            todayBtn: 1,
            autoclose: 1,
            todayHighlight: 1,
            startView: 2,
            minView: 2,
            forceParse: 0
        });

        $('.form_day').datetimepicker({
            language: 'zh-CN',
            format: 'yyyy/mm/dd',
            weekStart: 1,
            todayBtn: 1,
            autoclose: 1,
            todayHighlight: 1,
            startView: 2,
            minView: 2,
            forceParse: 0
        });
    } catch (e) {
    }
})

/**FangXB
 * HTML代码拼接
 * @param temp
 * @param data
 * @param regexp
 * @returns {string}
 */

function substitute(temp, data, regexp) {
    if (!(Object.prototype.toString.call(data) === "[object Array]")) data = [data];
    var ret = [];
    for (var i = 0, j = data.length; i < j; i++) {
        ret.push(replaceAction(data[i]));
    }
    return ret.join("");
    function replaceAction(object) {
        return temp.replace(regexp || (/\\?\{([^}]+)\}/g), function (match, name) {
            if (match.charAt(0) == '\\') return match.slice(1);
            return (object[name] != undefined) ? object[name] : '';
        });
    }
}
//参考例子：
/*var SongInfo = [
 {"singer":"林海","name":"琵琶语","url":"http://newsms.netor.com/m/grieve/stores/o/200701/200701m70505snet0r28212415.mp3"},
 {"singer":"尺八","name":"宙","url":"http://webdisk.yyjxkj.com/angel/aini/%E5%AE%99.mp3"},
 {"singer":"宗次亮","name":"故乡的原风景","url":"http://www.kf-cn.com/cn/miimg/sound.mp3"}]

 var dom = {
 'head':'<ul>',
 'repeat':'<li><a href="{url}">{singer}-{name}</a></li>',
 'foot':'</ul>'
 }

 var html = [dom.head,substitute(dom.repeat,SongInfo ),dom.foot].join("");*/

