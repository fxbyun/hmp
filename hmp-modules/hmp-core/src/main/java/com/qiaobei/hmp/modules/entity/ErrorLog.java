package com.qiaobei.hmp.modules.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * ErrorLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "error_log")
public class ErrorLog extends IdEntity {


    private String type;
    private Doctor doctor;
    private Date createTime;
    private String errorTitle;
    private String errorUrl;
    private String errorInfo;

    // Constructors

    /**
     * default constructor
     */
    public ErrorLog() {
    }


    @Column(name = "type", length = 20)
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "doctor_id")
    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    @Column(name = "create_time", length = 19)
    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "error_title")
    public String getErrorTitle() {
        return this.errorTitle;
    }

    public void setErrorTitle(String errorTitle) {
        this.errorTitle = errorTitle;
    }

    @Column(name = "error_url")
    public String getErrorUrl() {
        return this.errorUrl;
    }

    public void setErrorUrl(String errorUrl) {
        this.errorUrl = errorUrl;
    }

    @Column(name = "error_info", length = 65535)
    public String getErrorInfo() {
        return this.errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

}