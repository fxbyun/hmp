<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springside.org.cn/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <link href="${ctx}/assets/bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
    <link rel="stylesheet" href="${ctx}/assets/styles/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/assets/styles/style.css">
    <link href="${ctx}/assets/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" type="text/css"
          rel="stylesheet"/>

    <script type="text/javascript" src="${ctx}/assets/jquery/jquery-1.11.2.min.js"></script>
    <script type="text/javascript" src="${ctx}/assets/layer/layer.js"></script>

    <script type="text/javascript">
        $(function () {
            var index = parent.layer.getFrameIndex(window.name);

            $("#btnClosePatient").click(function () {
                parent.layer.closeAll();
            })


            $(".form-horizontal").validate();

            $("#gender3").parent().hide();

            if ("${msg}" != "") {
                layer.msg("${msg}");
            }
//            $("#txtBirthday").val($("#txtBirthday").val().replace("-", "/"));
//            $("#txtBirthday").val($("#txtBirthday").val().replace("-", "/"));
        });
    </script>
    <style>
        select {
            margin-right: 15px;
            min-width: 60px;
        }

        label {
            padding: 0;
        }
    </style>
</head>
<body style="background-color: #fff;">
<div style="margin: 20px;">
    <form:form action="/table/add" id="fromMe" cssClass="form-horizontal" method="post" modelAttribute="tableModify"
    >
        <form:hidden path="id"/>
        <form:hidden path="status"/>

        <div class="form-group">
            <label for="txtName" class="col-xs-2 col-sm-2 control-label text-right">表名</label>

            <div class="col-xs-10 col-sm-10">
                <form:input path="tableName" class="form-control required" id="txtName" placeholder="姓名"/>
            </div>
        </div>
        <div class="form-group">
            <label for="txtSfId" class="col-xs-2 col-sm-2 control-label text-right">操作</label>

            <div class="col-xs-10 col-sm-10">
                <form:select path="type" id="txtSfId" items="${tableModyfiTypeMap}"></form:select>
            </div>
        </div>
        <div class="form-group">
            <label for="columnName" class="col-xs-2 col-sm-2 control-label text-right">列名</label>

            <div class="col-xs-10 col-sm-10">
                <form:input path="columnName" class="form-control  " id="columnName" placeholder="列名"
                />
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-2 col-sm-2 control-label text-right">列类型</label>

            <div class="col-xs-10 col-sm-10">
                <form:input path="columnType" class="form-control  " placeholder="列类型"/>
            </div>
        </div>
        <div class="form-group">
            <label for="txtMobile" class="col-xs-2 col-sm-2 control-label text-right">新列名<span
                    style="color: red">*</span></label>

            <div class="col-xs-10 col-sm-10">
                <form:input path="columnNameNewName" class="form-control " id="txtMobile" placeholder="新列名"/>
            </div>
        </div>


        <div class="form-group">
            <div class="col-xs-12 col-sm-12 text-center">
                <h4 class="text-danger">${msg}</h4>
            </div>
        </div>
        <div class="form-group">
            <div class="col-xs-12 col-sm-12 text-center">
                <button type="submit" class="btn btn-success"><i class="fa fa-save"></i> 保存
                </button>
                <button id="btnClosePatient" type="button" class="btn btn-default"><i class="fa fa-times"></i> 关闭
                </button>
            </div>
        </div>
    </form:form>
</div>
<script type="text/javascript" src="${ctx}/assets/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript"
        src="${ctx}/assets/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="${ctx}/assets/jquery-validation/1.11.1/jquery.validate.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/jquery-validation/1.11.1/messages_bs_zh.js"></script>
<script>

</script>
</body>
</html>