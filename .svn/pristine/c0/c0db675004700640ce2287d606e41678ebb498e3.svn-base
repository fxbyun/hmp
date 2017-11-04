package com.qiaobei.hmp.modules.entity;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA 2016.4
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 2016/11/12 0012
 * Time 14:29
 */
@Entity
@Table(name = "emr_ext_cost")
public class EmrExtCost extends StatusEntity {
    private String className;
    private Double price;
    private Emr emr;
    private Long doctorCostId;


    @Column(name = "doctor_cost_id")
    public Long getDoctorCostId() {
        return doctorCostId;
    }

    public void setDoctorCostId(Long doctorCostId) {
        this.doctorCostId = doctorCostId;
    }


    @Basic
    @Column(name = "class_name")
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Basic
    @Column(name = "price")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "emr_id")
    public Emr getEmr() {
        return emr;
    }

    public void setEmr(Emr emr) {
        this.emr = emr;
    }


}
