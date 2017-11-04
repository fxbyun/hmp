<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach var="tag" items="${diagnosisPage.content}">
    <span class="tag" onclick="fn_SelectDiagnosisTag('${tag.id}','${tag.name}')">${tag.name}</span>
</c:forEach>
<div class="form-horizontal">
    <c:if test="${diagnosisPage.number -1 > 0}">
        <button type="button" onclick="fn_LoadDiagnosisTag(${diagnosisPage.number -1})" class="btn btn-info"><i class="icon-chevron-left"></i> 上一批</button>
    </c:if>
    <c:if test="${diagnosisPage.number + 1 < diagnosisPage.totalPages}">
        <button type="button" onclick="fn_LoadDiagnosisTag(${diagnosisPage.number + 1})" class="btn btn-info">下一批 <i class="icon-chevron-right"></i></button>
    </c:if>
    <input id="txtDiagnosisTag" value="${name}" type="text" class="form-control" placeholder="诊断" style="width: 200px"/>
    <span class="input-group-btn">
        <button onclick="fn_LoadDiagnosisTag()" class="btn btn-primary" type="button"><i class="icon-remove"></i></button>
        <button onclick="loadDiagnoses(0)" class="btn btn-primary" type="button"><i class="icon-search"></i></button>
    </span>
</div>
<script type="text/javascript">
    $('#txtDiagnosisTag').keydown(function (event) {
        if (event.keyCode == 13) {
            loadDiagnoses(0);
            return false;
        }
    });
    if ($('#txtDiagnosisTag').val()) {
        $('#txtDiagnosisTag').focus();
        $('#txtDiagnosisTag').select();
    }
    function loadDiagnoses(page) {
        var name = $('#txtDiagnosisTag').val();
        fn_LoadDiagnosisTag(page, name);
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
                '<input type="hidden" name="diagnosisTagIds" value="'+tagId+'"/>' +
                '<input type="hidden" name="diagnosisResults" value="'+tag+'"/>'+tag+'</span>'
        $('#divDiagnosis').append(ele);
    }
    function fn_RemoveElement(ele) {
        $(ele).remove();
    }
</script>