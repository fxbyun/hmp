<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <title>易佳诊健康管理平台</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <meta http-equiv="Cache-Control" content="no-store"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <meta http-equiv="Expires" content="0"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link href="${ctx}/assets/bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/assets/styles/style.css">
    <script src="${ctx}/assets/jquery/jquery-1.11.2.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        var useragent = navigator.userAgent;
        if (useragent.match(/MicroMessenger/i) != 'MicroMessenger') {
            // 这里警告框会阻塞当前页面继续加载
            alert('已禁止本次访问：您必须使用微信内置浏览器访问本页面！');
            // 以下代码是用javascript强行关闭当前页面
            var opened = window.open('about:blank', '_self');
            opened.opener = null;
            opened.close();
        }
    </script>
</head>
<body>
<div class="container">
    <div class="row" style="padding:10px">
        <div class="col-md-12">
            <div class="text-left tcb"><h4>登陆</h4></div>
            <form class="form-inline" id="loginForm" action="${ctx}/login" method="post">
                <table>
                    <tr style="height:40px">
                        <td align="right"><b>卡号：</b></td>
                        <td><input type="text" class="form-control" name="username" id="username" /></td>
                    </tr>
                    <tr style="height:40px">
                        <td align="right"><b>密码：</b></td>
                        <td><input type="password" class="form-control" name="password" id="password" /></td>
                    </tr>
                    <tr style="height:45px">
                        <td colspan="2"><div class="text-center"><button type="submit" class="btn btn-info">提交</button></div></td>
                    </tr>
                </table>
            </form>
        </div>
        <c:if test="${error != null}">
            <div class="alert alert-danger text-center">${error}</div>
        </c:if>
    </div>
</div>
<script src="${ctx}/assets/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>