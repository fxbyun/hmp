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
        <ul class="nav wx-out-span" role="tablist">
            <li class="active">
                <a href="#tabone" aria-controls="profile" data-toggle="tab">找诊所</a>
            </li>
            <li><span style="line-height: 2.3em; padding: 0 0.8em;">|</span></li>
            <li>
                <a href="#tabtwo" aria-controls="profile" data-toggle="tab">找医生</a>
            </li>
        </ul>

    </div>

    <div class="wx-out-conter tab-content">
        <%-- 门诊列表 --%>
        <div role="tabpanel" class="tab-pane active" id="tabone">
            <div class="wx-out-seach">
                <input name="outName" id="txtOutName" type="text" class="form-control" placeholder="搜索诊所">
                <i class="fa fa-search" id="btnOutName"></i>
            </div>
            <ul>
                <%--最近所去的诊所--%>
                <c:if test="${not empty lastDoctor}">
                    <li class="active"
                        onclick="javascript:location.href='${ctx}/outpatient/outLocationDetail?doctorId=${lastDoctor.id}'">
                            <%--医生头像--%>
                        <a href="${ctx}/outpatient/outLocationDetail?doctorId=${lastDoctor.id}" class="float-left">
                            <c:if test="${empty zhenSuoHeadMap[lastDoctor.id]}">
                                <img src="${ctx}/assets/images/default.jpg" width="50" height="52" alt="门诊头像">
                            </c:if>
                            <c:if test="${not empty zhenSuoHeadMap[lastDoctor.id]}">
                                <img src="${ctx}/temp/${zhenSuoHeadMap[lastDoctor.id]}" width="50" height="52" alt="门诊头像">
                            </c:if>
                        </a>

                        <div class="wx-out-info float-left">
                            <a href="${ctx}/outpatient/outLocationDetail?doctorId=${lastDoctor.id}">${lastDoctor.outpatientService}</a>
                            <div><i class="outpa-icon02 fa fa-map-marker"></i><span>${lastDoctor.businessAddr}</span>
                            </div>
                        </div>
                        <span class="float-right out-ahref">最近去过</span>
                    </li>
                </c:if>

                <%--搜索的所有医生信息--%>
                <c:forEach items="${doctorPage.content}" var="doctor" varStatus="status">
                    <li onclick="javascript:location.href='${ctx}/outpatient/outLocationDetail?doctorId=${doctor.id}'">
                        <a href="${ctx}/outpatient/outLocationDetail?doctorId=${doctor.id}" class="float-left">
                         <c:if test="${empty zhenSuoHeadMap[doctor.id]}">
                             <img src="${ctx}/assets/images/default.jpg" width="50" height="52" alt="门诊头像">
                         </c:if>
                         <c:if test="${not empty zhenSuoHeadMap[doctor.id]}">
                             <img src="${ctx}/temp/${zhenSuoHeadMap[doctor.id]}" width="50" height="52" alt="门诊头像">
                         </c:if>
                        </a>
                        <div class="wx-out-info float-left">
                            <a href="${ctx}/outpatient/outLocationDetail?doctorId=${doctor.id}">${doctor.outpatientService}</a>
                            <div><i class="outpa-icon02 fa fa-map-marker"></i><span>${doctor.businessAddr}</span></div>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <%-- 门诊列表 END --%>

        <%-- 医生列表 --%>
        <div role="tabpanel" class="tab-pane" id="tabtwo">
            <div class="wx-outpel">
                <div class="wx-out-seach">
                    <input name="doctorName" id="txtDoctorName" type="text" class="form-control" placeholder="搜索医生">
                    <i class="fa fa-search" id="btnDocName"></i>
                </div>
                <%--<div class="float-right">
                    <span style="font-size: 1.3rem;">排序</span>
                    <select class="form-control">
                        <option>接诊数</option>
                        <option>平均分</option>
                    </select>
                </div>--%>
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
                            <div><i class="outpa-icon02 fa fa-map-marker"></i><span>${lastDoctor.outpatientService}</span>
                            </div>
                        </div>
                        <span class="float-right out-ahref">最近去过</span>
                    </li>
                </c:if>

                <%--找医生：搜索的所有医生信息--%>
                <c:forEach items="${doctorPage.content}" var="doctor" varStatus="status">
                    <li onclick="javascript:location.href='${ctx}/outpatient/outLocationDetail?doctorId=${doctor.id}'">
                        <a href="${ctx}/outpatient/outLocationDetail" class="float-left">
                            <c:if test="${empty docHeadUrl[doctor.id]}">
                                <img src="${ctx}/temp/doctor.jpg" width="50" height="52" alt="门诊头像">
                            </c:if>
                            <c:if test="${not empty docHeadUrl[doctor.id]}">
                                <img src="${ctx}/temp/${docHeadUrl[doctor.id]}" width="50" height="52" alt="门诊头像">
                            </c:if>
                        </a>
                        <div class="wx-out-info float-left">
                            <a href="${ctx}/outpatient/outLocationDetail?doctorId=${doctor.id}">${doctor.name}</a>
                            <div><i class="outpa-icon02 fa fa-map-marker"></i><span>${doctor.outpatientService}</span></div>

                        </div>
                    </li>


                    <%--<li onclick="javascript:location.href='${ctx}/outpatient/outLocationDetail?doctorId=${doctor.id}'">
                        <a href="${ctx}/outpatient/outLocationDetail" class="float-left">
                            <c:if test="${empty docHeadUrl[doctor.id]}">
                                <img src="${ctx}/temp/doctor.jpg" style="margin: 0.8em 0;" width="50" height="52" alt="医生头像">
                            </c:if>
                            <c:if test="${not empty docHeadUrl[doctor.id]}">
                                <img src="${ctx}/temp/${docHeadUrl[doctor.id]}" style="margin: 0.8em 0;" width="50" height="52" alt="医生头像">
                            </c:if>
                        </a>
                        <div class="wx-pel-info float-left">
                            <a href="${ctx}/outpatient/outLocationDetail?doctorId=${doctor.id}" style="line-height: 1.8em;">${doctor.name}</a>
                            <div><span>门诊：${doctor.businessAddr}</span></div>
                            <div><span>擅长：${doctor.specialty}</span></div>
                        </div>
                    </li>--%>
                </c:forEach>

            </ul>

        </div>
        <%-- 医生列表 END --%>
    </div>

</div>
<script>
    $(function () {
        $("#btnOutName").click(function(){
            var outName=$("#txtOutName").val();
            window.location.href = "${ctx}/outpatient/clinicIndex?outName=" + outName;
        });

        $("#btnDocName").click(function(){
            var doctorName=$("#txtDoctorName").val();
            window.location.href = "${ctx}/outpatient/doctorIndex?doctorName=" + doctorName;
        });

    })
</script>
</body>
</html>
