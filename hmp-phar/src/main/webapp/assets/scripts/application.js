var SYMPTOM_TAGS_ID = '#divSymptomTags';
var DIAGNOSIS_TAGS_ID = '#divDiagnosisTags';
var DEPARTMENT_TABS_ID = '#tabsDepartment';
var WESTERN_MEDICINES_ID = '#divWesternMedicines';
var CHINESE_MEDICINES_ID = '#divChineseMedicines';

var sss;
function fn_RemoveElement(ele) {
    debugger
    sss = ele;
    var indexWz = $("#divSymptom span[class='tag']").index($(ele));
    if (indexWz >= -1) {
        $('#talkcontentone span[class="tag"]').eq(indexWz).remove();
    }

    $('#talkChuBuZd span').each(function () {

        var eles = $(ele).find("input[name='diagnosisResults']").val();
        if (eles != undefined) {
            eles = eles.replace(" ", "");
            if (eles == $(this).text()) {
                $(this).remove()
            }
        }

    })
    var findId = $(ele).attr("id");
    $(ele).remove();
    if ($("#divChineseItems span").size() == "0") {
        $("#chineseQty").val("0");
    }
    try {
        delOldMed({"medicineId": findId.replace("medicineTag_", ""), "multiplexTag": ""})
        priceMap.remove(findId);
        deletMedForPaper(findId);
        clearAddNewItem();
    } catch (e) {

    }


}

/* 查询收费明细 */
function selectChargeDetail() {
    layer.open({
        type: 2,
        title: '收费明细',
        area: ['650px', '400px'],
        scrollbar: false,
        content: '/fragment/chargeDetail'
    });
}

function fn_BackspaceSymptomTag(ele) {
    $('#divSymptom').find(":last-child").remove()
    $('#talkcontentone').find(":last-child").remove()
}

function fn_LoadSymptomTag(page, name) {
    if (page == undefined) page = 0;
    if (name == undefined) name = "";
    name = name.replace(/[ ]/g, "");

    $(SYMPTOM_TAGS_ID).load('/fragment/symptomTags?page={0}&name={1}'.format(page, name));


}

function fn_LoadSymptomOtherTag(page, name) {
    if (page == undefined) page = 0;
    if (name == undefined) name = "";
    name = name.replace(/[ ]/g, "");

    $(SYMPTOM_TAGS_ID).load('/fragment/symptomOtherTags?page={0}&name={1}'.format(page, name));


}

function fn_SelectSymptomTag(tagId, tag) {
    var arr = $.map($('#divSymptom span input[name="mainSuits"]'), function (n, i) {
        return $(n).val();
    });
    if ($.inArray(tag, arr) != -1) {
        layer.msg("此标签已添加,勿重复添加");
        return;
    }
    var ele = '<span class="tag" onclick="fn_RemoveElement(this)" data-id="">' +
        '<input type="hidden" name="symptomTagIds" value="{0}"/>'.format(tagId) +
        '<input type="hidden" name="mainSuits" value="{0}"/>{1}</span>'.format(tag, tag);
    $('#divSymptom').append(ele);

    ele = ele.replace('fn_RemoveElement(this)', "");
    ele = ele.replace('name="symptomTagIds"', "");
    ele = ele.replace('name="mainSuits"', "");

    $('#talkcontentone').append(ele);


}
function fn_AddSymptomTag(tag) {
    if (tag) {
        $.postJSON("/tag/addSymptomTag", {"symptomTag": tag}, function (res) {
            if (res && res.success) {
                if (res.msg)layer.msg(res.msg);
                //fn_LoadSymptomTag(0);
                var arr = $.map($('#divSymptom span input[name="mainSuits"]'), function (n, i) {
                    return $(n).val();
                });
                if ($.inArray(tag, arr) == -1) {
                    fn_SelectSymptomTag(res.data.id, res.data.name);
                }
            }
        })
    }
}

function fn_SelectSymptomOtherTag(tag) {
    var arr = $.map($('#divSymptom span input[name="mainSuits"]'), function (n, i) {
        return $(n).val();
    });
    if ($.inArray(tag, arr) != -1) {
        layer.msg("此标签已添加,勿重复添加");
        return;
    }
    fn_AddSymptomTag(tag);
}

function fn_SelectQtyUnitTag(tag) {
    var ele = '<span class="tag" onclick="fn_RemoveElement(this)">' +
        '<input type="hidden" name="mainSuits" value="{0}"/>{1}</span>'.format(tag, tag);
    $('#divSymptom').append(ele);

    ele = ele.replace('fn_RemoveElement(this)', "");
    ele = ele.replace('name="mainSuits"', "");
    $('#talkcontentone').append(ele);
}

function fn_LoadDiagnosisTag(page, name) {
    if (page == undefined) page = 0;
    if (name == undefined) name = "";
    name = name.replace(/[ ]/g, "");
    var departmentId = $(DEPARTMENT_TABS_ID).find('input:radio:checked').val();

    $(DIAGNOSIS_TAGS_ID).load('/fragment/diagnosisTags?page={0}&departmentId={1}&name={2}'.format(page, departmentId, name));

}

function fn_LoadDiagnosisOtherTag(page, name) {
    if (page == undefined) page = 0;
    if (name == undefined) name = "";
    name = name.replace(/[ ]/g, "");
    var departmentId = $(DEPARTMENT_TABS_ID).find('input:radio:checked').val();

    $(DIAGNOSIS_TAGS_ID).load('/fragment/diagnosisOtherTags?page={0}&departmentId={1}&name={2}'.format(page, departmentId, name));


}

