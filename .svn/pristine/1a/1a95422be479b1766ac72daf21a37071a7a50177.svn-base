<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<!DOCTYPE html>
<html lang="cn">
<head>
    <title>患者咨询</title>
    <script>
        function fn_LoadPage(page) {
            $('#hidPage').val(page);
            $('#btnSubmit').click();
        }
        $(function () {
            $("#nav-consultation").addClass("active");
            $('#currentPage').keydown(function (event) {
                if (event.keyCode == 13) {
                    var page = $('#currentPage').val();
                    if(isNaN(page))page = 1;
                    if(page == "")page = 1;
                    if(page < 1)page = 1;
                    var total = ${emrPage.totalPages};
                    if(page > total )page = total;
                    fn_LoadPage(page-1);
                    return false;
                }
            });


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
        });
        function reply(id){
            $('#patient').val($('#patName').val());
            $("evaForm" + id).submit();
            return false;
        }

        function goToThisPage() {
            var page = $('#currentPage').val();
            if (isNaN(page))page = 1;
            if (page == "")page = 1;
            if (page < 1)page = 1;
            var total = ${emrPage.totalPages};
            if (page > total)page = total;
            fn_LoadPage(page - 1);
            return false;
        }
    </script>
</head>
<body class="case inquire">
<div class="case-content">
    <div class="container">
        <div class="search row">
            <form id="searchForm" action="${ctx}/consultation" method="post" class="form-inline">
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
        <div class="review">
            <div class="review-item">
                <c:forEach items="${emrPage.content}" var="emr">
                    <h3>
                        <span><fmt:formatDate value="${emr.createOn}" type="date" pattern="yyyy/MM/dd h:m"/></span> <span>${emr.patientName}：</span> <span>${emr.mainSuit}</span>
                        <a href="${ctx}/consultation/detail/${emr.id}" class="btn btn-info pull-right">病历详情</a>
                    </h3>
                    <div class="review-item-content row">
                        <c:forEach items="${emr.evaluateList}" var="eva">
                            <c:choose>
                                <c:when test="${eva.type eq 'ELE'}">
                                    <div class="col-md-9 col-sm-9">
                                        <span>评星：${eva.grade}星</span>
                                        <span class="text-left"><h4>评价：${eva.content}</h4></span>
                                    </div>
                                    <div class="col-md-3 col-sm-3 text-right">
                                        <span></span>
                                        <span><fmt:formatDate value="${eva.createTime}" type="date" pattern="yyyy/MM/dd hh:mm"/></span>
                                    </div>
                                </c:when>
                                <c:when test="${eva.type eq 'DTO'}">
                                    <div class="review-content col-md-9 col-sm-9">
                                        <p class="text-left"><span>我</span>回复<span>${emr.patientName}</span>：${eva.content}</p>
                                    </div>
                                    <div class="col-md-3 col-sm-3 text-right">
                                        <span><fmt:formatDate value="${eva.createTime}" type="date" pattern="yyyy/MM/dd hh:mm"/></span>
                                    </div>
                                </c:when>
                                <c:when test="${eva.type eq 'OTD'}">
                                    <div class="review-content col-md-9 col-sm-9">
                                        <p class="text-left"><span>${emr.patientName}</span>回复<span>我</span>：${eva.content}</p>
                                    </div>
                                    <div class="col-md-3 col-sm-3 text-right">
                                        <span><fmt:formatDate value="${eva.createTime}" type="date" pattern="yyyy/MM/dd hh:mm"/></span>
                                    </div>
                                </c:when>
                            </c:choose>
                        </c:forEach>
                        <div class="form-group text-right">
                            <form class="form-inline" action="${ctx}/eval" id="evaForm${emr.id}" method="post">
                                <input type="hidden" name="emrId" value="${emr.id}"/>
                                <input type="hidden" name="patientUid" value="${emr.patientUid}"/>
                                <input type="hidden" name="patientName" value="${emr.patientName}"/>
                                <input type="hidden" name="doctorId" value="${emr.doctor.id}"/>
                                <input type="hidden" name="doctorName" value="${emr.doctorName}"/>
                                <input type="hidden" name="pageNo" value="${emrPage.number + 1}"/>
                                <input type="hidden" name="patient" id="patient"/>
                                <input type="text" class="form-control" name="content" style="width: 89%" id="content${emr.id}" placeholder="咨询/回复"/>
                                <button class="btn btn-info" onclick="reply(${emr.id})">回复</button>
                            </form>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <div class="text-center" style="margin: 10px 0 10px 0">
            <c:if test="${emrPage.number > 0}">
                <button type="button" onclick="fn_LoadPage(${emrPage.number - 1})" class="btn btn-info"><i class="fa fa-chevron-left"></i> 上一页</button>
            </c:if>
            <c:if test="${emrPage.number + 1 < emrPage.totalPages}">
                <button type="button" onclick="fn_LoadPage(${emrPage.number + 1})" class="btn btn-info"><i class="fa fa-chevron-right"></i> 下一页</button>
            </c:if>
            &nbsp;&nbsp;&nbsp;&nbsp;
            第 <input type="text" class="form-control" style="width:50px" id="currentPage" value="${emrPage.number + 1}">页/共<span>${emrPage.totalPages}</span>页
            <a href="#" onclick="javascript:goToThisPage();" class="btn btn-info">跳转 <i class="fa fa-chevron-right"></i></a>
        </div>
    </div>
</div>
</body>
</html>