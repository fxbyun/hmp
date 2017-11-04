package com.qiaobei.hmp.service.impl;

import com.qiaobei.hmp.modules.entity.AppointConfig;
import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.repository.AppointConfigDao;
import com.qiaobei.hmp.service.AppointConfigService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by IntelliJ IDEA 2016.4
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 2016/8/31 0031
 * Time 14:54
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
