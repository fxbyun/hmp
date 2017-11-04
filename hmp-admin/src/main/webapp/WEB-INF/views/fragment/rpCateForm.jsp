<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springside.org.cn/tags/form" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <title>易佳诊健康管理平台</title>
    <meta charset="UTF-8">
    <link href="${ctx}/assets/styles/admin.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="${ctx}/assets/scripts/admin.js"></script>
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/assets/html5shiv/html5shiv.js"></script>
    <script type="text/javascript" src="${ctx}/assets/respond/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript" src="${ctx}/assets/layer/layer.js"></script>
    <script type="text/javascript">
        $(function () {

        })
    </script>
</head>
<body>
<div style="padding: 20px;">
    <form:form action="${ctx}/rpCate/save" cssClass="form-horizontal" method="post" modelAttribute="category">
        <form:hidden path="id"/>
        <table width="100%">
            <tr>
                <td align="right">类别名称： &nbsp;</td>
                <td><form:input path="name" cssClass="form-control" id="txtName" placeholder="请输入名称"/></td>
            </tr>
            <tr>
                <td align="right">类别说明： &nbsp;</td>
                <td><form:input path="remark" cssClass="form-control" id="txtspec" placeholder="类别说明"/></td>
            </tr>
            <tr>
                <td align="center" colspan="2">
                    <button type="submit" class="btn btn-primary"><i class="fa fa-save"></i> 保存类别</button>
                    <a href="${ctx}/fragment/rpCate/add" class="btn btn-success"><i class="fa fa-plus"></i> 继续新增</a>
                </td>
            </tr>
            <tr>
                <td align="center" colspan="2">
                    <h5 class="col-xs-offset-3 col-sm-offset-3 col-xs-9 col-sm-9 text-danger"> ${msg} </h5>
                </td>
            </tr>
        </table>
    </form:form>
</div>
</body>
</html>