<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html lang="cn" id="ppo">
<head>
    <title>打印A5处方笺(预览)</title>
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
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/base.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/print.lodop/LodopFuncs.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/print.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/jpPrint/jQuery.print.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/acupuncturePoint.js" type="js"/>
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
            overflow: hidden;
        }

        .layui-layer-btn {
            text-align: center;
        }

        .layui-layer-btn0 {
            font-size: 30px;
        }

        .layui-layer-btn a {
            height: 37px;
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
            /*box-shadow: 0 3px 3px #d9d9d9;*/
        }

        .main > div {
            margin: 0.3em 2em;
        }

        .main-header h2 {
            text-align: center;
            padding: 0.6em 0 .5em 0;
        }

        .main-sTitle {
            border-bottom: 2px solid #050505;
        }

        .main-sTitle input {
            border-bottom: 1px solid #777;
            width: 6.2em;
        }

        input {
            border: 0;
            line-height: 1.2em;
            height: 1.2em;
        }

        .font-2_5 {
            text-align: left;
            font-size: 2em;
        }

        .font-2 {
            font-size: 1.5em;
            margin-left: -11px;
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

        .fz input {
            float: left;
        }

        .print-tfoot {
            border-top: 2px solid black;
            position: absolute;
            bottom: 0;
            left: 20px;
        }

        .chinese-print {
            float: left;
            width: 50%;
            line-height: 1.5em;
        }

        /*.pt-detail-xq1{
            margin-top: 5px;
        }*/
        .pt-detail-xq2 {
            padding-left: 2em;
            line-height: 1.2em;
            font-size: 0.9em;
        }

    </style>
    <script type="application/javascript">
        //打印处方签
        function printA5or80CfqPullTypeASAloen(type) {

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

                }
            }
            </c:if>

            $("span[tmpname='size']").each(function () {
                $(this).text($(this).text().replace(".0#", "#"));
                $(this).text($(this).text().replace(".0", ""));
            })


        })
    </script>
    <script>
        $(function () {
            var content = $(".pt-detail-xq2").text();
            if (content == null || content == "" || content == '&nbsp;') {
                $(".pt-detail-xq2").hide();
            } else {
                $(".pt-detail-xq2").show();
            }
        });
    </script>

