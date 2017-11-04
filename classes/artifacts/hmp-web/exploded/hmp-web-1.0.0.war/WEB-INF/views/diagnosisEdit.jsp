<%--@elvariable id="doctor" type="com.qiaobei.hmp.modules.entity.Doctor"--%>
<%--@elvariable id="HTML_JSON" type="java.lang.String"--%>
<%--@elvariable id="patient" type="com.qiaobei.hmp.modules.entity.Patient"--%>
<%--@elvariable id="emr" type="com.qiaobei.hmp.modules.entity.Emr"--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<%@ include file="/WEB-INF/commons/loadRe.jsp" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<jsp:useBean id="beanTmp" class="com.qiaobei.hmp.support.WeixinUtil"/>
<!DOCTYPE html>
<html lang="cn">
<head>
    <title>修改病历</title>
    <%----%>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/styles/diagnosisEdit/index.css" type="css"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/prescription/prescription.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/application.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/slider.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/print.lodop/LodopFuncs.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/print.js" type="js"/>
    <c:if test="${!beanTmp.isLocal()}">
        <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/errorScan/errorScan.js" type="js"/>
    </c:if>

    <script type="application/javascript" src="http://localhost:8000/CLodopfuncs.js?priority=1"></script>
    <object id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0
            style="position:absolute;left:0;top:-10px;"></object>
    <object id="LODOP_EM" TYPE="application/x-print-lodop" width=0 height=0
            style="position:absolute;left:0;top:-10px;"></object>
    <%-- 导入自动加载的处方药品以及检查检验等信息 --%>
    <%@ include file="./medAutoPaper.jsp" %>
    <script type="text/javascript">
        //var priceMap = new Map();
        //var tempPriceMap = priceMap;
        var divSuggestHtml;
        var addMedId = "";
        var oldName = "";
        $(function () {
            $('#dxYes').click(function () {
                $('#dxDay').show()
            });
            $('#dxNo').click(function () {
                $('#dxDay').hide()
            });
            if ($('input[name=\'autoSend\']:radio:checked').val() == "否") {
                $('#dxDay').hide()
            }
            //加载挂号
            fn_loadRegistration();
            $('#nav-diagnosis').addClass("active");
            $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
                $(e.relatedTarget).children(':radio').prop("checked", false);
                $(e.target).children(':radio').prop("checked", true);
                fn_LoadDiagnosisTag(0);
                return false;
            });
            fn_LoadSymptomTag(0);
            fn_LoadWesternMedicinePage(0);
            fn_LoadChineseMedicinePage(0);
            fn_LoadDiagnosisTag(0);

            //初始化中医理疗
            fn_LoadTherapyTag(0);
            fn_LoadTherapyPaper(${HTML_JSON});

            $('.number').keyup(function () {
                $(this).val($(this).val().replace(/[^0-9.]/g, ''));
            }).bind("paste", function () {
                $(this).val($(this).val().replace(/[^0-9.]/g, ''));
            }).css("ime-mode", "disabled");

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

            //删除所有为空元素
            for (var i = 0; i < oneMedInfo.length; i++) {
                if (oneMedInfo[i] == undefined || oneMedInfo[i].medicineId == undefined) {
                    oneMedInfo.splice(i, 1);
                }
            }
            oneMedInfo.sort(compare666);
            //删除所有为空元素
            for (var i = 0; i < oneMedInfo.length; i++) {
                if (oneMedInfo[i] == undefined || oneMedInfo[i].medicineId == undefined) {
                    oneMedInfo.splice(i, 1);
                }
            }
            for (var index = 0; index < oneMedInfo.length; index++) {
                data = oneMedInfo[index];
                medObjArr.push(data);
            }

            medObjArr = compare666(medObjArr);
            $('#divWesternItems').html("");
            $('#divChineseItems span').each(function () {
                $(this).remove();
            });
            $(medObjArr).each(function () {
                if (this.medType != undefined) {
                    fn_SelectMedicine(this, this.medType, "arr");
                } else {
                    /*这里的data是一样的含义，但是由于前面js定义的data的不规范，导致传进来的data的属性名可能不一样*/
                    fn_SelectMedicine(this, this.medicineType, "arr");
                }
            });
            debugger
            $("#cost").val("${emr.cost}");


            if ("${emr.backDays}" in [1, 2, 3, 4]) {
                $('input:radio[value="${emr.backDays}"][name="days"]').get(0).checked = true;
            } else if ("${emr.backDays}" == 4) {
                $('input:radio[value="${emr.backDays}"][name="days"]').get(0).checked = true;
                $("#thoerDays").val("${emr.backDays}");
            } else if ("${emr.backDays}" != "") {
                $('input:radio[value="4"][name="days"]').get(0).checked = true;
                $("#thoerDays").val("${emr.backDays}");
            }
            if ("${emr.backTime}" != "") {
                $('input:radio[value="${emr.backTime}"][name="times"]').get(0).checked = true;
            }

            //

            if ($("#therapyList").html().trim() == '') {

            } else {
                $(".divTherapy").prepend("<h4>中医理疗</h4>")
            }


            if ("${callAdv}" == "true") {
                $.postJSON("/fragment/callWebSocket", {}, function (re) {
                });
            }

        });
        /*--------------------------------以上是function的-----------------------------*/
        function fn_EditPatient() {
            layer.open({
                type: 2,
                title: '修改患者信息',
                area: ['500px', '400px'],
                scrollbar: false,
                content: '/fragment/patient/update/${patient.uid}',
                end: function () {
                    location.reload();
                }
            });
        }

        function printSlipPage() {
            var idex = layer.open({
                title: "分页打印",
                content: ("${ctx}/pub/printnew/{0}".format("${emr.id}")),
                area: ['520px', '600px'],
                scrollbar: false,
                type: 2,
                end: function () {
                    //                判断是否还需要独立打印
                    $.postJSON("${ctx}/pub/isNeedAlonePrint", {emrId: "${emr.id}"}, function (ret) {
                        if (ret.success) {
                            var idexAlon = layer.open({
                                title: "预览打印",
                                content: ("${ctx}/pub/printnew/{0}?type=alone".format("${emr.id}")),
                                area: ['520px', '600px'],
                                scrollbar: false,
                                type: 2,
                                end: function () {
                                    Alert.success("独立打印执行完毕!");
                                }
                            });
                            Alert.success("打印成功,本次已启用分页打印!请勿重复点击,请稍后!");
                        }
                    });
                }
            });
        }
        //打印处方签
        function printA5or80Cfq(type) {
            var printModel = '${doctor.printModel}';
            if (type == "A5打印") {
                if (printModel != "预览打印") {
                    <%--if (${emr.emrMedicineList.size()>8}) {--%>
                    printSlipPage();
                    <%--} else {--%>
                    <%--YJZ_Printer.printUrl('../pub/printRpA5/{0}'.format("${emr.id}"));--%>
                    <%--//                判断是否还需要独立打印--%>
                    <%--$.postJSON("/pub/isNeedAlonePrint", {emrId: "${emr.id}"}, function (ret) {--%>
                    <%--if (ret.success) {--%>
                    <%--YJZ_Printer.printUrl('../pub/printRpA5/{0}?type=alone'.format("${emr.id}"));--%>
                    <%--Alert.success("打印成功,本次已启用分页打印!请勿重复点击,请稍后!");--%>
                    <%--}--%>
                    <%--});--%>
                    <%--}--%>
                } else {
                    var idex = layer.open({
                        title: "预览打印",
                        content: ("${ctx}/pub/printRpA5/{0}?printModel={1}".format("${emr.id}", printModel)),
                        area: ['520px', '600px'],
                        btn: ["关闭"],
                        yes: function () {
                            layer.close(idex);
                        },
                        scrollbar: false,
                        type: 2,
                        end: function () {
                            //                判断是否还需要独立打印
                            $.postJSON("${ctx}/pub/isNeedAlonePrint", {emrId: "${emr.id}"}, function (ret) {
                                if (ret.success) {
                                    var idexAlon = layer.open({
                                        title: "预览打印",
                                        content: ("${ctx}/pub/printRpA5/{0}?printModel={1}&type=alone".format("${emr.id}", printModel)),
                                        area: ['520px', '600px'],
                                        btn: ["关闭"],
                                        yes: function () {
                                            layer.close(idexAlon);
                                        },
                                        scrollbar: false,
                                        type: 2,
                                        end: function () {
                                            Alert.success("独立打印执行完毕!");
                                        }
                                    });
                                    Alert.success("打印成功,本次已启用分页打印!请勿重复点击,请稍后!");
                                }
                            });
                        }
                    });

                }
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
        function refMedPage() {
            var diagonsisName = $('#divDiagnosis span input[name=\'diagnosisResults\']').val();
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

        /*中医理疗打印*/
        function printTherapyA5(url, data) {
            console.info(data);
            var index = layer.open({
                title: "预览打印",
                content: (url.format("${emr.id}")),
                area: ['1140px', '600px'],
                btn: ["关闭"],
                yes: function () {
                    layer.close(index);
                },
                scrollbar: false,
                type: 2

            });


        }


        function printByType(type) {
            //打印下拉选择框
            var print_url = "${ctx}";
            switch (type) {
                case "therapy":
                    print_url += "/pub/printPhyTable?emrId={0}";
                    printTherapyA5(print_url);
                    break;
                case "examLab":
                    print_url += "/pub/printItemsTable?emrId={0}";
                    printTherapyA5(print_url);
                    break;
                case "fuJia":
                    print_url += "/pub/printChargesTable?emrId={0}";
                    printTherapyA5(print_url);
                    break;
                default:
                    break;
            }
        }


        /*打印全部单据*/
        function printAll(emrId) {
            $.postJSON("${ctx}/pub/isNeedPrintType", {emrId: emrId}, function (result) {
                if (result.success) {
                    var isNeedPrint = JSON.parse(result.data);
                    printA5oOr80Jzd('<shiro:principal property='printType'/>');
                    var time = 1000;
                    if (isNeedPrint.medicine) {
                        time += 2000;
                        setTimeout(
                            printA5or80Cfq('<shiro:principal property='printType'/>'), time
                        );
                    }

                    if (isNeedPrint.therapy) {
                        time += 2000;
                        setTimeout(
                            printByType('therapy'), time
                        );
                    }

                    if (isNeedPrint.extCost) {
                        time += 2000;
                        setTimeout(
                            printByType('fuJia'), time
                        );
                    }

                    if (isNeedPrint.adviceDict) {
                        time += 2000;
                        setTimeout(
                            printByType('examLab'), time
                        );
                    }

                } else {

                }
            })
        }


    </script>
</head>
<body>
<div class="container electronic">
    <form:form action="/diagnosis/save" method="post" onsubmit="infoSubmit();return true;" modelAttribute="emr">
        <%--@elvariable id="genderMap" type="java.util.Map"--%>
        <%--@elvariable id="autoSendDay" type="java.lang.Long"--%>
        <%--@elvariable id="witeListId" type="java.lang.Long"--%>
    <input type="hidden" name="comBackDays" id="comBackDays" value="${emr.backDays}"/>
    <input type="hidden" name="comBackTimes" id="comBackTimes" value="${emr.backTime}"/>
    <input type="hidden" name="witeListId" id="witeListId" value="${witeListId}"/>

    <c:choose>
        <c:when test="${emr.type == 'TMP'}">
            <input type="hidden" value="TMP" name="type">
        </c:when>
        <c:otherwise>
            <input type="hidden" value="COMMON" name="type">
        </c:otherwise>
    </c:choose>
    <div class="row diagnosisEdit">
        <form:hidden path="id"/>
        <div class="col-sm-12 col-md-12">
            <div class="intro-sign" style="min-height: 110px">
                <div id="divRegistration" style="margin-top: -10px;"></div>
                <div class="form-inline">
                    <div class="row" id="divPatientInfo">
                        <c:import url="fragment/patientView.jsp"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-sm-6 col-md-7">
            <div class="doctors-action">
                <div style="margin:0 auto;width: 75%;display: none">
                    <ul class="nav nav-tabs sections-list" id="tabsDepartment">
                            <%--@elvariable id="departments" type="java.util.List"--%>
                        <c:forEach varStatus="status" var="dept" items="${departments}">
                            <li <c:if test="${emr.departmentId == dept.id}">class="active"</c:if>>
                                <a href="#" data-toggle="tab">
                                    <input id="departmentId${status.count}" class="hidden" name="departmentId"
                                           type="radio"
                                           value="${dept.id}"
                                           <c:if test="${emr.departmentId == dept.id}">checked="checked"</c:if>
                                           title=""/>${dept.name}
                                </a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
                <input type="hidden" name="departmentId" value="${emr.departmentId}"/>

                <div id="divDiagnosos" class="index-box">
                    <script type="application/javascript">
                        var jyHtml = "";

                        var divSp = "";
                        $(function () {

                            <c:forEach var="sug" items="${emr.emrSuggestList}">
                            divSp += ' <div class="diagnosis_${sug.id}" style="font-size: 16px">${sug.suggestContent}<a href="javascript:" onclick="removeTeSun($(this).parent())"><i class="fa fa-times"></i></a><input type="hidden" name="suggestIds" value="${sug.suggestId}" /> <input type="hidden" name="suggestContents" value="${sug.suggestContent}" /> </div>';
                            </c:forEach>

                            //setInterval('pullVarTodiv()', 200);
                            pullVarTodiv();

                        });
                        function pullVarTodiv() {
                            if ($("#divSuggest").html() != "" && $("#divSuggest").children().length > 0 && divSp != $("#divSuggest").html()) {
                                divSp = $("#divSuggest").html();
                            }

                            if (divSp != "") {
                                $("#divSuggest").html(divSp)
                            }
                            var tmp = $("#divSuggest").html();
                            if (tmp != null && tmp != "") {
                                jyHtml = $("#divSuggest").html();
                                $("#divSuggest").html(jyHtml);
                            } else if (jyHtml != "") {
                                $("#divSuggest").html(jyHtml)
                            }
                        }

                    </script>
                    <!-- Nav tabs -->
                    <ul class="nav nav-tabs" role="tablist">
                        <li role="presentation" class="active"><a href="#tabnoe"
                                                                  aria-controls="tabnoe" role="tab"
                                                                  data-toggle="tab">患者主诉
                            <p class="bottom-line">></p>
                        </a></li>
                        <li role="presentation"><a href="#tabtwo"
                                                   aria-controls="profile" role="tab"
                                                   data-toggle="tab">体格检查
                            <p class="bottom-line"></p>
                        </a>
                        </li>

                        <li role="presentation"><a href="#tabthree"
                                                   aria-controls="profile" role="tab"
                                                   data-toggle="tab">初步诊断
                            <p class="bottom-line"></p>
                        </a>
                        </li>

                        <li role="presentation" onclick="refMedPage()"><a href="#tabfour"
                                                                          aria-controls="profile" role="tab"
                                                                          data-toggle="tab">处方西药
                            <p class="bottom-line"></p>
                        </a>
                        </li>

                        <li role="presentation" onclick="refMedPage()"><a href="#tabfive"
                                                                          aria-controls="profile" role="tab"
                                                                          data-toggle="tab">处方中药
                            <p class="bottom-line"></p>
                        </a>
                        </li>
                        <li role="presentation" onclick="refMedPage()"><a href="#tabsix" aria-controls="profile"
                                                                          role="tab"
                                                                          data-toggle="tab">中医理疗
                            <p class="bottom-line"></p>
                        </a>
                        </li>
                    </ul>

                    <!-- Tab panes -->
                    <div class="tab-content">
                        <div role="tabpanel" class="tab-pane active" id="tabnoe">
                            <div class="diagnose">

                                <div id="divSymptom" class="label-box" style="position: relative;">
                                        <%-- 添加需求 主诉手写 --%>
                                    <div class="other-box">
                                        <span>其他</span>
                                        <div><input type="text" name="remarks" value="${emr.remarks}" title=""></div>
                                    </div>
                                        <%-- end --%>
                                    <c:forEach var="tag" items="${emr.mainSuitList}" varStatus="status">
                                        <span class="tag" onclick="fn_RemoveElement(this)">
                                            <input name="mainSuits" value="${tag}" type="hidden">${tag}</span>
                                    </c:forEach>
                                </div>
                                <div id="divSymptomTags" class="patient-label-box"></div>
                            </div>
                        </div>

                        <div role="tabpanel" class="tab-pane" id="tabtwo" class="tabtwos">
                            <div class="form-inline">
                                <div class="row">
                                        <%--@elvariable id="emr" type="com.qiaobei.hmp.modules.entity.Emr"--%>
                                    <c:forEach items="${emr.vitalSignList}" var="vs" varStatus="status">

                                        <div class="<c:if test="${status.count<2}">notbr pull-left</c:if><c:if test="${status.count>1}">col-md-6</c:if>">
                                                <%--@elvariable id="vitalSignLabels" type="java.util.Map"--%>
                                            <c:if test="${not empty vitalSignLabels[vs.type]}">
                                                <label for="vitalSign${status.index}">${vitalSignLabels[vs.type]}</label>
                                            </c:if>
                                            <input type="hidden" name="vitalSignList[${status.index}].type"
                                                   value="${vs.type}"/>

                                            <c:if test="${vs.type!='Ot'}">
                                                <input id="vitalSign${status.index}" type="text"
                                                       name="vitalSignList[${status.index}].value"
                                                       value='<fmt:formatNumber value="${vs.value}" pattern="###.#" />'
                                                       class="form-control" onchange="pullItToPager(this)"/>
                                            </c:if>
                                                <%--如果是'其他'这一项，样式得改变--%>
                                            <c:if test="${vs.type=='Ot'}">
                                                <input id="vitalSign${status.index}" type="text"
                                                       name="vitalSignList[${status.index}].value"
                                                       value='${vs.value}'
                                                       class="" onchange="pullItToPager(this)"
                                                       style='width:230px;border: 0px;border-bottom: 1px solid #0498d2;outline: none;'

                                                />
                                            </c:if>

                                                <%--@elvariable id="vitalSignUnits" type="java.util.Map"--%>
                                            <c:if test="${not empty vitalSignUnits[vs.type]}">
                                                <label style="margin-right: 13px;">${vitalSignUnits[vs.type]}</label>
                                            </c:if>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                            <div class="form-group" style="margin-top: 15px;">
                                <button type="button" class="btn btn-success" style="margin-right: 10px;"
                                        onclick="inspectionItemsDetail()">添加检查/检验项目
                                </button>
                                <button type="button" class="btn btn-success" style="margin-right: 10px;"
                                        onclick="chargesDetails()">附加项目费用
                                </button>
                                <button type="button" class="btn btn-success" onclick="takePhotoBox('JianYan')">
                                    上传检查/检验报告
                                </button>
                                    <%--<button type="button" class="btn btn-success" onclick="takePhotoBox('JianCha')">上传检验报告--%>
                                    <%--</button>--%>
                            </div>
                        </div>
                        <div role="tabpanel" class="tab-pane" id="tabthree">
                            <div class="diagnose">
                                <div id="divDiagnosis" class="label-box">
                                    <c:forEach var="tag" items="${emr.diagnosisList}">
                                <span class="tag" onclick="fn_RemoveElement(this)">
                                    <input name="diagnosisResults"
                                           value="${tag.name}"
                                           type="hidden">${tag.name}
                                    <input type="hidden" name="diagnosisTagIds" value="${tag.id}">
                                </span>
                                    </c:forEach>
                                </div>
                                <div id="divDiagnosisTags" class="diagnosis-label-box">
                                    <h4 class="form-inline" style="margin-bottom: 10px">诊后建议</h4>
                                    <div id="divSuggest" class="medicine-label-box">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div role="tabpanel" class="tab-pane" id="tabfour">
                            <div class="diagnose">
                                <div id="divWesternRp"></div>
                                <div id="divWesternItems" class="label-box" style="display: none;">
                                </div>
                                <div id="divWesternMedicines" class="medicine-label-box"></div>
                                <div class="form-group" style="margin-bottom: 0; margin-top: 20px;">
                                    <a href="javascript:;" class="btn btn-default"
                                       onclick="takePhotoBox('Western')">上传西药处方笺</a>
                                </div>
                            </div>
                        </div>

                        <div role="tabpanel" class="tab-pane" id="tabfive">
                            <div class="diagnose">
                                <div id="divChineseRp"></div>
                                <div id="divChineseItems" class="label-box number-fu" style="display: none;">
                                    <div class="chinesty">
                                        <input type="number" min="0" name="chineseQty" class="form-control"
                                               style="width: 50px"
                                               id="chineseQty" onkeyup="whenChangZhongMedFuSize(this)"
                                               onchange="whenChangZhongMedFuSize(this)" value="${emr.chineseQty}"
                                               onkeydown="doBack(this)"
                                               placeholder="0"/>&nbsp;副
                                    </div>
                                </div>
                                <div id="divChineseMedicines" class="medicine-label-box"></div>
                                <div class="form-group" style="margin-bottom: 0; margin-top: 20px;">
                                    <a href="javascript:;" class="btn btn-default"
                                       onclick="takePhotoBox('Chinese')">上传中药处方笺</a>
                                </div>
                            </div>
                        </div>
                        <div role="tabpanel" class="tab-pane" id="tabsix">
                            <div class="diagnose">

                                <div class="medicine-label-box">
                                    <div id="divTherapy" class="tab-box-list">


                                    </div>

                                </div>

                            </div>

                        </div>
                    </div>

                </div>
            </div>
        </div>
        <div class="col-sm-6 col-md-5" style="padding-left: 0;">
            <div class="right-show-box">
                <div class="main mCenter ">
                    <div class="main-header pd_l ">
                        <h1><shiro:principal
                                property='outpatientService'/>处方笺
                            <span class="biao-shi-span" id="emrTypeSpan">
                                <c:if test="${patient.status == 'Tmp'}">
                                    临时
                                </c:if>
                                <c:if test="${patient.status != 'Tmp'}">
                                    普通
                                </c:if>

                        </span>
                        </h1>
                    </div>

                    <div>
                        <ul class="main-sTitle">
                            <ul>
                                <li>
                                    <span>姓名:</span>
                                    <input style=" width: 4.2em" id="paperName" type="text" class=" "
                                           value="${patient.name}" readonly title=""/>

                                        <%--<div style="width: 4.2em;" id="paperName">${patient.name}</div>--%>
                                </li>
                                <li>
                                    <span>性别:</span>
                                    <input id="paperSex" type="text" style="width: 2em" class=" "
                                           value="${genderMap[patient.gender]}" readonly
                                           title=""/>
                                        <%--<div id="paperSex" style="width: 2em;">${genderMap[patient.gender]}</div>--%>
                                </li>
                                <li>
                                    <span>年龄:</span>
                                    <input id="paperAge" type="text" style="width: 3em" class=""
                                           value="${patient.age}" readonly title=""/>
                                        <%--<div id="paperAge" style="width: 3em;">${patient.age}</div>--%>
                                </li>
                                <li>
                                    <span>病史:</span>

                                    <input id="patientTags" type="text" style="width: 12em;" value="" readonly
                                           title=""/>
                                </li>

                            </ul>
                            <ul>
                                <li>
                                    <span>联系方式:</span>
                                    <input id="paperPhoneNum" style="width: 8em" type="text" class=" "
                                           value="${patient.mobile}" readonly title=""/>
                                        <%--<div id="paperPhoneNum" style="width: 10em;">${patient.mobile}</div>--%>
                                </li>
                                <li>
                                    <span>地址:</span>
                                        <%--style="width:15.2em"--%>
                                    <input id="paperAddrs" type="text" style="width: 16.5em;" readonly
                                           value="${patient.province}${patient.city}${patient.area}${patient.address}"
                                           title=""/>
                                        <%--<div id="paperAddrs" style="min-width: 15.3em;">${patient.province}${patient.city}${patient.area}${patient.address}</div>--%>
                                </li>
                            </ul>
                            <ul>
                                <li>
                                    <span>患者主诉:</span>

                                        <%--<div id="talkcontentone" style="min-width: 8em;"></div>--%>
                                    <div id="talkcontentone"
                                         style="width: 27em; word-break:keep-all; white-space:nowrap; overflow:hidden; ">
                                        <c:if test="${not empty emr.mainSuit}">
                                            <c:forEach items="${emr.mainSuit.split(';')}" var="nowOne">
                                                <span class="tag">${nowOne}</span>
                                            </c:forEach>
                                        </c:if>

                                        <c:if test="${not empty emr.remarks}">
                                            <span class="">${emr.remarks}</span>
                                        </c:if>

                                    </div>
                                </li>
                            </ul>
                            <ul>

                                <li>
                                    <span>体征:</span>

                                    <div id="talkSmtz"
                                         style="width: 12em; word-break:keep-all; white-space:nowrap; overflow:hidden; ">
                                            <%--@elvariable id="vitalSignUnits" type="java.util.Map"--%>
                                        <c:forEach items="${emr.vitalSignList}" var="vs" varStatus="status">
                                            <%--@elvariable id="vitalSignLabels" type="java.util.Map"--%>
                                            <c:if test="${not empty vs.value }">
                                                <c:if test="${vitalSignLabels[vs.type]=='血压'}">
                                                    <span class="tag">${vitalSignLabels[vs.type]}${vs.value}${vitalSignUnits[vs.type]}
                                                    <c:if test="${not empty vitalSignUnits[vs.type]}">;&nbsp;
                                                    </c:if>
                                                </c:if>
                                                <c:if test="${vitalSignLabels[vs.type]=='/'}">

                                                    ${vitalSignLabels[vs.type]}${vs.value}${vitalSignUnits[vs.type]}
                                                    <c:if test="${not empty vitalSignUnits[vs.type]}">;&nbsp;
                                                    </c:if>
                                                    </span>
                                                </c:if>

                                                <c:if test="${vitalSignLabels[vs.type]!='/' && vitalSignLabels[vs.type]!='血压' }">
                                                    <c:if test="${vitalSignLabels[vs.type]!='其他'}">
                                                        <span class="tag">${vitalSignLabels[vs.type]}${vs.value}${vitalSignUnits[vs.type]}
                                                                <c:if test="${not empty vitalSignUnits[vs.type]}">;&nbsp;
                                                                </c:if>
                                                        </span>
                                                    </c:if>

                                                    <c:if test="${vitalSignLabels[vs.type]=='其他'}">
                                                        <span class="tag"
                                                              id="ot_text">${vs.value}${vitalSignUnits[vs.type]}
                                                                <c:if test="${not empty vitalSignUnits[vs.type]}">;&nbsp;
                                                                </c:if>
                                                        </span>
                                                    </c:if>

                                                </c:if>
                                            </c:if>

                                        </c:forEach>
                                    </div>

                                </li>
                                <li>
                                    <span>初步诊断:</span>
                                    <div id="talkChuBuZd"
                                         style="width: 12.5em; word-break:keep-all; white-space:nowrap; overflow:hidden; ">
                                        <c:if test="${not empty emr.diagnosisResult}">
                                            <c:forEach items="${emr.diagnosisResult.split(';')}" var="nowOne2">
                                                <span class="tag">${nowOne2}</span>
                                            </c:forEach>
                                        </c:if>
                                    </div>
                                </li>
                            </ul>

                        </ul>
                    </div>


                    <div style="min-height: 100mm;">
                        <section class="main-contain" style="margin-top: -1em;min-height: 550px">
                                <%--<span class="font-2_5" style="font-size: 32px;">R </span>
                                <span class="font-2" style="font-size: 32px;">p：</span>--%>
                            <div class="tabbable">
                                <ul class="nav-pills pills-list clearfix" role="tablist">
                                    <li class="active"><a href="#med" id="medList" class="btn btn-default"
                                                          data-toggle="tab">Rp</a>
                                    </li>
                                    <li><a href="#phy" id="therapyLi" class="btn btn-default" data-toggle="tab">中医理疗</a>
                                    </li>
                                    <li><a href="#inspect" class="btn btn-default" id="inspectLi"
                                           data-toggle="tab">检查/检验</a></li>
                                    <li><a href="#charges" class="btn btn-default" id="fujiaId"
                                           data-toggle="tab">附加费用</a></li>
                                </ul>
                                <div class="tab-content">
                                    <div id="med" role="tabpanel" class="tab-pane active">
                                        <div class="main-contain-detail" style="float:none;"
                                             id="medContext"></div>
                                        <div class="form-group" id="medEmrFileContext">
                                            <c:forEach items="${emr.emrFileList}" var="emrFile">
                                                <c:if test="${emrFile.fileType=='Western'||emrFile.fileType=='Chinese'}">
                                                    <p>
                                                        <a href="#"
                                                           onclick="openImg('/fileDir/${emr.doctor.id}/${emrFile.fileName}.png')"
                                                            <%--target="_blank"--%>
                                                        >
                                                            <span>${emrFile.getMetaValue(emrFile.fileType)}处方笺：
                                                                <fmt:formatDate value="${emrFile.createOn}"
                                                                                pattern="yyyy/MM/dd HH:mm"/>
                                                            </span>
                                                        </a>
                                                        <i class="fa fa-trash-o"
                                                           style="font-size:16px; color: #eab065;line-height: 1.5; padding-left: 10px;"
                                                           onclick="$(this).parent().remove()"></i>
                                                        <input type="hidden" name="emrFileName"
                                                               value="${emrFile.fileName}">
                                                        <input type="hidden" name="emrFileType"
                                                               value="${emrFile.fileType}"></p>
                                                </c:if>
                                            </c:forEach>
                                        </div>
                                    </div>
                                    <div id="phy" role="tabpanel" class="tab-pane">
                                        <div class="div-border">
                                            <h3 class="text-center">中医理疗</h3>
                                            <div>
                                                    <%--<span style="width: 25%; padding-left: 10px;" id="patientName">姓名：${patient.name}</span>
                                                    <span style="width: 18%;" id="patientGender">性别：${genderMap[patient.gender]}</span>
                                                    <span style="width: 20%;" id="patientAge">年龄：${patient.age}</span>
                                                    <span style="width: 34%;" id="patientMobile">电话：${patient.mobile}</span>--%>
                                                <span style="width: 25%; padding-left: 10px;">姓名：
                                                        <span id="patientName">
                                                                ${emr.patientName}
                                                        </span>

                                                    </span>
                                                <span style="width: 18%;">性别：
                                                        <span id="patientGender">
                                                                ${genderMap[patient.gender]}
                                                        </span>
                                                    </span>
                                                <span style="width: 20%;">年龄：
                                                        <span id="patientAge">
                                                                ${patient.age}
                                                        </span>

                                                    </span>
                                                <span style="width: 34%;">电话：
                                                        <span id="patientMobile">
                                                                ${patient.mobile}
                                                        </span>

                                                    </span>
                                            </div>
                                        </div>
                                        <div class="phy-box divTherapy">
                                                <%--<h4>中医理疗</h4>--%>
                                            <div id="therapyList">

                                            </div>
                                        </div>
                                    </div>
                                    <div id="inspect" role="tabpanel" class="tab-pane">
                                        <div class="div-border">
                                            <h3 class="text-center">项目申请单</h3>
                                            <div>
                                                    <span style="width: 25%; padding-left: 10px;">姓名：
                                                        <span id="examName">

                                                        </span>
                                                    </span>
                                                <span style="width: 18%;">性别：
                                                        <span id="examGender">

                                                        </span>
                                                    </span>
                                                <span style="width: 20%;">年龄：
                                                        <span id="examAge">

                                                        </span>
                                                    </span>
                                                <span style="width: 34%;">电话：
                                                        <span id="examMobo">

                                                        </span>
                                                    </span>
                                            </div>
                                        </div>
                                        <div class="form-group" id="medEmrFileExamLbe">
                                            <c:forEach items="${emr.emrFileList}" var="emrFile">
                                                <c:if test="${emrFile.fileType=='JianYan'||emrFile.fileType=='JianCha'}">
                                                    <p>
                                                        <a href="#"
                                                           onclick="openImg('/fileDir/${emr.doctor.id}/${emrFile.fileName}.png')"
                                                            <%--target="_blank"--%>
                                                        >
                                                            <span>${emrFile.getMetaValue(emrFile.fileType)}报告：
                                                             <fmt:formatDate value="${emrFile.createOn}"
                                                                             pattern="yyyy/MM/dd HH:mm"/>
                                                            </span>
                                                        </a>
                                                        <i class="fa fa-trash-o"
                                                           style="font-size:16px; color: #eab065;line-height: 1.5; padding-left: 10px;"
                                                           onclick="$(this).parent().remove()"></i>
                                                        <input type="hidden" name="emrFileName"
                                                               value="${emrFile.fileName}">
                                                        <input type="hidden" name="emrFileType"
                                                               value="${emrFile.fileType}"></p>
                                                </c:if>
                                            </c:forEach>
                                        </div>
                                    </div>
                                    <div id="charges" role="tabpanel" class="tab-pane">
                                        <div class="div-border">
                                            <h3 class="text-center">附加项目费用</h3>
                                            <div>
                                                <span style="width: 25%; padding-left: 10px;">姓名：${patient.name}</span>
                                                <span style="width: 18%;">性别：${genderMap[patient.gender]}</span>
                                                <span style="width: 20%;">年龄：${patient.age}</span>
                                                <span style="width: 34%;">电话：${patient.mobile}</span>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </section>

                    </div>

                    <div class="main-footer">
                        <table style="border-top: 2px solid black;width: 100%;">
                            <tbody>
                            <tr>
                                <td>
                                    <span>处方医生:</span>
                                    <input type="text" value="<shiro:principal property='name'/>" title=""/>
                                </td>
                                <td>
                                    <span>药剂师:</span>
                                    <input type="text" title=""/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                        <%--<b style="font-size: 14px;font-weight: normal">收费金额：</b>--%>
                                        <%--<input type="text" name="cost" value="${emr.cost}" style="width: 100px"  class="form-control"/>--%>

                                    <button onclick="selectChargeDetail()" type="button" class="btn btn-success"
                                            style="border-radius: 20px; outline: none;">收费明细
                                    </button>
                                    <input type="text"
                                           name="cost"
                                           readonly
                                           id="cost"
                                           style="width: 100px"
                                           class="form-control"
                                           value="${emr.cost}"
                                           title=""/>
                                    <input type="hidden" id="hiddenSetCost" value="${emr.cost}"/>
                                    <input type="hidden" id="hiddenSetCostPrice" value="0"/>
                                </td>
                                <td>
                                    <span>时间:</span>
                                    <span><fmt:formatDate value="${emr.createOn}" pattern="yyyy/MM/dd HH:mm"/></span>

                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">发送回访短信
                                    <input id="dxYes" type="radio" name="autoSend" value="是"
                                           style="margin-left: 5px;width: 14px; vertical-align: middle; margin-top: 0;"
                                        <%--@elvariable id="autoSend" type="java.lang.String"--%>
                                           <c:if test="${'是'.equals(autoSend)}">checked='checked'</c:if> title="">
                                    是
                                    <input id="dxNo" type="radio" name="autoSend"
                                           style="width: 14px; vertical-align: middle; margin-top: 0;" value="否"
                                           <c:if test="${!'是'.equals(autoSend)}">checked='checked'</c:if> title=""> 否

                                    <span id="dxDay">
                                            <span style="padding-left: 20px; padding-right: 5px;">就诊过后</span>
                                            <input name="autoSendDay" type="number"
                                                   value="${autoSendDay}"
                                                   style="width: 60px; height: 32px; font-size: 16px; border-radius: 5px; border: 1px solid #ccc;"
                                                   class="text-center" title="">
                                            <span>天发送</span>
                                             <input type="hidden" id="sendMsgInfo" value="${emr.sendMsgInfo}"
                                                    name="sendMsgInfo">
                                            <a href="#" onclick="fn_CareMes()" style="margin-left: 15px;">编辑/预览</a>
                                        </span>
                                </td>
                                    <%--<td>就诊过后 天发送</td>--%>
                            </tr>
                            </tbody>
                        </table>
                    </div>

                </div>
                <div class="<%--diagnose--%> clearfix">

                    <div class="button-item text-center btn-boxs">
                            <%--@elvariable id="previous" type="java.lang.Long"--%>
                        <c:if test="${not empty previous}">
                            <a href="/diagnosis/${previous}" class="btn btn-default"
                               style="margin-right: 0;width: 72px">
                                上一位</a>
                        </c:if>
                            <%--@elvariable id="next" type="java.lang.Long"--%>
                        <c:if test="${not empty next}">
                            <a href="/diagnosis/${next}" class="btn btn-default" style="margin-right: 0;width: 72px">
                                下一位</a>
                        </c:if>
                        <c:if test="${not empty emr.id}">
                            <%--//预览打印处方笺--%>
                            <%--<a onclick="javascript:printA5or80Cfq('<shiro:principal property='printType'/>')"
                               class="btn btn-default" style="width: 89px"> 打印处方笺
                            </a>--%>

                            <div class="dropdown" style="width: 102px; display: inline-block;">
                                <a href="#" class="dropdown-toggle btn btn-default" data-toggle="dropdown"
                                   style="width: 100%;">
                                    打印选择
                                    <b class="caret"></b>
                                </a>
                                <ul class="dropdown-menu">
                                    <li><a href="#" onclick="printAll(${emr.id})">打印全部</a></li>
                                    <li><a href="#"
                                           onclick="printA5or80Cfq('<shiro:principal property="printType"/>')"
                                    >打印处方笺</a></li>
                                    <li><a href="#"
                                           onclick="printA5oOr80Jzd('<shiro:principal property="printType"/>')"
                                    >打印就诊单</a></li>
                                    <li><a href="#" onclick="printByType('therapy')">打印理疗单</a></li>
                                    <li><a href="#" onclick="printByType('examLab')">打印检查单</a></li>
                                    <li><a href="#" onclick="printByType('fuJia')">打印附加费</a></li>
                                </ul>
                            </div>


                            <%-- <select id="print_type" class="btn btn-default btn-select"
                                     style="/*width: 102px;display: inline-block; padding: 8px 4px; height: 44px;*/">
                                 <option value="all">打印全部</option>
                                 <option value="">打印处方笺</option>
                                 <option value="therapy">打印理疗单</option>
                                 <option value="examLab">打印检查单</option>
                                 <option value="fuJia">打印附加费</option>
                             </select>--%>
                            <%-- <a onclick="javascript:printA5oOr80Jzd('<shiro:principal property='printType'/>')"
                                class="btn btn-default" style="width: 89px"> 打印就诊单
                             </a>--%>
                        </c:if>
                        <form:button type="submit" name="btnSubmit" class="btn btn-default"
                                     style="width: 72px"> 保存信息</form:button>

                        <a onclick="fn_AddPatient()" class="btn btn-lg btn-default pull-right"> 临时处方</a>

                    </div>
                </div>
            </div>
        </div>
    </div>

    <div style="width: 100%;">
            <%-- TODO 暂时不做该功能 就诊病人历史检查检验信息 --%>
            <%--<div class="report-items">--%>
            <%--<c:import url="/fragment/phyOverview"/>--%>
            <%--</div>--%>
        <div id="divPatientOverView" class="report-items">
            <c:import url="/fragment/patient/${patient.uid}/overview?refer=true"/>
        </div>
        <div id="banner_tabs" class="flexslider">
            <ul class="slides">
                    <%--@elvariable id="adlist" type="java.util.List"--%>
                <c:choose>
                    <c:when test="${not empty adlist}">

                        <c:forEach items="${adlist}" var="ad">
                            <c:if test="${not empty ad.file.fileName}">
                                <li>
                                    <a target="_blank" href="${ad.url}">
                                        <img src="${ctx}/temp/${ad.file.fileName}"/>
                                    </a>
                                </li>
                            </c:if>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <li><a href="#"><img src="${ctx}/assets/images/ad/1.jpg"/></a></li>
                        <li><a href="#"><img src="${ctx}/assets/images/ad/2.jpg"/></a></li>
                        <li><a href="#"><img src="${ctx}/assets/images/ad/3.jpg"/></a></li>
                        <li><a href="#"><img src="${ctx}/assets/images/ad/4.jpg"/></a></li>
                        <li><a href="#"><img src="${ctx}/assets/images/ad/5.jpg"/></a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
            <ul class="flex-direction-nav">
                <li><a class="flex-prev" href="javascript:">Previous</a></li>
                <li><a class="flex-next" href="javascript:">Next</a></li>
            </ul>
        </div>
    </div>
</div>
</form:form>
</body>
</html>