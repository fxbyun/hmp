package com.qiaobei.hmp.service.impl;

import com.qiaobei.hmp.modules.entity.Registration;
import com.qiaobei.hmp.repository.RegistrationDao;
import com.qiaobei.hmp.service.RegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("registrationService")
@Transactional
public class RegistrationServiceImpl implements RegistrationService {

    private static Logger logger = LoggerFactory.getLogger(RegistrationServiceImpl.class);
    @Resource
    private RegistrationDao registrationDao;

    @Override
    public List<Registration> getRegistrationList(Long doctorId, Registration.Status stuats) {
        return registrationDao.findByDoctorIdAndStatus(doctorId, stuats);
    }

    @Override
    public Registration getRegistrationById(Long id) {
        return registrationDao.findOne(id);
    }

    @Override
    public Registration getRegistration(String uid, Registration.Status stuats) {
        return registrationDao.findByPatientUidAndStatus(uid, stuats);
    }

    @Override
    public void deleteAllRegistration() {
        registrationDao.deleteAll();
    }

    @Override
    public void save(Registration registration) {
        registrationDao.save(registration);
    }

    @Override
    public void delete(Long id) {
        registrationDao.delete(id);
    }
}
