package com.qiaobei.hmp.modules.entity;


import javax.persistence.*;
import java.util.Date;

/**
 * 用户登陆信息
 */
@Entity
@Table(name = "user_login")
public class UserLogin extends IdEntity {

    private static final long serialVersionUID = -3719138362034049393L;

    private Doctor user;
    private Date loginTime;
    private String loginIP;

    public UserLogin() {
    }

    public UserLogin(Long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    public Doctor getUser() {
        return user;
    }

    public void setUser(Doctor user) {
        this.user = user;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "login_time", length = 7)
    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    @Column(name = "login_ip", length = 40)
    public String getLoginIP() {
        return loginIP;
    }

    public void setLoginIP(String loginIP) {
        this.loginIP = loginIP;
    }
}
