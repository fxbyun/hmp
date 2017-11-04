package com.qiaobei.hmp.modules.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * 症状标签
 */
@Entity
@Table(name = "symptom_tag")
@DynamicInsert()
@DynamicUpdate()
public class SymptomTag extends IdEntity {

    private Long doctorId;
    private String doctorName;
    private String code;
    private String helpCode;
    private String name;
    private Long frequency;
    private Date createOn;

    public SymptomTag() {
    }

    public SymptomTag(Long id) {
        super(id);
    }

    public void setDoctor(Doctor doctor) {
        this.doctorId = doctor.getId();
        this.doctorName = doctor.getName();
    }

    @Column(name = "doctor_id")
    @JsonIgnore
    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    @Column(name = "doctor_name", length = 20)
    @JsonIgnore
    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    @Column(name = "code", nullable = false, length = 20)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "name", nullable = false, length = 200)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "frequency")
    @JsonIgnore
    public Long getFrequency() {
        return frequency;
    }

    public void setFrequency(Long frequency) {
        this.frequency = frequency;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_on", nullable = false)
    @JsonIgnore
    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    @Column(name = "help_code", nullable = false, length = 50)
    public String getHelpCode() {
        return helpCode;
    }

    public void setHelpCode(String helpCode) {
        this.helpCode = helpCode;
    }
}