package com.qiaobei.hmp.modules.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.collect.Lists;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

/**
 * 药品
 */
@Entity
@Table(name = "medicine")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Medicine extends IdEntity {

    private String sn;
    private String name;
    private String specification;
    private Unit unit = Unit.bxs;
    private Type type;
    private Company defaultCompany;
    private String defaultCompanyName;
    private List<Company> companyList = Lists.newArrayList(); // 有序的关联对象集合
    private List<DoctorMedicine> doctorMedicineList = Lists.newArrayList();
    private List<MedicineCount> medicineCountList = Lists.newArrayList();
    private String unitLabel;
    private boolean used;
    private String usage;
    private String useMode;
    private String useQty;
    private Unit useUnit;
    private String usingTime;
    private String useTimes;
    private Boolean usageFlag;
    private String category;
    private String helpCode;
    private int tmpCountSize;
    private String standard;
    private String rate;
    private List<MedicinePrivate> medicinePrivateList;

    public Medicine() {
    }

    public Medicine(Long id, String name) {
        super(id);
        this.name = name;
    }

    public Medicine(Long id) {
        super(id);
    }

    public Medicine(Type type) {
        this.type = type;
        if (Type.Western == type) this.unit = Unit.bxs;
        else this.unit = Unit.g;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH}, optional = true)
    @JoinColumn(name = "default_company_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public Company getDefaultCompany() {
        return defaultCompany;
    }

    public void setDefaultCompany(Company defaultCompany) {
        this.defaultCompany = defaultCompany;
    }

    @Column(name = "default_company_name", length = 100)
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public String getDefaultCompanyName() {
        return defaultCompanyName;
    }

    public void setDefaultCompanyName(String defaultCompanyName) {
        this.defaultCompanyName = defaultCompanyName;
    }

    // 多对多定义
    @ManyToMany
    @JoinTable(name = "medicine_company", joinColumns = {@JoinColumn(name = "medicine_id")},
            inverseJoinColumns = {@JoinColumn(name = "company_id")})
    // Fecth策略定义
//    @Fetch(FetchMode.SELECT)
    // 集合按id排序
    @OrderBy("id ASC")
    @JsonIgnore
    // 缓存策略
//    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public List<Company> getCompanyList() {
        return companyList;
    }

    public void setCompanyList(List<Company> companyList) {
        this.companyList = companyList;
    }

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "medicine")
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public List<DoctorMedicine> getDoctorMedicineList() {
        return doctorMedicineList;
    }

    public void setDoctorMedicineList(List<DoctorMedicine> doctorMedicineList) {
        this.doctorMedicineList = doctorMedicineList;
    }

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "medicine")
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public List<MedicineCount> getMedicineCountList() {
        return medicineCountList;
    }

    public void setMedicineCountList(List<MedicineCount> medicineCountList) {
        this.medicineCountList = medicineCountList;
    }

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "medicine")
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public List<MedicinePrivate> getMedicinePrivateList() {
        return medicinePrivateList;
    }

    public void setMedicinePrivateList(List<MedicinePrivate> medicinePrivateList) {
        this.medicinePrivateList = medicinePrivateList;
    }

    @Column(name = "type", nullable = false)
    @Enumerated(value = EnumType.ORDINAL)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Column(name = "sn", length = 30)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    @Column(name = "name", nullable = false, length = 50)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "specification", length = 50)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    @Column(name = "unit", nullable = false)
    @Enumerated(value = EnumType.STRING)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    @Column(name = "usage", length = 200)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    @Column(name = "use_mode", length = 20)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public String getUseMode() {
        return useMode;
    }

    public void setUseMode(String useMode) {
        this.useMode = useMode;
    }

    @Column(name = "use_qty", length = 20)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public String getUseQty() {
        return useQty;
    }

    public void setUseQty(String useQty) {
        this.useQty = useQty;
    }

    @Column(name = "use_unit", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @Enumerated(value = EnumType.STRING)
    public Unit getUseUnit() {
        return useUnit;
    }

    public void setUseUnit(Unit useUnit) {
        this.useUnit = useUnit;
    }

    @Column(name = "using_time", length = 20)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public String getUsingTime() {
        return usingTime;
    }

    public void setUsingTime(String usingTime) {
        this.usingTime = usingTime;
    }

    @Column(name = "use_times")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public String getUseTimes() {
        return useTimes;
    }

    public void setUseTimes(String useTimes) {
        this.useTimes = useTimes;
    }

    @Transient
    public String getUnitLabel() {
        return unitLabel;
    }

    public void setUnitLabel(String unitLabel) {
        this.unitLabel = unitLabel;
    }

    @Transient
    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    @Column(name = "usage_flag")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public Boolean getUsageFlag() {
        return usageFlag;
    }

    public void setUsageFlag(Boolean usageFlag) {
        this.usageFlag = usageFlag;
    }

    @Column(name = "category")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Column(name = "help_code", nullable = false, length = 50)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public String getHelpCode() {
        return helpCode;
    }

    public void setHelpCode(String helpCode) {
        this.helpCode = helpCode;
    }

    @Transient
    public int getTmpCountSize() {
        return tmpCountSize;
    }

    public void setTmpCountSize(int tmpCountSize) {
        this.tmpCountSize = tmpCountSize;
    }

    @Column(name = "standard", length = 30)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    @Column(name = "rate", length = 20)
    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public enum Type {
        Western,            //中药
        Chinese,            //西药
        ChineseTherapy;     //中医理疗
    }

    /**
     * bxs:盒 btl:瓶 pkg:包 pcs:支 grs:颗 g:克 mg:毫克 ml:毫升 oth:其它 acupoint:穴位 part：部位 therapyUnit:（中医理疗）单位 ge：个
     * skill:手法
     */
    public enum Unit {
        bxs, btl, pkg, grs, pcs, g, mg, ml, needle, oth, acupoint, part, therapyUnit, ge, skill
    }
}
