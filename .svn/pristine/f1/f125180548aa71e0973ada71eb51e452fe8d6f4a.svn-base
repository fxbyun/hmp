package com.qiaobei.hmp.service.impl;

import com.qiaobei.hmp.modules.entity.AppointConfig;
import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.repository.AppointConfigDao;
import com.qiaobei.hmp.service.AppointConfigService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2016/8/31 0031.
 */
@Service("appointConfigService")
@Transactional
public class AppointConfigServiceImpl implements AppointConfigService {

    @Resource
    private AppointConfigDao appointConfigDao;

    @Override
    public AppointConfig findByDoctor(Doctor doctor) {
        return appointConfigDao.findByDoctor(doctor);
    }

    @Override
    public void save(AppointConfig appointConfig) {
        appointConfigDao.save(appointConfig);
    }
}
