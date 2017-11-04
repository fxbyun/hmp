package com.qiaobei.hmp.repository;


import com.qiaobei.hmp.modules.entity.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DiagnosisDao extends JpaRepository<Diagnosis, Long>, JpaSpecificationExecutor<Diagnosis> {

    @Query(value = "select new com.qiaobei.hmp.modules.entity.Diagnosis(e.id,e.name) from Diagnosis e where e.name=:name ")
    List<Diagnosis> findByName(@Param("name") String name);
}

