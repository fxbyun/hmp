<%--@elvariable id="ctx" type="java.lang.String"--%>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/9/7
  Time: 9:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>终端机设置</title>
    <script>
        $(function () {
            $("#nav-system").addClass("active");
            $(".parentImg").mousemove(function () {
                var url = $(this).find("img").attr("src");
                $(this).append("<img class='bigImg' src='" + url + "'>");
            });
            $(".parentImg").mouseout(function () {
                $(".bigImg").remove();
            });


        })

        function updateAdvingSetting() {
            $.postJSON("${ctx}/updateAdvingSetting", {}, function () {
                Alert.success("更新成功,终端机即将更新广告位!");
            })
        }

        function goToThisPage() {
            window.location.href = "${ctx}/advertSet?pageNo=" + $("#currentPage").val();
        }
        function fn_LoadRpList(pageNo) {
            window.location.href = "${ctx}/advertSet?pageNo=" + pageNo;
        }
    </script>
</head>
<body>
<div class="manage">
    <form action="${ctx}/advertSet" method="post" class="form-inline">
        <input type="hidden" name="pageNo" value="${page.number+1}"/>
        <div class="container">
            <ul class="navigation">
                <li><a href="${ctx}/infro" class="btn btn-default">信息设置</a></li>
                <li class="active"><a href="${ctx}/advertSet" class="btn btn-default">终端机设置</a></li>
                <li><a href="${ctx}/employee" class="btn btn-default">员工管理</a></li>
            </ul>
            <div class="advSet">
                <table class="rp-table" width="100%">
                    <colgroup width="10%"></colgroup>
                    <colgroup width="7%"></colgroup>
                    <colgroup width="10%"></colgroup>
                    <colgroup width="20%"></colgroup>
                    <colgroup width="15%"></colgroup>
                    <colgroup width="5%"></colgroup>
                    <colgroup width="15%"></colgroup>
                    <colgroup width="8%"></colgroup>
                    <colgroup width="10%"></colgroup>
                    <thead>
                    <tr style="height: 75px;">
                        <th>名称</th>
                        <th>类型</th>
                        <th>缩略图</th>
                        <th>内容</th>
                        <th>链接</th>
                        <th>序号</th>
                        <th>创建时间</th>
                        <th>剩余天数</th>
                        <th>
                            <a href="${ctx}/advertNewSet" class="btn btn-success"><i class="fa fa-plus"></i>新建</a>

                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${page.content}" var="n">
                        <tr>
                            <td>
                                <div>${n.name}</div>
                            </td>
                            <td>${typeMap[n.type]}</td>
                            <td>
                                <c:if test="${n.type =='Pic'}">
                                    <a href="#" class="parentImg">
                                        <img src="${ctx}/fileDir/${doctor.id}/${n.file.fileName}">
                                    </a>

                                </c:if>

                            </td>
                            <td>
                                <div>
                                        ${n.content}
                                </div>
                            </td>
                            <td>
                                <div>
                                        ${n.url}
                                </div>
                            </td>
                            <td>
                                    ${n.orderNo}
                            </td>
                            <td>
                                <fmt:formatDate value="${n.createOn }" pattern="yyyy/MM/dd hh:mm:ss"/>

                            </td>
                            <td>  ${n.getValidityDay()}
                                天
                            </td>
                            <td><a href="${ctx}/advertNewSet?id=${n.id}">查看/修改</a></td>
                        </tr>
                    </c:forEach>

                    </tbody>
                </table>
                <div class="text-center">
                    <c:if test="${page.number > 0}">
                        <button type="button" onclick="fn_LoadRpList(${page.number - 1})" class="btn btn-default"
                                style="width: 100px; height: 50px; margin-right: 15px;">上一页
                        </button>
                    </c:if>
                    <c:if test="${page.number + 1 < page.totalPages}">
                        <button type="button" onclick="fn_LoadRpList(${page.number + 1})" class="btn btn-default"
                                style="width: 100px; height: 50px;">下一页
                        </button>
                    </c:if>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    第 <input type="text" class="form-control" style="width:50px; text-align: center;" id="currentPage"
                             value="${page.number + 1}">页/共<span>${page.totalPages}</span>页
                    <a href="#" onclick="javascript:goToThisPage();" class="btn btn-success">跳转 </a>
                    <a href="#" onclick="updateAdvingSetting()" class="btn btn-success" style="margin-left: 10px;"><i
                            class="fa fa-plus"></i>更新服务终端机</a>
                </div>
            </div>
        </div>
    </form>
</div>
</body>
</html>
