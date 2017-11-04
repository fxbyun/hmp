<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: teemoer@cntv.cn
  Date: 2016/7/1 0001
  Time: 17:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table width="100%">
    <colgroup width="15%"></colgroup>
    <colgroup width="25%"></colgroup>
    <colgroup width="25%"></colgroup>
    <colgroup width="35%"></colgroup>
    <tr>
        <th>序号</th>
        <th>充值金额</th>
        <th>充值后余额</th>
        <th>消费时间</th>
    </tr>
    <c:forEach varStatus="status" items="${msgRechargeDetailPage.content}" var="oneRe">
        <tr>
            <td>${status.count + (msgRechargeDetailPage.number * msgRechargeDetailPage.getSize())}</td>
            <td>${oneRe.addMoney}元</td>
            <td>${oneRe.afterMoney}元</td>
            <td><fmt:formatDate value="${oneRe.createDate}" type="date" pattern="yyyy/MM/dd hh:mm" /></td>
        </tr>
    </c:forEach>
</table>
<div class="fenye" style="font-size:14px;">
    <div class="fenye-buttom text-center">
        <c:if test="${msgRechargeDetailPage.number > 0}">
            <button type="button" onclick="fn_LoadPageRecHis(${msgRechargeDetailPage.number - 1})"
                    class="btn btn-success" style="width: 80px; margin-right: 10px;">上一页
            </button>
        </c:if>
        <c:if test="${msgRechargeDetailPage.number + 1 < msgRechargeDetailPage.totalPages}">
            <button type="button" onclick="fn_LoadPageRecHis(${msgRechargeDetailPage.number + 1})"
                    class="btn btn-success" style="width: 80px;" s> 下一页
            </button>
        </c:if>
        <div class="form-group" style="margin-left: 20px;">
            第 <span>${msgRechargeDetailPage.number + 1}</span>页/共<span>${msgRechargeDetailPage.totalPages}</span>页
        </div>
    </div>

</div>
</body>
</html>
