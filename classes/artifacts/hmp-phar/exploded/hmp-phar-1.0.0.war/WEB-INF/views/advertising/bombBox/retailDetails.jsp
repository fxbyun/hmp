<%--@elvariable id="patient" type="com.qiaobei.hmp.modules.entity.Patient"--%>
<%--@elvariable id="retail" type="com.qiaobei.hmp.modules.entity.Retail"--%>
<%--@elvariable id="retMed" type="com.qiaobei.hmp.modules.entity.RetailMedicine"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/advInclude.jsp" %>
<%@ taglib prefix="hmp" uri="/WEB-INF/TLD/HmpLoadStaticFile.tld" %>
<html>
<head>
    <title>零售订单详情</title>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/advertising/scripts/advertisingIndex.js" type="js"/>
</head>
<body style="background-color: #fff; ">
<div class="charge-content">
    <div class="patil-info">
        <table class="bomb-table" width="100%" border="1">
            <colgroup width="10%"></colgroup>
            <colgroup width="15%"></colgroup>
            <colgroup width="10%"></colgroup>
            <colgroup width="13%"></colgroup>
            <colgroup width="10%"></colgroup>
            <colgroup width="12%"></colgroup>
            <colgroup width="10%"></colgroup>
            <colgroup width="15%"></colgroup>
            <c:if test="${not empty patient}">
                <tr style="height: 45px;">
                    <th>姓名</th>
                    <td>${patient.name}</td>
                    <th>性别</th>
                    <td>${genderMap[patient.gender]}</td>
                    <th>年龄</th>
                    <td>${patient.age}</td>
                    <th>电话</th>
                    <td>${patient.mobile}</td>
                </tr>
            </c:if>
        </table>
    </div>
    <div style="height: 200px; overflow-y: auto;">
        <table class="bomb-table" width="100%" border="1">
            <colgroup width="10%"></colgroup>
            <colgroup width="10%"></colgroup>
            <colgroup width="10%"></colgroup>
            <colgroup width="10%"></colgroup>
            <colgroup width="10%"></colgroup>
            <colgroup width="10%"></colgroup>
            <colgroup width="10%"></colgroup>
            <colgroup width="9%"></colgroup>
            <colgroup width="12%"></colgroup>
            <colgroup width="9%"></colgroup>
            <thead>
            <tr>
                <th>药品名称</th>
                <th>药厂</th>
                <th>条码</th>
                <th>数量</th>
                <th>零售价格</th>
                <th>统计单位</th>
                <td>药品规格</td>
                <td>库存</td>
                <td>有效期</td>
                <td>零售总额</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${retail.retailMedicineList}" var="retMed" varStatus="status">
                <tr>
                    <td><span class="text-num" style="width: 80px;" title="">${retMed.medicinePrivate.name}</span></td>
                    <td><span class="text-num" style="width: 80px;"
                              title="">${empty stockMap[retMed.id]?companyMap[retMed.id]:stockMap[retMed.id].companyName}</span>
                    </td>
                    <td><span class="text-num" style="width: 80px;" title="">${retMed.barCode}</span></td>
                    <td>${retMed.qty*retMed.copies}</td>
                    <td>${retMed.retailPrice}</td>
                    <td>${medicineUnits[retMed.unit]}</td>
                    <td>${retMed.standard}</td>
                    <td>${empty stockMap[retMed.id]?"":stockMap[retMed.id].stockNum}</td>
                    <td>${retMed.validityDate}</td>
                    <td>${retMed.totalPrice}</td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>
    <div class="text-right" style="padding-right: 20px;">
        <span>药品合计：￥${retail.allMedCost}</span>
        <span>实收金额：￥${retail.realCost}</span>
    </div>
    <div class="text-center" style="margin-top: 20px;">
        <button id="btnClose" class="btn btn-default" type="button" style="width: 80px;">关闭</button>
    </div>
</div>
</body>
</html>
