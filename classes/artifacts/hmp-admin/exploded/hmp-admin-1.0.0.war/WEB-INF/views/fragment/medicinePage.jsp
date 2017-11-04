<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach var="med" items="${medicinePage.content}">
    <span class="tag" onclick="fn_ShowSelectMedicine(${med.id})">${med.name}</span>
</c:forEach>
<div class="row-fluid">
    <div style="float:left;width:40% ">
        <c:if test="${medicinePage.number > 0}">
            <button type="button" onclick="loadMedicines('${type}',${medicinePage.number - 1})" class="btn btn-info"><i class="icon-chevron-left"></i> 上一批</button>
        </c:if>
        <c:if test="${medicinePage.number + 1 < medicinePage.totalPages}">
            <button type="button" onclick="loadMedicines('${type}',${medicinePage.number + 1})" class="btn btn-info">下一批 <i class="icon-chevron-right"></i></button>
        </c:if>
    </div>
    <div style="float:left;width:60% ">
        <div class="input-group">
            <input id="txtMedicineName" value="${name}" style="width: 200px" type="text" placeholder="药品名称">
            <span class="input-group-btn">
                <button onclick="loadMedicines('${type}',0)" class="btn btn-primary" type="button"><i class="icon-search"></i></button>
            </span>
        </div>
    </div>
</div>
<script type="text/javascript">
    $('#txtMedicineName').keydown(function (event) {
        if (event.keyCode == 13) {
            loadMedicines('${type}',0);
            return false;
        }
    });
    if ($('#txtMedicineName').val()) {
        $('#txtMedicineName').focus();
        $('#txtMedicineName').select();
    }
    function loadMedicines(type, page) {
        var name = $('#txtMedicineName').val();
        fn_LoadMedicinePage(type, page, name);
    }
    function loadMedicineOthers(type, page) {
        var name = $('#txtMedicineName').val();
        fn_LoadOtherMedicinePage(type, page, name);
    }
</script>