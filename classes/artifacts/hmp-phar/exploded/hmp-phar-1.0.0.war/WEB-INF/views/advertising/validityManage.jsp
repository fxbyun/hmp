<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/9/19
  Time: 9:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/advInclude.jsp" %>
<%@ taglib prefix="hmp" uri="/WEB-INF/TLD/HmpLoadStaticFile.tld" %>
<html>
<head>
    <title>有效期管理</title>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/advertising/styles/validity.css" type="css"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/validityManage/validity.js" type="js"/>
    <%--<hmp:HmpLoadFile ctx="${ctx}" url="/assets/math/uuid.js" type="js"/>--%>
</head>
<body>
<%@ include file="/WEB-INF/layouts/advheader.jsp" %>
<div class="container chargeManage">
    <ul class="navigation">
        <li class="active"><a href="${ctx}/adv/validityManage" class="btn btn-default">库存管理</a></li>
        <li><a href="${ctx}/adv/validityManage/lossRecord" class="btn btn-default">损耗记录</a></li>
        <li><a href="${ctx}/adv/validityManage/entryRecord" class="btn btn-default">入货库记录</a></li>
    </ul>

    <div class="adv-container validityManage" id="KC_HTML">
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
                <td colspan="4" class="val-fa-color">
                    <span><i class="fa fa-square"></i>快过期药品</span>
                    <span><i class="fa fa-square"></i>需补充药品</span>
                    <span><i class="fa fa-square"></i>已过期药品</span>
                </td>
                <td colspan="8" class="val-btn">
                    <input type="text" name="medName" id="medName" value="${medName}" class="form-control"
                           style="display: inline; width: 25%;"
                           placeholder="输入您要查询的药品名">
                    <button id="btnSearch" class="btn btn-default" type="button">搜索</button>
                    <a class="btn btn-default" href="${ctx}/adv/validityManage/overdueMedicine">过期提醒</a>
                    <a class="btn btn-default" id="replenishDetail" onclick="replenishDetail()">智能补货</a>
                    <a class="btn btn-default" href="${ctx}/adv/validityManage/repOrders">智能订单</a>
                </td>
            </tr>
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
            <%--<c:forEach items="${details}" var="detail" varStatus="status">
                <tr onclick="saveMedicine(${detail.medicine.medicine.id},${detail.iaiInto.id},${detail.id},'validityManage')">
                    <td>${status.index+1}</td>
                    <td>${detail.medicine.name}</td>
                    <td><span class="val-text">${companyMap[detail.id]}</span></td>
                    <td>${detail.barCode}</td>
                    <td class="<c:if test="${expireMap[detail.id]=='Expire'}">
                                bg-color-ff
                               </c:if>
                               <c:if test="${expireMap[detail.id]=='fastExpire'}">
                                bg-color-f9
                               </c:if>
                    ">
                        <fmt:formatDate value="${detail.validityDate}" pattern="yyyy/MM/dd" />
                    </td>
                    <td>${detail.bayingPrice}</td>
                    <td>${detail.medicine.price}</td>
                    <td class="<c:if test="${lackMap[detail.id]}">bg-color-7c</c:if>">${detail.totalNumber}</td>
                    <td>${detail.warnLine}</td>
                    <td>${detail.medicine.standard}</td>
                    <td>${medicineUnits[detail.medicine.unit]}</td>
                    <td>${detail.totalNumber*detail.bayingPrice}</td>
                </tr>
            </c:forEach>--%>
            <tr onclick="saveMedicine()">
                <td>01</td>
                <td>感冒灵</td>
                <td><span class="val-text">太和药厂</span></td>
                <td>123456789</td>
                <td class="bg-color-ff">2016-10-22</td>
                <td>5.00</td>
                <td>10.00</td>
                <td>200</td>
                <td>20</td>
                <td>盒</td>
                <td>盒</td>
                <td>500.00</td>
            </tr>
            <tr onclick="saveMedicine()">
                <td>02</td>
                <td>感冒灵</td>
                <td><span class="val-text">太和药厂</span></td>
                <td>123456789</td>
                <td>2016-12-22</td>
                <td>5.00</td>
                <td>10.00</td>
                <td class="bg-color-7c">200</td>
                <td>20</td>
                <td>盒</td>
                <td>盒</td>
                <td>500.00</td>
            </tr>
            <tr onclick="saveMedicine()">
                <td>03</td>
                <td>感冒灵</td>
                <td><span class="val-text">太和药厂</span></td>
                <td>123456789</td>
                <td class="bg-color-f9">2016-12-22</td>
                <td>5.00</td>
                <td>10.00</td>
                <td>200</td>
                <td>20</td>
                <td>盒</td>
                <td>盒</td>
                <td>500.00</td>
            </tr>

            </tbody>

        </table>
    </div>
    <div class="text-center" style="margin-bottom: 20px;">
        <button type="button" onclick="fn_LoadRpList(${detailPage.number - 1})" class="btn btn-default"
                style="width: 100px; height: 50px; margin-right: 15px;">上一页
        </button>
        <button type="button" onclick="fn_LoadRpList(${detailPage.number + 1},${detailPage.totalPages})"
                class="btn btn-default"
                style="width: 100px; height: 50px;">下一页
        </button>
        &nbsp;&nbsp;&nbsp;&nbsp;
        第 <input type="text" class="form-control" id="currentPage"
                 value="${iaiIntoPage.number+1}">页/共<span>1</span>页
        <a href="#" onclick="javascript:goToThisPage();" class="btn btn-success">跳转 </a>
    </div>
</div>
<%@ include file="/WEB-INF/layouts/advfooter.jsp" %>
<script>
    $(function () {
        $("#btnSearch").click(function () {
            var medName = $("#medName").val();
            var url = "${ctx}/adv/validityManage?medName={0}".format(medName);
            location.href = url;
        })
    })

    function replenishDetail() {
//        var addUUID = Math.uuidFast();

        <%--var url = "${ctx}/adv/validityManage/replenishment?uuid={0}".format(addUUID);--%>
        location.href = "${ctx}/adv/validityManage/replenishment";
    }


    function fn_GoDetail(page) {
        var medName = $("#medName").val();
        var url = "${ctx}/adv/validityManage?medName={0}&page={1}".format(medName, page);
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
        if (num < 1) {
            page = 1;
        }
        if (num == "") {
            page = 1;
        }
        if (isNaN(num)) {
            page = 1;
        }
        fn_GoDetail(page);
    }

</script>


</body>
</html>
