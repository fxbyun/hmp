<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="hmp" uri="/WEB-INF/TLD/HmpLoadStaticFile.tld" %>
<!DOCTYPE html>
<html lang="cn">
<head>
    <title>打印处方笺</title>
    <style>
        span.title {
            font-size: 13px;
            letter-spacing: 6px;
            text-align: center;
            padding-bottom: 12px;
            margin-top: 5px;
            border-bottom: double 3px #000000;
            margin-bottom: 5px;
            display: block;
            font-weight: bold;
        }

        .subtitle {
            text-align: center;
            letter-spacing: 2px;
            padding-top: 8px;;
            border-top: dashed 1px #000000;
        }

        p.title {
            font-weight: bold;
            margin: 0px;
            padding: 0px;
        }

        p.item {
            margin: 0px;
            padding: 0px;
            height: auto;
            line-height: 18px;
            width: 260px;
        }

        p.row {
            margin: 0px;
            padding: 0px;
            text-indent: 20px;
            height: 18px;
            line-height: 18px;
        }

        p.row2 {
            text-indent: 38px;
        }

        label.margin15 {
            margin-left: 15px;
        }

        .boole {
            /*text-align: center;*/
            /*letter-spacing: 2px;*/
            margin-bottom: 8px;;
            border-bottom: dashed 1px #000000;
        }

        .font-2_5 {
            /*float:right;*/
            text-align: left;
            font-size: 2.5em;
        }

        .font-2 {
            font-size: 2em;
            margin-left: -7px;
        }

        input[type="radio"] {
            width: 1em;
            height: 10px;
            margin: 0;
        }

        input {
            border: 0;
            line-height: 1.2em;
            height: 1.2em;
        }

        .dia-span {
            line-height: 18px;
            /*float: left;*/
            padding-right: 5px;
            padding-left: 2px;
        }

        .content {
            padding-bottom: 12px;
        }

        .content1 .beizhu {
            overflow: hidden;
        }

        .content1 .beizhu .groupName {
            font-size: 12px !important;
            width: 18% !important;
        }

        .content1 .beizhu .groupName + div {
            width: 82% !important;
        }

    </style>
    <script src="${ctx}/assets/jquery/jquery-1.11.2.min.js" type="text/javascript"></script>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/print.lodop/LodopFuncs.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/print.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/acupuncturePoint.js" type="js"/>

    <object id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0
            style="position:absolute;left:0px;top:-10px;"></object>
    <object id="LODOP_EM" TYPE="application/x-print-lodop" width=0 height=0
            style="position:absolute;left:0px;top:-10px;"></object>
    <script type="application/javascript">
        <%--//打印处方签--%>
        <%--function printA5or80CfqPullTypeASAloen(type) {--%>
        <%--//            if (type == "A5打印") {--%>
        <%--&lt;%&ndash;YJZ_Printer.printUrl('../../pub/printRpA5/${emr.id}?type=alone');&ndash;%&gt;--%>
        <%--//            } else {--%>
        <%--&lt;%&ndash;YJZ_Printer.print80Url('../../pub/printRp/${emr.id}?type=alone');&ndash;%&gt;--%>
        <%--//            }--%>
        <%--}--%>
        <%
       int medSize=0;
       %>

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

            $("div[class='content']").each(function () {
                if ($(this).find("span").size() <= 1) {
                    $(this).remove();
                }
            })

            $("span[tmpname='size']").each(function () {
                $(this).text($(this).text().replace(".0#", "#"));
            })
        })
    </script>
