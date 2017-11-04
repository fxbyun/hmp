<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="hmp" uri="/WEB-INF/TLD/HmpLoadStaticFile.tld" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <title>易佳诊健康管理平台-重置密码</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <meta http-equiv="Cache-Control" content="no-store"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <meta http-equiv="Expires" content="0"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link href="${ctx}/assets/bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/assets/styles/style.css">
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/styles/green.css" type="css" needChengStyle="true"/>
    <link rel="stylesheet" href="${ctx}/assets/styles/font-awesome/css/font-awesome.min.css">
    <link href="${ctx}/assets/jquery-validation/1.11.1/validate.css" type="text/css" rel="stylesheet"/>
    <style>
        table tr td[align="right"] {
            width: 30%;
        }
    </style>
</head>
<body>
<div class="container" style="width: 100%;">
    <div class="register">
        <div class="company-name">
            <img src="${ctx}/assets/images/logo2.png" width="600px" alt="易佳诊健康管理平台">
        </div>
        <span class="pull-right release" style="font-size: 25px;margin-right: 38%">V2.0</span>

        <div class="register new-pass">
            <div class="register-table">
            <c:if test="${error != null}">
                <div class="alert alert-danger text-left" style="margin-bottom: 0px">${error}</div>
            </c:if>
            <c:if test="${msg != null}">
                <div class="alert alert-success text-left" style="margin-bottom: 0px">${msg}</div>
            </c:if>
            <form class="form-inline" action="${ctx}/anon/resetPwd" method="post" id="resetPwdForm">
                <table border="0" style="font-size: 16px">
                    <tr style="height: 45px">
                        <td align="right"><b>手机号码 :&nbsp;&nbsp;</b></td>
                        <td align="left"><input type="number" class="form-control" id="phoneNo" name="phoneNo"/></td>
                    </tr>
                    <tr style="height: 45px">
                        <td align="right"><b>验证码 :&nbsp;&nbsp;</b></td>
                        <td align="left">
                            <input type="text" class="form-control" style="width:28%;" id="authCode" name="authCode" />
                            <button class="btn btn-sm btn-success" id="sendCode">免费获取验证码</button>
                        </td>
                    </tr>
                    <%--<tr>
                        <td align="right"></td>
                        <td align="left">
                            <button class="btn btn-sm btn-info" id="sendCode">免费获取验证码</button>
                        </td>
                    </tr>--%>
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
                <div class="text-center" style="margin-top: 20px;">
                    <button class="btn btn-success" type="submit">确认修改</button>
                    <button type="button" class="btn btn-default" style="width: 25%; margin-left: 10px;"
                            onclick="javascript:history.go(-1);">返回
                    </button>
                </div>
            </form>
            </div>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/layouts/footer.jsp" %>
<script src="${ctx}/assets/jquery/jquery-1.11.2.min.js" type="text/javascript"></script>
<script src="${ctx}/assets/jquery-validation/1.11.1/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctx}/assets/jquery-validation/1.11.1/messages_bs_zh.js" type="text/javascript"></script>
<script type="text/javascript">
    $(function () {
        $("#resetPwdForm").validate({
            rules: {
                phoneNo: "required",
                authCode: "required",
                newPwd: {
                    required : true,
                    minlength : 6
                },
                confirmPwd: {
                    required : true,
                    minlength : 6,
                    equalTo: "#newPwd"
                }
            }
        });
        $("#sendCode").click(function () {
            var phone = $("#phoneNo").val();
            if(phone == ""){
                layer.alert("请输入手机号。");
                return false;
            }
            var seconds = 60;
            var setTime = function () {
                if (seconds == 0) {
                    $("#sendCode").removeAttr("disabled");
                    $("#sendCode").html("免费获取验证码");
                    seconds = 60;
                    clearInterval(timer);
                } else {
                    $("#sendCode").attr("disabled",true);
                    $("#sendCode").html("重新发送(" + seconds + "s)");
                    seconds--;
                }
            }
            var timer = setInterval(function(){setTime();},1000);
            $.getJSON("${ctx}/anon/sendAuthCode",{"phone":phone},function(result){
                if(!result.success){
                    layer.alert("验证码发送失败！请重新发送。",{icon: 0});
                }
            });
            return false;
        });
    });
</script>
</body>
</html>