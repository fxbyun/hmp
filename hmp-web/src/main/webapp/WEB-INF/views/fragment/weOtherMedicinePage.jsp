<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<div class="tab-box-list west-boxs">
    <c:forEach var="med" items="${medicinePage.content}">
        <span class="tag" onclick="fn_ShowSelectMedicine(${med.id})">${med.name}</span>
    </c:forEach>
</div>
<div class="form-group btn-boxs">
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
    <button type="button" onclick="loadWesterns(0)" class="btn btn-default hide"> 常用药品</button>
    <label class="btn btn-default hide">药品库</label>

    <div class="up-dpwn-bt">

        <c:if test="${medicinePage.number > 0}">
            <button type="button" onclick="loadWesternOthers(${medicinePage.number - 1})" class="btn btn-info"> 上一批</button>
        </c:if>
        <c:if test="${medicinePage.number + 1 < medicinePage.totalPages}">
            <button type="button" onclick="loadWesternOthers(${medicinePage.number + 1})" class="btn btn-info">下一批 </button>
        </c:if>
        <div class="up-dpwn-bt">

                <div class="input-group">
                    <input id="txtWeMedicineName" value="${name}" type="text" class="form-control" placeholder="药品名称">
                    <span class="input-group-btn">
                        <button onclick="fn_LoadWesternOtherMedicinePage()" class="btn btn-primary" type="button"><i class="fa fa-times"></i></button>
                        <button onclick="loadWesternOthers(0)" class="btn btn-primary" type="button"
                                style="border-left: 1px solid #fff;"><i class="fa fa-search"></i></button>
                    </span>
                </div>


            <button onclick="fn_AddMedicine('Western');" type="button" class="btn btn-success"><i class="fa fa-plus"></i> 添加药品</button>
        </div>
    </div>
</div>
<script type="text/javascript">
    $('#txtWeMedicineName').keydown(function (event) {
        if (event.keyCode == 13) {
            loadWesternOthers(0);
            return false;
        }
    });
    if ($('#txtWeMedicineName').val()) {
        $('#txtWeMedicineName').focus();
        $('#txtWeMedicineName').select();
    }
    function loadWesterns(page) {
        var name = $('#txtWeMedicineName').val();
        fn_LoadWesternMedicinePage(page, name);
    }
    function loadWesternOthers(page) {
        var name = $('#txtWeMedicineName').val();
        fn_LoadWesternOtherMedicinePage(page, name);
    }
</script>