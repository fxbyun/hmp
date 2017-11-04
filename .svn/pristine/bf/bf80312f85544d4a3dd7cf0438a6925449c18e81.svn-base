package com.qiaobei.hmp.repository;

import com.qiaobei.hmp.modules.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface PatientDao extends JpaRepository<Patient, Long>, JpaSpecificationExecutor<Patient> {

    Patient findByWxId(String wxId);

    Patient findByUid(String uid);

    Patient findByUdid(String udid);

    List<Patient> findByMobile(String mobile);

    List<Patient> findByMobileLike(String mobile);

}

