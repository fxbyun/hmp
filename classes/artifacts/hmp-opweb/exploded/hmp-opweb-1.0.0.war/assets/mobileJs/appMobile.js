/**
 * Created by IntelliJ IDEA 15.0
 * User ZW_Teemoer  teemoer@cntv.cn
 * Date 16/8/9
 * Time 下午2:02
 */

/**
 * 全局变量
 */

DIAGNOSIS_TAG = "diagnosisTag";
SYMPTOMS_TAG = "symptoms";
MED_TYPE = {
    Western: "Western",
    Chinese: "Chinese",
    MED_DIV: {
        WesternDiv: "westernsSelTag",
        ChineseDiv: "chineseSelTag"
    }

};

/**
 * 加载手机端病症标签页面
 * @param page   页码
 */
function loadMobileSymptoms(page) {
    var name = "";
    if (page == null || page == "" || page == undefined) {
        page = 0;
    }


    if ($.strIsVail($("#symptomsScanInput").val())) {
        name = $("#symptomsScanInput").val();
        $("#sumotom_tag_div").load("/learn/subPage/symptom?page={0}&name={1}".format(page, name));
    } else {
        $("#sumotom_tag_div").load("/learn/subPage/symptom?page={0}".format(page));
    }


}
/**
 * 增加患者病症
 */

function addMobileSymptomTag() {
    var value = $("#addSymptomsInput").val();
    if ($.strIsVail(value)) {
        $.postJSON("/tag/addSymptomTag", {
            "symptomTag": value,
            "helpCode": pinyin.getCamelChars(value)
        }, function (res) {
            if (res && res.success) {
                layer.msg("添加成功!");
                addSymptomTagToDiv(res.data.id, res.data.name)
            } else {
                layer.msg("增加病症失败!");
            }

        })
    } else {
        layer.msg("请输入病症!");
    }
}
/**
 *   增加选择的病症标签到DIV中
 */

function addSymptomTagToDiv(id, tagName) {
    var isSave = false;
    $("#selectSymptomsDiv span").each(function () {
        if ($(this).find("input[name='mainSuits']").val() == tagName) {
            layer.msg("您已经增加该病症!");
            isSave = true;
            return false;
        }
    });
    if (!isSave) {
        $("#selectSymptomsDiv").append(utlis_CreateSpanByTypeAndIdAndName(id, tagName, SYMPTOMS_TAG));
    }

}

/**
 * 加载手机端症状标签页面
 * @param page   页码
 */
function loadMobileDiagnosisTag(page) {
    var name = "";
    if (page == null || page == "" || page == undefined) {
        page = 0;
    }
    if ($.strIsVail($("#dianosisScanInput").val() != "")) {
        name = $("#dianosisScanInput").val();
        $("#diagbosis_tag_div").load("/learn/subPage/diagbosis?page={0}&name={1}".format(page, name));
    } else {
        $("#diagbosis_tag_div").load("/learn/subPage/diagbosis?page={0}".format(page));
    }
}

/**
 * 增加症状
 */
function addMobileDiagnosisTag() {
    var value = $("#addDiagbosisInput").val();
    if ($.strIsVail(value)) {

        $.postJSON("/tag/addDiagnosisTag", {
            "departmentId": 1, "diagnosisTag": value,
            "helpCode": pinyin.getCamelChars(value)
        }, function (res) {
            if (res && res.success) {
                layer.msg("增加症状成功!");

                addDiagnosisTagToDiv(res.data.id, res.data.name);

            } else {
                layer.msg("增加症状失败!");
            }
        })

    } else {
        layer.msg("请输入症状");
    }
}

/**
 *   增加选择的病症标签到DIV中
 */

function addDiagnosisTagToDiv(id, tagName) {
    var isSave = false;
    $("#selectDiagnosisDiv span").each(function () {
        if ($(this).find("input[name='diagnosisResults']").val() == tagName) {
            layer.msg("您已经增加该症状!");
            isSave = true;
            return false;
        }
    });
    if (!isSave) {
        $("#selectDiagnosisDiv").append(utlis_CreateSpanByTypeAndIdAndName(id, tagName, DIAGNOSIS_TAG))
    }

}

/**
 * 加载手机端药方名称页面
 * @param page   页码
 */
function loadMobileCategory() {

    $("#tabthree").load("/learn/subPage/category");
}

/**
 * 打开药方类别管理窗口
 * @param url
 */
function openMobileCategoryManagerWinDow(url) {
    layer.open({
        type: 2,
        title: '药方类别管理',
        area: ['90%', '50%'],
        scrollbar: false,
        content: url,
        end: function () {

            $.postJSON("/learn/subPage/category/getCategoryList", {}, function (res) {
                if (res && res.success) {
                    $("#medCategorySelect").html("");
                    for (oneC in res.data) {
                        var oneD = res.data[oneC];
                        var option = document.createElement("option");
                        $(option).attr("value", oneD.id);
                        $(option).text(oneD.name);
                        $("#medCategorySelect").append($(option));
                    }
                }

            })


        }
    });
}

function openMobileEditCategory(id, name) {
    layer.prompt({title: '修改药方类别', maxlength: 200, value: name, offset: "70px"},
        function (value, index, elem) {
            $.postJSON("/learn/subPage/category/editCategory", {
                "id": id,
                "name": value
            }, function (res) {
                if (res && res.success) {
                    window.location.reload()
                }
                layer.close(index)
            })
        })
}

/**
 * 增加药方类别
 */
function openMobileAddCategory() {
    layer.prompt({title: '增加药方类别', maxlength: 200, value: "", offset: "70px"},
        function (value, index, elem) {
            $.postJSON("/learn/subPage/category/addCategory", {
                "name": value
            }, function (res) {
                if (res && res.success) {
                    window.location.reload()
                }
                layer.close(index)
            })
        })
}
/**
 * 删除药方类别
 * @param id
 */
