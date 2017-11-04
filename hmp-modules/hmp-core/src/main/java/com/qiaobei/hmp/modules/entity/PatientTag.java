package com.qiaobei.hmp.modules.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * 标签
 */
@Entity
@Table(name = "patient_tag")
@DynamicInsert()
@DynamicUpdate()
public class PatientTag extends IdEntity {

    private Long doctorId;
    private String doctorName;
    private String name;
    private Long frequency;
    private Date createOn;

    private Patient patient;

    public PatientTag() {
    }

    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "patient_id")
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
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

    public void setDoctor(Doctor doctor) {
        this.doctorId = doctor.getId();
        this.doctorName = doctor.getName();
    }

    @Column(name = "name", nullable = false, length = 200)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "frequency")
    public Long getFrequency() {
        return frequency;
    }

    public void setFrequency(Long frequency) {
        this.frequency = frequency;
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
