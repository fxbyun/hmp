package com.qiaobei.hmp.test;

import java.util.Date;

/**
 * 药方
 */

public class PrescriptionTmp {

    private Long id;
    private Long doctorId;
    private String doctorName;
    private Long categoryId;
    private String categoryName;
    private String name;
    private int medicineType;
    private String remark;
    private Date createOn;
    private String diagnosis;
    private int status;

    @Override
    public String toString() {
        return "PrescriptionTmp{" +
                "id=" + id +
                ", doctorId=" + doctorId +
                ", doctorName='" + doctorName + '\'' +
                ", categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", name='" + name + '\'' +
                ", medicineType=" + medicineType +
                ", remark='" + remark + '\'' +
                ", createOn=" + createOn +
                ", diagnosis='" + diagnosis + '\'' +
                ", status=" + status +
                '}';
    }

    public PrescriptionTmp() {
    }

    public PrescriptionTmp(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMedicineType() {
        return medicineType;
    }

    public void setMedicineType(int medicineType) {
        this.medicineType = medicineType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }
}
