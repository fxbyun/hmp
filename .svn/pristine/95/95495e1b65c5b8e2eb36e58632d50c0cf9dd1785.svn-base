<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<!DOCTYPE html>
<html lang="cn">
<head>
    <title>药方管理</title>
    <script>
        function fn_LoadSymptomTag(page, name) {
            if (page == undefined) page = 0;
            if (name == undefined) name = "";
            name = name.replace(/[ ]/g, "");
            $('#divTags').load('/fragment/configSymptomTags?page={0}&name={1}'.format(page, name));
        }

        function fn_LoadSymptomOtherTag(page, name) {
            if (page == undefined) page = 0;
            if (name == undefined) name = "";
            name = name.replace(/[ ]/g, "");
            $('#divOtherTags').load('/fragment/configSymptomOtherTags?page={0}&name={1}'.format(page, name));
        }

        function fn_SelectSymptomOtherTag(tag) {
            if (tag) {
                $.postJSON("/tag/addSymptomTag", {"symptomTag": tag}, function (res) {
                    if (res && res.success) {
                        if (res.msg)layer.msg(res.msg);
                        fn_LoadSymptomTag(0);
                    }
                })
            }
        }

        function fn_SelectSymptomTag(tagId, tag) {
            if (tagId) {
                var index = layer.confirm("删除此症状标签后需重新添加,确认删除？", function () {
                    layer.close(index);
                    $.postJSON("/tag/removeSymptomTag", {"tagId": tagId}, function (res) {
                        if (res && res.success) {
                            if (res.msg)layer.msg(res.msg);
                            fn_LoadSymptomTag(0);
                        }
                    })
                });
            }
        }

        $(function () {
            $("#nav-manage").addClass("active");
            fn_LoadSymptomTag(0);
            fn_LoadSymptomOtherTag(0);

        });
    </script>
</head>
<body>
<div class="manage">
    <div class="container">
        <ul class="navigation">
            <li><a href="${ctx}/manage" class="btn btn-default">用药分析</a></li>
            <c:if test="${doctor.doctorType!='Sub_Doctor'}">
                <li><a href="${ctx}/message" class="btn btn-default">短信发送</a></li>
            </c:if>
            <li><a href="${ctx}/rp" class="btn btn-default">我的药方</a></li>
            <li><a href="${ctx}/rplib" class="btn btn-default">药方库</a></li>
            <li class="active"><a href="#" class="btn btn-default">数据整理</a></li>
            <%--<c:if test="${doctor.doctorType!='Sub_Doctor'}">
                <li><a href="${ctx}/appointment" class="btn btn-default">预约挂号</a></li>
            </c:if>--%>
            <li><a href="${ctx}/appointment" class="btn btn-default">预约挂号</a></li>
            <c:if test="${doctor.doctorType!='Sub_Doctor'}">
                <li><a href="${ctx}/validityManage" class="btn btn-default">有效期管理</a></li>
            </c:if>
            <c:if test="${doctor.doctorType!='Sub_Doctor'}">
                <li><a href="${ctx}/financial" class="btn btn-default">财务统计</a></li>
            </c:if>

            <ul class="navigation-sub">
                <li class="active"><a href="#" class="btn btn-default">患者主诉整理</a></li>
                <li><a href="${ctx}/config/diagnosis" class="btn btn-default">诊断结果整理</a></li>
            </ul>
        </ul>

        <div class="diagnose">

            <div class="data-tag">
                <p><i class="icon-data-04"></i>常用症状</p>

                <div id="divTags" class="diagnosis-label-box"></div>
            </div>
            <div class="data-dia"><i class="img-d05"></i></div>
            <div class="data-tag">
                <p><i class="icon-data-04"></i>症状库</p>

                <div id="divOtherTags" class="diagnosis-label-box"></div>
            </div>
        </div>
    </div>
</div>
</body>
</html>