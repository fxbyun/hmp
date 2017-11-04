package com.qiaobei.hmp.modules.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.collect.Lists;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "iai_into")
public class IaiInto extends IdEntity {

    private String goodsNo;//订单号
    private Date createDate;//入库日期
    private Supplier supplier;//供应商
    private Long createBy;//创建医生
    private Double totalMoney;//总价
    private List<IaiIntoDetail> intoDetailList = Lists.newArrayList();
    private String whoCreate;   //填表人
    private String uuid;        //标识符
    private IaiIntoStatus status;       //保存状态
    private IaiIntoType iaiIntoType;    //订单类型

    public IaiInto() {
    }

    public IaiInto(Long id) {
        super(id);
    }

    @Column(name = "goods_no")
    public String getGoodsNo() {
        return this.goodsNo;
    }

    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", length = 10)
    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Column(name = "create_by")
    public Long getCreateBy() {
        return this.createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    @Column(name = "total_money", precision = 22)
    public Double getTotalMoney() {
        return this.totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "iaiInto")
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public List<IaiIntoDetail> getIntoDetailList() {
        return intoDetailList;
    }

    public void setIntoDetailList(List<IaiIntoDetail> intoDetailList) {
        this.intoDetailList = intoDetailList;
    }

    @Column(name = "who_create")
    public String getWhoCreate() {
        return whoCreate;
    }

    public void setWhoCreate(String whoCreate) {
        this.whoCreate = whoCreate;
    }

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    @Column(name = "uuid")
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Column(name = "status")
    @Enumerated(value = EnumType.ORDINAL)
    public IaiIntoStatus getStatus() {
        return status;
    }

    public void setStatus(IaiIntoStatus status) {
        this.status = status;
    }

    @Column(name = "iai_into_type")
    @Enumerated(value = EnumType.ORDINAL)
    public IaiIntoType getIaiIntoType() {
        return iaiIntoType;
    }

    public void setIaiIntoType(IaiIntoType iaiIntoType) {
        this.iaiIntoType = iaiIntoType;
    }

    public String EnumStatus(){
        if(this.status==IaiIntoStatus.NOT_SAVE){
            return "未保存";
        }else if(this.status==IaiIntoStatus.SAVE){
            return "已保存";
        }else {
            return "未保存";
        }

    }

    public String EnumIaiType(){
        if(this.iaiIntoType==IaiIntoType.STORAGE){
            return "入货单";
        }else if(this.iaiIntoType==IaiIntoType.REPLENISH){
            return "智能补货单";
        }else {
            return "入货单";
        }

    }


    public enum IaiIntoType {
        STORAGE,                //入库单
        REPLENISH               //智能补货单
    }

    public enum IaiIntoStatus {
        NOT_SAVE, SAVE
    }






}