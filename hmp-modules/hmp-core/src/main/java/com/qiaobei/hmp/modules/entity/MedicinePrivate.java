package com.qiaobei.hmp.modules.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.qiaobei.hmp.modules.entity.Medicine.Type;
import com.qiaobei.hmp.modules.entity.Medicine.Unit;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * 医生私有药品
 */
@Entity
@Table(name = "medicine_private")
@DynamicInsert(true)
@DynamicUpdate(true)
public class MedicinePrivate extends IdEntity {

    //public enum Type {
    //    Western, Chinese;
    //}

    /**
     * bxs:盒 btl:瓶 pkg:包 pcs:支 grs:颗 g:克 mg:毫克 ml:毫升 oth:其它
     */
    //public enum Unit {
    //    bxs, btl, pkg, grs, pcs, g, mg, ml, oth;
    //}

    private Medicine medicine;
    private Doctor doctor;
    private String sn;
    private String name;
    private String specification;
    private Unit unit = Unit.bxs;
    private Type type;
    private Company defaultCompany;
    private String defaultCompanyName;
    //private List<MedicineCount> medicineCountList = Lists.newArrayList();
    private String unitLabel;
    private boolean used;

    private String usage;
    private String useMode;
    private String useQty;
    private String realQty;         //数量/单位 每次用量Qty
    private Unit realUnit;          //数量/单位
    private Unit useUnit;
    private String usingTime;
    private String useTimes;
    private Boolean usageFlag;
    private String category;
    private String helpCode;
    private String standard;
    private String info;
    private Double rate;
    //是否被库存管理
    private HaveManager haveManager;

    //零售价
    private Double price;
    //条码
    private String barCode;

    public MedicinePrivate() {
    }

    public MedicinePrivate(Long id) {
        super(id);
    }

    public MedicinePrivate(Type type) {
        this.type = type;
        if (Type.Western == type) this.unit = Unit.bxs;
        else this.unit = Unit.g;
    }

    @Column(name = "bar_code")
    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    @Column(name = "info")
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Column(name = "real_qty")
    public String getRealQty() {
        return realQty;
    }

    public void setRealQty(String realQty) {
        this.realQty = realQty;
    }

    @Column(name = "real_unit", nullable = false)
    @Enumerated(value = EnumType.STRING)
    public Unit getRealUnit() {
        return realUnit;
    }

    public void setRealUnit(Unit realUnit) {
        this.realUnit = realUnit;
    }

    @Transient
    public MedicinePrivate medToMedPriveate(Medicine medicine) {
        this.setMedicine(medicine);
        this.setSn(medicine.getSn());
        this.setName(medicine.getName());
        this.setSpecification(medicine.getSpecification());
        this.setUnit(medicine.getUnit());
        this.setType(medicine.getType());
        this.setDefaultCompany(medicine.getDefaultCompany());
        this.setDefaultCompanyName(medicine.getDefaultCompanyName());
        //this.setMedicineCountList(medicine.getMedicineCountList());
        this.setUnitLabel(medicine.getUnitLabel());
        this.setUsed(medicine.isUsed());
        this.setUsage(medicine.getUsage());
        this.setUseMode(medicine.getUseMode());
        this.setUseQty(medicine.getUseQty());
        this.setUseUnit(medicine.getUseUnit());
        this.setUsingTime(medicine.getUsingTime());
        this.setUseTimes(medicine.getUseTimes());
        this.setUsageFlag(medicine.getUsageFlag());
        this.setCategory(medicine.getCategory());
        this.setHelpCode(medicine.getHelpCode());
        this.setStandard(medicine.getStandard());
        return this;
    }

