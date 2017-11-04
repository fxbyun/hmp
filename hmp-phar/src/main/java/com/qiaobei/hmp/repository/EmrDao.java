package com.qiaobei.hmp.repository;

import com.qiaobei.hmp.modules.entity.Emr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface EmrDao extends JpaRepository<Emr, Long>, JpaSpecificationExecutor<Emr> {

}