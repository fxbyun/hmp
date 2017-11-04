<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/8/15
  Time: 10:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>医生主页</title>
    <link rel="stylesheet" href="${ctx}/assets/star/css/star-rating.min.css" type="text/css"/>
    <script src="${ctx}/assets/star/js/star-rating.min.js" type="text/javascript"></script>
    <script>
        $(function () {
            var daySelect = "divAppointList_today";
            //点击时间选择
            $("#dateAndWeek").children("li").each(function () {
                $(this).click(function () {
                    var dateWeek = $(this).text();
                    var index = $(this).index();
                    if (index == 1) {
                        daySelect = "divAppointList_today";
                    }
                    if (index == 2) {
                        daySelect = "divAppointList_tomorrow";
                    }
                    if (index == 3) {
                        daySelect = "divAppointList_afterTomorrow";
                    }
                    $("#appDate").text(dateWeek);
                });
            });

            //设置偶数行和奇数行
            $(".out-time-detail>li:odd").addClass("backg4");   //为奇数行设置样式(添加样式类)
            $(".out-time-detail>li:even").addClass("backg3");  // 为偶数行设置样式类

            $(".out-treatment-share").click(function () {
                $("#" + daySelect).hide(1000);
            })

            $(".out-regist").click(function () {
                $("#" + daySelect).show(1000);
            })

            $("#reservation").addClass("active");


        });
        function goAppoint(obj, doctorId, appointListId) {
            var num = $(obj).find(".remainder").text().replace("余", "");
            if (parseInt(num) == 0) {
                layer.alert("该时间段已经没有预约号，请选择其他时间段！");
            } else {
                var url = "${ctx}/outpatient/outReservationInfo?doctorId={0}&appListId={1}".format(doctorId, appointListId);
                location.href = url;
            }


        }

    </script>
