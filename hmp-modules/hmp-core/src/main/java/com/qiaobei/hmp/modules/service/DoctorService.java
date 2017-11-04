package com.qiaobei.hmp.modules.service;

import com.qiaobei.hmp.modules.entity.Doctor;

import java.util.List;

public interface DoctorService {
    /**
     * 按id查找
     */
    Doctor getDoctor(Long id);

    List<Doctor> getSubDoctor(Doctor doctor);

    List<Doctor> findSubDoctor(Doctor doctor);
}