</head>
<body style="padding:2px 0;">
<div class="main mCenter" style="min-height: 198mm; position: relative;" id="printDiv">
    <table style="padding: 0 20px;">
        <thead>
        <tr class="main-header pd_l ">
            <td colspan="4">
                <h2>${emr.doctor.outpatientService}处方笺</h2>
            </td>

        </tr>
        <tr class="main-sTitle" style="line-height: 1.5em;">
            <td style="width: 25%">
                <span>患者姓名:</span>
                <input type="text" class="" style="width: 3em" value="${emr.patientName}" readonly/>
            </td>
            <td style="width: 20%">
                <span>性别:</span>
                <input type="text" style="width: 3em" class=" " value="${genderMap[emr.patient.gender]}"
                       readonly/>
            </td>
            <td style="width: 20%">
                <span>年龄:</span>
                <input type="text" style="width: 3em" class="" value="${emr.patient.age}" readonly/>
            </td>
            <td style="width: 35%">
                <span>病史:</span>
                <input type="text" style="width: 70%;"
                       value="<c:forEach var="tag" items="${emr.patient.patientTagList}">${tag.name};</c:forEach>"
                       readonly/>
            </td>
        </tr>
        <tr class="main-sTitle" style="line-height: 1.5em;">
            <td colspan="2">
                <span>联系方式:</span>
                <input type="text" class=" " style="width: 60%;" value="${emr.patient.mobile}" readonly/>
            </td>
            <td colspan="2">
                <span>地址:</span>
                <input type="text" style="width: 80%;" value="<c:choose>
                    <c:when test="${empty emr.patient.address}">
                        ${emr.patient.province}${emr.patient.city}${emr.patient.area}
                    </c:when>
                    <c:otherwise>
                        ${emr.patient.address}
                    </c:otherwise>
                </c:choose>
                " readonly/>
            </td>
        </tr>
        <tr class="main-sTitle" style="line-height: 1.5em;">
            <td colspan="4">
                <span>患者主诉:</span>
                <input type="text" class=" " style="width: 82%"
                       value="<c:if test="${empty emr.mainSuit}">无</c:if> <c:if
            test="${not empty emr.mainSuit}">${emr.mainSuit.replace(";","")}</c:if>&nbsp;<c:if test="${not empty emr.remarks}">${emr.remarks}</c:if>"
                       readonly/>
            </td>
        </tr>
        <tr class="main-sTitle" style="line-height: 1.5em;">
            <td colspan="2">
                <span>体征:</span>
                <input type="text" style="width: 12em" value="<c:forEach items="${emr.vitalSignList}" var="vs" varStatus="status"><c:if
                                test="${not empty vs.value}">${vitalSignLabels[vs.type]}${vs.value}${vitalSignUnits[vs.type]}
                            <c:if test="${not empty vitalSignUnits[vs.type]}">;&nbsp;</c:if></c:if></c:forEach>"
                       readonly/>
            </td>
            <td colspan="2">
                <span>初步诊断:</span>
                <input type="text" class=" " style="width: 67%;"
                       value="<c:if test="${empty emr.diagnosisResult}">无</c:if><c:if
                                test="${not empty emr.diagnosisResult}">${emr.diagnosisResult}</c:if>" readonly/>
            </td>
        </tr>

        <tr class="main-sTitle" style="line-height: 1.5em;">
            <td colspan="4" style="border-bottom: 2px solid black;"></td>
        </tr>
        </thead>
        <tbody>
        <tr style="height: 0px;">
            <td colspan="4"><%-- 此处是做间隔用 --%></td>
        </tr>
        <tr>
            <td colspan="4">
                <span class="font-2_5">R </span>
                <span class="font-2">p：</span>
            </td>
        </tr>
        <tr>
            <td colspan="4">
                <table width="100%" style="font-size: 1em;">
                    <c:set var="westernMedicinesByUseMode" value="${emr.westernMedicinesByUseModeAndGruopId}"/>
                    <c:set var="xwString" value="${'针灸, 拔罐, 按摩,贴敷'}"/>
                    <c:if test="${not empty westernMedicinesByUseMode}">
                        <c:forEach var="entry" items="${westernMedicinesByUseMode}">

                            <c:if test="${printType.equals('ALL')}">
                                <c:if test="${!doctor.needAlonePrinTypeStrings.contains(entry.key)}">
                                    <c:forEach varStatus="status2" var="em2" items="${entry.value}">
                                        <tr style="line-height: 1.8em;">
                                            <td colspan="2">
                                                <span style="font-weight: bold;">
                                                        ${entry.key}
                                                </span>
                                                <span>
                                                    <c:choose>
                                                        <c:when test="${entry.key.equals('研末') && not empty em2.key && em2.key>0}">
                                                            [分${em2.key}包]
                                                        </c:when>
                                                        <c:when test="${ (xwString.indexOf(entry.key)>=0)&& not empty em2.key && em2.key>0}">
                                                            [${xwArrys[em2.key]}]
                                                        </c:when>
                                                        <c:when test="${!entry.key.equals('研末') && !(xwString.indexOf(entry.key)>=0) && not empty em2.key && em2.key>0 && em2.key!=10}">
                                                            [分组${groupArrys[em2.key]}]
                                                        </c:when>
                                                    </c:choose>
                                                </span>
                                            </td>
                                        </tr>
                                        <tr style="line-height: 1.5em;">
                                            <td style="padding-left: 2em;" colspan="2">
                                                <c:forEach varStatus="status" var="em" items="${em2.value}">
                                                    <div class="pt-detail-xq1">
                                                 <span>
                                                         ${status.count}、
                                                 </span>
                                                        <strong>
                                                                ${em.medicineName}
                                                        </strong>
                                                        <span>
                                                                ${em.standard}
                                                        </span>

                                                        <span tmpName="size">
                                                     ${em.qty}${medicineUnits[em.unit]}
                                                     <c:if test="${em.copies!=1.0&&em.copies!=0&&em.copies!=null}">x${em.copies}份&nbsp;
                                                     </c:if>
                                                 </span>
                                                            <%----%>
                                                        <span>
                                                     <c:if test="${em.specialInstructions!=null&&em.specialInstructions!=''}">
                                                         (${em.specialInstructions})
                                                     </c:if>
                                                 </span>

                                                    </div>
                                                    <div class="pt-detail-xq2">
                                                        <c:if test="${em.hasUsage}">
                                                            <span>${em.useTimes} &nbsp;${em.useQty}${em.useUnit}</span>
                                                            <span>${em.usingTime}</span>
                                                        </c:if>
                                                    </div>
                                                </c:forEach>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:if>
                            </c:if>

                            <c:if test="${!printType.equals('ALL')}">
                                <c:if test="${doctor.needAlonePrinTypeStrings.contains(entry.key)}">

                                    <c:forEach varStatus="status2" var="em2" items="${entry.value}">
                                        <tr style="line-height: 1.8em;">
                                            <td colspan="2">
                                                <strong>
                                                        ${entry.key}
                                                </strong>
                                                <span>
                                                    <c:choose>
                                                        <c:when test="${entry.key.equals('研末') && not empty em2.key && em2.key>0}">
                                                            [分${em2.key}包]
                                                        </c:when>
                                                        <c:when test="${(xwString.indexOf(entry.key)>=0) && not empty em2.key && em2.key>0}">
                                                            [${xwArrys[em2.key]}]
                                                        </c:when>
                                                        <c:when test="${!entry.key.equals('研末') && !(xwString.indexOf(entry.key)>=0) && not empty em2.key && em2.key>0 && em2.key!=10}">
                                                            [分组${groupArrys[em2.key]}]
                                                        </c:when>
                                                    </c:choose>
                                                </span>
                                            </td>
                                        </tr>
                                        <tr style="line-height: 1.5em;">
                                            <td colspan="2" style="padding-left: 2em;">
                                                <c:forEach varStatus="status" var="em" items="${em2.value}">
                                                    <div class="pt-detail-xq1">
                                                     <span>
                                                             ${status.count}、
                                                     </span>
                                                        <span>
                                                                ${em.medicineName}
                                                        </span>
                                                        <span>
                                                                ${em.standard}
                                                        </span>
                                                        <span tmpName="size">
                                                         ${em.qty}${medicineUnits[em.unit]}
                                                         <c:if test="${em.copies!=1.0&&em.copies!=0&&em.copies!=null}">x${em.copies}份&nbsp;
                                                         </c:if>
                                                     </span>
                                                        <span>
                                                     <c:if test="${em.specialInstructions!=null&&em.specialInstructions!=''}">
                                                         (${em.specialInstructions})
                                                     </c:if>
                                                    </span>
                                                    </div>
                                                    <div class="pt-detail-xq2">
                                                        <c:if test="${em.hasUsage}">
                                                            <span>${em.useTimes} &nbsp;${em.useQty}${em.useUnit}</span>
                                                            <span>${em.usingTime}</span>
                                                        </c:if>
                                                    </div>
                                                </c:forEach>
                                            </td>

                                        </tr>
                                    </c:forEach>
                                </c:if>
                            </c:if>
                        </c:forEach>
                    </c:if>
                    <%--chineseMedicinesByUseMode上面也有啊，两次完全不一样啊--%>
                    <c:set var="chineseMedicinesByUseMode" value="${emr.chinaMedicinesByUseModeAndGruopId}"/>
                    <c:if test="${not empty chineseMedicinesByUseMode}">
                        <c:forEach var="entry" items="${chineseMedicinesByUseMode}">
                            <c:if test="${printType.equals('ALL')}">
                                <c:if test="${!doctor.needAlonePrinTypeStrings.contains(entry.key)}">

                                    <c:forEach varStatus="status2" var="em2" items="${entry.value}">
                                        <tr style="line-height: 1.8em;">
                                            <td colspan="2">
                                                <strong>${entry.key}
                                                    <c:if test="${'煎服'.equals(entry.key)}">(${emr.chineseQty}副)</c:if>
                                                    <c:if test="${'研末'.equals(entry.key)}">[分${em2.key}包]</c:if>
                                                </strong>
                                                <span>
                                                    <c:choose>
                                                        <c:when test="${(xwString.indexOf(entry.key)>=0) && not empty em2.key && em2.key>0}">
                                                            [${xwArrys[em2.key]}]
                                                        </c:when>

                                                        <c:when test="${ !(xwString.indexOf(entry.key)>=0) && not empty em2.key && em2.key>0 && em2.key!=10}">
                                                            &nbsp;
                                                        </c:when>
                                                    </c:choose>
                                                </span>
                                            </td>
                                        </tr>
                                        <tr style="line-height: 1.5em;">
                                            <td style="overflow: hidden; padding-left: 2em;" colspan="2">
                                                <c:forEach varStatus="status" var="em" items="${em2.value}">

                                                    <div class="chinese-print">
                                                         <span>
                                                                 ${status.count}、
                                                         </span>
                                                        <strong>
                                                                ${em.medicineName}
                                                        </strong>
                                                        <span>
                                                                ${em.standard}
                                                        </span>
                                                        <span tmpName="size">
                                                         ${em.qty}${medicineUnits[em.unit]}

                                                         <c:if test="${!entry.key.equals('煎服')}">
                                                             <c:if test="${em.copies!=1.0&&em.copies!=0&&em.copies!=null}">x${em.copies}份&nbsp;
                                                             </c:if>
                                                         </c:if>
                                                         <c:if test="${em.specialInstructions!=null&&em.specialInstructions!=''}">
                                                             (${em.specialInstructions})
                                                         </c:if>

                                                         </span>
                                                    </div>
                                                </c:forEach>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:if>
                            </c:if>
                            <c:if test="${!printType.equals('ALL')}">
                                <c:if test="${doctor.needAlonePrinTypeStrings.contains(entry.key)}">

                                    <c:forEach varStatus="status2" var="em2" items="${entry.value}">
                                        <tr style="line-height: 1.8em;">
                                            <td colspan="2">
                                                <strong>${entry.key}</strong>
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
                                            </td>
                                        </tr>
                                        <tr style="line-height: 1.5em;">
                                            <td colspan="2" style="padding-left: 2em;">
                                                <c:forEach varStatus="status" var="em" items="${em2.value}">
                                                    <div class="chinese-print">
                                                         <span>
                                                                 ${status.count}、
                                                         </span>
                                                        <strong>
                                                                ${em.medicineName}
                                                        </strong>
                                                        <span>
                                                                ${em.standard}
                                                        </span>
                                                        <span tmpName="size">
                                                         ${em.qty}${medicineUnits[em.unit]}

                                                         <c:if test="${!entry.key.equals('煎服')}">
                                                             <c:if test="${em.copies!=1.0&&em.copies!=0&&em.copies!=null}">x${em.copies}份&nbsp;
                                                             </c:if>
                                                         </c:if>
                                                         <c:if test="${em.specialInstructions!=null&&em.specialInstructions!=''}">
                                                             (${em.specialInstructions})
                                                         </c:if>

                                                         </span>
                                                    </div>
                                                </c:forEach>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:if>
                            </c:if>
                        </c:forEach>
                    </c:if>

                    <c:if test="${medSize==0}">
                        <c:set var="westernMedicinesByUseMode" value="${emr.westernMedicinesByUseModeAndGruopId}"/>
                        <c:if test="${not empty westernMedicinesByUseMode}">
                            <c:forEach var="entry" items="${westernMedicinesByUseMode}">

                                <c:forEach varStatus="status2" var="em2" items="${entry.value}">
                                    <tr style="line-height: 1.8em;">
                                        <td colspan="2">
                                            <strong>
                                                    ${entry.key}
                                            </strong>
                                            <span>
                                                <c:choose>
                                                    <c:when test="${entry.key.equals('研末') && not empty em2.key && em2.key>0}">
                                                        [分${em2.key}包]
                                                    </c:when>
                                                    <c:when test="${(xwString.indexOf(entry.key)>=0) && not empty em2.key && em2.key>0}">
                                                        [${xwArrys[em2.key]}]
                                                    </c:when>
                                                    <c:when test="${!entry.key.equals('研末') && !(xwString.indexOf(entry.key)>=0) && not empty em2.key && em2.key>0 && em2.key!=10}">
                                                        [分组${groupArrys[em2.key]}]
                                                    </c:when>
                                                </c:choose>
                                            </span>
                                        </td>
                                    </tr>
                                    <tr style="line-height: 1.5em;">
                                        <td colspan="2" style="padding-left: 2em;">
                                            <c:forEach varStatus="status" var="em" items="${em2.value}">
                                                <div class="pt-detail-xq1">
                                                     <span>
                                                             ${status.count}、
                                                     </span>
                                                    <span>
                                                            ${em.medicineName}
                                                    </span>
                                                    <span>
                                                            ${em.standard}
                                                    </span>
                                                    <span tmpName="size">
                                                     ${em.qty}${medicineUnits[em.unit]}

                                                     <c:if test="${!entry.key.equals('煎服')}">
                                                         <c:if test="${em.copies!=1.0&&em.copies!=0&&em.copies!=null}">x${em.copies}份&nbsp;
                                                         </c:if>
                                                     </c:if>
                                                     <c:if test="${em.specialInstructions!=null&&em.specialInstructions!=''}">
                                                         (${em.specialInstructions})
                                                     </c:if>

                                                     </span>
                                                </div>
                                                <div class="pt-detail-xq2">
                                                    <c:if test="${em.hasUsage}">
                                                        <span>${em.useTimes} &nbsp;${em.useQty}${em.useUnit}</span>
                                                        <span>${em.usingTime}</span>
                                                    </c:if>
                                                </div>
                                            </c:forEach>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:forEach>
                        </c:if>
                        <c:set var="chineseMedicinesByUseMode" value="${emr.chinaMedicinesByUseModeAndGruopId}"/>
                        <c:if test="${not empty chineseMedicinesByUseMode}">
                            <c:forEach var="entry" items="${chineseMedicinesByUseMode}">

                                <c:forEach varStatus="status2" var="em2" items="${entry.value}">
                                    <tr style="line-height: 1.8em;">
                                        <td colspan="2">
                                            <strong>${entry.key}
                                                <c:if
                                                        test="${'煎服'.equals(entry.key)}">(${emr.chineseQty}副)</c:if>
                                            </strong>
                                            <span>
                                                <c:choose>
                                                    <c:when test="${(xwString.indexOf(entry.key)>=0) && not empty em2.key && em2.key>0}">
                                                        ${xwArrys[em2.key]}:
                                                    </c:when>

                                                    <c:when test="${ !(xwString.indexOf(entry.key)>=0) && not empty em2.key && em2.key>0 && em2.key!=10}">
                                                        &nbsp;
                                                    </c:when>
                                                </c:choose>
                                            </span>
                                        </td>
                                    </tr>
                                    <tr style="line-height: 1.5em;">
                                        <td style="overflow:hidden;" colspan="2" style="padding-left: 2em;">
                                            <c:forEach varStatus="status" var="em" items="${em2.value}">
                                                <div class="chinese-print">
                                                 <span>
                                                         ${status.count}、
                                                 </span>
                                                    <strong>
                                                            ${em.medicineName}
                                                    </strong>
                                                    <span>
                                                            ${em.standard}
                                                    </span>
                                                    <span tmpName="size">
                                                 ${em.qty}${medicineUnits[em.unit]}

                                                 <c:if test="${!entry.key.equals('煎服')}">
                                                     <c:if test="${em.copies!=1.0&&em.copies!=0&&em.copies!=null}">x${em.copies}份&nbsp;
                                                     </c:if>
                                                 </c:if>
                                                 <c:if test="${em.specialInstructions!=null&&em.specialInstructions!=''}">
                                                     (${em.specialInstructions})
                                                 </c:if>

                                                 </span>
                                                </div>
                                            </c:forEach>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:forEach>
                        </c:if>
                    </c:if>
                </table>
            </td>
        </tr>
        </tbody>
        <tfoot>
        <tr>
            <td colspan="4">
                <table width="92%" class="print-tfoot">
                    <tr style="line-height: 1.5em;">
                        <td style="padding-right: 8em;">
                            <span>处方医生:</span>
                            <span>${emr.doctor.name}</span>
                        </td>
                        <td>
                            <span>药剂师:</span>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <span>药价:</span>
                            <span>${emr.cost}</span>
                        </td>
                        <td>
                            <span>时间:</span>
                            <span><fmt:formatDate value="${emr.createOn}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
        </tfoot>
    </table>
    <script>
        try {
            $(function () {
                if ("${printModel}" == "预览打印") {

                    $.print("#printDiv");

                }
            })
        } catch (e) {

        }
    </script>
</div>
</body>
</html>
