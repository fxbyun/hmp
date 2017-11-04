package com.qiaobei.hmp.service;

import com.qiaobei.hmp.modules.entity.Prescription;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PrescriptionService {

    Page<Prescription> findPage(Pageable page, String name, String createBy);

    Prescription getPrescription(Long id);

    void savePrescriptin(Prescription p);

    void sharePrescriptin(Prescription p);

    void cancelSharePrescriptin(Prescription p);

}
