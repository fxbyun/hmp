/**
 * Created by IntelliJ IDEA 2016.4
 * IDEA汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * @author 凉生
 * Date 2017/1/17 0017.
 * Time 10:43.
 */

var webSocket = null;
var url = null;
$(function () {
    //初始化websocket
//            url = 'ws://' + window.location.host + "/wx/websocket?doctorId=123&tmpId";
    if (window.location.host && window.location.host.indexOf("yijiazhen") >= 0) {
        url = 'ws://' + "www.yijiazhen.com" + "/wx/webSocket?doctorId=${doctor.id}";
    } else {
        url = 'ws://' + "127.0.0.1:8080" + "/wx/webSocket?doctorId=${doctor.id}";
        Alert.warning("当前为本地开发模式,webSocket服务器默认连接\n" + url, "调试信息:");
    }
    connect();
});
//开启websocket连接
function connect() {
    webSocket = new WebSocket(url);
    //开启连接
    webSocket.onopen = function () {
        Alert.info('广告机服务连接中....');
    };
    //当收到信息
    webSocket.onmessage = function (event) {
        var date = JSON.parse(event.data);
        // Alert.success(date.msg, date.user);
        if (date.type == "init") {
            Alert.success(date.msg, date.user);
        } else {
            console.info(date.user + ":" + date.msg);
            //通知本地查询服务器排队数据
            try {
                utlit_getAdvingSize();
            } catch (e) {

            }
        }
    };
    //当关闭webSocket
    webSocket.onclose = function (event) {
        Alert.warning('事件: 连接被关闭...');
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
 * @param msg  格式必须为 {doctor:X}
 */
function callChang(msg) {
    if (!msg.doctor) {
        throw " 传入参数 格式必须为 {doctor:doctorId}";
    }
    if (webSocket != null) {
        // Alert.success('我发送: ' + msg);
        //发送信息
        webSocket.send(JSON.stringify(msg));
    } else {
        Alert.error('未连接到服务器,请刷新网页,如果多次出现本提示请联系技术人员!');
    }
}