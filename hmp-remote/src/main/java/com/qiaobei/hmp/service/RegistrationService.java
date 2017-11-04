package com.qiaobei.hmp.service;


import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.Patient;
import com.qiaobei.hmp.modules.entity.Registration;

import java.util.List;

public interface RegistrationService {

    List<Registration> getRegistrationList(Long doctorId, Registration.Status stuats);

    Registration getRegistrationById(Long id);

    Registration getRegistration(String uid, Registration.Status stuats);

    void deleteAllRegistration();

    void save(Registration registration);

    void delete(Long id);

    List<Registration> getRegistrationByPatientUidAndName(String uid, String name);

    List<Registration> getByPatientAndDoctor(Patient patient, Doctor doctor);

    Long getRegistrationCountByDoctor(Doctor doctor);

    Registration getCallNameByDoctor(Doctor doctor);

    String getRegistrationToDayAndTypeEndNo(Registration.RegistrationTypeEnum locale);

    String getRegistrationToDayAndTypeEndNo(Registration.RegistrationTypeEnum locale, List<Long> doctorList);

    public List<Registration> getRegistrationList(Long doctorId, Registration.Status stuats, Registration.QueueStatus queueStatus);

    void setRegistrationCallStatusNot(Doctor doctor, String callName);
}
