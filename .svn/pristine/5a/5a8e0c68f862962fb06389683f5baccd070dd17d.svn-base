package com.qiaobei.hmp.modules.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "iai_into_detail")
public class IaiIntoDetail extends IdEntity {



    private IaiInto iaiInto;//主表
    private MedicinePrivate medicine;//私有药品
    private Date validityDate;//有效期
    private Long companyId;//供应商ID
    private String barCode;//条码
    private Double bayingPrice;//进货价
    private Double totalNumber;//产品数量
    private Long warnLine; //预警线
    private DetailStatus status;
    private DetailType detailType;
    private Double incomeQuantity;      //进货量
    public IaiIntoDetail() {
    }

    //复制用
    public static IaiIntoDetail copyIaiIntoDetail(IaiIntoDetail detail) {
        IaiIntoDetail detailTemp = new IaiIntoDetail();
        detailTemp.iaiInto = detail.getIaiInto();
        detailTemp.medicine = detail.getMedicine();
        detailTemp.validityDate = detail.getValidityDate();
        detailTemp.companyId = detail.getCompanyId();
        detailTemp.barCode = detail.getBarCode();
        detailTemp.bayingPrice = detail.getBayingPrice();
        detailTemp.totalNumber = detail.getTotalNumber();
        detailTemp.warnLine = detail.getWarnLine();
        detailTemp.incomeQuantity=detail.getIncomeQuantity();
        return detailTemp;
    }

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "iai_into_id")
    public IaiInto getIaiInto() {
        return iaiInto;
    }

    public void setIaiInto(IaiInto iaiInto) {
        this.iaiInto = iaiInto;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "med_id")
    public MedicinePrivate getMedicine() {
        return medicine;
    }

    public void setMedicine(MedicinePrivate medicine) {
        this.medicine = medicine;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "validity_date", length = 10)
    public Date getValidityDate() {
        return this.validityDate;
    }

    public void setValidityDate(Date validityDate) {
        this.validityDate = validityDate;
    }

    @Column(name = "company_id")
    public Long getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    @Column(name = "bar_code")
    public String getBarCode() {
        return this.barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    @Column(name = "baying_price", precision = 22)
    public Double getBayingPrice() {
        return this.bayingPrice;
    }

    public void setBayingPrice(Double bayingPrice) {
        this.bayingPrice = bayingPrice;
    }

    @Column(name = "total_number")
    public Double getTotalNumber() {
        return this.totalNumber;
    }

    public void setTotalNumber(Double totalNumber) {
        this.totalNumber = totalNumber;
    }

    @Column(name = "warn_line")
    public Long getWarnLine() {
        return warnLine;
    }

    public void setWarnLine(Long warnLine) {
        this.warnLine = warnLine;
    }

    @Column(name = "status")
    @Enumerated(value = EnumType.ORDINAL)
    public DetailStatus getStatus() {
        return status;
    }

    public void setStatus(DetailStatus status) {
        this.status = status;
    }

    @Column(name = "detail_type")
    @Enumerated(value = EnumType.ORDINAL)
    public DetailType getDetailType() {
        return detailType;
    }

    public void setDetailType(DetailType detailType) {
        this.detailType = detailType;
    }

    @Column(name = "income_quantity")
    public Double getIncomeQuantity() {
        return incomeQuantity;
    }

    public void setIncomeQuantity(Double incomeQuantity) {
        this.incomeQuantity = incomeQuantity;
    }

    public enum DetailType {
        STORAGE,                //入库单
        REPLENISH               //智能补货单
    }

    public enum DetailStatus {
        NOT_SAVE, SAVE
    }
}