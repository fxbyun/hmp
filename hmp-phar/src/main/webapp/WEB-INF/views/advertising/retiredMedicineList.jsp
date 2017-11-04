<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/10/12
  Time: 15:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/advInclude.jsp" %>
<%@ taglib prefix="hmp" uri="/WEB-INF/TLD/HmpLoadStaticFile.tld" %>
<html>
<head>
    <title>已退药列表</title>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/advertising/scripts/advertisingIndex.js" type="js"/>
    <script>
        $(function () {
            $("#nav-pharmacy").addClass("active");
        })

        function fn_GoDetail(page) {
            var medName = $("#medName").val();
            var url = "${ctx}/adv/pharmacyManage?medName={0}&page={1}&status={2}".format(medName, page, 'Have_Dispensing_Back');
            location.href = url;
        }

        function fn_LoadRpList(page, totalPage) {
            if (page + 1 > totalPage) {
                layer.msg("这是是最后一页了，已经没有下一页了！");
                return;
            }
            if (page < 0) {
                layer.msg("这已经是第一页了");
                return;
            }

            fn_GoDetail(page)
        }

        function goToThisPage() {

            var num = $("#currentPage").val();
            if (isNaN(num))num = 1;
            if (num == "")num = 1;
            if (num < 1)page = 1;
            var page = parseInt(num) - 1;
            fn_GoDetail(page);
        }

    </script>
</head>
<body>
<%@ include file="/WEB-INF/layouts/advheader.jsp" %>
<div class="container chargeManage">
    <ul class="navigation">
        <li><a href="${ctx}/adv/pharmacyManage" class="btn btn-default">待发药列表</a></li>
        <li><a href="${ctx}/adv/pharmacyManage?status=Have_Dispensing" class="btn btn-default">已发药列表</a></li>
        <li class="active"><a href="${ctx}/adv/pharmacyManage?status=Have_Dispensing_Back"
                              class="btn btn-default">已退药列表</a></li>
    </ul>
    <form action="${ctx}/adv/pharmacyManage" method="post">
        <input id="hidPage" type="hidden" name="page"/>
        <input id="status" type="hidden" name="status" value="Have_Dispensing_Back"/>
        <div class="adv-container">
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
                <colgroup width="8%"></colgroup>
                <colgroup width="8%"></colgroup>
                <colgroup width="8%"></colgroup>
                <colgroup width="13%"></colgroup>
                <colgroup width="20%"></colgroup>
                <colgroup width="15%"></colgroup>
                <colgroup width="10%"></colgroup>
                <colgroup width="10%"></colgroup>
                <thead>
                <tr>
                    <th>序号</th>
                    <th>姓名</th>
                    <th>性别</th>
                    <th>年龄</th>
                    <th>电话号码</th>
                    <th>诊断</th>
                    <th>就诊时间</th>
                    <th>药剂师</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${emrListPage.content}" varStatus="status" var="one">
                    <tr>
                        <td>
                                ${status.count +(emrListPage.number * emrListPage.getSize())}
                        </td>
                        <td>
                                ${one.patientName}
                        </td>
                        <td>
                                ${genderMap[one.patient.gender]}
                        </td>
                        <td>
                                ${one.patient.getAge()}
                        </td>
                        <td>
                                ${one.patient.mobile}
                        </td>
                        <td>
                            <c:forEach items="${one.diagnosisList}" var="oneDiagnosis">
                                <span class="text-num" title="${oneDiagnosis.name}">${oneDiagnosis.name}</span>
                            </c:forEach>
                        </td>
                        <td>
                            <fmt:formatDate value="${one.createOn}" pattern="yyyy/MM/dd HH:mm"/>
                        </td>
                        <td>
                                ${empty one.drugRefundName?one.doctor.name:one.drugRefundName}
                        </td>
                        <td>
                            <button class="btn btn-default" type="button" onclick="retiredMedicineDetails(${one.id})">
                                详情
                            </button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>

            </table>
        </div>
    </form>
    <div class="text-center" style="margin-bottom: 20px;">
        <c:if test="${emrListPage.number > 0}">
            <button type="button" onclick="fn_LoadRpList(${emrListPage.number - 1})"
                    class="btn btn-default" style="width: 100px; height: 50px; margin-right: 15px;">上一页
            </button>
        </c:if>
        <c:if test="${emrListPage.number + 1 < emrListPage.totalPages}">
            <button type="button" onclick="fn_LoadRpList(${emrListPage.number + 1})"
                    class="btn btn-default" style="width: 100px; height: 50px;">下一页
            </button>
        </c:if>
        &nbsp;&nbsp;&nbsp;&nbsp;
        第 <input type="text" class="form-control" style="width:50px; text-align: center; display: inline-block;"
                 id="currentPage" value="${emrListPage.number + 1}">页/共<span>${emrListPage.totalPages}</span>页
        <a href="#" class="btn btn-success" onclick="javascript:goToThisPage();">跳转 </a>
    </div>
</div>
<%@ include file="/WEB-INF/layouts/advfooter.jsp" %>
</body>
</html>
