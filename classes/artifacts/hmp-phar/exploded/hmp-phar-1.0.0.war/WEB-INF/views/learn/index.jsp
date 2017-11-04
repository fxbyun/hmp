<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/7/8
  Time: 11:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>录入药方</title>
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
            <li>
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
    <!-- Tab panes -->
    <div>
        <%-- 病症 --%>
        <div>
            <p style="font-size: 18px; text-align: center; line-height: 3rem;">新药方是治疗啥病的？</p>

            <div class="pre-disease" style="margin-top: 10px">
                <div class="label-box"></div>
                <div class="patient-label-box">
                    <div class="row talk-tab">
                        <div class="tab-box-list" id="sumotom_tag_div">
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
                            <span class="tag">口臭</span>
                            <span class="tag">痰黄</span>
                            <span class="tag">流脓鼻涕</span>
                            <span class="tag">心慌气短</span>
                        </div>

                        <div class="form-group btn-boxs">
                            <input type="text" class="form-control seach-input" placeholder="首字母或名称搜索">
                            <button class="btn btn-default pull-right seach" type="button">搜索</button>
                        </div>
                        <div class="form-group btn-boxs">
                            <input type="text" class="form-control seach-input" placeholder="搜索不到?手动添加症状!如:'上呼吸道感染'">
                            <button class="btn btn-default pull-right seach" type="button">添加</button>
                        </div>
                    </div>
                    <%-- 按钮 --%>
                    <div class="text-center pre-btn">
                        <a class="btn btn-default">上一步</a>
                        <a href="${ctx}/learn/symptom" class="btn btn-success">下一步</a>
                        <a href="${ctx}/learn/symptom">跳过</a>
                    </div>
                    <%-- 按钮 END --%>
                </div>
            </div>

        </div>

        <%-- 症状 --%>
        <%--<div role="tabpanel" class="tab-pane" id="tabtwo">
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
                            <span class="tag">口臭</span>
                            <span class="tag">痰黄</span>
                            <span class="tag">流脓鼻涕</span>
                            <span class="tag">心慌气短</span>
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
                    &lt;%&ndash; 按钮 &ndash;%&gt;
                    <div class="text-center pre-btn">
                        <a href="#tabone" data-toggle="tab" class="btn btn-success">上一步</a>
                        <a href="#tabthree" data-toggle="tab" class="btn btn-success">下一步</a>
                        <a href="#tabthree" data-toggle="tab">跳过</a>
                    </div>
                    &lt;%&ndash; 按钮 END &ndash;%&gt;
                </div>
            </div>

        </div>--%>

        <%-- 药方名称 --%>
        <%--<div role="tabpanel" class="tab-pane" id="tabthree">
            <p style="font-size: 18px; text-align: center; line-height: 3rem;">给药方起个名字吧</p>
            <p class="pre-name-p"><span>药方名称：</span><input class="form-control" type="text" placeholder="跪求个名字"></p>

            <p style="font-size: 18px; text-align: center; line-height: 3rem; margin-top:5rem;">类别可以助您快速找到药方噢</p>

            <p class="pre-name-p">
                <span>药方名称：</span>
                <select class="form-control">
                    <option>常见病症</option>
                    <option>儿童病症</option>
                </select>

            </p>
            <div class="pre-cate">
                <a href="${ctx}/learn/categoryManage">新增/修改/删除</a>
            </div>
            &lt;%&ndash; 按钮 &ndash;%&gt;
            <div class="text-center pre-btn" style="margin: 10%;">
                <a href="#tabtwo" data-toggle="tab" class="btn btn-success">上一步</a>
                <a href="#tabfour" data-toggle="tab" class="btn btn-success">下一步</a>
                <a href="#tabfour" data-toggle="tab">跳过</a>
            </div>
            &lt;%&ndash; 按钮 END &ndash;%&gt;
        </div>--%>

        <%-- 选择药品 --%>
        <%--<div role="tabpanel" class="tab-pane" id="tabfour">
            <p style="font-size: 18px; text-align: center; line-height: 3rem;">您打算使用哪些药品来治疗呢？</p>
            <div class="pre-medicine">
                <ul class="nav nav-tabs" role="tablist">
                    <li class="active"><a href="#western" class="btn btn-default" data-toggle="tab">西药及中成药房</a></li>
                    <li><a href="#chinese" class="btn btn-default" data-toggle="tab">中草药房</a></li>
                </ul>
                <div class="tab-content">
                    &lt;%&ndash; 西药 &ndash;%&gt;
                    <div id="western" class="tab-pane active">
                        <div class="pre-disease" style="margin-top: 10px">
                            <div id="divSymptom" class="label-box"></div>
                            <div id="divSymptomTags" class="medicine-label-box">
                                <div class="tab-box-list west-boxs" id="western_span_tag_div">
                                    <span class="tag">安基比林1毫升庆大4万1支地米塞1毫克</span>
                                    <span class="tag">Berberine 0.1</span>
                                    <span class="tag">复方苦木消炎分散片</span>
                                    <span class="tag">葫芦娃克咳片</span>
                                    <span class="tag">痰咳净片</span>
                                    <span class="tag">橘红颗粒</span>
                                    <span class="tag">Azithromycin 0.25</span>
                                    <span class="tag">铋镁碳酸氢钠片</span>
                                    <span class="tag">右旋糖酐铁</span>
                                    <span class="tag">克咳敏</span>
                                </div>

                                <div class="form-group btn-boxs" style="padding: 0 10px;">
                                    <input type="text" class="form-control seach-input" placeholder="首字母或名称搜索"
                                           style="width: 55%;">
                                    <button class="btn btn-default pull-left seach" type="button">搜索</button>
                                    <a href="${ctx}/learn/addPrescription" class="btn btn-default pull-right"
                                       style="width: 24%;">新增药品</a>
                                </div>
                                &lt;%&ndash; 按钮 &ndash;%&gt;
                                <div class="text-center pre-btn">
                                    <a href="#tabthree" data-toggle="tab" class="btn btn-success">上一步</a>
                                    <button type="button" class="btn btn-success" style="width: 20%;">保存</button>
                                </div>
                                &lt;%&ndash; 按钮 END &ndash;%&gt;
                            </div>
                        </div>
                    </div>
                    &lt;%&ndash; 中药 &ndash;%&gt;
                    <div id="chinese" class="tab-pane">
                        <div class="pre-disease" style="margin-top: 10px">
                            <div class="label-box"></div>
                            <div class="medicine-label-box">
                                <div class="tab-box-list first-box-list" id="chmed_span_tag_div">
                                    <span class="tag">麻黄（去节）</span>
                                    <span class="tag">杏仁（去皮尖）</span>
                                    <span class="tag">膻中穴</span>
                                    <span class="tag">喘定针 2ml</span>
                                    <span class="tag">麻黄汤</span>
                                    <span class="tag">生石膏</span>
                                    <span class="tag">化痰散</span>
                                    <span class="tag">陈醋调和</span>
                                    <span class="tag">肺炎散</span>
                                    <span class="tag">吴茱萸</span>
                                    <span class="tag">柴胡针 2ml</span>
                                    <span class="tag">胃肠安贴</span>
                                    <span class="tag">穿心莲针 2ml</span>
                                    <span class="tag">生大黄</span>
                                    <span class="tag">天竺黄</span>
                                    <span class="tag">生诃子</span>
                                    <span class="tag">羌活</span>
                                    <span class="tag">紫苏子</span>
                                    <span class="tag">*三丫苦</span>
                                    <span class="tag">乌梅</span>
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
                                &lt;%&ndash; 按钮 &ndash;%&gt;
                                <div class="text-center pre-btn">
                                    <a href="#tabthree" data-toggle="tab" class="btn btn-success">上一步</a>
                                    <button type="button" class="btn btn-success" style="width: 20%;">保存</button>
                                </div>
                                &lt;%&ndash; 按钮 END &ndash;%&gt;
                            </div>
                        </div>
                    </div>

                </div>
            </div>


        </div>--%>

    </div>

</div>
</body>
</html>
