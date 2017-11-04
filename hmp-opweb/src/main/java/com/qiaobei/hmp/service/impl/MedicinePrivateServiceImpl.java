package com.qiaobei.hmp.service.impl;

import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.Medicine;
import com.qiaobei.hmp.modules.entity.MedicinePrivate;
import com.qiaobei.hmp.repository.MedicinePrivateDao;
import com.qiaobei.hmp.service.MedicinePrivateService;
import org.javasimon.aop.Monitored;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by teemoer@cntv.cn on 2016/6/17 0017.
 */
@Service("medicinePrivateService")
@Transactional
@Monitored
public class MedicinePrivateServiceImpl implements MedicinePrivateService {
    @Resource
    MedicinePrivateDao medicinePrivateDao;


    @Override
    public MedicinePrivate getMedPriByMedId(Long id, Doctor doctor) {
        return medicinePrivateDao.findByMedAndDoctor(new Medicine(id), doctor);
    }

    @Override
    public void save(MedicinePrivate medicinePrivate) {
        medicinePrivateDao.save(medicinePrivate);
    }

    @Override
    public void update(MedicinePrivate medicinePrivate) {
        medicinePrivateDao.save(medicinePrivate);
    }

    @Override
    public MedicinePrivate getMedPrivate(Long id) {
        return medicinePrivateDao.getOne(id);
    }
}
