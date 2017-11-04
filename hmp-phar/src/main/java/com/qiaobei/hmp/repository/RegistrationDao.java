package com.qiaobei.hmp.repository;

import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.Registration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface RegistrationDao extends JpaRepository<Registration, Long>, JpaSpecificationExecutor<Registration> {

    List<Registration> findByDoctorIdAndStatus(Long doctorId, Registration.Status status);


    @Query("select d from Registration  d where d.doctorId=:doctorId " +
            "and d.status=:status " +
            "and d.createOn<:maxTime order by d.createOn,d.noNumber")
    List<Registration> findByDoctorIdAndStatusOrderByCreateOn(
            @Param("doctorId") Long doctorId,
            @Param("status") Registration.Status status,
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







    @Query("select d from Registration  d where d.doctorId in :doctorList " +
            "and d.status=:status " +
            "and d.createOn<:maxTime order by d.createOn,d.noNumber")
    List<Registration> findByDoctorIdAndStatusOrderByCreateOn(
            @Param("doctorList") List<Long> doctorList,
            @Param("status") Registration.Status status,
            @Param("maxTime") Date maxTime
    );



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







    @Query("select re from Registration as re where " +
            " re.doctorId =:doctorId and re.status =:status " +
            "and (re.haveSingIn <>0 or re.haveSingIn is null ) " +
            "and re.registrationType =3 " +
            " and re.createOn<:maxTime"
    )
    Page<Registration> findByDoctorIdAndStatusAndHaveSinInOrderByCreateOn(Pageable pageable, @Param("doctorId") Long doctorId,
                                                                          @Param("status") Registration.Status status,
                                                                          @Param("maxTime") Date maxTime
//                                                                          @Param("haveSingIn") Registration.RegistrationTypeEnum haveSingIn,
//                                                                          @Param("registrationType") Registration.RegistrationTypeEnum registrationType
    );

    Registration findByPatientUidAndStatus(String uid, Registration.Status stuats);

    List<Registration> getRegistrationByPatientUidAndPatientName(String uid, String patientName);

    @Modifying
    @Query("update Registration as e set e.haveSingIn=0 where e.id=:id")
    void updateSetWxSingIn(@Param("id") Long id);

    @Query("select re from Registration re where re.registrationType=:types and re.createOn  between :startTime and  :endTime   ")
    List<Registration> getRegistrationToDayAndType(
            @Param("startTime") Date startTime,
            @Param("endTime") Date endTime,
            @Param("types") Registration.RegistrationTypeEnum types);


    @Query("select re from Registration re where re.registrationType=:types and re.createOn  between :startTime and  :endTime and re.doctorId in :doctorList")
    List<Registration> getRegistrationToDayAndType(
            @Param("startTime") Date startTime,
            @Param("endTime") Date endTime,
            @Param("types") Registration.RegistrationTypeEnum types,
            @Param("doctorList") List<Long> doctorList);



    @Query("select re from Registration re where   re.createOn  between :startTime and  :endTime   and (re.registrationType<>:types or re.registrationType is null )")
    List<Registration> getRegistrationToDayAndTypeLocal(
            @Param("startTime") Date startTime,
            @Param("endTime") Date endTime,
            @Param("types") Registration.RegistrationTypeEnum types);


    @Query("select re from Registration re where   re.createOn  between :startTime and  :endTime   and (re.registrationType<>:types or re.registrationType is null ) and re.doctorId in :doctorList ")
    List<Registration> getRegistrationToDayAndTypeLocal(
            @Param("startTime") Date startTime,
            @Param("endTime") Date endTime,
            @Param("types") Registration.RegistrationTypeEnum types, @Param("doctorList") List<Long> doctorList);
}

