<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<%--@elvariable id="medicine" type="com.qiaobei.hmp.modules.entity.Medicine"--%>
<%--@elvariable id="medPrivate" type="com.qiaobei.hmp.modules.entity.MedicinePrivate"--%>
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
<div style="margin: 20px;" class="form-horizontal">
    <input type="hidden" id="medicineId" value="${medicine.id}" />
    <input type="hidden" id="medicinePrivateId" value="${medPrivate.id}"/>
    <input type="hidden" id="medicineName" value="${medicine.name}" />
    <input type="hidden" id="medicineType" value="${medicine.type}" />
    <input type="hidden" id="medicineUnit" value="${medicine.unit}" />
    <input type="hidden" id="companyId" value="${medicine.defaultCompany.id}" />
    <input type="hidden" id="myMedicinePrice" value="${medPrivate.price}" />


    <%--<div class="form-group">--%>
        <%--<div class="col-xs-12 col-sm-12">--%>
            <%--<h3 title="${medPrivate.info}" class="media-h3--%>
                       <%--<c:if test="${medicine_kc<=0 || medicine_guoQi==true}">color-red </c:if>--%>
                    <%--&lt;%&ndash; color-red 当库存为0或有效期到期时，追加此样式 &ndash;%&gt;">${medPrivate.name}--%>
                <%--&lt;%&ndash;@elvariable id="medicine" type="com.qiaobei.hmp.modules.entity.MedicinePrivate"&ndash;%&gt;--%>
                <%--<c:if test="${medPrivate.haveManager!='YES'}">--%>
                    <%--<span style="font-size: 14px; padding-left: 20px;">药品价格：${medPrivate.price}元/${medicineUnits.get(medPrivate.unit)}</span>--%>
                <%--</c:if>--%>
                <%--<c:if test="${medPrivate.haveManager=='YES'}">--%>
                    <%--<span style="font-size: 14px; padding-left: 20px;"--%>
                          <%--class="<c:if test="${medicine_kc<=0}">color-red </c:if>">库存：${medicine_kc} ${medPrivate.get(medPrivate.unit)}</span>--%>
                    <%--<span style="font-size: 14px; padding-left: 20px;">药品价格：${medPrivate.price}元/${medicineUnits.get(medPrivate.unit)}</span>--%>
                <%--</c:if>--%>

            <%--</h3>--%>
        <%--</div>--%>

    <%--</div>--%>

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

        <div class="col-xs-3 col-sm-3">
            <select name="useMode" id="selUseMode" class="form-control" onchange="fuckChangeIt2()">
                <c:forEach var="u" items="${medicineUseModes}">
                    <option value="${u}" <c:if test='${medPrivate.useMode==u}'>selected</c:if>>${u}</option>
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

        <label class="col-xs-2 col-sm-2 control-label text-right">药品规格</label>
        <div class="col-xs-4 col-sm-2">
            <input type="text" class="form-control text-success" id="txtStandard" name="standard"
                   value="${medicine.standard==null?standard:medicine.standard}">
        </div>
    </div>
    <div class="form-group" id="divUseTimes">
        <label class="col-xs-2 col-sm-2 control-label text-right">标准用量</label>

        <%--<div class="col-xs-6 col-sm-6">
            <label for="chkHasUsage"><input type="checkbox" id="chkHasUsage" <c:if test='${medicine.usageFlag == true}'>checked</c:if>> 设置标准用量</label>
        </div>--%>


        <div class="col-xs-9 col-sm-9"
             style="background-color: #F2F2F2; border:1px solid #ccc; margin-left: 13px; padding-left: 0;">
            <div class="col-xs-12 col-sm-12" style="padding: 10px 15px;">
                <input type="radio" id="useYes" name="usageFlag" value="0"
                       <c:if test="${medPrivate.usageFlag}">checked</c:if> > 适用
                <input id="useNo" type="radio" name="usageFlag" style="margin-left: 10px;"
                       <c:if test="${!medPrivate.usageFlag}">checked</c:if>
                       value="1"> 不适用

            </div>
            <div class="col-xs-12 col-sm-12" id="showDiv" style="margin-bottom: 10px; padding-left: 0;">
                <div class="col-xs-3 col-sm-3" style="padding-right: 0;">
                    <select id="selUseTimes" class="form-control">
                        <c:forEach var="u" items="${medicineUseTimes}">
                            <option value="${u}" <c:if test='${medPrivate.useTimes==u}'>selected</c:if>>${u}</option>
                        </c:forEach>
                    </select>
                </div>
                <label class="col-xs-2 col-sm-1 text-center" style="line-height: 32px; padding: 0;"> 每次</label>

                <div class="col-xs-2 col-sm-2" style="padding-left: 0px;">
                    <form:input path="medPrivate.useQty" min = "1" type="number" cssClass="form-control"/>
                </div>
                <div class="col-xs-2 col-sm-2" style="padding:0; margin-left:-18px;">
                    <form:select cssStyle="margin-left:22px;" path="medPrivate.useUnit" cssClass="form-control"
                                 items="${medicineUnits}"/>
                </div>
                <div class="col-xs-3 col-sm-3" style="margin-left: 25px;">
                    <select id="selUsingTime" class="form-control">
                        <c:forEach var="u" items="${medicineUsingTimes}">
                            <option value="${u}" <c:if test='${medPrivate.usingTime==u}'>selected</c:if>>${u}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

        </div>

    </div>
    <%--    <div class="form-group" id="divUseTimes">
            <div class="col-xs-offset-2 col-sm-offset-2 col-xs-3 col-sm-3">
                <select id="selUseTimes" class="form-control" style="width: 150px">
                    <c:forEach var="u" items="${medicineUseTimes}">
                        <option value="${u}" <c:if test='${medicine.useTimes==u}'>selected</c:if>>${u}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-xs-3 col-sm-3">
                <select id="selUsingTime" class="form-control" style="width: 150px">
                    <c:forEach var="u" items="${medicineUsingTimes}">
                        <option value="${u}" <c:if test='${medicine.usingTime==u}'>selected</c:if>>${u}</option>
                    </c:forEach>
                </select>
            </div>

        </div>

        <div class="form-group">
            <label class="col-xs-2 col-sm-2 control-label text-right" style="margin-left:40px; margin-right: 5px; padding-right:0;">每次</label>

            <div class="col-xs-2 col-sm-2">
                <form:input path="medicine.useQty" type="number" cssClass="form-control"/>
            </div>
            <div class="col-xs-3 col-sm-3">
                <form:select cssStyle="width: 150px; margin-left:22px;" path="medicine.useUnit" cssClass="form-control" items="${medicineUnits}" />
            </div>


            <div class="col-xs-2 col-sm-2">
                &lt;%&ndash;<button id="btnSaveMedicineUsage" type="button" class="btn btn-success">更新用量</button>&ndash;%&gt;
            </div>
        </div>--%>

    <div class="form-group" style="border-top:1px solid #ccc; padding-top: 15px;">
        <label class="col-xs-2 col-sm-2 control-label text-right" for="txtMedicineQty">数量/单位</label>

        <div class="col-xs-2 col-sm-2">
            <input type="number" min="1" class="form-control" id="txtMedicineQty" value="${medPrivate.realQty}">
        </div>
        <%--<div class="col-xs-2 col-sm-2">--%>
            <%--<select name="medicineUnit" id="selMedicineUnit" class="form-control">--%>
                <%--<c:forEach var="u" items="${medicineUnits}">--%>
                    <%--<option value="${u.key}"--%>
                            <%--<c:if test='${medPrivate.realUnit==u.key}'>selected</c:if>>${u.value}</option>--%>
                <%--</c:forEach>--%>
            <%--</select>--%>
        <%--</div>--%>

        <div class="col-xs-2 col-sm-2" style="padding: 0;">
            <select name="medicineUnit" id="selMedicineUnit" class="form-control text-success">
                <c:forEach var="u" items="${medicineUnits}">
                    <c:if test="${empty medPrivate.realUnit}">
                        <option value="${u.key}"
                                <c:if test='${medPrivate.useUnit==u.key}'>selected</c:if>>${u.value}</option>
                    </c:if>

                    <c:if test="${not empty medPrivate.realUnit}">
                        <option value="${u.key}"
                                <c:if test='${medPrivate.realUnit==u.key}'>selected</c:if>>${u.value}</option>
                    </c:if>

                </c:forEach>
            </select>
        </div>
        <label class="col-xs-1 col-sm-1 text-center" for="txtMedicineQty" style="line-height: 30px;">X</label>

        <div class="col-xs-2 col-sm-2">
            <input type="number" min="1" class="form-control" id="txtMedicineCopies" value="1">
        </div>
        <label class="col-xs-1 col-sm-1" for="txtMedicineQty" style="line-height: 30px;"> 次</label>
    </div>
    <div id="divConversion"></div>
    <div class="form-group" style="border-top:1px solid #ccc; padding-top: 15px;">
        <label class="col-xs-2 col-sm-2 control-label text-right" for="txtMedicineQty">特殊说明</label>

        <div class="col-xs-10 col-sm-10">
            <input class="form-control" type="text" id="specialInstructions" name="specialInstructions"
                   value="${specialInstructions}">
        </div>
    </div>
