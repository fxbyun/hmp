package com.qiaobei.hmp.modules.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * 排队表
 */
@Entity
@Table(name = "registration")
@DynamicInsert()
@DynamicUpdate()
public class Registration extends StatusEntity {

    private Long doctorId;
    private String doctorName;
    private String patientUid;//卡号
    private String patientName;
    private Date createOn;//挂号开始时间   //现场挂号为现场刷卡的时刻
    private Date completeOn;//挂号结束时间
    private CallStatus callStatus;
    private int sequence;
    private Long appointPatientId;//微信预约ID
    private RegistrationTypeEnum registrationType; //挂号类型  现场或微信
    private RegistrationTypeEnum haveBindingCard; //是否已经绑定就诊卡
    private Long patientId;
    private RegistrationTypeEnum haveSingIn;//是否已经签到
    private String noNumber;//排队号码
    private String doctorDeptName;//排队号码

    private QueueStatus queueStatus;


    public Registration() {
    }

    public Registration(Long id) {
        super(id);
    }

    @Column(name = "doctor_dept_name")
    public String getDoctorDeptName() {
        return doctorDeptName;
    }

    public void setDoctorDeptName(String doctorDeptName) {
        this.doctorDeptName = doctorDeptName;
    }

    @Column(name = "queue_status")
    @Enumerated(value = EnumType.ORDINAL)
    public QueueStatus getQueueStatus() {
        return queueStatus;
    }

    public void setQueueStatus(QueueStatus queueStatus) {
        this.queueStatus = queueStatus;
    }

    @Column(name = "patient_id")
    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    @Column(name = "no_number")
    public String getNoNumber() {
        return noNumber;
    }

    public void setNoNumber(String noNumber) {
        this.noNumber = noNumber;
    }

    @Column(name = "have_sing_in")
    public RegistrationTypeEnum getHaveSingIn() {
        return haveSingIn;
    }

    public void setHaveSingIn(RegistrationTypeEnum haveSingIn) {
        this.haveSingIn = haveSingIn;
    }

    @Column(name = "registration_type")
    public RegistrationTypeEnum getRegistrationType() {
        return registrationType;
    }

    public void setRegistrationType(RegistrationTypeEnum registrationType) {
        this.registrationType = registrationType;
    }

    @Column(name = "have_binding_card")
    public RegistrationTypeEnum getHaveBindingCard() {
        return haveBindingCard;
    }

    public void setHaveBindingCard(RegistrationTypeEnum haveBindingCard) {
        this.haveBindingCard = haveBindingCard;
    }

    @Column(name = "call_status")
    public CallStatus getCallStatus() {
        return callStatus;
    }

    public void setCallStatus(CallStatus callStatus) {
        this.callStatus = callStatus;
    }

    @Column(name = "doctor_id")
    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    @Column(name = "doctor_name", length = 20)
    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    @Column(name = "patient_uid", length = 20)
    public String getPatientUid() {
        return patientUid;
    }

    public void setPatientUid(String patientUid) {
        this.patientUid = patientUid;
    }

    @Column(name = "patient_name", length = 20)
    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_on", nullable = false)
    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "complete_on", nullable = false)
    public Date getCompleteOn() {
        return completeOn;
    }

    public void setCompleteOn(Date completeOn) {
        this.completeOn = completeOn;
    }

    @Column(name = "sequence")
    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    @Column(name = "appoint_patient_id")
    public Long getAppointPatientId() {
        return appointPatientId;
    }

    public void setAppointPatientId(Long appointPatientId) {
        this.appointPatientId = appointPatientId;
    }

    public enum QueueStatus {
        NotVisit,           //未就诊
        Delete,             //已删除
        Visited             //已就诊
    }

    public enum RegistrationTypeEnum {
        YES,//已经绑卡  或者已经签到
        NO,//未绑卡   或者没有签到
        LOCALE,//现场挂号
        WECHAT//微信挂号
    }

    public enum CallStatus {
        CALL,
        DO_NOT_CALL
    }
}
