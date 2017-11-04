<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/6/4 0004
  Time: 11:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
</head>
<div style="padding: 5px 0 5px 20px;">
    <h3>患者主诉</h3>

    <p>无</p>
</div>
<div style="padding: 5px 0 5px 20px;">
    <h3>生命体征</h3>

    <p>无
    </p>
</div>
<div style="padding: 5px 0 5px 20px;">
    <h3>初步诊断</h3>

    <p>
        <c:if test="${empty emr.zhenDuan}">无</c:if>
        <c:if
                test="${not empty emr.zhenDuan}">${emr.zhenDuan}</c:if>
    </p>
</div>


<div style="padding: 5px 0 5px 20px; overflow: hidden;">
    <h3>处方详情</h3>
    <div class="content">
        <div>
            <div style="width: 80%; line-height: 30px;">
                ${ emr.chuFang}
            </div>
        </div>
    </div>

</div>

