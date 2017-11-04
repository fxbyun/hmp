package com.qiaobei.hmp.repository;


import com.qiaobei.hmp.modules.entity.Pharmacist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface PharmacistDao extends JpaRepository<Pharmacist, Long>, JpaSpecificationExecutor<Pharmacist> {

    /**
     * 按微信号查找
     */
    public Pharmacist findByWxId(String wxId);

    /**
     * 按帐号查找
     */
    public Pharmacist findByAccount(String account);

    /**
     * 按医生id查找
     */
    public List<Pharmacist> findByDoctorId(Long doctorId);

}

