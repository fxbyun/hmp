<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/9/20
  Time: 15:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/advInclude.jsp" %>
<%@ taglib prefix="hmp" uri="/WEB-INF/TLD/HmpLoadStaticFile.tld" %>
<html>
<head>
    <title>智能补货</title>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/advertising/styles/validity.css" type="css"/>
    <%--<hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/application.js" type="js"/>--%>
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
    <div class="adv-container validityManage saveValList replenish" id="ZN_HTML">
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
                        <input type="text" id="medNameInput" class="form-control"
                               style="display: inline-block; width: 15%;"
                               placeholder="输入您要查询的药品名称">
                        <input type="text" id="iaiIntoId" value="${iaiInto.id}" style="display: none;"/>
                        <input type="text" id="fromHtml" value="replenishment" style="display: none;"/>
                        <button class="btn btn-defalut" type="button" onclick="searchMed(${iaiInto.id})">搜索</button>
                        <a href="javascript:" onclick="addMedicine()" class="btn btn-success"
                           style="display: inline;">新增药品</a>
                        <div class="list-medicine clearfix">
                            <a class="prev fa fa-chevron-left"></a>
                            <div class="list-tab" id="medListDiv">
                                <div class="scroll_demo" onmousewheel="showKey()">
                                    <li>
                                        <a href="javascript:" onclick="saveMedicine()">
                                            <span class="tag tag-i span-tag">5%葡萄糖液</span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="javascript:" onclick="saveMedicine(1234,22)">
                                            <span class="tag tag-i span-tag">人参</span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="javascript:" onclick="saveMedicine(1047,22)">
                                            <span class="tag tag-i span-tag">半夏</span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="javascript:" onclick="saveMedicine(2702,22)">
                                            <span class="tag tag-i span-tag">女贞</span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="javascript:" onclick="saveMedicine(2703,22)">
                                            <span class="tag tag-i span-tag">旱莲</span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="javascript:" onclick="saveMedicine(2704,22)">
                                            <span class="tag tag-i span-tag">败酱</span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="javascript:" onclick="saveMedicine(1841,22)">
                                            <span class="tag tag-i span-tag">氢化可的松</span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="javascript:" onclick="saveMedicine(1915,22)">
                                            <span class="tag tag-i span-tag">双八面体蒙脱石（思密达）</span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="javascript:" onclick="saveMedicine(1942,22)">
                                            <span class="tag tag-i span-tag">双歧三联活菌片（金双歧）</span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="javascript:" onclick="saveMedicine(959,22)">
                                            <span class="tag tag-i span-tag">*九香虫</span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="javascript:" onclick="saveMedicine(941,22)">
                                            <span class="tag tag-i span-tag">*合欢花</span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="javascript:" onclick="saveMedicine(2354,22)">
                                            <span class="tag tag-i span-tag"> 阿昔洛韦膏</span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="javascript:" onclick="saveMedicine(985,22)">
                                            <span class="tag tag-i span-tag">*三丫苦</span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="javascript:" onclick="saveMedicine(1838,22)">
                                            <span class="tag tag-i span-tag">盐酸氨溴索口服液</span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="javascript:" onclick="saveMedicine(677,22)">
                                            <span class="tag tag-i span-tag">头孢拉定胶囊</span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="javascript:" onclick="saveMedicine(3200,22)">
                                            <span class="tag tag-i span-tag">必咳平</span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="javascript:" onclick="saveMedicine(3153,22)">
                                            <span class="tag tag-i span-tag">焦三仙</span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="javascript:" onclick="saveMedicine(3519,22)">
                                            <span class="tag tag-i span-tag">射干麻黄汤</span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="javascript:" onclick="saveMedicine(1726,22)">
                                            <span class="tag tag-i span-tag">普伐他汀胶囊</span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="javascript:" onclick="saveMedicine(4829,22)">
                                            <span class="tag tag-i span-tag">小儿抗病毒 口服 液</span>
                                        </a>
                                    </li>
                                </div>
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
                <tr onclick="saveMedicine(${detail.medicine.medicine.id},${detail.iaiInto.id},${detail.id},'replenishment')">
                    <td>${status.index+1}</td>
                    <td>${detail.medicine.name}</td>
                    <td><span class="val-text">${companyMap[detail.id]}</span></td>
                    <td>${detail.barCode}</td>
                        <%--<td><fmt:formatDate value="${detail.validityDate}" pattern="yyyy/MM/dd" /></td>--%>
                        <%--<td>${detail.bayingPrice}</td>--%>
                        <%--<td>${detail.medicine.price}</td>--%>
                    <td>${detail.totalNumber}</td>
                        <%--<td>${detail.warnLine}</td>--%>
                    <td>${detail.medicine.standard}</td>
                    <td>${medicineUnits[detail.medicine.unit]}</td>
                        <%--<td>${detail.bayingPrice*detail.totalNumber}</td>--%>
                    <td><a href="javascript:" onclick="deleteValListTr(this,${detail.id})">删除</a></td>
                </tr>
            </c:forEach>
            <tr onclick="saveMedicine()">
                <td>1</td>
                <td>九虫香</td>
                <td><span class="val-text">天津药业</span></td>
                <td>123333</td>
                <td>100</td>
                <td>15g</td>
                <td>g</td>
                <td><a href="javascript:" onclick="deleteValListTr(this,${detail.id})">删除</a></td>
            </tr>
            </tbody>

        </table>

    </div>
    <div class="text-center" style="margin-bottom: 20px;">
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
<%@ include file="/WEB-INF/layouts/advfooter.jsp" %>
<script>
    $(function () {
        $("#btnSaveIaiInto").click(function () {
            var iaiIntoId = $("#iaiIntoId").val();
            $.postJSON("${ctx}/validityManage/replenishment/saveReplenish", {"iaiIntoId": iaiIntoId}, function (data) {
                if (data.success) {
                    layer.msg("智能补货单，保存成功！");
                } else {
                    layer.msg("智能补货单，保存失败！");
                }
            })
        });
    })

    function fn_GoDetail(page) {
        var medName = $("#medName").val();
        var url = "${ctx}/validityManage/replenishment?&page={1}".format(page);
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
