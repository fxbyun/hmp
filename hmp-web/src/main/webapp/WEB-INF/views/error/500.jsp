<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.slf4j.Logger,org.slf4j.LoggerFactory" %>
<jsp:useBean id="beanTmp" class="com.qiaobei.hmp.support.WeixinUtil"/>
<%
    //设置返回码200，避免浏览器自带的错误页面
    response.setStatus(200);
    //记录日志
    Logger logger = LoggerFactory.getLogger("500.jsp");
    logger.error(exception.getMessage(), exception);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>500 - 系统内部错误</title>
</head>

<body>
<h2>
    <c:if test="${beanTmp.isLocal()}">
        <h3>本地开发错误信息</h3>
        <h4 style="color: red">
            错误标题:${exception}
        </h4>
        <h4>
            错误请求url：${pageContext.errorData.requestURI}
        </h4>
        <span>
            详情：<br>
            <c:forEach var="trace" items="${exception.stackTrace}">${trace.toString()}<br/></c:forEach>
        </span>
    </c:if>
    <c:if test="${!beanTmp.isLocal()}">500 - 系统发生内部错误.</c:if>
</h2>
<h2>
    <script type="application/javascript">
        var errorHappyUrl = "${pageContext.errorData.requestURI}";
        var errorTitle = '${exception}';
        var errorStack = "<c:forEach var="trace" items="${exception.stackTrace}">${trace.toString()}<br/></c:forEach>";
        try {
            parent.parent.parent.$.post("/common/log/java/error", {
                errorHappyUrl: errorHappyUrl,
                errorTitle: errorTitle,
                errorStack: errorStack
            }, function (ret) {
            });
        } catch (e) {
        }
    </script>
</h2>
</body>
</html>
