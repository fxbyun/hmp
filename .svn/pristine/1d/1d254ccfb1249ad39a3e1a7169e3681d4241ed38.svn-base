<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <link href="${ctx}/assets/bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
    <link rel="stylesheet" href="${ctx}/assets/styles/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/assets/styles/style.css">
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/styles/green.css" type="css" needChengStyle="true"/>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/assets/html5shiv/html5shiv.js"></script>
    <script type="text/javascript" src="${ctx}/assets/respond/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript" src="${ctx}/assets/jquery/jquery-1.11.2.min.js"></script>
    <script type="text/javascript" src="${ctx}/assets/layer/layer.js"></script>
    <script type="text/javascript">
        $(function () {
            var index = parent.layer.getFrameIndex(window.name);
            $('#btnClose').click(function () {
                parent.layer.close(index);
            });
        });
    </script>
    <style>
        .col-sm-6 {
            display: inline-block;
        }

        .form-group:nth-of-type(1) label, .form-group:nth-of-type(2) label {
            padding-left: 12px;
        }

        .btn {
            width: 80px;
        }

        button.btn:first-child {
            margin-right: 5px;
        }
    </style>
</head>
<body style="background-color: #fff;">
<div style="margin: 30px 20px; line-height: 40px;">
    <form id="pwdForm" class="form-inline" method="post" action="/fragment/doctor/updatePwd">
        <div class="form-group">
            <label for="oldPwd"><b>旧密码：</b></label>
            <div class="col-sm-6">
                <input type="password" class="form-control" placeholder="请输入旧密码" name="oldPwd" id="oldPwd">
            </div>
        </div>
        <div class="form-group">
            <label for="newPwd"><b>新密码：</b></label>
            <div class="col-sm-6">
                <input type="password" class="form-control" placeholder="请输入新密码" name="newPwd" id="newPwd">
            </div>
        </div>
        <div class="form-group">
            <label for="confirmPwd"><b>确认密码：</b></label>
            <div class="col-sm-6">
                <input type="password" class="form-control" placeholder="请输入确认密码" name="confirmPwd" id="confirmPwd">
            </div>
        </div>
        <div style="text-align: center;">
            <button type="submit" class="btn btn-success btn-sm">修改</button>
            <button type="button" id="btnClose" class="btn btn-default btn-sm">关闭</button>
        </div>
    </form>
    <div style="margin-top:6px">
        <c:if test="${error != null}">
            <div class="alert alert-danger text-left">${error}</div>
        </c:if>
        <c:if test="${msg != null}">
            <div class="alert alert-success text-left">${msg}</div>
        </c:if>
    </div>
</div>
<script type="text/javascript" src="${ctx}/assets/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/jquery-validation/1.11.1/jquery.validate.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/jquery-validation/1.11.1/messages_bs_zh.js"></script>
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
            }
        });
    });
</script>
</body>
</html>