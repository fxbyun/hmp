package com.qiaobei.hmp.modules.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "msg_meassage")
@DynamicInsert()
@DynamicUpdate()
public class MsgMeassage extends IdEntity {

    private Doctor doctor;
    private String info;

    public MsgMeassage() {
    }


    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "doctor_id")
    public Doctor getDoctor() {
        return this.doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    @Column(name = "info")
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}