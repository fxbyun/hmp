<%--@elvariable id="retail" type="com.qiaobei.hmp.modules.entity.Retail"--%>
<%--@elvariable id="retMed" type="com.qiaobei.hmp.modules.entity.RetailMedicine"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/staticInclude.jsp" %>
<%@ taglib prefix="hmp" uri="/WEB-INF/TLD/HmpLoadStaticFile.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>药品购买记录</title>
    <style>
        .form-inline.form-group .form-control {
            width: 150px;
            display: inline-block;
            vertical-align: middle;
        }

        .panel-title span {
            padding-right: 10px;
            line-height: 22px;
        }

        .panel-body {
            padding: 15px;
        }

        .panel-body .panel-title + p {
            margin-top: 10px;
        }

        .panel-body p {
            margin: 0 0 5px;
            line-height: 14px;
            padding-left: 15px;
        }

        .panel-body p span {
            padding-left: 20px;
        }
    </style>
    <script>
        $(function () {
            var index = parent.layer.getFrameIndex(window.name);
            $('#btnClose').click(function () {
                parent.layer.close(index);
            });
        });
    </script>
</head>
<body style="background-color: #fff; padding-top: 15px;">
<div class="container">
    <div class="row">
        <div class="col-xs-12 col-md-12">
            <form id="searchForm" action="${ctx}/retail/medicBuyDetails" method="post">
                <div class="form-inline form-group">
                    <label for="txtStartDate">查询日期</label>
                    <input name="patientId" style="display: none;" value="${patientId}"/>
                    <input type="text" class="form_day" name="startTime"
                           value="<fmt:formatDate value="${startTime}" pattern="yyyy/MM/dd"/>"
                           class="form-control form_date2" id="txtStartDate" readonly="">
                    至
                    <input type="text" class="form_day" name="endTime"
                           value="<fmt:formatDate value="${endTime}" pattern="yyyy/MM/dd"/>"
                           class="form-control form_date2" id="txtEndDate" readonly="">
                    <button class="btn btn-success pull-right" id="btn_search" type="button">搜索</button>
                </div>
            </form>
            <div class="form-group" style="height: 420px; overflow-y: auto;">
                <c:forEach items="${retailList}" var="retail" varStatus="status">
                    <div class="panel panel-success">
                        <div class="panel-heading">
                            <h3 class="panel-title"><span><fmt:formatDate value="${retail.chargeTime}"
                                                                          pattern="yyyy/MM/dd HH:mm"/></span><span>${retail.doctor.outpatientService}</span><span>${retail.pharmacist.name}</span>${TypeMap[retail.pharmacist.personType]}
                            </h3>
                        </div>
                        <div class="panel-body">

                            <strong class="panel-title">西药及中成药</strong>
                            <c:forEach items="${westernMap[retail.id]}" var="retMed" varStatus="status">
                                <p>${status.index+1}、${retMed.medicinePrivate.name}
                                    <span>${retMed.qtyUnitStr()}${medicineUnits[retMed.medicinePrivate.unit]}*${retMed.copies}</span>
                                </p>
                            </c:forEach>


                            <strong class="panel-title">中草药房</strong>
                            <c:forEach items="${chineseMap[retail.id]}" var="retMed" varStatus="status">
                                <p>${status.index+1}、${retMed.medicinePrivate.name}</p>
                            </c:forEach>
                        </div>
                    </div>
                </c:forEach>
                <c:if test="${empty retailList}">
                    <div class="panel panel-success">
                        <div class="panel-heading">
                            <h3 class="panel-title">该患者无购买药品记录！</h3>
                        </div>

                    </div>
                </c:if>
            </div>
            <div class="form-group text-center" style="margin-top: 15px;">
                <button id="btnClose" type="button" class="btn btn-default" style="width: 80px;">关闭</button>
            </div>

        </div>

    </div>
</div>
<script type="application/javascript">
    $(function () {

        $("#btn_search").click(function () {
            $("#searchForm").submit();
        });
    })
</script>
</body>
</html>
