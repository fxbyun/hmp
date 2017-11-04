<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/8/31
  Time: 15:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<%@ include file="/WEB-INF/commons/staticInclude.jsp" %>
<html>
<head>
    <title>绑定健康卡</title>
    <style>
        .form-group {
            overflow: hidden;
            margin-bottom: 25px;
        }

        .control-label {
            font-weight: normal;
            color: #666;
        }

        .form-control {
            height: 50px;
            font-size: 30px;
        }

        select.form-control {
            margin-bottom: 10px;
            padding: 3px 12px;
        }

        input.form-control {
            font-size: 30px;
        }

        button.btn {
            font-size: 30px;
            width: 150px;
        }

        .datetimepicker table {
            width: 390px;
            height: 400px;
            font-size: 22px;
        }
    </style>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/getPinYin.js" type="js"/>
    <script>
        $(function () {
            var index = parent.layer.getFrameIndex(window.name);

            if ("${msg}" != "") {
                $.postJSON(ctx + "/advertising/registration/save", {
                    "udid": "${udid}",
                    "mobile": "",
                    "doctorId": "${doctorId}"
                }, function (result) {
                    if (result.success) {
                        parent.utlit_getAdvingSize();
                        parent.layer.closeAll();
                    }
                });
                /*parent.fn_ClickRegistr("激活成功，请您重新刷卡！",index,);
                 parent.layer.close(index);*/
            } else {
                layer.msg("请您先激活健康卡！")
            }

            $('#btnClosePatient').click(function () {
                parent.layer.close(index);
            });

            //初始化时间
            $('.form_date').datetimepicker({
                language: 'zh-CN',
                format: 'yyyy/mm/dd',
                weekStart: 1,
                todayBtn: 1,
                autoclose: 1,
                todayHighlight: 1,
                startView: 2,
                minView: 2,
                forceParse: 0,
                //开启年龄显示
                needShowAge: true
            });
            $("#gender3").parent().hide();

        })

        function pullPinYin() {
            $("#txtHelpCode").val(pinyin.getCamelChars($("#txtName").val()));
            return true;
        }
        function subMitForm() {
            if (checkMobile()) {
                pullPinYin();
                $("#fromMe").submit();
            }
        }
        //手机号码校验
        function checkMobile() {
            var sMobile = $("#txtMobile").val();
            if (sMobile.length != 11) {
                alert("手机号输入错误!");
                return false
            } else {
                return true;
            }
        }
    </script>
