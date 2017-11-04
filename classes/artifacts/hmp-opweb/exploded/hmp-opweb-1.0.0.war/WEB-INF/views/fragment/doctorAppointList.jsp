<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/9/14 0014
  Time: 9:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<div class="out-treatment-share"></div>
<div class="out-times">
    <div class="text-center out-times-title"><span>请选择就诊时间段</span></div>
    <ul class="out-time-detail">
        <c:forEach items="${appointLists}" var="appointList">
            <li onclick="javascript:location.href='${ctx}/outpatient/outReservationInfo?doctorId=${doctor.id}&appListId=${appointList.id}'">
                <div style="overflow: hidden; line-height: 3em;">
                    <span class="float-left" style="width: 42%"><fmt:formatDate value="${appointList.startTime}" pattern="hh:mm" />-<fmt:formatDate value="${appointList.endTime}" pattern="hh:mm" /></span>
                    <span class="float-left" style="width: 30%">
                        <c:choose>
                            <c:when test="${appointList.peopleNum<appointList.configPeopleNum}">
                                可预约${appointList.peopleNum-appointList.configPeopleNum}
                            </c:when>
                            <c:otherwise>
                                约满${appointList.peopleNum-appointList.configPeopleNum}
                            </c:otherwise>
                        </c:choose>

                    </span>
                    <span class="float-left"
                          style="width: 18%">余${appointList.configPeopleNum-appointList.peopleNum}</span>
                    <i style="width: 10%; font-size: 2.2rem; color: #868686; line-height: 1.8em;"
                       class="fa fa-angle-right float-right text-center"></i>
                </div>
            </li>
        </c:forEach>

    </ul>
</div>
<script>
    $(function () {
        $(".out-treatment-share").click(function () {
            $(".out-treatment-time").hide(1000);
        })
    })
</script>