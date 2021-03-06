package com.qiaobei.hmp.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.qiaobei.hmp.modules.entity.*;
import com.qiaobei.hmp.modules.entity.MedicinePrivate_;
import com.qiaobei.hmp.redis.MedicineCountCache;
import com.qiaobei.hmp.repository.CompanyDao;
import com.qiaobei.hmp.repository.DoctorMedicineDao;
import com.qiaobei.hmp.repository.MedicineDao;
import com.qiaobei.hmp.repository.MedicinePrivateDao;
import com.qiaobei.hmp.service.MedicineCountService;
import com.qiaobei.hmp.service.MedicineService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.utils.Numbers;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA 2016.4
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 11/1/15
 * Time 13:14
 */
@Service("medicineService")
@Transactional
//这个注解为什么有时候会报错啊
//@Monitored
public class MedicineServiceImpl implements MedicineService {
    @Resource
    private MedicineDao medicineDao;
    @Resource
    private MedicinePrivateDao medicinePrivateDao;
    @Resource
    private DoctorMedicineDao doctorMedicineDao;
    @Resource
    private CompanyDao companyDao;
    @Resource
    private MedicineCountService medicineCountService;

    @Resource
    private MedicineCountCache medicineCountCache;

    private static List<MedicineCount> westernCountsStatic;
    private static List<MedicineCount> ChineseCountsStatic;

