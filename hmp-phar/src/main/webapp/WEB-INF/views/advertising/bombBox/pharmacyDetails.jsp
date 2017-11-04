<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/10/12
  Time: 14:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/advInclude.jsp" %>
<%@ taglib prefix="hmp" uri="/WEB-INF/TLD/HmpLoadStaticFile.tld" %>
<html>
<head>
    <title>药品清单</title>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/advertising/scripts/advertisingIndex.js" type="js"/>
    <script type="application/javascript">
        $(function () {

        })
        function SendMed(id) {
            updateStatus(id, "Have_Dispensing", "${emr.realCost}");
        }
    </script>
</head>
<body style="background-color: #fff;">
<div class="charge-content">
    <div style="height: 182px; overflow-y: auto;">
        <table class="bomb-table" width="100%" border="1">
            <colgroup width="20%"></colgroup>
            <colgroup width="15%"></colgroup>
            <colgroup width="15%"></colgroup>
            <colgroup width="20%"></colgroup>
            <colgroup width="10%"></colgroup>
            <colgroup width="10%"></colgroup>
            <colgroup width="10%"></colgroup>
            <thead>
            <tr>
                <th>药品名称</th>
                <th>药品规格</th>
                <th>治疗方式</th>
                <th>标准用量</th>
                <th>数量</th>
                <th>零售价格</th>
                <th>零售总额</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${emr.getEmrMedicineList()}" var="oneEmrMedObj">
                <tr>
                    <td>
                         <span class="text-num" title="${oneEmrMedObj.medicineName}">
                                 ${oneEmrMedObj.medicineName}
                         </span>
                    </td>
                    <td>
                            ${oneEmrMedObj.standard}
                    </td>
                    <td>
                            ${oneEmrMedObj.useMode}
                    </td>
                    <td>
                        <c:if test="${oneEmrMedObj.hasUsage}">
                            ${oneEmrMedObj.useTimes}
                            <c:if test="${oneEmrMedObj.useQty.indexOf('每次')<0}">
                                每次
                            </c:if>
                            ${oneEmrMedObj.useQty} ${oneEmrMedObj.useUnit}
                        </c:if>
                    </td>
                    <td>${oneEmrMedObj.qty}</td>
                    <td>${oneEmrMedObj.unitPrice}</td>
                    <td>${oneEmrMedObj.price}</td>
                </tr>
            </c:forEach>
            <%-- 新增纸质处方 start --%>
            <%--<tr style="background-color: #e1ffdd;">--%>
            <%--<th colspan="2">纸质处方</th>--%>
            <%--<th></th>--%>
            <%--<th></th>--%>
            <%--<th colspan="2">日期</th>--%>
            <%--<th>总额</th>--%>
            <%--</tr>--%>
            <%--<tr>--%>
            <%--<td colspan="2"><a href="##" target="_blank" title="201612021230">纸质处方西药：201612021230</a></td>--%>
            <%--<td></td>--%>
            <%--<td></td>--%>
            <%--<td colspan="2">2016/12/02</td>--%>
            <%--<td>0.0</td>--%>
            <%--</tr>--%>
            <%-- end --%>
            </tbody>
        </table>
    </div>

    <div style="overflow: hidden; margin-top: 15px;">
        <span class="pull-left">医生姓名：${emr.doctor.name}</span>
        <span class="pull-right">总额：<input type="text" class="form-control" style="display: inline-block; width: 80px;"
                                           value="${emr.realCost}" disabled></span>
    </div>
    <div class="text-center" style="margin-top: 20px;">
        <button class="btn btn-success" type="button" onclick="SendMed(${emr.id})">确认发药</button>
        <button id="btnClose" class="btn btn-default" type="button" style="width: 80px; margin-left: 10px;">取消</button>
    </div>
</div>

</body>
</html>
