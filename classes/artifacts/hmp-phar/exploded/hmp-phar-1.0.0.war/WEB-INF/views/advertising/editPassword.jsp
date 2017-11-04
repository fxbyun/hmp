
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/advInclude.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>修改密码</title>
    <script type="text/javascript">
        function chek() {
            var oldPwd = $("#oldPwd");
            var newPwd = $("#newPwd");
            var confirmPwd = $("#confirmPwd");

            if (oldPwd.val() == "") {
                layer.msg("旧密码不为空！");
                return false;
            }

            if (newPwd.val() == "") {
                layer.msg("新密码不为空！");
                return false;
            }
            if (confirmPwd.val() == "") {
                layer.msg("重复密码不为空！");
                return false;
            }

            if (newPwd.val().length < 6) {
                layer.msg("新密码必须大于6位！");
                return false;
            }
            if (confirmPwd.val().length < 6) {
                layer.msg("重复密码必须大于6位！");
                return false;
            }

            if (newPwd.val() != confirmPwd.val()) {
                layer.msg("两次输入的密码不相同！");
                return false;
            }


            $("#pwdForm").submit();
        }

        function cencel() {
            $("input").val("");
        }
    </script>

</head>
<body>
<%@ include file="/WEB-INF/layouts/advheader.jsp" %>
<div class="container inspectManage">
    <ul class="navigation">
        <li><a href="${ctx}/adv/personalInfo" class="btn btn-default">个人资料</a></li>
        <li class="active"><a href="${ctx}/adv/editPassword" class="btn btn-default">密码修改</a></li>
    </ul>
    <div class="adv-container" style="padding: 10px 20px;">
        <div class="row">
            <div class="col-xs-12">
                <div class="box-solid">
                    <h4>修改密码</h4>
                </div>
                <div style="font-size: 16px; padding-left: 10px;">
                    <p>默认密码为账号后六位，设置登录密码后，再次登录客户端时输入正确的登录密码。</p>
                    <p style="color: red;">注意:密码必须大于6位数，由字母、数字组成</p>
                    <div class="row" style="margin-top: 3%;">
                        <div class="col-xs-12 col-md-4 col-sm-12"></div>
                        <div class="col-xs-12 col-md-4 col-sm-12">
                            <form id="pwdForm" method="post" action="${ctx}/adv/editPassword">
                                <div class="form-group">
                                    <span>账户名称：</span><span>${loginUser.name}</span>
                                </div>

                                <div class="form-group">
                                    <span>当前密码：</span><input type="password" class="form-control"
                                                             style="display: inline-block; width: 70%;" name="oldPwd"
                                                             id="oldPwd">
                                </div>
                                <div class="form-group">
                                    <span>新的密码：</span><input type="password" class="form-control"
                                                             style="display: inline-block; width: 70%;" name="newPwd"
                                                             id="newPwd">
                                </div>
                                <div class="form-group">
                                    <span>确认密码：</span><input type="password" class="form-control"
                                                             style="display: inline-block; width: 70%;"
                                                             name="confirmPwd"
                                                             id="confirmPwd">
                                </div>
                                <div class="form-group text-center" style="margin-top: 40px;">
                                    <button type="button" onclick="chek()" class="btn btn-success"
                                            style="width: 100px; margin-right: 10px;">确认修改
                                    </button>
                                    <button type="button" onclick="cencel()" class="btn btn-default"
                                            style="width: 100px;">
                                        取消修改
                                    </button>
                                </div>
                            </form>

                        </div>
                        <div class="col-xs-12 col-md-4 col-sm-12"></div>
                    </div>

                </div>
            </div>
        </div>

    </div>
    <div style="margin-top:6px">
        <c:if test="${error != null}">
            <div class="alert alert-danger text-left">${error}</div>
        </c:if>
        <c:if test="${msg != null}">
            <div class="alert alert-success text-left">${msg}</div>
        </c:if>
    </div>
</div>


<%@ include file="/WEB-INF/layouts/advfooter.jsp" %>
</body>
</html>
