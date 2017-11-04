<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/8/13
  Time: 13:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Title</title>
    <script>
        $(function () {

            function getLocalIp() {
                var url = 'http://chaxun.1616.net/s.php?type=ip&output=json&callback=?&_=' + Math.random();
                var ip;
                $.getJSON(url, function (data) {
                    ip = data.Ip;
                    return ip;
                });
            }



            $("#reservation").addClass("active");
            var localCity="广东省";
            /*var localIp = getLocalIp();
             alert(localIp);*/
            //getLocalCity(ip);

            var localCity = "${ctx}/outpatient/outLocation/city?city=" + localCity;
            $.postJSON(localCity, function (data) {
                if (data.success == true) {
                    $("#cityDoc").empty();
                    $.each(JSON.parse(data.data), function (i, field) {
                        $("#cityDoc").append('<li><a>' + field.city + '(' + field.total + ')</a></li>')
                    })
                }
            })
            $("#proDoc").children().each(function () {
                $(this).click(function () {
                    var provinceTxt =  $(this).children().text();
                    var index = provinceTxt.indexOf("(")
                    var province=provinceTxt.substring(0,index);
                    var url = "${ctx}/outpatient/outLocation/city?city=" + province;

                    /*$("#divCityDoc").empty();
                     $("#divCityDoc").load("/outpatient/outLocation/city?city=" + province);*/

                    /*未知地区直接跳转到搜索页面*/
                    if(province=='未知地区'){
                        var url = "${ctx}/outpatient/clinicIndex?unknownCity=true";
                        location.href = url;
                    }

                    $.postJSON(url, function (data) {
                        if (data.success == true) {
                            $("#cityDoc").empty();
                            $.each(JSON.parse(data.data), function (i, field) {
                                $("#cityDoc").append('<li><a>' + field.city + '(' + field.total + ')</a></li>')
                            })
                        }
                    })




                });
            })
            $("#cityDoc").on("click", "li", function () {
                var cityTxt = $(this).children().text();
                var index = cityTxt.indexOf("(")
                var city = cityTxt.substring(0, index);
                var url = "${ctx}/outpatient/clinicIndex?city=" + city;
                location.href = url;
            })

        });


        /*function getLocalCity(ip) {
         debugger
         var url = "http://ip.taobao.com/service/getIpInfo.php?ip=" + ip;
         $.getJSON(url, function (json) {

         var myprovince2 = json.data.area;
         var mycity2 = json.data.region;
         alert("您所在的城市是：" + myprovince2 + mycity2);
         });
         }*/


    </script>
</head>
<body>
<div class="warp location">

    <div class="wx-outpatient outLocation">
        <span>当前：
        <c:if test="${not empty cityName}">
            ${cityName}
        </c:if>
        </span>
    </div>
    <div>
        <div class="col-xs-6 out-loca-city" style="padding: 0px;">
            <ul id="proDoc" class="nav">
                <c:forEach items="${provinceDocList}" var="proDoc">
                    <c:choose>
                        <c:when test="${empty proDoc.province}">
                            <li>
                                <a href="#">未知地区(${proDoc.total})</a>
                            </li>
                        </c:when>

                        <c:otherwise>
                            <li>
                                <a href="#">${proDoc.province}(${proDoc.total})</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </ul>
        </div>
        <%--点击省份出现对应的市区--%>
        <div id="divCityDoc" class="col-xs-6" style="padding: 0px">
            <ul id="cityDoc" class="nav">

            </ul>
        </div>

    </div>
</div>
</body>
</html>
