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
            box-shadow: 0 4px 5px -1px #666;
        }

        /* 我的患者样式 */

        .intro-sign select {
            /*border: 1px solid #0498d2;*/
            margin-left: 5px;
        }

        .case-table {
            border-top: 0;
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

        .patient .intro-sign {
        "border-bottom-left-radius": "10px",
        "border-bottom-right-radius": "10px"
        }

    </style>

    <script>

        $(function () {
            $("#nav-emr").addClass("active");
            //设置偶数行和奇数行
            $("tbody>tr:odd").addClass("backg");   //为奇数行设置样式(添加样式类)
            $("tbody>tr:even").addClass("backg2");  // 为偶数行设置样式类
        });
        function fn_LoadEmrList(page) {
            $('#hidPage').val(page);
            $('#btnSubmit').click();
        }
        function toLink(patientId, emrId) {
            window.location.href = "${ctx}/ptDetail?patientId={0}&emrId={1}".format(patientId, emrId);
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
        //        function hide_seach() {
        $(function () {
            $("#moreSeach").hide();
            $(".patient .intro-sign").css({
                "border-bottom-left-radius": "10px",
                "border-bottom-right-radius": "10px"
            });
            var paId = '${patientId}';
            $("#hidPatientId").val(paId);
        })

        //        }

        function toSeeThisPatienEmrListLink(patienId) {
            hide_seach();
        }

        function goToMyPatient() {
            window.location.href = "${ctx}/patientManage";
        }
        
        
        function goToThisPage() {
            var page = parseInt($("#currentPage").val());
            if (page <= 0 || isNaN(page)) {
                page = 1;
            }
            $('#hidPage').val(page - 1);
            $('#btnSubmit').click();
        }
    </script>
</head>
<body>
<div class="container electronic tabbable">
    <form id="patientManagerForm" action="/patientManageEmrList" method="post">
        <input id="hidPage" type="hidden" name="page"/>
        <input id="hidPatientId" type="hidden" name="patientId"/>


        <div class="row patientList">
            <div class="col-md-12">
                <div class="intro-sign form-inline">
                    <div class="row">
                        <div class="col-md-3 col-sm-3">
                            <div class="form-group">
                                <ul class="nav nav-tabs" role="tablist">
                                    <li role="presentation"><a href="#manage" class="btn btn-default"
                                                               aria-controls="tabnoe" role="tab"
                                                               data-toggle="tab" onclick="goToMyPatient()">我的患者</a>
                                    </li>
                                    <li role="presentation" class="active"><a href="#manage" id="btnManage"
                                                                              class="btn btn-default"
                                                                              aria-controls="profile" role="tab"
                                                                              data-toggle="tab">历史病历</a>
                                    </li>
                                </ul>
                            </div>
                        </div>

                        <div class="col-md-4 col-sm-4" style="padding: 0;">
                            <c:if test="${doctor.doctorType!='Sub_Doctor'}">
                                <div class="form-group" style="margin-left: 20px;">
                                    <label>主治医生</label>
                                    <select class="form-control" name="subDoctorId">
                                        <option value="0">全部</option>
                                        <option value="${doctor.id}"
                                                <c:if test="${doctor.id==subDoctorId}"> selected</c:if>
                                        >${doctor.name}</option>
                                        <c:forEach items="${subDoctorList}" var="one">
                                            <option value="${one.id}"
                                                    <c:if test="${one.id==subDoctorId}"> selected</c:if>

                                            >${one.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </c:if>
                            <div class="form-group">
                                <label for="search">搜索姓名</label>
                                <input type="text" name="patient" class="form-control" style="width: 120px;"
                                       value="${patientName}">
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
                                <button id="btnSubmit" type="submit" class="btn btn-info pull-right">搜索</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="tab-content">

                    <%-- 历史病历 --%>
                    <div role="tabpanel" class="tab-pane active" id="manage" class="tabtwos">
                        <div class="intro-sign form-group">
                            <table class="case-table table-hover">
                                <colgroup width="20%"></colgroup>
                                <colgroup width="15%"></colgroup>
                                <colgroup width="12%"></colgroup>
                                <colgroup width="13%"></colgroup>
                                <colgroup width="20%"></colgroup>
                                <colgroup width="20%"></colgroup>
                                <thead>
                                <tr>
                                    <td>日期</td>
                                    <td>姓名</td>
                                    <td>年龄</td>
                                    <td>性别</td>
                                    <td>医生</td>
                                    <td>初步诊断</td>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="emr" items="${emrPage.content}">
                                <tr onclick="toLink(${emr.patient.id},${emr.id})"
                                        <c:if test="${emr.patient.status=='Tmp'}">
                                            class="bgcolorf0"
                                        </c:if>
                                >
                                    <td><fmt:formatDate value="${emr.createOn }" pattern="yyyy/MM/dd"/></td>
                                    <td>${emr.patient.name}</td>
                                    <td>${emr.patient.age}</td>
                                    <td><c:if test="${emr.patient.gender eq 'Male'}">男</c:if><c:if
                                            test="${emr.patient.gender eq 'Female'}">女</c:if></td>
                                    <td>${emr.doctor.name}</td>
                                    <td>${emr.diagnosisResult}</td>
                                </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </div>

                </div>
                <%-- 分页 --%>
                <div class="text-center" style="margin: 10px 10px">
                    <c:if test="${emrPage.number > 0}">
                        <button type="button" onclick="fn_LoadEmrList(${emrPage.number - 1})" class="btn btn-default"
                                style="width:100px;height:45px;"> 上一页
                        </button>
                    </c:if>
                    <c:if test="${emrPage.number + 1 < emrPage.totalPages}">
                        <button type="button" onclick="fn_LoadEmrList(${emrPage.number + 1})" class="btn btn-default"
                                style="width:100px;height:45px;">下一页
                        </button>
                    </c:if>

                    &nbsp;&nbsp;&nbsp;&nbsp;
                    第 <input type="text" class="form-control" style="width:50px;text-align:center;" id="currentPage"
                             value="${emrPage.number + 1}">页/共<span>${emrPage.totalPages}</span>页
                    <a href="#" onclick="javascript:goToThisPage();" class="btn btn-success">跳转</a>
                </div>
            </div>

        </div>


    </form>
</div>


</body>
</html>
