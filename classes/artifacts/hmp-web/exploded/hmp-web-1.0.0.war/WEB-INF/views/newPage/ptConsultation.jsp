<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/5/28
  Time: 13:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html lang="cn">
<head>
    <title>患者咨询</title>
    <style>
        body {
            background-color: #dde6ea;
        }

        * {
            margin: 0;
            padding: 0;
        }

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

        .intro-sign .pull-right {
            width: 80px;
            font-size: 16px;
            letter-spacing: 2px;
            box-shadow: 0 3px 3px -1px #666;
            padding: 4px 0;
        }

        .col-sm-4 .btn-boxs .btn-default {
            width: 120px;
            font-weight: 400;
            letter-spacing: 2px;
            font-size: 18px;
        }

        .intro-sign.form-inline {
            margin-bottom: 20px;
        }

        .intro-sign {
            margin-bottom: 10px;
        }

        .review-item-content {
            font-size: 18px;
            padding: 0;
        }

        .review-item-content p {
            padding-left: 0;
        }

        .review-item-content .col-md-3 span {
            padding-left: 10px;
            font-size: 16px;
            width: 100%;
            margin-left: 10px;
        }

        .col-sm-11 .btn-boxs .btn-default {
            width: 110px;
            box-shadow: none;
            letter-spacing: 0;
            margin: 0;
        }

        /* 图标 */
        .review-content i {
            background: url("/assets/styles/images/qb01.png") left center no-repeat;
            width: 13px;
            height: 13px;
            padding: 5px 10px 0 20px;
            margin-left: 20px;
        }


    </style>
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
                    if (isNaN(page))page = 1;
                    if (page == "")page = 1;
                    if (page < 1)page = 1;
                    var total = ${emrPage.totalPages};
                    if (page > total)page = total;
                    fn_LoadPage(page - 1);

                    return false;
                }
            });


            $('#txtStartDate,#txtEndDate').datetimepicker({
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
        function reply(id) {
            $('#patient').val($('#patName').val());
            $("evaForm" + id).submit();
            return false;
        }


        function goToThisPage() {
            var total = ${emrPage.totalPages};
            if (total > 0) {
                var page = $('#currentPage').val();
                if (isNaN(page))page = 1;
                if (page == "")page = 1;
                if (page < 1)page = 1;
                if (page > total)page = total;
                fn_LoadPage(page - 1);
            } else {
                $("#tiaozhuan").attr(disabled = true);
            }
            return false;
        }


    </script>
</head>
<body>
<div class="container electronic tabbable">
    <div class="row patientCon">
        <div class="col-md-12">
            <div class="intro-sign form-inline">
                <form id="searchForm" action="${ctx}/ptConsultation" method="post" class="form-inline">
                    <input id="hidPage" type="hidden" name="page"/>

                    <div class="row">

                        <div class="col-md-3 col-sm-4 text-right">
                            <c:if test="${doctor.doctorType=='Clinic_Boss'}">
                                <div class="form-group" style="margin-right: 15px;">
                                    <label>主治医生</label>

                                    <select class="form-control" name="subDoctorId">
                                        <option value="">全部</option>
                                        <option value="${doctor.id}" <c:if
                                                test="${doctor.id==subDoctorId}"> selected</c:if>
                                        >${doctor.name}</option>
                                        <c:forEach items="${subDoctorList}" var="one">
                                            <option value="${one.id}"
                                                    <c:if test="${one.id==subDoctorId}"> selected</c:if>

                                            >${one.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </c:if>
                        </div>

                        <div class="col-md-3 col-sm-3">
                            <div class="form-group">
                                <label for="search">搜索患者</label>
                                <input type="text" name="patient" value="${patient}" class="form-control">
                            </div>
                        </div>

                        <div class="col-md-5 col-sm-4">
                            <div class="form-group">
                                <label for="txtStartDate">搜索日期</label>
                                <input type="text" name="startDate"
                                       vvalue="<fmt:formatDate value='${dateFilter.startDate}' pattern='yyyy/MM/dd'/>"
                                       class="form-control form_date2"
                                       id="txtStartDate" style="width:160px;" readonly>
                                至
                                <input type="text" name="endDate" style="width:160px;" value=""
                                       class="form-control form_date2"
                                       id="txtEndDate"
                                       　value="<fmt:formatDate value='${dateFilter.endDate}' pattern='yyyy/MM/dd'/>"
                                       readonly>
                            </div>
                        </div>

                        <div class="col-md-1 col-sm-1" style="margin-left: -5px;">
                            <div class="form-group">
                                <button id="btnSubmit" type="submit" class="btn btn-default pull-right active">搜索
                                </button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>


            <c:forEach items="${emrPage.content}" var="emr">
                <div class="intro-sign">
                    <div class="row">
                        <div class="col-md-1 col-sm-1">
                            <img class="img-circle" src="/assets/images/touxiang.png" width="107" height="107">
                        </div>
                        <div class="col-md-11 col-sm-11" style="padding-right: 10px; padding-left:30px;">
                            <div style="font-size: 18px; padding-left:16px;" class="btn-boxs">
                                <span><fmt:formatDate value="${emr.createOn}" type="date"
                                                      pattern="yyyy/MM/dd h:m"/></span>
                                <span>${emr.patientName}：</span>
                                <span>${emr.mainSuit}</span>
                                <a href="${ctx}/ptDetail?patientId=${emr.patient.id}&emrId=${emr.id}"
                                   class="btn btn-default pull-right">病历详情</a>
                            </div>

                            <div class="review-item-content row">
                                <c:forEach items="${emr.evaluateList}" var="eva">
                                    <c:choose>
                                        <c:when test="${eva.type eq 'ELE'}">
                                            <div class="col-md-9 col-sm-9">
                                                <span>评星：${eva.grade}</span>
                                                <span class="text-left"><h4
                                                        style="font-size:18px;">评价：${eva.content}</h4></span>
                                            </div>
                                            <div class="col-md-3 col-sm-3 text-right">
                                                <br>
                                                <span><fmt:formatDate value="${eva.createTime}" type="date"
                                                                      pattern="yyyy/MM/dd hh:mm"/></span>
                                            </div>
                                        </c:when>
                                        <c:when test="${eva.type eq 'DTO'}">
                                            <div class="review-content col-md-9 col-sm-9">
                                                <p class="text-left">
                                                    <i></i><span>我</span>回复<span>${emr.patientName}</span>：${eva.content}
                                                </p>
                                            </div>
                                            <div class="col-md-3 col-sm-3 text-right">
                                                <span><fmt:formatDate value="${eva.createTime}" type="date"
                                                                      pattern="yyyy/MM/dd hh:mm"/></span>
                                            </div>
                                        </c:when>
                                        <c:when test="${eva.type eq 'OTD'}">
                                            <div class="review-content col-md-9 col-sm-9">
                                                <p class="text-left">
                                                    <i></i><span>${emr.patientName}</span>回复<span>我</span>：${eva.content}
                                                </p>
                                            </div>
                                            <div class="col-md-3 col-sm-3 text-right">
                                                <span><fmt:formatDate value="${eva.createTime}" type="date"
                                                                      pattern="yyyy/MM/dd hh:mm"/></span>
                                            </div>
                                        </c:when>


                                    </c:choose>
                                </c:forEach>
                                <div class="form-group">
                                    <form class="form-inline" action="${ctx}/eval" id="evaForm${emr.id}" method="post">
                                        <input type="hidden" name="emrId" value="${emr.id}"/>
                                        <input type="hidden" name="patientUid" value="${emr.patientUid}"/>
                                        <input type="hidden" name="patientName" value="${emr.patientName}"/>
                                        <input type="hidden" name="doctorId" value="${emr.doctor.id}"/>
                                        <input type="hidden" name="doctorName" value="${emr.doctorName}"/>
                                        <input type="hidden" name="pageNo" value="${emrPage.number + 1}"/>
                                        <input type="hidden" name="patient" id="patient"/>
                                        <input type="text" class="form-control" name="content" style="width: 89%"
                                               id="content${emr.id}"
                                               placeholder="咨询/回复"/>
                                        <button class="btn pull-right" onclick="reply(${emr.id})">回复</button>
                                    </form>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>


            </c:forEach>


            <%-- 分页 --%>
            <div class="text-center" style="margin:30px 10px 10px 10px">
                <c:if test="${emrPage.number > 0}">
                    <button type="button" onclick="fn_LoadPage(${emrPage.number - 1})" class="btn btn-default"
                            style="width:100px;height:45px;"> 上一页
                    </button>
                </c:if>
                <c:if test="${emrPage.number + 1 < emrPage.totalPages}">
                    <button type="button" onclick="fn_LoadPage(${emrPage.number + 1})" class="btn btn-default"
                            style="width:100px;height:45px;">下一页
                    </button>
                </c:if>

                &nbsp;&nbsp;&nbsp;&nbsp;
                第 <input type="text" class="form-control" style="width:50px;text-align:center;" id="currentPage"
                         value="${emrPage.number + 1}">页/共<span>${emrPage.totalPages}</span>页
                <a href="#" id="tiaozhuan" onclick="javascript:goToThisPage();" class="btn btn-success">跳转</a>
            </div>
        </div>
    </div>


</div>

</body>
</html>
