package com.qiaobei.hmp.service;

import com.qiaobei.hmp.modules.entity.AppointPatient;
import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.Patient;

import java.util.List;

/**
 * Created by Administrator on 2016/8/31 0031.
 */
public interface AppointPatientService {
    public void saveAppointPatient(AppointPatient appointPatient);

    public List<AppointPatient> findAppointPatientByListId(Long listId);

    public AppointPatient findById(Long id);

    //根据listId找到 id最小的 appointPatient
    public AppointPatient findMinIdByListId(Long listId);

    public List<AppointPatient> findPatientByMobile(String mobile);


}
