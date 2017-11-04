package com.qiaobei.hmp.modules.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.*;
import org.springside.modules.utils.Collections3;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.util.List;


@Entity
@Table(name = "role")
@DynamicInsert()
@DynamicUpdate()
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Role extends StatusEntity {

    private String code;
    private String name;
    private String remark;
    private List<Permission> permissionList = Lists.newArrayList();

    public Role() {
    }

    public Role(Long id) {
        super(id);
    }


    @Column(name = "code", nullable = false, length = 50)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "name", nullable = false, length = 50)
    public String getName() {

        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "remark", length = 50)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    // 多对多定义
    @ManyToMany
    @JoinTable(name = "role_permission", joinColumns = {@JoinColumn(name = "role_id")}, inverseJoinColumns =
            {@JoinColumn(name = "permission_id")})
    // Fecth策略定义
    @Fetch(FetchMode.SUBSELECT)
    // 集合按id排序
    @OrderBy("id ASC")
    // 缓存策略
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> roleList) {
        this.permissionList = roleList;
    }

    @Transient
    @JsonIgnore
    public String getPermissionNames() {
        return Collections3.extractToString(permissionList, "code", ", ");
    }


    @Transient
    @JsonIgnore
    public List getPermissions() {
        return Collections3.extractToList(permissionList, "code");
    }

}