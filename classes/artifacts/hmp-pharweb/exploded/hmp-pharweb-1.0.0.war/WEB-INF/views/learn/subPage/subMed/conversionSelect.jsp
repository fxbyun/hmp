<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<div class="form-group" style="line-height: 33px;">

    <%--换算单位:--%>


    <label>
        换算单位:
        <span>1 ${medicineUnits[medicine.unit]} = </span>
        <input type="text" class="form-control text-center"
               style="display: inline-block; width: 15%; margin: 0 0.8rem;"
               value="${conversion.rate}">
        <span>${medicineUnits[unit]}</span>
    </label>

</div>
