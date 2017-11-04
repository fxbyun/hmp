package com.qiaobei.hmp.modules.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * 药方库项
 */
@Entity
@Table(name = "prescription_lib_item")
public class PrescriptionLibItem extends IdEntity {

    @JsonIgnore
    private PrescriptionLib prescriptionLib;
    private Long medicineId;
    private String medicineName;
    private Medicine.Type medicineType;
    private Long companyId;
    private String companyName;
    private Double qty;
    private Double rate;
    private Medicine.Unit unit;
    private String unitLabel;
    private Double copies;
    private String useMode;
    private Boolean hasUsage;

    public PrescriptionLibItem() {
    }

    public PrescriptionLibItem(Long id) {
        super(id);
    }

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "prescription_lib_id")
    public PrescriptionLib getPrescriptionLib() {
        return prescriptionLib;
    }

    public void setPrescriptionLib(PrescriptionLib prescriptionLib) {
        this.prescriptionLib = prescriptionLib;
    }

    @Column(name = "medicine_name", length = 50)
    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    @Column(name = "medicine_id")
    public Long getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Long medicineId) {
        this.medicineId = medicineId;
    }

    @Column(name = "medicine_type", length = 1)
    public Medicine.Type getMedicineType() {
        return medicineType;
    }

    public void setMedicineType(Medicine.Type medicineType) {
        this.medicineType = medicineType;
    }

    @Column(name = "company_id")
    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    @Column(name = "company_name", length = 50)
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Column(name = "qty")
    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

    @Column(name = "rate")
    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    @Column(name = "unit", length = 10)
    public Medicine.Unit getUnit() {
        return unit;
    }

    public void setUnit(Medicine.Unit unit) {
        this.unit = unit;
    }

    @Transient
    public String getUnitLabel() {
        return unitLabel;
    }

    public void setUnitLabel(String unitLabel) {
        this.unitLabel = unitLabel;
    }

    @Column(name = "has_usage")
    public Boolean getHasUsage() {
        return hasUsage;
    }

    public void setHasUsage(Boolean hasUsage) {
        this.hasUsage = hasUsage;
    }

    @Column(name = "use_mode")
    public String getUseMode() {
        return useMode;
    }

    public void setUseMode(String useMode) {
        this.useMode = useMode;
    }

    @Column(name = "copies")
    public Double getCopies() {
        return copies;
    }

    public void setCopies(Double copies) {
        this.copies = copies;
    }
}
