/*
 * 发送验证码
 * */
function send_code(btnSendCode) {
    var phone = $("#phoneNo").val();
    if (phone == "") {
        layer.alert("请输入手机号。");
        return false;
    }
    var seconds = 60;
    var setTime = function () {
        if (seconds == 0) {
            $(btnSendCode).removeAttr("disabled");
            $(btnSendCode).html("免费获取验证码");
            seconds = 60;
            clearInterval(timer);
        } else {
            $(btnSendCode).attr("disabled", true);
            $(btnSendCode).html("重新发送(" + seconds + "s)");
            seconds--;
        }
    }
    var timer = setInterval(function () {
        setTime();
    }, 1000);
    $.getJSON(ctx + "/anon/sendAuthCode", {"phone": phone}, function (result) {
        // debugger
        if (!result.success) {
            layer.alert("验证码发送失败！请重新发送。", {icon: 0});
        }
    });
    return false;

}
