<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/2
  Time: 11:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/advInclude.jsp" %>
<%@ taglib prefix="hmp" uri="/WEB-INF/TLD/HmpLoadStaticFile.tld" %>
<!DOCTYPE html>
<html>
<head>
    <title>个人资料</title>


    <script type="text/javascript">
        function subOk() {
            var username = $("#Phr_username").val();
            var Phr_username = $("#Phr_username").val();
            if (Phr_username == "") {
                layer.msg("昵称不能为空！");
                return false;
            }
            $.postJSON("${ctx}/adv/editUsername", {
                "username": username
            }, function (ret) {

                if (ret.success) {
                    layer.msg("修改成功！");
                } else {
                    layer.msg("修改失败！");
                }
                location.reload();
            });


        }


    </script>
</head>
<body>
<%@ include file="/WEB-INF/layouts/advheader.jsp" %>
<div class="container inspectManage">
    <ul class="navigation">
        <li class="active"><a href="${ctx}/adv/personalInfo" class="btn btn-default">个人资料</a></li>
        <li><a href="${ctx}/adv/editPassword" class="btn btn-default">密码修改</a></li>
        <%--<li><a href="${ctx}/adv/pub/printSet" class="btn btn-default">打印模板设置</a></li>--%>
    </ul>
    <div class="adv-container" style="padding: 10px 20px;">
        <div class="row">
            <div class="col-xs-12">
                <div class="box-solid">
                    <h4>个人信息</h4>
                </div>
                <div style="font-size: 16px; padding-left: 10px;">
                    <p>默认昵称可在主账号修改也可在个人资料修改，设置昵称后，再次登录客户端时显示新的昵称。</p>
                    <div class="row" style="margin-top: 3%; text-align: center;">
                        <div class="col-xs-12 col-md-4 col-sm-12"></div>
                        <div class="col-xs-12 col-md-4 col-sm-12">
                            <div class="form-group text-left">
                                <span>账户名称：&nbsp;</span><span id="span">${loginUser.name}</span>
                            </div>
                            <div class="form-group  text-left">
                                <span style="letter-spacing: 4.5px;">新昵称：</span>
                                <input type="text" class="form-control" id="Phr_username"
                                       style="display: inline-block; width: 65%;">
                            </div>
                            <div class="form-group text-center" style="margin-top: 40px;">
                                <button type="button" class="btn btn-success" onclick="subOk()"
                                        style="width: 100px; margin-right: 10px;">确认修改
                                </button>
                                <button type="button" class="btn btn-default" style="width: 100px;">取消修改</button>
                            </div>
                        </div>
                        <div class="col-xs-12 col-md-4 col-sm-12"></div>
                    </div>
                </div>
            </div>
        </div>


    </div>
</div>
<%@ include file="/WEB-INF/layouts/advfooter.jsp" %>
</body>
</html>