    private static Map<Long,List<Medicine>> doctorMedicineMap = Maps.newConcurrentMap();

//    @Autowired
//    private EntityManager entityManager;

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
        return medicineDao.findPostPageByTypeAndNameLike(page, type, "%" + name + "%");
    }

    /**
     * = = 我最大的梦想就是优化此处的代码 奈何老板一直紧急要求开发其他东西,(同事吐槽：你大爷的，赶时间就能写成这样，坑爹啊！)
     * 没时间搞这里,后来的仁兄,如果你优化好了这儿的代码 ,请务必告诉我一声.
     * 此处推荐用 流 结合 querydls或者springDataJpa来做
     * 在下主页 https://www.java.sx  感激不尽哇!
     *
     * @param page
     * @param doctor
     * @param type
     * @param name
     * @param diagosisName
     * @return
     */
    @Override
    public Page<Medicine> listDoctorMedTag(Pageable page, Doctor doctor, Medicine.Type type, String name, String
            diagosisName) {
//        return listDoctorMedTagNotSmart(page);
//        //TODO 临时注释
        List<Medicine> medicineList = Lists.newArrayList();
        List<MedicineCount> medicineCountList = Lists.newArrayList();

        if(type == Medicine.Type.Western){
            if(westernCountsStatic == null || westernCountsStatic.size()<=0){
                medicineCountList = medicineCountService.getMedCountListByType(type);
                westernCountsStatic = medicineCountList;
            }else {
                medicineCountList = westernCountsStatic;
            }
        }

        if(type == Medicine.Type.Chinese){
            if(ChineseCountsStatic == null || ChineseCountsStatic.size()<=0){
                medicineCountList = medicineCountService.getMedCountListByType(type);
                ChineseCountsStatic = medicineCountList;
            }else {
                medicineCountList = ChineseCountsStatic;
            }
        }




//        medicineCountList=medicineCountCache.getRedisTemplate().opsForList().range("TEST2",0,1000);
//
//
//
//        if(medicineCountList==null || medicineCountList.size()<=0){
//            ListOperations<String,MedicineCount> operation=medicineCountCache.getRedisTemplate().opsForList();
//            medicineCountList = medicineCountService.getMedCountListByType(type);
//
//
//
//            medicineCountList.forEach(medicineCount -> {
//                //medicineCount.setDoctor(null);
//                operation.rightPush("TEST2",medicineCount);
//
//            });
//
//        }

        if ("".equals(name) || null == name) {
            if (type == null) {
                if (doctor.getDoctorType() == Doctor.Doctor_Type.Clinic_Boss) {
                    medicineList = medicinePrivateDao.findByDoctor(
                            doctor).stream().map(
                            MedicinePrivate::getMedicine
                    ).collect(Collectors.toList());

                } else if (doctor.getDoctorType() == Doctor.Doctor_Type.Sub_Doctor) {
                    medicineList = medicinePrivateDao.findByDoctor(
                            new Doctor(doctor.getPrimaryDoctorId())).stream().map(
                            MedicinePrivate::getMedicine
                    ).collect(Collectors.toList());
                } else {
                    medicineList = medicineDao.findAllMedList();
                }
                medicineCountList = medicineCountService.getAllMedCountList();
            } else {

                if (doctor.getDoctorType() == Doctor.Doctor_Type.Clinic_Boss) {


                    //doctorMedicineMap.put(doctor.getId(),medicineList);

                    if(doctorMedicineMap !=null && doctorMedicineMap.size()>0){
                        medicineList = doctorMedicineMap.get(doctor.getId());
                    }

                    if(medicineList.size() <= 0){
                        medicineList = medicinePrivateDao.findByTypeAndDoctor(type, doctor).stream().map(
                                MedicinePrivate::getMedicine
                        ).collect(Collectors.toList());
                        doctorMedicineMap.put(doctor.getId(),medicineList);
                    }

                } else if (doctor.getDoctorType() == Doctor.Doctor_Type.Sub_Doctor) {
                    medicineList = medicinePrivateDao.findByTypeAndDoctor(type, new Doctor(doctor.getPrimaryDoctorId())).stream().map(
                            MedicinePrivate::getMedicine
                    ).collect(Collectors.toList());
                } else {
                    medicineList = medicineDao.findAllMedByType(type);
                }
                //medicineCountList = medicineCountService.getMedCountListByType(type);
            }

        } else {
            if (type == null) {
                if (doctor.getDoctorType() == Doctor.Doctor_Type.Clinic_Boss) {
                    medicineList = medicinePrivateDao.findAll((root, query, cb) -> cb.and(
                            cb.equal(
                                    root.get(MedicinePrivate_.doctor),
                                    doctor
                            ),
                            cb.or(
                                    cb.like(
                                            root.get(MedicinePrivate_.name),
                                            "%" + name + "%"
                                    ), cb.like(
                                            root.get(MedicinePrivate_.helpCode),
                                            "%" + name + "%"
                                    )
                            )
                    )).stream().map(MedicinePrivate::getMedicine).collect(Collectors.toList());
                } else if (doctor.getDoctorType() == Doctor.Doctor_Type.Sub_Doctor) {
                    medicineList = medicinePrivateDao.findAll((root, query, cb) -> cb.and(
                            cb.equal(
                                    root.get(MedicinePrivate_.doctor),
                                    new Doctor(doctor.getPrimaryDoctorId())
                            ),
                            cb.or(
                                    cb.like(
                                            root.get(MedicinePrivate_.name),
                                            "%" + name + "%"
                                    ), cb.like(
                                            root.get(MedicinePrivate_.helpCode),
                                            "%" + name + "%"
                                    )
                            )
                    )).stream().map(MedicinePrivate::getMedicine).collect(Collectors.toList());
                } else {
                    medicineList = medicineDao.findAllMedListAndNameLike("%" + name + "%");
                }
                medicineCountList = medicineCountService.getMedCountListByTypeAndMedNameLike("%" + name + "%");
            } else {

                if (doctor.getDoctorType() == Doctor.Doctor_Type.Clinic_Boss) {
                    medicineList = medicinePrivateDao.findAll((root, query, cb) -> cb.and(
                            cb.equal(
                                    root.get(MedicinePrivate_.doctor),
                                    doctor
                            ),
                            cb.equal(
                                    root.get(MedicinePrivate_.type),
                                    type
                            ),
                            cb.or(
                                    cb.like(
                                            root.get(MedicinePrivate_.name),
                                            "%" + name + "%"
                                    ), cb.like(
                                            root.get(MedicinePrivate_.helpCode),
                                            "%" + name + "%"
                                    )
                            )
                    )).stream().map(MedicinePrivate::getMedicine).collect(Collectors.toList());

                } else if (doctor.getDoctorType() == Doctor.Doctor_Type.Sub_Doctor) {
                    medicineList = medicinePrivateDao.findAll((root, query, cb) -> cb.and(
                            cb.equal(
                                    root.get(MedicinePrivate_.doctor),
                                    new Doctor(doctor.getPrimaryDoctorId())
                            ),
                            cb.equal(
                                    root.get(MedicinePrivate_.type),
                                    type
                            ),
                            cb.or(
                                    cb.like(
                                            root.get(MedicinePrivate_.name),
                                            "%" + name + "%"
                                    ), cb.like(
                                            root.get(MedicinePrivate_.helpCode),
                                            "%" + name + "%"
                                    )
                            )
                    )).stream().map(MedicinePrivate::getMedicine).collect(Collectors.toList());
                } else {
                    medicineList = medicineDao.findAllMedListByTypeAndNameLike(type, "%" + name + "%");
                }
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
                nedDelMed.add(medicineCount.getMedicine());
                medicineCount.getMedicine().setTmpCountSize(medicineCount.getCountSize());
                //属于该医生的药品
                if (medicineCount.getDoctor().getId().equals(doctor.getId())) {
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
        assert medicineList != null;
        List<Medicine> oldMed = Lists.newArrayList(medicineList);
        medicineList.clear();
        Comparator<Medicine> comparator = (o1, o2) -> {
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

        List<Medicine> spilList = Lists.newArrayList();
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


        return new PageImpl<>(spilList, page, hs.size());
    }


    public Page<Medicine> listDoctorMedTagNotSmart(Pageable page) {

        return medicineDao.findAll(page);
    }


    @Override
    @Transactional(readOnly = true)
    public Page<Medicine> ListDoctorTag(Pageable page, Doctor doctor, Medicine.Type type, String name, String
            diagosisName) {
        Page<Medicine> medicinesPage;
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
    public Page<Medicine> pageByDoctorAndCategory(Pageable page, Doctor doctor, Medicine.Type type, String category) {
        return medicineDao.findPageByDoctorAndTypeAndCategory(page, doctor, type, category);
    }

    @Override
    public Medicine getMedicineByName(String name) {
        return medicineDao.findByName(name);
    }
}
