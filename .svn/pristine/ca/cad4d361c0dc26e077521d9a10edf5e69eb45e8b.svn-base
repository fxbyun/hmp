<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>门诊</title>
    <script>
        $(function () {

            <c:if test="${not empty flag}">
            $(".tab-pane").eq(0).removeClass("active")
            $(".tab-pane").eq(1).addClass("active")
            </c:if>

            $("#reservation").addClass("active");
        })
    </script>
</head>
<body>
<div class="warp">
    <div class="form-group wx-outpatient">
        <a href="${ctx}/outpatient/outLocation" class="text-left" style="float: left;">
            <i class="fa fa-map-marker outpa-icon"></i>
            <span>${cityName}</span>
        </a>
        <ul class="nav wx-out-span">
            <li>
                <a href="${ctx}/outpatient/clinicIndex">找诊所</a>
            </li>
            <li><span style="line-height: 2.3em; padding: 0 0.8em;">|</span></li>
            <li>
                <a href="${ctx}/outpatient/doctorIndex">找医生</a>
            </li>
        </ul>

    </div>

    <div class="wx-out-conter tab-content">
        <%-- 医生列表 --%>
        <div role="tabpanel" class="tab-pane active" id="tabtwo">
            <div class="wx-outpel">
                <div class="wx-out-seach">
                    <input name="doctorName" id="txtDoctorName" type="text" class="form-control" placeholder="搜索医生">
                    <i class="fa fa-search" id="btnDocName"></i>
                </div>
            </div>
            <ul>
                <%--找医生:最近去过--%>
                <c:if test="${not empty lastDoctor}">
                    <li class="active"
                        onclick="javascript:location.href='${ctx}/outpatient/outLocationDetail?doctorId=${lastDoctor.id}'">
                            <%--医生头像--%>
                        <a href="${ctx}/outpatient/outLocationDetail?doctorId=${lastDoctor.id}" class="float-left">
                            <c:if test="${empty docHeadUrl[lastDoctor.id]}">
                                <img src="${ctx}/temp/doctor.jpg" width="50" height="52" alt="门诊头像">
                            </c:if>
                            <c:if test="${not empty docHeadUrl[lastDoctor.id]}">
                                <img src="${ctx}/temp/${docHeadUrl[lastDoctor.id]}" width="50" height="52" alt="门诊头像">
                            </c:if>
                        </a>

                        <div class="wx-out-info float-left">
                            <a href="${ctx}/outpatient/outLocationDetail?doctorId=${lastDoctor.id}">${lastDoctor.name}</a>
                            <div>
                                <i class="outpa-icon02 fa fa-map-marker"></i><span>${lastDoctor.outpatientService}</span>
                            </div>
                        </div>
                        <span class="float-right out-ahref">最近去过</span>
                    </li>
                </c:if>

                <%--找医生：搜索的所有医生信息--%>
                <c:forEach items="${doctorPage.content}" var="doctor" varStatus="status">
                    <li onclick="javascript:location.href='${ctx}/outpatient/outLocationDetail?doctorId=${doctor.id}'">
                        <a href="${ctx}/outpatient/outLocationDetail?doctorId=${doctor.id}" class="float-left">
                            <c:if test="${empty docHeadUrl[doctor.id]}">
                                <img src="${ctx}/temp/doctor.jpg" width="50" height="52" alt="门诊头像">
                            </c:if>
                            <c:if test="${not empty docHeadUrl[doctor.id]}">
                                <img src="${ctx}/temp/${docHeadUrl[doctor.id]}" width="50" height="52" alt="门诊头像">
                            </c:if>
                        </a>
                        <div class="wx-out-info float-left">
                            <a href="${ctx}/outpatient/outLocationDetail?doctorId=${doctor.id}">${doctor.name}</a>
                            <div><i class="outpa-icon02 fa fa-map-marker"></i><span>${doctor.outpatientService}</span>
                            </div>

                        </div>
                    </li>

                </c:forEach>

            </ul>

        </div>
        <%-- 医生列表 END --%>
    </div>

</div>
<script>
    $(function () {
        $("#btnOutName").click(function () {
            var outName = $("#txtOutName").val();
            window.location.href = "${ctx}/outpatient/doctorIndex?outName=" + outName;
        });

        $("#btnDocName").click(function () {
            var doctorName = $("#txtDoctorName").val();
            window.location.href = "${ctx}/outpatient/doctorIndex?doctorName=" + doctorName;
        });

    })
</script>
</body>
</html>
