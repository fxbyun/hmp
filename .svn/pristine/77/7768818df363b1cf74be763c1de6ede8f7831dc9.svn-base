package com.qiaobei.hmp.modules.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2016/8/31 0031.
 */
@Entity
@Table(name = "appoint_patient")
@DynamicInsert(true)
@DynamicUpdate(true)
public class AppointPatient extends IdEntity {


    //预约状态
    public enum Status{
        Undistributed,      //未预约
        Distributed,        //已预约
        Canceled,           //已取消
        Treatment,          //已就诊
        No_Treatment,       //未就诊
        Deleted,             //已删除（该预约号不能被预定）
    }

    public enum SendMessage{
        Not,Has
    }

    private AppointList appointList;
    private Patient patient;
    private SendMessage hasSendMessage;
    private Status appointStatus;

    private Date date;

    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "list_id")
    public AppointList getAppointList() {
        return appointList;
    }

    public void setAppointList(AppointList appointList) {
        this.appointList = appointList;
    }

    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "patient_id")
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Column(name="has_send_message")
    @Enumerated(value = EnumType.ORDINAL)
    public SendMessage getHasSendMessage() {
        return hasSendMessage;
    }

    public void setHasSendMessage(SendMessage hasSendMessage) {
        this.hasSendMessage = hasSendMessage;
    }

    @Column(name = "appoint_status", nullable = true)
    @Enumerated(value = EnumType.ORDINAL)
    public Status getAppointStatus() {
        return appointStatus;
    }

    public void setAppointStatus(Status appointStatus) {
        this.appointStatus = appointStatus;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String StatusByEnum(){
        if(this.appointStatus==Status.Undistributed){
            return "未预约";
        }else if(this.appointStatus==Status.Distributed) {
            return "已预约";
        }else if(this.appointStatus==Status.Canceled){
            return "已取消";
        }else if(this.appointStatus==Status.Treatment){
            return "已就诊";
        }else if(this.appointStatus==Status.Deleted){
            return "已删除";
        }else {
            return "未就诊";
        }
    }
}
