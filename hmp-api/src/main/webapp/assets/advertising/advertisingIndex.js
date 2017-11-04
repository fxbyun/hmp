/**
 * Created by IntelliJ IDEA 2016.2
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 16/8/18
 * Time 下午3:37
 */

var TOP_MESAGE_ARR = [];
//视频和图片播放区域的轮播句柄  可通过  videoSlider.next() 控制跳转到下一个


//排队人数
var adcertSize = 0;
//获取排队信息 句柄
var advertingHunder;
$(function () {
//轮询 获取排队列表 和 用户设置信息
    utlit_getAdvingSize();
//轮询 获取 图片和 跑马灯字符 的条数，如果发声改变 那就 刷新 页面
//     utlis_getPicAndTextCount();

});

var videoSlider;
$(function () {
    $("#msgDiv").hide();
    //图片区轮播效果
    var bannerSlider = new Slider($('#adv_banner'), {
        time: 10000,
        delay: 400,
        event: 'hover',
        auto: true,
        mode: 'fade',
        controller: $('#bannerCtrl'),
        activeControllerCls: 'active'
    });
    $('#adv_banner .flex-prev').click(function () {
        bannerSlider.prev()
    });
    $('#adv_banner .flex-next').click(function () {
        bannerSlider.next()
    });
    //视频区轮播效果
    videoSlider = new Slider($('#adv_video'), {
        time: 5000,
        delay: 400,
        event: 'hover',
        auto: true,
        mode: 'fade',
        controller: $('#videoCtrl'),
        activeControllerCls: 'active'
    });
    $('#adv_video .flex-prev').click(function () {
        videoSlider.prev()
    });
    $('#adv_video .flex-next').click(function () {
        videoSlider.next()
    });
    //初始化时间插件
    $('#timeSpan').jclock({withDate: true, utc: true});
});

/**
 * 点击 挂号按钮 打开 挂号刷卡窗口
 */
function fn_ClickRegistr(msg, layerIndex, doctorId) {

    parent.layer.open({
        type: 2,
        title: ['排队挂号', 'font-size:35px; height:60px; padding:8px 10px; background-color:#529b47; color:#fff;'],
        area: ['800px', '550px'],
        offset: ['600px', '150px'],
        //offset: '10%',
        scrollbar: false,
        move: false,
        closeBtn: 0,
        content: ctx + '/advertising/registration?msg={0}&doctorId={1}'.format(msg, doctorId)
        //end:parent.layer.close(layerIndex)
    });


    setTimeout(function () {
        parent.layer.close(layerIndex)
    }, 500)
}


function playVo(userName, deptName) {
    if (userName == undefined || userName == "") {
        return;
    }
    var deptNameVar = "医生处";
    if (deptName != undefined && deptName != "" && deptName != "null" && deptName != null) {
        deptNameVar = deptName;
    }
    var msg = '请 ' + userName + ' 到' + deptNameVar + '就诊';
    try {
        window.apps.callJs(msg);
    } catch (e) {
        utlis_msg("叫号Error");
    }

}


function fn_AddPatientInfo(cardNo, cardPwd, doctorId) {
    layer.open({
        type: 2,
        title: ['激活健康卡', 'font-size:35px; height:60px; padding:8px 10px; background-color:#529b47; color:#fff;'],
        area: ['600px', '1000px'],
        offset: '10%',
        scrollbar: false,
        move: false,
        closeBtn: 0,
        content: ctx + '/advertising/patientInfo?cardNo=' + cardNo + "&cardPwd=" + cardPwd + "&doctorId=" + doctorId
    });
}


//循环提取 顶部公告,显示到 公告DIV中
// var nowTopMsgIndex = 0;
function loadTopRunText() {
    var tmpVal = "";
    for (one in TOP_MESAGE_ARR) {
        // tmpVal += utils_getText(TOP_MESAGE_ARR[one]);
        tmpVal += (TOP_MESAGE_ARR[one] + "★★★★★★★");
    }
    $("#topMessg").html(utils_getText(tmpVal));
}


/**
 *
 * @param str 需要替换的字符串
 * @returns {返回 HTML 精简后的TXT文本}
 */
function utils_getText(str) {
    str = str.replace('&lt;', '<').replace('&gt;', '>').replace('&nbsp;', '    ');
    if (str.indexOf('&lt;') >= 0 || str.indexOf('&nbsp;') >= 0) {
        str = utils_getText(str);
        return str + "";
    } else {
        return str + "";
    }
}

