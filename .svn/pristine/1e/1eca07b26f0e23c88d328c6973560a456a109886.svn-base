package com.qiaobei.hmp.modules.entity;

import com.qiaobei.hmp.modules.support.OrdinalEnum;
import org.assertj.core.util.Lists;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "remote_permission")
@DynamicInsert(true)
@DynamicUpdate(true)
public class RemotePermission extends IdEntity {

    private String name;
    private String code;
    private String description;
    private Integer parentId;
    private Status status;
    private List<RemoteRole> roleList = Lists.newArrayList();

    @ManyToMany(mappedBy = "permissionList", fetch = FetchType.LAZY)
    public List<RemoteRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<RemoteRole> roleList) {
        this.roleList = roleList;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "parent_id")
    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
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