function fn_SelectDiagnosisTag(tagId, tag) {
    var arr = $.map($('#divDiagnosis span input[name="diagnosisResults"]'), function (n, i) {
        return $(n).val();
    });
    if ($.inArray(tag, arr) != -1) {
        layer.msg("此标签已添加,勿重复添加");
        return;
    }
    var ele = '<span class="tag" onclick="fn_RemoveElement(this);removeTe()">' +
        '<input type="hidden" name="diagnosisTagIds" value="{0}"/>'.format(tagId) +
        '<input type="hidden" name="diagnosisResults" value="{0}"/>{1}</span>'.format(tag, tag);

    $('#divDiagnosis').append(ele);

    $('#talkChuBuZd').append('<span class="tag" >' + tag + '</span>');
    fn_LoadSuggest(tag);
    fn_LoadRP(tag);
}
function removeTe() {
    if ($("#divDiagnosis").find("span").size() == 0) {
        $("#divWesternRp").html("");
        divSp = "";
        jyHtml = "";
        $("#divSuggest").html("");

    }
}

function removeTeSun() {
    try {
        if ($("#divSuggest").find("div").size() == 1) {
            divSp = "";
            jyHtml = "";
            $("#divSuggest").html("");
        }
    } catch (e) {

    }
}
function fn_LoadSuggest(tag) {
    var arr = $.map($('#divSuggest div input[name="suggestContents"]'), function (n, i) {
        return $(n).val();
    });
    var url = '/suggest?name={0}'.format(tag);
    $.getJSON(url, function (res) {
        if (res && res.success) {
            $.each(res.data, function (i, n) {
                if ($.inArray(n.content, arr) == -1) {
                    var ele = '<div style="font-size: 16px">{0}'.format(n.content) +
                        '<a href="javascript:" onclick="removeTeSun();fn_RemoveElement($(this).parent())"><i class="fa fa-times"></i></a>' +
                        '<input type="hidden" name="suggestIds" value="{0}"/><input type="hidden" name="suggestContents" value="{1}"/></div>'.format(n.id, n.content);
                    $('#divSuggest').append(ele);
                } else {
                    arr.push(n.content);
                }
            });
        }
    })
}

function fn_LoadRP(tag) {
    var westernRpArr = $.map($('#divWesternRp span'), function (n, i) {
        return $(n).html();
    });
    var chineseRpArr = $.map($('#divChineseRp span'), function (n, i) {
        return $(n).html();
    });
    var url = '/rp/find?tag={0}'.format(tag);
    $.getJSON(url, function (res) {
        if (res && res.success) {
            $.each(res.data, function (i, n) {
                var ele = '<span class="rp-span" onclick="fn_LoadRpMedicine({0})">{1}</span>'.format(n.id, n.name);
                if (n.medicineType == 'Western') {
                    if ($.inArray(n.name, westernRpArr) == -1) {
                        $('#divWesternRp').append(ele);
                    }
                } else {
                    if ($.inArray(n.name, chineseRpArr) == -1) {
                        $('#divChineseRp').append(ele);
                    }
                }
            });
        }
    })
}

function fn_LoadRpMedicine(rpId) {
    $.getJSON("/rp/{0}/items".format(rpId), function (res) {
        $.each(res, function (i, n) {
            fn_SelectMedicine(n, n.medicineType);
        });
    })
}

function fn_AddDiagnosisTag(tag) {
    if (tag) {
        var departmentId = $('#tabsDepartment').find('input:radio:checked').val();
        $.postJSON("/tag/addDiagnosisTag", {"diagnosisTag": tag, 'departmentId': departmentId}, function (res) {
            if (res && res.success) {
                if (res.msg)layer.msg(res.msg);
                fn_SelectDiagnosisTag(res.data.id, res.data.name);
            }
        })
    }
}
function fn_SelectDiagnosisOtherTag(tag) {
    var arr = $.map($('#divDiagnosis span input[name="diagnosisResults"]'), function (n, i) {
        return $(n).val();
    });
    if ($.inArray(tag, arr) != -1) {
        layer.msg("此标签已添加,勿重复添加");
        return;
    }
    fn_AddDiagnosisTag(tag);
}


