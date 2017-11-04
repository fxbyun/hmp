package com.qiaobei.hmp.repository;

import com.qiaobei.hmp.modules.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PatientDao extends JpaRepository<Patient, Long>, JpaSpecificationExecutor<Patient> {

    /**
     * 按微信号查找
     */
    @Query(value = "select patient from Patient patient where patient.wxId=?1 and patient.wxId is not null ")
    public List<Patient> findByWxId(String wxId);

    /**
     * 按卡号查找
     */
    public Patient findByUid(String uid);

}

