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

            $("#sb1,#sb2").click(function () {

                $("#tabone").attr("class", "tab-pane");
                $("#tabtwo").attr("class", "tab-pane active");
            });
            $(".pre-btn>a").click(function () {
                if ($(".tab-pane").is(".active")) {
                    var num = $(this).attr("href");
                    $(".pre-detail-nav>.nav>li").removeClass("active");

                    var num2 = $(".pre-detail-nav>.nav>li>a");
                    if (num == "#tabone") {
                        num = 1;
                    } else if (num == "#tabtwo") {
                        num = 2;
                    } else if (num == "#tabthree") {
                        num = 3;
                        $(".pre-detail-nav>.nav>li>a").eq(2).trigger("click");
                    } else if (num == "#tabfour") {
                        num = 4;
                    }
                    num2.eq(num - 1).parent().attr("class", "active");

                }

            })
        });
    </script>
    <style>
        .nav-tabs > li a:hover {
            background: transparent;
            border: 1px solid transparent;
            color: #B8B8B8;
        }
    </style>
    <script type="text/javascript" src="${ctx}/assets/mobileJs/index/index.js"></script>
</head>
<body>
<div id="indexDiv">
    <form action="${ctx}/learn/prescription/save" method="post">
        <!-- Nav tabs -->
        <div class="pre-detail-nav">
            <ul class="nav nav-tabs" role="tablist">
                <li class="active">
                    <a href="#tabone" style="float:left;" aria-controls="profile" data-toggle="tab">诊断</a>
                    <i class="pre-bx"></i>
                </li>
                <li>
                    <a id="needClick" href="#tabtwo" style="float:left;" aria-controls="profile"
                       data-toggle="tab">主诉</a>
                    <i class="pre-bx"></i>
                </li>
                <li>
                    <a href="#tabthree" style="float:left;" aria-controls="profile" data-toggle="tab">药方名称</a> <i
                        class="pre-bx"></i>
                </li>
                <li>
                    <a href="#tabfour" aria-controls="profile" data-toggle="tab" onclick="utlis_CheackNameIsInput()">选择药品</a>
                </li>
            </ul>
        </div>
        <!-- Tab panes -->
        <div class="tab-content">
            <%-- 诊断 --%>
            <div role="tabpanel" class="tab-pane active" id="tabone">
                <p style="font-size: 18px; text-align: center; line-height: 3rem;">新药方是治疗啥病的？</p>

                <div class="pre-disease" style="margin-top: 10px">
                    <div class="label-box" id="selectDiagnosisDiv"></div>
                    <div class="patient-label-box">
                        <div class="row talk-tab">
                            <div class="tab-box-list" id="diagbosis_tag_div">

                            </div>

                            <div class="form-group btn-boxs">
                                <input id="dianosisScanInput" type="text" class="form-control seach-input"
                                       placeholder="首字母或名称搜索" style="width: 70%;">
                                <button class="btn btn-default cleared" type="button">
                                    <i class="fa fa-times"></i>
                                </button>
                                <button class="btn btn-default pull-right seach" type="button"
                                        onclick="loadMobileDiagnosisTag()">搜索
                                </button>
                            </div>
                            <div class="form-group btn-boxs">
                                <input id="addDiagbosisInput" type="text" class="form-control seach-input"
                                       placeholder="搜索不到?手动添加症状!如:'上呼吸道感染'">
                                <button onclick="addMobileDiagnosisTag()" class="btn btn-default pull-right seach"
                                        type="button">添加
                                </button>
                            </div>
                        </div>
                        <%-- 按钮 --%>
                        <div class="text-center pre-btn">
                            <a class="btn btn-default">上一步</a>
                            <a id="sb1" href="#tabtwo" class="btn btn-success">下一步</a>
                            <a id="sb2" href="#tabtwo">跳过</a>
                        </div>
                        <%-- 按钮 END --%>
                    </div>
                </div>
            </div>
            <%-- 病症 --%>
            <div role="tabpanel" class="tab-pane" id="tabtwo">
                <p style="font-size: 18px; text-align: center; line-height: 3rem;">患者主要表现是什么?</p>

                <div class="pre-disease" style="margin-top: 10px">
                    <div class="label-box" id="selectSymptomsDiv"></div>
                    <div class="patient-label-box">
                        <div class="row talk-tab">
                            <div class="tab-box-list" id="sumotom_tag_div">

                            </div>

                            <div class="form-group btn-boxs">
                                <input id="symptomsScanInput" type="text" class="form-control seach-input"
                                       placeholder="首字母或名称搜索" style="width: 70%;">
                                <button class="btn btn-default cleared" type="button">
                                    <i class="fa fa-times"></i>
                                </button>
                                <button class="btn btn-default pull-right seach" type="button"
                                        onclick="loadMobileSymptoms()">搜索
                                </button>
                            </div>
                            <div class="form-group btn-boxs">
                                <input id="addSymptomsInput" type="text" class="form-control seach-input"
                                       placeholder="搜索不到?手动添加症状!如:'发热'">
                                <button class="btn btn-default pull-right seach" type="button"
                                        onclick="addMobileSymptomTag()">添加
                                </button>
                            </div>
                        </div>
                        <%-- 按钮 --%>
                        <div class="text-center pre-btn">
                            <a href="#tabone" data-toggle="tab" class="btn btn-success">上一步</a>
                            <a href="#tabthree" data-toggle="tab" class="btn btn-success">下一步</a>
                            <a href="#tabthree" data-toggle="tab">跳过</a>
                        </div>
                        <%-- 按钮 END --%>
                    </div>
                </div>
            </div>


            <%-- 药方名称 --%>
            <div role="tabpanel" class="tab-pane" id="tabthree">
            </div>

            <%-- 选择药品 --%>
            <div role="tabpanel" class="tab-pane" id="tabfour">
                <p style="font-size: 18px; text-align: center; line-height: 3rem;">您打算使用哪些药品来治疗呢？</p>
                <div class="pre-medicine">
                    <ul class="nav nav-tabs" role="tablist">
                        <li class="active"><a href="#western" class="btn btn-default" data-toggle="tab">西药及中成药房</a></li>
                        <li><a href="#chinese" class="btn btn-default" data-toggle="tab">中草药房</a></li>
                    </ul>
                    <div class="tab-content">
                        <%-- 西药 --%>
                        <div id="western" class="tab-pane active">
                            <div class="label-box" id="westernsSelTag"></div>
                            <div class="pre-disease" style="margin-top: 10px" id="westernsTag">
                            </div>
                        </div>
                        <%-- 中药 --%>
                        <div id="chinese" class="tab-pane">
                            <div class="label-box" id="chineseSelTag"></div>
                            <div class="pre-disease" style="margin-top: 10px" id="chineseTg">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
<div id="hiddDiv">
</div>

</body>

</html>
