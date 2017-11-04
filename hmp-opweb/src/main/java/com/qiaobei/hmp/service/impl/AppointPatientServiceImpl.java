package com.qiaobei.hmp.service.impl;

import com.qiaobei.hmp.modules.entity.AppointPatient;
import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.Patient;
import com.qiaobei.hmp.repository.AppointPatientDao;
import com.qiaobei.hmp.service.AppointPatientService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2016/8/31 0031.
 */
@Service("appointPatientService")
@Transactional
public class AppointPatientServiceImpl implements AppointPatientService {

    @Resource
    private AppointPatientDao appointPatientDao;


    @Override
    public void saveAppointPatient(AppointPatient appointPatient) {
        appointPatientDao.save(appointPatient);
    }

    @Override
    public List<AppointPatient> findAppointPatientByListId(Long listId) {
        return appointPatientDao.findAppointPatientByAppointListId(listId);
    }

    @Override
    public AppointPatient findById(Long id) {
        return appointPatientDao.findById(id);
    }

    @Override
    public AppointPatient findMinIdByListId(Long listId) {
        return appointPatientDao.findById(appointPatientDao.findMinIdByListId(listId));
    }

    @Override
    public List<AppointPatient> findPatientByMobile(String mobile) {
        return appointPatientDao.findPatientByMobile(mobile);
    }


}
