package com.qiaobei.hmp.modules.entity;


import com.qiaobei.hmp.modules.support.DateUtils;

import java.util.Date;

/**
 * Created by Administrator on 2016/9/5 0005.
 */
public class DayTime {
    private Long id;
    private String time;
    private String weekday;


    /**获得开始时间
     * @param timeStr
     * @return
     */
    public Date getStartTime(String timeStr){
        int index = timeStr.indexOf("到");
        String startStr=timeStr.substring(0,index);
        Date startDate=DateUtils.str2Date(startStr,DateUtils.datetimeFormat);
        return startDate;
    }

    /**获得开始时间
     * @param timeStr
     * @return
     */
    public Date getEndTime(String timeStr){
        int index = timeStr.indexOf("到");
        String endStr=timeStr.substring(index+1,timeStr.length()-1);
        Date endDate=DateUtils.str2Date(endStr,DateUtils.datetimeFormat);
        return endDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }
}
