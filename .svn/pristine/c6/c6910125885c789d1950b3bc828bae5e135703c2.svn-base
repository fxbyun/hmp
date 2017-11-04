<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/10/11 0011
  Time: 10:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<p style="font-size: 1.3em;">未签到患者：</p>
<div class="outpatient-tab">
    <c:forEach items="${list.content}" var="oneWx">
        <button type="button"
                onclick="setWxSingIn('${oneWx.id}','${oneWx.patientName}')"
                class="btn btn-success">
            <span>${oneWx.patientName}</span></br>
            <fmt:formatDate value="${oneWx.createOn}" type="date"
                            pattern="HH:mm"/>--
            <fmt:formatDate value="${oneWx.completeOn}" type="date" pattern="HH:mm"/>
        </button>
    </c:forEach>

</div>
<p class="text-right" style="margin-top: 10px;">
    <c:if test="${list.number > 0}">
        <button type="button" onclick="loadWxPage(${list.number - 1})" class="btn btn-success">上一页</button>
    </c:if>
    <c:if test="${list.number + 1 < list.totalPages}">
        <button type="button" onclick="loadWxPage(${list.number + 1})" class="btn btn-success">下一页</button>
    </c:if>
</p>

