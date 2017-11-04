<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/5/26
  Time: 15:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html lang="cn">
<head>
    <title>患者管理</title>

    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/getPinYin.js" type="js"/>
    <style>

        .intro-sign {
            padding: 20px 20px 0 20px;
        }

        .intro-sign input {
            margin-right: 5px;
        }

        .form-group label {
            color: #666;
            font-size: 16px;
            font-weight: normal;
        }

        .form-group input {
            /*border:1px solid #0498d2;*/
            font-weight: normal;
            color: #555;
        }

        .form-group a {
            font-size: 18px;
            font-weight: 400;
            width: 120px;
            letter-spacing: 2px;
        }

        .form-group .btn-default {
            color: #666;
            margin-right: 15px;
        }

        .nav-tabs > li > a {
            border: 1px solid #ccc;
            color: #666;
        }

        .nav-tabs > li.active > a, .nav-tabs > li.active > a:focus, .nav-tabs > li.active > a:hover {
            color: #fff;
        }

        .nav-tabs > li > a:hover {
            background-color: #f1f1f1;
        }

        .nav-tabs > li > a {
            padding: 5px 5px;
            border-radius: 4px;
        }

        .intro-sign .pull-right {
            width: 80px;
            font-size: 16px;
            letter-spacing: 2px;
            color: #666;
            box-shadow: 0 2px 3px -1px #666;
        }

        /* 我的患者样式 */

        .intro-sign select {
            /*border: 1px solid #0498d2;*/
            margin-left: 5px;
        }

        .case-table {
            border-top: 0;
            box-shadow: 0 3px 3px #d9d9d9;
            border-radius: 10px;
        }

        .case-table tbody tr td:last-child {
            text-align: center;
        }

        /*.case-table thead td{
            border-bottom: 2px solid #0498d2;
        }*/
        .case-table tbody tr {
            border: 0;
            cursor: pointer;
        }

        .case-table thead td {
            padding: 10px 0;
        }

        .case-table tbody td {
            color: #333;
            font-size: 16px;
        }

        .case-table thead {
            font-size: 18px;
        }

        .case-table tbody tr:last-child {
            border-bottom: 0;
        }

        .case-table tbody tr:last-child td:first-child {
            border-bottom-left-radius: 10px;
        }

        .case-table tbody tr:last-child td:last-child {
            border-bottom-right-radius: 10px;
        }

        .case-table tbody td {
            padding: 15px 0;
        }

        .tab-content > .tab-pane > .intro-sign {
            padding: 0;
            box-shadow: 0 3px 3px #d9d9d9;
        }

        .case-table .backg2 {
            background-color: #eee;
        }

        .case-table thead tr {
            height: 70px;;
        }

        #moreSeach {
            border-top: 1px solid #ccc;
            float: left;
            width: 103%;
            padding-top: 15px;
            margin-left: -17px;
            padding-left: 32px;
        }

        tbody > tr:nth-of-type(odd) {
            background-color: #eee;
        }

        tbody > tr:nth-of-type(even) {
            background-color: #e5e5e5;
        }

    </style>

    <script>

        function fn_LoadEmrList(page) {
            $('#hidPage').val(page);
            $('#btnSubmit').click();
        }
        $(function () {
            $("#nav-emr").addClass("active");

            $('.form_date2').datetimepicker({
                language: 'zh-CN',
                format: 'yyyy/mm/dd',
                weekStart: 1,
                todayBtn: 1,
                autoclose: 1,
                todayHighlight: 1,
                startView: 2,
                minView: 2,
                forceParse: 0
            });

        });
        function show_seach() {
            $("#moreSeach").show();
            $(".patient>.col-md-12.intro-sign").css({
                "border-bottom-left-radius": "0px",
                "border-bottom-right-radius": "0px"
            });
        }
        function hide_seach() {
//            $("#moreSeach").hide();
//            $(".patient .intro-sign").css({
//                "border-bottom-left-radius": "10px",
//                "border-bottom-right-radius": "10px"
//            });
            window.location.href = "${ctx}/patientManageEmrList"
        }

        function toSeeThisPatienEmrListLink(patienId) {
//            hide_seach();

            window.location.href = "${ctx}/patientManageEmrList?patientId=" + patienId
        }

        function pullData() {
            var nowDate = new Date();
            var scanDate = new Date(nowDate);
            var ageTops = $("#ageTops").val();
            var ageFlooer = $("#ageFlooer").val();

            //endDate
            if (ageTops != "") {
                if (ageTops != "0") {
                    ageTops = nowDate.getFullYear() - ageTops + "/" + (parseInt(nowDate.getMonth()) + 1) + "/" + nowDate.getDate();
                } else {
                    ageTops = nowDate.getFullYear() + "/" + (parseInt(nowDate.getMonth()) + 1) + "/" + nowDate.getDate();
                }
            } else {
                ageTops = nowDate.getFullYear() + "/" + (parseInt(nowDate.getMonth()) + 1) + "/" + nowDate.getDate();
            }

            //startDate
            if (ageFlooer != "") {
                if (ageFlooer != "0") {
                    ageFlooer = nowDate.getFullYear() - ageFlooer + "/" + (parseInt(nowDate.getMonth()) + 1) + "/" + nowDate.getDate();
                } else {
                    ageFlooer = nowDate.getFullYear() - 1 + "/" + (parseInt(nowDate.getMonth()) + 1) + "/" + nowDate.getDate();
                }
            } else {
                ageFlooer = "1900/01/01";
            }


            $("#ageTopsVal").val(ageTops);
            $("#ageFlooerval").val(ageFlooer);
            return true;
        }
        function goToThisPage() {
            var num = $("#currentPage").val();
            $("#hidPage").val(parseInt(num) - 1);
            $("#patientManagerForm").submit();
        }
        /*患者姓名拼音*/
        function pullPinYin() {
            $("#helpCode").val(pinyin.getCamelChars($("#patientName").val()));
            return true;
        }
    </script>
