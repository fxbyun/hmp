<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/10/27
  Time: 15:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/staticInclude.jsp" %>
<%@ taglib prefix="hmp" uri="/WEB-INF/TLD/HmpLoadStaticFile.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>添加项目</title>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/application.js" type="js"/>
    <script>
        $(function () {
            var index = parent.layer.getFrameIndex(window.name);
            $("#btnClose").click(function () {
                parent.layer.close(index);
            });
        });

        /**
         * 当下拉框改变
         */
        function cheangType() {
            $("#type").val($("select option:selected").val());
            $("form").submit();
        }

        function addToDoctorWxamLab() {
            var idArr = new Array();
            $.map($(".diagnosis-label-box input[type='checkbox']:checked"), function (i) {
                idArr.push($(i).val());
            });

            $.postJSON("/fragment/addJianChaOrJianYan",
                    {
                        ids: idArr,
                        type: $("select option:selected").val()
                    }, function (ret) {
                        if (ret.success) {
                            parent.layer.msg("增加成功!");
                            setTimeout(function () {
                                parent.window.location.reload();
                            }, 1000);
                        }
                    })

        }
    </script>
</head>
<body style="background-color: #fff;">
<form action="${ctx}/fragment/saveItemsDetail" method="post">
    <div class="save-items" style="padding: 10px;">
        <div class="diagnosis-label-box" style="height: 260px;">
            <c:forEach items="${jExamClassList}" var="one">
            <span class="checkbox">
                <label class="tag span-tag">
                <input type="checkbox" value="${one.id}">&nbsp; ${one.className}</label>
            </span>
            </c:forEach>
        </div>
        <div class="text-center up-dpwn-bt" style="width: 100%;">
            <select name="type" id="type" class="form-control"
                    onchange="cheangType(this)"
                    style="display: inline-block; width: 80px; vertical-align: middle;">
                <option value="JianCha"
                        <c:if test="${type=='JianCha'}">selected</c:if> >检查
                </option>
                <option value="JianYan"
                        <c:if test="${type=='JianYan'}">selected</c:if> >检验
                </option>
            </select>
            <button type="button" class="btn btn-default">上一批</button>
            <button type="button" class="btn btn-default">下一批</button>
            <div class="input-group" style="display: inline-block;">
                <input type="text" class="form-control" style="width: 136px;" value="">
                <span class="input-group-btn">
                    <button onclick="fn_LoadSymptomTag()" class="btn btn-primary" type="button"><i class="fa fa-times"
                                                                                                   style="padding: 3px 0;"></i></button>
                    <button onclick="loadSymptoms(0)" class="btn btn-primary" style="border-left: 1px solid #fff;"
                            type="button">
                        <i class="fa fa-search" style="padding: 3px 0;"></i></button>
                </span>
            </div>
        </div>
        <div class="text-center btn-wd-mr">
            <button type="button" class="btn btn-success" onclick="addToDoctorWxamLab()">确认</button>
            <button id="btnClose" type="button" class="btn btn-default">取消</button>
        </div>
    </div>
</form>
</body>
</html>
