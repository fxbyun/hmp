<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<div class="tab-box-list first-box-list" id="chmed_span_tag_div">
    <%--开始坐诊的中药药品列表--%>
    <c:forEach var="med" items="${medicinePage.content}">
        <span class="tag" onclick="fn_ShowSelectMedicine(${med.id},'Chinese')">${med.name}</span>
    </c:forEach>
</div>
<div class="form-group btn-boxs" style="margin-top: 25px; margin-bottom: 0;">
    <button type="button" onclick="fn_ShowMyMedicine('Chinese')"
            class="btn btn-default"> 我的药品
    </button>
    &nbsp;&nbsp;
    <button type="button" onclick="fn_ShowSelectRp('Chinese')" style="margin-right:0px;margin-left:-18px;"
            class="btn btn-default"> 我的药方
    </button>
    &nbsp;&nbsp;


    <label class="btn btn-default hide">常用药品</label>
    <button type="button" onclick="loadChineseOthers(0)" class="btn btn-default hide"> 药品库</button>
    <div class="up-dpwn-bt">

        <c:if test="${medicinePage.number > 0}">
            <button type="button" onclick="loadChinese(${medicinePage.number - 1})" class="btn btn-info"> 上一批</button>
        </c:if>
        <bu id="syp_Back_ch">
        <c:if test="${medicinePage.number + 1 < medicinePage.totalPages}">
            <button type="button" onclick="loadChinese(${medicinePage.number + 1})" class="btn btn-info">下一批 </button>
        </c:if>
            </bu>
        <div class="pull-right">

            <div class="input-group">
                <input id="txtChMedicineName" value="${name}" type="text" class="form-control" placeholder="药品名称">
                <span class="input-group-btn">
                     <button onclick="fn_LoadChineseMedicinePage()" class="btn btn-primary" type="button"><i class="fa fa-times"></i></button>
                    <button onclick="loadChinese(0)" class="btn btn-primary" type="button"
                            style="border-left: 1px solid #fff;"><i class="fa fa-search"></i></button>
                </span>
            </div>

            <c:if test="${doctor.doctorType!='Sub_Doctor'}">
                <button onclick="fn_AddMedicine('Chinese','中草药房');" type="button" class="btn btn-success"><i
                        class="fa fa-plus"></i> 添加药品
                </button>
            </c:if>

        </div>
    </div>
</div>
<script type="text/javascript">
    $('#txtChMedicineName').keydown(function (event) {
        if (event.keyCode == 13) {
            loadChinese(0);
            return false;
        }
    });

    function scanKey4() {
        var page = 0;
        var name = $("#txtChMedicineName").val();

//        var departmentId = $(DEPARTMENT_TABS_ID).find('input:radio:checked').val();
        var diagnosisName = $("#divDiagnosis span input[name='diagnosisResults']").val()

        diagnosisName = (diagnosisName == undefined ? "" : diagnosisName);
        $.ajax({
            type: 'POST',
            url: "/fragment/medicines/Chinese?page={0}&name={1}&diagonsisName={2}".format(page, name, diagnosisName),
            data: {},
            dataType: "json",
            success: function (resu) {
                var tag_enetit_array = resu.content;
                var sum_span_tag = "";

                for (var index in tag_enetit_array) {
                    var oneSpanTag = tag_enetit_array[index];
                    var spn_tag = ' <span class="tag" onclick="fn_ShowSelectMedicine({0})">{1}</span>'.format(oneSpanTag.id, oneSpanTag.name)
                    sum_span_tag += spn_tag;
                }
                $("#chmed_span_tag_div").html(sum_span_tag);
                if (resu.totalElements >= 28) {
                    var button_next = '<button type="button" onclick="loadChinese(1)" class="btn btn-info">下一批</button>'
                    $("#syp_Back_ch").html(button_next)
                } else {
                    $("#syp_Back_ch").html("")
                }
            }
        });
    }

    $('#txtChMedicineName').keyup(function (even) {
        //TODO 临时去除
//        scanKey4();
    })


    if ($('#txtChMedicineName').val()) {
        $('#txtChMedicineName').focus();
        $('#txtChMedicineName').select();
    }
    function loadChinese(page) {
        var name = $('#txtChMedicineName').val();
        fn_LoadChineseMedicinePage(page, name);
    }
    function loadChineseOthers(page) {
        var name = $('#txtChMedicineName').val();
        fn_LoadChineseOtherMedicinePage(page, name);
    }
    $(function () {
        $('#txtChMedicineName').focus();
    })
</script>