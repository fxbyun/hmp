package com.qiaobei.hmp.service.impl;

import com.qiaobei.hmp.modules.entity.Pharmacist;
import com.qiaobei.hmp.repository.PharmacistDao;
import com.qiaobei.hmp.service.PharmacistService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("pharmacistService")
@Transactional
public class PharmacistServiceImpl implements PharmacistService {

    @Resource
    private PharmacistDao pharmacistDao;

    @Override
    @Transactional(readOnly = true)
    public Pharmacist getPharmacistById(Long id) {
        return pharmacistDao.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Pharmacist getPharmacistByWxId(String wxId) {
        return pharmacistDao.findByWxId(wxId);
    }

    @Override
    @Transactional(readOnly = true)
    public Pharmacist getPharmacistByAccount(String account) {
        return pharmacistDao.findByAccount(account);
    }

    @Override
    public void savePharmacist(Pharmacist p) {
        pharmacistDao.save(p);
    }

    @Override
    public void delPharmacist(Long id) {
        pharmacistDao.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pharmacist> getPharmacistByDoctor(Long doctorId) {
        return pharmacistDao.findByDoctorId(doctorId);
    }
}
