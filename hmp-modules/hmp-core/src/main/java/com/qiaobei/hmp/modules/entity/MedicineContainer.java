package com.qiaobei.hmp.modules.entity;


import com.qiaobei.hmp.modules.support.OrdinalEnum;

/**
 * 药品转化容器（拥有常见属性，用于计算）
 */
public class MedicineContainer {
    private MedicinePrivate medicinePrivate;
    private Medicine medicine;
    private Doctor doctor;
    private String specification;
    private Medicine.Unit unit = Medicine.Unit.bxs;
    private Medicine.Type type;
    private Double qty;
    private String helpCode;
    private String standard;
    private String info;
    private Double rate;
    private Double copies;
    private Status status;
    //药品分类
    private String category;
    //药品标准用量（是否适用）
    private Boolean usageFlag;
    //药品治疗方式
    private String useMode;


    //记录医生习惯，某个属性使用最多
    private String strMoreUser;
    private Boolean booleanMoreUser;
    private Double doubleMoreUser;
    //是否被库存管理
    private MedicinePrivate.HaveManager haveManager;
    //零售价
    private Double price;

    public MedicineContainer(Double doubleMoreUser) {
        this.doubleMoreUser = doubleMoreUser;
    }

    public MedicineContainer(Boolean booleanMoreUser) {
        this.booleanMoreUser = booleanMoreUser;
    }

    public MedicineContainer(String strMoreUser) {
        this.strMoreUser = strMoreUser;
    }

    public MedicineContainer() {
    }

    public MedicinePrivate getMedicinePrivate() {
        return medicinePrivate;
    }

    public void setMedicinePrivate(MedicinePrivate medicinePrivate) {
        this.medicinePrivate = medicinePrivate;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public Medicine.Unit getUnit() {
        return unit;
    }

    public void setUnit(Medicine.Unit unit) {
        this.unit = unit;
    }

    public Medicine.Type getType() {
        return type;
    }

    public void setType(Medicine.Type type) {
        this.type = type;
    }

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

    public String getHelpCode() {
        return helpCode;
    }

    public void setHelpCode(String helpCode) {
        this.helpCode = helpCode;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public MedicinePrivate.HaveManager getHaveManager() {
        return haveManager;
    }

    public void setHaveManager(MedicinePrivate.HaveManager haveManager) {
        this.haveManager = haveManager;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getCopies() {
        return copies;
    }

    public void setCopies(Double copies) {
        this.copies = copies;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Boolean getUsageFlag() {
        return usageFlag;
    }

    public void setUsageFlag(Boolean usageFlag) {
        this.usageFlag = usageFlag;
    }

    public String getUseMode() {
        return useMode;
    }

    public void setUseMode(String useMode) {
        this.useMode = useMode;
    }

    public Boolean getBooleanMoreUser() {
        return booleanMoreUser;
    }

    public void setBooleanMoreUser(Boolean booleanMoreUser) {
        this.booleanMoreUser = booleanMoreUser;
    }

    public Double getDoubleMoreUser() {
        return doubleMoreUser;
    }

    public void setDoubleMoreUser(Double doubleMoreUser) {
        this.doubleMoreUser = doubleMoreUser;
    }

    public String getStrMoreUser() {
        return strMoreUser;
    }

    public void setStrMoreUser(String strMoreUser) {
        this.strMoreUser = strMoreUser;
    }

    public enum Status implements OrdinalEnum {
        NORMAL {
            @Override
            public Integer getValue() {
                return 0;
            }

            @Override
            public String getName() {
                return "正常损耗";
            }
        },//正常损耗
        ACTIVE {
            @Override
            public Integer getValue() {
                return 1;
            }

            @Override
            public String getName() {
                return "主动添加";
            }
        }, //主动添加 或者 生成损耗单
        RETAIL {
            @Override
            public Integer getValue() {
                return 2;
            }

            @Override
            public String getName() {
                return "零售开单";
            }
        }
    }
}
