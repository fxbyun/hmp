<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/10/9
  Time: 11:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/advInclude.jsp" %>
<%@ taglib prefix="hmp" uri="/WEB-INF/TLD/HmpLoadStaticFile.tld" %>
<html>
<head>
    <title>补货订单详情</title>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/advertising/styles/validity.css" type="css"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/validityManage/validity.js" type="js"/>
    <style>
        table {
            border: 1px solid #ccc;
        }

        th, td {
            text-align: center;
            height: 30px;
        }

        tr.active {
            background-color: #f9e6cc;
        }

        tbody tr {
            cursor: pointer;
        }
    </style>
    <script>
        $(function () {
            $("tbody tr").click(function () {
                $("tbody tr").removeClass("active");
                $(this).addClass("active");
            });
        })
    </script>
</head>
<body style="background-color: #fff;">
<div style="padding: 15px 20px;">
    <div style="height: 180px;">
        <table width="100%" border="1">
            <colgroup width="20%"></colgroup>
            <colgroup width="80%"></colgroup>
            <thead>
            <tr>
                <th>序号</th>
                <th>订单单号</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${replenishList}" var="rep" varStatus="status">
                <tr id="${rep.id}">
                    <td>${status.index+1}</td>
                    <td>${rep.goodsNo}</td>
                </tr>
            </c:forEach>
            <tr>
                <td>1</td>
                <td>10021212</td>
            </tr>
            <tr>
                <td>2</td>
                <td>10021212</td>
            </tr>
            </tbody>
        </table>
    </div>
    <p class="text-right" style="margin: 12px 0 20px;">
        <button type="button" onclick="fn_LoadRpList(${replenishPage.number - 1})" class="btn btn-default">上一页</button>
        <button type="button" onclick="fn_LoadRpList(${replenishPage.number + 1},${replenishPage.totalPages})"
                class="btn btn-default">下一页
        </button>
    </p>
    <p class="text-center" style="margin: 0;">
        <button type="button" id="loadReplenish" class="btn btn-success" style="width: 70px; margin-right: 10px;">载入
        </button>
        <button id="btnClose" type="button" class="btn btn-default" style="width: 70px;">取消</button>
    </p>
</div>
<style>
    .select_replenish {
        background: #5cb85c;
    }
</style>


<script>
    $(function () {
        $("#loadReplenish").click(function () {
            var replenishId = $("tbody").children("tr.active").attr("id")
            var iaiIntoId = parent.$("#iaiIntoId").val();
            var url = "${ctx}/adv/validityManage/bombBox/loadReplenish";
            $.postJSON(url, {"replenishId": replenishId, "iaiIntoId": iaiIntoId}, function (data) {
                if (data.success) {
                    layer.msg("载入中。。。");
                    parent.location.reload();
                    parent.layer.closeAll();
                } else {
                    layer.msg("载入失败！");
                }
            })
        });
    })

    function selectReplenish(even) {

    }

    function fn_GoDetail(page) {
        var url = "${ctx}/adv/validityManage/bombBox/showReplenish?page={0}".format(page);
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

</script>
</body>
</html>
