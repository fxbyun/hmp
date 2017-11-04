<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/application.js" type="js"/>
<script type="text/javascript">
    $(function () {
        $('input[name="month"]').change(function () {
            var month = $('input[name="month"]:checked').val();
            fn_LoadPatientEmrList(0, month)
        });
    });

    var medDataSum = {};
    var medChinaDataSum = {};
    var addEmrId = "";
    var addType = "";

    function setEmrId(id, addType1) {
        addEmrId = "emrId" + id;
        addType = addType1;
    }
    //#
    function createMedData(data, emrId) {
        var tag = "rpMedicineTag_" + data.medicineId;
        if (data.groupIndex === undefined)data.groupIndex = "0";
        if (data.groupIndex === null)data.groupIndex = "0";
        if (data.groupIndex == "")data.groupIndex = "0";
        /// alert(data.qty+data.rate+data.unit+data.useMode+":"+data.useTimes+data.useQty);

        var ele = '<span class="tag" ' + ' id="{0}">{1}&nbsp;{2}{3}x{4}份'.format(tag, data.medicineName, data.qty, data.unitLabel, data.copies) +
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

        var ele = '<span class="tag" ' + ' id="{0}">{1}&nbsp;{2}{3}x{4}份'.format(tag, data.medicineName, data.qty, data.unitLabel, data.copies) +
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


</script>
<div class="panel-body text-center">
    <div class="btn-group" data-toggle="buttons">
        <c:forEach var="m" varStatus="status" items="${months}">
            <label class="btn btn-success <c:if test='${month==m}'>active</c:if>">
                <input type="radio" name="month" value="<fmt:formatDate value='${m}' pattern='yyyy/MM'/>"
                       autocomplete="off"
                       <c:if test='${month==m}'>checked</c:if>>
                <fmt:formatDate value='${m}' pattern='yy年MMM'/>
            </label>
        </c:forEach>
    </div>
    <button class="btn btn-warning " onclick="fn_Old_LoadPatientEmrList();return false">查看旧数据</button>
    <%--这里只能是span等，但不能是button--%>
    <span class="btn btn-default pull-right" style="margin-right: 48px; margin-top: 10px;"
          onclick="buyMedicDetails(${patient.id})">查看购药记录
    </span>
</div>
<c:if test="${empty emrPage.content}"><p style="text-indent: 30px;">无病历记录</p></c:if>
<c:forEach var="emr" items="${emrPage.content}">
    <div class="history-report">
        <h3><fmt:formatDate value="${emr.createOn}" pattern="yyyy/MM/dd HH:mm"/>&nbsp;
                ${emr.doctor.businessAddr}&nbsp;${emr.doctor.outpatientService} &nbsp;${emr.doctorName} &nbsp;医生</h3>

        <div class="talk-content">
            <h4>患者主诉</h4>

            <p><c:if test="${empty emr.mainSuit}">无</c:if> <c:if
                    test="${not empty emr.mainSuit}">${emr.mainSuit.replace(";","")}</c:if></p>

            <c:if test="${not empty emr.remarks}">
                其他:<p>${emr.remarks}</p>
            </c:if>
 
            <h4>生命体症</h4>

            <p>
                <c:forEach items="${emr.vitalSignList}" var="vs" varStatus="status">
                    <c:if test="${not empty vs.value}">
                        <c:if test="${vitalSignLabels[vs.type]=='其他'}">
                            ${vs.value}${vitalSignUnits[vs.type]}<c:if
                                test="${not empty vitalSignUnits[vs.type]}">;&nbsp;</c:if>
                        </c:if>

                        <c:if test="${vitalSignLabels[vs.type]!='其他'}">
                            ${vitalSignLabels[vs.type]}${vs.value}${vitalSignUnits[vs.type]}<c:if
                                test="${not empty vitalSignUnits[vs.type]}">;&nbsp;</c:if>
                        </c:if>

                    </c:if>
                </c:forEach>
                <c:if test="${empty emr.getVitalSignText()}">无</c:if>
            </p>
            <h4>诊断结果</h4>

            <p><c:if test="${empty emr.diagnosisResult}">无</c:if>
                <c:if test="${not empty emr.diagnosisResult}">${emr.diagnosisResult}</c:if></p>

            <c:if test="${emr.doctor.id == user.id}">
                <h4>处方---西药及中成药房 &nbsp;&nbsp;<c:if test="${refer && not empty emr.westernItems}">
                    <button type="button" onclick="fn_ReferEmrWesternItems(${emr.id})" class="btn btn-success"><i
                            class="fa fa-sm fa-external-link-square"></i> 使用该药方
                    </button>
                    <button onclick="setEmrId(${emr.id},'western');fn_AddRp();" type="button" class="btn btn-success"><i
                            class="fa fa-plus"></i>
                        新增为经验方
                    </button>
                </c:if></h4>
                <%--<c:set var="westernMedicinesByUseMode" value="${emr.westernMedicinesByUseMode}" />--%>
                <c:set var="xwString" value="${'针灸, 拔罐, 按摩,贴敷'}"/>
                <c:set var="westernMedicinesByUseMode" value="${emr.westernMedicinesByUseModeAndGruopId}"/>
                <c:if test="${empty westernMedicinesByUseMode}"><p>无</p></c:if>
                <c:if test="${not empty westernMedicinesByUseMode}">
                    <c:forEach var="entry" items="${westernMedicinesByUseMode}">
                        <c:forEach varStatus="status2" var="em2" items="${entry.value}">
                            <p class="title" data-ids="${em2}">
                                <b class="title" data-id="${entry}">${entry.key}</b>
                                <c:choose>
                                    <c:when test="${entry.key.equals('研末') && not empty em2.key && em2.key>0}">
                                        [分${em2.key}包]
                                    </c:when>
                                    <c:when test="${(xwString.indexOf(entry.key)>=0) && not empty em2.key && em2.key>0}">
                                        [${xwArrys[em2.key]}]
                                    </c:when>
                                    <c:when test="${not empty em2.key && !em2.key.equals('')  && !entry.key.equals('研末') &&  !(xwString.indexOf(entry.key)>=0)

                                     }">
                                        <c:if test="${!'null'.equals(em2.key) &&  em2.key>0 && em2.key!=10}">
                                            [分组${groupArrys[em2.key]}]
                                        </c:if>
                                    </c:when>
                                </c:choose>
                            </p>
                            <c:forEach varStatus="status" var="em" items="${em2.value}">
                                <script type="application/javascript">
                                    var data = {
                                        "medicineName": "${em.medicineName}",
                                        "qty": "${em.qty}",
                                        "unitLabel": "${em.unitLabel}",
                                        "copies": "${em.copies}",
                                        "medicineId": "${em.medicineId}",
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

                                <div class="row" style="padding-left: 50px;">
                                    <p>${status.count}、<strong>${em.medicineName}</strong>
                                        &nbsp;${em.standard}&nbsp;&nbsp;${em.qty}${medicineUnits[em.unit]}
                                        <c:if test="${em.copies!=1.0&&em.copies!=0&&em.copies!=null}">
                                            x${em.copies}&nbsp;份&nbsp;
                                        </c:if>
                                        <c:if test="${em.specialInstructions!=null&&em.specialInstructions!=''}">
                                            (${em.specialInstructions})
                                        </c:if>
                                    </p>
                                    <c:if test="${em.hasUsage}">
                                        <p>${em.useTimes}&nbsp;每次${em.useQty}${em.useUnit}&nbsp;${em.usingTime}</p>
                                    </c:if>
                                </div>

                                <%--每次${em.medicine.useQty}${medicineUnits[em.medicine.useUnit]}&nbsp;&nbsp;--%>
                            </c:forEach>
                        </c:forEach>
                    </c:forEach>
                </c:if>
                <c:forEach items="${emr.emrFileList}" var="emrFile">
                    <c:if test="${emrFile.fileType=='Western'}">
                        <p>
                            <a target="_blank"
                               href="/fileDir/${emr.doctor.id}/${emrFile.fileName}.png">下载附件：${emr.getMetaValue(emrFile.fileType)}处方笺：
                                <fmt:formatDate value="${emrFile.createOn}" pattern="yyyy/MM/dd HH:mm"/></a>
                            <button type="button" class="btn btn-warning"
                                    onclick="openImg('/fileDir/${emr.doctor.id}/${emrFile.fileName}.png')">
                                查看
                            </button>
                        </p>
                    </c:if>
                </c:forEach>
                <h4>处方---中草药房 &nbsp;&nbsp;<c:if test="${refer && not empty emr.chineseItems}">
                    <button type="button" onclick="fn_ReferEmrChineseItems(${emr.id})" class="btn btn-success"><i
                            class="fa fa-sm fa-external-link-square"></i> 使用该药方
                    </button>
                    <button onclick="setEmrId(${emr.id},'china');fn_AddRp();" type="button" class="btn btn-success"><i
                            class="fa fa-plus"></i> 新增为经验方
                    </button>
                </c:if></h4>
                <div style="width: 50%;overflow: hidden;">


                    <c:set var="chineseMedicinesByUseMode" value="${emr.chinaMedicinesByUseModeAndGruopId}"/>
                    <c:if test="${empty chineseMedicinesByUseMode}"><p>无</p></c:if>
                    <c:if test="${not empty chineseMedicinesByUseMode}">
                        <c:forEach var="entry" items="${chineseMedicinesByUseMode}">

                            <c:forEach varStatus="status2" var="em2" items="${entry.value}">
                                <p class="title" data-ids="${em2}">
                                    <b class="title">
                                            ${entry.key}
                                        <c:if test="${entry.key=='煎服'}">
                                            共(${emr.chineseQty})副
                                        </c:if>
                                    </b>
                                    <c:choose>
                                        <c:when test="${(xwString.indexOf(entry.key)>=0) && not empty em2.key && em2.key>0}">
                                            [${xwArrys[em2.key]}]
                                        </c:when>
                                        <c:when test="${!(xwString.indexOf(entry.key)>=0) && not empty em2.key && em2.key>0 && em2.key!=10}">
                                            &nbsp;
                                        </c:when>
                                    </c:choose>
                                </p>
                                <c:forEach varStatus="status" var="em" items="${em2.value}">
                                    <script type="application/javascript">
                                        var data = {
                                            "medicineName": "${em.medicineName}",
                                            "qty": "${em.qty}",
                                            "unitLabel": "${em.unitLabel}",
                                            "copies": "${em.copies}",
                                            "medicineId": "${em.medicineId}",
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

                                    <c:choose>
                                        <c:when test="${entry.key=='煎服'&&!status.last}">
                                            <p style="width: 50%; float: left">${status.count}、<strong>${em.medicineName}</strong><span>&nbsp;${em.standard}&nbsp;&nbsp;${em.qty}${medicineUnits[em.unit]}&nbsp;</span>
                                            </p>
                                            <%--<div style="padding-left: 50px;float: left;">
                                                <p>${status.count}、<strong>${em.medicineName}</strong><span>&nbsp;${em.standard}&nbsp;&nbsp;${em.qty}${medicineUnits[em.unit]}&nbsp;</span></p>
                                            </div>--%>
                                        </c:when>
                                        <%--因为煎服样式问题：条件是最后一个是奇数时不浮动--%>
                                        <c:when test="${status.last&&status.count%2!=0}">
                                            <p style="width: 50%; float: none;">${status.count}、<strong>${em.medicineName}</strong><span>&nbsp;${em.standard}&nbsp;&nbsp;${em.qty}${medicineUnits[em.unit]}&nbsp;</span>
                                            </p>
                                            <%--<div style="padding-left: 50px;float: left;">
                                                <p>${status.count}、<strong>${em.medicineName}</strong><span>&nbsp;${em.standard}&nbsp;&nbsp;${em.qty}${medicineUnits[em.unit]}&nbsp;</span></p>
                                            </div>--%>
                                        </c:when>

                                        <c:otherwise>
                                            <div style="padding-left: 50px;">
                                                <p>${status.count}、<strong>${em.medicineName}</strong><span>&nbsp;${em.standard}&nbsp;&nbsp;${em.qty}${medicineUnits[em.unit]}&nbsp;</span>
                                                </p>
                                            </div>
                                        </c:otherwise>
                                    </c:choose>


                                    <c:if test="${entry.key!='煎服'}">
                                        <c:if test="${em.copies!=1.0&&em.copies!=0&&em.copies!=null}">
                                            x${em.copies}&nbsp;份&nbsp;
                                        </c:if>
                                    </c:if>
                                    <c:if test="${em.specialInstructions!=null&&em.specialInstructions!=''}">
                                        (${em.specialInstructions})
                                    </c:if>
                                    </p>
                                    <%--<c:if test="${em.hasUsage}"><br />每日${em.medicine.useTimes}次&nbsp;&nbsp;每次${em.medicine.useQty}${medicineUnits[em.medicine.useUnit]}&nbsp;&nbsp;${em.medicine.usingTime}</c:if>--%>
                                </c:forEach>
                            </c:forEach>
                        </c:forEach>

                    </c:if>
                    <c:forEach items="${emr.emrFileList}" var="emrFile">
                        <c:if test="${ emrFile.fileType=='Chinese'}">
                            <p>
                                <a target="_blank"
                                   href="/fileDir/${emr.doctor.id}/${emrFile.fileName}.png">下载附件：${emr.getMetaValue(emrFile.fileType)}处方笺：
                                    <fmt:formatDate value="${emrFile.createOn}" pattern="yyyy/MM/dd HH:mm"/></a>
                                <button type="button" class="btn btn-warning"
                                        onclick="openImg('/fileDir/${emr.doctor.id}/${emrFile.fileName}.png')">
                                    查看
                                </button>
                            </p>
                        </c:if>
                    </c:forEach>
                </div>
            </c:if>
                <%--中医理疗--%>
            <div>
                <h4>中医理疗</h4>
                <c:if test="${therapyMap[emr]==null}">
                    <p>无</p>
                </c:if>
                <c:if test="${therapyMap[emr].size()>0}">
                    <c:forEach items="${therapyMap[emr]}" var="the">
                        <p>
                            <span>${the.medicineName}</span>
                            <span>每次${the.useQty}${therapyUnits[the.unit]}</span>
                            <span>${the.copies}次</span>
                        </p>
                    </c:forEach>
                </c:if>
            </div>

                <%--检查检验--%>
            <div>
                <h4>检查/检验</h4>
                <c:if test="${adviceDictMap[emr].size()==0}">
                    <p>无</p>
                </c:if>
                <c:if test="${adviceDictMap[emr].size()>0}">
                    <c:forEach items="${adviceDictMap[emr]}" var="advice">
                        <p><span>检查项目：</span><span>${advice.adviceName}</span></p>
                        <p><span>检查类别：</span><span>${advice.examLabName}</span></p>
                        <p><span>特殊说明：</span><span>${advice.info}</span></p>
                        <c:forEach items="${advice.examLabFileList}" var="file">
                            <c:if test="${not empty file.fileName}">
                                <p><a href="JavaScript:void(0)"
                                      onclick="downFile('${ctx}/fileDir/${doctorMap[emr].id}/${file.fileName}','${file.fileName}')">下载附件:${file.fileName}</a>
                                    <input type="button"
                                           onclick="openFileWindow('${ctx}/fileDir/${doctorMap[emr].id}/${file.fileName}','${file.types}')"
                                           value="查看"
                                           class="btn btn-warning"
                                           style="display: inline-block; margin-left: 15px; height:30px;"></p>
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
                        <p>
                            <a target="_blank"
                               href="/fileDir/${emr.doctor.id}/${emrFile.fileName}.png">下载附件：${emr.getMetaValue(emrFile.fileType)}报告：
                                <fmt:formatDate value="${emrFile.createOn}" pattern="yyyy/MM/dd HH:mm"/></a>
                            <button type="button" class="btn btn-warning"
                                    onclick="openImg('/fileDir/${emr.doctor.id}/${emrFile.fileName}.png')">
                                查看
                            </button>
                        </p>
                    </c:if>
                </c:forEach>
            </div>

                <%--附件费用--%>
            <div>
                <h4>附加项目费用</h4>
                <c:if test="${extCostMap[emr].size()==0}">
                    <p>无</p>
                </c:if>
                <c:if test="${extCostMap[emr].size()>0}">
                    <c:forEach items="${extCostMap[emr]}" var="ext">
                        <p><span>${ext.className}</span><span>${ext.price}</span></p>
                    </c:forEach>
                </c:if>
            </div>
                <%--当登录医生是该病历的医生才显示金额--%>
            <c:if test="${emr.doctorName==loginDoctor.name}">
                <h4>收费金额：${emr.cost}</h4>
            </c:if>

        </div>
    </div>
</c:forEach>
<div class="text-center">
    <c:if test="${emrPage.number > 0}">
        <button type="button" onclick="fn_LoadPatientEmrList(${emrPage.number - 1},'<fmt:formatDate value='${month}'
                                                                                                    pattern='yyyy/MM'/>')"
                class="btn btn-success"><i class="fa fa-chevron-left"></i>上一页
        </button>
    </c:if>
    <c:if test="${emrPage.number + 1 < emrPage.totalPages}">
        <button type="button" onclick="fn_LoadPatientEmrList(${emrPage.number + 1},'<fmt:formatDate value='${month}'
                                                                                                    pattern='yyyy/MM'/>')"
                class="btn btn-success"><i class="fa fa-chevron-right"></i>下一页
        </button>
    </c:if>
    &nbsp;&nbsp;&nbsp;&nbsp;
    第&nbsp;${emrPage.number + 1}&nbsp;页/共&nbsp;${emrPage.totalPages}&nbsp;页
</div>
<br/>