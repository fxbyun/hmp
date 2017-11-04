<%--
Created by IntelliJ IDEA.
User: Administrator
Date: 2016/5/30
Time: 13:50
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html lang="cn">
<head>
    <title>患者详情</title>
    <%----%>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/prescription/prescription.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/application.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/print.lodop/LodopFuncs.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/print.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/jpPrint/jQuery.print.js" type="js"/>
    <script type="application/javascript" src="http://localhost:8000/CLodopfuncs.js?priority=1"></script>
    <object id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0
            style="position:absolute;left:0px;top:-10px;"></object>
    <object id="LODOP_EM" TYPE="application/x-print-lodop" width=0 height=0
            style="position:absolute;left:0px;top:-10px;"></object>
    <style>
        body {
            font-size: 16px;
        }

        .col-sm-2 .btn-boxs .btn-default {
            width: 120px;
            font-weight: 400;
            letter-spacing: 2px;
            font-size: 18px;
        }

        .intro-sign .patient-tags {
            margin-left: 40px;
            font-size: 16px;
        }

        .patient-tags span {
            padding-right: 20px;
        }

        .intro-sign .information {
            margin-left: 40px;
        }

        .pt-detail {
            overflow: hidden;
            border-radius: 10px;
            margin-bottom: 20px;
        }

        .pt-detail .doctors-action {
            float: left;
            margin-bottom: 0;
            min-height: 513px;
        }

        .pt-detail .doctors-action .pt-title {
            color: #333;
            text-align: center;
        }

        .pt-title span {
            padding: 22px 5px 25px 0;
            color: #333;
            vertical-align: middle;
        }

        .pt-detail .nav li {
            color: #333;
            text-align: center;
        }

        #emrTimeList {
            overflow-y: scroll;
            height: 450px;
            width: 180px;
        }

        .pt-detail .nav > li > a {
            color: #333;
            font-size: 16px;
            padding: 18px 0 18px 0;
        }

        .pt-detail .nav > li > a:hover, .pt-detail .nav > li > a:active {
            background-color: #fff;
        }

        .pt-detail .nav > li.active, .pt-detail .nav > li.active a {
            background-color: #fff;
        }

        /*右边内容*/
        .doctors-action h3 {
            font-size: 22px;
            color: #000;
        }

        .pt-detail .pt-infor {
            float: left;
            width: 87%;
        }

        .pt-infor p {
            padding-left: 55px;
            color: #333;
        }

        .pt-detail-xq1 span {
            padding-right: 5px;
        }

        .pt-detail-xq2 {
            margin-left: 1.8em;
            font-size: 15px;
        }

        .pt-detail-xq2 span {
            padding-right: 10px;
        }

        .content {
            margin-left: 55px;
            overflow: hidden;
        }

        .content .col-md-5.col-sm-5 {
            padding: 10px 10px;
        }

        .review-item-content p {
            padding-left: 0;
        }

        /* 图标 */
        .review-content i {
            background: url("/assets/styles/images/qb01.png") left center no-repeat;
            width: 13px;
            height: 13px;
            padding: 5px 10px 0 20px;
            margin-left: 20px;
        }

        .review-item-content {
            padding-left: 0px;
            font-size: 18px;
            margin-left: -15px;
        }

        .intro-sign input {
            font-weight: normal;
        }

        /*按钮*/
        .btn-boxs .btn {
            width: 115px;
        }

        .review-item-content .col-md-3 span {
            width: 100%;
            margin-left: 20px;
        }

        .intro-sign .pull-right {
            width: 80px;
            font-size: 16px;
            letter-spacing: 2px;
            /* box-shadow: 0 3px 3px -1px #666;*/
            padding: 6px 0;
        }

        #medContext {
            padding-left: 37px;
        }

        .font-2_5 {
            /*float:right;*/
            text-align: left;
            font-size: 2.5em;
        }

        .font-2 {
            font-size: 2em;
            margin-left: -11px;
        }

        #medContext .content1 .beizhu {
            overflow: hidden;
        }

        #medContext .content1 .beizhu > .groupName {
            width: 8% !important;
        }

        .layui-layer-btn {
            text-align: center;
        }

        /* .layui-layer-btn0 {
             font-size: 30px;
         }

         .layui-layer-btn a {
             height: 37px;
         }*/
    </style>
    <script>
        $(function () {
            $("body").prepend("<div id='toast-content' style='top:40px;position: absolute !important;'></div>");

            $('ul.nav > li').click(function (e) {
                e.preventDefault();
                $('ul.nav > li').removeClass('active');
                $(this).addClass('active');

            });

            $("#emrTimeList li[dataid='${clickEmrId}']").attr("class", "active");
            var ids = ${clickEmrId};
            loadEmrDtilById(ids)
        });
        var clickEmrId = "${clickEmrId}";
        var emrId = "123";
        //根据病历ID 加载病历
        function loadEmrDtilById(id) {
            $("#emrInfoDiv").load("/newPage/sunPage/emrInfo?id={0}".format(id));
            $("#emrTalkDiv").load("/newPage/sunPage/emrTalkInfo?id={0}".format(id));
            emrId = id;
            Alert.success("加载病历成功!");

            $("#topJzTime").text($("#emrTimeList li[dataid='" + id + "'] a").text());
            $("#addNewEmr").attr("href", "${ctx}/diagnos/{0}".format(id))
        }
        //新增病历
        function addNewEmr() {
            window.location.href = $("#addNewEmr").attr("href");
        }
        //打印处方签
        function printA5or80Cfq(type, printModel) {
            if (type == "A5打印") {
                if (printModel != "预览打印") {
                    YJZ_Printer.printUrl('../pub/printRpA5/{0}'.format(emrId));
//                判断是否还需要独立打印
                    $.postJSON("/pub/isNeedAlonePrint", {emrId: emrId}, function (ret) {
                        if (ret.success) {
                            YJZ_Printer.printUrl('../pub/printRpA5/{0}?type=alone'.format(emrId));
                            Alert.success("打印成功,本次已启用分页打印!请勿重复点击,请稍后!");
                        }
                    });
                } else {
                    var idex = layer.open({
                        title: "预览打印",
                        content: ("${ctx}/pub/printRpA5/{0}?printModel={1}".format(emrId, printModel)),
                        area: ['520px', '600px'],
                        btn: ["关闭"],
                        yes: function () {
                            layer.close(idex);
                        },
                        scrollbar: false,
                        type: 2,
                        end: function () {
                            //                判断是否还需要独立打印
                            $.postJSON("/pub/isNeedAlonePrint", {emrId: emrId}, function (ret) {
                                if (ret.success) {
                                    var idexAlon = layer.open({
                                        title: "预览打印",
                                        content: ("${ctx}/pub/printRpA5/{0}?printModel={1}&type=alone".format(emrId, printModel)),
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
        function printA5oOr80Jzd(type, printModel) {

            if (type == "A5打印") {
                YJZ_Printer.printUrl('../pub/printDiagnosisA5/{0}'.format(emrId));//就诊单默认还是80打印机
            } else {
                YJZ_Printer.print80Url('../pub/printDiagnosis/{0}'.format(emrId));
            }

        }

        function binDingCardInfo(patientId) {
            layer.open({
                type: 2,
                title: '绑定健康卡',
                area: ['700px', '600px'],
                scrollbar: false,
                content: '/patient/tmpPatientBinDingCardPage?id=' + patientId,
                end: function () {
                    window.location.href = "${ctx}/patientManage";
                }
            });
        }

        //显示检查结果弹窗
        function tableResult(id) {
            layer.open({
                type: 2,
                title: '检查结果',
                area: ['600px', '380px'],
                scrollbar: false,
                content: 'newPage/sunPage/tableResult?id=' + id
            });
        }

        function loadExamLabByName(className, obj) {
            $("#detailTable").load("/newPage/sunPage/loadExamLabPage?id=${patient.id}&className={0}".format(className));
            $("#obj").parent().prev.removeAttr("class", "active");
            $("#obj").parent().attr("class", "active");

        }

    </script>
</head>
<body>
<div class="container electronic">
    <div class="row patientDetail">
        <div class="col-md-12">

            <div class="intro-sign form-inline" style="margin-bottom:20px;">
                <div class="row">
                    <div class="col-md-1 col-sm-1">
                        <img src="/assets/images/touxiang.png" width="107" height="107">
                    </div>
                    <div class="col-md-9 col-sm-9 information">
                        <div class="row">
                            <div class="col-md-4 col-sm-4">
                                <span>患者姓名：</span>
                                <span>${patient.name}</span>

                                <p style="line-height: 30px; margin:10px 0 10px 0;">
                                    <span>联系方式：</span>
                                    <span>${patient.mobile}</span>
                                </p>
                            </div>
                            <div class="col-md-4 col-sm-4">
                                <span>性别：</span>
                                <span><c:if test="${patient.gender == 'Male'}">男</c:if><c:if
                                        test="${patient.gender == 'Female'}">女</c:if></span>

                                <p style="line-height: 30px; margin:10px 0 10px 0;">
                                    <span>就诊时间：</span>
                                    <span id="topJzTime">2016/04/23 11:00</span>
                                </p>
                            </div>
                            <div class="col-md-4 col-sm-4">
                                <span>年龄：</span>
                                <span>${patient.age}</span>

                                <p style="line-height: 30px; margin:10px 0 10px 0;">
                                    <span>诊疗医生：</span>
                                    <span id="doctorNameEmr">${doctor.name}</span>
                                </p>
                            </div>
                            <div class="col-md-12 col-sm-12">
                                <span>地址：</span>
                                <span>${patient.address}</span>
                            </div>
                            <div class="col-md-12 col-sm-12">
                                <p style="line-height: 30px; margin:10px 0 10px 0;">
                                    <span style="padding-right:10px;">病史标签：</span>
                                    <c:forEach var="tag" items="${patient.patientTagList}">
                                        <span>${tag.name}</span>
                                    </c:forEach>
                                </p>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-1 col-sm-1 btn-boxs" style="padding:0; float: right;">
                        <a href="#" id="addNewEmr" class="btn btn-default pull-right" style="width: 120px;">添加新病历</a>
                        <a href="#" class="btn btn-default pull-right" style="width: 120px; margin-top: 5px"
                           onclick="buyMedicDetails(${patient.id})">查看购药记录</a>
                        <a href="#" class="btn btn-default pull-right" onclick="binDingCardInfo('${patient.id}')"
                           style="width: 120px; margin-top: 5px;
                           <c:if test="${patient.status != 'Tmp'}">display: none </c:if>
                                   ">绑卡/换卡</a>
                    </div>
                </div>
            </div>

            <%-- 添加的检查项目需求  --%>
            <div class="doctors-action"
                 <c:if test="${emrClassSet.size()<=0}">style="display: none" </c:if> >
                <div class="pt-items">
                    <span style="vertical-align: top;">历史检查项目有</span>
                    <ul class="nav nav-pills">
                        <c:forEach items="${emrClassSet}" var="oneExamLab" varStatus="status">
                            <li
                                    <c:if test="${status.count==1}">class="active" </c:if> >
                                <a href="#" class="btn btn-default"
                                   onclick="loadExamLabByName('${oneExamLab}',this)">${oneExamLab}</a>
                            </li>
                        </c:forEach>

                        <%--<li><a href="#" class="btn btn-default">胃镜</a></li>--%>
                    </ul>
                    <div id="detailTable" style="margin-top: 10px;">
                        <%@include file="sunPage/sunTable.jsp" %>
                    </div>
                </div>
            </div>
            <%-- END --%>

            <div class="pt-detail">
                <div class="doctors-action" style="padding: 0; width: 13%;">
                    <div class="pt-title"><span class="glyphicon glyphicon-time"></span>历史病历</div>
                    <div style="overflow: hidden;">
                        <ul class="nav" id="emrTimeList">
                            <c:if test="${not empty emr}">
                                <c:forEach items="${emr}" var="oneEmr">
                                    <li onclick="loadEmrDtilById('${oneEmr.id}')" dataid="${oneEmr.id}">
                                        <a href="#"><fmt:formatDate value="${oneEmr.createOn}"
                                                                    pattern="yyyy/MM/dd HH:mm"/></a>
                                    </li>
                                </c:forEach>
                            </c:if>
                        </ul>
                    </div>
                </div>
                <div class="pt-infor doctors-action" id="emrInfoDiv">
                </div>

            </div>
            <div class="intro-sign" id="ptDetail">
                <div class="row" id="emrTalkDiv">
                    <%--<div class="col-md-1 col-sm-1">
                        <img class="img-circle" src="/assets/images/touxiang.png" width="107" height="107">
                    </div>--%>
                </div>
            </div>
            <div class="text-center btn-boxs" style="padding: 10px 0 10px">
                <button type="button" onclick="history.back()" class="btn btn-success"> 返回
                </button>
                &nbsp;&nbsp;
                <button type="button" onclick="addNewEmr()" class="btn btn-default"> 添加新病历
                </button>
                &nbsp;&nbsp;
                <button type="button" onclick="printA5or80Cfq('${doctor.printType}','${doctor.printModel}')"
                        class="btn btn-default"> 打印处方笺
                </button>
                &nbsp;&nbsp;
                <button type="button" onclick="printA5oOr80Jzd('${doctor.printType}','${doctor.printModel}')"
                        class="btn btn-default"> 打印就诊单
                </button>
            </div>

        </div>

    </div>
</div>
</body>
<script type="application/javascript">

</script>
</html>
