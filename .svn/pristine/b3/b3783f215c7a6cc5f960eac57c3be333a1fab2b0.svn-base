package com.qiaobei.hmp.repository;

import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.Emr;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmrDao extends JpaRepository<Emr, Long>, JpaSpecificationExecutor<Emr> {
    List<Emr> findByPatientUid(String uid);

    @Query("select count(distinct e.patientUid) from Emr e where e.doctor.id=?1")
    Long getPatientCount(Long doctorId);

    Page<Emr> findEmrByDoctor(Doctor doctor, Pageable pageable);
}

