<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${ctx}/assets/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/assets/styles/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/assets/styles/default.min.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/assets/styles/style.css">
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/styles/green.css" type="css" needChengStyle="true"/>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/assets/html5shiv/html5shiv.js"></script>
    <script type="text/javascript" src="${ctx}/assets/respond/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript" src="${ctx}/assets/jquery/jquery-1.11.2.min.js"></script>
    <script type="text/javascript" src="${ctx}/assets/layer/layer.js"></script>
    <script type="text/javascript" src="${ctx}/assets/laypage/laypage.js"></script>
    <script type="text/javascript">
        function fn_RemoveElement(ele) {
            $(ele).remove();
        }
        function fn_LoadRpDiagnosisTag(page, name) {
            if (page == undefined) page = 0;
            if (name == undefined) name = "";
            name = name.replace(/[ ]/g, "");
            $("#divDiagnosisTags").load('/fragment/rpDiagnosisTags?page='+page+'&name='+name+'');
        }
        function fn_LoadRpDiagnosisOtherTag(page, name) {
            if (page == undefined) page = 0;
            if (name == undefined) name = "";
            name = name.replace(/[ ]/g, "");
            $("#divDiagnosisTags").load('/fragment/rpDiagnosisOtherTags?page='+page+'&name='+name+'');
        }
        function fn_SelectDiagnosisTag(tag) {
            var ele = '<span class="tag" onclick="fn_RemoveElement(this)">'+tag+'</span>';
            $('#divDiagnosis').append(ele);
        }
        $(function () {
            var index = parent.layer.getFrameIndex(window.name);
            fn_LoadRpDiagnosisTag();
            $('#btnConfirm').click(function () {
                var arr = $.map($('#divDiagnosis span'), function (n, i) {
                    return $(n).html();
                });
                if(arr.length == 0){
                    layer.alert("请选择诊断结果。");
                    return;
                }
                var diagnosis = "";
                $.each(arr, function (i, n) {
                    diagnosis += "," + n;
                });
                parent.$("#txtDiagnosis").val(diagnosis.substring(1));
                parent.layer.close(index);
            });
            $('#btnCancel').click(function () {
                parent.layer.close(index);
            });
        });
    </script>
</head>
<body>
    <div class="diagnose" style="margin: 15px; background-color: #fff; border-radius:5px; padding: 15px 10px;">
        <div id="divDiagnosis" class="label-box">
        </div>
        <div id="divDiagnosisTags" class="diagnosis-label-box"></div>
        <div class="text-center" style="margin-top: 10px">
            <button type="button" id="btnConfirm" class="btn btn-success" style="background-color: #218a3f; width: 80px;">确定</button>
            <button type="button" id="btnCancel" class="btn" style="width: 80px;">取消</button>
        </div>
    </div>
    <script type="text/javascript" src="${ctx}/assets/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>