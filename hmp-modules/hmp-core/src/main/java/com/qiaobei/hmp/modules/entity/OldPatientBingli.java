package com.qiaobei.hmp.modules.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "old_patient_bingli")
public class OldPatientBingli extends IdEntity {

    private Date createTime;
    private String patienName;
    private String sex;
    private Date age;
    private String createDate;
    private String zhenDuan;
    private String addr;
    private String chuFang;
    private String doctorName;
    private String peiYao;
    private String qiTa;
    private String shouFei;
    private String fuHe;
    private String menHenO;
    private String feiBie;
    private String keBie;
    @JsonIgnore
    private OldPatient oldPatient;

    public OldPatientBingli() {
    }

    @ManyToOne()
    @JoinColumn(name = "old_patient_id")
    public OldPatient getOldPatient() {
        return oldPatient;
    }

    public void setOldPatient(OldPatient oldPatient) {
        this.oldPatient = oldPatient;
    }

    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "patien_name")
    public String getPatienName() {
        return this.patienName;
    }

    public void setPatienName(String patienName) {
        this.patienName = patienName;
    }

    @Column(name = "sex")
    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "age", length = 10)
    public Date getAge() {
        return this.age;
    }

    public void setAge(Date age) {
        this.age = age;
    }

    @Column(name = "create_date")
    public String getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    @Column(name = "zhen_duan")
    public String getZhenDuan() {
        return this.zhenDuan;
    }

    public void setZhenDuan(String zhenDuan) {
        this.zhenDuan = zhenDuan;
    }

    @Column(name = "addr")
    public String getAddr() {
        return this.addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    @Column(name = "chu_fang", length = 65535)
    public String getChuFang() {
        return this.chuFang;
    }

    public void setChuFang(String chuFang) {
        this.chuFang = chuFang;
    }

    @Column(name = "doctor_name")
    public String getDoctorName() {
        return this.doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    @Column(name = "pei_yao")
    public String getPeiYao() {
        return this.peiYao;
    }

    public void setPeiYao(String peiYao) {
        this.peiYao = peiYao;
    }

    @Column(name = "qi_ta")
    public String getQiTa() {
        return this.qiTa;
    }

    public void setQiTa(String qiTa) {
        this.qiTa = qiTa;
    }

    @Column(name = "shou_fei")
    public String getShouFei() {
        return this.shouFei;
    }

    public void setShouFei(String shouFei) {
        this.shouFei = shouFei;
    }

    @Column(name = "fu_he")
    public String getFuHe() {
        return this.fuHe;
    }

    public void setFuHe(String fuHe) {
        this.fuHe = fuHe;
    }

    @Column(name = "men_hen_o")
    public String getMenHenO() {
        return this.menHenO;
    }

    public void setMenHenO(String menHenO) {
        this.menHenO = menHenO;
    }

    @Column(name = "fei_bie")
    public String getFeiBie() {
        return this.feiBie;
    }

    public void setFeiBie(String feiBie) {
        this.feiBie = feiBie;
    }

    @Column(name = "ke_bie")
    public String getKeBie() {
        return this.keBie;
    }

    public void setKeBie(String keBie) {
        this.keBie = keBie;
    }

}