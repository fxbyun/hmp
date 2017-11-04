<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/7/13
  Time: 11:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>类别管理</title>
    <script>
        $(function () {
            //设置偶数行和奇数行
            $("tbody>tr:odd").addClass("backg");   //为奇数行设置样式(添加样式类)
            $("tbody>tr:even").addClass("backg2");  // 为偶数行设置样式类
        });

    </script>
</head>
<body>
<div class="category">
    <table border="0">
        <thead>
        <tr>
            <th>药方类别</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>常见症状和急症</td>
            <td>
                <a href="${ctx}/learn/editCategory" class="btn btn-default">编辑</a>
                <button type="button" class="btn btn-default"><i class="glyphicon glyphicon-trash"></i>删除</button>
            </td>
        </tr>
        <tr>
            <td>传染性疾病</td>
            <td>
                <a href="${ctx}/learn/editCategory" class="btn btn-default">编辑</a>
                <button type="button" class="btn btn-default"><i class="glyphicon glyphicon-trash"></i>删除</button>
            </td>
        </tr>
        <tr>
            <td>常见症状和急症</td>
            <td>
                <a href="${ctx}/learn/editCategory" class="btn btn-default">编辑</a>
                <button type="button" class="btn btn-default"><i class="glyphicon glyphicon-trash"></i>删除</button>
            </td>
        </tr>
        <tr>
            <td>传染性疾病</td>
            <td>
                <a href="${ctx}/learn/editCategory" class="btn btn-default">编辑</a>
                <button type="button" class="btn btn-default"><i class="glyphicon glyphicon-trash"></i>删除</button>
            </td>
        </tr>
        </tbody>
    </table>
    <p class="text-center" style="margin-top: 2rem;">
        <a class="btn btn-success" href="${ctx}/learn/addCategory">新增类别</a>
        <button type="button" class="btn btn-default" style="width: 22%; margin-left: 1rem;"
                onclick="javascript:history.go(-1);">关闭
        </button>
    </p>
</div>
</body>
</html>
