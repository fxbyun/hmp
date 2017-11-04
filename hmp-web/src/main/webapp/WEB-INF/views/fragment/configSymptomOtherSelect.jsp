<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<c:forEach var="tag" items="${symptomPage.content}">
    <span class="tag span-tag" onclick="fn_SelectSymptomOtherTag('${tag}')">${tag}</span>
</c:forEach>
<div class="form-group text-right" style="position: absolute; bottom: 10px; right: -50px;">
    <%--<div class="col-sm-3 col-xs-3 col-lg-3 text-left">
        &lt;%&ndash;<button type="button" onclick="loadSymptoms(0)" class="btn btn-default"><i class="fa fa-external-link"></i> 常用症状</button>&ndash;%&gt;
        &lt;%&ndash;<label class="btn btn-warning">症状库</label>&ndash;%&gt;
    </div>--%>
    <div class="col-sm-4 col-xs-4 col-lg-4 up-dpwn-bt-02">
        <c:if test="${symptomPage.number > 0}">
            <button type="button" onclick="loadSymptomOthers(${symptomPage.number - 1})" class="btn btn-info">上一批</button>
        </c:if>
        <c:if test="${symptomPage.number + 1 < symptomPage.totalPages}">
            <button type="button" onclick="loadSymptomOthers(${symptomPage.number + 1})" class="btn btn-info">下一批</button>
        </c:if>
    </div>
    <div class="col-sm-5 col-xs-5 col-lg-5">
        <div class="input-group">
            <input id="txtSymptomOtherTag" value="${name}" type="text" class="form-control" placeholder="搜索症状名">
            <span class="input-group-btn">
                <%--<button onclick="fn_LoadSymptomOtherTag()" class="btn btn-primary" type="button"><i class="fa fa-times"></i></button>--%>
                <button onclick="loadSymptomOthers()" class="btn btn-primary" type="button"><i class="fa fa-search"></i></button>
            </span>
        </div>
    </div>
    <div class="col-sm-1 col-xs-1 col-lg-1">
        <%--<button onclick="addSymptomTag();" type="button" class="btn btn-success"><i class="fa fa-plus"></i> 添加症状</button>--%>
    </div>
</div>

<script type="text/javascript">
    $('#txtSymptomOtherTag').keydown(function (event) {
        if (event.keyCode == 13) {
            loadSymptomOthers();
            return false;
        }
    });
    if ($('#txtSymptomOtherTag').val()) {
        $('#txtSymptomOtherTag').focus();
        $('#txtSymptomOtherTag').select();
    }

    function loadSymptomOthers(page) {
        fn_LoadSymptomOtherTag(page, $('#txtSymptomOtherTag').val());
    }

</script>