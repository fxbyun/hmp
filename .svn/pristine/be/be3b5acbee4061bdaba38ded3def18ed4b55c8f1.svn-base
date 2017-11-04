package com.qiaobei.hmp.service;


import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.Emr;
import com.qiaobei.hmp.modules.entity.StatusEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface EmrService {

    Emr getEmr(Long id);

    Page<Emr> getEmrListByDoctor(Doctor doctor, StatusEntity.Status status, Pageable pageable, Date startDate, Date endDate, String cardPwd, String name, String phone);

    void updateEmr(Emr emr);

    void backMedByEmrMedIds(List<Long> longList);

    void save(Emr emr);

    List<Emr> findAllByDoctorListAndTimeAndShouYinId(List<Doctor> doctorList, Date startDate, Date endDate, String patientName, Long shouYinId);
}