var callName = "";
function utlit_getAdvingSize() {
    // advertingHunder = setInterval(function () {
    $.postJSON(ctx + "/advertising/getAdvings", {}, function (res) {
        if (adcertSize != res.data) {
            // clearInterval(advertingHunder);
            adcertSize = res.data;

            //从服务器下拉排队人员信息
            utlis_getAdvingListInfo();
            //继续轮询
            // utlit_getAdvingSize();
        }
        if (callName != res.msg && res.msg != "" && res.msg != null && res.msg != "null") {
            callName = res.msg;
            playVo(callName, res.deptName);
        }
    });
    // }, 2000)

}

var advertingPicAndTextHunder;
function utlis_getPicAndTextCount() {
    // advertingPicAndTextHunder = setInterval(function () {
        $.postJSON(ctx + "/advertising/getPicCount", {}, function (res) {
            if (adcertPicAndTextSize != res.data) {
                // clearInterval(advertingPicAndTextHunder);
                //刷新
                window.location.reload();
            }
        })
    // }, 8000)

}

function utlis_getAdvingListInfo() {
    $.postJSON(ctx + "/advertising/getAdvingListInfo", {}, function (res) {
        var ul = document.createElement("ul");
        $(ul).attr("class", "clear-fax");
        var len = res.length;
        for (var s = 1; s <= (12 - len); s++) {
            res.push({id: "", patientName: "", type: "tmp"})
        }
        var aType = 1;
        var bType = 1;
        $(res).each(function (num, one) {
            var tmpNum = num + 1;
            if (one.id != "") {
                // if (one.registrationType == "WECHAT") {
                //     one.id = "B" + (bType < 10 ? ("0" + bType) : bType);
                //     bType++;
                // } else {
                //     one.id = "A" + (aType < 10 ? ("0" + aType) : aType);
                //     aType++;
                // }
                one.id = one.noNumber;
            }
            var li = utlis_genderLi(num, one.id, one.patientName, one.type)
            $(ul).append(li);
        });
        $("#advDiv").html($(ul))
    })
}

function utlis_genderLi(liNum, divNum, spanUserName, type) {
    var li = document.createElement("li");
    if (type) {
        $(li).attr("class", "line-three");
        // $(li).attr("class", "line-two");
    } else {
        if (liNum == 0) {
            // $(li).attr("class", "line-one");
            $(li).attr("class", "line-two");
        } else if (liNum == 1) {
            $(li).attr("class", "line-two");
        } else {
            $(li).attr("class", "line-two");
            // $(li).attr("class", "");
        }
    }


    var div = document.createElement("div");
    $(div).attr("class", "line-up-number");
    if (type) {
        $(div).attr("style", "background-color:#ffffff");
    }
    $(div).text(divNum);
    $(li).append($(div));
    var div2 = document.createElement("div");
    var i = document.createElement("i");
    var span = document.createElement("span");
    $(span).text(spanUserName);
    $(div2).append($(i))
    $(div2).append($(span))
    $(li).append(div2);
    return $(li);
}


/**
 *  加载提示框
 * @param msg
 */
function utlis_msg(msg) {
    $("#msgDiv").text(msg)
    $("#msgDiv").fadeIn(3000, function () {
        $("#msgDiv").text(msg);
        $("#msgDiv").fadeOut(6000, function () {
            $("#msgDiv").text("");
        });
    });

    if ("挂号成功！" == msg) {

        setTimeout(function () {
            parent.parent.utlit_getAdvingSize();
        }, 3000)
    }


}

/**
 * 打开安卓wifi设置页面
 */
var times = 0;
var clearTimesHunder;
function utlis_openWifiSetting() {
    times += 1;
    if (clearTimesHunder != "") {
        clearTimeout(clearTimesHunder);
    }
    clearTimesHunder = setTimeout(function () {
        times = 0;
    }, 2000);
    try {
        if (times >= 5) {
            window.apps.callJsOpenWifiSetting();
        }
    } catch (e) {
    }
}


/**
 * 退出App
 * @type {number}
 */
