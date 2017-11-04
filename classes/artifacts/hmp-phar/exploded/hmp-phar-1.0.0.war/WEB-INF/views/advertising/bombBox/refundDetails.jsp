<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/12/29
  Time: 10:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/advInclude.jsp" %>
<%@ taglib prefix="hmp" uri="/WEB-INF/TLD/HmpLoadStaticFile.tld" %>
<html>
<head>
    <title>退费详情</title>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/advertising/scripts/advertisingIndex.js" type="js"/>
</head>
<body style="background-color: #fff;">
<div class="charge-content">
    <div style="height: 182px; overflow-y: auto;">
        <table class="bomb-table" width="100%" border="1">
            <colgroup width="20%"></colgroup>
            <colgroup width="20%"></colgroup>
            <colgroup width="20%"></colgroup>
            <colgroup width="20%"></colgroup>
            <colgroup width="20%"></colgroup>
            <thead>
            <tr>
                <th>药品名称</th>
                <th>药品规格</th>
                <th>数量</th>
                <th>零售价格</th>
                <th>零售总额</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td><span class="text-num" title="感冒灵颗粒">感冒灵颗粒</span></td>
                <td>5mg</td>
                <td>100</td>
                <td>20.00</td>
                <td>200.00</td>
            </tr>
            <tr>
                <td><span class="text-num" title="感冒灵颗粒">感冒灵颗粒</span></td>
                <td>5mg</td>
                <td>100</td>
                <td>20.00</td>
                <td>200.00</td>
            </tr>
            <tr>
                <td><span class="text-num" title="感冒灵颗粒">感冒灵颗粒</span></td>
                <td>5mg</td>
                <td>100</td>
                <td>20.00</td>
                <td>200.00</td>
            </tr>
            <tr>
                <td><span class="text-num" title="感冒灵颗粒">感冒灵颗粒</span></td>
                <td>5mg</td>
                <td>100</td>
                <td>20.00</td>
                <td>200.00</td>
            </tr>
            <tr>
                <td><span class="text-num" title="感冒灵颗粒">感冒灵颗粒</span></td>
                <td>5mg</td>
                <td>100</td>
                <td>20.00</td>
                <td>200.00</td>
            </tr>
            </tbody>
        </table>
    </div>
    <div class="form-group" style="margin-top: 15px;">
        <span class="pull-left">备注：</span>
        <%-- 如果从已收费列表中点击退费，以下加了disabled处全部去掉 --%>
        <textarea class="form-control" style="width: 92%;" disabled>此处是备注内容，不可修改！</textarea>
    </div>
    <div style="overflow: hidden;">
        <span class="pull-left">医生姓名：张三</span>
        <span class="pull-right">总额：<input type="text" class="form-control" style="display: inline-block; width: 80px;"
                                           value="120.00" disabled></span>
    </div>
    <div class="text-center" style="margin-top: 20px;">
        <%-- 已收费列表点击退费时，显示此样式 --%>
        <%--<button class="btn btn-success" type="button" onclick="">确认退费</button>
        <button id="btnClose" class="btn btn-default" type="button" style="width: 80px; margin-left: 10px;">取消</button>--%>
        <%--  此处是点击退费详情时，只显示关闭按钮 --%>
        <button id="btnClose" class="btn btn-default" type="button" style="width: 80px;">关闭</button>
    </div>
</div>
</body>
</html>
