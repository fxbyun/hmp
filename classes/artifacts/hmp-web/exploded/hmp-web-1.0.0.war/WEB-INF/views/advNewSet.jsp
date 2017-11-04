<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/9/7
  Time: 10:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springside.org.cn/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="hmp" uri="/WEB-INF/TLD/HmpLoadStaticFile.tld" %>
<!DOCTYPE html>
<html>
<head>
    <title>功能设置</title>
    <%--@elvariable id="ctx" type="java.lang.string"--%>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/adv/index.js" type="js"/>
    <script>
        $(function () {
            var ss = $('#showImg').attr("src");
            if (ss == null || ss == "") {
                $("#imgWidth").show();
            } else {
                $("#imgWidth").hide();
            }
        });
    </script>
</head>
<body>
<div class="manage">
    <div class="container">
        <ul class="navigation">
            <li><a href="${ctx}/infro" class="btn btn-default">信息设置</a></li>
            <li class="active"><a href="${ctx}/advertSet" class="btn btn-default">终端机设置</a></li>
            <li><a href="${ctx}/employee" class="btn btn-default">员工管理</a></li>
        </ul>
        <form:form id="advertForm" modelAttribute="adverting" action="${ctx}/adverting/add"
                   method="post" class="form-horizontal"
                   enctype="multipart/form-data"><%--@elvariable id="adverting" type="com.qiaobei.hmp.modules.entity.Adverting"--%>
            <input type="hidden" name="id" value="${adverting.id}" id="adId"/>
            <div class="intro-sign advNewSet" style="margin-top: 20px;">
                <h2 class="text-center" style="margin-bottom: 40px;">功能设置</h2>
                <p>
                    <span>名称</span>
                    <form:input path="name" cssClass="form-control"/>
                        <%--<input class="form-control" type="text">--%>
                </p>
                <p>
                    <span>类型</span>
                        <%--@elvariable id="typeMap" type="java.util.Map"--%>
                    <form:bsradiobuttons path="type" items="${typeMap}" labelCssClass="inline"/>
                        <%--<input type="radio" name="type">图片--%>
                        <%--<input type="radio" name="type">文本--%>
                </p>
                <p class="txt">
                    <span>文本内容</span>
                    <form:textarea path="content" cssClass="form-control"/>
                </p>
                <p class="txtImg">
                    <span>上传图片</span>
                        <%--<input type="file" name="files" id="file" class="form-control"/>--%>
                    <c:if test="${adverting.type =='Pic'}">
                        <a href="#" class="parentImg">
                            <img id="showImg" alt="上传图片" src="${ctx}/fileDir/${doctor.id}/${adverting.file.fileName}">
                        </a>
                    </c:if>
                    <c:if test="${adverting.type !='Pic'}">
                        <img id="showImg" src="" alt="上传图片"/>
                    </c:if>
                    <span id="imgWidth" class="color-red">建议1080x607</span>
                    <br>
                    <a href="javascript:;" class="a-upload">
                        <input type="file" name="files" id="file">选择文件
                    </a>

                </p>
                <p>
                    <span>链接地址</span>
                        <%--<input type="text" class="form-control">--%>
                    <form:input path="url" cssClass="form-control"/>
                </p>
                <p>
                    <span>有效期</span>
                        <%--<form:input path="indate" cssClass="form-control"--%>
                        <%--/>--%>
                    <input type="number" id="indate" name="indate" class="form-control" value="${adverting.indate}"
                           title=""/>
                    天（为空为0，则永久不过期）
                </p>
                <p>
                    <span>序号</span>
                        <%--<form:input   path="orderNo" cssClass="form-control"/>--%>
                    <input type="number" id="orderNo" name="orderNo" class="form-control" value="${adverting.orderNo}"/>
                    默认为上传顺序
                </p>
                <p>
                        <%--@elvariable id="msg" type="java.lang.String"--%>
                    <c:if test="${msg != null}">
                <div class="alert alert-success">${msg}</div>
                </c:if>
                </p>
                <p class="text-center">
                    <button class="btn btn-success" type="button" onclick="sub()">保存</button>
                    <button class="btn btn-default" type="button" onclick="deleteAdving(${adverting.id})">删除</button>
                    <button class="btn btn-success" type="button" onclick="location.href='${ctx}/advertSet'">
                        返回列表
                    </button>
                </p>

            </div>
        </form:form>
    </div>
</div>
</body>

</html>
