package com.qiaobei.hmp.repository;


import com.qiaobei.hmp.modules.entity.Patient;
import com.qiaobei.hmp.modules.entity.StatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PatientDao extends JpaRepository<Patient, Long>, JpaSpecificationExecutor<Patient> {

    Patient findByWxId(String wxId);

    List<Patient> findByUid(String uid);

    List<Patient> findByUdid(String udid);

    @Query("select patient from Patient patient where patient.udid=?1 ")
    List<Patient> findListByUdid(String udid);

    List<Patient> findByMobile(String mobile);

    List<Patient> findByMobileLike(String mobile);

    @Query("select p from Patient p where ( p.name like ?1 or p.helpCode like ?1)  and p.status<>12 ")
    List<Patient> findByStatusAndNameHelpCodeLike(String nameOrCode);

    @Query("select p from Patient p where p.mobile=?1 and p.status<>?2")
    List<Patient> findByMobileNoTmp(String keyword, StatusEntity.Status status);
}

