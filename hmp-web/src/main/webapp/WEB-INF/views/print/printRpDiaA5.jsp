<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html lang="cn" id="ppo">
<head>
    <title>打印A5处方笺</title>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="stylesheet" href="">
    <script src="${ctx}/assets/jquery/jquery-1.11.2.min.js" type="text/javascript"></script>
    <link href="${ctx}/assets/toastr/toastr.css" type="text/css" rel="stylesheet">
    <script src="${ctx}/assets/toastr/toastr.js" type="text/javascript"></script>
    <script src="${ctx}/assets/toastr/toastr.ext.js" type="text/javascript"></script>

    <script type="text/javascript" src="${ctx}/assets/scripts/base.js"></script>
    <script type="text/javascript" src="${ctx}/assets/print.lodop/LodopFuncs.js"></script>
    <script type="text/javascript" src="${ctx}/assets/scripts/print.js"></script>
    <script type="text/javascript" src="${ctx}/assets/scripts/acupuncturePoint.js"></script>
    <object id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0
            style="position:absolute;left:0px;top:-10px;"></object>
    <object id="LODOP_EM" TYPE="application/x-print-lodop" width=0 height=0
            style="position:absolute;left:0px;top:-10px;"></object>
    <style>
        * {
            padding: 0;
            margin: 0;
        }

        body {
            color: #000;
            background-color: #fff;
        }

        ul {
            list-style-type: none;
            /*padding: 0 1em;*/
            /*text-align: center;*/
            overflow: hidden;
        }

        li {
            padding-right: .5em;
            display: inline-block;
            padding-bottom: 5px;
            float: left;
        }

        .main {
            width: 530px;
            height: 100%;
            background: #fff;
            -webkit-border-radius: 6px;
            -moz-border-radius: 6px;
            border-radius: 6px;
            box-shadow: 0 3px 3px #d9d9d9;
        }

        .main > div {
            margin: 0.3em 2em;
        }

        .main-header h1 {
            text-align: center;
            padding: 1em 0 .5em 0;
        }

        .main-sTitle, .conter1 {
            border-bottom: 2px solid #050505;
        }

        .main-contain {
            width: 100%;
            height: auto;
        }

        .main-contain div {
            overflow: hidden;
        }

        .main-sTitle input {
            border-bottom: 1px solid #777;
            width: 6.2em;
        }

        .main-footer {
            margin-top: 1em;
            /*line-height: 2.5em;*/
        }

        .main-footer input {
            width: 148px;
        }

        .beizhu {
            width: 100%;
            /*overflow: height;*/
            overflow: hidden;
        }

        .beizhu span {
            /*padding-left: .4em;*/
            line-height: 1.5em;
            float: left;
        }

        .beizhu1 {
            float: left;
            padding: .1em;
            margin-left: 2.3em;
            margin-bottom: .3em;
            min-width: 204px;
        }

        .beizhu1 > div:nth-child(1) {
            font-weight: bold;
        }

        .beizhu1 > div > span:nth-child(2) {
            font-weight: bold;
        }

        .beizhu2 {
            float: left;
        }

        .beizhu_name > span:nth-child(1) {
            font-weight: bold;
        }

        input {
            border: 0;
            line-height: 1.2em;
            height: 1.2em;
        }

        .main-contain > div:not(:nth-last-child(1)) {
            padding: 0.5em 0 .2em 0;
        }

        .main-contain > div:nth-last-child(1) {
            margin-top: 1em;
        }

        .input_zd {
            border: 0;
            resize: none;
            height: 1.5em;
            width: 100%;
            overflow-y: visible;
            font-size: 1em;
        }

        .main-contain-detail {
            height: 100%;
            width: 100%;
            float: left;
            display: inline-block;
            padding-bottom: 1em;
            /*overflow-y: scroll;*/
        }

        .mCenter {
            position: relative;
            top: 0;
            bottom: 0;
            left: 0;
            right: 0;
        }

        .font-2_5 {
            /*float:right;*/
            text-align: left;
            font-size: 2.5em;
        }

        .font-2 {
            font-size: 2em;
            margin-left: -11px;
        }

        .content1 {
            width: 100%;
            height: 100%;
            /*min-height: 80%;*/
            /*font-size: 13px;*/
        }

        .main-sTitle ul li > span {
            float: left;
        }

        .main-sTitle ul li > div {
            float: left;
            height: 1.2em;
            border-bottom: 1px solid #777;
        }

        .main-sTitle ul li > div input[type="radio"] {
            float: left;
            width: 1em;
            height: 18px;
            border: 0;
        }

        .dia-span {
            line-height: 18px;
            float: left;
            padding-right: 5px;
            padding-left: 2px;
        }

        .beizhu1 {
            margin-left: 0;
        }

        .content1 span > strong {
            font-size: 14px;
        }

        .beizhu > .groupName {
            font-size: 14px !important;
        }

        .content {
            margin-bottom: 5px;
            overflow: hidden;
        }

        .content .col-md-5.col-sm-5 {
            padding: 10px 10px;
            float: left;
        }

        #medContext .content1 .beizhu {
            overflow: hidden;
        }

        #medContext .content1 .beizhu > .groupName {
            width: 8% !important;
        }

    </style>
    <script type="application/javascript">
        //打印处方签
        function printA5or80CfqPullTypeASAloen(type) {
            <%--if (type == "A5打印") {--%>
            <%--alert("1") ;--%>
            <%--YJZ_Printer.printUrl('../../pub/printRpA5/${emr.id}?type=alone');--%>
            <%--} else {--%>
            <%--YJZ_Printer.print80Url('../../pub/printRp/${emr.id}?type=alone');--%>
            <%--}--%>
        }


        <%
        int medSize=0;
        %>
        <c:set var="westernMedicinesByUseMode" value="${emr.westernMedicinesByUseMode}" />
        <c:if test="${not empty westernMedicinesByUseMode}">
        <c:forEach var="entry" items="${westernMedicinesByUseMode}">
        <c:forEach varStatus="status" var="em" items="${entry.value}">

        <c:if test="${printType.equals('ALL')}">
        <c:if test="${!doctor.needAlonePrinTypeStrings.contains(em.useMode)}">
        <% medSize+=1;%>

        </c:if>
        </c:if>
        <c:if test="${!printType.equals('ALL')}">
        <c:if test="${doctor.needAlonePrinTypeStrings.contains(em.useMode)}">
        <% medSize+=1;%>

        </c:if>
        </c:if>

        </c:forEach>
        </c:forEach>
        </c:if>

        <c:set var="chineseMedicinesByUseMode" value="${emr.chineseMedicinesByUseMode}" />
        <c:if test="${not empty chineseMedicinesByUseMode}">
        <c:forEach var="entry" items="${chineseMedicinesByUseMode}">
        <c:forEach varStatus="status" var="em" items="${entry.value}">

        <c:if test="${printType.equals('ALL')}">
        <c:if test="${!doctor.needAlonePrinTypeStrings.contains(em.useMode)}">
        <% medSize+=1;%>

        </c:if>
        </c:if>

        <c:if test="${!printType.equals('ALL')}">
        <c:if test="${doctor.needAlonePrinTypeStrings.contains(em.useMode)}">
        <% medSize+=1;%>

        </c:if>
        </c:if>

        </c:forEach>
        </c:forEach>
        </c:if>

        $(function () {
            if ("${emr.backDays}" in [1, 2, 3]) {
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


            <c:if test="${printType.equals('ALL')}">
            var allMedSize = "${emr.emrMedicineList.size()}";
            <% session.setAttribute("medSize",medSize); %>
            var newMedSize = "<%= medSize%>";
            if (allMedSize != newMedSize) {
                if (newMedSize == "0") {

                } else {
                    printA5or80CfqPullTypeASAloen("${doctor.printType}")
                }
            }
            </c:if>

            $("span[tmpname='size']").each(function () {
                $(this).text($(this).text().replace(".0#", "#"));
            })
        })
    </script>

</head>
<body style="padding:2px 0;font-size: 14px;">
<div class="main mCenter " style="min-height: 180mm; position: relative;">
    <div class="main-header pd_l ">
        <h1>${emr.doctor.outpatientService}处方笺</h1>
    </div>
    <div>
        <ul class="main-sTitle">
            <ul>
                <li>
                    <span>患者姓名:</span>
                    <input type="text" class="" style="width: 4em" value="${emr.patientName}" readonly/>
                </li>
                <li>
                    <span>性别:</span>
                    <input type="text" style="width: 2em" class=" " value="${genderMap[emr.patient.gender]}" readonly/>
                </li>
                <li>
                    <span>年龄:</span>
                    <input type="text" style="width: 4em" class="" value="${emr.patient.age}" readonly/>
                </li>
                <li>
                    <span>病史:</span>
                    <input type="text" style="width: 10em;"
                           value="<c:forEach var="tag" items="${emr.patient.patientTagList}">${tag.name};</c:forEach>"
                           readonly/>
                </li>
            </ul>
            <ul>
                <li>
                    <span>联系方式:</span>
                    <input type="text" class=" " style="width: 8.4em" value="${emr.patient.mobile}" readonly/>
                </li>
                <li>
                    <span>地址:</span>
                    <input type="text" style="width: 18.1em" value="${emr.patient.address}" readonly/>
                </li>
            </ul>
            <ul>
                <li>
                    <span>患者主诉:</span>
                    <input type="text" class=" " style="width: 30em"
                           value="<c:if test="${empty emr.mainSuit}">无</c:if> <c:if
                            test="${not empty emr.mainSuit}">${emr.mainSuit}</c:if>" readonly/>
                </li>
            </ul>
            <ul>
                <li>
                    <span>体征:</span>
                    <input type="text" class=" " style="width: 15em" value="<c:forEach items="${emr.vitalSignList}" var="vs" varStatus="status"><c:if
                            test="${not empty vs.value}">${vitalSignLabels[vs.type]}${vs.value}${vitalSignUnits[vs.type]}
                        <c:if test="${not empty vitalSignUnits[vs.type]}">;&nbsp;</c:if></c:if></c:forEach>" readonly/>
                </li>
                <li>
                    <span>初步诊断:</span>
                    <input type="text" class=" " style="width: 12em" value="<c:if test="${empty emr.diagnosisResult}">无</c:if><c:if
                            test="${not empty emr.diagnosisResult}">${emr.diagnosisResult}</c:if>" readonly/>
                </li>
            </ul>

        </ul>
    </div>
    <br/>

    <div style="min-height: 100mm; font-size: 1em;">
        <section class="main-contain" style="margin-top: -1em;min-height: 550px">
            <div class="main-contain-detail" id="medContext">
                <span class="font-2_5">R </span>
                <span class="font-2">p：</span>

                <div style="padding: 5px 0 5px 20px; overflow: hidden;">
                    <c:set var="westernMedicinesByUseMode" value="${emr.westernMedicinesByUseModeAndGruopId}"/>
                    <c:set var="xwString" value="${'针灸, 拔罐, 按摩,贴敷'}"/>
                    <c:if test="${not empty westernMedicinesByUseMode}">
                        <c:forEach var="entry" items="${westernMedicinesByUseMode}">

                            <c:if test="${printType.equals('ALL')}">
                                <c:if test="${!doctor.needAlonePrinTypeStrings.contains(entry.key)}">
                                    <div class="content">
                                        <div>
                                            <span>
                                                <strong>
                                                        ${entry.key}
                                                </strong>
                                            </span>
                                        </div>
                                        <c:forEach varStatus="status2" var="em2" items="${entry.value}">
                                            <div data-ids="${em2}" style="overflow:hidden;">
                                                <span style="float:left; padding-top:10px; padding-right: 10px;">
                                                    <strong>
                                                        <c:choose>
                                                            <c:when test="${entry.key.equals('研末') && not empty em2.key && em2.key>0}">
                                                                分${em2.key}包:
                                                            </c:when>
                                                            <c:when test="${ (xwString.indexOf(entry.key)>=0)&& not empty em2.key && em2.key>0}">
                                                                ${xwArrys[em2.key]}:
                                                            </c:when>
                                                            <c:when test="${!entry.key.equals('研末') && !(xwString.indexOf(entry.key)>=0) && not empty em2.key && em2.key>0 && em2.key!=10}">
                                                                分组${groupArrys[em2.key]}:
                                                            </c:when>
                                                        </c:choose>
                                                    </strong>
                                                </span>

                                                <div style="float: left;width: 80%">
                                                    <c:forEach varStatus="status" var="em" items="${em2.value}">
                                                        <div class="col-md-5 col-sm-5">
                                                            <div class="pt-detail-xq1">
                                                            <span>
                                                                    ${status.count}、
                                                            </span>
                                                                <span>
                                                                        ${em.medicineName}
                                                                </span>
                                                                <span tmpName="size">
                                                                ${em.qty}${medicineUnits[em.unit]}
                                                                    <c:choose><c:when
                                                                            test="${(em.copies ==1)||(em.copies ==0)}"></c:when><c:otherwise>x${em.copies}#</c:otherwise>
                                                                    </c:choose>
                                                            </span>
                                                            </div>
                                                            <div class="pt-detail-xq1"> &nbsp;
                                                                <c:if test="${em.hasUsage}">
                                                                    <span>${em.useTimes} &nbsp;${em.useQty}${em.useUnit}</span>
                                                                    <span>${em.usingTime}</span>
                                                                </c:if>
                                                            </div>
                                                        </div>
                                                    </c:forEach>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </c:if>
                            </c:if>

                            <c:if test="${!printType.equals('ALL')}">
                                <c:if test="${doctor.needAlonePrinTypeStrings.contains(entry.key)}">
                                    <div class="content">
                                        <div>
                                            <span>
                                                <strong>
                                                        ${entry.key}
                                                </strong>
                                            </span>
                                        </div>
                                        <c:forEach varStatus="status2" var="em2" items="${entry.value}">
                                            <div data-ids="${em2}" style="overflow:hidden;">
                                                <span style="float:left; padding-top:10px; padding-right: 10px;">
                                                    <strong>
                                                        <c:choose>
                                                            <c:when test="${entry.key.equals('研末') && not empty em2.key && em2.key>0}">
                                                                分${em2.key}包:
                                                            </c:when>
                                                            <c:when test="${(xwString.indexOf(entry.key)>=0) && not empty em2.key && em2.key>0}">
                                                                ${xwArrys[em2.key]}:
                                                            </c:when>
                                                            <c:when test="${!entry.key.equals('研末') && !(xwString.indexOf(entry.key)>=0) && not empty em2.key && em2.key>0 && em2.key!=10}">
                                                                分组${groupArrys[em2.key]}:
                                                            </c:when>
                                                        </c:choose>
                                                    </strong>
                                                </span>

                                                <div style="float: left;width: 80%">
                                                    <c:forEach varStatus="status" var="em" items="${em2.value}">
                                                        <div class="col-md-5 col-sm-5">
                                                            <div class="pt-detail-xq1">
                                                            <span>
                                                                    ${status.count}、
                                                            </span>
                                                                <span>
                                                                        ${em.medicineName}
                                                                </span>
                                                                <span tmpName="size">
                                                                ${em.qty}${medicineUnits[em.unit]}
                                                                    <c:choose>
                                                                        <c:when test="${(em.copies ==1)||(em.copies ==0)}"></c:when>
                                                                        <c:otherwise>x${em.copies}&nbsp;#</c:otherwise>
                                                                    </c:choose>
                                                            </span>
                                                            </div>
                                                            <div class="pt-detail-xq1"> &nbsp;
                                                                <c:if test="${em.hasUsage}">
                                                                    <span>${em.useTimes} &nbsp;${em.useQty}${em.useUnit}</span>
                                                                    <span>${em.usingTime}</span>
                                                                </c:if>
                                                            </div>
                                                        </div>
                                                    </c:forEach>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </c:if>
                            </c:if>
                        </c:forEach>
                    </c:if>
                </div>
                <div style="padding: 5px 0 5px 20px; overflow: hidden;">
                    <c:set var="chineseMedicinesByUseMode" value="${emr.chinaMedicinesByUseModeAndGruopId}"/>
                    <c:if test="${not empty chineseMedicinesByUseMode}">
                        <c:forEach var="entry" items="${chineseMedicinesByUseMode}">
                            <c:if test="${printType.equals('ALL')}">
                                <c:if test="${!doctor.needAlonePrinTypeStrings.contains(entry.key)}">

                                    <div class="content">
                                        <div><span><strong>${entry.key}<c:if
                                                test="${'煎服'.equals(entry.key)}">(${emr.chineseQty}副)</c:if></strong></span>
                                        </div>
                                        <c:forEach varStatus="status2" var="em2" items="${entry.value}">
                                            <div data-ids="${em2}" style="overflow:hidden;">
                                                 <span style="float:left; padding-top:10px; padding-right: 10px;">
                                                        <strong>
                                                            <c:choose>
                                                                <c:when test="${(xwString.indexOf(entry.key)>=0) && not empty em2.key && em2.key>0}">
                                                                    ${xwArrys[em2.key]}:
                                                                </c:when>

                                                                <c:when test="${ !(xwString.indexOf(entry.key)>=0) && not empty em2.key && em2.key>0 && em2.key!=10}">
                                                                    &nbsp;
                                                                </c:when>
                                                            </c:choose>
                                                        </strong>
                                                 </span>

                                                <div style="float: left;width: 80%">
                                                    <c:forEach varStatus="status" var="em" items="${em2.value}">

                                                        <div class="col-md-5 col-sm-5">
                                                            <div class="pt-detail-xq1">
                                                            <span>
                                                                    ${status.count}、
                                                            </span>
                                                                <span>
                                                                        ${em.medicineName}
                                                                </span>
                                                                <span>
                                                            ${em.qty}${medicineUnits[em.unit]}

                                                            <c:if test="${!entry.key.equals('煎服')}">
                                                                x${em.copies}&nbsp;#
                                                            </c:if>

                                                            </span>
                                                            </div>
                                                        </div>
                                                    </c:forEach>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </c:if>
                            </c:if>
                            <c:if test="${!printType.equals('ALL')}">
                                <c:if test="${doctor.needAlonePrinTypeStrings.contains(entry.key)}">

                                    <div class="content">
                                        <div><span><strong>${entry.key}</strong></span></div>
                                        <c:forEach varStatus="status2" var="em2" items="${entry.value}">
                                            <div data-ids="${em2}" style="overflow:hidden;">
                                                 <span style="float:left; padding-top:10px; padding-right: 10px;">
                                                        <strong>
                                                            <c:choose>
                                                                <c:when test="${(xwString.indexOf(entry.key)>=0) && not empty em2.key && em2.key>0}">
                                                                    ${xwArrys[em2.key]}:
                                                                </c:when>

                                                                <c:when test="${ !(xwString.indexOf(entry.key)>=0) && not empty em2.key && em2.key>0 && em2.key!=10}">
                                                                    &nbsp;
                                                                </c:when>
                                                            </c:choose>
                                                        </strong>
                                                 </span>

                                                <div style="float: left;width: 80%">
                                                    <c:forEach varStatus="status" var="em" items="${em2.value}">

                                                        <div class="col-md-5 col-sm-5">
                                                            <div class="pt-detail-xq1">
                                                            <span>
                                                                    ${status.count}、
                                                            </span>
                                                                <span>
                                                                        ${em.medicineName}
                                                                </span>
                                                                <span>
                                                            ${em.qty}${medicineUnits[em.unit]}

                                                            <c:if test="${!entry.key.equals('煎服')}">
                                                                x${em.copies}&nbsp;#
                                                            </c:if>

                                                            </span>
                                                            </div>
                                                        </div>
                                                    </c:forEach>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </c:if>
                            </c:if>
                        </c:forEach>
                    </c:if>
                </div>
                <c:if test="${medSize==0}">
                    <div style="padding: 5px 0 5px 20px; overflow: hidden;">
                        <c:set var="westernMedicinesByUseMode" value="${emr.westernMedicinesByUseModeAndGruopId}"/>
                        <c:if test="${not empty westernMedicinesByUseMode}">
                            <c:forEach var="entry" items="${westernMedicinesByUseMode}">
                                <div class="content">
                                    <div>
                                    <span>
                                        <strong>
                                                ${entry.key}
                                        </strong>
                                    </span>
                                    </div>
                                    <c:forEach varStatus="status2" var="em2" items="${entry.value}">
                                        <div data-ids="${em2}" style="overflow:hidden;">
                                        <span style="float:left; padding-top:10px; padding-right: 10px;">
                                            <strong>
                                                <c:choose>
                                                    <c:when test="${entry.key.equals('研末') && not empty em2.key && em2.key>0}">
                                                        分${em2.key}包:
                                                    </c:when>
                                                    <c:when test="${(xwString.indexOf(entry.key)>=0) && not empty em2.key && em2.key>0}">
                                                        ${xwArrys[em2.key]}:
                                                    </c:when>
                                                    <c:when test="${!entry.key.equals('研末') && !(xwString.indexOf(entry.key)>=0) && not empty em2.key && em2.key>0 && em2.key!=10}">
                                                        分组${groupArrys[em2.key]}:
                                                    </c:when>
                                                </c:choose>
                                            </strong>
                                        </span>

                                            <div style="float: left;width: 80%">
                                                <c:forEach varStatus="status" var="em" items="${em2.value}">
                                                    <div class="col-md-5 col-sm-5">
                                                        <div class="pt-detail-xq1">
                                                    <span>
                                                            ${status.count}、
                                                    </span>
                                                            <span>
                                                                    ${em.medicineName}
                                                            </span>
                                                            <span tmpName="size">
                                                        ${em.qty}${medicineUnits[em.unit]}
                                                            <c:choose>
                                                                <c:when test="${(em.copies ==1)||(em.copies ==0)}"></c:when>
                                                                <c:otherwise>x${em.copies}&nbsp;#</c:otherwise>
                                                            </c:choose>
                                                    </span>
                                                        </div>
                                                        <div class="pt-detail-xq1"> &nbsp;
                                                            <c:if test="${em.hasUsage}">
                                                                <span>${em.useTimes} &nbsp;${em.useQty}${em.useUnit}</span>
                                                                <span>${em.usingTime}</span>
                                                            </c:if>
                                                        </div>
                                                    </div>
                                                </c:forEach>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </c:forEach>
                        </c:if>
                    </div>
                    <div style="padding: 5px 0 5px 20px; overflow: hidden;">
                        <c:set var="chineseMedicinesByUseMode" value="${emr.chinaMedicinesByUseModeAndGruopId}"/>
                        <c:if test="${not empty chineseMedicinesByUseMode}">
                            <c:forEach var="entry" items="${chineseMedicinesByUseMode}">
                                <div class="content">
                                    <div><span><strong>${entry.key}<c:if
                                            test="${'煎服'.equals(entry.key)}">(${emr.chineseQty}副)</c:if></strong></span>
                                    </div>
                                    <c:forEach varStatus="status2" var="em2" items="${entry.value}">
                                        <div data-ids="${em2}" style="overflow:hidden;">
                                         <span style="float:left; padding-top:10px; padding-right: 10px;">
                                                <strong>
                                                    <c:choose>
                                                        <c:when test="${(xwString.indexOf(entry.key)>=0) && not empty em2.key && em2.key>0}">
                                                            ${xwArrys[em2.key]}:
                                                        </c:when>

                                                        <c:when test="${ !(xwString.indexOf(entry.key)>=0) && not empty em2.key && em2.key>0 && em2.key!=10}">
                                                            &nbsp;
                                                        </c:when>
                                                    </c:choose>
                                                </strong>
                                         </span>

                                            <div style="float: left;width: 80%">
                                                <c:forEach varStatus="status" var="em" items="${em2.value}">

                                                    <div class="col-md-5 col-sm-5">
                                                        <div class="pt-detail-xq1">
                                                    <span>
                                                            ${status.count}、
                                                    </span>
                                                            <span>
                                                                    ${em.medicineName}
                                                            </span>
                                                            <span>
                                                    ${em.qty}${medicineUnits[em.unit]}

                                                    <c:if test="${!entry.key.equals('煎服')}">
                                                        x${em.copies}&nbsp;#
                                                    </c:if>

                                                    </span>
                                                        </div>
                                                    </div>
                                                </c:forEach>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </c:forEach>
                        </c:if>
                    </div>
                </c:if>

            </div>
        </section>
    </div>
    <div class="main-footer" style="position: absolute; bottom: 0; right: 0;">
        <table style="border-top: 2px solid black;width: 482px">
            <tbody>
            <tr>
                <td>
                    <span>处方医生:</span>
                    <input type="text"/>
                </td>
                <td>
                    <span>药剂师:</span>
                    <input type="text"/>
                </td>
            </tr>
            <tr>
                <td>
                    <span>药价:</span>
                    <span>${emr.cost}</span>
                </td>
                <td>
                    <span>时间:</span>
                    <span><fmt:formatDate value="${emr.createOn}" pattern="yyyy-MM-dd"/></span>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
