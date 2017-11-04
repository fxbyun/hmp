<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <title>易佳诊健康管理平台</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <meta http-equiv="Cache-Control" content="no-store"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <meta http-equiv="Expires" content="0"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link href="${ctx}/assets/bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/assets/styles/style.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/assets/styles/public.css">
    <script src="${ctx}/assets/jquery/jquery-1.11.2.min.js" type="text/javascript"></script>
</head>
<body>
<div class="container">
    <div class="row" style="padding:10px">
        <div class="col-md-12">
            <%--<div class="text-left tcb"><h3>登陆</h3></div>--%>
            <p class="text-center" style="margin-top: 3rem;">
                <img src="${ctx}/assets/images/logo3.png">

            <h3 class="text-center">易佳诊诊所运营管理平台</h3>
            </p>
            <p class="text-right" style="padding-right: 3rem;">v2.0</p>
            <form class="form-horizontal" action="${ctx}/logon" method="post">
                <input type="hidden" value="mobile" name="loginType">
                <div class="text-center log-table form-inline">
                    <div class="form-group">
                        <input type="text" class="form-control" name="username" id="username" placeholder="账号" />
                        <i class="glyphicon glyphicon-user"></i>
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" name="password" id="password" placeholder="密码">
                        <i class="glyphicon glyphicon-lock"></i>
                    </div>
                    <div class="pull-right">
                        <a href="${ctx}/editPassword" style="color: #218a3f;">忘记密码?</a>
                    </div>

                </div>
                <div class="text-center" style="margin-top: 3rem;">
                    <button type="submit" class="btn btn-success"
                            style="width: 30%; background-color: #218a3f; padding:0.7rem 0.8rem;">登录
                    </button>
                </div>

                <%--<table>
                    <tr style="height:40px">
                        <td align="right"><b>帐号：</b></td>
                        <td><input type="text" class="form-control" name="username" id="username" /></td>
                    </tr>
                    <tr style="height:40px">
                        <td align="right"><b>密码：</b></td>
                        <td><input type="password" class="form-control" name="password" id="password" /></td>
                    </tr>
                    <tr style="height:45px">
                        <td colspan="2"><div class="text-center"><button type="submit" class="btn btn-info">提交</button></div></td>
                    </tr>
                </table>--%>
            </form>
        </div>
        <c:if test="${error != null}">
            <div class="alert alert-danger text-center">${error}</div>
        </c:if>
    </div>
</div>
<script src="${ctx}/assets/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>