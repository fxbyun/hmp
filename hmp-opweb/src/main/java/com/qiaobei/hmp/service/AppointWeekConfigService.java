package com.qiaobei.hmp.service;

import com.qiaobei.hmp.modules.entity.AppointWeekConfig;
import com.qiaobei.hmp.modules.entity.Doctor;

import java.util.List;

/**
 * Created by Administrator on 2016/8/31 0031.
 */
public interface AppointWeekConfigService {
    public List<AppointWeekConfig> findByDoctor(Doctor doctor);
    public AppointWeekConfig findByDoctorAndWeekday(Doctor doctor, AppointWeekConfig.Weekday weekday);
    public List<AppointWeekConfig> findAllOrderByWeekday(Doctor doctor);
    public void save(AppointWeekConfig a);
    public boolean weekIsSelect(Doctor doctor, AppointWeekConfig.Weekday weekday);
}
