package com.qiaobei.hmp.modules.entity;

import com.google.common.collect.Lists;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "msg_send_history")
@DynamicInsert()
@DynamicUpdate()
public class MsgSendHistory extends IdEntity {
    private Doctor doctor;
    private Date createDate;
    private Integer sendSize;
    private Integer successSize;
    private String context;
    private String title;
    private SendType msgType;
    private Double useMoney;
    private List<MsgSendHistoryDetail> msgSendHistoryDetail = Lists.newArrayList();

    public MsgSendHistory() {
    }

    public MsgSendHistory(Long id) {
        super(id);
    }

    @Column(name = "success_size")
    public Integer getSuccessSize() {
        return successSize;
    }

    public void setSuccessSize(Integer successSize) {
        this.successSize = successSize;
    }

    @OneToMany(mappedBy = "msgSendHistory", orphanRemoval = true, cascade = CascadeType.ALL)
    public List<MsgSendHistoryDetail> getMsgSendHistoryDetail() {
        return msgSendHistoryDetail;
    }

    public void setMsgSendHistoryDetail(List<MsgSendHistoryDetail> msgSendHistoryDetail) {
        this.msgSendHistoryDetail = msgSendHistoryDetail;
    }

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "doctor_id")
    public Doctor getDoctor() {
        return this.doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    @Column(name = "create_date")
    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Column(name = "send_size")
    public Integer getSendSize() {
        return this.sendSize;
    }

    public void setSendSize(Integer sendSize) {
        this.sendSize = sendSize;
    }

    @Column(name = "context", length = 65535)
    public String getContext() {
        return this.context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    @Column(name = "title", length = 0)
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "msg_type")
    public SendType getMsgType() {
        return this.msgType;
    }

    public void setMsgType(SendType msgType) {
        this.msgType = msgType;
    }

    @Column(name = "use_money", precision = 22)
    public Double getUseMoney() {
        return this.useMoney;
    }

    public void setUseMoney(Double useMoney) {
        this.useMoney = useMoney;
    }

    public enum SendType {
        //@MetaData("自动")
        AUTO,
        //@MetaData("主动")
        SELF
    }

}