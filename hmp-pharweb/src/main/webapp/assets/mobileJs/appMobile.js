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
HIDD_SHOW = {
    Show: "show",
    Hidd: "hidd"

};
var lay;  //加载或者提示标签的 句柄
var index_Layer_hunde;

$(function () {
    $(".cleared").click(function () {
        $(".seach-input").val("");
        $(".seach").trigger("click");
    })
})
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
        //把 该病症增加到医生常用病症
        $.postJSON("/tag/addSymptomTag", {
            "symptomTag": tagName,
            "helpCode": pinyin.getCamelChars(tagName)
        }, function (res) {
            if (res && res.success) {
                if (res.msg)layer.msg(res.msg);
            }
        })
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
        $.postJSON("/tag/addDiagnosisTag", {
            "diagnosisTag": tagName, 'departmentId': -1,
            "helpCode": pinyin.getCamelChars(tagName)
        }, function (res) {
            if (res && res.success) {
                if (res.msg)layer.msg(res.msg);
            }

        })
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
var CategoryManagerLoadUrl;
function openMobileCategoryManagerWinDow(url) {
    CategoryManagerLoadUrl = url;
    var loadUrl = url;
    utlis_HiddOrShowHearAndBodyAndFooter(HIDD_SHOW.Hidd);
    $("#hiddDiv").load(loadUrl);
}

/**
 * 打开修改药方类别窗口
 * @param id
 * @param name
 */
function openMobileEditCategory(id, name) {
    layer.prompt({title: '修改药方类别', maxlength: 200, value: name, offset: "70px"},
        function (value, index, elem) {
            $.postJSON("/learn/subPage/category/editCategory", {
                "id": id,
                "name": value
            }, function (res) {
                if (res && res.success) {
                    $("#hiddDiv").load(CategoryManagerLoadUrl);
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
                    $("#hiddDiv").load(CategoryManagerLoadUrl);
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
        $("#hiddDiv").load(CategoryManagerLoadUrl);
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
    utlis_HiddOrShowHearAndBodyAndFooter(HIDD_SHOW.Hidd);
    $("#hiddDiv").load("/learn/subPage/selMed?id=" + id);
}

/**
 * 选择药品窗口
 * @param id
 */
var isNeedDel = false;
function editMobileMed(id, groupId, useQty, specialInstructions, useMode, lay2) {
    isNeedDel = false;
    var loadUrl = (("/learn/subPage/editSelMed?" +
        "id={0}&groupId={1}&selectMedUseModType=" +
        "{2}&txtMedicineQty={3}&specialInstructions={4}"
    ).format(id, groupId, useMode, useQty, specialInstructions));

    lay = lay2;
    utlis_HiddOrShowHearAndBodyAndFooter(HIDD_SHOW.Hidd);
    $("#hiddDiv").load(loadUrl);
}

/**
 * 打开增加药品 页面
 * @param medType
 */
function openAddMedWind(medType) {
    var loadUrl = "/learn/subPage/addPrescription?type=" + medType;
    $("#hiddDiv").load(loadUrl);
    utlis_HiddOrShowHearAndBodyAndFooter(HIDD_SHOW.Hidd);
}

/**
 *
 * @returns {已经输入返回真 未输入返回夹}
 */
function utlis_CheackNameIsInput() {
    if ($("input[name='name']").val() == "") {
        layer.msg("请您输入药方名称!");
        setTimeout(function () {
            // $(".pre-detail-nav>.nav>li").eq(3).find("a").attr("style", "color:#B8B8B8");
            $(".pre-detail-nav>.nav>li>a").eq(2).trigger("click");
        }, 200);
        return false;
    } else {
        $(".pre-detail-nav>.nav>li").eq(3).find("a").attr("style", "");
        return true;
    }
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
    var removeIcon = document.createElement("a");
    var removeIconSun = document.createElement("i");
    $(removeIconSun).attr("class", "fa fa-times");
    $(removeIcon).attr("href", "#");
    $(removeIcon).append($(removeIconSun));
    $(inputTypeId).attr("type", "hidden");
    $(inputNameId).attr("type", "hidden");
    $(fSpan).attr("class", "tag");
    $(fSpan).attr("style", "padding-right:10px");
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
    $(fSpan).append($(removeIcon));
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
    var tmpLay = tag + utlis_GetRandom(1000, "cih");
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
        '<a href="javascript:" onclick="editMobileMed(\'{0}\',\'{1}\',\'{2}\',\'{3}\',\'{4}\',\'{5}\')"><i class="fa fa-cog"></i></a> '.format(data.medicineId, data.groupId, data.qty, data.specialInstructions, data.useMode, tmpLay) +
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

/**
 * 显示 加载药品的DIV 和隐藏头 按钮  尾部信息  中间页面  或者 相反
 * @param type   需要隐藏还是显示
 */
function utlis_HiddOrShowHearAndBodyAndFooter(type, medType) {
    if (type == HIDD_SHOW.Hidd) {
        $("#headerDiv").hide();
        $("#indexDiv").hide();
        $("#footerDiv").hide();
        $("#hiddDiv").show();
        layer.msg("数据加载中,请稍后", {time: 2000, offset: ['40%']});
        index_Layer_hunde = layer.load(0, {shade: false});
    } else if (type == HIDD_SHOW.Show) {
        $("#headerDiv").show();
        $("#indexDiv").show();
        $("#footerDiv").show();
        $("#hiddDiv").hide();
        $("#hiddDiv").html("");

        if (medType == "edit") {
            if (isNeedDel) {
                $(("span[ids='{0}']").format(lay)).remove();
            }
            lay = "";
        } else if (medType == "categroy") {
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


    }
}