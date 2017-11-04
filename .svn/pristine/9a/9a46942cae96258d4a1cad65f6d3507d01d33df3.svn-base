<%--@elvariable id="types" type="java.lang.String"--%>
<%--@elvariable id="subId" type="java.lang.Long"--%>
<%--@elvariable id="subTwoId" type="java.lang.Long"--%>
<%--@elvariable id="emrJClassAdviceDict" type="com.qiaobei.hmp.modules.entity.JClassAdviceDict"--%>
<%--@elvariable id="jLabClass" type="com.qiaobei.hmp.modules.entity.JLabClass"--%>
<%--@elvariable id="jExamClass" type="com.qiaobei.hmp.modules.entity.JExamClass"--%>
<%--@elvariable id="exp2List" type="java.util.List"--%>
<%--@elvariable id="doctor" type="com.qiaobei.hmp.modules.entity.Doctor"--%>
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
        var doctorType = '${loginDoctor.doctorType}';
        //doctor是主医生
        var allowUpdatePrice = ${doctor.allowSubDoctorUpdatePrice==''|| !doctor.allowSubDoctorUpdatePrice?false:true};
        $(function () {
            if (type == "JianYan") {
                tmpOldId = $("#exp1").val();
            } else {
                tmpOldId = $("#exp3").val();
            }
            var index = parent.layer.getFrameIndex(window.name);
            $("#btnClose").click(function () {
                parent.layer.close(index);
            });

            $("#exp2").change(function () {
                if (${emrJClassAdviceDict.type=='JianYan'}) {
                    return;
                }
                $("#exp3").empty();
                $.getJSON("${ctx}/fragment/getExamAdviceDictBySubId?id=" + $("#exp2").val(), function (result) {
                    $.each(result.data, function (i, n) {
                        $("#exp3").append("<option value='" + n.id + "'>" + n.adviceName + "</option>")
                    });
                    $("#exp3").change();
                });
            });
            if (${subId!=0 && not empty subId }) {
                if ("${emrJClassAdviceDict.type}" == "JianCha") {
                    $("#exp2").val("${subId}");
                    $("#exp2").trigger("change");
                    $("#exp2").attr("disabled", "disabled")
                    if ("${subTwoId}" != 0) {
                        $("#exp3").attr("disabled", "disabled")
                        var interHunder = setInterval(function () {
                            $("#exp3").val("${subTwoId}");
                        }, 500);
                        if ($("#exp3").val() == "${subTwoId}") {
                            clearInterval(interHunder);
                        }
                    }
                } else {
                    $("#exp1").val("${subId}");
                    $("#exp1").trigger("change");
                    $("#exp1").attr("disabled", "disabled")
                }
            }

            if (${noMonery}) {
                $("#price").val("0");
            }
            //子医生不可编辑价格
            if (doctorType == 'Sub_Doctor' && !allowUpdatePrice) {
                $("#price").prop("readonly", "true");
                $("#price").click(function () {
                    layer.msg("只有诊所的主治医生才能修改价格！");
                });
            }
        });

        function addExamOrLabToParent() {
            var obj = {};
            var adviceName = "";
            if (type == "JianYan") {
                obj["id"] = $("#exp1").val();
                adviceName = $("#exp1").find("option:selected").text();
            } else {
                obj["id"] = $("#exp3").val();
                adviceName = $("#exp3").find("option:selected").text();
            }

            obj["adviceType"] = type;
            obj["adviceName"] = adviceName;
            obj["examOrLabName"] = "${emrJClassAdviceDict.examLabName}";
            obj["info"] = $("#info").val();
            obj["price"] = $("#price").val();
            obj["tmpId"] = "${tmpId}";
            if ("${types}" == "edit")
                obj["oldId"] = tmpOldId;
            parent.parent.addExamOrLab(obj);
            if ("${tmpId}".length < 5) {
                parent.parent.layer.closeAll();
            } else {
                var index = parent.layer.getFrameIndex(window.name);
                parent.parent.layer.close(index);
            }
        }
    </script>
</head>
<body style="background-color: #fff;">
<form:form modelAttribute="emrJClassAdviceDict" action="/fragment/saveJDoctorExamLabDetil" method="post">
    <div style="margin: 15px 30px;" class="form-horizontal">
        <div class="form-group">
            <label class="col-xs-3 col-sm-3 text-center">项目名称：</label>
            <div class="col-xs-8 col-sm-8">
                <form:input path="examLabName" class="form-control" readonly="true"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-3 col-sm-3 text-center">
                    ${emrFile.getMetaValue(emrJClassAdviceDict.type)}大项：
            </label>
            <div class="col-xs-8 col-sm-8">
                <c:choose>
                    <%-- 检验 --%>
                    <c:when test="${emrJClassAdviceDict.type=='JianYan'}">


                        <form:select cssClass="form-control"
                                     path="exp1"
                                     items="${jLabClass.jClassAdviceDictList}"
                                     itemLabel="adviceName"
                                     class="form-control"
                                     itemValue="id"/>
                    </c:when>
                    <%-- 检查 --%>
                    <c:otherwise>
                        <form:select path="exp2"
                                     items="${jExamClass.jExamSubclassDictList}"
                                     itemLabel="className"
                                     class="form-control"
                                     itemValue="id"/>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
            <%-- 检查部位 --%>

        <c:if test="${emrJClassAdviceDict.type!='JianYan'}">
            <div class="form-group">
                <label class="col-xs-3 col-sm-3 text-center">
                    检查部位：
                </label>
                <div class="col-xs-8 col-sm-8">
                    <c:choose>

                        <c:when test="${not empty exp2List }">
                            <form:select path="exp3"
                                         items="${exp2List}"
                                         itemLabel="adviceName"
                                         itemValue="id"
                                         class="form-control"
                            />
                        </c:when>
                        <c:otherwise>
                            <form:select path="exp3"
                                         items="${jExamClass.jExamSubclassDictList.get(0).jClassAdviceDictList}"
                                         itemLabel="adviceName"
                                         itemValue="id"
                                         class="form-control"
                            />
                        </c:otherwise>
                    </c:choose>

                </div>
            </div>
        </c:if>

        <div class="form-group">
            <label class="col-xs-3 col-sm-3 text-center">
                    ${emrFile.getMetaValue(emrJClassAdviceDict.type)}费用：
            </label>
            <div class="col-xs-8 col-sm-8">
                <form:input path="price" type="number" class="form-control " hmp-input-double="hmp-input-double"/>
                    <%--<input type="text" class="form-control">--%>
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-3 col-sm-3 text-right">特殊说明：</label>
            <div class="col-xs-8 col-sm-8">
                <form:textarea path="info" class="form-control" cssstyle="height: 100px;"/>
                    <%--<textarea class="form-control" id="info" style="height: 100px;">无</textarea>--%>
            </div>
        </div>
        <div class="form-group text-center">
            <button type="button" class="btn btn-success" onclick="addExamOrLabToParent()">确定</button>
            <button id="btnClose" type="button" class="btn btn-default">取消</button>
        </div>
    </div>
</form:form>

</body>
</html>
