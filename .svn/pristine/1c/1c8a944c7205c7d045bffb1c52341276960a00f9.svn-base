package com.qiaobei.hmp.modules.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * 药品规格换算申请
 */
@Entity
@Table(name = "conversion_apply")
@DynamicInsert(true)
@DynamicUpdate(true)
public class ConversionApply extends StatusEntity {

    private String medicineName;
    private Medicine.Unit fromUnit;
    private Medicine.Unit toUnit;
    private Integer rate;
    private String applyBy;
    private Long applyById;
    private Date applyOn;
    private String verifyBy;
    private Long verifyById;
    private Date verifyOn;

    private Conversion conversion;
    private Medicine medicine;

    public ConversionApply(Long id) {
        super(id);
    }

    public ConversionApply() {
    }

    @ManyToOne(cascade = {CascadeType.REFRESH}, optional = true)
    @JoinColumn(name = "conversion_id")
    public Conversion getConversion() {
        return conversion;
    }

    public void setConversion(Conversion conversion) {
        this.conversion = conversion;
        this.setMedicine(conversion.getMedicine());
        this.fromUnit = conversion.getFromUnit();
        this.toUnit = conversion.getToUnit();
    }

    @ManyToOne(cascade = {CascadeType.REFRESH}, optional = true)
    @JoinColumn(name = "medicine_id")
    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
        this.medicineName = medicine.getName();
    }

    @Column(name = "medicine_name", nullable = false, length = 50)
    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    @Column(name = "from_unit", nullable = false)
    @Enumerated(value = EnumType.STRING)
    public Medicine.Unit getFromUnit() {
        return fromUnit;
    }

    public void setFromUnit(Medicine.Unit fromUnit) {
        this.fromUnit = fromUnit;
    }

    @Column(name = "to_unit", nullable = false)
    @Enumerated(value = EnumType.STRING)
    public Medicine.Unit getToUnit() {
        return toUnit;
    }

    public void setToUnit(Medicine.Unit toUnit) {
        this.toUnit = toUnit;
    }

    @Column(name = "apply_by", length = 20)
    public String getApplyBy() {
        return applyBy;
    }

    public void setApplyBy(Doctor doctor) {
        this.applyById = doctor.getId();
        this.applyBy = doctor.getName();
    }

    public void setApplyBy(String applyBy) {
        this.applyBy = applyBy;
    }

    @Column(name = "apply_by_id")
    public Long getApplyById() {
        return applyById;
    }

    public void setApplyById(Long applyById) {
        this.applyById = applyById;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "apply_on", length = 20)
    public Date getApplyOn() {
        return applyOn;
    }

    public void setApplyOn(Date applyOn) {
        this.applyOn = applyOn;
    }

    @Column(name = "rate", nullable = false, length = 10)
    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    @Column(name = "verify_by")
    public String getVerifyBy() {
        return verifyBy;
    }

    public void setVerifyBy(Doctor doctor) {
        this.verifyById = doctor.getId();
        this.verifyBy = doctor.getName();
    }

    public void setVerifyBy(String verifyBy) {
        this.verifyBy = verifyBy;
    }

    @Column(name = "verify_by_id")
    public Long getVerifyById() {
        return verifyById;
    }

    public void setVerifyById(Long verifyById) {
        this.verifyById = verifyById;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "verify_on")
    public Date getVerifyOn() {
        return verifyOn;
    }

    public void setVerifyOn(Date verifyOn) {
        this.verifyOn = verifyOn;
    }
}
