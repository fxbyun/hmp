<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <title>易佳诊健康管理平台</title>
    <meta charset="UTF-8">
    <link href="${ctx}/assets/styles/admin.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="${ctx}/assets/scripts/admin.js"></script>
    <script type="text/javascript" src="${ctx}/assets/layer/layer.js"></script>
    <script type="text/javascript" src="${ctx}/assets/scripts/hmp.js"></script>
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/assets/html5shiv/html5shiv.js"></script>
    <script type="text/javascript" src="${ctx}/assets/respond/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="form-horizontal">
    <input type="hidden" id="hidMedicineUnit" value="${medicine.unit}"/>
    <table border="0" style="width: 100%;font-size: 14px">
        <tr style="height: 40px">
            <td align="right" width="100px">品名：</td>
            <td>${medicine.name}</td>
        </tr>
        <tr style="height: 40px">
            <td align="right">治疗方式：</td>
            <td>
                <select name="useMode" id="selUseMode" style="width:100px">
                    <c:forEach var="u" items="${medicineUseModes}">
                        <option value="${u}" <c:if test='${medicine.useMode==u}'>selected</c:if>>${u}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr style="height: 40px">
            <td align="right">标准用量：</td>
            <td>
                <label class="checkbox" for="chkHasUsage">
                <input <c:if test="${medicine.type == 'Western'}">checked="checked"</c:if> type="checkbox" id="chkHasUsage"> 设置标准用量</label>
            </td>
        </tr>
        <tr>
            <td></td>
            <td><div id="divMedicineUsage"></div></td>
        </tr>
        <tr style="height: 40px">
            <td align="right">药厂(产地)：</td>
            <td>
                <select id="sltCompany" name="companyId" class="form-control">
                    <c:forEach var="co" items="${medicine.companyList}">
                        <option value="${co.id}" <c:if test="${medicine.defaultCompany == co}">selected</c:if> >${co.name}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr style="height: 40px">
            <td align="right">数量/单位：</td>
            <td>
                <input type="number" min="1" style="width:60px" id="txtMedicineQty" value="1">
                <select name="medicineUnit" id="selMedicineUnit" style="width:80px" disabled="disabled">
                    <c:forEach var="u" items="${medicineUnits}">
                        <option value="${u.key}" <c:if test='${medicine.unit==u.key}'>selected</c:if>>${u.value}</option>
                    </c:forEach>
                </select> X
                <input type="number" min="1" style="width:60px" id="txtMedicineCopies" value="1">份
            </td>
        </tr>
        <%--<tr style="height: 40px">--%>
            <%--<td></td>--%>
            <%--<td> <div id="divConversion"></div></td>--%>
        <%--</tr>--%>
        <tr style="height: 40px">
            <td colspan="2" align="center">
                <button id="btnSubmitMedicine" type="button" class="btn btn-primary"><i class="fa fa-check"></i> 确定</button>
                <button id="btnCloseMedicine" type="button" class="btn btn-default"><i class="fa fa-times"></i> 取消</button>
                <%--<button id="btnConvert" type="button" class="btn btn-success">修改换算率</button>--%>
            </td>
        </tr>
    </table>
</div>
<script type="text/javascript">
    $("#txtMedicineQty").focus().select();
    function fn_SetMedicineInfo(medicine) {
        if (medicine) {
            if(medicine.companyId) $('#selCompany').val(medicine.companyId);
            if(medicine.unit) {
                $('#selMedicineUnit').val(medicine.unit);
                $('#selMedicineUnit').change();
            }
            if(medicine.qty) $('#txtMedicineQty').val(medicine.qty);
            if(medicine.rate) $('#txtMedicineRate').val(medicine.rate);
            if(medicine.copies) $('#txtMedicineCopies').val(medicine.copies);
            if(medicine.useMode) $('#selUseMode').val(medicine.useMode);
            if(medicine.hasUsage) {
                $('#chkHasUsage').prop("checked", medicine.hasUsage);
                $('#chkHasUsage').change();
            }
        }
    }
    <%--$('#selMedicineUnit').change(function () {--%>
        <%--var unit = $('#hidMedicineUnit').val();--%>
        <%--var $opt = $('#selMedicineUnit option:selected');--%>
        <%--var selUnit = $('#selMedicineUnit').val();--%>
        <%--var text = $opt.text();--%>
        <%--if (unit == selUnit) $('#divConversion,#btnConvert').hide();--%>
        <%--else {--%>
            <%--$('#divConversion,#btnConvert').show();--%>
            <%--$('#divConversion').load("/fragment/conversion/select?medicineId=${medicine.id}", {'unit': selUnit});--%>
        <%--}--%>
    <%--});--%>
    $(function () {
        var index = parent.layer.getFrameIndex(window.name);
        $('#btnSubmitMedicine').click(function () {
            var qty = $('#txtMedicineQty').val();
            if (!$.isNumeric(qty)) {
                layer.alert('请输入药品数量');
                return;
            }
            var companyId = $('#sltCompany').val();
            if (!$.isNumeric(companyId)) {
                layer.alert('请选择药品的生产厂家');
                return;
            }
            var $opt = $('#selMedicineUnit option:selected');
            var unit = $opt.val();
            var unitLabel = $opt.text();
            var rate = 1;
            if (unit != '${medicine.unit}') {
                rate = $("#txtMedicineRate").val();
            }
            if (!$.isNumeric(rate)) {
                rate = 1;
//                layer.alert('请正确输入药品换算率');
//                return;
            }
            var data = {
                'medicineId':${medicine.id},
                'medicineName': '${medicine.name}',
                'companyId': $('#sltCompany').val(),
                'unit': unit, 'unitLabel': unitLabel,
                'qty': qty, 'rate': rate,
                'copies': $('#txtMedicineCopies').val(),
                'useMode': $('#selUseMode').val(),
                'hasUsage': $('#chkHasUsage').is(':checked')
            };
            parent.fn_SelectMedicine(data, '${medicine.type}');
            parent.layer.close(index);
        });
        $('#btnCloseMedicine').click(function () {
            parent.layer.close(index);
        });
        $('#chkHasUsage').change(function () {
            var checked = $(this).is(':checked');
            if (checked) {
                $('#divMedicineUsage').load('${ctx}/fragment/medicine/usage/${medicine.id}');
            } else {
                $('#divMedicineUsage').empty();
            }
        });
        var data = parent.fn_GetMedicineInfo(${medicine.id});
        if (data) fn_SetMedicineInfo(data);
        else if('Western' == '${medicine.type}'){
//            $('#chkHasUsage').attr("checked", true);
            $('#divMedicineUsage').load('${ctx}/fragment/medicine/usage/${medicine.id}');
        }
    });
</script>
</body>
</html>