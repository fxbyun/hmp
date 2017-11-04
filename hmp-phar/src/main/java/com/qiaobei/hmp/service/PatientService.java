package com.qiaobei.hmp.service;

import com.qiaobei.hmp.modules.entity.Card;
import com.qiaobei.hmp.modules.entity.Patient;

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

    List<Patient> getPatientListByUdid(String udid);
}