</head>
<body style="background-color: #fff; font-size: 30px;">
<form:form action="${ctx}/advertising/patient/save" id="fromMe" cssClass="form-horizontal" method="post"
           modelAttribute="patient"
           onsubmit="pullPinYin()">
    <form:hidden path="id"/>
    <form:hidden path="uid"/>
    <form:hidden path="udid"/>
    <form:hidden path="password"/>
    <form:hidden path="salt"/>
    <form:hidden path="plainPassword"/>
    <form:hidden path="province"></form:hidden>
    <form:hidden path="city"></form:hidden>
    <form:hidden path="area"></form:hidden>
    <input name="doctorId" value="${doctorId}" style="display: none"/>
    <div style="margin-top: 30px; padding: 0 30px;">
        <div class="form-group">
            <label for="txtName" class="col-xs-3 col-sm-3 control-label text-right">姓名</label>

            <div class="col-xs-9 col-sm-9">
                    <%--<input class="form-control" id="txtName" placeholder="姓名" />--%>
                <form:input path="name" class="form-control" id="txtName" placeholder="姓名"/>
                <form:input path="helpCode" class="form-control" id="txtHelpCode" style="display:none;"/>
            </div>
        </div>
            <%--<div class="form-group">--%>
            <%--<label for="txtSfId" class="col-xs-3 col-sm-3 control-label text-right">身份证</label>--%>

            <%--<div class="col-xs-9 col-sm-9">--%>
            <%--&lt;%&ndash;<input class="form-control" id="txtSfId" maxlength="18" placeholder="身份证号" />&ndash;%&gt;--%>
            <%--<form:input path="sfId" class="form-control" id="txtSfId" maxlength="18" placeholder="身份证号"/>--%>
            <%--</div>--%>
            <%--</div>--%>
        <div class="form-group">
            <label for="txtBirthday" class="col-xs-3 col-sm-3 control-label text-right">生日</label>

            <div class="col-xs-9 col-sm-9">
                    <%--<input path="birthday" class="form-control form_date" id="txtBirthday" placeholder="生日"--%>
                    <%--readonly="true" />--%>
                <form:input path="birthday" class="form-control form_date" id="txtBirthday" placeholder="生日"
                            readonly="true"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-3 col-sm-3 control-label text-right">性别</label>

            <div class="col-xs-9 col-sm-9">
                    <%--<input type="radio" name="sex" class="radio-inline">男--%>
                    <%--<input type="radio" name="sex" class="radio-inline">女--%>
                <form:bsradiobuttons path="gender" items="${genderMap}" readonly="true" labelCssClass="radio-inline"/>
            </div>
        </div>
        <div class="form-group">
            <label for="txtMobile" class="col-xs-3 col-sm-3 control-label text-right">手机<span
                    style="color: red">*</span></label>

            <div class="col-xs-9 col-sm-9">
                    <%--<input path="mobile" class="form-control required" id="txtMobile" placeholder="手机" />--%>
                <form:input path="mobile" class="form-control required" id="txtMobile" placeholder="手机"/>
            </div>
        </div>

        <div class="form-group more">
            <label for="more" class="col-xs-2 col-sm-2 control-label text-center" onclick="getMore()">更多<i id="more"
                                                                                                           class="fa fa-angle-double-up"></i></label>
            <span style="font-size: 12px;">为了便于及时与您取得联系，建议您完善以下信息</span>
        </div>
        <div class="showMoreTwo" style="display: none">
            <div class="form-group">
                <label for="txtSfId" class="col-xs-3 col-sm-3 control-label text-right">身份证</label>

                <div class="col-xs-9 col-sm-9">
                        <%--<input class="form-control" id="txtSfId" maxlength="18" placeholder="身份证号" />--%>
                    <form:input path="sfId" class="form-control" id="txtSfId" maxlength="18" placeholder="身份证号"/>
                </div>
            </div>
            <div class="form-group">
                <label for="txtSn" class="col-xs-3 col-sm-3 control-label text-right">邮箱</label>

                <div class="col-xs-9 col-sm-9">
                        <%--<input path="email" class="form-control" id="txtSn" placeholder="邮箱" />--%>
                    <form:input path="email" class="form-control" id="txtSn" placeholder="邮箱"/>
                </div>
            </div>
            <div class="form-group">
                <label for="txtAddress" class="col-xs-3 col-sm-3 control-label text-right">住址</label>

                <div class="col-xs-9 col-sm-9">
                        <%--<select class="form-control">--%>
                        <%--<option>广东省</option>--%>
                        <%--<option>山东省</option>--%>
                        <%--</select>--%>
                    <form:select cssClass="form-control"
                                 path="provinceNo"
                                 items="${provinceList}"
                                 itemLabel="areaName"
                                 itemValue="areaNo"
                    >

                    </form:select>

                        <%--<select class="form-control">--%>
                        <%--<option>深圳市</option>--%>
                        <%--</select>--%>
                    <form:select path="cityNo"
                                 items="${cityList}"
                                 itemLabel="areaName"
                                 itemValue="areaNo"
                                 cssClass="form-control">

                    </form:select>
                        <%--<select class="form-control">--%>
                        <%--<option>南山区</option>--%>
                        <%--</select>--%>
                    <form:select path="areaNo"
                                 items="${areaList}"
                                 itemLabel="areaName"
                                 itemValue="areaNo"
                                 cssClass="form-control"
                    >

                    </form:select>

                    <input path="address" class="form-control" id="txtAddress" placeholder="住址"
                           cssStyle="margin-top:10px"/>
                        <%--<form:select path="provinceNo" items="${provinceList}" itemLabel="areaName"
                                     itemValue="areaNo"></form:select>
                        <form:select path="cityNo" items="${cityList}" itemLabel="areaName" itemValue="areaNo"
                                     cssStyle="margin-top:10px"></form:select>
                        <form:select path="areaNo" items="${areaList}" itemLabel="areaName" itemValue="areaNo"
                                     cssStyle="margin-top:10px"></form:select> <br />
                        <form:input path="address" class="form-control" id="txtAddress" placeholder="住址"
                                    cssStyle="margin-top:10px" />--%>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="col-xs-12 col-sm-12 text-center">
                <h4 class="text-danger"><%--${msg}--%></h4>
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
    </div>

</form:form>


<script type="application/javascript">
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

    function getMore() {
        if ($('.showMoreTwo').css('display') == 'none') {
            $('.showMoreTwo').show()
        } else {
            $('.showMoreTwo').hide()
        }

    }
</script>
</body>
</html>
