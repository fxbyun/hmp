/**
 * Created by Administrator on 2017/1/17.
 */

$(function () {
    //获取屏幕分辨率
    var screenW = screen.width;

    if (screenW == 1024) {
        //坐诊页
        $(".diagnosis .dia-logon").find("div.col-md-3").prop("class", "col-md-4 col-sm-4");

        //我的患者  搜索栏
        $(".patientManage #moreSeach div.col-md-4").prop("class", "col-md-5 col-sm-5");
        $(".patientManage #moreSeach div.col-md-5:last-child").prop("class", "col-md-4 col-sm-4");
        $(".patientManage #moreSeach").find("input[name='diagonsisName']").attr("style", "width: 133px");
        $(".patientManage #moreSeach .btn-warning").attr("style", "width: 100px;margin-left: 0px;");
    }

})
