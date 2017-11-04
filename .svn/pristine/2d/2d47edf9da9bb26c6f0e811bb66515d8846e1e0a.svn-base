<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/8/17
  Time: 14:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>预约信息确认</title>
    <script>
        var patientId=null;
        var doctorId = ${doctor.id};
        var appointListId = ${appointList.id}

        $(function () {
            $("#reservation").addClass("active");

            patientId = $("#selPatient").find("option:selected").attr("id");
            var url = "${ctx}/consultation/conArchives?doctorId={0}&appointListId={1}".format(doctorId, appointListId);
            $("#editInfo").attr("href",url);
            //选择病人时事件
            $("#selPatient").change(function () {
                patientId = $("#selPatient").find("option:selected").attr("id");
                //var url = "/consultation/conArchives?patientId="+patientId;
                //$("#editInfo").attr("href",url);
            });

            //点击保存
            $("#btnSave").click(function () {

                if ($("#selPatient").children().size() <= 0) {
                    layer.alert("该用户还没有绑定过任何患者信息，请选择在线建档！");
                }

                var url = "${ctx}/outpatient/outReward"
                $.postJSON(url,{"patientId":patientId,"appointListId":appointListId},function (data) {
                    if(data.success==true){
                        layer.msg("预定成功！");
                        var appPatientId = data.data;
                        $("#appPatientId").val(appPatientId);
                        $("form").submit();
                    }else{
                        layer.msg(data.msg)
                    }

                })
            })
            //在线建档后，选中填写完的病患
            <c:if test="${not empty patientEdit}">
            patientId = ${patientEdit.id}
                    $("#selPatient #" + patientId).attr("selected", "selected");
            </c:if>



            
        })
    </script>
</head>
<body>
<div class="warp">
    <div class="out-detail text-center">
        <i class="fa fa-angle-left" onclick="javascript:history.go(-1)"></i>
        <span class="text-center">预约确认</span>
    </div>
    <div class="out-detail-title">
        <div style="overflow:hidden;">
            <span style="color:#333; float:left; padding-right: 9px; line-height: 2.1em;">就诊人</span>

            <select id="selPatient" class="form-control" style="display: inline-block; width: 80%; float:left;">
                <c:forEach items="${patientList}" var="patient">
                    <option id="${patient.id}">${patient.name}&nbsp;&nbsp;
                        <c:if test="${patient.gender.toString()=='Male'}">
                            男&nbsp;&nbsp;
                        </c:if>
                        <c:if test="${patient.gender.toString()=='Female'}">
                            女&nbsp;&nbsp;
                        </c:if>
                        ${patient.age}
                    </option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="out-detail-title">
        <div style="overflow:hidden;">
            <span class="float-left" style="color:#333;">在线建档</span>
            <a id="editInfo" href="" class="float-right"><span
                    style="font-size: 1.5rem; color: #AEAEAE; padding: 0.5em;">无健康卡</span><i class="fa fa-angle-right"
                                                                                             style="font-size: 2.5rem; vertical-align: middle;"></i></a>
        </div>
    </div>
    <div class="out-detail-title">
        <div class="res-detail">
            <p><span>就医门诊</span><span>${doctor.outpatientService}</span></p>
            <p><span>就诊医生</span><span>${doctor.name}</span></p>
            <p><span>预约时间</span><span><fmt:formatDate value="${appointList.date}" pattern="yyyy-MM-dd" /></span></p>
            <p><span>预约时段</span><span><fmt:formatDate value="${appointList.startTime}" pattern="HH:mm" />-<fmt:formatDate value="${appointList.endTime}" pattern="HH:mm" /></span></p>
        </div>
    </div>

    <div style="display: none">
        <form action="${ctx}/wxtest/toReward/test" method="post">
            <input id="appPatientId" name="appPatientId" type="text" value="">
        </form>
    </div>

    <button id="btnSave" type="button" class="btn btn-success btn-sure" style="width: 100%;">确认
    </button>


</div>
</body>
</html>
