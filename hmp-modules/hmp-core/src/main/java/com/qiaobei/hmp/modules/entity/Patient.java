package com.qiaobei.hmp.modules.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.qiaobei.hmp.modules.support.DateUtils;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.joda.time.DateTime;
import org.joda.time.Months;
import org.joda.time.Years;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 患者
 */
@Entity
@Table(name = "patient")
@DynamicInsert()
@DynamicUpdate()
public class Patient extends StatusEntity {

    private String name;
    private String password;
    private String salt;
    private String plainPassword;
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
    private List<EmrJClassAdviceDict> emrJClassAdviceDicts = Lists.newArrayList();
    private String helpCode;
    private Date createOn;

    public Patient() {
    }

    public Patient(Long id, String name) {
        super(id);
        this.name = name;
    }

    public Patient(Long id, String name, String mobile) {
        super(id);
        this.name = name;
        this.mobile = mobile;
    }

    public Patient(Long id) {
        super(id);
    }

    /**
     * 获取当前病人做过的检查或者检验 按照 大类名字进行分组
     *
     * @return 获取当前病人做过的检查或者检验
     */
    @Transient
    @JsonIgnore
    public Set<String> getEmrClassList() {
        return this.getEmrJClassAdviceDicts().stream().map(
                EmrJClassAdviceDict::getExamLabName
        ).collect(Collectors.toSet());
    }

    @Column(name = "create_on")
    public Date getCreateOn() {
        if (createOn == null) {
            return DateUtils.getDayStartTime();
        }
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    @Column(name = "name", length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "udid", length = 30)
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
    @Column(name = "password", nullable = false, length = 100)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonIgnore
    @Column(name = "salt", length = 20)
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Transient
    @JsonIgnore
    public String getPlainPassword() {
        return plainPassword;
    }

    public void setPlainPassword(String plainPassword) {
        this.plainPassword = plainPassword;
    }


    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "patient")
    @JsonIgnore
    public List<EmrJClassAdviceDict> getEmrJClassAdviceDicts() {
        return emrJClassAdviceDicts;
    }

    public void setEmrJClassAdviceDicts(List<EmrJClassAdviceDict> emrJClassAdviceDicts) {
        this.emrJClassAdviceDicts = emrJClassAdviceDicts;
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

    @Override
    public String toString() {
        return "Patient{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", plainPassword='" + plainPassword + '\'' +
                ", uid='" + uid + '\'' +
                ", udid='" + udid + '\'' +
                ", sfId='" + sfId + '\'' +
                ", wxId='" + wxId + '\'' +
                ", mobile='" + mobile + '\'' +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", birthday=" + birthday +
                ", provinceNo=" + provinceNo +
                ", province='" + province + '\'' +
                ", cityNo=" + cityNo +
                ", city='" + city + '\'' +
                ", areaNo=" + areaNo +
                ", area='" + area + '\'' +
                ", patientTagList=" + patientTagList +
                ", helpCode='" + helpCode + '\'' +
                '}';
    }
}
