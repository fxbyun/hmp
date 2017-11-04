<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/8/19
  Time: 14:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>我的咨询</title>
    <script>
        $(function () {
            $("#person").addClass("active");
        })
    </script>
</head>
<body>
<div class="warp">
    <div class="out-detail text-center">
        <i class="fa fa-angle-left" onclick="javascript:history.go(-1)"></i>
        <span class="text-center">我的咨询</span>
    </div>
    <div class="per-my-reg">
        <ul>
            <li>
                <%--<div class="per-my-title"><span class="float-left">挂号详情</span><span class="float-right">已就诊</span></div>--%>
                <div class="per-my-reginfo">
                    <img class="float-left" src="/assets/images/img4.png" width="52" height="58" alt="医生头像"
                         style="margin: 0.5em 0;">
                    <div class="float-left" style="padding-left: 1em;">
                        <a href="#" style="line-height: 2em;">李医生</a>
                        <p><span>门诊：华佗门诊</span></p>
                        <p><span>擅长：上呼吸道感染</span></p>
                    </div>
                </div>
                <div class="per-my-cnsultinfo">
                    <p><span>就诊人&nbsp;&nbsp;&nbsp;&nbsp;</span><span>张三</span></p>
                    <p><span>预约时间</span><span>2016-05-20 14:20</span></p>
                </div>
                <div class="text-right per-my-btn">
                    <a href="${ctx}/consultation/conEvaluate" class="btn btn-default">写评价</a>
                    <a href="${ctx}/consultation/conConsulting" class="btn btn-default">咨询</a>
                </div>
            </li>
        </ul>
    </div>

</div>
</body>
</html>
