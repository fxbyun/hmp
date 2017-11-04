package com.qiaobei.hmp.modules.entity;

import com.qiaobei.hmp.modules.support.Constants;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 药品
 */
@Entity
@Table(name = "company")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Company extends StatusEntity {

    private String code;
    private String name;
    private String address;

    public static Company NotSpecified() {
        Company co = new Company(Constants.COMPANY_NS_ID);
        co.setName(Constants.COMPANY_NS_NAME);
        return co;
    }

    public Company() {
    }

    public Company(Long id) {
        super(id);
    }

    public Company(String name) {
        this.name = name;
    }

    @Column(name = "code", length = 10)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "name", nullable = false, length = 150)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "address", length = 255)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
