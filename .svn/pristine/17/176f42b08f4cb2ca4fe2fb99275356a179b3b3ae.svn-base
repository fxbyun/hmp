<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/9/20
  Time: 11:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/staticInclude.jsp" %>
<%@ taglib prefix="hmp" uri="/WEB-INF/TLD/HmpLoadStaticFile.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>编辑药品</title>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/advertising/styles/validity.css" type="css"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/validityManage/validity.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/jquery.form/jquery.form.js" type="js"/>
    <script>
        $(function () {

        })

    </script>
</head>
<body style="background-color: #fff;">
<form name="iaiLossDetail" id="lossDetailForm" action="${ctx}/adv/IaiLossManage/bombBox/saveMedTag" method="post">
    <div class="tag-medicine">
        <div class="content-left text-right">
            <p>
                <label for="medName">药品名称</label>
                <input type="text" name="MedicineName" value="${medPrivate.name}" class="form-control" id="medName"
                       readonly="readonly">
                <input type="text" id="medPrivateId" name="medPrivateId" value="${medPrivate.id}" class="form-control"
                       style="display: none">
                <input type="text" id="iaiLossId" name="iaiLossId" value="${iaiLoss.id}" class="form-control"
                       style="display: none">
                <%--<input type="text" name="iaiIntoId" value="${iaiInto.id}" class="form-control" style="display: none">
                <c:if test="${not empty detail}">
                    <input type="text" name="detailId" value="${detail.id}" class="form-control" style="display: none">
                </c:if>--%>
            </p>
            <p>
                <label for="company">药厂/产地</label>
                <select name="companyId" id="company" class="form-control">
                    <c:forEach items="${companyMap}" var="item">
                        <option value="${item.key}">${item.value}</option>
                    </c:forEach>
                </select>
                <%--<label for="medAddress">药厂/产地</label><a href="javascript:" id="btnSelectCompany" class="btn btn-success"
                                                        style="margin-left: 10px;">选择</a>
                <input type="text" name="medAddress" value="<c:if test="${not empty medPrivate}">${medPrivate.defaultCompany.name}</c:if>" class="form-control" readonly id="medAddress" style="width: 50%;">
                <input type="hidden" name="medAddressId" value="<c:if test="${not empty medPrivate}">${medPrivate.defaultCompany.id}</c:if>" class="form-control" readonly id="medAddressId" style="width: 50%;">--%>
            </p>
            <p>
                <label for="standard">规&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;格</label>
                <input type="text" name="standard"
                       value="<c:if test="${not empty medPrivate}">${medPrivate.standard}</c:if>" class="form-control"
                       readonly="readonly" id="standard">
            </p>


        </div>
        <div class="content-right text-right">
            <p>
                <%--<label for="validityDate">有&nbsp;效&nbsp;期</label>
                <input type="text" name="validityDate" value="<c:if test="${not empty detail}"><fmt:formatDate value="${detail.validityDate}" pattern="yyyy/MM/dd"/></c:if>" class="form-control form_day" readonly id="validityDate">--%>
                <label for="validityDate">有&nbsp;效&nbsp;期</label>
                <select name="detailId" id="validityDate" class="form-control">
                    <c:forEach items="${details}" var="detail" varStatus="status">
                        <c:if test="${status.index==0}">
                            <option value="${detail.id}" selected="selected"><fmt:formatDate
                                    value="${detail.validityDate}" pattern="yyyy/MM/dd"/></option>
                        </c:if>

                        <c:if test="${status.index!=0}">
                            <option value="${detail.id}"><fmt:formatDate value="${detail.validityDate}"
                                                                         pattern="yyyy/MM/dd"/></option>
                        </c:if>
                    </c:forEach>
                </select>
            </p>

            <p>
                <label for="lossNumber">损耗数量</label>
                <input type="number" min="1" name="totalNumber" value="" class="form-control " hmp-input-number
                       id="lossNumber">
            </p>

            <p>
                <label for="medUnit">统计单位</label>
                <%--<input type="text" class="form-control" id="medUnit">--%>
                <select id="medUnit" name="medicineUnit" id="selMedicineUnit" class="form-control" readonly="readonly"
                        disabled="disabled">
                    <c:forEach var="u" items="${medicineUnits}">
                        <c:if test="${not empty medPrivate}">
                            <option value="${u.key}"
                                    <c:if test='${medPrivate.unit==u.key}'>selected</c:if>>${u.value}</option>
                        </c:if>

                        <c:if test="${empty medPrivate}">
                            <option value="${u.key}"
                                    <c:if test='${med.unit==u.key}'>selected</c:if>>${u.value}</option>
                        </c:if>

                    </c:forEach>
                </select>
            </p>


        </div>
    </div>
</form>
<div class="text-center" style="margin-top: 15px;">
    <button class="btn btn-success" id="saveIaiDetail" type="button" style="width: 80px; margin-right: 5px;">保存</button>
    <button class="btn btn-default" id="btnClose" type="button" style="width: 80px;">关闭</button>
</div>
<script>
    $(function () {
        $("#company").change(function () {
            var url = "${ctx}/IaiLossManage/bombBox/selectCompany";
            var medId = $("#medPrivateId").val();
            var companyId = $("#company").val();
            $.postJSON(url, {"medPrivateId": medId, "companyId": companyId}, function (data) {
                if (data.success) {
                    var htmlText = data.data;
                    $("#validityDate").empty();
                    $("#validityDate").append(htmlText);
                } else {
                    layer.msg("有效期获取失败！");
                }
            })
        });

        $("#saveIaiDetail").click(function () {

            var detailId = $("#validityDate").val();
            var lossNumber = $("#lossNumber").val();
            var lossId = $("#iaiLossId").val();
            var url = "${ctx}/adv/IaiLossManage/bombBox/saveMedTag";
            $.postJSON(url, {"detailId": detailId, "lossNumber": lossNumber, "lossId": lossId}, function (data) {
                if (data.success) {
                    parent.location.reload();
                    parent.layer.closeAll();
                } else {
                    layer.msg("保存失败！请联系技术人员。");
                }
            })
        });


    })
</script>


</body>
</html>
