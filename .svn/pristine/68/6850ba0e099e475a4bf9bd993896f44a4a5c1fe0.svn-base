<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/8/24
  Time: 15:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="hmp" uri="/WEB-INF/TLD/HmpLoadStaticFile.tld" %>

<html>
<head>
    <title>预约挂号</title>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/appointment/appointment.js" type="js"/>
    <script>
        $(function () {
            var weekTimeJson =${weekTimeJson};
            var dayTime = getDayTimeArrayFromJson(weekTimeJson);
            createDivSelect(dayTime);
            $("#nav-manage").addClass("active");
            /*//外层table设置偶数行和奇数行
             $(".rp-table>tbody>tr:even").addClass("co2");
             $(".rp-table>tbody>tr.co2:odd").addClass("backg");   //为奇数行设置样式(添加样式类)
             $(".rp-table>tbody>tr.co2:even").addClass("backg2");  // 为偶数行设置样式类*/

            // 开启和关闭预约按钮点击样式
            $(".navigation-sub>li").click(function () {
                $(".navigation-sub>li").removeClass("active");
                $(this).addClass("active");
            });
            //关闭预约按钮
            $(".navigation-sub>li:odd").click(function () {
                $.postJSON("${ctx}/appoint/closeAppoint", {"openStatic": "Close"});
                $("#setData").hide();
            })
            $(".navigation-sub>li:even").click(function () {
                $.postJSON("${ctx}/appoint/openAppoint", {"openStatic": "Open"});
                $("#setData").show();
            });


            /*判断是否打开设置的面板*/
            <c:choose>
            <c:when test="${not empty appointConfig||appointConfig.openStatic.toString()=='Open'}">
            <%--alert("${appointConfig.openStatic.toString()}")--%>
                $("#appoint_open").addClass("active")
                $("#appoint_close").removeClass("active")
                $("#setData").show();
            </c:when>

            <c:otherwise>
            $("#appoint_close").addClass("active")
            $("#appoint_open").removeClass("active")
            $("#setData").hide();
            </c:otherwise>
            </c:choose>

            /*判断医生每15分钟预约的人数*/
            <c:if test="${not empty appointConfig}">
            var personNum = "${appointConfig.personNum}";
            $("#peopleNum").val(personNum);
            </c:if>

            /*设置工作时间段的小时选项*/
            $(".hour").each(function () {
                $(this).append(setHour());
            });

            $(".minute").each(function () {
                $(this).append(setMinute());
            });
            //渲染完以后加入是时间数据
            addTimeData(dayTime)
            //时间段 +号点击效果
            $("div.app-select i.fa-plus").click(function () {
                var box = $(this).parent();
                var new_box = box.clone().appendTo("div#" + box.parent().attr("id"));

                $(box).find("select").each(function (i) {
                    $(new_box).find("select").eq(i).val($(this).val());
                });

                $(new_box).find("i").addClass("fa fa-minus");
                $(new_box).find("i").click(function () {
                    $(this).parent().remove();
                })
                $(new_box).attr("id", "");
            });
            //  -号 点击效果
            $("div.app-select i.fa-minus").click(function () {
                $(this).parent().remove();
            });

            //详情页 删除和发送短信 按钮操作
            $(".ap-detail>table>tbody>tr>td:last-child>button:even").click(function () {
                fn_deleteDateilTr(this);
            });
            $(".ap-detail>table>tbody>tr>td:last-child>button:odd").click(function () {
                fn_sendMes();
            });

            /*时间大小判断*/
            $("div.app-select").each(function () {
                /!*给每一个时间段的最后一个下拉选择设置一个点击事件*!/
                $(this).children("select").last().change(function () {
                    var selector = $(this).parent().parent();
                    var startAndEnd = getTimeBySelector(selector)
                    compareTime(startAndEnd, "开始时间应该小于结束时间")
                });
            })

            //显示预约列表
            fn_LoadAppointListPage(0, "today");


            /*点击保存设置按钮，用于调试方法*/
            $("#saveAppointConfig").click(function () {
                var weekday = getDaySelect();
                try {
                    var allDayTime = getAllDayTime();
                } catch (e) {
                    return false
                }
                //var allDayTime=getAllDayTime();
                if (allDayTime.length == 1) {
                    allDayTime.push("{YJZ}");
                }
                var peopleNum = getEach15MinuteNum();
                var openStatic = true;
                var test = {
                    "openStatic": openStatic,
                    "weekday": weekday,
                    "allDayTime": allDayTime,
                    "peopleNum": peopleNum
                };
                $.postJSON("${ctx}/appoint/saveConfig", {
                    "openStatic": openStatic,
                    "weekday": weekday,
                    "allDayTime": allDayTime,
                    "peopleNum": peopleNum
                }, function (data) {
                    layer.alert(data.msg)
                });
            });


        });

    </script>
