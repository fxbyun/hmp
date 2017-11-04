package com.qiaobei.hmp.service.impl;

import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.repository.DoctorDao;
import com.qiaobei.hmp.service.DoctorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("doctorService")
@Transactional
public class DoctorServiceImpl implements DoctorService {

    private static Logger logger = LoggerFactory.getLogger(DoctorServiceImpl.class);

    @Resource
    private DoctorDao doctorDao;

    @Override
    public Doctor getDoctor(Long id) {
        return doctorDao.getOne(id);
    }

}
