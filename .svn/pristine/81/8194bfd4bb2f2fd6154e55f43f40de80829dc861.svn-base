package com.qiaobei.hmp.modules.entity;

import javax.persistence.*;


/**
 * 医生常用检查检验 表
 */
@Entity
@Table(name = "j_doctor_exam_lab")
public class JDoctorExamLab extends IdEntity {

    private JClassAdviceDict.Advice_Type type;// 是 检查还是检验
    private Long examLabId; //检查或者检验主表 的ID
    private String examLabName;//检查或者检验主表 的名字  心电图 或者 血液检验
    private Long doctorId;
    private Double price;
    private Long subId;  // 就是二级菜单Id  如果是检验的话 就是 具体项目的id  如果是检查的话 就是 大部位id 比如 背部
    private Long subTwoId;//三级菜单ID  //如果是检查的话 就有 本菜单 指具体部位的Id  比如
    private String classAdviceDictName;//检查/检验具体部位名字 比如  子宫输卵管碘剂造影加数字化

    public JDoctorExamLab() {
    }

    @Column(name = "class_advice_dict_name")
    public String getClassAdviceDictName() {
        return classAdviceDictName;
    }

    public void setClassAdviceDictName(String classAdviceDictName) {
        this.classAdviceDictName = classAdviceDictName;
    }

    @Column(name = "sub_id")
    public Long getSubId() {
        return subId;
    }

    public void setSubId(Long subId) {
        this.subId = subId;
    }

    @Column(name = "sub_two_id")
    public Long getSubTwoId() {
        return subTwoId;
    }

    public void setSubTwoId(Long subTwoId) {
        this.subTwoId = subTwoId;
    }

    @Enumerated(value = EnumType.STRING)
    @Column(name = "type", length = 200)
    public JClassAdviceDict.Advice_Type getType() {
        return type;
    }

    public void setType(JClassAdviceDict.Advice_Type type) {
        this.type = type;
    }

    @Column(name = "exam_lab_name")
    public String getExamLabName() {
        return examLabName;
    }

    public void setExamLabName(String examLabName) {
        this.examLabName = examLabName;
    }

    @Column(name = "exam_lab_id")
    public Long getExamLabId() {
        return this.examLabId;
    }

    public void setExamLabId(Long examLabId) {
        this.examLabId = examLabId;
    }

    @Column(name = "doctor_id")
    public Long getDoctorId() {
        return this.doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    @Column(name = "price", precision = 10)
    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}