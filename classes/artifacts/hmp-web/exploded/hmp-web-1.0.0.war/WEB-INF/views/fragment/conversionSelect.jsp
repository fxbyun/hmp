<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>

<div class="form-group" style="line-height: 33px;">
    <input type="hidden" id="hidConversionId" value="" />

    <label class="col-xs-3 col-sm-3 text-right text-success" style="margin-left: 25px;">换算单位</label>
    <label class="col-xs-1 col-sm-1 text-success"
           style="padding:0px;width: 75px;">1 ${empty medicineUnits[medicine.unit]?medicineUnits[medicine.useUnit]:medicineUnits[medicine.unit]}
        = </label>
<%--我的药品==换算单位区域--%>
<div class="col-xs-2 col-sm-2" style="margin-left: -1.5em;">
    <input type="number" min="1" class="form-control text-success" id="txtMedicineRate"
           value="${rate==null||rate==''?1:rate}">
</div>
<label class="col-xs-2 col-sm-2 text-success" style="margin-left: -1.5em;">${medicineUnits[unit]}</label>

<div class="col-xs-12 col-sm-12">
    <h5 id="lblConversionMsg" class="text-danger text-center">${msg}</h5>
</div>
</div>
<script>
    $(function () {
        //自动触发药品选择的确定按钮
        /*debugger
        if (isShowWindow) {
            $("#btnSubmitMedicine").trigger("click");
         }*/
    })
</script>