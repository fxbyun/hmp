<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/10/9
  Time: 14:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/advInclude.jsp" %>
<%@ taglib prefix="hmp" uri="/WEB-INF/TLD/HmpLoadStaticFile.tld" %>
<html>
<head>
    <title>Title</title>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/validityManage/validity.js" type="js"/>
</head>
<body style="background-color: #fff;">
<div class="text-center" style="margin-top: 15px;">
    <label style="padding-right: 1.2em;" for="supplierName">供应商名称</label>
    <input id="supplierName" value="${supplier.name}" class="form-control" style="width: 60%; display: inline-block;">
</div>
<p class="text-center" style="margin-top: 30px;">
    <button id="saveSupplier" onclick="saveSupplier(${supplier.id})" class="btn btn-success" type="button">保存供应商
    </button>
    <button id="btnClose" class="btn btn-default" type="button" style="width: 80px; margin-left: 5px;">关闭</button>
</p>
</body>
</html>
