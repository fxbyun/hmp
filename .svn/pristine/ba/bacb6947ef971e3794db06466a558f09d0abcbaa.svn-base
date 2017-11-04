/**
 * Created by Administrator on 2016/8/25 0025.
 */

function createVideo(videoId, divId) {
    var player = new YKU.Player(divId, {
        styleid: '0',
        //开发者ID
        client_id: 'f098c5614ed1d205',
        //播放视频ID
        vid: videoId,
        newPlayer: true,
        autoplay: true,
        events: {
            onPlayerReady: function () {
                // console.info("播放器准备完毕!")
            },
            onPlayStart: function () {
                // console.info("播放器开始播放!")
            },
            onPlayEnd: function () {
                //调用 滑动事件 滑动到下一个页面
                videoSlider.next();
                // alert("我放完了!")
                // console.info("播放器完成播放!")
            }
        }
    });
}
