package com.qiaobei.hmp.service;

import com.qiaobei.hmp.modules.entity.*;
import com.qiaobei.hmp.support.DateFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface EmrService {

    Emr getEmr(Long id);

    Long getLastEmrId(Long doctorId);

    Long getPreviousEmrId(Long doctorId, Long id);

    Long getNextEmrId(Long doctorId, Long id);

    void saveEmr(Emr emr);

    Page<Emr> findCountByPatient(Pageable page, Doctor doctorId, String patientName, DateFilter dateFilter,
                                 DateFilter ageFilter, Gender genderSex, String diagonsisName, Long... subDoctorId);

    Page<Emr> pageEmr(Pageable page, Long doctorId, String patientName, DateFilter dateFilter);

    Page<Emr> pageEmrForManagerToList(Pageable page, Long doctorId, String patientName, DateFilter dateFilter, Long
            patianId, Long... subDoctorId);

    Page<Emr> pagePatientFromEmr(Pageable page, Long doctorId, String patientName, DateFilter dateFilter, DateFilter
            ageFilter, Gender
                                         genderSex, String diagonsisName);

    Page<Emr> pageEmrReplied(Pageable page, Long doctorId, String patientName, DateFilter dateFilter, Long... subDoctorId);

    Page<Emr> pageEmrByPatient(Pageable page, String patientUid, DateFilter dateFilter);

    List<Emr> listByPatientUidBetween(Doctor doctor, String patientUid, Date start, Date end);

    Long getPatientCount(Long doctorId);

    Long getPatientCountByCurrentMonth(Long doctorId);

    List<Emr> listEmrByDoctor(Doctor doctor);

    Long getEmrCount(Long doctorId);

    List<Statistics> findDiagnosisStatisticsData(Pageable page, Long doctorId);

    List<Statistics> findDiagnosisStatisticsData(Pageable page, List<Doctor> doctorList);

    List<Statistics> findDiseaseStatisticsData(Pageable page, Long doctorId, Date month);

    List<Statistics> findDiseaseStatisticsData(Pageable page, List<Long> doctorList, Date month);

    List<Statistics> findChineseStatisticsData(Pageable page, Long doctorId, Date month);

    List<Statistics> findChineseStatisticsData(Pageable page, List<Long> doctorList, Date month);

    List<Statistics> findWesternStatisticsData(Pageable page, Long doctorId, Date month);

    List<Statistics> findWesternStatisticsData(Pageable page, List<Long> doctorList, Date month);

    Page<Statistics> findDiseaseDiatels(Pageable page, Long doctorId, DateFilter dateFilter);

    Page<Statistics> findDiseaseDiatels(Pageable page, List<Long> doctorList, DateFilter dateFilter);

    Page<Statistics> findChineseDiatels(Pageable page, Long doctorId, DateFilter dateFilter);

    Page<Statistics> findChineseDiatels(Pageable page, List<Long> doctorList, DateFilter dateFilter);

    Page<Statistics> findWesternDiatels(Pageable page, Long doctorId, DateFilter dateFilter);

    Page<Statistics> findWesternDiatels(Pageable page, List<Long> doctorList, DateFilter dateFilter);

    List<Emr> getEmrByPatientAndDoctor(Patient patient, Doctor doctor);

    List<Emr> getEmrListByNotSendIsNeddAutoSend();

    void saveAndPullEmr(Emr emr);

    void updateTmpEmrToCommonByPaitent(Patient patient);

    List<Emr> findByStatus(StatusEntity.Status hangUp);

    void updatePatien(Long id, Long id1);

    List<Emr> findAllByDoctorListAndTime(List<Doctor> doctorList, Date startDate, Date endDate, String... patientName);

    List<Emr> findAllByDoctorListAndTimeAndShouYinId(List<Doctor> doctorList, Date startDate, Date endDate, String patientName, Long shouYinId);

    Page<Emr> pageEmr(PageRequest pageRequest, List<Doctor> doctorId, String patientName, DateFilter dateFilter, Long shouYinId);

    List<Patient> findAllPatientByDoctor(Doctor doctor);


    Map<String, Boolean> isNeedPrint(Long emrId);



}
