<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/7/11
  Time: 16:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<%@ include file="/WEB-INF/commons/staticInclude.jsp" %>
<html>
<head>
    <title>药品详情</title>
    <script>
        var medType = "${medicine.type}";
        var index = parent.layer.getFrameIndex(window.name);
        var xwArray = ["针灸", "拔罐", "按摩", "贴敷"];
        var groupId = "${groupId}";
        $(function () {
            parent.layer.close(parent.index_Layer_hunde);
            $("#entryPre").addClass("active");
            $("#conversionDiv").load("/learn/subPage/selMed/conversion/select?medicineId=${medicine.medicine.id}", {'unit': $('#selMedicineUnit').val()});

            //药品 标准用量使用切换
            $('input[name="type"]').change(function () {
                var type = $('input[name="type"]:checked').val();
                if (type == 0) {
                    $('#divMedicineUsage').load('/learn/subPage//selMed/medicine/usage/${medicine.id}');
                } else {
                    $('#divMedicineUsage').empty();
                }
            });
            var type = $('input[name="type"]:checked').val();
            if (type == 0) {
                $('#divMedicineUsage').load('/learn/subPage/selMed/medicine/usage/${medicine.id}');
            }

            //点击确定按钮
            $('#btnSubmitMedicine').click(function () {
                var qty = $('#txtMedicineQty').val();
                if (!$.isNumeric(qty)) {
                    layer.msg('请输入药品数量');
                    return;
                }


                var $opt = $('#selMedicineUnit option:selected');
                var unit = $opt.val();
                var unitLabel = $opt.text();
                var rate = 1;


                var groupMsg = $("#useGroups").val();
//
                if ($("#selUseMode").val() == "研末") {
                    if ((groupMsg == "" || parseInt(groupMsg) <= 0) || isNaN(parseInt(groupMsg))) {
                        groupMsg = "0";
                        if (parseInt(groupMsg) <= 0) {
                            layer.msg("请输入格式正确的分包数量");
                        } else {
                            layer.msg("请输入研末所需要的分包数量");
                        }
                        return;
                    }

                }
                if ($("#select2-useGroups-container").text() == "请选择穴位" && $.inArray($("#selUseMode").val(), xwArray) >= 0) {
                    layer.msg("该类型必须选择穴位,请选择正确的穴位!")
                    return;
                }

                //保存药品规格到医生的私人库中
                <%--$.get("/medicine/saveStandard/${medicine.id}?standard=" + $("#txtStandard").val());--%>
                //保存标准用量到医生的私人库中
                $.postJSON("/medicine/saveUsage", $('#frmMedicineUsage').serializeArray(), function () {
                    var data = {
                        'medicineId': "${medicine.medicine.id}${haveMore}",
                        'medicineName': '${medicine.name}',
                        'companyId': -1,
                        'unit': unit, 'unitLabel': unitLabel,
                        'qty': qty, 'rate': rate,
                        'copies': $('#txtMedicineCopies').val(),
                        'useMode': $('#selUseMode').val(),
                        'hasUsage': ($('input[name="type"]:checked').val() == 0),
                        'useTimes': $('#useTimes').val(),
                        'usingTime': $('#txtUsingTime').val(),
                        'useQty': $("#useQty").val(),
                        "useUnit": $("#useUnit option:selected").text(),
                        "groupId": groupMsg,
                        "openType": "${openType}",
                        "id": "${medicine.id}",
                        "specialInstructions": $("#specialInstructions").val(),
//                        "standard": $("#txtStandard").val(),
                        "medType": "${medicine.type}"
                    };
                    if ((medType != "Western" && $.inArray($("#selUseMode").val(), xwArray) < 0) || data.groupId == 0) {
                        data.groupId = "10";
                    }
                    parent.isNeedDel = true;
                    parent.addMedToDivByMedType(data);
//                    parent.layer.close(index);
                    parent.utlis_HiddOrShowHearAndBodyAndFooter(HIDD_SHOW.Show, "edit");
                });


            });

            //点击关闭按钮
            $('#btnCloseMedicine').click(function () {
//                parent.layer.close(index);
                parent.utlis_HiddOrShowHearAndBodyAndFooter(HIDD_SHOW.Show, "edit");
            });


            fuckChangeIt()
        });


        function fuckChangeIt() {
            if ($("#selUseMode").val() == "研末") {
                if ($("span[dir='ltr']")) {
                    $("span[dir='ltr']").remove();
                }
                $("#groupLab").text("分包");
                $("#useGroups").replaceWith("<input type='text' " +
                        "id='useGroups' name='useGroups' class='form-control' " +
                        "value='' style='width: 41px;float: left;;' placeholder='请输入 1,2,3来分包数;默认9包' > " +
                        '<label class=" control-label text-right" style="display: inline-block; width: 30%;" id="packetId">包</label>');


                if (groupId != 10) {
                    $("#useGroups").val(groupId);
                } else {
                    $("#useGroups").val(9);
                }


            } else if ($.inArray($("#selUseMode").val(), xwArray) >= 0) {

                if ("穴位" == $("#groupLab").text()) {
                    return
                }

                $("#useGroups").replaceWith('<select name="useGroups" id="useGroups" class="form-control" style="display: inline-block; width: 30%;"></select>');
                $("#groupLab").text("穴位");
                $(acuPointsArrs).each(function (index, val) {
                    $("#useGroups").append('<option value="{0}">{1}</option>'.format(index, val));
                })

                $("#packetId").hide();
                $("#useGroups").select2({
                    language: "zh-CN"
                });


                if (groupId != 10) {
                    $("#useGroups").val(groupId);
                    $("#select2-useGroups-container").text(acuPointsArrs[groupId]);
                    $("#select2-useGroups-container").attr("title", acuPointsArrs[groupId]);
                } else {
                    $("#useGroups").val(acuPointsArrs[9]);
                }


            }
            else {
                $("#groupLab").text("分组");
                if ($("span[dir='ltr']")) {
                    $("span[dir='ltr']").remove();
                }
                $("#useGroups").replaceWith('<select name="useGroups" id="useGroups" class="form-control" style="display: inline-block; width: 30%;">' +
                        '<option value="10">不分组</option><option value="1">分组一</option>' +
                        '<option value="2">分组二</option><option value="3">分组三</option>' +
                        '<option value="4">分组四</option><option value="5">分组五</option>' +
                        '<option value="6">分组六</option><option value="7">分组七</option>' +
                        '<option value="8">分组八</option><option value="9">分组九</option></select>');
                $("#packetId").hide();

                if (groupId == "0" || groupId == "") {
                    groupId = 10;
                }
                $("#useGroups").val(groupId);
            }
        }


    </script>
