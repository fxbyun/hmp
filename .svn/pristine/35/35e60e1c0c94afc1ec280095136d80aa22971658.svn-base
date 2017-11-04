package com.qiaobei.hmp.repository;

import com.qiaobei.hmp.modules.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface RegistrationDao extends JpaRepository<Registration, Long>, JpaSpecificationExecutor<Registration> {

    List<Registration> findByDoctorIdAndStatus(Long doctorId, Registration.Status status);

    Registration findByPatientUidAndStatus(String uid, Registration.Status stuats);

    Registration findByAppointPatientId(Long appointPatientId);

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
}

