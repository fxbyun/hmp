<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/9/20
  Time: 15:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/staticInclude.jsp" %>
<%@ taglib prefix="hmp" uri="/WEB-INF/TLD/HmpLoadStaticFile.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>智能补货</title>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/application.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/validityManage/validity.js" type="js"/>
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
        <div class="validityManage saveValList replenish" id="ZN_HTML">
            <table class="rp-table" width="100%">
                <colgroup width="10%"></colgroup>
                <colgroup width="20%"></colgroup>
                <colgroup width="20%"></colgroup>
                <colgroup width="10%"></colgroup>
                <colgroup width="10%"></colgroup>
                <colgroup width="10%"></colgroup>
                <colgroup width="10%"></colgroup>
                <colgroup width="10%"></colgroup>

                <thead>
                <tr class="order-num">
                    <td colspan="13" class="val-list-h4">
                        <h4>订单123456464</h4>
                    </td>
                </tr>
                <tr style="height: 80px;">
                    <td colspan="13" class="val-list-medicine">
                        <div class="clearfix">
                            <input type="text" id="medNameInput" class="form-control" style="display: inline-block; width: 15%;"
                                   placeholder="输入您要查询的药品名称">
                            <input type="text" id="iaiIntoId" value="${iaiInto.id}" style="display: none;" />
                            <input type="text" id="fromHtml" value="replenishment" style="display: none;" />
                            <button class="btn btn-defalut" type="button" onclick="searchReplenishMed(${iaiInto.id})">
                                搜索
                            </button>
                            <a href="javascript:" onclick="addMedicine()" class="btn btn-success"
                               style="display: inline;">新增药品</a>
                            <div class="list-medicine clearfix">
                                <a class="prev fa fa-chevron-left"></a>
                                <div class="list-tab" id="replenishMedTag">

                                </div>
                                <a class="next fa fa-chevron-right"></a>
                            </div>

                        </div>
                    </td>
                </tr>
                <tr style="background-color: #eeeeee;">
                    <th>序号</th>
                    <th>药品名称</th>
                    <th>药厂</th>
                    <th>条码</th>
                    <th>需补药品数量</th>
                    <th>规格</th>
                    <th>统计单位</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${details}" var="detail" varStatus="status">
                    <tr onclick="openReplenishMedicine(${detail.medicine.medicine.id},${detail.iaiInto.id},${detail.id},'replenishment')">
                        <td>${status.index+1}</td>
                        <td>${detail.medicine.name}</td>
                        <td><span class="val-text">${companyMap[detail.id]}</span></td>
                        <td>${detail.barCode}</td>
                        <%--<td><fmt:formatDate value="${detail.validityDate}" pattern="yyyy/MM/dd" /></td>--%>
                        <%--<td>${detail.bayingPrice}</td>--%>
                        <%--<td>${detail.medicine.price}</td>--%>
                        <td>${detail.incomeQuantity}</td>
                        <%--<td>${detail.warnLine}</td>--%>
                        <td>${detail.medicine.standard}</td>
                        <td>${medicineUnits[detail.medicine.unit]}</td>
                        <%--<td>${detail.bayingPrice*detail.totalNumber}</td>--%>
                        <td><a href="javascript:" onclick="deleteValListTr(this,${detail.id})">删除</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

        </div>
        <div class="text-center">
            <button type="button" onclick="fn_LoadRpList(${detailPage.number - 1})" class="btn btn-default">上一页</button>
            <button type="button" onclick="fn_LoadRpList(${detailPage.number + 1},${detailPage.totalPages})"
                    class="btn btn-default">下一页
            </button>
            <button type="button" id="btnSaveIaiInto" class="btn btn-success" style="width: 80px;">保存</button>
            <button type="button" class="btn btn-success">打印订单</button>
            <button type="button" class="btn btn-default" style="width: 80px;" onclick="javascript:history.go(-1);">
                返回
            </button>
        </div>
    </div>
</div>
<script>
    $(function () {
        var iaiIntoId = $("#iaiIntoId").val()
        $("#btnSaveIaiInto").click(function () {
            //var iaiIntoId=$("#iaiIntoId").val();
            $.postJSON("${ctx}/validityManage/replenishment/saveReplenish",{"iaiIntoId":iaiIntoId},function (data) {
                if(data.success){
                    layer.msg("智能补货单，保存成功！");
                }else {
                    layer.msg("智能补货单，保存失败！");
                }
            })
        });
        selectReplenishMed("", 0, iaiIntoId)




    })

    function fn_GoDetail(page) {
        var medName=$("#medName").val();
        var url = "${ctx}/validityManage/goIaiIntoDetailRep?iaiIntoId={0}&page={1}".format(${iaiInto.id}, page);
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

    function searchReplenishMed(iaiIntoId) {
        selectReplenishMed($("#medNameInput").val(), 0, iaiIntoId);
    }


    function selectReplenishMed(name, page, iaiIntoId) {
        if (!name) {
            name = "";
        }
        if (!page) {
            page = 0;
        }
        var url;
        if (iaiIntoId == undefined) {
            url = "/validityManage/bombBox/getReplenishMedList?name={0}&page={1}".format(name, page);
        } else {
            url = "/validityManage/bombBox/getReplenishMedList?name={0}&page={1}&iaiIntoId={2}".format(name, page, iaiIntoId);
        }

        $("#replenishMedTag").load(url);
    }

</script>


</body>
</html>
