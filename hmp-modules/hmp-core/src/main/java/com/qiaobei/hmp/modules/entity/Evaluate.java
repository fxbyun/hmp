package com.qiaobei.hmp.modules.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * 病历评价咨询
 */
@Entity
@Table(name = "evaluate")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Evaluate extends IdEntity {

    private Emr emr;
    private String patientUid;
    private String patientName;
    private Long doctorId;
    private String doctorName;
    private String content;
    private String grade;
    private Type type;
    private Boolean readFlag;
    private Date createTime;
    public Evaluate(Long id) {
        super(id);
    }

    public Evaluate() {
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "emr_id")
    public Emr getEmr() {
        return emr;
    }

    public void setEmr(Emr emr) {
        this.emr = emr;
    }

    @Column(name = "patient_Uid", length = 20)
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

    @Column(name = "doctor_id", length = 20)
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

    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "grade", length = 10)
    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", length = 20)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "type", nullable = false)
    @Enumerated(value = EnumType.ORDINAL)
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Column(name = "read_flag", length = 1)
    public Boolean getReadFlag() {
        return readFlag;
    }

    public void setReadFlag(Boolean readFlag) {
        this.readFlag = readFlag;
    }

    public enum Type {
        ELE,//评价
        DTO,//医生回复患者
        OTD,//患者回复医生
    }
}