var times2 = 0;
var clearTimesHunder2;
function utlis_exitApp() {
    times2 += 1;
    if (clearTimesHunder2 != "") {
        clearTimeout(clearTimesHunder2);
    }
    clearTimesHunder2 = setTimeout(function () {
        times2 = 0;
    }, 2000);
    try {
        if (times2 >= 5) {
            window.apps.exitApp();
        }
    } catch (e) {
    }
}


//天气接口
$(function () {
    $("#msgDiv").hide();
    (function (T, h, i, n, k, P, a, g, e) {
        g = function () {
            P = h.createElement(i);
            a = h.getElementsByTagName(i)[0];
            P.src = k;
            P.charset = "utf-8";
            P.async = 1;
            a.parentNode.insertBefore(P, a)
        };
        T["ThinkPageWeatherWidgetObject"] = n;
        T[n] || (T[n] = function () {
            (T[n].q = T[n].q || []).push(arguments)
        });
        T[n].l = +new Date();
        if (T.attachEvent) {
            T.attachEvent("onload", g)
        } else {
            T.addEventListener("load", g, false)
        }
    }(window, document, "script", "tpwidget", "//widget.thinkpage.cn/widget/chameleon.js"))
    tpwidget('init', {
        "flavor": "bubble",
        "location": "WX4FBXXFKE4F",
        "geolocation": "enabled",
        "position": "top-right",
        "margin": "10px 10px",
        "language": "zh-chs",
        "unit": "c",
        "theme": "white",
        "uid": "U5A72F83A3",
        "hash": "77994c1c689206efed9a93da26240e8e"
    });
    tpwidget('show');
    $(".context_2-SnCKa").find("div").eq(0);
    var hunder2 = setInterval(function () {
        $("#wdSpan").text($(".context_2-SnCKa").find("div").eq(1).text())
        try {
            $("#tqTpDiv").css("background-image", $(".context_2-SnCKa").next().css("background-image"));
            $("#73cd93ca-5573-11e6-beb8-9e71128cae77").hide();
        } catch (e) {
        }
        if ($(".context_2-SnCKa").find("div").eq(1).text() != undefined && $(".context_2-SnCKa").find("div").eq(1).text() != "") {
            clearInterval(hunder2);
        }
    }, 1000);
});


/**
 * 暂未开放提示框
 * */
function no_msg() {
    $("#msgDiv").text("该功能暂未开放!");
    $("#msgDiv").fadeIn(2000, function () {
        $("#msgDiv").fadeOut(2000, function () {
            $("#msgDiv").text("");
        });
    });
}

// /**
//  * 打开 选择医生窗口
//  * */
// function fn_ChangeDoctor() {
//     layer.open({
//         type: 2,
//         title: '请选择所需医生',
//         area: ['660px', '405px'],
//         /*offset: '10%',*/
//         scrollbar: false,
//         content: ctx + '/advertising/changeDoctor'
//     });
// }
// /**
//  * 打开 选择医生窗口
//  * 如果该诊所只有一个医生就直接打开 挂号刷卡窗口
//  * */
// function fn_ChangeDoctor(doctorId) {
//     debugger
//     doctortype = ${doctor.doctorType}
//     alert(doctortype)
//     if(doctortype != "Clinic_Boss"){
// //                            var index = parent.layer.getFrameIndex(window.name);
// //         doctorId = ${doctor.id}
//             fn_ClickRegistr("",doctorId);
//     }else {
//         layer.open({
//             type: 2,
//             title: '请选择所需医生',
//             area: ['660px', '405px'],
//             /*offset: '10%',*/
//             scrollbar: false,
//             content: ctx + '/advertising/changeDoctor'
//         });
//     }
// }
// /**
//  * 点击 挂号按钮 打开 挂号刷卡窗口
//  */
// function fn_ClickRegistr(msg, doctorId) {
//
//     parent.layer.open({
//         type: 2,
//         title: ['排队挂号', 'font-size:35px; height:60px; padding:8px 10px; background-color:#529b47; color:#fff;'],
//         area: ['800px', '550px'],
//         offset: ['600px', '150px'],
//         //offset: '10%',
//         scrollbar: false,
//         move: false,
//         closeBtn: 0,
//         content: ctx + '/advertising/registration?msg={0}&doctorId={1}'.format(msg, doctorId)
//         //end:parent.layer.close(layerIndex)
//     });
//
//
//     setTimeout(function () {
//         parent.layer.close()
//     }, 500)
// }