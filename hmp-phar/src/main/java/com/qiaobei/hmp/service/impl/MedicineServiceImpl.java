package com.qiaobei.hmp.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.qiaobei.hmp.modules.entity.*;
import com.qiaobei.hmp.modules.service.IaiLossDetailService;
import com.qiaobei.hmp.modules.support.EntityTmpCloumsVal;
import com.qiaobei.hmp.repository.*;
import com.qiaobei.hmp.service.CompanyService;
import com.qiaobei.hmp.service.MedicineCountService;
import com.qiaobei.hmp.service.MedicineService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.javasimon.aop.Monitored;
import org.omg.CORBA.Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.utils.Numbers;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import java.util.*;
import java.util.stream.Collectors;

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


    @Resource
    private MedicinePrivateDao medicinePrivateDao;

    @Resource
    private IaiLossDetailService iaiLossDetailService;

    @Resource
    private CompanyService companyService;

    @Autowired
    private EntityManager entityManager;

    @Resource
    private DoctorDao doctorDao;


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
        //TODO: 增加修改日志
    }

    @Override
    public void saveMedicine(Medicine medicine, Doctor doctor, boolean tmp) {
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
        //TODO: 增加修改日志
    }

    @Override
    public Page<Medicine> pageTagByWestern(Pageable page) {
        return medicineDao.findPageByType(page, Medicine.Type.Western);
    }

    @Override
    public Page<Medicine> pageTagByChinese(Pageable page) {
        return medicineDao.findPageByType(page, Medicine.Type.Chinese);
    }

    @Override
    public Page<Medicine> pageTagByType(Pageable page, Medicine.Type type) {
        return medicineDao.findPageByType(page, type);
    }

    @Override
    public Page<Medicine> pageDoctorTag(Pageable page, Doctor doctor, Medicine.Type type) {
        return medicineDao.findPageByDoctorAndType(page, doctor, type);
    }

    @Override
    public Page<Medicine> pageOtherTag(Pageable page, Doctor doctor, Medicine.Type type) {
        return medicineDao.findPageByNotDoctorAndType(page, doctor, type);
    }

    @Override
    public Page<Medicine> pageDoctorTag(Pageable page, Doctor doctor, String name) {
        if (StringUtils.isEmpty(name)) {
            return medicineDao.findPageByDoctor(page, doctor);
        }
        return medicineDao.findPageByDoctorAndNameLike(page, doctor, "%" + name + "%");
    }

    @Override
    public Page<Medicine> pageOtherTag(Pageable page, Doctor doctor, String name) {

        if (StringUtils.isEmpty(name)) {
            return medicineDao.findPageByNotDoctor(page, doctor);
        }
        return medicineDao.findPageByNotDoctorAndNameLike(page, doctor, "%" + name + "%");
    }

    @Override
    public Page<Medicine> pageDoctorTag(Pageable page, Doctor doctor, Medicine.Type type, String name) {
        if (StringUtils.isEmpty(name)) {
            return medicineDao.findPageByDoctorAndType(page, doctor, type);
        }

        //return medicineDao.findPageByDoctorAndTypeAndNameLike(page, doctor, type, "%" + name + "%");
        return medicineDao.findPageByTypeAndNameLike(page, type, "%" + name + "%");
    }

    @Override
    public Page<Medicine> pageDoctorTagPost(Pageable page, Doctor doctor, Medicine.Type type, String name) {
        if (StringUtils.isEmpty(name)) {
            return medicineDao.findPageByDoctorAndType(page, doctor, type);
        }

        //return medicineDao.findPageByDoctorAndTypeAndNameLike(page, doctor, type, "%" + name + "%");
        return medicineDao.findPostPageByTypeAndNameLike(page, type, "%" + name + "%");
    }

    @Override
    public Page<Medicine> ListDoctorMedTag(Pageable page, Doctor doctor, Medicine.Type type, String name, String
            diagosisName) {
        List<Medicine> medicineList = null;
        List<MedicineCount> medicineCountList = null;
        if ("".equals(name) || null == name) {
            if (type == null) {
                medicineList = medicineDao.findAllMedList();
                medicineCountList = medicineCountService.getAllMedCountList();
            } else {
                medicineList = medicineDao.findAllMedByType(type);
                medicineCountList = medicineCountService.getMedCountListByType(type);
            }

        } else {
            if (type == null) {
                medicineList = medicineDao.findAllMedListAndNameLike("%" + name + "%");
                medicineCountList = medicineCountService.getMedCountListByTypeAndMedNameLike("%" + name + "%");
            } else {
                medicineList = medicineDao.findAllMedListByTypeAndNameLike(type, "%" + name + "%");
                medicineCountList = medicineCountService.getMedCountListByTypeAndMedNameLike(type, "%" + name + "%");
            }

        }
        List<Medicine> thisDoctorMedAndDiag = Lists.newArrayList();
        List<Medicine> thisDoctorMed = Lists.newArrayList();
        List<Medicine> otherDoctorMedAndDiag = Lists.newArrayList();
        List<Medicine> otherDoctorMed = Lists.newArrayList();
        List<Medicine> nedDelMed = Lists.newArrayList();

        if (medicineList != null && medicineList.size() > page.getPageSize()) {
            for (MedicineCount medicineCount : medicineCountList) {
                if (!medicineList.contains(medicineCount.getMedicine()))
                    continue;
                //删除总药品中与排序药品 相同的药
                //medicineList.remove(medicineCount.getMedicine());
                nedDelMed.add(medicineCount.getMedicine());
                medicineCount.getMedicine().setTmpCountSize(medicineCount.getCountSize());
                //属于该医生的药品
                if (medicineCount.getDoctor().getId().equals(doctor.getId())) {
                    //System.err.println("diagosisName:"+ diagosisName +"----"+ medicineCount.getMedicine().getName()
                    // +"----"+"medicineCount
                    // .getDiagosisName():"+ medicineCount.getDiagosisName());
                    if (null != diagosisName && medicineCount.getDiagosisName().contains(diagosisName)) {
                        //属于该医生 并且是这个疾病的药品
                        thisDoctorMedAndDiag.add(medicineCount.getMedicine());
                    } else {
                        //属于该医生 并且不是这个疾病的药品
                        thisDoctorMed.add(medicineCount.getMedicine());
                    }
                } else {
                    if (null != diagosisName && medicineCount.getDiagosisName().contains(diagosisName)) {
                        //不属于该医生 并且是这个疾病的药品
                        otherDoctorMedAndDiag.add(medicineCount.getMedicine());
                    } else {
                        //不属于该医生 并且不是这个疾病的药品
                        otherDoctorMed.add(medicineCount.getMedicine());
                    }
                }
            }
        }
        List<Medicine> oldMed = Lists.newArrayList(medicineList);
        if (medicineList != null)
            medicineList.clear();
        Comparator comparator = (Comparator<Medicine>) (o1, o2) -> {
            if (o1.getTmpCountSize() > o2.getTmpCountSize())
                return -1;
            else if (o1.getTmpCountSize() < o2.getTmpCountSize())
                return 1;
            return 0;
        };
        Collections.sort(thisDoctorMedAndDiag, comparator);
        Collections.sort(thisDoctorMed, comparator);
        Collections.sort(otherDoctorMedAndDiag, comparator);
        Collections.sort(otherDoctorMed, comparator);
        medicineList.addAll(thisDoctorMedAndDiag);
        medicineList.addAll(thisDoctorMed);
        medicineList.addAll(otherDoctorMedAndDiag);
        medicineList.addAll(otherDoctorMed);
        oldMed.removeAll(nedDelMed);
        medicineList.addAll(oldMed);

        LinkedHashSet<Medicine> hs = medicineList.stream().map(medicine ->
                new Medicine(medicine.getId(), medicine.getName())
        ).collect(Collectors.toCollection(LinkedHashSet::new));

        List spilList = Lists.newArrayList();
        List<Medicine> medicineListEnd = Lists.newArrayList();
        CollectionUtils.addAll(medicineListEnd, hs.toArray());
        if (hs.size() >= (page.getPageNumber() + 1) * page.getPageSize()) {
            for (int i = page.getPageNumber() * page.getPageSize();
                 i < (page.getPageNumber() + 1) * page.getPageSize();
                 i++) {
                spilList.add(medicineListEnd.get(i));
            }
        } else if (hs.size() > 0 && page.getPageNumber() * page.getPageSize() < hs.size()) {
            for (int i = page.getPageNumber() * page.getPageSize();
                 i < hs.size();
                 i++) {
                spilList.add(medicineListEnd.get(i));
            }
        } else {
            spilList.addAll(medicineListEnd);
        }


        Page<Medicine> medicinePage = new PageImpl<>(spilList, page, hs.size());
        return medicinePage;
    }



    @Override
    @Transactional(readOnly = true)
    public Page<Medicine> ListDoctorTag(Pageable page, Doctor doctor, Medicine.Type type, String name, String
            diagosisName) {
        Page<Medicine> medicinesPage = null;
        if ("".equals(name) || null == name) {
            medicinesPage = medicineDao.findListByType(page, type);
        } else {
            medicinesPage = medicineDao.findListByTypeAndNameLike(page, type, "%" + name + "%");
        }
        return medicinesPage;
    }

    @Override
    public Page<Medicine> pageOtherTag(Pageable page, Doctor doctor, Medicine.Type type, String name) {
        if (StringUtils.isEmpty(name)) {
            return medicineDao.findPageByNotDoctorAndType(page, doctor, type);
        }
        return medicineDao.findPageByNotDoctorAndTypeAndNameLike(page, doctor, type, "%" + name + "%");
    }

    @Override
    public Page<Medicine> pageByName(Pageable pageable, String name) {
        return medicineDao.findPageByNameLike(pageable, name);
    }

    @Override
    public void addMedicineToDoctor(Doctor doctor, Medicine medicine) {
        doctorMedicineDao.save(new DoctorMedicine(doctor, medicine));
    }

    @Override
    public void removeMedicineFromDoctor(Doctor doctor, Medicine medicine) {
        DoctorMedicine dm = doctorMedicineDao.findByDoctorAndMedicine(doctor, medicine);
        if (dm != null) {
            doctorMedicineDao.delete(dm);
        }
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
    public MedicineStock getMedicineStock(Doctor doctor, MedicinePrivate medicinePrivate) {
        MedicineStock medicineStock = new MedicineStock();
        MedicinePrivate tmpMedicinePrivate = medicinePrivate;

        if (tmpMedicinePrivate.getHaveManager() == MedicinePrivate.HaveManager.YES) {
            medicineStock.setHaveManager(true);
            List<EntityTmpCloumsVal> entityTmpCloumsValList = iaiLossDetailService.getIaiLossDetilStockByMedPaivate(tmpMedicinePrivate);
            if (CollectionUtils.isEmpty(entityTmpCloumsValList)) {
                return medicineStock;
            }
            Double kc = entityTmpCloumsValList.stream().mapToDouble(EntityTmpCloumsVal::getTotlenSize).sum();
            final Date[] date = {new Date()};
            final boolean[] isHave = {false};
            entityTmpCloumsValList.forEach(
                    entityTmpCloumsVal -> {
                        if (entityTmpCloumsVal.getTotlenSize() > 0 && entityTmpCloumsVal.getValidityDate().getTime() >= date[0].getTime()) {
                            medicineStock.setValidityDate(entityTmpCloumsVal.getValidityDate());
                            isHave[0] = true;
                        }
                    }
            );
            if (!isHave[0]) {
                if (entityTmpCloumsValList.size() > 0) {
                    medicineStock.setValidityDate(entityTmpCloumsValList.get(entityTmpCloumsValList.size() - 1).getValidityDate());
                }
                medicineStock.setExpire(true);
            }
            medicineStock.setStockNum(kc);
            medicineStock.setCompanyId(entityTmpCloumsValList.get(entityTmpCloumsValList.size() - 1).getCompanyId());
            //获得公司名
            Optional.ofNullable(medicineStock.getCompanyId()).ifPresent(id -> {
                Optional.ofNullable(companyDao.getOne(id)).ifPresent(company -> medicineStock.setCompanyName(company.getName()));
            });
            medicineStock.setBarCode(entityTmpCloumsValList.get(entityTmpCloumsValList.size() - 1).getBarCode());
        } else {
            medicineStock.setHaveManager(false);
        }

        return medicineStock;
    }

    @Override
    public Map<String, Object> prescribe(Doctor doctor, MedicinePrivate medicinePrivate) {
        Map tmpMap = Maps.newHashMap();
        MedicinePrivate tmpMedicinePrivate = medicinePrivate;

        if (tmpMedicinePrivate.getHaveManager() == MedicinePrivate.HaveManager.YES) {
            List<EntityTmpCloumsVal> entityTmpCloumsValList = iaiLossDetailService.getIaiLossDetilStockByMedPaivate(tmpMedicinePrivate);

            Double kc = entityTmpCloumsValList.stream().mapToDouble(EntityTmpCloumsVal::getTotlenSize).sum();
            final Date[] date = {new Date()};
            final boolean[] isHave = {false};
            entityTmpCloumsValList.forEach(
                    entityTmpCloumsVal -> {
                        if (entityTmpCloumsVal.getTotlenSize() > 0 && entityTmpCloumsVal.getValidityDate().getTime() >= date[0].getTime()) {
                            tmpMap.put("medicine_yxrq", entityTmpCloumsVal.getValidityDate());
                            isHave[0] = true;
                        }
                    }
            );
            if (!isHave[0]) {
                if (entityTmpCloumsValList.size() > 0) {
                    tmpMap.put("medicine_yxrq", entityTmpCloumsValList.get(entityTmpCloumsValList.size() - 1).getValidityDate());
                }
                tmpMap.put("medicine_guoQi", true);
            }
            tmpMap.put("medicine_kc", kc);
        }

        return tmpMap;
    }

    @Override
    public Page<Medicine> pageByDoctorAndCategory(Pageable page, Doctor doctor, Medicine.Type type, String category) {
        return medicineDao.findPageByDoctorAndTypeAndCategory(page, doctor, type, category);
    }
}

