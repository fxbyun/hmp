<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${ctx}/assets/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/assets/styles/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/assets/styles/default.min.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/assets/styles/style.css">
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/styles/green.css" type="css" needChengStyle="true"/>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/assets/html5shiv/html5shiv.js"></script>
    <script type="text/javascript" src="${ctx}/assets/respond/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript" src="${ctx}/assets/jquery/jquery-1.11.2.min.js"></script>
    <script type="text/javascript" src="${ctx}/assets/layer/layer.js"></script>
    <script type="text/javascript" src="${ctx}/assets/laypage/laypage.js"></script>
    <script type="text/javascript">
        var index = parent.layer.getFrameIndex(window.name);
        function fn_LoadMedicineList(page) {
            var category = $('input[name="category"]:checked').val();
            $('#divMedicines').load("/fragment/myMedicine?page={0}&medicineType=${medicineType}&category={1}".format(page, category));
        }
        $(function () {
            $('#divMedicines').load("/fragment/myMedicine?page={0}&medicineType=${medicineType}&category={1}".format(0, "${categories[0]}"));
            $('input[name="category"]').change(function () {
                fn_LoadMedicineList(0);
            });
            $('#btnSubmit').click(function () {
                var qty = $('#txtMedicineQty').val();
                if (!$.isNumeric(qty)) {
                    layer.alert('请输入药品数量');
                    return;
                }
                var $opt = $('#selMedicineUnit option:selected');
                var unit = $opt.val();
                var unitLabel = $opt.text();
                var rate = 1;
                if (unit != $('#medicineUnit')) {
                    rate = $("#txtMedicineRate").val();

                }
//                if (!$.isNumeric(rate)) {
//                    layer.alert('请正确输入药品换算率');
//                    return;
//                }
                if(rate == 0){
                    layer.alert("请输入不为0的值！");
                    return;
                }
                var groupMsg = $("#useGroups").val();

                if ($("#selUseMode").val() == "研末") {
                    groupMsg = parseInt(groupMsg, 10);
                    if (groupMsg > 30) {
                        parent.Alert.warning("您输入分包数量过多，请确定是否输入有误！");
                        return
                    }
                    if ((groupMsg == "" || parseInt(groupMsg) <= 0) || isNaN(parseInt(groupMsg))) {
                        groupMsg = "0";
                        if (parseInt(groupMsg) <= 0) {
                            parent.Alert.warning("请输入格式正确的分包数量");
                        } else {
                            parent.Alert.warning("请输入研末所需要的分包数量");
                        }
                        return;
                    }

                }
                if ($("#select2-useGroups-container").text() == "请选择穴位" && $.inArray($("#selUseMode").val(), xwArray) >= 0) {
                    parent.Alert.warning("该类型必须选择穴位,请选择正确的穴位!")
                    return;
                }
                var price = 0;
                //var tmpPrice = "${medPrivate.price}"; //单价
                var tmpPrice = $("#myMedicinePrice").val();
                if(tmpPrice==undefined || tmpPrice==""){
                    tmpPrice = 0;
                }
                if(rate == undefined){
                    rate = 1;
                }
                var copiesss = $('#txtMedicineCopies').val();
                //qty 药品数量 copiesss 份数 rate 换算单位
                if (qty && copiesss && tmpPrice && rate) {
                    price = qty * copiesss * (tmpPrice / rate);
                    price = price.toFixed(2);
                }
                var data = {
                    'price': price, // price = qty * copiesss * (tmpPrice / rate)
                    'unitPrice': tmpPrice,//单价
                    'medicineId':$('#medicineId').val(),
                    'medicinePrivateId': $("#medicinePrivateId").val(),
                    'medicineName': $('#medicineName').val(),
                    'companyId': $('#companyId').val(),
                    'unit': unit, 'unitLabel': unitLabel,
                    'qty': qty,
                    'rate': rate,
                    'copies': $('#txtMedicineCopies').val(),
                    'useMode': $('#selUseMode').val(),
                    'hasUsage': $('#useYes').is(':checked'),//适用于否
                    'useTimes': $('#selUseTimes').val(),//每天几次
                    'usingTime': $("#selUsingTime").val(),//饭前还是饭后
                    'useQty': $("#useQty").val(),//标准用量
                    'tagNum':0,
                    'type':"${medicineType}",
                    "openType":"add",
                    'medType':"${medicineType}",
                    "multiplexTag":"",
                    "groupIndex":groupMsg,
                    "standard": $("#txtStandard").val(),//规格
                    "specialInstructions": $("#specialInstructions").val(),//特殊说明
                    "useUnit": $("#useUnit option:selected").text()
                };
                //保存单位换算到医生的私人库中
                var saveRateUrl="/medicine/saveRate/"+data.medicineId+"?rate="+$("#txtMedicineRate").val();
                $.get(saveRateUrl);
                parent.fn_SelectMedicine(data,"${medicineType}");
                parent.layer.close(index);
            });
            $('#btnClose').click(function () {
                parent.layer.close(index);
            });
            $('#btnConvert').click(function () {
                var rate = $('#txtMedicineRate').val();
                if (!$.isNumeric(rate)) {
                    layer.msg("请正确填写换算值");
                    return false;
                }
                var conversionId = $('#hidConversionId').val();
                var url = '/conversion/apply';
                if (conversionId) {
                    $.postJSON(url, {'conversionId': conversionId, 'rate': rate}, function (res) {
                        layer.msg(res.msg);
                    });
                } else {
                    var unit = $('#selMedicineUnit').val();
                    $.postJSON(url, {'medicineId': $('#medicineId').val(), 'unit': unit, 'rate': rate}, function (res) {
                        layer.msg(res.msg);
                    });
                }
            });
        });
    </script>
    <style>
        .btn.btn-success {
            background-color: #218a3f;
            border-color: #218a3f;
        }
    </style>
</head>
<body>

<div style="margin: 20px;">
    <input type="hidden" id="price" value="${medPrivate.price}"/>

    <div class="category-box">
        <c:forEach varStatus="status" var="cate" items="${categories}">
            <label class="btn btn-success">
                <input type="radio" name="category" value="${cate}" <c:if test="${status.first}">checked="checked"</c:if>/> ${cate}
            </label>
        </c:forEach>
        <div id="divMedicines">
                <c:import url="myMedicinePage.jsp"/>
        </div>
    </div>
    <div class="text-center">
        <%--我的药品弹出框--%>
        <button id="btnSubmit" type="button" class="btn btn-success"><i class="fa fa-check"></i> 确定</button>
        <button id="btnClose" type="button" class="btn btn-default"><i class="fa fa-times"></i> 取消</button>
        <%--<button id="btnConvert" type="button" class="btn btn-success">修改换算率</button>--%>
    </div>
</div>
<script type="text/javascript" src="${ctx}/assets/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/scripts/hmp.js"></script>
<script type="text/javascript" src="${ctx}/assets/scripts/highcharts.js"></script>
<hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/prescription/prescription.js" type="js"/>
<hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/application.js" type="js"/>
</body>
</html>