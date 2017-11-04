<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/12
  Time: 12:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/staticInclude.jsp" %>
<%@ taglib prefix="hmp" uri="/WEB-INF/TLD/HmpLoadStaticFile.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>中医理疗申请单</title>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/styles/print.css" type="css"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/jpPrint/jQuery.print.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/print.js" type="js"/>
</head>
<body>
<div class="print-mg">
    <div class="text-center" style="margin-bottom: 10px;"><span class="print-title">中医理疗申请单</span></div>
    <p style="font-size: 16px;">
        <span>机构名称：${emr.doctor.outpatientService}</span>
        <span style="float: right">就诊时间：<fmt:formatDate value="${emr.createOn}" pattern="yyyy/MM/dd HH:mm"/></span>
    </p>
    <table width="100%" border="1">
        <colgroup width="17%"></colgroup>
        <colgroup width="15%"></colgroup>
        <colgroup width="10%"></colgroup>
        <colgroup width="10%"></colgroup>
        <colgroup width="10%"></colgroup>
        <colgroup width="10%"></colgroup>
        <colgroup width="10%"></colgroup>
        <colgroup width="18%"></colgroup>
        <tr>
            <td>姓名</td>
            <td>${patient.name}</td>
            <td>性别</td>
            <td>${genderMap[patient.gender]}</td>
            <td>年龄</td>
            <td>${patient.age}</td>
            <td>电话</td>
            <td>${patient.mobile}</td>
        </tr>
        <tr>
            <td>患者主诉</td>
            <td colspan="7" style="text-align: left; padding-left: 10px;">
                ${emr.mainSuit}
                <c:if test="${not empty emr.remarks}">
                    <c:if test="${not empty mainSuit}">
                        ,
                    </c:if>
                    ${emr.remarks}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>初步诊断</td>
            <td colspan="7" style="text-align: left; padding-left: 10px;">
                ${emr.diagnosisResult}
            </td>
        </tr>
        <tr>
            <td colspan="2">项目名称</td>
            <td colspan="2">项目次数</td>
            <td colspan="2">特殊说明</td>
            <td colspan="2">项目费用</td>
        </tr>

        <c:forEach items="${therapyList}" var="the">
            <tr>
                <td colspan="2">${the.medicineName}</td>
                <td colspan="2">
                    <c:if test="${not empty the.id}">
                        ${the.useQty}${therapyUnit[the.unit]}X${the.copies}次
                    </c:if>
                </td>
                <td colspan="2"></td>
                <td colspan="2">${the.price}</td>
            </tr>
        </c:forEach>

        <tr>
            <td colspan="4">
                <div class="text-left" style="margin-left: 10px;">申请医生：${emr.doctor.name}</div>
            </td>
            <td colspan="4">
                <div class="text-left" style="margin-left: 10px;">合计费用：
                    ${therapyPrice}
                </div>
            </td>
        </tr>

    </table>
</div>
<script>
    try {
        $(function () {
            $.print(".print-mg");
        })
    } catch (e) {

    }
</script>
</body>
</html>