/*开始坐诊药品选择方法（选择药品弹窗）*/
function fn_ShowSelectMedicine(medicineId, medType) {
    //弹出框的宽高
    var height = "";
    var width = "";
    if (selectMedUseModType == undefined) {
        selectMedUseModType = "";
    }
    if (lastSelectXwOrFenBao == undefined) {
        lastSelectXwOrFenBao = "";
    }

    var arr = $.map($('#divWesternItems li input[name="medicineIds"],#divChineseItems li input[name="medicineIds"]'), function (n, i) {
        return $(n).val();
    });
    var tmp = "" + medicineId;
    if ($.inArray(tmp, arr) != -1) {
        var index = layer.confirm("此药品已添加，是否修改已选药品用量", function () {
            layer.close(index);
            layer.open({
                type: 2,
                title: '选择药品',
                area: ['680px', '675px'],
                scrollbar: false,
                content: '/fragment/medicine/select/' + medicineId
            });
        });
    } else {
        if (medType == "Western") {
            height = "700px";
            width = "700px";
        } else {
            height = "700px";
            width = "600px";
        }

        var types = "add";
        if (medType == "edit") {
            types = medType;
        }

        layer.open({
            type: 2,
            title: '选择药品',
            area: [height, width],
            scrollbar: false,
            content: '/fragment/medicine/select/' + medicineId + "?type=" + types + "&selectMedUseModType=" + selectMedUseModType + "&lastSelectXwOrFenBao=" + lastSelectXwOrFenBao
        });
    }
}
function fn_GetMedicineInfo(medicineId) {
    var $tag = $('#medicineTag_' + medicineId);
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

function fn_ShowEditMedicine(medicineId, types, tagMedId, medType) {

    var groupId = 10;
    var txtMedicineQty = "";
    //特殊药品说明
    var specialInstru = "";
    //弹出框的宽高
    var height = "";
    var width = "";
    var standard = "";
    if (tagMedId != null && tagMedId != undefined && tagMedId != "") {
        groupId = $("#" + tagMedId).find("input[name='medicineGroupId']").val();
        txtMedicineQty = $("#" + tagMedId).find("input[name='medicineQty']").val();
        specialInstru = $("#" + tagMedId).find("input[name='specialInstructions']").val();
        standard = $("#" + tagMedId).find("input[name='standard']").val();
        //治疗方式 如贴服 煎服之类的
        selectMedUseModType = $("#" + tagMedId).find("input[name='medicineUseModes']").val();
    }
    if (medType == "Western") {
        height = "700px";
        width = "700px";
    } else {
        height = "700px";
        width = "620px";
    }
    layer.open({
        type: 2,
        title: '选择药品',
        area: [height, width],
        scrollbar: false,
        content: '/fragment/medicine/select/' + medicineId + "?type=edit&groupId="
        + groupId + "&txtMedicineQty="
        + txtMedicineQty + "&specialInstructions="
        + decodeURI(specialInstru)
        + "&standard=" + decodeURI(standard)
        + "&selectMedUseModType=" + decodeURI(selectMedUseModType)
    });

}
/**
 *   递归 根据ID 判断是否已经有这个药  如果有 就递加 S
 * @param medId
 * @returns {*}
 */
function recursionMed(medId) {
    $("#divWesternItems span").each(function () {
        if ($(this).attr("id") == ("medicineTag_" + medId)) {
            medId = medId + "s";
            medId = recursionMed(medId);
        }
    })
    $("#divChineseItems span").each(function () {
        if ($(this).attr("id") == ("medicineTag_" + medId)) {
            medId = medId + "s";
            medId = recursionMed(medId);
        }
    })
    return medId;
}
var priceMap = new Map();
function fn_SelectMedicine(data, type, putTpye) {
    //给data一个顺序属性
    if (data.multiplexTag == undefined || data.multiplexTag == "undefined" || data.multiplexTag == "") {
        data.multiplexTag = "";
    }
    data.medicineId = data.medicineId + data.multiplexTag;
    if (putTpye != "arr") {
        if (data.openType == "edit") {
            delOldMed(data);
            data.openType = "add";

        }
        /*点击使用该药方时，data.openType==undfined,因为从后台过来的data中就没有这个属性*/
        if (data.openType == undefined) {
            delOldMed(data);
            selectMedUseModType = data.useMode;
            lastSelectXwOrFenBao = data.groupIndex;
            medObjArr.push(data);
            clearAddNewItem();
            return
        }
        selectMedUseModType = data.useMode;
        lastSelectXwOrFenBao = data.groupIndex;
        medObjArr.push(data);
        clearAddNewItem();
        return;
    }
    if (data.openType == "add") {
        var tmpTagsOne = "medicineTag_" + data.medicineId;

        if ($("#divWesternItems span").size() > 0) {
            $("#divWesternItems span").each(function () {
                if ($(this).attr("id") == tmpTagsOne) {
                    data.medicineId = recursionMed(data.medicineId);
                }
            });
        }
        if ($("#divChineseItems span").size() > 0) {
            $("#divChineseItems span").each(function () {
                if ($(this).attr("id") == tmpTagsOne) {
                    data.medicineId = recursionMed(data.medicineId);
                }
            });
        }
    }

    var westernArr = $.map($('#divWesternItems span input[name="medicineIds"],#divChineseItems li input[name="medicineIds"]'), function (n, i) {
        return $(n).val();
    });
    var chineseArr = $.map($('#divChineseItems span input[name="medicineIds"],#divChineseItems li input[name="medicineIds"]'), function (n, i) {
        return $(n).val();
    });
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

    data.tagNum++;

    var huanHang = "";
    var head1 = '';
    var wei1 = '';


    if (type == 'Western') {
        huanHang = 'style="display: block;"';
        head1 = '<p1 style="min-width: 400px">';
        wei1 = '</p1>'
    } else if (type == 'Chinese') {
        huanHang = 'style="min-width: 300px;"';
    }
    if (type === 'Chinese') {
        data.medType = "Chinese";
        if ($("#chineseQty").val() == "0" || $("#chineseQty").val() == "") {
            $("#chineseQty").val("1");
        }
    } else {
        data.medType = "Western";
    }

    var tmp = "" + data.medicineId;
    var exist = ($.inArray(tmp, westernArr) != -1 || $.inArray(tmp, chineseArr) != -1);
    var tag = "medicineTag_" + data.medicineId;
    //
    var ele =
        '<span class="tag ' + data.tagNum + '" ' + huanHang + ' id="{0}">{1}&nbsp;{2}{3}x{4}份'.format(tag, data.medicineName, data.qty, data.unitLabel, data.copies) +
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
        '<a href="javascript:" onclick="fn_ShowEditMedicine(\'{0}\',\'{1}\',\'{2}\',\'{3}\')"><i class="fa fa-cog"></i></a> '.format(data.medicineId, data.type, tag, data.medType) +
        '<a href="javascript:" onclick="fn_RemoveElement($(this).parent())"><i class="fa fa-times"></i></a> ' +
        '</span>';

    // 将 药品信息 和 右边的 处方签 进行同步
    doPullMedToPaper(tag, data, exist);
    var prices = 0;

    priceMap.remove(tag);
    priceMap.put(tag, data);

    //计算中医理疗费用
    getTherapyPrice();

    $.each(priceMap.values(), function (index, val) {
        //煎服计算
        if (val.useMode == "煎服") {
            val.qtyTmp = val.qty * $("#chineseQty").val();
            val.price = ((val.qtyTmp * val.copies * (val.unitPrice / val.rate))).toFixed(2);
        } else {
            val.qtyTmp = val.qty;
            //    price = qty * copiesss * (tmpPrice / rate);
            if (val.medType != "therapy" && val.medType != undefined) {
                val.price = (val.qty * val.copies * (val.unitPrice / val.rate)).toFixed(2);
            }

        }
        prices = parseFloat(prices) + parseFloat(val.price);
    });
    //药品收费明细
    $("#cost").val(prices.toFixed(2));

    if (exist) {
        var $tag = $("#" + tag);
        $tag.after(ele);
        $tag.remove();
    } else {

        if (type == 'Western') {
            $('#divWesternItems').append(ele);
        }
        else if (type === 'Chinese') {
            $('#divChineseItems').append(ele);
        }
        else $('#divItems .clearfix').before(ele);
    }
    var hasUsage = data.hasUsage;
    var medIds = (data.medicineId + "").replace(new RegExp("[s]+"), "");
    var id = (data.id + "").replace(new RegExp("[s]+"), "");
    //当data没有id这个属性，会报undefined
    if (id == "undefined") {
        id = medIds;
    }
    if (id && id != "" && id != undefined && id != "undefined") {
        var url = "/medicine/updateUsageFlag/" + id;
        $.postJSON(url, {'usageFlag': hasUsage});
    }


}


function whenChangZhongMedFuSize(obj) {
    $('#fuSize').text($(obj).val())
}
////////////////////////////////////////////////////////////////////////////

/*
 * 坐诊页- 临时患者信息弹框
 * */

function fn_AddPatient() {
    // $(".biao-shi-span").text("临时");
    layer.open({
        type: 2,
        title: '临时患者信息',
        area: ['400px', '580px'],
        scrollbar: false,
        content: '/patient/saveTemprescription'
    });
}


/*
 * 坐诊页- 编辑回访短信弹框
 * */
function fn_CareMes() {
    var index = layer.open({
        type: 2,
        maxmin: false,
        title: '编辑回访短信',
        area: ['450px', '320px'],
        scrollbar: false,
        content: '/patient/editMes?sendMsgInfo=' + $("#sendMsgInfo").val()

    });
}
function setMsgInfo(str) {
    $("#sendMsgInfo").val(str);
}

function getMsgInfo() {
    return $("#sendMsgInfo").val();
}
///////////////////////////////////////////////////////////////

/**
 * 添加检查项目 明细 弹框
 * */
function inspectionItemsDetail() {
    layer.open({
        type: 2,
        title: '检查/检验项目明细',
        area: ['650px', '450px'],
        scrollbar: false,
        content: '/fragment/inspecItemsDetail'
    });
}

/**
 * 修改项目价格
 * */
function editItemsDetail(id) {
    layer.open({
        type: 2,
        title: '修改项目价格',
        area: ['300px', '200px'],
        scrollbar: false,
        content: '/fragment/editItemsDetail?id=' + id
    });
}
/**
 * 删除检查项目
 * */
function deleteItemTr(id) {
    layer.confirm("是否删除此项目！(可以在新增项目重新添加)", function () {
        $.postJSON("/fragment/delDoctorJiyanChaOrJianYan", {
            id: id
        }, function (ret) {
            if (ret.success) {
                layer.msg("删除成功!");
                window.location.reload();
            } else {
                layer.msg("删除失败!");
            }
        });
    });
}


/**
 * 添加项目
 * */
function saveItemsDetail() {
    layer.open({
        type: 2,
        title: '添加项目',
        area: ['550px', '400px'],
        scrollbar: true,
        content: '/fragment/saveItemsDetail'
    });
}


/**
 * 附加费用 明细 弹框
 * */
function chargesDetails() {
    layer.open({
        type: 2,
        title: '附加费用',
        area: ['650px', '380px'],
        scrollbar: false,
        content: '/fragment/chargesDetails'
    });
}


/**
 * 添加项目
 * */
function addItems() {
    layer.open({
        type: 2,
        title: '添加项目',
        area: ['350px', '200px'],
        scrollbar: false,
        content: '/fragment/addItems'
    });
}

/**
 * 编辑项目
 * */
function editItems(eve) {
    layer.open({
        type: 2,
        title: '编辑项目',
        area: ['350px', '200px'],
        scrollbar: false,
        content: '/fragment/editPre?id=' + eve
    });
}

/**
 * 选择理疗
 * */
function selectPhy(eve, therapyId) {
    if (therapyId == undefined || therapyId == "undefined") {
        layer.msg("therapyId为undefined");
    }
    var url = "/fragment/therapy/selectPhy?therapyId={0}".format(therapyId);
    layer.open({
        type: 2,
        title: '选择理疗',
        area: ['500px', '320px'],
        scrollbar: false,
        content: url
    });
}
//在处方签上选择理疗
function paperTherapy(even, therapyId, useQty, useUnit, therapyCopy) {

    $(even).addClass("edit");
    if (therapyId == undefined || therapyId == "undefined") {
        layer.msg("therapyId为undefined");
    }
    var url = "/fragment/therapy/selectPhy?therapyId={0}&useQty={1}&useUnit={2}&therapyCopy={3}".format(therapyId, useQty, useUnit, therapyCopy);

    layer.open({
        type: 2,
        title: '选择理疗',
        area: ['500px', '320px'],
        scrollbar: false,
        content: url,
        cancel: function () {
            $(even).removeClass("edit");
        }
    });
}


/**
 * 添加理疗
 * */
function savetPhy() {
    layer.open({
        type: 2,
        title: '添加理疗',
        area: ['450px', '260px'],
        scrollbar: false,
        content: '/fragment/popTherapy'
    });
}

/**
 * 修改理疗
 * */
function editPhy(eve) {
    var therapyId = $("#therapyId").val();
    layer.open({
        type: 2,
        title: '修改理疗',
        area: ['450px', '260px'],
        scrollbar: false,
        content: '/fragment/therapy/updateTherapy?therapyId={0}'.format(therapyId)
    });
}

/**
 * 删除就诊单上面的附加费用
 * */
function fn_delContent(obj) {

    var thisClassDiv = $(obj).parent();
    console.info($($(thisClassDiv).parent().find("div")).length);
    if ($(thisClassDiv).parent().find("div").length == 1) {
        $(thisClassDiv).parent().remove();
    } else {
        $(thisClassDiv).remove();
    }

}

/**
 * 删除就诊单上面的附加费用
 * */
function fn_delTherapy(obj) {

    var therapyId = $(obj).parent().find("input[name='therapyId']").val();
    getTherapyPrice('delete', therapyId);
    var thisClassDiv = $(obj).parent();

    $(thisClassDiv).remove();
    if ($("#therapyList").html().trim() == "") {
        $(".phy-box").children("h4").remove();
    }

}

/**
 * 修改附加费用项目
 * @param id
 */
function editFujiaItems(id) {
    var div = $("div[ids='" + id + "']");
    var price = $(div).find("input[name='fuJiaPrices']").val();
    var name = $(div).find("input[name='fuJiaNames']").val();

    layer.open({
        type: 2,
        title: '编辑附加项目',
        area: ['420px', '250px'],
        scrollbar: false,
        content: '/fragment/editFujiaItems?id={0}&price={1}&name={2}'.format(id, price, name),
    });
}

/**
 * 编辑项目
 * */

function fn_editItems() {
    var checkedObj = $("tbody tr input[type='radio']:checked");
    if (checkedObj.length <= 0) {
        layer.msg("请选择项目");
        return;
    }
    var id = $("tbody tr input[type='radio']:checked").val();


    layer.open({
        type: 2,
        title: '编辑项目',
        area: ['460px', '375px'],
        scrollbar: false,
        content: '/fragment/editPhyItems?id=' + id,
    });
}


/////////////////////////////////////////////////////////////


/*开始坐诊（添加药品弹出框）*/
function fn_AddMedicine(medType, medicineTypes, iaiType) {
    //弹出框的宽高
    var height = "";
    var width = "";
    if (iaiType == undefined) {
        iaiType = "";
    }
    if (medType == "Western") {
        height = "700px";
        width = "580px";
    } else {
        height = "700px";
        width = "580px";
    }
    //medicineTypes = "";
    layer.open({
        type: 2,
        title: ['添加药品 - ' + medicineTypes, 'font-weight: bold'],
        area: [height, width],
        scrollbar: false,
        content: '/adv/medicine/add?type=' + medType + "&iaiType=" + iaiType,
        end: function () {
            if (medType == "Western") {
                fn_LoadWesternMedicinePage(0);
                if (addMedId != "" && addMedId != undefined)
                    fn_ShowSelectMedicine(addMedId);
            } else {
                fn_LoadChineseMedicinePage(0);
                if (addMedId != "" && addMedId != undefined)
                    fn_ShowSelectMedicine(addMedId);
            }
            addMedId = "";

        }
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

function fn_ShowSelectRp(medicineType) {
    layer.open({
        type: 2,
        title: '选择药方',
        area: ['888px', '680px'],
        scrollbar: false, maxmin: true,
        content: '/rp/select/' + medicineType
    });
}

function fn_ShowMyMedicine(medicineType) {
    layer.open({
        type: 2,
        title: '我的药品',
        area: ['888px', '680px'],
        scrollbar: false, maxmin: true,
        content: '/fragment/myMedicine/' + medicineType
    });
}

function fn_ReferEmrWesternItems(emrId) {
    $.getJSON(String.format("/emr/{0}/westernItems", emrId), function (data) {
        $.each(data, function (i, n) {
            n.openType = "add";
            if (n.groupIndex == undefined || n.groupIndex == "null") {
                n.groupIndex = "10";
            }
            n.tagNum = 0;
            n.medicineId = n.medicineId + n.multiplexTag;
            fn_SelectMedicine(n, n.medicineType);
        });
        layer.msg("药品成功添加");
    });
}

function fn_ReferEmrChineseItems(emrId) {
    $.getJSON(String.format("/emr/{0}/chineseItems", emrId), function (data) {
        $.each(data, function (i, n) {
            if (n.groupIndex == undefined) {
                n.groupIndex = n.groupIndex;
            }
            n.tagNum = 0;
            fn_SelectMedicine(n, n.medicineType);
        });
        layer.msg("药品成功添加");
    });
}

function fn_LoadWesternMedicinePage(page, name) {
    var diagonsisName = $("#divDiagnosis span input[name='diagnosisResults']").val()
    if (page == undefined) page = 0;
    if (name == undefined) name = "";
    if (diagonsisName == undefined) diagonsisName = "";
    name = name.replace(/[ ]/g, "");
    var url = "/fragment/medicines/Western?page={0}&name={1}&diagonsisName={2}".format(page, name, diagonsisName);

    $('#divWesternMedicines').load(url);


}

//中医理疗初始化标签
function fn_LoadTherapyTag(page, helpCode) {
    /*var diagonsisName = $("#divDiagnosis span input[name='diagnosisResults']").val()*/
    if (page == undefined) page = 0;
    if (helpCode == undefined) helpCode = "";
    /*if (diagonsisName == undefined) diagonsisName = "";*/
    helpCode = helpCode.replace(/[ ]/g, "");

    var url = "/fragment/therapy/therapyList?page={0}&helpCode={1}".format(page, decodeURI(helpCode));
    $('#divTherapy').load(url);
}

//初始化中医理疗的处方笺
function fn_LoadTherapyPaper(HTML_JSON) {

    $("#therapyList").append(HTML_JSON);
}


function fn_LoadWesternOtherMedicinePage(page, name) {
    if (page == undefined) page = 0;
    if (name == undefined) name = "";
    name = name.replace(/[ ]/g, "");
    var url = "/fragment/otherMedicines/Western?page={0}&name={1}".format(page, name);

    $('#divWesternMedicines').load(url);

}

function fn_LoadChineseMedicinePage(page, name) {
    if (page == undefined) page = 0;
    if (name == undefined) name = "";
    name = name.replace(/[ ]/g, "");
    var diagnosisName = $("#divDiagnosis span input[name='diagnosisResults']").val()
    diagnosisName = (diagnosisName == undefined ? "" : diagnosisName);
    var url = "/fragment/medicines/Chinese?page={0}&name={1}&diagnosisName={2}".format(page, name, diagnosisName);

    $('#divChineseMedicines').load(url);


}

function fn_LoadChineseOtherMedicinePage(page, name) {
    if (page == undefined) page = 0;
    if (name == undefined) name = "";
    name = name.replace(/[ ]/g, "");
    var url = "/fragment/otherMedicines/Chinese?page={0}&name={1}".format(page, name);

    $('#divChineseMedicines').load(url);

}


function fn_LoadPatientOverview(patientUid) {
    $(".dia-logon").hide();
    $("#btnSubmit").attr("disabled", false);
    $('#divPatientOverview').load("/fragment/patient/{0}/overview?refer=true".format(patientUid));
}

function fn_LoadPatientOverviewWx(patientUid, wxPatienId) {
    $(".dia-logon").hide();
    $("#btnSubmit").attr("disabled", false);
    $('#divPatientOverview').load("/fragment/patient/{0}/overview?refer=true&wxPatienId={1}".format(patientUid, wxPatienId));
}

function fn_loadRegistration() {
    $('#divRegistration').load("/fragment/regist");
}
function fn_LoadPatient(uid) {
    $('#divPatientInfo').load("/fragment/patient/" + uid);
}

$(function () {
    $('#blood-press').highcharts({
        chart: {
            type: 'line'
        },
        title: {
            text: '血压报告'
        },
        xAxis: {
            categories: ['10.7-15:20', '10.8-15:21', '10.9-16:20', '10.10-15:20', '10.11-15:21', '10.12-16:20', '10.13-15:20', '10.14-15:21', '10.15-16:20', '10.16-15:20', '10.17-15:21', '10.18-16:20', '10.19-15:20', '10.20-15:21', '10.21-16:20']
        },
        yAxis: {
            title: {
                text: '血压值(mmHg)'
            }
        },
        tooltip: {
            enabled: false,
            formatter: function () {
                return '<b>' + this.series.name + '</b><br>' + this.x + ': ' + this.y + '°C';
            }
        },
        plotOptions: {
            line: {
                dataLabels: {
                    enabled: true
                },
                enableMouseTracking: false
            }
        },
        series: [{
            name: '高压',
            data: [110, 120, 115, 116, 115, 114, 109, 122, 125, 113, 122, 110, 108, 116]
        }, {
            name: '低压',
            data: [80, 79, 85, 89, 80, 81, 82, 83, 85, 86, 80, 81, 85, 85]
        }]
    });
    $('#hr-report').highcharts({
        chart: {
            type: 'line'
        },
        title: {
            text: '心率报告'
        },
        xAxis: {
            categories: ['10.7-15:20', '10.8-15:21', '10.9-16:20', '10.10-15:20', '10.11-15:21', '10.12-16:20', '10.13-15:20', '10.14-15:21', '10.15-16:20', '10.16-15:20', '10.17-15:21', '10.18-16:20', '10.19-15:20', '10.20-15:21', '10.21-16:20']
        },
        yAxis: {
            title: {
                text: '心率'
            }
        },
        tooltip: {
            enabled: false,
            formatter: function () {
                return '<b>' + this.series.name + '</b><br>' + this.x + ': ' + this.y + '°C';
            }
        },
        plotOptions: {
            line: {
                dataLabels: {
                    enabled: true
                },
                enableMouseTracking: false
            }
        },
        series: [{
            name: '心率',
            data: [110, 120, 115, 116, 115, 114, 109, 122, 125, 113, 122, 110, 108, 116]
        }]
    });

    $('#glucose-report').highcharts({
        chart: {
            type: 'line'
        },
        title: {
            text: '血糖报告'
        },
        xAxis: {
            categories: ['10.7-15:20', '10.8-15:21', '10.9-16:20', '10.10-15:20', '10.11-15:21', '10.12-16:20', '10.13-15:20', '10.14-15:21', '10.15-16:20', '10.16-15:20', '10.17-15:21', '10.18-16:20', '10.19-15:20', '10.20-15:21', '10.21-16:20']
        },
        yAxis: {
            title: {
                text: '血糖(mg/ml)'
            }
        },
        tooltip: {
            enabled: false,
            formatter: function () {
                return '<b>' + this.series.name + '</b><br>' + this.x + ': ' + this.y + '°C';
            }
        },
        plotOptions: {
            line: {
                dataLabels: {
                    enabled: true
                },
                enableMouseTracking: false
            }
        },
        series: [{
            name: '数值',
            data: [79, 78, 85, 82, 86, 78, 76, 82, 73, 84, 88, 86, 83, 82]
        }]
    });
    $('#admiss-datas').highcharts({
        chart: {
            type: 'column',
            margin: [50, 50, 100, 80]
        },
        title: {
            text: false
        },
        xAxis: {
            categories: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'

            ],
            labels: {
                rotation: 0,
                align: 'center',
                style: {
                    fontSize: '16px',
                    fontFamily: 'Verdana, sans-serif'
                }
            }
        },
        yAxis: {
            min: 0,
            title: {
                text: '单位 (人)',
                style: {
                    fontSize: '16px'
                }
            }

        },
        legend: {
            enabled: false
        },
        tooltip: {
            pointFormat: '共有: <b>{point.y:.1f} 人</b>',
        },
        series: [{
            name: 'Population',
            data: [600, 800, 850, 650, 900, 900, 700, 780, 650, 890, 650, 800],
            dataLabels: {
                enabled: true,
                rotation: 0,
                color: '#666',
                align: 'center',
                x: 0,
                y: 0,
                style: {
                    fontSize: '16px',
                    fontFamily: 'Verdana, sans-serif',
                    textShadow: '0 0 0px black'
                }
            }
        }]
    });
    $('#illness-data').highcharts({
        chart: {
            type: 'column',
            margin: [50, 50, 100, 80]
        },
        title: {
            text: false
        },
        xAxis: {
            categories: ['细菌性感冒', '扁桃体发炎', '肠炎', '支气管炎', '胃溃疡', '伤口发炎', '病毒性感冒', '鼻炎', '哮喘', '肺炎'],
            labels: {
                rotation: -45,
                align: 'right',
                style: {
                    fontSize: '16px',
                    fontFamily: 'Verdana, sans-serif'
                }
            }
        },
        yAxis: {
            min: 0,
            title: {
                text: '单位 (人)',
                style: {
                    fontSize: '18px'
                }
            }
        },
        legend: {
            enabled: false
        },
        tooltip: {
            pointFormat: '共有: <b>{point.y:.1f} 人</b>',
        },
        series: [{
            name: 'Population',
            data: [600, 800, 850, 650, 900, 900, 700, 780, 650, 890],
            dataLabels: {
                enabled: true,
                rotation: 0,
                color: '#666',
                align: 'center',
                x: 0,
                y: 0,
                style: {
                    fontSize: '16px',
                    fontFamily: 'Verdana, sans-serif',
                    textShadow: '0 0 0px black'
                }
            }
        }]
    });
    $('#western-datas').highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text: false
        },
        subtitle: {
            text: false
        },
        xAxis: {
            categories: ['康泰克（盒）', '诺氟沙星（盒）', '病毒灵（盒）', '皮炎平（支）', '速效伤风胶囊（盒）', '咳必清（瓶）', '阿司匹林（盒）', '板蓝根冲剂（盒）', '复方颠茄片（盒）', '复合维生素B(瓶）'],
            labels: {
                rotation: -45,
                align: 'right',
                style: {
                    fontSize: '14px',
                    fontFamily: 'Verdana, sans-serif'
                }
            }
        },
        yAxis: {
            min: 0,
            title: {
                text: '一月份',
                style: {
                    fontSize: '18px'
                }
            }
        },

        plotOptions: {
            column: {
                pointPadding: 0.2,
                borderWidth: 0
            }
        },
        series: [{
            name: '当月累计',
            data: [120, 110, 106, 129, 144, 176, 135, 148, 216, 194]

        }, {
            name: '上月累计',
            data: [83, 78, 98, 93, 106, 84, 105, 104, 91, 83]

        }]
    });
    $('#chinese-datas').highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text: false
        },
        subtitle: {
            text: false
        },
        xAxis: {
            categories: ['当归', '板蓝根', '菊花', '柴胡', '薄荷', '车前子', '公英', '黄岑', '生地', '甘草'],
            labels: {
                style: {
                    fontSize: '16px'
                }
            }

        },
        yAxis: {
            min: 0,
            title: {
                text: '单位(克)',
                style: {
                    fontSize: '18px'
                }
            }
        },

        plotOptions: {
            column: {
                pointPadding: 0.2,
                borderWidth: 0
            }
        },
        series: [{
            name: '当月累计',
            data: [2800, 2980, 2550, 2650, 2450, 2300, 2410, 2450, 2650, 2750]

        }, {
            name: '上月累计',
            data: [2300, 2560, 2480, 2950, 2130, 2450, 2780, 2150, 2650, 2850]

        }]
    });


    $('.input-daterange input').each(function () {
        $(this).datepicker("clearDates");
    });
});

