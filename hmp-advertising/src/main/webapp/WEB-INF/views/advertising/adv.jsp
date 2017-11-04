<%--@elvariable id="picAndTextCount" type="java.lang.long"--%>
<%--@elvariable id="doctor" type="com.qiaobei.hmp.modules.entity.Doctor"--%>
<%--@elvariable id="advertings" type="java.util.List"--%>
<%--
Created by IntelliJ IDEA 2016.2
汉化By http://www.java.sx (凉生 && Sky——Pang)
User 凉生
Date 16/8/18
Time 下午3:37
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<%@ include file="/WEB-INF/commons/staticInclude.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>广告终端</title>
    <link href="${ctx}/assets/styles/adv.css" rel="stylesheet">
    <script type="text/javascript" src="http://player.youku.com/jsapi"></script>
    <script src="${ctx}/assets/webSockets/sockjs-0.3.min.js"></script>
    <script type="application/javascript">
        var adcertPicAndTextSize = "${picAndTextCount}";
        setTimeout(function () {
            window.location.reload();
        }, (1000 * 60 * 4));
    </script>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/advertising/advertisingIndex.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/advertising/ykUtlis.js" type="js"/>
    <%@ include file="/WEB-INF/views/advertising/sub/webSocket.jsp" %>
</head>
<body>
<div class="adv-warp">
    <header>
        <div class="adv-time clear-fax">
            <div class="adv-fl-span text-center" style="min-width: 28%;">
                <span style="font-size: 5em; line-height: 1.2em;" id="timeSpan">获取中</span>

                <p style="font-size: 20px;">
                    <span id="dateSpan">获取中</span><span id="oldChinaDateId">获取中</span>
                </p>
            </div>
            <div class="adv-fl-span text-center" style="min-width: 54%; line-height: 8em;"
                 onclick="utlis_openWifiSetting()">
                <span style="font-size: 3em;">${doctor.outpatientService}</span>
            </div>
            <div class="adv-fr-span" style="min-width: 18%; margin-top: 2em;" id="topWdAndTqDiv"
                 onclick="utlis_exitApp()">
                <span style="font-size: 3em; line-height: 0;" id="wdSpan"></span>
                <img id="tqTpDiv" src="${ctx}/assets/images/233.png" width="77" height="68"

                     alt="天气图标"
                     style="vertical-align: bottom;background-size:77px 68px">
                <%--<div id="tqTpDiv" class="icon_3Cn7_4Z icon13_iMKW-Uw"></div>--%>
            </div>
        </div>
    </header>

    <div class="content">
        <%-- 挂号排队区 --%>
        <div class="adv-solid clear-fax">
            <div class="adv-solid02 adv-fl-span"><span class="line-up-span" style="font-size: 43px;">排队中</span></div>
            <div class="adv-fr-span" style="width: 80%;">
                <script>
                    $(function () {
                        <c:forEach items="${advertings}" var="oneAdv">

                        <c:if test="${not empty oneAdv.content}">
                        TOP_MESAGE_ARR.push('${oneAdv.content}');
                        </c:if>
                        </c:forEach>
                        loadTopRunText();
                    })
                </script>
                <marquee id="topMessg" scrollamount=8
                         style="color: #5cb531; filter: shadow(color=#FFFF33 ); font-size: 43px; width: 100%; padding: 20px 0; ">
                    无公告
                </marquee>
            </div>

        </div>
        <div class="line-up">
            <div style="height: 224px; width: 1020px; position: relative;">
                <%--<iframe width="100%" height="224" frameborder="0" scrolling="no" src="/advertising/slideU"></iframe>--%>
                <div class="line-paid" id="advDiv">
                    <ul class="clear-fax">
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
                <span>
                    <%--橘色显示为正在就诊，绿色显示为准备就诊。--%>
                    A类号为诊所现场挂号患者，B类号为微信预约挂号患者。</span>
            </div>
        </div>
        <%-- 挂号排队区 END --%>
        <div class="adv-content">
            <div class="video clear-fax">
                <div id="adv_banner" class="flexslider adv-fl-span" style="height: 560px; width: 800px;">
                    <ul class="slides">
                        <c:forEach items="${sysCenterImg}" var="oneImg">
                            <li>
                                <img src="${ctx}/fileDir/${doctor.id}/${oneImg.fileName}"
                                     alt="广告图" width="800"
                                     height="560">
                            </li>
                        </c:forEach>
                    </ul>
                    <ol id="bannerCtrl" class="flex-control-nav flex-control-paging">
                        <%--@elvariable id="sysCenterImg" type="java.util.List"--%>
                        <c:forEach items="${sysCenterImg}" var="oneImg" varStatus="status">
                            <li
                                    <c:if test="${status.count==1}">class="active"</c:if> >
                                <a>${status.count}</a>
                            </li>
                        </c:forEach>
                    </ol>

                </div>

                <div class="adv-fr-span" style="width: 250px;">
                    <button type="button" class="btn btn-success" onclick="fn_ChangeDoctor(${doctor.id})"
                            style="background-color: #ff7124;"><i class="fa fa-credit-card icon-line01"></i>点击挂号
                    </button>
                    <button type="button" class="btn btn-success" style="letter-spacing: 10px;" onclick="no_msg()"><i
                            class="icon-line02"></i>大健康
                    </button>
                    <button type="button" class="btn btn-success" onclick="no_msg()"><i class="icon-line03"></i>品质生活
                    </button>
                    <button type="button" class="btn btn-success" onclick="no_msg()"><i class="icon-line04"></i>地方商圈
                    </button>
                </div>
            </div>
            <div class="adv-msg" id="msgDiv"></div>

            <%-- 视频区--%>
            <%--@elvariable id="advertingsFooter" type="java.util.List"--%>
            <c:forEach items="${advertingsFooter}" varStatus="status" var="oneAdv">
                <script>
                    $(function () {
                        <c:if test="${oneAdv.type=='video'}">
                        //TODO  临时注释
                        <%--createVideo("${oneAdv.url}", "youkuplayer${status.count}");--%>
                        </c:if>
                        <c:if test="${status.count==1}">
                        videoSlider.setNowType("${oneAdv.type}");
                        </c:if>
                    })
                </script>
            </c:forEach>
            <%--http://player.youku.com/embed/XOTIxMDE4NDIw--%>
            <div id="adv_video" style="height: 607px; width: 100%; position: relative;">
                <ul class="slides">
                    <c:forEach items="${advertingsFooter}" varStatus="status" var="oneAdv">
                        <c:choose>
                            <%--<c:when test="${oneAdv.type=='video'}">--%>
                            <%--<div id="youkuplayer${status.count}" types="${oneAdv.position}"--%>
                            <%--style="width:1080px;height:607px">--%>
                            <%--</div>--%>
                            <%--</c:when>--%>
                            <c:when test="${oneAdv.type=='Pic'}">
                                <li>
                                    <img src="${ctx}/fileDir/${doctor.id}/${oneAdv.name}"
                                         alt="广告图" width="1080"
                                         height="607">
                                </li>
                            </c:when>


                        </c:choose>
                    </c:forEach>
                </ul>
                <ol id="videoCtrl" class="flex-control-nav flex-control-paging" style="left:450px;">
                    <c:forEach items="${advertingsFooter}" varStatus="status" var="oneAdv">

                        <c:choose>
                            <%--<c:when test="${oneAdv.type=='video'}">--%>
                            <%--<li--%>
                            <%--<c:if test="${status.count==1}">class="active"</c:if> types="${oneAdv.type}">--%>
                            <%--<a href="#">${status.count}</a>--%>
                            <%--</li>--%>
                            <%--</c:when>--%>

                            <c:when test="${oneAdv.type=='Pic'}">
                                <li
                                        <c:if test="${status.count==1}">class="active"</c:if>
                                        types="${oneAdv.type}">
                                    <a href="#">${status.count}</a>
                                </li>
                            </c:when>

                        </c:choose>
                    </c:forEach>
                </ol>
            </div>

            <%-- 视频区 END--%>


        </div>
    </div>
    <footer>
        <%-- 公司信息区--%>
        <div style="display: none">
            <%--<embed id="tmpDiv" src="" autostart="true" loop="true" width="200" height="200"></embed>--%>
            <audio id="tmpDiv" src="" controls="controls" hidden="true">
            </audio>
        </div>
        <div class="adv-info">
            <div class="adv-fl-span logo"><img src="${ctx}/assets/images/logo3.png" alt="易佳诊"></div>
            <div class="adv-fl-span text-center" style="width: 60%; margin-top: 4em; letter-spacing: 8px;"><span>深圳市易佳诊科技有限公司</span>
            </div>
            <div class="adv-fr-span text-center" style="width: 20%; margin-top: 3em; font-size: 22px;">
                <span>13500048003</span>

                <div><span>www.qpart.cc</span></div>
            </div>
        </div>
        <%-- 公司信息区 END--%>
    </footer>

