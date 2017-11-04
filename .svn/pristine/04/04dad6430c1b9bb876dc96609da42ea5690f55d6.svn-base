package com.qiaobei.hmp.repository;

import com.qiaobei.hmp.modules.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface RegistrationDao extends JpaRepository<Registration, Long>, JpaSpecificationExecutor<Registration> {

    List<Registration> findByDoctorIdAndStatus(Long doctorId, Registration.Status status);

    Registration findByPatientUidAndStatus(String uid, Registration.Status stuats);
}

