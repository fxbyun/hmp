package com.qiaobei.hmp.modules.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "msg_money")
@DynamicInsert()
@DynamicUpdate()
public class MsgMoney extends IdEntity {

    private Doctor doctor;
    private Double deposit;

    public MsgMoney() {
    }

    public MsgMoney(Doctor doctor, Double deposit) {
        this.doctor = doctor;
        this.deposit = deposit;
    }

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "doctor_id")
    public Doctor getDoctor() {
        return this.doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    @Column(name = "deposit")
    public Double getDeposit() {
        return this.deposit;
    }

    public void setDeposit(Double deposit) {
        this.deposit = deposit;
    }

}