package com.qiaobei.hmp.repository;

import com.qiaobei.hmp.modules.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MedicineDao extends JpaRepository<Medicine, Long>, JpaSpecificationExecutor<Medicine> {

}

