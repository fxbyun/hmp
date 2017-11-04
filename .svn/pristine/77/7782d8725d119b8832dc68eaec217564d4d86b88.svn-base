package com.qiaobei.hmp.service;

import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.Medicine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MedicineService {

    Medicine getMedicine(Long id);

    void saveMedicine(Medicine medicine, Doctor doctor);

    void saveMedicine(Medicine medicine, Doctor doctor, boolean tmp);

    void saveMedicineUsage(Medicine medicine, Doctor doctor);

    Page<Medicine> pageTagByWestern(Pageable page);

    Page<Medicine> pageTagByChinese(Pageable page);

    Page<Medicine> pageTagByType(Pageable page, Medicine.Type type);

    Page<Medicine> pageDoctorTag(Pageable page, Doctor doctor, Medicine.Type type);

    Page<Medicine> pageOtherTag(Pageable page, Doctor doctor, Medicine.Type type);

    Page<Medicine> pageDoctorTag(Pageable page, Doctor doctor, String name);

    Page<Medicine> pageOtherTag(Pageable page, Doctor doctor, String name);

    Page<Medicine> pageDoctorTag(Pageable page, Doctor doctor, Medicine.Type type, String name);

    Page<Medicine> ListDoctorTag(Pageable page, Doctor doctor, Medicine.Type type, String name, String diagosisName);

    Page<Medicine> listDoctorMedTag(Pageable page, Doctor doctor, Medicine.Type type, String name, String diagosisName);


    Page<Medicine> pageOtherTag(Pageable page, Doctor doctor, Medicine.Type type, String name);

    Page<Medicine> pageByName(Pageable pageable, String name);

    void addMedicineToDoctor(Doctor doctor, Medicine medicine);

    void removeMedicineFromDoctor(Doctor doctor, Medicine medicine);

    boolean isUsedMedicineForDoctor(Doctor doctor, Medicine medicine);

    Medicine isThisMedHaveSameInSystem(String medName);

    void updateUsageFlag(Medicine medicine);

    void updateCategory(Medicine medicine);

    Page<Medicine> pageDoctorTagPost(Pageable page, Doctor doctor, Medicine.Type type, String name);

    Page<Medicine> pageByDoctorAndCategory(Pageable page, Doctor doctor, Medicine.Type type, String category);

    Medicine getMedicineByName(String name);
}