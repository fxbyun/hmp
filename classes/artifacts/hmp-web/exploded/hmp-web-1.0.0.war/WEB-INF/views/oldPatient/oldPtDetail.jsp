<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/5/30
  Time: 13:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<%@ include file="/WEB-INF/commons/staticInclude.jsp" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html lang="cn">
<head>
    <title>患者详情</title>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/prescription/prescription.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/application.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/print.lodop/LodopFuncs.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/print.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/acupuncturePoint.js" type="js"/>

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

        .nav > li > a {
            color: #333;
            font-size: 16px;
            padding: 18px 0 18px 0;
        }

        .nav > li > a:hover {
            background-color: #fff;;
        }

        .nav > li.active {
            background-color: #fff;
        }

        /*右边内容*/
        .doctors-action h3 {
            font-size: 22px;
            color: #000;
        }

        .pt-infor {
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
            margin-left: 5px;
            padding-left: 1.5em;
        }

        .pt-detail-xq2 span {
            padding-right: 10px;
        }

        .content {
            margin-left: 55px;
            margin-bottom: 20px;
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

    </style>
    <script>
        $(function () {
            $("body").prepend("<div id='toast-content' style='top:40px;position: absolute !important;'></div>");
        })
        $(document).ready(function () {

            $('ul.nav > li').click(function (e) {
                e.preventDefault();
                $('ul.nav > li').removeClass('active');
                $(this).addClass('active');

            });

        });
        var clickEmrId = "${clickEmrId}";
        var emrId = "123";

        function loadEmrDtilById(id) {
            $("#emrInfoDiv").load("/oldPatient/oldPtDetail/emrInfo?id={0}".format(id));
            emrId = id;
            $("#topJzTime").text($("#emrTimeList li[dataid='" + id + "'] a").text());
            Alert.success("加载病历成功!");
        }
        $(function () {
            $("#emrTimeList li[dataid='${clickEmrId}']").attr("class", "active");
            var ids = ${clickEmrId};
            loadEmrDtilById(ids)
        })

        function binDingCard(id) {
            $.postJSON("${ctx}/oldPatient/isThisPatienHaveBinDingCard", {"patientId": id}, function (rest) {
                if (rest.success) {
                    Alert.error("该病人已绑定就诊卡!");
                } else {
                    Alert.success("该病人未绑卡!");

                    layer.open({
                        type: 2,
                        title: '绑定健康卡',
                        area: ['700px', '600px'],
                        scrollbar: false,
                        content: '/oldPatient/oldPtDetail/binDingCard?oldPatientId=${patient.id}',
                        end: function () {
                            window.location.reload();
                        }
                    });
                }
            })


        }

        function addNewEmr() {
            Alert.error("很抱歉,处方为您的旧数据,暂时不支持新增病历!")
        }

        //打印处方签
        function printA5or80Cfq(type) {
            Alert.error("很抱歉,处方为您的旧数据,暂时不支持打印!")
        }

        //打印就诊单
        function printA5oOr80Jzd(type) {
            Alert.error("很抱歉,处方为您的旧数据,暂时不支持打印!")

        }
    </script>

</head>
<body>
<div class="container electronic">
    <div class="row oldPtDetail">
        <div class="col-md-12">

            <div class="intro-sign form-inline" style="margin-bottom:20px;">
                <div class="row">
                    <div class="col-md-1 col-sm-1">
                        <img src="/assets/images/touxiang.png" width="107" height="107">
                    </div>
                    <div class="col-md-3 col-sm-3 information">
                        <span>患者姓名：</span>
                        <span>${patient.name}</span>

                        <p style="line-height: 30px; margin:10px 0 10px 0;">
                            <span>联系方式：</span>
                            <span>无</span>
                        </p>
                    </div>
                    <div class="col-md-3 col-sm-3">
                        <span>性别：</span>
                        <span><c:if test="${patient.gender == 'Male'}">男</c:if><c:if
                                test="${patient.gender == 'Female'}">女</c:if></span>

                        <p style="line-height: 30px; margin:10px 0 10px 0;">
                            <span>就诊时间：</span>
                            <span id="topJzTime"> </span>
                        </p>
                    </div>
                    <div class="col-md-2 col-sm-2">
                        <span>年龄：</span>
                        <span>${patient.age}</span>

                        <p style="line-height: 30px; margin:10px 0 10px 0;">
                            <span>诊疗医生：</span>
                            <span>${doctor.name}</span>
                        </p>
                    </div>
                    <div class="col-md-2 col-sm-2 btn-boxs" style="margin-left:55px; padding-right:0;">
                        <a href="#" id="addNewEmr" class="btn btn-default pull-right" style="width: 120px;display: none">添加新病历</a>
                        <a href="#" class="btn btn-warning"
                           style="width: 120px; float: right; margin-right: 6px; margin-top: 5px; font-size: 16px;">
                            <c:choose>
                                <c:when test="${'UNACTIVATION'.equals(patient.status.toString())}">
                                    <span onclick="binDingCard(${patient.id})">绑定健康卡</span>
                                </c:when>
                                <c:otherwise>
                                    <span>患者已绑卡</span>
                                </c:otherwise>
                            </c:choose>
                        </a>
                    </div>
                    <div class="col-md-8 col-sm-8 information">
                        <span>地址：</span>
                        <span>${patient.address}</span>
                    </div>
                    <div class="col-md-10 col-sm-10 patient-tags" style="line-height: 30px; margin-top:10px;">
                        <span style="padding-right:10px;">病史标签：</span>
                        <%--<c:forEach var="tag" items="${patient.patientTagList}">--%>
                        <span>
                                <%--${tag.name}--%>
                            无
                        </span>
                        <%--</c:forEach>--%>
                    </div>
                </div>
            </div>

            <div class="pt-detail">
                <div class="doctors-action" style="padding: 0; width: 13%;">
                    <div class="pt-title"><span class="glyphicon glyphicon-time"></span>历史病历</div>
                    <div style="overflow: hidden;">
                        <ul class="nav" id="emrTimeList">
                            <c:if test="${not empty emr}">
                                <c:forEach items="${emr}" var="oneEmr">
                                    <li onclick="loadEmrDtilById('${oneEmr.id}')" dataid="${oneEmr.id}">
                                        <a href="#"><fmt:formatDate value="${oneEmr.createTime}"
                                                                    pattern="yyyy/MM/dd HH:mm" /></a>
                                    </li>
                                </c:forEach>
                            </c:if>
                        </ul>
                    </div>
                </div>
                <div class="pt-infor doctors-action" id="emrInfoDiv">
                </div>

            </div>

            <div class="text-center btn-boxs" style="padding: 10px 0 10px">
                <button type="button" onclick="history.back()" class="btn btn-success"> 返回
                </button>
                &nbsp;&nbsp;
                <button type="button" onclick="addNewEmr()" class="btn btn-default"> 添加新病历
                </button>
                &nbsp;&nbsp;
                <button type="button" onclick="printA5or80Cfq('${doctor.printType}')" class="btn btn-default"> 打印处方笺
                </button>
                &nbsp;&nbsp;
                <button type="button" onclick="printA5oOr80Jzd('${doctor.printType}')" class="btn btn-default"> 打印就诊单
                </button>
            </div>

        </div>

    </div>
</div>
</body>
<script type="application/javascript">

</script>
</html>
