<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springside.org.cn/tags/form" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<div id="body" style="height: 100%">
    <section id="main">
        <div class="container">
            <div class="row-fluid">
                <div class="span12">
                    <div class="content-box">
                        <div class="content-box-header">
                            <i class="icon-plus"></i> 添加诊后建议
                        </div>
                        <div class="row-fluid">
                            <div style="padding: 15px;width: 800px;margin: 0 auto;">
                                <form:form id="suggestForm" modelAttribute="suggest" action="${ctx}/suggest/add" method="post" class="form-horizontal">
                                    <input type="hidden" name="id" value="${suggest.id}" id="suggestId"/>
                                    <label><b>诊后建议：</b></label>
                                    <form:input path="content" cssClass="form-control"/>
                                    <label><b>关联的诊断结果：</b></label>
                                    <div id="divDiagnosis" class="label-box">
                                        <c:if test="${not empty suggest.tagId}">
                                            <span class="tag" onclick="fn_RemoveElement(this)"> <input name="diagnosisTagIds" value="${suggest.tagId}" type="hidden">${suggest.tagName}</span>
                                        </c:if>
                                    </div>
                                    <div id="divDiagnosisTags" class="diagnosis-label-box"></div>
                                    <br/>
                                    <button type="button" class="btn btn-info" id="btn_save">保存</button>&nbsp;&nbsp;
                                    <a href="${ctx}/suggest/list" class="btn btn-info">返回列表</a>&nbsp;&nbsp;
                                    <c:if test="${not empty suggest.id}">
                                        <button type="button" class="btn btn-info" id="btn_new"><i class="icon-plus"></i>继续新增</button>&nbsp;&nbsp;
                                        <button type="button" id="btn_del" class="btn btn-info">删除</button>
                                    </c:if>
                                </form:form>
                                <c:if test="${error != null}">
                                    <div class="alert alert-danger">${error}</div>
                                </c:if>
                                <c:if test="${msg != null}">
                                    <div class="alert alert-success">${msg}</div>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <footer class="footer">
        <h5 class="transparent-text center">
            <div>©2015 深圳市乔北科技有限责任公司版权所有 <a href="http://www.miitbeian.gov.cn/publish/query/indexFirst.action">粤ICP备15089657号-2</a></div>
        </h5>
    </footer>
</div>
<script type="text/javascript">
    $(function(){
        $("#btn_save").click(function(){
            var name = $('#content').val();
            if(name == ""){
                layer.alert("请输入建议内容！");
                return false;
            }
            $("#suggestForm").submit();
            return false;
        });
        $("#btn_del").click(function(){
            layer.confirm('确定删除？', {
                btn: ['确定','取消']
            }, function(){
                var id  = $("#suggestId").val();
                location.href = "${ctx}/suggest/del/" + id;
            });
        });
        $("#btn_new").click(function(){
            $("#content").val("");
            $("#suggestId").val("");
            $("#divDiagnosis").empty();
        });
        fn_LoadDiagnosisTag();
    });
    function fn_LoadDiagnosisTag(page, name) {
        if (page == undefined) page = 1;
        if (name == undefined) name = "";
        name = name.replace(/[ ]/g, "");
        $("#divDiagnosisTags").load("${ctx}/fragment/suggest/diagnosisTags?page=" + page + "&name=" + name);
    }
</script>