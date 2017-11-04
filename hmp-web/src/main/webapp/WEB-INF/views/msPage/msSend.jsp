<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/6/21
  Time: 16:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <link href="${ctx}/assets/bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
    <link href="${ctx}/assets/styles/default.min.css" type="text/css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="${ctx}/assets/styles/style.css">
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/styles/green.css" type="css" needChengStyle="true"/>
    <link rel="stylesheet" href="${ctx}/assets/styles/font-awesome/css/font-awesome.min.css">

    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/getPinYin.js" type="js"/>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/assets/html5shiv/html5shiv.js"></script>
    <script type="text/javascript" src="${ctx}/assets/respond/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript" src="${ctx}/assets/jquery/jquery-1.11.2.min.js"></script>
    <script type="text/javascript" src="${ctx}/assets/layer/layer.js"></script>
    <script type="text/javascript" src="${ctx}/assets/laypage/laypage.js"></script>
    <style>
        .intro-sign p span {
            padding: 0 5px;
        }

        .btn-success {
            background-color: #218a3f;
            border-color: #218a3f;
        }
    </style>
    <script>
        $(function () {
            var index = parent.layer.getFrameIndex(window.name);
            $('#btnClose').click(function () {
                parent.layer.close(index);
            });


            if (${needRecharge}) {
                parent.Alert.error("您的金额已不足支付当前费用,请先充值!")
            }
        });

        function sendMsg() {
            if (${needRecharge}) {
                parent.Alert.error("您的金额已不足支付当前费用,请先充值!")
                return;
            }
            $("#shade").text("短信发送中,请稍后。。。。。。");
            $("#shade").show();

            $.postJSON("/msPage/sendMsg", {}, function (rest) {
                $("#shade").hide();
                if (rest.success) {
                    var index = parent.layer.getFrameIndex(window.name);
                    parent.isSend = true;
                    parent.layer.close(index);
                } else {
                    parent.Alert.error(rest.msg);
                }
            })


        }
    </script>
</head>
<body style="background-color: #fff;">
<div id="shade" style="position:absolute; z-index:100;text-align: center;background-color:#000;padding-top:20%;
   		font-size: 30px;color: #fff;font-weight: bold;width: 100%;height: 100%;opacity: 0.4" hidden="hidden">。。。。。。
</div>
<div style="margin: 20px;">
    <div style="margin-left: 20px;">
        <p>就诊时间：<span>
            <c:if test="${!'全部'.equals(txtStartDateMsg)}">
                <fmt:formatDate value="${txtStartDateMsg}" pattern="yyyy/MM/dd" />
            </c:if>
            <c:if test="${'全部'.equals(txtStartDateMsg)}">
                ${txtStartDateMsg}
            </c:if>

            </span>
            至<span>
                <c:if test="${!'全部'.equals(txtEndDateMsg)}">
                    <fmt:formatDate value="${txtEndDateMsg}" pattern="yyyy/MM/dd" />
                </c:if>
                <c:if test="${'全部'.equals(txtEndDateMsg)}">
                    ${txtEndDateMsg}
                </c:if>
            </span>
        </p>

        <p>性别：<span>${sexMsg}</span></p>

        <p>年龄段：<span>${ageTopMsg}</span>至<span>${ageForMsg}</span></p>

        <p>病症：${diagonsisNameMsg}</p>

        <p>共计人数：${patientIdListMsg.size()}人</p>

        <p>本次金额统计:<span>${msgPrice}</span>(元/条) *
            <span>${patientIdListMsg.size()}</span>(人)*<span>${msgContextMsgOnePatientSendSize}</span>(条/人) =
            <span>${useMoneryMsg}</span>(元)</p>

        <div class="text-center" style="margin-top: 30px;">
            <button onclick="sendMsg()" class="btn btn-success"
                    style="width: 100px;<c:if test="${needRecharge}">background-color: red</c:if>">发送
            </button>
            <button id="btnClose" class="btn btn-default" style="width: 100px;">取消</button>
        </div>
    </div>
</div>

<script type=" text/javascript" src="${ctx}/assets/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/scripts/hmp.js"></script>
<script src="${ctx}/assets/jquery-validation/1.11.1/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctx}/assets/jquery-validation/1.11.1/messages_bs_zh.js" type="text/javascript"></script>
</body>
</html>
