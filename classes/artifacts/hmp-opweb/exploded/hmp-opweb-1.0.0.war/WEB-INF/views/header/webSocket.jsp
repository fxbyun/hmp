<%--
  Created by IntelliJ IDEA 2016.4
  IDEA汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
  @author 凉生
  Date 2017/1/17 0017.
  Time 14:40.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="${ctx}/assets/scripts/base.js"></script>
<link href="${ctx}/assets/toastr/toastr.css" type="text/css" rel="stylesheet">
<script src="${ctx}/assets/toastr/toastr.js" type="text/javascript"></script>
<script src="${ctx}/assets/toastr/toastr.ext.js" type="text/javascript"></script>

<script type="application/javascript">
    /**
     * Created by IntelliJ IDEA 2016.4
     * IDEA汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
     * @author 凉生
     * Date 2017/1/17 0017.
     * Time 10:43.
     */

    var webSocket = null;
    var url = null;
    var isLocal = false;
    $(function () {
        //初始化websocket
//            url = 'ws://' + window.location.host + "/wx/websocket?doctorId=123&tmpId";
        if (window.location.host && window.location.host.indexOf("yijiazhen") >= 0) {
            url = 'ws://' + "www.yijiazhen.com:9999" + "/wx/webSocket";
        } else {
            url = 'ws://' + "192.168.1.14:8080" + "/wx/webSocket";
            isLocal = true;
            Alert.warning("当前为本地开发模式,webSocket服务器默认连接\n" + url + " \n 你传入的医生Id为:${doctor.id}", "调试信息:");
        }
        connect();
    });
    //开启websocket连接
    function connect() {
        webSocket = new WebSocket(url);
        //开启连接
        try {
            webSocket.onopen = function () {
                Alert.info('广告机服务连接中....');
            };
        } catch (e) {
            Alert.error("连接叫号服务器错误...")
        }

        //当收到信息
        webSocket.onmessage = function (event) {
            debugger
            var date = JSON.parse(event.data);
            // Alert.success(date.msg, date.user);
            if (date.type == "init") {
                Alert.success(date.msg, date.user);
            } else {
                console.info(date.user + ":" + date.msg);
                //通知本地查询服务器排队数据
                try {
                    if (isLocal) {
                        Alert.success(date.msg, date.user);
                    }
                    if (date.type == "setting") {
                        utlis_getPicAndTextCount();
                    } else {
                        utlit_getAdvingSize();
                    }
                } catch (e) {

                }
            }
        };
        //当关闭webSocket
        webSocket.onclose = function (event) {
            Alert.warning('连接被关闭...', "服务器信息:");
        };
    }
    //关闭连接
    function disconnect() {
        if (webSocket != null) {
            webSocket.close();
            webSocket = null;
        }
    }
    /**
     * 通知绑定该医生的广告机和护士端 服务器排队或广告机设置已经改变
     */
    function callChang(doctorId) {
        var obj = {"doctor": doctorId};
        if (webSocket != null) {
            // Alert.success('我发送: ' + msg);
            //发送信息
            webSocket.send(JSON.stringify(obj));
        } else {
            Alert.error('未连接到服务器,请刷新网页,如果多次出现本提示请联系技术人员!');
        }
    }
</script>
