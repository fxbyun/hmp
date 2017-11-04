<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/11
  Time: 16:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/advInclude.jsp" %>
<%@ taglib prefix="hmp" uri="/WEB-INF/TLD/HmpLoadStaticFile.tld" %>
<html>
<head>
    <title>图片轮播</title>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/advertising/scripts/advertisingIndex.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/advertising/scripts/advertisingIndex.js" type="js"/>
    <script type="text/javascript" src="${ctx}/assets/scripts/slider.js"></script>
    <script type="application/javascript">
        $(function () {
            var bannerSlider = new Slider($('#banner_tabs'), {
                time: 5000,
                delay: 400,
                event: 'hover',
                auto: false,
                mode: 'fade',
                controller: $('#bannerCtrl'),
                activeControllerCls: 'active'
            });
            $('#banner_tabs .flex-prev').click(function () {
                bannerSlider.prev()
            });
            $('#banner_tabs .flex-next').click(function () {
                bannerSlider.next()
            });

            $(document).on("mousewheel DOMMouseScroll", function (e) {

                var delta = (e.originalEvent.wheelDelta && (e.originalEvent.wheelDelta > 0 ? 1 : -1)) ||  // chrome & ie
                        (e.originalEvent.detail && (e.originalEvent.detail > 0 ? -1 : 1));              // firefox


                if (delta > 0) {
                    // 向上滚
//                console.log("wheelup");
                    bannerSlider.prev()
                } else if (delta < 0) {
                    // 向下滚
//                console.log("wheeldown");
                    bannerSlider.next()
                }
            });
        })


        document.body.addEventListener("DOMMouseScroll", function (event) {

            var direction = event.detail && (event.detail > 0 ? "mousedown" : "mouseup");
            console.log(direction);
        });

        // chrome and ie
        document.body.onmousewheel = function (event) {
            event = event || window.event;

            var direction = event.wheelDelta && (event.wheelDelta > 0 ? "mouseup" : "mousedown");
            console.log(direction);
        };


    </script>
</head>
<body style="background-color: #fff;">
<div style="padding: 10px;">
    <div id="banner_tabs" class="flexslider">
        <ul class="slides">
            <li><a href="#"><img src="${ctx}/assets/styles/images/img_01.png"/></a></li>
            <li><a href="#"><img src="${ctx}/assets/styles/images/img_02.png"/></a></li>
            <li><a href="#"><img src="${ctx}/assets/styles/images/img_03.png"/></a></li>
            <li><a href="#"><img src="${ctx}/assets/styles/images/img_04.png"/></a></li>
            <li><a href="#"><img src="${ctx}/assets/styles/images/img_01.png"/></a></li>
        </ul>
        <ul class="flex-direction-nav">
            <li><a class="flex-prev" href="javascript:;">Previous</a></li>
            <li><a class="flex-next" href="javascript:;">Next</a></li>
        </ul>
    </div>
</div>

</body>
</html>
