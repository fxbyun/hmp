<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<!DOCTYPE html>
<html lang="cn">
<head>
    <title>病历详情</title>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/print.lodop/LodopFuncs.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/print.js" type="js"/>
    <script type="text/javascript" src="${ctx}/assets/jquery/jquery-1.11.2.min.js"></script>

    <object id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0
            style="position:absolute;left:0px;top:-10px;"></object>
    <object id="LODOP_EM" TYPE="application/x-print-lodop" width=0 height=0
            style="position:absolute;left:0px;top:-10px;"></object>
    <script type="application/javascript">

        //打印处方签
        function printA5or80Cfq(type) {
            if (type == "A5打印") {
                YJZ_Printer.printUrl('../pub/printRpA5/{0}'.format(emrId));
//                判断是否还需要独立打印
                $.postJSON("/pub/isNeedAlonePrint", {emrId: emrId}, function (ret) {
                    if (ret.success) {
                        YJZ_Printer.printUrl('../pub/printRpA5/{0}?type=alone'.format(emrId));
                        Alert.success("打印成功,本次已启用分页打印!请勿重复点击,请稍后!");
                    }
                });

            } else {
                YJZ_Printer.print80Url('../pub/printRp/{0}'.format(emrId));
                $.postJSON("/pub/isNeedAlonePrint", {emrId: emrId}, function (ret) {
                    if (ret.success) {
                        YJZ_Printer.print80Url('../pub/printRp/{0}?type=alone'.format(emrId));
                        Alert.success("打印成功,本次已启用分页打印!请勿重复点击,请稍后!");
                    }

                });
            }
        }

        //打印就诊单
        function printA5oOr80Jzd(type) {
            if (type == "A5打印") {
                YJZ_Printer.printUrl('../pub/printDiagnosisA5/${emr.id}');//就诊单默认还是80打印机
            } else {
                YJZ_Printer.print80Url('../pub/printDiagnosis/${emr.id}');
            }

        }

        var medDataSum = {};
        var medChinaDataSum = {};
        var addEmrId = "";
        var addType = "";

        function setEmrId(id, addType1) {
            addEmrId = "emrId" + id;
            addType = addType1;
//
        }

        function createMedData(data, emrId) {
            var tag = "medicineTag_" + data.medicineId;

            var ele = '<span class="tag"  id="' + tag + '">' + data.medicineName + '&nbsp;' + data.qty + data.unitLabel + 'x' + data.copies +
                    '<input type="hidden" name="medicineIds" value="' + data.medicineId + '" />' +
                    '<input type="hidden" name="companyIds" value="' + data.companyId + '" />' +
                    '<input type="hidden" name="medicineQty" value="' + data.qty + '" />' +
                    '<input type="hidden" name="medicineRate" value="' + data.rate + '" />' +
                    '<input type="hidden" name="medicineUnit" value="' + data.unit + '" /> ' +
                    '<input type="hidden" name="medicineCopies" value="' + data.copies + '" /> ' +
                    '<input type="hidden" name="medicineUseModes" value="' + data.useMode + '" /> ' +
                    '<input type="hidden" name="medicineHasUsages" value="' + data.hasUsage + '" /> ' +
                    '<a href="javascript:" onclick="fn_ShowEditMedicine(' + data.medicineId + ')"><i class="fa fa-cog"></i></a> ' +
                    '<a href="javascript:" onclick="fn_RemoveElement($(this).parent())"><i class="fa fa-times"></i></a> ' +
                    '</span>';
            medDataSum["emrId" + emrId] += ele;
        }

        function createChinaMedData(data, emrId) {
            var tag = "medicineTag_" + data.medicineId;

            var ele = '<span class="tag"  id="' + tag + '">' + data.medicineName + '&nbsp;' + data.qty + data.unitLabel + 'x' + data.copies +
                    '<input type="hidden" name="medicineIds" value="' + data.medicineId + '" />' +
                    '<input type="hidden" name="companyIds" value="' + data.companyId + '" />' +
                    '<input type="hidden" name="medicineQty" value="' + data.qty + '" />' +
                    '<input type="hidden" name="medicineRate" value="' + data.rate + '" />' +
                    '<input type="hidden" name="medicineUnit" value="' + data.unit + '" /> ' +
                    '<input type="hidden" name="medicineCopies" value="' + data.copies + '" /> ' +
                    '<input type="hidden" name="medicineUseModes" value="' + data.useMode + '" /> ' +
                    '<input type="hidden" name="medicineHasUsages" value="' + data.hasUsage + '" /> ' +
                    '<a href="javascript:" onclick="fn_ShowEditMedicine(' + data.medicineId + ')"><i class="fa fa-cog"></i></a> ' +
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
</head>
<body class="case inquire">
<div class="case-content">
    <div class="container">
        <div class="review">
            <div class="review-item">
                <h3>病历基本信息：
                    <a href="${ctx}/diagnos/${emr.id}" class="btn btn-info">添加新病历</a>
                </h3>
            </div>
            <div class="row">
                <div class="col-md-11 col-md-offset-1 col-sm-11">
                    <table style="font-size: 18px">
                        <tr style="height: 40px">
                            <td>诊疗医生：</td>
                            <td width="120px" style="color:#999">${emr.doctorName}</td>
                            <td colspan="2">就诊时间：</td>
                            <td colspan="3" style="color:#999"><fmt:formatDate value="${emr.createOn}"
                                                                               pattern="yyyy/MM/dd HH:mm" /></td>
                        </tr>
                        <tr style="height: 40px">
                            <td>患者姓名：</td>
                            <td style="color:#999">${emr.patient.name}</td>
                            <td>性别：</td>
                            <td width="50px" style="color:#999">${genderMap[emr.patient.gender]}</td>
                            <td>年龄：</td>
                            <td width="50px" style="color:#999">${emr.patient.age}</td>
                            <td>联系方式：</td>
                            <td style="color:#999">${emr.patient.mobile}</td>
                        </tr>
                        <tr style="height: 40px">
                            <td>地址：</td>
                            <td colspan="7" style="color:#999">${emr.patient.address}</td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
        <div class="review">
            <div class="review-item">
                <h3>诊疗信息：</h3>
            </div>
            <div class="history-report">
                <div class="talk-content">
                    <h4>患者主诉：</h4>

                    <p><c:if test="${empty emr.mainSuit}">无</c:if> <c:if
                            test="${not empty emr.mainSuit}">${emr.mainSuit.replace(";","")}</c:if></p>
                    <h4>生命体症</h4>

                    <p>
                        <c:forEach items="${emr.vitalSignList}" var="vs" varStatus="status">
                            ${vitalSignLabels[vs.type]}${vs.value}${vitalSignUnits[vs.type]}<c:if
                                test="${not empty vitalSignUnits[vs.type]}">;&nbsp;</c:if>
                        </c:forEach>
                    </p>
                    <h4>诊断结果：</h4>

                    <p><c:if test="${empty emr.diagnosisResult}">无</c:if>
                        <c:if test="${not empty emr.diagnosisResult}">${emr.diagnosisResult}</c:if></p>
                    <h4>处方药-西药及中成药房：
                        <button onclick="setEmrId(${emr.id},'western');fn_AddRp();" type="button"
                                class="btn btn-success"><i class="fa fa-plus"></i>
                            新增为经验方
                        </button>
                    </h4>
                    <c:set var="westernMedicinesByUseMode" value="${emr.westernMedicinesByUseMode}" />
                    <c:if test="${empty westernMedicinesByUseMode}"><p>无</p></c:if>
                    <c:if test="${not empty westernMedicinesByUseMode}">
                        <c:forEach var="entry" items="${westernMedicinesByUseMode}">
                            <p class="title">${entry.key}</p>
                            <c:forEach varStatus="status" var="em" items="${entry.value}">
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
                                        "hasUsage": "${em.hasUsage}"

                                    }
                                    createMedData(data, ${emr.id})
                                </script>
                                <p class="row">${status.count}、${em.medicineName}&nbsp;&nbsp;${em.qty}${medicineUnits[em.unit]}x${em.copies}&nbsp;份</p>
                                <c:if test="${em.hasUsage}">
                                    <p class="row row2">${em.useTimes}&nbsp;每次${em.useQty}${em.useUnit}&nbsp;${em.usingTime}</p>
                                </c:if>
                                <%--每次${em.medicine.useQty}${medicineUnits[em.medicine.useUnit]}&nbsp;&nbsp;--%>
                            </c:forEach>
                        </c:forEach>
                    </c:if>

                    <h4>处方药-中草药房：
                        <button onclick="setEmrId(${emr.id},'china');fn_AddRp();" type="button" class="btn btn-success">
                            <i class="fa fa-plus"></i>
                            新增为经验方
                        </button>
                    </h4>
                    <c:set var="chineseMedicinesByUseMode" value="${emr.chineseMedicinesByUseMode}" />
                    <c:if test="${empty chineseMedicinesByUseMode}"><p>无</p></c:if>
                    <c:if test="${not empty chineseMedicinesByUseMode}">
                        <c:forEach var="entry" items="${chineseMedicinesByUseMode}">
                            <p class="title">${entry.key}<c:if test="${entry.key=='煎服'}">
                                共(${emr.chineseQty})副
                                </c:if></p>
                            <c:forEach varStatus="status" var="em" items="${entry.value}">
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
                                        "hasUsage": "${em.hasUsage}"

                                    }
                                    createChinaMedData(data, ${emr.id})
                                </script>
                                <p class="row">${status.count}、${em.medicineName}&nbsp;&nbsp;${em.qty}${medicineUnits[em.unit]}</p>
                                <%--<c:if test="${em.hasUsage}"><br />每日${em.medicine.useTimes}次&nbsp;&nbsp;每次${em.medicine.useQty}${medicineUnits[em.medicine.useUnit]}&nbsp;&nbsp;${em.medicine.usingTime}</c:if>--%>
                            </c:forEach>
                        </c:forEach>
                    </c:if>
                    <h4>收费金额：${emr.cost}</h4>
                </div>
            </div>
        </div>
        <c:if test="${not empty emr.evaluateList}">
            <div class="review">
                <div class="review-item">
                    <h3>患者咨询：</h3>
                </div>
                <div class="review-item-content row">
                    <c:forEach items="${emr.evaluateList}" var="eva">
                        <c:choose>
                            <c:when test="${eva.type eq 'ELE'}">
                                <h4>评星：${eva.grade}星</h4>
                                <h4>评价：${eva.content}</h4>
                            </c:when>
                            <c:when test="${eva.type eq 'DTO'}">
                                <div class="review-content col-md-9 col-sm-9">
                                    <p class="text-left"><span>我</span>回复<span>${emr.patientName}</span>：${eva.content}
                                    </p>
                                </div>
                                <div class="col-md-3 col-sm-3 text-right">
                                    <span><fmt:formatDate value="${eva.createTime}" type="date"
                                                          pattern="yyyy/MM/dd h:m" /></span>
                                </div>
                            </c:when>
                            <c:when test="${eva.type eq 'OTD'}">
                                <div class="review-content col-md-9 col-sm-9">
                                    <p class="text-left"><span>${emr.patientName}</span>回复<span>我</span>：${eva.content}
                                    </p>
                                </div>
                                <div class="col-md-3 col-sm-3 text-right">
                                    <span><fmt:formatDate value="${eva.createTime}" type="date"
                                                          pattern="yyyy/MM/dd h:m" /></span>
                                </div>
                            </c:when>
                        </c:choose>
                    </c:forEach>
                    <div class="form-group text-right">
                        <form class="form-inline" action="${ctx}/emrEval" method="post">
                            <input type="hidden" name="emrId" value="${emr.id}" />
                            <input type="hidden" name="patientUid" value="${emr.patientUid}" />
                            <input type="hidden" name="patientName" value="${emr.patientName}" />
                            <input type="hidden" name="doctorId" value="${emr.doctor.id}" />
                            <input type="hidden" name="doctorName" value="${emr.doctorName}" />
                            <input type="text" class="form-control" name="content" style="width: 89%"
                                   id="content${emr.id}" placeholder="回复" />
                            <button class="btn btn-info" type="submit">回复</button>
                        </form>
                    </div>
                </div>
            </div>
        </c:if>
        <div class="text-center" style="padding: 10px 0 10px">
            <button type="button" onclick="history.back()" class="btn btn-info"><i class="fa fa-chevron-left"></i> 返回
            </button>
            &nbsp;&nbsp;
            <button type="button" onclick="printA5or80Cfq('${emr.doctor.printType}')" class="btn btn-primary"><i
                    class="fa fa-print"></i> 打印处方笺
            </button>
            &nbsp;&nbsp;
            <button type="button" onclick="printA5oOr80Jzd('${emr.doctor.printType}')" class="btn btn-primary"><i
                    class="fa fa-print"></i> 打印就诊单
            </button>
        </div>
    </div>
</div>
</body>
</html>