<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/8/19
  Time: 16:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>关于我们</title>
    <script>
        $(function () {
            $("#person").addClass("active");
        })
    </script>
</head>
<body>
<div class="warp">
    <img src="${ctx}/assets/images/img1.png" width="100%" height="20%" alt="广告图">
    <div class="per-about-us">
        <p>
            <span>深圳市易佳诊科技有限责任公司成立于2016年7月，是一家为基层诊疗服务机构提供科学适用信息服务软件的互联网公司。</span>
        </p>
        <p>
            <span>地址：深圳市南山区海德三道195号天利名城B座负一层意启众筹B20</span>
        </p>
        <p>
            <span>电话：0755-66642034</span>
        </p>
    </div>
    <button type="button" class="btn btn-success per-goback" style="width: 100%;" onclick="javascript:history.go(-1)">
        返回
    </button>
</div>

</body>
</html>
