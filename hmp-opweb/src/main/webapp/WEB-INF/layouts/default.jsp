<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="hmp" uri="/WEB-INF/TLD/HmpLoadStaticFile.tld" %>
<!DOCTYPE html>
<html>
<head>
    <title>易佳诊诊所运营管理平台<sitemesh:title /></title>
    <meta charset="UTF-8">
    <%--<meta name="viewport" content="width=device-width, initial-scale=1">--%>
    <meta name="viewport"
          content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <meta http-equiv="Cache-Control" content="no-store" />
    <meta http-equiv="Pragma" content="no-cache" />
    <meta http-equiv="Expires" content="0" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link href="${ctx}/assets/bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
    <%--<link href="${ctx}/assets/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" type="text/css" rel="stylesheet" />--%>
    <%--<link href="${ctx}/assets/styles/default.min.css" type="text/css" rel="stylesheet" />--%>
    <link href="${ctx}/assets/styles/style.css" type="text/css" rel="stylesheet">
    <link href="${ctx}/assets/styles/default.css" type="text/css" rel="stylesheet" />
    <link href="${ctx}/assets/styles/public.css" type="text/css" rel="stylesheet" />
    <link href="${ctx}/assets/jquery-validation/1.11.1/validate.css" type="text/css" rel="stylesheet" />


    <link href="${ctx}/assets/styles/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/assets/html5shiv/html5shiv.js"></script>
    <script type="text/javascript" src="${ctx}/assets/respond/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript" src="${ctx}/assets/jquery/jquery-1.11.2.min.js"></script>
    <script type="text/javascript" src="${ctx}/assets/layer/layer.js"></script>
    <%--<script type="text/javascript" src="${ctx}/assets/scripts/findErrorScan.js"></script>--%>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/getPinYin.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/acupuncturePoint.js" type="js"/>
    <%--<link href="${ctx}/assets/toastr/toastr.css" type="text/css" rel="stylesheet">
    <link href="${ctx}/assets/datetimepicker/jquery.datetimepicker.css" type="text/css" rel="stylesheet">
    <link href="${ctx}/assets/select2/select2.css" type="text/css" rel="stylesheet">--%>
    <script type="text/javascript" src="${ctx}/assets/bootstrap/js/bootstrap.min.js"></script>
    <%--<script type="text/javascript" src="${ctx}/assets/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
    <%--<script type="text/javascript" src="${ctx}/assets/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>--%>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/hmp.js" type="js"/>
    <%--<script type="text/javascript" src="${ctx}/assets/scripts/highcharts.js"></script> --%>

    <%--<script type="text/javascript" src="${ctx}/assets/scripts/pinyin.js"></script>
    <script type="text/javascript" src="${ctx}/assets/select2/select2.js"></script>
    <script type="text/javascript" src="${ctx}/assets/select2/select2.ext.js"></script>
    <script type="text/javascript" src="${ctx}/assets/toastr/toastr.ext.js"></script>
    <script type="text/javascript" src="${ctx}/assets/toastr/toastr.js"></script>
    <script type="text/javascript" src="${ctx}/assets/scripts/base.js"></script>--%>
    <script type="text/javascript" src="${ctx}/assets/wrapper/iscroll.js"></script>
    <script type="text/javascript" src="${ctx}/assets/mobileJs/appMobile.js"></script>
    <script type="text/javascript" src="${ctx}/assets/jquery-validation/1.11.1/jquery.validate.min.js"></script>
    <script type="text/javascript" src="${ctx}/assets/jquery-validation/1.11.1/messages_bs_zh.js"></script>
    <sitemesh:head />
</head>

<body>

<%@ include file="/WEB-INF/layouts/header.jsp" %>
<sitemesh:body />
<%@ include file="/WEB-INF/layouts/footer.jsp" %>


<%--<script type="text/javascript" src="${ctx}/assets/datetimepicker/jquery.datetimepicker.js"></script>--%>

<script type="application/javascript">
    function isDebug() {
        Alert.info('本功能正在开发中,敬请期待!', "感谢您的关注");
        return false;
    }
</script>
</body>
</html>