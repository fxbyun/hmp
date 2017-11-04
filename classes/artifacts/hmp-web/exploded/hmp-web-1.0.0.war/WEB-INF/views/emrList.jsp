<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<!DOCTYPE html>
<html lang="cn">
<head>
    <title>历史病历</title>
    <script>
        function fn_LoadEmrList(page) {
            $('#hidPage').val(page);
            $('#btnSubmit').click();
        }
        $(function () {
            $("#nav-emr").addClass("active");

            $('.form_date').datetimepicker({
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
            $('#currentPage').keydown(function (event) {
                if (event.keyCode == 13) {
                    var page = $('#currentPage').val();
                    if(isNaN(page))page = 1;
                    if(page == "")page = 1;
                    if(page < 1)page = 1;
                    var total = ${emrPage.totalPages};
                    if(page > total )page = total;
                    fn_LoadEmrList(page-1);
                    return false;
                }
            });
        });
        function toLink(id) {
            window.location.href = "/emr/" + id;
        }

        function goToThisPage() {
            var page = $('#currentPage').val();
            if (isNaN(page))page = 1;
            if (page == "")page = 1;
            if (page < 1)page = 1;
            var total = ${emrPage.totalPages};
            if (page > total)page = total;
            fn_LoadEmrList(page - 1);
            return false;
        }
    </script>
</head>
<body class="case">
<div class="case-content">
    <div class="container">
        <div class="search row">
            <form id="fromEmrQuery" action="/emr" method="post" class="form-inline">
                <input id="hidPage" type="hidden" name="page"/>

                <div class="col-md-4 col-sm-4">
                    <div class="form-group">
                        <label for="search">搜索患者 :</label>
                        <input type="text" name="patient" value="${patient}" class="form-control" id="search"/>
                    </div>
                </div>
                <div class="col-md-6 col-sm-6">
                    <div class="form-group">
                        <label for="txtStartDate">搜索日期 :</label>
                        <input type="text" name="startDate" value="<fmt:formatDate value='${dateFilter.startDate}' pattern='yyyy/MM/dd'/>" class="form-control form_date" id="txtStartDate"/>
                        至
                        <input type="text" name="endDate" value="<fmt:formatDate value='${dateFilter.endDate}' pattern='yyyy/MM/dd'/>" class="form-control form_date" id="txtEndDate"/>
                    </div>
                </div>
                <div class="col-md-2 col-sm-2">
                    <button id="btnSubmit" type="submit" class="btn btn-info">搜索</button>
                </div>

            </form>
        </div>
        <table class="case-table table-hover">
            <colgroup width="20%"></colgroup>
            <colgroup width="15%"></colgroup>
            <colgroup width="10%"></colgroup>
            <colgroup width="10%"></colgroup>
            <colgroup width="45%"></colgroup>
            <thead>
            <tr>
                <td>日期</td>
                <td>姓名</td>
                <td>年龄</td>
                <td>性别</td>
                <td>诊疗结果</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="emr" items="${emrPage.content}">
                <tr onclick="toLink(${emr.id})">
                    <td><fmt:formatDate value="${emr.createOn }" pattern="yyyy/MM/dd"/></td>
                    <td>${emr.patient.name}</td>
                    <td>${emr.patient.age}</td>
                    <td><c:if test="${emr.patient.gender eq 'Male'}">男</c:if><c:if test="${emr.patient.gender eq 'Female'}">女</c:if></td>
                    <td>${emr.diagnosisResult}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="text-center" style="margin: 10px 10px">
            <c:if test="${emrPage.number > 0}">
                <button type="button" onclick="fn_LoadEmrList(${emrPage.number - 1})" class="btn btn-info"><i class="fa fa-chevron-left"></i> 上一页</button>
            </c:if>
            <c:if test="${emrPage.number + 1 < emrPage.totalPages}">
                <button type="button" onclick="fn_LoadEmrList(${emrPage.number + 1})" class="btn btn-info"><i class="fa fa-chevron-right"></i> 下一页</button>
            </c:if>
            &nbsp;&nbsp;&nbsp;&nbsp;
            第 <input type="text" class="form-control" style="width:50px" id="currentPage" value="${emrPage.number + 1}">页/共<span>${emrPage.totalPages}</span>页
            <a href="#" onclick="javascript:goToThisPage();" class="btn btn-info">跳转 <i class="fa fa-chevron-right"></i></a>
        </div>
    </div>
</div>
</body>
</html>