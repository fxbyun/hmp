<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/8/18
  Time: 17:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>评价窗口</title>
    <link rel="stylesheet" href="${ctx}/assets/star/css/star-rating.min.css" type="text/css"/>
    <script src="${ctx}/assets/star/js/star-rating.min.js" type="text/javascript"></script>
    <style>
        .rating-xs {
            font-size: 1.5em;
        }

        .out-detail-title p {
            line-height: 1.3em;
        }

        .out-detail-title p span {
            color: #fff;
        }

    </style>
    <script>
        $(function () {
            $("#reservation").addClass("active");
        });

    </script>
</head>
<body>
<div class="warp">
    <div class="out-detail text-center">
        <i class="fa fa-angle-left" onclick="javascript:history.go(-1)"></i>
        <span class="text-center">评价窗口</span>
    </div>
    <div class="out-detail-title" style="overflow:hidden; background-color: #5cb531; margin-top: 1px; border:0;">
        <div class="float-left text-center">

            <c:if test="${empty headUrl}">
                <img class="doctor-tx" src="${ctx}/temp/doctor.jpg" width="60" height="60" alt="医生头像">
            </c:if>
            <c:if test="${not empty headUrl}">
                <img class="doctor-tx" src="${ctx}/temp/${headUrl}" width="60" height="60" alt="医生头像">
            </c:if>
            <div><a href="#" style="line-height: 2em; color: #fff;">${doctor.name}</a></div>
        </div>
        <div class="float-left" style="padding-left: 1.2em; width: 80%;">
            <p><span>从医年限：18年</span></p>
            <p><span>职业资格：${doctor.certificate}</span></p>
            <p><span>擅长：${doctor.specialty}</span></p>
        </div>
    </div>
    <%-- 此处为原来数据 供参考--%>
    <%--<div class="out-detail-title" style="overflow:hidden;">
        <c:if test="${empty headUrl}">
            <img class="float-left" src="${ctx}/temp/1466995166782.jpg" width="52" height="58" alt="医生头像">
        </c:if>
        <c:if test="${not empty headUrl}">
            <img class="float-left" src="${ctx}/temp/${headUrl}" width="52" height="58" alt="医生头像">
        </c:if>
        <div class="float-left" style="padding-left: 1.2em; width: 80%;">
            <a href="#" style="line-height: 2em;">${doctor.name}</a>
            <p><span>${doctor.outpatientService}</span></p>
            <p><span>擅长：${doctor.specialty}</span></p>
            <p><span>月平均接诊数：${averPerNums}人    平均分：${average}</span></p>
        </div>
    </div>--%>

    <%-- 此处为原来数据 供参考--%>
    <div class="con-evaluate">
        <ul>
            <c:forEach items="${evaluateList}" var="evaluate">
                <li href="#content1" role="button" data-toggle="collapse" aria-expanded="true">
                    <p><span>${evaluate.content}</span></p>
                    <div style="overflow:hidden;">
                        <div class="float-left" style="overflow: hidden;">
                            <span style="padding-right: 5px;">${evaluate.patientName}</span>
                            <input name="grade" disabled="disabled" value="${evaluate.grade*2}" type="number"
                                   class="rating" min=0 max=10 step=0.1 data-size="xs"
                                   style="display: inline-block; float: left;">
                        </div>
                        <div class="float-right"><span><fmt:formatDate value="${evaluate.createTime}" type="date" pattern="yyyy/MM/dd hh:mm"/></span></div>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>

</div>
</body>
</html>
