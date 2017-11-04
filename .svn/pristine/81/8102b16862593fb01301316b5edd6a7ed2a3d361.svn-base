package com.qiaobei.hmp.modules.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

/**
 * 病历药方项
 */
@Entity
@Table(name = "emr_medicine")
public class EmrMedicine extends StatusEntity {

    @JsonIgnore
    private Emr emr;
    @JsonIgnore
    private Medicine medicine;
    @JsonIgnore
    private Company company;
    private String medicineName;
    private String companyName;
    private Medicine.Type medicineType;
    private Double qty;
    private Medicine.Unit unit;
    private Medicine.Unit tjUnit;//统计单位
    private Double rate;
    @JsonIgnore
    private Double realQty;
    @JsonIgnore
    private Medicine.Unit realUnit;
    private Long doctorId;
    private String doctorName;
    @JsonIgnore
    private Date createOn;
    private String unitLabel;
    private Double copies;
    private String useMode;
    private Boolean hasUsage;

    private String useTimes;
    private String usingTime;
    private String useQty; //每次吃多少的
    private String useUnit;//每次吃的单位  如  片  盒 瓶
    private String groupIndex;//分组编号 默认是空 为未分组
    private String multiplexTag;//重复药品的区分ID后缀  比如 123s
    private String specialInstructions;//药品的特殊说明
    private String standard;
    private Double price;
    private Double unitPrice;
    private Long medicinePrivateId;

    public EmrMedicine() {
    }

    public EmrMedicine(Long id) {
        super(id);
    }

    @ManyToOne(optional = true)
    @JoinColumn(name = "emr_id")
    public Emr getEmr() {
        return emr;
    }

    public void setEmr(Emr emr) {
        this.emr = emr;
    }

    @Column(name = "tj_unit")
    public Medicine.Unit getTjUnit() {
        return tjUnit;
    }

    public void setTjUnit(Medicine.Unit tjUnit) {
        this.tjUnit = tjUnit;
    }

    @Column(name = "unit_price")
    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Column(name = "medicine_private_id")
    public Long getMedicinePrivateId() {
        return medicinePrivateId;
    }

    public void setMedicinePrivateId(Long medicinePrivateId) {
        this.medicinePrivateId = medicinePrivateId;
    }

    @Column(name = "price")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @ManyToOne(cascade = {CascadeType.REFRESH}, optional = true)
    @JoinColumn(name = "company_id")
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
        this.companyName = company.getName();
    }

    @ManyToOne(cascade = CascadeType.REFRESH, optional = true)
    @JoinColumn(name = "medicine_id")
    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
        this.medicineName = medicine.getName();
        this.medicineType = medicine.getType();
    }

    @Transient
    public Long getMedicineId() {
        return medicine.getId();
    }

    @Transient
    public Long getCompanyId() {
        return company.getId();
    }

    @Column(name = "company_name", length = 255)
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Column(name = "medicine_name", length = 50)
    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    @Column(name = "medicine_type", nullable = false)
    @Enumerated(value = EnumType.ORDINAL)
    public Medicine.Type getMedicineType() {
        return medicineType;
    }

    public void setMedicineType(Medicine.Type type) {
        this.medicineType = type;
    }

    @Column(name = "qty")
    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

    @Column(name = "unit", nullable = false)
    @Enumerated(value = EnumType.STRING)
    public Medicine.Unit getUnit() {
        return unit;
    }

    public void setUnit(Medicine.Unit unit) {
        this.unit = unit;
    }

    @Column(name = "real_unit", nullable = false)
    @Enumerated(value = EnumType.STRING)
    public Medicine.Unit getRealUnit() {
        return realUnit;
    }

    public void setRealUnit(Medicine.Unit unit) {
        this.realUnit = unit;
    }

    @Column(name = "doctor_id")
    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    @Column(name = "doctor_name", length = 20)
    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_on", nullable = false)
    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    @Column(name = "real_qty")
    public Double getRealQty() {
        return realQty;
    }

    public void setRealQty(Double realQty) {
        this.realQty = realQty;
    }

    @Column(name = "rate")
    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
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

    @Transient
    public String getUnitLabel() {
        return unitLabel;
    }

    public void setUnitLabel(String unitLabel) {
        this.unitLabel = unitLabel;
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

    @Column(name = "special_instructions", length = 200)
    public String getSpecialInstructions() {
        return specialInstructions;
    }

    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }

    @Column(name = "standard", length = 30)
    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }
}
