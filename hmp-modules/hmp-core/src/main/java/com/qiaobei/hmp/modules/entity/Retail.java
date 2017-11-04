package com.qiaobei.hmp.modules.entity;


import com.google.common.collect.Lists;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springside.modules.utils.MetaData;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/12/28 0028.
 */
@Entity
@Table(name = "retail")
@DynamicInsert()
@DynamicUpdate()
public class Retail extends IdEntity {
    private Patient patient;     //患者id
    private String orderId;     //订单id
    private Date chargeTime;    //收费时间
    private Double realCost;    //实收金额
    private Boolean ifBinding;  //是否绑定了用户信息
    private Double allMedCost;  //药品总金额
    private Pharmacist pharmacist;  //收银员id


    private Doctor doctor;          //主治医生

    private Retail_Status retailStatus;     //看注解
    private Charge_Status ChargeStatus;     //看注解

    private List<RetailMedicine> retailMedicineList = Lists.newArrayList();

    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "patient_id")
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Column(name = "order_id")
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Column(name = "charge_time", length = 7)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getChargeTime() {
        return chargeTime;
    }

    public void setChargeTime(Date chargeTime) {
        this.chargeTime = chargeTime;
    }

    @Column(name = "real_cost")
    public Double getRealCost() {
        if (null == realCost) {
            return 0.00D;
        } else {
            return realCost;
        }
    }

    public void setRealCost(Double realCost) {
        this.realCost = realCost;
    }

    @Column(name = "if_Binding")
    public Boolean getIfBinding() {
        return ifBinding;
    }

    public void setIfBinding(Boolean ifBinding) {
        this.ifBinding = ifBinding;
    }

    @Column(name = "all_med_cost")
    public Double getAllMedCost() {
        if (null == allMedCost) {
            return 0.00D;
        } else {
            return allMedCost;
        }
    }

    public void setAllMedCost(Double allMedCost) {
        this.allMedCost = allMedCost;
    }

    @Column(name = "retail_status")
    @Enumerated(value = EnumType.STRING)
    public Retail_Status getRetailStatus() {
        return retailStatus;
    }

    public void setRetailStatus(Retail_Status retailStatus) {
        this.retailStatus = retailStatus;
    }

    @Column(name = "charge_status")
    @Enumerated(value = EnumType.STRING)
    public Charge_Status getChargeStatus() {
        return ChargeStatus;
    }

    public void setChargeStatus(Charge_Status chargeStatus) {
        ChargeStatus = chargeStatus;
    }

    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "pharmacist_id")
    public Pharmacist getPharmacist() {
        return pharmacist;
    }

    public void setPharmacist(Pharmacist pharmacist) {
        this.pharmacist = pharmacist;
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "retail")
    public List<RetailMedicine> getRetailMedicineList() {
        return retailMedicineList;
    }

    public void setRetailMedicineList(List<RetailMedicine> retailMedicineList) {
        this.retailMedicineList = retailMedicineList;
    }

    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "doctor_id")
    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public enum Retail_Status {
        @MetaData(value = "未删除")
        Not_Delete,
        @MetaData(value = "已删除")
        Delete,
        @MetaData(value = "保存")
        Save,
        @MetaData(value = "未保存")
        Not_Save
    }

    public enum Charge_Status {
        @MetaData(value = "未收费")
        Not_Charge,
        @MetaData(value = "已收费")
        Charge
    }
}
