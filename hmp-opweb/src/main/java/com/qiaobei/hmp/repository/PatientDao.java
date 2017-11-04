package com.qiaobei.hmp.repository;

import com.qiaobei.hmp.modules.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatientDao extends JpaRepository<Patient, Long>, JpaSpecificationExecutor<Patient> {

    Patient findByWxId(String wxId);

    Patient findByUid(String uid);

    Patient findByUdid(String udid);

    @Query(value = "select p from Patient p where p.mobile=?1 and p.status<>4 ")
    List<Patient> findByMobileNum(String mobile);

    List<Patient> findByMobileLike(String mobile);

}

