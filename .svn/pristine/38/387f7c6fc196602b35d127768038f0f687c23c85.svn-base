package com.qiaobei.hmp.service;

import com.qiaobei.hmp.modules.entity.Patient;

import java.util.List;

public interface PatientService {

    /**
     * 根据id查找
     */
    public Patient getPatientById(Long id);

    /**
     * 根据微信号查找
     */
    public List<Patient> getPatientByWxId(String wxId);

    /**
     * 根据卡号查找
     */
    public Patient getPatientByUid(String uid);

    public void savePatient(Patient p);
}
