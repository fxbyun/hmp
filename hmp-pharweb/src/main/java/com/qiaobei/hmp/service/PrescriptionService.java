package com.qiaobei.hmp.service;

import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.Medicine;
import com.qiaobei.hmp.modules.entity.Prescription;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PrescriptionService {

    Prescription getPrescription(Long id);

    void savePrescription(Prescription prescription);

    void deletePrescription(Long id);

    Page<Prescription> pagePrescription(Pageable page, Long doctorId, Long categoryId, Medicine.Type medicineType,
                                        String name);

    Page<Prescription> pagePrescription(Pageable page, Long doctorId);

    List<Prescription> listPrescription(Long doctorId, String tag);

    List<Prescription> listRP(Medicine.Type type);

    List<Prescription> listRP(Long doctorId, Medicine.Type type, Long catId);

    void batchAddPrescription(Doctor d, String ids, Long cateId);

}
