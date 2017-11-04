package com.qiaobei.hmp.repository;

import com.qiaobei.hmp.modules.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PatientDao extends JpaRepository<Patient, Long>, JpaSpecificationExecutor<Patient> {

    /**
     * 按卡号查找
     */
    public Patient findByUid(String uid);

    @Query("select count(*) from Patient p where p.wxId !=null")
    public Long bindWXCount();

}

