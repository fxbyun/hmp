package com.qiaobei.hmp.repository;

import com.qiaobei.hmp.modules.entity.VitalSign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface VitalSignDao extends JpaRepository<VitalSign, Long>, JpaSpecificationExecutor<VitalSign> {

}

