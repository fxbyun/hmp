<%--
  Created by IntelliJ IDEA.
  User: cih
  Date: 16/8/9
  Time: 下午2:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--录入药方 药方对应的诊状--%>

<c:forEach items="${diagnosisPage.content}" var="oneDia">
    <span class="tag" onclick="addDiagnosisTagToDiv('${oneDia.id}','${oneDia.name}')">${oneDia.name}</span>
</c:forEach>

<div class="form-group text-center pre-btn pre-up-down">
    <c:choose>
        <c:when test="${diagnosisPage.number > 0}">
            <button type="button" class="btn btn-success" onclick="loadMobileDiagnosisTag('${diagnosisPage.number-1}')">
                上一页
            </button>
        </c:when>
        <c:otherwise>
            <button type="button" class="btn btn-default">上一页</button>
        </c:otherwise>
    </c:choose>

    <c:choose>
        <c:when test="${diagnosisPage.number + 1 < diagnosisPage.totalPages}">
            <button type="button" class="btn btn-success" onclick="loadMobileDiagnosisTag('${diagnosisPage.number+1}')">
                下一页
            </button>
        </c:when>
        <c:otherwise>
            <button type="button" class="btn btn-default">下一页</button>
        </c:otherwise>
    </c:choose>
</div>