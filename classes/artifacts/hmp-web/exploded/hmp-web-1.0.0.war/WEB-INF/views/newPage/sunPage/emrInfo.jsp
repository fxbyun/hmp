<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/6/4 0004
  Time: 11:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
    <script type="application/javascript">


        var medDataSum = {};
        var medChinaDataSum = {};
        var addEmrId = "";
        var addType = "";

        function setEmrId(id, addType1) {
            addEmrId = "emrId" + id;
            addType = addType1;
        }

        function createMedData(data, emrId) {
            var tag = "rpMedicineTag_" + data.medicineId;
            if (data.groupIndex === undefined)data.groupIndex = "0";
            if (data.groupIndex === null)data.groupIndex = "0";
            if (data.groupIndex == "")data.groupIndex = "0";

            var ele = '<span class="tag"  id="' + tag + '">' + data.medicineName + '&nbsp;' + data.qty + data.unitLabel + 'x' + data.copies +
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
                    '<input type="hidden" name="medicineGroupId" value="' + data.groupIndex + '" /> ' +
                    '<input type="hidden" name="specialInstructions" value="' + data.specialInstructions + '" /> ' +
                    '<input type="hidden" name="standard" value="' + data.standard + '" /> ' +
                    '<a href="javascript:" onclick="fn_ShowEditMedicine('
                    + '\'' + data.medicineId + '\',' + '\'edit\',\'' + tag + '\'' +
                    ')"><i class="fa fa-cog"></i></a> ' +
                    '<a href="javascript:" onclick="fn_RemoveElement($(this).parent())"><i class="fa fa-times"></i></a> ' +
                    '</span>';
            medDataSum["emrId" + emrId] += ele;
        }

        function createChinaMedData(data, emrId) {
            var tag = "rpMedicineTag_" + data.medicineId;
            if (data.groupIndex === undefined)data.groupIndex = "0";
            if (data.groupIndex === null)data.groupIndex = "0";
            if (data.groupIndex == "")data.groupIndex = "0";

            var ele = '<span class="tag"  id="' + tag + '">' + data.medicineName + '&nbsp;' + data.qty + data.unitLabel + 'x' + data.copies +
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
                    '<input type="hidden" name="medicineGroupId" value="' + data.groupIndex + '" /> ' +
                    '<input type="hidden" name="specialInstructions" value="' + data.specialInstructions + '" /> ' +
                    '<input type="hidden" name="standard" value="' + data.standard + '" /> ' +
                    '<a href="javascript:" onclick="fn_ShowEditMedicine('
                    + '\'' + data.medicineId + '\',' + '\'edit\',\'' + tag + '\'' +
                    ')"><i class="fa fa-cog"></i></a> ' +
                    '<a href="javascript:" onclick="fn_RemoveElement($(this).parent())"><i class="fa fa-times"></i></a> ' +
                    '</span>';
            medChinaDataSum["emrId" + emrId] += ele;
        }

        //新增药方
        function fn_AddRp() {
            var index = layer.open({
                type: 2,
                maxmin: true,
                title: '添加药方',
                area: ['900px', '720px'],
                scrollbar: false,
                content: '/rp/add',
                end: function () {
                    fn_LoadRpList(${rpPage.number});
                }
            });
            layer.full(index);
        }

        $(function () {
            $("span[tmpSize='copiesSize']").each(function () {
                $(this).text($(this).text().replace(".0#", "#"));
            });
            $("#doctorNameEmr").text("${emr.doctor.name}");
        });

        /* 全屏查看图片 */
        function openImg(url) {
            var index = layer.open({
                type: 2,
                maxmin: true,
                title: '查看图片',
                area: ['700px', '500px'],
                scrollbar: false,
                content: url
            });
//            layer.full(index);
        }

    </script>
</head>
<div style="padding: 5px 0 5px 20px;">
    <h3>患者主诉<%--${emr.doctor.name}--%></h3>

    <p><c:if test="${empty emr.mainSuit}">无</c:if> <c:if
            test="${not empty emr.mainSuit}">${emr.mainSuit.replace(";","")}</c:if></p>

    <c:if test="${not empty emr.remarks}">
    <p>其他主诉：${emr.remarks}<p>
    </c:if>