</head>
<body>
<div class="manage">
    <div class="container">
        <ul class="navigation">
            <li><a href="${ctx}/manage" class="btn btn-default">用药分析</a></li>
            <c:if test="${doctor.doctorType!='Sub_Doctor'}">
                <li><a href="${ctx}/message" class="btn btn-default" onclick="isDebug()">短信发送</a></li>
            </c:if>
            <li><a href="${ctx}/rp" class="btn btn-default">我的药方</a></li>
            <li><a href="${ctx}/rplib" class="btn btn-default">药方库</a></li>
            <li><a href="${ctx}/config/symptom" class="btn btn-default">数据整理</a></li>

            <li class="active"><a href="${ctx}/appointment" class="btn btn-default">预约挂号</a></li>

            <c:if test="${doctor.doctorType!='Sub_Doctor'}">
                <li><a href="${ctx}/validityManage" class="btn btn-default">有效期管理</a></li>
            </c:if>
            <c:if test="${doctor.doctorType!='Sub_Doctor'}">
                <li><a href="${ctx}/financial" class="btn btn-default">财务统计</a></li>
            </c:if>
            <ul class="navigation-sub">
                <li id="appoint_open"><a href="#" class="btn btn-default">开启预约</a></li>
                <li id="appoint_close"><a href="#" class="btn btn-default">关闭预约</a></li>
            </ul>
        </ul>
        <div class="appointment">
            <div id="setData" class="intro-sign tabbable" style="margin-top: 20px; margin-bottom: 0;">
                <div style="margin: 0; overflow:hidden; padding-top: 10px;">
                    <span style="margin-left: 65px; float:left; padding-top: 5px;">设置工作时间段：</span>
                    <ul class="app-day-ul clearfix" role="tablist" id="weekday">

                        <li><input type="checkbox" name="day"
                                   <c:if test="${weekList.contains('Monday')}">checked="checked"</c:if> >
                            <a href="#time1" data-toggle="tab">周一</a>
                        </li>
                        <li><input type="checkbox" name="day"
                                   <c:if test="${weekList.contains('Tuesday')}">checked="checked"</c:if> >
                            <a href="#time2" data-toggle="tab">周二</a>
                        </li>
                        <li><input type="checkbox" name="day"
                                   <c:if test="${weekList.contains('Wednesday')}">checked="checked"</c:if> >
                            <a href="#time3" data-toggle="tab">周三</a>
                        </li>
                        <li><input type="checkbox" name="day"
                                   <c:if test="${weekList.contains('Thursday')}">checked="checked"</c:if> >
                            <a href="#time4" data-toggle="tab">周四</a>
                        </li>
                        <li><input type="checkbox" name="day"
                                   <c:if test="${weekList.contains('Friday')}">checked="checked"</c:if> >
                            <a href="#time5" data-toggle="tab">周五</a>
                        </li>
                        <li><input type="checkbox" name="day"
                                   <c:if test="${weekList.contains('Saturday')}">checked="checked"</c:if> >
                            <a href="#time6" data-toggle="tab">周六</a>
                        </li>
                        <li><input type="checkbox" name="day"
                                   <c:if test="${weekList.contains('Sunday')}">checked="checked"</c:if> >
                            <a href="#time7" data-toggle="tab">周日</a>
                        </li>
                    </ul>
                </div>
                <div id="times" class="tab-content">
                    <div id="time1" class="tab-pane">
                        <%--<div class="app-select">
                            <select class="form-control hour start"></select>
                            <select class="form-control minute start"></select>
                            <span>至</span>
                            <select class="form-control hour end"></select>
                            <select class="form-control minute end"></select>
                            <i class="fa fa-plus"></i>
                        </div>--%>
                    </div>
                    <div id="time2" class="tab-pane">

                    </div>
                    <div id="time3" class="tab-pane">

                    </div>
                    <div id="time4" class="tab-pane">

                    </div>
                    <div id="time5" class="tab-pane">

                    </div>
                    <div id="time6" class="tab-pane">

                    </div>
                    <div id="time7" class="tab-pane">

                    </div>


                </div>
                <p>
                    <span>每15分钟开放预约人数：</span>
                    <select id="peopleNum" class="form-control"
                            style="display: inline-block; width: 80px; margin-left: 30px;">
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                    </select>
                </p>
                <p class="text-center">
                    <button id="saveAppointConfig" type="button" class="btn btn-success" style="width: 120px;">保存设置
                    </button>
                </p>
            </div>
            <%--预约列表--%>
            <div id="appointList">


            </div>


        </div>

    </div>
</div>
</body>
</html>
