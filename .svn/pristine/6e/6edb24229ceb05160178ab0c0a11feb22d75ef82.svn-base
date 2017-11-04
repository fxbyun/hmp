<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<c:forEach var="tag" items="${diagnosisPage.content}">
    <span class="tag span-tag" style="width: auto;" onclick="fn_SelectDiagnosisTag('${tag}')">${tag}</span>
</c:forEach>
<div class="form-group text-right" style="margin-top: 10px;">
    <div class="col-sm-4 col-xs-4 col-lg-4 text-left">
        <button type="button" onclick="loadDiagnoses(0)" class="btn btn-default"><i class="fa fa-external-link"></i> 常用诊断</button>
        <label class="btn btn-warning">诊断库</label>
    </div>
    <div class="col-sm-4 col-xs-4 col-lg-4 up-dpwn-bt-02">
        <c:if test="${diagnosisPage.number > 0}">
            <button type="button" onclick="loadDiagnosisOthers(${diagnosisPage.number - 1})" class="btn btn-info">上一批</button>
        </c:if>
        <c:if test="${diagnosisPage.number + 1 < diagnosisPage.totalPages}">
            <button type="button" onclick="loadDiagnosisOthers(${diagnosisPage.number + 1})" class="btn btn-info">下一批</button>
        </c:if>
    </div>
    <div class="col-sm-4 col-xs-4 col-lg-4">
        <div class="input-group">
            <input id="txtDiagnosisTag" value="${name}" type="text" class="form-control" placeholder="诊断">
            <span class="input-group-btn">
                <button onclick="fn_LoadRpDiagnosisOtherTag()" class="btn btn-primary" type="button"><i class="fa fa-times"></i></button>
                <button onclick="loadDiagnosisOthers(0)" class="btn btn-primary" type="button"><i class="fa fa-search"></i></button>
            </span>
        </div>
    </div>
</div>
<script type="text/javascript">
    $('#txtDiagnosisTag').keydown(function (event) {
        if (event.keyCode == 13) {
            loadDiagnoses(0);
            return false;
        }
    });
    if ($('#txtDiagnosisTag').val()) {
        $('#txtDiagnosisTag').focus();
        $('#txtDiagnosisTag').select();
    }
    function loadDiagnoses(page) {
        var name = $('#txtDiagnosisTag').val();
        fn_LoadRpDiagnosisTag(page, name);
    }
    function loadDiagnosisOthers(page) {
        var name = $('#txtDiagnosisTag').val();
        fn_LoadRpDiagnosisOtherTag(page, name);
    }
</script>