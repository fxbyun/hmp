<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/7/13
  Time: 10:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <title>修改密码</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <meta http-equiv="Cache-Control" content="no-store" />
    <meta http-equiv="Pragma" content="no-cache" />
    <meta http-equiv="Expires" content="0" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link href="${ctx}/assets/bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="${ctx}/assets/styles/style.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/assets/styles/public.css">
    <script src="${ctx}/assets/jquery/jquery-1.11.2.min.js" type="text/javascript"></script>
</head>
<body>
<div class="container">
    <div class="register new-pass">
        <p class="text-center" style="margin-top: 3rem;">
            <img src="${ctx}/assets/images/logo2.png">

        <h3 class="text-center">易佳诊诊所运营管理平台</h3>
        </p>
        <p class="text-right" style="padding-right: 3rem;">v2.0</p>

        <div class="register-table">
            <table border="0">
                <tr style="height: 45px">
                    <td align="right"><b>手机号码 :&nbsp;&nbsp;</b></td>
                    <td align="left"><input type="text" class="form-control" id="phoneNo" name="phoneNo" /></td>
                </tr>
                <tr style="height: 45px">
                    <td align="right"><b>验证码 :&nbsp;&nbsp;</b></td>
                    <td align="left">
                        <input type="text" class="form-control pull-left" id="authCode" name="authCode"
                               style="width: 45%; display: inline-block;" />
                        <button class="btn btn-success pull-right" id="sendCode">获取验证码</button>
                    </td>
                </tr>
                <tr style="height: 45px">
                    <td align="right"><b>新密码 :&nbsp;&nbsp;</b></td>
                    <td align="left" style="width: 190px"><input type="password" class="form-control" id="newPwd"
                                                                 name="newPwd" /></td>
                </tr>
                <tr style="height: 45px">
                    <td align="right"><b>确认新密码 :&nbsp;&nbsp;</b></td>
                    <td align="left"><input type="password" class="form-control" id="confirmPwd"
                                            name="confirmPwd" /></td>
                </tr>

            </table>
            <p class="text-center" style="margin-top: 3rem;">
                <button class="btn btn-success" type="submit">确认修改</button>
                <button class="btn btn-success" type="button" style="width: 30%;" onclick="javascript:history.go(-1);">
                    返回
                </button>
            </p>

        </div>
    </div>
</div>
</body>
</html>
