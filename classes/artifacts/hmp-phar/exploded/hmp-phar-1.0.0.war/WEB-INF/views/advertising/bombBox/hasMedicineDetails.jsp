<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/10/12
  Time: 15:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/advInclude.jsp" %>
<%@ taglib prefix="hmp" uri="/WEB-INF/TLD/HmpLoadStaticFile.tld" %>
<html>
<head>
    <title>已发药品清单</title>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/advertising/scripts/advertisingIndex.js" type="js"/>
    <script>
        $(function () {
            function initTableCheckbox() {
                var $tbr = $('table tbody tr');
                /*点击每一行的选中复选框时*/
                $tbr.find('input').click(function (event) {
                    /*调整选中行的CSS样式*/
                    $(this).parent().parent().toggleClass('tr-background');
                    /*如果已经被选中行的行数等于表格的数据行数，将全选框设为选中状态，否则设为未选中状态*/
                    //  $checkAll.prop('checked', $tbr.find('input:checked').length == $tbr.length ? true : false);
                    /*阻止向上冒泡，以防再次触发点击操作*/
                    event.stopPropagation();
                });
                /*点击每一行时也触发该行的选中操作*/
                $tbr.click(function () {
                    $(this).find('input').click();
                });
            }

            initTableCheckbox();

        });
        function backMed(emrId) {
            $.postJSON("${ctx}/adv/hasMedicineList/backMed",
                    {
                        id: emrId,
                        ids: getCheckboxVal("checkItem"),
                        backMedRemarks: $("#backMedRemarks").val()
                    }, function (ret) {
                        if (ret.success) {
                            parent.layer.msg("操作成功");
                            setTimeout(function () {
                                parent.parent.parent.window.location.reload();
                            }, 1000);
                        } else {
                            parent.layer.msg("操作失败,请联系技术人员!");
                        }
                    });

        }
    </script>
</head>
<body style="background-color: #fff;">
<div class="charge-content">
    <div style="height: 182px; overflow-y: auto;">
        <table class="bomb-table" width="100%" border="1">
            <colgroup width="10%"></colgroup>
            <colgroup width="20%"></colgroup>
            <colgroup width="20%"></colgroup>
            <colgroup width="15%"></colgroup>
            <colgroup width="15%"></colgroup>
            <colgroup width="20%"></colgroup>
            <thead>
            <tr>
                <th>选择</th>
                <th>药品名称</th>
                <th>药品规格</th>
                <th>数量</th>
                <th>零售价格</th>
                <th>零售总额</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${emr.getEmrMedicineList()}" var="oneEmrMedObj">
                <tr>
                    <td><input type="checkbox"
                               name="checkItem"
                               value="${oneEmrMedObj.id}"
                               <c:if test="${oneEmrMedObj.status=='Have_Dispensing_Back'}">disabled checked
                               title="该药品已退费,无法操作!" </c:if>
                    /></td>
                    <td><span class="text-num" title="${oneEmrMedObj.medicineName}"> ${oneEmrMedObj.medicineName}</span>
                    </td>
                    <td>${oneEmrMedObj.standard}</td>
                    <td>${oneEmrMedObj.qty}</td>
                    <td>${oneEmrMedObj.unitPrice}</td>
                    <td>${oneEmrMedObj.price}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div style="margin-top: 15px;">
        <span style="vertical-align: top;">备注：</span>
        <textarea class="form-control" name="backMedRemarks" id="backMedRemarks"
                  style="display: inline-block; width: 93%;"
        >${emr.backMedRemarks}</textarea>
    </div>

    <div style="overflow: hidden; margin-top: 15px;">
        <span class="pull-left">医生姓名：${emr.doctor.name}</span>
        <span class="pull-right">总额：<input type="text" class="form-control" style="display: inline-block; width: 80px;"
                                           value="${emr.realCost}" disabled></span>
    </div>
    <div class="text-center" style="margin-top: 20px;">
        <button class="btn btn-success" type="button" onclick="backMed(${emr.id})">确认退药</button>
        <button id="btnClose" class="btn btn-default" type="button" style="width: 80px; margin-left: 10px;">取消</button>
    </div>
</div>

</body>
</html>
