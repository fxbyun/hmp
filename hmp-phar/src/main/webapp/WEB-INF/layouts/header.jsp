<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/7/8
  Time: 11:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<div class="header">
    <div class="pull-left">
        <span>易佳诊诊所运营管理系统</span>
    </div>
    <div class="pull-right">
        <%--<a href="${ctx}/info/infoSet">门诊设置</a> <span>|</span>--%>
        <a id="lnkQuit" href="javascript:" class="drop-out">退出</a>
    </div>
</div>
<nav class="nav-tabs nav-wx">
    <ul>
        <li id="entryPre"><a href="${ctx}/learn/index">录入药方</a></li>
        <li id="templatePre"><a href="${ctx}/learn/templatePrescription">模板药方</a></li>
        <li id="myPre"><a href="${ctx}/learn/myPrescription">我的药方</a></li>
    </ul>
</nav>
<script type="text/javascript">
    $(function () {
        $('#lnkQuit').click(function () {
            layer.confirm("确定退出系统?", {title: '退出', icon: 0}, function () {
                window.location.href = "${ctx}/logout";
            })
        });
        /*fn_getNoReadEvaluate();
         window.setInterval(fn_getNoReadEvaluate, 60000);*/
    });
    /*function fn_getNoReadEvaluate() {
     $.getJSON("${ctx}/evaluate", function (result) {
     if (!result.success) {
     $("#showLB").removeClass("hidden");
     }
     })
     }*/
</script>