</head>
<body>
<div style="padding:2px 0;font-size: 12px;width:75mm;">
    <span class="title">${doctor.outpatientService}处方签</span>

    <p class="item">
        <label>姓名：</label>${emr.patientName}
        <label class="margin15">性别：</label>${genderMap[emr.patient.gender]}
        <label class="margin15">年龄：</label>${emr.patient.age}
        <br />
        <label>联系方式：</label>${emr.patient.mobile}
        <br>
        <label>地址：</label>${emr.patient.address}
        <br>
        <label>病史：</label><c:forEach var="tag" items="${emr.patient.patientTagList}">
        ${tag.name};</c:forEach>
    </p>


    <p class="item"><label>患者主诉：</label>
        <c:if test="${empty emr.mainSuit}">无</c:if>
        <c:if test="${not empty emr.mainSuit}">${emr.mainSuit.replace(";","")}</c:if>
        <c:if test="${not empty emr.remarks}">${emr.remarks}</c:if>
    </p>

    <p class="item"><label>生命体征：</label>
        <c:forEach items="${emr.vitalSignList}" var="vs" varStatus="status"><c:if
                test="${not empty vs.value}">${vitalSignLabels[vs.type]}${vs.value}${vitalSignUnits[vs.type]}<c:if
                test="${not empty vitalSignUnits[vs.type]}">;&nbsp;</c:if></c:if></c:forEach>
    </p>

    <p class="item"><label>初步诊断：</label>
        <c:if test="${empty emr.diagnosisResult}">无</c:if>
        <c:if test="${not empty emr.diagnosisResult}">${emr.diagnosisResult}</c:if>
    </p>

    <p class="item"><label>复诊：</label>
        <input type="radio" name="days" value="1"><span>1天</span>
        <input type="radio" name="days" value="2"><span>2天</span>
        <input type="radio" name="days" value="3"><span>3天</span>
        <input type="radio" name="days" value="4"><input type="text" class="dia-span" id="thoerDays"
                                                         style="border-bottom: 1px solid #777; width: 6em;" />


    </p>

    <p class="item boole">
        <input type="radio" name="times" value="1" style="margin-left: 3.1em;"><span>上午</span>
        <input type="radio" name="times" value="2"><span>下午</span>
    </p>
    <br />

    <div id="medContext">

        <span class="font-2_5">R </span>
        <span class="font-2">p：</span>

        <div style="padding: 5px 0 5px 20px; overflow: hidden;">
            <c:set var="westernMedicinesByUseMode" value="${emr.westernMedicinesByUseModeAndGruopId}" />
            <c:set var="xwString" value="${'针灸, 拔罐, 按摩,贴敷'}" />

            <c:if test="${not empty westernMedicinesByUseMode}">

                <c:forEach var="entry" items="${westernMedicinesByUseMode}">
                    <c:if test="${printType.equals('ALL')}">
                        <div class="content">
                            <div><span><strong>${entry.key}</strong></span></div>
                            <c:forEach varStatus="status2" var="em2" items="${entry.value}">
                                <c:if test="${!doctor.needAlonePrinTypeStrings.contains(entry.key)}">

                                    <div data-ids="${em2}" style="overflow:hidden;">
                                        <span style="float:left; line-height: 40px; padding-right: 25px;">
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

                                        <div style="width: 80%;<c:if
                                                test="${not empty groupArrys[em2.key]}">float:left;</c:if>">
                                            <c:forEach varStatus="status" var="em" items="${em2.value}">
                                                <% medSize += 1;%>
                                                <script type="application/javascript">
                                                    var data = {
                                                        "medicineName": "${em.medicineName}",
                                                        "qty": "${em.qty}",
                                                        "unitLabel": "${em.unitLabel}",
                                                        "copies": "${em.copies}",
                                                        "medicineId": "${em.medicineId}",
                                                        "companyId": "${em.companyId}",
                                                        "qty": "${em.qty}",
                                                        "rate": "${em.rate}",
                                                        "unit": "${em.unit}",
                                                        "useMode": "${em.useMode}",
                                                        "hasUsage": "${em.hasUsage}",
                                                        "specialInstructions": "${em.specialInstructions}",
                                                        "standard":"${em.standard}"
                                                    }
                                                    <%--createMedData(data, ${emr.id})--%>
                                                </script>
                                                <div class="col-md-5 col-sm-5">
                                                    <div class="pt-detail-xq1">
                                        <span>
                                                ${status.count}、
                                        </span><span>${em.medicineName}&nbsp;${em.standard}</span>
                                                        <span tmpName="size">${em.qty}${medicineUnits[em.unit]}
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
                                </c:if>
                            </c:forEach>
                        </div>
                    </c:if>

                    <c:if test="${!printType.equals('ALL')}">
                        <div class="content">
                            <div><span><strong>${entry.key}</strong></span></div>
                            <c:forEach varStatus="status2" var="em2" items="${entry.value}">
                                <c:if test="${doctor.needAlonePrinTypeStrings.contains(entry.key)}">

                                    <div data-ids="${em2}" style="overflow:hidden;">
                                        <span style="float:left; line-height: 40px; padding-right: 25px;">
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

                                        <div style="width: 80%;<c:if
                                                test="${not empty groupArrys[em2.key]}">float:left;</c:if>">
                                            <c:forEach varStatus="status" var="em" items="${em2.value}">
                                                <% medSize += 1;%>

                                                <script type="application/javascript">
                                                    var data = {
                                                        "medicineName": "${em.medicineName}",
                                                        "qty": "${em.qty}",
                                                        "unitLabel": "${em.unitLabel}",
                                                        "copies": "${em.copies}",
                                                        "medicineId": "${em.medicineId}",
                                                        "companyId": "${em.companyId}",
                                                        "qty": "${em.qty}",
                                                        "rate": "${em.rate}",
                                                        "unit": "${em.unit}",
                                                        "useMode": "${em.useMode}",
                                                        "hasUsage": "${em.hasUsage}",
                                                        "specialInstructions": "${em.specialInstructions}",
                                                        "standard":"${em.standard}"
                                                    }
                                                    <%--createMedData(data, ${emr.id})--%>
                                                </script>
                                                <div class="col-md-5 col-sm-5">
                                                    <div class="pt-detail-xq1">
                                        <span>
                                                ${status.count}、
                                        </span><span>${em.medicineName}&nbsp;${em.standard}</span>
                                                        <span tmpName="size">${em.qty}${medicineUnits[em.unit]}
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
                                </c:if>
                            </c:forEach>
                        </div>
                    </c:if>
                </c:forEach>
            </c:if>
        </div>
        <div style="padding: 5px 0 5px 20px; overflow: hidden;">
            <c:set var="chineseMedicinesByUseMode" value="${emr.chinaMedicinesByUseModeAndGruopId}" />
            <c:if test="${not empty chineseMedicinesByUseMode}">
                <c:forEach var="entry" items="${chineseMedicinesByUseMode}">
                    <c:if test="${printType.equals('ALL')}">
                        <div class="content">
                            <div><span><strong>${entry.key}
                                <c:if test="${entry.key.equals('煎服')}">(${emr.chineseQty}副)</c:if>
                            </strong></span></div>
                            <c:forEach varStatus="status2" var="em2" items="${entry.value}">
                                <c:if test="${!doctor.needAlonePrinTypeStrings.contains(entry.key)}">


                                    <div data-ids="${em2}" style="overflow:hidden;line-height: 30px;">
                                        <span style="float:left;  padding-right: 25px;">
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

                                        <div style="width: 80%;float: right;">
                                            <c:forEach varStatus="status" var="em" items="${em2.value}">
                                                <% medSize += 1;%>
                                                <script type="application/javascript">
                                                    var data = {
                                                        "medicineName": "${em.medicineName}",
                                                        "qty": "${em.qty}",
                                                        "unitLabel": "${em.unitLabel}",
                                                        "copies": "${em.copies}",
                                                        "medicineId": "${em.medicineId}",
                                                        "companyId": "${em.companyId}",
                                                        "qty": "${em.qty}",
                                                        "rate": "${em.rate}",
                                                        "unit": "${em.unit}",
                                                        "useMode": "${em.useMode}",
                                                        "hasUsage": "${em.hasUsage}",
                                                        "specialInstructions": "${em.specialInstructions}",
                                                        "standard":"${em.standard}"
                                                    }
                                                    <%--createChinaMedData(data, ${emr.id})--%>
                                                </script>

                                                <div class="col-md-5 col-sm-5">
                                                    <div class="pt-detail-xq1" style="line-height: 20px">
                                                        <span>
                                                                ${status.count}、
                                                        </span>
                                                        <span>${em.medicineName}&nbsp;${em.standard}</span>
                                                        <span>${em.qty}${medicineUnits[em.unit]}
                                                                <c:if test="${!entry.key.equals('煎服')}">
                                                                    <c:if test="${em.copies!=1.0&&em.copies!=0&&em.copies!=null}">
                                                                        x${em.copies}&nbsp;份&nbsp;
                                                                    </c:if>
                                                                </c:if>
                                                        </span>
                                                        <span>
                                                            <c:if test="${em.specialInstructions!=null&&em.specialInstructions!=''}">
                                                                (${em.specialInstructions})
                                                            </c:if>
                                                        </span>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </div>

                                </c:if>
                            </c:forEach>
                        </div>
                    </c:if>
                    <c:if test="${!printType.equals('ALL')}">
                        <div class="content">
                            <div><span><strong>${entry.key}
                                <c:if test="${entry.key.equals('煎服')}">(${emr.chineseQty}副)</c:if>
                            </strong></span></div>

                            <c:forEach varStatus="status2" var="em2" items="${entry.value}">
                                <c:if test="${doctor.needAlonePrinTypeStrings.contains(entry.key)}">


                                    <div data-ids="${em2}" style="overflow:hidden;line-height: 30px;">
                                    <span style="float:left;  padding-right: 25px;">
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

                                        <div style="width: 80%;float: right;">
                                            <c:forEach varStatus="status" var="em" items="${em2.value}">
                                                <% medSize += 1;%>
                                                <script type="application/javascript">
                                                    var data = {
                                                        "medicineName": "${em.medicineName}",
                                                        "qty": "${em.qty}",
                                                        "unitLabel": "${em.unitLabel}",
                                                        "copies": "${em.copies}",
                                                        "medicineId": "${em.medicineId}",
                                                        "companyId": "${em.companyId}",
                                                        "qty": "${em.qty}",
                                                        "rate": "${em.rate}",
                                                        "unit": "${em.unit}",
                                                        "useMode": "${em.useMode}",
                                                        "hasUsage": "${em.hasUsage}",
                                                        "specialInstructions": "${em.specialInstructions}",
                                                        "standard":"${em.standard}"
                                                    }
                                                    <%--createChinaMedData(data, ${emr.id})--%>
                                                </script>

                                                <div class="col-md-5 col-sm-5">
                                                    <div class="pt-detail-xq1">
                                            <span>
                                                    ${status.count}、
                                            </span>
                                                        <span>${em.medicineName}&nbsp;${em.standard}</span>
                                            <span>${em.qty}${medicineUnits[em.unit]}
                                            <c:if test="${!entry.key.equals('煎服')}">
                                                <c:if test="${em.copies!=1.0&&em.copies!=0&&em.copies!=null}">
                                                    x${em.copies}&nbsp;份&nbsp;
                                                </c:if>
                                            </c:if>
                                            </span>
                                             <span>
                                                <c:if test="${em.specialInstructions!=null&&em.specialInstructions!=''}">
                                                    (${em.specialInstructions})
                                                </c:if>
                                            </span>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </c:if>
                            </c:forEach>
                        </div>
                    </c:if>
                </c:forEach>
            </c:if>
        </div>
    </div>
    <br />
    <br />

    <p class="title" style="border-bottom:  1px dotted #000000"><label>主治医生：</label></p>
    <span>
        <p class="item" style="float: left;width: auto">
            <b>
                药价：${emr.cost}
            </b>
        </p>

        <p class="item" style="float: right;width: auto;margin-right: 30px">
            <label>就诊时间：</label><fmt:formatDate value="${emr.createOn}" pattern="yyyy-MM-dd" />
        </p>
        <br />
         <p>
             <label> 请按照处方笺抓药，并核对患者信息！</label>
         </p>
    </span>


</div>
</body>
<script>

    $("div[class='content']").each(function () {
        if ($(this).find("span").size() <= 1) {
            $(this).remove();
        }
    })
    $(function () {
        $("div[class='content']").each(function () {
            if ($(this).find("span").size() <= 1) {
                $(this).remove();
            }
        })
    })

</script>
</html>
