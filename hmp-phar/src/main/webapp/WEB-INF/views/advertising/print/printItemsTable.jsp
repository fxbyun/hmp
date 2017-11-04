<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/12
  Time: 11:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/advInclude.jsp" %>
<%@ taglib prefix="hmp" uri="/WEB-INF/TLD/HmpLoadStaticFile.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>项目检查申请表</title>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/advertising/styles/print.css" type="css"/>
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
    <div class="text-center" style="margin-bottom: 10px;">
        <span class="print-title">
         <c:forEach items="${emr.emrJClassAdviceDicts}" var="oneJClass" varStatus="status">
             <c:if test="${status.index !=0}">
                 /
             </c:if>
             ${oneJClass.examLabName}
         </c:forEach> 项目申请单
         </span>
    </div>
    <p style="font-size: 16px;">机构名称：${emr.doctor.outpatientService}</p>
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
        <tr>
            <td>患者主诉</td>
            <td colspan="7" style="text-align: left; padding-left: 10px;">

                <c:forEach items="${emr.mainSuitList}" var="oneDiagnosis" varStatus="status">
                    <c:if test="${status.index !=0}">
                        ,
                    </c:if>
                    ${oneDiagnosis}
                </c:forEach>

                <c:if test="${not empty emr.remarks}">
                    <c:if test="${emr.mainSuitList.size()>0}">
                        ,
                    </c:if>
                    ${emr.remarks}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>初步诊断</td>
            <td colspan="7" style="text-align: left; padding-left: 10px;">
                <c:forEach items="${emr.diagnosisList}" var="oneDiagnosis" varStatus="status">
                    <c:if test="${status.index !=0}">
                        ,
                    </c:if>
                    ${oneDiagnosis.name}
                </c:forEach>
            </td>
        </tr>
        <tr>
            <td>检查大项</td>
            <td colspan="2">
                <c:forEach items="${emr.emrJClassAdviceDicts}" var="oneJClass" varStatus="status">
                    <c:if test="${status.index !=0}">
                        /
                    </c:if>
                    ${oneJClass.examLabName}
                </c:forEach>
            </td>
            <td>检查部位</td>
            <td colspan="4">
                <c:forEach items="${emr.emrJClassAdviceDicts}" var="oneJClass" varStatus="status">
                    <c:if test="${status.index !=0}">
                        ,
                    </c:if>
                    ${oneJClass.adviceName}
                </c:forEach>
            </td>
        </tr>
        <tr>
            <td colspan="8">
                <div class="text-left" style="min-height: 190px; padding: 5px 10px;">
                    <span>特殊说明：</span>
                    <span>
                        </br>
                        <c:forEach items="${emr.emrJClassAdviceDicts}" var="oneJClass" varStatus="status">
                            ${status.index +1}、${oneJClass.info}</br>
                        </c:forEach>
                    </span>
                </div>
            </td>
        </tr>
        <tr>
            <td colspan="4">
                <div class="text-left" style="margin-left: 10px;">
                    <span>申请医生：</span>
                    <span>${emr.doctor.name}</span>
                </div>
            </td>
            <td colspan="4">
                <div class="text-left" style="margin-left: 10px;">
                    <span>就诊时间：</span>
                    <span>
                        <fmt:formatDate value="${emr.createOn}" pattern="yyyy/MM/dd HH:mm"/>
                    </span>
                </div>
            </td>
        </tr>
    </table>
</div>
</body>
</html>