<%--
  Created by IntelliJ IDEA.
  User: teemoer@cntv.cn
  Date: 2016/7/5 0005
  Time: 14:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/loadRe.jsp" %>
<html>
<head>
    <title>下载LODOP组件</title>
    <style>
        a {
            color: #000;
            width: 100%;
            height: 15%;
            line-height: 3em;
            display: block;
        }
    </style>

    <script type="application/javascript">
        parent.$(".layui-layer-shade").click(function () {
            $(this).remove()
        })

    </script>
</head>
<body style="background-color: #00dd1c; text-align: center;">
<h2>
    <a href="/assets/print.lodop/install_lodop32.exe">下载安装打印插件(1)</a>
    <a href="${url}">下载安装打印插件(2)</a>
    <br>

</h2>

<h2>
    <span style="color: white">安装完毕请重启浏览器<br>插件1无效请下载插件2</span>
</h2>
</body>
</html>
