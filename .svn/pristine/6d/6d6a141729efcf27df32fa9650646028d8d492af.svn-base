<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/staticInclude.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
    <script type="text/javascript">
        var ctx = "${ctx}"
    </script>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/login.js" type="js"/>
    <title>易佳诊诊所运营管理平台-登录</title>
</head>
<body>
<div class="warp">
    <%--备注：这里需要这一下后端传过来的error的提示，另外点击获取验证码后样式有点问题--%>
    <div class="text-center"
         style="padding-top: 2.5em; padding-bottom: 5em; overflow: hidden; height: 100%; position: relative;">
        <h3>登录账号</h3>
        <div>
            <form id="login" action="${ctx}/logon" method="post">
                <div class="col-xs-10 login-num">
                    <div class="login-solid">
                        <div class="login-i"><i class="fa fa-user"></i></div>
                        <input name="phoneNo" id="phoneNo" type="text" placeholder="手机号" class="form-control login-in">
                    </div>
                </div>
                <div class="col-xs-10 login-num">
                    <input name="authCode" type="text" class="form-control" style="display: inline-block; width: 50%;" placeholder="输入验证码">
                    <button type="button" class="btn btn-default" id="" onclick="send_code(this)">获取验证码</button>
                </div>
                <button type="submit" class="btn btn-success text-center login-btn" style="width: 100%;">登录</button>
            </form>
        </div>
    </div>
</body>
</html>
