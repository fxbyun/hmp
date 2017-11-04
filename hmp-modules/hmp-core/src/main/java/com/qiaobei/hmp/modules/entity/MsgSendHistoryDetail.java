package com.qiaobei.hmp.modules.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;


@Entity
@Table(name = "msg_send_history_detail")
@DynamicInsert()
@DynamicUpdate()
public class MsgSendHistoryDetail extends IdEntity {
    private MsgSendHistory msgSendHistory;
    private Patient patient;
    private SendStatus sendStatus;


    public MsgSendHistoryDetail() {
    }

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "msg_send_history_id", referencedColumnName = "id", unique = true)
    public MsgSendHistory getMsgSendHistory() {
        return msgSendHistory;
    }

    public void setMsgSendHistory(MsgSendHistory msgSendHistory) {
        this.msgSendHistory = msgSendHistory;
    }

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "patient_id")
    public Patient getPatient() {
        return this.patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Column(name = "status")
    public SendStatus getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(SendStatus sendStatus) {
        this.sendStatus = sendStatus;
    }

    public enum SendStatus {
        Success, Failure
    }


}