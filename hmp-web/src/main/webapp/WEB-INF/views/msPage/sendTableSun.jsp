<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: teemoer@cntv.cn
  Date: 2016/7/1 0001
  Time: 14:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <script>
        $(function () {
            //设置偶数行和奇数行
            $(".mes-table-inline tbody>tr:odd").addClass("backg");   //为奇数行设置样式(添加样式类)
            $(".mes-table-inline tbody>tr:even").addClass("backg2");  // 为偶数行设置样式类
        });
    </script>
</head>
<body>
<table width="100%" class="mes-table-inline">
    <thead>
    <tr>
        <th>序号</th>
        <th>患者姓名</th>
        <th>手机号码</th>
        <th>接收情况</th>
    </tr>
    </thead>
    <c:forEach varStatus="status" var="oenItem" items="${msgSendHistoryTableListPage.content}">
        <tr>
            <td>${status.count}</td>
            <td>${oenItem.patient.name}</td>
            <td>${oenItem.patient.mobile}</td>
            <td>
                <c:if test="${oenItem.sendStatus.toString().equals('Success')}">成功</c:if>
                <c:if test="${!oenItem.sendStatus.toString().equals('Success')}"><p style="color: red">失败</p></c:if>
            </td>
        </tr>
    </c:forEach>

</table>
</body>
</html>