function delMobileCategory(id) {
    $.getJSON("/learn/subPage/category/del/" + id, function (data) {
        window.location.reload()
    })
}


/**
 * 加载手机端中药标签页面
 * @param page   页码
 */
function loadChinaMedTag(page) {
    var name = "";
    if (page == null || page == "" || page == undefined) {
        page = 0;
    }

    if ($.strIsVail($("#chineseScanInput").val())) {
        name = $("#chineseScanInput").val();
        $("#chineseTg").load("/learn/subPage/chinaMedTag?name={0}&page={1}".format(name, page));
    } else {
        $("#chineseTg").load("/learn/subPage/chinaMedTag?page={0}".format(page));
    }


}


/**
 * 加载手机端西药标签页面
 * @param page   页码
 */
function loadWesternsTag(page) {
    var name = "";
    if (page == null || page == "" || page == undefined) {
        page = 0;
    }

    if ($.strIsVail($("#westernsScanInput").val())) {
        name = $("#westernsScanInput").val();
        $("#westernsTag").load("/learn/subPage/WesternsTag?name={0}&page={1}".format(name, page));
    } else {
        $("#westernsTag").load("/learn/subPage/WesternsTag?page={0}".format(page));
    }


}


/**
 * 选择药品窗口
 * @param id
 */
function selMobileMed(id) {
    layer.open({
        type: 2,
        title: '选择药品',
        area: ['100%', '70%'],
        scrollbar: false,
        content: "/learn/subPage/selMed?id=" + id,
        end: function () {

        }
    });
}

/**
 * 选择药品窗口
 * @param id
 */
var isNeedDel = false;
function editMobileMed(id, groupId, useQty, specialInstructions, useMode,lay) {
    isNeedDel = false;
    layer.open({
        type: 2,
        title: '修改药品',
        area: ['100%', '70%'],
        scrollbar: false,
        content: ("/learn/subPage/editSelMed?" +
        "id={0}&groupId={1}&selectMedUseModType={2}&txtMedicineQty={3}&specialInstructions={4}".format(id, groupId, useMode, useQty, specialInstructions)),
        end: function () {
            if (isNeedDel) {
                $("span[ids='{0}']".format(lay)).remove();
            }
        }
    });
}
/**
 * 根据药品类型增加药品到 对应的DIV
 * @param medObj
 */
function addMedToDivByMedType(medObj) {
    var spanObj = utlis_createMedSpan(medObj);
    if (medObj.medType == MED_TYPE.Western) {

        $("#" + MED_TYPE.MED_DIV.WesternDiv).append(spanObj);
    } else if (medObj.medType == MED_TYPE.Chinese) {

        $("#" + MED_TYPE.MED_DIV.ChineseDiv).append(spanObj);
    }
}
/**
 *创建病症和症状的SPAN
 * @param id
 * @param name
 * @param type
 */
function utlis_CreateSpanByTypeAndIdAndName(id, name, type) {
    var fSpan = document.createElement("span");
    var inputTypeId = document.createElement("input");
    var inputNameId = document.createElement("input");
    $(inputTypeId).attr("type", "hidden");
    $(inputNameId).attr("type", "hidden");
    $(fSpan).attr("class", "tag");
    $(fSpan).attr("onclick", "$(this).remove()");
    if (type == DIAGNOSIS_TAG) {
        $(inputTypeId).attr("name", "diagnosisTagIds");
        $(inputTypeId).attr("value", id);
        $(inputNameId).attr("name", "diagnosisResults");
        $(inputNameId).attr("value", name);
        //TODO 此处应该增加发送ajax  将医生的  症状增加到常用库


    } else {
        //TODO 此处应该增加发送ajax  将医生的 病症 增加到常用库
        $(inputTypeId).attr("name", "symptomTagIds");
        $(inputTypeId).attr("value", id);
        $(inputNameId).attr("name", "mainSuits");
        $(inputNameId).attr("value", name);
    }
    $(fSpan).append($(inputNameId));
    $(fSpan).append($(inputTypeId));
    $(fSpan).append(name);
    return $(fSpan);
}
/**
 * 根据传递进来的药品OBJ  生成 MED_SPAN Jquery对象 返回
 * @param data
 * @returns {*|jQuery|HTMLElement}
 */
function utlis_createMedSpan(data) {
    var tag = "medicineTag_" + data.medicineId;
    //生成一个随机数
    var tmpLay= tag+ utlis_GetRandom(1000,"cih");
    var ele = '<span class="tag"  id="{0}" ids="{5}">{1}&nbsp;{2}{3}x{4}#'.format(tag, data.medicineName, data.qty, data.unitLabel, data.copies, tmpLay) +
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
        // '<input type="hidden" name="standard" value="' + data.standard + '" /> ' +
        '<a href="javascript:" onclick="editMobileMed(\'{0}\',\'{1}\',\'{2}\',\'{3}\',\'{4}\',\'{5}\')"><i class="fa fa-cog"></i></a> '.format(data.medicineId, data.groupId, data.qty, data.specialInstructions, data.useMode,tmpLay) +
        '<a href="javascript:" onclick="$(this).parent().remove()"><i class="fa fa-times"></i></a> ' +
        '</span>';
    return $(ele);
}

/**
 * 获得随机数        格式为 lay+随机数(0-9)*n(位数)
 * @param n  位数
 * @param lay   指定前缀
 * @returns {lay+随机数(0-9)*n(位数)}
 */
function utlis_GetRandom(n, lay) {
    if (lay) {
        return (lay + (Math.floor(Math.random() * n + 1)));
    }
    return Math.floor(Math.random() * n + 1);
}