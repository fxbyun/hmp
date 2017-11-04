package com.qiaobei.hmp.service;

import com.qiaobei.hmp.modules.entity.Doctor;

public interface DoctorService {

    /**
     * 按id查找Doctor
     */
    Doctor getDoctor(Long id);

}