/**
 * 监视体检检查  输入框改变 即同步右边的处方签
 */
var boluYao = ""//血压 值
function pullItToPager(obj) {
    var str = "";
    var num = 0.0;

    $(obj).siblings().each(function () {
        num = parseInt(num) + 1;
        if (num == 2) {
            str += $(obj).val();
        } else {
            str += $(this).text();
        }
    })

    if (str.indexOf("血压") > -1) {
        boluYao = str;
    }

    if (str.indexOf("mmHg") > -1) {
        str = boluYao + str;
        if ($('#vitalSign0').val() == "")
            return;
    }

    if (str.indexOf("血压") > -1) {
        if ($("#vitalSign1").val() != "" && str.indexOf("mmHg") < 0) {
            str = str + "/" + $("#vitalSign1").val() + "mmHg;";
            $("#talkSmtz span").each(function () {

                if ($(this).text().indexOf("血压") > -1) {
                    $(this).remove();
                }
            })


        }
    } else {
        str += ";";
    }


    if ($(obj).val() == "") {
        var needDeleteText = $(obj).prev().prev().text();

        if (needDeleteText == "/") {
            needDeleteText = "血压"
        }

        $("#talkSmtz span").each(function () {
            if ($(this).text().indexOf(needDeleteText) > -1) {
                $(this).remove();
            }
        })

    } else {
        if (str.indexOf("血压") > -1 && str.indexOf("mmHg") < 0) {
            return;
        }

        if (str.indexOf("血压") < 0 && str.indexOf("mmHg") > -1) {
            if ($("#vitalSign0").val() != "") {
                str = "血压" + $("#vitalSign0").val() + str;
            } else {
                return;
            }

        }


        var needDeleteText = $(obj).prev().prev().text();
        if (needDeleteText == "/") {
            needDeleteText = "血压"
        }
        $("#talkSmtz span").each(function () {
            if ($(this).text().indexOf(needDeleteText) > -1) {
                $(this).remove();
            }
        })

        var enety = '<span class="tag"  >' + str + '</span>';
        $("#talkSmtz").append(enety);
    }


}


