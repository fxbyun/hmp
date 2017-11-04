package com.qiaobei.hmp.service.impl;

import com.qiaobei.hmp.modules.entity.AppointWeekConfig;
import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.repository.AppointWeekConfigDao;
import com.qiaobei.hmp.service.AppointWeekConfigService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by IntelliJ IDEA 2016.4
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 2016/8/31 0031
 * Time 14:56
 */
@Service("appointWeekConfigService")
@Transactional
public class AppointWeekConfigServiceImpl implements AppointWeekConfigService {

    @Resource
    private AppointWeekConfigDao appointWeekConfigDao;

    @Override
    public List<AppointWeekConfig> findByDoctor(Doctor doctor) {
        return appointWeekConfigDao.findByDoctor(doctor);
    }

    @Override
    public AppointWeekConfig findByDoctorAndWeekday(Doctor doctor, AppointWeekConfig.Weekday weekday) {
        return appointWeekConfigDao.findByDoctorAndWeekday(doctor, weekday);
    }

    @Override
    public List<AppointWeekConfig> findAllOrderByWeekday(Doctor doctor) {
        return appointWeekConfigDao.findByDoctorOrderByWeekdayAsc(doctor);
    }

    @Override
    public void save(AppointWeekConfig appointWeekConfig) {
        appointWeekConfigDao.save(appointWeekConfig);
    }

    @Override
    public boolean weekIsSelect(Doctor doctor, AppointWeekConfig.Weekday weekday) {
        AppointWeekConfig appointWeekConfig = appointWeekConfigDao.findByDoctorAndWeekday(doctor, weekday);
        return appointWeekConfig.getOpenStatic().toString().equals("Open");

    }
}
