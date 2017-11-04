<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/6/27
  Time: 14:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<!DOCTYPE html>
<html lang="cn">
    <title>互动咨询</title>
</head>
<body>
     <div class="container electronic">
         <div class="row" style="margin-bottom: 20px;">
             <div class="col-lg-3" style="padding-right: 0;">
                <div class="pt-wx-people">
                    <p style="padding: 20px;">
                        <img src="/assets/styles/images/1.png" width="62" height="60">
                        <span style="font-size: 20px; padding-left: 10px;">张三医生</span>
                    </p>
                    <div class="pt-wx-search">
                        <i class="fa fa-search"></i>
                        <input type="text" placeholder="搜索（姓名或手机号）">
                    </div>
                    <button class="btn btn-success" style="width: 80%;">搜索</button>

                    <div class="pt-wx-nav">
                        <ul class="nav">
                            <li>
                                <span class="pt-wx-relation">10</span>
                                <h3>李四</h3>
                                <div>
                                    <dl>
                                        <dd>男 48岁 18802021213</dd>
                                        <dd>病史：高血压、糖尿病、低血糖、高血压、糖尿病、低血糖</dd>
                                    </dl>
                                </div>
                            </li>
                            <li>
                                <span class="pt-wx-relation">5</span>
                                <h3>李四四四</h3>
                                <div>
                                    <dl>
                                        <dd>男 48岁 18802021213</dd>
                                        <dd>病史：高血压、糖尿病</dd>
                                    </dl>
                                </div>
                            </li>
                            <li>
                                <h3>李里四</h3>
                                <div>
                                    <dl>
                                        <dd>男 48岁 18802021213</dd>
                                        <dd>病史：高血压、糖尿病、低血糖高血压、糖尿病、低血糖</dd>
                                    </dl>
                                </div>
                            </li>
                            <li>
                                <h3>李四</h3>

                                <div>
                                    <dl>
                                        <dd>男 48岁 18802021213</dd>
                                        <dd>病史：高血压、糖尿病、低血糖、高血压、糖尿病、低血糖</dd>
                                    </dl>
                                </div>
                            </li>

                        </ul>
                    </div>
                </div>
             </div>
             <div class="col-lg-9" style="padding-left: 0;">
                 <div class="pt-wx-talk tabbable">
                     <div class="form-group pt-wx-sear" style="overflow: hidden;">
                         <label for="txtStartDate" style="margin-left: 20px;">就诊日期</label>
                         <input type="text" name="startDate"
                                value="<fmt:formatDate value="${startDate}" pattern="yyyy/MM/dd" />"
                                class="form-control form_date"
                                id="txtStartDate" style="width:120px;">
                         <span>至</span>
                         <input type="text" name="endDate" style="width:120px;"
                                value="<fmt:formatDate value="${endDate}" pattern="yyyy/MM/dd" />"
                                class="form-control form_date" id="txtEndDate">
                         <button class="btn btn-default" style="width: 80px;">搜索</button>
                         <span style="float:right; font-size: 20px; padding-right: 30px;">李四四</span>
                     </div>
                     <ul class="nav nav-tabs" role="tablist">
                         <li class="active"><a href="#" data-toggle="tab">2016/06/20 15:30</a></li>
                         <li><a href="#" data-toggle="tab">2016/06/20 15:30</a></li>
                         <li><a href="#" data-toggle="tab">2016/06/20 15:30</a></li>
                         <li><a href="#" data-toggle="tab">2016/06/20 15:30</a></li>
                         <li><a href="#" data-toggle="tab">2016/06/20 15:30</a></li>
                         <li><a href="#" data-toggle="tab">2016/06/20 15:30</a></li>
                     </ul>
                     <div class="tab-content" style="margin: 20px 40px;">
                         <p>患者主诉：头痛、发热、恶心</p>
                         <p>生命体征：血压120/130mmHg</p>
                         <p>初步诊断：上呼吸道感染</p>
                         <p class="text-center">
                             <a href="#medicine" data-toggle="collapse"><span class="fa fa-angle-double-down" style="margin-right: 5px;"></span>显示全部</a>
                         </p>
                         <div id="medicine" class="collapse" aria-labelledby="headingone">
                             <h4>处方-西药</h4>
                             <p>1、杏仁止咳糖浆</p>
                             <h4>处方-中药</h4>
                             <p>1、麻黄汤</p>
                         </div>
                         <div class="pt-wx-pttalk" data-toggle="collapse">

                         </div>
                         <div class="pt-wx-re-talk">

                         </div>

                     </div>


                 </div>
             </div>
         </div>
     </div>





</body>
</html>
