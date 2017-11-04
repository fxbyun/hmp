<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/7/13
  Time: 15:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<html>
<head>
    <title>药方详情</title>
    <style>
        .form-horizontal .radio-inline {
            padding-top: 0;
        }
    </style>
</head>
<body>
<div style="margin: 1rem 0;">
    <form:form action="/learn/detailPrescription/saveToMe" cssClass="form-horizontal" method="post" modelAttribute="rp">
        <div style="margin-top: 1rem; padding: 1rem;">

            <p>
                <span>药方名称</span>
                <form:input path="name" cssClass="form-control" cssStyle="width: 77%; display: inline-block;"
                            id="txtName" placeholder="请输入名称" />
            </p>

            <p>
                <span>药方类别</span>

                <form:select path="categoryId" items="${categories}" itemValue="id" itemLabel="name"
                             cssClass="form-control" cssStyle="width: 77%; display: inline-block;" id="sltCategory" />
            </p>
            <p>
                <span>药方分类</span>

                <form:bsradiobuttons path="medicineType" items="${medicineTypes}" readonly="true"
                                     cssStyle="width:20%; margin-top:0.4em;" labelCssClass="radio-inline"/>
            </p>
            <div class="text-center">
                <button type="submit" class="btn btn-success">
                    <i class="fa fa-save"></i> 保存至我的药方
                </button>
            </div>
            <p>药房详情</p>

            <div class="bor-box" style="border:1px solid #ccc; padding: 0.5rem; min-height: 6rem;">
                <span>
                        ${rp.remark}
                </span>
            </div>

        </div>
        <div class="pre-medicine" style="padding: 0.2rem;">
            <ul class="nav nav-tabs" role="tablist">
                <li class="active">
                    <a href="#western" class="btn btn-default" data-toggle="tab">用药清单
                    </a>
                </li>
                    <%--<li><a href="#chinese" class="btn btn-default" data-toggle="tab">中草药房</a></li>--%>
            </ul>
            <div class="tab-content" style="margin-top: 0.5rem;">
                <div id="western" class="tab-pane active">
                    <div class="label-box">
                        <span class="tag">阿莫西林 &nbsp;&nbsp;10粒/片</span>
                        <c:forEach var="item" items="${rp.prescriptionItemList}">
                        <span class="tag"
                              id="rpMedicineTag_${item.medicineId}">${item.medicineName}&nbsp;${item.qty}${medicineUnits[item.unit]}x${item.copies}份
                            <input name="medicineIds" value="${item.medicineId}" type="hidden">
                            <input name="companyIds" value="${item.companyId}" type="hidden">
                            <input name="medicineQty" value="${item.qty}" type="hidden">
                            <input name="medicineRate" value="${item.rate}" type="hidden">
                            <input name="medicineUnit" value="${item.unit}" type="hidden">
                            <input type="hidden" name="medicineCopies" value="${item.copies}" />
                            <input type="hidden" name="medicineUseModes" value="${item.useMode}" />
                            <input type="hidden" name="medicineHasUsages" value="${item.hasUsage}" />
                        </span>
                        </c:forEach>
                    </div>
                    <div class="tab-box-list west-boxs" id="western_span_tag_div">
                    </div>

                </div>

            </div>

        </div>
    </form:form>
</div>
</body>
</html>
