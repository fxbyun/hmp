<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<style>
   .select-box{
       background-color: #fff;
       border-radius: 10px;
   }
   div#divMedicineUsage{
       background-color: #fff;
       border-radius: 10px;
       padding: 10px 10px;
       margin-bottom: 10px;
   }
</style>
<div class="select-box">
    <c:forEach var="m" varStatus="status" items="${mpList}">
        <label class="btn btn-default">
            <input type="radio" name="medicineId" value="${m.id}" <c:if test="${status.first}"></c:if>/> ${m.name}
        </label>
    </c:forEach>
    <div class="text-center">
        <c:if test="${medicinePage.number > 0}">
            <button type="button" onclick="fn_LoadMedicineList(${medicinePage.number - 1})" class="btn btn-success"><i class="fa fa-chevron-left"></i> 上一页</button>
        </c:if>
        <c:if test="${medicinePage.number + 1 < medicinePage.totalPages}">
            <button type="button" onclick="fn_LoadMedicineList(${medicinePage.number + 1})" class="btn btn-success"><i class="fa fa-chevron-right"></i> 下一页</button>
        </c:if>
        &nbsp;&nbsp;&nbsp;&nbsp;
        第&nbsp;${medicinePage.number + 1}&nbsp;页/共&nbsp;${medicinePage.totalPages}&nbsp;页
    </div>
</div>
<div id="divMedicineUsage"></div>
<script type="text/javascript">
    $(function(){
        $('input[name="medicineId"]').change(function () {
            var mId = $('input[name="medicineId"]:checked').val();
            $('#divMedicineUsage').load('/fragment/myMedicine/usage/' + mId);
        });
//        $('input[name="medicineId"]').change();
        firstChange();
    });
    function firstChange() {
        var mId = $('input[name="medicineId"]:checked').val();
        $('#divMedicineUsage').load('/fragment/myMedicine/usage/' + mId);
    }
</script>