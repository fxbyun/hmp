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
                            <i class="icon-tags"></i> 卡信息
                        </div>
                        <div class="row-fluid">
                            <div style="width: 500px;margin: 10px auto;">
                                <c:choose>
                                    <c:when test="${patient != null}">
                                        <p><b>姓名：</b><span>${patient.name}</span></p>
                                        <p><b>年龄：</b><span>${patient.age}</span></p>
                                        <p><b>手机号：</b><span>${patient.mobile}</span></p>
                                        <p><b>住址：</b><span>${patient.address}</span></p>
                                        <p><b>身份证：</b><span>${patient.sfId}</span></p>
                                        <p><b>病史：</b><span>
                                            <c:if test="${not empty patient.patientTagList}">
                                                <c:forEach items="${patient.patientTagList}" var="tag">
                                                    ${tag.name} &nbsp;
                                                </c:forEach>
                                            </c:if>
                                            </span>
                                        </p>
                                        <p><a href="${ctx}/card/list" class="btn btn-info">返回列表</a></p>
                                    </c:when>
                                    <c:otherwise>
                                        <h4>卡还未使用</h4>
                                        <p>
                                            <a href="${ctx}/card/del/${card.id}" class="btn btn-info">删除</a> &nbsp;&nbsp;
                                            <a href="${ctx}/card/list" class="btn btn-info">返回列表</a>
                                        </p>
                                    </c:otherwise>
                                </c:choose>
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
        $("#btn_next").click(function(){
            var cardNo = $("#cardNo").val();
            $("#cardNo").val(cardNo * 1 + 1 * 1);
        });
        $("#btn_save").click(function(){
            $("#cardForm").submit();
            return false;
        });
    });
</script>