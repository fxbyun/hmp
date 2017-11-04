package com.qiaobei.hmp.modules.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * 药品规格换算
 */
@Entity
@Table(name = "conversion")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Conversion extends StatusEntity {

    private String medicineName;
    private Medicine.Unit fromUnit;
    private Medicine.Unit toUnit;
    private Integer rate;
    private Medicine medicine;


    public Conversion(Long id) {
        super(id);
    }

    public Conversion() {
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

    @Column(name = "rate", nullable = false, length = 10)
    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }
}
