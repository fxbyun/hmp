package com.qiaobei.hmp.modules.entity;

import com.qiaobei.hmp.modules.support.DecimalCalculate;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springside.modules.utils.MetaData;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2016/12/28 0028.
 */
@Entity
@Table(name = "retail_medicine")
@DynamicInsert()
@DynamicUpdate()
public class RetailMedicine extends IdEntity {
    private MedicinePrivate medicinePrivate;    //私有药品id
    private Retail retail;                      //零售主表
    private Double qty;
    private Medicine.Unit unit = Medicine.Unit.bxs;
    private Double rate = 1D;
    private Date createTime;                    //创建时间
    private Double copies;
    private String standard;
    private Double retailPrice = 0.00D;                //零售价格,默认是药品价格
    private Status status;
    private String barCode;
    private Double totalPrice = 0.00D;                  //一共多少钱


    private Date validityDate;                  //该零售药品的有效期

    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "medicine_private_id")
    public MedicinePrivate getMedicinePrivate() {
        return medicinePrivate;
    }

    public void setMedicinePrivate(MedicinePrivate medicinePrivate) {
        this.medicinePrivate = medicinePrivate;
    }

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "retail_id")
    public Retail getRetail() {
        return retail;
    }

    public void setRetail(Retail retail) {
        this.retail = retail;
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

    @Column(name = "create_time", length = 7)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "copies")
    public Double getCopies() {
        return copies;
    }

    public void setCopies(Double copies) {
        this.copies = copies;
    }

    @Column(name = "standard")
    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    @Column(name = "retail_price")
    public Double getRetailPrice() {
        if (null == this.retailPrice) {
            this.retailPrice = 0.00D;
        }
        return retailPrice;
    }

    public void setRetailPrice(Double retailPrice) {
        this.retailPrice = retailPrice;
    }

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Column(name = "bar_code")
    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    @Column(name = "unit")
    public Medicine.Unit getUnit() {
        return unit;
    }

    public void setUnit(Medicine.Unit unit) {
        this.unit = unit;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "validity_date")
    public Date getValidityDate() {
        return validityDate;
    }

    public void setValidityDate(Date validityDate) {
        this.validityDate = validityDate;
    }

    @Column(name = "total_price")
    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double qtyUnitStr() {
        if (this.unit != this.medicinePrivate.getUnit() && null != this.rate && this.rate > 0) {
            return DecimalCalculate.roundDown(this.qty / this.rate, 2);
        } else {
            return this.qty;
        }
    }

    public enum Status {
        @MetaData(value = "未保存")
        Not_Save,
        @MetaData(value = "保存")
        Save
    }
}
