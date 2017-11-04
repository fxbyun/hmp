<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/8/13
  Time: 15:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>门诊主页</title>
    <script>
        $(function () {
            $("#reservation").addClass("active");
        })
    </script>
</head>
<body>
<div class="warp">
    <div class="out-detail text-center">
        <i class="fa fa-angle-left" onclick="javascript:history.go(-1)"></i>
        <span class="text-center">门诊主页</span>
    </div>
    <div class="out-detail-title" style="overflow:hidden; position: relative;">
        <c:if test="${empty imageWall}">
            <img class="float-left" src="${ctx}/assets/images/default.jpg" width="66" height="70" alt="门诊头像">
        </c:if>
        <c:if test="${not empty imageWall}">
            <img class="float-left" src="${ctx}/temp/${imageWall.fileName}" width="66" height="70" alt="门诊头像">
        </c:if>
        <div class="float-left" style="padding-left: 1.5em; width: 78%;">
            <dl>
                <dt>${clinicDoctor.outpatientService}</dt>
                <dd><i class="outpa-icon02 fa fa-map-marker"></i>${clinicDoctor.businessAddr}</dd>
            </dl>
        </div>
        <a href="${ctx}/outpatient/outClinicDetail?doctorId=${clinicDoctor.id}" class="look-detail">查看详情></a>
    </div>

    <div class="out-dateil-regist">
        <ul>
            <c:forEach items="${allClinicDoctorList}" var="doctor">
                <li onclick="<c:if
                        test="${doctor.appointStatus=='Open'}">javascript:window.location.href='${ctx}/outpatient/outAppointment?doctorId=${doctor.id}'</c:if>">
                    <a class="float-left" href="
                    <c:if test="${doctor.appointStatus=='Open'}">
                        ${ctx}/outpatient/outAppointment?doctorId=${doctor.id}
                    </c:if>

                    <c:if test="${doctor.appointStatus!='Open'}">
                        javascript:void(0)
                    </c:if>
                    ">
                        <c:if test="${empty headUrlMap[doctor]}">
                            <img class="float-left" src="${ctx}/temp/doctor.jpg" width="52" height="58" alt="医生头像">
                        </c:if>
                        <c:if test="${not empty headUrlMap[doctor]}">
                            <img class="float-left" src="${ctx}/temp/${headUrlMap[doctor]}" width="52" height="58"
                                 alt="医生头像">
                        </c:if>
                    </a>
                    <div class="float-left" style="padding-left: 0.5em; position: relative; width: 80%;">
                        <a href="#">${doctor.name}</a>
                        <p><i class="icon-06"></i><span>擅长：${doctor.specialty}</span></p>
                        <p><i class="icon-09"></i><span>从医年限：
                        <c:if test="${not empty doctor.seniority}">
                            ${doctor.seniority}年
                        </c:if>
                    </span></p>
                        <p><i class="icon-08"></i><span>月平均接诊数：${averPerNumsMap[doctor]}    <i
                                class="icon-07"></i>平均分：${averageMap[doctor]}</span></p>
                        <c:if test="${doctor.appointStatus=='Open'}">
                            <a href="${ctx}/outpatient/outAppointment?doctorId=${doctor.id}"
                               class="btn btn-default out-regist">预约</a>
                        </c:if>

                        <c:if test="${doctor.appointStatus!='Open'}">
                            <a href="javascript:void(0) " class="btn btn-default out-regist"
                               style="background-color: #808080">预约</a>
                        </c:if>

                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>

</div>
</body>
</html>
