package com.qiaobei.hmp.repository;


import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.DoctorMedicine;
import com.qiaobei.hmp.modules.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DoctorMedicineDao extends JpaRepository<DoctorMedicine, Long>,
        JpaSpecificationExecutor<DoctorMedicine> {
    DoctorMedicine findByDoctorAndMedicine(Doctor doctor, Medicine medicine);
}

