<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>预约首页</title>
    <script>
        $(function () {
            //设置偶数行和奇数行
            $(".index-info-ul>li:odd").addClass("backg4");   //为奇数行设置样式(添加样式类)
            $(".index-info-ul>li:even").addClass("backg3");  // 为偶数行设置样式类

            $("#reservation").addClass("active");
        });

    </script>
</head>
<body>
<div class="warp">
    <img src="/assets/images/img1.jpg" width="100%" height="124" alt="广告图">
    <div class="out-detail-title wx-index">
        <div class="float-left" style="overflow:hidden; padding: 0.8em 0.2em 0 1em;">
            <div class="float-left">
                <i class="fa fa-clock-o"></i>
            </div>
            <div class="float-left text-center" style="line-height: 2.3em;">
                <a href="${ctx}/outpatient/clinicIndex" style="font-size: 1.8rem;">预约挂号</a>
                <p><span>轻轻松松&nbsp;&nbsp;随时随地</span></p>
            </div>

        </div>
        <div class="float-right">
            <a href="#" style="text-align: center;">
                <i class="fa fa-comments-o"></i>
                <p style="font-size: 1.5rem;">咨询医生</p>
            </a>
        </div>
    </div>
    <div>
        <div class="index-infomation">
            <div class="wx-icon-solid float-left"></div>
            <div class="float-left"><span>健康资讯</span></div>
        </div>
        <ul class="index-info-ul">
            <li>
                <a href="#"><img class="float-left" src="/assets/images/img5.png" width="70" height="48"
                                 alt="健康资讯图片"></a>
                <div class="float-left">
                    <a href="#">春季养生</a>
                    <p><i class="fa fa-align-left"></i><span>健康咨询 养生类</span></p>
                </div>
                <a href="#" class="index-info-btn"><i class="fa fa-file-text-o"></i></a>
            </li>
            <li>
                <a href="#"><img class="float-left" src="/assets/images/img5.png" width="70" height="48"
                                 alt="健康资讯图片"></a>
                <div class="float-left">
                    <a href="#">夏季养生</a>
                    <p><i class="fa fa-align-left"></i><span>健康咨询 养生类</span></p>
                </div>
                <a href="#" class="index-info-btn"><i class="fa fa-file-text-o"></i></a>
            </li>
            <li>
                <a href="#"><img class="float-left" src="/assets/images/img5.png" width="70" height="48"
                                 alt="健康资讯图片"></a>
                <div class="float-left">
                    <a href="#">秋季养生</a>
                    <p><i class="fa fa-align-left"></i><span>健康咨询 养生类</span></p>
                </div>
                <a href="#" class="index-info-btn"><i class="fa fa-file-text-o"></i></a>
            </li>
            <li>
                <a href="#"><img class="float-left" src="/assets/images/img5.png" width="70" height="48"
                                 alt="健康资讯图片"></a>
                <div class="float-left">
                    <a href="#">冬季养生</a>
                    <p><i class="fa fa-align-left"></i><span>健康咨询 养生类</span></p>
                </div>
                <a href="#" class="index-info-btn"><i class="fa fa-file-text-o"></i></a>
            </li>
        </ul>
    </div>


</div>
</body>
</html>
