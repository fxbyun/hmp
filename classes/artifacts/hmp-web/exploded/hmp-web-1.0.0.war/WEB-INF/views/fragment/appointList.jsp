<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/9/9 0009
  Time: 14:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<table class="rp-table" style="margin-top: 20px;" width="100%">
    <colgroup width="20%"></colgroup>
    <colgroup width="30%"></colgroup>
    <colgroup width="10%"></colgroup>
    <colgroup width="30%"></colgroup>
    <thead>
    <tr style="height: 80px;">
        <td colspan="2" style="font-size: 20px; text-align: left;"><i class="fa fa-clock-o"></i>预约挂号列表</td>
        <td colspan="2" style="text-align: right;">
            <ul class="app-dayList">
                <li id="afterTomorrow">
                    <a href="#" class="btn btn-default" onclick="fn_LoadAppointListPage(0,'afterTomorrow')">后天</a>
                </li>
                <li id="tomorrow">
                    <a href="#" class="btn btn-default" onclick="fn_LoadAppointListPage(0,'tomorrow')">明天</a>
                </li>
                <li id="today" class="">
                    <a href="#" class="btn btn-default" onclick="fn_LoadAppointListPage(0,'today')">今天</a>
                </li>
            </ul>

            <%--<button type="button" class="btn btn-default">明天</button>
            <button type="button" class="btn btn-default">后天</button>--%>
        </td>
    </tr>
    <tr>
        <th>日期</th>
        <th>预约时间段</th>
        <th>预约人数</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <%--以下是预约内容--%>
    <c:forEach items="${appointLists}" var="appointList" varStatus="">
        <tr>
            <td><fmt:formatDate value='${appointList.date}' pattern='yyyy/MM/dd'/></td>
            <td><fmt:formatDate value='${appointList.startTime}' pattern='HH:mm'/>~<fmt:formatDate value='${appointList.endTime}' pattern='HH:mm'/><%--<fmt:formatDate value='${appointList.endTime}' pattern='h:m'/>--%></td>
            <td>${appointList.peopleNum}</td>
            <td>
                <button type="button" href="#appDetail_${appointList.id}" data-toggle="collapse" class="btn btn-default"
                        style="width: 100px;">详情<i class="fa fa-caret-down"></i></button>
                <button type="button" class="btn btn-default" onclick="delAllAppointPatient(${appointList.id});">
                    <i class="fa fa-trash-o"></i> 删除全部
                </button>
            </td>
        </tr>
        <%--这里是预约号的详细内容--%>
        <tr id="appDetail_${appointList.id}" class="collapse" aria-labelledby="headingone">
            <td colspan="4">
                <div class="ap-detail">
                    <table width="100%">
                        <colgroup width="10%"></colgroup>
                        <colgroup width="10%"></colgroup>
                        <colgroup width="10%"></colgroup>
                        <colgroup width="10%"></colgroup>
                        <colgroup width="20%"></colgroup>
                        <colgroup width="10%"></colgroup>
                        <colgroup width="30%"></colgroup>
                        <thead>
                        <tr>
                            <th>预约序号</th>
                            <th>姓名</th>
                            <th>年龄</th>
                            <th>性别</th>
                            <th>手机</th>
                            <th>状态</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${appPatientMap[appointList.id]}" var="appointPatient" varStatus="appointStatus">
                            <c:if test="${not empty appointPatient}">
                                <tr id="appPatient_${appointPatient.id}">
                                    <td>${appointStatus.count}</td>
                                    <td>${appointPatient.patient.name}</td>
                                    <td>${appointPatient.patient.age}</td>
                                    <td>
                                        <c:if test="${appointPatient.patient.gender.toString()=='Male'}">
                                            男
                                        </c:if>

                                        <c:if test="${appointPatient.patient.gender.toString()=='Female'}">
                                            女
                                        </c:if>

                                    </td>
                                    <td>${appointPatient.patient.mobile}</td>
                                    <td class="appStatus">${appointPatient.StatusByEnum()}</td>
                                    <td>
                                        <button  type="button" class="btn btn-success" onclick="delAppointPatient(${appointPatient.id})">删除预约</button>
                                        <button  type="button" class="btn btn-success" onclick="fn_sendMessage(${appointPatient.id})">发送短信</button>
                                    </td>
                                </tr>
                            </c:if>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="text-center">
    <button type="button" onclick="fn_LoadRpList(${appointPage.number - 1})" class="btn btn-default"
            style="width: 100px; height: 50px; margin-right: 15px;">上一页
    </button>
    <button type="button" onclick="fn_LoadRpList(${appointPage.number + 1},${appointPage.totalPages})" class="btn btn-default"
            style="width: 100px; height: 50px;">下一页
    </button>
    &nbsp;&nbsp;&nbsp;&nbsp;
    第 <input type="text" class="form-control" style="width:50px; text-align: center;" id="currentPage"
             value="${appointPage.number+1}">页/共<span>${appointPage.totalPages}</span>页
    <a href="#" onclick="javascript:goToThisPage();" class="btn btn-success">跳转 </a>
</div>
<script>
    $(function () {
        //外层table设置偶数行和奇数行
        $(".rp-table>tbody>tr:even").addClass("co2");
        $(".rp-table>tbody>tr.co2:odd").addClass("backg");   //为奇数行设置样式(添加样式类)
        $(".rp-table>tbody>tr.co2:even").addClass("backg2");  // 为偶数行设置样式类

        dayActive();
    })
    function goToThisPage() {
        var num = $("#currentPage").val();
        var page = parseInt(num) - 1;
        var day=$("ul.app-dayList").children(".active").attr("id");
        fn_LoadAppointListPage(page,day);
    }

    function fn_LoadRpList(page,totalPage) {

        if(page+1>totalPage){
            layer.msg("这是是最后一页了，已经没有下一页了！");
            return;
        }
        if(page<0){
            layer.msg("这已经是第一页了");
            return;
        }

        var day=$("ul.app-dayList").children(".active").attr("id");
        fn_LoadAppointListPage(page,day);
    }

    function dayActive() {
        var dayId = "#${day}"
        $(dayId).attr("class","active")
    }

</script>