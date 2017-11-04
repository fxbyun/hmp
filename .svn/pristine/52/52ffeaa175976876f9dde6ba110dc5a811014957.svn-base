package com.qiaobei.hmp.modules.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.base.Objects;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "doctor")
@DynamicInsert()
@DynamicUpdate()
// 默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Doctor extends StatusEntity {
    private static final long serialVersionUID = 1419401807533048175L;
    private String mobile;
    private String email;
    private String name;
    private String password;
    private String salt;
    private String plainPassword;
    private Gender gender;
    private Integer age;
    private String tel;
    private String card;
    private String authCode;
    private Date createOn;

    private String outpatientService;
    private String businessLicense;
    private String legal;
    private String legalCard;//法人身份证号
    private String businessAddr;
    private String specialty;//擅长
    private Integer seniority;//从医年限
    private String intro;//简介
    private String wealth; //我的财富
    private String certificate;
    private Integer evaluationCount;
    private String wxId;
    private Integer integration; //总分
    private Integer integral;    //积分总值
    private Date verifyOn; //审核通过信息
    private String verifyBy;
    private Long verifyById;
    private String printInfo;
    private String printType;  //打印类型 80打印机 还是A5纸 默认 80    2016年5月4日 15:31:53 zw
    private String printModel;  //打印模式  lodop 为快速打印  js为预览打印
    private String recommender; //推荐人
    private String recommendMobile;//推荐人电话
    private Integer provinceNo;//省
    private String province;
    private Integer cityNo;//十
    private String city;
    private Integer areaNo;//县
    private String area;
    private String autoSend;
    private String autoSendActivateMsg;//是否发送激活短信
    private Long autoSendDay; //自动回访短信发送默认天数
    private String needAlonePrinTypeStrings;//需要单独打印的模版的字符串 用英文逗号, 隔开
    private MsgMoney msgMoney;//
    private Long primaryDoctorId;   //主帐号id
    private Doctor_Type doctorType; //医生类型
    private String deptName;        //部门名称

    //诊所简介
    private String clinicInfo;
    private DoctorAppointStatus appointStatus;


    //设置是否允许子医生或者护士修改价格
    private Boolean allowSubDoctorUpdatePrice;
    private Boolean allowNurseUpdatePrice;


    public Doctor() {
    }


    public Doctor(Long id) {
        this.id = id;
    }

    @Column(name = "dept_name")
    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Column(name = "appoint_status")
    @Enumerated(value = EnumType.ORDINAL)
    public DoctorAppointStatus getAppointStatus() {
        return appointStatus;
    }

    public void setAppointStatus(DoctorAppointStatus appointStatus) {
        this.appointStatus = appointStatus;
    }

    @Column(name = "primary_doctor_id")
    public Long getPrimaryDoctorId() {
        return primaryDoctorId;
    }

    public void setPrimaryDoctorId(Long primaryDoctorId) {
        this.primaryDoctorId = primaryDoctorId;
    }

    @Column(name = "doctor_type")
    @Enumerated(value = EnumType.STRING)
    public Doctor_Type getDoctorType() {
        return doctorType;
    }

    public void setDoctorType(Doctor_Type doctorType) {
        this.doctorType = doctorType;
    }

    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "doctor")
    public MsgMoney getMsgMoney() {
        return msgMoney;
    }

    public void setMsgMoney(MsgMoney msgMoney) {
        this.msgMoney = msgMoney;
    }

    @Column(name = "auto_send", length = 100)
    public String getAutoSend() {
        return autoSend;
    }

    public void setAutoSend(String autoSend) {
        this.autoSend = autoSend;
    }

    @Column(name = "auto_send_activate_msg")
    public String getAutoSendActivateMsg() {
        return autoSendActivateMsg;
    }

    public void setAutoSendActivateMsg(String autoSendActivateMsg) {
        this.autoSendActivateMsg = autoSendActivateMsg;
    }

    @Column(name = "auto_send_day", length = 100)
    public Long getAutoSendDay() {
        return autoSendDay;
    }

    public void setAutoSendDay(Long autoSendDay) {
        this.autoSendDay = autoSendDay;
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
    public String getPlainPassword() {
        return plainPassword;
    }

    public void setPlainPassword(String plainPassword) {
        this.plainPassword = plainPassword;
    }

    @Column(name = "name", nullable = false, length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "gender", nullable = false)
    @Enumerated(value = EnumType.ORDINAL)
    public Gender getGender() {
        return this.gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Column(name = "age")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Column(name = "tel", length = 20)
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Column(name = "mobile", length = 20)
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Column(name = "email", length = 50)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_on")
    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    @Column(name = "outpatient_service", length = 50)
    public String getOutpatientService() {
        return outpatientService;
    }

    public void setOutpatientService(String outpatientService) {
        this.outpatientService = outpatientService;
    }

    @Column(name = "evaluation_count")
    public Integer getEvaluationCount() {
        return evaluationCount;
    }

    public void setEvaluationCount(Integer evaluationCount) {
        this.evaluationCount = evaluationCount;
    }

    @Column(name = "business_license", length = 50)
    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    @Column(name = "specialty")
    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    @Column(name = "intro")
    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    @Column(name = "wealth", length = 50)
    public String getWealth() {
        return wealth;
    }

    public void setWealth(String wealth) {
        this.wealth = wealth;
    }

    @Column(name = "wx_id", length = 50)
    public String getWxId() {
        return wxId;
    }

    public void setWxId(String wxId) {
        this.wxId = wxId;
    }

    @Column(name = "integration")
    public Integer getIntegration() {
        return integration;
    }

    public void setIntegration(Integer integration) {
        this.integration = integration;
    }

    @Column(name = "integral")
    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    @Column(name = "business_addr", length = 100)
    public String getBusinessAddr() {
        return businessAddr;
    }

    public void setBusinessAddr(String businessAddr) {
        this.businessAddr = businessAddr;
    }

    @Column(name = "legal", length = 20)
    public String getLegal() {
        return legal;
    }

    public void setLegal(String legal) {
        this.legal = legal;
    }

    @Column(name = "legal_card", length = 20)
    public String getLegalCard() {
        return legalCard;
    }

    public void setLegalCard(String legalCard) {
        this.legalCard = legalCard;
    }

    @Column(name = "card", length = 20)
    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    @Transient
    @JsonIgnore
    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    @Column(name = "seniority")
    public Integer getSeniority() {
        return seniority;
    }

    public void setSeniority(Integer seniority) {
        this.seniority = seniority;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "verify_on")
    public Date getVerifyOn() {
        return verifyOn;
    }

    public void setVerifyOn(Date verifyOn) {
        this.verifyOn = verifyOn;
    }

    @Column(name = "verify_name", length = 20)
    public String getVerifyBy() {
        return verifyBy;
    }

    public void setVerifyBy(String verifyBy) {
        this.verifyBy = verifyBy;
    }

    @Column(name = "verify_id")
    public Long getVerifyById() {
        return verifyById;
    }

    public void setVerifyById(Long verifyById) {
        this.verifyById = verifyById;
    }

    @Column(name = "certificate")
    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    @Column(name = "print_info", length = 100)
    public String getPrintInfo() {
        return printInfo;
    }

    public void setPrintInfo(String printInfo) {
        this.printInfo = printInfo;
    }

    @Column(name = "print_type", length = 100)
    public String getPrintType() {
        return printType;
    }

    public void setPrintType(String printType) {
        this.printType = printType;
    }

    @Column(name = "print_model", length = 100)
    public String getPrintModel() {
        return printModel;
    }

    public void setPrintModel(String printModel) {
        this.printModel = printModel;
    }

    @Column(name = "recommender", length = 20)
    public String getRecommender() {
        return recommender;
    }

    public void setRecommender(String recommender) {
        this.recommender = recommender;
    }

    @Column(name = "recommend_mobile", length = 15)
    public String getRecommendMobile() {
        return recommendMobile;
    }

    public void setRecommendMobile(String recommendMobile) {
        this.recommendMobile = recommendMobile;
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

    @Column(name = "need_alone_prin_type_strings")
    public String getNeedAlonePrinTypeStrings() {
        return needAlonePrinTypeStrings;
    }

    public void setNeedAlonePrinTypeStrings(String needAlonePrinTypeStrings) {
        this.needAlonePrinTypeStrings = needAlonePrinTypeStrings;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .toString();
    }

    @Column(name = "clinic_info")
    public String getClinicInfo() {
        return clinicInfo;
    }

    public void setClinicInfo(String clinicInfo) {
        this.clinicInfo = clinicInfo;
    }

    @Column(name = "allow_sub_doctor_update_price")
    public Boolean getAllowSubDoctorUpdatePrice() {
        return allowSubDoctorUpdatePrice;
    }

    public void setAllowSubDoctorUpdatePrice(Boolean allowSubDoctorUpdatePrice) {
        this.allowSubDoctorUpdatePrice = allowSubDoctorUpdatePrice;
    }

    @Column(name = "allow_nurse_update_price")
    public Boolean getAllowNurseUpdatePrice() {
        return allowNurseUpdatePrice;
    }

    public void setAllowNurseUpdatePrice(Boolean allowNurseUpdatePrice) {
        this.allowNurseUpdatePrice = allowNurseUpdatePrice;
    }

    public enum DoctorAppointStatus {
        Close, Open
    }


    public enum Doctor_Type {
        Common_Doctor,//普通医生  无法生成 子用户
        Clinic_Boss,//诊所主治医生或老板帐号  可以生成 子帐号和 修改诊所信息
        Sub_Doctor // 子帐号医生  不能修改诊所信息
    }
}
