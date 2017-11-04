<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<div class="tab-box-list first-box-list" id="diagon_tag_div">
    <c:forEach var="tag" items="${diagnosisPage.content}">
        <span class="tag" style="width: 32%" onclick="fn_SelectDiagnosisTag('${tag.id}','${tag.name}')">${tag.name}</span>
    </c:forEach>
</div>
<div class="form-group btn-boxs">
    <label class="btn btn-default">常用诊断</label>
    <button type="button" onclick="loadDiagnosisOthers(0)" class="btn btn-default"> 诊断库</button>
    <div class="up-dpwn-bt">

        <c:if test="${diagnosisPage.number > 0}">
            <button type="button" onclick="fn_LoadDiagnosisTag(${diagnosisPage.number - 1})" class="btn btn-info"> 上一批</button>
        </c:if>
        <bu id="syp_Back_dia">
            <c:if test="${diagnosisPage.number + 1 < diagnosisPage.totalPages}">
                <button type="button" onclick="fn_LoadDiagnosisTag(${diagnosisPage.number + 1})" class="btn btn-info">下一批</button>
            </c:if>
        </bu>
        <div class="pull-right">

            <div class="input-group" style="display: inline-block">
                <input id="txtDiagnosisTag" value="${name}" type="text" class="form-control" placeholder="诊断"
                       style="width: 135px;">
                    <span class="input-group-btn">
                        <button onclick="fn_LoadDiagnosisTag()" class="btn btn-primary" type="button"><i class="fa fa-times"></i></button>
                        <button onclick="loadDiagnoses(0)" class="btn btn-primary" type="button"
                                style="border-left: 1px solid #fff;"><i class="fa fa-search"></i></button>
                    </span>
            </div>


            <button onclick="addDiagnosisTag();" type="button" class="btn btn-success"><i class="fa fa-plus"></i> 添加诊断</button>
        </div>
    </div>
    <div class="diagnose after-line">
        <h4 class="form-inline text-center" style="margin-bottom: 10px">诊后建议</h4>

        <div id="divSuggest" class="medicine-label-box"></div>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        if ($("#divSuggest").children().length <= 0) {
            $("#divSuggest").html(jyHtml);
        }

        if ($("#divSuggest").children().length <= 0) {
            $("#divSuggest").html(divSuggestHtml);
        }

    })

    $('#txtDiagnosisTag').keydown(function (event) {
        if (event.keyCode == 13) {
            loadDiagnoses(0);
            return false;
        }
    });

    function scanKey2() {
        var page = 0;
        var name = $("#txtDiagnosisTag").val();
        var departmentId = $(DEPARTMENT_TABS_ID).find('input:radio:checked').val();
        $.ajax({
            type: 'POST',
            url: '/fragment/diagnosisTags?page={0}&departmentId={1}&name={2}'.format(page, departmentId, name),
            data: {},
            dataType: "json",
            success: function (resu) {
                var tag_enetit_array = resu.content;
                var sum_span_tag = "";
                for (var index in tag_enetit_array) {
                    var oneSpanTag = tag_enetit_array[index];
                    var spn_tag = ' <span class="tag" style="width: 32%" onclick="fn_SelectDiagnosisOtherTag(\'{0}\')">{1}</span>'.format(oneSpanTag.name, oneSpanTag.name)
                    sum_span_tag += spn_tag;
                }
                $("#diagon_tag_div").html(sum_span_tag);
                if (resu.totalElements >= 21) {
                    var button_next = '<button type="button" onclick="fn_LoadDiagnosisTag(1)" class="btn btn-info">下一批</button>'
                    $("#syp_Back_dia").html(button_next)
                } else {
                    $("#syp_Back_dia").html("")
                }
            }
        });
    }

    $('#txtDiagnosisTag').keyup(function (even) {
        scanKey2();
    })

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
            $.postJSON("/tag/addDiagnosisTag", {
                "diagnosisTag": value, 'departmentId': departmentId,
                "helpCode": pinyin.getCamelChars(value)
            }, function (res) {
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