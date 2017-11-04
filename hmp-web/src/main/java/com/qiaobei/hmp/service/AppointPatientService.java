package com.qiaobei.hmp.service;

import com.qiaobei.hmp.modules.entity.AppointPatient;

import java.util.List;

/**
 * Created by Administrator on 2016/8/31 0031.
 */
public interface AppointPatientService {
    public void saveAppointPatient(AppointPatient appointPatient);

    public List<AppointPatient> findAppointPatientByListId(Long listId);

    public AppointPatient findById(Long id);

    //查找昨天的预约单
    public List<AppointPatient> findYesterdayAppointPatientList();

    public void delete(Long id);

    public void delete(List<AppointPatient> appointPatientList);

    public List<AppointPatient> findThreeDayBefore();
}
