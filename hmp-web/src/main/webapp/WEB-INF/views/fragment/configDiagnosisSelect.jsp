<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<c:forEach var="tag" items="${diagnosisPage.content}">
    <span class="tag span-tag" onclick="fn_SelectDiagnosisTag('${tag.id}','${tag.name}')">${tag.name}</span>
</c:forEach>
<div class="form-group text-right" style="position: absolute; bottom: 10px; right: 0;">
    <%--<div class="col-sm-3 col-xs-3 col-lg-3 text-left">
        &lt;%&ndash;<label class="btn btn-warning">常用诊断</label>&ndash;%&gt;
        &lt;%&ndash;<button type="button" onclick="loadDiagnosisOthers(0)" class="btn btn-default"><i class="fa fa-external-link"></i> 诊断库</button>&ndash;%&gt;
    </div>--%>
    <div class="col-sm-4 col-xs-4 col-lg-4 up-dpwn-bt-02">
        <c:if test="${diagnosisPage.number > 0}">
            <button type="button" onclick="loadDiagnosiss(${diagnosisPage.number - 1})" class="btn btn-info">上一批</button>
        </c:if>
        <c:if test="${diagnosisPage.number + 1 < diagnosisPage.totalPages}">
            <button type="button" onclick="loadDiagnosiss(${diagnosisPage.number + 1})" class="btn btn-info">下一批</button>
        </c:if>
    </div>
    <div class="col-sm-5 col-xs-5 col-lg-5">
        <div class="input-group">
            <input id="txtDiagnosisTag" value="${name}" type="text" class="form-control" placeholder="诊断">
            <span class="input-group-btn">
                <%--<button onclick="fn_LoadDiagnosisTag()" class="btn btn-primary" type="button"><i class="fa fa-times"></i></button>--%>
                <button onclick="loadDiagnosiss()" class="btn btn-primary" type="button"><i class="fa fa-search"></i></button>
            </span>
        </div>
    </div>
    <div class="col-sm-3 col-xs-3 col-lg-3 pull-right">
        <button onclick="addDiagnosisTag();" type="button" class="btn btn-success"><i class="fa fa-plus"></i> 添加诊断</button>
    </div>
</div>
<script type="text/javascript">
    $('#txtDiagnosisTag').keydown(function (event) {
        if (event.keyCode == 13) {
            loadDiagnosiss();
            return false;
        }
    });
    if ($('#txtDiagnosisTag').val()) {
        $('#txtDiagnosisTag').focus();
        $('#txtDiagnosisTag').select();
    }
    function loadDiagnosiss(page) {
        var name = $('#txtDiagnosisTag').val();
        fn_LoadDiagnosisTag(page, name);
    }

    function addDiagnosisTag() {
        layer.prompt({title: '添加诊断', maxlength: 200}, function (value, index, elem) {
            $.postJSON("/tag/addDiagnosisTag", {"departmentId":1,"diagnosisTag": value,
                "helpCode": pinyin.getCamelChars(value)}, function (res) {
                if (res && res.success) {
                    if (res.msg)layer.msg(res.msg);
                        loadDiagnosiss();
                }
                layer.close(index);
            })
        });
    }
</script>