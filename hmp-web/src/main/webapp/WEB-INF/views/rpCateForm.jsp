<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <link href="${ctx}/assets/bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
    <link href="${ctx}/assets/styles/default.min.css" type="text/css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/assets/styles/style.css">
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/styles/green.css" type="css" needChengStyle="true"/>
    <link rel="stylesheet" href="${ctx}/assets/styles/font-awesome/css/font-awesome.min.css">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/assets/html5shiv/html5shiv.js"></script>
    <script type="text/javascript" src="${ctx}/assets/respond/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript" src="${ctx}/assets/jquery/jquery-1.11.2.min.js"></script>
    <script type="text/javascript" src="${ctx}/assets/layer/layer.js"></script>
    <script type="text/javascript" src="${ctx}/assets/laypage/laypage.js"></script>
    <script type="text/javascript">
        function fn_AddCompanies(companies) {
            var ids = $.map($('input[name="defaultCompanyId"]'), function (n) {
                return $(n).val();
            });
            $.each(companies, function (i, co) {
                if ($.inArray(co.id, ids) == -1) {
                    var ele = '<div class="radio"><a href="javascript:" onclick="$(this).parent().remove()"><i class="fa fa-times"></i></a>&nbsp;&nbsp;&nbsp;<label>' +
                            '<input type="radio" name="defaultCompanyId" value="' + co.id + '" >' +
                            '<input type="hidden" name="companyList" value="' + co.id + '" />' + co.name + '</label></div>';
                    $('#divCompanyList').append(ele);
                }
            });
        }
        $(function () {
            $('#btnSelectCompany').click(function () {
                layer.open({
                    type: 2,
                    title: '选择添加药厂',
                    area: ['650px', '500px'],
                    scrollbar: false,
                    content: '/fragment/companies'
                });
            });
        })
    </script>
    <script>
        $(function () {
            var index = parent.layer.getFrameIndex(window.name);
            $('#btnClose').click(function () {
                parent.layer.close(index);
            });
        });
    </script>
</head>
<body>
<div style="margin: 20px;">
    <form:form action="/rpCate/save" cssClass="form-horizontal" method="post" modelAttribute="category">
        <form:hidden path="id"/>
        <div class="form-group has-error">
            <label for="txtName" class="col-xs-3 col-sm-3 control-label">类别名称</label>

            <div class="col-xs-9 col-sm-9">
                <form:input path="name" cssClass="form-control" id="txtName" placeholder="请输入名称"/>
            </div>
        </div>
        <%--<div class="form-group">
            <label for="txtSpec" class="col-xs-3 col-sm-3 control-label">类别说明</label>

            <div class="col-xs-9 col-sm-9">
                <form:textarea path="remark" cssClass="form-control" id="txtspec" rows="2"></form:textarea>
            </div>
        </div>--%>

        <div class="form-group">
            <h5 class="col-xs-offset-3 col-sm-offset-3 col-xs-9 col-sm-9 text-danger"> ${msg} </h5>
        </div>
        <div class="form-group">
            <div class="col-xs-offset-3 col-sm-offset-3 col-xs-9 col-sm-9">
                <button type="submit" class="btn btn-primary"><i class="fa fa-save"></i> 保存类别</button>
                <%--<a href="/rpCate/add" class="btn btn-success"><i class="fa fa-plus"></i> 继续新增</a>--%>
                <button id="btnClose" class="btn btn-default" style="width:80px; margin-left: 10px;">关闭</button>
            </div>
        </div>

    </form:form>
</div>
<script type="text/javascript" src="${ctx}/assets/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>