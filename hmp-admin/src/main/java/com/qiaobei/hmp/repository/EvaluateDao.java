package com.qiaobei.hmp.repository;

import com.qiaobei.hmp.modules.entity.Evaluate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EvaluateDao extends JpaRepository<Evaluate, Long>, JpaSpecificationExecutor<Evaluate> {

}

