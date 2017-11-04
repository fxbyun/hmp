package com.qiaobei.hmp.modules.entity;

import javax.persistence.*;

@Entity
@Table(name = "j_doctor_ext_cost")
public class JDoctorExtCost extends IdEntity {

    private String className;
    private Double price;
    private Doctor doctor;

    public JDoctorExtCost() {
    }

    @Column(name = "class_name")
    public String getClassName() {
        return this.className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Column(name = "price", precision = 10)
    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "doctor_id")
    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}