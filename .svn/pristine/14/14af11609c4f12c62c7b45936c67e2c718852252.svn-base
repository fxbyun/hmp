<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/9/20
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="hmp" uri="/WEB-INF/TLD/HmpLoadStaticFile.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>过期药品提醒</title>
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
                <li class="active"><a href="${ctx}/validityManage" class="btn btn-default">库存管理</a></li>
                <li><a href="${ctx}/validityManage/lossRecord" class="btn btn-default">损耗记录</a></li>
                <li><a href="${ctx}/validityManage/entryRecord" class="btn btn-default">入货库记录</a></li>
            </ul>
        </ul>
        <div class="validityManage overdueMedicine tabbable">
            <div class="over-seach">
                <input type="text" name="medName" id="medName" value="${medName}" class="form-control" style="display: inline; width: 25%;" placeholder="输入您要查询的药品名">
                <button id="btnSearch" class="btn btn-default" type="button">搜索</button>
                <ul class="nav nav-tabs" role="tablist" style="float:right;">
                    <li class="active"><a class="btn btn-default" href="#soonTable" data-toggle="tab">即将过期药品</a></li>
                    <li><a class="btn btn-default" href="#isTable" data-toggle="tab">已过期药品</a></li>
                </ul>
            </div>
            <div class="tab-content">
                <div id="soonTable" class="tab-pane active">
                    <table class="rp-table" width="100%">
                        <colgroup width="5%"></colgroup>
                        <colgroup width="10%"></colgroup>
                        <colgroup width="12%"></colgroup>
                        <colgroup width="12%"></colgroup>
                        <colgroup width="10%"></colgroup>
                        <colgroup width="7%"></colgroup>
                        <colgroup width="7%"></colgroup>
                        <colgroup width="10%"></colgroup>
                        <colgroup width="5%"></colgroup>
                        <colgroup width="8%"></colgroup>
                        <colgroup width="6%"></colgroup>
                        <colgroup width="8%"></colgroup>
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>药品名称</th>
                            <th>药厂</th>
                            <th>条码</th>
                            <th>有效期</th>
                            <th>进货价(元)</th>
                            <th>零售价(元)</th>
                            <th>库存数量</th>
                            <th>预警线</th>
                            <th>规格</th>
                            <th>统计单位</th>
                            <th>总额</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${fastExpires}" var="fastExpire" varStatus="status">
                        <tr onclick="saveMedicine(${fastExpire.medicine.medicine.id},${fastExpire.iaiInto.id},${fastExpire.id},'overdueMedicine')">
                            <td>${status.index+1}</td>
                            <td>${fastExpire.medicine.name}</td>
                            <td><span class="val-text">${fastExpireCompanyMap[fastExpire.id]}</span></td>
                            <td>${fastExpire.barCode}</td>
                            <td>
                            <fmt:formatDate value="${fastExpire.validityDate}" pattern="yyyy/MM/dd" />
                            </td>
                            <td>${fastExpire.bayingPrice}</td>
                            <td>${fastExpire.medicine.price}</td>
                            <td>${fastStockMap[fastExpire.id]}</td>
                            <td>${fastExpire.warnLine}</td>
                            <td>${fastExpire.medicine.standard}</td>
                            <td>${medicineUnits[fastExpire.medicine.unit]}</td>
                            <td>${fastStockMap[fastExpire.id]*fastExpire.bayingPrice}</td>
                        </tr>
                        </c:forEach>
                        </tbody>


                    </table>
                    <div class="text-center">
                        <button type="button" onclick="fn_LoadRpList(${detailPage.number - 1})" class="btn btn-default">上一页</button>
                        <button type="button" onclick="fn_LoadRpList(${fastExpirePage.number + 1},${fastExpirePage.totalPages})" class="btn btn-default">下一页</button>
                        <button type="button" class="btn btn-success">打印订单</button>
                        <button type="button" class="btn btn-default" style="width: 80px;"
                                onclick="javascript:history.go(-1);">
                            返回
                        </button>
                    </div>
                </div>


                <%--我是过期分界线--%>
                <div id="isTable" class="tab-pane">
                    <table class="rp-table" width="100%">
                        <colgroup width="5%"></colgroup>
                        <colgroup width="10%"></colgroup>
                        <colgroup width="12%"></colgroup>
                        <colgroup width="12%"></colgroup>
                        <colgroup width="10%"></colgroup>
                        <colgroup width="7%"></colgroup>
                        <colgroup width="7%"></colgroup>
                        <colgroup width="10%"></colgroup>
                        <colgroup width="5%"></colgroup>
                        <colgroup width="8%"></colgroup>
                        <colgroup width="6%"></colgroup>
                        <colgroup width="8%"></colgroup>
                        <thead>
                        <%--<tr>
                            <td colspan="4" class="val-fa-color"></td>
                            <td colspan="8" class="val-btn">

                            </td>
                        </tr>--%>
                        <tr>
                            <th>序号</th>
                            <th>药品名称</th>
                            <th>药厂</th>
                            <th>条码</th>
                            <th>有效期</th>
                            <th>进货价(元)</th>
                            <th>零售价(元)</th>
                            <th>产品数量</th>
                            <th>预警线</th>
                            <th>规格</th>
                            <th>统计单位</th>
                            <th>总额</th>
                        </tr>
                        </thead>
                        <tbody>

                        <c:forEach items="${expires}" var="expire" varStatus="status">
                            <tr onclick="saveMedicine(${expire.medicine.medicine.id},${expire.iaiInto.id},${expire.id},'overdueMedicine')">
                                <td>${status.index+1}</td>
                                <td>${expire.medicine.name}</td>
                                <td><span class="val-text">${expireCompanyMap[expire.id]}</span></td>
                                <td>${expire.barCode}</td>
                                <td>
                                    <fmt:formatDate value="${expire.validityDate}" pattern="yyyy/MM/dd" />
                                </td>
                                <td>${expire.bayingPrice}</td>
                                <td>${expire.medicine.price}</td>
                                <td>${expireStockMap[expire.id]}</td>
                                <td>${expire.warnLine}</td>
                                <td>${expire.medicine.standard}</td>
                                <td>${medicineUnits[expire.medicine.unit]}</td>
                                <td>${expireStockMap[expire.id]*expire.bayingPrice}</td>
                            </tr>
                        </c:forEach>
                        </tbody>


                    </table>
                    <div class="text-center">
                        <button type="button" onclick="fn_LoadRpList(${expirePage.number - 1})" class="btn btn-default">上一页</button>
                        <button type="button" onclick="fn_LoadRpList(${expirePage.number + 1},${expirePage.totalPages})" class="btn btn-default">下一页</button>
                        <button type="button" class="btn btn-success">打印订单</button>
                        <button type="button" class="btn btn-success" onclick="toAddLossDetail()">生成损耗单</button>
                        <button type="button" class="btn btn-default" style="width: 80px;"
                                onclick="javascript:history.go(-1);">
                            返回
                        </button>
                    </div>
                </div>

            </div>


        </div>
    </div>
</div>
<script>
    $(function () {
        $("#btnSearch").click(function () {
            var medName=$("#medName").val();
            var url = "${ctx}/validityManage/overdueMedicine?medName={0}".format(medName);
            location.href = url;
        })
    })

    /*生成损耗单*/
    function toAddLossDetail() {
        var addUUID = Math.uuidFast();
        var page=0;
        var url = "${ctx}/IaiLossManage/saveIaiLoss?uuid={0}&&page={1}".format(addUUID,page);
        location.href=url;
    }

    function fn_GoDetail(page) {
        var medName=$("#medName").val();
        var url = "${ctx}/validityManage/overdueMedicine?medName={0}&page={1}".format(medName,page);
        location.href=url;
    }

    function fn_LoadRpList(page,totalPage) {
        if(page+1>totalPage){
            layer.msg("这是是最后一页了，已经没有下一页了！");
            return;
        }
        if(page<0){
            layer.msg("这已经是第一页了");
            return;
        }

        fn_GoDetail(page)
    }

    function goToThisPage() {
        var num = $("#currentPage").val();
        var page = parseInt(num) - 1;
        if(page<0){
            page=1;
        }
        fn_GoDetail(page);
    }

</script>
</body>
</html>
