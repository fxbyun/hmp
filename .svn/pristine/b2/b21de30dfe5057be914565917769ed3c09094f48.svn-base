package com.qiaobei.hmp.modules.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2016/8/31 0031.
 */
@Entity
@Table(name = "appoint_time_config")
@DynamicInsert(true)
@DynamicUpdate(true)
public class AppointTimeConfig extends IdEntity {
    @JsonIgnore
    private AppointWeekConfig appointWeekConfig;
    private Date startTime;
    private Date endTime;

    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "week_id")
    public AppointWeekConfig getAppointWeekConfig() {
        return appointWeekConfig;
    }

    public void setAppointWeekConfig(AppointWeekConfig appointWeekConfig) {
        this.appointWeekConfig = appointWeekConfig;
    }

    @Temporal(TemporalType.TIME)
    @Column(name = "start_time")
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Temporal(TemporalType.TIME)
    @Column(name = "end_time")
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
