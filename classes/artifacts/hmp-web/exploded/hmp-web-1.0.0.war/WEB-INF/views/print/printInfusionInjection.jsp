<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/12/15
  Time: 16:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/staticInclude.jsp" %>
<%@ taglib prefix="hmp" uri="/WEB-INF/TLD/HmpLoadStaticFile.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>注射滴注模板</title>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/styles/print.css" type="css"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/print.js" type="js"/>
</head>
<body style="font-family: '宋体'; font-size: 16px;">
<div class="print-mg infu-inject">
    <div class="print-title text-center" style="margin-bottom: 15px;"><strong>注射/滴注单</strong></div>
    <p class="print-p"><span class="pull-left">机构名称：<span>华佗门诊</span></span></p>
    <div>
        <table width="100%" border="1">
            <colgroup width="15%"></colgroup>
            <colgroup width="20%"></colgroup>
            <colgroup width="12%"></colgroup>
            <colgroup width="12%"></colgroup>
            <colgroup width="13%"></colgroup>
            <colgroup width="18%"></colgroup>
            <tr>
                <td>姓名</td>
                <td>张三三</td>
                <td>性别</td>
                <td>男</td>
                <td>年龄</td>
                <td>26岁</td>
            </tr>
            <tr>
                <td>电话</td>
                <td colspan="5">13588551212</td>
            </tr>
            <tr>
                <td>出诊结果</td>
                <td colspan="5">呼吸道疾病</td>
            </tr>
            <tr style="height: 390px;">
                <td rowspan="3">药名、用法用量</td>
                <td colspan="5" rowspan="3" style="vertical-align: top;">
                    <div class="text-left" style="padding: 10px;">
                        <div class="content1 clearfix">
                            <div class="pull-left" style="width: 70%;">
                                <div style="padding: 5px 0;"><strong>注射[分组二]</strong></div>
                                <div style="padding-left: 10px;">
                                    <b>1、葡萄糖</b>&nbsp;<span>5支</span>
                                    <p><span>每日2次</span>&nbsp;<span>每次2支</span><span>饭后服</span></p>
                                </div>
                                <div style="padding-left: 10px;">
                                    <span style="font-weight: bold;">2、葡萄糖</span>&nbsp;<span>5支</span>
                                    <p><span>每日2次</span>&nbsp;<span>每次2支</span><span>饭后服</span></p>
                                </div>
                            </div>
                            <div class="text-left pull-right" style="width: 30%;">护士确认：</div>
                        </div>
                        <div class="content1 clearfix">
                            <div class="pull-left" style="width: 70%;">
                                <div style="padding: 5px 0;"><strong>注射[分组二]</strong></div>
                                <div style="padding-left: 10px;">
                                    <strong style="font-weight: bold !important;">1、葡萄糖</strong>&nbsp;<span>5支</span>
                                    <p><span>每日2次</span>&nbsp;<span>每次2支</span><span>饭后服</span></p>
                                </div>
                                <div style="padding-left: 10px;">
                                    <strong>2、葡萄糖</strong>&nbsp;<span>5支</span>
                                    <p><span>每日2次</span>&nbsp;<span>每次2支</span><span>饭后服</span></p>
                                </div>
                            </div>
                            <div class="text-left pull-right" style="width: 30%;">护士确认：</div>
                        </div>

                    </div>
                </td>

            </tr>
            <%--<tr>
                <td colspan="3"><div class="text-left"  style="min-height: 150px; padding: 0 10px;">
                    <div class="content1">
                        <h4>注射[分组二]</h4>
                        <div style="padding-left: 10px;">
                            <strong>葡萄糖</strong>&nbsp;&nbsp;<span>5支</span>
                            <p style="font-size: 14px; margin: 0 0 5px;"><span>每日2次</span>&nbsp;&nbsp;<span>每次2支</span><span>饭后服</span></p>
                        </div>
                        <div style="padding-left: 10px;">
                            <strong>葡萄糖</strong>&nbsp;&nbsp;<span>5支</span>
                            <p style="font-size: 14px; margin: 0 0 5px;"><span>每日2次</span>&nbsp;&nbsp;<span>每次2支</span><span>饭后服</span></p>
                        </div>
                    </div>
                </div></td>
                <td colspan="2"><div class="text-left" style="min-height: 150px; padding: 5px 10px;">护士确认：</div></td>
            </tr>
            <tr>
                <td colspan="3"><div class="text-left"  style="min-height: 150px; padding: 0 10px;">
                    <div class="content1">
                        <h4>注射[分组二]</h4>
                        <div style="padding-left: 10px;">
                            <strong>葡萄糖</strong>&nbsp;&nbsp;<span>5支</span>
                            <p style="font-size: 14px; margin: 0 0 5px;"><span>每日2次</span>&nbsp;&nbsp;<span>每次2支</span><span>饭后服</span></p>
                        </div>
                        <div style="padding-left: 10px;">
                            <strong>葡萄糖</strong>&nbsp;&nbsp;<span>5支</span>
                            <p style="font-size: 14px; margin: 0 0 5px;"><span>每日2次</span>&nbsp;&nbsp;<span>每次2支</span><span>饭后服</span></p>
                        </div>
                    </div>
                </div></td>
                <td colspan="2"><div class="text-left" style="min-height: 150px; padding: 5px 10px;">护士确认：</div></td>
            </tr>--%>
            <tr></tr>
            <tr></tr>
            <tr>
                <td>处方医生</td>
                <td>杨永信</td>
                <td colspan="2">就诊时间</td>
                <td colspan="2">2016/12/12 12:30</td>
            </tr>
        </table>
    </div>
</div>
</body>
</html>
