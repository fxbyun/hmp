$(function () {

})

// 删除全部
function fn_deleteTr(even) {
    layer.confirm("该时段有病人，是否确认删除？", function () {

        var btn1 = $(even).prev("button").attr("href").replace("#", "");
        var btn2 = $(even).parent().parent().next().attr("id");
        $(even).prev("button").parent().parent().remove();
        btn2 == btn1 ? $("#" + btn2).remove() : "";
        parent.layer.closeAll();
    });
}

//详情页 发送短信
function fn_sendMes() {
    var index = layer.open({
        type: 2,
        maxmin: false,
        title: '发送短信',
        area: ['450px', '320px'],
        scrollbar: false,
        content: '/msPage/appSendMes'

    });
}
//详情页 删除预约
function fn_deleteDateilTr(even) {
    layer.confirm("该时段有病人，是否确认删除？", function () {

        $(even).parent().parent().remove();

        parent.layer.closeAll();
    });
}

/*设置小时选择的*/
function setHour(){
    var hourHtml="";
    for(var i=0;i<24;i++){
        //个位数加0
        var hourStr=i+"";
        if(i<10){
            hourStr="0"+hourStr;
        }
        hourHtml=hourHtml+"<option>"+hourStr+"</option>";
    }
    return hourHtml
}
/*设置分钟选择的*/
function setMinute(){

    var minuteHtml="";
    for(var i=0;i<60;){
        //个位数加0
        var minuteStr=i+"";
        if(i<10){
            minuteStr="0"+minuteStr;
        }
        minuteHtml=minuteHtml+"<option>"+minuteStr+"</option>";
        i=i+15;
    }
    return minuteHtml
}

/*获取当前的时间：格式yyyy-mm-dd*/
function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = year + seperator1 + month + seperator1 + strDate;
    return currentdate;
}
/*获得某天的所有时间段 day的格式为#time1 */
function getDayTime(day){
    var daytime=[];
    var time=new Object();
    //得到这是星期几的设置
    var weekday=day.replace("#time","")-1;
    var selectorId=day + " .app-select";
    $(selectorId).each(function(){
        var timeId=$(this).attr("id");
        if(timeId==undefined){
            timeId="";
        }
        var startHour = null;
        var startMinute = null;
        var endHour = null;
        var endMinute = null;

        $(this).find("select option:selected").each(function(i){
            switch(i)
            {
                case 0:
                    startHour =$(this).text();
                    break;
                case 1:
                    startMinute =$(this).text();
                    break;
                case 2:
                    endHour =$(this).text();
                    break;
                case 3:
                    endMinute =$(this).text();
                    break;
            }
        });
        var nowDay=getNowFormatDate();
        var startTime=nowDay+" "+startHour+":"+startMinute+":00"
        var endTime=nowDay+" "+endHour+":"+endMinute+":00"
        var timeStr=startTime+'到'+endTime;
        //开始时间应该大于结束时间
        //compareTime(timeStr,getWeekNameEn(weekday)+"时间设置有问题");
        //构建时间段对象
        time.id=timeId;
        time.time=timeStr;
        time.weekday=weekday;
        //console.info(time);
        daytime.push(JSON.stringify(time));
    });
    // console.info(daytime);
    return daytime;
}
/*获得所有勾选的天数*/
function getDaySelect(){
    var daySelect=[];
    $("#weekday input").each(function(i){
        var flag=$(this).get(0).checked;
        daySelect.push(flag)
    });
    return daySelect;
}
/*获得所有的时间段*/
function getAllDayTime(){

    /*获得所有勾选的天数*/
    var daySelectArray = getDaySelect();
    var allDayTimeArray=[];
    daySelectArray.forEach(function(daySelect,i){
        if(daySelect){
            var day="#time"+(i+1);
            var daytime=[];
            daytime = getDayTime(day);
            checkDayTime(day)

            for(var i=0;i<daytime.length;i++){
                allDayTimeArray.push(daytime[i])
            }
        }
    });
    return allDayTimeArray;
}

/*得到每15分钟的预约人数*/
function getEach15MinuteNum(){
    var num=$("#peopleNum").val();
    return num;
}

/*从后台得到的Json数据转化成一个dayTime数组*/
function getDayTimeArrayFromJson(json) {
    var dayTime=[]
    for(var array in json){
        var dayTimeArray=json[array];
        for(var i in dayTimeArray){
            dayTimeArray[i].weekday=array;
            var data=changeObject(dayTimeArray[i]);
            dayTime.push(data);
        }
    }
    return dayTime;
}
/*将一个object转化成另一个object*/
function changeObject(obj) {
    var data={
        "id":obj.id,
        "weekday":obj.weekday,
        "startHour":"",
        "startMinute":"",
        "endHour":"",
        "endMinute":""
    }
    data.startHour=getHour(obj.startTime);
    data.startMinute=getMinute(obj.startTime)
    data.endHour=getHour(obj.endTime)
    data.endMinute=getMinute(obj.endTime)
    return data;
}

