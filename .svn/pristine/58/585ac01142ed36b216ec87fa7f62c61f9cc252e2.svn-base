<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/7/14
  Time: 15:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>药方名称</title>
    <script>
        $(function () {
            $("#entryPre").addClass("active");
        });
    </script>
</head>
<body>
<div>
    <!-- Nav tabs -->
    <div class="pre-detail-nav">
        <ul class="nav nav-tabs">
            <li class="active">
                <a href="${ctx}/learn/index" style="float:left;">病症</a>
                <i class="pre-bx"></i>
            </li>
            <li class="active">
                <a href="${ctx}/learn/symptom" style="float:left;">症状</a>
                <i class="pre-bx"></i>
            </li>
            <li class="active">
                <a href="${ctx}/learn/namePrescription" style="float:left;">药方名称</a> <i class="pre-bx"></i>
            </li>
            <li>
                <a href="${ctx}/learn/chooseDrugs">选择药品</a>
            </li>
        </ul>
    </div>
    <div>
        <p style="font-size: 18px; text-align: center; line-height: 3rem;">给药方起个名字吧</p>

        <p class="pre-name-p"><span>药方名称：</span><input class="form-control" type="text" placeholder="跪求个名字"></p>

        <p style="font-size: 18px; text-align: center; line-height: 3rem; margin-top:5rem;">类别可以助您快速找到药方噢</p>

        <p class="pre-name-p">
            <span>药方名称：</span>
            <select class="form-control">
                <option>常见病症</option>
                <option>儿童病症</option>
            </select>

        </p>
        <div class="pre-cate">
            <a href="${ctx}/learn/categoryManage">新增/修改/删除</a>
        </div>
        <%-- 按钮 --%>
        <div class="text-center pre-btn" style="margin: 10%;">
            <a href="${ctx}/learn/symptom" class="btn btn-success">上一步</a>
            <a href="${ctx}/learn/chooseDrugs" class="btn btn-success">下一步</a>
            <a href="${ctx}/learn/chooseDrugs">跳过</a>
        </div>
        <%-- 按钮 END --%>
    </div>
</div>
</body>
</html>
