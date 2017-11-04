package com.qiaobei.hmp.repository;

import com.qiaobei.hmp.modules.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PatientDao extends JpaRepository<Patient, Long>, JpaSpecificationExecutor<Patient> {

    Patient findByWxId(String wxId);

    List<Patient> findByUid(String uid);

    List<Patient> findByUdid(String udid);

    List<Patient> findByMobile(String mobile);

    List<Patient> findByMobileLike(String mobile);

    @Query("select patient from Patient patient where patient.udid=?1 ")
    List<Patient> findListByUdid(String udid);

}

