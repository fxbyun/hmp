<%--@elvariable id="mp" type="com.qiaobei.hmp.modules.entity.MedicinePrivate"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/advInclude.jsp" %>
<%@ taglib prefix="hmp" uri="/WEB-INF/TLD/HmpLoadStaticFile.tld" %>
<html>
<head>
    <title>修改药品价格</title>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/advertising/styles/validity.css" type="css"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/jquery.form/jquery.form.js" type="js"/>
</head>
<body style="background-color: #fff;">
<div style="padding: 20px;">
    <div class="row">
        <div class="col-xs-5 col-xs-offset-1">
            <div class="form-group">
                <input type="text" name="price" id="price" class="form-control" value="${mp.price}">
            </div>
        </div>
        <div class="col-xs-5" style="margin-left: -20px;">
            <div class="form-group">
                <select id="selMedicineUnit" class="form-control text-success" name="unit">
                    <c:forEach var="u" items="${medicineUnits}">
                        <option value="${u.key}"
                                <c:if test='${mp.unit==u.key}'>selected</c:if>>${u.value}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
    </div>
    <div class="text-center" style="margin-top: 10px;">
        <button id="btnUpdateMed" class="btn btn-success" type="button" style="margin-right: 10px; width: 70px;">确认
        </button>
        <button id="btnClose" class="btn btn-default" type="button" style="width: 70px;">取消</button>
    </div>
</div>


<script type="text/javascript">
    $(function () {
        $("#btnUpdateMed").click(function () {
            var price = $("#price").val();
            var unit = $("#selMedicineUnit").val();
            $.postJSON(ctx + "/retail/updateMed", {price: price, unit: unit, medId:${mp.id}}, function (data) {
                if (data.success) {
                    var index = parent.layer.getFrameIndex(window.name);
                    layer.msg("修改成功！");
                    parent.layer.close(index);
                } else {
                    layer.msg("修改失败！");
                }

            });
        })
    });
</script>


</body>
</html>
