<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/10/11
  Time: 16:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="ele-navbar">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target="#example-navbar-collapse">
                <span class="sr-only">切换导航</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <%--<a href="#" style="width: 420px;" class="navbar-brand"></a>--%>
        </div>
        <div class="collapse navbar-collapse" id="example-navbar-collapse">
            <ul class="nav navbar-nav">
                <li id="nav-index"><a href="${ctx}/adv/index">挂号管理</a></li>
                <li id="nav-charge"><a href="${ctx}/adv/chargeManage">收费管理</a></li>
                <li id="nav-pharmacy"><a href="${ctx}/adv/pharmacyManage">药房管理</a></li>
                <li id="nav-inspect"><a href="${ctx}/adv/inspectManage">检查管理</a></li>
                <li id="nav-validity"><a href="${ctx}/adv/validityManage">有效期管理</a></li>
                <div class="my-infro pull-right">
                    <a href="${ctx}/adv/personalInfo" style="color:white">
                    <span> <c:if test="${empty loginUser.name}">
                        未设置
                    </c:if> ${loginUser.name}</span>
                    </a>
                    <a style="color: white" id="lnkQuit" href="javascript:" class="drop-out">退出</a>
                </div>
            </ul>
            <script type="text/javascript">
                $(function () {
                    $('#lnkQuit').click(function () {
                        layer.confirm("确定退出系统?", {title: '退出', icon: 0}, function () {
                            window.location.href = "${ctx}/logout";
                        })
                    });
                });
            </script>
        </div>
    </div>
</nav>
