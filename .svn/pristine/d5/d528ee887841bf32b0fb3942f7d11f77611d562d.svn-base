<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/8/11
  Time: 11:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/advInclude.jsp" %>
<%@ taglib prefix="hmp" uri="/WEB-INF/TLD/HmpLoadStaticFile.tld" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <style>
        span {
            padding-right: 10px;
            vertical-align: middle;
            font-size: 18px;
        }

        input.form-control {
            height: 40px;
            font-size: 18px;
        }

        .btn {
            width: 120px;
            font-size: 16px;
        }

        .btn-success {
            background-color: #529b47;
            margin-right: 10px;
        }
    </style>
    <script>
        $(function () {

            var index = parent.layer.getFrameIndex(window.name);
            $('#btnClose').click(function () {
                parent.layer.close(index);
            });
        });
    </script>
</head>
<body style="background-color: #fff;">
<div style="margin: 2em 2em; line-height: 2.8em;" class="text-center">
    <div class="form-group text-left" style="margin-bottom: 30px;">
        <span>&nbsp;&nbsp;&nbsp;刷&nbsp;卡&nbsp;&nbsp;&nbsp;</span>
        <input type="password" style="width: 80%; display: inline-block; float:right;" id="udid" class="form-control"
               placeholder="请刷卡" autocomplete="off">
    </div>
    <div class="form-group clearfix">
        <span>手动添加</span>
        <input type="text" id="mobile" class="form-control" style="float:right; width: 80%; "
               placeholder="请输入手机号">
    </div>
    <div class="form-group text-center" style="margin-top: 2em;">
        <button type="button" class="btn btn-success" id="btnSave">挂号</button>
        <button type="button" class="btn btn-default" id="btnClose">取消</button>
    </div>
</div>
</body>
</html>
