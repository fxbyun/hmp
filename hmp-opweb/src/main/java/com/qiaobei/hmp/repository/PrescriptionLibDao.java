package com.qiaobei.hmp.repository;

import com.qiaobei.hmp.modules.entity.Medicine;
import com.qiaobei.hmp.modules.entity.PrescriptionLib;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface PrescriptionLibDao extends JpaRepository<PrescriptionLib, Long>,
        JpaSpecificationExecutor<PrescriptionLib> {

    List<PrescriptionLib> findByMedicineType(Medicine.Type type);

    List<PrescriptionLib> findByMedicineTypeAndCategoryId(Medicine.Type type, Long catId);
}

