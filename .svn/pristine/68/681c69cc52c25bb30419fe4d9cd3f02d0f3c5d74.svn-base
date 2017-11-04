package com.qiaobei.hmp.service;


import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.Emr;
import com.qiaobei.hmp.modules.entity.Pharmacist;

import java.util.List;
import java.util.Map;

public interface PharmacistService {

    /**
     * 根据id查找
     */
    public Pharmacist getPharmacistById(Long id);

    /**
     * 根据微信号查找
     */
    public Pharmacist getPharmacistByWxId(String wxId);

    /**
     * 根据帐号查找
     */
    public Pharmacist getPharmacistByAccount(String account);

    /**
     * 保存帐号
     */
    public void savePharmacist(Pharmacist p);

    /**
     * 删除帐号
     */
    public void delPharmacist(Long id);

    /**
     * 根据医生查找
     */
    public List<Pharmacist> getPharmacistByDoctor(Long doctorId);

    void update(Pharmacist pharmacist);

    //根据emr获取收银人信息
    Pharmacist getByEmr(Emr emr);

    //根据emrList获取收银人的信息,返回Map<Emr,Pharmacist>
    Map<Emr, Pharmacist> getCashierMapByEmrList(List<Emr> emrList);

    //找出该诊所收银人/药剂师等
    List<Pharmacist> findByBossDoctor(Doctor doctor);

    List<Pharmacist> findAllCashier(Doctor doctor);

    Map<Pharmacist.PersonType, String> personTypeNameMap();

}
