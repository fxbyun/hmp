package com.qiaobei.hmp.modules.entity;

import com.qiaobei.hmp.modules.support.OrdinalEnum;

import javax.persistence.*;
import java.util.Date;

/**
 * 药剂师
 */
@Entity
@Table(name = "pharmacist")
public class Pharmacist extends IdEntity {

    private String name;
    private String password;
    private String salt;
    private String account;
    private String sfId;
    private String wxId;
    private String mobile;
    private Gender gender;
    private String email;
    private String address;
    private Date birthday;
    private Long doctorId;
    private PersonType personType;
    private Doctor doctor;
    private IsChiefNurse isChiefNurse;

    public Pharmacist() {
    }

    public Pharmacist(Long id) {
        super(id);
    }

    @Column(name = "is_chief_nurse")
    @Enumerated(EnumType.STRING)
    public IsChiefNurse getIsChiefNurse() {
        return isChiefNurse;
    }

    public void setIsChiefNurse(IsChiefNurse isChiefNurse) {
        this.isChiefNurse = isChiefNurse;
    }

    @Column(name = "person_type")
    public PersonType getPersonType() {
        return personType;
    }

    public void setPersonType(PersonType personType) {
        this.personType = personType;
    }

    @Column(name = "name", nullable = false, length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "account", length = 30)
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Column(name = "sf_id", length = 18)
    public String getSfId() {
        return sfId;
    }

    public void setSfId(String sfId) {
        this.sfId = sfId;
    }

    @Column(name = "wx_id", length = 50)
    public String getWxId() {
        return wxId;
    }

    public void setWxId(String wxId) {
        this.wxId = wxId;
    }

    @Column(name = "mobile", length = 15)
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Column(name = "gender", nullable = false)
    @Enumerated(value = EnumType.ORDINAL)
    public Gender getGender() {
        return this.gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Column(name = "email", length = 30)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "address", length = 50)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "birthday")
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Column(name = "password", length = 100)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "salt", length = 100)
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Column(name = "doctor_id")
    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    @Transient
    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public enum IsChiefNurse implements OrdinalEnum {
        YES {
            @Override
            public Integer getValue() {
                return 0;
            }

            @Override
            public String getName() {
                return "护士长";
            }
        },//是护士长
        NO {
            @Override
            public Integer getValue() {
                return 1;
            }

            @Override
            public String getName() {
                return "普通护士";
            }
        }//不是护士长
    }

    public enum PersonType {
        Wecath_dispenser,//药剂师
        Nurse,          //护士
        Sub_Doctor, //医生子帐号
        Cashier,         //收银员
        Inspect_Doctor,     //检查医生

    }
}
