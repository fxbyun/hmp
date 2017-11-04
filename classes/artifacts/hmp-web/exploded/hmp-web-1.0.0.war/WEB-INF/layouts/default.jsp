<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="hmp" uri="/WEB-INF/TLD/HmpLoadStaticFile.tld" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
    <title>易佳诊诊所运营管理平台<sitemesh:title /></title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <meta http-equiv="Cache-Control" content="no-store" />
    <meta http-equiv="Pragma" content="no-cache" />
    <meta http-equiv="Expires" content="0" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/bootstrap/css/bootstrap.min.css" type="css"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css"
                     type="css"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/styles/default.min.css" type="css"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/styles/style.css" type="css"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/styles/base.css" type="css"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/styles/green.css" type="css" needChengStyle="true"/>
    <%--<hmp:HmpLoadFile ctx="${ctx}" url="/assets/styles/blue.css" type="css" needChengStyle="true"/>--%>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/styles/font-awesome/css/font-awesome.min.css" type="css"/>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/html5shiv/html5shiv.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/respond/respond.min.js" type="js"/>

    <![endif]-->
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/jquery/jquery-1.11.2.min.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/jquery/jquery-1.11.2.min.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/layer/layer.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/xwArray.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/findErrorScan.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/getPinYin.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/acupuncturePoint.js" type="js"/>


    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/toastr/toastr.css" type="css"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/datetimepicker/jquery.datetimepicker.css" type="css"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/select2/select2.css" type="css"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/bootstrap/js/bootstrap.min.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"
                     type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/hmp.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/highcharts.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/pinyin.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/select2/select2.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/select2/select2.ext.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/toastr/toastr.ext.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/toastr/toastr.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/base.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/public.js" type="js"/>

    <sitemesh:head />
    <%--GrowingIO监测网站使用数据--%>
    <script type='text/javascript'>
        var _vds = _vds || [];
        window._vds = _vds;
        (function () {
            _vds.push(['setAccountId', 'a3ae0bea00ca7fc2']);
            _vds.push(['setCS1', 'doctorId', '${doctor.id}']);
            _vds.push(['setCS2', 'doctorName', '${doctor.name}']);
            _vds.push(['setCS3', 'outpatientService', '${doctor.outpatientService}']);
            _vds.push(['setCS4', 'city', '${doctor.city}']);

            (function () {
                var vds = document.createElement('script');
                vds.type = 'text/javascript';
                vds.async = true;
                vds.src = ('https:' == document.location.protocol ? 'https://' : 'http://') + 'dn-growing.qbox.me/vds.js';
                var s = document.getElementsByTagName('script')[0];
                s.parentNode.insertBefore(vds, s);
            })();
        })();
    </script>


</head>

<body>
<div id="shade" style="position:absolute; z-index:100;text-align: center;background-color:#000;padding-top:20%;
   		font-size: 30px;color: #fff;font-weight: bold;width: 100%;height: 100%;opacity: 0.4" hidden="hidden">。。。。。。
</div>
<%@ include file="/WEB-INF/layouts/header.jsp" %>
<sitemesh:body />
<%@ include file="/WEB-INF/layouts/footer.jsp" %>


<%--<script type="text/javascript" src="${ctx}/assets/datetimepicker/jquery.datetimepicker.js"></script>--%>

<script type="application/javascript">
    try {
        $.each($("input[type='number']"), function (index, obj) {
            $(obj).keyup(function () {
                if ($(this).val().length > 8) {
                    $(this).val($(this).val().slice(0, 7));
                }
            })
        })
    } catch (e) {

    }
    function isDebug() {
//        Alert.info('本功能正在开发中,敬请期待!', "感谢您的关注");
//        return false;
    }
</script>
</body>
</html>