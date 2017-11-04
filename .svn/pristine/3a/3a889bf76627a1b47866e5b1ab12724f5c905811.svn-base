<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/1/9
  Time: 16:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="hmp" uri="/WEB-INF/TLD/HmpLoadStaticFile.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>A5处方笺-分页打印</title>
    <script type="text/javascript" src="${ctx}/assets/jquery/jquery-1.11.2.min.js"></script>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/print.lodop/LodopFuncs.js" type="js"/>
    <script type="application/javascript" src="http://localhost:8000/CLodopfuncs.js?priority=1"></script>
    <object id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0
            style="position:absolute;left:0;top:-10px;"></object>
    <object id="LODOP_EM" TYPE="application/x-print-lodop" width=0 height=0
            style="position:absolute;left:0;top:-10px;"></object>

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
</head>
<body style="padding:2px 0;">
<div class="main mCenter" id="printDiv">
    <div id="div1" style="width: 500px;">
        <table width="100%">
            <thead>
            <tr class="main-header pd_l" style="line-height: 40px;">
                <td colspan="4" style="text-align: center;">
                    <strong style="font-size:1.5em; ">${emr.doctor.outpatientService}处方笺</strong>
                </td>

            </tr>
            <tr class="main-sTitle" style="line-height: 1.5em;">
                <td style="width: 25%">
                    <span>患者姓名:</span>
                    <input type="text" style="width: 3em; border: none; border-bottom: 1px solid #666;"
                           value="${emr.patientName}" readonly/>
                </td>
                <td style="width: 20%">
                    <span>性别:</span>
                    <input type="text" style="width: 3em; border: none; border-bottom: 1px solid #666;"
                           value="${genderMap[emr.patient.gender]}"
                           readonly/>
                </td>
                <td style="width: 20%; overflow:hidden;">
                    <span style="float:left;">年龄:</span>
                    <div style="width: 3em; float: left; border-bottom:1px solid #666; font-size: 14px; margin-left: 5px; line-height: 21px;">${emr.patient.age}</div>
                    <%--<input type="text" style="width: 3em" class="" value="${emr.patientName}" readonly/>--%>
                    <%--<span>${emr.patient.age}</span>--%>
                </td>
                <td style="width: 35%">
                    <span>病史:</span>
                    <input type="text" style="width: 70%; border: none; border-bottom: 1px solid #666;"
                           value="<c:forEach var="tag" items="${emr.patient.patientTagList}">${tag.name};</c:forEach>"
                           readonly/>
                </td>
            </tr>
            <tr class="main-sTitle" style="line-height: 1.2em;">
                <td colspan="2">
                    <span>联系方式:</span>
                    <input type="text" style="width: 60%; border: none; border-bottom: 1px solid #666;"
                           value="${emr.patient.mobile}" readonly/>
                </td>
                <td colspan="2">
                    <span>地址:</span>
                    <input type="text" style="width: 80%; border: none; border-bottom: 1px solid #666;"
                           value="<c:choose>
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
            <tr class="main-sTitle" style="line-height: 1.2em;">
                <td colspan="4">
                    <span>患者主诉:</span>
                    <input type="text" style="width: 82%; border: none; border-bottom: 1px solid #666;" value="
                <c:if test="${empty emr.mainSuit}">无</c:if><c:if test="${not empty emr.mainSuit}">${emr.mainSuit.replace(";","")}</c:if><c:if test="${not empty emr.remarks}">${emr.remarks}</c:if>"
                           readonly/>
                </td>
            </tr>
            <tr class="main-sTitle" style="line-height: 1.2em;">
                <td colspan="2">
                    <span>体征:</span>
                    <input type="text" style="width: 12em; border: none; border-bottom: 1px solid #666;" value="<c:forEach items="${emr.vitalSignList}" var="vs" varStatus="status"><c:if
                                test="${not empty vs.value}">${vitalSignLabels[vs.type]}${vs.value}${vitalSignUnits[vs.type]}
                            <c:if test="${not empty vitalSignUnits[vs.type]}">;&nbsp;</c:if></c:if></c:forEach>"
                           readonly/>
                </td>
                <td colspan="2">
                    <span>初步诊断:</span>
                    <input type="text" style="width: 67%; border: none; border-bottom: 1px solid #666;"
                           value="<c:if test="${empty emr.diagnosisResult}">无</c:if><c:if
                                test="${not empty emr.diagnosisResult}">${emr.diagnosisResult}</c:if>" readonly/>
                </td>
            </tr>
            <tr class="main-sTitle" style="line-height: 1.2em;">
                <td colspan="4" style="border-bottom: 2px solid black;"></td>
            </tr>
            </thead>
        </table>
    </div>
    <div id="div2" style="width: 530px;min-height: 500px;">
        <table>
            <tr style="height: 0px;">
                <td colspan="4"><%-- 此处是做间隔用 --%></td>
            </tr>
            <tr>
                <td colspan="4">
                    <span class="font-2_5" style="font-size: 25px;">R<span class="font-2"
                                                                           style="font-size: 20px;">p：</span></span>

                </td>
            </tr>
            <c:set var="westernMedicinesByUseMode" value="${emr.westernMedicinesByUseModeAndGruopId}"/>
            <c:set var="xwString" value="${'针灸, 拔罐, 按摩,贴敷'}"/>
            <c:if test="${not empty westernMedicinesByUseMode}">
                <c:forEach var="entry" items="${westernMedicinesByUseMode}">

                    <c:if test="${printType.equals('ALL')}">
                        <c:if test="${!doctor.needAlonePrinTypeStrings.contains(entry.key)}">
                            <c:forEach varStatus="status2" var="em2" items="${entry.value}">
                                <tr style="line-height: 1.7em;">
                                    <td colspan="2">
                                        <strong>
                                                ${entry.key}
                                        </strong>
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
                                <c:forEach varStatus="status" var="em" items="${em2.value}">
                                    <tr style="line-height: 1.4em;">
                                        <td style="padding-left: 2em;" colspan="2">

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
                                                     <c:if test="${em.copies!=1.0&&em.copies!=0&&em.copies!=null}">
                                                         x${em.copies}&nbsp;份&nbsp;
                                                     </c:if>
                                                 </span>
                                                    <%----%>
                                                <span>
                                                     <c:if test="${em.specialInstructions!=null&&em.specialInstructions!=''}">
                                                         (${em.specialInstructions})
                                                     </c:if>
                                                 </span>

                                            </div>
                                            <div class="pt-detail-xq2" style="padding-left: 30px;">
                                                <c:if test="${em.hasUsage}">
                                                    <span>${em.useTimes}</span><span>${em.useQty}${em.useUnit}</span>
                                                    <span>${em.usingTime}</span>
                                                </c:if>
                                            </div>

                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:forEach>
                        </c:if>
                    </c:if>

                    <c:if test="${!printType.equals('ALL')}">
                        <c:if test="${doctor.needAlonePrinTypeStrings.contains(entry.key)}">
                            <%--<tr style="line-height: 1.5em;">
                                <td colspan="2">

                                </td>
                            </tr>--%>
                            <c:forEach varStatus="status2" var="em2" items="${entry.value}">
                                <tr style="line-height: 1.7em;">
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
                                <c:forEach varStatus="status" var="em" items="${em2.value}">
                                    <tr style="line-height: 1.4em;">
                                        <td colspan="2" style="padding-left: 2em;">

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
                                                         <c:if test="${em.copies!=1.0&&em.copies!=0&&em.copies!=null}">
                                                             x${em.copies}&nbsp;份&nbsp;
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
                                                    <span>${em.useTimes}</span><span>${em.useQty}${em.useUnit}</span>
                                                    <span>${em.usingTime}</span>
                                                </c:if>
                                            </div>

                                        </td>

                                    </tr>
                                </c:forEach>
                            </c:forEach>
                        </c:if>
                    </c:if>
                </c:forEach>
            </c:if>

            <c:set var="chineseMedicinesByUseMode" value="${emr.chinaMedicinesByUseModeAndGruopId}"/>
            <c:if test="${not empty chineseMedicinesByUseMode}">
                <c:forEach var="entry" items="${chineseMedicinesByUseMode}">
                    <c:if test="${printType.equals('ALL')}">
                        <c:if test="${!doctor.needAlonePrinTypeStrings.contains(entry.key)}">
                            <c:forEach varStatus="status2" var="em2" items="${entry.value}">
                                <tr style="line-height: 1.8em;">
                                    <td colspan="2">
                                        <strong>${entry.key}<c:if
                                                test="${'煎服'.equals(entry.key)}">(${emr.chineseQty}副)</c:if></strong>
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

                                            <div class="chinese-print" style="width:50%; float:left;">
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
                                                             <c:if test="${em.copies!=1.0&&em.copies!=0&&em.copies!=null}">
                                                                 x${em.copies}&nbsp;#&nbsp;
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
                            <%--<tr>
                                <td colspan="2">

                                </td>
                            </tr>--%>
                            <c:forEach varStatus="status2" var="em2" items="${entry.value}">
                                <tr style="line-height: 1.8em;">
                                    <td colspan="2">
                                        <strong>${entry.key}<c:if
                                                test="${'煎服'.equals(entry.key)}">(${emr.chineseQty}副)</c:if></strong>
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
                                            <div class="chinese-print" style="width:50%; float:left;">
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
                                                             <c:if test="${em.copies!=1.0&&em.copies!=0&&em.copies!=null}">
                                                                 x${em.copies}&nbsp;#&nbsp;
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
                        <%--<tr style="line-height: 1.5em;">
                            <td colspan="2">

                            </td>
                        </tr>--%>
                        <c:forEach varStatus="status2" var="em2" items="${entry.value}">
                            <tr style="line-height: 1.7em;">
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
                            <c:forEach varStatus="status" var="em" items="${em2.value}">
                                <tr style="line-height: 1.4em;">
                                    <td colspan="2" style="padding-left: 2em;">
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
                                                         <c:if test="${em.copies!=1.0&&em.copies!=0&&em.copies!=null}">
                                                             x${em.copies}&nbsp;#&nbsp;
                                                         </c:if>
                                                     </c:if>
                                                     <c:if test="${em.specialInstructions!=null&&em.specialInstructions!=''}">
                                                         (${em.specialInstructions})
                                                     </c:if>

                                                     </span>
                                        </div>
                                        <div class="pt-detail-xq2" style="padding-left: 30px;">
                                            <c:if test="${em.hasUsage}">
                                                <span>${em.useTimes} &nbsp;${em.useQty}${em.useUnit}</span>
                                                <span>${em.usingTime}</span>
                                            </c:if>
                                        </div>

                                    </td>
                                </tr>
                            </c:forEach>
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
                                        <div class="chinese-print" style="width:50%; float:left;">
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
                                                     <c:if test="${em.copies!=1.0&&em.copies!=0&&em.copies!=null}">
                                                         x${em.copies}&nbsp;#&nbsp;
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
    </div>
    <div id="div3" style="width: 530px;">
        <table width="100%">
            <colgroup width="50%"></colgroup>
            <colgroup width="50%"></colgroup>
            <tfoot>
            <tr>
                <td colspan="2" style="border-top: 2px solid black;"></td>
            </tr>
            <tr style="line-height: 1em;">
                <td>
                    <span>处方医生:</span>
                    <span>${emr.doctor.name}</span>
                    <%--<input type="text"/>--%>
                </td>
                <td>
                    <span>药剂师:</span>
                    <%--<input type="text"/>--%>
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
            </tfoot>
        </table>
    </div>
    <br>
    <%--<button type="button" value="print" onclick="PreviewMytable()">print</button>--%>
    <script>
        function PreviewMytable() {

            var LODOP = getLodop();
//            LODOP.PRINT_INIT("分页打印表格1");
            LODOP.PRINT_INITA(0, 0, 500, 333, "分页打印表格1");
            var strStyle = "<style> table,td,th {border-width: 1px;border-style: solid;border-collapse: collapse}</style>"

            LODOP.SET_PRINT_PAGESIZE(1, 0, 0, "A5");

//            LODOP.SET_PRINT_PAGESIZE(1, "1480", "2100", "A4");


            //表头
            LODOP.ADD_PRINT_HTM("10px", "20px", "500px", "250px", document.getElementById("div1").innerHTML);
            LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
            LODOP.SET_PRINT_STYLEA(0, "LinkedItem", 1);

            //表内容
            LODOP.ADD_PRINT_TABLE("160px", "20px", "450px", "BottomMargin:50px", strStyle + document.getElementById("div2").innerHTML);
            LODOP.SET_PRINT_STYLEA(0, "Vorient", 3);

            //表尾
            LODOP.ADD_PRINT_HTM("800px", "20px", "500px", "185px", document.getElementById("div3").innerHTML);
            LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
            LODOP.SET_PRINT_STYLEA(0, "LinkedItem", 1);
//            LODOP.ADD_PRINT_HTM(1, 600, 300, 100, "总页号：<font color='#0000ff' format='ChineseNum'><span tdata='pageNO'>第##页</span>/<span tdata='pageCount'>共##页</span></font>");
//            LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
//            LODOP.SET_PRINT_STYLEA(0, "Horient", 1);
//            LODOP.PREVIEW();
            LODOP.PRINT();
            try {
                var index = parent.layer.getFrameIndex(window.name);
                setTimeout(function () {
                    parent.layer.close(index);
                }, 2000)
            } catch (e) {
                console.info(e);
            }
        }
        $(function () {
            try {
                parent.parent.Alert.warning("打印任务进行中,请勿操作!");
            } catch (e) {
                console.info(e);
            }
            setTimeout(function () {
                SetPrintA5();
            }, 500);
        });


        var iPrinterCountVar;
        //设置打印机 为A5尺寸
        function SetPrintA5() {
            CreatePrinterList();
            LODOP = getLodop();
            LODOP.PRINT_INIT("");
            if (LODOP.CVERSION) CLODOP.On_Return = function (TaskID, Value) {
                console("Value:" + Value + " || " + "TaskID:" + TaskID);
            };
            for (var i = 0; i < iPrinterCountVar; i++) {
                var strResult = LODOP.SET_PRINT_MODE("WINDOW_DEFPAGESIZE:" + i, "A5(148x210 mm)");
                console.info("i:" + i + "  ||  " + "strResult:" + strResult);
                if (!LODOP.CVERSION)
                    console.info("i:" + i + "  ||  " + "strResult:" + strResult);
            }

            PreviewMytable();
//            PreviewMytable();

        }

        //获取打印机
        function CreatePrinterList() {
            LODOP = getLodop();
            iPrinterCountVar = LODOP.GET_PRINTER_COUNT();
        }

    </script>
</div>
</body>
</html>
