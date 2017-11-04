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
            var HTML_NAME = parent.$(".validityManage").attr("id");
            var index = parent.layer.getFrameIndex(window.name);

            /*用户提醒*/
            $("#standard").click(function () {
                layer.msg("统计单位/规格可以到开始坐诊处修改!");
            });

            $("#btnClose").click(function () {
                parent.layer.close(index);
            });

            $("#saveIaiDetail").click(function () {
                if (checkEmpty()) {
                    layer.msg(checkEmpty());
                    return false;
                }


                $("#addIaiDetail").ajaxSubmit({
                    url: "${ctx}/adv/validityManage/bombBox/addIaiIntoDetail",
                    success: function (data) {
                        if (data.success == true) {
                            layer.msg(data.msg)
                            if (HTML_NAME == "RK_HTML") {
                                parent.$("#iaiDetail").load("${ctx}/adv/fragment/validityManage/showIaiDetails?iaiIntoId=" +${iaiInto.id});
                            } else {
                                parent.location.reload();
                            }
                            parent.layer.closeAll();
                        }
                    },
                    error: function () {
                        label.msg("药品入库失败！");
                    }

                })

            });

        })


    </script>
</head>
<body style="background-color: #fff;">
<form name="iaiDetail" id="addIaiDetail" action="${ctx}/adv/validityManage/bombBox/addIaiIntoDetail" method="post">
    <div class="tag-medicine">
        <div class="content-left text-right">
            <p>
                <label for="medName">药品名称</label>
                <input type="text" name="MedicineName" value="${med.name}" class="form-control" id="medName"
                       readonly="readonly">
                <input type="text" name="medicineId" value="${med.id}" class="form-control" style="display: none">
                <input type="text" name="iaiIntoId" value="${iaiInto.id}" class="form-control" style="display: none">
                <c:if test="${not empty detail}">
                    <input type="text" name="detailId" value="${detail.id}" class="form-control" style="display: none">
                </c:if>
            </p>
            <p>
                <label for="medAddress">药厂/产地</label><a href="javascript:" id="btnSelectCompany" class="btn btn-success"
                                                        style="margin-left: 10px;">选择</a>
                <input type="text" name="medAddress"
                       value="<c:if test="${not empty medPrivate}">${medPrivate.defaultCompany.name}</c:if>"
                       class="form-control" readonly id="medAddress" style="width: 50%;">
                <input type="hidden" name="medAddressId"
                       value="<c:if test="${not empty medPrivate}">${medPrivate.defaultCompany.id}</c:if>"
                       class="form-control" readonly id="medAddressId" style="width: 50%;">
            </p>
            <p style="display: none">
                <label for="bayingPrice">进&nbsp;货&nbsp;价</label>
                <input type="number" min="0" value="${not empty detail?detail.bayingPrice:0 }"
                       name="bayingPrice" class="form-control" id="bayingPrice">
            </p>
            <p>
                <label for="standard">规&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;格</label>
                <input readonly type="text" name="standard"
                       value="<c:if test="${not empty medPrivate}">${medPrivate.standard}</c:if>" class="form-control"
                       id="standard">
            </p>


        </div>
        <div class="content-right text-right">
            <p>
                <label for="validityDate">有&nbsp;效&nbsp;期</label>
                <input type="text" name="validityDate"
                       value="<c:if test="${not empty detail}"><fmt:formatDate value="${detail.validityDate}" pattern="yyyy/MM/dd"/></c:if>"
                       class="form-control form_day" readonly id="validityDate">
            </p>
            <p style="display: none">
                <label for="barCode">条&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码</label>
                <input type="text" name="barCode" value="<c:if test="${not empty detail}">${detail.barCode}</c:if>"
                       class="form-control" id="barCode">
            </p>
            <p style="display: none">
                <label for="price">零&nbsp;售&nbsp;价</label>
                <input type="number" min="0" value="${not empty medPrivate?medPrivate.price:0 }"
                       name="price" hmp-input-double class="form-control" id="price">
            </p>
            <p>
                <label for="totalNumber">产品数量</label>
                <input type="number" min="1" name="totalNumber"
                       value="<c:if test="${not empty detail}">${detail.totalNumber}</c:if>" class="form-control "
                       hmp-input-number id="totalNumber">
            </p>

            <p style="display: none">
                <label for="warnLine">预警线</label>
                <input type="number" min="1" name="warnLine"
                       value="${not empty detail?detail.warnLine:""}" class="form-control "
                       hmp-input-number id="warnLine">
            </p>

            <p>
                <label for="medUnit">统计单位</label>
                <%--<input type="text" class="form-control" id="medUnit">--%>
                <select id="medUnit" name="medicineUnit" id="selMedicineUnit" class="form-control" readonly
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
    function checkEmpty() {
        /*if ($("#bayingPrice").val() == "" || $("#bayingPrice").val() == undefined) {
         return "进货价不能为空哦！";
         }*/

        if ($("#validityDate").val() == "" || $("#validityDate").val() == undefined) {
            return "药品有效期不能为空哦！";
        }

        /*if ($("#price").val() == "" || $("#price").val() == undefined) {
         return "零售价不能为空哦！";
         }*/

        if ($("#totalNumber").val() == "" || $("#totalNumber").val() == undefined) {
            return "产品数量不能为空哦！";
        }

        /*if ($("#warnLine").val() == "" || $("#warnLine").val() == undefined) {
         return "预警线不能为空哦！";
         }*/
        return false;
    }
</script>


</body>
</html>
