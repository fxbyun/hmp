<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/7/13
  Time: 11:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<%@ include file="/WEB-INF/commons/staticInclude.jsp" %>
<html>
<head>
    <title>类别管理</title>
    <style>
        .layui-layer .layui-anim .layui-layer-dialog .layui-layer-prompt {
            top: 70px;
        }

        th, td {
            text-align: center;
        }

        tr {
            height: 40px;
        }
    </style>
    <script>
        $(function () {
            //设置偶数行和奇数行
            $("tbody>tr:odd").addClass("backg");   //为奇数行设置样式(添加样式类)
            $("tbody>tr:even").addClass("backg2");  // 为偶数行设置样式类
            parent.layer.close(parent.index_Layer_hunde);


        });

    </script>

</head>
<body>
<div class="category">
    <table border="0" width="100%">
        <thead>
        <tr>
            <th>药方类别</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="entry" items="${categories}">
            <tr>
                <td>${entry.name}</td>
                <td>
                    <button class="btn btn-default"
                            onclick="openMobileEditCategory('${entry.id}','${entry.name}')"
                            style="margin-right: 5px; width: 45%;">编辑
                    </button>
                    <button class="btn btn-default" onclick="delMobileCategory(${entry.id})"><i
                            class="fa fa-trash-o"></i>删除
                    </button>
                </td>
            </tr>
        </c:forEach>
        <%--<tr>--%>
        <%--<td>常见症状和急症</td>--%>
        <%--<td>--%>
        <%--<a href="#"  class="btn btn-default">编辑</a>--%>
        <%--<button type="button" class="btn btn-default"><i class="glyphicon glyphicon-trash"></i>删除</button>--%>
        <%--</td>--%>
        <%--</tr>--%>

        </tbody>
    </table>
    <p class="text-center" style="margin-top: 2rem;">
        <a class="btn btn-success" href="#" onclick="openMobileAddCategory()">新增类别</a>
        <button type="button" class="btn btn-default" style="width: 22%; margin-left: 1rem;"
                onclick="closeLayer()">关闭
        </button>
    </p>
</div>
<script>
    function closeLayer() {
        parent.utlis_HiddOrShowHearAndBodyAndFooter(HIDD_SHOW.Show, "categroy");
    }
</script>
</body>
</html>
