<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/10/9
  Time: 12:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/advInclude.jsp" %>
<%@ taglib prefix="hmp" uri="/WEB-INF/TLD/HmpLoadStaticFile.tld" %>
<html>
<head>
    <title>供应商管理</title>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/advertising/styles/validity.css" type="css"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/validityManage/validity.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/styles/style.css" type="css"/>

</head>
<body style="background-color: #fff;">
<div class="editSupplier">
    <div>
        <table width="100%">
            <colgroup width="60%"></colgroup>
            <colgroup width="40%"></colgroup>
            <thead>
            <tr>
                <th>供应商</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody id="tb_supplier">
            <c:if test="${not empty supplierList}">
                <c:forEach items="${supplierList}" var="supplier">
                    <tr>
                        <td>${supplier.name}</td>
                        <td>
                            <button class="btn btn-default" onclick="fn_EditSupplier(1)"
                                    style="margin-right: 5px; width: 80px;">
                                编辑
                            </button>
                            <button class="btn btn-default" onclick="delSupplier(this,${supplier.id})"><i
                                    class="fa fa-trash-o"></i> 删除
                            </button>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
            <tr>
                <td>真功夫</td>
                <td>
                    <button class="btn btn-default" onclick="fn_EditSupplier(${supplier.id})"
                            style="margin-right: 5px; width: 80px;">
                        编辑
                    </button>
                    <button class="btn btn-default" onclick="delSupplier(this,${supplier.id})"><i
                            class="fa fa-trash-o"></i> 删除
                    </button>
                </td>
            </tr>

            </tbody>
        </table>
    </div>
    <p class="text-center" style="margin-top: 20px;">
        <button class="btn btn-success" type="button" onclick="fn_AddSupplier()"><i class="fa fa-plus"></i> 新增供应商
        </button>
        <button id="btnCloseSupplier" class="btn btn-default" type="button" style="width: 80px; margin-left: 10px;">关闭
        </button>
    </p>
</div>
<script>
    $(function () {
        $("#btnCloseSupplier").click(function () {
            parent.layer.close();
            parent.location.reload();
        });
    })

</script>


</body>
</html>
