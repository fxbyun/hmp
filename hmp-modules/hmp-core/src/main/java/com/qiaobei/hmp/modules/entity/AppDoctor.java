package com.qiaobei.hmp.modules.entity;

/**
 * Created by Administrator on 2016/9/29 0029.
 */
public class AppDoctor {
    private Long id;
    private String outpatientService;
    private String businessAddr;
    private Double fenAvg;
    private Long num;
    private Long months;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOutpatientService() {
        return outpatientService;
    }

    public void setOutpatientService(String outpatientService) {
        this.outpatientService = outpatientService;
    }

    public String getBusinessAddr() {
        return businessAddr;
    }

    public void setBusinessAddr(String businessAddr) {
        this.businessAddr = businessAddr;
    }

    public Double getFenAvg() {
        return fenAvg;
    }

    public void setFenAvg(Double fenAvg) {
        this.fenAvg = fenAvg;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public Long getMonths() {
        return months;
    }

    public void setMonths(Long months) {
        this.months = months;
    }
}
