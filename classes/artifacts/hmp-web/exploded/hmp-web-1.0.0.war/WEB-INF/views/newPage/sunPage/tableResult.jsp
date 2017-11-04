<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/10/27
  Time: 11:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/staticInclude.jsp" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>Title</title>
    <script>
        $(function () {
            var index = parent.layer.getFrameIndex(window.name);
            $("#btnClose").click(function () {
                parent.layer.close(index);
            });
        });

        /**
         *  文件 地址 和文件类型
         * @param url
         * @param type
         */
        function openFileWindow(url, type) {
            window.open(url);
        }
    </script>
</head>
<body style="background-color: #fff;">
<div class="charge-content" style="padding: 15px 15px 15px 0;">
    <div class="form-horizontal">
        <div class="form-group" style="margin: 0 0 15px;">
            <label class="col-xs-2 col-sm-2 text-right" style="padding: 0;">机构名称</label>
            <div class="col-xs-4 col-sm-4">
                <input type="text" class="form-control" value="${doctor.outpatientService}" disabled>
            </div>
            <label class="col-xs-2 col-sm-2 text-right" style="padding: 0;">诊断时间</label>
            <div class="col-xs-4 col-sm-4">

                <input type="text" class="form-control"
                       value="<fmt:formatDate value="${emrJClassAdviceDict.emr.createOn}" pattern="yyyy/MM/dd HH:mm"/>"
                       disabled>
            </div>
        </div>
        <div class="form-group" style="margin: 0 0 15px;">
            <label class="col-xs-2 col-sm-2 text-right" style="padding: 0;">检查医生</label>
            <div class="col-xs-4 col-sm-4">
                <input type="text" class="form-control" value="${doctor.name}" disabled>
            </div>
            <label class="col-xs-2 col-sm-2 text-right" style="padding: 0;">检查项目</label>
            <div class="col-xs-4 col-sm-4">
                <input type="text" class="form-control" value="${emrJClassAdviceDict.adviceName}" disabled>
            </div>
        </div>
        <div class="form-group" style="margin: 0 0 15px;">
            <label class="col-xs-2 col-sm-2 text-right" style="padding: 0;">检查详情</label>
            <div class="col-xs-10 col-sm-10">
                <textarea class="form-control" style="height: 100px;"
                          disabled>${emrJClassAdviceDict.resultInfo}</textarea>
            </div>

        </div>
        <div class="form-group" style="margin: 0 0 15px;">
            <label class="col-xs-2 col-sm-2 text-right" style="padding: 0;">检查附件</label>
            <div class="col-xs-10 col-sm-10">
                <%--<a href="#" title="xxxxx" style="margin-left: 15px; color: #218e3f;">xxxxxx.jpg</a>--%>
                <c:forEach items="${emrJClassAdviceDict.examLabFileList}" var="oneJClass">
                    <c:if test="${not empty oneJClass.fileName}">
                        <input type="button"
                               onclick="openFileWindow('${ctx}/fileDir/${doctor.id}/${oneJClass.fileName}','${oneJClass.types}')"
                               value="${oneJClass.fileName}"
                               class="btn btn-warning"
                               style="display: inline-block; margin-left: 15px;">
                    </c:if>
                </c:forEach>
            </div>
        </div>

    </div>


    <div class="text-center" style="margin-top: 22px;">
        <button id="btnClose" type="button" class="btn btn-default" style="width: 100px; margin-left: 15px;">关闭</button>
    </div>

</div>
</body>
</html>
