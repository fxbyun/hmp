<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/10/10
  Time: 10:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/advInclude.jsp" %>
<%@ taglib prefix="hmp" uri="/WEB-INF/TLD/HmpLoadStaticFile.tld" %>
<html>
<head>
    <title>门诊挂号</title>
    <script>
        function fn_ClickRegistr() {
            layer.open({
                type: 2,
                title: '排队挂号',
                area: ['500px', '300px'],
                scrollbar: false,
                content: '/adv/registration'
            });
        }
    </script>
</head>
<body>
    <header>
        <nav class="ele-navbar">
            <div class="container">
                <div class="pull-left"><span class="outpatient-name">华佗个体门诊</span></div>
                <div class="pull-right"><span class="per-name">张三</span><a href="#">退出</a></div>

            </div>
        </nav>
    </header>
    <div class="container advIndex">
        <div class="adv-title">
            <span class="line-up-span">排队中</span>
        </div>
        <div class="div-box">
            <div class="col-md-2 col-sm-2" style="margin-left: -10px;">
                <button type="button" class="btn btn-success" onclick="fn_ClickRegistr()"><i
                        class="fa fa-credit-card icon-line01"></i>点击挂号
                </button>
                <button type="button" class="btn btn-success" style="letter-spacing: 10px;"><i class="icon-line02"></i>大健康
                </button>
                <button type="button" class="btn btn-success"><i class="icon-line03"></i>品质生活</button>
                <button type="button" class="btn btn-success"><i class="icon-line04"></i>地方商城</button>
            </div>
            <div class="col-md-10 col-sm-10" style="padding: 0;">
                <div class="line-up">
                    <div style="height: 224px; width: 100%; position: relative;">
                        <div class="line-paid" id="advDiv">
                            <ul class="clear-fax">
                                <li class="line-two">
                                    <div class="line-up-number">A01</div>
                                    <div>
                                        <i></i>
                                        <span>张三三</span>
                                    </div>
                                </li>
                                <li class="line-two">
                                    <div class="line-up-number">A02</div>
                                    <div>
                                        <i></i>
                                        <span>李思思</span>
                                    </div>
                                </li>
                                <li class="line-one">
                                    <div class="line-up-number">B01</div>
                                    <div>
                                        <i></i>
                                        <span>王沙沙</span>
                                    </div>
                                </li>
                                <li class="line-three">
                                    <div class="line-up-number" style="background-color: #fff;"></div>
                                    <div>
                                        <i></i>
                                        <span></span>
                                    </div>
                                </li>
                                <li class="line-three">
                                    <div class="line-up-number" style="background-color: #fff;"></div>
                                    <div>
                                        <i></i>
                                        <span></span>
                                    </div>
                                </li>
                                <li class="line-three">
                                    <div class="line-up-number" style="background-color: #fff;"></div>
                                    <div>
                                        <i></i>
                                        <span></span>
                                    </div>
                                </li>
                                <li class="line-three">
                                    <div class="line-up-number" style="background-color: #fff;"></div>
                                    <div>
                                        <i></i>
                                        <span></span>
                                    </div>
                                </li>
                                <li class="line-three">
                                    <div class="line-up-number" style="background-color: #fff;"></div>
                                    <div>
                                        <i></i>
                                        <span></span>
                                    </div>
                                </li>
                                <li class="line-three">
                                    <div class="line-up-number" style="background-color: #fff;"></div>
                                    <div>
                                        <i></i>
                                        <span></span>
                                    </div>
                                </li>
                                <li class="line-three">
                                    <div class="line-up-number" style="background-color: #fff;"></div>
                                    <div>
                                        <i></i>
                                        <span></span>
                                    </div>
                                </li>
                                <li class="line-three">
                                    <div class="line-up-number" style="background-color: #fff;"></div>
                                    <div>
                                        <i></i>
                                        <span></span>
                                    </div>
                                </li>
                                <li class="line-three">
                                    <div class="line-up-number" style="background-color: #fff;"></div>
                                    <div>
                                        <i></i>
                                        <span></span>
                                    </div>
                                </li>
                            </ul>

                        </div>
                    </div>
                    <div class="line-op"></div>
                    <div class="line-prompt">
                        <span>温馨提示：</span>
                        <span>A类号为诊所现场挂号患者，B类号为微信预约挂号患者，橙色为预约挂号未签到患者</span>
                    </div>
                    <div style="margin-top: 20px;">
                        <p style="font-size: 1.3em;">预约挂号状态：</p>
                        <div class="outpatient-tab">
                            <button type="button" class="btn btn-success"><span>张三</span></br>9:00-9:15</button>
                            <button type="button" class="btn btn-success"><span>张三</span></br>9:00-9:15</button>
                            <button type="button" class="btn btn-success"><span>张三</span></br>9:00-9:15</button>
                            <button type="button" class="btn btn-success"><span>张三</span></br>9:00-9:15</button>
                            <button type="button" class="btn btn-success"><span>张三</span></br>9:00-9:15</button>
                        </div>
                        <p class="text-right" style="margin-top: 10px;">
                            <button type="button" class="btn btn-success">上一页</button>
                            <button type="button" class="btn btn-success">下一页</button>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <%-- 此处可能不需要 待定 别删 --%>
    <%--<footer>
        <div style="display: none">
            &lt;%&ndash;<embed id="tmpDiv" src="" autostart="true" loop="true" width="200" height="200"></embed>&ndash;%&gt;
            <audio id="tmpDiv" src="" controls="controls" hidden="true">
            </audio>
        </div>
        <div class="adv-info">
            <div class="pull-left logo"><img src="${ctx}/assets/advertising/images/logo3.png" alt="易佳诊"></div>
            <div class="pull-left text-center" style="width: 60%; margin-top: 4em; letter-spacing: 8px;"><span>深圳市易佳诊科技有限公司</span>
            </div>
            <div class="pull-right text-center" style="width: 20%; margin-top: 3em; font-size: 22px;"><span>0755 - 66642034</span>

                <div><span>www.qpart.cc</span></div>
            </div>
        </div>
    </footer>--%>
    <footer class="footer">
        ©2015 深圳市乔北科技有限责任公司版权所有 <span><a href="http://www.miitbeian.gov.cn/publish/query/indexFirst.action"
                                         target="_blank">粤ICP备15089657号-2</a></span> <span>电话：0755-66642034</span>
    </footer>


</body>
</html>
