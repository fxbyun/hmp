<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html lang="cn">
<head>
    <title>门诊管理</title>
    <link rel="stylesheet" href="${ctx}/assets/star/css/star-rating.min.css" type="text/css"/>
    <script src="${ctx}/assets/star/js/star-rating.min.js" type="text/javascript"></script>
    <script>
        $(function () {
            $("#nav-system").addClass("active");
        });
        $(function () {
            //设置偶数行和奇数行
            $("tbody>tr:odd").addClass("backg");   //为奇数行设置样式(添加样式类)
            $("tbody>tr:even").addClass("backg2");  // 为偶数行设置样式类
            <c:if test="${not empty msg}">
            layer.msg("${msg}");
            console.info("${msg}");
            </c:if>
        });
    </script>
</head>
<body>
<div class="manage infro-set">
    <div class="container">
        <ul class="navigation">
            <li class="active"><a href="${ctx}/infro" class="btn btn-default">信息设置</a></li>
            <c:if test="${doctor.doctorType!='Sub_Doctor'}">
                <li><a href="${ctx}/advertSet" class="btn btn-default">终端机设置</a></li>
                <li><a href="${ctx}/employee" class="btn btn-default">员工管理</a></li>
            </c:if>
        </ul>


        <div class="infro-content">
            <div class="row">
                <div class="col-md-2 col-sm-2 text-center" style="padding-right: 0;">
                    <div class="infro-left">
                        <div class="doctor-infro">
                            <c:if test="${portraitFile == null}">
                                <img class="img-circle" src="${ctx}/assets/styles/images/1.png"/>
                            </c:if>
                            <c:if test="${portraitFile != null}">
                                <img class="img-circle" src="${ctx}/temp/${portraitFile}"/>
                            </c:if>
                        </div>
                        <h4><i class="fa fa-mars"></i> ${doctor.name}</h4>
                        <input id="grade" name="grade" disabled="disabled" value="${average}" type="number"
                               class="rating" min=0 max=10 step=0.1
                               data-size="xs">

                        <p>平均分${average}</p>

                        <p>总分 ${doctor.integration}</p>
                        <img width="120" height="120" style="margin-top: 100px;"
                             src="${ctx}/assets/images/pharcode.jpg">
                    </div>
                </div>
                <div class="col-md-10 col-sm-10 basic-infro" style="padding: 0 60px 0 30px;">
                    <p>门诊名称 : <span>${doctor.outpatientService}</span></p>

                    <p>营业执照号码：<span>${doctor.businessLicense}</span></p>

                    <p>门诊地址：<span>${doctor.province}${doctor.city}${doctor.area}<c:out
                            value='${doctor.businessAddr}'/></span></p>

                    <div class="row" style="margin: 0; border-top:1px solid #ccc;">
                        <div class="col-md-6 col-sm-6" style="margin-left: -15px; margin-top:30px;">
                            <p>法人代表：<span>${doctor.legal}</span></p>

                            <p>手机号码：<span>${doctor.mobile}</span></p>

                            <p>从医年限：
                                <span>
                                    <c:if test="${not empty doctor.seniority}">${doctor.seniority}年</c:if>
                                </span>
                            </p>

                            <p>身份证号码： <span>${doctor.card}</span></p>
                        </div>
                        <div class="col-md-6 col-sm-6" style="margin-left: -15px; margin-top:30px;">
                            <p>法人身份证号：<span>${doctor.legalCard}</span></p>

                            <p>职业资格：<span><c:out value='${doctor.certificate}'/> </span></p>

                            <p>电子邮箱：<span>${doctor.email}</span></p>
                        </div>
                    </div>
                    <p>擅长领域：<span><c:out value='${doctor.specialty}'/></span></p>

                    <p>个人简介：<span><c:out value='${doctor.intro}'/></span></p>
                    <p>所属科室：
                        <span>
                        <c:choose>
                            <c:when test="${not empty doctor.deptName}">${doctor.deptName}</c:when>
                            <c:otherwise>医生处</c:otherwise>
                        </c:choose>
                    </span>
                    </p>

                    <p>尾部打印信息：<span><c:out value='${doctor.printInfo}'/></span></p>

                    <p>打印尺寸选择：<span><c:out value='${doctor.printType}'/></span></p>

                    <p>打印模式设置：
                        <span>
                            <c:out value='${doctor.printModel}'/>
                        </span>
                    </p>
                    <div>
                        <p>单独打印模块：<span><c:out value='${doctor.needAlonePrinTypeStrings}'/></span></p>


                        <p>发送回访短信：
                            <%--<span>默认发送回访短信</span>--%>
                            <input type="radio" name="autoSend" style="margin-left: 10px;" disabled
                                   <c:if test="${'是'.equals(doctor.autoSend)}">checked='checked'</c:if>
                            > 是
                            <input type="radio" name="autoSend" style="margin-left: 5px;" disabled
                                   <c:if test="${!'是'.equals(doctor.autoSend)}">checked='checked'</c:if>
                            > 否
                            <c:if test="${'是'.equals(doctor.autoSend)}">
                                <span style="padding-left: 20px; padding-right: 5px;">就诊过后</span>

                                <span><c:choose><c:when
                                        test="${not empty doctor.autoSendDay}">${doctor.autoSendDay}</c:when><c:otherwise>0</c:otherwise>
                                </c:choose>&nbsp;&nbsp;天发送</span>
                            </c:if>
                        </p>

                        <%--允许子医生进行费用更改：--%>
                        <c:if test="${doctor.doctorType!='Sub_Doctor'}">
                            <p>允许子医生进行费用更改：
                                <input type="radio" name="allowSubDoctorUpdatePrice" value="true"
                                       <c:if test="${doctor.allowSubDoctorUpdatePrice}">checked='checked'</c:if>
                                       disabled> 允许
                                <input type="radio" name="allowSubDoctorUpdatePrice" style="margin-left: 5px;"
                                       value="false"
                                       <c:if test="${!doctor.allowSubDoctorUpdatePrice}">checked='checked'</c:if>
                                       disabled> 不允许
                            </p>
                        </c:if>

                        <c:if test="${doctor.doctorType!='Sub_Doctor'}">
                            <p>允许普通护士进行费用更改：
                                <input type="radio" name="allowNurseUpdatePrice" value="true"
                                       <c:if test="${doctor.allowNurseUpdatePrice}">checked='checked'</c:if>
                                       disabled> 允许
                                <input type="radio" name="allowNurseUpdatePrice" style="margin-left: 5px;"
                                       value="false"
                                       <c:if test="${!doctor.allowNurseUpdatePrice}">checked='checked'</c:if>
                                       disabled> 不允许
                            </p>
                        </c:if>

                        <p>发送健康卡激活短信：
                            <%--<span>发送激活通知短信</span>--%>
                            <input type="radio" name="autoSendActivateMsg" style="margin-left: 10px;" disabled
                                   <c:if test="${'是'.equals(doctor.autoSendActivateMsg)}">checked='checked'</c:if>
                            > 是
                            <input type="radio" name="autoSendActivateMsg" style="margin-left: 5px;" disabled
                                   <c:if test="${!'是'.equals(doctor.autoSendActivateMsg)}">checked='checked'</c:if>
                            > 否

                        </p>

                        <p style="font-size:16px; color: #858484;"><i class="fa fa-exclamation-circle"
                                                                      style="color:#eab065; padding-right:5px; vertical-align:middle; padding-bottom:2px;"></i>
                            温馨提示：更加完善的信息资料有助于提高患者对您的关注及信任程度</p>
                    </div>


                    <div class="change-bottom text-center">
                        <a href="${ctx}/infroChange/<shiro:principal property='id'/>" class="btn btn-success">信息修改</a>
                        <button class="btn btn-success" id="btnAmendPwd">修改密码</button>
                    </div>
                </div>

            </div>
        </div>

    </div>

</div>

<script type="text/javascript">
    $("#btnAmendPwd").click(function () {
        layer.open({
            type: 2,
            title: '修改密码',
            area: ['370px', '400px'],
            scrollbar: false,
            content: '/fragment/doctor/updatePwd'
        });
    });
</script>
</body>
</html>