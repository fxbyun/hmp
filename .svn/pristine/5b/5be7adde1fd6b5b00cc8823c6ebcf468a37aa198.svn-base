<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/6/17
  Time: 15:39
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
    <script>
        var indexDiv;
        $(function () {
            var index = parent.layer.getFrameIndex(window.name);
            $('#btnClose').click(function () {
                parent.layer.close(index);
            });
            if ($("#imgDiv").find("img").size() == 0) {
                $("#imgDiv").html("");
                $("#monery").val(100);
                indexDiv = layer.load(0, {shade: false});
                $("#imgDiv").load("${ctx}/pub/wx/payMoney?monery=" + 100 + "&v=" + Math.random());
            }
        });

        function setMonery(mo, obj) {
            indexDiv = layer.load(0, {shade: false});
            $("#imgDiv").html("");
            $("#monery").val(mo);
            $("#payMonery").find("button").each(function () {
                $(this).attr("class", "btn btn-default");
            });
            $(obj).attr("class", "btn btn-default active");


            $("#imgDiv").load("${ctx}/pub/wx/payMoney?monery=" + mo + "&v=" + Math.random());

        }
    </script>
</head>
<body style="background-color: #fff;">
<div style="margin: 20px; min-height: 500px; position: relative;">
    <form action="${ctx}/pub/wx/payMoney" method="post">
        <div style="margin-left: 20px;">
            <input type="hidden" name="monery" id="monery" />
            <h4 style="margin-bottom: 20px;">暂时只支持微信支付</h4>

            <p class="text-center">请选择您要充值的金额</p>

            <p id="payMonery" class="text-center recharge">
                <%--<button onclick="setMonery(1,this)" type="button" class="btn btn-default">￥1</button>--%>
                <button onclick="setMonery(50,this)" type="button" class="btn btn-default">￥50</button>
                <button onclick="setMonery(100,this)" type="button" class="btn btn-default active">￥100</button>
                <button onclick="setMonery(200,this)" type="button" class="btn btn-default">￥200</button>
                <button onclick="setMonery(500,this)" type="button" class="btn btn-default">￥500</button>
            </p>

        </div>
        <div class="text-center" id="imgDiv" style="text-align: center">
            <%--<img src="#" width="300" height="300">--%>
        </div>
        <p class="text-center" style="margin-top: 35px; position: absolute; left: 40%; bottom: 0;">
            <button id="btnClose" class="btn btn-success" style="width: 100px;">返回</button>
        </p>
    </form>
</div>

<script type="text/javascript" src="${ctx}/assets/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/scripts/hmp.js"></script>
<script src="${ctx}/assets/jquery-validation/1.11.1/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctx}/assets/jquery-validation/1.11.1/messages_bs_zh.js" type="text/javascript"></script>
</body>
</html>
