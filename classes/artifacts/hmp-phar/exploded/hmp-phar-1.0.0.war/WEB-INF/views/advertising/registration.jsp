<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/8/11
  Time: 11:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/advInclude.jsp" %>
<%--<%@ include file="/WEB-INF/commons/staticInclude.jsp" %>--%>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/typeahead/typeahead.bundle.js" type="js"/>
    <style>
        span {
            padding-right: 10px;
            vertical-align: middle;
            font-size: 18px;
        }

        input.form-control {
            height: 40px;
            font-size: 18px;
        }

        .btn {
            width: 120px;
            font-size: 16px;
        }

        .btn-success {
            background-color: #529b47;
            margin-right: 10px;
        }

        .typeahead,
        .tt-query,
        .tt-hint {
            width: 396px;
            height: 30px;
            padding: 8px 12px;
            font-size: 14px;
            line-height: 30px;
            border: 2px solid #ccc;
            -webkit-border-radius: 8px;
            -moz-border-radius: 8px;
            border-radius: 8px;
            outline: none;
        }

        .typeahead {
            background-color: #fff;
        }

        .typeahead:focus {
            border: 2px solid #0097cf;
        }

        .tt-query {
            -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
            -moz-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
            box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
        }

        .tt-hint {
            color: #999
        }

        .tt-menu {
            width: 300px;
            margin: 12px 0;
            padding: 8px 0;
            background-color: #fff;
            /*border: 1px solid #ccc;*/
            border: 1px solid rgba(0, 0, 0, 0.2);
            -webkit-border-radius: 8px;
            -moz-border-radius: 8px;
            border-radius: 8px;
            -webkit-box-shadow: 0 5px 10px rgba(0, 0, 0, .2);
            -moz-box-shadow: 0 5px 10px rgba(0, 0, 0, .2);
            box-shadow: 0 5px 10px rgba(0, 0, 0, .2);
        }

        .tt-suggestion {
            padding: 3px 20px;
            font-size: 14px;
            line-height: 24px;
        }

        .tt-suggestion:hover {
            cursor: pointer;
            color: #fff;
            background-color: #0097cf;
        }

        .tt-suggestion.tt-cursor {
            color: #fff;
            background-color: #0097cf;

        }

        .tt-suggestion p {
            margin: 0;
        }

        .gist {
            font-size: 14px;
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
                var checkFlag = true;
                var doctorId = ${doctor.id};
                if ($("#mobile").val() == "" && $("#udid").val() == "") {
                    layer.msg("请刷卡或输入正确的手机号!")
                    return;
                }
                //$(this).attr("disabled","true");
                $.postJSON(ctx + "/advertising/registration/save", {
                    "udid": $("#udid").val(),
                    "mobile": $("#mobile").val(),
                    "doctorId":doctorId
                }, function (result) {
                    if (result.success) {
                        parent.utlis_msg(result.msg, function () {
                        });
                        parent.utlit_getAdvingSize();
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
             $("#btnSure").trigger("click");
             }, 2000);*/
        }
    </script>
</head>
<body style="background-color: #fff;">
<div style="margin: 2em 2em; line-height: 2.8em;" class="text-center">
    <div class="form-group text-left" style="margin-bottom: 30px;">
        <span style="padding-right: 1.8em;">&nbsp;&nbsp;&nbsp;刷&nbsp;卡&nbsp;&nbsp;&nbsp;</span>
        <input type="password" style="width: 60%; display: inline-block;" id="udid" class="form-control"
               placeholder="请刷卡" autocomplete="off">
    </div>
    <div class="form-group clearfix" style="line-height: 2em;">
        <span style="float:left; line-height: 1em;">手动添加</span>
        <input type="text" id="mobile" class="form-control" style="float:left; width: 120%; left: -38px;"
               placeholder="请输入手机号">
    </div>
    <div class="form-group text-center" style="margin-top: 2em;">
        <button type="button" class="btn btn-success" id="btnSave">挂号</button>
        <button type="button" class="btn btn-default" id="btnClose">取消</button>
    </div>
</div>
</body>
</html>
