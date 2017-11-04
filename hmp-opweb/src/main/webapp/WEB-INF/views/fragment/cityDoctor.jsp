<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script>
    $("#divCityDoc #cityDoc").children().each(function () {
        $(this).click(function () {
            var cityTxt =  $(this).children().text();
            var index = cityTxt.indexOf("(")
            var city=cityTxt.substring(0,index);
            var url = "${ctx}/outpatient/clinicIndex?city=" + city;
            location.href=url;
        });
    })
</script>
<ul id="cityDoc" class="nav">
    <c:forEach items="${cityDocList}" var="cityDoc">
        <li><a>${cityDoc.city}(${cityDoc.total})</a></li>
    </c:forEach>
</ul>
