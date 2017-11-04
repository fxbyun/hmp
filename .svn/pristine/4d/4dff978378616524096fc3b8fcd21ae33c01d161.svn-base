<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/9/29
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>门诊详情</title>
    <script>
        $(function () {
            $("#reservation").addClass("active");
            $("#show-all").click(function () {
                $(".content-some").hide();
                $(".content-all").show();
            });
            $("#hide-all").click(function () {
                $(".content-all").hide();
                $(".content-some").show();
            });
        });
    </script>
</head>
<body>
<div class="warp">
    <div class="out-detail text-center">
        <i class="fa fa-angle-left" onclick="javascript:history.go(-1)"></i>
        <span class="text-center">门诊详情</span>
    </div>
    <div class="out-detail-title" style="overflow:hidden; position: relative;">
        <c:if test="${empty clinicHeadUrl}">
            <img class="float-left" src="${ctx}/assets/images/default.jpg" width="66" height="70" alt="门诊头像">
        </c:if>
        <c:if test="${not empty clinicHeadUrl}">
            <img class="float-left" src="${ctx}/temp/${clinicHeadUrl}" width="66" height="70" alt="门诊头像">
        </c:if>
        <div class="float-left" style="padding-left: 1.5em;">
            <dl>
                <dt>${doctor.outpatientService}</dt>
                <dd><i class="outpa-icon02 fa fa-map-marker"></i>${doctor.businessAddr}</dd>
            </dl>
        </div>
    </div>
    <div>
        <h4 style="margin-left: 1em;">简介</h4>
        <div class="content-some">
            <span>
                ${doctor.clinicInfo}
            </span>
            <div class="text-right"><a href="javascript:;" id="show-all" class="text-right"
                                       style="display: block;font-size: 1.3rem; margin-right: 5%;">查看全部</a></div>
        </div>
        <div class="content-all">
            <span>
                ${doctor.clinicInfo}
            </span>
            <div class="text-center">--------------------&nbsp;&nbsp;<a href="javascript:;" id="hide-all">点击收起</a>&nbsp;&nbsp;--------------------
            </div>
        </div>
        <div class="show-clinic-img">
            <c:if test="${not empty imageUrlList}">
                <c:forEach items="${imageUrlList}" var="image">

                    <img src="${ctx}/temp/${image}" width="150" height="150" alt="诊所图片">
                </c:forEach>
            </c:if>

            <c:if test="${empty imageUrlList}">
                <img src="${ctx}/assets/images/default.jpg" width="150" height="150" alt="诊所图片">
                <img src="${ctx}/assets/images/default.jpg" width="150" height="150" alt="诊所图片">
                <img src="${ctx}/assets/images/default.jpg" width="150" height="150" alt="诊所图片">
                <img src="${ctx}/assets/images/default.jpg" width="150" height="150" alt="诊所图片">
            </c:if>

        </div>
    </div>

</div>

</body>
</html>
