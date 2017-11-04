<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/advInclude.jsp" %>
<%@ taglib prefix="hmp" uri="/WEB-INF/TLD/HmpLoadStaticFile.tld" %>
<html>
<head>
    <title>入货库记录</title>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/advertising/styles/validity.css" type="css"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/validityManage/validity.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/math/uuid.js" type="js"/>
</head>
<body>
<%@ include file="/WEB-INF/layouts/advheader.jsp" %>
<div class="container">
    <ul class="navigation">
        <li><a href="${ctx}/adv/validityManage" class="btn btn-default">库存管理</a></li>
        <li><a href="${ctx}/adv/validityManage/lossRecord" class="btn btn-default">损耗记录</a></li>
        <li class="active"><a href="${ctx}/adv/validityManage/entryRecord" class="btn btn-default">入货库记录</a></li>
    </ul>
    <div class="adv-container validityManage entryRecord">
        <table class="rp-table" width="100%">
            <colgroup width="10%"></colgroup>
            <colgroup width="20%"></colgroup>
            <colgroup width="20%"></colgroup>
            <colgroup width="20%"></colgroup>
            <colgroup width="10%"></colgroup>
            <colgroup width="10%"></colgroup>
            <colgroup width="5%"></colgroup>
            <colgroup width="5%"></colgroup>
            <thead>
            <tr>
                <td colspan="7" class="val-btn">
                    <input type="text" name="goodsNo" id="goodsNo" value="${goodsNo}" class="form-control"
                           style="display: inline; width: 25%;"
                           placeholder="输入您要查询的进货单单号">
                    <button class="btn btn-default" id="btnSearch" type="button">搜索</button>
                    <a class="btn btn-success" id="toAddIaiDetail" href="javascript:void(0);"
                       onclick="toAddIaiDetail()">新增入库单</a>
                </td>
            </tr>
            <tr>
                <th>序号</th>
                <th>进货单单号</th>
                <th>进货日期</th>
                <th>供应商</th>
                <th>填表人</th>
                <th>进货金额</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${iaiIntoList}" var="iai" varStatus="status">
                <tr onclick="showDetail('${iai.uuid}')">
                    <td>${status.index+1}</td>
                    <td>${iai.goodsNo}</td>
                    <td><fmt:formatDate value="${iai.createDate}" pattern="yyyy/MM/dd"/></td>
                    <td>${iai.supplier.name}</td>
                    <td>${iai.whoCreate}</td>
                    <td>${iai.totalMoney}</td>
                    <td><a href="javascript:" onclick="deleteIaiInto(this,${iai.id})">删除</a></td>
                </tr>
            </c:forEach>
            <tr onclick="showDetail()">
                <td>1</td>
                <td>RK2016110447-00000018</td>
                <td>2016-12-22</td>
                <td>KFC</td>
                <td>张三</td>
                <td>200.0</td>
                <td><a href="javascript:" onclick="deleteIaiInto(this,${iai.id})">删除</a></td>
            </tr>
            <tr onclick="showDetail()">
                <td>2</td>
                <td>RK2016110447-00000018</td>
                <td>2016-12-22</td>
                <td>KFC</td>
                <td>张三</td>
                <td>200.0</td>
                <td><a href="javascript:" onclick="deleteIaiInto(this,${iai.id})">删除</a></td>
            </tr>
            </tbody>
        </table>
    </div>
    <div class="text-center" style="margin-bottom: 20px;">
        <button type="button" onclick="fn_LoadRpList(${iaiIntoPage.number - 1})" class="btn btn-default"
                style="width: 100px; height: 50px; margin-right: 15px;">上一页
        </button>
        <button type="button" onclick="fn_LoadRpList(${iaiIntoPage.number + 1},${iaiIntoPage.totalPages})"
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
            var url = "${ctx}/validityManage/entryRecord?goodsNo={0}".format(goodsNo);
            location.href = url;
        })
    })

    function fn_GoEntryRecord(page) {
        var goodsNo = $("#goodsNo").val();
        var url = "${ctx}/validityManage/entryRecord?goodsNo={0}&page={1}".format(goodsNo, page);
        location.href = url;
    }


    function toAddIaiDetail() {
        var url = "${ctx}/validityManage/isHistory";
        $.postJSON(url, function (data) {
            if (data.success) {
                //#FangXB 你有一张为保存的入库单是否继续编辑
                var iaiUUID = data.data;
                layer.confirm("你有一张历史入库单，是否继续编辑？", {
                    btn: ["新增", "继续编辑"],
                    btn1: function () {
                        var addUUID = Math.uuidFast();
                        var addUrl = "${ctx}/validityManage/saveValList?addUUID={0}".format(addUUID);
                        location.href = addUrl;
                    },
                    btn2: function () {
                        var editUrl = "${ctx}/validityManage/saveValList?addUUID={0}".format(iaiUUID);
                        location.href = editUrl;
                    }
                });
            } else {
                var addUUID = Math.uuidFast();
                var url = "${ctx}/validityManage/saveValList?addUUID={0}".format(addUUID);
                location.href = url;
            }


        })


    }

    function showDetail(uuid) {
        <%--var url = "${ctx}/adv/validityManage/saveValList?addUUID={0}".format(uuid);--%>
        location.href = '${ctx}/adv/validityManage/saveValList';
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
