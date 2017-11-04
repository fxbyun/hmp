<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<div class="tab-box-list first-box-list">
    <c:forEach var="med" items="${medicinePage.content}">
        <span class="tag" onclick="fn_ShowSelectMedicine(${med.id})">${med.name}</span>
    </c:forEach>
</div>
<div class="form-group btn-boxs">
    <button type="button" onclick="fn_ShowMyMedicine('Chinese')"
            class="btn btn-default"> 我的药品
    </button>
    &nbsp;&nbsp;
    <button type="button" onclick="fn_ShowSelectRp('Chinese')" style="margin-right:0px;margin-left:-18px;"
            class="btn btn-default"> 我的药方
    </button>
    &nbsp;&nbsp;
    <button type="button" onclick="loadChinese(0)" class="btn btn-default hide"> 常用药品</button>
    <label class="btn btn-default hide">药品库</label>

    <div class="up-dpwn-bt">

        <c:if test="${medicinePage.number > 0}">
            <button type="button" onclick="loadChineseOthers(${medicinePage.number - 1})" class="btn btn-info"> 上一批</button>
        </c:if>
        <c:if test="${medicinePage.number + 1 < medicinePage.totalPages}">
            <button type="button" onclick="loadChineseOthers(${medicinePage.number + 1})" class="btn btn-info">下一批 </button>
        </c:if>
        <div class="pull-right">

                <div class="input-group">
                    <input id="txtChMedicineName" value="${name}" type="text" class="form-control" placeholder="药品名称">
                    <span class="input-group-btn">
                        <button onclick="fn_LoadChineseOtherMedicinePage()" class="btn btn-primary" type="button"><i class="fa fa-times"></i></button>
                        <button onclick="loadChineseOthers(0)" class="btn btn-primary" type="button"
                                style="border-left: 1px solid #fff;"><i class="fa fa-search"></i></button>
                    </span>
                </div>


            <button onclick="fn_AddMedicine('Chinese');" type="button" class="btn btn-success"><i class="fa fa-plus"></i> 添加药品</button>
        </div>
    </div>
</div>
<script type="text/javascript">
    $('#txtChMedicineName').keydown(function (event) {
        if (event.keyCode == 13) {
            loadChineseOthers(0);
            return false;
        }
    });
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
</script>