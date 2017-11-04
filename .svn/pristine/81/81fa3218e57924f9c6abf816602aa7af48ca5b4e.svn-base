<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/12/20
  Time: 11:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/advInclude.jsp" %>
<%@ taglib prefix="hmp" uri="/WEB-INF/TLD/HmpLoadStaticFile.tld" %>
<!DOCTYPE html>
<html>
<head>
    <title>打印模板设置</title>
    <script>
        $(function () {
            $(".parentImg img:first-child").hover(function () {
                $(this).siblings("img").show();

            }, function () {
                $(this).siblings("img").hide();
            });
        });

    </script>
</head>
<body>
<%@ include file="/WEB-INF/layouts/advheader.jsp" %>
<div class="container inspectManage">
    <ul class="navigation">
        <li><a href="${ctx}/adv/personalInfo" class="btn btn-default">个人资料</a></li>
        <li><a href="${ctx}/adv/editPassword" class="btn btn-default">密码修改</a></li>
        <li class="active"><a href="${ctx}/adv/pub/printSet" class="btn btn-default">打印模板设置</a></li>
    </ul>
    <div class="adv-container" style="padding: 10px 20px;">
        <div class="row">
            <div class="col-xs-12">
                <div class="box-solid">
                    <h4>打印模板设置</h4>
                </div>
                <div style="font-size: 16px; padding-left: 10px;">
                    <p>选择您所需要的模板，点击确认修改后即生效。</p>
                    <div class="row" style="margin-top: 3%; text-align: center;">
                        <div class="col-xs-6 col-md-6 col-sm-6">
                            <div class="form-group">
                                <input id="p1" type="radio" name="print" value="" checked>
                                <label for="p1">广东省医疗机构门(急)诊、住院收费收据</label>
                            </div>
                            <div class="form-group">
                                <span class="parentImg">
                                    <img id="template2" src="/assets/advertising/images/big_temp2.png" width="200"
                                         height="78" alt="广东省医疗机构门(急)诊、住院收费收据">
                                    <img class="bigImg" src="/assets/advertising/images/big_temp2.png" width="350"
                                         height="180" alt="广东省医疗机构门(急)诊、住院收费收据">
                                </span>
                            </div>
                        </div>
                        <div class="col-xs-6 col-md-6 col-sm-6 text-left">
                            <div class="form-group">
                                <input id="p2" type="radio" name="print" value="">
                                <label for="p2">深圳市社会医疗收费清单</label>
                            </div>
                            <div class="form-group">
                                <span class="parentImg">
                                    <img id="template1" src="/assets/advertising/images/template1.png" width="200"
                                         height="78" alt="深圳市社会医疗收费清单">
                                    <img class="bigImg" src="/assets/advertising/images/big_temp1.png" width="350"
                                         height="180" alt="深圳市社会医疗收费清单">
                                </span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group text-center" style="margin-top: 40px;">
                    <button type="button" class="btn btn-success" onclick="subOk()"
                            style="width: 100px; margin-right: 10px;">确认修改
                    </button>
                </div>
            </div>
        </div>


    </div>
</div>
<%@ include file="/WEB-INF/layouts/advfooter.jsp" %>
</body>
</html>