$(document).ready(function () {
    $("input[type='text']").each(
        function () {
            $(this).keypress(function (e) {
                var key = window.event ? e.keyCode : e.which;
                if (key.toString() == "13") {
                    return false;
                }
            });
        });
})


//屏蔽 回车 提交
function doBack(obj) {
    $(obj).keypress(function (e) {
        var key = window.event ? e.keyCode : e.which;
        if (key.toString() == "13") {
            return false;
        }
    });


}

function infoSubmit() {
    var day = $('input:radio[name="days"]:checked').val();
    if (day != undefined) {
        if (day <= 3) {
            $("#comBackDays").val(day);
        } else if (day == "4") {
            $("#comBackDays").val($("#thoerDays").val());
        }
    }
    $("#comBackTimes").val($('input:radio[name="times"]:checked').val());


    $("#shade").text("药方保存中,请稍后。。。。。。");
    $("#shade").show();
    return true;

}

//计算中医理疗的费用
function getTherapyPrice(flag, therapyId) {

    var TherapyValue;

    if (flag == 'delete') {
        TherapyValue = $(".therapy-value");
    } else {
        TherapyValue = parent.$(".therapy-value");
    }


    TherapyValue.each(function (obj) {
        var data = {};
        var tmpPrice = $(this).children(".therapyPrice").val();
        var copy = $(this).children("input[name='therapyCopy']").val();
        var price = tmpPrice * copy;

        data['id'] = $(this).children("input[name='therapyId']").val();
        data['name'] = $(this).children("input[name='therapyName']").val();
        data['price'] = price;
        data['medType'] = "therapy";

        data['qty'] = $(this).children("input[name='useQty']").val();
        data['copies'] = $(this).children("input[name='therapyCopy']").val();

        data['unitLabel'] = $(this).children("input[name='useUnit']").val();
        data['unitPrice'] = $(this).children("input[name='therapyPrice']").val();
        data['tjUnit'] = $(this).children("input[name='useUnit']").val();


        parent.priceMap.remove("therapy_" + data.id);
        parent.priceMap.put("therapy_" + data.id, data);
        if (therapyId != undefined || therapyId != '') {
            parent.priceMap.remove("therapy_" + therapyId);
        }

        var prices = 0;
        $.each(parent.priceMap.values(), function (index, val) {
            prices = parseFloat(prices) + parseFloat(val.price);
        });
        parent.$("#cost").val(prices.toFixed(2));

    });


}


var groupNameArray = [
    " ",
    "分组一",
    "分组二",
    "分组三",
    "分组四",
    "分组五",
    "分组六",
    "分组七",
    "分组八",
    "分组九",
    " ",
];
var groupBackNameArray = [
    " ",
    "分组一",
    "分组二",
    "分组三",
    "分组四",
    "分组五",
    "分组六",
    "分组七",
    "分组八",
    "分组九",
    " ",
];


var pakegNameArray = [
    "不分包",
    "分1包",
    "分2包",
    "分3包",
    "分4包",
    "分5包",
    "分6包",
    "分7包",
    "分8包",
    "分9包",
    "分10包",
    "分11包",
    "分12包",
    "分13包",
    "分14包",
    "分15包",
    "分16包",
    "分17包",
    "分18包",
    "分19包",
    "分20包",
    "分21包",
    "分22包",
    "分23包",
    "分24包",
    "分25包",
    "分26包",
    "分27包",
    "分28包",
    "分29包",
    "分30包",

];





