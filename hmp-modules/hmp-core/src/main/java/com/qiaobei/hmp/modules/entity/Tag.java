package com.qiaobei.hmp.modules.entity;


import javax.persistence.*;

/**
 * 标签
 */
@Entity
@Table(name = "tag")
public class Tag extends IdEntity {

    private Long departmentId;
    private String departmentName;
    private String name;
    private TagType type;
    public Tag() {
    }

    public Tag(Long id) {
        super(id);
    }

    @Column(name = "department_id")
    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    @Column(name = "department_name", length = 20)
    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Column(name = "name", nullable = false, length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "type", nullable = false)
    @Enumerated(value = EnumType.ORDINAL)
    public TagType getType() {
        return type;
    }

    public void setType(TagType type) {
        this.type = type;
    }

    public enum TagType {
        MainSuit, DiagnosisResult
    }
}
