package com.qiaobei.hmp.repository;

import com.qiaobei.hmp.modules.entity.Diagnosis;
import com.qiaobei.hmp.modules.entity.DiagnosisTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface DiagnosisDao extends JpaRepository<Diagnosis, Long>, JpaSpecificationExecutor<Diagnosis> {

    List<Diagnosis> findByPatientUid(String uid);
}

