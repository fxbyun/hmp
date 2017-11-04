<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: teemoer@cntv.cn
  Date: 2016/7/1 0001
  Time: 11:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <script type="application/javascript">
        function fn_LoadPage(num) {
            $("#msgListDiv").load("${ctx}/msPage/getMsgSendList?page=" + num);
        }
    </script>
</head>
<body>
<h3 style="margin-bottom: 30px; font-size: 18px;">发送记录</h3>
<table id="msfInfo" class="mes-table text-center" style="border: 1px solid #ccc;">
    <colgroup style="width: 8%;"></colgroup>
    <colgroup style="width: 22%;"></colgroup>
    <colgroup style="width: 20%;"></colgroup>
    <colgroup style="width: 15%;"></colgroup>
    <colgroup style="width: 15%;"></colgroup>
    <colgroup style="width: 10%;"></colgroup>
    <colgroup style="width: 10%;"></colgroup>
    <thead>
    <tr>
        <th>序号</th>
        <th>日期</th>
        <th>标题</th>
        <th>应发人数</th>
        <th>实收人数</th>
        <th>金额</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach varStatus="status" var="one" items="${msgSendHistoryListPage.content}">
        <tr>
            <td> ${status.count +(msgSendHistoryListPage.number * msgSendHistoryListPage.getSize())}
            </td>
            <td><fmt:formatDate value="${one.createDate}" pattern="yyyy/MM/dd HH:mm" /></td>
            <td>${one.title}</td>
            <td>${one.msgSendHistoryDetail.size()}</td>
            <td>${one.successSize}</td>
            <td>${one.useMoney}</td>
            <td><a href="#a${status.count}" thinkDivId="${one.id}" data-toggle="collapse" onclick="closeTherTd(this)"
                   aria-controls="a${status.count}">详情
                <i class="fa fa-caret-down" style="padding-left: 5px;"></i></a>
            </td>
        </tr>
        <tr id="a${status.count}" class="collapse" aria-labelledby="headingone">
            <td colspan="7" style="text-align: left;">
                <div style="padding: 5px;">
                    <div id="divTable${one.id}" style=" margin-top: 10px; overflow-y: auto; height: 240px;">

                    </div>
                        <%--<div class="text-right" style="margin-top: 10px;">--%>
                        <%--<input type="text" style="padding: 0 5px;" value="" placeholder="患者姓名">--%>
                        <%--<button>搜索</button>--%>
                        <%--</div>--%>
                </div>
            </td>
        </tr>
    </c:forEach>

    </tbody>
</table>
<div class="fenye">
    <div class="fenye-buttom text-center">
        <c:if test="${msgSendHistoryListPage.number > 0}">
            <button type="button" onclick="fn_LoadPage(${msgSendHistoryListPage.number - 1})"
                    class="btn btn-success" style="width: 80px;">上一页
            </button>
        </c:if>
        <c:if test="${msgSendHistoryListPage.number + 1 < msgSendHistoryListPage.totalPages}">
            <button type="button" onclick="fn_LoadPage(${msgSendHistoryListPage.number + 1})"
                    class="btn btn-success" style="width: 80px;" s> 下一页
            </button>
        </c:if>
        <div class="form-group">
            第 <span>${msgSendHistoryListPage.number + 1}</span>页/共<span>${msgSendHistoryListPage.totalPages}</span>页
        </div>
    </div>
</div>
</body>
</html>
