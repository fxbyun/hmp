<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<div id="body" style="height: 100%">
    <section id="main">
        <div class="container">
            <div class="row-fluid">
                <div class="span12">
                    <div class="content-box">
                        <div class="content-box-header">
                            <i class="icon-plus"></i> 新增卡号
                        </div>
                        <div class="row-fluid">
                            <div style="padding: 15px;width: 300px;margin: 0 auto;">
                                <form id="cardForm" modelAttribute="card" action="${ctx}/card/add" method="post" class="form-horizontal">
                                    <label><b>卡号：</b></label>
                                    <input type="text" name="cardNo" id="cardNo" class="input-medium" value="${card.cardNo}"/>
                                    <label><b>识别码：</b></label>
                                    <input type="text" name="udid" id="udid" class="input-medium"/>
                                    <br/> <br/>
                                    <button type="button" class="btn btn-info" id="btn_save">保存</button>&nbsp;&nbsp;
                                    <a href="${ctx}/card/list" class="btn btn-info">返回列表</a>
                                </form>
                                <c:if test="${error != null}">
                                    <div class="alert alert-danger mt10">${error}</div>
                                </c:if>
                                <c:if test="${msg != null}">
                                    <div class="alert alert-success mt10">${msg}</div>
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
            <div>©2015 深圳市乔北科技有限责任公司版权所有 <a href="http://www.miitbeian.gov.cn/publish/query/indexFirst.action" target="_blank">粤ICP备15089657号-2</a></div>
        </h5>
    </footer>
</div>
<script type="text/javascript">
    $(function(){
        $("#udid").focus().select();
        $("#btn_save").click(function(){
            if(isNaN($("#cardNo").val())){
                layer.alert("卡号格式不对！");
                return false;
            }
            $("#cardForm").submit();
            return false;
        });
        $('#udid').keydown(function (event) {
            if (event.keyCode == 13) {//keyCode=13是回车键
                $("#btn_save").click();
                return false;
            }
        });
    });
</script>