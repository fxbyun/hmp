package com.qiaobei.hmp.service.impl;

import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.Emr;
import com.qiaobei.hmp.repository.EmrDao;
import com.qiaobei.hmp.service.EmrService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("emrService")
public class EmrServiceImpl implements EmrService {

    private static Logger logger = LoggerFactory.getLogger(EmrServiceImpl.class);

    @Resource
    private EmrDao emrDao;

    @Override
    public Long count() {
        return emrDao.count();
    }

    @Override
    public Boolean isDoctorHasEmr(Doctor doctor) {
        Page<Emr> emrPage = emrDao.findEmrByDoctor(doctor, new PageRequest(0, 1));
        if (CollectionUtils.isNotEmpty(emrPage.getContent())) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Long getPatientCountByDoctor(Long doctorId) {
        return emrDao.getPatientCount(doctorId);
    }
}
