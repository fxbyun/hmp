package com.qiaobei.hmp.modules.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2016/8/31 0031.
 */
@Entity
@Table(name = "appoint_list")
@DynamicInsert(true)
@DynamicUpdate(true)
public class AppointList extends IdEntity {

    public enum Status{
        Valid,Invalid
    }

    private Date date;
    private Date startTime;
    private Date endTime;
    private Integer peopleNum;
    private Status status;


    private Doctor doctor;
    private Integer configPeopleNum;
    private Integer remainder;          //剩余可用的预约号

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_time")
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_time")
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Column(name = "people_num")
    public Integer getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(Integer peopleNum) {
        this.peopleNum = peopleNum;
    }

    @Column(name = "status", nullable = false)
    @Enumerated(value = EnumType.ORDINAL)
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "doctor_id")
    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    @JoinColumn(name = "config_people_num")
    public Integer getConfigPeopleNum() {
        return configPeopleNum;
    }

    public void setConfigPeopleNum(Integer configPeopleNum) {
        this.configPeopleNum = configPeopleNum;
    }

    @Column(name = "remainder")
    public Integer getRemainder() {
        return remainder;
    }

    public void setRemainder(Integer remainder) {
        this.remainder = remainder;
    }

}
