package com.qiaobei.hmp.modules.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 药方分类
 */
@Entity
@Table(name = "prescription_category")
@DynamicInsert()
@DynamicUpdate()
public class PrescriptionCategory extends IdEntity {

    private Long doctorId;
    private String doctorName;
    private String name;
    private String remark;

    public PrescriptionCategory() {
    }

    public PrescriptionCategory(Long id) {
        super(id);
    }

    @Column(name = "name", nullable = false, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "remark", length = 100)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(name = "doctor_name", length = 20)
    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    @Column(name = "doctor_id")
    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public void setDoctor(Doctor doctor) {
        if (doctor != null) {
            this.doctorId = doctor.getId();
            this.doctorName = doctor.getName();
        }
    }
}
