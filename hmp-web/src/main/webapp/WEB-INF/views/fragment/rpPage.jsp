<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<style>
    .select-box{
        background-color: #fff;
        border-radius: 10px;
    }
    div#divRpDetails{
        background-color: #fff;
        border-radius: 10px;
        margin-bottom: 10px;
    }

    div#divRpDetails h4{
        padding-top:15px;
    }
</style>
<div class="select-box">
    <c:forEach var="rp" varStatus="status" items="${rpPage.content}">
        <label class="btn btn-default">
            <input type="radio" name="rpId" value="${rp.id}" <c:if test="${status.first}">checked="checked"</c:if>/> ${rp.name}
        </label>
    </c:forEach>
    <div class="text-center">
        <c:if test="${rpPage.number > 0}">
            <button type="button" onclick="fn_LoadRpList(${rpPage.number - 1})" class="btn btn-success"><i class="fa fa-chevron-left"></i> 上一页</button>
        </c:if>
        <c:if test="${rpPage.number + 1 < rpPage.totalPages}">
            <button type="button" onclick="fn_LoadRpList(${rpPage.number + 1})" class="btn btn-success"><i class="fa fa-chevron-right"></i> 下一页</button>
        </c:if>
        &nbsp;&nbsp;&nbsp;&nbsp;
        第&nbsp;${rpPage.number + 1}&nbsp;页/共&nbsp;${rpPage.totalPages}&nbsp;页
    </div>
</div>
<c:set var="rp" value="${rpPage.content[0]}"/>
<div id="divRpDetails" style="padding-left: 10px;padding-right: 10px;">
    <h4 class="text-center">药方描述</h4>
    <label>功能主治：</label>

    <div style="padding: 10px;">${rp.remark}</div>
    <label>用药清单：</label>

    <div id="divItems" style="padding: 10px;min-height: 150px;">
        <c:forEach var="item" items="${rp.prescriptionItemList}">
            <span class="tag">${item.medicineName}${item.qty}${medicineUnits[item.unit]}x${item.copies}次</span>
        </c:forEach>
    </div>
</div>
<script type="text/javascript">
    $('input[name="rpId"]').change(function () {
        var rpId = $('input[name="rpId"]:checked').val();
        $('#divRpDetails').load('/fragment/rp/' + rpId);
    });
</script>
