<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/10/12
  Time: 15:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/advInclude.jsp" %>
<%@ taglib prefix="hmp" uri="/WEB-INF/TLD/HmpLoadStaticFile.tld" %>
<html>
<head>
    <title>已退药品详情</title>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/advertising/scripts/advertisingIndex.js" type="js"/>
</head>
<body style="background-color: #fff;">
<div class="charge-content">
    <div style="height: 182px; overflow-y: auto;">
        <table class="bomb-table" width="100%" border="1">
            <colgroup width="20%"></colgroup>
            <colgroup width="20%"></colgroup>
            <colgroup width="15%"></colgroup>
            <colgroup width="15%"></colgroup>
            <colgroup width="20%"></colgroup>
            <colgroup width="10%"></colgroup>
            <thead>
            <tr>
                <th>药品名称</th>
                <th>药品规格</th>
                <th>数量</th>
                <th>零售价格</th>
                <th>零售总额</th>
                <th>退药数量</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${emr.getEmrMedicineList()}" var="oneEmrMedObj">
                <tr>
                    <td>
                        <span class="text-num" title="${oneEmrMedObj.medicineName}"> ${oneEmrMedObj.medicineName}</span>
                    </td>
                    <td>
                            ${oneEmrMedObj.standard}
                    </td>
                    <td>
                            ${oneEmrMedObj.qty}
                    </td>
                    <td>
                            ${oneEmrMedObj.unitPrice}
                    </td>
                    <td>
                            ${oneEmrMedObj.price}
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${oneEmrMedObj.status=='Have_Dispensing_Back'}">
                                ${oneEmrMedObj.qty}
                            </c:when>
                            <c:when test="${oneEmrMedObj.status=='CHARGE'}">
                                未发货
                            </c:when>
                            <c:when test="${oneEmrMedObj.status=='Normal'}">
                                未缴费
                            </c:when>
                            <c:otherwise>
                                该药未退
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div style="margin-top: 15px;">
        <span style="vertical-align: top;">备注：</span>
        <textarea class="form-control" style="display: inline-block; width: 93%;" disabled>
            ${emr.backMedRemarks}
        </textarea>
    </div>

    <div style="overflow: hidden; margin-top: 15px;">
        <span class="pull-left">医生姓名：${emr.doctor.name}</span>
        <span class="pull-right">总额：<input type="text" class="form-control" style="display: inline-block; width: 80px;"
                                           value="${emr.realCost}" disabled></span>
    </div>
    <div class="text-center" style="margin-top: 20px;">
        <%--<button class="btn btn-success" type="button">确认</button>--%>
        <button id="btnClose" class="btn btn-default" type="button" style="width: 80px; margin-left: 10px;">关闭</button>
    </div>
</div>
</body>
</html>
