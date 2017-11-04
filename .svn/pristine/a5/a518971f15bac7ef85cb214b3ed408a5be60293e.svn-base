/**
 * Created by IntelliJ IDEA 2016.2
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 16/8/23
 * Time 下午3:52
 */
var isDiagnosisFormChange = false;
var isDiagnosisFormSave = false;
var msg = "页面数据已修改，离开页面将丢失所有未保存数据。确认离开？";
var addMedId = "";
//处方单类型  为 临时处方还是 正常处方
var emrType = "";
window.onbeforeunload = function (event) {
    event = event || window.event;
    if (isDiagnosisFormChange && !isDiagnosisFormSave) {
        event.returnValue = msg;
        return msg;
    }
    return;
};
$(function () {

    $('#dxYes').click(function () {
        $('#dxDay').show()
    });
    $('#dxNo').click(function () {
        $("#dxDay").hide()
    });
    if ($("input[name='autoSend']:radio:checked").val() == "否") {
        $("#dxDay").hide()
    }

    $("#nav-diagnosis").addClass("active");
    $("#txtPatientUdid").focus().select();
    $("#btnSubmit").attr("disabled", true);
    $('#frmDiagnosis').change(function () {
        isDiagnosisFormChange = true;
    });
    $('#frmDiagnosis').submit(function () {
        isDiagnosisFormSave = true;
    });

    $('#txtPatientUdid').keydown(function (event) {
        if (event.keyCode == 13) {//keyCode=13是回车键
            var udid = $('#txtPatientUdid').val();
            $('#txtPatientUdid').val('');
            if (udid) {
                $.postJSON("/patient/login4Udid", {"udid": udid}, function (result) {
                    if (result.status === 'Passed' || result.status === 'NewCard') {
                        $('#divPatientInfo').load("/fragment/patient/" + result.data);
                        var tmp = $("#divRegistration").find("span[patientuid='" + result.data + "']").attr("ids");
                        if (tmp != undefined && tmp != "") {
                            $("#witeListId").val(tmp);
                        }
                        fn_LoadPatientOverview(result.data);
                        $("#btnSubmit").attr("disabled", false);
                    } else if (result.status === 'Used') {
                        var index = layer.confirm("此患者今天已经生成病历，是否加载历史病历？", function () {
                            layer.close(index);
                            window.location.href = "/diagnosis/" + result.data;
                        });
                    } else if (result.status === 'Invalid') {
                        layer.alert("无效的卡号或密码");
                    }
                });
            }
            return false;
        }
    });
    $('#btnLoadPatient').click(function () {
        var uid = $('#txtPatientUid').val();
        var pwd = $('#txtPlainPassword').val();
        $("#btnSubmit").attr("disabled", true);
        if (!uid || !pwd) {
            layer.alert("请正确输入用户卡号及密码");
            return false;
        }
        $.postJSON("/patient/login4Uid", {"uid": uid, "pwd": pwd}, function (result) {
            if (result.status === 'Passed' || result.status === 'NewCard') {
                $('#divPatientInfo').load("/fragment/patient/" + result.data);
                fn_LoadPatientOverview(result.data);
                $(".dia-logon").hide();
                $("#btnSubmit").attr("disabled", false);
            } else if (result.status === 'Used') {
                var index = layer.confirm("此患者今天已经生成病历，是否加载历史病历？", function () {
                    layer.close(index);
                    window.location.href = "/diagnosis/" + result.data;
                });
            } else if (result.status === 'Invalid') {
                layer.alert("无效的卡号或密码");
            }
        });
    });

    $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
        $(e.relatedTarget).children(':radio').prop("checked", false);
        $(e.target).children(':radio').prop("checked", true);
        fn_LoadDiagnosisTag(0);
        return false;
    });
    //开始就诊，页面初始化加载
    //加载挂号
    fn_loadRegistration();
    fn_LoadSymptomTag(0);
    fn_LoadWesternMedicinePage(0);
    fn_LoadChineseMedicinePage(0);
    fn_LoadDiagnosisTag(0);

    //初始化中医理疗
    fn_LoadTherapyTag(0);

    var patientList = new Bloodhound({
        datumTokenizer: Bloodhound.tokenizers.whitespace,
        queryTokenizer: Bloodhound.tokenizers.whitespace,
//                prefetch: '/patient/query?key=136',
        remote: {
            url: '/patient/query?key=%QUERY',
            wildcard: '%QUERY'
        }
    });

    $('#txtPatientUid').typeahead({
        minLength: 2,
        highlight: true
    }, {
        name: 'patient-list',
        limit: 10,
        async: true,
        display: 'uid',
        source: patientList,
        templates: {
            'empty': '<div class="tt-suggestion">无结果</div>',
            'suggestion': function (o) {
                console.info(o);
                var name = o.name == null ? "" : o.name;
                return '<div onclick="loadPatienToSeeDoctor(\'{3}\',\'{4}\')">{0} - <strong>{1}</strong> – {2}</div>'.format(o.mobile, o.uid, name, o.udid, o.status);
            }
        }
    });

    var bannerSlider = new Slider($('#banner_tabs'), {
        time: 5000,
        delay: 400,
        event: 'hover',
        auto: true,
        mode: 'fade',
        controller: $('#bannerCtrl'),
        activeControllerCls: 'active'
    });
    $('#banner_tabs .flex-prev').click(function () {
        bannerSlider.prev()
    });
    $('#banner_tabs .flex-next').click(function () {
        bannerSlider.next()
    });

    //读取短信通知表  通知用户是否有诊后短信推送发送失败!
    $.postJSON("/getInformMessage", {}, function (ret) {
        if (ret.success) {
            layer.open(
                {
                    title: "您有一条通知",
                    closeBtn: 0,
                    content: ret.msg,
                    btn: ["马上充值", "我知道了(忽略)"]
                    ,
                    yes: function () {
                        $.postJSON("/delInformMessage");
//                                    Alert.info("弹出充值页面!");
                    },
                    cancel: function () {
                        $.postJSON("/delInformMessage");
//                                    Alert.info("取消");
                    }
                }
            );

        } else {
        }

    });

    $('label').click(function () {
        var radioId = $(this).attr('name');
        $(this).attr('class', 'dia-lable checked');
        $('input[type="radio"]').removeAttr('checked') && $('#' + radioId).attr('checked', 'checked');
    });
    if ($("#toast-content").length < 1) {
        $("body").prepend("<div id='toast-content' style='top:12px;position: absolute !important;'></div>");
        //alert("页面上未找到[id='toast-content']元素,无法弹出提示框");
    }
    if (navigator) {
        if (navigator.userAgent) {
            if (navigator.userAgent.indexOf("UBrowser") < 0) {
//                        Alert.warning("易佳诊团队推荐您使用UC浏览器访问本软件,以获取更佳的体验效果。", "温馨提示!");

                $(".toast-message").click(function () {
                    window.location.href = 'http://pdds.ucweb.com/download/newest/UCBrowser/zh-cn/101/999/www';
                })
            }
        }
    }
});


