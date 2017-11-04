/**
 * Created by IntelliJ IDEA 2016.2
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 16/8/18
 * Time 下午3:37
 */

if (ctx == undefined) {
    ctx = "/phar";
}
//排队人数
var adcertSize = 0;
//获取排队信息 句柄
var advertingHunder;
$(function () {
    var index = parent.layer.getFrameIndex(window.name);
    $("#btnClose").click(function () {
        parent.window.location.reload();
        parent.layer.closeAll();
    });
    $("#msgDiv").hide();

    $('.form_date').datetimepicker({
        language: 'zh-CN',
        format: 'yyyy/mm/dd',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0,
        //开启年龄显示
        // needShowAge: true
    });


});

function setWxSingIn(id, name) {
    var confLayer = layer.confirm('您确认签到 ' + name + ' 患者吗？', {
        btn: ['确认', '取消'] //按钮
    }, function () {
        $.postJSON(ctx + "/advertising/setWxSingIn", {id: id}, function (date) {
            if (date.success) {
                layer.msg("该微信患者签到成功!")
                layer.close(confLayer);
                loadWxPage(0);
            } else {
                // console.info(date);
            }
        })
    }, function () {
        layer.close(confLayer);
    });

}

function loadWxPage(pageNo) {
    utlis_getAdvingListInfo();
    $("#wxPaiDuiList").load(ctx + '/advertising/getAdvingWxSinInListInfo?pageNow=' + pageNo);
}

/**
 * 打开 选择医生窗口
 * */
function fn_ChangeDoctor() {
    layer.open({
        type: 2,
        title: '请选择所需医生',
        area: ['660px', '400px'],
        scrollbar: false,
        /*服务器上记得取消注释，亲！*/
        content: ctx + '/adv/bombBox/changeDoctor'
    });
}

/**
 * 打开 挂号窗口
 * @param msg
 */
function fn_ClickRegistr(msg, layerIndex, doctorId) {
    parent.layer.open({
        type: 2,
        title: '排队挂号',
        area: ['500px', '300px'],
        scrollbar: false,
        content: ctx + '/advertising/registration?msg={0}&doctorId={1}'.format(msg, doctorId)
    });
    parent.layer.close(layerIndex);


}

/**
 * 叫号
 * @param userName
 * @param deptName
 */
function playVo(userName, deptName) {
    if (userName == undefined) {
        return;
    }
    var playObj = document.getElementById('tmpDiv');
    var deptNameVar = "医生处";
    if (deptName != undefined && deptName != "" && deptName != "null" && deptName != null) {
        deptNameVar = deptName;
    }
    var url = 'http://tts.baidu.com/text2audio?' +
        'tex=' +
        '请 ' + userName + ' 到' + deptNameVar + '就诊' +
        '&cuid=baidu_speech_demo&lan=zh&ctp=1&pdt=1';
    $("#tmpDiv").attr("src", url);
    playObj.play();
    setTimeout(function () {
        playObj.play();
    }, 8000);
}

/**
 *  绑卡
 * @param cardNo
 * @param cardPwd
 */
function fn_AddPatientInfo(cardNo, cardPwd, doctorId) {
    layer.open({
        type: 2,
        title: ['激活健康卡'/*, 'font-size:35px; height:60px; padding:8px 10px; background-color:#529b47; color:#fff;'*/],
        area: ['400px', '610px'],
        scrollbar: false,
        move: false,
        /*closeBtn: 0,*/
        content: ctx + '/advertising/patientInfo?cardNo=' + cardNo + "&cardPwd=" + cardPwd + "&doctorId=" + doctorId
    });
}


//循环提取 顶部公告,显示到 公告DIV中
// var nowTopMsgIndex = 0;
function loadTopRunText() {
    var tmpVal = "";
    for (one in TOP_MESAGE_ARR) {
        // tmpVal += utils_getText(TOP_MESAGE_ARR[one]);
        tmpVal += TOP_MESAGE_ARR[one];
    }
    // $("#topMessg").html($(utils_getText(tmpVal)).text());
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
        return str;
    } else {
        return str;
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
            if (callName != res.msg && res.msg != "" && res.msg != null && res.msg != "null" && res.data > 0) {
                callName = res.msg;
                console.info(res.deptName)
                playVo(callName, res.deptName);
            }
        });
    // }, 2000)

}


