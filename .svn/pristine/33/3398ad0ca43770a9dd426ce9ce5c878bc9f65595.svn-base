<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <link href="${ctx}/assets/bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
    <link href="${ctx}/assets/styles/default.min.css" type="text/css" rel="stylesheet"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/styles/style.css" type="css"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/styles/green.css" type="css" needChengStyle="true"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/assets/select2/select2.css">
    <link rel="stylesheet" href="${ctx}/assets/styles/font-awesome/css/font-awesome.min.css">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/assets/html5shiv/html5shiv.js"></script>
    <script type="text/javascript" src="${ctx}/assets/respond/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript" src="${ctx}/assets/jquery/jquery-1.11.2.min.js"></script>

    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/xwArray.js" type="js"/>
    <script type="text/javascript" src="${ctx}/assets/layer/layer.js"></script>
    <script type="text/javascript" src="${ctx}/assets/laypage/laypage.js"></script>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/hmp.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/base.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/acupuncturePoint.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/pinyin.js" type="js"/>
    <script type="text/javascript" src="${ctx}/assets/select2/select2.js"></script>
    <script type="text/javascript" src="${ctx}/assets/select2/select2.ext.js"></script>
    <script type="text/javascript">
        //选择的分组数
        var groupIndex = "${groupId}";

        //西药大部分分组类型默认值
        var defaultWesternIndex = 10;

        //中药大部分分组类型默认值
        var defaultChineseIndex = 9;

        var handleType = "${handleType}";

        var medType = "";
        var lastSelectXwOrFenBao = "${lastSelectXwOrFenBao}";
        var isShowWindow = "${isShowWindow}";
        function fn_SetMedicineInfo(medicine) {
            if (medicine) {
                if (medicine.companyId) $('#selCompany').val(medicine.companyId);
                if (medicine.unit) {
                    //$('#selMedicineUnit').val(medicine.unit);
                    $('#selMedicineUnit').change();
                }

                if (medicine.qty) $('#txtMedicineQty').val(medicine.qty);
                if ("${medicine.useQty}" != "" && "${medicine.useQty}" != "null") $('#txtMedicineQty').val("${medicine.useQty}");
                if ("${medicine.realQty}" != "" && "${medicine.realQty}" != "null") $('#txtMedicineQty').val("${medicine.realQty}");
                if (medicine.rate) $('#txtMedicineRate').val(medicine.rate);
                if (medicine.copies) $('#txtMedicineCopies').val(medicine.copies);
                //会上个药品的治疗方式付给下个药品
                //if (medicine.useMode) $('#selUseMode').val(medicine.useMode);
                if (medicine.hasUsage == 'true') {
                    $($('input[name="type"]')[0]).prop("checked", true);
                } else {
                    $($('input[name="type"]')[1]).prop("checked", true);
                }
                if ($("#selUseMode").val() == "研末" || $.inArray($("#selUseMode").val(), xwArray) >= 0) {
                    fuckChangeIt();
                }
                if (groupIndex != "" && handleType == 'Medicine_Edit') {
                    fuckChangeIt();
                    $("#useGroups").val(groupIndex);
                }
                if (lastSelectXwOrFenBao != "") {
                    fuckChangeIt();
                    if ($("#selUseMode").val() == "${selectMedUseModType}") {
                        $("#useGroups").val(lastSelectXwOrFenBao);
                    }
                }

                if ($("#selUseMode").val() == "煎服") {
                    $("#useGroups").hide();
                    $("#groupLab").hide();
                }
            }
        }


        function fn_UpdateCategory() {
            var cate = $("#selMCate").val();
            var mid = ${medicine.id};
            $.getJSON("/medicine/updateCate/{0}?category={1}".format(mid, cate), function (result) {
                if (result.success) {
                    layer.msg("分类已经更新。");
                }
            });
        }
        $(function () {
            medType = "${medicine.type}";
            if (medType != "Western") {
                $("#groupLab").attr("style", "display: none;")
                $("#useGroups").attr("style", "display: none;")
            }
            fuckChangeIt();

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
                    layer.alert('请正确输入药品换算率');
                    return;
                }
                if (rate == 0) {
                    layer.alert("请输入不为0的值");
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
                var tmpPrice = "${medicine.price}"; //单价
                var copiesss = $('#txtMedicineCopies').val();
                //qty 药品数量 copiesss 份数 rate 换算单位
                if (qty && copiesss && tmpPrice && rate) {
                    price = qty * copiesss * (tmpPrice / rate);
                    price = price.toFixed(2);
                }
                var data = {
                    'medicineId': "${medicine.medicine.id}${haveMore}",
                    "multiplexTag": "",
                    'medicineName': '${medicine.name}',
                    'companyId': $('#sltCompany').val(),//厂家ID
                    'unit': unit,// 数量/单位 下拉框的value
                    'unitLabel': unitLabel,// 数量/单位下拉框 text
                    'qty': qty,//qty 药品数量
                    'rate': rate,//换算单位
                    'copies': $('#txtMedicineCopies').val(),//份数
                    "price": price, // price = qty * copiesss * (tmpPrice / rate)
                    "unitPrice": tmpPrice,//单价
                    "medType": "${medicine.type}",//药品类型
                    'useMode': $('#selUseMode').val(),//治疗方式
                    'hasUsage': ($('input[name="type"]:checked').val() == 0),// 适用与否
                    'useTimes': $('#useTimes').val(),//每天几次
                    'usingTime': $('#txtUsingTime').val(),//饭前还是饭后
                    'useQty': $("#useQty").val(),// 标准用量 每次多少
                    "useUnit": $("#useUnit option:selected").text(),
                    "realUnit": "${medicine.realUnit}",
                    "groupIndex": groupMsg,//分组下标
                    "openType": "${openType}",//打开类型 是 增加还是修改
                    "id": "${medicine.id}",
                    "specialInstructions": $("#specialInstructions").val(),
                    "standard": $("#txtStandard").val(),//规格
                    "tagNum": 0,
                    "medicinePrivateId": "${medicine.id}",
                    "tjUnit": $('#hidMedicineUnit').val(),//统计单位 如 盒/瓶/只
                    "category": $('#selMCate').val()
                };
                if ((medType != "Western" && $.inArray($("#selUseMode").val(), xwArray) < 0) && data.groupIndex == 0) {
                    data.groupIndex = "9";
                }
                if (data.groupIndex == 0) {
                    data.groupIndex = "9";
                }
                if (medType == "Chinese" && $('#selUseMode').val() == "煎服") {
                    if (data.groupIndex != "10") {
//                        parent.Alert.warning("中药煎服不可分组!");
                    }
                    data.groupIndex = "10";
                }
                parent.$("#hiddenSetCost").val("0");
                parent.fn_SelectMedicine(data, '${medicine.type}');

                //保存医生开药习惯
                $.postJSON("/medicine/saveRealQty", {
                    medPrivateId:${medicine.id},
                    realQty: $("#txtMedicineQty").val(),
                    realUnit: $("#selMedicineUnit").val()
                })

                //$.postJSON("/medicine/saveDoctorHabit",JSON.stringify(data))
                $.ajax({
                    type: "post",
                    contentType: "application/json",
                    url: "${ctx}/medicine/saveDoctorHabit",
                    data: JSON.stringify(data),
                    dataType: "JSON",
                })



                try {
                    //保存标准用量到医生的私人库中
                    $.postJSON("/medicine/saveUsage", $('#frmMedicineUsage').serializeArray(), function (d) {
                        //保存药品规格到医生的私人库中
                        $.get("/medicine/saveStandard/${medicine.id}?standard=" + $("#txtStandard").val(), {}, function (d) {
                            //保存单位换算到医生的私人库中
                            if ($("#txtMedicineRate").val() != undefined) {
                                $.get("/medicine/saveRate/${medicine.medicine.id}?rate=" + $("#txtMedicineRate").val(), d, function (d) {
                                    parent.layer.close(index);
                                });
                            } else {
                                parent.layer.close(index);
                            }
                        });
                    })
                } catch (e) {
                    parent.layer.close(index);
                }
                //tab标签切换
                parent.$("#medList").trigger("click");

            });
            $('#btnCloseMedicine').click(function () {
                parent.layer.close(index);
            });
            $('#btnEditMedicine').click(function () {
                var openTypes = "${openType}";
                if ('${medicine.type}' == 'Western') {
                    parent.fn_EditMedicine(${medicine.id}, '${medicine.type}', '西药及中成药房', function () {

                        parent.layer.iframeSrc(index, '/fragment/medicine/select/${medicine.medicine.id}' + "?type=" + openTypes + "&_=" + jQuery.now());
                    });
                } else {
                    parent.fn_EditMedicine(${medicine.id}, '${medicine.type}', '中草药房', function () {

                        parent.layer.iframeSrc(index, '/fragment/medicine/select/${medicine.medicine.id}' + "?type=" + openTypes + "&_=" + jQuery.now());
                    });
                }
            });
            $('#btnConvert').click(function () {
                var rate = $('#txtMedicineRate').val();
                if (!$.isNumeric(rate)) {
                    layer.msg("请正确填写换算值");
                    return false;
                }
                if (rate == 0) {
                    layer.msg("请输入");
                    return false;
                }
                var conversionId = $('#hidConversionId').val();
                var url = '/conversion/apply';
                if (conversionId) {
                    $.postJSON(url, {'conversionId': conversionId, 'rate': rate}, function (res) {
                        $('#lblConversionMsg').html(res.msg);
                    });
                } else {
                    var unit = $('#selMedicineUnit').val();
                    $.postJSON(url, {
                        'medicineId': ${medicine.medicine.id},
                        'unit': unit,
                        'rate': rate
                    }, function (res) {
                        $('#lblConversionMsg').html(res.msg);
                    });
                }
            });

            $('#selMedicineUnit').change(function () {
                var unit = $('#hidMedicineUnit').val();
                var $opt = $('#selMedicineUnit option:selected');
                var selUnit = $('#selMedicineUnit').val();
                var text = $opt.text();
                if (unit == selUnit) {
                    $('#divConversion,#btnConvert').hide();
                    /*//自动触发药品选择的确定按钮
                    if (isShowWindow) {
                        $("#btnSubmitMedicine").trigger("click");
                     }*/
                }
                else {
                    $('#divConversion,#btnConvert').show();
                    $('#divConversion').load("/fragment/medicine/updateRateForm?medicineId=${medicine.medicine.id}", {'unit': selUnit});
                }
            });

            $('#chkUsed').change(function () {
                var used = $(this).is(':checked');
                $.postJSON("/medicine/${medicine.id}/used", {'used': used});
            });

            var data = parent.fn_GetMedicineInfo(${medicine.medicine.id});
            if (data) {
                fn_SetMedicineInfo(data);
            } else if ("true" == "${medicine.usageFlag}") {
                $($('input[name="type"]')[0]).prop("checked", true);
                $('#divMedicineUsage').load('/medicine/usage/${medicine.id}');
            }
            $('#selMedicineUnit').change();


            $('input[name="type"]').change(function () {
                var type = $('input[name="type"]:checked').val();
                if (type == 0) {
                    $('#divMedicineUsage').load('/medicine/usage/${medicine.id}');
                } else {
                    $('#divMedicineUsage').empty();
                }
            });
            $('input[name="type"]').change();
            var selectMedUseModType = "${selectMedUseModType}";
            if (selectMedUseModType != "") {
                //保存之前的治疗方式
                //$("#selUseMode").val(selectMedUseModType);
                fuckChangeIt();

            }
            if (lastSelectXwOrFenBao != "") {
                fuckChangeIt();
                //$("#useGroups").val(lastSelectXwOrFenBao);
                if ($("#selUseMode").val() == selectMedUseModType) {
                    $("#useGroups").val(lastSelectXwOrFenBao);
                }
            }

            if ("${medicine.type}" == "Chinese") {
                //FangXB
//                $("#selUseMode option[value='研末']").remove()
//                $("#groupLab").css("display", "none")
//                $("#divUseGroups").css("display", "none")


            }

        });

        var old = "";
        function fuckChangeIt2() {
            lastSelectXwOrFenBao = "";
            fuckChangeIt();
        }

        function fuckChangeIt() {
            if ($("#selUseMode").val() == "研末") {
                if ($("span[dir='ltr']")) {
                    $("span[dir='ltr']").remove();
                }
                $("#groupLab").text("分包");
                $("#packetId").remove();
                $("#useGroups").replaceWith("<input type='text' " +
                    "id='useGroups' name='useGroups' class='form-control' " +
                    "value='' style='width: 41px;float: left;;' placeholder='请输入 1,2,3来分包数;默认9包' > " +
                    '<label class=" control-label text-right" style="line-height: 34px;float: left;margin-left:20px;" id="packetId">包</label>');
                if (old != "" && $.inArray(old, xwArray) >= 0) {
                    $("#useGroups").val(groupIndex);
                    old = $("#selUseMode").val();

                    //上面逻辑太乱不管了，如果是新增药品，并且还是研末的话
                    if (handleType == 'Medicine_Add' && $("#selUseMode").val() == '研末') {
                        $("#useGroups").val(defaultChineseIndex);
                    }
                    return;
                }
                if (groupIndex != 10) {
                    $("#useGroups").val(groupIndex);
                } else {
                    //设置研末默认包数
                    $("#useGroups").val(defaultChineseIndex);
                }


                if (lastSelectXwOrFenBao != "" && $("#selUseMode").val() == "${selectMedUseModType}") {
                    $("#useGroups").val(lastSelectXwOrFenBao);
                }

                //上面逻辑太乱不管了，如果是新增药品，并且还是研末的话
                if (handleType == 'Medicine_Add' && $("#selUseMode").val() == '研末') {
                    $("#useGroups").val(defaultChineseIndex);
                }

            } else if ($.inArray($("#selUseMode").val(), xwArray) >= 0) {
                if (old != "" && ($.inArray(old, xwArray) >= 0)) {
                } else {
                    $("#useGroups").replaceWith('<select name="useGroups" id="useGroups" class="form-control"></select>');
                    $("#groupLab").text("穴位");
                    $("#groupLab").css("padding", "0px");
                    $(acuPointsArrs).each(function (index, val) {
                        $("#useGroups").append('<option value="{0}">{1}</option>'.format(index, val));
                    })
                    if (lastSelectXwOrFenBao != "") {
                        $("#useGroups").val(lastSelectXwOrFenBao);
                    }
                    $("#packetId").remove();
                    $("#useGroups").select2({
                        language: "zh-CN"
                    });
                }


                if (groupIndex != 10) {
                    $("#useGroups").val(groupIndex);
                    $("#select2-useGroups-container").text(acuPointsArrs[groupIndex]);
                    $("#select2-useGroups-container").attr("title", acuPointsArrs[groupIndex]);
                } else {
                    $("#useGroups").val(acuPointsArrs[0]);
                }

            } else {
                $("#groupLab").text("分组");
                if ($("span[dir='ltr']")) {
                    $("span[dir='ltr']").remove();
                }
                $("#useGroups").replaceWith('<select name="useGroups" id="useGroups" class="form-control">' +
                    '<option value="10">不分组</option><option value="1">分组一</option>' +
                    '<option value="2">分组二</option><option value="3">分组三</option>' +
                    '<option value="4">分组四</option><option value="5">分组五</option>' +
                    '<option value="6">分组六</option><option value="7">分组七</option>' +
                    '<option value="8">分组八</option><option value="9">分组九</option></select>');
                $("#packetId").remove();
                if (old != "" && ($.inArray(old, xwArray) >= 0)) {
                    $("#useGroups").val(defaultWesternIndex);
                    old = $("#selUseMode").val();
                    return;
                }
                if (groupIndex == "0") {
                    groupIndex = 10;
                }

                if (handleType == 'Medicine_Add') {
                    $("#useGroups").val(defaultWesternIndex);
                } else {
                    $("#useGroups").val(groupIndex);
                }

            }


            old = $("#selUseMode").val();
            if ($("#selUseMode").val() == "煎服") {
                $("#useGroups").hide();
                $("#groupLab").hide();
            } else {
                //FangXB
                if ("${medicine.type}" != "Chinese") {
                    $("#groupLab").show();
                }

            }

            //若
        }
    </script>
