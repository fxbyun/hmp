<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>门诊</title>
    <script src="http://api.map.baidu.com/api?v=1.4" type="text/javascript"></script>
    <script type="text/javascript" src="${ctx}/assets/jquery-cookie/jquery.cookie.js"></script>
    <script type="text/javascript" src="${ctx}/assets/scripts/dateTime.js"></script>
    <script type="text/javascript" src="${ctx}/assets/scripts/getLocation.js"></script>
    <script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
    <script>
        /*$(function () {
            if ($("#location").text().trim() == '') {
                getLocation(success, error);
            }
            $("#reservation").addClass("active");
        })

        //获取地址成功
        var success = function getPlaceSuccess(place) {
            $.postJSON("/outpatient/saveLocation", {province: place.province, city: place.city}, function (data) {
                if (data.success) {
                    $("#location").text(place.city);
                }
            })

        }

        //获取地址失败
        var error = function getPlaceError(error) {
            console.info("定位失败:" + error);
        }*/

        <%--wx.config({--%>
        <%--debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。--%>
        <%--appId: '${jssdkMap[appId]}', // 必填，企业号的唯一标识，此处填写企业号corpid--%>
        <%--timestamp: parseInt("${jssdkMap[timestamp]}",10), // 必填，生成签名的时间戳--%>
        <%--nonceStr: '${jssdkMap[noncestr]}', // 必填，生成签名的随机串--%>
        <%--signature: '${jssdkMap[signature]}',// 必填，签名，见附录1--%>
        <%--jsApiList: ['getLocation'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2--%>
        <%--});--%>
        <%--wx.ready(function(){--%>
        <%--});--%>

        <%--wx.error(function(res){--%>
        <%--});--%>

        <%--wx.getLocation({--%>
        <%--success: function (res) {--%>
        <%--alert("小宝鸽获取地理位置成功，经纬度为：（" + res.latitude + "，" + res.longitude + "）" );--%>
        <%--},--%>
        <%--fail: function(error) {--%>
        <%--AlertUtil.error("获取地理位置失败，请确保开启GPS且允许微信获取您的地理位置！");--%>
        <%--}--%>
        <%--});--%>


    </script>
</head>
<body>
<div class="warp">
    <div class="form-group wx-outpatient">
        <a href="${ctx}/outpatient/outLocation" class="text-left" style="float: left;">
            <i class="fa fa-map-marker outpa-icon"></i>
            <span id="location">${cityName}</span>
        </a>
        <ul class="nav wx-out-span">
            <li>
                <a href="${ctx}/outpatient/clinicIndex">找诊所</a>
            </li>
            <li><span style="line-height: 2.3em; padding: 0 0.8em;">|</span></li>
            <li>
                <a href="${ctx}/outpatient/doctorIndex">找医生</a>
            </li>
        </ul>

    </div>

    <div class="wx-out-conter tab-content">
        <%-- 门诊列表 --%>
        <div role="tabpanel" class="tab-pane active" id="tabone">
            <div class="wx-out-seach">
                <input name="outName" id="txtOutName" type="text" class="form-control" placeholder="搜索诊所">
                <i class="fa fa-search" id="btnOutName"></i>
            </div>
            <ul>
                <%--最近所去的诊所--%>
                <c:if test="${not empty lastDoctor}">
                    <li class="active"
                        onclick="javascript:location.href='${ctx}/outpatient/outLocationDetail?doctorId=${lastDoctor.id}'">
                            <%--医生头像--%>
                        <a href="${ctx}/outpatient/outLocationDetail?doctorId=${lastDoctor.id}" class="float-left">
                            <c:if test="${empty zhenSuoHeadMap[lastDoctor.id]}">
                                <img src="${ctx}/assets/images/default.jpg" width="50" height="52" alt="门诊头像">
                            </c:if>
                            <c:if test="${not empty zhenSuoHeadMap[lastDoctor.id]}">
                                <img src="${ctx}/temp/${zhenSuoHeadMap[lastDoctor.id]}" width="50" height="52"
                                     alt="门诊头像">
                            </c:if>
                        </a>

                        <div class="wx-out-info float-left">
                            <a href="${ctx}/outpatient/outLocationDetail?doctorId=${lastDoctor.id}">${lastDoctor.outpatientService}</a>
                            <div><i class="outpa-icon02 fa fa-map-marker"></i><span>${lastDoctor.businessAddr}</span>
                            </div>
                        </div>
                        <span class="float-right out-ahref">最近去过</span>
                    </li>
                </c:if>

                <%--搜索的所有医生信息--%>
                <c:forEach items="${doctorPage.content}" var="doctor" varStatus="status">
                    <li onclick="javascript:location.href='${ctx}/outpatient/outLocationDetail?doctorId=${doctor.id}'">
                        <a href="${ctx}/outpatient/outLocationDetail?doctorId=${doctor.id}" class="float-left">
                            <c:if test="${empty zhenSuoHeadMap[doctor.id]}">
                                <img src="${ctx}/assets/images/default.jpg" width="50" height="52" alt="门诊头像">
                            </c:if>
                            <c:if test="${not empty zhenSuoHeadMap[doctor.id]}">
                                <img src="${ctx}/temp/${zhenSuoHeadMap[doctor.id]}" width="50" height="52" alt="门诊头像">
                            </c:if>
                        </a>
                        <div class="wx-out-info float-left">
                            <a href="${ctx}/outpatient/outLocationDetail?doctorId=${doctor.id}">${doctor.outpatientService}</a>
                            <div><i class="outpa-icon02 fa fa-map-marker"></i><span>${doctor.businessAddr}</span></div>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <%-- 门诊列表 END --%>
    </div>

</div>
<script>
    $(function () {
        $("#btnOutName").click(function () {
            var outName = $("#txtOutName").val();
            window.location.href = "${ctx}/outpatient/clinicIndex?outName=" + outName;
        });

        $("#btnDocName").click(function () {
            var doctorName = $("#txtDoctorName").val();
            window.location.href = "${ctx}/outpatient/clinicIndex?doctorName=" + doctorName;
        });

    })
</script>
</body>
</html>
