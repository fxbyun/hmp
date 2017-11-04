package com.qiaobei.hmp.repository;

import com.qiaobei.hmp.modules.entity.ConversionApply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ConversionApplyDao extends JpaRepository<ConversionApply, Long>,
        JpaSpecificationExecutor<ConversionApply> {
}

