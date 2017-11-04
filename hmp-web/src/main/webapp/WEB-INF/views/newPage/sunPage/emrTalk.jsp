<%@ include file="/WEB-INF/commons/include.jsp" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/6/4 0004
  Time: 14:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    $(function () {
        var content = $(".col-md-12 .col-sm-12").text();
        if (content == null || content == "") {
            $("#ptDetail").hide();
        } else {
            $("#ptDetail").show();
        }
    });
</script>
<c:if test="${not empty emr.evaluateList}">
    <div class="col-md-12 col-sm-12" style="color: #333;">
        <div style="font-size: 18px;" class="btn-boxs">
            <span>
                <fmt:formatDate value="${emr.createOn}" pattern="yyyy/MM/dd HH:mm" />
            </span>
            <span>你好：</span> <span>${emr.diagnosisResult}</span>
                <%--<a href="#" class="btn btn-default pull-right">病历详情</a>--%>
        </div>

        <div class="review-item-content">

            <c:forEach items="${emr.evaluateList}" var="eva">
                <c:choose>
                    <c:when test="${eva.type eq 'ELE'}">
                        <div class="col-md-9 col-sm-9">
                            <span>评星：${eva.grade}星</span>
                            <span class="text-left"><h4 style="font-size:18px;">评价：${eva.content}</h4></span>

                        </div>
                        <div class="col-md-3 col-sm-3 text-right">
                            <br>
                            <span><fmt:formatDate value="${eva.createTime}" pattern="yyyy/MM/dd HH:mm" /></span>
                        </div>
                    </c:when>

                    <c:when test="${eva.type eq 'DTO'}">
                        <div class="review-content col-md-9 col-sm-9">
                            <p class="text-left"><i></i><span>我</span>回复<span>${emr.patientName}</span>：${eva.content}</p>
                        </div>
                        <div class="col-md-3 col-sm-3 text-right">
                            <span><fmt:formatDate value="${eva.createTime}" type="date" pattern="yyyy/MM/dd h:m" /></span>
                        </div>
                    </c:when>
                    <c:when test="${eva.type eq 'OTD'}">
                        <div class="review-content col-md-9 col-sm-9">
                            <p class="text-left"><i></i><span>${emr.patientName}</span>回复<span>我</span>：${eva.content}</p>
                        </div>
                        <div class="col-md-3 col-sm-3 text-right">
                            <span><fmt:formatDate value="${eva.createTime}" type="date" pattern="yyyy/MM/dd h:m" /></span>
                        </div>
                    </c:when>
                </c:choose>
            </c:forEach>


                <%--<div class="review-content col-md-9 col-sm-9">--%>
                <%--<p class="text-left"><i></i><span>我</span>回复<span>你好</span>：好的</p>--%>
                <%--</div>--%>
                <%--<div class="col-md-3 col-sm-3 text-right">--%>
                <%--<span>2016/05/25 01:40</span>--%>
                <%--</div>--%>


            <div class="form-group">
                <form class="form-inline" action="${ctx}/emrEval" method="post">
                    <input type="hidden" name="emrId" value="${emr.id}" />
                    <input type="hidden" name="patientUid" value="${emr.patientUid}" />
                    <input type="hidden" name="patientId" value="${emr.patient.id}" />
                    <input type="hidden" name="patientName" value="${emr.patientName}" />
                    <input type="hidden" name="doctorId" value="${emr.doctor.id}" />
                    <input type="hidden" name="doctorName" value="${emr.doctorName}" />
                    <input type="text" class="form-control" name="content" style="width: 89%" id="content${emr.id}" placeholder="回复" />
                    <button class="btn pull-right" type="submit">回复</button>
                </form>
            </div>
        </div>
    </div>
</c:if>
