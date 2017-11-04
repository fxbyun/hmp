<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/10/13
  Time: 16:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/staticInclude.jsp" %>
<%@ taglib prefix="hmp" uri="/WEB-INF/TLD/HmpLoadStaticFile.tld" %>
<html>
<head>
    <title>编辑药品</title>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/advertising/styles/validity.css" type="css"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/validityManage/validity.js" type="js"/>
    <script>
        $(function () {
            var index = parent.layer.getFrameIndex(window.name);
            $("#btnClose").click(function () {
                parent.layer.close(index);
            });
        })
    </script>
</head>
<body style="background-color: #fff;">
<div class="tag-medicine">
    <div class="content-left text-right">
        <p>
            <label for="medName">药品名称</label>
            <input type="text" class="form-control" id="medName">
        </p>
        <p>
            <label for="medAddress">药厂/产地</label><a href="javascript:" id="btnSelectCompany" class="btn btn-success"
                                                    style="margin-left: 10px;">选择</a>
            <input type="text" class="form-control" id="medAddress" style="width: 50%;">
        </p>
        <p>
            <label for="medSpecif">规&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;格</label>
            <input type="text" class="form-control" id="medSpecif">
        </p>
        <p>
            <label for="medUnit">统计单位</label>
            <input type="text" class="form-control" id="medUnit">
        </p>

    </div>
    <div class="content-right text-right">
        <p>
            <label for="medCode">条&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码</label>
            <input type="text" class="form-control" id="medCode">
        </p>
        <p>
            <label for="medValidity">有&nbsp;效&nbsp;期</label>
            <input type="text" class="form-control" id="medValidity">
        </p>
        <p>
            <label for="medNumber">预&nbsp;警&nbsp;线</label>
            <input type="text" class="form-control" id="medNumber">
        </p>
        <p>
            <label for="price">零&nbsp;售&nbsp;价</label>
            <input type="text" class="form-control" id="price">
        </p>
        <p>
            <label for="price">y预&nbsp;警&nbsp;线</label>
            <input type="text" class="form-control" id="warnLine">
        </p>
    </div>
</div>
<div class="text-center" style="margin-top: 15px;">
    <button class="btn btn-success" type="button" style="width: 80px; margin-right: 5px;">保存</button>
    <button class="btn btn-default" id="btnClose" type="button" style="width: 80px;">关闭</button>
</div>
</body>
</html>
