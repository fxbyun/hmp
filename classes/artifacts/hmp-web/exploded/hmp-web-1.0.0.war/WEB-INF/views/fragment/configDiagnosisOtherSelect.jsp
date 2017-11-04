<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<c:forEach var="tag" items="${diagnosisPage.content}">
    <span class="tag span-tag" onclick="fn_SelectDiagnosisOtherTag('${tag}')">${tag}</span>
</c:forEach>
<div class="form-group text-right" style="position: absolute; bottom: 10px; right: -50px;">
    <%--<div class="col-sm-3 col-xs-3 col-lg-3 text-left">
        &lt;%&ndash;<button type="button" onclick="loadDiagnosiss(0)" class="btn btn-default"><i class="fa fa-external-link"></i> 常用诊断</button>&ndash;%&gt;
        &lt;%&ndash;<label class="btn btn-warning">诊断库</label>&ndash;%&gt;
    </div>--%>
    <div class="col-sm-4 col-xs-4 col-lg-4 up-dpwn-bt-02">
        <c:if test="${diagnosisPage.number > 0}">
            <button type="button" onclick="loadDiagnosisOthers(${diagnosisPage.number - 1})" class="btn btn-info">上一批</button>
        </c:if>
        <c:if test="${diagnosisPage.number + 1 < diagnosisPage.totalPages}">
            <button type="button" onclick="loadDiagnosisOthers(${diagnosisPage.number + 1})" class="btn btn-info">下一批</button>
        </c:if>
    </div>
    <div class="col-sm-5 col-xs-5 col-lg-5">
        <div class="input-group">
            <input id="txtDiagnosisOtherTag" value="${name}" type="text" class="form-control" placeholder="诊断">
            <span class="input-group-btn">
                <%--<button onclick="fn_LoadDiagnosisOtherTag()" class="btn btn-primary" type="button"><i class="fa fa-times"></i></button>--%>
                <button onclick="loadDiagnosisOthers()" class="btn btn-primary" type="button"><i class="fa fa-search"></i></button>
            </span>
        </div>
    </div>
    <div class="col-sm-1 col-xs-1 col-lg-1">
        <%--<button onclick="addDiagnosisTag();" type="button" class="btn btn-success"><i class="fa fa-plus"></i> 添加诊断</button>--%>
    </div>
</div>

<script type="text/javascript">
    $('#txtDiagnosisOtherTag').keydown(function (event) {
        if (event.keyCode == 13) {
            loadDiagnosisOthers();
            return false;
        }
    });
    if ($('#txtDiagnosisOtherTag').val()) {
        $('#txtDiagnosisOtherTag').focus();
        $('#txtDiagnosisOtherTag').select();
    }

    function loadDiagnosisOthers(page) {
        fn_LoadDiagnosisOtherTag(page, $('#txtDiagnosisOtherTag').val());
    }

</script>