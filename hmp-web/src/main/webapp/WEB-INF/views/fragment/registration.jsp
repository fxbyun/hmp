<%--@elvariable id="ctx" type="java.lang.String"--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/staticInclude.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <link href="${ctx}/assets/bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/assets/html5shiv/html5shiv.js"></script>
    <script type="text/javascript" src="${ctx}/assets/respond/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript" src="${ctx}/assets/jquery/jquery-1.11.2.min.js"></script>
    <script type="text/javascript" src="${ctx}/assets/layer/layer.js"></script>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/typeahead/typeahead.bundle.js" type="js"/>

    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/hmp.js" type="js"/>
    <script type="text/javascript">
        $(function () {
            $("#txtCard").focus().select();
            var index = parent.layer.getFrameIndex(window.name);
            $('#btnClose').click(function () {
                parent.layer.close(index);
            });
            $('#txtCard').keydown(function (event) {
                if (event.keyCode == 13) {
                    $('#txtMobile').val("");
                    $('#btnSave').click();
                    return false;
                }
            });
            $('#btnSave').click(function () {
                var udid = $('#txtCard').val();
                var mobile = $('#txtMobile').val();
                if (udid == "") {
                    if (mobile.length < 6) {
                        alert("无对应的患者!");
                        return;
                    }
                }
                if (mobile == "") {
                    if (udid == "") {
                        alert("请刷卡或者输入手机号!");
                        return;
                    }
                }


                $.postJSON("/fragment/registration/save", {"udid": udid, "mobile": mobile}, function (result) {
                    if (result.success) {
                        parent.fn_loadRegistration();
                        parent.layer.close(index);
                    } else {
                        if (result.msg == "该患者未绑卡,请先进行绑卡操作!") {
                            var index_layer = parent.layer.confirm("初次开卡用户，请完善用户信息", function () {
                                fn_EditPatient(udid, index);
                                //parent.layer.closeAll();
                                parent.layer.close(index_layer);
                            });
                        } else {
                            parent.layer.msg(result.msg);
                        }

                        //parent.layer.close(index);
                    }
                })
            });


            var patientList = new Bloodhound({
                datumTokenizer: Bloodhound.tokenizers.whitespace,
                queryTokenizer: Bloodhound.tokenizers.whitespace,
//                prefetch: '/patient/query?key=136',
                remote: {
                    url: '/patient/query?key=%QUERY',
                    wildcard: '%QUERY'
                }
            });

            $('#txtMobile,#txtCard').typeahead({
                minLength: 2,
                highlight: true
            }, {
                name: 'patient-list',
                limit: 10,
                async: true,
                display: 'uid',
                source: patientList,
                templates: {
                    'empty': '',
                    'suggestion': function (o) {
                        var name = o.name == null ? "" : o.name;
                        return '<div>{0} - <strong>{1}</strong> – {2}</div>'.format(o.mobile, o.uid, name);
                    }
                }
            });
        });

        function fn_EditPatient(udid, index) {
            $.postJSON("/patient/login4Udid", {"udid": udid}, function (result) {
                if (result.status === 'Passed' || result.status === 'NewCard') {
                    parent.layer.open({
                        type: 2,
                        title: '修改患者信息',
                        area: ['400px', '580px'],
                        scrollbar: false,
                        content: '/fragment/patient/update/' + result.data,
                        end: parent.layer.close(index)
                    });
                }
            });

        }
    </script>
    <style>
        .tt-menu {
            left: -55px !important;
            width: 300px;
        }
    </style>
</head>
<body>
<div style="margin: 20px; font-family: '微软雅黑',serif;">

    <div class="form-group">
        <div class="row">
            <label for="txtCard" class="col-xs-3 col-sm-3 control-label text-right">刷卡</label>

            <div class="col-xs-7 col-sm-7">
                <input type="text" class="form-control tt-hint" id="txtCard" placeholder="请刷卡"/>
            </div>
        </div>
        </div>
    <div class="form-group">
        <div class="row">
            <label for="txtMobile" class="col-xs-3 col-sm-3 control-label text-right">手动添加</label>

            <div class="col-xs-7 col-sm-7">
                <input type="text" name="mobile" maxlength="11" hmp-input-number class="form-control" id="txtMobile"
                       placeholder="请输入手机号"/>
            </div>
        </div>
        </div>
    <div class="form-group">
        <div class="row">
            <div class="col-xs-offset-3 col-sm-offset-3 col-xs-9 col-sm-9" style="margin-top:20px;">
                <button type="button" id="btnSave" class="btn btn-success"
                        style="background-color: #218e3f; width: 70px; margin-right:10px;">
                    挂号
                </button>
                <button id="btnClose" type="button" class="btn btn-default"><i class="fa fa-times"></i> 取消</button>
            </div>
        </div>
        </div>
</div>
<script type="text/javascript" src="${ctx}/assets/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>