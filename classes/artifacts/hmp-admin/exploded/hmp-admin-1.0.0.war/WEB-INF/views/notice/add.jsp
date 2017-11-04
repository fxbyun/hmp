<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<div id="body" style="height: 120%">
    <section id="main">
        <div class="container">
            <div class="row-fluid">
                <div class="span12">
                    <div class="content-box">
                        <div class="content-box-header">
                            <i class="icon-plus"></i> 发布通知
                        </div>
                        <div class="row-fluid">
                            <div style="padding: 15px;width: 700px;margin: 0 auto;">
                                <form id="noticeForm" modelAttribute="notice" action="${ctx}/notice/add" method="post" class="form-horizontal">
                                    <input type="hidden" name="id" value="${notice.id}"/>
                                    <label><b>标题：</b></label>
                                    <input type="text" name="subject" id="subject" class="input-medium" value="${notice.subject}"/>
                                    <label><b>内容：</b></label>
                                    <textarea id="tinymce_basic" name="content" id="content" style="height: 300px">${notice.content}</textarea>
                                    <br/> <br/>
                                    <button type="button" class="btn btn-info" id="btn_save">发布</button>&nbsp;&nbsp;
                                    <a href="${ctx}/notice/list" class="btn btn-info">返回列表</a>
                                </form>
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
            if($("#subject").val() == ""){
                layer.alert("请输入通知标题！");
                return false;
            }
            if($("#content").val() == ""){
                layer.alert("请输入通知内容！");
                return false;
            }
            $("#noticeForm").submit();
            return false;
        });
    });
</script>