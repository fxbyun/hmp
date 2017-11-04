<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/6/16
  Time: 15:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<html>
<head>
    <title>短信发送及设置</title>
    <style>
        .wordwrap {
            position: absolute;
            right: 15px;
            bottom: 4px;
            font-size: 12px;;
        }

        .title {
            position: absolute;
            right: 15px;
            bottom: 20px;
            color: #999;
        }

        .mes-textarea {
            width: 100%;
            min-height: 150px;
            resize: none;
        }

        .mes-textarea:focus {
            outline: none;
        }

        .rp-table thead tr th:first-child, table.rp-table tr td:first-child {
            padding-left: 0;
        }

        .rp-table tbody tr, .rp-table tbody tr td {
            height: 40px;
        }

        .mes-table-inline {

            border: none;
        }

        .mes-table-inline tr th, .mes-table-inline tr td {
            border: 0;
        }

        .mes-span-tag {
            padding: 5px;
            position: relative;
        }

        .mes-span-tag i {
            position: absolute;
            right: -2px;
            top: -2px;
            z-index: 1;
        }

        .mes-span-tag:hover {
            background-color: #ccc;
            cursor: pointer;
            border-radius: 3px;
        }

        .fenye {
            font-size: 14px;
            position: absolute;
            width: 100%;
            right: 0;
            top: 54em;
            padding: 0;
        }

        #mes-span {
            position: absolute;
            top: 7px;
            left: 12px;
            color: #999;
        }

        #msgListDiv {
            padding: 0 10px;
            float: left;
            width: 50%;
            border-left: 2px solid #ccc;
            margin-bottom: 20px;
            min-height: 820px;
            position: relative;
        }
    </style>
    <script>
        var isPay = false;
        var patientInfo = [];
        function fn_checkDetail() {
            layer.open({
                type: 2,
                title: '充值和消费明细',
                area: ['600px', '500px'],
                scrollbar: false,
                content: '/msPage/msDetail/'
            });
        }
        function fn_recharge() {
            layer.open({
                type: 2,
                title: '微信充值',
                area: ['600px', '600px'],
                scrollbar: false,
                content: '/msPage/msRecharge/',
                end: function () {
                    if (isPay == true) {
                        isPay = false;
                        layer.msg("恭喜您,充值成功!");
                        $.post('/msPage/clearSessionMsgInfo', {}, function (str) {
                            if (str["msgTotal"]) {
                                $("#totleSendSize").text(str["msgTotal"]);
                                $("#totleNewonery").text(str["msgMoney"]);
                            }
                        })
                    }
                }
            });
        }

        var patientIdList = [];
        var mobList = [];
        var ageTop = "";
        var ageFor = "";
        var diagonsisName = "";
        var sex = "";
        var txtStartDate = "";
        var txtEndDate = "";
        var msgContext = "";
        var title = "";
        var isSend = false;

        function addPeople() {
            layer.open({
                type: 2,
                title: '添加收件人',
                area: ['600px', '665px'],
                scrollbar: false,
                content: '/msPage/msAddPeople/',
                end: function () {
                    $("#countSendPeople").text(patientIdList.length);
                    $("#diagonsisNameSpan").text(diagonsisName);
                    if (diagonsisName != undefined && diagonsisName != "") {
                        $("#diagonsisNameSpan").parent().show();
                    }

                    if (txtStartDate != undefined && txtStartDate != "") {
                        txtStartDate = txtStartDate + "-";
                        $("#jzStart").parent().show();
                    }
                    if (txtEndDate != undefined && txtEndDate != "") {
                        $("#jzStart").parent().show();
                    }


                    $("#jzStart").text(txtStartDate);
                    $("#jzTop").text(txtEndDate);

                    if (ageTop != undefined && ageTop != "") {
                        ageTop = ageTop + "岁-";
                        $("#ageStrat").parent().show();
                    }
                    if (ageFor != undefined && ageFor != "") {
                        ageFor = ageFor + "岁";
                        $("#ageStrat").parent().show();
                    }
                    $("#ageStrat").text(ageTop);
                    $("#ageTop").text(ageFor);

                    if (sex == "Male") {
                        sex = "男";
                    } else if (sex == "Female") {
                        sex = "女";
                    } else {
                        sex = "全部"
                    }

                    $("#genderSex").text(sex);
                }
            });
        }


        function mesSend() {

            if (patientIdList.length < 1) {
                Alert.warning("请先增加收件人!");
                return false;
            }
            if ($("#mes-title").val() == "") {
                Alert.warning("请输入您的短信主题!");
                return false;
            }
            if ($("#mes-text").val() == "") {
                Alert.warning("请输入您的短信内容!");
                return false;
            }


            msgContext = $("#mes-span").text() + $("#mes-text").val() + "--${doctor.outpatientService}。退订回复TD";
            var obj = {
                "patientIdList": patientIdList,
                "mobList": mobList,
                "ageTop": ageTop,
                "ageFor": ageFor,
                "diagonsisName": diagonsisName,
                "sex": sex,
                "txtStartDate": txtStartDate,
                "txtEndDate": txtEndDate,
                "msgContext": msgContext,
                "title": $("#mes-title").val(),
            }
            $("#shade").text("短信发送中,请稍后。。。。。。");


            $.post('/msPage/addSendPatienInfo', obj, function (str) {
                if (str.success) {
                    layer.open({
                        type: 2,
                        title: '确认发送',
                        area: ['500px', '350px'],
                        scrollbar: false,
                        content: '/msPage/msSend',
                        end: function () {
                            $("#shade").hide();
                            $.post('/msPage/clearSessionMsgInfo', obj, function (str) {
//                                console.info(str);
                                if (str["msgTotal"]) {
                                    $("#totleSendSize").text(str["msgTotal"]);
                                    $("#totleNewonery").text(str["msgMoney"]);
                                }
                            })
                            if (isSend) {
                                Alert.success("发送短信成功!");
                                isSend = false;
                            }
                            $("#msgListDiv").load("${ctx}/msPage/getMsgSendList");
                        }

                    });
                } else {
                    Alert.error("发送失败,请联系技术人员!");
                }

            });
        }

        $(function () {
            $("#nav-manage").addClass("active");
            if (patientIdList.length == 0) {

                $("#jzStart").text("");
                $("#jzStart").parent().hide();
                $("#jzTop").text("");

                $("#ageStrat").text("");
                $("#ageTop").text("");
                $("#ageStrat").parent().hide();

                $("#genderSex").text("全部");
                $("#genderSex").parent().hide();

                $("#diagonsisNameSpan").text("");
                $("#diagonsisNameSpan").parent().hide();
            }

            //先选出 textarea 和 统计字数 dom 节点
            var wordCount = $("#mes-div"),
                    textArea = wordCount.find("textarea"),
                    word = wordCount.find(".word");
            //调用
            statInputNum(textArea, word);


            //鼠标经过事件
            $(".mes-span-tag").mouseover(function () {
                $(this).find("i").addClass("fa fa-times-circle");
            });
            $(".mes-span-tag").mouseout(function () {
                $(".mes-span-tag > i").removeClass("fa fa-times-circle");
            });


        });
        /*
         * 剩余字数统计
         * 注意 最大字数只需要在放数字的节点哪里直接写好即可 如：<var class="word">200</var>
         */
        function statInputNum(textArea, numItem) {
            var max = numItem.text(),
                    curLength;
            textArea[0].setAttribute("maxlength", max);
            curLength = textArea.val().length;
            numItem.text(max - curLength);
            textArea.on('input propertychange', function () {
                numItem.text(max - $(this).val().length);
            });
        }


        function closeTherTd(obj) {

            var objs = "#divTable" + $(obj).attr("thinkDivId");
            $(objs).load("${ctx}/msPage/loadSmgSendHosDetil?id=" + $(obj).attr("thinkDivId"));
            $("#msfInfo a").each(function () {
                $(this).removeAttr("onclick");
            })
            $("#msfInfo a").each(function () {
                if ($(this).attr("aria-expanded") == "true") {
                    $(this).trigger("click");
                }
            })

            $("#msfInfo a").each(function () {
                $(this).attr("onclick", "closeTherTd(this)");
            })

        }
        $('#mes-text').keyup(function () {
            $(this).css('color', '#000');
        });
        $('#mes-text').blur(function () {
            alert($(this).text());
            $('#mes-span').text() + $(this).text();

        });

        $(document).ready(function () {
            $("#sel").select2();
            $("#msgListDiv").load("${ctx}/msPage/getMsgSendList");
        });

        function removeThisPatien(id, mob, obj) {
            patientIdList.pop(id);
            $("#countSendPeople").text(patientIdList.length);
            mobList.pop(mob);
            $(obj).remove();
            if (patientIdList.length == 0) {

                $("#jzStart").text("");
                $("#jzStart").parent().hide();
                $("#jzTop").text("");

                $("#ageStrat").text("");
                $("#ageTop").text("");
                $("#ageStrat").parent().hide();

                $("#genderSex").text("全部");
                $("#genderSex").parent().hide();

                $("#diagonsisNameSpan").text("");
                $("#diagonsisNameSpan").parent().hide();
            }


        }
    </script>
