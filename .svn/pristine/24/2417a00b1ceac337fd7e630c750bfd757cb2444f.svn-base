package com.qiaobei.hmp.service.impl;

import com.google.common.collect.Lists;
import com.qiaobei.hmp.modules.entity.*;
import com.qiaobei.hmp.repository.CompanyDao;
import com.qiaobei.hmp.repository.DoctorMedicineDao;
import com.qiaobei.hmp.repository.MedicineDao;
import com.qiaobei.hmp.service.MedicineCountService;
import com.qiaobei.hmp.service.MedicineService;
import org.javasimon.aop.Monitored;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.utils.Numbers;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.List;

/**
 * Created by yanbin on 11/1/15.
 */
@Service("medicineService")
@Transactional
@Monitored
public class MedicineServiceImpl implements MedicineService {
    @Resource
    private MedicineDao medicineDao;
    @Resource
    private DoctorMedicineDao doctorMedicineDao;
    @Resource
    private CompanyDao companyDao;
    @Resource
    private MedicineCountService medicineCountService;


    @Override
    public Medicine getMedicine(Long id) {
        return medicineDao.findOne(id);
    }


    @Override
    public void saveMedicine(Medicine medicine, Doctor doctor) {
        Company company = medicine.getDefaultCompany();
        if (company != null) {
            Long companyId = medicine.getDefaultCompany().getId();
            if (Numbers.isNotNullOrZero(companyId)) {
                company = companyDao.getOne(companyId);
                if (company != null) {
                    medicine.setDefaultCompany(company);
                    medicine.setDefaultCompanyName(company.getName());
                }
            }
        }
        if (medicine.getUsageFlag() == null) {
            if (medicine.getType() == Medicine.Type.Western) {
                medicine.setUsageFlag(true);
            } else {
                medicine.setUsageFlag(false);
            }
        }
        medicineDao.saveAndFlush(medicine);
        if (medicine.isUsed() && !isUsedMedicineForDoctor(doctor, medicine)) {
            addMedicineToDoctor(doctor, medicine);
        }

    }

    @Override
    public void saveMedicineUsage(Medicine medicine, Doctor doctor) {
        medicineDao.save(medicine);

    }

    @Override
    public void addMedicineToDoctor(Doctor doctor, Medicine medicine) {
        doctorMedicineDao.save(new DoctorMedicine(doctor, medicine));
    }

    @Override
    public void removeMedicineFromDoctor(Doctor doctor, Medicine medicine) {

    }

    @Override
    public Page<Medicine> pageDoctorTag(Pageable page, Doctor doctor, String name) {
        return null;
    }

    @Override
    public Page<Medicine> pageDoctorTag(Pageable page, Doctor doctor, Medicine.Type type) {
        return null;
    }

    @Override
    public Page<Medicine> pageDoctorTag(Pageable page, Doctor doctor, Medicine.Type type, String name) {
        return null;
    }

    public boolean isUsedMedicineForDoctor(Doctor doctor, Medicine medicine) {
        DoctorMedicine dm = doctorMedicineDao.findByDoctorAndMedicine(doctor, medicine);
        return dm != null;
    }

    @Override
    public Medicine isThisMedHaveSameInSystem(String medName) {
        return medicineDao.findByName(medName);
    }

    @Override
    public void updateUsageFlag(Medicine medicine) {
        medicineDao.save(medicine);
    }

    @Override
    public void updateCategory(Medicine medicine) {
        medicineDao.save(medicine);
    }

    @Override
    public Page<Medicine> getMedPageByTypeAndName(Pageable pageable, String name, Medicine.Type medType) {

        return medicineDao.findAll((root, query, cb) -> {
            List<Predicate> predicateList = Lists.newArrayList();
            predicateList.add(
                    cb.equal(root.get(Medicine_.type), medType)
            );
            if (null != name) {
                predicateList.add(
                        cb.and(
                                cb.or(
                                        cb.like(root.get(Medicine_.name), "%" + name + "%"),
                                        cb.like(root.get(Medicine_.helpCode), "%" + name + "%")
                                )
                        )
                );
            }
            return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
        }, pageable);
    }
}
