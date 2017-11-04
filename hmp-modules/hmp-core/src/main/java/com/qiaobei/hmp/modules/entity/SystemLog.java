package com.qiaobei.hmp.modules.entity;


import javax.persistence.*;
import java.util.Date;

/**
 * 日志
 */
@Entity
@Table(name = "logs")
public class SystemLog extends IdEntity {

    private static final long serialVersionUID = 7902903481557774169L;

    private String account;
    private String module;
    private String methods;
    private String actionTime;
    private String userIP;
    private Date operTime;
    private String description;

    public SystemLog() {
    }

    public SystemLog(Long id) {
        this.id = id;
    }

    @Column(name = "account", length = 30)
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "oper_time", length = 7)
    public Date getOperTime() {
        return operTime;
    }

    public void setOperTime(Date operTime) {
        this.operTime = operTime;
    }

    @Column(name = "user_ip", length = 30)
    public String getUserIP() {
        return userIP;
    }

    public void setUserIP(String userIP) {
        this.userIP = userIP;
    }

    @Column(name = "module", length = 30)
    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    @Column(name = "methods", length = 30)
    public String getMethods() {
        return methods;
    }

    public void setMethods(String methods) {
        this.methods = methods;
    }

    @Column(name = "action_time", length = 30)
    public String getActionTime() {
        return actionTime;
    }

    public void setActionTime(String actionTime) {
        this.actionTime = actionTime;
    }

    @Column(name = "description", length = 5000)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