</div>

</body>
</html>
<script>
    /**
     * 打开 选择医生窗口
     * 如果该诊所只有一个医生就直接打开 挂号刷卡窗口
     * */
    function fn_ChangeDoctor(doctorId) {
        doctortype = "${doctor.doctorType}"
        if(doctortype != "Clinic_Boss"){
//                            var index = parent.layer.getFrameIndex(window.name);
//         doctorId = ${doctor.id}
            fn_ClickRegistr("",doctorId);
        }else {
            layer.open({
                type: 2,
                title: '请选择所需医生',
                area: ['660px', '405px'],
                /*offset: '10%',*/
                scrollbar: false,
                content: ctx + '/advertising/changeDoctor'
            });
        }
    }
    /**
     * 点击 挂号按钮 打开 挂号刷卡窗口
     */
    function fn_ClickRegistr(msg, doctorId) {

        parent.layer.open({
            type: 2,
            title: ['排队挂号', 'font-size:35px; height:60px; padding:8px 10px; background-color:#529b47; color:#fff;'],
            area: ['800px', '550px'],
            offset: ['600px', '150px'],
            //offset: '10%',
            scrollbar: false,
            move: false,
            closeBtn: 0,
            content: ctx + '/advertising/registration?msg={0}&doctorId={1}'.format(msg, doctorId)
            //end:parent.layer.close(layerIndex)
        });


        setTimeout(function () {
            parent.layer.close()
        }, 500)
    }
</script>
