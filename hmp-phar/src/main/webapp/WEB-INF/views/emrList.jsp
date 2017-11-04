<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <title>易佳诊健康管理平台</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <meta http-equiv="Cache-Control" content="no-store"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <meta http-equiv="Expires" content="0"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link href="${ctx}/assets/bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/assets/styles/style.css">
    <script src="${ctx}/assets/jquery/jquery-1.11.2.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        function toLink(id){
            window.location.href = "${ctx}/emr/" + id;
        }
    </script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <c:if test="${empty emrList}"></c:if>
            <div class="text-left tcb"><h4>点击请选择患者</h4></div>
            <table class="table table-striped" style="border:1px #DDDDDD solid">
                <tbody>
                <c:forEach items="${emrList}" var="emr">
                    <tr onclick="toLink(${emr.id})">
                        <td><fmt:formatDate value="${emr.createOn}" type="date" pattern="yyyy/MM/dd h:m"/></td>
                        <td>${emr.medicineName}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<script src="${ctx}/assets/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>
