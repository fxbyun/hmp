<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/10/26
  Time: 16:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>员工管理</title>
    <script>
        $(function () {
            $("#nav-system").addClass("active");
        });

        function initPassword(id, type) {
            layer.confirm('是否恢复初始密码（默认为当前账号后六位），恢复密码后请及时修改密码，以免忘记。', function (index) {
                $.postJSON("${ctx}/initPassword", {
                    id: id,
                    type: type
                }, function (re) {
                    if (re.success) {
                        layer.msg('密码已初始化！', {time: 2000});
                    } else {
                        layer.msg('初始化失败!', {time: 2000});
                    }
                })

            });


        }

        function editNickname(id, type, name) {
            layer.prompt({title: '修改昵称', value: name}, function (ret) {
                var url = "${ctx}/updateNameByTypeAndId";
                if (ret.length > 4 || ret.length < 2) {
                    alert("请输入2-4个字符");
                    return false
                }
                updateName(url, id, type, ret);
            });

        }

        function updateName(url, id, type, name) {
            $.postJSON(url,
                {
                    id: id,
                    name: name,
                    type: type
                },
                function (re) {
                    if (re.success) {
                        layer.msg("修改成功!")
                        setTimeout(function () {
                            window.location.reload();
                        }, 1000);
                    } else {
                        layer.msg("修改失败!")
                    }
                }
            )
        }

        function selectChange(obj) {
            window.location.href = "${ctx}/employee?personType=" + $(obj).val();

        }

        function setThisAsChiefNurse(id, isChiefNurse) {
            if (isChiefNurse == "YES") {
                isChiefNurse = "NO";
            } else {
                isChiefNurse = "YES";
            }
            $.postJSON(
                "${ctx}/setThisIdAsChiefNurse",
                {
                    id: id,
                    status: isChiefNurse
                }, function (ret) {
                    if (ret.success) {
                        Alert.success("操作成功!");
                        setTimeout(function () {
                            window.location.reload();
                        }, 2000);
                    }
                }
            )
        }


        function addSubDoctor(url) {

            layer.confirm("您确定要增加吗?", {btn: ["确定", "取消"]}, function () {
                window.location.href = url;
            });

        }

    </script>
</head>
<body>
<div class="manage employee">
    <input type="hidden" value="${one.id}" name="id2"/>
    <div class="container">


        <ul class="navigation">
            <li><a href="${ctx}/infro" class="btn btn-default">信息设置</a></li>
            <li><a href="${ctx}/advertSet" class="btn btn-default">终端机设置</a></li>
            <li class="active"><a href="${ctx}/employee" class="btn btn-default">员工管理</a></li>
        </ul>
        <div class="intro-sign form-inline" style="padding-bottom: 0;">
            <div class="row">
                <div class="col-md-4 col-sm-4">
                    <div class="form-group">
                        <label>选择类型:</label>
                        <select class="form-control" onchange="selectChange(this)">
                            <option value="Wecath_dispenser" <c:if
                                    test="${personType=='Wecath_dispenser'}"> selected </c:if>>我的药剂师
                            </option>
                            <option value="Nurse" <c:if test="${personType=='Nurse'}"> selected </c:if> >我的护士</option>
                            <%--<option value="Cashier" <c:if test="${personType=='Cashier'}"> selected </c:if> >我的收银员
                            </option>--%>
                            <c:if test="${doctor.doctorType=='Clinic_Boss'}">
                                <option value="Sub_Doctor" <c:if test="${personType=='Sub_Doctor'}"> selected </c:if>>
                                    我的子帐号
                                </option>
                            </c:if>
                        </select>
                    </div>
                </div>
                <div style="float: right; margin-right: 20px;">
                    <c:choose>
                        <c:when test="${personType=='Wecath_dispenser'}">
                            <a href="#"
                               onclick="addSubDoctor('${ctx}/manage/new?type=Wecath_dispenser')"
                               style="width: 120px;"
                               class="btn btn-success">新增药剂师</a>
                        </c:when>

                        <c:when test="${personType=='Nurse'}">
                            <a href="#"
                               onclick="addSubDoctor('${ctx}/manage/new?type=Nurse')"
                               style="width: 120px;"
                               class="btn btn-success">新增药护士</a>
                        </c:when>

                        <c:when test="${doctor.doctorType=='Clinic_Boss'}">
                            <a href="#"
                               onclick="addSubDoctor('${ctx}/manage/new?type=Sub_Doctor')"
                               style="width: 120px;"
                               class="btn btn-success">新增子帐号</a>
                        </c:when>


                    </c:choose>

                    <%--<button class="btn btn-success" style="width: 120px;">新增药剂师</button>--%>
                </div>
            </div>
            <div class="row" style="margin-right: -20px; margin-left: -20px;">
                <table width="100%" class="table-hover">
                    <colgroup width="25%"></colgroup>
                    <colgroup width="25%"></colgroup>
                    <colgroup width="25%"></colgroup>
                    <colgroup width="25%"></colgroup>
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>账号</th>
                        <th>昵称</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:forEach items="${list}" var="one" varStatus="status">
                        <tr>
                            <td>
                                    ${status.count}
                            </td>
                            <td>
                                <c:if test="${personType!='Sub_Doctor'}">
                                    ${one.account}
                                </c:if>
                                <c:if test="${personType=='Sub_Doctor'}">
                                    ${one.mobile}
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${empty one.name}">
                                    未填写
                                </c:if>
                                    ${one.name}
                                <i class="fa fa-edit"
                                   onclick="editNickname(${one.id},'${personType}','${one.name}')"></i></td>
                            <td>
                                <a href="${ctx}/manage/unbind/${one.id}?personType=${personType}"

                                   class="btn btn-default">

                                    <c:if test="${personType!='Sub_Doctor'}">
                                        解除绑定
                                    </c:if>
                                    <c:if test="${personType=='Sub_Doctor'}">
                                        <c:if test="${one.status=='Normal'}">
                                            禁止登录
                                        </c:if>
                                        <c:if test="${one.status!='Normal'}">
                                            允许登录
                                        </c:if>

                                    </c:if>
                                </a>
                                <c:if test="${personType=='Sub_Doctor'}">
                                    <button id="password" onclick="initPassword(${one.id},'${personType}')"
                                            class="btn btn-default rest-password">恢复初始密码
                                    </button>
                                </c:if>
                                <c:if test="${personType=='Nurse'}">
                                    <button id="password"
                                            <c:if test="${one.isChiefNurse=='YES'}"> disabled </c:if>
                                            onclick="setThisAsChiefNurse(${one.id},'${one.isChiefNurse}')"
                                            class="btn btn-default rest-password">设为护士长
                                    </button>
                                    <button id="password"
                                            <c:if test="${one.isChiefNurse=='NO'}"> disabled </c:if>
                                            onclick="setThisAsChiefNurse(${one.id},'${one.isChiefNurse}')"
                                            class="btn btn-default rest-password">解除护士长
                                    </button>
                                </c:if>

                            </td>
                        </tr>
                    </c:forEach>

                    </tbody>
                </table>
            </div>


        </div>
    </div>
</div>
</body>
</html>
