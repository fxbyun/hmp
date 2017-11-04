<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<nav class="ele-navbar">
    <div class="container">
        <ul>
            <li id="nav-diagnosis"><a href="${ctx}/diagnosis">开始坐诊</a></li>
            <li id="nav-emr"><a href="${ctx}/patientManage">患者管理</a></li>
            <li id="nav-consultation">
                <a href="${ctx}/ptConsultation">患者咨询
                    <div id="showLB" class="fa fa-volume-down hidden"></div>
                </a>
            </li>
            <li id="nav-manage"><a href="${ctx}/manage">门诊管理</a></li>
            <div class="my-infro pull-right">
                <a href="${ctx}/notice" style="color:white">
                    <img src="${ctx}/assets/styles/images/e-maill.png">
                    <span id="itemCount">${noReadCount}</span>
                    <spn id="doctorName"><shiro:principal property="name"/></spn>
                    医生
                </a>
                <a style="color: white" id="lnkQuit" href="javascript:" class="drop-out">退出</a>

            </div>
        </ul>

    </div>
</nav>
<script type="text/javascript">
    $(function () {
        $('#lnkQuit').click(function () {
            layer.confirm("确定退出系统?", {title: '退出', icon: 0}, function () {
                window.location.href = "${ctx}/logout";
            })
        });
        fn_getNoReadEvaluate();
        window.setInterval(fn_getNoReadEvaluate, 60000);
    });
    function fn_getNoReadEvaluate() {
        $.getJSON("${ctx}/evaluate", function (result) {
            if (!result.success) {
                $("#showLB").removeClass("hidden");
            }
        })
    }
</script>