</head>
<body>
<div>


    <div style="padding: 1rem;">
        <h3>
            ${medicine.name}</h3>

        <p>
            <span style="padding-right: 0.9rem;">药品分类</span>
            <c:if test="${medicine.type == 'Western'}">
                <select id="selMCate" class="form-control" style="display: inline-block; width: 77%;">
                    <option value="">未分类</option>
                    <c:forEach var="c" items="${westernMedicineCate}">
                        <option value="${c}" <c:if test='${medicine.category==c}'>selected</c:if>>${c}</option>
                    </c:forEach>
                </select>
            </c:if>
            <c:if test="${medicine.type == 'Chinese'}">
                <select id="selMCate" class="form-control" style="display: inline-block; width: 77%;">
                    <option value="">未分类</option>
                    <c:forEach var="c" items="${chineseMedicineCate}">
                        <option value="${c}" <c:if test='${medicine.category==c}'>selected</c:if>>${c}</option>
                    </c:forEach>
                </select>
            </c:if>
        </p>

        <p>
            <span style="padding-right: 0.9rem;">治疗方式</span>
            <select id="selUseMode" class="form-control" style="display: inline-block; width: 30%;"
                    onchange="fuckChangeIt()">
                <c:forEach var="u" items="${medicineUseModes}">
                    <option value="${u}" <c:if test='${medicine.useMode==u}'>selected</c:if>>${u}</option>
                </c:forEach>
            </select>
            <span style="padding:0 0.8rem;" id="groupLab">分组</span>
            <select class="form-control" style="display: inline-block; width: 30%;" id="useGroups">
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
        </p>
        <p><span>标准用量</span></p>

        <div style="border: 1px solid #ccc; padding: 1rem;">
            <p class="text-center">
                <input type="radio" name="type" style="width: 1.5rem;"
                       <c:if test="${medicine.type != 'Chinese'}">checked</c:if> value="0">适用
                <input type="radio" name="type"
                       <c:if test="${medicine.type == 'Chinese'}">checked</c:if> value="1">不适用
            </p>
            <div id="divMedicineUsage">
            </div>
        </div>

        <p style="margin-top: 0.8rem;"><span>数量/单位</span></p>

        <div>
            <input type="text" class="form-control text-center" style="display: inline-block; width: 15%;"
                   value="${medicine.useQty}" id="txtMedicineQty">
            <select class="form-control" style="display: inline-block; width: 30%;" id="selMedicineUnit">
                <c:forEach var="u" items="${medicineUnits}">
                    <option value="${u.key}" <c:if test='${medicine.useUnit==u.key}'>selected</c:if>>${u.value}</option>
                </c:forEach>
            </select>
            <span style="padding: 0 1rem; font-size: 20px;">x</span>
            <input type="text" class="form-control text-center" style="display: inline-block; width: 15%;"
                   id="txtMedicineCopies" value="1">
            <label style="padding-left: 1rem;">份</label>

            <p style="margin-top: 1rem; color: #218a3f;" id="conversionDiv">
            </p>

        </div>

        <p style="margin-top: 2.5rem;">
            <span style="padding-right: 0.9rem;">特殊说明</span>
            <input id="specialInstructions" name="specialInstructions" class="form-control"
                   style="display: inline-block; width: 76%;" value="${specialInstructions}">
        </p>

        <p style="margin-top: 1.5rem;" class="text-center btn-bgcolor">
            <a type="button" class="btn btn-success" id="btnSubmitMedicine">确定</a>
            <a type="button" class="btn btn-success" id="btnCloseMedicine">返回</a>
        </p>
    </div>

</div>
</body>
</html>
