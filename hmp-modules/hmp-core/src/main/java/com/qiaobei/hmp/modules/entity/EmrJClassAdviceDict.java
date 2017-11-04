package com.qiaobei.hmp.modules.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA 2016.4
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 2016/11/8 0008
 * Time 16:33
 */
@Entity
@Table(name = "emr_j_class_advice_dict")
public class EmrJClassAdviceDict extends IdEntity {
    @JsonIgnore
    private Emr emr;
    private JClassAdviceDict.Advice_Type type;
    private Long exp1;  //JClassAdviceDict中的ID
    private Long exp2;
    private Long exp3;
    private String info;//医生填写的特殊说明
    private String resultInfo; //检查/检验医生填写的结论
    private String examLabName; //大类名称
    private String adviceName; //具体项目名称
    private Double price;
    private Patient patient;
    private Date createOn = new Date();
    private StatusEntity.Status status;
    private List<ExamLabFile> examLabFileList = Lists.newArrayList();
    private Date updateOn = new Date();
    private String tmpFileNameId;

    @Column(name = "tmp_file_name_id")
    public String getTmpFileNameId() {
        return tmpFileNameId;
    }

    public void setTmpFileNameId(String tmpFileNameId) {
        this.tmpFileNameId = tmpFileNameId;
    }
    @Column(name = "update_on")
    public Date getUpdateOn() {
        return updateOn;
    }

    public void setUpdateOn(Date updateOn) {
        this.updateOn = updateOn;
    }

    @Column(name = "result_info")
    public String getResultInfo() {
        return resultInfo;
    }

    public void setResultInfo(String resultInfo) {
        this.resultInfo = resultInfo;
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "emrJClassAdviceDict")
    public List<ExamLabFile> getExamLabFileList() {
        return examLabFileList;
    }

    public void setExamLabFileList(List<ExamLabFile> examLabFileList) {
        this.examLabFileList = examLabFileList;
    }

    @Column(name = "status2")
    public StatusEntity.Status getStatus() {
        return status;
    }

    public void setStatus(StatusEntity.Status status) {
        this.status = status;
    }

    @Column(name = "create_on")
    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    @ManyToOne(fetch = FetchType.EAGER, optional = true,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "patient_id")
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Column(name = "advice_name")
    public String getAdviceName() {
        return adviceName;
    }

    public void setAdviceName(String adviceName) {
        this.adviceName = adviceName;
    }

    @Column(name = "exam_lab_name")
    public String getExamLabName() {
        return examLabName;
    }

    public void setExamLabName(String examLabName) {
        this.examLabName = examLabName;
    }

    @Column(name = "price")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @ManyToOne(fetch = FetchType.EAGER, optional = true,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "emr_id")
    public Emr getEmr() {
        return emr;
    }

    public void setEmr(Emr emr) {
        this.emr = emr;
    }


    @Basic
    @Column(name = "type")
    @Enumerated(value = EnumType.STRING)
    public JClassAdviceDict.Advice_Type getType() {
        return type;
    }

    public void setType(JClassAdviceDict.Advice_Type type) {
        this.type = type;
    }

    @Basic
    @Column(name = "exp1")
    public Long getExp1() {
        return exp1;
    }

    public void setExp1(Long exp1) {
        this.exp1 = exp1;
    }

    @Basic
    @Column(name = "exp2")
    public Long getExp2() {
        return exp2;
    }

    public void setExp2(Long exp2) {
        this.exp2 = exp2;
    }

    @Basic
    @Column(name = "exp3")
    public Long getExp3() {
        return exp3;
    }

    public void setExp3(Long exp3) {
        this.exp3 = exp3;
    }

    @Basic
    @Column(name = "info")
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

}
