package com.qiaobei.hmp.modules.entity;

import com.qiaobei.hmp.modules.support.OrdinalEnum;
import org.assertj.core.util.Lists;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "remote_role")
@DynamicInsert(true)
@DynamicUpdate(true)
public class RemoteRole extends IdEntity {
    private String name;
    private String description;
    private Date createTime;
    private Date updateTime;
    private Status status;

    private List<RemotePermission> permissionList = Lists.newArrayList();
    private List<RemoteUser> userList = Lists.newArrayList();

    @ManyToMany(mappedBy = "roleList", fetch = FetchType.LAZY)
    public List<RemoteUser> getUserList() {
        return userList;
    }

    public void setUserList(List<RemoteUser> userList) {
        this.userList = userList;
    }

    // 多对多定义
    @ManyToMany
    @JoinTable(name = "remote_role_permission", joinColumns = {@JoinColumn(name = "role_id")}, inverseJoinColumns =
            {@JoinColumn(name = "permission_id")})
    // Fecth策略定义
    @Fetch(FetchMode.SUBSELECT)
    // 集合按id排序
    @OrderBy("id ASC")
    // 缓存策略
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    public List<RemotePermission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<RemotePermission> roleList) {
        this.permissionList = roleList;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "update_time")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    public enum Status implements OrdinalEnum {
        ENABLE {
            @Override
            public Integer getValue() {
                return 0;
            }

            @Override
            public String getName() {
                return "启用";
            }
        },
        DISABLE {
            @Override
            public Integer getValue() {
                return 1;
            }

            @Override
            public String getName() {
                return "禁用";
            }
        }
    }


}