function utlis_getAdvingListInfo() {
    $.postJSON(ctx + "/advertising/getAdvingListInfo", {}, function (res) {

        var ul = document.createElement("ul");
        $(ul).attr("class", "clear-fax");
        var len = res.length;
        for (var s = 1; s <= (12 - len); s++) {
            res.push({id: "", patientName: "", type: "tmp"})
        }
        $(res).each(function (num, one) {
            var tmpNum = num + 1;
            if (one.id != "") {
                one.id = one.noNumber;
            }
            var li = utlis_genderLi(num, one.id, one.patientName, one.type, one);
            $(ul).append(li);
        });
        $("#advDiv").html($(ul))
    })
}

function utlis_genderLi(liNum, divNum, spanUserName, type, one) {
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

    if (one.registrationType == "WECHAT" && one.haveSingIn != "YES") {
        $(li).attr("class", "line-one");
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
    $("#msgDiv").text(msg);
    $("#msgDiv").fadeIn(3000, function () {
        $("#msgDiv").fadeOut(6000, function () {
            $("#msgDiv").text("");
        });
    });
}


/**
 * 收费明细弹框
 */
function chargeDetails(id) {
    layer.open({
        type: 2,
        title: '费用明细',
        area: ['700px', '400px'],
        scrollbar: false,
        content: ctx + '/adv/bombBox/chargeDetails?id=' + id
    });
}

/**
 * 确认信息
 */
function showInfo(id, cost) {
    layer.open({
        type: 2,
        title: '确认费用明细',
        area: ['400px', '280px'],
        scrollbar: false,
        content: ctx + '/adv/bombBox/showInfo?id={0}&realCost={1}'.format(id, cost)
    });
}

/**
 * 退费信息
 */
function alreadyInfo(emrId, status, isChiefNurse) {
    if (isChiefNurse != "YES") {
        Alert.error("只有主护士才能进行退费操作!");
        return;
    }
    if (status == "Normal") {
        Alert.warning("该病历有新药品增加,请先收费!");
        return;
    }
    if (status != "Have_Dispensing_Back" && status != "CHARGE") {
        Alert.warning("该病历已经发药并且暂未退药,无法进行退费操作!");
        return;
    }
    layer.open({
        type: 2,
        title: '退费信息',
        area: ['400px', '300px'],
        scrollbar: false,
        content: ctx + '/adv/bombBox/alreadyInfo?emrId=' + emrId
    });
}

function backMoney(emrId, backMoney) {
    $.postJSON(ctx + "/adv/bombBox/backMoneyDetil",
        {
            emrId: emrId,
            backMoney: backMoney
        },
        function (ret) {
            if (ret.success) {
                Alert.success("退费操作成功!");
                hmp_reload(window.parent);
            } else {
                Alert.error("退费操作失败!");
            }
        }
    )
}

/**
 * 欠费信息
 */
function arrearsInfo() {
    layer.open({
        type: 2,
        title: '欠费信息',
        area: ['400px', '280px'],
        scrollbar: false,
        content: ctx + '/adv/bombBox/arrearsInfo'
    });
}

function updateStatus(id, status, realCost) {
    $.postJSON(ctx + "/adv/bombBox/updateStatus",
        {id: id, status: status, realCost: realCost}, function (ret) {
            if (ret.success) {
                parent.layer.msg("操作成功");
                setTimeout(function () {
                    parent.parent.parent.window.location.reload();
                }, 1000);
            } else {
                parent.layer.msg("操作成功,请联系技术人员!");
            }
        });

}


/**
 * 药品清单弹框
 */
function pharmacyDetails(id) {
    layer.open({
        type: 2,
        title: '药品清单',
        area: ['700px', '400px'],
        scrollbar: false,
        content: ctx + '/adv/bombBox/pharmacyDetails?id=' + id
    });
}

/**
 * 已发 药品清单弹框
 */
function hasMedicineDetails(id) {
    layer.open({
        type: 2,
        title: '药品清单',
        area: ['700px', '450px'],
        scrollbar: false,
        content: ctx + '/adv/bombBox/hasMedicineDetails?id=' + id
    });
}

/**
 * 已退 药品清单弹框
 */
function retiredMedicineDetails(emrId) {
    layer.open({
        type: 2,
        title: '药品清单',
        area: ['700px', '450px'],
        scrollbar: false,
        content: ctx + '/adv/bombBox/retiredMedicineDetails?emrId=' + emrId
    });
}

/**
 *  检查结果
 */
function inspectionResult(id) {
    layer.open({
        type: 2,
        title: '检查结果',
        area: ['560px', '500px'],
        scrollbar: false,
        content: ctx + '/adv/bombBox/inspectionResult?id=' + id
    });
}


/**
 *  已完成 检查结果
 */
function inspectionResultFinish(id) {
    layer.open({
        type: 2,
        title: '检查结果',
        area: ['560px', '500px'],
        scrollbar: false,
        content: ctx + '/adv/bombBox/hasCompletedResult?id=' + id
    });
}

/**
 *  获取指定的 复选框 对应name的值数组
 * @param boxName
 * @returns {Array}
 */
function getCheckboxVal(boxName) {
    var valObj = new Array();
    $("input[type=checkbox]:checked").each(function (index, val) {
        valObj.push($(this).val());
    })

    return valObj;
}

/**
 *  图片显示
 * */

function fn_ImageShow() {
    var index = parent.layer.open({
        maxmin: true,
        type: 2,
        title: '图片预览',
        area: ['850px', '600px'],
        scrollbar: false,
        content: ctx + '/adv/bombBox/imageShow'
    });
    layer.full(index);
}

/**
 *  打印收据 2016-11-16 11:36:50
 * */
function fn_printReceipt(id) {
    layer.open({
        type: 2,
        title: '打印收据',
        area: ['180mm', '100mm'],
        scrollbar: false,
        content: ctx + '/adv/print/printReceipt?id=' + id
    });
    // window.open("/adv/print/printReceipt")
    // = '/adv/print/printReceipt';
}

/**
 *  修改零售总额
 *  */
function editPrice(id, val, obj, isAllowUpdatePrice) {
    if (!isAllowUpdatePrice) {
        layer.msg("药品价格被设置不能修改！");
        return;
    }
    layer.prompt({title: '修改零售总额', value: val, maxlength: 6}, function (value, index) {
        $.postJSON(
            ctx + "/adv/bombBox/updatePrice",
            {
                id: id,
                price: value,
                oldPrice: val
            },
            function (ret) {
                if (ret.success) {
                    layer.msg("修改成功!");
                    setTimeout(function () {
                        window.location.reload();
                    }, 1001)
                }
            }
        );
    });
}
/**
 *  删除零售订单
 * */
function delRetailTr(elem) {
    event.stopPropagation();
    $(elem).parent().parent().remove();
}

/**
 *  订单详情
 * */
function retailDetails(retailId, orderId) {
    layer.open({
        type: 2,
        title: '订单号：' + orderId,
        area: ['820px', '420px'],
        scrollbar: false,
        content: getCtx() + '/retail/retailDetails?retailId={0}'.format(retailId)
    });
}

function showBox() {
    var card = layer.confirm('请问患者有没有健康卡？', {
        btn: ['有卡', '无卡', '取消']
    }, function () {
        parent.layer.open({
            type: 2,
            title: '关联健康卡号',
            area: ['500px', '300px'],
            scrollbar: false,
            content: '/retail/bombBox/registration'
        });
    }, function () {
        $.postJSON("/retail/noCard", function (data) {
            if (data.success) {
                window.location.href = '/retail/RetailBilling';
            }
        })
    }, function () {
        layer.close(card);
    });
}
/**
 * 选择药品
 * */
function selectMedic(elem) {
    layer.open({
        type: 2,
        title: '选择药品',
        area: ['560px', '380px'],
        scrollbar: false,
        content: '/adv/selectMedic'
    });
}

/**
 *  零售开单
 *  费用明细
 * */
function retailChargeDetails(retailId) {
    layer.open({
        type: 2,
        title: '费用明细',
        area: ['400px', '180px'],
        scrollbar: false,
        content: '/retail/bombBox/retailChargeDetails?retailId={0}'.format(retailId)
    });
}

/**
 *  拍摄照片
 * */
function takePhotosBox(doctorId, id) {
    layer.open({
        type: 2,
        title: '拍摄照片',
        area: ['525px', '445px'],
        scrollbar: true,
        content: '/adv/bombBox/takePhotos?doctorId=' + doctorId + "&id=" + id
    });
}


/**
 *  退费详情弹框
 */
function refundDetails(elem) {
    layer.open({
        type: 2,
        title: '退费清单',
        area: ['580px', '450px'],
        scrollbar: false,
        content: ctx + '/adv/bombBox/refundDetails'
    });
}

//延时刷新
function hmp_reload(parent, time) {
    if (!time) {
        time = 1000;
    }
    setTimeout(function () {
        if (parent) {
            parent.window.location.reload();
        } else {
            window.location.reload();
        }
    }, time)
}


