<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springside.org.cn/tags/form" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<div id="body" style="height: 120%">
    <section id="main">
        <div class="container">
            <div class="row-fluid">
                <div class="span12">
                    <div class="content-box">
                        <div class="content-box-header">
                            <i class="icon-user"></i> 修改医生信息
                        </div>
                        <div class="row-fluid">
                            <div style="width: 600px;margin: 10px auto;">
                                <form:form modelAttribute="doctor" method="post" action="${ctx}/doctor/save"
                                           id="doctorForm">
                                    <form:hidden path="id"></form:hidden>
                                    <form:hidden path="province"></form:hidden>
                                    <form:hidden path="city"></form:hidden>
                                    <form:hidden path="area"></form:hidden>
                                    <table border="0">
                                        <tr>
                                            <td align="right"><b>门诊名称:</b></td>
                                            <td colspan="3"><input type="text" style="width:100%"
                                                                   name="outpatientService"
                                                                   value="${doctor.outpatientService}"/></td>
                                        </tr>
                                        <tr>
                                            <td align="right"><b>门诊地址:</b></td>
                                            <td colspan="3">
                                                <form:select path="provinceNo" items="${provinceList}"
                                                             itemLabel="areaName" itemValue="areaNo"></form:select>
                                                <br/>
                                                <form:select path="cityNo" items="${cityList}" itemLabel="areaName"
                                                             itemValue="areaNo"></form:select><br/>
                                                <form:select path="areaNo" items="${areaList}" itemLabel="areaName"
                                                             itemValue="areaNo"></form:select> <br/>
                                                <input type="text" style="width:100%" name="businessAddr"
                                                       value="${doctor.businessAddr}"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td align="right"><b>医生类型:</b></td>
                                            <td colspan="3">
                                                <select name="doctorType" id="doctorType" <c:if
                                                        test="${doctor.doctorType=='Sub_Doctor'}"> readonly="true" disabled </c:if>  >
                                                    <option value="Common_Doctor"
                                                            <c:if test="${empty doctor.doctorType || doctor.doctorType=='Common_Doctor'}">selected</c:if>
                                                    >普通医生
                                                    </option>
                                                    <option value="Clinic_Boss"
                                                            <c:if test="${  doctor.doctorType=='Clinic_Boss'}">selected</c:if>
                                                    >主治医生
                                                    </option>
                                                    <c:if test="${   doctor.doctorType=='Sub_Doctor'  }">
                                                        <option value="Sub_Doctor"
                                                                <c:if test="${  doctor.doctorType=='Sub_Doctor'}">selected</c:if>
                                                        >子帐号
                                                        </option>
                                                    </c:if>

                                                </select>
                                            </td>

                                        </tr>
                                        <tr>
                                            <td align="right"><b>法人:</b></td>
                                            <td><input type="text" name="legal" class="form_date"
                                                       value="${doctor.legal}"/></td>
                                            <td align="right"><b>法人身份证:</b></td>
                                            <td><input type="text" name="legalCard" value="${doctor.legalCard}"/></td>
                                        </tr>
                                        <tr>
                                            <td align="right"><b>营业执照号码:</b></td>
                                            <td><input type="text" name="businessLicense"
                                                       value="${doctor.businessLicense}"/></td>
                                            <td align="right"><b>姓名:</b></td>
                                            <td><input type="text" name="name" value="${doctor.name}"/></td>
                                        </tr>
                                        <tr>
                                            <td align="right"><b>性别:</b></td>
                                            <td>&nbsp;&nbsp;<form:bsradiobuttons path="gender" items="${genderMap}"
                                                                                 labelCssClass="inline"/></td>
                                            <td align="right"><b>手机号码:</b></td>
                                            <td><input type="text" name="mobile" value="${doctor.mobile}"/></td>
                                        </tr>
                                        <tr>
                                            <td align="right"><b>电子邮箱:</b></td>
                                            <td><input type="text" name="email" value="${doctor.email}"/></td>
                                            <td align="right"><b>身份证号:</b></td>
                                            <td><input type="text" name="card" value="${doctor.card}"/></td>
                                        </tr>
                                        <tr>
                                            <td align="right"><b>职业资格:</b></td>
                                            <td colspan="3"><input type="text" style="width: 100%" name="certificate"
                                                                   value="${doctor.certificate}"/></td>
                                        </tr>
                                        <tr>
                                            <td align="right"><b>擅长领域:</b></td>
                                            <td colspan="3"><textarea style="width: 100%" name="specialty"
                                                                      rows="2">${doctor.specialty}</textarea></td>
                                        </tr>
                                        <tr>
                                            <td align="right"><b>个人简介:</b></td>
                                            <td colspan="3"><textarea style="width: 100%" id="intro" name="intro"
                                                                      rows="2">${doctor.intro}</textarea></td>
                                        </tr>
                                        <tr>
                                            <td align="right"><b>尾部打印信息:</b></td>
                                            <td colspan="3"><input type="text" style="width: 100%" name="printInfo"
                                                                   value="${doctor.printInfo}"/></td>
                                        </tr>
                                        <tr>
                                            <td colspan="4" style="text-align: center">
                                                <button id="save" type="submit" class="btn btn-info">保存</button>
                                                &nbsp;&nbsp;&nbsp;
                                                <a class="btn btn-info" href="${ctx}/doctor/list">返回列表</a>&nbsp;&nbsp;&nbsp;
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
            <div>©2015 深圳市乔北科技有限责任公司版权所有 <a href="http://www.miitbeian.gov.cn/publish/query/indexFirst.action">粤ICP备15089657号-2</a>
            </div>
        </h5>
    </footer>
</div>
<script type="text/javascript">
    $(function () {
        $("#provinceNo").change(function () {
            $("#cityNo").empty();
            $("#areaNo").empty();
            var proId = $(this).val();
            $.getJSON("${ctx}/getNations/" + proId, function (result) {
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
            $.getJSON("${ctx}/getNations/" + cityId, function (result) {
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