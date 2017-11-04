package com.qiaobei.hmp.modules.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.qiaobei.hmp.modules.support.OrdinalEnum;
import org.springside.modules.utils.MetaData;

import javax.persistence.*;
import java.util.Date;


/**
 * 检查 检验 详细项目表
 */
@Entity
@Table(name = "j_class_advice_dict")
public class JClassAdviceDict extends IdEntity {


    private String adviceName;
    private Advice_Type adviceType;
    private Date createDate;
    private String createBy;
    private CodeStatus.StatusStr isRemove;
    private JLabClass jLabClass; //检验                  exp1
    private JExamSubclassDict jExamSubclassDict; //检查  exp2
    private String helpCode;

    private Double price;

    private String classAdviceDictName;//大项名称

    public JClassAdviceDict() {
    }

    @Transient
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Transient
    public String getClassAdviceDictName() {
        return classAdviceDictName;
    }

    public void setClassAdviceDictName(String classAdviceDictName) {
        this.classAdviceDictName = classAdviceDictName;
    }

    @Column(name = "help_code")
    public String getHelpCode() {
        return helpCode;
    }

    public void setHelpCode(String helpCode) {
        this.helpCode = helpCode;
    }

    @Column(name = "advice_name", length = 128)
    public String getAdviceName() {
        return this.adviceName;
    }

    public void setAdviceName(String adviceName) {
        this.adviceName = adviceName;
    }

    @Column(name = "advice_type", length = 2)
    @Enumerated(value = EnumType.STRING)
    public Advice_Type getAdviceType() {
        return this.adviceType;
    }

    public void setAdviceType(Advice_Type adviceType) {
        this.adviceType = adviceType;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "create_date", length = 10)
    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Column(name = "create_by", length = 128)
    public String getCreateBy() {
        return this.createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Column(name = "is_remove")
    public CodeStatus.StatusStr getIsRemove() {
        return this.isRemove;
    }

    public void setIsRemove(CodeStatus.StatusStr isRemove) {
        this.isRemove = isRemove;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "exp1")
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public JLabClass getjLabClass() {
        return jLabClass;
    }

    public void setjLabClass(JLabClass jLabClass) {
        this.jLabClass = jLabClass;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "exp2")
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public JExamSubclassDict getjExamSubclassDict() {
        return jExamSubclassDict;
    }

    public void setjExamSubclassDict(JExamSubclassDict jExamSubclassDict) {
        this.jExamSubclassDict = jExamSubclassDict;
    }

    public enum Advice_Type implements OrdinalEnum {
        @MetaData(value = "检验")
        JianYan {
            @Override
            public Integer getValue() {
                return 0;
            }

            @Override
            public String getName() {
                return "检验";
            }
        },//检验
        @MetaData(value = "检查")
        JianCha {
            @Override
            public Integer getValue() {
                return 1;
            }

            @Override
            public String getName() {
                return "检查";
            }
        }//检查
    }
}