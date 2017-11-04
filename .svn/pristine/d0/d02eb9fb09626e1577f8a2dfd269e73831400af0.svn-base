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

    List<Patient> queryByMobileNoTmp(String keyword);

    Patient getPatientByUdid(String udid);

    //照理说得到的Patient应该是唯一的,但是数据库的数据有问题的时候会报错
    List<Patient> getPatientListByUdid(String udid);

    Patient getPatientById(Long id);

    void savePatient(Patient p);

    Patient savePatient4Card(Card card);

    Patient saveWxPatientBindingCard(Card card, Patient patient);


    void deletePatien(Patient patient);

    void saveAndFlushPatient(Patient patient);

    Patient findByUid(String patientUid);

    Patient findOne(Long id);


}
