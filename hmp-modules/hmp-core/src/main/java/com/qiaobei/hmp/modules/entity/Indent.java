package com.qiaobei.hmp.modules.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * 订单
 */
@Entity
@Table(name = "indent")
@DynamicInsert()
@DynamicUpdate()
public class Indent extends IdEntity {

    private static final long serialVersionUID = -4979486566492446817L;
    private Doctor user;
    private Medicine medicine;
    private String company;
    private String quantity;
    private Date createTime;
    private double price;

    public Indent() {
    }

    public Indent(Long id) {
        super(id);
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "doctor_id")
    public Doctor getUser() {
        return user;
    }

    public void setUser(Doctor user) {
        this.user = user;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "medicine_id")
    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    @Column(name = "company", length = 50)
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Column(name = "quantity", length = 10)
    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", length = 20)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "price")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
