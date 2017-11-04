package com.qiaobei.hmp.modules.entity;

import com.google.common.collect.Lists;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * 药方
 */
@Entity
@Table(name = "prescription")
public class Prescription extends StatusEntity {

    private Long doctorId;
    private String doctorName;
    private Long categoryId;
    private String categoryName;
    private String name;
    private Medicine.Type medicineType;
    private String remark;
    private Date createOn;
    private String diagnosis;
    private List<PrescriptionItem> prescriptionItemList = Lists.newArrayList();

    public Prescription() {
    }

    public Prescription(Long id) {
        super(id);
    }

    public Prescription(Medicine.Type type) {
        this.medicineType = type;
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

    @Column(name = "name", length = 50)
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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_on", nullable = false)
    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    @Column(name = "medicine_type", nullable = false)
    @Enumerated(value = EnumType.ORDINAL)
    public Medicine.Type getMedicineType() {
        return medicineType;
    }

    public void setMedicineType(Medicine.Type type) {
        this.medicineType = type;
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "prescription")
    public List<PrescriptionItem> getPrescriptionItemList() {
        return prescriptionItemList;
    }

    public void setPrescriptionItemList(List<PrescriptionItem> prescriptionItemList) {
        this.prescriptionItemList = prescriptionItemList;
    }

    @Column(name = "doctor_name")
    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    @Column(name = "category_id")
    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    @Column(name = "category_name")
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Column(name = "diagnosis")
    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }
}
