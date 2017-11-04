<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<!DOCTYPE html>
<html lang="cn">
<head>
    <title>修改病历</title>
    <script type="text/javascript">
        $(function () {
            $("#nav-diagnosis").addClass("active");

            var bannerSlider = new Slider($('#banner_tabs'), {
                time: 5000,
                delay: 400,
                event: 'hover',
                auto: true,
                mode: 'fade',
                controller: $('#bannerCtrl'),
                activeControllerCls: 'active'
            });
            $('#banner_tabs .flex-prev').click(function() {
                bannerSlider.prev()
            });
            $('#banner_tabs .flex-next').click(function() {
                bannerSlider.next()
            });
        });
    </script>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/print.lodop/LodopFuncs.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/print.js" type="js"/>
    <script type="text/javascript" src="${ctx}/assets/scripts/slider.js"></script>
    <script type="application/javascript" src="http://localhost:8000/CLodopfuncs.js?priority=1"></script>
    <object id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0
            style="position:absolute;left:0px;top:-10px;"></object>
    <object id="LODOP_EM" TYPE="application/x-print-lodop" width=0 height=0
            style="position:absolute;left:0px;top:-10px;"></object>


    <script type="application/javascript">
        //打印处方签
        function printA5or80Cfq(type) {
            if (type == "A5打印") {
                var printModel = '${doctor.printModel}';
                if (printModel != "预览打印") {
                    YJZ_Printer.printUrl('../pub/printRpA5/{0}'.format("${emr.id}"));
//                判断是否还需要独立打印
                    $.postJSON("/pub/isNeedAlonePrint", {emrId: "${emr.id}"}, function (ret) {
                        if (ret.success) {
                            YJZ_Printer.printUrl('../pub/printRpA5/{0}?type=alone'.format("${emr.id}"));
                            Alert.success("打印成功,本次已启用分页打印!请勿重复点击,请稍后!");
                        }
                    });
                } else {

                    var idex = layer.open({
                        title: "预览打印",
                        content: ("${ctx}/pub/printRpA5/{0}?printModel={1}".format("${emr.id}", printModel)),
                        area: ['520px', '600px'],
                        btn: ["关闭"],
                        yes: function () {
                            layer.close(idex);
                        },
                        scrollbar: false,
                        type: 2,
                        end: function () {
                            //                判断是否还需要独立打印
                            $.postJSON("${ctx}/pub/isNeedAlonePrint", {emrId: "${emr.id}"}, function (ret) {
                                if (ret.success) {
                                    var idexAlon = layer.open({
                                        title: "预览打印",
                                        content: ("${ctx}/pub/printRpA5/{0}?printModel={1}&type=alone".format("${emr.id}", printModel)),
                                        area: ['520px', '600px'],
                                        btn: ["关闭"],
                                        yes: function () {
                                            layer.close(idexAlon);
                                        },
                                        scrollbar: false,
                                        type: 2,
                                        end: function () {
                                            Alert.success("独立打印执行完毕!");
                                        }
                                    });
                                    Alert.success("打印成功,本次已启用分页打印!请勿重复点击,请稍后!");
                                }
                            });
                        }
                    });

                }




            } else {
                YJZ_Printer.print80Url('../pub/printRp/{0}'.format("${emr.id}"));
                $.postJSON("/pub/isNeedAlonePrint", {emrId: "${emr.id}"}, function (ret) {
                    if (ret.success) {
                        YJZ_Printer.print80Url('../pub/printRp/{0}?type=alone'.format("${emr.id}"));
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
    </script>
    <style>
        .btn-success{
            background-color: #218a3f;
        }
    </style>
</head>
<body>
<div class="container electronic">
    <form:form action="/diagnosis/save" method="post" modelAttribute="emr">
        <form:hidden path="id"/>
        <div class="intro-sign">
            <div class="row">
                <div class="col-md-3 col-sm-3">
                    <label class="word-dis">姓 名:</label>
                    <input type="hidden" name="patient.id" value="${patient.id}"/>
                    <input type="hidden" name="patientUid" value="${patient.uid}"/>
                    <input type="hidden" name="patientName" value="${patient.name}"/>
                    <span>${patient.name}</span>
                </div>
                <div class="col-md-3 col-sm-3">
                    <div class="form-group">
                        <label class="word-dis">年 龄:</label>
                        <span>${patient.age}</span>
                    </div>
                </div>
                <div class="col-md-3 col-sm-3">
                    <label class="word-dis">性别:</label>
                    <span>${genderMap[patient.gender]}</span>
                </div>
            </div>
            <div class="row">
                <div class="col-md-3 col-sm-3">
                    <div class="form-group">
                        <label>手机号:</label>
                        <span>${patient.mobile}</span>
                    </div>
                </div>
                <div class="col-md-3 col-sm-3">
                    <div class="form-group">
                        <label>身份证:</label>
                        <span>${patient.sfId}</span>
                    </div>
                </div>
                <div class="col-md-4 col-sm-4">
                    <div class="form-group">
                        <label class="word-dis">地址:</label>
                        <span>${patient.province}${patient.city}${patient.area}${patient.address}</span>
                    </div>
                </div>
                <div class="col-md-2 col-sm-2 text-right">
                </div>
            </div>
            <div class="row patient-label">
                <div class="col-md-1 col-sm-1">
                    <p style="font-size: 14px;">病史标签:</p>
                </div>
                <div id="divPatientTags" class="col-md-8 col-sm-8 labels-box">
                    <c:forEach var="tag" items="${patient.patientTagList}">
                        <span class="tag">${tag.name}</span>
                    </c:forEach>
                </div>
            </div>
        </div>
        <div class="doctors-action">
            <%--<div class="container">--%>
                <%--<ul class="nav nav-tabs sections-list" id="tabsDepartment">--%>
                    <%--<c:forEach varStatus="status" var="dept" items="${departments}">--%>
                        <%--<li <c:if test="${emr.departmentId == dept.id}">class="active"</c:if>><a href="#" data-toggle="tab">${dept.name}</a></li>--%>
                    <%--</c:forEach>--%>
                <%--</ul>--%>

            <%--</div>--%>
            <div id="divDiagnosos">
                <div class="diagnose" style="margin-top: 10px">
                    <p>患者主诉</p>
                    <div id="divSymptom" class="label-box"><c:forEach var="tag" items="${emr.mainSuitList}">
                        <span class="tag"><input name="mainSuits" value="${tag}" type="hidden">${tag}</span>
                    </c:forEach>
                    </div>
                </div>
                <div class="form-inline">
                    <b style="font-size: 20px">生命体征：</b>
                    <c:forEach items="${emr.vitalSignList}" var="vs" varStatus="status">
                        <%--<div class="form-group">--%>
                        <c:if test="${not empty vitalSignLabels[vs.type]}">
                            <label for="vitalSign${status.index}">${vitalSignLabels[vs.type]}</label>
                        </c:if>
                        <%--<input type="hidden" name="vitalSignList[${status.index}].type" value="${vs.type}"/>--%>

                        <input id="vitalSign${status.index}" type="text" name="vitalSignList[${status.index}].value"
                               value='<fmt:formatNumber value="${vs.value}" pattern="###.#" />' readonly class="form-control"/>
                        <c:if test="${not empty vitalSignUnits[vs.type]}">
                            <label style="margin-right: 13px;">${vitalSignUnits[vs.type]}</label>
                        </c:if>
                        <%--</div>--%>
                    </c:forEach>
                </div>
                <div class="diagnose">
                    <p>诊断结果</p>
                    <div id="divDiagnosis" class="label-box">
                        <c:forEach var="tag" items="${emr.diagnosisList}">
                            <span class="tag"><input name="diagnosisResult" value="${tag.name}" type="hidden">${tag.name}</span>
                        </c:forEach>
                    </div>
                </div>
                <div class="diagnose">
                    <p>处方---西药及中成药房</p>
                    <div id="divWesternItems" class="label-box">
                        <c:forEach var="item" items="${emr.westernItems}">
                            <span class="tag">${item.medicineName}&nbsp;${item.qty}${medicineUnits[item.unit]}x${item.copies}份</span>
                        </c:forEach>
                    </div>
                </div>
                <div class="diagnose">
                    <h4 class="form-inline" style="margin-bottom: 10px">处方---中草药房&nbsp;&nbsp;${emr.chineseQty}副
                    </h4>
                    <div id="divChineseItems" class="label-box">
                        <c:forEach var="item" items="${emr.chineseItems}">
                            <span class="tag">${item.medicineName}&nbsp;${item.qty}${medicineUnits[item.unit]}</span>
                        </c:forEach>
                    </div>
                </div>
                <div class="diagnose">
                    <h4 class="form-inline" style="margin-bottom: 10px">诊后建议</h4>
                    <div class="medicine-label-box">
                        <c:forEach var="sug" items="${emr.emrSuggestList}">
                            <div style="font-size: 16px">${sug.suggestContent}</div>
                        </c:forEach>
                    </div>
                </div>
            </div>
            <div class="form-inline text-right" style="margin-bottom: 5px">
                <b style="font-size: 20px">收费金额：</b><input type="text" name="cost" style="width: 90px;border:1px solid #218a3f;" value="${emr.cost}" disabled class="form-control"/></div>
            <div class="button-item text-center form-inline">
                <c:if test="${not empty previous}">
                    <a href="/diagnosis/${previous}" class="btn btn-success"><i class="fa fa-chevron-left"></i> 上一位</a>
                </c:if>
                <c:if test="${not empty next}">
                    <a href="/diagnosis/${next}" class="btn btn-success">下一位 <i class="fa fa-chevron-right"></i></a>
                </c:if>
                &nbsp;&nbsp;
                <button type="button" onclick="printA5or80Cfq('${emr.doctor.printType}');" class="btn btn-lg btn-success"><i class="fa fa-print"></i> 打印处方笺</button>
                <button type="button" onclick="printA5oOr80Jzd('${emr.doctor.printType}');" class="btn btn-lg btn-success"><i class="fa fa-print"></i> 打印就诊单</button>
                <a href="/diagnosis" class="btn btn-lg btn-success"><i class="fa fa-plus"></i> 新患者</a>
            </div>
        </div>
    </form:form>
    <c:if test="${not empty patient}">
        <div id="divPatientOverView" class="report-items">
            <c:import url="/fragment/patient/${patient.uid}/overview"/>
            <%--<c:import url="fragment/patientOverview.jsp"/>--%>
        </div>
    </c:if>
    <div id="banner_tabs" class="flexslider">
        <ul class="slides">
            <c:choose>
                <c:when test="${not empty adlist}">
                    <c:forEach items="${adlist}" var="ad">
                        <li><a target="_blank" href="${ad.url}"><img src="${ctx}/temp/${ad.file.fileName}"/></a></li>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <li><a href="#"><img src="${ctx}/assets/images/ad/1.jpg"/></a></li>
                    <li><a href="#"><img src="${ctx}/assets/images/ad/2.jpg"/></a></li>
                    <li><a href="#"><img src="${ctx}/assets/images/ad/3.jpg"/></a></li>
                    <li><a href="#"><img src="${ctx}/assets/images/ad/4.jpg"/></a></li>
                    <li><a href="#"><img src="${ctx}/assets/images/ad/5.jpg"/></a></li>
                </c:otherwise>
            </c:choose>
        </ul>
        <ul class="flex-direction-nav">
            <li><a class="flex-prev" href="javascript:;">Previous</a></li>
            <li><a class="flex-next" href="javascript:;">Next</a></li>
        </ul>
    </div>
</div>
<div id="divUidList" class="list-group"></div>
</body>
</html>