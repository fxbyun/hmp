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
</head>
<body>
<div class="container">
    <div class="row" style="padding:10px">
        <div class="col-md-12">
            <c:choose>
                <c:when test="${msg != null}">
                    <div class="text-center">
                        <h3 class="tcb">${msg}</h3>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="text-left tcb"><h4>请输入您要绑定的帐号</h4></div>
                    <div class="text-center">注：初始密码为帐号的最后6位</div>
                    <form class="form-horizontal" action="${ctx}/anon/bind" method="post">
                        <input type="hidden" name="openId" value="${OPENID}"/>
                        <table>
                            <tr style="height:40px">
                                <td align="right"><b>帐号：</b></td>
                                <td><input type="text" class="form-control" name="cardNo" id="cardNo" /></td>
                            </tr>
                            <tr style="height:40px">
                                <td align="right"><b>密码：</b></td>
                                <td><input type="password" class="form-control" name="pwd" id="pwd" /></td>
                            </tr>
                            <tr style="height:45px">
                                <td colspan="2"><div class="text-center"><button type="submit" class="btn btn-info">提交</button></div></td>
                            </tr>
                        </table>
                    </form>
                </c:otherwise>
            </c:choose>
        </div>
        <c:if test="${error != null}">
            <div class="alert alert-danger text-center">${error}</div>
        </c:if>
    </div>
</div>
<script src="${ctx}/assets/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>