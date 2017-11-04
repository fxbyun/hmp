<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<jsp:useBean id="now" class="java.util.Date"/>
<jsp:useBean id="beanTmp" class="com.qiaobei.hmp.support.WeixinUtil"/>
<!DOCTYPE html>
<html lang="cn">
<head>
    <title>医生坐诊</title>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/typeahead/typeahead.bundle.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/prescription/prescription.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/application.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/slider.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/styles/diagnosis/index.css" type="css"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/diagnosis/index.js" type="js"/>
    <c:if test="${!beanTmp.isLocal()}">
        <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/errorScan/errorScan.js" type="js"/>
    </c:if>
    <script type="application/javascript">
        var divSuggestHtml;
        $(function () {
            $("#emrPatientSelect").select2({
                language: "zh-CN"
            });

        });
        function clearOtherInputVal() {
            $("#txtPatientUid").val("");
            $("#txtPlainPassword").val("");
            var uuuuid = $("#emrPatientSelect").find("option:selected").val();
            if (uuuuid == undefined || uuuuid == "") {
                return false;
            }
            $("#txtPatientUdid").val(uuuuid);

            setTimeout(function () {
                var e = $.Event("keydown");
                e.which = 13;
                e.keyCode = 13;
                $("#txtPatientUdid").trigger(e);
            }, 500);
        }
    </script>
