<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<c:set var="ctx" value="${pageContext.request.contextPath}" />--%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<div id="body" style="height: 100%">
    <section id="main">
        <div class="container">
            <div class="row-fluid">
                <div class="span12">
                    <div class="content-box">
                        <div class="content-box-header">
                            <i class="icon-user"></i> 医生详情
                        </div>
                        <div class="divPermitUpload" style="display: none">
                            <form id="uploadForm" action="${ctx}/doctor/uploadPermit" method="post" enctype="multipart/form-data">
                                <input type="text" name="doctorId" value="${doctor.id}" id="txtDoctorId"  />
                                <input type="file" name="businessFile" id="businessFile" onchange="uploadPermit()"/>
                            </form>
                        </div>

                        <div class="row-fluid">
                            <div style="width: 600px;margin: 10px auto;">
                                <p><b>门诊名称：</b><span>${doctor.outpatientService}</span></p>

                                <p>
                                    <b>门诊地址：</b><span>${doctor.province}${doctor.city}${doctor.area}${doctor.businessAddr}</span>
                                </p>

                                <p><b>法人：</b><span>${doctor.legal}</span></p>

                                <p><b>法人身份证：</b><span>${doctor.legalCard}</span></p>

                                <p><b>门诊营业执照号码：</b><span>${doctor.businessLicense}</span></p>

                                <p><b>医生姓名：</b><span>${doctor.name}</span></p>

                                <p><b>身份证明号码：</b><span>${doctor.card}</span></p>

                                <p><b>性别：</b><span><c:if test="${doctor.gender == 'Male'}">男</c:if><c:if
                                        test="${doctor.gender == 'Female'}">女</c:if></span></p>

                                <p><b>手机号码：</b><span>${doctor.mobile}</span></p>

                                <p><b>电子邮箱：</b><span>${doctor.email}</span></p>

                                <p><b>尾部打印信息：</b><span>${doctor.printInfo}</span></p>

                                <p><b>推荐人：</b><span>${doctor.recommender}</span></p>

                                <p><b>医治总人数：</b><span>${patientCount}人</span></p>

                                <p><b>余额：</b>
                                <span>
                                    <c:if test="${empty doctor.msgMoney }">0元</c:if>
                                    <c:if test="${not empty doctor.msgMoney }">${doctor.msgMoney.deposit}元</c:if>
                                </span></p>
                                <p>
                                    <b>医生系统级别：</b>
                                    <c:if test="${isSubDoctor}">
                                        <span>子医生</span>
                                    </c:if>
                                    <c:if test="${!isSubDoctor}">
                                        <span>主医生</span>
                                    </c:if>
                                </p>

                                <p>
                                    <button id="pass" class="btn btn-info">通过</button>
                                    &nbsp;&nbsp;
                                    <button id="nopass" class="btn btn-info" href="">不通过</button>
                                    &nbsp;&nbsp;
                                    <button id="recharge" class="btn btn-info" href="#">修改金额</button>
                                    &nbsp;&nbsp;
                                    <button id="retPwd" class="btn btn-info">重置密码</button>
                                    &nbsp;&nbsp;
                                    <button id="viewPermit" class="btn btn-info">查看营业执照</button>
                                    &nbsp;&nbsp;
                                    <button id="uploadPermit" class="btn btn-info">上传营业执照</button>
                                    &nbsp;&nbsp;
                                    <c:if test="${isSubDoctor}">
                                        <button id="delSubDoctor" class="btn btn-info">删除子医生</button>
                                        &nbsp;&nbsp;
                                    </c:if>
                                    <a class="btn btn-info" href="${ctx}/doctor/list">返回列表</a></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="permit"></div>
    </section>
    <footer class="footer">
        <h5 class="transparent-text center">
            <div>©2015 深圳市乔北科技有限责任公司版权所有 <a href="http://www.miitbeian.gov.cn/publish/query/indexFirst.action">粤ICP备15089657号-2</a>
            </div>
        </h5>
    </footer>
</div>
<script type="text/javascript">
    var id = ${doctor.id};
    $("#pass").click(function () {
        $.getJSON("${ctx}/doctor/pass/" + id, function (result) {
            if (result.success) {
                $("#pass").attr("disabled", true);
                layer.alert(result.msg);
            }
        });
    });
    $("#nopass").click(function () {
        $.getJSON("${ctx}/doctor/nopass/" + id, function (result) {
            if (result.success) {
                $("#nopass").attr("disabled", true);
                layer.alert(result.msg);
            }
        });
    });
    $("#viewPermit").click(function () {
        $.getJSON("${ctx}/doctor/viewPermit/" + id, function (result) {
            if (result.success) {
                $("#permit").empty();
                $("#permit").append("<img src='${ctx}/temp/" + result.msg + "' style='width:800px;height:600px'/>");
                layer.open({
                    type: 1,
                    title: false,
                    closeBtn: false,
                    skin: 'layui-layer-nobg',
                    area: ['800px', '600px'],
                    shadeClose: true,
                    content: $('#permit')
                });
            } else {
                layer.alert(result.msg);
            }
        });
    });
    $("#retPwd").click(function () {
        var id = ${doctor.id};
        $.getJSON("${ctx}/doctor/retPwd/" + id, function (result) {
            if (result.success) {
                layer.alert("密码已重置。");
            }
        });
        return false;
    });

    $("#recharge").click(function () {
        layer.open({
            title: "修改金额",
            content: "${ctx}/doctor/recharegPage?id=${doctor.id}",
            area: ['400px', '350px'],
            scrollbar: false,
            type: 2,
            end: function () {
                window.location.reload();
            }
        });
    })

    $("#uploadPermit").click(function () {
       return  $("#businessFile").click();
    })

    function uploadPermit(){
        if($("#businessFile").val()!=""){
            $("#uploadForm").submit();
        }
    }

    //删除子医生
    function delSubDoctor(doctorId) {
        $.postJSON("${ctx}/doctor/delSubDoctor", {"doctorId": doctorId}, function (result) {
            if (result.success) {
                layer.msg("子医生删除成功！");
                setTimeout(function () {
                    window.location.href = "${ctx}/doctor/list";
                }, 2000)
            } else {
                layer.msg("子医生删除失败！");
            }

        })
    }

    $(function(){
        var msg = "${msg}";

        if(msg!=""){
            layer.open({
                title: "提示",
                content: msg,
                area: ['400px', '80px'],
                scrollbar: false,
                type: 1
            });
        }

        //删除子医生
        $("#delSubDoctor").click(function () {
            layer.confirm("你是否确定要删除该子医生？", function () {
                delSubDoctor(${doctor.id});
            })
        });

    })

</script>