</div>
<div style="padding: 5px 0 5px 20px;">
    <h3>生命体征</h3>

    <p>
        <c:forEach items="${emr.vitalSignList}" var="vs" varStatus="status">
            <c:if test="${vitalSignLabels[vs.type]!='其他'}">
                <c:if test="${not empty vs.value}">${vitalSignLabels[vs.type]}${vs.value}${vitalSignUnits[vs.type]}
                    <c:if test="${not empty vitalSignUnits[vs.type]}">;&nbsp;</c:if>
                </c:if>
            </c:if>

            <c:if test="${vitalSignLabels[vs.type]=='其他'}">
                <c:if test="${not empty vs.value}">${vs.value}${vitalSignUnits[vs.type]}
                    <c:if test="${not empty vitalSignUnits[vs.type]}">;&nbsp;</c:if>
                </c:if>
            </c:if>
        </c:forEach>

    </p>
</div>
<div style="padding: 5px 0 5px 20px;">
    <h3>初步诊断</h3>

    <p>
        <c:if test="${empty emr.diagnosisResult}">无</c:if>
        <c:if
                test="${not empty emr.diagnosisResult}">${emr.diagnosisResult}</c:if>
    </p>
</div>


<div style="padding: 5px 0 5px 20px; overflow: hidden;">
    <c:set var="westernMedicinesByUseMode" value="${emr.westernMedicinesByUseModeAndGruopId}"/>
    <h3>处方药 - 西药及中成药房
        <c:if test="${not empty westernMedicinesByUseMode}">
            <button onclick="setEmrId(${emr.id},'western');fn_AddRp();" type="button" class="btn btn-success"><i
                    class="fa fa-plus"></i>
                新增为经验方
            </button>
        </c:if>
    </h3>

    <c:set var="xwString" value="${'针灸, 拔罐, 按摩,贴敷'}"/>
    <c:if test="${not empty westernMedicinesByUseMode}">

        <c:forEach var="entry" items="${westernMedicinesByUseMode}">
            <div class="content">

                <c:forEach varStatus="status2" var="em2" items="${entry.value}">
                    <div data-ids="${em2}">
                        <div class="form-group" style="margin-bottom: 0;">
                            <strong>${entry.key}</strong>
                            <span>
                                <c:choose>
                                    <c:when test="${not empty em2.key && entry.key.equals('研末') &&  em2.key>0}">
                                        [分${em2.key}包]
                                    </c:when>
                                    <c:when test="${not empty em2.key &&(xwString.indexOf(entry.key)>=0) &&  em2.key>0}">
                                        [${xwArrys[em2.key]}]
                                    </c:when>
                                    <c:when test="${not empty em2.key && !em2.key.equals('')  && !entry.key.equals('研末') &&  !(xwString.indexOf(entry.key)>=0)

                                     }">
                                        <c:if test="${!'null'.equals(em2.key) &&  em2.key>0 && em2.key!=10}">
                                            [分组${groupArrys[em2.key]}]
                                        </c:if>
                                    </c:when>
                                </c:choose>
                            </span></div>

                        <div class="form-group" style="overflow:hidden; line-height: 23px;">
                            <c:forEach varStatus="status" var="em" items="${em2.value}">

                                <script type="application/javascript">
                                    var data = {
                                        "medicineName": "${em.medicineName}",
                                        "qty": "${em.qty}",
                                        "unitLabel": "${em.unitLabel}",
                                        "copies": "${em.copies}",
                                        "medicineId": "${em.medicineId}${em.multiplexTag}",
                                        "companyId": "${em.companyId}",
                                        "qty": "${em.qty}",
                                        "rate": "${em.rate}",
                                        "unit": "${em.unit}",
                                        "useMode": "${em.useMode}",
                                        "hasUsage": "${em.hasUsage}",
                                        "useTimes": "${em.useTimes}",
                                        "usingTime": "${em.usingTime}",
                                        "useQty": "${em.useQty}",
                                        "useUnit": "${em.useUnit}",
                                        "groupIndex": "${em.groupIndex}",
                                        "specialInstructions": "${em.specialInstructions}",
                                        "standard": "${em.standard}"
                                    }
                                    createMedData(data, ${emr.id})
                                </script>


                                <div class="col-md-6 col-sm-6">
                                    <div class="pt-detail-xq1">
                                        <span>${status.count}、</span>
                                        <strong>${em.medicineName}&nbsp;${em.standard}</strong>
                                        <span tmpSize="copiesSize">${em.qty}${medicineUnits[em.unit]}
                                             <c:if test="${em.copies!=1.0&&em.copies!=0&&em.copies!=null}">
                                                x${em.copies}&nbsp;份&nbsp;
                                            </c:if>
                                        <span>
                                             <c:if test="${em.specialInstructions!=null&&em.specialInstructions!=''}">
                                                 (${em.specialInstructions})
                                             </c:if>
                                        </span>
                                    </div>
                                    <div class="pt-detail-xq2"> &nbsp;
                                        <c:if test="${em.hasUsage}">
                                            <span>${em.useTimes} &nbsp;${em.useQty}${em.useUnit}</span>
                                            <span>${em.usingTime}</span>
                                        </c:if>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>

                    </div>

                </c:forEach>
            </div>

        </c:forEach>

    </c:if>

    <%-- checkPhoto start --%>
    <c:forEach items="${emr.emrFileList}" var="emrFile">
        <c:if test="${emrFile.fileType=='Western'}">
            <div class="form-group" style="margin-left: 7%; margin-bottom: 0;">
                <a href="/fileDir/${emr.doctor.id}/${emrFile.fileName}.png" target="_blank">
                    下载附件：${emrFile.getMetaValue(emrFile.fileType)}处方签：
                    <fmt:formatDate value="${emrFile.createOn}" pattern="yyyy/MM/dd HH:mm"/></a>
                </a>
                <button type="button" class="btn btn-warning"
                        onclick="openImg('/fileDir/${emr.doctor.id}/${emrFile.fileName}.png')">查看
                </button>
            </div>
        </c:if>
    </c:forEach>

    <%-- end --%>
