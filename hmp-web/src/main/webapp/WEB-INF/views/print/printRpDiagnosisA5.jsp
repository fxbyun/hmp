<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html lang="cn" id="ppo">
<head>
    <title>打印A5就诊单(快速、预览)</title>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="stylesheet" href="">
    <script src="${ctx}/assets/jquery/jquery-1.11.2.min.js" type="text/javascript"></script>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/print.lodop/LodopFuncs.js" type="js"/>
    <%--<script type="text/javascript" src="${ctx}/assets/scripts/application.js"></script>--%>
    <%--<script type="text/javascript" src="${ctx}/assets/scripts/slider.js"></script>--%>


    <style>
        * {
            padding: 0;
            margin: 0;
        }

        body {
            color: #000;
        }

        ul {
            list-style-type: none;
            /*padding: 0 1em;*/
            /*text-align: center;*/
        }

        li {
            padding-right: .5em;
            display: inline-block;
        }

        .main {
            width: 550px;
            height: 100%;
            background: #fff;
            -webkit-border-radius: 6px;
            -moz-border-radius: 6px;
            border-radius: 6px;
            box-shadow: 0 3px 3px #d9d9d9;
        }

        .main > div {
            margin: 0 2em;
        }

        .main-header > strong {
            font-size: 24px;
            padding: 1em 0 0.5em 0;
            text-align: center;
            display: block;
        }

        .main-sTitle, .conter1 {
            border-bottom: 2px solid #050505;
        }

        .main-contain {
            width: 100%;
            height: auto;
        }

        .main-contain div {
            overflow: hidden;
        }

        .main-sTitle input {
            border-bottom: 1px solid #777;
            width: 6.2em;
        }

        .main-footer {
            margin-top: 1em;
            line-height: 2.5em;
        }

        .main-footer input {
            width: 148px;
        }

        .beizhu {
            width: 100%;
            /*overflow: height;*/
            overflow: hidden;
        }

        .beizhu span {
            /*padding-left: .4em;*/
            line-height: 1.5em;
            float: left;
        }

        .beizhu1 {
            float: left;
            padding: .1em;
            margin-left: 2.3em;
            margin-bottom: .3em;
            min-width: 204px;
        }

        .beizhu1 > div:nth-child(1) {
            font-weight: bold;
        }

        .beizhu1 > div > span:nth-child(2) {
            font-weight: bold;
        }

        .beizhu2 {
            float: left;
        }

        .beizhu_name > span:nth-child(1) {
            font-weight: bold;
        }

        input {
            border: 0;
            line-height: 1.5em;
            height: 1.5em;
        }

        .main-contain > div:not(:nth-last-child(1)) {
            padding: 0.5em 0 .2em 0;
        }

        .main-contain > div:nth-last-child(1) {
            margin-top: 1em;
        }

        /*.main-contain>div span,.main-contain>div textarea{*/
        /*float:left*/
        /*}*/
        .input_zd {
            border: 0;
            resize: none;
            height: 1.5em;
            width: 100%;
            overflow-y: visible;
            font-size: 1em;
        }

        /*.main-contain-detail textarea{*/
        /*height:20em;*/
        /*}*/
        .main-contain-detail {
            /*height: 100%;*/
            width: 100%;
            float: left;
            display: inline-block;
            padding-bottom: 1em;
            /*overflow-y: scroll;*/
        }

        /*.main-contain-detail >span{*/
        /**/
        /*}*/
        /*居中*/
        .mCenter {
            position: relative;
            top: 0;
            bottom: 0;
            left: 0;
            right: 0;
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

        .content1 {
            width: 100%;
            /*height: 100%;*/
            min-height: 80%;
            font-size: 13px;
        }

    </style>
</head>
<body style="padding:2px 0;font-size: 12px;" id="diii">

<div class="main mCenter " style="min-height: 210mm">
    <div class="main-header pd_l">
        <strong>${emr.doctor.outpatientService}就诊单</strong>
    </div>
    <div>
        <ul class="main-sTitle" style="height: 65px;">
            <ul>
                <li>
                    <span>患者姓名:</span>
                    <input type="text" class="" style="width: 3em" value="${emr.patientName}" />
                </li>
                <li>
                    <span>性别:</span>
                    <input type="text" style="width: 2em" class=" " value="${genderMap[emr.patient.gender]}" />
                </li>
                <li>
                    <span>年龄:</span>
                    <input type="text" style="width: 3em" class="" value="${emr.patient.age}" />
                    <%--<div style="width: 3em; float: left; border-bottom:1px solid #777; font-size: 14px; margin-left: 5px; line-height: 21px;">${emr.patient.age}</div>--%>
                </li>
                <li>
                    <span>联系方式:</span>
                    <input type="text" class=" " style="width: 9em" value="${emr.patient.mobile}" />
                </li>
            </ul>
            <ul>

                <li>
                    <span>地址:</span>
                    <%--style="width:15.2em"--%>
                    <input type="text" style="width: 33em" value="<c:choose>
                        <c:when test="${empty emr.patient.address}">
                            ${emr.patient.province}${emr.patient.city}${emr.patient.area}
                        </c:when>
                        <c:otherwise>
                            ${emr.patient.address}
                        </c:otherwise>
                    </c:choose>
                    " />
                </li>
            </ul>

            <ul>

                <li>
                    <span>诊所地址:</span>
                    <%--style="width:15.2em"--%>
                    <input type="text" style="width: 31em;border-bottom: 0px"
                           value="${emr.doctor.province}${emr.doctor.city}${emr.doctor.area}<c:out value='${emr.doctor.businessAddr}'/>" />
                </li>
            </ul>

            <br />
        </ul>
    </div>
    <br />

    <div style="min-height: 100mm;">
        <section class="main-contain" style="margin-top: -1em;height: 460px">
            <p style="    margin-top: 10px;"> 患者主诉：
            <p>
                <c:if test="${empty emr.mainSuit}">无</c:if>
                <c:if test="${not empty emr.mainSuit}">${emr.mainSuit.replace(";","")}</c:if>
                &nbsp;<c:if test="${not empty emr.remarks}">${emr.remarks}</c:if>
            </p>
            </p>



            <p style="    margin-top: 10px;">生命体征：<c:forEach items="${emr.vitalSignList}" var="vs"
                                                                                 varStatus="status"><c:if
                    test="${not empty vs.value}">${vitalSignLabels[vs.type]}${vs.value}${vitalSignUnits[vs.type]}
                <c:if test="${not empty vitalSignUnits[vs.type]}">;&nbsp;</c:if></c:if></c:forEach>
            </p>

            <p style="    margin-top: 10px;margin-bottom: 10px;"> 初步诊断：<c:if test="${empty emr.diagnosisResult}">无</c:if><c:if
                    test="${not empty emr.diagnosisResult}">${emr.diagnosisResult}</c:if>
            </p>

            <div class="main-contain-detail">
                <div class="content1">
                    <p class="subtitle" style="margin-bottom: 11px;">服药医嘱</p>
                    <c:set var="doctorAdviceList" value="${emr.doctorAdviceList}" />
                    <c:if test="${empty doctorAdviceList}"><p>无</p></c:if>

                    <c:if test="${not empty doctorAdviceList}">
                        <c:forEach var="entry" items="${doctorAdviceList}">
                            <span><strong></strong></span>

                            <div class="content1">
                                <span><strong>${entry.key}</strong></span>

                                <div class="beizhu">

                                    <c:forEach varStatus="status" var="ad" items="${entry.value}">
                                        <div class="beizhu1">
                                            <div class="beizhu2">
                                                <span>${status.count}、</span>
                                            </div>
                                            <div>
                                                <div class="beizhu_name">

                                                    <span>${ad}</span>
                                                </div>
                                                <div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>


                    <div class="suggest" style="margin-bottom: 10px">&nbsp;&nbsp;&nbsp;</div>
                    <div class="suggest" style="margin-bottom: 10px">诊后建议</div>
                    <%--<p class="subtitle" style="font-size: 17px;margin-bottom: 11px;">诊后建议</p>--%>
                    <c:set var="emrSuggestList" value="${emr.emrSuggestList}" />
                    <c:if test="${empty emrSuggestList}"><p>无</p></c:if>
                    <c:if test="${not empty emrSuggestList}">
                        <c:forEach varStatus="status" var="s" items="${emrSuggestList}">
                            <div class="suggest" style="margin-bottom: 10px">${status.count}、${s.suggestContent}</div>
                        </c:forEach>
                    </c:if>
                </div>
            </div>
        </section>
    </div>

    <%--style="min-height: 167px;position: relative;bottom: -40px;"--%>
    <div class="main-footer" >
        <c:if test="${not empty emr.doctor.printInfo}">
            <p style="text-align: center">${emr.doctor.printInfo}</p>
        </c:if>

        <div class="main-footer ub pad-tb" style="border-top: 2px dotted #d8d8d8;">
            <div class="ub ub-f1 ub-ver tex-r mar-r1">
                <strong style="font-size: 10px; padding: 0 35%;">易佳诊，连接健康每一刻</strong>

                <h2 class="p-title" style=" display: inline-block;position: relative;left: 90px;">
                </h2>
            </div>
            <span class="ub ub-f1 "
                  style="line-height: 1.8em;display: inline-flex;position: relative;left: 0px;top: -30px;">
                <img style="width:7em;height:7em;float: left" width="100px" height="100px"
                     src="${ctx}/assets/images/qrcode.jpg">
                <span class="ub ub-ver pad-lr" style="float: left">
                    <p>扫描二维码，</p>

                    <p>关注"易佳诊健康管理"！</p>

                    <p>将您的健康卡绑定到微信,</p>

                    <p>享受更全面的健康服务！</p>
                </span>
            </span>
        </div>

    </div>
</div>
</body>

</html>
