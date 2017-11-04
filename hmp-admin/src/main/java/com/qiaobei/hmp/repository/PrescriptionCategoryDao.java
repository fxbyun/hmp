package com.qiaobei.hmp.repository;

import com.qiaobei.hmp.modules.entity.PrescriptionCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PrescriptionCategoryDao extends JpaRepository<PrescriptionCategory, Long>,
        JpaSpecificationExecutor<PrescriptionCategory> {

    @Query("from PrescriptionCategory p where p.doctorId is null")
    List<PrescriptionCategory> findCategory();
}

