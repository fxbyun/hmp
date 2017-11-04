<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: cih
  Date: 16/8/9
  Time: 下午2:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%--患者主诉--%>


<c:forEach items="${symptomPage.content}" var="oneSy">
    <span class="tag" onclick="addSymptomTagToDiv('${oneSy.id}','${oneSy.name}')">${oneSy.name}</span>
</c:forEach>


<div class="form-group text-center pre-btn pre-up-down">

    <c:choose>
        <c:when test="${symptomPage.number > 0}">
            <button type="button" class="btn btn-success" onclick="loadMobileSymptoms('${symptomPage.number-1}')">上一页
            </button>
        </c:when>
        <c:otherwise>
            <button type="button" class="btn btn-default">上一页</button>
        </c:otherwise>
    </c:choose>

    <c:choose>
        <c:when test="${symptomPage.number + 1 < symptomPage.totalPages}">
            <button type="button" class="btn btn-success" onclick="loadMobileSymptoms('${symptomPage.number+1}')">下一页
            </button>
        </c:when>
        <c:otherwise>
            <button type="button" class="btn btn-default">下一页</button>
        </c:otherwise>
    </c:choose>

</div>


