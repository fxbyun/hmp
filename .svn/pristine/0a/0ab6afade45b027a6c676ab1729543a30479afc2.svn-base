<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<form:form action="/medicine/saveUsage" id="frmMedicineUsage" cssClass="form-horizontal" method="post"
           modelAttribute="medicine">
    <form:hidden path="id" />
    <div style="overflow:hidden;">
        <div style="float:left; width: 35%;">
            <form:select path="useTimes" cssClass="form-control" items="${medicineUseTimes}" />
        </div>
        <div class="text-right" style="float:right; width: 60%;">
            <span>每次</span>
            <form:input path="useQty" cssStyle="display: inline-block; width: 25%;" cssClass="form-control" />
            <form:select path="useUnit" cssClass="form-control" items="${medicineUnits}"
                         cssStyle="display: inline-block; width: 45%;" />
        </div>
    </div>
    <div style="width: 35%; margin-top: 1rem;">

        <form:select path="usingTime" id="txtUsingTime" cssClass="form-control" cssStyle="display: inline-block;"
                     items="${medicineUsingTimes}" />
    </div>

</form:form>
