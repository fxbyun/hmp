<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springside.org.cn/tags/form" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<div id="body" style="height: 120%">
    <section id="main">
        <div class="container">
            <div class="row-fluid">
                <div class="span12">
                    <div class="content-box">
                        <div class="content-box-header">
                            <i class="icon-plus"></i> 发布广告
                        </div>
                        <div class="row-fluid">
                            <div style="padding: 15px;width: 700px;margin: 0 auto;">
                                <form:form id="advertForm" modelAttribute="advert" action="${ctx}/advert/add" method="post" class="form-horizontal" enctype="multipart/form-data">
                                    <input type="hidden" name="id" value="${advert.id}" id="adId"/>
                                    <label><b>名称：</b></label>
                                    <form:input path="name" cssClass="form-control"/>
                                    <label><b>位置：</b></label>
                                    <form:select path="position" cssClass="form-control" items="${positionMap}" />
                                    <label><b>类型：</b></label>
                                    <form:bsradiobuttons path="type" items="${typeMap}" labelCssClass="inline"/>
                                    <div id="textArea">
                                        <label><b>内容：</b></label>
                                        <textarea id="tinymce_basic" name="content" style="height:100px">${advert.content}</textarea>
                                    </div>
                                    <div id="picArea">
                                        <label><b>图片：</b></label>
                                        <input type="file" name="file" id="file"/><br/>
                                        <label><b>链接：</b></label>
                                        <form:input path="url" cssClass="form-control" />
                                    </div>
                                    <label><b>有效期：</b></label>
                                    <form:input path="indate" cssClass="form-control" cssStyle="width:80px"/> 天&nbsp;&nbsp;为空或设置0，则表示永不过期。
                                    <br/> <br/>
                                    <button type="button" class="btn btn-info" id="btn_save">保存</button>&nbsp;&nbsp;
                                    <a href="${ctx}/advert/list" class="btn btn-info">返回列表</a>&nbsp;&nbsp;
                                    <c:if test="${not empty advert.id}">
                                        <button type="button" id="del" class="btn btn-info">删除</button>
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
<script type="text/javascript" src="${ctx}/assets/tinymce/js/tinymce/tinymce.min.js"></script>
<script type="text/javascript">
    $(function(){
        $('.form_date').datetimepicker({
            language: 'zh-CN',
            format: 'yyyy/mm/dd',
            weekStart: 1,
            todayBtn: 1,
            autoclose: 1,
            todayHighlight: 1,
            startView: 2,
            minView: 2,
            forceParse: 0
        });
        tinymce.init({
            selector: "#tinymce_basic",
            plugins: [
                "advlist autolink lists link image charmap print preview hr anchor pagebreak",
                "searchreplace wordcount visualblocks visualchars code fullscreen",
                "insertdatetime media nonbreaking save table contextmenu directionality",
                "emoticons template paste textcolor"
            ],
            toolbar: "bold italic | forecolor backcolor | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image"
        });
        $("#btn_save").click(function(){
            /*var type = $("input[name='type']:checked").val();
            if(type == 'Pic' && $("#file").val() == ""){
                layer.alert("请选择图片！");
                return false;
            }
            if(type == 'Text' && $("#tinymce_basic").val() == ""){
                layer.alert("请填写内容！");
                return false;
            }*/
            $("#advertForm").submit();
            return false;
        });
        $("input[name='type']").change(function(){
            var type = $("input[name='type']:checked").val();
            if(type == 'Pic'){
                $("#textArea").css("display","none");
                $("#picArea").css("display","");
            }else{
                $("#textArea").css("display","");
                $("#picArea").css("display","none");
            }
        });
        $("input[name='type']").change();
        $("#del").click(function(){
            layer.confirm('确定删除？', {
                btn: ['确定','取消']
            }, function(){
                var id  = $("#adId").val();
                location.href = "${ctx}/advert/del/" + id;
            });
        });
    });
</script>