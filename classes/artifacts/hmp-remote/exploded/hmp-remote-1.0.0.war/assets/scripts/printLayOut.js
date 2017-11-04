/**
 * Created by teemoer@cntv.cn on 2016/6/12 0012.
 */

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

function doPullMedToPaper(tag, medicineName, qty, unitLabel, copies, data, exist, useTimes, usingTime, hasUsage, useQty, useUnit) {
    if (data.useMode == "研末") {
        groupNameArray = pakegNameArray;
    } else if (data.useMode == "贴敷") {
        groupNameArray = acuPointsArrs;
    } else {
        groupNameArray = groupBackNameArray;
    }
    if (data.groupId == "") {
        data.groupId = "0";
    }

    //var span = '<span class="tag" id="{0}">{1}&nbsp;{2}{3}x{4}份'.format(tag, data.medicineName, data.qty, data.unitLabel, data.copies) + '</span>';
    //服用方式  如 口服  取值   data.useMode
    if (useQty != "") {
        useQty = " 每次 " + useQty;
    }
    if (!hasUsage) {
        useTimes = "&nbsp;";
        usingTime = "&nbsp;";
        useUnit = "&nbsp;";
        useQty = "&nbsp;"
    }

    if (useTimes == "") {
        useTimes = "&nbsp;";
    }

    //是否新建服用类型Div 比如 口服 滴注 嚼服
    if (isNeddCreateNewMedDiv(data.useMode)) {
        var strs = createAllTypeDiv(data.useMode, "1", medicineName, qty, unitLabel, copies, useTimes, usingTime, tag, useQty, useUnit, data.groupId);
        $("#medContext").append(strs)
    } else {
        var oldTypeName = isThisMedHaveInAnerType("medicineTag_" + data.medicineId, true, data.useMode);

        var noode = thisTypeHaveHowMnyMed(data.useMode);
        if (exist) {
            var objs = $('div [ids="' + tag + '"]');
            noode.size = $(objs.find('div')[0]).find('span').text().replace("、", "");
            noode.chinedJqObj = objs;
        }
        var tmpMedDiv = createMedDiv(noode.size, medicineName, qty, unitLabel, copies, useTimes, usingTime, tag, useQty, useUnit, data.useMode);
        if (oldTypeName == data.useMode) {
            var groupName = $(noode.chinedJqObj).parent().prev().text().replace("：", "");
            if (!exist) {

                if (groupName == groupNameArray[data.groupId]) {
                    $(noode.chinedJqObj).after(tmpMedDiv);
                } else {
                    var ppTop = '<div class="beizhu">';
                    var ppFloer = '</div>';
                    var fenHao = "：";
                    if (data.groupId == 10 || data.groupId == 0) {
                        fenHao = "";
                    }
                    var groupNameDiv = '<div class="groupName" style="width:13%; float:left; font-size:16px; font-weight:bold;">' + groupNameArray[data.groupId] + fenHao + '</div>';
                    var pTop = '<div style="float:left; width:87%;">';
                    var pFloer = "</div>";
                    if (groupNameArray[data.groupId] == " ") {
                        //groupNameDiv = "";
                        pTop = '<div style="float:left; width:100%;">';
                    }
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
                        if (data.groupId == 10 || data.groupId == 0) {
                            fenHao = "";
                        }
                        var groupNameDiv = '<div class="groupName" style="width:13%; float:left; font-size:16px; font-weight:bold;">' + groupNameArray[data.groupId] + fenHao + '</div>';
                        var pTop = '<div style="float:left; width:87%;">';
                        if (groupNameArray[data.groupId] == " ") {
                            //groupNameDiv = "";
                            pTop = '<div style="float:left; width:100%;">';
                        }

                        var pFloer = "</div>";
                        tmpMedDiv = createMedDiv(nowTypeHaveMedSize + 1, medicineName, qty, unitLabel, copies, useTimes, usingTime, tag, useQty, useUnit, data.useMode);
                        var endMendWithGroupDiv = ppTop + groupNameDiv + pTop + tmpMedDiv + pFloer + ppFloer;
                        //$(beizhuDiv).after(endMendWithGroupDiv)
                        if ($(beizhuDiv).find("div[class='beizhu1']").size() > 0) {
                            $(beizhuDiv).parent().append(endMendWithGroupDiv)
                        } else {
                            $(beizhuDiv).append(endMendWithGroupDiv)
                        }

                    } else {
                        tmpMedDiv = createMedDiv(nowTypeHaveMedSize + 1, medicineName, qty, unitLabel, copies, useTimes, usingTime, tag, useQty, useUnit, data.useMode);
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
                if (data.groupId == 10 || data.groupId == 0) {
                    fenHao = "";
                }
                var groupNameDiv = '<div class="groupName" style="width:13%; float:left; font-size:16px; font-weight:bold;">' + groupNameArray[data.groupId] + fenHao + '</div>';
                var pTop = '<div style="float:left; width:87%;">';
                var pFloer = "</div>";
                if (groupNameArray[data.groupId] == " ") {
                    //groupNameDiv = "";
                    pTop = '<div style="float:left; width:100%;">';
                }
                tmpMedDiv = createMedDiv(nowTypeHaveMedSize + 1, medicineName, qty, unitLabel, copies, useTimes, usingTime, tag, useQty, useUnit, data.useMode);
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

                    //$(beizhuDiv).after(endMendWithGroupDiv)
                }
            } else {
                tmpMedDiv = createMedDiv(nowTypeHaveMedSize + 1, medicineName, qty, unitLabel, copies, useTimes, usingTime, tag, useQty, useUnit, data.useMode);
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
function createMedDiv(num, medName, qty, danWei, size, pl, fhf, tagId, useQty, useUnit, oldTypeName) {
    //TODO 回滚
    //useUnit="";
    //useQty="";
    var fen = "#";
    var isJZ = qty + danWei + 'x' + size + fen;

    if (parseInt(size) <= 1) {
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

    var str = '<div class="beizhu1" ids="' + tagId + '">' +
        '<div class="beizhu2">' +
        '<span>'
        //+ num
        + '</span>' +
        '</div>' +

        '<div>' +
        '<div class="beizhu_name">' +
        '<span>' + medName + '</span>' +
        '<span style="margin-left: 10px">' + isJZ + '</span>' +
        '</div>' +

        '<div>' +
        '<span style="margin-right: 1em;">' + pl + useQty + useUnit + '</span>' +
        '<span >' + fhf + '</span>' +
        '</div>' +
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
 * @param fhf       饭后服  还是饭前
 */
function createAllTypeDiv(typeName, num, medName, qty, danWei, size, pl, fhf, tagId, useQty, useUnit, groupId) {
    var fuSize = "";
    var fen = "#";
    var isJZ = qty + danWei + 'x' + size + fen;

    if (parseInt(size) <= 1) {
        isJZ = qty + danWei;
    }

    if (typeName == "煎服") {
        fuSize = '<strong>(</strong><strong id="fuSize">' + data.chineseQty + '</strong><strong>副)</strong>';
        fen = "";
        //isJZ = "";
        isJZ = qty + danWei + useUnit;
        if (useUnit == danWei) {
            isJZ = qty + danWei;
        }
    }

    var groupText = '';
    var fenHao = "：";
    if (groupId == 10 || groupId == 0) {
        fenHao = "";
    }
    groupText = '<div class="groupName" style="width:13%; float:left; font-size:16px; font-weight:bold;">' + groupNameArray[groupId] + fenHao + '</div>';
    var groupDivTop = '<div style="float:left; width:87%;">';
    if (groupNameArray[groupId] == " ") {
        //groupText = "";
        groupDivTop = '<div style="float:left; width:100%;">';
    }

    var groupDivfloter = '</div>';

    var str = '<div class="content1">' +
        '<span><strong>' + typeName
        //+ ":"
        + '</strong>' +
        '' + fuSize +
        '</span>' +
        '<div class="beizhu">' + groupText + groupDivTop +
        '<div class="beizhu1" ids="' + tagId + '">' +
        '<div class="beizhu2">' +
        '<span>'
        //+ num
        + '</span>' +
        '</div>' +

        '<div>' +
        '<div class="beizhu_name">' +
        '<span>' + medName + '</span>' +
        '<span style="margin-left: 10px">' + isJZ + '</span>' +
        '</div>' +

        '<div>' +
        '<span style="margin-right: 1em;">' + pl + useQty + useUnit + '</span>' +
        '<span >' + fhf + '</span>' +
        '</div>' +
        '</div>' +
        '</div>' + groupDivfloter +

        '</div>'


        + '</div>';
    return str;
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

$(function () {
    for (var index = 1; index < oneMedInfo.length; index++) {
        data = oneMedInfo[index];
        doPullMedToPaper("medicineTag_" + data.medicineId,
            data.medicineName,
            data.qty,
            data.unitLabel,
            data.copies,
            data,
            -1,
            data.useTimes,
            data.usingTime,
            data.hasUsage,
            data.useQty,
            data.useUnit)
    }
})