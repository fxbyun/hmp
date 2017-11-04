<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/10/20
  Time: 17:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/advInclude.jsp" %>
<%@ taglib prefix="hmp" uri="/WEB-INF/TLD/HmpLoadStaticFile.tld" %>
<html>
<head>
    <title>已完成列表</title>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/advertising/scripts/advertisingIndex.js" type="js"/>
    <script>
        $(function () {
            $("#nav-inspect").addClass("active");
        })

        function fn_LoadRpList(page) {
            $("#hidPage").val(page);
            $("#btnSubmit").click();
        }

        function goToThisPage() {
            var page = $("#currentPage").val();
            if (isNaN(page))page = 1;
            if (page < 1) page = 1;
            var total = ${emrJClassAdviceDictPage.totalPages};
            if (page > total) page = total;
            fn_LoadRpList(page - 1);
            return false;
        }
    </script>
</head>
<body>
<%@ include file="/WEB-INF/layouts/advheader.jsp" %>
<div class="container inspectManage">
    <ul class="navigation">
        <li><a href="${ctx}/adv/inspectManage" class="btn btn-default">待检查列表</a></li>
        <li class="active"><a href="${ctx}/adv/inspectManage?status=Have_Exam_Or_Lab&page=" +page
                              class="btn btn-default">已完成列表</a></li>
    </ul>
    <form action="${ctx}/adv/inspectManage" method="post">
        <input id="hidPage" type="hidden" name="page"/>
        <input id="status" type="hidden" name="status" value="${status}"/>
        <div class="adv-container">
            <div class="text-right">
                <span>刷卡：</span><input type="text" class="form-control"
                                       style="display: inline-block; width: 20%; margin-right: 20px;"
                                       name="cardPwd"
                                       value="${cardPwd}"
                                       placeholder="请输入患者的健康卡">
                <span>姓名：</span><input type="text" class="form-control"
                                       style="display: inline-block; width: 20%; margin-right: 20px;"
                                       name="name"
                                       value="${name}"
                                       placeholder="请输入患者姓名">
                <span>电话：</span><input type="text" class="form-control" style="display: inline-block; width: 20%;"
                                       name="phone"
                                       value="${phone}"
                                       placeholder="请输入患者电话号码">
                <button type="submit" class="btn btn-success" id="btnSubmit" style="margin-right: 20px;">搜索</button>
            </div>
            <table class="adv-table" width="100%">
                <colgroup width="6%"></colgroup>
                <colgroup width="8%"></colgroup>
                <colgroup width="6%"></colgroup>
                <colgroup width="5%"></colgroup>
                <colgroup width="12%"></colgroup>
                <colgroup width="15%"></colgroup>
                <colgroup width="12%"></colgroup>
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
                    <th>初步诊断</th>
                    <th>检查项目</th>
                    <th>就诊时间</th>
                    <th>检查医生</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${emrJClassAdviceDictPage.content}" var="oneEmrJClass" varStatus="status">
                    <tr>
                        <td>
                                ${status.count +(emrJClassAdviceDictPage.number * emrJClassAdviceDictPage.getSize())}
                        </td>
                        <td>
                                ${oneEmrJClass.emr.patientName}
                        </td>
                        <td>
                                ${genderMap[oneEmrJClass.emr.patient.gender]}
                        </td>
                        <td>
                                ${oneEmrJClass.patient.getAge()}
                        </td>
                        <td>
                                ${oneEmrJClass.patient.mobile}
                        </td>
                        <td>
                            <c:forEach items="${oneEmrJClass.emr.diagnosisList}" var="oneDiagnosis">
                                <span class="text-num" title="${oneDiagnosis.name}">${oneDiagnosis.name}</span>
                            </c:forEach>
                        </td>
                        <td>
                                ${oneEmrJClass.adviceName}
                        </td>
                        <td>
                            <fmt:formatDate value="${oneEmrJClass.emr.createOn}" pattern="yyyy/MM/dd HH:mm"/>
                        </td>
                        <td>${oneEmrJClass.emr.doctor.name}</td>
                        <td>
                            <button class="btn btn-default" type="button"
                                    onclick="inspectionResultFinish( ${oneEmrJClass.id})">查看报告
                            </button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>

            </table>
        </div>
    </form>
    <div class="text-center" style="margin-bottom: 20px;">
        <c:if test="${emrJClassAdviceDictPage.number > 0}">

            <button type="button" class="btn btn-default"
                    onclick="fn_LoadRpList(${emrJClassAdviceDictPage.number - 1})"
                    style="width: 100px; height: 50px; margin-right: 15px;">上一页
            </button>

        </c:if>

        <c:if test="${emrJClassAdviceDictPage.number + 1 < emrJClassAdviceDictPage.totalPages}">
            <button type="button" class="btn btn-default"
                    onclick="fn_LoadRpList(${emrJClassAdviceDictPage.number + 1})"
                    style="width: 100px; height: 50px;">下一页
            </button>
        </c:if>


        &nbsp;&nbsp;&nbsp;&nbsp;
        第 <input type="text" class="form-control"
                 style="width:50px; text-align: center; display: inline-block;"
                 id="currentPage" value="${emrJClassAdviceDictPage.number + 1}">页/共
        <span>${emrJClassAdviceDictPage.totalPages}</span>页
        <a href="#" class="btn btn-success" onclick="javascript:goToThisPage();">跳转 </a>
    </div>
</div>
<%@ include file="/WEB-INF/layouts/advfooter.jsp" %>
</body>
</html>
