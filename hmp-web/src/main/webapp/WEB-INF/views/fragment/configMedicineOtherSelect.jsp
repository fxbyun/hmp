<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<c:forEach var="tag" items="${medicinePage.content}">
    <span class="tag span-tag" onclick="fn_SelectMedicineOtherTag('${tag.id}')">${tag.name}</span>
</c:forEach>
<div class="form-group text-right">
    <div class="col-sm-3 col-xs-3 col-lg-3 text-left">
        <%--<button type="button" onclick="loadMedicines(0)" class="btn btn-default"><i class="fa fa-external-link"></i> 常用药品</button>--%>
        <%--<label class="btn btn-warning">药品库</label>--%>
    </div>
    <div class="col-sm-4 col-xs-4 col-lg-4">
        <c:if test="${medicinePage.number > 0}">
            <button type="button" onclick="loadMedicineOthers(${medicinePage.number - 1})" class="btn btn-info"><i class="fa fa-chevron-left"></i> 上一批</button>
        </c:if>
        <c:if test="${medicinePage.number + 1 < medicinePage.totalPages}">
            <button type="button" onclick="loadMedicineOthers(${medicinePage.number + 1})" class="btn btn-info">下一批 <i class="fa fa-chevron-right"></i></button>
        </c:if>
    </div>
    <div class="col-sm-3 col-xs-3 col-lg-3">
        <div class="input-group">
            <input id="txtMedicineOtherTag" value="${name}" type="text" class="form-control" placeholder="药品">
            <span class="input-group-btn">
                <button onclick="fn_LoadMedicineOtherTag()" class="btn btn-primary" type="button"><i class="fa fa-times"></i></button>
                <button onclick="loadMedicineOthers()" class="btn btn-primary" type="button"><i class="fa fa-search"></i></button>
            </span>
        </div>
    </div>
    <div class="col-sm-1 col-xs-1 col-lg-1">
        <%--<button onclick="addMedicineTag();" type="button" class="btn btn-success"><i class="fa fa-plus"></i> 添加药品</button>--%>
    </div>
</div>

<script type="text/javascript">
    $('#txtMedicineOtherTag').keydown(function (event) {
        if (event.keyCode == 13) {
            loadMedicineOthers(0);
            return false;
        }
    });
    if ($('#txtMedicineOtherTag').val()) {
        $('#txtMedicineOtherTag').focus();
        $('#txtMedicineOtherTag').select();
    }

    function loadMedicineOthers(page) {
        fn_LoadMedicineOtherTag(page, "",$('#txtMedicineOtherTag').val());
    }

</script>