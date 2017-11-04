package com.qiaobei.hmp.modules.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;


/**
 * 检验主表
 */
@Entity
@Table(name = "j_lab_class")
public class JLabClass extends IdEntity {

    private String className;
    private Date createDate;
    private String createBy;
    private CodeStatus.StatusStr isRemove;
    private List<JClassAdviceDict> jClassAdviceDictList;

    public JLabClass() {
    }

    public JLabClass(Long id) {
        super(id);
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
        return this.isRemove;
    }

    public void setIsRemove(CodeStatus.StatusStr isRemove) {
        this.isRemove = isRemove;
    }


    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "jLabClass")
    @JsonIgnore
    public List<JClassAdviceDict> getjClassAdviceDictList() {
        return jClassAdviceDictList;
    }

    public void setjClassAdviceDictList(List<JClassAdviceDict> jClassAdviceDictList) {
        this.jClassAdviceDictList = jClassAdviceDictList;
    }
}