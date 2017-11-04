<%--@elvariable id="retail" type="com.qiaobei.hmp.modules.entity.Retail"--%>
<%--@elvariable id="rm" type="com.qiaobei.hmp.modules.entity.RetailMedicine"--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/advInclude.jsp" %>
<%@ taglib prefix="hmp" uri="/WEB-INF/TLD/HmpLoadStaticFile.tld" %>
<html>
<head>
    <title>零售开单</title>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/advertising/styles/validity.css" type="css"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/advertising/scripts/advertisingIndex.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/validityManage/validity.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/retail.js" type="js"/>
    <script type="application/javascript">
        $(function () {
            $("#nav-charge").addClass("active");
            initTableCheckbox();

            //加载药品滚动条
            loadMedInfoRetail("", 0, ${retail.id}, "");

            $("#btnDel").click(function () {
                var delRetail = [];
                $(".td_check").each(function () {
                    if ($(this).prop("checked")) {
                        var retail = new Object();
                        retail.id = $(this).prop("value");
                        delRetail.push(retail);
                    }
                });

                $.ajax({
                    type: "post",
                    url: "${ctx}/retail/delRetailMed",
                    contentType: "application/json",
                    data: JSON.stringify(delRetail),
                    dataType: "JSON",
                    success: function (data) {
                        if (data.success) {
                            layer.msg(data.msg);
                            location.reload();
                        }
                    }
                })
            });

            //算总计
            $(".retailMoney").each(function () {

            })




        });

        /**/
        function searchRetailMed() {
            loadMedInfoRetail($("#medNameInput").val(), 0, ${retail.id}, $("#barCode").val());
        }

        function initTableCheckbox() {
            var $thr = $('table thead tr');
            var $checkAllTh = $('<th><input style="display: none" type="checkbox" id="checkAll" name="checkAll" /></th>');
            /*“全选/反选”复选框*/
            var $checkAll = $thr.find('input');
            $checkAll.click(function (event) {
                /*将所有行的选中状态设成全选框的选中状态*/
                $tbr.find('input').prop('checked', $(this).prop('checked'));
                var ids = [];
                $('table tbody tr').find('input').each(function () {
                    ids.push($(this).val())
                })
                if ($(this).prop('checked')) {
                    $tbr.find('input').parent().parent().addClass('tr-background');
                } else {
                    $tbr.find('input').parent().parent().removeClass('tr-background');
                }
                /*阻止向上冒泡，以防再次触发点击操作*/
                event.stopPropagation();
            });
            /*点击全选框所在单元格时也触发全选框的点击操作*/
            $checkAllTh.click(function () {
                $(this).find('input').click();
            });
            var $tbr = $('table tbody tr');
            var $checkItemTd = $('<td><input type="checkbox" name="checkItem" /></td>');

            /*点击每一行的选中复选框时*/
            $tbr.find('input').click(function (event) {
                $(this).parent().parent().toggleClass('tr-background');
                /*如果已经被选中行的行数等于表格的数据行数，将全选框设为选中状态，否则设为未选中状态*/
                $checkAll.prop('checked', $tbr.find('input:checked').length == $tbr.length ? true : false);

                /*阻止向上冒泡，以防再次触发点击操作*/
                event.stopPropagation();
            });
            /*点击每一行时也触发该行的选中操作*/
            $tbr.click(function () {
                $(this).find('input').click();
            });
        }

        function fn_EditMedicine(medicineId, type, medicineType, callback) {
            layer.open({
                type: 2,
                title: ['修改药品 - ' + medicineType, 'font-weight:bold'],
                area: ['720px', '600px'],
                scrollbar: false,
                content: '/adv/medicine/edit/' + medicineId,
                end: callback
            });
        }
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
            <li class="active" id="retailId" onclick="showBox()"><a href="javascript:;" class="btn btn-default">零售开单</a>
            </li>
            <li><a href="${ctx}/retail/RetailList" class="btn btn-default">零售列表</a></li>
        </ul>
        <div class="row" style="border-top: 1px solid #ccc; margin-top: 10px;">
            <p class="data-info">
                <span>当日零售数据：</span>
                <span>现已收金额<span>￥${todayRetailMoney}</span></span>
            </p>
        </div>
    </div>

    <div class="adv-container">
        <%--@elvariable id="patient" type="com.qiaobei.hmp.modules.entity.Patient"--%>
        <c:if test="${not empty patient}">
        <div class="patil-info" style="margin: 0 20px 15px;">
            <table class="bomb-table" width="100%" border="1">
                <colgroup width="10%"></colgroup>
                <colgroup width="15%"></colgroup>
                <colgroup width="10%"></colgroup>
                <colgroup width="13%"></colgroup>
                <colgroup width="10%"></colgroup>
                <colgroup width="12%"></colgroup>
                <colgroup width="10%"></colgroup>
                <colgroup width="15%"></colgroup>
                <tr style="height: 45px;">
                    <th>姓名</th>
                    <td>${patient.name}</td>
                    <th>性别</th>
                    <td>${genderMap[patient.gender]}</td>
                    <th>年龄</th>
                    <td>${patient.age}</td>
                    <th>电话</th>
                    <td>${patient.mobile}</td>
                </tr>
            </table>
        </div>
        </c:if>
        <div class="val-list-medicine">
            <div class="clearfix">
                <input type="text" id="barCode" class="form-control" style="display: inline-block; width: 14%;"
                       placeholder="请输入要查询的条码">
                <input type="text" id="medNameInput" class="form-control" style="display: inline-block; width: 14%;"
                       placeholder="输入要查询的药品">
                <button class="btn btn-success" type="button" onclick="searchRetailMed()">搜索</button>
                <div style="width: 65%;" class="list-medicine clearfix">
                    <a class="prev fa fa-chevron-left" onclick="scrollPage('left')"></a>
                    <div class="list-tab" id="retailIntoMed">
                        <div class="scroll_demo" onmousewheel="showKey()">

                        </div>
                    </div>
                    <a class="next fa fa-chevron-right" onclick="scrollPage('right')"></a>
                </div>
            </div>
        </div>
        <table class="adv-table" width="100%">
            <colgroup width="4"></colgroup>
            <colgroup width="10%"></colgroup>
            <colgroup width="10%"></colgroup>
            <colgroup width="10%"></colgroup>
            <colgroup width="8%"></colgroup>
            <colgroup width="10%"></colgroup>
            <colgroup width="10%"></colgroup>
            <colgroup width="10%"></colgroup>
            <colgroup width="8%"></colgroup>
            <colgroup width="10%"></colgroup>
            <colgroup width="10%"></colgroup>
            <thead>
            <tr>
                <th><input type="checkbox" id="checkedAll" name="checkedAll"></th>
                <th>药品名称</th>
                <th>药厂</th>
                <th>条码</th>
                <th>数量</th>
                <th>零售价</th>
                <th>统计单位</th>
                <th>药品规格</th>
                <th>库存</th>
                <th>有效期</th>
                <th>零售总额</th>
            </tr>
            </thead>
            <tbody>

            <c:forEach var="rm" items="${retailMedicine}">
                <tr>
                    <td><input type="checkbox" class="td_check" value="${rm.id}"></td>
                    <td>${rm.medicinePrivate.name}</td>
                    <td>${empty stockMap[rm.id]?companyMap[rm.id]:stockMap[rm.id].companyName}</td>
                    <td>${rm.barCode}</td>
                    <td>${totalNumMap[rm.id]}</td>
                    <td>${rm.retailPrice}</td>
                    <td>${medicineUnits[rm.medicinePrivate.unit]}</td>
                    <td>${rm.standard}</td>
                    <td>${empty stockMap[rm.id]?"":stockMap[rm.id].stockNum}</td>
                    <td><fmt:formatDate value="${empty stockMap[rm.id]?'':stockMap[rm.id].validityDate}"
                                        pattern="yyyy/MM/dd"/></td>
                    <td class="retailMoney">${rm.totalPrice}</td>
                </tr>
            </c:forEach>
            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td>总计：￥${retail.allMedCost}</td>
            </tr>
            </tbody>

        </table>
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
        <button type="button" class="btn btn-success" onclick="retailChargeDetails(${retail.id})"
                style="width: 75px; margin: 0 10px;">收费
        </button>
        <button id="btnDel" type="button" class="btn btn-default" style="width: 75px;">删除</button>
    </div>
</div>
<%@ include file="/WEB-INF/layouts/advfooter.jsp" %>
</body>
</html>
