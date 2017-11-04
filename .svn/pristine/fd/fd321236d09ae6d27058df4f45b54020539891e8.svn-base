<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/9/19
  Time: 16:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="hmp" uri="/WEB-INF/TLD/HmpLoadStaticFile.tld" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<%@ include file="/WEB-INF/commons/advInclude.jsp" %>
<html>
<head>
    <title>新增进货单</title>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/advertising/styles/validity.css" type="css"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/application.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/validityManage/validity.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/jquery.form/jquery.form.js" type="js"/>
    <script>
        $(function () {
            $("#iaiDetail").load("${ctx}/adv/fragment/validityManage/showIaiDetails?iaiIntoId=" +${iaiInto.id});
        })
    </script>
</head>
<body>
<%@ include file="/WEB-INF/layouts/advheader.jsp" %>
<div class="manage">
    <div class="container">
        <ul class="navigation">
            <ul class="navigation-sub">
                <li><a href="${ctx}/adv/validityManage" class="btn btn-default">库存管理</a></li>
                <li><a href="${ctx}/adv/validityManage/lossRecord" class="btn btn-default">损耗记录</a></li>
                <li class="active"><a href="${ctx}/adv/validityManage/entryRecord" class="btn btn-default">入货库记录</a>
                </li>
            </ul>
        </ul>
        <div class="validityManage saveValList" id="RK_HTML">
            <div class="val-list-h4"><h4 id="html_title">新增入库单</h4></div>
            <form id="saveIaiInto" method="post">
                <div class="clearfix" style="margin-top: 15px;">
                    <div class="pull-left" style="width: 50%; padding-right: 5%;">
                        <div class="form-group text-right">
                            <span>进货单单号：</span>
                            <input type="text" id="iaiIntoId" name="id" value="${iaiInto.id}" style="display: none"/>
                            <input type="text" name="goodsNo" class="form-control" value="${iaiInto.goodsNo}"
                                   style="display: inline-block; width: 75%;">
                            <i class="icon-val-order" title="进货单号" onclick="orderDetails()"></i>
                        </div>
                        <div class="form-group text-right">
                            <span>供应商：</span>
                            <select type="text" name="supplierId" class="form-control"
                                    style="display: inline-block; width: 75%;">
                                <c:if test="${not empty supplierList}">
                                    <c:forEach items="${supplierList}" var="supplier">
                                        <c:if test="${supplier.id==iaiInto.supplier.id}">
                                            <option value="${supplier.id}" selected="selected">${supplier.name}</option>
                                        </c:if>

                                        <c:if test="${supplier.id!=iaiInto.supplier.id}">
                                            <option value="${supplier.id}">${supplier.name}</option>
                                        </c:if>

                                    </c:forEach>
                                </c:if>
                            </select>
                            <i class="icon-val-per" title="编辑供应商" onclick="supplierManage(this)"></i>
                        </div>
                    </div>
                    <div class="pull-right" style="width: 50%; padding-right: 5%;">
                        <div class="form-group text-right">
                            <span>进货日期：</span>
                            <input type="text" name="createDate" class="form-control form_day"
                                   style="display: inline-block; width: 80%;"
                                   value="<fmt:formatDate value="${iaiInto.createDate}" type="date" pattern="yyyy/MM/dd"/>"
                                   readonly>
                        </div>
                        <div class="form-group text-right">
                            <span>填表人：</span>
                            <input name="whoCreate" value="${doctor.name}" type="text" class="form-control"
                                   style="display: inline-block; width: 80%;">
                        </div>
                    </div>
                </div>
            </form>
            <div class="val-list-medicine">
                <div class="clearfix">
                    <input type="text" id="medNameInput" class="form-control"
                           style="display: inline-block; width: 15%;"
                           placeholder="输入您要查询的药品名称">
                    <button class="btn btn-defalut" type="button" onclick="searchMed(${iaiInto.id})">搜索</button>
                    <a href="javascript:" onclick="addMedicine()" class="btn btn-success">新增药品</a>
                    <div class="list-medicine clearfix">
                        <a class="prev fa fa-chevron-left" onclick="scrollPageTag('left',${iaiInto.id})"></a>
                        <div class="list-tab" id="medListDiv">

                        </div>
                        <a class="next fa fa-chevron-right" onclick="scrollPageTag('right',${iaiInto.id})"></a>
                    </div>
                </div>
            </div>

            <div id="iaiDetail" style="background-color: #dde6ea;">


            </div>
        </div>
    </div>
</div>
<script>
    $(function () {
        $("#btnSaveIaiInto").click(function () {
            $("#saveIaiInto").submit();
        });


    })


</script>


</body>
</html>
