var SYMPTOM_TAGS_ID = '#divSymptomTags';
var DIAGNOSIS_TAGS_ID = '#divDiagnosisTags';
var DEPARTMENT_TABS_ID = '#tabsDepartment';
var WESTERN_MEDICINES_ID = '#divWesternMedicines';
var CHINESE_MEDICINES_ID = '#divChineseMedicines';

var sss;
function fn_RemoveElement(ele) {
    sss = ele;
    var indexWz = $("#divSymptom span").index($(ele));
    if (indexWz > -1) {
        $('#talkcontentone span').eq(indexWz).remove();
    }


    //$('#talkcontentone span').each(function () {
    //    //alert($(this).text())
    //    if ($(ele).text() == $(this).text()) {
    //        $(this).remove()
    //    }
    //})

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

    deletMedForPaper(findId);


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
    //var ele = '<span class="tag" onclick="fn_RemoveElement(this)">' +
    //    '<input type="hidden" name="mainSuits" value="{0}"/>{1}</span>'.format(tag, tag);
    //$('#divSymptom').append(ele);
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
    var ele = '<span class="tag" onclick="fn_RemoveElement(this)">' +
        '<input type="hidden" name="diagnosisTagIds" value="{0}"/>'.format(tagId) +
        '<input type="hidden" name="diagnosisResults" value="{0}"/>{1}</span>'.format(tag, tag);

    $('#divDiagnosis').append(ele);

    $('#talkChuBuZd').append('<span class="tag" onclick="fn_RemoveElement(this)">' + tag + '</span>');
    fn_LoadSuggest(tag);
    fn_LoadRP(tag);
}

