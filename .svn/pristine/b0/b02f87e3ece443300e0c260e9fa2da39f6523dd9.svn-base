package com.qiaobei.hmp.modules.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.joda.time.DateTime;
import org.joda.time.Months;
import org.joda.time.Years;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "old_patient")
public class OldPatient extends IdEntity {


    private String name;
    private Status status;
    private Gender gender;
    private String address;
    private Date birthday;
    private String cfid;
    private String zhenduan;
    private Doctor doctor;
    private Date createTime;
    private Patient patient;
    private List<OldPatientBingli> oldPatientBinglis;


    /**
     * default constructor
     */
    public OldPatient() {
    }

    public OldPatient(Long id) {
        this.id = id;
    }

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "new_patient_id")
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "oldPatient")
    public List<OldPatientBingli> getOldPatientBinglis() {
        return oldPatientBinglis;
    }

    public void setOldPatientBinglis(List<OldPatientBingli> oldPatientBinglis) {
        this.oldPatientBinglis = oldPatientBinglis;
    }

    @Column(name = "name")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "status", nullable = false)
    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Column(name = "gender")
    public Gender getGender() {
        return this.gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Column(name = "address", length = 50)
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "birthday", length = 10)
    public Date getBirthday() {
        return this.birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Column(name = "cfid", length = 65535)
    public String getCfid() {
        return this.cfid;
    }

    public void setCfid(String cfid) {
        this.cfid = cfid;
    }

    @Column(name = "zhenduan", length = 65535)
    public String getZhenduan() {
        return this.zhenduan;
    }

    public void setZhenduan(String zhenduan) {
        this.zhenduan = zhenduan;
    }

    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "doctor_id")
    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
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

    @Transient
    @JsonIgnore
    public Date getListViTime() {
        OldPatientBingli endOldPatientBingli = null;

        for (OldPatientBingli oldPatientBingli : oldPatientBinglis) {
            if (endOldPatientBingli != null) {
                if (oldPatientBingli.getCreateTime().getTime() > endOldPatientBingli.getCreateTime().getTime()) {
                    endOldPatientBingli = oldPatientBingli;
                }
            } else {
                endOldPatientBingli = oldPatientBingli;
            }
        }
        if (endOldPatientBingli != null) {
            return endOldPatientBingli.getCreateTime();
        }
        return null;
    }

    public enum Status {
        UNACTIVATION,
        ACTIVATION
    }


}