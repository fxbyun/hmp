<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<div id="body" style="height: 100%">
    <section id="main">
        <div class="container">
            <div class="row-fluid">
                <div class="span12">
                    <div class="content-box" >
                        <div class="content-box-header">
                            <i class="icon-user"></i> 修改密码
                        </div>
                        <div style="padding:15px;width: 370px;margin: 0 auto;">
                            <form id="pwdForm" class="form-inline" method="post" action="${ctx}/pwd/save">
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
                                <div style="text-align: center;width: 220px;margin-top: 10px">
                                    <button type="submit" class="btn btn-success btn-xs">修改</button>
                                </div>
                            </form>
                            <div style="width: 210px;margin-top: 10px">
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
        </div>
    </section>
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