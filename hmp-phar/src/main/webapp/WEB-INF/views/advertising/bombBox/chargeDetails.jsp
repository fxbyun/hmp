<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/10/11
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/advInclude.jsp" %>
<%@ taglib prefix="hmp" uri="/WEB-INF/TLD/HmpLoadStaticFile.tld" %>
<html>
<head>
    <title>收费明细</title>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/advertising/scripts/advertisingIndex.js" type="js"/>
    <style type="text/css">
        .layui-layer-btn .layui-layer-btn0 {
            background-color: #5cb85c;
            border-color: #4cae4c;
        }
    </style>
    <script type="application/javascript">
        var isAllowUpdatePrice = ${isAllowUpdatePrice};
        $(function () {
            if (${emr.getEmrMedicineList().size()==0}) {
                $("#emrTable").hide();
            }
            if (${emr.getEmrJClassAdviceDicts().size()==0}) {
                $("#examLabTable").hide();
            }
            if (${emr.getEmrExtCostList().size()==0}) {
                $("#fuJiaTable").hide();
            }
            if (!isAllowUpdatePrice) {
                $("#realCost").prop("disabled", "disabled");
            }
        })
    </script>
</head>
<body style="background-color: #fff;">
<div class="charge-content">
    <div style="height: 182px; overflow-y: auto;">
        <table class="bomb-table" width="100%" border="1" id="emrTable">
            <colgroup width="20%"></colgroup>
            <colgroup width="10%"></colgroup>
            <colgroup width="10%"></colgroup>
            <colgroup width="10%"></colgroup>
            <colgroup width="15%"></colgroup>
            <colgroup width="20%"></colgroup>
            <colgroup width="15%"></colgroup>
            <thead>
            <tr style="background-color: #e1ffdd;">
                <th>药品名称</th>
                <th>药品规格</th>
                <th>单位</th>
                <th>数量</th>
                <th>零售价格</th>
                <th>零售总额</th>
                <th>状态</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${emr.getEmrMedicineList()}" var="oneEmrMedObj">
                <tr>
                    <td>
                        <span class="text-num" title="${oneEmrMedObj.medicineName}">
                                ${oneEmrMedObj.medicineName}
                        </span>
                    </td>
                    <td>${oneEmrMedObj.standard}</td>
                    <td>${medicineUnits.get(oneEmrMedObj.unit)}</td>
                    <td><fmt:formatNumber value="${oneEmrMedObj.qty * oneEmrMedObj.copies}" pattern="##.##"
                                          minFractionDigits="2"/></td>
                    <td>${oneEmrMedObj.unitPrice}</td>
                    <td><span>${oneEmrMedObj.price}</span>
                        <a href="javascript:;"
                           onclick="editPrice('${oneEmrMedObj.id}','${oneEmrMedObj.price}',this,${isAllowUpdatePrice})"><i
                                class="fa fa-edit"
                                style="padding-left:10px;"></i></a>
                    </td>
                    <td>${oneEmrMedObj.status.getName()}</td>
                </tr>
            </c:forEach>
            <%-- 新增纸质处方 start --%>
            <%--<tr style="background-color: #e1ffdd;">--%>
            <%--<th colspan="2">纸质处方</th>--%>
            <%--<th></th>--%>
            <%--<th></th>--%>
            <%--<th>日期</th>--%>
            <%--<th>总额</th>--%>
            <%--</tr>--%>
            <%--<tr>--%>
            <%--<td colspan="2"><a href="##" target="_blank" title="201612021230">纸质处方西药：201612021230</a></td>--%>
            <%--<td></td>--%>
            <%--<td></td>--%>
            <%--<td>2016/12/02</td>--%>
            <%--<td>0.0</td>--%>
            <%--</tr>--%>
            <%-- end --%>
            </tbody>
        </table>


        <table class="bomb-table" width="100%" border="1" id="examLabTable">
            <colgroup width="20%"></colgroup>
            <colgroup width="10%"></colgroup>
            <colgroup width="10%"></colgroup>
            <colgroup width="10%"></colgroup>
            <colgroup width="15%"></colgroup>
            <colgroup width="20%"></colgroup>
            <colgroup width="15%"></colgroup>
            <thead>
            <tr style="background-color: #e1ffdd;">
                <th>检查/检验</th>
                <th>类型</th>
                <th>大项</th>
                <th>特殊说明</th>
                <th>日期</th>
                <th>总额</th>
                <th>状态</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${emr.getEmrJClassAdviceDicts()}" var="oneEmrExamLab">
                <tr>
                    <td>
                        <span class="text-num" title="${oneEmrExamLab.adviceName}">
                                ${oneEmrExamLab.adviceName}
                        </span>
                    </td>
                    <td>

                        <c:if test="${oneEmrExamLab.type=='JianYan'}">
                            检验
                        </c:if>
                        <c:if test="${oneEmrExamLab.type=='JianCha'}">
                            检查
                        </c:if>
                    </td>
                    <td>
                            ${oneEmrExamLab.examLabName}
                    </td>
                    <td>
                            ${oneEmrExamLab.info}
                    </td>
                    <td>
                        <fmt:formatDate value="${oneEmrExamLab.createOn}" pattern="yyyy/MM/dd"/>
                    </td>
                    <td>${oneEmrExamLab.price}
                            <%--<a href="javascript:;" onclick="editPrice(this)"><i class="fa fa-edit"--%>
                            <%--style="padding-left:10px;"></i></a>--%>
                    </td>
                    <td>
                            ${oneEmrExamLab.status.getName()}
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <table class="bomb-table" width="100%" border="1" id="fuJiaTable">
            <colgroup width="20%"></colgroup>
            <colgroup width="10%"></colgroup>
            <colgroup width="10%"></colgroup>
            <colgroup width="10%"></colgroup>
            <colgroup width="15%"></colgroup>
            <colgroup width="20%"></colgroup>
            <colgroup width="15%"></colgroup>
            <thead>
            <tr style="background-color: #e1ffdd;">
                <th>附加费用</th>
                <th></th>
                <th></th>
                <th></th>
                <th>日期</th>
                <th>总额</th>
                <th>状态</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${emr.getEmrExtCostList()}" var="oneExtCost">
                <tr>
                    <td>
                        <span class="text-num" title="${oneExtCost.className}">
                                ${oneExtCost.className}
                        </span>
                    </td>
                    <td>
                    </td>
                    <td>
                    </td>
                    <td>
                    </td>
                    <td>
                        <fmt:formatDate value="${emr.createOn}" pattern="yyyy/MM/dd"/>
                    </td>
                    <td>${oneExtCost.price}
                            <%--<a href="javascript:;" onclick="editPrice(this)"><i class="fa fa-edit"--%>
                            <%--style="padding-left:10px;"></i></a>--%>
                    </td>
                    <td>${oneExtCost.status.getName()}
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>


    </div>

    <div style="overflow: hidden; margin-top: 15px;">
        <span class="pull-left">医生姓名：${emr.doctor.name}</span>
        <span class="pull-right" style="margin-left: 20px;">补收金额：<input type="number" class="form-control"
                                                                        style="display: inline-block; width: 80px;"
                                                                        name="realCost"
                                                                        id="realCost"
                                                                        value="${emr.cost-emr.realCost}"></span>
        <span class="pull-right" style="margin-left: 15px;">已收金额：<input type="text" class="form-control"
                                                                        style="display: inline-block; width: 80px;"
                                                                        value="${emr.realCost}"
                                                                        disabled></span>
        <span class="pull-right">应收金额：<input type="text" class="form-control"
                                             style="display: inline-block; width: 80px;"
                                             value="${emr.cost}"
                                             disabled></span>

    </div>
    <div class="text-center" style="margin-top: 20px;">
        <button id="btnCost" class="btn btn-success" type="button">确认收费</button>
        <button id="btnClose" class="btn btn-default" type="button" style="width: 80px; margin-left: 10px;">取消</button>
    </div>
</div>
<script>
    $(function () {
        $("#btnCost").click(function () {
            var price = ( parseFloat($("#realCost").val())).toFixed(2);
            showInfo(${emr.id}, price);
        });

        $("#realCost").val(parseFloat($("#realCost").val()).toFixed(2));
    })
</script>


</body>
</html>
