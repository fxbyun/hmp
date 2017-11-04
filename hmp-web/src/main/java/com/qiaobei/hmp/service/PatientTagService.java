package com.qiaobei.hmp.service;


import com.qiaobei.hmp.modules.entity.Patient;
import com.qiaobei.hmp.modules.entity.PatientTag;

import java.util.List;

public interface PatientTagService {

    /**
     * 根据微信号查找
     */
    PatientTag getPatientTagById(Long id);

    void savePatientTag(PatientTag tag);

    void deletePatientTagById(Long id);

    List<PatientTag> listPatientTagsByPatient(Long patientId);

    void delPatienTagByPatient(Patient patient);

}
