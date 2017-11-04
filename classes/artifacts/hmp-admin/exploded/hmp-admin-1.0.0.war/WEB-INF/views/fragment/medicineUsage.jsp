<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springside.org.cn/tags/form" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<form:form action="/medicine/saveUsage" id="frmMedicineUsage" cssClass="form-horizontal" method="post"
           modelAttribute="medicine">
    <form:hidden path="id"/>
    <form:select path="useTimes" cssStyle="width:100px" items="${medicineUseTimes}"/> &nbsp;&nbsp;
    <form:select path="usingTime" id="txtUsingTime" cssStyle="width:100px" items="${medicineUsingTimes}"/>&nbsp;&nbsp;
    <%--<button id="btnSaveMedicineUsage" type="button" class="btn btn-success">更新用量</button>--%>
</form:form>
<script type="text/javascript">
    $('#btnSaveMedicineUsage').click(function () {
        $.postJSON("${ctx}/medicine/saveUsage", $('#frmMedicineUsage').serializeArray(), function (result) {
            if (result.success) {
                layer.alert(result.msg);
            }else{
                layer.alert(reult.msg);
            }
        })
    });
</script>