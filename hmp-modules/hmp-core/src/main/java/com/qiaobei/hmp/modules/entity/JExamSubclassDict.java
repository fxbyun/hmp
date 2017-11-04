package com.qiaobei.hmp.modules.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;


/**
 * 检查子表
 */
@Entity
@Table(name = "j_exam_subclass_dict")
public class JExamSubclassDict extends IdEntity {


    private String className;
    private JExamClass jExamClass;
    private Date createDate;
    private String createBy;
    private CodeStatus.StatusStr isRemove;
    private List<JClassAdviceDict> jClassAdviceDictList;


    public JExamSubclassDict() {
    }

    public JExamSubclassDict(Long id) {
        super(id);
    }

    @Column(name = "class_name")
    public String getClassName() {
        return this.className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_class_id")
    public JExamClass getjExamClass() {
        return jExamClass;
    }

    public void setjExamClass(JExamClass jExamClass) {
        this.jExamClass = jExamClass;
    }


    @Column(name = "create_date", length = 19)
    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Column(name = "create_by")
    public String getCreateBy() {
        return this.createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Column(name = "is_remove")
    public CodeStatus.StatusStr getIsRemove() {
        return isRemove;
    }

    public void setIsRemove(CodeStatus.StatusStr isRemove) {
        this.isRemove = isRemove;
    }

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "jExamSubclassDict")
    @JsonIgnore
    public List<JClassAdviceDict> getjClassAdviceDictList() {
        return jClassAdviceDictList;
    }

    public void setjClassAdviceDictList(List<JClassAdviceDict> jClassAdviceDictList) {
        this.jClassAdviceDictList = jClassAdviceDictList;
    }
}