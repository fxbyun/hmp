package com.qiaobei.hmp.modules.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.qiaobei.hmp.modules.support.UserStaticEnum;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.joda.time.DateTime;
import org.joda.time.Months;
import org.joda.time.Years;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/8/24 0024.
 */
@Entity
@Table(name = "mobile_user")
@DynamicInsert(true)
@DynamicUpdate(true)
public class MobileUser extends IdEntity{
    private String name;
    private String salt;
    private String uid;
    private String udid;
    private String sfId;
    private String wxId;
    private String mobile;
    private Gender gender;
    private String email;
    private String address;
    private Date birthday;
    private Integer provinceNo;
    private String province;
    private Integer cityNo;
    private String city;
    private Integer areaNo;
    private String area;
    private List<PatientTag> patientTagList = Lists.newArrayList();
    private String helpCode;
    private UserStaticEnum status;
    private String openId;
    private String locationPlace;

    public MobileUser() {
    }

    public MobileUser(Long id, String name) {
        super(id);
        this.name = name;
    }

    public MobileUser(Long id, String name, String mobile) {
        super(id);
        this.name = name;
        this.mobile = mobile;
    }

    public MobileUser(Long id) {
        super(id);
    }

    @Column(name = "name", length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "udid", length = 30)
    @JsonIgnore
    public String getUdid() {
        return udid;
    }

    public void setUdid(String udid) {
        this.udid = udid;
    }

    @Column(name = "uid", length = 30)
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Column(name = "sf_id", length = 18)
    @JsonIgnore
    public String getSfId() {
        return sfId;
    }

    public void setSfId(String sfId) {
        this.sfId = sfId;
    }

    @Column(name = "wx_id", length = 50)
    @JsonIgnore
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
    @JsonIgnore
    public Gender getGender() {
        return this.gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Column(name = "email", length = 30)
    @JsonIgnore
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "address", length = 50)
    @JsonIgnore
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "birthday")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @JsonIgnore
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Transient
    @JsonIgnore
    public String getAge() {
        DateTime now = DateTime.now();
        DateTime bty = new DateTime(birthday);
        int months = Months.monthsBetween(bty, now).getMonths();
        if (months < 24) {
            return months + "个月";
        }
        return Years.yearsBetween(bty, now).getYears() + "岁";
    }

    @JsonIgnore
    @Column(name = "salt", length = 20)
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "patient")
    @JsonIgnore
    public List<PatientTag> getPatientTagList() {
        return patientTagList;
    }

    public void setPatientTagList(List<PatientTag> patientTagList) {
        this.patientTagList = patientTagList;
    }

    @Column(name = "province_no")
    public Integer getProvinceNo() {
        return provinceNo;
    }

    public void setProvinceNo(Integer provinceNo) {
        this.provinceNo = provinceNo;
    }

    @Column(name = "province")
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Column(name = "city_no")
    public Integer getCityNo() {
        return cityNo;
    }

    public void setCityNo(Integer cityNo) {
        this.cityNo = cityNo;
    }

    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "area_no")
    public Integer getAreaNo() {
        return areaNo;
    }

    public void setAreaNo(Integer areaNo) {
        this.areaNo = areaNo;
    }

    @Column(name = "area")
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Column(name = "help_code")
    public String getHelpCode() {
        return helpCode;
    }

    public void setHelpCode(String helpCode) {
        this.helpCode = helpCode;
    }

    @Column(name = "status")
    @Enumerated(value = EnumType.ORDINAL)
    public UserStaticEnum getStatus() {
        return status;
    }

    public void setStatus(UserStaticEnum status) {
        this.status = status;
    }


    @Column(name = "open_id")
    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    @Column(name = "location_place")
    public String getLocationPlace() {
        return locationPlace;
    }

    public void setLocationPlace(String locationPlace) {
        this.locationPlace = locationPlace;
    }
}
