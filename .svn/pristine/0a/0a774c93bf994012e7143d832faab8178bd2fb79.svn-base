package com.qiaobei.hmp.repository;


import com.qiaobei.hmp.modules.entity.Patient;
import com.qiaobei.hmp.modules.entity.PatientTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatientTagDao extends JpaRepository<PatientTag, Long>, JpaSpecificationExecutor<PatientTag> {
    List<PatientTag> findByPatient(Patient patient);

    @Query("delete from PatientTag as pt where pt.patient=:patient")
    @Modifying
    void delByPatient(@Param("patient") Patient patient);

}

