package com.qiaobei.hmp.service;

import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.OldPatientBingli;
import com.qiaobei.hmp.modules.entity.Patient;
import com.qiaobei.hmp.support.DateFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by IntelliJ IDEA 15.0
 * User ZW_Teemoer
 * Date 2016/7/28 0028
 * Time 10:51
 */
public interface OldPatientBingliService {
    Page<OldPatientBingli> pageEmrForManagerToList(Pageable pageable, String patientName, DateFilter dateFilter, Long
            patientId);


    List<OldPatientBingli> findAllByDoctor(Doctor doctor);

    OldPatientBingli getById(Long emrId);

    Page<OldPatientBingli> getPageByUid(PageRequest id, Patient patient, DateFilter dateFilter);
}
