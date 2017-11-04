<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/8/25 0025
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <script type="text/javascript" src="http://player.youku.com/jsapi"></script>
    <script type="text/javascript">
        var player = new YKU.Player('youkuplayer', {
            styleid: '0',
            //开发者ID
            client_id: 'ee04859b466a1ce2',
            //播放视频ID
            vid: 'XNzQyNjc0ODM2',
            newPlayer: true,
            autoplay: true,
            events: {
                onPlayerReady: function () {
                    console.info("播放器准备完毕!")
                },
                onPlayStart: function () {
                    console.info("播放器开始播放!")
                },
                onPlayEnd: function () {
                    console.info("播放器完成播放!")
                }
            }
        });
        function createYkPlayer() {

        }
        //播放视频
        function playVideo() {
            player.playVideo();
        }
        //暂停视频
        function pauseVideo() {
            player.pauseVideo();
        }
        //跳转到XX 时间
        function seekTo(s) {
            player.seekTo(s);
        }
        // 获取 视频总时长
        function currentTime() {
            return player.currentTime();
        }
    </script>
</head>
<body>
<div id="youkuplayer" style="width:1080px;height:607px">
</div>
</body>
</html>