function fn_LoadSuggest(tag, tagId) {
    var arr = $.map($('#divSuggest div input[name="suggestContents"]'), function (n, i) {
        return $(n).val();
    });
    var url = '/suggest?name={0}'.format(tag);
    $.getJSON(url, function (res) {
        if (res && res.success) {
            $.each(res.data, function (i, n) {
                if ($.inArray(n.content, arr) == -1) {
                    var ele = '<div style="font-size: 16px">{0}'.format(n.content) +
                        '<a href="javascript:" onclick="fn_RemoveElement($(this).parent())"><i class="fa fa-times"></i></a>' +
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
                //fn_LoadDiagnosisTag(0);
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
    //var ele = '<span class="tag" onclick="fn_RemoveElement(this)">' +
    //    '<input type="hidden" name="diagnosisResults" value="{0}"/>{1}</span>'.format(tag, tag);
    //$('#divDiagnosis').append(ele);
}
function fn_ShowSelectMedicine(medicineId) {
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
        layer.open({
            type: 2,
            title: '选择药品',
            area: ['700px', '700px'],
            scrollbar: false,
            content: '/fragment/medicine/select/' + medicineId + "?type=add&selectMedUseModType=" + selectMedUseModType + "&lastSelectXwOrFenBao=" + lastSelectXwOrFenBao
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

function fn_ShowEditMedicine(medicineId, types, tagMedId,medType) {

    var groupId = 10;
    var txtMedicineQty = "";
    //特殊药品说明
    var specialInstru="";
    //弹出框的宽高
    var height="";
    var width="";
    var standard="";
    if (tagMedId != null && tagMedId != undefined && tagMedId != "") {
        groupId = $("#" + tagMedId).find("input[name='medicineGroupId']").val();
        txtMedicineQty = $("#" + tagMedId).find("input[name='medicineQty']").val();
        specialInstru=$("#" + tagMedId).find("input[name='specialInstructions']").val();
        standard=$("#" + tagMedId).find("input[name='standard']").val();
    }
    if(medType=="Western"){
        height="700px";
        width="700px";
    }else{
        height="700px";
        width="620px";
    }
    layer.open({
        type: 2,
        title: '选择药品',
        area: [height, width],
        scrollbar: false,
        content: '/fragment/medicine/select/' + medicineId + "?type=edit&groupId=" + groupId + "&txtMedicineQty=" + txtMedicineQty+"&specialInstructions="+decodeURI(specialInstru)+"&standard="+decodeURI(standard)
    });

}

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

var selectMedUseModType = "";
var lastSelectXwOrFenBao = "";
function fn_SelectMedicine(data, type) {
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
    if (data.groupId === undefined)data.groupId = data.groupIndex;
    if (data.groupId === undefined)data.groupId = "0";
    if (data.openType === undefined)data.openType = "edit";
    if (data.multiplexTag === undefined)data.multiplexTag = "";
    if (data.multiplexTag === null)data.multiplexTag = "";
    //药品特殊说明
    if (data.specialInstructions === undefined)data.specialInstructions = "";
    if (data.specialInstructions === null)data.specialInstructions = "";
    //药品规格
    if (data.standard === undefined)data.standard = "";
    if (data.standard === null)data.standard = "";
    var huanHang = "";
    var head1 = '';
    var wei1 = '';
    selectMedUseModType = data.useMode;
    if (data.groupId != 0)
        lastSelectXwOrFenBao = data.groupId;
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
    var ele = '<span class="tag" ' + huanHang + ' id="{0}">{1}&nbsp;{2}{3}x{4}#'.format(tag, data.medicineName, data.qty, data.unitLabel, data.copies) +
        '<input type="hidden" name="medicineIds" value="' + data.medicineId + '" />' +
        '<input type="hidden" name="companyIds" value="' + data.companyId + '" />' +
        '<input type="hidden" name="medicineQty" value="' + data.qty + '" />' +
        '<input type="hidden" name="medicineRate" value="' + data.rate + '" />' +
        '<input type="hidden" name="medicineUnit" value="' + data.unit + '" /> ' +
        '<input type="hidden" name="medicineCopies" value="' + data.copies + '" /> ' +
        '<input type="hidden" name="medicineUseModes" value="' + data.useMode + '" /> ' +
        '<input type="hidden" name="medicineHasUsages" value="' + data.hasUsage + '" /> ' +
        '<input type="hidden" name="medicineUseTimes" value="' + data.useTimes + '" /> ' +
        '<input type="hidden" name="medicineUseUsingTime" value="' + data.usingTime + '" /> ' +
        '<input type="hidden" name="medicineUseQty" value="' + data.useQty + '" /> ' +
        '<input type="hidden" name="medicineUseUnit" value="' + data.useUnit + '" /> ' +
        '<input type="hidden" name="medicineGroupId" value="' + data.groupId + '" /> ' +
        '<input type="hidden" name="specialInstructions" value="' + data.specialInstructions + '" /> ' +
        '<input type="hidden" name="standard" value="' + data.standard + '" /> ' +
        '<a href="javascript:" onclick="fn_ShowEditMedicine(\'{0}\',\'{1}\',\'{2}\',\'{3}\')"><i class="fa fa-cog"></i></a> '.format(data.medicineId, data.type, tag,data.medType) +
        '<a href="javascript:" onclick="fn_RemoveElement($(this).parent())"><i class="fa fa-times"></i></a> ' +
        '</span>';

    doPullMedToPaper(tag, data.medicineName, data.qty, data.unitLabel, data.copies, data, exist, data.useTimes, data.usingTime, data.hasUsage, data.useQty, data.useUnit,data.specialInstructions,data.standard);

    if (exist) {
        var $tag = $("#" + tag);
        $tag.after(ele);
        $tag.remove();
    } else {
        if (type === 'Western') $('#divWesternItems').append(ele);
        else if (type === 'Chinese') {
            $('#divChineseItems').append(ele);
        }
        else $('#divItems .clearfix').before(ele);
    }
    var hasUsage = data.hasUsage;
    var medIds = (data.medicineId + "").replace(new RegExp("[s]+"), "");
    var url = "/medicine/updateUsageFlag/" + data.id;
    $.postJSON(url, {'usageFlag': hasUsage});

}


/**
 *
 * @param tag    span 的ID
 * @param medicineName     药品名字
 * @param qty              数量
 * @param unitLabel        单位
 * @param copies           分数
 * @param data
 * @param exist            是否已经存在
 */
function doPullMedToPaper(tag, medicineName, qty, unitLabel, copies, data, exist, useTimes, usingTime, hasUsage, useQty, useUnit,specialInstructions,standard) {
    if (parseFloat(copies) > 1) {
        var copiesTmp = (copies + "").split(".");
        if (copiesTmp.length > 1) {
            if (copiesTmp[1] == 0) {
                copies = copiesTmp[0];
            }
        }
    }

    if (data.useMode == "研末") {
        groupNameArray = pakegNameArray;
    } else if ($.inArray(data.useMode, xwArray) >= 0) {
        groupNameArray = acuPointsArrs;
    } else {
        groupNameArray = groupBackNameArray;
    }
    var span = '<span class="tag" id="{0}">{1}&nbsp{6}{2}{3}x{4}#&nbsp({5})'.format(tag, data.medicineName, data.qty, data.unitLabel, data.copies,data.specialInstructions,data.standard) + '</span>';
    //服用方式  如 口服  取值   data.useMode
    if (useQty != "") {
        useQty = " 每次 " + useQty;
    }
    if (!hasUsage) {
        useTimes = "";
        usingTime = "";
        useUnit = "";
        useQty = ""
    }

    //是否新建服用类型Div 比如 口服 滴注 嚼服
    if (isNeddCreateNewMedDiv(data.useMode)) {
        var strs = createAllTypeDiv(data.useMode, "1", medicineName, qty, unitLabel, copies, useTimes, usingTime, tag, useQty, useUnit, data.groupId, data.medType,data.specialInstructions,data.standard);
        deletMedForPaper("medicineTag_" + data.medicineId)
        $("#medContext").append(strs)
    } else {
        var oldTypeName = isThisMedHaveInAnerType("medicineTag_" + data.medicineId, true, data.useMode);

        var noode = thisTypeHaveHowMnyMed(data.useMode);
        if (exist) {
            var objs = $('div [ids="' + tag + '"]');
            noode.size = $(objs.find('div')[0]).find('span').text().replace("、", "");
            noode.chinedJqObj = objs;
        }
        var tmpMedDiv = createMedDiv(noode.size, medicineName, qty, unitLabel, copies, useTimes, usingTime, tag, useQty, useUnit, data.useMode, data.medType,data.specialInstructions,data.standard);
        if (oldTypeName == data.useMode) {
            var groupName = $(noode.chinedJqObj).parent().prev().text().replace("：", "");
            if (!exist) {
                if (groupName == groupNameArray[data.groupId]) {
                    $(noode.chinedJqObj).after(tmpMedDiv);
                } else {

                    var ppTop = '<div class="beizhu">';
                    var ppFloer = '</div>';
                    var fenHao = "：";
                    var pTop = '<div style="float:left; width:85%;">';
                    if (data.groupId == 10 || data.groupId == 0) {
                        fenHao = "";
                        pTop = '<div style="float:left; width:100%;">';
                    }
                    var groupNameDiv = '<div class="groupName" style="width:15%; float:left; font-size:16px; font-weight:bold;">' + groupNameArray[data.groupId] + fenHao + '</div>';

                    var pFloer = "</div>";
                    var endMendWithGroupDiv = ppTop + groupNameDiv + pTop + tmpMedDiv + pFloer + ppFloer;
                    $(noode.chinedJqObj).parent().parent().after(endMendWithGroupDiv)
                }
            } else {
                if (groupName == groupNameArray[data.groupId]) {
                    $(noode.chinedJqObj).replaceWith(tmpMedDiv);
                } else {
                    var beizhuDiv = "";
                    var nowTypeHaveMedSize = 0;
                    $('strong').each(function () {
                        if ($(this).text().replace(":", "") == data.useMode) {
                            beizhuDiv = $(this).parent().next();
                            return;
                        }
                    });
                    var groupName = $(noode.chinedJqObj).parent().prev().text().replace("：", "");
                    var thisGroupNameNextBeiZhu1Div = "";
                    $(beizhuDiv).parent().find("div[class='groupName']").each(function () {
                        if ($(this).text().replace("：", "") == groupNameArray[data.groupId]) {
                            thisGroupNameNextBeiZhu1Div = $(this).next().find("div[class='beizhu1']:last");
                            nowTypeHaveMedSize = $(this).next().find("div[class='beizhu1']").size();
                        }
                    });
                    if ($(noode.chinedJqObj).parent().find("div[class='beizhu1']").size() > 1) {
                        $(noode.chinedJqObj).remove();
                    } else {
                        beizhuDiv = $(beizhuDiv).parent();
                        $(noode.chinedJqObj).parent().parent().remove();
                    }

                    if (thisGroupNameNextBeiZhu1Div == "") {
                        var ppTop = '<div class="beizhu">';
                        var ppFloer = '</div>';
                        var fenHao = "：";
                        var pTop = '<div style="float:left; width:85%;">';
                        if (data.groupId == 10 || data.groupId == 0) {
                            fenHao = "";
                            pTop = '<div style="float:left; width:100%;">';
                        }
                        var groupNameDiv = '<div class="groupName" style="width:15%; float:left; font-size:16px; font-weight:bold;">' + groupNameArray[data.groupId] + fenHao + '</div>';

                        var pFloer = "</div>";
                        tmpMedDiv = createMedDiv(nowTypeHaveMedSize + 1, medicineName, qty, unitLabel, copies, useTimes, usingTime, tag, useQty, useUnit, data.useMode, data.medType,data.specialInstructions,data.standard);
                        var endMendWithGroupDiv = ppTop + groupNameDiv + pTop + tmpMedDiv + pFloer + ppFloer;

                        if ($(beizhuDiv).find("div[class='beizhu1']").size() > 0) {
                            $(beizhuDiv).parent().append(endMendWithGroupDiv)
                        } else {
                            $(beizhuDiv).append(endMendWithGroupDiv)
                        }


                    } else {
                        tmpMedDiv = createMedDiv(nowTypeHaveMedSize + 1, medicineName, qty, unitLabel, copies, useTimes, usingTime, tag, useQty, useUnit, data.useMode, data.medType,data.specialInstructions,data.standard);
                        thisGroupNameNextBeiZhu1Div.after(tmpMedDiv)
                    }


                }


            }
        } else {
            var nowTypeHaveMedSize = 0;
            var beizhuDiv = "";
            $('strong').each(function () {
                if ($(this).text().replace(":", "") == data.useMode) {
                    beizhuDiv = $(this).parent().next();
                    return;
                }
            });
            var groupName = $(noode.chinedJqObj).parent().prev().text().replace("：", "");
            var thisGroupNameNextBeiZhu1Div = "";
            $(beizhuDiv).parent().find("div[class='groupName']").each(function () {
                if ($(this).text().replace("：", "") == groupNameArray[data.groupId]) {
                    thisGroupNameNextBeiZhu1Div = $(this).next().find("div[class='beizhu1']:last");
                    nowTypeHaveMedSize = $(this).next().find("div[class='beizhu1']").size();
                }
            });
            if (thisGroupNameNextBeiZhu1Div == "") {
                var ppTop = '<div class="beizhu">';
                var ppFloer = '</div>';
                var fenHao = "：";
                var pTop = '<div style="float:left; width:85%;">';
                if (data.groupId == 10 || data.groupId == 0) {
                    fenHao = "";
                    pTop = '<div style="float:left; width:100%;">';
                }
                var groupNameDiv = '<div class="groupName" style="width:15%; float:left; font-size:16px; font-weight:bold;">' + groupNameArray[data.groupId] + fenHao + '</div>';

                var pFloer = "</div>";
                tmpMedDiv = createMedDiv(nowTypeHaveMedSize + 1, medicineName, qty, unitLabel, copies, useTimes, usingTime, tag, useQty, useUnit, data.useMode, data.medType,data.specialInstructions,data.standard);
                var endMendWithGroupDiv = ppTop + groupNameDiv + pTop + tmpMedDiv + pFloer + ppFloer;
                if ($(beizhuDiv).nextAll().size() > 0) {
                    if (groupNameArray[data.groupId] == " ") {
                        $(beizhuDiv).siblings("span").after(endMendWithGroupDiv);
                    } else {
                        $(beizhuDiv).nextAll().last().after(endMendWithGroupDiv)
                    }
                } else {
                    if (groupNameArray[data.groupId] == " ") {
                        $(beizhuDiv).parent().find("strong").parent().after(endMendWithGroupDiv)
                    } else {
                        $(beizhuDiv).after(endMendWithGroupDiv)
                    }

                }

            } else {
                tmpMedDiv = createMedDiv(nowTypeHaveMedSize + 1, medicineName, qty, unitLabel, copies, useTimes, usingTime, tag, useQty, useUnit, data.useMode, data.medType,data.specialInstructions,data.standard);
                thisGroupNameNextBeiZhu1Div.after(tmpMedDiv)
            }
        }
    }
}


/**
 *  @param medName 药品名称
 *  @return 返回老分类的名称
 */
function isThisMedHaveInAnerType(medId, needDelete, nowType) {
    var isSave = false;
    var ids = "";
    var oldTypeName = "";
    $('#medContext div [class="beizhu1"]').each(function () {
        if ($(this).attr("ids") == medId) {
            isSave = true;
            oldTypeName = $(this).parent().parent().parent().find("strong").eq(0).text().replace(":", "");
            return;
        }
    });
    if (needDelete && medId != "" && (oldTypeName != nowType)) {
        deletMedForPaper(medId)
    }
    return oldTypeName;
}


function deletMedForPaper(findId) {
    $('#medContext div [class="beizhu1"]').each(function () {
        var nowObjIds = $(this).attr("ids");
        if (nowObjIds == findId) {
            if ($(this).parent().parent().parent().find('div [class="beizhu1"]').size() > 1) {
                var nowObj2 = $('div [ids="' + findId + '"]');
                //判断 当前是第几个
                var nowIden = nowObj2.index() + 1;
                //判断总共有多少个药品
                var childrenSize = nowObj2.parent().children().size();
                if (childrenSize > nowIden) {
                    for (var i = nowIden; i < childrenSize; i++) {
                        //nowObj2.parent().children().eq(i).find('span').eq(0).text(i);
                    }
                }
                //删除 某种服药方式类型下面的单个药品 比如 煎服下面的 巴豆

                var medSize = $(this).parent().find("div[class='beizhu1']").size();
                if (medSize > 1) {
                    $(this).remove();
                } else {
                    $(this).parent().parent().remove();
                }


            } else {
                //当药品全部被删除的时候 也一起删除 服药方式类型
                $(this).parent().parent().parent().remove();
            }
            return true;
        }
    })
}

/**
 *
 * @param typeName
 * @returns {boolean}  根据传递进来点 Typename 判断  是否需要创建 TypeDiv
 */
function isNeddCreateNewMedDiv(typeName) {
    if ($('.content1 strong').size() > 0) {
        var flag = true;
        $('.content1 strong').each(function () {
            var typeOldName = $(this).text().replace(":", "");
            if (typeOldName == typeName) {
                flag = false;
                return false;
            }
        })
        return flag;
    } else {
        return true;
    }
}

/**
 *
 * @param typeName  要查询的类别名字
 * @returns {{size: number, chinedJqObj: null}}   当前分类有的药的数量   最后一个药的JQ对象
 */

function thisTypeHaveHowMnyMed(typeName) {

    var retObj = {size: 0, chinedJqObj: null};
    typeName += ":";
    $('.content1 strong').each(function () {
        //alert(typeName)
        //alert($(this).text()+"======")
        if (typeName == $(this).text()) {
            var thisTypeNameChined = $(this).parent().parent().find('div[class="beizhu1"]');
            var chinedSize = parseInt($(thisTypeNameChined).size()) + 1;
            var chinedJqObj = $(this).parent().parent().find('div[class="beizhu1"]:last');
            retObj.size = chinedSize;
            retObj.chinedJqObj = chinedJqObj;
        }
    });

    return retObj;

    //$($('.content1 strong').parent().parent()).find('div[class="beizhu1"]')
}


/**
 *
 * @param typeName  要创建的 大分类 Div 类型 比如  口服  煎服 等
 * @param num       当前药品的顺序
 * @param medName   药品名称
 * @param qty       数量
 * @param danWei    单位
 * @param size      分数
 * @param pl        频率
 * @param fhf       饭后服  还是饭前
 */
function createAllTypeDiv(typeName, num, medName, qty, danWei, size, pl, fhf, tagId, useQty, useUnit, groupId, medType,specialInstr,standard) {
    var fuSize = "";
    var fen = "#";
    var isJZ = qty + danWei + 'x' + size + fen;
    if (parseFloat(size) <= 0 || parseFloat(size) == 1) {
        isJZ = qty + danWei;
    }

    if (typeName == "煎服") {
        fuSize = '<strong>(</strong><strong id="fuSize">' + $('#chineseQty').val() + '</strong><strong>副)</strong>';
        fen = "";
        //isJZ = "";
        isJZ = qty + danWei + useUnit;
        if (useUnit == danWei) {
            isJZ = qty + danWei;
        }

    }

    var groupText = '';
    var fenHao = "：";
    var groupDivTop = '<div style="float:left; width:85%;">';
    if (groupId == 10 || groupId == 0) {
        fenHao = "";
        groupDivTop = '<div style="float:left; width:100%;">';
    }
    groupText = '<div class="groupName" style="width:15%; float:left; font-size:16px; font-weight:bold;">' + groupNameArray[groupId] + fenHao + '</div>';

    var groupDivfloter = '</div>';
    var sty = "";
    var nedNbsp = "";
    if (medType == "Western") {
        //$(".beizhu1").css("float","none");
        sty = "float:none;"
        nedNbsp = "";
    } else if (medType == undefined) {
        //$(".beizhu1").css("float", "left");
        sty = "float:left;"
        nedNbsp = "";
    } else {
        //$(".beizhu1").css("float", "left");
        sty = "float:left;"
        nedNbsp = "&nbsp;";
    }
    //特殊说明为空
    if(specialInstr==null||specialInstr==""){
        specialInstr="";
    }else{
        specialInstr="("+specialInstr+")";
    }


    var str = '<div class="content1">' +
        '<span><strong>' + typeName + ":" + '</strong>' +
        '' + fuSize +
        '</span>' +
        '<div class="beizhu">' + groupText + groupDivTop +
        '<div class="beizhu1" style="' + sty + '"   ids="' + tagId + '">' +
        '<div class="beizhu2">' +
        '<span>'
            //+ num
        + '</span>' +
        '</div>' +

        '<div>' +
        '<div class="beizhu_name">' +
        '<span>' + medName + '</span>' +
        '<span style="margin-left: 5px"> </span>' +
        '<span>' + standard + '</span>' +
        '<span style="margin-left: 10px">' + isJZ + '</span>' +
        '<span style="margin-left: 10px"> </span>' +
        '<span>' + specialInstr + '</span>' +
        '</div>' +

        '<div>' +
        '<span style="margin-right: 1em;" medType="tmpNbsp">' + pl + useQty + useUnit + nedNbsp + '</span>' +
        '<span >' + fhf + '</span>' +
        '</div>' +
        '</div>' +
        '</div>' + groupDivfloter +

        '</div>'


        + '</div>';
    return str;
}
/**
 *
 * @param num       当前药品的顺序
 * @param medName   药品名称
 * @param qty       数量
 * @param danWei    单位
 * @param size      分数
 * @param pl        频率
 * @param fhf       饭后服  还是饭前
 */
function createMedDiv(num, medName, qty, danWei, size, pl, fhf, tagId, useQty, useUnit, oldTypeName, medType,specialInstr,standard) {
    //TODO 回滚
    //useUnit="";
    //useQty="";
    var fen = "#";
    var isJZ = qty + danWei + 'x' + size + fen;
    if (parseFloat(size) <= 0 || parseFloat(size) == 1) {
        isJZ = qty + danWei;
    }
    if (oldTypeName == "煎服") {
        fen = "";
        //isJZ = "";
        isJZ = qty + danWei + useUnit;
        if (useUnit == danWei) {
            isJZ = qty + danWei;
        }
    }
    var sty = "";
    var nedNbsp = "";
    if (medType == "Western") {
        //$(".beizhu1").css("float","none");
        sty = "float:none;";
        nedNbsp = "";
    } else if (medType == undefined) {
        //$(".beizhu1").css("float", "left");
        sty = "float:left;";

        nedNbsp = "";
    } else {
        sty = "float:left;";

        nedNbsp = "&nbsp;";
    }
    //特殊说明为空
    if(specialInstr==null||specialInstr==""){
        specialInstr="";
    }else{
        specialInstr="("+specialInstr+")";
    }
    var str = '<div class="beizhu1" style="' + sty + '"  ids="' + tagId + '">' +
        '<div class="beizhu2">' +
        '<span>'
            //+ num
        + '</span>' +
        '</div>' +

        '<div>' +
        '<div class="beizhu_name">' +
        '<span>' + medName + '</span>' +
        '<span style="margin-left: 5px"></span>' +
        '<span>' + standard + '</span>' +
        '<span style="margin-left: 10px">' + isJZ + '</span>' +
        '<span style="margin-left: 10px"></span>' +
        '<span>' + specialInstr + '</span>' +
        '</div>' +

        '<div>' +
        '<span style="margin-right: 1em;" medType="tmpNbsp">' + pl + useQty + useUnit + nedNbsp + '</span>' +
        '<span >' + fhf + '</span>' +
        '</div>' +
        '</div>';

    return str;
}


function whenChangZhongMedFuSize(obj) {
    $('#fuSize').text($(obj).val())
}

function fn_AddMedicine(type, medicineTypes) {
    //medicineTypes = "";
    layer.open({
        type: 2,
        title: ['添加药品 - ' + medicineTypes, 'font-weight: bold'],
        area: ['720px', '550px'],
        scrollbar: false,
        content: '/medicine/add?type=' + type,
        end: function () {
            if (type == "Western") {
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
        content: '/medicine/edit/' + medicineId,
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
            fn_SelectMedicine(n, n.medicineType);
        });
        layer.msg("药品成功添加");
    });
}

function fn_ReferEmrChineseItems(emrId) {
    $.getJSON(String.format("/emr/{0}/chineseItems", emrId), function (data) {
        $.each(data, function (i, n) {
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
    $('#divPatientOverview').load("/fragment/patient/{0}/overview?refer=true".format(patientUid));
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

//            $("#tabtwo div[class='row'] div input[type='text']").each(function () {
//                alert($(this).attr('id'))
//            })


    var str = "";
    var num = 0.0;

    $(obj).siblings().each(function () {
//          <label for="vitalSign1">/</label>
//           <input type="hidden" name="vitalSignList[1].type" value="Hbp">
//          <input id="vitalSign1" type="text" name="vitalSignList[1].value" value="" class="form-control" onkeydown="pullItToPager(this)" onchange="pullItToPager(this)">
//          <label style="margin-right: 13px;">mmHg</label>      //此元素  可能没有
        num = parseInt(num) + 1;
//                console.info(num)

        if (num == 2) {
            str += $(obj).val();
        } else {

            str += $(this).text();
        }

//             if($(this).attr("id")=)
//                $(this).text()
//
//
//
    })

    if (str.indexOf("血压") > -1) {
        boluYao = str;
//                return;
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
//                            boluYao="";
                    $(this).remove();
                    console.info(1)
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
                console.info(2)
                console.info(needDeleteText)
                console.info($(this).text())
//                        alert(needDeleteText)
//                        alert($(this).text())


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

                console.info(3)
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


////打印处方签
//function printA5or80Cfq(type) {
//
//    if (type == "A5打印") {
//        YJZ_Printer.printUrl('../pub/printRpA5/${emr.id}');
//    } else {
//        YJZ_Printer.print80Url('../pub/printRp/${emr.id}');
//    }
//}
//
////打印就诊单
//function printA5oOr80Jzd(type) {
//    if (type == "A5打印") {
//        YJZ_Printer.printUrl('../pub/printDiagnosisA5/${emr.id}');//就诊单默认还是80打印机
//    } else {
//        YJZ_Printer.print80Url('../pub/printDiagnosis/${emr.id}');
//    }
//
//}


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
    "分十包",
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

//
//var pakegNameArray = [
//    "不分包",
//    "分一包",
//    "分二包",
//    "分三包",
//    "分四包",
//    "分五包",
//    "分六包",
//    "分七包",
//    "分八包",
//    "分九包",
//    "分十包",
//    "分十一包",
//    "分十二包",
//    "分十三包",
//    "分十四包",
//    "分十五包",
//    "分十六包",
//    "分十七包",
//    "分十八包",
//    "分十九包",
//    "分二十包",
//    "分二十一包",
//    "分二十二包",
//    "分二十三包",
//    "分二十四包",
//    "分二十五包",
//    "分二十六包",
//    "分二十七包",
//    "分二十八包",
//    "分二十九包",
//    "分三十包",
//
//];





