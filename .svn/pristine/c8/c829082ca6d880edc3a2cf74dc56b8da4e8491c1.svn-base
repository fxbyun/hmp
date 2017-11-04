package com.qiaobei.hmp.modules.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * 药方项
 */
@Entity
@Table(name = "prescription_item")
public class PrescriptionItem extends IdEntity {

    @JsonIgnore
    private Prescription prescription;
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
    private String useTimes;
    private String usingTime;
    private String useQty;     //每次吃多少的
    private String useUnit;    //每次吃的单位  如  片  盒 瓶
    private Boolean hasUsage;

    private String groupIndex;//分组编号 默认是空 为未分组
    private String multiplexTag;//重复药品的区分ID后缀  比如 123s

    private String specialInstructions;//药品的特殊说明
    private String standard;//药品规格

    private Double price;  // 根据单价计算出的总价
    private Medicine.Unit tjUnit; //  统计单位  如 grs
    private Double unitPrice;//物品价单
    private Long medicinePrivateId; //私有药品ID


    public PrescriptionItem() {
    }

    public PrescriptionItem(Long id) {
        super(id);
    }

    @Transient
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Transient
    public Medicine.Unit getTjUnit() {
        return tjUnit;
    }

    public void setTjUnit(Medicine.Unit tjUnit) {
        this.tjUnit = tjUnit;
    }

    @Transient
    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Transient
    public Long getMedicinePrivateId() {
        return medicinePrivateId;
    }

    public void setMedicinePrivateId(Long medicinePrivateId) {
        this.medicinePrivateId = medicinePrivateId;
    }

    @Column(name = "group_index")
    public String getGroupIndex() {
        return groupIndex;
    }

    public void setGroupIndex(String groupIndex) {
        this.groupIndex = groupIndex;
    }

    @Column(name = "multiplex_tag")
    public String getMultiplexTag() {
        return multiplexTag;
    }

    public void setMultiplexTag(String multiplexTag) {
        this.multiplexTag = multiplexTag;
    }

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "prescription_id")
    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
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

    @Column(name = "use_times")
    public String getUseTimes() {
        return useTimes;
    }

    public void setUseTimes(String useTimes) {
        this.useTimes = useTimes;
    }

    @Column(name = "using_time")
    public String getUsingTime() {
        return usingTime;
    }

    public void setUsingTime(String usingTime) {
        this.usingTime = usingTime;
    }

    @Column(name = "use_qty")
    public String getUseQty() {
        return useQty;
    }

    public void setUseQty(String useQty) {
        this.useQty = useQty;
    }

    @Column(name = "use_unit")
    public String getUseUnit() {
        return useUnit;
    }

    public void setUseUnit(String useUnit) {
        this.useUnit = useUnit;
    }

    @Column(name = "copies")
    public Double getCopies() {
        return copies;
    }

    public void setCopies(Double copies) {
        this.copies = copies;
    }

    @Transient
    public String getUnitLabel() {
        return unitLabel;
    }

    public void setUnitLabel(String unitLabel) {
        this.unitLabel = unitLabel;
    }

    @Column(name = "special_instructions", length = 10)
    public String getSpecialInstructions() {
        return specialInstructions;
    }

    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }

    @Column(name = "standard", length = 10)
    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }
}
