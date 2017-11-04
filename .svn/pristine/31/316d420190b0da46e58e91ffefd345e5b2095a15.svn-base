package com.qiaobei.hmp.modules.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "msg_recharge_detail")
@DynamicInsert()
@DynamicUpdate()
public class MsgRechargeDetail extends IdEntity {
    private Doctor doctor;
    private Double money;
    private Double afterMoney;
    private Double addMoney;
    private Date createDate;
    private Date updateDate;
    private String rechargeWay;
    private String rechargeNum;
    private String wxInfoSend;
    private String wxInfoBack;
    private PayType havePay;
    private String reMark;


    public MsgRechargeDetail() {
    }

    @Column(name = "re_mark")
    public String getReMark() {
        return reMark;
    }

    public void setReMark(String reMark) {
        this.reMark = reMark;
    }

    @Column(name = "have_pay")
    public PayType getHavePay() {
        return havePay;
    }

    public void setHavePay(PayType havePay) {
        this.havePay = havePay;
    }

    @Column(name = "add_money")
    public Double getAddMoney() {
        return addMoney;
    }

    public void setAddMoney(Double addMoney) {
        this.addMoney = addMoney;
    }

    @Column(name = "update_date")
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Column(name = "wx_info_send")
    public String getWxInfoSend() {
        return wxInfoSend;
    }

    public void setWxInfoSend(String wxInfoSend) {
        this.wxInfoSend = wxInfoSend;
    }

    @Column(name = "wx_info_back")
    public String getWxInfoBack() {
        return wxInfoBack;
    }

    public void setWxInfoBack(String wxInfoBack) {
        this.wxInfoBack = wxInfoBack;
    }

    @Column(name = "after_money")
    public Double getAfterMoney() {
        return afterMoney;
    }

    public void setAfterMoney(Double afterMoney) {
        this.afterMoney = afterMoney;
    }

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    public Doctor getDoctor() {
        return this.doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    @Column(name = "money")
    public Double getMoney() {
        return this.money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    @Column(name = "create_date")
    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Column(name = "recharge_way")
    public String getRechargeWay() {
        return this.rechargeWay;
    }

    public void setRechargeWay(String rechargeWay) {
        this.rechargeWay = rechargeWay;
    }

    @Column(name = "recharge_num")
    public String getRechargeNum() {
        return this.rechargeNum;
    }

    public void setRechargeNum(String rechargeNum) {
        this.rechargeNum = rechargeNum;
    }
}