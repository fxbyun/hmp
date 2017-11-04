package com.qiaobei.hmp.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.Emr;
import com.qiaobei.hmp.modules.entity.Pharmacist;
import com.qiaobei.hmp.repository.DoctorDao;
import com.qiaobei.hmp.repository.PharmacistDao;
import com.qiaobei.hmp.service.PharmacistService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("pharmacistService")
@Transactional
public class PharmacistServiceImpl implements PharmacistService {

    @Resource
    private PharmacistDao pharmacistDao;

    @Resource
    private DoctorDao doctorDao;

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

    @Override
    public Map<Pharmacist.PersonType, String> personTypeNameMap() {
        Map<Pharmacist.PersonType, String> nameMap = Maps.newHashMap();
        nameMap.put(Pharmacist.PersonType.Cashier, "收银员");
        nameMap.put(Pharmacist.PersonType.Nurse, "护士");
        nameMap.put(Pharmacist.PersonType.Wecath_dispenser, "药剂师");
        nameMap.put(Pharmacist.PersonType.Sub_Doctor, "医生子帐号");
        nameMap.put(Pharmacist.PersonType.Inspect_Doctor, "检查医生");
        return nameMap;
    }

    @Override
    public List<Pharmacist> findAllCashier(Doctor doctor) {
        List<Pharmacist> cashierList = Lists.newArrayList();
        findByBossDoctor(doctor).forEach(phar -> {
            //如果是收银员
            if (phar.getPersonType() == Pharmacist.PersonType.Nurse && null != phar.getName()) {
                cashierList.add(phar);
            }
        });
        return cashierList;
    }

    @Override
    public List<Pharmacist> findByBossDoctor(Doctor doctor) {
        //非主治医生转为主治
        if (doctor.getDoctorType() == Doctor.Doctor_Type.Sub_Doctor) {
            doctor = doctorDao.getOne(doctor.getPrimaryDoctorId());
        }
        return pharmacistDao.findByDoctorId(doctor.getId());
    }

    @Override
    public Map<Emr, Pharmacist> getCashierMapByEmrList(List<Emr> emrList) {
        Map<Emr, Pharmacist> cashierMap = Maps.newHashMap();
        emrList.forEach(emr -> {
            if (emr.getCashierId() != null && 0 != emr.getCashierId()) {
                Pharmacist pharmacist;
                try {
                    pharmacist = pharmacistDao.getOne(emr.getCashierId());
                    pharmacist.getName();
                } catch (Exception e) {
                    pharmacist = null;
                }
                cashierMap.put(emr, pharmacist);

            } else {
                cashierMap.put(emr, null);
            }
        });
        return cashierMap;
    }

    @Override
    public Pharmacist getByEmr(Emr emr) {
        return pharmacistDao.getOne(emr.getCashierId());
    }

    @Override
    public void update(Pharmacist pharmacist) {
        pharmacistDao.save(pharmacist);
    }
}
