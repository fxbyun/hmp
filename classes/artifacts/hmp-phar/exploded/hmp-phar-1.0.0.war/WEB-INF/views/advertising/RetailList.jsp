<%--@elvariable id="retail" type="com.qiaobei.hmp.modules.entity.Retail"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/advInclude.jsp" %>
<%@ taglib prefix="hmp" uri="/WEB-INF/TLD/HmpLoadStaticFile.tld" %>
<!DOCTYPE html>
<html>
<head>
    <title>零售列表</title>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/advertising/scripts/advertisingIndex.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/retail.js" type="js"/>
    <script type="application/javascript">
        $(function () {
            $("#nav-charge").addClass("active");
        })
    </script>
</head>
<body>
<%@ include file="/WEB-INF/layouts/advheader.jsp" %>
<div class="container chargeManage">
    <div class="navigation">
        <ul class="clearfix">
            <li><a href="${ctx}/adv/chargeManage" class="btn btn-default">待收费列表</a></li>
            <li><a href="${ctx}/adv/chargeManage?status=CHARGE" class="btn btn-default">已收费列表</a></li>
            <li><a href="${ctx}/adv/chargeManage?status=HANG_UP" class="btn btn-default">挂起列表</a></li>
            <li><a href="${ctx}/adv/chargeManage?status=Back_Money_Success" class="btn btn-default">退费列表</a></li>
            <li id="retailId" onclick="showBox()"><a href="javascript:;" class="btn btn-default">零售开单</a></li>
            <li class="active"><a href="${ctx}/retail/RetailList" class="btn btn-default">零售列表</a></li>
        </ul>
        <div class="row" style="border-top: 1px solid #ccc; margin-top: 10px;">
            <c:if test="${loginUser.isChiefNurse=='YES'}">
                <p class="data-info">
                    <span>当日零售数据：</span>
                    <span>现已收金额<span>￥${todayRetailMoney}</span></span>
                </p>
            </c:if>
        </div>
    </div>
    <form action="${ctx}/retail/RetailList" method="post" id="formSubmit">
        <div class="adv-container">
            <div class="text-right">
                <input type="text" class="form-control" style="display: inline-block; width: 20%;"
                       name="orderId"
                       id="orderId"
                       value="${orderId}"
                       placeholder="请输入要查询的零售订单号">
                <button type="button" class="btn btn-success" id="btnSearch" style="margin-right: 20px;">搜索</button>
            </div>
            <table class="adv-table" width="100%">
                <colgroup width="15%"></colgroup>
                <colgroup width="20%"></colgroup>
                <colgroup width="18"></colgroup>
                <colgroup width="16%"></colgroup>
                <colgroup width="16%"></colgroup>
                <colgroup width="15%"></colgroup>
                <thead>
                <tr>
                    <th>序号</th>
                    <th>零售订单号</th>
                    <th>销售时间</th>
                    <th>收银员</th>
                    <th>实收金额</th>
                    <%--<th>操作</th>--%>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${retailPage.content}" var="retail" varStatus="status">
                    <tr onclick="retailDetails(${retail.id},${retail.orderId})">
                        <td>${status.index+1}</td>
                        <td>${retail.orderId}</td>
                        <td><fmt:formatDate value="${retail.chargeTime}" pattern="yyyy/MM/dd HH:mm"/></td>
                        <td>${retail.pharmacist.name}</td>
                        <td>${retail.realCost}</td>
                            <%--<td><a href="javascript:;" onclick="delRetailTr(this)">删除</a></td>--%>
                    </tr>
                </c:forEach>
                </tbody>

            </table>
        </div>
        <div class="text-center" style="margin-bottom: 20px;">

            <c:if test="${retailPage.number > 0}">

                <button type="button" onclick="fn_LoadRpList(${retailPage.number - 1})"
                        class="btn btn-default" style="width: 100px; height: 50px; margin-right: 15px;">上一页
                </button>
            </c:if>
            <c:if test="${retailPage.number + 1 < retailPage.totalPages}">
                <button type="button" onclick="fn_LoadRpList(${retailPage.number + 1})"
                        class="btn btn-default" style="width: 100px; height: 50px;">下一页
                </button>

            </c:if>

            &nbsp;&nbsp;&nbsp;&nbsp;
            第 <input type="text" class="form-control" style="width:50px; text-align: center; display: inline-block;"
                     id="currentPage" value="${retailPage.number + 1}">页/共<span>${retailPage.totalPages}</span>页
            <a href="#" class="btn btn-success" onclick="javascript:goToThisPage();">跳转 </a>
        </div>
    </form>
</div>
<%@ include file="/WEB-INF/layouts/advfooter.jsp" %>
<script>
    $(function () {
        $("#btnSearch").click(function () {
            var orderId = $("#orderId").val();
            var url = "${ctx}/retail/RetailList?orderId={0}".format(orderId);
            location.href = url;
        })
    })

    function fn_GoDetail(page) {
        var orderId = $("#orderId").val();
        var url = "${ctx}/retail/RetailList?orderId={0}&page={1}".format(orderId, page);
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
        var page = parseInt(num) - 1;
        if (page < 0) {
            page = 1;
        }
        fn_GoDetail(page);
    }

</script>


</body>
</html>
