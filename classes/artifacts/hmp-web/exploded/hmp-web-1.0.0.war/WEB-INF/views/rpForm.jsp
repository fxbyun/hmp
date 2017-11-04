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
        $(function () {
            try {
                var addType = window.parent.addType;
                var medDatasObj = "";
                var emrId = "";
                var htmlData = "";
                if (addType == "western") {
                    medDatasObj = window.parent.medDataSum;
                    emrId = window.parent.addEmrId;
                    htmlData = medDatasObj[emrId];
                } else if (addType == "china") {
                    medDatasObj = window.parent.medChinaDataSum;
                    emrId = window.parent.addEmrId;
                    htmlData = medDatasObj[emrId];
                    $("#medicineType2").trigger("click");
                }

                htmlData = htmlData.replace("undefined", "");
                if (htmlData != "" && htmlData != undefined) {
                    $("#divItems").html(htmlData)
                }

            } catch (e) {
                console.info(e)
            }
        })
        function fn_RemoveElement(ele) {
            $(ele).remove();
        }
        function fn_GetMedicineInfo(medicineId) {
            var $tag = $('#rpMedicineTag_' + medicineId);
            if ($tag.length == 0) return;
            return {
                'companyId': $tag.find('input[name="companyIds"]').val(),
                'qty': $tag.find('input[name="medicineQty"]').val(),
                'rate': $tag.find('input[name="medicineRate"]').val(),
                'unit': $tag.find('input[name="medicineUnit"]').val(),
                'copies': $tag.find('input[name="medicineCopies"]').val(),
                'useMode': $tag.find('input[name="medicineUseModes"]').val(),
                'hasUsage': $tag.find('input[name="medicineHasUsages"]').val()
            }
        }
        function fn_ShowSelectMedicine(medicineId, medType) {
            //弹出框的宽高
            var height = "";
            var width = "";
            var arr = $.map($('#divItems li input[name="medicineIds"]'), function (n, i) {
                return $(n).val();
            });
            var tmp = "" + medicineId;
            if ($.inArray(tmp, arr) != -1) {
                var index = layer.confirm("此药品已添加，是否修改已选药品用量", function () {
                    layer.close(index);
                    layer.open({
                        type: 2,
                        title: '选择药品',
                        area: ['680px', '575px'],
                        scrollbar: false,
                        content: '/fragment/medicine/select/' + medicineId
                    });
                });
            } else {
                if (medType == "Western") {
                    height = "700px";
                    width = "690px";
                } else {
                    height = "700px";
                    width = "600px";
                }
                layer.open({
                    type: 2,
                    title: '选择药品',
                    area: [height, width],
                    scrollbar: false,
                    content: '/fragment/medicine/select/' + medicineId + "?type=add"
                });
            }
        }

        function fn_ShowEditMedicine(medicineId, types, tagMedId) {
            var groupIndex = 10;
            var txtMedicineQty = "";

            //药品的特殊说明
            var specialInstru = "";
            //药品的规格
            var standard = "";

            if (tagMedId != null && tagMedId != undefined && tagMedId != "") {
                groupIndex = $("#" + tagMedId).find("input[name='medicineGroupId']").val();
                txtMedicineQty = $("#" + tagMedId).find("input[name='medicineQty']").val();
                specialInstru = $("#" + tagMedId).find("input[name='specialInstructions']").val();
                standard = $("#" + tagMedId).find("input[name='standard']").val();
            }
            layer.open({
                type: 2,
                title: '选择药品',
                area: ['680px', '675px'],
                scrollbar: false,
                content: '/fragment/medicine/select/' + medicineId + "?type=" + types + "&groupId=" + groupIndex + "&txtMedicineQty=" + txtMedicineQty + "&specialInstructions=" + decodeURI(specialInstru) + "&standard=" + decodeURI(standard)
            });

        }

        function fn_EditMedicine(medicineId, type, callback) {
            layer.open({
                type: 2,
                title: '修改药品',
                area: ['720px', '600px'],
                scrollbar: false,
                content: '/medicine/edit/' + medicineId,
                end: callback
            });
        }

        function fn_AddMedicine(type) {
            layer.open({
                type: 2,
                title: '添加药品',
                area: ['720px', '600px'],
                scrollbar: false,
                content: '/medicine/add?type=' + type,
                end: function () {
                    fn_LoadMedicinePage(type, 0);
                }
            });
        }

        /**
         * 递归搜索ID返回最大ID +S
         * @param medId
         * @returns {*}
         */
        function recursionMed(medId) {
            $("#divItems span").each(function () {
                if ($(this).attr("id") == ("rpMedicineTag_" + medId)) {
                    medId = medId + "s";
                    medId = recursionMed(medId);
                }
            })
            return medId;
        }

        function fn_SelectMedicine(data) {
//            console.info(data)
            if (data.copies === undefined)data.copies = 1;
            if (data.useMode === undefined)data.useMode = '口服';
            if (data.hasUsage === undefined)data.hasUsage = true;
            if (data.useTimes === undefined)data.useTimes = "";
            if (data.usingTime === undefined)data.usingTime = "";
            if (data.useQty === undefined)data.useQty = "";
            if (data.useUnit === undefined)data.useUnit = "";
            if (data.groupIndex === undefined)data.groupIndex = "0";
            if (data.groupIndex === "")data.groupIndex = "0";
            if (data.groupIndex === null)data.groupIndex = "0";
            if (data.openType === undefined)data.openType = "edit";
            if (data.multiplexTag === undefined)data.multiplexTag = "";
            if (data.multiplexTag === null)data.multiplexTag = "";
            //药品特殊说明
            if (data.specialInstructions === undefined)data.specialInstructions = "";
            if (data.specialInstructions === null)data.specialInstructions = "";
            //药品规格
            if (data.standard === undefined)data.standard = "";
            if (data.standard === null)data.standard = "";
            if (data.tjUnit === null || data.tjUnit == undefined)data.tjUnit = data.unit;
            if (data.price === null || data.price === undefined)data.price = 0;
            if (data.unitPrice === null || data.unitPrice === undefined || data.unitPrice == "")data.unitPrice = 0;

            if (data.openType == "add") {
                var tmpTagsOne = "rpMedicineTag_" + data.medicineId;

                if ($("#divItems span").size() > 0) {
                    $("#divItems span").each(function () {
                        if ($(this).attr("id") == tmpTagsOne) {
                            data.medicineId = recursionMed(data.medicineId);
                        }
                    });
                }
            }


            var arr = $.map($('#divItems span input[name="medicineIds"]'), function (n, i) {
                return $(n).val();
            });
            var tmp = "" + data.medicineId;
            var exist = ($.inArray(tmp, arr) != -1);
            var tag = "rpMedicineTag_" + data.medicineId;
            var ele =
                    '<span class="tag" id="{0}">{1}&nbsp;{2}{3}x{4}份'.format(tag, data.medicineName, data.qty, data.unitLabel, data.copies) +
                    '<input type="hidden" name="medicineIds" value="' + data.medicineId + '" />' +
                    '<input type="hidden" name="companyIds" value="' + data.companyId + '" />' +
                    '<input type="hidden" name="prices" value="' + data.price + '" />' +
                    '<input type="hidden" name="tjUnits" value="' + data.tjUnit + '" />' +
                    '<input type="hidden" name="medicineQty" value="' + data.qty + '" />' +
                    '<input type="hidden" name="medicinePrivateIds" value="' + data.medicinePrivateId + '" />' +
                    '<input type="hidden" name="unitPrices" value="' + data.unitPrice + '" />' +
                    '<input type="hidden" name="medicineRate" value="' + data.rate + '" />' +
                    '<input type="hidden" name="medicineUnit" value="' + data.unit + '" /> ' +
                    '<input type="hidden" name="medicineCopies" value="' + data.copies + '" /> ' +
                    '<input type="hidden" name="medicineUseModes" value="' + data.useMode + '" /> ' +
                    '<input type="hidden" name="medicineHasUsages" value="' + data.hasUsage + '" /> ' +
                    '<input type="hidden" name="medicineUseTimes" value="' + data.useTimes + '" /> ' +
                    '<input type="hidden" name="medicineUseUsingTime" value="' + data.usingTime + '" /> ' +
                    '<input type="hidden" name="medicineUseQty" value="' + data.useQty + '" /> ' +
                    '<input type="hidden" name="medicineUseUnit" value="' + data.useUnit + '" /> ' +
                    '<input type="hidden" name="medicineGroupId" value="' + data.groupIndex + '" /> ' +
                    '<input type="hidden" name="specialInstructions" value="' + data.specialInstructions + '" /> ' +
                    '<input type="hidden" name="standard" value="' + data.standard + '" /> ' +
                    '<a href="javascript:" onclick="fn_ShowEditMedicine(\'{0}\',\'edit\',\'{1}\')"><i class="fa fa-cog"></i></a> '.format(data.medicineId, tag) +
                    '<a href="javascript:" onclick="fn_RemoveElement($(this).parent())"><i class="fa fa-times"></i></a> ' +
                    '</span>';
            if (exist) {
                var $tag = $("#" + tag);
                $tag.after(ele);
                $tag.remove();
            }
            else {
                $('#divItems').append(ele);
            }
        }
        function fn_LoadMedicinePage(type, page, name) {
            if (page == undefined) page = 0;
            if (name == undefined) name = "";
            var url = "/fragment/medicines2/{0}?page={1}&name={2}".format(type, page, name);
            $('#divMedicines').load(url);
        }

        function fn_LoadOtherMedicinePage(type, page, name) {
            if (page == undefined) page = 0;
            if (name == undefined) name = "";
            var url = "/fragment/otherMedicines2/{0}?page={1}&name={2}".format(type, page, name);
            $('#divMedicines').load(url);
        }

        function fn_MedicineTypeChange() {
            var type = $('input[name="medicineType"]:checked').val();
            fn_LoadMedicinePage(type, 0);
        }

        function fn_LoadCategories() {
            var sel = $('#sltCategory').val();
            $.getJSON("/rpCate", function (data) {
                $('#sltCategory').empty();
                $.each(data, function (i, n) {
                    $('#sltCategory').append('<option value="{0}">{1}</optioin>'.format(n.id, n.name));
                })
                $('#sltCategory').val(sel);
            })
        }

        function fn_AddCategory() {
            layer.open({
                type: 2,
                title: '新增药方类别',
                area: ['500px', '200px'],
                scrollbar: false,
                content: '/rpCate/add/',
                end: fn_LoadCategories
            });
        }

        function fn_EditCategory() {
            var sel = $('#sltCategory').val();
            if (sel) {
                layer.open({
                    type: 2,
                    title: '修改药方类别',
                    area: ['500px', '200px'],
                    scrollbar: false,
                    content: '/rpCate/edit/' + sel,
                    end: fn_LoadCategories
                });
            }
        }
        //TODO 修改为类别管理弹框 2016-6-15 11:05:43
        function fn_ManageCategory() {
            //var sel = $('#sltCategory').val();
            //if (sel) {
            layer.open({
                type: 2,
                title: '类别管理',
                area: ['680px', '500px'],
                scrollbar: false,
                content: '/rpCate/manage/',
                end: fn_LoadCategories
            });
            //}
        }

        $(function () {
            var index = parent.layer.getFrameIndex(window.name);
            var isFormChange = false;
            $('input[name="medicineType"]').change(fn_MedicineTypeChange);
            $('#btnSelectCompany').click(function () {
                layer.open({
                    type: 2,
                    title: '选择添加药厂',
                    area: ['650px', '500px'],
                    scrollbar: false,
                    content: '/fragment/companies'
                });
            });
            fn_MedicineTypeChange();
            $("#frmRp").validate();
            $('#frmRp').change(function () {
                isFormChange = true;
            });
            $('#btnClose').click(function () {
                if (isFormChange) {
                    layer.confirm("信息已修改，关闭将丢失所有信息。确认关闭？", function () {
                        parent.layer.close(index);
                    });
                } else parent.layer.close(index);
            });
            $('#toAdd').click(function () {
                if (isFormChange) {
                    layer.confirm("信息已修改，离开将丢失所有信息。确认离开？", function () {
                        location.href = "/rp/add";
                    });
                } else location.href = "/rp/add";
            });
        })
        function fn_SelectDiagnosis() {
            layer.open({
                type: 2,
                title: '选择诊断结果',
                area: ['780px', '540px'],
                scrollbar: false,
                content: '/rp/select/diagnosis'
            });
        }
    </script>