/*将字符串（"06:00:00"）分割中出“06”，即小时*/
function getHour(str) {
    var index=str.indexOf(":");
    var hour=str.substring(0,index);
    return hour;
}

/*将字符串（"06:10:00"）分割中出“10”，即分钟*/
function getMinute(str) {
    var index=str.indexOf(":");
    var minute=str.substring(index+1,index+3);
    return minute;
}

/*先插入每天的时间有多少个div，并且构建id*/
function createDivSelect(dayTime) {
    var selectIdStr="";
    var divClass='<div class="app-select" ';
    var divStart='>';
    var selectContent= '<select class="form-control hour start"></select>'+
                    '<select class="form-control minute start"></select>'+
                    '<span>至</span>'+
                    '<select class="form-control hour end"></select>'+
                    '<select class="form-control minute end"></select>'


    //如果是第一个则是加号，不然是减号
    var jia_hao='<i class="fa fa-plus"></i>';
    var jian_hao='<i class="fa fa-minus"></i>';

    for(var time in dayTime){
        selectIdStr='id="'+dayTime[time].id+'"';

        var selectDiv=divClass+selectIdStr+divStart+selectContent+jia_hao;
        switch(dayTime[time].weekday){
            case "Monday":
                if($("div#time1").children().size()>0){
                    selectDiv=divClass+selectIdStr+divStart+selectContent+jian_hao
                }
                $("div#time1").append(selectDiv);
                break
            case "Tuesday":
                if($("div#time2").children().size()>0){
                    selectDiv=divClass+selectIdStr+divStart+selectContent+jian_hao
                }
                $("div#time2").append(selectDiv);
                break
            case "Wednesday":
                if($("div#time3").children().size()>0){
                    selectDiv=divClass+selectIdStr+divStart+selectContent+jian_hao
                }
                $("div#time3").append(selectDiv);
                break
            case "Thursday":
                if($("div#time4").children().size()>0){
                    selectDiv=divClass+selectIdStr+divStart+selectContent+jian_hao
                }
                $("div#time4").append(selectDiv);
                break
            case "Friday":
                if($("div#time5").children().size()>0){
                    selectDiv=divClass+selectIdStr+divStart+selectContent+jian_hao
                }
                $("div#time5").append(selectDiv);
                break
            case "Saturday":
                if($("div#time6").children().size()>0){
                    selectDiv=divClass+selectIdStr+divStart+selectContent+jian_hao
                }
                $("div#time6").append(selectDiv);
                break
            case "Sunday":
                if($("div#time7").children().size()>0){
                    selectDiv=divClass+selectIdStr+divStart+selectContent+jian_hao
                }
                $("div#time7").append(selectDiv);
                break
        }
    }
    $("div.tab-pane").each(function(){
        var hasChildren = $(this).children('div').length;
        if(hasChildren==0){
            var selectDiv=divClass+divStart+selectContent+jia_hao;
            $(this).append(selectDiv)
        }
    })
}

/*在页面渲染插入时间数据*/
function addTimeData(dayTime) {
    for(var i in dayTime){
        var time=dayTime[i]
        switch (time.weekday){
            case "Monday":
                whereToAdd(time)
                break
            case "Tuesday":
                whereToAdd(time)
                break
            case "Wednesday":
                whereToAdd(time)
                break
            case "Thursday":
                whereToAdd(time)
                break
            case "Friday":
                whereToAdd(time)
                break
            case "Saturday":
                whereToAdd(time)
                break
            case "Sunday":
                whereToAdd(time)
                break
        }
    }

}

function whereToAdd(time) {
    var divId="div#"+time.id;
    //$(divId).children("select").eq(0).find("option:selected").text(time.startHour);
    var test = "option:contains(" + time.startHour + ")"
    $(divId).children("select").eq(0).find("option:contains(" + time.startHour + ")").attr("selected", true)

    $(divId).children("select").eq(1).find("option:contains(" + time.startMinute + ")").attr("selected", true)
    $(divId).children("select").eq(2).find("option:contains(" + time.endHour + ")").attr("selected", true)
    $(divId).children("select").eq(3).find("option:contains(" + time.endMinute + ")").attr("selected", true)
}

/*根据传来的选择器对象，获得该时间段*/
function getTimeBySelector(selector){
    var startHour = null;
    var startMinute = null;
    var endHour = null;
    var endMinute = null;

    selector.find("select option:selected").each(function(i){
        switch(i)
        {
            case 0:
                startHour =$(this).text();
                break;
            case 1:
                startMinute =$(this).text();
                break;
            case 2:
                endHour =$(this).text();
                break;
            case 3:
                endMinute =$(this).text();
                break;
        }
    });
    var nowDay=getNowFormatDate();
    var startTime=nowDay+" "+startHour+":"+startMinute+":00"
    var endTime=nowDay+" "+endHour+":"+endMinute+":00";
    var timeStr=startTime+'到'+endTime;
    return timeStr;
}

