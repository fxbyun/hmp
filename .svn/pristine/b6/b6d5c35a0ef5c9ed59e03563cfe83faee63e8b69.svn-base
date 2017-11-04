<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/8/19
  Time: 16:00
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>就诊人管理</title>
    <script>
        $(function () {
            $("#person").addClass("active");

            $("#addPatient").click(function () {
                location.href = "${ctx}/consultation/conArchives"
            })

        })
    </script>
</head>
<body>
<div class="warp">
    <div class="out-detail text-center">
        <i class="fa fa-angle-left" onclick="javascript:history.go(-1)"></i>
        <span class="text-center">就诊人管理</span>
    </div>
    <div class="out-detail-title">
        <a href="${ctx}/consultation/conArchives"><i class="fa fa-plus"></i><span style="color: #333;">新增就诊人</span></a>
    </div>
    <div class="wx-patient per-patient">
        <c:if test="${ not empty patientList}">
            <ul>
                <c:forEach items="${patientList}" var="patient" varStatus="patientNum">
                    <li>
                        <p><span>${patient.name}</span><span style="padding: 0 0.5em;">|</span>
                            <span>
                                <c:if test="${patient.gender.toString()=='Male'}">
                                    男
                                </c:if>
                                <c:if test="${patient.gender.toString()=='Female'}">
                                    女
                                </c:if>
                                <c:if test="${empty patient.gender.toString()}">
                                    无
                                </c:if>
                            </span>
                            <span style="padding: 0 0.5em;">|</span><span>${patient.age}</span>
                        </p>
                        <p><span>联系电话：${patient.mobile}</span></p>
                        <a href="${ctx}/consultation/conArchives?patientId=${patient.id}" class="per-edit"><i class="fa fa-edit"></i></a>
                            <%--删除按钮暂时隐藏 --%>
                            <%--<i id="delPatient" class="fa fa-trash-o per-delete"
                               onclick="delPatient(this,${patient.id})"></i>--%>
                    </li>
                </c:forEach>
            </ul>
        </c:if>
    </div>
</div>
<script>
    function delPatient(obj, patientId) {
        layer.confirm("你确定删除该患者信息吗？(该患者的电子病历也会被删除)", function () {
            var url = "${ctx}/personal/delPatient";
            $.postJSON(url, {"patientId": patientId}, function (data) {
                if (data.success) {
                    layer.msg(data.msg);
                    $(obj).parent().remove();
                } else {
                    layer.msg('删除失败！');
                }
            });
        });
    }
</script>
</body>
</html>
