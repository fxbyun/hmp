package com.qiaobei.hmp.modules.entity;

/**
 * Created by Administrator on 2016/8/31 0031.
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * 医生预约设置
 */
@Entity
@Table(name = "appoint_config")
@DynamicInsert(true)
@DynamicUpdate(true)
public class AppointConfig extends IdEntity{

    public enum Static{
        Close,Open
    }

    private Doctor doctor;
    private Static openStatic;
    private Integer perMin;
    private Integer personNum;
    private Date flagChangeDate;

    @OneToOne(cascade = CascadeType.REFRESH, optional = true)
    @JoinColumn(name = "doctor_id")
    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    @Column(name = "open_static", nullable = false)
    @Enumerated(value = EnumType.ORDINAL)
    public Static getOpenStatic() {
        return openStatic;
    }

    public void setOpenStatic(Static openStatic) {
        this.openStatic = openStatic;
    }

    @Column(name = "per_min")
    public Integer getPerMin() {
        return perMin;
    }

    public void setPerMin(Integer perMin) {
        this.perMin = perMin;
    }

    @Column(name = "person_num")
    public Integer getPersonNum() {
        return personNum;
    }

    public void setPersonNum(Integer personNum) {
        this.personNum = personNum;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "flag_change_date")
    public Date getFlagChangeDate() {
        return flagChangeDate;
    }

    public void setFlagChangeDate(Date flagChangeDate) {
        this.flagChangeDate = flagChangeDate;
    }
}
