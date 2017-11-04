<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/23
  Time: 12:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>日患者明细统计</title>
    <script type="text/javascript">
        function fn_LoadRpList(page) {
            $("#hidPage").val(page);
            $("#formSubmit").submit();
        }
        function goToThisPage() {
            var currentPage = $("#currentPage").val();
            $("#hidPage").val(currentPage - 1);
            $("#formSubmit").submit();
        }
    </script>
</head>
<body>
<div class="manage" style="margin-bottom: 10px;">
    <div class="container">
        <ul class="navigation">
            <li><a href="${ctx}/manage" class="btn btn-default">用药分析</a></li>
            <c:if test="${doctor.doctorType!='Sub_Doctor'}">
                <li><a href="${ctx}/message" class="btn btn-default" onclick="isDebug()">短信发送</a></li>
            </c:if>
            <li><a href="${ctx}/rp" class="btn btn-default">我的药方</a></li>
            <li><a href="${ctx}/rplib" class="btn btn-default">药方库</a></li>
            <li><a href="${ctx}/config/symptom" class="btn btn-default">数据整理</a></li>
            <c:if test="${doctor.doctorType!='Sub_Doctor'}">
                <li><a href="${ctx}/appointment" class="btn btn-default">预约挂号</a></li>
            </c:if>
            <c:if test="${doctor.doctorType!='Sub_Doctor'}">
                <li><a href="${ctx}/validityManage" class="btn btn-default">有效期管理</a></li>
            </c:if>
            <li class="active"><a href="${ctx}/financial" class="btn btn-default">财务统计</a></li>
            <ul class="navigation-sub">
                <li><a href="${ctx}/financial" class="btn btn-default">日经营统计</a></li>
                <li class="active"><a href="${ctx}/statisics/patientDetail" class="btn btn-default"
                                      style="width: 152px;">日患者明细统计</a></li>
            </ul>
        </ul>
        <div class="infro-content">
            <form action="/statisics/patientDetail" method="post" id="formSubmit">
                <input id="hidPage" type="hidden" name="page"/>
                <div class="row form-inline">
                    <div class="col-md-4 col-sm-4 pull-right" style="margin-right: 10px;">
                        <div class="form-group" style="margin-right: 10px;">
                            <label for="txtStartDate">查询日期</label>
                            <input type="text" name="startDate"
                                   value="<fmt:formatDate value="${startDate}" pattern="yyyy/MM/dd" />"
                                   class="form-control form_day" id="txtStartDate"

                                   style="width:120px;" readonly="">
                            至
                            <input type="text" name="endDate" style="width:120px;"
                                   value="<fmt:formatDate value="${endDate}" pattern="yyyy/MM/dd" />"
                                   class="form-control form_day"
                                   id="txtEndDate" readonly="">
                        </div>
                        <div class="form-group">
                            <button id="btnSubmit" type="submit" class="btn btn-info pull-right">搜索
                            </button>
                        </div>
                    </div>
                    <div class="col-md-3 col-sm-3" style="float: right; margin-right: -30px;">
                        <div class="form-group">
                            <label for="search">患者姓名</label>
                            <input type="text"
                                   id="patientName"
                                   name="patientName"
                                   class="form-control"
                                   value="${patientName}">
                        </div>
                    </div>
                    <div class="col-md-4 col-sm-4 text-right" style="float: right;">
                        <div class="form-group" style="margin-right: 30px;">
                            <label>主治医生</label>
                            <select class="form-control" name="doctorId">
                                <option value="">全部</option>
                                <c:forEach items="${doctorList}" var="one">
                                    <option value="${one.id}" <c:if
                                            test="${one.id==doctorId}"> selected</c:if>
                                    >${one.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>收银员</label>
                            <select class="form-control" name="shouYinId">
                                <option value="">全部</option>
                                <c:forEach items="${shouYinMap}" var="one">
                                    <option value="${one.key}" <c:if
                                            test="${one.key==shouYinId}"> selected</c:if> >${one.value}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
            </form>
            <div class="financial statisics">
                <table width="100%" border="1">
                    <colgroup width="13%"></colgroup>
                    <colgroup width="12%"></colgroup>
                    <colgroup width="13%"></colgroup>
                    <colgroup width="12%"></colgroup>
                    <colgroup width="13%"></colgroup>
                    <colgroup width="12%"></colgroup>
                    <colgroup width="13%"></colgroup>
                    <colgroup width="12%"></colgroup>

                    <tr>
                        <th>应收总金额</th>
                        <td>${countCost}</td>
                        <th>实收总金额</th>
                        <td>${countRealCost}</td>
                        <th>挂起总金额</th>
                        <td>${HANG_UP}</td>
                        <th>无收银</th>
                        <td>${NO_Baby}</td>
                    </tr>

                </table>
                <table width="100%" border="1" style="float: none;">
                    <colgroup width="7%"></colgroup>
                    <colgroup width="13%"></colgroup>
                    <colgroup width="13%"></colgroup>
                    <colgroup width="10%"></colgroup>
                    <colgroup width="10%"></colgroup>
                    <colgroup width="10%"></colgroup>
                    <colgroup width="10%"></colgroup>
                    <colgroup width="12%"></colgroup>
                    <colgroup width="15%"></colgroup>
                    <thead>
                    <tr>
                        <td colspan="9" style="font-size: 20px;">经营总和数据</td>
                    </tr>
                    <tr>
                        <th>序号</th>
                        <th>患者姓名</th>
                        <th>主治医生</th>
                        <th>应收金额</th>
                        <th>实收金额</th>
                        <th>退款金额</th>
                        <th>挂起金额</th>
                        <th>收银员</th>
                        <th>创建时间</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${emrPage.content}" var="oneEmr" varStatus="status">
                        <tr>
                            <td>
                                    ${status.count +(emrPage.number * emrPage.getSize())}
                            </td>
                            <td>
                                    ${oneEmr.patientName}
                            </td>
                            <td>${oneEmr.doctor.name}</td>
                            <td>${oneEmr.cost}</td>
                            <td>${oneEmr.realCost}</td>
                            <td>0.00</td>
                            <c:if test="${oneEmr.getStatus()=='HANG_UP'}">
                                <td>${oneEmr.cost}</td>
                            </c:if>
                            <c:if test="${oneEmr.getStatus()!='HANG_UP'}">
                                <td>0.00</td>
                            </c:if>
                            <td>${not empty cashierMap[oneEmr]?cashierMap[oneEmr].name:oneEmr.cashierName}</td>
                            <td><fmt:formatDate value="${oneEmr.createOn}" pattern="yyyy/MM/dd HH:mm"/></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div class="text-center">
                    <c:if test="${emrPage.number > 0}">
                        <button type="button" onclick="fn_LoadRpList(${emrPage.number - 1})" class="btn btn-default"
                                style="width: 100px; height: 50px; margin-right: 15px;">上一页
                        </button>
                    </c:if>

                    <c:if test="${emrPage.number + 1 < emrPage.totalPages}">
                        <button type="button" onclick="fn_LoadRpList(${emrPage.number + 1})" class="btn btn-default"
                                style="width: 100px; height: 50px;">下一页
                        </button>
                    </c:if>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    第 <input type="text" class="form-control" style="width:50px; text-align: center;" id="currentPage"
                             value="${emrPage.number+1}">页/共<span>${emrPage.totalPages}</span>页
                    <a href="#" onclick="javascript:goToThisPage();" class="btn btn-success">跳转 </a>
                </div>
            </div>


        </div>
    </div>
</div>
</body>
</html>
