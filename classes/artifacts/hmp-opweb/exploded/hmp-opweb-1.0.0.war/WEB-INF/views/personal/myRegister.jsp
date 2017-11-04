<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/8/19
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>我的挂号</title>
    <script src="${ctx}/assets/webSockets/sockjs-0.3.min.js"></script>
    <%@ include file="/WEB-INF/views/header/webSocket.jsp" %>
    <script>
        //删除预约
        function delReward(rewardId, obj, doctorId) {
            layer.confirm("您要删除该预约号吗？", function () {
                $.postJSON("${ctx}/personal/delMyReward", {"rewardId": rewardId}, function (data) {
                    if (data.success == true) {
                        layer.msg(data.msg)
                        $(obj).parent().parent().find(".appStatus").text("已取消");
                        $(obj).parent().parent().find(".appCancel").css("display", "none");
                        callChang(doctorId);
                    } else {
                        layer.msg(data.msg)
                    }
                })
            })

        }
        //查看医生信息
        function doctorInfo(doctorId) {
            var url = "${ctx}/outpatient/outAppointment?doctorId="+doctorId;
            location.href=url;
        }

        $(function () {
            $.postJSON("${ctx}/personal/myReward", function (data) {
                if (data.success = true) {
                }
            })

            $("#person").addClass("active");




        })
    </script>
</head>
<body>
<div class="warp">
    <div class="out-detail text-center">
        <i class="fa fa-angle-left" onclick="javascript:history.go(-1)"></i>
        <span class="text-center">我的挂号</span>
    </div>
    <div class="per-my-reg">
        <ul>
            <c:forEach var="myReward" items="${myRewardList}" varStatus="">
                <li>
                    <div class="per-my-title"><span class="float-left">挂号详情</span>
                        <span class="float-right appStatus">
                            <c:if test="${myReward.isShowCancel}">
                                ${myReward.appStatus}
                            </c:if>
                            <c:if test="${!myReward.isShowCancel}">
                                已过期
                            </c:if>
                        </span></div>
                    <div class="per-my-reginfo" onclick="doctorInfo(${myReward.doctorId})">
                        <img class="float-left" src="${ctx}/temp/${myReward.headUrl}" width="52" height="58" alt="医生头像"
                             style="margin: 0.5em 0;">
                        <div class="float-left" style="padding-left: 1em; width: 84%;">
                            <a href="#" style="line-height: 2em;">${myReward.doctorName}</a>
                            <p><span>门诊：${myReward.outServiceName}</span></p>
                            <p><span
                                    style="display:block; width: 100%;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;">擅长：${myReward.specialty}</span>
                            </p>
                        </div>
                    </div>
                    <div class="per-my-cnsultinfo">
                        <p><span>就诊人&nbsp;&nbsp;&nbsp;&nbsp;</span><span>${myReward.patientName}</span></p>
                        <p><span>预约时间</span><span>${myReward.appDate}</span></p>
                        <p><span>预约时段</span><span>${myReward.appStartTime}-${myReward.appEndTime}</span></p>
                    </div>
                    <div class="text-right per-my-btn" id="cancelReward">
                        <c:if test="${myReward.appStatus=='已预约'&& myReward.isShowCancel}">
                            <a href="javascript:void(0)"
                               onclick="delReward(${myReward.rewardId},this,${myReward.doctorId})"
                               class="btn btn-default appCancel">取消预约</a>
                        </c:if>

                            <%--<a href="${ctx}/personal/myConsulting" class="btn btn-default">咨询</a>--%>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>


</div>

</body>
</html>
