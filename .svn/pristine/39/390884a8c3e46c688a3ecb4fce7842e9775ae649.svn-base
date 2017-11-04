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
    <script src="${ctx}/assets/jquery/jquery-1.11.2.min.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/assets/styles/style.css">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <div class="text-left tcb"><h4>个人信息</h4></div>
            <div class="well well-sm emr">
                <table>
                    <tr style="height:30px">
                        <td align="right"><b>姓名：</b></td>
                        <td>${phar.name}</td>
                    </tr>
                    <tr style="height:30px">
                        <td align="right"><b>性别：</b></td>
                        <td><c:if test="${phar.gender == 'Male'}">男</c:if><c:if test="${phar.gender == 'Female'}">女</c:if></td>
                    </tr>
                    <tr style="height:30px">
                        <td align="right"><b>出生年月：</b></td>
                        <td><fmt:formatDate value='${phar.birthday}' pattern='yyyy/MM/dd'/></td>
                    </tr>
                    <tr style="height:30px">
                        <td align="right"><b>手机：</b></td>
                        <td>${phar.mobile}</td>
                    </tr>
                    <tr style="height:30px">
                        <td align="right"><b>身份证：</b></td>
                        <td>${phar.sfId}</td>
                    </tr>
                    <tr style="height:30px">
                        <td align="right"><b>住址：</b></td>
                        <td>${phar.address}</td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>
<script src="${ctx}/assets/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>
