<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/7/11 0011
  Time: 17:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<html>
<head>
    <title>请扫描二维码进行支付</title>
    <script>
        layer.close(indexDiv);
        $(function () {
            var callBack = setInterval(function () {
                $.postJSON("${ctx}/wx/isPay", {payNo: "${payNo}"}, function (ret) {
//                    parent.Alert.success("恭喜您,充值成功!");
                    if (ret.success) {
                        clearInterval(callBack);
                        parent.isPay = true;
                        parent.layer.closeAll();
                    }
//                    parent.layer.close();
                });
            }, "5000");
        })

    </script>


</head>
<body>

<img src="${ctx}/pub/getWzewm?v=${payNo}" width="300" height="300">

</body>
</html>
