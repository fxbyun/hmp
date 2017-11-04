package com.qiaobei.hmp.repository;

import com.qiaobei.hmp.modules.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface RegistrationDao extends JpaRepository<Registration, Long>, JpaSpecificationExecutor<Registration> {

//    List<Registration> findByDoctorIdAndStatus(Long doctorId, Registration.Status status);

    @Query("select d from Registration  d where d.doctorId=:doctorId " +
            "and d.status=:status " +
            "and d.createOn<:maxTime order by d.createOn,d.noNumber")
    List<Registration> findByDoctorIdAndStatusOrderByCreateOn(
            @Param("doctorId") Long doctorId,
            @Param("status") Registration.Status status,
            @Param("maxTime") Date maxTime
    );



    @Query("select d from Registration  d where d.doctorId in :doctorList " +
            "and d.status=:status " +
            "and d.createOn<:maxTime order by d.createOn,d.noNumber")
    List<Registration> findByDoctorIdAndStatusOrderByCreateOn(
            @Param("doctorList") List<Long> doctorList,
            @Param("status") Registration.Status status,
            @Param("maxTime") Date maxTime
    );


    Registration findByPatientUidAndStatus(String uid, Registration.Status status);

    List<Registration> getRegistrationByPatientUidAndPatientName(String uid, String patientName);

    @Query("select re from Registration re where re.registrationType=:types and re.createOn  between :startTime and  :endTime   ")
    List<Registration> getRegistrationToDayAndType(
            @Param("startTime") Date startTime,
            @Param("endTime") Date endTime,
            @Param("types") Registration.RegistrationTypeEnum types);

    @Query("select re from Registration re where   re.createOn  between :startTime and  :endTime   and (re.registrationType<>:types or re.registrationType is null )")
    List<Registration> getRegistrationToDayAndTypeLocal(
            @Param("startTime") Date startTime,
            @Param("endTime") Date endTime,
            @Param("types") Registration.RegistrationTypeEnum types);



    @Query("select d from Registration  d where d.doctorId in :doctorList " +
            "and d.status=:status " +
            "and (d.queueStatus=:queueStatus or d.queueStatus is null) " +
            "and d.createOn<:maxTime order by d.createOn,d.noNumber")
    List<Registration> findByDoctorIdAndStatusOrderByCreateOn(
            @Param("doctorList") List<Long> doctorList,
            @Param("status") Registration.Status status,
            @Param("queueStatus") Registration.QueueStatus queueStatus,
            @Param("maxTime") Date maxTime
    );



    @Query("select d from Registration  d where d.doctorId=:doctorId " +
            "and d.status=:status " +
            "and (d.queueStatus=:queueStatus or d.queueStatus is null) " +
            "and d.createOn<:maxTime order by d.createOn,d.noNumber")
    List<Registration> findByDoctorIdAndStatusOrderByCreateOn(
            @Param("doctorId") Long doctorId,
            @Param("status") Registration.Status status,
            @Param("queueStatus") Registration.QueueStatus queueStatus,
            @Param("maxTime") Date maxTime
    );


    @Query("select re from Registration re where   re.createOn  between :startTime and  :endTime   and (re.registrationType<>:types or re.registrationType is null ) and re.doctorId in :doctorList ")
    List<Registration> getRegistrationToDayAndTypeLocal(
            @Param("startTime") Date startTime,
            @Param("endTime") Date endTime,
            @Param("types") Registration.RegistrationTypeEnum types, @Param("doctorList") List<Long> doctorList);



    @Query("select re from Registration re where re.registrationType=:types and re.createOn  between :startTime and  :endTime and re.doctorId in :doctorList")
    List<Registration> getRegistrationToDayAndType(
            @Param("startTime") Date startTime,
            @Param("endTime") Date endTime,
            @Param("types") Registration.RegistrationTypeEnum types,
            @Param("doctorList") List<Long> doctorList);

    @Modifying
    @Query("update Registration  r set r.callStatus=:callStatus where r.patientName=:callName and r.doctorId in:doctorList ")
    void setRegistrationCallStatusNotCall(
            @Param("doctorList") List<Long> doctorList,
            @Param("callName") String callName,
            @Param("callStatus") Registration.CallStatus callStatus);

}

