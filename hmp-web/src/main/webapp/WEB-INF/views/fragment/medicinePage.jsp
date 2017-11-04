<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<c:forEach var="med" items="${medicinePage.content}">
    <span class="tag span-tag" onclick="fn_ShowSelectMedicine(${med.id},'${med.type}')">${med.name}</span>
</c:forEach>
<div class="form-group text-right" style="margin-top: 10px;">
    <div class="col-sm-3 col-xs-3 col-lg-3 text-left" style="padding-right: 0;">
        <label class="btn btn-warning">常用药品</label>
        <button type="button" onclick="loadMedicineOthers('${type}',0)" class="btn btn-default"><i class="fa fa-external-link"></i> 药品库</button>
    </div>
    <div class="col-sm-4 col-xs-4 col-lg-4 up-dpwn-bt-02">
        <c:if test="${medicinePage.number > 0}">
            <button type="button" onclick="loadMedicines('${type}',${medicinePage.number - 1})" class="btn btn-info">上一批</button>
        </c:if>
        <c:if test="${medicinePage.number + 1 < medicinePage.totalPages}">
            <button type="button" onclick="loadMedicines('${type}',${medicinePage.number + 1})" class="btn btn-info">下一批</button>
        </c:if>
    </div>
    <div class="col-sm-3 col-xs-3 col-lg-3">
        <div class="input-group">
            <input id="txtMedicineName" value="${name}" type="text" class="form-control" placeholder="药品名称">
            <span class="input-group-btn">
                <button onclick="loadMedicines('${type}',0)" class="btn btn-primary" type="button"><i class="fa fa-search"></i></button>
            </span>
        </div>
    </div>
    <div class="col-sm-2 col-xs-2 col-lg-2">
        <button onclick="fn_AddMedicine('${type}');" type="button" class="btn btn-success"><i class="fa fa-plus"></i> 添加药品</button>
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