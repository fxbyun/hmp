<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/8/18
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>咨询窗口</title>
</head>
<body>
<div class="warp">
    <div class="out-detail text-center">
        <i class="fa fa-angle-left" onclick="javascript:history.go(-1)"></i>
        <span class="text-center">咨询窗口</span>
    </div>
    <div class="out-detail-title" style="overflow:hidden;">
        <img class="float-left" src="/assets/images/img4.png" width="52" height="58" alt="医生头像" style="margin: 1em 0;">
        <div class="float-left" style="padding-left: 1.2em; width: 80%;">
            <a href="#" style="line-height: 2em;">李医生</a>
            <p><span>门诊：华佗门诊</span></p>
            <p><span>擅长：上呼吸道感染</span></p>
            <p><span>月平均接诊数：1000人    平均分：9.0</span></p>
        </div>
    </div>
    <div class="out-detail-title con-consulting">
        <div><span>张三</span><span style="padding: 0 0.5em;">|</span><span>男</span><span
                style="padding: 0 0.5em;">|</span><span>40岁</span></div>
        <p><span>联系电话：13025121102</span></p>
        <p><span>生命体征：</span></p>
        <p><span>诊断结果：</span></p>
        <p><span>医嘱：</span></p>
        <p><span>诊后建议：</span></p>
    </div>
    <div class="out-detail-title out-appoint-evaluate" style="padding: 0 1em;"
         onclick="javascript:location.href='${ctx}/consultation/conEvaluate'">
        <div class="float-left">
            <p>评价</p>
            <span>101条评价</span>
        </div>
        <div class="float-right">
            <a href="#"><i class="fa fa-angle-right" style="font-size: 1.8em; line-height: 1.5em;"></i></a>
        </div>
    </div>
    <div class="con-reply">
        <div class="con-reply-show">
            <div class="float-left">
                <span class="color-5c">我</span><span>回复</span><span class="color-5c">李医生</span><span
                    style="padding: 0 0.2em;">:</span>
                <span>谢谢</span>

            </div>
            <div class="float-right"><span>2016/05/30 08:40</span></div>
        </div>
        <div style="margin-top: 5px;">
            <input type="text" class="form-control" placeholder="咨询/回复" style="display: inline-block;width: 80%;">
            <button type="button" class="btn btn-success">发送</button>
        </div>
    </div>


</div>
</body>
</html>