</head>
<body>
<div class="warp">
    <div class="out-detail text-center">
        <i class="fa fa-angle-left" onclick="javascript:history.go(-1)"></i>
        <span class="text-center">医生主页</span>
    </div>
    <div class="out-centent">
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

                <%--<p><span>擅长：${doctor.specialty}</span></p>
                <p><span>月平均接诊数：${averPerNums}人    平均分：${average}</span></p>--%>
                <p><span>从医年限：
                    <c:if test="${not empty doctor.seniority}">
                        ${doctor.seniority}年
                    </c:if>
                </span></p>
                <p><span>职业资格：${doctor.certificate}</span></p>
                <p><span>擅长：${doctor.specialty}</span></p>
            </div>
        </div>
        <div class="out-appoint-info">
            <p>简介</p>
            <span>${doctor.intro}</span>
        </div>
        <div class="out-appointment">
            <div class="out-detail-title" style="border: 0; padding: 0;">
                <ul id="dateAndWeek" class="out-appoint nav">
                    <a href="#" class="fa fa-angle-left float-left"></a>
                    <c:forEach items="${dateAndWeekForThree}" var="entry" varStatus="entryStatic">
                        <li><a href="#"><fmt:formatDate value="${entry.key}" pattern="MM-dd"/>&nbsp;${entry.value}</a>
                        </li>
                    </c:forEach>
                    <a href="#" class="fa fa-angle-right float-right"></a>
                </ul>
            </div>
            <div class="out-detail-title out-appoint-regist" style="border-bottom: 1px solid #E4E4E4;">
                <span id="appDate"><fmt:formatDate value="${nowDate}" pattern="MM-dd"/>&nbsp; ${weekOfDate}</span>
                <a href="#" id="btnAppoint" class="btn btn-default out-regist">预约</a>
            </div>
            <div class="out-detail-title out-appoint-evaluate" style="border-bottom: 1px solid #E4E4E4;"
                 onclick="javascript:location.href='${ctx}/consultation/conEvaluate?doctorId='+${doctor.id}">
                <div class="float-left">
                    <p>患者评价(${evaluateList.size()})</p>
                </div>
                <div class="float-right">
                    <a href="#"><i class="fa fa-angle-right" style="font-size: 1.8em; line-height: 1.5em;"></i></a>
                </div>
            </div>
            <%--五条评价--%>
            <div class="con-evaluate">
                <ul>
                    <c:forEach var="eva" items="${evaluateList}" end="4">
                        <li href="#content1" role="button" data-toggle="collapse" aria-expanded="true">
                            <div style="overflow: hidden;">
                                <div class="float-left">
                                    <img src="${ctx}/temp/1466995166782.jpg" width="40" height="40" alt="患者头像">
                                    <span>${eva.patientName}</span>
                                </div>
                                <div class="float-right">
                                    <input name="grade" disabled="disabled" value="${eva.grade*2}" type="number"
                                           class="rating" min=0 max=10 step=0.1 data-size="xs"
                                           style="display: inline-block;">
                                </div>
                            </div>
                            <p style="margin-top: 1%;">${eva.content}</p>
                            <p>
                                <span>评价时间：<fmt:formatDate value="${eva.createTime}" type="date"
                                                           pattern="yyyy/MM/dd hh:mm"/></span>
                            </p>
                        </li>
                    </c:forEach>

                </ul>
            </div>

        </div>
    </div>
    <!--今天的-->
    <div id="divAppointList_today" class="out-treatment-time">
        <div class="out-treatment-share"></div>
        <div class="out-times">
            <div class="text-center out-times-title"><span>请选择就诊时间段</span></div>
            <ul class="out-time-detail">
                <c:if test="${not empty appointLists_today}">
                    <c:forEach items="${appointLists_today}" var="appointList">
                        <li onclick="goAppoint(this,${doctor.id},${appointList.id})">
                            <div style="overflow: hidden; line-height: 3em;">
                                <span class="float-left" style="width: 42%"><fmt:formatDate
                                        value="${appointList.startTime}" pattern="HH:mm"/>-<fmt:formatDate
                                        value="${appointList.endTime}" pattern="HH:mm"/></span>
                                <span class="float-left" style="width: 30%">
                        <c:choose>
                            <c:when test="${appointList.peopleNum<appointList.remainder}">
                                可预约
                            </c:when>
                            <c:otherwise>
                                约满
                            </c:otherwise>
                        </c:choose>

                    </span>
                                <span class="float-left remainder"
                                      style="width: 18%">余${appointList.remainder-appointList.peopleNum}</span>
                                <i style="width: 10%; font-size: 2.2rem; color: #868686; line-height: 1.8em;"
                                   class="fa fa-angle-right float-right text-center"></i>
                            </div>
                        </li>
                    </c:forEach>
                </c:if>

            </ul>
        </div>
    </div>

    <!--明天的-->
    <div id="divAppointList_tomorrow" class="out-treatment-time">
        <div class="out-treatment-share"></div>
        <div class="out-times">
            <div class="text-center out-times-title"><span>请选择就诊时间段</span></div>
            <ul class="out-time-detail">
                <c:if test="${not empty appointLists_tomorrow}">
                    <c:forEach items="${appointLists_tomorrow}" var="appointList">
                        <li onclick="goAppoint(this,${doctor.id},${appointList.id})">
                            <div style="overflow: hidden; line-height: 3em;">
                                <span class="float-left" style="width: 42%"><fmt:formatDate
                                        value="${appointList.startTime}" pattern="HH:mm"/>-<fmt:formatDate
                                        value="${appointList.endTime}" pattern="HH:mm"/></span>
                                <span class="float-left" style="width: 30%">
                        <c:choose>
                            <c:when test="${appointList.peopleNum<appointList.remainder}">
                                可预约
                            </c:when>
                            <c:otherwise>
                                约满
                            </c:otherwise>
                        </c:choose>

                    </span>
                                <span class="float-left remainder"
                                      style="width: 18%">余${appointList.remainder-appointList.peopleNum}</span>
                                <i style="width: 10%; font-size: 2.2rem; color: #868686; line-height: 1.8em;"
                                   class="fa fa-angle-right float-right text-center"></i>
                            </div>
                        </li>
                    </c:forEach>
                </c:if>

            </ul>
        </div>
    </div>

    <!--后天的-->
    <div id="divAppointList_afterTomorrow" class="out-treatment-time">
        <div class="out-treatment-share"></div>
        <div class="out-times">
            <div class="text-center out-times-title"><span>请选择就诊时间段</span></div>
            <ul class="out-time-detail">
                <c:if test="${not empty appointLists_afterTomorrow}">
                    <c:forEach items="${appointLists_afterTomorrow}" var="appointList">
                        <li onclick="goAppoint(this,${doctor.id},${appointList.id})">
                            <div style="overflow: hidden; line-height: 3em;">
                                <span class="float-left" style="width: 42%"><fmt:formatDate
                                        value="${appointList.startTime}" pattern="HH:mm"/>-<fmt:formatDate
                                        value="${appointList.endTime}" pattern="HH:mm"/></span>
                                <span class="float-left" style="width: 30%">
                        <c:choose>
                            <c:when test="${appointList.peopleNum<appointList.remainder}">
                                可预约
                            </c:when>
                            <c:otherwise>
                                约满
                            </c:otherwise>
                        </c:choose>

                    </span>
                                <span class="float-left remainder"
                                      style="width: 18%">余${appointList.remainder-appointList.peopleNum}</span>
                                <i style="width: 10%; font-size: 2.2rem; color: #868686; line-height: 1.8em;"
                                   class="fa fa-angle-right float-right text-center"></i>
                            </div>
                        </li>
                    </c:forEach>
                </c:if>

            </ul>
        </div>
    </div>

</div>
<script>
    $(function () {


    })
</script>
</body>
</html>
