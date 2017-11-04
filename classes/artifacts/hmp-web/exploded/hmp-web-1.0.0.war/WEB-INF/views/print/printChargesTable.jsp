<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/12
  Time: 12:32
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
    <title>附加费用缴纳单</title>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/styles/print.css" type="css"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/jpPrint/jQuery.print.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/print.js" type="js"/>
    <script>
        try {
            $(function () {
                $.print(".print-mg");
            })
        } catch (e) {

        }
    </script>
</head>
<body>
<div class="print-mg">
    <div class="text-center" style="margin-bottom: 10px;"><span class="print-title">附加费用缴纳单</span></div>
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
            <td>${emr.patient.name}</td>
            <td>性别</td>
            <td>${genderMap[emr.patient.gender]}</td>
            <td>年龄</td>
            <td>${emr.patient.age}</td>
            <td>电话</td>
            <td>${emr.patient.mobile}</td>
        </tr>
        <%--<tr>
            <td>患者主诉：</td>
            <td colspan="2">
                <c:forEach items="${emr.diagnosisList}" var="oneDiagnosis" varStatus="status">
                    <c:if test="${status.index !=0}">
                        ,
                    </c:if>
                    ${oneDiagnosis.name}
                </c:forEach>
            </td>
            <td>初步诊断：</td>
            <td colspan="4">
                <c:forEach items="${emr.mainSuitList}" var="oneDiagnosis" varStatus="status">
                    <c:if test="${status.index !=0}">
                        ,
                    </c:if>
                    ${oneDiagnosis}
                </c:forEach>
            </td>
        </tr>--%>
        <tr>
            <td>序号</td>
            <td colspan="4">项目名称</td>
            <td colspan="3">项目费用</td>
        </tr>
        <c:forEach items="${emrExtCostList}" var="oneDoctorCost" varStatus="status">
            <tr>
                <td>
                        ${status.count}
                </td>
                <td colspan="4">
                        ${oneDoctorCost.className}
                </td>
                <td colspan="3">
                        ${oneDoctorCost.price}
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="5">
                <div class="text-left" style="margin-left: 10px;">处方医生：${emr.doctor.name}</div>
            </td>
            <td colspan="4">
                <div class="text-left" style="margin-left: 10px;" id="fujia_prices">合计费用：
                    ${fujiaPrices}
                    <%--<fmt:formatDate value="${emr.createOn}" pattern="yyyy/MM/dd HH:mm"/>--%>
                </div>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
