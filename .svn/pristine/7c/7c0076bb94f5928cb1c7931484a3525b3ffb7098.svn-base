<%--@elvariable id="retail" type="com.qiaobei.hmp.modules.entity.retail"--%>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/12/12
  Time: 14:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/advInclude.jsp" %>
<%@ taglib prefix="hmp" uri="/WEB-INF/TLD/HmpLoadStaticFile.tld" %>
<html>
<head>
    <title>费用明细</title>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/advertising/scripts/advertisingIndex.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/jquery.form/jquery.form.js" type="js"/>
</head>
<body style="background-color: #fff;">
<div style="padding: 20px;">
    <form id="priceForm" action="${ctx}/" method="post">
        <div class="row" style="line-height: 33px;">
            <div class="col-sm-5 col-xs-5">
                <span>应收金额：${totalPrice}</span>
            </div>
            <div class="col-sm-7 col-xs-7 clearfix">
                <span style="float:left;">实收金额：</span>
                <input name="realPrice" class="form-control" style="display: inline-block; width: 50%;" type="text"
                       value="${totalPrice}">
                <input name="retailId" class="form-control" style="display: none; width: 50%;" type="text"
                       value="${retail.id}">
            </div>
        </div>
        <div class="text-center" style="margin-top: 20px;">
            <button id="btnSure" type="button" class="btn btn-success">确认收费</button>
            <button id="btnClose" type="button" class="btn btn-default" style="width: 75px; margin-left: 10px;">取消
            </button>
        </div>
    </form>
</div>
<script type="application/javascript">
    $(function () {
        $("#btnSure").click(function () {
            $("#priceForm").ajaxSubmit({
                url: "${ctx}/retail/submitPrice",
                success: function (data) {
                    if (data.success == true) {
                        parent.location.href = '${ctx}/retail/RetailList';
                        layer.msg("收费成功！");
                    } else {
                        layer.msg("收费失败！");
                        window.location.href = '/retail/RetailBilling';
                        parent.layer.closeAll();
                    }
                }
            })
        })

    })
</script>


</body>
</html>