</div>
<div style="padding: 5px 0 5px 20px; overflow: hidden;">
    <c:set var="chineseMedicinesByUseMode" value="${emr.chinaMedicinesByUseModeAndGruopId}"/>
    <h3>处方药 - 中草药房
        <c:if test="${not empty chineseMedicinesByUseMode}">

            <button onclick="setEmrId(${emr.id},'china');fn_AddRp();" type="button" class="btn btn-success"><i
                    class="fa fa-plus"></i>
                新增为经验方
            </button>
        </c:if>


    </h3>
    <c:if test="${not empty chineseMedicinesByUseMode}">
        <c:forEach var="entry" items="${chineseMedicinesByUseMode}">
            <div class="content">
                <c:forEach varStatus="status2" var="em2" items="${entry.value}">
                    <div data-ids="${em2}" style="overflow:hidden;">
                        <div class="form-group" style="margin-bottom: 0;">
                            <strong>${entry.key}
                                <c:if test="${entry.key.equals('煎服')}">
                                    （${emr.chineseQty}副）
                                </c:if>
                            </strong>
                            <span>
                                    <c:choose>
                                        <c:when test="${entry.key.equals('贴敷') && not empty em2.key && em2.key>0}">
                                            [${xwArrys[em2.key]}]
                                        </c:when>

                                        <c:when test="${ !entry.key.equals('贴敷') && not empty em2.key && em2.key>0 && em2.key!=10}">
                                            &nbsp;
                                        </c:when>
                                    </c:choose>
                                </span>
                        </div>

                        <div class="form-group" style="overflow:hidden; line-height: 30px;">
                            <c:forEach varStatus="status" var="em" items="${em2.value}">
                                <script type="application/javascript">
                                    var data = {
                                        "medicineName": "${em.medicineName}",
                                        "qty": "${em.qty}",
                                        "unitLabel": "${em.unitLabel}",
                                        "copies": "${em.copies}",
                                        "medicineId": "${em.medicineId}${em.multiplexTag}",
                                        "companyId": "${em.companyId}",
                                        "qty": "${em.qty}",
                                        "rate": "${em.rate}",
                                        "unit": "${em.unit}",
                                        "useMode": "${em.useMode}",
                                        "hasUsage": "${em.hasUsage}",
                                        "useTimes": "${em.useTimes}",
                                        "usingTime": "${em.usingTime}",
                                        "useQty": "${em.useQty}",
                                        "useUnit": "${em.useUnit}",
                                        "groupIndex": "${em.groupIndex}",
                                        "specialInstructions": "${em.specialInstructions}",
                                        "standard": "${em.standard}"
                                    }
                                    createChinaMedData(data, ${emr.id})
                                </script>

                                <div class="col-md-6 col-sm-6">
                                    <div class="pt-detail-xq1">
                                        <span>${status.count}、</span>
                                        <strong>${em.medicineName}&nbsp;${em.standard}</strong>
                                        <span tmpSize="copiesSize">${em.qty}${medicineUnits[em.unit]}
                                        <c:if test="${!entry.key.equals('煎服')}">
                                            x${em.copies}份
                                        </c:if>
                                        </span>
                                        <span>
                                             <c:if test="${em.specialInstructions!=null&&em.specialInstructions!=''}">
                                                 (${em.specialInstructions})
                                             </c:if>
                                        </span>
                                    </div>
                                </div>

                            </c:forEach>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:forEach>
    </c:if>
    <%-- checkPhoto start --%>
    <c:forEach items="${emr.emrFileList}" var="emrFile">
        <c:if test="${emrFile.fileType=='Chinese'}">
            <div class="form-group" style="margin-left: 7%; margin-bottom: 0;">
                <a href="/fileDir/${emr.doctor.id}/${emrFile.fileName}.png" target="_blank">
                    下载附件：${emrFile.getMetaValue(emrFile.fileType)}处方签：
                    <fmt:formatDate value="${emrFile.createOn}" pattern="yyyy/MM/dd HH:mm"/></a>
                </a>
                    <%--<button type="button" class="btn btn-warning"
                            onclick="javascript:window.open('/fileDir/${emr.doctor.id}/${emrFile.fileName}.png')">查看
                    </button>--%>
                <button type="button" class="btn btn-warning"
                        onclick="openImg('/fileDir/${emr.doctor.id}/${emrFile.fileName}.png')">查看
                </button>
            </div>
        </c:if>
    </c:forEach>
    <%-- end --%>

