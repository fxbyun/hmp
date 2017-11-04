<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <title>易佳诊诊所运营管理平台-登录</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <meta http-equiv="Cache-Control" content="no-store"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <meta http-equiv="Expires" content="0"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link type="image/x-icon" href="${ctx}/assets/styles/images/favicon.ico" rel="shortcut icon">
    <link href="${ctx}/assets/bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
    <link href="${ctx}/assets/styles/default.min.css" type="text/css" rel="stylesheet"/>
    <script src="${ctx}/assets/jquery/jquery-1.11.2.min.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/assets/styles/style.css">
    <link rel="stylesheet" href="${ctx}/assets/styles/font-awesome/css/font-awesome.min.css">
    <script src="${ctx}/assets/jquery-validation/1.11.1/jquery.validate.min.js" type="text/javascript"></script>
    <script src="${ctx}/assets/jquery-validation/1.11.1/messages_bs_zh.js" type="text/javascript"></script>
    <link href="${ctx}/assets/jquery-validation/1.11.1/validate.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<div class="container" style="width: 100%">
    <div class="login" style="width: 100%">
        <div class="company-name">
            <img src="${ctx}/assets/images/logo2.png" width="600px" alt="易佳诊健康管理平台">
        </div>
        <span class="pull-right release" style="font-size: 25px;margin-right: 38%">V2.0</span>

        <form id="loginForm" action="${ctx}/logon" method="post">
            <div class="log-table form-inline">
                <div class="form-group">
                    <input type="text" class="form-control" name="username" id="username" placeholder="手机号/邮箱">
                    <i class="fa fa-user"></i>
                </div>
                <br>

                <div class="form-group">
                    <input type="password" class="form-control" name="password" id="password" placeholder="密码">
                    <i class="fa fa-shield"></i>
                </div>
                <div class="remember">
                    <div class="form-group pull-left text-left">
                        <input type="checkbox" name="rememberMe" value="true" class="form-control" id="choose" placeholder="">
                        <label for="choose">记住我</label>
                    </div>
                    <div class="pull-right">
                        <a href="${ctx}/anon/retrieve">忘记密码?</a>
                        <span>|</span>
                        <a href="${ctx}/anon/register" class="immediately">立即注册</a>
                    </div>
                </div>
            </div>
            <button type="submit" class="btn-login" style="background-color: #5caa4f">登录/OPEN</button>
        </form>
        <c:if test="${error != null}">
            <div style="color:red;margin-top:30px">${error}</div>
        </c:if>
    </div>
</div>
</div>
<%@ include file="/WEB-INF/layouts/footer.jsp" %>
</div>
<script src="${ctx}/assets/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/assets/layer/layer.js"></script>
<script type="text/javascript">
    if(${msg != null}){
        layer.msg("${msg}");
    }
</script>
</body>
</html>
