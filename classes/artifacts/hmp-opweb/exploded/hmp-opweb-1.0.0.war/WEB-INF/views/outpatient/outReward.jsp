<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/8/22
  Time: 11:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>打赏页面</title>
    <script src="${ctx}/assets/webSockets/sockjs-0.3.min.js"></script>
    <%@ include file="/WEB-INF/views/header/webSocket.jsp" %>
    <script>
        $(function () {

            $("#reservation").addClass("active");
            var isPass = false;
            $("#goToPay").click(function () {

                var rewardId = ${appointReward.id};

                if (isPass) {
                    return
                }
                isPass = true;
                var money = $("#txtMoney").val();
                $.postJSON("${ctx}/outpatient/getPayDate", {"money": money, "rewardId": rewardId}, function (data) {
                    if (data.success == true) {
                        onBridgeReady(data.data);
                    } else {
                        layer.alert(data.msg)
                    }
                })
            });

            $("#noToPay").click(function () {
                location.href = "${ctx}/outpatient/clinicIndex";
            })


            $("#test").click(function () {
                onBridgeReady();
            })
            //websocket通知轮询排队
            callChang(${doctor.id});
        });

        /**
         *
         * @param obj
         * @returns {string}
         */
        function isObj(obj) {
            var tmpMsg = "";
            if (typeof obj == "object") {
                for (x in obj) {
                    if (typeof obj[x] == "object") {
                        tmpMsg += (x + ":{\n")
                        for (j in obj[x]) {
                            tmpMsg += (j + ":" + obj[x][j] + "\n");
                        }
                        tmpMsg += ( "}\n")
                    } else {
                        tmpMsg += (x + ":" + obj[x] + "\n");
                    }
                }
            } else {
                tmpMsg = obj;
            }
            return tmpMsg;
        }

        function onBridgeReady(obj) {
            WeixinJSBridge.invoke(
                    'getBrandWCPayRequest', {
                        "appId": obj.appId,     //公众号名称，由商户传入
                        "timeStamp": obj.timeStamp,         //时间戳，自1970年以来的秒数
                        "nonceStr": obj.nonceStr, //随机串
                        "package": obj.package,
                        "signType": obj.signType,         //微信签名方式
                        "paySign": obj.paySign //微信签名
                    }, function (res) {
//                        $("body").text(isObj(res));
                        try {
                            if (res.err_msg) {
                                if (res.err_msg.indexOf("ok")) {
                                    layer.msg("打赏成功，感谢您的慷慨！")
                                    location.href = "${ctx}/outpatient/clinicIndex";
                                }
                                // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。
                            }
                        } catch (e) {
                        }

                        //$("body").text(isObj(res));
                    }
            );
        }
    </script>
</head>
<body>
<div class="warp">
    <div>
        <img src="${ctx}/assets/images/img7.png" width="100%" height="20%" alt="广告图">
        <h3 class="text-center" style="line-height: 3em; "><i class="fa fa-thumbs-o-up" style="padding-right: 5px;"></i>打赏
        </h3>
        <div class="text-center">
            <i class="fa fa-rmb" style="margin: 0 0.5em;"></i>
            <input id="txtMoney" type="text" class="form-control" style="width: 80px; display: inline-block;" value="1">
            <span style="padding-left: 0.5em;">元</span>
        </div>
        <div class="text-center" style="margin-top: 3em;">
            <button id="goToPay" type="button" class="btn btn-success" style="width: 80px; margin-right: 5px;">打赏
            </button>
            <button id="noToPay" type="button" class="btn btn-success" style="width: 80px;">不打赏
            </button>
        </div>


    </div>
</div>
</body>
</html>