function compateDate(date1,date2) {
    if(date1>=date2){
        return false;
    }else{}
    return true;
}

function compareTime(timeStr,msg){
    var startStr=timeStr.substring(0,timeStr.indexOf("到"))
    var endStr=timeStr.substring(timeStr.indexOf("到")+1,timeStr.length)
    var startDate=new Date(startStr);
    var endDate=new Date(endStr);
    if(startDate>=endDate){
        layer.alert(msg)
        throw SyntaxError();
    }
}
/*得到开始时间Date*/
function getStartDate(timeStr) {
    var startStr=timeStr.substring(0,timeStr.indexOf("到"))
    var startDate=new Date(startStr);
    return startDate;
}
/*得到结束的时间Date*/
function getEndDate(timeStr) {
    var endStr=timeStr.substring(timeStr.indexOf("到")+1,timeStr.length)
    var endDate=new Date(endStr);
    return endDate;
}
/*得到开始时间String*/
function getStartString(timeStr){
    var startStr=timeStr.substring(0,timeStr.indexOf("到"))
    return startStr;
}
/*得到结束的时间String*/
function getEndString(timeStr) {
    var endStr=timeStr.substring(timeStr.indexOf("到")+1,timeStr.length);
    return endStr;
}

function getWeekNameEn(index) {
    switch (index){
        case 0:
            return "Monday"
        case 1:
            return "Tuesday"
        case 2:
            return "Wednesday"
        case 3:
            return "Thursday"
        case 4:
            return "Friday"
        case 5:
            return "Saturday"
        case 6:
            return "Sunday"
        default:
            return "Monday"
    }
}

function getWeekNameCH(index) {
    switch (index){
        case 0:
            return "星期一"
        case 1:
            return "星期二"
        case 2:
            return "星期三"
        case 3:
            return "星期四"
        case 4:
            return "星期五"
        case 5:
            return "星期六"
        case 6:
            return "星期日"
        default:
            return "星期一"
    }
}
/*检查时间是否设置有问题，该后台*/
function checkDayTime(dayDivId) {
    $(dayDivId).children("div").each(function () {

        var timeStr=getTimeBySelector($(this));
        var weekday=getWeekNameCH(parseInt($(this).parent().attr("id").replace("time",""))-1);
        compareTime(timeStr,weekday+"时间设置有问题！");
        //该时间段的结束时间
        var endDate = getEndDate(timeStr);
        if($(this).next().length>0){
            var nextTimeStr=getTimeBySelector($(this).next());
            //下一个时间段的开始时间
            var nextStartDate = getStartDate(nextTimeStr);
            if(!compateDate(endDate,nextStartDate)){
                var startStr=getStartString(nextTimeStr);

                layer.alert(weekday+":"+startStr.substring(startStr.indexOf(" ")+1,startStr.length)+"该时间应该大于上一个时间段的结束时间")
                throw SyntaxError();
            }
        }
    })
}

function fn_LoadAppointListPage(page, day) {

    if (page == undefined) page = 0;
    var url = "/fragment/appoint/appointThreeDay?page={0}&day={1}".format(page, day);
    $('#appointList').load(url);
}

//发送短信
function sendMessage(appointPatientId) {

}

//删除单条预约
function delAppointPatient(appointPatientId) {
    layer.confirm("你确定删除该预约号吗？",function () {
        var url = "/fragment/appoint/delAppointPatient";
        $.postJSON(url,{"id":appointPatientId},function (data) {

            if(data.success){
                layer.msg(data.msg);
                var delTrId="#appPatient_"+appointPatientId;
                $(delTrId).children(".appStatus").text("已删除");
                var peoNum = $(delTrId).closest(".collapse").prev().children().eq(2);
                if (peoNum.text() > 0) {
                    peoNum.text(peoNum.text() - 1);
                }
            }else{
                layer.msg('删除失败！');
            }
        });
    });
}

//删除整个时间段下的预约
function delAllAppointPatient(listId) {

    layer.confirm("你确定删除这条列表下的预约号吗？",function () {
        var url = "/fragment/appoint/delAllAppointPatient";
        $.postJSON(url,{"listId":listId},function (data) {

            if(data.success){
                layer.msg(data.msg);
                var delTrId="#appDetail_"+listId;
                $(delTrId).find(".appStatus").each(function () {
                    $(this).text("已删除");
                })
            }else{
                layer.msg('删除失败！');
            }
        });
    });
}

/*
 * 预约列表- 编辑发送短信弹框
 * */
function fn_sendMessage(appPatientId) {
    var index = layer.open({
        type: 2,
        maxmin: false,
        title: '编辑预约删除短信',
        area: ['450px', '320px'],
        scrollbar: false,
        content: '/msPage/appoint/editMsg?appPatientId='+appPatientId

    });
}
