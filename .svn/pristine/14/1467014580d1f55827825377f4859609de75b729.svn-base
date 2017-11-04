package com.qiaobei.hmp.service;


import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.Patient;
import com.qiaobei.hmp.modules.entity.Registration;

import java.util.List;

public interface RegistrationService {

    List<Registration> getRegistrationList(Long doctorId, Registration.Status stuats);


    List<Registration> getRegistrationList(Long doctorId, Registration.Status stuats, Registration.QueueStatus queueStatus);

    Registration getRegistrationById(Long id);

    Registration getRegistration(String uid, Registration.Status stuats, Doctor doctor, Registration.QueueStatus queueStatus);

    void deleteAllRegistration();

    void save(Registration registration);

    void delete(Long id);

    void setAllDoNotCallByDoctor(Doctor doctor);

    void updatePatienHaveBinDingcard(Patient patient);

    Registration getByAppointPatientId(Long appointPatientId);

    String getRegistrationToDayAndTypeEndNo(
            Registration.RegistrationTypeEnum types);


    String getRegistrationToDayAndTypeEndNo(Registration.RegistrationTypeEnum locale, List<Long> doctorList);

}
