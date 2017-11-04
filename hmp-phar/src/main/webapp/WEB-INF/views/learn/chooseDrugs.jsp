<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/7/14
  Time: 15:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>选择药品</title>
    <script>
        $(function () {
            $("#entryPre").addClass("active");
        });
        function fn_SelectSymptomTag() {
            window.location.href = "${ctx}/learn/editPrescription"
        }


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
            <li class="active">
                <a href="${ctx}/learn/namePrescription" style="float:left;">药方名称</a> <i class="pre-bx"></i>
            </li>
            <li class="active">
                <a href="${ctx}/learn/chooseDrugs">选择药品</a>
            </li>
        </ul>
    </div>

    <div>
        <p style="font-size: 18px; text-align: center; line-height: 3rem;">您打算使用哪些药品来治疗呢？</p>

        <div class="pre-medicine">
            <ul class="nav nav-tabs" role="tablist">
                <li class="active"><a href="#western" class="btn btn-default" data-toggle="tab">西药及中成药房</a></li>
                <li><a href="#chinese" class="btn btn-default" data-toggle="tab">中草药房</a></li>
            </ul>
            <div class="tab-content">
                <%-- 西药 --%>
                <div id="western" class="tab-pane active">
                    <div class="pre-disease" style="margin-top: 10px">
                        <div id="divSymptom" class="label-box"></div>
                        <div id="divSymptomTags" class="medicine-label-box">
                            <div class="tab-box-list west-boxs" id="western_span_tag_div">
                                <span class="tag" onclick="fn_SelectSymptomTag()">安基比林1毫升庆大4万1支地米塞1毫克</span>
                                <span class="tag" onclick="fn_SelectSymptomTag()">Berberine 0.1</span>
                                <span class="tag" onclick="fn_SelectSymptomTag()">复方苦木消炎分散片</span>
                                <span class="tag" onclick="fn_SelectSymptomTag()">葫芦娃克咳片</span>
                                <span class="tag" onclick="fn_SelectSymptomTag()">痰咳净片</span>
                                <span class="tag" onclick="fn_SelectSymptomTag()">橘红颗粒</span>
                                <span class="tag" onclick="fn_SelectSymptomTag()">Azithromycin 0.25</span>
                                <span class="tag" onclick="fn_SelectSymptomTag()">铋镁碳酸氢钠片</span>
                                <span class="tag" onclick="fn_SelectSymptomTag()">右旋糖酐铁</span>
                                <span class="tag" onclick="fn_SelectSymptomTag()">克咳敏</span>
                            </div>

                            <div class="form-group btn-boxs" style="padding: 0 10px;">
                                <input type="text" class="form-control seach-input" placeholder="首字母或名称搜索"
                                       style="width: 55%;">
                                <button class="btn btn-default pull-left seach" type="button">搜索</button>
                                <a href="${ctx}/learn/addPrescription" class="btn btn-default pull-right"
                                   style="width: 24%;">新增药品</a>
                            </div>
                            <%-- 按钮 --%>
                            <div class="text-center pre-btn">
                                <a href="${ctx}/learn/namePrescription" class="btn btn-success">上一步</a>
                                <button type="button" class="btn btn-success" style="width: 20%;">保存</button>
                            </div>
                            <%-- 按钮 END --%>
                        </div>
                    </div>
                </div>
                <%-- 中药 --%>
                <div id="chinese" class="tab-pane">
                    <div class="pre-disease" style="margin-top: 10px">
                        <div class="label-box"></div>
                        <div class="medicine-label-box">
                            <div class="tab-box-list first-box-list" id="chmed_span_tag_div">
                                <span class="tag" onclick="fn_SelectSymptomTag()">麻黄（去节）</span>
                                <span class="tag" onclick="fn_SelectSymptomTag()">杏仁（去皮尖）</span>
                                <span class="tag" onclick="fn_SelectSymptomTag()">膻中穴</span>
                                <span class="tag" onclick="fn_SelectSymptomTag()">喘定针 2ml</span>
                                <span class="tag" onclick="fn_SelectSymptomTag()">麻黄汤</span>
                                <span class="tag" onclick="fn_SelectSymptomTag()">生石膏</span>
                                <span class="tag" onclick="fn_SelectSymptomTag()">化痰散</span>
                                <span class="tag" onclick="fn_SelectSymptomTag()">陈醋调和</span>
                                <span class="tag" onclick="fn_SelectSymptomTag()">肺炎散</span>
                                <span class="tag" onclick="fn_SelectSymptomTag()">吴茱萸</span>
                                <span class="tag" onclick="fn_SelectSymptomTag()">柴胡针 2ml</span>
                                <span class="tag" onclick="fn_SelectSymptomTag()">胃肠安贴</span>
                                <span class="tag" onclick="fn_SelectSymptomTag()">穿心莲针 2ml</span>
                                <span class="tag" onclick="fn_SelectSymptomTag()">生大黄</span>
                                <span class="tag" onclick="fn_SelectSymptomTag()">天竺黄</span>
                                <span class="tag" onclick="fn_SelectSymptomTag()">生诃子</span>
                                <span class="tag" onclick="fn_SelectSymptomTag()">羌活</span>
                                <span class="tag" onclick="fn_SelectSymptomTag()">紫苏子</span>
                                <span class="tag" onclick="fn_SelectSymptomTag()">*三丫苦</span>
                                <span class="tag" onclick="fn_SelectSymptomTag()">乌梅</span>
                            </div>

                            <div class="form-group btn-boxs" style="padding: 0 10px;">
                                <input type="text" class="form-control seach-input" placeholder="首字母或名称搜索"
                                       style="width: 55%;">
                                <button class="btn btn-default pull-left seach" type="button">搜索</button>
                                <a href="${ctx}/learn/addPrescription" class="btn btn-default pull-right"
                                   style="width: 24%;">
                                    新增药品
                                </a>
                            </div>
                            <%-- 按钮 --%>
                            <div class="text-center pre-btn">
                                <a href="${ctx}/learn/namePrescription" class="btn btn-success">上一步</a>
                                <button type="button" class="btn btn-success" style="width: 20%;">保存</button>
                            </div>
                            <%-- 按钮 END --%>
                        </div>
                    </div>
                </div>

            </div>
        </div>


    </div>

</div>
</body>
</html>