function loadPatienToSeeDoctor(uuuuid, status) {
    if ($("#txtPatientUid").val().length > 10 && $("#txtPatientUid").val().indexOf("1") > -1) {
        Alert.warning("通过手机号查询患者,请输入健康卡密码!");
        return;
    }
    if (status != "DoctorHaveSee") {
        Alert.warning("该患者尚未在本诊所就诊过,请输入健康卡密码!");
        return;
    }

    $("#txtPatientUdid").val(uuuuid);
    setTimeout(function () {
        var e = $.Event("keydown");
        e.which = 13;
        e.keyCode = 13;
        $("#txtPatientUdid").trigger(e);
        Alert.success("该患者曾在本诊所就诊过,成功接诊!");
    }, 100);

}

//打印处方签
function printA5or80Cfq(type) {
    if (type == "A5打印") {
        YJZ_Printer.printUrl('../pub/printRpA5/{0}'.format("${emr.id}"));
//                判断是否还需要独立打印
        $.postJSON("/pub/isNeedAlonePrint", {emrId: "${emr.id}"}, function (ret) {
            if (ret.success) {
                YJZ_Printer.printUrl('../pub/printRpA5/{0}?type=alone'.format("${emr.id}"));
                Alert.success("打印成功,本次已启用分页打印!请勿重复点击,请稍后!");
            }
        });

    } else {
        YJZ_Printer.print80Url('../pub/printRp/{0}'.format("${emr.id}"));
        $.postJSON("/pub/isNeedAlonePrint", {emrId: "${emr.id}"}, function (ret) {
            if (ret.success) {
                YJZ_Printer.print80Url('../pub/printRp/{0}?type=alone'.format("${emr.id}"));
                Alert.success("打印成功,本次已启用分页打印!请勿重复点击,请稍后!");
            }
        });
    }
}

//打印就诊单
function printA5oOr80Jzd(type) {

    if (type == "A5打印") {
        YJZ_Printer.printUrl('../pub/printDiagnosisA5/${emr.id}');//就诊单默认还是80打印机
    } else {
        YJZ_Printer.print80Url('../pub/printDiagnosis/${emr.id}');
    }

}
var oldName = "";
function refMedPage() {
    var diagonsisName = $("#divDiagnosis span input[name='diagnosisResults']").val();
    if (diagonsisName == undefined) {
        diagonsisName = "";
    }
    if (diagonsisName != oldName) {
        oldName = diagonsisName;
        try {
            scanKey4();
            scanKey3()
        } catch (e) {

        }

    }
}

ajaxCallUrl = "/diagnosis/save";
function submitData() {
    $.ajax({
        cache: true,
        type: "POST",
        url: ajaxCallUrl,
        data: $('#frmDiagnosis').serialize(),//
        async: false,
        error: function (reg) {
            alert("Connection error");
        },
        success: function (data) {
        }
    })
}

/**
 *  修改当前处方类型    type 值有 { "tmp" ,"" (空字符串)}
 * @param type
 */
function changeEmrType(type, patientId) {
    if (patientId == "" || patientId == "null" || patientId == undefined)
        return;

    var $span = $("#emrTypeSpan");
    if (type == "tmp") {
        var tmpInupt = document.createElement("input");
        $(tmpInupt).attr("type", "hidden");
        $(tmpInupt).attr("value", "TMP");
        $(tmpInupt).attr("name", "type");
        fn_LoadPatientOverview(patientId);
        $('#divPatientInfo').load("/fragment/patient/" + patientId);
        $($span).html("");
        $($span).text("临时");
        $($span).append($(tmpInupt));
        // $($span).append($(patientIdInput));
        $("#btnSubmit").attr("disabled", false);
    } else {
        $($span).html("");
        $($span).text("普通");
        $("#btnSubmit").attr("disabled", true);
    }

}


