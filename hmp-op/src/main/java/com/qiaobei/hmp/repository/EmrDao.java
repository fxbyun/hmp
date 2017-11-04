package com.qiaobei.hmp.repository;

import com.qiaobei.hmp.modules.entity.Emr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface EmrDao extends JpaRepository<Emr, Long>, JpaSpecificationExecutor<Emr> {

    List<Emr> findByPatientUid(String uid);

}

