package com.qiaobei.hmp.modules.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "nation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Nation extends IdEntity {

    private Integer areaNo;
    private String areaName;
    private Integer parentNo;
    private String areaCode;
    private Integer areaLevel;
    private String typeName;

    public Nation() {
    }

    public Nation(Long id) {
        super(id);
    }

    @Column(name = "area_no")
    public Integer getAreaNo() {
        return areaNo;
    }

    public void setAreaNo(Integer areaNo) {
        this.areaNo = areaNo;
    }

    @Column(name = "area_name")
    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    @Column(name = "parent_no")
    public Integer getParentNo() {
        return parentNo;
    }

    public void setParentNo(Integer parentNo) {
        this.parentNo = parentNo;
    }

    @Column(name = "area_code")
    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    @Column(name = "area_level")
    public Integer getAreaLevel() {
        return areaLevel;
    }

    public void setAreaLevel(Integer areaLevel) {
        this.areaLevel = areaLevel;
    }

    @Column(name = "type_name")
    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}