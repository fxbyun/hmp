<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<title>易佳诊健康管理平台</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
	<meta http-equiv="Cache-Control" content="no-store"/>
	<meta http-equiv="Pragma" content="no-cache"/>
	<meta http-equiv="Expires" content="0"/>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<link href="${ctx}/assets/styles/admin.css" type="text/css" rel="stylesheet"/>
	<script type="text/javascript" src="${ctx}/assets/scripts/admin.js"></script>
	<script type="text/javascript" src="${ctx}/assets/layer/layer.js"></script>
	<script type="text/javascript" src="${ctx}/assets/scripts/hmp.js"></script>
	<link href="${ctx}/assets/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" type="text/css" rel="stylesheet"/>
	<sitemesh:head/>
</head>
<body>
	<%@ include file="/WEB-INF/layouts/header.jsp" %>
	<sitemesh:body/>
<!--[if lt IE 9]>
<script type="text/javascript" src="${ctx}/assets/html5shiv/html5shiv.js"></script>
<script type="text/javascript" src="${ctx}/assets/respond/respond.min.js"></script>
<![endif]-->
<script type="text/javascript" src="${ctx}/assets/jquery-validation/1.11.1/jquery.validate.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript">
	$("#logout").click(function(){
		layer.confirm('您确定退出系统？', {
			btn: ['确定','取消']
		}, function(){
			location.href = "${ctx}/logout";
		});
	});
</script>
</body>
</html>