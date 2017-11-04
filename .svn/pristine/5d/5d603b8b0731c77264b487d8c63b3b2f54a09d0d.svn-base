<%--@elvariable id="doctor" type="com.qiaobei.hmp.modules.entity.Doctor"--%>
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
    <script src="${ctx}/assets/webSockets/sockjs-0.3.min.js"></script>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/advertising/scripts/advertisingIndex.js" type="js"/>
    <%@ include file="/WEB-INF/views/advertising/bombBox/webSocket.jsp" %>
    <script type="application/javascript">
        $(function () {
            //轮询 获取排队列表 和 用户设置信息
            utlit_getAdvingSize();
            loadWxPage(0);

//            callChang(47);
        })
    </script>
</head>
<body>
<%@ include file="/WEB-INF/layouts/advheader.jsp" %>
<div class="container advIndex">
    <div class="row div-box">
        <div class="col-xs-12 col-md-12">
            <div class="adv-title">
                <span class="line-up-span">排队中</span>
            </div>
        </div>
        <div class="col-xs-12 col-sm-3 col-md-2 index-btn" style="padding-top: 2em;">
            <button type="button" class="btn btn-success" onclick="/*fn_ClickRegistr()*/fn_ChangeDoctor()"><i
                    class="fa fa-credit-card icon-line01"></i>点击挂号
            </button>
            <button type="button" class="btn btn-success" style="letter-spacing: 10px;"><i class="icon-line02"></i>大健康
            </button>
            <button type="button" class="btn btn-success"><i class="icon-line03"></i>品质生活</button>
            <button type="button" class="btn btn-success"><i class="icon-line04"></i>地方商城</button>
        </div>
        <div class="col-xs-12 col-sm-9 col-md-10" style="padding: 0;">
            <div class="form-group line-up">
                <div style="height: 224px; width: 100%; position: relative; overflow: hidden;">
                    <div class="line-paid" id="advDiv">
                        <ul class="clear-fax">

                            <%--<li class="line-one">--%>
                            <%--<div class="line-up-number">B01</div>--%>
                            <%--<div>--%>
                            <%--<i></i>--%>
                            <%--<span>王沙沙</span>--%>
                            <%--</div>--%>
                            <%--</li>--%>
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
                <div style="margin-top: 20px;" id="wxPaiDuiList">

                </div>
            </div>
        </div>
    </div>

    <%--<div class="div-box">


    </div>--%>
</div>
<div style="display: none">
    <audio id="tmpDiv" src="" controls="controls" hidden="true">
    </audio>
</div>
<%@ include file="/WEB-INF/layouts/advfooter.jsp" %>

</body>
</html>
