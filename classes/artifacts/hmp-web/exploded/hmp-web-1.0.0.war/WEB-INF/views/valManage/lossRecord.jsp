<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/9/20
  Time: 14:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="hmp" uri="/WEB-INF/TLD/HmpLoadStaticFile.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>损耗记录</title>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/validityManage/validity.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/math/uuid.js" type="js"/>

</head>
<body>
<div class="manage">
    <div class="container">
        <ul class="navigation">
            <li><a href="${ctx}/manage" class="btn btn-default">用药分析</a></li>
            <li><a href="${ctx}/message" class="btn btn-default" onclick="isDebug()">短信发送</a></li>
            <li><a href="${ctx}/rp" class="btn btn-default">我的药方</a></li>
            <li><a href="${ctx}/rplib" class="btn btn-default">药方库</a></li>
            <li><a href="${ctx}/config/symptom" class="btn btn-default">数据整理</a></li>
            <li><a href="${ctx}/appointment" class="btn btn-default">预约挂号</a></li>
            <li class="active"><a href="${ctx}/validityManage" class="btn btn-default">有效期管理</a></li>
            <li><a href="${ctx}/financial" class="btn btn-default">财务统计</a></li>
            <ul class="navigation-sub">
                <li><a href="${ctx}/validityManage" class="btn btn-default">库存管理</a></li>
                <li class="active"><a href="${ctx}/validityManage/lossRecord" class="btn btn-default">损耗记录</a></li>
                <li><a href="${ctx}/validityManage/entryRecord" class="btn btn-default">入货库记录</a></li>
            </ul>
        </ul>
        <div class="validityManage entryRecord">
            <table class="rp-table" width="100%">
                <colgroup width="10%"></colgroup>
                <colgroup width="20%"></colgroup>
                <colgroup width="15%"></colgroup>
                <colgroup width="15%"></colgroup>
                <colgroup width="20%"></colgroup>
                <colgroup width="10%"></colgroup>
                <colgroup width="10%"></colgroup>
                <thead>
                <tr>
                    <td colspan="7" class="val-btn">
                        <input type="text" class="form-control" id="lossNo" value="${lossNo}" style="display: inline; width: 25%;"
                               placeholder="输入您要查询的单号">
                        <button id="btnSearch" class="btn btn-default" type="button">搜索</button>
                        <a class="btn btn-success" href="javascript:void(0)" onclick="toAddLossDetail()">添加损耗</a>
                    </td>
                </tr>
                <tr>
                    <th>序号</th>
                    <th>耗损单号</th>
                    <th>报损时间</th>
                    <th>报损总价</th>
                    <th>备注</th>
                    <th>报损人</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>

                <c:forEach items="${lossList}" var="loss" varStatus="status">
                    <tr onclick="showLoss('${loss.uuid}')">
                        <td>${status.index+1}</td>
                        <td>${loss.lossNo}</td>
                        <td><fmt:formatDate value="${loss.createDate}" pattern="yyyy/MM/dd"/></td>
                        <td>${loss.totalMoney}</td>
                        <td><span class="val-text loss-text">${loss.remark}</span></td>
                        <td>${loss.whoCreate}</td>
                        <td><a href="javascript:" onclick="deleteIaiLoss(this,${loss.id})">删除</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <div class="text-center">
                <button type="button" onclick="fn_LoadRpList(${lossPage.number - 1})" class="btn btn-default"
                        style="width: 100px; height: 50px; margin-right: 15px;">上一页
                </button>
                <button type="button" onclick="fn_LoadRpList(${lossPage.number + 1},${iaiIntoPage.totalPages})" class="btn btn-default"
                        style="width: 100px; height: 50px;">下一页
                </button>
                &nbsp;&nbsp;&nbsp;&nbsp;
                第 <input type="text" class="form-control" style="width:50px; text-align: center;" id="currentPage"
                         value="${lossPage.number + 1}">页/共<span>1</span>页
                <a href="#" onclick="javascript:goToThisPage();" class="btn btn-success">跳转 </a>
            </div>


        </div>
    </div>
</div>
<script>
    $(function () {
        $("#btnSearch").click(function () {
            var lossNo = $("#lossNo").val();
            var url = "${ctx}/validityManage/lossRecord?lossNo={0}".format(lossNo);
            window.location.href=url;
        });




    })


    function toAddLossDetail() {
        var addUUID = Math.uuidFast();
        var page=0;
        var url = "${ctx}/IaiLossManage/saveIaiLoss?uuid={0}&&page={1}".format(addUUID,page);
        location.href=url;
    }

    function showLoss(uuid) {
        var url= "${ctx}/IaiLossManage/saveIaiLoss?uuid={0}".format(uuid);
        window.location.href=url;
    }



</script>
</body>
</html>
