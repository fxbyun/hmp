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
    <script type="application/javascript">
        $(function () {
            if ("${msg}" == "libAdd") {
                layer.msg("该模板药方已经成功增加为您的药方!")
            } else if ("${msg}" == "edit") {
                layer.msg("药方修改成功!")
            } else if ("${msg}" == "selfAdd") {
                layer.msg("药方信息已保存!")
            }

        })

    </script>
    <style>
        .tab-box-list .label-box span.tag {
            width: 100%;
            text-align: left;
            padding: 0;
            margin: 0;
            border-radius: 0;
            box-shadow: none;
        }
    </style>
</head>
<body>
<div style="margin: 1rem 0;" id="indexDiv">
    <form:form action="/learn/myDetailPrescription/save" cssClass="form-horizontal" method="post" modelAttribute="rp"
               id="frmRp">
        <form:hidden path="id" />
        <form:hidden path="medicineType" />
        <div style="margin-top: 1rem; padding: 1rem;">

            <p>
                <span>药方名称</span>
                    <%--<input type="text" class="form-control" style="width: 77%; display: inline-block;" value="很牛的药方">--%>
                <form:input path="name" cssClass="form-control required" id="txtName"
                            cssStyle="width: 77%; display: inline-block;" placeholder="请输入名称" />
            </p>

            <p>
                <span>药方类别</span>
                    <%--<select class="form-control" style="width: 77%; display: inline-block;">--%>
                    <%--<option>全部</option>--%>
                    <%--<option>常见病症</option>--%>
                    <%--</select>--%>
                <form:select path="categoryId" items="${categories}" itemValue="id" itemLabel="name"
                             cssClass="form-control"
                             id="sltCategory" cssStyle="width: 77%; display: inline-block;" />
            </p>
                <%--<div class="pre-cate">--%>
                <%--<a href="${ctx}/learn/categoryManage">修改</a>--%>
                <%--</div>--%>
            <p>药方详情</p>

            <div class="bor-box" style="min-height: 6rem;">
                <form:textarea path="remark" cssClass="form-control" id="txtspec" rows="3"></form:textarea>
            </div>

        </div>
        <div class="pre-medicine" style="padding: 0.2rem;">
            <ul class="nav nav-tabs" role="tablist">
                <li class="active"><a href="#western" class="btn btn-default" data-toggle="tab">西药及中成药房</a></li>
                <li><a href="#chinese" class="btn btn-default" data-toggle="tab">中草药房</a></li>
            </ul>
            <div class="tab-content" style="margin-top: 0.5rem;">
                <div id="western" class="tab-pane active">
                    <div class="label-box" id="hidderDiv">
                        <c:forEach var="item" items="${rp.prescriptionItemList}" varStatus="status">
                            <c:if test="${item.medicineType =='Western'}">
                            <span class="tag" ids="medicineTag_${item.medicineId}cih${status.count}"
                                  id="rpMedicineTag_${item.medicineId}">${item.medicineName}&nbsp;${item.qty}${medicineUnits[item.unit]}x${item.copies}份
                                                <input name="medicineIds" value="${item.medicineId}" type="hidden">
                                                <input name="companyIds" value="${item.companyId}" type="hidden">
                                                <input name="medicineQty" value="${item.qty}" type="hidden">
                                                <input name="medicineRate" value="${item.rate}" type="hidden">
                                                <input name="medicineUnit" value="${item.unit}" type="hidden">
                                                <input type="hidden" name="medicineCopies" value="${item.copies}" />
                                                <input type="hidden" name="medicineUseModes" value="${item.useMode}" />
                                                <input type="hidden" name="medicineHasUsages"
                                                       value="${item.hasUsage}" />
                                                <input type="hidden" name="medicineGroupId"
                                                       value="${item.groupIndex}" />
                                                 <a href="javascript:"
                                                    onclick="editMobileMed('${item.medicineId}',
                                                            '${item.groupIndex}',
                                                            '${item.qty}',
                                                            '${item.qty}',
                                                            '${item.useMode}',
                                                            'medicineTag_${item.medicineId}cih${status.count}')">
                                                     <i class="fa fa-cog"></i>
                                                 </a>

                                                 <a href="javascript:" onclick="$(this).parent().remove()">
                                                     <i class="fa fa-times"></i></a>
                                          </span>
                            </c:if>
                        </c:forEach>
                    </div>
                    <script type="application/javascript">
                        $(function () {
                            loadWesternsTag()
                        })
                    </script>
                    <div class="label-box" id="westernsSelTag"></div>
                    <div class="tab-box-list west-boxs" id="westernsTag">

                    </div>


                </div>

                <div id="chinese" class="tab-pane">
                    <div class="label-box" id="hidderDiv2">
                        <c:forEach var="item" items="${rp.prescriptionItemList}" varStatus="status">
                            <c:if test="${item.medicineType =='Chinese'}">
                            <span class="tag" ids="medicineTag_${item.medicineId}cih${status.count}"
                                  id="rpMedicineTag_${item.medicineId}">${item.medicineName}&nbsp;${item.qty}${medicineUnits[item.unit]}x${item.copies}份
                                                <input name="medicineIds" value="${item.medicineId}" type="hidden">
                                                <input name="companyIds" value="${item.companyId}" type="hidden">
                                                <input name="medicineQty" value="${item.qty}" type="hidden">
                                                <input name="medicineRate" value="${item.rate}" type="hidden">
                                                <input name="medicineUnit" value="${item.unit}" type="hidden">
                                                <input type="hidden" name="medicineCopies" value="${item.copies}" />
                                                <input type="hidden" name="medicineUseModes" value="${item.useMode}" />
                                                <input type="hidden" name="medicineHasUsages"
                                                       value="${item.hasUsage}" />
                                                <input type="hidden" name="medicineGroupId"
                                                       value="${item.groupIndex}" />
                                                 <a href="javascript:"
                                                    onclick="editMobileMed('${item.medicineId}',
                                                            '${item.groupIndex}',
                                                            '${item.qty}',
                                                            '${item.qty}',
                                                            '${item.useMode}',
                                                            'medicineTag_${item.medicineId}cih${status.count}')">
                                                     <i class="fa fa-cog"></i>
                                                 </a>

                                                 <a href="javascript:" onclick="$(this).parent().remove()">
                                                     <i class="fa fa-times"></i></a>
                                          </span>
                            </c:if>
                        </c:forEach>
                    </div>

                    <script type="application/javascript">
                        $(function () {
                            loadChinaMedTag()
                        })
                    </script>
                    <div class="label-box" id="chineseSelTag"></div>
                    <div class="tab-box-list west-boxs" id="chineseTg">

                    </div>

                </div>
            </div>

        </div>
    </form:form>

</div>
<div id="hiddDiv"></div>
</body>
</html>
