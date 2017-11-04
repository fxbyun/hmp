<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <link href="${ctx}/assets/bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
    <link rel="stylesheet" href="${ctx}/assets/styles/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/assets/styles/style.css">
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/styles/green.css" type="css" needChengStyle="true"/>
    <link href="${ctx}/assets/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" type="text/css"
          rel="stylesheet" />
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/assets/html5shiv/html5shiv.js"></script>
    <script type="text/javascript" src="${ctx}/assets/respond/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript" src="${ctx}/assets/jquery/jquery-1.11.2.min.js"></script>
    <script type="text/javascript" src="${ctx}/assets/layer/layer.js"></script>
    <script type="text/javascript">
        $(function () {
            var index = parent.layer.getFrameIndex(window.name);
            $('#btnClosePatient').click(function () {
                parent.layer.close(index);
            });
            $('.form_date').datetimepicker({
                language: 'zh-CN',
                format: 'yyyy/mm/dd',
                weekStart: 1,
                todayBtn: 1,
                autoclose: 1,
                todayHighlight: 1,
                startView: 2,
                minView: 2,
                forceParse: 0
            });
            $(".form-horizontal").validate();

            $("#gender3").parent().hide();
        });
    </script>
    <style>
        select {
            margin-right: 15px;
        }
    </style>
</head>
<body style="background-color: #fff;">
<div style="margin: 20px;">
    <form:form action="/oldPatient/binDingCardPatient/save" id="fromMe" cssClass="form-horizontal" method="post"
               modelAttribute="patient">
        <form:hidden path="id" />
        <form:hidden path="uid" />
        <form:hidden path="province"></form:hidden>
        <form:hidden path="city"></form:hidden>
        <form:hidden path="area"></form:hidden>
        <input type="hidden" name="type" value="${type}">
        <input type="hidden" value="${oldPtient.id}" name="oldPtientId" />

        <div class="form-group">
            <label for="txtName" class="col-xs-2 col-sm-2 control-label text-right">姓名</label>

            <div class="col-xs-10 col-sm-10">
                <form:input path="name" class="form-control" id="txtName" placeholder="姓名" />
            </div>
        </div>
        <div class="form-group">
            <label for="txtSfId" class="col-xs-2 col-sm-2 control-label text-right">身份证</label>

            <div class="col-xs-10 col-sm-10">
                <form:input path="sfId" class="form-control" id="txtSfId" maxlength="18" placeholder="身份证号" />
            </div>
        </div>
        <div class="form-group">
            <label for="txtBirthday" class="col-xs-2 col-sm-2 control-label text-right">生日</label>

            <div class="col-xs-10 col-sm-10">
                <form:input path="birthday" class="form-control form_date" id="txtBirthday" placeholder="生日"
                            readonly="true" />
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-2 col-sm-2 control-label text-right">性别</label>

            <div class="col-xs-10 col-sm-10">
                <form:bsradiobuttons path="gender" items="${genderMap}" readonly="true" labelCssClass="radio-inline" />
            </div>
        </div>
        <div class="form-group">
            <label for="txtMobile" class="col-xs-2 col-sm-2 control-label text-right">手机<span
                    style="color: red">*</span></label>

            <div class="col-xs-10 col-sm-10">
                <form:input path="mobile" class="form-control required" id="txtMobile" placeholder="手机" />
            </div>
        </div>
        <div class="form-group">
            <label for="txtSn" class="col-xs-2 col-sm-2 control-label text-right">邮箱</label>

            <div class="col-xs-10 col-sm-10">
                <form:input path="email" class="form-control" id="txtSn" placeholder="邮箱" />
            </div>
        </div>
        <div class="form-group">
            <label for="txtAddress" class="col-xs-2 col-sm-2 control-label text-right">住址</label>

            <div class="col-xs-10 col-sm-10">
                <form:select path="provinceNo" items="${provinceList}" itemLabel="areaName"
                             itemValue="areaNo"></form:select>
                <form:select path="cityNo" items="${cityList}" itemLabel="areaName" itemValue="areaNo"
                             cssStyle="margin-top:10px"></form:select>
                <form:select path="areaNo" items="${areaList}" itemLabel="areaName" itemValue="areaNo"
                             cssStyle="margin-top:10px"></form:select> <br />
                <form:input path="address" class="form-control" id="txtAddress" placeholder="住址"
                            cssStyle="margin-top:10px" />
            </div>
        </div>
        <div class="form-group">
            <div class="col-xs-12 col-sm-12 text-center">
                <h4 class="text-danger">${msg}</h4>
            </div>
        </div>
        <div class="form-group">
            <div class="col-xs-12 col-sm-12 text-center">
                <button type="button" onclick="subMitForm()" class="btn btn-success"><i class="fa fa-save"></i> 保存
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
    //手机号码校验
    function checkMobile() {
        var sMobile = $("#txtMobile").val();


        if (sMobile.length != 11) {
            alert("手机号输入错误!");
            return false
        } else {
            return true;
        }


//
//        if (!(/^1[3|4|5|8][0-9]\d{4,8}$/.test(sMobile))) {
//            alert("手机号输入错误!");
//
//            return false;
//        } else {
//            return true;
//        }
    }

    function subMitForm() {

        if (checkMobile()) {
            $("#fromMe").submit();
        }
        ;


    }
    $(function () {
        $("#provinceNo").change(function () {
            $("#cityNo").empty();
            $("#areaNo").empty();
            var proId = $(this).val();
            $.getJSON("${ctx}/anon/getNations/" + proId, function (result) {
                $.each(result.data, function (i, n) {
                    $("#cityNo").append("<option value='" + n.areaNo + "'>" + n.areaName + "</option>")
                });
                $("#cityNo").change();
            });
            var text = $("#provinceNo option:selected").text();
            $("#province").val(text);
        });
        $("#cityNo").change(function () {
            $("#areaNo").empty();
            var cityId = $(this).val();
            $.getJSON("${ctx}/anon/getNations/" + cityId, function (result) {
                $.each(result.data, function (i, n) {
                    $("#areaNo").append("<option value='" + n.areaNo + "'>" + n.areaName + "</option>")
                });
                $("#areaNo").change();
            });
            var text = $("#cityNo option:selected").text();
            $("#city").val(text);
        });
        $("#areaNo").change(function () {
            var text = $("#areaNo option:selected").text();
            $("#area").val(text);
        });
    });
</script>
</body>
</html>