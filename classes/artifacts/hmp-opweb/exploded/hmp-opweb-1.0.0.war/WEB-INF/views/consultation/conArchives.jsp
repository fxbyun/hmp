<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/8/18
  Time: 9:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springside.org.cn/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>档案填写</title>
    <script>
        $(function () {
            $("#person").addClass("active");
            var birthday=$("#txtBirthday").val().replace("-","/").replace("-","/");
            $("#txtBirthday").val(birthday);
        })
    </script>
    <script type="text/javascript">

        $(function () {
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
            <c:if test="${not empty doctorId}">
            var flag = "${doctorId}";
            if (flag != "") {
                var doctorId = ${doctorId};
                var appointListId = ${appointListId};
                if (doctorId != undefined) {
                    $("#txtMobile").attr("readonly", "");
                    $("#fromMe").attr("action", "${ctx}/outpatient/savePatient?doctorId=" + doctorId + "&appointListId=" + appointListId);
                }
            }
            </c:if>


        });
    </script>
</head>
<body>
<div class="warp">
    <form:form action="${ctx}/personal/savePatient" id="fromMe" method="post" modelAttribute="patient"
               onsubmit="pullPinYin()">
        <div class="out-detail text-center">
        <i class="fa fa-angle-left" onclick="javascript:history.go(-1)"></i>
        <span class="text-center">档案填写</span>
    </div>
    <div class="wx-consultation">
        <div class="con-file">
            <form:hidden path="id" />
            <form:hidden path="uid" />
            <form:hidden path="province"></form:hidden>
            <form:hidden path="city"></form:hidden>
            <p>
                <span>姓名：</span>
                <form:input path="name" class="form-control" id="txtName" placeholder="姓名" />
                <form:input path="helpCode" class="form-control" id="txtHelpCode" style="display:none;" />
            </p>
            <div><span>性别：</span>
                <div>
                    <form:bsradiobuttons path="gender" items="${genderMap}" readonly="true" labelCssClass="radio-inline" />
                </div>

            </div>

            <p>
                <span>出生年月：</span>
                <form:input path="birthday" class="form-control form_date" id="txtBirthday" placeholder="生日"  readonly="true" />
            </p>
            <p>
                <span>手机：</span>
                <form:input path="mobile" class="form-control required" readonly="" id="txtMobile" placeholder="手机"/>
            </p>
            <p>
                <span>身份证：</span>
                <form:input path="sfId" class="form-control" id="txtSfId" maxlength="18" placeholder="身份证号" />
            </p>
            <p>
                <span style="height: 8em;">住址：</span>
                <form:select path="provinceNo" items="${provinceList}" class="form-control" itemLabel="areaName"
                             itemValue="areaNo"/>
                <form:select path="cityNo" items="${cityList}" class="form-control" itemLabel="areaName"
                             itemValue="areaNo" cssStyle="margin-top:10px"/>
                <form:select path="areaNo" items="${areaList}" class="form-control" itemLabel="areaName"
                             itemValue="areaNo" cssStyle="margin-top:10px"/>
            </p>
            <p><span>详细地址：</span>
                <form:input path="area" class="form-control" id="txtArea" maxlength="18" placeholder="街道" />
            </p>

        </div>

    </div>
    <button type="button" onclick="subMitForm()" class="btn btn-success btn-sure" style="width: 100%;">完成</button>
    </form:form>
</div>
<link href="${ctx}/assets/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" type="text/css"
      rel="stylesheet">
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
        var idCard = $("#txtSfId").val();
        var name = $("#txtName").val();
        var reg = new RegExp(/^1[3458][0-9]\d{8}$/);
        var reg2 = new RegExp(/^[1-9]{1}[0-9]{14}$|^[1-9]{1}[0-9]{16}([0-9]|[xX])$/);
        //debugger
        if(sMobile.length!=11){
            alert("手机号输入错误!");
            return false
        }else if(!reg.test(sMobile)){
            layer.msg("请输入正确格式的手机号码！")
            return false
        } else {
        }

        if(idCard!=null&&idCard!=""){
            if(!reg2.test(idCard)){
                layer.msg("请输入正确格式的身份证号码！")
                return false
            }
        }

        if(name==""||name==null){
            layer.msg("姓名不能为空！")
            return false;
        }

        if(isEmojiCharacter(name)){
            layer.msg("姓名不能包含emoji表情符号！")
            return false;
        }
        return true;
    }
    

    

    function subMitForm() {
        if(checkMobile()){
            $("#fromMe").submit();
        };
    }
    //helpCode患者姓名拼音首字母
    function pullPinYin(){
        $("#txtHelpCode").val(pinyin.getCamelChars($("#txtName").val()));
        return true;
    }

    //判断输入是否包含emoji表情
    function isEmojiCharacter(substring) {
        for ( var i = 0; i < substring.length; i++) {
            var hs = substring.charCodeAt(i);
            if (0xd800 <= hs && hs <= 0xdbff) {
                if (substring.length > 1) {
                    var ls = substring.charCodeAt(i + 1);
                    var uc = ((hs - 0xd800) * 0x400) + (ls - 0xdc00) + 0x10000;
                    if (0x1d000 <= uc && uc <= 0x1f77f) {
                        return true;
                    }
                }
            } else if (substring.length > 1) {
                var ls = substring.charCodeAt(i + 1);
                if (ls == 0x20e3) {
                    return true;
                }
            } else {
                if (0x2100 <= hs && hs <= 0x27ff) {
                    return true;
                } else if (0x2B05 <= hs && hs <= 0x2b07) {
                    return true;
                } else if (0x2934 <= hs && hs <= 0x2935) {
                    return true;
                } else if (0x3297 <= hs && hs <= 0x3299) {
                    return true;
                } else if (hs == 0xa9 || hs == 0xae || hs == 0x303d || hs == 0x3030
                        || hs == 0x2b55 || hs == 0x2b1c || hs == 0x2b1b
                        || hs == 0x2b50) {
                    return true;
                }
            }
        }
    }


    $(function () {
        if($("#txtMobile").val()==null||$("#txtMobile").val()==""){
            $("#txtMobile").attr("readonly",false)
        }


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
            $("#txtArea").val(text);
        });
    });
</script>
</body>
</html>
