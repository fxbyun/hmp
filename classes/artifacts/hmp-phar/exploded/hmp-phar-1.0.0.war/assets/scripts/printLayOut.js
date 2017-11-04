/**
 * Created by teemoer@cntv.cn on 2016/6/12 0012.
 */
var xwArray = ["针灸", "拔罐", "按摩", "贴敷"];

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

function doPullMedToPaper(tag, data, exist) {
    if (parseFloat(data.copies) > 1) {
        var copiesTmp = (data.copies + "").split(".");
        if (copiesTmp.length > 1) {
            if (copiesTmp[1] == 0) {
                data.copies = copiesTmp[0];
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
    //var span = '<span class="tag" id="{0}">{1}&nbsp{6}{2}{3}x{4}份&nbsp({5})'.format(tag, data.medicineName, data.qty,
            //data.unitLabel, data.copies, data.specialInstructions, data.standard) + '</span>';
    //服用方式  如 口服  取值   data.useMode
    if (data.useQty != "" && data.useQty != undefined && data.useQty != NaN) {
        if (data.useQty.indexOf("每次 ") < 0) {
            data.useQty = " 每次 " + data.useQty;
        }
    }
    //如果 标准用量不适用
    if (!data.hasUsage) {
        data.useTimes = "";
        data.usingTime = "";
        data.useUnit = "";
        data.useQty = ""
    }
    //是否新建服用类型Div 比如 口服 滴注 嚼服
    if (isNeddCreateNewMedDiv(data.useMode)) {
        var strs = createAllTypeDiv(data, tag);
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
        var tmpMedDiv = createMedDiv(tag, data);
        if (oldTypeName == data.useMode) {
            var groupName = $(noode.chinedJqObj).parent().prev().text().replace("：", "");
            if (!exist) {
                if (groupName == groupNameArray[data.groupIndex]) {
                    $(noode.chinedJqObj).after(tmpMedDiv);
                } else {
                    var ppTop = '<div class="beizhu">';
                    var ppFloer = '</div>';
                    var fenHao = "：";
                    var pTop = '<div style="float:left; width:85%;padding-left: 38px;">';
                    if (data.groupIndex == 10 || data.groupIndex == 0) {
                        fenHao = "";
                        pTop = '<div style="float:left; width:100%; padding-left: 38px;">';
                    }
                    var groupNameDiv = '<div class="groupName" style="float:left; font-size:16px; font-weight:bold;">' + groupNameArray[data.groupIndex] + fenHao + '</div>';

                    var pFloer = "</div>";
                    var endMendWithGroupDiv = ppTop + groupNameDiv + pTop + tmpMedDiv + pFloer + ppFloer;
                    $(noode.chinedJqObj).parent().parent().after(endMendWithGroupDiv)
                }
            } else {
                if (groupName == groupNameArray[data.groupIndex]) {
                    $(noode.chinedJqObj).replaceWith(tmpMedDiv);
                } else {
                    var beizhuDiv = "";
                    var nowTypeHaveMedSize = 0;
                    $('strong').each(function () {
                        if ($(this).text().replace(":", "") == data.useMode) {
                            beizhuDiv = $(this).parent().parent();
                            return;
                        }
                    });
                    var groupName = $(noode.chinedJqObj).parent().prev().text().replace("：", "");
                    var thisGroupNameNextBeiZhu1Div = "";
                    $(beizhuDiv).parent().find("div[class='groupName']").each(function () {
                        if ($(this).text().replace("：", "") == groupNameArray[data.groupIndex]) {
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
                        var pTop = '<div style="float:left; width:85%;padding-left: 38px;">';
                        if (data.groupIndex == 10 || data.groupIndex == 0) {
                            fenHao = "";
                            pTop = '<div style="float:left; width:100%; padding-left: 38px;">';
                        }
                        var groupNameDiv = '<div class="groupName" style="float:left; font-size:16px; font-weight:bold;">' + groupNameArray[data.groupIndex] + fenHao + '</div>';
                        var pFloer = "</div>";
                        tmpMedDiv = createMedDiv(tag, data);
                        var endMendWithGroupDiv = ppTop + groupNameDiv + pTop + tmpMedDiv + pFloer + ppFloer;
                        if ($(beizhuDiv).find("div[class='beizhu1']").size() > 0) {
                            $(beizhuDiv).parent().append(endMendWithGroupDiv)
                        } else {
                            $(beizhuDiv).append(endMendWithGroupDiv)
                        }
                    } else {
                        tmpMedDiv = createMedDiv(tag, data);
                        thisGroupNameNextBeiZhu1Div.after(tmpMedDiv)
                    }
                }
            }
        } else {
            var nowTypeHaveMedSize = 0;
            var beizhuDiv = "";
            $('strong').each(function () {
                if ($(this).text().replace(":", "") == data.useMode) {
                    beizhuDiv = $(this).parent().parent();
                    return;
                }
            });
            var groupName = $(noode.chinedJqObj).parent().prev().text().replace("：", "");
            var thisGroupNameNextBeiZhu1Div = "";
            $(beizhuDiv).parent().find("div[class='groupName']").each(function () {
                var groupNameTxt = $(this).text().replace("：", "").replace("[", "").replace("]", "").replace(data.useMode, "");
                if (groupNameTxt == groupNameArray[data.groupIndex] || (groupNameTxt == "" && groupNameArray[data.groupIndex] == " ")) {
                    thisGroupNameNextBeiZhu1Div = $(this).next().find("div[class='beizhu1']:last");
                    nowTypeHaveMedSize = $(this).next().find("div[class='beizhu1']").size();
                }
            });
            if (thisGroupNameNextBeiZhu1Div == "") {
                var ppTop = '<div class="beizhu">';
                var ppFloer = '</div>';
                var hou_fenHao = "]";
                var pre_fenHao = "[";
                var pTop = '<div style="float:left; width:85%;padding-left: 38px;">';
                var groupNameTxt = data.useMode + pre_fenHao + groupNameArray[data.groupIndex] + hou_fenHao;
                if (groupNameArray[data.groupIndex] == " ") {
                    groupNameTxt = ""+data.useMode;
                }
                if (data.groupIndex == 10 || data.groupIndex == 0) {
                    fenHao = "";
                    pTop = '<div style="float:left; width:100%; padding-left: 38px;">';
                }
                var groupNameDiv = '<div class="groupName" style="float:left; font-size:16px; font-weight:bold;">' + groupNameTxt + '</div>';
                var pFloer = "</div>";
                tmpMedDiv = createMedDiv(tag, data);
                var endMendWithGroupDiv = ppTop + groupNameDiv + pTop + tmpMedDiv + pFloer + ppFloer;
                if ($(beizhuDiv).nextAll().size() > 0) {
                    if (groupNameArray[data.groupIndex] == " ") {
                        $(beizhuDiv).siblings("span").after(endMendWithGroupDiv);
                    } else {
                        $(beizhuDiv).nextAll().last().after(endMendWithGroupDiv)
                    }
                } else {
                    if (groupNameArray[data.groupIndex] == " ") {

                        $(beizhuDiv).parent().find("strong").parent().parent().after(endMendWithGroupDiv)
                    } else {
                        $(beizhuDiv).after(endMendWithGroupDiv)
                    }
                }
            } else {
                tmpMedDiv = createMedDiv(tag, data);
                thisGroupNameNextBeiZhu1Div.after(tmpMedDiv)
            }
        }
    }
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
 * @param num       当前药品的顺序
 * @param medName   药品名称
 * @param qty       数量
 * @param danWei    单位
 * @param size      分数
 * @param pl        频率
 * @param fhf       饭后服  还是饭前
 */
function createMedDiv(tagId, data) {
    var fen = "份";
    var isJZ = data.qty + data.unitLabel + '&nbsp;x&nbsp;' + data.copies + fen;
    if (parseFloat(data.copies) <= 0 || parseFloat(data.copies) == 1) {
        isJZ = data.qty + data.unitLabel;
    }
    if (data.useMode == "煎服") {
        fen = "";
        isJZ = data.qty + data.unitLabel + data.useUnit;
        if (data.useUnit == data.unitLabel) {
            isJZ = data.qty + data.unitLabel;
        }
    }
    var sty = "";
    var nedNbsp = "";
    if (data.medType == "Western") {
        sty = "float:none;";
        nedNbsp = "";
    } else if (data.medType == undefined) {
        sty = "float:left;";

        nedNbsp = "";
    } else if (data.useMode != "煎服") {
        sty = "float:none;";
        nedNbsp = "";
    }
    else {
        sty = "float:left;";
        nedNbsp = "&nbsp;";
    }
    //特殊说明为空
    var specialStr=null;
    if (data.specialInstructions == null || data.specialInstructions == "") {
        specialStr="";
        //data.specialInstructions = "";
    } else {
        specialStr="(" + data.specialInstructions + ")";
        //data.specialInstructions = "(" + data.specialInstructions + ")";
    }
    //煎服的div
    var standardUseQty = '<div>' +
        '<span style="margin-right: 1em;" medType="tmpNbsp">' + data.useTimes +
        data.useQty + data.useUnit + nedNbsp + '</span>' +
        '<span >' + data.usingTime + '</span>' +
        '</div>';
    //当煎服时，不显示加入该div
    if (data.useMode == "煎服") {
        standardUseQty = "";
    }
    var str = '<div class="beizhu1" style="' + sty + '"  ids="' + tagId + '">' +
        '<div class="beizhu2">' +
        '<span>'
        + '</span>' +
        '</div>' +
        '<div>' +
        '<div class="beizhu_name">' +
        '<span>' + data.medicineName + '</span>' +
        '<span style="margin-left: 5px"></span>' +
        '<span>' + data.standard + '</span>' +
        '<span style="margin-left: 10px">' + isJZ + '</span>' +
        '<span style="margin-left: 10px"></span>' +
        '<span>' + specialStr + '</span>' +
        '</div>' +
        standardUseQty +
        '</div>';
    return str;
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
 * @param fhf       饭后服  还是饭前 data.useTimes
 */
function createAllTypeDiv(data, tag) {
    var fuSize = "";
    var fen = "份";
    var isJZ = data.qty + data.unitLabel + '&nbsp;x&nbsp;' + data.copies + fen;
    if (parseFloat(data.copies) <= 0 || parseFloat(data.copies) == 1) {
        isJZ = data.qty + data.unitLabel;
    }

    if (data.useMode == "煎服") {
        fuSize = '<strong>(</strong><strong id="fuSize">' + data.chineseQty + '</strong><strong>副)</strong>';
        fen = "";
        //isJZ = "";
        isJZ = data.qty + data.unitLabel + data.useUnit;
        if (data.useUnit == data.unitLabel) {
            isJZ = data.qty + data.unitLabel;
        }

    }

    var groupText = '';
    var pre_fenHao = "[";
    var hou_fenHao = "]";
    var groupDivTop = '<div style="float:left; width:85%;padding-left: 38px;">';
    var groupNameTxt = pre_fenHao + groupNameArray[data.groupIndex] + hou_fenHao
    if (groupNameArray[data.groupIndex] == " " || data.useMode == "煎服" || groupNameArray[data.groupIndex] == "") {
        groupNameTxt = "";
    }
    if (data.groupIndex == 10 || data.groupIndex == 0) {
        fenHao = "";
        groupDivTop = '<div style="float:left; width:100%; padding-left: 38px;">';
    }
    groupText = '<div class="groupName" style="float:left; font-size:16px; font-weight:bold;">' + groupNameTxt + '</div>';

    var groupDivfloter = '</div>';
    var sty = "";
    var nedNbsp = "";
    if (data.medType == "Western") {
        //$(".beizhu1").css("float","none");
        sty = "float:none;"
        nedNbsp = "";
    } else if (data.medType == undefined) {
        //$(".beizhu1").css("float", "left");
        sty = "float:left;"
        nedNbsp = "";
    } else if (data.useMode != "煎服") {
        sty = "float:none;";
        nedNbsp = "";
    } else {
        //$(".beizhu1").css("float", "left");
        sty = "float:left;"
        nedNbsp = "&nbsp;";
    }
    //特殊说明为空
    var specialStr=null;
    if (data.specialInstructions == null || data.specialInstructions == "") {
        specialStr="";
        //data.specialInstructions = "";
    } else {
        specialStr="(" + data.specialInstructions + ")";
        //data.specialInstructions = "(" + data.specialInstructions + ")";
    }
    //煎服的div
    var standardUseQty = '<div>' +
        '<span style="margin-right: 1em;" medType="tmpNbsp">' + data.useTimes + data.useQty + data.useUnit + nedNbsp + '</span>' +
        '<span >' + data.usingTime + '</span>' +
        '</div>';
    //当煎服时，不显示加入该div
    if (data.useMode == "煎服") {
        standardUseQty = "";
    }
    var str = '<div class="content1">' +
        '<div class="beizhu">' +
        '<span><strong>' + data.useMode + "" + '</strong>' +
        '' + fuSize +
        '</span>'
        + groupText + groupDivTop +
        '<div class="beizhu1" style="' + sty + '"   ids="' + tag + '">' +
        '<div class="beizhu2">' +
        '<span>'
        + '</span>' +
        '</div>' +
        '<div>' +
        '<div class="beizhu_name">' +
        '<span>' + data.medicineName + '</span>' +
        '<span style="margin-left: 5px"> </span>' +
        '<span>' + data.standard + '</span>' +
        '<span style="margin-left: 10px">' + isJZ + '</span>' +
        '<span style="margin-left: 10px"> </span>' +
        '<span>' + specialStr + '</span>' +
        '</div>' +
        standardUseQty +
        '</div>' +
        '</div>' + groupDivfloter +
        '</div>'
        + '</div>';


    /*if (data.useQty != "") {
     data.useQty = data.useQty.replace(" 每次 ", "");
     }*/
    return str;
}

/**
 *  @param medName 药品名称
 *  @return 返回老分类的名称
 */
function isThisMedHaveInAnerType(medId, needDelete, data) {
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
    oldTypeName = $(this).parent().parent().parent().find("strong").eq(0).text().replace(":", "");
    if (needDelete && medId != "" && (oldTypeName != data.useMode)) {
        deletMedForPaper(medId)
    }
    return oldTypeName;
}

/**
 *
 * @param typeName  要查询的类别名字
 * @returns {{size: number, chinedJqObj: null}}   当前分类有的药的数量   最后一个药的JQ对象
 */
function thisTypeHaveHowMnyMed(typeName) {
    var retObj = {size: 0, chinedJqObj: null};
    $('.content1 strong').each(function () {
        if (typeName == $(this).text()) {
            var thisTypeNameChined = $(this).parent().parent().find('div[class="beizhu1"]');
            var chinedSize = parseInt($(thisTypeNameChined).size()) + 1;
            var chinedJqObj = $(this).parent().parent().find('div[class="beizhu1"]:last');
            retObj.size = chinedSize;
            retObj.chinedJqObj = chinedJqObj;
        }
    });
    return retObj;
}
/**
 * 根据ID  删除 处方笺上面的药品
 * @param findId
 */
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
                var medSize = $(this).parent().parent().find("div[class='beizhu1']").size();
                if (medSize > 1) {
                    //当分组中药瓶为1时，删除分组符号
                    if ($(this).parent().find("div[class='beizhu1']").size() == 1) {
                        //不分组时HTML结构完全不同
                        if ($(this).parent().prev().prev().attr("class") == "beizhu") {
                            $(this).parent().prev().remove();
                            $(this).parent().remove();
                        } else {
                            $(this).parent().prev().prev().remove();
                            $(this).parent().prev().remove();
                            $(this).parent().remove();
                        }
                    } else {
                        $(this).remove();
                    }
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
$(function () {
    for (var index = 1; index < oneMedInfo.length; index++) {
        var data = oneMedInfo[index];

        doPullMedToPaper("medicineTag_" + data.medicineId,
            data, -1)

    }
})