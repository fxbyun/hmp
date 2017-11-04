<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/10/27
  Time: 15:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/staticInclude.jsp" %>
<%@ taglib prefix="hmp" uri="/WEB-INF/TLD/HmpLoadStaticFile.tld" %>
<html>
<head>
    <title>修改项目价格</title>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/application.js" type="js"/>
    <script>
        $(function () {
            var index = parent.layer.getFrameIndex(window.name);
            $("#btnClose").click(function () {
                parent.layer.close(index);
            });
        });

        function updateJDoctorExamLabPrice(id) {
            $.postJSON("/fragment/updateJDoctrExamLabPrice",
                    {
                        id: id,
                        price: $("#price").val()
                    },
                    function (ret) {
                        if (ret.success) {
                            layer.msg("修改成功");
                            setTimeout(function () {
                                parent.window.location.reload();
                            }, 1000);
                        }
                    })
        }
    </script>
</head>
<body style="background-color: #fff;">
<div style="padding: 10px;" class="text-center">
    <div class="form-group"><h5>${jDoctorExamLab.examLabName}</h5></div>
    <div class="form-group"><input type="number" class="form-control"
                                   id="price"
                                   value="${jDoctorExamLab.price}"
                                   style="width: 40%; display: inline-block;"> 元/次
    </div>
    <div class="form-group btn-wd-mr">
        <button type="button"
                onclick="updateJDoctorExamLabPrice(${jDoctorExamLab.id})"
                class="btn btn-success"
                style="width: 80px;">确定
        </button>
        <button id="btnClose" type="button" class="btn btn-default" style="width: 80px;">取消</button>
    </div>
</div>
</body>
</html>
