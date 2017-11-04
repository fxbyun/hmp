<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/8/11
  Time: 11:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<%@ include file="/WEB-INF/commons/staticInclude.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/typeahead/typeahead.bundle.js" type="js"/>
    <style>
        body {
            font-size: 35px;
            color: #666;
        }

        .layui-layer-btn0 {
            font-size: 14px;
        }

        span {
            padding-right: 10px;
        }

        input.form-control {
            height: 50px;
            font-size: 28px;
        }

        .btn {
            width: 150px;
            font-size: 30px;
        }

        .btn-success {
            background-color: #529b47;
            margin-right: 10px;
        }
    </style>
    <script>
        $(function () {
            if ("${msg}" != "") {
                layer.msg("${msg}")
            }
            var index = parent.layer.getFrameIndex(window.name);
            $('#btnClose').click(function () {
                parent.layer.close(index);
            });
            $("#udid").focus();
            $("#udid").keypress(function (e) {
                var key = window.event ? e.keyCode : e.which;
                if (key.toString() == "13") {
                    $("#btnSave").trigger("click");
                }
            });

            $("#btnSave").click(function () {
                var doctorId = ${doctor.id};
                if ($("#mobile").val() == "" && $("#udid").val() == "") {
                    layer.msg("请刷卡或输入正确的手机号!")
                    return;
                }

                $.postJSON(ctx + "/advertising/registration/save", {
                    "udid": $("#udid").val(),
                    "mobile": $("#mobile").val(),
                    "doctorId":doctorId
                }, function (result) {
                    if (result.success) {
                        parent.utlis_msg(result.msg, function () {
                        });
                        parent.layer.close(index);
                    } else {
                        layer.msg(result.msg);
                        if (result.data) {
                            parent.fn_AddPatientInfo(result.data.cardNo, result.data.udid,doctorId);
                            parent.layer.close(index);
                        }
                    }
                })
            });


            var patientList = new Bloodhound({
                datumTokenizer: Bloodhound.tokenizers.whitespace,
                queryTokenizer: Bloodhound.tokenizers.whitespace,
//                prefetch: '/patient/query?key=136',
                remote: {
                    url: ctx + '/patient/query?key=%QUERY',
                    wildcard: '%QUERY'
                }
            });
            $('#mobile').typeahead({
                minLength: 11,
                highlight: true
            }, {
                name: 'patient-list',
                limit: 10,
                async: true,
                display: 'uid',
                source: patientList,
                templates: {
                    'empty': '<div class="tt-suggestion">无结果</div>',
                    'suggestion': function (o) {
                        var name = o.name == null ? "" : o.name;
                        var div = "<div onclick='commit()'>{0} - <strong>{1}</strong> – {2}</div>";
                        return div.format("请选择", o.uid, name);
                    }
                }
            });

        });


        function commit() {
            /*setTimeout(function () {
                $("#btnSave").trigger("click");
            }, 2000);*/
        }
    </script>
</head>
<body style="background-color: #fff;">
<div style="margin: 2em 2em; line-height: 2.8em;" class="text-center">
    <div class="form-group text-left" style="margin-bottom: 30px;">
        <span style="padding-right: 1.6em;">刷&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;卡</span>
        <input type="password" style="width: 62.7%; display: inline-block;" id="udid" class="form-control"
               placeholder="请刷卡" autocomplete="off">
    </div>
    <div class="form-group clearfix" style="line-height: 2em;">
        <span style="float:left; line-height: 1em;">手动添加</span>
        <input type="text" id="mobile" class="form-control" style="float:left; width: 120%; left: -20px;"
               placeholder="请输入手机号">
    </div>
    <div class="form-group text-center" style="margin-top: 2em;">
        <button type="button" class="btn btn-success" id="btnSave">挂号</button>
        <button type="button" class="btn btn-default" id="btnClose">取消</button>
    </div>
</div>
</body>
</html>
