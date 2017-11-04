<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/10/11
  Time: 17:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/advInclude.jsp" %>
<%@ taglib prefix="hmp" uri="/WEB-INF/TLD/HmpLoadStaticFile.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>挂起列表</title>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/advertising/scripts/advertisingIndex.js" type="js"/>
    <script>
        $(function () {
            $("#nav-charge").addClass("active");
        })


        function loadEmrListPage(page) {
            var size = "${emrListPage.totalPages}";
            if (page + 1 > size) {
                layer.msg("这是是最后一页了，已经没有下一页了！");
                return;
            }
            if (page < 0) {
                layer.msg("这已经是第一页了");
                return;
            }

            $("#hidPage").val(page);
            $("#formSubmit").submit();
        }

        function goToThisPage() {
            var page = "${emrListPage.number}";
            if (isNaN(page)) page = 1;
            if (page == "") page = 1;
            if (page < 1) page = 1;
            var currentPage = $("#currentPage").val();
            $("#hidPage").val(currentPage - 1);
            $("#formSubmit").submit();
        }
    </script>


</head>
<body>
<%@ include file="/WEB-INF/layouts/advheader.jsp" %>
<div class="container chargeManage">
    <div class="navigation">
        <ul class="clearfix">
            <li><a href="${ctx}/adv/chargeManage" class="btn btn-default">待收费列表</a></li>
            <li><a href="${ctx}/adv/chargeManage?status=CHARGE" class="btn btn-default">已收费列表</a></li>
            <li class="active"><a href="${ctx}/adv/chargeManage?status=HANG_UP" class="btn btn-default">挂起列表</a></li>
            <li><a href="${ctx}/adv/chargeManage?status=Back_Money_Success" class="btn btn-default">退费列表</a></li>
            <li id="retailId" onclick="showBox()"><a href="javascript:;" class="btn btn-default">零售开单</a></li>
            <li><a href="${ctx}/retail/RetailList" class="btn btn-default">零售列表</a></li>
        </ul>
        <div class="row" style="border-top: 1px solid #ccc; margin-top: 10px;">
            <c:if test="${loginUser.isChiefNurse=='YES'}">
                <p class="data-info">
                    <span>当日财务数据：</span>
                    <span>应收金额<span>￥${countCost}</span><span style="font-size: 14px;"> | </span></span>
                    <span>已收费金额<span>￥${countRealCost}</span><span style="font-size: 14px;"> | </span></span>
                    <span>挂起费<span>￥${HANG_UP}</span><span style="font-size: 14px;"> | </span></span>
                        <%--<span>欠费<span>￥0.00</span></span>--%>
                </p>
            </c:if>
        </div>
    </div>
    <form action="${ctx}/adv/chargeManage" method="post" id="formSubmit">
        <input id="hidPage" type="hidden" name="page" value="${emrListPage.number}"/>
        <input id="status" type="hidden" name="status" value="HANG_UP"/>
        <div class="adv-container alreadyList">
            <div class="text-right">
                <span>刷卡：</span><input type="text" class="form-control"
                                       style="display: inline-block; width: 15%; margin-right: 20px;"
                                       name="cardPwd"
                                       value="${cardPwd}"
                                       placeholder="请输入患者的健康卡">
                <span>姓名：</span><input type="text" class="form-control"
                                       style="display: inline-block; width: 15%; margin-right: 20px;"
                                       name="name"
                                       value="${name}"
                                       placeholder="请输入患者姓名">
                <span>电话：</span><input type="text" class="form-control" style="display: inline-block; width: 15%;"
                                       name="phone"
                                       value="${phone}"
                                       placeholder="请输入患者电话号码">
                <span>起始时间：</span><input type="text" class="form-control form_day"
                                         style="display: inline-block; width: 10%;"
                                         placeholder="年/月/日"
                                         name="startDate"
                                         value="<fmt:formatDate value="${startDate}" pattern="yyyy/MM/dd"/>"
                                         readonly> -
                <input type="text" class="form-control form_day"
                       style="display: inline-block; width: 10%;"
                       placeholder="年/月/日"
                       name="endDate"
                       value="<fmt:formatDate value="${endDate}" pattern="yyyy/MM/dd"/>"
                       readonly>
                <button type="submit" class="btn btn-success" style="margin-right: 20px;">搜索</button>
            </div>
            <table class="adv-table" width="100%">
                <colgroup width="8%"></colgroup>
                <colgroup width="10%"></colgroup>
                <colgroup width="8%"></colgroup>
                <colgroup width="10%"></colgroup>
                <colgroup width="15%"></colgroup>
                <colgroup width="10%"></colgroup>
                <colgroup width="17%"></colgroup>
                <colgroup width="10%"></colgroup>
                <colgroup width="10%"></colgroup>
                <thead>
                <tr>
                    <th>序号</th>
                    <th>姓名</th>
                    <th>性别</th>
                    <th>年龄</th>
                    <th>电话号码</th>
                    <th>挂起金额</th>
                    <th>就诊时间</th>
                    <th>主治医生</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${emrListPage.content}" varStatus="status" var="one">
                    <tr>
                        <td>
                                ${status.count +(emrListPage.number * emrListPage.getSize())}
                        </td>
                        <td>${one.patientName}</td>
                        <td>${genderMap[one.patient.gender]}</td>
                        <td>${one.patient.getAge()}</td>
                        <td>${one.patient.mobile}</td>
                        <td>${one.cost-one.realCost}</td>
                        <td>
                            <fmt:formatDate value="${one.createOn}" pattern="yyyy/MM/dd HH:mm"/>
                        </td>
                            <%--<td> ${empty one.cashierName?one.doctor.name:one.cashierName}</td>--%>
                        <td> ${one.doctor.name}</td>

                        <td>
                            <button class="btn btn-default" type="button" onclick="chargeDetails(${one.id})">收费</button>
                                <%--<button class="btn btn-success" type="button" style="width: 80px;"
                                        onclick="fn_printReceipt(${one.id})">打印收据
                                </button>--%>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>

            </table>
        </div>
    </form>

    <div class="text-center" style="margin-bottom: 20px;">
        <c:if test="${emrListPage.number > 0}">
            <button type="button" class="btn btn-default" id="loadTopPage"
                    onclick="loadEmrListPage(${emrListPage.number - 1})"
                    style="width: 100px; height: 50px; margin-right: 15px;">上一页
            </button>
        </c:if>

        <c:if test="${emrListPage.number + 1 < emrListPage.totalPages}">
            <button type="button" class="btn btn-default" id="loadNextPage"
                    onclick="loadEmrListPage(${emrListPage.number + 1})" style="width: 100px; height: 50px;">下一页
            </button>
        </c:if>

        第 <input type="text" class="form-control" style="width:50px; text-align: center; display: inline-block;"
                 id="currentPage" value="${emrListPage.number + 1}">页/共<span>${emrListPage.totalPages}</span>页
        <a href="#" onclick="javascript:goToThisPage();" class="btn btn-success">跳转 </a>
    </div>
</div>
<%@ include file="/WEB-INF/layouts/advfooter.jsp" %>
</body>
</html>
