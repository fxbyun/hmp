<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<div class="tab-box-list west-boxs" id="western_span_tag_div">
    <%--开始坐诊的西药药品列表--%>
    <c:forEach var="med" items="${medicinePage.content}">
        <span class="tag" onclick="fn_ShowSelectMedicine(${med.id},'Western')">${med.name}</span>
    </c:forEach>
</div>
<div class="form-group btn-boxs" style="margin-top: 25px; margin-bottom: 0;">

    <button type="button" onclick="fn_ShowMyMedicine('Western')"
            class="btn btn-default"> 我的药品
    </button>
    &nbsp;&nbsp;
    <button type="button" onclick="fn_ShowSelectRp('Western')" style="margin-right:0px;margin-left:-18px;"
            class="btn btn-default"> 我的药方
    </button>
    &nbsp;&nbsp;
    <input type="hidden" min="1" name="westernQty" id="westernQty"
           value="${emr.westernQty}" />
    <label class="btn btn-default hide">常用药品</label>
    <button type="button" onclick="loadWesternOthers(0)" class="btn btn-default hide"> 药品库</button>
    <div class="up-dpwn-bt">

        <c:if test="${medicinePage.number > 0}">
            <button type="button" onclick="loadWesterns(${medicinePage.number - 1})" class="btn btn-info"> 上一批</button>
        </c:if>
        <bu id="syp_Back_wer">
            <c:if test="${medicinePage.number + 1 < medicinePage.totalPages}">
                <button type="button" onclick="loadWesterns(${medicinePage.number + 1})" class="btn btn-info">下一批</button>
            </c:if>
        </bu>
        <div class="pull-right">

            <div class="input-group">
                <input id="txtWeMedicineName" value="${name}" type="text" class="form-control" placeholder="药品名称">
                <span class="input-group-btn">
                    <button onclick="fn_LoadWesternMedicinePage()" class="btn btn-primary" type="button"><i class="fa fa-times"></i></button>
                    <button onclick="loadWesterns(0)" class="btn btn-primary" type="button"
                            style="border-left: 1px solid #fff;"><i class="fa fa-search"></i></button>
                </span>
            </div>
            <c:if test="${doctor.doctorType!='Sub_Doctor'}">
            <button onclick="fn_AddMedicine('Western','西药及中成药房');" type="button" class="btn btn-success"><i
                    class="fa fa-plus"></i> 添加药品
            </button>
            </c:if>
        </div>
    </div>
</div>
<script type="text/javascript">
    $('#txtWeMedicineName').keydown(function (event) {
        if (event.keyCode == 13) {
            loadWesterns(0);
            return false;
        }
    });


    function scanKey3() {
        var page = 0;
        var name = $("#txtWeMedicineName").val();
        if (name == "") {
            loadWesterns(0)
            return;
        }
//        var departmentId = $(DEPARTMENT_TABS_ID).find('input:radio:checked').val();

        var diagnosisName = $("#divDiagnosis span input[name='diagnosisResults']").val()
        diagnosisName = (diagnosisName == undefined ? "" : diagnosisName);
        $.ajax({
            type: 'POST',
            url: "/fragment/medicines/Western?page={0}&name={1}&diagonsisName={2}".format(page, name, diagnosisName),
            data: {},
            dataType: "json",
            success: function (resu) {
                var tag_enetit_array = resu.content;
                var sum_span_tag = "";

                for (var index in tag_enetit_array) {
                    var oneSpanTag = tag_enetit_array[index];
                    var spn_tag = '<span class="tag" onclick="fn_ShowSelectMedicine({0})">{1}</span>'.format(oneSpanTag.id, oneSpanTag.name)
                    sum_span_tag += spn_tag;
                }
                $("#western_span_tag_div").html(sum_span_tag);
                if (resu.totalElements >= 28) {
                    var button_next = '<button type="button" onclick="loadWesterns(1)" class="btn btn-info">下一批</button>'
                    $("#syp_Back_wer").html(button_next)
                } else {
                    $("#syp_Back_wer").html("")
                }
            }
        });
    }

    $('#txtWeMedicineName').keyup(function (even) {
        //TODO 临时去除
//        scanKey3();
    })

    if ($('#txtWeMedicineName').val()) {
        $('#txtWeMedicineName').focus();
        $('#txtWeMedicineName').select();
    }

    $(function () {
        $('#txtWeMedicineName').focus();
    })
    function loadWesterns(page) {
        var name = $('#txtWeMedicineName').val();
        fn_LoadWesternMedicinePage(page, name);
    }
    function loadWesternOthers(page) {
        var name = $('#txtWeMedicineName').val();
        fn_LoadWesternOtherMedicinePage(page, name);
    }
</script>