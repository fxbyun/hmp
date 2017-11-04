package com.qiaobei.hmp.modules.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2016/9/18 0018.
 */
@Entity
@Table(name = "appoint_reward")
@DynamicInsert(true)
@DynamicUpdate(true)
public class AppointReward extends IdEntity {



    public enum RewardStatus{
        NOT_PAID,       //下了预约但是没有支付
        SUCCESS,        //下了预约并且支付了
        FAIL,           //预约失败
        MY_DELETE,      //自己取消了预约
        DOC_DELETE,     //医生删除了预约
        DOC_TREE        //医生已就诊
    }


    private MobileUser mobileUser;
    private Doctor doctor;
    private AppointPatient appointPatient;
    private String mobile;
    private String message;
    private RewardStatus status;
    private Double money;

    private String orderId;
    private Date payDate;

    private String patientName;
    private Long patientId;
    private Date appointDate;

    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "mobile_user_id")
    public MobileUser getMobileUser() {
        return mobileUser;
    }

    public void setMobileUser(MobileUser mobileUser) {
        this.mobileUser = mobileUser;
    }

    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "doctor_id")
    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    @ManyToOne
    @JoinColumn(name = "appoint_patient_id")
    public AppointPatient getAppointPatient() {
        return appointPatient;
    }

    public void setAppointPatient(AppointPatient appointPatient) {
        this.appointPatient = appointPatient;
    }

    @JoinColumn(name = "mobile")
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @JoinColumn(name = "message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @JoinColumn(name = "status")
    public RewardStatus getStatus() {
        return status;
    }

    public void setStatus(RewardStatus status) {
        this.status = status;
    }

    @JoinColumn(name = "money")
    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }


    @JoinColumn(name = "order_id")
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "pay_date")
    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }


    @Column(name = "patient_name")
    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    @Column(name = "patient_id")
    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "appoint_date")
    public Date getAppointDate() {
        return appointDate;
    }

    public void setAppointDate(Date appointDate) {
        this.appointDate = appointDate;
    }

    public String RewardByEnum(){
        if(this.status==RewardStatus.NOT_PAID){
            return "已预约";
        }else if(this.status==RewardStatus.SUCCESS) {
            return "已预约";
        }else if(this.status==RewardStatus.FAIL){
            return "预约失败";
        }else if(this.status==RewardStatus.MY_DELETE){
            return "已取消";
        }else if(this.status==RewardStatus.DOC_DELETE){
            return "已删除";
        }else {
            return "已预约";
        }
    }
}
