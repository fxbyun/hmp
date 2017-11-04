<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <script src="${ctx}/assets/jquery/jquery-1.11.2.min.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/assets/styles/style.css">
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
    <div class="row">
        <div class="col-xs-12">
            <div class="text-left tcb"><h4>个人信息</h4></div>
            <div class="well well-sm emr">
                <table>
                    <tr style="height:30px">
                        <td align="right"><b>姓名：</b></td>
                        <td>${op.name}</td>
                    </tr>
                    <tr style="height:30px">
                        <td align="right"><b>性别：</b></td>
                        <td><c:if test="${op.gender == 'Male'}">男</c:if><c:if test="${op.gender == 'Female'}">女</c:if></td>
                    </tr>
                    <tr style="height:30px">
                        <td align="right"><b>出生年月：</b></td>
                        <td><fmt:formatDate value='${op.birthday}' pattern='yyyy/MM/dd'/></td>
                    </tr>
                    <tr style="height:30px">
                        <td align="right"><b>手机：</b></td>
                        <td>${op.mobile}</td>
                    </tr>
                    <tr style="height:30px">
                        <td align="right"><b>身份证：</b></td>
                        <td>${op.sfId}</td>
                    </tr>
                    <tr style="height:30px">
                        <td align="right"><b>住址：</b></td>
                        <td>${op.province}${op.city}${op.area}<c:out value='${op.address}'/></td>
                    </tr>
                    <tr style="height:30px">
                        <td align="right"><b>病史：</b></td>
                        <td>
                            <c:if test="${not empty op.patientTagList}">
                                <c:forEach items="${op.patientTagList}" var="tag">
                                    ${tag.name} &nbsp;
                                </c:forEach>
                            </c:if>
                        </td>
                    </tr>
                    <tr style="height:45px">
                        <td colspan="2"><div class="text-center">
                            <a href="${ctx}/edit/${op.id}" class="btn btn-primary btn-sm">修改</a> &nbsp;
                            <a href="${ctx}/unbind/${op.id}" class="btn btn-primary btn-sm">解除绑定</a>
                        </div></td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>
<script src="${ctx}/assets/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>
