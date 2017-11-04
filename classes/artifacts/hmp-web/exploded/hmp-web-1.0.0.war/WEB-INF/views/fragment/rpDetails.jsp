<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<div style="padding-left: 10px;padding-right: 10px;">
    <h4 class="text-center">药方描述</h4>
    <label>功能主治：</label>

    <div style="padding: 10px;">${rp.remark}</div>
    <label>用药清单：</label>

    <div id="divItems" style="padding: 10px;min-height: 150px;">
        <c:forEach var="item" items="${rp.prescriptionItemList}">
            <span class="tag">${item.medicineName}${item.qty}${medicineUnits[item.unit]}</span>
        </c:forEach>
    </div>
</div>
