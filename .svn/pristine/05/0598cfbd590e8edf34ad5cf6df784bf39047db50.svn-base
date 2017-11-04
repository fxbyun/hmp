package com.qiaobei.hmp.repository;

import com.qiaobei.hmp.modules.entity.Conversion;
import com.qiaobei.hmp.modules.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ConversionDao extends JpaRepository<Conversion, Long>, JpaSpecificationExecutor<Conversion> {
    Conversion findByMedicineAndToUnit(Medicine medicine, Medicine.Unit toUnit);
}

