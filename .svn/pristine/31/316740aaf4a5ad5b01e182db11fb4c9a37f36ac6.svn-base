<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springside.org.cn/tags/form" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<div id="body" style="height: 100%">
    <section id="main">
        <div class="container">
            <div class="row-fluid">
                <div class="span12">
                    <div class="content-box">
                        <div class="content-box-header">
                            <i class="icon-user"></i> 患者详情
                        </div>
                        <div class="row-fluid">
                            <div style="width: 400px;margin: 10px auto;">
                                <form:form modelAttribute="patient" method="post" action="${ctx}/patient/save" id="opForm">
                                    <form:hidden path="id"></form:hidden>
                                    <form:hidden path="province"></form:hidden>
                                    <form:hidden path="city"></form:hidden>
                                    <form:hidden path="area"></form:hidden>
                                    <table border="0">
                                        <tr>
                                            <td align="right"><b>姓名：</b></td>
                                            <td><input type="text" name="name" value="${patient.name}"/></td>
                                        </tr>
                                        <tr>
                                            <td align="right"><b>卡号：</b></td>
                                            <td><input type="text" name="uid" id="uid" value="${patient.uid}"/>
                                                <button id="updateCard" class="btn btn-info" href="${ctx}/patient/list">换卡</button></td>
                                        </tr>
                                        <tr>
                                            <td><b>出生年月：</b></td>
                                            <td><input type="text" name="birthday" class="form_date" value="<fmt:formatDate value="${patient.birthday}" pattern="yyyy/MM/hh"/>"/></td>
                                        </tr>
                                        <tr>
                                            <td align="right"><b>住址：</b></td>
                                            <td>
                                                <form:select path="provinceNo" items="${provinceList}" itemLabel="areaName" itemValue="areaNo"></form:select> <br/>
                                                <form:select path="cityNo" items="${cityList}" itemLabel="areaName" itemValue="areaNo"></form:select> <br/>
                                                <form:select path="areaNo" items="${areaList}" itemLabel="areaName" itemValue="areaNo"></form:select><br/>
                                                <input type="text" name="address" value="${patient.address}"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td align="right"><b>性别：</b></td>
                                            <td><form:bsradiobuttons path="gender" items="${genderMap}" labelCssClass="inline"/></td>
                                        </tr>
                                        <tr>
                                            <td align="right"><b>身份证：</b></td>
                                            <td><input type="text" name="sfId" value="${patient.sfId}"/></td>
                                        </tr>
                                        <tr>
                                            <td><b>手机号码：</b></td>
                                            <td><input type="text" name="mobile" value="${patient.mobile}"/></td>
                                        </tr>
                                        <tr>
                                            <td><b>电子邮箱：</b></td>
                                            <td><input type="text" name="email" value="${patient.email}"/></td>
                                        </tr>
                                        <tr>
                                            <td><b>历史病症：</b></td>
                                            <td>
                                                <c:if test="${not empty patient.patientTagList}">
                                                    <c:forEach items="${patient.patientTagList}" var="tag">
                                                        ${tag.name} &nbsp;
                                                    </c:forEach>
                                                </c:if>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="2" style="text-align: center">
                                                <button id="save" class="btn btn-info">保存</button>&nbsp;&nbsp;&nbsp;
                                                <button id="retPwd" class="btn btn-info">重置密码</button>&nbsp;&nbsp;&nbsp;
                                                <a class="btn btn-info" href="${ctx}/patient/list">返回列表</a>&nbsp;&nbsp;&nbsp;
                                            </td>
                                        </tr>
                                    </table>
                                </form:form>
                                <c:if test="${error != null}">
                                    <div class="alert alert-danger mt10">${error}</div>
                                </c:if>
                                <c:if test="${msg != null}">
                                    <div class="alert alert-success mt10">${msg}</div>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <footer class="footer">
        <h5 class="transparent-text center">
            <div>©2015 深圳市乔北科技有限责任公司版权所有 <a href="http://www.miitbeian.gov.cn/publish/query/indexFirst.action">粤ICP备15089657号-2</a></div>
        </h5>
    </footer>
</div>
<script type="text/javascript">
    $(function(){
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
        $("#retPwd").click(function(){
            var opId = $("#id").val();
            $.getJSON("${ctx}/patient/retPwd/" + opId,function(result){
                if(result.success){
                    layer.alert("密码已重置。");
                }
            });
            return false;
        });
        $("#updateCard").click(function(){
            var opId = $("#id").val();
            var uid = $("#uid").val();
            $.getJSON("${ctx}/patient/updateCard/" + opId + "/" + uid,function(result){
                if(result.success){
                    layer.alert("卡已更换。");
                }else{
                    layer.alert(result.msg);
                }
            });
            return false;
        });
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