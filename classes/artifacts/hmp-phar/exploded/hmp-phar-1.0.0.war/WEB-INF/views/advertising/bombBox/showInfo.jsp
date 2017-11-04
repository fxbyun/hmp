<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/10/11
  Time: 16:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/advInclude.jsp" %>
<%@ taglib prefix="hmp" uri="/WEB-INF/TLD/HmpLoadStaticFile.tld" %>
<html>
<head>
    <title>确认费用信息</title>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/advertising/scripts/advertisingIndex.js" type="js"/>
</head>
<body style="background-color: #fff;">
<div style="margin: 20px;">
    <div style="overflow:hidden;">
        <div class="pull-left" style="margin-left: 50px;">
            <p>姓名：${emr.patientName}</p>
            <p>年龄：${emr.patient.getAge()}</p>
            <p>应收金额：${emr.cost}</p>
            <p>补收金额：${realCost}</p>
            <%--<p>欠款：0.00</p>--%>
        </div>
        <div class="pull-left" style="margin-left: 50px;">
            <p>性别：${genderMap[emr.patient.gender]}</p>
            <p>&nbsp;</p>
            <p>已收金额：${emr.realCost}</p>
            <p style="margin: 0;"><br></p>
            <%--<p>应收金额：<input type="text" class="form-control" style="display: inline-block; width: 80px;"></p>--%>
            <%--<p>找零：0.00</p>--%>
        </div>
    </div>
    <p class="text-center" style="margin-top: 20px;">
        <button class="btn btn-success" type="button" style="margin-right: 10px;"
                onclick="updateStatus(${emr.id},'CHARGE',${realCost})">确认
        </button>
        <button id="btnClose" class="btn btn-default" type="button">取消</button>
    </p>
</div>
<script>
</script>

</body>
</html>
