package com.qiaobei.hmp.modules.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "iai_loss_detail")
public class IaiLossDetail extends StatusEntity {
    private IaiLoss iaiLoss;//报损组表
    private MedicinePrivate medicine;//私有药品
    private Long companyId;
    private Date validityDate;//又有效期
    private Date createDate;//报损时间
    private Double bayingPrice;//单价
    private Double totalNumber = 0D;    //报损总数(默认为0)
    private String standard; //规格
    private String units;
    private Long emrId;  //emr id
    private Long emrMedId;  //emrMedId
    private IaiLossType lossType;// 报损类型 正常损坏(开药)  主动损耗(过期或手动添加)  零售开单的损耗

    private DetailStatus detailStatus;
    private String barCode;
    private Long warnLine;
    private Long iaiIntoDetailId;
    private Long subDoctorId;   //子帐号ID
    public IaiLossDetail() {
    }

    public IaiLossDetail(Date validityDate, Double totalNumber) {
        this.validityDate = validityDate;
        this.totalNumber = totalNumber;
    }

    public static IaiLossDetail copyIaiLossDetail(IaiIntoDetail detail) {
        IaiLossDetail iaiLossDetail = new IaiLossDetail();
        iaiLossDetail.setIaiIntoDetailId(detail.getId());
        iaiLossDetail.setMedicine(detail.getMedicine());
        iaiLossDetail.setCompanyId(detail.getCompanyId());
        iaiLossDetail.setValidityDate(detail.getValidityDate());
        iaiLossDetail.setBayingPrice(detail.getBayingPrice());
        iaiLossDetail.setCreateDate(new Date());
        iaiLossDetail.setWarnLine(detail.getWarnLine());
        iaiLossDetail.setBarCode(detail.getBarCode());
        iaiLossDetail.setTotalNumber(detail.getTotalNumber());
        //报损总数就是入库药品的剩余数量
        return iaiLossDetail;
    }

    @Column(name = "sub_doctor_id")
    public Long getSubDoctorId() {
        return subDoctorId;
    }

    public void setSubDoctorId(Long subDoctorId) {
        this.subDoctorId = subDoctorId;
    }

    @Column(name = "bar_code")
    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    @Column(name = "warn_line")
    public Long getWarnLine() {
        return warnLine;
    }

    public void setWarnLine(Long warnLine) {
        this.warnLine = warnLine;
    }

    @Column(name = "iai_into_detail_id")
    public Long getIaiIntoDetailId() {
        return iaiIntoDetailId;
    }

    public void setIaiIntoDetailId(Long iaiIntoDetailId) {
        this.iaiIntoDetailId = iaiIntoDetailId;
    }

    @Column(name = "emr_med_id")
    public Long getEmrMedId() {
        return emrMedId;
    }

    public void setEmrMedId(Long emrMedId) {
        this.emrMedId = emrMedId;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "iai_loss_id")
    public IaiLoss getIaiLoss() {
        return iaiLoss;
    }

    public void setIaiLoss(IaiLoss iaiLoss) {
        this.iaiLoss = iaiLoss;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "med_id")
    public MedicinePrivate getMedicine() {
        return medicine;
    }

    public void setMedicine(MedicinePrivate medicine) {
        this.medicine = medicine;
    }

    @Column(name = "company_id")
    public Long getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "validity_date", length = 10)
    public Date getValidityDate() {
        return this.validityDate;
    }

    public void setValidityDate(Date validityDate) {
        this.validityDate = validityDate;
    }

    @Column(name = "baying_price", precision = 22)
    public Double getBayingPrice() {
        return this.bayingPrice;
    }

    public void setBayingPrice(Double bayingPrice) {
        this.bayingPrice = bayingPrice;
    }

    @Column(name = "total_number", precision = 22)
    public Double getTotalNumber() {
        return this.totalNumber;
    }

    public void setTotalNumber(Double totalNumber) {
        this.totalNumber = totalNumber;
    }

    @Column(name = "standard")
    public String getStandard() {
        return this.standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    @Column(name = "units")
    public String getUnits() {
        return this.units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    @Column(name = "emr_id")
    public Long getEmrId() {
        return emrId;
    }

    public void setEmrId(Long emrId) {
        this.emrId = emrId;
    }

    @Column(name = "loss_type")
    public IaiLossType getLossType() {
        return lossType;
    }

    public void setLossType(IaiLossType lossType) {
        this.lossType = lossType;
    }

    @Column(name = "create_date")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Column(name = "loss_status")
    @Enumerated(value = EnumType.ORDINAL)
    public DetailStatus getDetailStatus() {
        return detailStatus;
    }

    public void setDetailStatus(DetailStatus detailStatus) {
        this.detailStatus = detailStatus;
    }

    public enum IaiLossType {
        NORMAL,//正常损耗
        ACTIVE, //主动添加 或者 生成损耗单
        RETAIL  //零售开单
    }

    public enum DetailStatus {
        NOT_SAVE, SAVE
    }
}