<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/7/11
  Time: 16:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新增药品</title>
    <script>
        $(function () {
            $("#entryPre").addClass("active");
        });
    </script>
</head>
<body>
<div>
    <!-- Nav tabs -->
    <ul class="nav nav-tabs" role="tablist">
        <li class="active">
            <a href="${ctx}/learn/index" style="float:left;">病症</a>
            <i class="pre-bx"></i>
        </li>
        <li class="active">
            <a href="${ctx}/learn/symptom" style="float:left;">症状</a>
            <i class="pre-bx"></i>
        </li>
        <li class="active">
            <a href="${ctx}/learn/namePrescription" style="float:left;">药方名称</a> <i class="pre-bx"></i>
        </li>
        <li class="active">
            <a href="${ctx}/learn/chooseDrugs">选择药品</a>
        </li>
    </ul>

    <div style="padding: 1rem;">
        <h3 class="text-center">新建药品</h3>

        <p><span style="padding-right: 0.9rem;">药品名称</span>
            <input type="text" class="form-control" style="display: inline-block; width: 77%;"
                   placeholder="请输入药品名称">
        </p>

        <p>
            <span style="padding-right: 0.9rem;">药品分类</span>
            <select class="form-control" style="display: inline-block; width: 77%;">
                <option>呼吸道系统类</option>
            </select>
        </p>

        <p>
            <span style="padding-right: 0.9rem;">治疗方式</span>
            <select class="form-control" style="display: inline-block; width: 77%;">
                <option>口服</option>
                <option>滴注</option>
            </select>
        </p>
        <p><span>标准用量</span></p>

        <div style="border: 1px solid #ccc; padding: 1rem;">
            <p class="text-center">
                <input type="radio" name="be" style="width: 1.5rem;" checked>适用
                <input type="radio" name="be">不适用
            </p>

            <div style="overflow:hidden;">
                <div style="float:left; width: 35%;">
                    <select class="form-control" style="display: inline-block;">
                        <option>每日三次</option>
                    </select>
                </div>
                <div class="text-right" style="float:right; width: 60%;">
                    <span>每次</span>
                    <input class="form-control" type="number" style="display: inline-block; width: 25%;">
                    <select class="form-control" style="display: inline-block; width: 45%;">
                        <option>粒/片</option>
                    </select>
                </div>
            </div>
            <div style="width: 35%; margin-top: 1rem;">
                <select class="form-control" style="display: inline-block;">
                    <option>饭后服</option>
                </select>
            </div>


        </div>

        <p style="margin-top: 0.8rem;"><span>统计单位</span></p>

        <div>
            <div class="btn-group" data-toggle="buttons">
                <label class="btn btn-default active">
                    <input type="radio" name="unit" value="bxs" title="盒" checked="">盒</label>
                <label class="btn btn-default ">
                    <input type="radio" name="unit" value="btl" title="瓶">瓶</label>
                <label class="btn btn-default ">
                    <input type="radio" name="unit" value="pkg" title="包/排">包/排</label>
                <label class="btn btn-default ">
                    <input type="radio" name="unit" value="grs" title="粒/片">粒/片</label>
                <label class="btn btn-default ">
                    <input type="radio" name="unit" value="pcs" title="支">支</label>
                <label class="btn btn-default ">
                    <input type="radio" name="unit" value="g" title="g">g</label>
                <label class="btn btn-default ">
                    <input type="radio" name="unit" value="mg" title="mg">mg</label>
                <label class="btn btn-default ">
                    <input type="radio" name="unit" value="ml" title="ml">ml</label>
                <label class="btn btn-default ">
                    <input type="radio" name="unit" value="oth" title="单位">单位</label>
            </div>
        </div>

        <p style="margin-top: 1.2rem;">
            <span style="padding-right: 0.9rem;">药厂/产地</span>
            <select class="form-control" style="display: inline-block; width: 75%;">
                <option>很牛很牛的药厂</option>
            </select>
        </p>
        <p style="margin-top: 3rem;" class="text-center btn-bgcolor">
            <a type="button" class="btn btn-success">确定</a>
            <a type="button" class="btn btn-success" onclick="javascript: history.go(-1)">返回</a>
        </p>
    </div>

</div>
</body>
</html>
