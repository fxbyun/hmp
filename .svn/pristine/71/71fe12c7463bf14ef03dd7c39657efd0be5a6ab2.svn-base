package com.qiaobei.hmp.service.impl;

import com.qiaobei.hmp.modules.entity.AppointPatient;
import com.qiaobei.hmp.repository.AppointPatientDao;
import com.qiaobei.hmp.service.AppointPatientService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by IntelliJ IDEA 2016.4
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 2016/8/31 0031
 * Time 14:55
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
    public List<AppointPatient> findThreeDayBefore() {
        return appointPatientDao.findThreeDayBefore();
    }

    @Override
    public void delete(List<AppointPatient> appointPatientList) {
        appointPatientDao.delete(appointPatientList);
    }

    @Override
    public void delete(Long id) {
        appointPatientDao.delete(id);
    }

    @Override
    public List<AppointPatient> findYesterdayAppointPatientList() {
        return appointPatientDao.findYesterday();
    }

    @Override
    public AppointPatient findById(Long id) {
        return appointPatientDao.findById(id);
    }


}
