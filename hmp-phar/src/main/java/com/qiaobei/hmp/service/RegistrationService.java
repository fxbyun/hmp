package com.qiaobei.hmp.service;


import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.Patient;
import com.qiaobei.hmp.modules.entity.Registration;
import com.qiaobei.hmp.modules.entity.StatusEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RegistrationService {

    List<Registration> getRegistrationList(Long doctorId, Registration.Status stuats);

    List<Registration> getRegistrationList(Long doctorId, Registration.Status stuats,Registration.QueueStatus status);

    Registration getRegistrationById(Long id);

    Registration getRegistration(String uid, Registration.Status stuats);

    void deleteAllRegistration();

    void save(Registration registration);

    void delete(Long id);

    List<Registration> getRegistrationByPatientUidAndName(String uid, String name);

    List<Registration> getByPatientAndDoctor(Patient patient, Doctor doctor);

    Long getRegistrationCountByDoctor(Doctor doctor);

    Registration getCallNameByDoctor(Doctor doctor);

    Page<Registration> getRegistrationWxSinInList(Long id, StatusEntity.Status normal, int pageNow);

    void setWxSingInTrue(Long id);

    String getRegistrationToDayAndTypeEndNo(Registration.RegistrationTypeEnum locale);

    String getRegistrationToDayAndTypeEndNo(Registration.RegistrationTypeEnum locale,List<Long> doctorList);
}
