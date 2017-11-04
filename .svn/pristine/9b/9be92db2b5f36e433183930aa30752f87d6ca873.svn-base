<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<form:form action="/medicine/saveUsage" id="frmMedicineUsage" cssClass="form-horizontal" method="post"
           modelAttribute="medicine">
    <form:hidden path="id"/>
    <input type="hidden" value="2" name="tmpErrorId"/>
    <div class="form-group">

        <div class="col-xs-3 col-sm-3" style="padding-right: 0;">
            <form:select path="useTimes" cssClass="form-control" items="${medicineUseTimes}"/>
        </div>
        <label class="col-xs-2 col-sm-1 text-center" style="line-height: 32px; padding: 0;">每次</label>

        <div class="col-xs-2 col-sm-2" style="padding-left: 0px; margin-left: -15px;">
            <form:input path="useQty" type="number" cssClass="form-control text-center"/>
        </div>
        <div class="col-xs-2 col-sm-2" style="padding: 0;">
            <form:select path="useUnit" cssClass="form-control" items="${medicineUnits}" />
        </div>
        <div class="col-xs-3 col-sm-3" style="padding-right: 0;">
            <form:select path="usingTime" id="txtUsingTime" cssClass="form-control" items="${medicineUsingTimes}" />
        </div>
        <div class="col-xs-2 col-sm-2 text-center" style="margin-top: 20px; display: none;">
            <button id="btnSaveMedicineUsage" type="button" class="btn btn-success">更新用量</button>
        </div>

    </div>
</form:form>
<script type="text/javascript">
    $('#btnSaveMedicineUsage').click(function () {
        $.postJSON("/medicine/saveUsage", $('#frmMedicineUsage').serializeArray(), function (result) {
            if (result.success) {
                layer.alert(result.msg);
            }else{
                layer.alert(reult.msg);
            }
        })
    });
</script>