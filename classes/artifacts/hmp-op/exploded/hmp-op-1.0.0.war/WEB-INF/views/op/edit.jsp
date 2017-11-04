<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springside.org.cn/tags/form" %>
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
    <link href="${ctx}/assets/bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
    <link href="${ctx}/assets/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" type="text/css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/assets/styles/style.css">
    <script src="${ctx}/assets/jquery/jquery-1.11.2.min.js" type="text/javascript"></script>
    <script>
        var useragent = navigator.userAgent;
        if (useragent.match(/MicroMessenger/i) != 'MicroMessenger') {
            // 这里警告框会阻塞当前页面继续加载
            alert('已禁止本次访问：您必须使用微信内置浏览器访问本页面！');
            // 以下代码是用javascript强行关闭当前页面
            var opened = window.open('about:blank', '_self');
            opened.opener = null;
            opened.close();
        }
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
            $("#save").click(function(){
                $("#opForm").submit();
                return false;
            });
        });
    </script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <div class="text-left tcb"><h4>个人信息填写/修改</h4></div>
            <div class="well well-sm emr">
                <form:form modelAttribute="op" action="${ctx}/edit" method="post" class="form-inline" id="opForm">
                    <form:hidden path="id"/>
                    <form:hidden path="province" ></form:hidden>
                    <form:hidden path="city" ></form:hidden>
                    <form:hidden path="area" ></form:hidden>
                    <table>
                        <tr style="height:40px">
                            <td align="right"><b>姓名：</b></td>
                            <td><input type="text" class="form-control w180" id="name" name="name" value="<c:out value='${op.name}'/>"/></td>
                        </tr>
                        <tr style="height:40px">
                            <td align="right"><b>性别：</b></td>
                            <td><form:bsradiobuttons path="gender" items="${genderMap}" labelCssClass="radio-inline"/></td>
                        </tr>
                        <tr style="height:40px">
                            <td align="right"><b>出生年月：</b></td>
                            <td><input type="text" class="form-control w120 form_date" id="birthday" name="birthday" value="<fmt:formatDate value='${op.birthday}' pattern='yyyy/MM/dd'/>"/></td>
                        </tr>
                        <tr style="height:40px">
                            <td align="right"><b>手机：</b></td>
                            <td><input type="text" class="form-control w180" id="mobile" name="mobile" value="${op.mobile}"/></td>
                        </tr>
                        <tr style="height:40px">
                            <td align="right"><b>身份证：</b></td>
                            <td><input type="text" class="form-control w180" id="sfId" name="sfId" value="${op.sfId}"/></td>
                        </tr>
                        <tr style="height:80px">
                            <td align="right"><b>住址：</b></td>
                            <td>
                                <form:select path="provinceNo" items="${provinceList}" itemLabel="areaName" itemValue="areaNo"></form:select><br/>
                                <form:select path="cityNo" items="${cityList}" itemLabel="areaName" itemValue="areaNo" cssStyle="margin-top:5px"></form:select> <br/>
                                <form:select path="areaNo" items="${areaList}" itemLabel="areaName" itemValue="areaNo" cssStyle="margin-top:5px"></form:select> <br/>
                                <form:input path="address" class="form-control" id="txtAddress" placeholder="住址" cssStyle="margin-top:5px"/>
                            </td>
                        </tr>
                        <tr style="height:45px">
                            <td colspan="2">
                                <div class="text-center">
                                    <button type="button" class="btn btn-primary btn-sm" id="save">保存</button>
                                    <a class="btn btn-primary btn-sm" href="${ctx}/pwd/${op.id}">修改密码</a>
                                </div>
                            </td>
                        </tr>
                    </table>
                </form:form>
            </div>
        </div>
    </div>
</div>
<script src="${ctx}/assets/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/assets/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script>
    $(function () {
        $("#provinceNo").change(function(){
            $("#cityNo").empty();
            $("#areaNo").empty();
            var proId = $(this).val();
            $.getJSON("${ctx}/getNations/" + proId,function(result){
                $.each(result.data,function(i,n){
                    $("#cityNo").append("<option value='"+ n.areaNo+"'>"+ n.areaName+"</option>")
                });
                $("#cityNo").change();
            });
            var text = $("#provinceNo option:selected").text();
            $("#province").val(text);
        });
        $("#cityNo").change(function(){
            $("#areaNo").empty();
            var cityId = $(this).val();
            $.getJSON("${ctx}/getNations/" + cityId,function(result){
                $.each(result.data,function(i,n){
                    $("#areaNo").append("<option value='"+ n.areaNo+"'>"+ n.areaName+"</option>")
                });
                $("#areaNo").change();
            });
            var text = $("#cityNo option:selected").text();
            $("#city").val(text);
        });
        $("#areaNo").change(function(){
            var text = $("#areaNo option:selected").text();
            $("#area").val(text);
        });
    });
</script>
</body>
</html>