    @Transient
    public MedicinePrivate medPriveateToMedPriveate(MedicinePrivate medicine) {
        this.setMedicine(medicine.getMedicine());
        this.setSn(medicine.getSn());
        this.setName(medicine.getName());
        this.setSpecification(medicine.getSpecification());
        this.setUnit(medicine.getUnit());
        this.setType(medicine.getType());
        this.setDefaultCompany(medicine.getDefaultCompany());
        this.setDefaultCompanyName(medicine.getDefaultCompanyName());
        //this.setMedicineCountList(medicine.getMedicineCountList());
        this.setUnitLabel(medicine.getUnitLabel());
        this.setUsed(medicine.isUsed());
        this.setUsage(medicine.getUsage());
        this.setUseMode(medicine.getUseMode());
        this.setUseQty(medicine.getUseQty());
        this.setUseUnit(medicine.getUseUnit());
        this.setUsingTime(medicine.getUsingTime());
        this.setUseTimes(medicine.getUseTimes());
        this.setUsageFlag(medicine.getUsageFlag());
        this.setCategory(medicine.getCategory());
        this.setHelpCode(medicine.getHelpCode());
        this.setStandard(medicine.getStandard());
        this.setPrice(medicine.getPrice());
        this.setRate(medicine.getRate());
        return this;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "medicine_id")
    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "doctor_id")
    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "default_company_id")
    public Company getDefaultCompany() {
        return defaultCompany;
    }

    public void setDefaultCompany(Company defaultCompany) {
        this.defaultCompany = defaultCompany;
    }

    @Column(name = "default_company_name", length = 100)
    @JsonIgnore
    public String getDefaultCompanyName() {
        return defaultCompanyName;
    }


    //@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "medicine")
    //public List<MedicineCount> getMedicineCountList() {
    //    return medicineCountList;
    //}
    //
    //public void setMedicineCountList(List<MedicineCount> medicineCountList) {
    //    this.medicineCountList = medicineCountList;
    //}

    public void setDefaultCompanyName(String defaultCompanyName) {
        this.defaultCompanyName = defaultCompanyName;
    }

    @Column(name = "type", nullable = false)
    @Enumerated(value = EnumType.ORDINAL)
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Column(name = "sn", length = 30)
    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    @Column(name = "name", nullable = false, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "specification", length = 50)
    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    @Column(name = "unit", nullable = false)
    @Enumerated(value = EnumType.STRING)
    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    @Column(name = "usage", length = 200)
    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    @Column(name = "use_mode", length = 20)
    public String getUseMode() {
        return useMode;
    }

    public void setUseMode(String useMode) {
        this.useMode = useMode;
    }

    @Column(name = "use_qty", length = 20)
    public String getUseQty() {
        return useQty;
    }

    public void setUseQty(String useQty) {
        this.useQty = useQty;
    }

    @Column(name = "use_unit", nullable = false)
    @Enumerated(value = EnumType.STRING)
    public Unit getUseUnit() {
        return useUnit;
    }

    public void setUseUnit(Unit useUnit) {
        this.useUnit = useUnit;
    }

    @Column(name = "using_time", length = 20)
    public String getUsingTime() {
        return usingTime;
    }

    public void setUsingTime(String usingTime) {
        this.usingTime = usingTime;
    }

    @Column(name = "use_times")
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
    public Boolean getUsageFlag() {
        return usageFlag;
    }

    public void setUsageFlag(Boolean usageFlag) {
        this.usageFlag = usageFlag;
    }

    @Column(name = "category")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Column(name = "help_code", nullable = false, length = 50)
    public String getHelpCode() {
        return helpCode;
    }

    public void setHelpCode(String helpCode) {
        this.helpCode = helpCode;
    }

    @Column(name = "standard", length = 30)
    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    @Column(name = "rate", length = 20)
    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    @Column(name = "price", scale = 2)
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Column(name = "have_manager")
    public HaveManager getHaveManager() {
        return haveManager;
    }

    public void setHaveManager(HaveManager haveManager) {
        this.haveManager = haveManager;
    }

    public enum HaveManager {
        YES,
        NO
    }
}
