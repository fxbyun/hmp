<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/7/13
  Time: 14:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新增药方类别</title>
</head>
<body>
<div style="padding: 1rem 0;">
    <p class="text-center" style="margin-top: 3rem;">
        <label>类别名称</label>
        <input type="text" class="form-control" style="display: inline-block; width: 75%;">
    </p>

    <p class="text-center" style="margin-top:2rem; margin-bottom: 60%;">
        <button type="button" class="btn btn-success">保存类别</button>
        <button type="button" class="btn btn-default" onclick="javascript:history.go(-1);"
                style="width:22%;margin-left: 1rem;">关闭
        </button>
    </p>
</div>

</body>
</html>