</div>
<script type="text/javascript">
//    $(document).ready(function () {
//        debugger
//        fuckChangeIt();
//    })
    $(function (){
        fuckChangeIt();
        $('input[name="usageFlag"]').change(function () {

            var type = $('input[name="usageFlag"]:checked').val();
            var usageFlag = true;
            if (type == 0) {
                $('#showDiv').show();
            } else {
                $('#showDiv').hide();
            }


            var url = "/medicine/updateUsageFlag/${medicine.id}";
            $.postJSON(url, {'usageFlag': usageFlag});
        });
        $('input[name="usageFlag"]').change();

        $('#selMedicineUnit').change(function () {
            var unit = $('#medicineUnit').val();
            var $opt = $('#selMedicineUnit option:selected');
            var selUnit = $('#selMedicineUnit').val();
            var text = $opt.text();
            if (unit == selUnit) {$('#divConversion,#btnConvert').hide();}
            else {
                $('#divConversion,#btnConvert').show();
                /*$('#divConversion').load("/fragment/conversion/select?medicineId=", {'unit': selUnit});*/
                $('#divConversion').load("/fragment/medicine/updateRateForm?medicineId=${medPrivate.id}", {'unit': selUnit});

            }
        });
        $('#selMedicineUnit').change();
        $('#btnSaveMedicineUsage').click(function () {
            var data = {
                id: $('#medicineId').val(),
                useTimes: $('#selUseTimes').val(),
                useQty: $('#useQty').val(),
                useUnit: $("#useUnit").val(),
                usingTime: $('#selUsingTime').val(),
                tmpErrorId: "1"
            }
            $.postJSON("/medicine/saveUsage", data, function (result) {
                if (result.success) {
                    layer.msg(result.msg);
                } else {
                    layer.msg(reult.msg);
                }
            })
        });
    });
    var groupIndex = "${groupId}";
    //西药大部分分组类型默认值
    var defaultWesternIndex = 10;

    //中药大部分分组类型默认值
    var defaultChineseIndex = 9;

    var handleType = "${handleType}";
    debugger
    var medType = "";
    var lastSelectXwOrFenBao = "${lastSelectXwOrFenBao}";

    var old = "";
    function fuckChangeIt2() {
        lastSelectXwOrFenBao = "";
        fuckChangeIt();
    }
    function fuckChangeIt() {
        debugger
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
        if ($("#selUseMode").val() == "煎服" || $("#selUseMode").val() == "") {
            $("#useGroups").hide();
            $("#groupLab").hide();
        } else {
            //FangXB
            if ("${medicine.type}" != "Chinese") {
                $("#groupLab").show();
            }

        }
    }
</script>
<script type="text/javascript">
    $("#txtMedicineQty").focus().select();
</script>