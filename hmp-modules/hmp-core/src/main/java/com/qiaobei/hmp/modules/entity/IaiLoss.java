package com.qiaobei.hmp.modules.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.collect.Lists;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "iai_loss")
public class IaiLoss extends IdEntity {

    private String lossNo;
    private Date createDate;
    private Double totalMoney;
    private String remark;
    private Long createId;                  //对应的医生
    private List<IaiLossDetail> iaiLossDetailList = Lists.newArrayList();
    private LossStatus status;                      //状态

    private String whoCreate;
    private String uuid;



    public IaiLoss() {
    }

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "iaiLoss")
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public List<IaiLossDetail> getIaiLossDetailList() {
        return iaiLossDetailList;
    }

    public void setIaiLossDetailList(List<IaiLossDetail> iaiLossDetailList) {
        this.iaiLossDetailList = iaiLossDetailList;
    }

    @Column(name = "loss_no")
    public String getLossNo() {
        return this.lossNo;
    }

    public void setLossNo(String lossNo) {
        this.lossNo = lossNo;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "create_date", length = 10)
    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Column(name = "total_money", precision = 22)
    public Double getTotalMoney() {
        return this.totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    @Column(name = "remark", length = 65535)
    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(name = "create_id")
    public Long getCreateId() {
        return this.createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    @Column(name = "status")
    @Enumerated(value = EnumType.ORDINAL)
    public LossStatus getStatus() {
        return status;
    }

    public void setStatus(LossStatus status) {
        this.status = status;
    }

    @Column(name = "who_create")
    public String getWhoCreate() {
        return whoCreate;
    }

    public void setWhoCreate(String whoCreate) {
        this.whoCreate = whoCreate;
    }

    @Column(name = "uuid")
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public enum LossStatus {
        NOT_SAVE, SAVE
    }


}