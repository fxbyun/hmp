<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/7/14
  Time: 15:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>症狀</title>
    <script>
        $(function () {
            $("#entryPre").addClass("active");
        });
    </script>
</head>
<body>
<div>
    <!-- Nav tabs -->
    <div class="pre-detail-nav">
        <ul class="nav nav-tabs" role="tablist">
            <li class="active">
                <a href="${ctx}/learn/index" style="float:left;">病症</a>
                <i class="pre-bx"></i>
            </li>
            <li class="active">
                <a href="${ctx}/learn/symptom" style="float:left;">症状</a>
                <i class="pre-bx"></i>
            </li>
            <li>
                <a href="${ctx}/learn/namePrescription" style="float:left;">药方名称</a> <i class="pre-bx"></i>
            </li>
            <li>
                <a href="${ctx}/learn/chooseDrugs">选择药品</a>
            </li>
        </ul>
    </div>

    <div>
        <p style="font-size: 18px; text-align: center; line-height: 3rem;">患者主要表现是什么？</p>

        <div class="pre-disease" style="margin-top: 10px">
            <div class="label-box"></div>
            <div class="patient-label-box">
                <div class="row talk-tab">
                    <div class="tab-box-list">
                        <span class="tag">发热</span>
                        <span class="tag">咽痒</span>
                        <span class="tag">鼻塞</span>
                        <span class="tag">恶心</span>
                        <span class="tag">气短</span>
                        <span class="tag">腹泻 </span>
                        <span class="tag">胃部不舒</span>
                        <span class="tag">发烧</span>
                        <span class="tag">胸闷</span>
                        <span class="tag">牙痛</span>
                        <span class="tag">发热头痛</span>
                        <span class="tag">失眠</span>
                        <span class="tag">头痛</span>
                        <span class="tag">耳鸣</span>
                        <span class="tag">干咳</span>
                        <span class="tag">牙龈肿痛</span>
                    </div>
                    <div class="form-group text-center pre-btn pre-up-down" style="margin: 15px 0;">
                        <button type="button" class="btn btn-default" style="width: 48%;">上一页</button>
                        <button type="button" class="btn btn-success" style="width: 48%;">下一页</button>
                    </div>
                    <div class="form-group btn-boxs">
                        <input type="text" class="form-control seach-input" placeholder="首字母或名称搜索">
                        <button class="btn btn-default pull-right seach" type="button">搜索</button>
                    </div>
                    <div class="form-group btn-boxs">
                        <input type="text" class="form-control seach-input" placeholder="搜索不到?手动添加症状!如:'发热'">
                        <button class="btn btn-default pull-right seach" type="button">添加</button>
                    </div>
                </div>
                <%-- 按钮 --%>
                <div class="text-center pre-btn">
                    <a href="${ctx}/learn/index" class="btn btn-success">上一步</a>
                    <a href="${ctx}/learn/namePrescription" class="btn btn-success">下一步</a>
                    <a href="${ctx}/learn/namePrescription">跳过</a>
                </div>
                <%-- 按钮 END --%>
            </div>
        </div>

    </div>

</div>
</body>
</html>
