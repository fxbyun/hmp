package com.qiaobei.hmp.repository;


import com.qiaobei.hmp.modules.entity.Medicine;
import com.qiaobei.hmp.modules.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface PrescriptionDao extends JpaRepository<Prescription, Long>, JpaSpecificationExecutor<Prescription> {

    List<Prescription> findByDoctorIdAndDiagnosisLike(Long doctorId, String tag);

    List<Prescription> findByMedicineType(Medicine.Type type);

    List<Prescription> findByDoctorIdAndMedicineTypeAndCategoryId(Long doctorId, Medicine.Type type, Long catId);
}

