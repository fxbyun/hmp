package com.qiaobei.hmp.service;


import com.qiaobei.hmp.modules.entity.Pharmacist;

import java.util.List;

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
}
