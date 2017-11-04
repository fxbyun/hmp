package com.qiaobei.hmp.modules.entity;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 统计
 *
 * @author Andy
 */
public class Statistics {

    private static Map medicineUnits = Maps.newHashMap();

    static {
        medicineUnits.put(Medicine.Unit.bxs, "盒");
        medicineUnits.put(Medicine.Unit.btl, "瓶");
        medicineUnits.put(Medicine.Unit.pkg, "包");
        medicineUnits.put(Medicine.Unit.grs, "粒");
        medicineUnits.put(Medicine.Unit.pcs, "支");
        medicineUnits.put(Medicine.Unit.g, "克");
        medicineUnits.put(Medicine.Unit.mg, "毫克");
        medicineUnits.put(Medicine.Unit.ml, "毫升");
    }

    private long value;
    private String name;
    private Medicine.Unit unit;
    private Medicine.Type type;
    private double qty;

    public Statistics(String name, long value) {
        this.name = name;
        this.value = value;
    }

    public Statistics(String name, Medicine.Unit unit, Medicine.Type type, double qty) {
        if (type == Medicine.Type.Western) {
            this.name = name + "(" + medicineUnits.get(unit) + ")";
        } else {
            this.name = name;
        }
        this.qty = qty;
    }

    public Statistics(String name, Medicine.Unit unit, double qty) {
        this.name = name;
        this.unit = unit;
        this.qty = qty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public Medicine.Unit getUnit() {
        return unit;
    }

    public void setUnit(Medicine.Unit unit) {
        this.unit = unit;
    }
}
