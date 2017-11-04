<%--@elvariable id="types" type="java.lang.String"--%>
<%--@elvariable id="subId" type="java.lang.Long"--%>
<%--@elvariable id="subTwoId" type="java.lang.Long"--%>
<%--@elvariable id="emrJClassAdviceDict" type="com.qiaobei.hmp.modules.entity.JClassAdviceDict"--%>
<%--@elvariable id="jLabClass" type="com.qiaobei.hmp.modules.entity.JLabClass"--%>
<%--@elvariable id="jExamClass" type="com.qiaobei.hmp.modules.entity.JExamClass"--%>
<%--@elvariable id="exp2List" type="java.util.List"--%>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/1
  Time: 11:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/staticInclude.jsp" %>
<%@ taglib prefix="hmp" uri="/WEB-INF/TLD/HmpLoadStaticFile.tld" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>编辑项目</title>
    <script>
        var tmpOldId;
        var type = "${emrJClassAdviceDict.type}";
        $(function () {

            var index = parent.layer.getFrameIndex(window.name);
            $("#btnClose").click(function () {
                parent.layer.close(index);
            });

            $("#className").change(function () {
                if (${emrJClassAdviceDict.type=='JianYan'}) {
                    return;
                }
                $("#exp2").empty();
                $.getJSON("${ctx}/fragment/getExamAdviceDictSubById?id=" + $("#className").val(), function (result) {
                    $.each(result.data, function (i, n) {
                        $("#exp2").append("<option value='" + n.id + "'>" + n.className + "</option>")
                    });
                    $("#exp2").change();
                });
            });
        });
        function addExamOrLabToSystem() {
            if ($("#adviceName").val() == "") {
                parent.layer.msg("请输入${emrJClassAdviceDict.type.getName()}名称");
                return;
            }

            var classSubId = (($("#exp2").val() == undefined || $("#exp2").val() == "") ? 0 : $("#exp2").val());
            $.postJSON("${ctx}/fragment/addExamOrLabToSystem", {
                type: "${emrJClassAdviceDict.type}",
                classId: $("#className").val(),
                classSubId: classSubId,
                examOrLabName: $("#adviceName").val()
            }, function (ret) {
                if (ret) {
                    parent.layer.msg("添加成功!");
                    parent.layer.close(parent.layer.getFrameIndex(window.name));
                } else {
                    parent.parent.parent.Alert.error(ret.msg, "后台发生错误!");
                }
            })


        }
    </script>
</head>
<body style="background-color: #fff;">
<form:form modelAttribute="emrJClassAdviceDict" action="/fragment/saveJDoctorExamLabDetil" method="post">

    <input type="hidden" value="${emrJClassAdviceDict.type}" name="type"/>
    <div style="margin: 15px 30px;" class="form-horizontal">
        <div class="form-group">
            <label class="col-xs-3 col-sm-3 text-center">项目名称：</label>
            <div class="col-xs-8 col-sm-8">
                    <%--<form:input path="examLabName" class="form-control" readonly="true"/>--%>
                <select id="className" name="className" class="form-control">
                    <c:forEach items="${ClassList}" var="oneClass">
                        <option value="${oneClass.id}">${oneClass.className}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-3 col-sm-3 text-center">
                    ${emrJClassAdviceDict.type.getName()}大项：
            </label>
            <div class="col-xs-8 col-sm-8">
                <c:choose>
                    <%-- 检验 --%>
                    <c:when test="${emrJClassAdviceDict.type=='JianYan'}">
                        <input type="text"
                               name="adviceName"
                               id="adviceName"
                               class="form-control"
                               placeholder="请输入检验子项名">
                    </c:when>
                    <%-- 检查 --%>
                    <c:otherwise>
                        <form:select path="exp2"
                                     items="${ClassList.get(0).jExamSubclassDictList}"
                                     itemLabel="className"
                                     class="form-control"
                                     itemValue="id"/>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
            <%-- 检查部位 --%>

        <c:if test="${emrJClassAdviceDict.type=='JianCha'}">
            <div class="form-group">
                <label class="col-xs-3 col-sm-3 text-center">
                    检查部位：
                </label>
                <div class="col-xs-8 col-sm-8">
                    <input type="text"
                           name="adviceName"
                           id="adviceName"
                           class="form-control"
                           placeholder="请输入检查子项名">
                </div>
            </div>
        </c:if>

        <div class="form-group text-center">
            <button type="button" class="btn btn-success" onclick="addExamOrLabToSystem()">确定</button>
            <button id="btnClose" type="button" class="btn btn-default">取消</button>
        </div>
    </div>
</form:form>

</body>
</html>
