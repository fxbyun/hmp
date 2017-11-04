package com.qiaobei.hmp.service;

import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.Gender;
import com.qiaobei.hmp.modules.entity.OldPatient;
import com.qiaobei.hmp.modules.entity.Patient;
import com.qiaobei.hmp.support.DateFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by IntelliJ IDEA 15.0
 * User ZW_Teemoer
 * Date 2016/7/28 0028
 * Time 10:51
 */
public interface OldPatientService {
    Page<OldPatient> getAllOldPatientByDoctor(Doctor doctor, Pageable pageable);

    Page<OldPatient> findCountByPatient(Pageable pageable, Doctor doctor, String patientName, DateFilter dateFilter,
                                        DateFilter
                                                dateAge, Gender genderSex, String diagonsisName);

    OldPatient getById(Long oldPatientId);

    void activeOldPatientByOldId(Long oldPtientId);

    void save(OldPatient oldPatient);

    OldPatient findOneByNewPatient(Patient patient);
}

