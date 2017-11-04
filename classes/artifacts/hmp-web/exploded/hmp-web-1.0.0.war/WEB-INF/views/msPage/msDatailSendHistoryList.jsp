<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: teemoer@cntv.cn
  Date: 2016/7/1 0001
  Time: 17:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="${ctx}/assets/jquery/jquery-1.11.2.min.js"></script>
    <link href="${ctx}/assets/bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
    <link href="${ctx}/assets/datetimepicker/jquery.datetimepicker.css" type="text/css" rel="stylesheet">
    <link href="${ctx}/assets/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" type="text/css" rel="stylesheet" />
    <link href="${ctx}/assets/styles/default.min.css" type="text/css" rel="stylesheet" />
    <link href="${ctx}/assets/styles/style.css" type="text/css" rel="stylesheet">
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/styles/green.css" type="css" needChengStyle="true"/>
    <script type="text/javascript" src="${ctx}/assets/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${ctx}/assets/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript" src="${ctx}/assets/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
</head>
<body>
<script type="application/javascript">
    function changeDate() {
        startDate = $("#txtStartDate").val();
        endDate = $("#txtEndDate").val();
        $("#a2").load("/msPage/msDatailSendHistoryList?page=${msgSendHistoryPage.number}&startDate=" + startDate + "&endDate=" + endDate)

    }

    $(function () {
        $('#txtStartDate,#txtEndDate').datetimepicker({
            language: 'zh-CN',
            format: 'yyyy/mm/dd',
            weekStart: 1,
            todayBtn: 1,
            autoclose: 1,
            todayHighlight: 1,
            startView: 2,
            minView: 2,
            forceParse: 0
        });

    })

</script>
<style>
    .ms-time{
        padding: 5px;
    }
    .ms-time label, .ms-time input{
        float: left;
    }

    .ms-time label{
        margin: 8px 10px;
    }
    th{
        text-align: center;
    }

</style>
<%--<select style="width: 100%;" onchange="changeDate(this)" id="chooseMoth">--%>
<%--<option value="1">一月份</option>--%>
<%--<option value="2">二月份</option>--%>
<%--<option value="3">三月份</option>--%>
<%--<option value="4">四月份</option>--%>
<%--<option value="5">五月份</option>--%>
<%--<option value="6">六月份</option>--%>
<%--<option value="7">七月份</option>--%>
<%--<option value="8">八月份</option>--%>
<%--<option value="9">九月份</option>--%>
<%--<option value="10">十月份</option>--%>
<%--<option value="11">十一月份</option>--%>
<%--<option value="12">十二月份</option>--%>
<%--</select>--%>
<div class="ms-time">
    <input type="text" name="startDate" value="<fmt:formatDate value='${startDate}' pattern='yyyy/MM/dd'/>"
           class="form-control form_date"
           id="txtStartDate" style="width:30%;" readonly>
    <label>至</label>
    <input type="text" value="<fmt:formatDate value='${endDate}' pattern='yyyy/MM/dd'/>" name="endDate" style="width:30%;"
           class="form-control form_date"
           id="txtEndDate" readonly>
    <button id="btnSubmit" onclick="changeDate()" style="width: 65px;" type="button" class="btn btn-default pull-right active">搜索</button>
</div>
<table width="100%">
    <colgroup width="15%"></colgroup>
    <colgroup width="25%"></colgroup>
    <colgroup width="25%"></colgroup>
    <colgroup width="35%"></colgroup>
    <tr>
        <th>序号</th>
        <th>消费项目</th>
        <th>消费金额</th>
        <th>消费时间</th>
    </tr>
    <c:forEach varStatus="status" var="oneSend" items="${msgSendHistoryPage.content}">
        <tr>
            <td>${status.count + (msgSendHistoryPage.number * msgSendHistoryPage.getSize())}</td>
            <td>
                <c:if test="${oneSend.msgType.toString().equals('SELF')}">自主发送</c:if>
                <c:if test="${!oneSend.msgType.toString().equals('SELF')}">短信回访</c:if>
            </td>
            <td>消费${oneSend.useMoney}元</td>
            <td><fmt:formatDate value="${oneSend.createDate}" type="date" pattern="yyyy/MM/dd hh:mm" /></td>
        </tr>
    </c:forEach>
</table>
<div class="fenye" style="font-size: 14px;">
    <div class="fenye-buttom text-center">
        <c:if test="${msgSendHistoryPage.number > 0}">
            <button type="button"
                    onclick="fn_LoadPageSendHis(${msgSendHistoryPage.number - 1},'${startDate.toLocaleString()}','${endDate.toLocaleString()}')"
                    class="btn btn-success" style="width: 80px; margin-right: 10px;">上一页
            </button>
        </c:if>
        <c:if test="${msgSendHistoryPage.number + 1 < msgSendHistoryPage.totalPages}">
            <button type="button"
                    onclick="fn_LoadPageSendHis(${msgSendHistoryPage.number + 1},'${startDate.toLocaleString()}','${endDate.toLocaleString()}')"
                    class="btn btn-success" style="width: 80px;" s> 下一页
            </button>
        </c:if>
        <div class="form-group" style="margin-left: 20px;">
            第 <span>${msgSendHistoryPage.number + 1}</span>页/共<span>${msgSendHistoryPage.totalPages}</span>页
        </div>
    </div>

</div>
</body>
</html>
