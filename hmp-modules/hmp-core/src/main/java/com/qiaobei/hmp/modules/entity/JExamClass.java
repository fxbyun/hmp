package com.qiaobei.hmp.modules.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;


/**
 * 检查主表
 */
@Entity
@Table(name = "j_exam_class")
public class JExamClass extends IdEntity {


    private String className;
    private Date createDate;
    private String createBy;
    private CodeStatus.StatusStr isRemove;
    private String type;
    private List<JExamSubclassDict> jExamSubclassDictList;

    public JExamClass() {
    }

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "jExamClass")
    @JsonIgnore
    public List<JExamSubclassDict> getjExamSubclassDictList() {
        return jExamSubclassDictList;
    }

    public void setjExamSubclassDictList(List<JExamSubclassDict> jExamSubclassDictList) {
        this.jExamSubclassDictList = jExamSubclassDictList;
    }

    @Column(name = "class_name", length = 100)
    public String getClassName() {
        return this.className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Column(name = "create_date", length = 19)
    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Column(name = "create_by", length = 100)
    public String getCreateBy() {
        return this.createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Column(name = "is_remove", length = 100)
    public CodeStatus.StatusStr getIsRemove() {
        return isRemove;
    }

    public void setIsRemove(CodeStatus.StatusStr isRemove) {
        this.isRemove = isRemove;
    }


    @Column(name = "type", length = 100)
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

}