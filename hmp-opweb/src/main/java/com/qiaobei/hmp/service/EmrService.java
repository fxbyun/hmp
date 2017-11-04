package com.qiaobei.hmp.service;

import com.qiaobei.hmp.modules.entity.*;
import com.qiaobei.hmp.support.DateFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface EmrService {

    Emr getEmr(Long id);

    Long getLastEmrId(Long doctorId);

    Long getPreviousEmrId(Long doctorId, Long id);

    Long getNextEmrId(Long doctorId, Long id);

    void saveEmr(Emr emr);

    Page<Emr> findCountByPatient(Pageable page, Doctor doctorId, String patientName, DateFilter dateFilter,
                                 DateFilter ageFilter, Gender genderSex, String diagonsisName);

    Page<Emr> pageEmr(Pageable page, Long doctorId, String patientName, DateFilter dateFilter);

    Page<Emr> pageEmrForManagerToList(Pageable page, Long doctorId, String patientName, DateFilter dateFilter, Long
            patianId);

    Page<Emr> pagePatientFromEmr(Pageable page, Long doctorId, String patientName, DateFilter dateFilter, DateFilter
            ageFilter, Gender
            genderSex, String diagonsisName);

    Page<Emr> pageEmrReplied(Pageable page, Long doctorId, String patientName, DateFilter dateFilter);

    Page<Emr> pageEmrByPatient(Pageable page, String patientUid, DateFilter dateFilter);

    List<Emr> listByPatientUidBetween(Doctor doctor, String patientUid, Date start, Date end);

    Long getPatientCount(Long doctorId);

    Long getPatientCountByCurrentMonth(Long doctorId);

    List<Emr> listEmrByDoctor(Doctor doctor);

    Long getEmrCount(Long doctorId);

    List<Statistics> findDiagnosisStatisticsData(Pageable page, Long doctorId);

    List<Statistics> findDiseaseStatisticsData(Pageable page, Long doctorId, Date month);

    List<Statistics> findChineseStatisticsData(Pageable page, Long doctorId, Date month);

    List<Statistics> findWesternStatisticsData(Pageable page, Long doctorId, Date month);

    Page<Statistics> findDiseaseDiatels(Pageable page, Long doctorId, DateFilter dateFilter);

    Page<Statistics> findChineseDiatels(Pageable page, Long doctorId, DateFilter dateFilter);

    Page<Statistics> findWesternDiatels(Pageable page, Long doctorId, DateFilter dateFilter);

    List<Emr> getEmrByPatientAndDoctor(Patient patient, Doctor doctor);

    List<Emr> getEmrListByNotSendIsNeddAutoSend();

    void saveAndPullEmr(Emr emr);
}
