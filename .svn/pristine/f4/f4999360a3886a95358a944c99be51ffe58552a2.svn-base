package com.qiaobei.hmp.repository;


import com.qiaobei.hmp.modules.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface EmrDao extends JpaRepository<Emr, Long>, JpaSpecificationExecutor<Emr> {

    @Query("select max(o.id) from Emr o where o.doctor.id=?1")
    Long getLastId(Long doctorId);

    @Query("select max(o.id) from Emr o where o.doctor.id=?1 and o.id<?2")
    Long getPreviousId(Long doctorId, Long emrId);

    @Query("select min(o.id) from Emr o where o.doctor.id=?1 and o.id>?2")
    Long getNextId(Long doctorId, Long emrId);

    @Query("select count(distinct e.patientUid) from Emr e where e.doctor.id=?1")
    Long getPatientCount(Long doctorId);

    @Query("select count(distinct e.patientUid) from Emr e where date_format(e.createOn,'%Y-%m')=date_format(now()," +
            "'%Y-%m') and e.doctor.id=?1")
    Long getPatientCountByCurrentMonth(Long doctorId);

    List<Emr> findByDoctorAndPatientUidAndCreateOnBetween(Doctor doctor, String patientUid, Date start, Date end);

    List<Emr> findByDoctor(Doctor doctor);

    @Query("select count(*) from Emr e where e.doctor.id=?1")
    Long getEmrCount(Long doctorId);

    @Query(value = "SELECT new com.qiaobei.hmp.modules.entity.Statistics(CONCAT(YEAR(e.createOn),'-',MONTH(e.createOn),'月')," +
            "COUNT(*)) FROM Emr e WHERE e" +
            ".doctor.id=?1 GROUP BY YEAR(e.createOn) ,MONTH( e.createOn) ORDER BY YEAR(e.createOn) DESC ,MONTH( e" +
            ".createOn) DESC")
    Page<Statistics> findDiagnosisStatisticsData(Pageable pageable, Long doctorId);

    @Query(value = "select new com.qiaobei.hmp.modules.entity.Statistics(d.name,count(d.name)) from Diagnosis d WHERE d" +
            ".doctorId=?1 and date_format(d" +
            ".createOn,'%Y-%m')=date_format(?2,'%Y-%m') GROUP BY d.name ORDER BY count(d.name) DESC")
    Page<Statistics> findDiseaseStatisticsData(Pageable pageable, Long doctorId, Date month);


    @Query(value = "select new com.qiaobei.hmp.modules.entity.Statistics(d.name,count(d.name)) from Diagnosis d WHERE d.doctorId" +
            " in ?1 and date_format(d" +
            ".createOn,'%Y-%m')=date_format(?2,'%Y-%m') GROUP BY d.name ORDER BY count(d.name) DESC")
    Page<Statistics> findDiseaseStatisticsData(Pageable pageable, List<Long> doctorList, Date month);


    @Query(value = "select new com.qiaobei.hmp.modules.entity.Statistics(m.medicineName,m.realUnit,m.medicineType,sum(m" +
            ".realQty)) from EmrMedicine m WHERE " +
            "m.medicineType=1 and m.doctorId=?1 and date_format(m.createOn,'%Y-%m')=date_format(?2,'%Y-%m') GROUP BY " +
            "m.medicineName,m.realUnit,m" +
            ".medicineType ORDER BY sum(m.realQty) DESC")
    Page<Statistics> findChineseStatisticsData(Pageable pageable, Long doctorId, Date month);


    @Query(value = "select new com.qiaobei.hmp.modules.entity.Statistics(m.medicineName,m.realUnit,m.medicineType,sum(m" +
            ".realQty)) from EmrMedicine m WHERE " +
            "m.medicineType=1 and m.doctorId in ?1 and date_format(m.createOn,'%Y-%m')=date_format(?2,'%Y-%m') GROUP BY " +
            "m.medicineName,m.realUnit,m" +
            ".medicineType ORDER BY sum(m.realQty) DESC")
    Page<Statistics> findChineseStatisticsData(Pageable pageable, List<Long> doctorList, Date month);


    @Query(value = "select new com.qiaobei.hmp.modules.entity.Statistics(m.medicineName,m.realUnit,m.medicineType,sum(m" +
            ".realQty)) from EmrMedicine m WHERE " +
            "m.medicineType=0 and m.doctorId=?1 and date_format(m.createOn,'%Y-%m')=date_format(?2,'%Y-%m') GROUP BY " +
            "m.medicineName,m.realUnit,m" +
            ".medicineType ORDER BY sum(m.realQty) DESC")
    Page<Statistics> findWesternStatisticsData(Pageable pageable, Long doctorId, Date month);


    @Query(value = "select new com.qiaobei.hmp.modules.entity.Statistics(m.medicineName,m.realUnit,m.medicineType,sum(m" +
            ".realQty)) from EmrMedicine m WHERE " +
            "m.medicineType=0 and m.doctorId in ?1 and date_format(m.createOn,'%Y-%m')=date_format(?2,'%Y-%m') GROUP BY " +
            "m.medicineName,m.realUnit,m" +
            ".medicineType ORDER BY sum(m.realQty) DESC")
    Page<Statistics> findWesternStatisticsData(Pageable pageable, List<Long> doctorList, Date month);


    @Query(value = "select new com.qiaobei.hmp.modules.entity.Statistics(d.name,count(d.name)) from Diagnosis d WHERE d" +
            ".doctorId=?1 and d.createOn BETWEEN " +
            "?2 and ?3 GROUP BY d.name ORDER BY count(d.name) DESC")
    Page<Statistics> findDiseaseDiatels(Pageable pageable, Long doctorId, Date startDate, Date endDate);


    @Query(value = "select new com.qiaobei.hmp.modules.entity.Statistics(d.name,count(d.name)) from Diagnosis d WHERE d" +
            ".doctorId in ?1 and d.createOn BETWEEN " +
            "?2 and ?3 GROUP BY d.name ORDER BY count(d.name) DESC")
    Page<Statistics> findDiseaseDiatels(Pageable pageable, List<Long> doctorList, Date startDate, Date endDate);


    @Query(value = "select new com.qiaobei.hmp.modules.entity.Statistics(m.medicineName,m.realUnit,sum(m.realQty)) from " +
            "EmrMedicine m WHERE m" +
            ".medicineType=1 and m.doctorId=?1 and m.createOn BETWEEN ?2 and ?3 GROUP BY m.medicineName,m.realUnit " +
            "ORDER BY sum(m.realQty) DESC")
    Page<Statistics> findChineseDiatels(Pageable pageable, Long doctorId, Date startDate, Date endDate);


    @Query(value = "select new com.qiaobei.hmp.modules.entity.Statistics(m.medicineName,m.realUnit,sum(m.realQty)) from " +
            "EmrMedicine m WHERE m" +
            ".medicineType=1 and m.doctorId in ?1 and m.createOn BETWEEN ?2 and ?3 GROUP BY m.medicineName,m.realUnit " +
            "ORDER BY sum(m.realQty) DESC")
    Page<Statistics> findChineseDiatels(Pageable pageable, List<Long> doctorList, Date startDate, Date endDate);


    @Query(value = "select new com.qiaobei.hmp.modules.entity.Statistics(m.medicineName,m.realUnit,sum(m.realQty)) from " +
            "EmrMedicine m WHERE m" +
            ".medicineType=0 and m.doctorId=?1 and m.createOn BETWEEN ?2 and ?3 GROUP BY m.medicineName,m.realUnit " +
            "ORDER BY sum(m.realQty) DESC")
    Page<Statistics> findWesternDiatels(Pageable pageable, Long doctorId, Date startDate, Date endDate);

    @Query(value = "select new com.qiaobei.hmp.modules.entity.Statistics(m.medicineName,m.realUnit,sum(m.realQty)) from " +
            "EmrMedicine m WHERE m" +
            ".medicineType=0 and m.doctorId in ?1 and m.createOn BETWEEN ?2 and ?3 GROUP BY m.medicineName,m.realUnit " +
            "ORDER BY sum(m.realQty) DESC")
    Page<Statistics> findWesternDiatels(Pageable pageable, List<Long> doctorList, Date startDate, Date endDate);


    @Query(value = "select new com.qiaobei.hmp.modules.entity.Emr(e.id,max (e.createOn),e.patient) from Emr e " +
            " left join e.patient ep " +
            " where e.createOn between :createStartDate and :createEndDate " +
            " and ifnull(e.diagnosisResult,'') like :diagnosisResult " +

            " and ifnull(ep.birthday,'2016-08-01') between :birthdayStartDate and :birthdayEndDate " +
            " and ep.status <> :types" +
            " and (ep.name like :name " +
            " or ep.helpCode like :name) " +
            " and e.doctor in:doctor " +
            " group by e.patient,e.doctor " +
            " order by max (e.createOn) desc ")
    Page<Emr> findCountByPatientNoGender(Pageable pageable,
                                         @Param("createStartDate") Date startDateCreate,
                                         @Param("createEndDate") Date endDateCreate,
                                         @Param("diagnosisResult") String diagnosisResult,
                                         @Param("doctor") List<Doctor> doctor,
                                         @Param("birthdayStartDate") Date birthdayStartDate,
                                         @Param("birthdayEndDate") Date birthdayEndDate,
                                         @Param("types") StatusEntity.Status type,
                                         @Param("name") String name);

    @Query(value = "select new com.qiaobei.hmp.modules.entity.Emr(e.id,max (e.createOn),e.patient) from Emr e " +
            " left join e.patient ep " +
            " where " +
            "e.createOn between :createStartDate and :createEndDate " +
            " and ifnull(e.diagnosisResult,'') like :diagnosisResult " +
            " and e.doctor in:doctor" +
            " and ifnull(ep.birthday,'2016-08-01') between :birthdayStartDate and :birthdayEndDate " +
            " and (ep.name like :name " +
            " or ep.helpCode like :name) " +
            " and ep.gender = :gender " +
            " and ep.status <> :types" +
            " group by e.patient,e.doctor " +
            " order by max(e.createOn) desc ")
    Page<Emr> findCountByPatientHaveGender(Pageable pageable,
                                           @Param("createStartDate") Date startDateCreate,
                                           @Param("createEndDate") Date endDateCreate,
                                           @Param("diagnosisResult") String diagnosisResult,
                                           @Param("doctor") List<Doctor> doctor,
                                           @Param("birthdayStartDate") Date birthdayStartDate,
                                           @Param("birthdayEndDate") Date birthdayEndDate,
                                           @Param("name") String name,
                                           @Param("types") StatusEntity.Status type,
                                           @Param("gender") Gender gender);

    List<Emr> findByPatientAndDoctorOrderByCreateOnDesc(Patient patient, Doctor doctor);

    @Modifying
    @Query(value = "update Emr emr set emr.haveSend=0 where emr.id=:id")
    void updateSetIsHaveSend(@Param("id") Long id);


    @Modifying
    @Query(value = "update Emr e set e.patientUid=:uid where e.patient=:patient")
    void updateTmpEmrToCommonByPaitent(@Param("patient") Patient patient, @Param("uid") String uid);

    @Query(value = "SELECT new com.qiaobei.hmp.modules.entity.Statistics(CONCAT(YEAR(e.createOn),'-',MONTH(e.createOn),'月')," +
            "COUNT(*)) FROM Emr e WHERE e" +
            ".doctor in ?1 GROUP BY YEAR(e.createOn) ,MONTH( e.createOn) ORDER BY YEAR(e.createOn) DESC ,MONTH( e" +
            ".createOn) DESC")
    Page<Statistics> findDiagnosisStatisticsData(Pageable pageable, List<Doctor> doctorList);

    @Modifying
    @Query("update Emr e set e.patient=:newId where e.patient=:oldId")
    void updatePatien(@Param("oldId") Patient oldId, @Param("newId") Patient newId);

    @Query("select e.patient from Emr e where e.doctor=:doctor group by e.patient")
    List<Patient> findByDoctorGroupByPatien(@Param("doctor") Doctor doctor);

}