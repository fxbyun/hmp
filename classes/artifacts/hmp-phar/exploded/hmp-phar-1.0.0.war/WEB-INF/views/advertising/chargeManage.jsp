<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/10/11
  Time: 11:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/advInclude.jsp" %>
<%@ taglib prefix="hmp" uri="/WEB-INF/TLD/HmpLoadStaticFile.tld" %>
<html>
<head>
    <title>收费管理</title>
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
            <li class="active"><a href="${ctx}/adv/chargeManage" class="btn btn-default">待收费列表</a></li>
            <li><a href="${ctx}/adv/chargeManage?status=CHARGE" class="btn btn-default">已收费列表</a></li>
            <li><a href="${ctx}/adv/chargeManage?status=HANG_UP" class="btn btn-default">挂起列表</a></li>
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
        <input id="status" type="hidden" name="status"/>

        <div class="adv-container">
            <div class="row">
                <div class="col-xs-12 col-sm-12">
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
                        <span>电话：</span><input type="text" class="form-control"
                                               style="display: inline-block; width: 20%;"
                                               name="phone"
                                               value="${phone}"
                                               placeholder="请输入患者电话号码">
                        <button type="submit" class="btn btn-success" id="btnSubmit" style="margin-right: 20px;">搜索
                        </button>
                    </div>
                    <%@ include file="/WEB-INF/views/advertising/waitChargeTable.jsp" %>
                </div>
            </div>
        </div>
        <div class="text-center" style="margin-bottom: 20px;">

            <c:if test="${emrListPage.number > 0}">

                <button type="button" onclick="loadEmrListPage(${emrListPage.number - 1})"
                        class="btn btn-default" style="width: 100px; height: 50px; margin-right: 15px;">上一页
                </button>
            </c:if>
            <c:if test="${emrListPage.number + 1 < emrListPage.totalPages}">
                <button type="button" onclick="loadEmrListPage(${emrListPage.number + 1})"
                        class="btn btn-default" style="width: 100px; height: 50px;">下一页
                </button>

            </c:if>

            &nbsp;&nbsp;&nbsp;&nbsp;
            第 <input type="text" class="form-control" style="width:50px; text-align: center; display: inline-block;"
                     id="currentPage" value="${emrListPage.number + 1}">页/共<span>${emrListPage.totalPages}</span>页
            <a href="#" class="btn btn-success" onclick="javascript:goToThisPage();">跳转 </a>
        </div>
    </form>
</div>
<%@ include file="/WEB-INF/layouts/advfooter.jsp" %>
</body>
</html>
