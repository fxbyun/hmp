<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/8/18
  Time: 15:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>选择病例</title>
    <script>
        $(function () {
            //设置偶数行和奇数行
            $(".wx-sel-case>ul>li:odd").addClass("backg4");   //为奇数行设置样式(添加样式类)
            $(".wx-sel-case>ul>li:even").addClass("backg3");  // 为偶数行设置样式类
        });

    </script>
</head>
<body>
<div class="warp">
    <div class="out-detail text-center">
        <i class="fa fa-angle-left" onclick="javascript:history.go(-1)"></i>
        <span class="text-center">选择病例</span>
    </div>
    <div class="wx-sel-case">
        <ul>
            <li onclick="javascript:location.href='${ctx}/consultation/conConsulting'">
                <div>2016/02/12 17:30</div>
                <div>华佗门诊</div>
            </li>
            <li onclick="javascript:location.href='${ctx}/consultation/conConsulting'">
                <div>2016/02/12 17:30</div>
                <div>桥东李医生门诊</div>
            </li>
            <li onclick="javascript:location.href='${ctx}/consultation/conConsulting'">
                <div>2016/02/12 17:30</div>
                <div>福生门诊</div>
            </li>
            <li onclick="javascript:location.href='${ctx}/consultation/conConsulting'">
                <div>2016/02/12 17:30</div>
                <div>华佗门诊</div>
            </li>
            <li onclick="javascript:location.href='${ctx}/consultation/conConsulting'">
                <div>2016/02/12 17:30</div>
                <div>桥东李医生门诊</div>
            </li>
            <li onclick="javascript:location.href='${ctx}/consultation/conConsulting'">
                <div>2016/02/12 17:30</div>
                <div>福生门诊</div>
            </li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
        </ul>
    </div>
</div>

</body>
</html>
