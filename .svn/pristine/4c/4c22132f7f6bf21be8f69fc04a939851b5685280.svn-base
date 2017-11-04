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
@Table(name = "remote_user")
@DynamicInsert(true)
@DynamicUpdate(true)
public class RemoteUser extends IdEntity {

    private String userName;
    private String password;
    private String realName;
    private String openId;
    private Date createTime;
    private Date updateTime;
    private String mobile;
    private String salt;
    private Status status;

    //角色集合
    private List<RemoteRole> roleList = Lists.newArrayList();

    // 多对多定义
    @ManyToMany
    @JoinTable(name = "remote_user_role", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn
            (name = "role_id")})
    // Fecth策略定义
    @Fetch(FetchMode.SUBSELECT)
    // 集合按id排序
    @OrderBy("id ASC")
    // 缓存策略
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    public List<RemoteRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<RemoteRole> roleList) {
        this.roleList = roleList;
    }


    @Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "pass_word")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "real_name")
    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    @Column(name = "open_id")
    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
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
    @Enumerated(value = EnumType.STRING)
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    @Column(name = "mobile")
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Column(name = "salt")
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public enum Status implements OrdinalEnum {

        NORMAL {
            @Override
            public Integer getValue() {
                return 0;
            }

            @Override
            public String getName() {
                return "正常";
            }
        },

        NOPASS {
            @Override
            public Integer getValue() {
                return 1;
            }

            @Override
            public String getName() {
                return "未通过";
            }
        },

        DISABLE {
            @Override
            public Integer getValue() {
                return 2;
            }

            @Override
            public String getName() {
                return "禁用";
            }
        }
    }


}
