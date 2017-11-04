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
    <script src="${ctx}/assets/jquery/jquery-1.11.2.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        var useragent = navigator.userAgent;
        if (useragent.match(/MicroMessenger/i) != 'MicroMessenger') {
            // 这里警告框会阻塞当前页面继续加载
            alert('已禁止本次访问：您必须使用微信内置浏览器访问本页面！');
            // 以下代码是用javascript强行关闭当前页面
            var opened = window.open('about:blank', '_self');
            opened.opener = null;
            opened.close();
        }
    </script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <div class="text-left tcb"><h4>修改密码</h4></div>
            <div class="well well-sm emr">
                <form id="pwdForm" class="form-inline" method="post" action="${ctx}/pwd/save">
                    <input type="hidden" name="opId" value="${opId}"/>
                    <table>
                        <tr style="height:40px">
                            <td align="right"><b>旧密码：</b></td>
                            <td><input type="password" class="form-control w160" placeholder="请输入旧密码" name="oldPwd" id="oldPwd"/></td>
                        </tr>
                        <tr style="height:40px">
                            <td align="right"><b>新密码：</b></td>
                            <td><input type="password" class="form-control w160" placeholder="请输入新密码" name="newPwd" id="newPwd"/></td>
                        </tr>
                        <tr style="height:40px">
                            <td align="right"><b>确认密码：</b></td>
                            <td><input type="password" class="form-control w160" placeholder="请输入确认密码" name="confirmPwd" id="confirmPwd"/></td>
                        </tr>
                        <tr style="height:45px">
                            <td colspan="2"><div class="text-center"><button type="submit" class="btn btn-primary btn-sm">修改</button></div></td>
                        </tr>
                    </table>
                </form>
                <div style="margin-top: 10px">
                    <c:if test="${error != null}">
                        <div class="alert alert-danger text-left">${error}</div>
                    </c:if>
                    <c:if test="${msg != null}">
                        <div class="alert alert-success text-left">${msg}</div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        $("#pwdForm").validate({
            rules: {
                "oldPwd": {
                    minlength: 6,
                    required: true
                },
                "newPwd": {
                    minlength: 6,
                    required: true
                },
                "confirmPwd": {
                    required: true,
                    minlength: 6,
                    equalTo: "#newPwd"
                }
            },
            messages: {
                "oldPwd": {
                    required: "请输入旧密码",
                    minlength: $.validator.format("密码不能小于{0}个字 符")
                },
                "newPwd": {
                    required: "请输入新密码",
                    minlength: $.validator.format("密码不能小于{0}个字 符")
                },
                "confirmPwd": {
                    required: "请输入确认密码",
                    minlength: $.validator.format("密码不能小于{0}个字 符"),
                    equalTo: "新密码和确认密码不一致"
                }
            },
        });
    });
</script>
<script src="${ctx}/assets/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx}/assets/jquery-validation/1.11.1/jquery.validate.min.js" type="text/javascript"></script>
</body>
</html>