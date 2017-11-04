package com.qiaobei.hmp.service;

import com.qiaobei.hmp.modules.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PatientService {

    /**
     * 根据卡号查找
     */
    public Patient getPatientByUid(String uid);

    /**
     * 患者数量
     */
    Long count();

    /**
     * 按id查找
     */
    Patient getPatient(Long id);

    /**
     * 更新
     */
    void savePatient(Patient patient);

    /**
     * user分页、条件查询
     */
    Page<Patient> findPage(Pageable page, String name, String mobile, String uid);


    Long bindWXCount();

    void updateCard(Long id, String uid);

}
