<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="hmp" uri="/WEB-INF/TLD/HmpLoadStaticFile.tld" %>

<!DOCTYPE html>
<html>
<head>
    <title>易佳诊健康管理平台</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <meta http-equiv="Cache-Control" content="no-store" />
    <meta http-equiv="Pragma" content="no-cache" />
    <meta http-equiv="Expires" content="0" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link href="${ctx}/assets/bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
    <script src="${ctx}/assets/jquery/jquery-1.11.2.min.js" type="text/javascript"></script>
    <script src="${ctx}/assets/scripts/acupuncturePoint.js" type="text/javascript"></script>
    <script src="${ctx}/assets/scripts/printLayOut.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/assets/styles/style.css">
    <style>
        hr {
            border-top: 1px solid #A09A9A;
        }

        .font-2_5 {
            /*float:right;*/
            text-align: left;
            font-size: 2em;
        }

        .font-2 {
            font-size: 1.5em;
            margin-left: -7px;
        }
        .beizhu_name span:first-child{
            font-weight: bold;
        }
        .beizhu{
            overflow: hidden;
        }
        .beizhu>span{
            float: left;
        }

    </style>
    <script type="application/javascript">
        var oneMedInfo = [{}
            <c:set var="westernMedicinesByUseMode" value="${emr.westernMedicinesByUseMode}" />
            <c:if test="${not empty westernMedicinesByUseMode}">
            <c:forEach var="entry" items="${westernMedicinesByUseMode}">
            <c:forEach varStatus="status" var="em" items="${entry.value}">
            ,
            {
                'medicineId': "${em.medicine.id}${em.multiplexTag}",
                'medicineName': '${em.medicine.name}',
                'unit': "${em.unit}",
                'unitLabel': "${medicineUnits[em.unit]}",
                'qty': "${em.qty}",
                'copies': "${em.copies}",
                'useMode': "${em.useMode}",
                'hasUsage': "${em.hasUsage}",
                'useTimes': "${em.useTimes}",
                'medType': "${em.medicineType}",
                'usingTime': "${em.usingTime}",
                'useQty': "${em.useQty}",
                "useUnit": "${em.useUnit}",
                "groupIndex": "${em.groupIndex}",
                "specialInstructions": "${em.specialInstructions}",
                "standard": "${em.standard}",
                "openType": "add",
                "companyId": "${em.companyId}",
                "rate": "${em.rate}"
            }
            </c:forEach>
            </c:forEach>
            </c:if>

            <c:set var="chineseMedicinesByUseMode" value="${emr.chineseMedicinesByUseMode}" />
            <c:if test="${not empty chineseMedicinesByUseMode}">
            <c:forEach var="entry" items="${chineseMedicinesByUseMode}">
            <c:forEach varStatus="status" var="em" items="${entry.value}">
            <%--<c:if test="${not empty westernMedicinesByUseMode}">, </c:if>--%>
                ,
            {
                'medicineId': "${em.medicine.id}${em.multiplexTag}",
                'medicineName': '${em.medicine.name}',
                'unit': "${em.unit}",
                'unitLabel': "${medicineUnits[em.unit]}",
                'qty': "${em.qty}",
                'copies': "${em.copies}",
                'useMode': "${em.useMode}",
                'hasUsage': "${em.hasUsage}",
                'useTimes': "${em.useTimes}",
                'medType': "${em.medicineType}",
                'usingTime': "${em.usingTime}",
                'useQty': "${em.useQty}",
                "useUnit": "${em.useUnit}",
                "groupIndex": "${em.groupIndex}",
                "specialInstructions": "${em.specialInstructions}",
                "standard": "${em.standard}",
                "openType": "add",
                "chineseQty": "${emr.chineseQty}",
                "companyId": "${em.companyId}",
                "rate": "${em.rate}"
            }
            </c:forEach>
            </c:forEach>
            </c:if>
        ];

    </script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <div class="text-left tcb">

                <h4 style="text-align: center">
                    ${emr.doctor.outpatientService}
                    <br />
                    处方笺
                </h4></div>
            <div class="well well-sm">
                <table border="0" width="100%" style="font-size: 16px">
                    <tr style="height:30px;">
                        <td align="right" width="80px">患者姓名：</td>
                        <td>${emr.patientName}
                            &nbsp;年龄：${emr.patient.age}
                            &nbsp;性别：<c:if test="${emr.patient.gender == 'Male'}">男</c:if><c:if test="${emr.patient.gender == 'Female'}">女</c:if>
                        </td>
                    </tr>
                    <tr style="height:30px">
                        <td align="right">联系方式：</td>
                        <td>${emr.patient.mobile}</td>
                    </tr>


                    <tr style="height:30px">
                        <td align="right">患者主诉：</td>
                        <td><c:if test="${empty emr.mainSuit}">无</c:if>
                            <c:if test="${not empty emr.mainSuit}">${emr.mainSuit.replaceAll(";",'')}</c:if></td>
                    </tr>
                    <tr style="height:30px">
                        <td align="right">初步诊断：</td>
                        <td><c:if test="${empty emr.diagnosisResult}">无</c:if>
                            <c:if test="${not empty emr.diagnosisResult}">${emr.diagnosisResult}</c:if></td>
                    </tr>
                    <tr style="height:30px">
                        <td align="right">生命体征：</td>
                        <td><c:forEach items="${emr.vitalSignList}" var="vs" varStatus="status">
                            <c:if test="${not empty vs.value}">
                                ${vitalSignLabels[vs.type]}${vs.value}&nbsp;${vitalSignUnits[vs.type]}
                                <c:if test="${not empty vitalSignUnits[vs.type]}">;&nbsp;</c:if>
                            </c:if>
                        </c:forEach></td>
                    </tr>


                    <tr style="height: 1px">
                        <td colspan="2" height="1px">
                            <hr />
                        </td>
                    </tr>
                    <tr>

                        <td colspan="2">
                            <span class="font-2_5">R </span>

                            <span class="font-2">p：</span>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">
                            <%--西药：--%>
                        </td>
                        <td valign="middle" id="medContext">
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" height="1px">
                            <%--<hr/>--%>
                        </td>
                    </tr>
                    <tr>
                        <td align="center">
                            <%--中药${emr.chineseQty}副：--%>
                        </td>
                        <td valign="middle">
                        </td>
                    <tr>
                        <td>主治医师：</td>
                        <td>${emr.doctor.name}</td>
                    </tr>
                    </tr>
                    <tr>
                        <td colspan="2" height="1px">
                            <hr />
                        </td>
                    </tr>
                    <tr>
                        <td>药价:${emr.cost} </td>
                        <td style="text-align: right">
                            就诊时间: <fmt:formatDate value="${emr.createOn}" pattern="yyyy-MM-dd" /></td>
                    </tr>
                    <tr>
                        <td colspan="2">请按照处方笺抓药，并核对患者信息！</td>

                    </tr>

                </table>
                <%--<div style="text-align: center">--%>
                <%--<a href="${ctx}/anon/emr/list" class="btn btn-primary">查看所有患者药单</a>--%>
                <%--</div>--%>
            </div>
        </div>
    </div>
</div>
<script src="${ctx}/assets/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>
