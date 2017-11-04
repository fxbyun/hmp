package com.qiaobei.hmp.service;

import com.qiaobei.hmp.modules.entity.Card;
import com.qiaobei.hmp.modules.entity.Gender;
import com.qiaobei.hmp.modules.entity.Patient;
import com.qiaobei.hmp.support.DateFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PatientService {

    /**
     * 根据微信号查找
     */
    Patient getPatientByWxId(String wxId);

    /**
     * 根据卡号查找
     */
    Patient getPatientByUid(String uid);

    List<Patient> listPatientByMobile(String mobile);

    List<Patient> queryByMobile(String keyword);

    Patient getPatientByUdid(String udid);

    Patient getPatientById(Long id);

    void savePatient(Patient p);

    Patient savePatient4Card(Card card);

    Page<Patient> pagePatientFromEmr(Pageable page, Long doctorId, String patientName, DateFilter dateFilter,
                                     DateFilter ageFilter, Gender
            genderSex, String diagonsisName);

    void deletePatient(Patient patient);
}
