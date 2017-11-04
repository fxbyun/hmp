package com.qiaobei.hmp.modules.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * 医生预约设置
 */
@Entity
@Table(name = "appoint_week_config")
@DynamicInsert(true)
@DynamicUpdate(true)
public class AppointWeekConfig extends IdEntity {


    public enum Weekday{
        Monday,Tuesday,Wednesday,Thursday,Friday,Saturday,Sunday
    }

    public enum OpenStatic{
        Close, Open,
    }

    public enum ProductStatic{
        Not,Had
    }

    private Doctor doctor;
    private Weekday weekday;
    private OpenStatic openStatic;
    private ProductStatic productStatic;

    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "doctor_id")
    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    @Column(name = "weekday", nullable = true)
    @Enumerated(value = EnumType.ORDINAL)
    public Weekday getWeekday() {
        return weekday;
    }

    public void setWeekday(Weekday weekday) {
        this.weekday = weekday;
    }

    @Column(name = "open_static", nullable = false)
    @Enumerated(value = EnumType.ORDINAL)
    public OpenStatic getOpenStatic() {
        return openStatic;
    }

    public void setOpenStatic(OpenStatic openStatic) {
        this.openStatic = openStatic;
    }

    @Column(name = "product_static", nullable = true)
    @Enumerated(value = EnumType.ORDINAL)
    public ProductStatic getProductStatic() {
        return productStatic;
    }

    public void setProductStatic(ProductStatic productStatic) {
        this.productStatic = productStatic;
    }

}
