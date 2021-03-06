package com.qiaobei.hmp.modules.service.impl;

import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.Doctor_;
import com.qiaobei.hmp.modules.repository.DoctorDao;
import com.qiaobei.hmp.modules.service.DoctorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("doctorServiceCode")
@Transactional
public class DoctorServiceImpl implements DoctorService {
    private static Logger logger = LoggerFactory.getLogger(DoctorServiceImpl.class);
    
    @Resource(name = "doctorDaoCode")
    private DoctorDao doctorDao;

    @Override
    @Transactional(readOnly = true)
    public Doctor getDoctor(Long id) {
        return doctorDao.findOne(id);
    }

    @Override
    public List<Doctor> getSubDoctor(Doctor doctor) {
        return doctorDao.findAll((root, query, cb) -> cb.and(
                cb.equal(
                        root.get(Doctor_.primaryDoctorId),
                        doctor.getId()
                )
        ));
    }

    @Override
    public List<Doctor> findSubDoctor(Doctor doctor) {
        return doctorDao.findAll((root, query, cb) -> cb.and(
                cb.equal(
                        root.get(Doctor_.primaryDoctorId),
                        doctor.getId()
                )
        ));
    }


}
