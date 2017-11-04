<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<div class="tab-box-list first-box-list">
    <c:forEach var="tag" items="${diagnosisPage.content}">
        <span class="tag" onclick="fn_SelectDiagnosisOtherTag('${tag}')">${tag}</span>
    </c:forEach>
</div>
<div class="form-group btn-boxs">

    <button type="button" onclick="loadDiagnoses(0)" class="btn btn-default"> 常用诊断</button>
    <label class="btn btn-default">诊断库</label>

    <div class="up-dpwn-bt">

        <c:if test="${diagnosisPage.number > 0}">
            <button type="button" onclick="loadDiagnosisOthers(${diagnosisPage.number - 1})" class="btn btn-info"> 上一批
            </button>
        </c:if>
        <c:if test="${diagnosisPage.number + 1 < diagnosisPage.totalPages}">
            <button type="button" onclick="loadDiagnosisOthers(${diagnosisPage.number + 1})" class="btn btn-info">下一批
            </button>
        </c:if>
        <div class="pull-right">

            <div class="input-group" style="display: inline-block">
                <input id="txtDiagnosisTag" value="${name}" type="text" class="form-control" placeholder="诊断"
                       style="width: 135px;">
                <span class="input-group-btn">
                    <button onclick="fn_LoadDiagnosisOtherTag()" class="btn btn-primary" type="button"><i
                            class="fa fa-times"></i></button>
                    <button onclick="loadDiagnosisOthers(0)" class="btn btn-primary" type="button"
                            style="border-left: 1px solid #fff;"><i class="fa fa-search"></i></button>
                </span>
            </div>


            <button onclick="addDiagnosisTag();" type="button" class="btn btn-success"><i class="fa fa-plus"></i> 添加诊断
            </button>
        </div>
    </div>
    <div class="diagnose after-line">
        <h4 class="form-inline text-center" style="margin-bottom: 10px">诊后建议</h4>

        <div id="divSuggest" class="medicine-label-box"></div>
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
        fn_LoadDiagnosisTag(page, name);
    }
    function loadDiagnosisOthers(page) {
        var name = $('#txtDiagnosisTag').val();
        fn_LoadDiagnosisOtherTag(page, name);
    }

    function addDiagnosisTag() {
        layer.prompt({title: '添加诊断', maxlength: 200}, function (value, index, elem) {
            var departmentId = $('#tabsDepartment').find('input:radio:checked').val();
            $.postJSON("/tag/addDiagnosisTag", {"diagnosisTag": value, 'departmentId': departmentId}, function (res) {
                if (res && res.success) {
                    if (res.msg)layer.msg(res.msg);
                    fn_SelectDiagnosisTag(res.data.id, res.data.name);
                    loadDiagnoses(0);
                }
                layer.close(index)
            })
        });
    }
</script>