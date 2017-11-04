<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/6/16
  Time: 17:03
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
        .nav.nav-tabs {
            overflow: hidden;
            padding: 2px 50px 10px 50px;
            margin: 0 auto;
            text-align: center;
            width: 80%;
        }

        .nav.nav-tabs > li {
            margin-right: 20px;
        }

        .nav.nav-tabs > li > a {
            border: 1px solid #ccc;
            color: #333;
            width: 120px;
            padding: 10px 5px;
            border-radius: 5px;
        }

        .nav.nav-tabs > li > a:hover {
            border: 1px solid #ccc;
        }

        .nav.nav-tabs > li.active > a, .nav.nav-tabs > li.active > a:focus, .nav.nav-tabs > li.active > a:hover {
            color: #fff;
            box-shadow: 0px 1px 2px 2px #eee;
        }

        td, th {
            text-align: center;
        }

        .intro-sign {
            margin-bottom: 0px;
        }

        .fenye {
            margin-top: 15px;
            margin-bottom: 0;
            font-size: 14px;
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

            $("#a1").load("${ctx}/msPage/msDetailRechargeList")
            $("#a2").load("${ctx}/msPage/msDatailSendHistoryList")
        });

        function fn_LoadPageSendHis(num) {
            $("#a2").load("${ctx}/msPage/msDatailSendHistoryList?page=" + num + "&startDate=" + $("#txtStartDate").val() + "&endDate=" + $("#txtEndDate").val())
        }
        function fn_LoadPageRecHis(num) {
            $("#a1").load("${ctx}/msPage/msDetailRechargeList?page=" + num)
        }
    </script>
</head>
<body>
<div style="margin: 20px;" class="msDetail">
    <div class="intro-sign tabbable text-center">
        <ul class="nav nav-tabs" role="tablist">
            <li class="active">
                <a href="#a1" class="btn btn-default" data-toggle="tab">充值明细</a>
            </li>
            <li>
                <a href="#a2" class="btn btn-default" data-toggle="tab">历史消费明细</a>
            </li>
        </ul>
        <div class="tab-content" style="width: 100%; min-height: 268px;">


            <div id="a1" class="tab-pane active" style="border:1px solid #ccc; width: 100%; min-height: 220px;">

            </div>
            <div id="a2" class="tab-pane" style="border:1px solid #ccc; width: 100%; min-height: 220px;">

            </div>

        </div>
        <div class="text-center" style="margin-top: 20px;">
            <button style="width: 120px;" id="btnClose" class="btn btn-default">关闭</button>
        </div>
    </div>
</div>
<script type="text/javascript" src="${ctx}/assets/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/scripts/hmp.js"></script>
<script src="${ctx}/assets/jquery-validation/1.11.1/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctx}/assets/jquery-validation/1.11.1/messages_bs_zh.js" type="text/javascript"></script>
</body>
</html>
