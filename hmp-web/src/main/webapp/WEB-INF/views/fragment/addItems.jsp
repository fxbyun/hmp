<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/10/27
  Time: 17:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/staticInclude.jsp" %>
<%@ taglib prefix="hmp" uri="/WEB-INF/TLD/HmpLoadStaticFile.tld" %>
<html>
<head>
    <title>添加项目</title>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/application.js" type="js"/>
    <script>
        $(function () {
            var index = parent.layer.getFrameIndex(window.name);
            $("#btnClose").click(function () {
                parent.layer.close(index);
            });
        });
        function add() {
            $.postJSON("/fragment/addPre", {
                "name": $("#name").val(),
                "price": $("#price").val()
            }, function (ret) {
                if (ret.success) {
                    layer.msg("添加成功!");
                    parent.window.location.reload();
                } else {
                    layer.msg("添加失败")
                }
            })
        }
    </script>
</head>
<body style="background-color: #fff;">
<div style="padding: 10px 20px;">
    <div class="form-group">
        <span>项目名称：</span>
        <input type="text" class="form-control" style="display: inline-block; width: 70%;" id="name">
    </div>
    <div class="form-group">
        <span>项目费用：</span>
        <input type="number" class="form-control" style="display: inline-block; width: 40%;"
               id="price"><span> 元/次</span>
    </div>
    <div class="text-center btn-wd-mr">
        <button type="button" class="btn btn-success" onclick="add()">添加
            <button>
        <button id="btnClose" type="button" class="btn btn-default">取消</button>
    </div>
</div>

</body>
</html>
