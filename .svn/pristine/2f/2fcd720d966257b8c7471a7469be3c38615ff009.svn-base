<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>

<c:forEach var="med" items="${medicinePage.content}">
    <span class="tag span-tag" onclick="fn_ShowSelectMedicine(${med.id})">${med.name}</span>
</c:forEach>
<div class="form-group text-right" style="margin-top: 10px;">
    <div class="col-sm-3 col-xs-3 text-left" style="padding-right: 0;">
        <button type="button" onclick="loadMedicines('${type}',0)" class="btn btn-default"><i class="fa fa-external-link"></i> 常用药品</button>
        <label class="btn btn-warning">药品库</label>
    </div>
    <div class="col-sm-4 col-xs-4 col-lg-4  up-dpwn-bt-02">
        <c:if test="${medicinePage.number > 0}">
            <button type="button" onclick="loadMedicineOthers('${type}',${medicinePage.number - 1})" class="btn btn-info">上一批</button>
        </c:if>
        <c:if test="${medicinePage.number + 1 < medicinePage.totalPages}">
            <button type="button" onclick="loadMedicineOthers('${type}',${medicinePage.number + 1})" class="btn btn-info">下一批</button>
        </c:if>
    </div>
    <div class="col-sm-3 col-xs-3 col-lg-3">
        <div class="input-group">
            <input id="txtChMedicineName" value="${name}" type="text" class="form-control" placeholder="药品名称">
            <span class="input-group-btn">
                <button onclick="loadMedicineOthers('${type}',0)" class="btn btn-primary" type="button"><i class="fa fa-search"></i> 查询</button>
            </span>
        </div>
    </div>
    <div class="col-sm-2 col-xs-2 col-lg-2">
        <button onclick="fn_AddMedicine('${type}');" type="button" class="btn btn-success"><i class="fa fa-plus"></i> 添加药品</button>
    </div>
</div>

<script type="text/javascript">
    $('#txtChMedicineName').keydown(function (event) {
        if (event.keyCode == 13) {
            loadMedicineOthers('${type}',0);
            return false;
        }
    });
    if ($('#txtChMedicineName').val()) {
        $('#txtChMedicineName').focus();
        $('#txtChMedicineName').select();
    }
    function loadMedicines(type, page) {
        var name = $('#txtChMedicineName').val();
        fn_LoadMedicinePage(type, page, name);
    }
    function loadMedicineOthers(type, page) {
        var name = $('#txtChMedicineName').val();
        fn_LoadOtherMedicinePage(type, page, name);
    }
</script>