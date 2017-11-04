<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/8/19
  Time: 10:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>个人主页</title>
    <script>
        $(function () {
            $("#person").addClass("active");
        })
    </script>
</head>
<body>
<div class="warp">
    <div class="">
        <div class="per-user">
            <img src="${ctx}/assets/images/touxiang.png" height="80" width="80" alt="头像">
            <a href="##"><span>
                <c:if test="${empty user.name}">
                    ${user.mobile}
                </c:if>
                <c:if test="${not empty user.name}">
                    ${user.name}
                </c:if>
            </span></a>
        </div>
        <div class="per-solid" style="height: 3em;"></div>
    </div>
    <div class="per">
        <div class="per-help per-solid" onclick="javascript:location.href='${ctx}/personal/myReward'">
            <i class="fa fa-users"></i><span>我的挂号</span><i class="fa fa-angle-right float-right"></i>
        </div>
        <div class="per-help per-solid" onclick="javascript:location.href='${ctx}/personal/perPatientManage'">
            <i class="fa fa-users"></i><span>就诊人管理</span><i class="fa fa-angle-right float-right"></i>
        </div>
        <%--<div class="per-help">
            <i class="fa fa-users"></i><span>帮助中心</span><i class="fa fa-angle-right float-right"></i>
        </div>--%>
        <div class="per-help" onclick="javascript:location.href='${ctx}/personal/aboutUs'">
            <i class="fa fa-users"></i><span>关于我们</span><i class="fa fa-angle-right float-right"></i>
        </div>
    </div>


    <a href="${ctx}/logout" class="btn btn-success per-tagger">
        <span>当前：${user.mobile}</span>
        <p>切换登录账号</p>
    </a>


</div>
</body>
</html>
