package com.qiaobei.hmp.service;

import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.Medicine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MedicineService {

    Medicine getMedicine(Long id);

    void saveMedicine(Medicine medicine, Doctor doctor);

    void saveMedicineUsage(Medicine medicine, Doctor doctor);


    Medicine isThisMedHaveSameInSystem(String medName);

    void updateUsageFlag(Medicine medicine);

    void updateCategory(Medicine medicine);

    void addMedicineToDoctor(Doctor doctor, Medicine medicine);


    Page<Medicine> getMedPageByTypeAndName(Pageable pageable, String name, Medicine.Type medType);
}