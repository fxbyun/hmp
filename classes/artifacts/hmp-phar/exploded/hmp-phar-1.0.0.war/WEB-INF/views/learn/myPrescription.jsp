<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/7/11
  Time: 16:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的药方</title>
</head>
<body>
<div>
    <div style="margin-top: 1rem;">
        <div>
            <p class="text-center">
                <span>药方类别</span>
                <select class="form-control" style="width: 60%; display: inline-block;">
                    <option>全部</option>
                    <option>常用药方</option>
                </select>
            </p>
            <p class="text-center">
                <span>药方类型</span>
                <select class="form-control" style="width: 60%; display: inline-block;">
                    <option>全部</option>
                    <option>中草药</option>
                </select>
            </p>
            <p class="text-center">
                <span>药方名称</span>
                <input type="text" class="form-control" placeholder="默认全部" style="width: 60%; display: inline-block;">
            </p>

            <p class="text-center btn-boxs">
                <button type="button" class="btn btn-default">搜索</button>
            </p>
        </div>
        <div style="margin-top: 2rem;">
            <table class="tem-medicine">
                <colgroup style="width: 20%"></colgroup>
                <colgroup style="width: 30%"></colgroup>
                <colgroup style="width: 50%"></colgroup>
                <thead>
                <tr>
                    <th>序号</th>
                    <th>药方名称</th>
                    <th>药方详情</th>
                </tr>
                </thead>
                <tbody>
                <tr onclick="fn_detailShow()">
                    <td>1、</td>
                    <td>很牛的中药</td>
                    <td>
                        <div class="demo">很厉害的药方，能够包治百病。很厉害的药方，能够包治百病。很厉害的药方，能够包治百病。很厉害的药方，能够包治百病。</div>
                    </td>
                </tr>
                <tr onclick="fn_detailShow()">
                    <td>2、</td>
                    <td>很牛的中药</td>
                    <td>
                        <div class="demo">很厉害的药方。</div>
                    </td>
                </tr>
                <tr onclick="fn_detailShow()">
                    <td>3、</td>
                    <td>很牛的中药</td>
                    <td>
                        <div class="demo">很厉害的药方，能够包治百病。</div>
                    </td>
                </tr>
                <tr onclick="fn_detailShow()">
                    <td>4、</td>
                    <td>很牛的中药</td>
                    <td>
                        <div class="demo">很厉害的药方。</div>
                    </td>
                </tr>
                <tr onclick="fn_detailShow()">
                    <td>5、</td>
                    <td>很牛的中药</td>
                    <td>
                        <div class="demo">很厉害的药方，能够包治百病。</div>
                    </td>
                </tr>
                </tbody>
            </table>

            <%-- 按钮 --%>
            <div class="text-center pre-btn" style="margin-top: 2rem;">
                <button type="button" class="btn btn-success">上一页</button>
                <button type="button" class="btn btn-success">下一页</button>
                <a href="${ctx}/learn/index">首页</a>
            </div>
            <div class="text-center btn-boxs" style="margin-top: 2rem;">
                <span style="padding-right: 0.5rem;">第</span>
                <input type="text" class="form-control text-center" style="width: 15%; display: inline-block;"
                       value="3">
                <span style="padding:0 0.5rem;">页/共10页</span>
                <button type="button" class="btn btn-default">跳转</button>

            </div>
            <%-- 按钮 END --%>

        </div>
    </div>
</div>

<script>
    $(function () {
        //设置偶数行和奇数行
        $("tbody>tr:odd").addClass("backg");   //为奇数行设置样式(添加样式类)
        $("tbody>tr:even").addClass("backg2");  // 为偶数行设置样式类
    });
    function fn_detailShow() {
        window.location.href = "${ctx}/learn/detailPrescription"
    }
</script>
<script>
    $(function () {
        $("#myPre").addClass("active");
    });
</script>
</body>
</html>
