package com.qiaobei.hmp.repository;

import com.qiaobei.hmp.modules.entity.PrescriptionLib;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PrescriptionLibDao extends JpaRepository<PrescriptionLib, Long>,
        JpaSpecificationExecutor<PrescriptionLib> {

    PrescriptionLib findByParentId(Long parentId);
}