</head>
<body>
<div style="margin: 15px; background-color: #fff; border-radius: 10px; padding: 50px;">
    <form:form action="/rp/save" cssClass="form-horizontal" method="post" modelAttribute="rp" id="frmRp">
        <form:hidden path="id"/>
        <div class="form-group">
            <label for="sltCategory" class="col-xs-2 col-sm-2 control-label">药方类别</label>

            <div class="col-xs-6 col-sm-6">
                <form:select path="categoryId" items="${categories}" itemValue="id" itemLabel="name"
                             cssClass="form-control" id="sltCategory"/>
            </div>
            <div class="col-xs-4 col-sm-4">
                    <%--<button onclick="fn_EditCategory()" type="button" class="btn btn-info btn-sm"><i class="fa fa-pencil"></i> 修改类别</button>
                    <button onclick="fn_AddCategory()" type="button" class="btn btn-success btn-sm"><i class="fa fa-plus"></i> 新增类别</button>--%>
                <button onclick="fn_ManageCategory()" type="button" style="width: 80px;" class="btn btn-success btn-sm">
                    类别管理
                </button>
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-2 col-sm-2 control-label">药方分类</label>

            <div class="col-xs-10 col-sm-10">
                <form:bsradiobuttons path="medicineType" items="${medicineTypes}" readonly="true"
                                     labelCssClass="radio-inline"/>
            </div>
        </div>
        <div class="form-group">
            <label for="txtName" class="col-xs-2 col-sm-2 control-label">适用病症</label>

            <div class="col-xs-2 col-sm-2">
                <button onclick="fn_SelectDiagnosis()" type="button" class="btn btn-success btn-sm">选择诊断结果</button>
            </div>
            <div class="col-xs-4 col-sm-4">
                <form:input path="diagnosis" cssClass="form-control" id="txtDiagnosis" placeholder="适用病症"/>
            </div>
        </div>
        <div class="form-group">
            <label for="txtName" class="col-xs-2 col-sm-2 control-label">药方名称</label>

            <div class="col-xs-6 col-sm-6">
                <form:input path="name" cssClass="form-control required" id="txtName" placeholder="请输入名称"/>
            </div>
        </div>
        <div class="form-group">
            <label for="txtSpec" class="col-xs-2 col-sm-2 control-label">药方详情</label>

            <div class="col-xs-10 col-sm-10">
                <form:textarea path="remark" cssClass="form-control" id="txtspec" rows="3"></form:textarea>
            </div>
        </div>
        <div class="form-group">
            <label for="txtSpec" class="col-xs-2 col-sm-2 control-label">用药清单</label>

            <div class="col-xs-10 col-sm-10">
                <div id="divItems" class="label-box">
                    <c:forEach var="item" items="${rp.prescriptionItemList}">
                        <span class="tag"
                              id="rpMedicineTag_${item.medicineId}${item.multiplexTag}">${item.medicineName}&nbsp;${item.qty}${medicineUnits[item.unit]}x${item.copies}份
                            <input name="medicineIds" value="${item.medicineId}${item.multiplexTag}" type="hidden">
                            <input name="companyIds" value="${item.companyId}" type="hidden">
                            <input name="medicineQty" value="${item.qty}" type="hidden">
                            <input name="medicineRate" value="${item.rate}" type="hidden">
                            <input name="medicineUnit" value="${item.unit}" type="hidden">
                            <input type="hidden" name="prices" value="${item.price}"/>
                            <input type="hidden" name="tjUnits" value="${item.tjUnit}"/>
                            <input type="hidden" name="unitPrices" value="${item.unitPrice}"/>
                            <input type="hidden" name="medicinePrivateIds" value="${item.medicinePrivateId}"/>
                            <input type="hidden" name="medicineCopies" value="${item.copies}"/>
                            <input type="hidden" name="medicineUseModes" value="${item.useMode}"/>
                            <input type="hidden" name="medicineHasUsages" value="${item.hasUsage}"/>
                            <input name="medicineUseTimes" value="${item.useTimes}" type="hidden">
                            <input name="medicineUseUsingTime" value="${item.usingTime}" type="hidden">
                            <input name="medicineUseQty" value="${item.useQty}" type="hidden">
                            <input name="medicineUseUnit" value="${item.useUnit}" type="hidden">
                            <input name="medicineGroupId" value="${item.groupIndex}" type="hidden">
                            <input name="specialInstructions" value="${item.specialInstructions}" type="hidden">
                            <input name="standard" value="${item.standard}" type="hidden">
                            <a href="javascript:"
                               onclick="fn_ShowEditMedicine('${item.medicineId}${item.multiplexTag}','edit','rpMedicineTag_${item.medicineId}${item.multiplexTag}')"><i
                                    class="fa fa-cog"></i></a>
                            <a href="javascript:" onclick="fn_RemoveElement($(this).parent())"><i
                                    class="fa fa-times"></i></a>
                        </span>
                    </c:forEach>
                </div>
                <div id="divMedicines" class="medicine-label-box"></div>
            </div>
        </div>

        <div class="form-group">
            <div class="col-xs-offset-2 col-sm-offset-2 col-xs-5 col-sm-5">
                <button type="submit" class="btn btn-success"><i class="fa fa-save"></i> 保存药方</button>
                <button id="toAdd" type="button" class="btn btn-success"><i class="fa fa-plus"></i> 继续新增</button>
                <button id="btnClose" type="button" class="btn btn-default"><i class="fa fa-times"></i> 关闭</button>
            </div>
            <div class="col-xs-5 col-sm-5"><h5 class="text-danger">${msg}</h5></div>
        </div>

    </form:form>
</div>
<script type="text/javascript" src="${ctx}/assets/bootstrap/js/bootstrap.min.js"></script>
<hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/hmp.js" type="js"/>
<script src="${ctx}/assets/jquery-validation/1.11.1/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctx}/assets/jquery-validation/1.11.1/messages_bs_zh.js" type="text/javascript"></script>
</body>
</html>