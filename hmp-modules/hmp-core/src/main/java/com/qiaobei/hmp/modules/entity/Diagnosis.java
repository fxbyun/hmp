package com.qiaobei.hmp.modules.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * 标签
 */
@Entity
@Table(name = "diagnosis")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Diagnosis extends IdEntity {

    private Long departmentId;
    private String departmentName;
    private Long doctorId;
    private String doctorName;
    private String patientUid;
    private String patientName;
    private String name;
    private Date createOn;
    private Emr emr;

    public Diagnosis() {
    }

    public Diagnosis(Long id) {
        super(id);
    }

    public Diagnosis(Long id, String name) {
        super(id);
        this.name = name;
    }

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE}, optional = true)
    @JoinColumn(name = "emr_id")
    public Emr getEmr() {
        return emr;
    }

    public void setEmr(Emr emr) {
        this.emr = emr;
        this.doctorId = emr.getDoctor().getId();
        this.doctorName = emr.getDoctorName();
        this.departmentId = emr.getDepartmentId();
        this.departmentName = emr.getDepartmentName();
        this.patientUid = emr.getPatientUid();
        this.patientName = emr.getPatientName();
    }

    @Column(name = "department_id")
    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    @Column(name = "department_name", length = 20)
    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
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

    @Column(name = "name", nullable = false, length = 200)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_on", nullable = false)
    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }
}