</head>
<body style="background-color: #fff;">
<div style="margin: 20px;" class="form-horizontal">
    <input type="hidden" id="price" value="${medicine.price}"/>
    <input type="hidden" id="hidMedicineUnit" value="${medicine.unit}"/>
    <input type="hidden" id="isNewMedForPriv" name="isNewMedForPriv" value="${isNewMedForPriv}"/>

    <div class="form-group">
        <div class="col-xs-12 col-sm-12">
            <h3 title="${medicine.info}" class="media-h3
                       <c:if test="${medicine_kc<=0 || medicine_guoQi==true}">color-red </c:if>
                    <%-- color-red 当库存为0或有效期到期时，追加此样式 --%>">${medicine.name}
                <%--@elvariable id="medicine" type="com.qiaobei.hmp.modules.entity.MedicinePrivate"--%>
                <c:if test="${medicine.haveManager!='YES'}">
                    <span style="font-size: 14px; padding-left: 20px;">药品价格：${medicine.price}元/${medicineUnits.get(medicine.unit)}</span>
                </c:if>
                <c:if test="${medicine.haveManager=='YES'}">
                    <span style="font-size: 14px; padding-left: 20px;"
                          class="<c:if test="${medicine_kc<=0}">color-red </c:if>">库存：${medicine_kc} ${medicineUnits.get(medicine.unit)}</span>
                    <span style="font-size: 14px; padding-left: 20px;">药品价格：${medicine.price}元/${medicineUnits.get(medicine.unit)}</span>

                    <div style="font-size: 14px;
                     float: right; line-height: 2.2em;"
                         class="<c:if test="${medicine_guoQi}">color-red </c:if>">有效日期：
                        <c:choose>
                            <c:when test="${not empty medicine_yxrq }">
                                <fmt:formatDate value="${medicine_yxrq}" pattern="yyyy/MM/dd"/>
                            </c:when>
                            <c:otherwise>
                                无
                            </c:otherwise>
                        </c:choose>


                    </div>
                </c:if>

            </h3>
        </div>

    </div>
    <div class="form-group">
        <label class="col-xs-2 col-sm-2 control-label text-right">药品分类</label>

        <div class="col-xs-5 col-sm-5">
            <c:if test="${medicine.type == 'Western'}">
                <select id="selMCate" class="form-control">
                    <option value="">未分类</option>
                    <c:forEach var="c" items="${westernMedicineCate}">
                        <option value="${c}" <c:if test='${medicine.category==c}'>selected</c:if>>${c}</option>
                    </c:forEach>
                </select>
            </c:if>
            <c:if test="${medicine.type == 'Chinese'}">
                <select id="selMCate" class="form-control">
                    <option value="">未分类</option>
                    <c:forEach var="c" items="${chineseMedicineCate}">
                        <option value="${c}" <c:if test='${medicine.category==c}'>selected</c:if>>${c}</option>
                    </c:forEach>
                </select>
            </c:if>
        </div>
    </div>
    <div class="form-group">
        <label class="col-xs-2 col-sm-2 control-label text-right">治疗方式</label>

        <div class="col-xs-5 col-sm-5">
            <select name="useMode" id="selUseMode" class="form-control" onchange="fuckChangeIt2()">
                <c:forEach var="u" items="${medicineUseModes}">
                    <option value="${u}" <c:if test='${medicine.useMode==u}'>selected</c:if>>${u}</option>
                </c:forEach>
            </select>
        </div>
        <label class="col-xs-1 col-sm-2 control-label text-right" style="padding-left: 0px; line-height: 35px;"
               id="groupLab">分组</label>

        <div id="divUseGroups" class="col-xs-4 col-sm-4" style="overflow: hidden">
            <select name="useGroups" id="useGroups" class="form-control">
                <option value="10">不分组</option>
                <option value="1">分组一</option>
                <option value="2">分组二</option>
                <option value="3">分组三</option>
                <option value="4">分组四</option>
                <option value="5">分组五</option>
                <option value="6">分组六</option>
                <option value="7">分组七</option>
                <option value="8">分组八</option>
                <option value="9">分组九</option>
            </select>
        </div>
    </div>
    <div class="form-group">
        <label class="col-xs-2 col-sm-2 control-label text-right">标准用量</label>

        <div class="col-xs-9 col-sm-9"
             style="background-color: #F2F2F2; border:1px solid #ccc; margin-left: 13px; padding-left: 0;">

            <div class="col-xs-12 col-sm-12" style="padding: 10px 15px;">
                <input type="radio" name="type" value="0"> 适用
                <input type="radio" name="type" value="1" style="margin-left: 10px;" checked> 不适用
            </div>
            <div id="divMedicineUsage" style="padding: 15px 15px 0 15px;">

            </div>
        </div>
    </div>


    <div class="form-group" style="display: none;">
        <label class="col-xs-2 col-sm-2 control-label text-right">药厂(产地)</label>

        <div class="col-xs-8 col-sm-8">
            <select id="sltCompany" name="companyId" class="form-control">
                <c:forEach var="co" items="${medicine.medicine.companyList}">
                    <option value="${co.id}"
                            <c:if test="${medicine.defaultCompany == co}">selected</c:if> >${co.name}</option>
                </c:forEach>
                <c:if test="${empty medicine.medicine.companyList || medicine.medicine.companyList.size()==0}">
                    <option type="hidden" selected value="-1">默认药厂</option>
                </c:if>
            </select>
        </div>
    </div>
    <%--药品规格--%>
    <div class="form-group" style="border-top:1px solid #ccc; padding: 20px 0 0px 0; margin: 0; color:#3c763d;">
        <div class="col-xs-2 col-sm-2"></div>

        <label class="col-xs-2 col-sm-2 control-label text-success"
               style="padding: 0; line-height: 30px;">药品规格</label>
        <div class="col-xs-4 col-sm-2" style="margin-left: -26px;padding: 0px;">
            <input type="text" class="form-control text-success" id="txtStandard" name="standard"
                   value="${medicine.standard==null?standard:medicine.standard}">
        </div>
    </div>

    <div class="form-group text-success" style="border-top:0px solid #ccc; padding: 10px 0 10px 0; margin: 0;">
        <div class="col-xs-2 col-sm-2"></div>

        <label class="col-xs-2 col-sm-2 control-label text-success" for="txtMedicineQty"
               style="padding: 0; line-height: 30px;">数量/单位</label>

        <div class="col-xs-2 col-sm-2" style="margin-left: -40px;">
            <input type="number" min="1" class="form-control text-success" name="realQty" id="txtMedicineQty"
                   value="${medicine.realQty==null?medicine.useQty:medicine.realQty}">
        </div>
        <div class="col-xs-2 col-sm-2" style="padding: 0;">
            <select name="medicineUnit" id="selMedicineUnit" class="form-control text-success">
                <c:forEach var="u" items="${medicineUnits}">
                    <c:if test="${empty medicine.realUnit}">
                        <option value="${u.key}"
                                <c:if test='${medicine.useUnit==u.key}'>selected</c:if>>${u.value}</option>
                    </c:if>

                    <c:if test="${not empty medicine.realUnit}">
                        <option value="${u.key}"
                                <c:if test='${medicine.realUnit==u.key}'>selected</c:if>>${u.value}</option>
                    </c:if>

                </c:forEach>
            </select>
        </div>
        <label class="col-xs-1 col-sm-1 text-center" for="txtMedicineQty" style="line-height: 33px;">X</label>

        <div class="col-xs-2 col-sm-2" style="margin-left: -10px;">
            <input type="number" min="1" class="form-control text-success" id="txtMedicineCopies" value="1">
        </div>
        <label class="col-xs-1 col-sm-1" for="txtMedicineQty" style="line-height: 33px; margin-left: -10px;">份</label>
    </div>
    <div id="divConversion"></div>
    <div class="form-group" style="border-top:1px solid #ccc; margin: 0; padding: 15px 0;">
        <label class="col-xs-2 col-sm-2 text-center" for="txtMedicineQty">特殊说明</label>

        <div class="col-xs-10 col-sm-10">
            <input class="form-control" type="text" id="specialInstructions" name="specialInstructions"
                   value="${specialInstructions}">
        </div>
    </div>
    <div class="form-group">
        <div class="col-xs-offset-2 col-sm-offset-2 col-xs-9 col-sm-9" style="text-align: center; margin-top: 20px;">
            <button id="btnSubmitMedicine" type="button" class="btn btn-success"><i class="fa fa-check"></i> 确定</button>
            <button id="btnCloseMedicine" type="button" class="btn btn-default"><i class="fa fa-times"></i> 取消</button>
            <%--<button id="btnConvert" type="button" class="btn btn-success" style="display:none;">修改换算率</button>--%>
            <c:if test="${doctor.doctorType!='Sub_Doctor'}">
                <button id="btnEditMedicine" type="button" class="btn btn-success"><i class="fa fa-edit"></i> 修改药品
                </button>
            </c:if>
        </div>
    </div>
</div>
<script type="text/javascript" src="${ctx}/assets/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">
    $("#txtMedicineQty").focus().select();
</script>
</body>
</html>