</head>
<body>
<div class="container electronic diagnosis">
    <div id="shade" hidden="hidden">。。。。。。
    </div>
    <form:form action="/diagnosis/save" id="frmDiagnosis" method="post" onsubmit="infoSubmit();return true;"
               modelAttribute="emr">

        <input type="hidden" name="comBackDays" id="comBackDays"/>
        <input type="hidden" name="comBackTimes" id="comBackTimes"/>
        <input type="hidden" name="witeListId" id="witeListId"/>

        <div class="row index">
            <form:hidden path="id"/>
            <div class="col-sm-12 col-md-12">
                <div class="intro-sign" style="padding: 20px 20px 0;">
                    <div class="form-inline">
                        <div class="row" style="margin-right: -15px; margin-left: -15px;">
                            <div id="divRegistration" class="col-md-12 col-sm-12"
                                 style="margin-top: -10px; margin-bottom: 8px;"></div>
                                <%--dia-logon当用户登录时隐藏--%>
                            <div class="dia-logon col-md-12 col-sm-12">
                                <div class="col-md-2 col-sm-2">
                                    <div class="form-group">
                                        <img src="${ctx}/assets/styles/images/card.png">
                                        <input type="password" class="form-control" id="txtPatientUdid" placeholder="刷卡"
                                               autocomplete="off">
                                    </div>
                                </div>
                                <div class="col-md-3 col-sm-4 iphonenember">
                                    <div class="form-group">
                                        <img src="${ctx}/assets/styles/images/id.png">
                                        <input type="text" class="form-control " id="txtPatientUid" autocomplete="off"
                                               placeholder="输入患者姓名/手机号/卡号">
                                    </div>
                                </div>
                                <div class="col-md-3 col-sm-4">
                                    <div class="form-group">
                                        <img src="${ctx}/assets/styles/images/password.png">
                                        <input type="password" class="form-control" id="txtPlainPassword"
                                               placeholder="输入密码" style="width: 60%;">
                                        <button id="btnLoadPatient" onclick="redirectPage()" type="button"
                                                class="btn btn-info pull-right">确定
                                        </button>
                                    </div>

                                </div>
                                    <%--<div class="col-md-5 col-sm-5" style="padding-left: 40px;">--%>
                                    <%--<div class="form-group">--%>
                                    <%--<i class="fa fa-user"--%>
                                    <%--style="font-size: 2.2rem; color: #5e5b5a; padding-right: 5px;vertical-align: middle;"></i>--%>
                                    <%--<select class="form-control" id="emrPatientSelect"--%>
                                    <%--onchange="clearOtherInputVal()" onclick="clearOtherInputVal()"--%>
                                    <%--title="请选择患者">--%>
                                    <%--<option></option>--%>
                                    <%--&lt;%&ndash;@elvariable id="emrPatientList" type="java.util.List"&ndash;%&gt;--%>
                                    <%--<c:forEach items="${emrPatientList}" var="onePatien">--%>
                                    <%--<option value="${onePatien.udid}">--%>
                                    <%--${onePatien.name}--%>
                                    <%---${onePatien.mobile}--%>
                                    <%---${onePatien.age}--%>
                                    <%---${onePatien.getMetaValue(onePatien.gender)}--%>
                                    <%--</option>--%>
                                    <%--</c:forEach>--%>
                                    <%--</select>--%>
                                    <%--<button id="btnLoadPatient" type="button" class="btn btn-info pull-right"--%>
                                    <%--style="margin-left: 1em;">确定--%>
                                    <%--</button>--%>
                                    <%--</div>--%>

                                    <%--</div>--%>
                            </div>
                        </div>
                        <div id="divPatientInfo">
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
                                               type="radio" value="${dept.id}"
                                            <%--@elvariable id="emr" type="com.qiaobei.hmp.modules.entity.DiagnosisTag"--%>
                                               <c:if test="${emr.departmentId == dept.id}">checked="checked"</c:if>
                                               title=""/>${dept.name}
                                    </a>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                    <input type="hidden" name="departmentId" value="1"/>

                    <div id="divDiagnosos" class="index-box">

                        <script type="application/javascript">
                            var jyHtml = "";
                            var divSp = "";
                            $(function () {
                                <%--@elvariable id="emr" type="com.qiaobei.hmp.modules.entity.Emr"--%>
                                <c:forEach var="sug" items="${emr.emrSuggestList}">
                                divSp += ' <div class="diagnosis_${sug.id}" style="font-size: 16px">${sug.suggestContent}<a href="javascript:" onclick="removeTeSun($(this).parent())"><i class="fa fa-times"></i></a><input type="hidden" name="suggestIds" value="${sug.suggestId}" /> <input type="hidden" name="suggestContents" value="${sug.suggestContent}" /> </div>';
                                </c:forEach>
                                //setInterval('pullVarTodiv()', 200);
                                pullVarTodiv();
                            });
                            function pullVarTodiv() {
                                if ($("#divSuggest").html() != "" && $("#divSuggest").children().length > 0) {
                                    if (divSp != $("#divSuggest").html()) {
                                        divSp = $("#divSuggest").html();
                                    }
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
                            <li role="presentation" class="active"><a href="#tabnoe" aria-controls="tabnoe" role="tab"
                                                                      data-toggle="tab">患者主诉
                                <p class="bottom-line">></p>
                            </a></li>
                            <li role="presentation"><a href="#tabtwo" aria-controls="profile" role="tab"
                                                       data-toggle="tab">体格检查
                                <p class="bottom-line"></p>
                            </a>
                            </li>

                            <li role="presentation"><a href="#tabthree" aria-controls="profile" role="tab"
                                                       data-toggle="tab">初步诊断
                                <p class="bottom-line"></p>
                            </a>
                            </li>

                            <li role="presentation" onclick="refMedPage()"><a href="#tabfour" aria-controls="profile"
                                                                              role="tab"
                                                                              data-toggle="tab">处方西药
                                <p class="bottom-line"></p>
                            </a>
                            </li>

                            <li role="presentation" onclick="refMedPage()"><a href="#tabfive" aria-controls="profile"
                                                                              role="tab"
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


                                    <div id="divSymptom" class="label-box"><c:forEach var="tag"
                                                                                      items="${emr.mainSuitList}">
                                            <span class="tag" onclick="fn_RemoveElement(this)"><input name="mainSuits"
                                                                                                      value="${tag}"
                                                                                                      type="hidden">${tag}</span>
                                    </c:forEach>
                                            <%-- 添加需求 主诉手写 --%>
                                        <div class="other-box">
                                            <span>其他</span>
                                            <div><input name="remarks" type="text" title=""></div>
                                        </div>
                                            <%-- end --%>
                                    </div>
                                    <div id="divSymptomTags" class="patient-label-box"></div>
                                </div>
                            </div>
                            <div role="tabpanel" id="tabtwo" class="tab-pane tabtwos">
                                <div class="form-inline">
                                    <div class="row">
                                        <c:forEach items="${emr.vitalSignList}" var="vs" varStatus="status">


                                            <div class="<c:if test="${status.count<2}">notbr pull-left</c:if><c:if test="${status.count>1}">col-md-6</c:if>">
                                                    <%--@elvariable id="vitalSignLabels" type="java.util.EnumMap"--%>
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
                                                           value='<fmt:formatNumber value="${vs.value}" pattern="###.#" />'
                                                           class="" onchange="pullItToPager(this)"
                                                           style='width:230px;border: 0px;border-bottom: 1px solid #0498d2;outline: none;'

                                                    />
                                                </c:if>

                                                    <%--@elvariable id="vitalSignUnits" type="java.util.EnumMap"--%>
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
                                        <%--<button type="button" class="btn btn-success" onclick="takePhotoBox('JianCha')">
                                            上传检验报告
                                        </button>--%>
                                </div>
                            </div>
                            <div role="tabpanel" class="tab-pane" id="tabthree">
                                <div class="diagnose">

                                    <div id="divDiagnosis" class="label-box">
                                        <c:forEach var="tag" items="${emr.diagnosisList}">
                                    <span class="tag" onclick="fn_RemoveElement(this)"> <input name="diagnosisResults"
                                                                                               value="${tag.name}"
                                                                                               type="hidden">${tag.name}</span>
                                        </c:forEach>
                                    </div>
                                    <div id="divDiagnosisTags" class="diagnosis-label-box"></div>
                                </div>
                            </div>
                            <div role="tabpanel" class="tab-pane" id="tabfour">
                                <div class="diagnose">


                                    <div id="divWesternRp"></div>
                                    <div id="divWesternItems" class="label-box" style="display: none">
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


                                    <div id="divChineseRp">

                                    </div>
                                    <div id="divChineseItems" class="label-box number-fu" style="display: none;">

                                        <div class="chinesty">
                                            <input type="number" min="0" name="chineseQty" class="form-control"
                                                   style="width: 50px"
                                                   id="chineseQty" onkeydown="doBack(this)"
                                                   onkeyup="whenChangZhongMedFuSize(this)"
                                                   onchange="whenChangZhongMedFuSize(this)" value="${emr.chineseQty}"
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
                            <h1><shiro:principal property='outpatientService'/>处方笺
                                <span class="biao-shi-span" id="emrTypeSpan">普通</span>
                            </h1>
                        </div>
                        <div>
                            <ul class="main-sTitle">
                                <ul>
                                    <li>
                                        <span>姓名:</span>
                                        <input id="paperName" readonly style=" width: 4.2em" type="text" class=" "
                                               title="姓名"/>
                                    </li>
                                    <li>
                                        <span>性别:</span>
                                        <input id="paperSex" type="text" style="width: 2em" class=" " readonly
                                               title="性别"/>
                                    </li>
                                    <li>
                                        <span>年龄:</span>
                                        <input id="paperAge" type="text" style="width: 3em" class="" readonly title=""/>
                                    </li>
                                    <li>
                                        <span>病史:</span>
                                        <input id="patientTags" type="text" style="width: 12.8em;" readonly value=" "
                                               title=""/>
                                    </li>
                                </ul>
                                <ul>
                                    <li>
                                        <span>联系方式:</span>
                                        <input id="paperPhoneNum" style="width: 8em" type="text" class=" " readonly
                                               title=""/>
                                    </li>
                                    <li>
                                        <span>地址:</span>
                                        <input id="paperAddrs" type="text" style="width: 17.2em;" readonly value=" "
                                               title=""/>
                                    </li>

                                </ul>
                                <ul>
                                    <li>
                                        <span>患者主诉:</span>
                                        <div id="talkcontentone"
                                             style="width: 27.4em;word-break:keep-all; white-space:nowrap; overflow:hidden; "></div>
                                    </li>
                                </ul>
                                <ul>

                                    <li>
                                        <span>体征:</span>
                                        <div id="talkSmtz"
                                             style="width: 12em; word-break:keep-all; white-space:nowrap; overflow:hidden; "></div>
                                    </li>
                                    <li>
                                        <span>初步诊断:</span>
                                        <div id="talkChuBuZd"
                                             style="width: 12.5em; word-break:keep-all; white-space:nowrap; overflow:hidden; "></div>
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
                                                              data-toggle="tab">Rp</a></li>
                                        <li><a href="#phy" id="therapyLi" class="btn btn-default"
                                               data-toggle="tab">中医理疗</a></li>
                                        <li><a href="#inspect" id="inspectLi" class="btn btn-default" data-toggle="tab">检查/检验</a>
                                        </li>
                                        <li><a href="#charges" id="fujiaId" class="btn btn-default" data-toggle="tab">附加费用</a>
                                        </li>
                                    </ul>
                                    <div class="tab-content">
                                        <div id="med" role="tabpanel" class="tab-pane active">
                                            <div class="main-contain-detail" style="float:none;"
                                                 id="medContext"></div>
                                            <div class="form-group" id="medEmrFileContext">
                                            </div>
                                        </div>
                                        <div id="phy" role="tabpanel" class="tab-pane">
                                            <div class="div-border">
                                                <h3 class="text-center">中医理疗</h3>
                                                <div>
                                                        <%--@elvariable id="patient" type="com.qiaobei.hmp.modules.entity.Patient"--%>
                                                    <span style="width: 25%; padding-left: 10px;">姓名：
                                                        <span id="patientName">
                                                                ${emr.patientName}
                                                        </span>

                                                    </span>
                                                    <span style="width: 18%;">性别：
                                                        <%--@elvariable id="genderMap" type="java.util.Map"--%>
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
                                                    <%--<p><a href="#" target="_blank"><span>检查报告：201602121230.png</span></a>--%>
                                                    <%--<i class="fa fa-trash-o"--%>
                                                    <%--style="font-size:16px; color: #eab065;line-height: 1.5; padding-left: 10px;"--%>
                                                    <%--onclick="fn_RemoveElement()"></i></p>--%>
                                            </div>
                                        </div>
                                        <div id="charges" role="tabpanel" class="tab-pane">
                                            <div class="div-border">
                                                <h3 class="text-center">附加项目费用</h3>
                                                <div>
                                                    <span style="width: 25%; padding-left: 10px;">姓名：
                                                        <span id="fujiaName">

                                                        </span>
                                                    </span>
                                                    <span style="width: 18%;">性别：
                                                        <span id="fujiaGender">

                                                        </span>
                                                    </span>
                                                    <span style="width: 20%;">年龄：
                                                        <span id="fujiaAge">

                                                        </span>
                                                    </span>
                                                    <span style="width: 34%;">电话：
                                                        <span id="fujiaMobo">

                                                        </span>
                                                    </span>
                                                </div>
                                            </div>
                                                <%--<div class="phy-box charges">--%>
                                                <%--<h4>附加项目费用</h4>--%>
                                                <%--<div>--%>
                                                <%--<strong onclick="editItems(this)">体验费</strong><span>100.00元</span><i--%>
                                                <%--class="fa fa-trash-o" onclick="fn_delContent(this)"></i>--%>
                                                <%--</div>--%>
                                                <%--<div>--%>
                                                <%--<strong onclick="editItems(this)">伤口处理费</strong><span>100.00元</span><i--%>
                                                <%--class="fa fa-trash-o" onclick="fn_delContent(this)"></i>--%>
                                                <%--</div>--%>

                                                <%--</div>--%>
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
                                            <%--<input type="text"--%>
                                            <%--name="cost"--%>
                                            <%--style="width: 100px"--%>
                                            <%--class="form-control"/>--%>
                                        <button onclick="selectChargeDetail()" type="button" class="btn btn-success"
                                                style="border-radius: 20px; outline: none;">收费明细
                                        </button>
                                            <%--<b style="font-size: 14px;font-weight: normal">收费金额：</b>--%>
                                        <input type="text"
                                               name="cost"
                                               readonly
                                               id="cost"
                                               style="width: 100px"
                                               value="0"
                                               class="form-control" title=""/>
                                        <input type="hidden" id="hiddenSetCost" value="0"/>
                                        <input type="hidden" id="hiddenSetCostPrice" value="0"/>
                                    </td>
                                    <td>
                                        <span>时间:</span>
                                        <span><fmt:formatDate value="${now}" pattern="yyyy/MM/dd HH:mm"/></span>

                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="2">发送回访短信<input id="dxYes" type="radio" name="autoSend" value="是"
                                                                 style="margin-left: 5px;width: 14px; vertical-align: middle; margin-top: 0;"
                                        <%--@elvariable id="doctor" type="com.qiaobei.hmp.modules.entity.Doctor"--%>
                                                                 <c:if test="${'是'.equals(doctor.autoSend)}">checked='checked'</c:if>
                                                                 title="">
                                        是
                                        <input id="dxNo" type="radio" name="autoSend"
                                               style="width: 14px; vertical-align: middle; margin-top: 0;" value="否"
                                               <c:if test="${!'是'.equals(doctor.autoSend)}">checked='checked'</c:if>
                                               title=""> 否
                                        <span id="dxDay">
                                            <span style="padding-left: 20px; padding-right: 5px;">就诊过后</span>
                                            <input name="autoSendDay" type="number"
                                                   value="${doctor.autoSendDay}"
                                                   style="width: 60px; height: 32px; font-size: 16px; border-radius: 5px; border: 1px solid #ccc;"
                                                   class="text-center" title="">
                                            <span>天发送</span>
                                             <input type="hidden" id="sendMsgInfo" value="" name="sendMsgInfo">
                                            <a href="#" onclick="fn_CareMes()" style="margin-left: 15px;">编辑/预览</a>
                                        </span>

                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>

                    <div class="button-item text-center">
                        <div class="pull-left">
                            <c:if test="${not empty emr.id}">
                                <button type="button"
                                        onclick="printA5or80Cfq('<shiro:principal property='printType'/>')"
                                        class="btn btn-lg btn-default"> 打印处方笺
                                </button>
                                <button type="button"
                                        onclick="printA5oOr80Jzd('<shiro:principal property='printType'/>')"
                                        class="btn btn-lg btn-default"> 打印就诊单
                                </button>
                            </c:if>
                            <form:button type="submit" name="btnSubmit" class="btn btn-lg btn-default"
                                         id="btnSubmit"> 保存信息</form:button>
                        </div>

                            <%--@elvariable id="previous" type="java.lang.long"--%>
                            <%--@elvariable id="next" type="java.lang.long"--%>
                        <c:if test="${not empty previous}">
                            <a href="/diagnosis/${previous}" class="btn btn-default"> 上一位</a>
                        </c:if>
                        <c:if test="${not empty next}">
                            <a href="/diagnosis/${next}" class="btn btn-default"> 下一位</a>
                        </c:if>
                        &nbsp;&nbsp;
                        <a onclick="fn_AddPatient()" class="btn btn-lg btn-default pull-right"> 临时处方</a>
                            <%--<a href="/diagnosis" class="btn btn-lg btn-default pull-right"> 新患者</a>--%>
                    </div>
                </div>
            </div>
            <div id="divUidList" class="list-group"></div>
        </div>
    </form:form>
    <div id="divPatientOverview" class="report-items"></div>
    <div id="banner_tabs" class="flexslider">
        <ul class="slides">
            <c:choose>
                <%--@elvariable id="adlist" type="java.util.list"--%>
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
            <li><a class="flex-prev" href="javascript:;">Previous</a></li>
            <li><a class="flex-next" href="javascript:;">Next</a></li>
        </ul>
    </div>
</div>
</body>
</html>