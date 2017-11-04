package com.qiaobei.hmp.modules.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * Created by zw on 2016/5/27 0027.
 */
@Entity
@Table(name = "medicine_count")
@DynamicInsert()
@DynamicUpdate()
public class MedicineCount extends IdEntity {
    private Medicine medicine;
    private Doctor doctor;
    private String diagosisName;
    private int countSize;

    public MedicineCount() {

    }

    public MedicineCount(Long id) {
        super(id);
    }


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "medicine_id")
    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "doctor_id")
    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getDiagosisName() {
        return diagosisName;
    }

    public void setDiagosisName(String diagosisName) {
        this.diagosisName = diagosisName;
    }

    public int getCountSize() {
        return countSize;
    }

    public void setCountSize(int countSize) {
        this.countSize = countSize;
    }
}
