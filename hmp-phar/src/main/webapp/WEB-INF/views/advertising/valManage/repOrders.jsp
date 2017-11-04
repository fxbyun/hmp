<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/9/20
  Time: 15:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/advInclude.jsp" %>
<%@ taglib prefix="hmp" uri="/WEB-INF/TLD/HmpLoadStaticFile.tld" %>
<html>
<head>
    <title>补货订单</title>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/advertising/styles/validity.css" type="css"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/validityManage/validity.js" type="js"/>
</head>
<body>
<%@ include file="/WEB-INF/layouts/advheader.jsp" %>
<div class="container">
    <ul class="navigation">
        <li class="active"><a href="${ctx}/adv/validityManage" class="btn btn-default">库存管理</a></li>
        <li><a href="${ctx}/adv/validityManage/lossRecord" class="btn btn-default">损耗记录</a></li>
        <li><a href="${ctx}/adv/validityManage/entryRecord" class="btn btn-default">入货库记录</a></li>
    </ul>
    <div class="adv-container validityManage entryRecord repOrders">
        <table class="rp-table" width="100%">
            <colgroup width="20%"></colgroup>
            <colgroup width="30%"></colgroup>
            <colgroup width="30%"></colgroup>
            <colgroup width="20%"></colgroup>
            <thead>
            <tr>
                <td colspan="7" class="val-btn">
                    <input type="text" class="form-control" style="display: inline; width: 25%;"
                           placeholder="输入您要查询的单号">
                    <button class="btn btn-default" type="button">搜索</button>
                    <a class="btn btn-success" href="javascript;" onclick="javascript:history.go(-1);">返回</a>
                </td>
            </tr>
            <tr>
                <th>序号</th>
                <th>订单单号</th>
                <th>订单生成时间</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${iaiIntoRepList}" var="iai" varStatus="status">
                <tr onclick="showDetail('${iai.id}')">
                    <td>${status.index+1}</td>
                    <td>${iai.goodsNo}</td>
                    <td><fmt:formatDate value="${iai.createDate}" pattern="yyyy/MM/dd"/></td>
                    <td><a href="javascript:" onclick="deleteIaiIntoRep(this,${iai.id})">删除</a></td>
                </tr>
            </c:forEach>
            <tr onclick="showDetail()">
                <td>1</td>
                <td>ZN2016110447-00000014</td>
                <td>2016-12-22</td>
                <td><a href="javascript:" onclick="deleteIaiIntoRep(this,${iai.id})">删除</a></td>
            </tr>
            </tbody>
        </table>
    </div>
    <div class="text-center" style="margin-bottom: 20px;">
        <button type="button" onclick="fn_LoadRpList(${IaiIntoRepPage.number - 1})" class="btn btn-default"
                style="width: 100px; height: 50px; margin-right: 15px;">上一页
        </button>
        <button type="button" onclick="fn_LoadRpList(${IaiIntoRepPage.number + 1},${IaiIntoRepPage.totalPages})"
                class="btn btn-default"
                style="width: 100px; height: 50px;">下一页
        </button>
        &nbsp;&nbsp;&nbsp;&nbsp;
        第 <input type="text" class="form-control" style="width:50px; text-align: center;" id="currentPage"
                 value="${iaiIntoPage.number+1}">页/共<span>1</span>页
        <a href="#" onclick="javascript:goToThisPage();" class="btn btn-success">跳转 </a>
    </div>
</div>
<%@ include file="/WEB-INF/layouts/advfooter.jsp" %>
<script>
    $(function () {
        $("#btnSearch").click(function () {
            var goodsNo = $("#goodsNo").val();
            var url = "${ctx}/validityManage/repOrders?goodsNo={0}".format(goodsNo);
            location.href = url;
        })
    })

    function fn_GoEntryRecord(page) {
        var goodsNo = $("#goodsNo").val();
        var url = "${ctx}/validityManage/repOrders?goodsNo={0}&page={1}".format(goodsNo, page);
        location.href = url;
    }


    function showDetail(iaiIntoId) {
        <%--var url = "${ctx}/adv/validityManage/goIaiIntoDetailRep?iaiIntoId={0}".format(iaiIntoId);--%>
        location.href = "${ctx}/adv/validityManage/goIaiIntoDetailRep";
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

        fn_GoEntryRecord(page)
    }

    function goToThisPage() {
        var num = $("#currentPage").val();
        var page = parseInt(num) - 1;
        if (page < 0) {
            page = 1;
        }
        fn_GoEntryRecord(page);
    }

</script>
</body>
</html>
