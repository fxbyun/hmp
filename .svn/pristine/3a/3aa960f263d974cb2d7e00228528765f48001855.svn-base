package com.qiaobei.hmp.service;


import com.qiaobei.hmp.modules.entity.Registration;

import java.util.List;

public interface RegistrationService {

    List<Registration> getRegistrationList(Long doctorId, Registration.Status stuats);

    Registration getRegistrationById(Long id);

    Registration getRegistration(String uid, Registration.Status stuats);

    void deleteAllRegistration();

    void save(Registration registration);

    void delete(Long id);
}
