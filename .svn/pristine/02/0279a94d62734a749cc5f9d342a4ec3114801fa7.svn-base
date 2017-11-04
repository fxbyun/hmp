<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/7/29 0029
  Time: 14:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<%@ include file="/WEB-INF/commons/staticInclude.jsp" %>
<html>
<head>
    <title>Title</title>
    <link href="${ctx}/assets/bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/prescription/prescription.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/application.js" type="js"/>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/assets/html5shiv/html5shiv.js"></script>
    <script type="text/javascript" src="${ctx}/assets/respond/respond.min.js"></script>
    <![endif]-->
    <script>

        $(function () {

            $('#txtPatientUdid').keydown(function (event) {
                if (event.keyCode == 13) {//keyCode=13是回车键
                    var udid = $('#txtPatientUdid').val();
                    $('#txtPatientUdid').val('');
                    if (udid) {
                        $.postJSON("/patient/login4Udid", {"udid": udid}, function (result) {
                            if (result.status === 'NewCard') {

                                fn_EditPatient(result.data, '${id}')

                            } else if (result.status === "Passed") {
                                $.postJSON("/oldPatient/isThisCardPatientActivation", {"uid": udid}, function (result2) {
                                    if (result2.success) {
                                        Alert.error("该卡已经被绑定!");
                                    } else {
                                        Alert.success("未绑定的卡!");

                                        fn_EditPatient(result.data, '${id}')
                                    }
                                })
                            }
                            else if (result.status === 'Invalid') {
                                layer.alert("无效的卡号或密码");
                            } else {
                                layer.alert("该卡已被使用!");
                            }
                        });
                    }
                    return false;
                }
            });

            $("#txtPatientUdid").focus().select();
            setTimeout(function () {
                $("#txtPatientUdid").val("");
            }, 500)


        })

        function fn_EditPatient(cardId, oldPatientId) {
            $("#txtPatient").hide();
            $("#changePatientMes").load('/oldPatient/oldPtDetail/binDingCardPatient/' + cardId + "?oldPatientId=" + oldPatientId + "&type=tmp");
        }
    </script>
</head>
<body style="background-color: #fff; font-family: '微软雅黑';">
<div>
    <div class="col-xs-12" style="padding-top: 20px;">
        <div class="form-group" id="txtPatient">
            <div class="col-xs-12 col-sm-12" style="margin-top: 3em;">
                <h1 class="text-center" style="font-size: 4em;">请刷卡</h1>
            </div>
            <div class="col-xs-5 col-sm-5" style="margin:5% 30%;">
                <img src="${ctx}/assets/styles/images/card.png"
                     style="float:left; margin-right: 10px; padding-top: 5px;">
                <input type="password" class="form-control" id="txtPatientUdid" placeholder="刷卡" autocomplete="off"
                       value=""
                       style="float:left; width: 80%; font-family: '微软雅黑';">
            </div>
        </div>
        <div id="changePatientMes"></div>
    </div>
</div>

<script type="text/javascript" src="${ctx}/assets/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript"
        src="${ctx}/assets/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript"
        src="${ctx}/assets/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="${ctx}/assets/jquery-validation/1.11.1/jquery.validate.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/jquery-validation/1.11.1/messages_bs_zh.js"></script>
</body>
</html>
