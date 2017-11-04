<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/7/15 0015
  Time: 10:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
    <title></title>
    <link href="/assets/styles/admin.css" type="text/css" rel="stylesheet">
</head>
<body>
<form action="${ctx}/doctor/recharegSave" method="post">
    <input type="hidden" name="doctorId" value="${doctor.id}" />
    <p style="padding-left: 5.5em;">
        <label>医生姓名：${doctor.name}</label>
    </p>
    <p style="text-align: center;">
        <label>修改金额：<input name="monery" value="${doctor.msgMoney.deposit}" type="text" /></label>
        <span>${msg}</span>
    </p>
     <p style="padding-left: 5.5em;">
         <label>现有余额：${doctor.msgMoney.deposit}</label>
     </p>


    <p style="text-align: center; margin-top: 30px;">
        <button type="submit" class="btn btn-info" style="margin-right: 10px;">修改</button>
        <button type="button" class="btn btn-default" onclick="parent.layer.closeAll()">关闭</button>
    </p>


</form>
</body>
</html>
