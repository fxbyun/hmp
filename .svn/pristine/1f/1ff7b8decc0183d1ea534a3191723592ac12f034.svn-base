package com.qiaobei.hmp.modules.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "notice_item")
@DynamicInsert()
@DynamicUpdate()
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class NoticeItem extends StatusEntity {

    private Notice notice;
    private Long doctorId;
    private String doctorName;
    private String sendBy;
    private Long sendById;
    private Date sendOn;
    private Date readOn;

    public NoticeItem() {
    }

    public NoticeItem(Long id) {
        super(id);
    }

    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "notice_id")
    public Notice getNotice() {
        return notice;
    }

    public void setNotice(Notice notice) {
        this.notice = notice;
    }

    @Column(name = "doctor_name", nullable = false, length = 100)
    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    @Column(name = "doctor_id")
    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    @Column(name = "send_by", length = 20)
    public String getSendBy() {
        return sendBy;
    }

    public void setSendBy(String sendBy) {
        this.sendBy = sendBy;
    }

    @Column(name = "send_by_id")
    public Long getSendById() {
        return sendById;
    }

    public void setSendById(Long sendById) {
        this.sendById = sendById;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "send_on", length = 6)
    public Date getSendOn() {
        return sendOn;
    }

    public void setSendOn(Date sendOn) {
        this.sendOn = sendOn;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "read_on", length = 6)
    public Date getReadOn() {
        return readOn;
    }

    public void setReadOn(Date readOn) {
        this.readOn = readOn;
    }
}