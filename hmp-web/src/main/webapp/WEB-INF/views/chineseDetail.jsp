<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<!DOCTYPE html>
<html lang="cn">
<head>
    <title></title>
    <script type="text/javascript">
        function fn_LoadPage(page) {
            $('#hidPage').val(page);
            $('#btnSubmit').click();
        }
        //
        $(function () {
            $("#nav-manage").addClass("active");
            $('#currentPage').keydown(function (event) {
                if (event.keyCode == 13) {
                    var page = $('#currentPage').val();
                    if(isNaN(page))page = 1;
                    if(page == "")page = 1;
                    if(page < 1)page = 1;
                    var total = ${detailsPage.totalPages};
                    if(page > total )page = total;
                    fn_LoadPage(page-1);
                    return false;
                }
            });
            //跳转
            $("#skip").click(function () {
                fn_LoadPage($("#currentPage").val() - 1);
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

        function select(){
            var myDate = new Date();
            var year = myDate.getFullYear();
            var month = myDate.getMonth()+1;
            var date =  myDate.getDate();
            var txtEndDate = $("#txtEndDate").val();
            var startDate =$("#txtStartDate").val();
            //月，日，时，分，秒 小于10时，补0
            if(month<10){
                month = "0" + month;
            }
            if(date<10){
                date = "0" + date;
            }
            var time = year+month+date;
            var time = year+"/"+month+"/"+date;
            console.info(time);

            if(txtEndDate > time){
                layer.msg("结束时间不能大于当前时间，请重新选择！",{time:2000});
                return false;
            }else if(txtEndDate < startDate){
                layer.msg("结束时间不能小于开始时间，请重新选择！",{time:2000});
                return false;
            }
            $("#searchForm").submit();

        }
    </script>
    <style>
        .row {
            margin-left: 0;
            margin-right: 0;
        }

        .analysis-data {
            padding-bottom: 10px;
        }
    </style>
</head>
<body>
<div class="manage">
    <div class="container">
        <ul class="navigation">
            <li class="active"><a href="${ctx}/manage" class="btn btn-default">用药分析</a></li>
            <c:if test="${doctor.doctorType=='Clinic_Boss'}">
            <li><a href="${ctx}/message" class="btn btn-default">短信发送</a></li>
            </c:if>
            <li><a href="${ctx}/rp" class="btn btn-default">我的药方</a></li>
            <li><a href="${ctx}/rplib" class="btn btn-default">药方库</a></li>
            <li><a href="${ctx}/config/symptom" class="btn btn-default">数据整理</a></li>
            <c:if test="${doctor.doctorType=='Clinic_Boss'}">
            <li><a href="${ctx}/appointment" class="btn btn-default">预约挂号</a></li>
            </c:if>
            <c:if test="${doctor.doctorType!='Sub_Doctor'}">
                <li><a href="${ctx}/validityManage" class="btn btn-default">有效期管理</a></li>
            </c:if>
            <c:if test="${doctor.doctorType!='Sub_Doctor'}">
                <li><a href="${ctx}/financial" class="btn btn-default">财务统计</a></li>
            </c:if>

        </ul>
        <div class="analysis-data">
            <div class="data-head">
                <h3>
                    <img src="${ctx}/assets/styles/images/d03.png" alt="img">
                    用药数量分析中药房 -- 详细列表
                </h3>
            </div>

            <div class="search row">
                <form id="searchForm" action="${ctx}/chineseDetail/query" method="post" class="form-inline">
                    <input id="hidPage" type="hidden" name="page" />

                    <div class="col-md-8 col-sm-8">
                        <div class="form-group">
                            <label for="txtStartDate">搜索日期 :</label>
                            <input type="text" name="startDate"
                                   value="<fmt:formatDate value='${dateFilter.startDate}' pattern='yyyy/MM/dd'/>"
                                   class="form-control form_day" id="txtStartDate" readonly />
                            至
                            <input type="text" name="endDate"
                                   value="<fmt:formatDate value='${dateFilter.endDate}' pattern='yyyy/MM/dd'/>"
                                   class="form-control form_day" id="txtEndDate" readonly />
                        </div>
                        <div style="float: right;">
                            <label>主治医生</label>
                            <select id="selDoctor" name="doctorId" class="form-control"
                                    style="width: 90px; display: inline-block;">
                                <option value="0">全部</option>
                                <c:forEach items="${subDoctorList}" var="sub">
                                    <option value="${sub.id}">${sub.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="col-md-2 col-sm-2">
                        <button id="btnSubmit" type="button" onclick="select()" class="btn btn-success">搜索</button>
                    </div>
                </form>
            </div>
            <div class="data-table row">
                <div class="col-md-6 col-sm-6">
                    <table class="text-center">
                        <thead>
                        <tr>
                            <td>序号</td>
                            <td style="width:35%;">名称</td>
                            <td>单位</td>
                            <td>数量</td>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${detailsPage.content}" var="d" varStatus="i">
                            <c:if test="${i.index + 1 < 11}">
                                <tr>
                                    <td>${i.index + 1}</td>
                                    <td>${d.name}</td>
                                    <td>${medicineUnits[d.unit]}</td>
                                    <td>${d.qty}</td>
                                </tr>
                            </c:if>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="col-md-6 col-sm-6">
                    <table class="text-center">
                        <thead>
                        <tr>
                            <td>序号</td>
                            <td style="width:35%;">名称</td>
                            <td>单位</td>
                            <td>数量</td>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${detailsPage.content}" var="d" varStatus="i">
                            <c:if test="${i.index + 1 > 10}">
                                <tr>
                                    <td>${i.index + 1}</td>
                                    <td>${d.name}</td>
                                    <td>${medicineUnits[d.unit]}</td>
                                    <td>${d.qty}</td>
                                </tr>
                            </c:if>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="fenye">
                <div class="text-center">
                    <c:if test="${detailsPage.number > 0}">
                        <button type="button" onclick="fn_LoadPage(${detailsPage.number - 1})" class="btn btn-success">
                            上一页
                        </button>
                    </c:if>
                    <c:if test="${detailsPage.number + 1 < detailsPage.totalPages}">
                        <button type="button" onclick="fn_LoadPage(${detailsPage.number + 1})" class="btn btn-success">
                            下一页
                        </button>
                    </c:if>
                    <div class="form-group">
                        第 <input type="text" class="form-control" id="currentPage"
                                 value="${detailsPage.number + 1}">页/共<span>${detailsPage.totalPages}</span>页
                        <a href="#" id="skip" class="btn btn-success">跳转 </a>
                    </div>
                </div>

            </div>

        </div>

    </div>
</div>
</body>
</html>