</div>

<%-- 此处是新加的内容  --%>
<div style="padding: 5px 0 5px 20px;">
    <h3>中医理疗</h3>
    <c:if test="${therapyList.size()==0}">
        <p>无</p>
    </c:if>
    <c:if test="${therapyList.size()>0}">
        <c:forEach items="${therapyList}" var="the">
            <p>
                <span>${the.medicineName}</span>
                <span>每次${the.useQty}${therapyUnits[the.unit]}</span>
                <span>${the.copies}次</span>
            </p>
        </c:forEach>
    </c:if>
</div>


<%--检查检验--%>
<div style="padding: 5px 0 5px 20px;">
    <h3>检查/检验</h3>
    <c:if test="${adviceList.size()==0}">
        <p>无</p>
    </c:if>
    <c:if test="${adviceList.size()>0}">
        <c:forEach items="${adviceList}" var="advice">
            <p><span>检查项目：</span><span>${advice.adviceName}</span></p>
            <p><span>检查类别：</span><span>${advice.examLabName}</span></p>
            <p><span>特殊说明：</span><span>${advice.info}</span></p>
            <c:forEach items="${advice.examLabFileList}" var="file">
                <c:if test="${not empty file.fileName}">
                    <p><a href="JavaScript:void(0)"
                          onclick="downFile('${ctx}/fileDir/${doctor.id}/${file.fileName}','${file.fileName}')">下载附件:${file.fileName}</a>
                        <input type="button"
                               onclick="openImg('/fileDir/${emr.doctor.id}/${file.fileName}')"
                               value="查看"
                               class="btn btn-warning"
                               style="display: inline-block; margin-left: 15px;"></p>

                </c:if>
            </c:forEach>
        </c:forEach>
        <script>
            function downFile(fileUrl, fileName) {
                location.href = "${ctx}/patient/adviceDictFileDown?fileUrl={0}&fileName={1}".format(fileUrl, fileName)
            }
            function openFileWindow(url, type) {
                window.open(url);
            }
        </script>
    </c:if>
    <c:forEach items="${emr.emrFileList}" var="emrFile">
        <c:if test="${ emrFile.fileType=='JianYan' || emrFile.fileType=='JianCha'}">
            <div class="form-group" style="margin-left: 7%; margin-bottom: 0;">
                <a href="/fileDir/${emr.doctor.id}/${emrFile.fileName}.png" target="_blank">
                    下载附件：${emrFile.getMetaValue(emrFile.fileType)}报告：
                    <fmt:formatDate value="${emrFile.createOn}" pattern="yyyy/MM/dd HH:mm"/></a>
                </a>
                <button type="button" class="btn btn-warning"
                        onclick="openImg('/fileDir/${emr.doctor.id}/${emrFile.fileName}.png')">查看
                </button>
            </div>
        </c:if>
    </c:forEach>
</div>


<%--附件费用--%>
<div style="padding: 5px 0 5px 20px;">
    <h3>附加项目费用</h3>
    <c:if test="${extCostList.size()==0}">
        <p>无</p>
    </c:if>
    <c:if test="${extCostList.size()>0}">
        <c:forEach items="${extCostList}" var="ext">
            <p><span>${ext.className}</span><span>${ext.price}</span></p>
        </c:forEach>
    </c:if>
</div>
<%--收费金额--%>
<c:if test="${emr.doctor.name==loginDoctor.name}">
    <div style="padding: 5px 0 5px 20px;">
        <h3>收费金额:${emr.cost}</h3>
    </div>
</c:if>