</head>
<body>

<div class="manage">
    <div class="container">
        <ul class="navigation">
            <li><a href="${ctx}/manage" class="btn btn-default">用药分析</a></li>
            <c:if test="${doctor.doctorType!='Sub_Doctor'}">
                <li class="active"><a href="${ctx}/message" class="btn btn-default" onclick="isDebug()">短信发送</a></li>
            </c:if>
            <li><a href="${ctx}/rp" class="btn btn-default">我的药方</a></li>
            <li><a href="${ctx}/rplib" class="btn btn-default">药方库</a></li>
            <li><a href="${ctx}/config/symptom" class="btn btn-default">数据整理</a></li>
            <c:if test="${doctor.doctorType!='Sub_Doctor'}">
                <li><a href="${ctx}/appointment" class="btn btn-default">预约挂号</a></li>
            </c:if>
            <c:if test="${doctor.doctorType!='Sub_Doctor'}">
                <li><a href="${ctx}/validityManage" class="btn btn-default">有效期管理</a></li>
            </c:if>
            <c:if test="${doctor.doctorType!='Sub_Doctor'}">
                <li><a href="${ctx}/financial" class="btn btn-default">财务统计</a></li>
            </c:if>
            <div class="analysis">
                <p>总计发送信息：
                    <yjz id="totleSendSize">${msgInfo['msgTotal']}</yjz>
                </p>

                <p>账户余额：
                    <yjz id="totleNewonery">${msgInfo['msgMoney']}</yjz>
                    元
                </p>
                <button class="btn btn-success" style="width: 80px; margin-right: 10px;" onclick="fn_recharge()">立即充值
                </button>
                <button class="btn btn-default" style="width: 80px;" onclick="fn_checkDetail()">明细</button>
            </div>
        </ul>
        <div class="infro-content" style="overflow:hidden; min-height: 500px; margin-bottom: 20px;">
            <div style="padding-left: 20px; float: left; width: 50%; margin-bottom: 30px;">
                <h3 style="margin-bottom: 30px; font-size: 18px;">发送短信</h3>

                <p style="padding-left: 20px;">
                    标题：<input type="text" id="mes-title" style="width: 88%;display: inline-block;"
                              class="form-control"
                              value="">
                </p>

                <p style="padding-left: 20px;"><a href="#" onclick="addPeople()" style="font-size: 16px;">
                    <i class="fa fa-plus-square-o" style="padding-right: 5px; vertical-align: middle;"></i>添加收件人</a>
                </p>

                <p style="padding-left: 20px;">
                    <span>就诊时间：
                        <span id="jzStart">

                        </span>
                        <span id="jzTop">

                        </span>
                    </span>
                    <span>患者年龄：
                        <span id="ageStrat">

                        </span>
                        <span id="ageTop">

                        </span>
                    </span>
                    <span>性别：
                        <span id="genderSex">

                        </span>
                    </span>
                    <span>病症：
                        <span id="diagonsisNameSpan">

                        </span>
                    </span>
                    <span>总计收件人：
                        <span id="countSendPeople">
                                      0
                        </span>
                    </span>
                </p>


                <div id="addPatienList" class="form-control"
                     style="margin-left: 20px; width: 92%; min-height: 30px; height: auto; line-height: 30px;">
                </div>

                <p style="padding-left: 20px; margin-top: 10px;">编辑信息：</p>

                <div style="position: relative;width: 92%; margin-left: 20px;" id="mes-div">
                    <textarea id="mes-text" class="form-control mes-textarea" style="text-indent: 50px;"></textarea>
                    <span id="mes-span">[易佳诊]</span>

                    <div class="title"><span id="mes-zs">--${doctor.outpatientService}。退订回复TD</span></div>
                    <div class="wordwrap"><span><var
                            class="word">${187-doctor.outpatientService.length()}</var>/201</span></div>
                </div>

                <p class="text-center" style="margin-top: 50px;">
                    <input type="button" onclick="mesSend()" class="btn btn-success" style="width: 120px;" value="发送">
                </p>
            </div>

            <div id="msgListDiv"></div>
        </div>


    </div>
</div>
</body>
</html>
