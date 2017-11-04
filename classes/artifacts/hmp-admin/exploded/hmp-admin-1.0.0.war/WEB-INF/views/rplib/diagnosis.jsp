<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <title>易佳诊健康管理平台</title>
    <meta charset="UTF-8">
    <link href="${ctx}/assets/styles/admin.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="${ctx}/assets/scripts/admin.js"></script>
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/assets/html5shiv/html5shiv.js"></script>
    <script type="text/javascript" src="${ctx}/assets/respond/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript" src="${ctx}/assets/layer/layer.js"></script>
</head>
<body>
<div class="diagnose" style="margin: 15px">
    <div id="divDiagnosis" class="label-box">
    </div>
    <div id="divDiagnosisTags" class="diagnosis-label-box"></div>
    <div style="margin-top: 10px;text-align: center">
        <button type="button" id="btnConfirm" class="btn btn-primary">确定</button>
        <button type="button" id="btnCancel" class="btn">取消</button>
    </div>
</div>
<script type="text/javascript">
    function fn_LoadDiagnosisTag(page, name) {
        if (page == undefined) page = 0;
        if (name == undefined) name = "";
        name = name.replace(/[ ]/g, "");
        $("#divDiagnosisTags").load('${ctx}/fragment/rplib/diagnosisTags?page='+page+'&name='+name+'');
    }
    function fn_SelectDiagnosisTag(tagId, tag) {
        var arr = $.map($('#divDiagnosis span input[name="diagnosisResults"]'), function (n, i) {
            return $(n).val();
        });
        if ($.inArray(tag, arr) != -1) {
            layer.msg("此标签已添加,勿重复添加");
            return;
        }
        var ele = '<span class="tag" onclick="fn_RemoveElement(this)">' +
                '<input type="hidden" name="diagnosisResults" value="' + tag + '"/>' + tag + '</span>'
        $('#divDiagnosis').append(ele);
    }
    function fn_RemoveElement(ele) {
        $(ele).remove();
    }
    $(function () {
        fn_LoadDiagnosisTag();
        var index = parent.layer.getFrameIndex(window.name);
        $('#btnConfirm').click(function () {
            var arr = $.map($('#divDiagnosis span input[name="diagnosisResults"]'), function (n, i) {
                return $(n).val();
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
</body>
</html>