</head>
<body>
<div class="container electronic tabbable">
    <form id="patientManagerForm" action="/patientManage" method="post" onsubmit="pullData(); return true;">
        <input id="hidPage" type="hidden" name="page"/>
        <input id="ageTopsVal" type="hidden" name="ageTops"/>
        <input id="ageFlooerval" type="hidden" name="ageFlooer"/>


        <div class="row patientManage">
            <div class="col-md-12">
                <div class="intro-sign form-inline">
                    <div class="row">
                        <div class="col-md-4 col-sm-4">
                            <div class="form-group">
                                <ul class="nav nav-tabs" role="tablist">
                                    <li role="presentation" class="active"><a href="#patient" class="btn btn-default"
                                                                              aria-controls="tabnoe" role="tab"
                                                                              data-toggle="tab" onclick="show_seach()">我的患者</a>
                                    </li>
                                    <li role="presentation"><a href="#manage" id="btnManage" class="btn btn-default"
                                                               aria-controls="profile" role="tab"
                                                               data-toggle="tab" onclick="hide_seach()">历史病历</a>
                                    </li>
                                </ul>
                            </div>
                        </div>

                        <div class="col-md-3 col-sm-3">
                            <div class="form-group">
                                <label for="search">搜索姓名</label>
                                <input type="text" id="patientName" name="patient" class="form-control"
                                       value="${patientName}"
                                >
                                <input type="text" style="display: none" name="helpCode" id="helpCode"/>
                            </div>
                        </div>

                        <div class="col-md-4 col-sm-4">
                            <div class="form-group">
                                <label for="txtStartDate">搜索日期</label>
                                <input type="text" name="startDate"
                                       value="<fmt:formatDate value="${startDate}" pattern="yyyy/MM/dd" />"
                                       class="form-control form_date2"
                                       id="txtStartDate" style="width:120px;" readonly>
                                至
                                <input type="text" name="endDate" style="width:120px;"
                                       value="<fmt:formatDate value="${endDate}" pattern="yyyy/MM/dd" />"
                                       class="form-control form_date2" id="txtEndDate" readonly>
                            </div>
                        </div>

                        <div class="col-md-1 col-sm-1">
                            <div class="form-group">
                                <button id="btnSubmit" type="submit" onclick="pullPinYin()"
                                        class="btn btn-info pull-right">搜索
                                </button>
                            </div>
                        </div>
                        <%-- 我的患者 搜索 --%>
                        <div class="form-inline" id="moreSeach">
                            <div class="col-md-3 col-sm-3">
                                <div class="form-group">
                                    <label for="texAge">年龄段</label>
                                    <input type="number" min="0" id="ageTops" name="ageTopsString" value="${ageTopsString}"
                                           class="form-control" style="width: 25%;">
                                    至
                                    <input type="number" min="0" id="ageFlooer" name="ageFlooerString" style="width: 25%;"
                                           value="${ageFlooerString}" class="form-control">
                                </div>

                            </div>
                            <div class="col-md-4 col-sm-4">
                                <div class="form-group">
                                    <label for="texSex">性别筛选</label>
                                    <select class="form-control" name="genderSex">
                                        <option value="">全体</option>
                                        <option value="Male"
                                                <c:if test="${genderSex=='Male'}">
                                                    selected="selected"
                                                </c:if>
                                        >男
                                        </option>
                                        <option value="Female"
                                                <c:if test="${genderSex=='Female'}">
                                                    selected="selected"
                                                </c:if>
                                        >女
                                        </option>
                                    </select>
                                </div>
                                <c:if test="${doctor.doctorType=='Clinic_Boss'}">
                                    <div class="form-group" style="margin-left: 20px;">
                                        <label>主治医生</label>
                                        <select class="form-control" name="subDoctorId">
                                            <option value="">全部</option>
                                            <option value="${doctor.id}"
                                                    <c:if test="${doctor.id==subDoctorId}"> selected</c:if>
                                            >${doctor.name}</option>
                                            <c:forEach items="${subDoctorList}" var="one">
                                                <option value="${one.id}" <c:if
                                                        test="${one.id==subDoctorId}"> selected</c:if> >${one.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </c:if>
                            </div>
                            <div class="col-md-5 col-sm-5">
                                <div class="form-group">
                                    <label for="search">病症搜索</label>
                                    <input type="text" name="diagonsisName" value="${diagonsisName}"
                                           class="form-control"
                                           style="width: 270px;">
                                    <button type="button" class="btn btn-warning"
                                            onclick="window.location.href='${ctx}/oldPatient/getAllByDoctor'"
                                            style="width: 120px; margin-left: 10px;">查看旧数据
                                    </button>
                                </div>

                            </div>
                        </div>
                    </div>

                </div>
                <div class="tab-content">
                    <%-- 患者 --%>
                    <div role="tabpanel" class="tab-pane active" id="patient">
                        <div class="intro-sign form-group">
                            <table class="case-table table-hover">
                                <colgroup width="25%"></colgroup>
                                <colgroup width="15%"></colgroup>
                                <colgroup width="13%"></colgroup>
                                <colgroup width="12%"></colgroup>
                                <colgroup width="15%"></colgroup>
                                <colgroup width="20%"></colgroup>
                                <thead>
                                <tr>
                                    <td>最后一次就诊时间</td>
                                    <td>姓名</td>
                                    <td>年龄</td>
                                    <td>性别</td>
                                    <td>医生</td>
                                    <td>电话号码</td>
                                    <%--<td>就诊次数</td>--%>
                                </tr>
                                </thead>
                                <tbody>
                                <div id="emrPatientListDiv">
                                    <c:forEach items="${patientPage.content}" var="oneEmr" varStatus="emrNum">
                                        <tr onclick="toSeeThisPatienEmrListLink(${oneEmr.patient.id})">
                                            <td><fmt:formatDate value="${oneEmr.createOn}"
                                                                pattern="yyyy/MM/dd HH:mm"/>
                                            </td>
                                            <td>${oneEmr.patient.name}</td>
                                            <td>${oneEmr.patient.age}</td>
                                            <td>

                                                <c:choose>
                                                    <c:when test="${oneEmr.patient.gender== 'Male'}">
                                                        男
                                                    </c:when>
                                                    <c:when test="${oneEmr.patient.gender=='Female'}">
                                                        女
                                                    </c:when>

                                                    <%--<c:otherwise>--%>
                                                    <%--未录入--%>
                                                    <%--</c:otherwise>--%>


                                                </c:choose>
                                            </td>
                                            <td>${oneEmr.doctor.name}</td>
                                            <td>${oneEmr.patient.mobile}</td>
                                                <%--<td>${oneEmr.patient.mobile}</td>--%>
                                        </tr>
                                    </c:forEach>
                                </div>

                            </table>
                        </div>
                    </div>
                </div>

                <%-- 分页 --%>
                <div class="text-center" style="margin: 10px 10px">
                    <c:if test="${patientPage.number !=0}">
                        <button type="button" onclick="fn_LoadEmrList(${patientPage.number -1})" class="btn btn-default"
                                style="width:100px;height:45px;"> 上一页
                        </button>
                    </c:if>
                    <c:if test="${patientPage.number+1 != patientPage.totalPages }">
                        <button type="button" onclick="fn_LoadEmrList(${patientPage.number +1})" class="btn btn-default"
                                style="width:100px;height:45px;">下一页
                        </button>
                    </c:if>

                    &nbsp;&nbsp;&nbsp;&nbsp;
                    第 <input type="text" class="form-control" style="width:50px;text-align:center;" id="currentPage"
                             value="${patientPage.number+1}">页/共<span>${patientPage.totalPages}</span>页
                    <a href="#" onclick="javascript:goToThisPage();" class="btn btn-success">跳转</a>
                </div>
            </div>

        </div>


    </form>
</div>


</body>
</html>
