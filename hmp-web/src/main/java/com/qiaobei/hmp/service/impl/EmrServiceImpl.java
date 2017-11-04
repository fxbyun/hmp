package com.qiaobei.hmp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.qiaobei.hmp.modules.entity.*;
import com.qiaobei.hmp.modules.service.IaiLossDetailService;
import com.qiaobei.hmp.modules.support.DateUtils;
import com.qiaobei.hmp.redis.WxCache;
import com.qiaobei.hmp.redis.WxInfo;
import com.qiaobei.hmp.repository.*;
import com.qiaobei.hmp.service.DoctorService;
import com.qiaobei.hmp.service.EmrService;
import com.qiaobei.hmp.support.DateFilter;
import com.qiaobei.hmp.support.TemplateWX;
import com.qiaobei.hmp.support.WeixinUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.utils.Clock;
import org.springside.modules.utils.Collections3;
import org.springside.modules.utils.Numbers;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service("emrService")
@Transactional
public class EmrServiceImpl implements EmrService {

    private static Logger logger = LoggerFactory.getLogger(EmrServiceImpl.class);
    private final WxCache wxCache;
    private Clock clock = Clock.DEFAULT;
    @Resource
    private EmrDao emrDao;
    @Resource
    private DepartmentDao departmentDao;
    @Resource
    private MedicineDao medicineDao;
    @Resource
    private CompanyDao companyDao;
    @Resource
    private PatientDao patientDao;
    @Resource
    private PharmacistDao pharmacistDao;
    @Resource
    private DoctorDao doctorDao;
    @Resource
    private SymptomTagDao symptomTagDao;
    @Resource
    private DiagnosisTagDao diagnosisTagDao;
    @Resource
    private SuggestDao suggestDao;
    @Resource
    private DoctorService doctorService;
    @Resource
    private IaiLossDetailService iaiLossDetailService;

    @Autowired
    public EmrServiceImpl(WxCache wxCache) {
        this.wxCache = wxCache;
    }


    @Override
    @Transactional(readOnly = true)
    public Emr getEmr(Long id) {
        return emrDao.getOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Long getLastEmrId(Long doctorId) {
        return emrDao.getLastId(doctorId);
    }

    @Override
    @Transactional(readOnly = true)
    public Long getPreviousEmrId(Long doctorId, Long id) {
        return emrDao.getPreviousId(doctorId, id);
    }

    @Override
    @Transactional(readOnly = true)
    public Long getNextEmrId(Long doctorId, Long id) {
        return emrDao.getNextId(doctorId, id);
    }

    @Override
    public void saveEmr(Emr emr) {
        iaiLossDetailService.updataStatus(StatusEntity.Status.Removed, emr.getId());
        Department dept = departmentDao.getOne(emr.getDepartmentId());
        Date now = clock.getCurrentDate();
        if (Numbers.isNullOrZero(emr.getId())) {
            if (dept != null) {
                emr.setDepartmentName(dept.getName());
            }
            Patient patient = patientDao.findByUid(emr.getPatientUid()).get(0);
            if (patient == null) {
                patient = patientDao.findOne(emr.getPatient().getId());
            }
            emr.setPatient(patient);
            emr.setPatientName(patient.getName());
            emr.setCreateOn(now);
            //增加积分
            Doctor doctor = doctorDao.findOne(emr.getDoctor().getId());
            int integral = doctor.getIntegral() == null ? 0 : doctor.getIntegral();
            int integration = doctor.getIntegration() == null ? 0 : doctor.getIntegration();
            doctor.setIntegral(integral + 50);
            doctor.setIntegration(integration + 5);
            doctorDao.save(doctor);
        }

        for (VitalSign vitalSign : emr.getVitalSignList()) {
            vitalSign.setCreateOn(now);
            vitalSign.setPatientUid(emr.getPatientUid());
            vitalSign.setPatientName(emr.getPatientName());
            vitalSign.setEmr(emr);
        }
        for (Diagnosis diagnosis : emr.getDiagnosisList()) {
            diagnosis.setEmr(emr);
            diagnosis.setCreateOn(now);
        }

        for (EmrMedicine item : emr.getEmrMedicineList()) {
            Medicine medicine = medicineDao.findOne(item.getMedicine().getId());
            Company company = companyDao.findOne(item.getCompany().getId());
            item.setDoctorId(emr.getDoctor().getId());
            item.setDoctorName(emr.getDoctorName());
            item.setCreateOn(now);

            //如果是中医理疗的话以下设置就免了吧！！
            if (item.getMedicineType() == Medicine.Type.ChineseTherapy) {
                continue;
            }

            //换算成标准单位的数量，便于统计，保留两位小数
            item.setRealUnit(medicine.getUnit());
            BigDecimal bd = new BigDecimal(item.getQty());
//            Double text = item.getRate();
            BigDecimal bdRate = new BigDecimal(item.getRate());
            BigDecimal bdCopies = new BigDecimal(item.getCopies());
            //中药乘以副数
            if (medicine.getType() == Medicine.Type.Chinese) {
                if (emr.getChineseQty() == 0)
                    emr.setChineseQty(1);
                bdCopies = bdCopies.multiply(new BigDecimal(emr.getChineseQty()));
            } else {
                emr.setWesternQty(1);
            }
            BigDecimal realQty = bd.multiply(bdCopies).divide(bdRate, 2, BigDecimal.ROUND_HALF_UP);
            item.setRealQty(realQty.doubleValue());
            item.setMedicine(medicine);
            item.setCompany(company);
            item.setEmr(emr);
        }
        for (EmrSuggest item : emr.getEmrSuggestList()) {
            Suggest suggest = suggestDao.findOne(item.getSuggestId());
            item.setSuggestContent(suggest.getContent());
            item.setEmr(emr);
        }
        emrDao.save(emr);

        if (Collections3.isNotEmpty(emr.getSymptomTagIds())) {
            symptomTagDao.updateTagFrequency(emr.getSymptomTagIds());
        }

        if (Collections3.isNotEmpty(emr.getDiagnosisTagIds())) {
            diagnosisTagDao.updateTagFrequency(emr.getDiagnosisTagIds());
        }
        try {
            //发微信给药剂师
            if (!sendWXToPhar(emr)) {
                sendWXToPhar(emr);
            }
            sendWXToOp(emr);   //发微信给患者
        } catch (Exception e) {
            logger.error("Send WX message error:" + e.getMessage());
        }
    }


    @SafeVarargs
    private final Specification<Emr> buildSpecification(final Long doctorId, final String patientUid, final String
            patientName, final Boolean replied, final DateFilter dateFilter, final Long
                                                                patientId, List<Doctor>... subDoctorList) {
        return (root, query, cb) -> {
            List<Predicate> predicates = Lists.newArrayList();
            if (StringUtils.isNotEmpty(patientUid)) {
                predicates.add(cb.equal(root.get("patientUid").as(String.class), patientUid));
            }

            if (StringUtils.isNotEmpty(patientName)) {

                predicates.add(
                        cb.and(
                                cb.or(
                                        cb.like(root.join(Emr_.patient).get(Patient_.helpCode), "%" + patientName + "%"),
                                        cb.like(root.get("patientName").as(String.class), "%" + patientName + "%"))
                        )
                );
            }

            if (replied != null) {
                predicates.add(cb.equal(root.get("replied").as(Boolean.class), replied));
            }
            if (null != dateFilter && dateFilter.isValid()) {
//                predicates.add(cb.between(root.get("createOn").as(Date.class), dateFilter.getStartDate(),
//                        dateFilter.getRealEndDate()));
                predicates.add(
                        cb.greaterThanOrEqualTo(
                                root.get(Emr_.createOn),
                                dateFilter.getStartDate()
                        )
                );
                predicates.add(
                        cb.lessThanOrEqualTo(
                                root.get(Emr_.createOn),
                                dateFilter.getEndDate()
                        )
                );
            }
            if (subDoctorList.length > 0 && subDoctorList[0].size() > 0) {
                CriteriaBuilder.In<Doctor> in = cb.in(root.get("doctor").as(Doctor.class));
                for (int i = 0; i < subDoctorList[0].size(); i++) {
                    in.value(
                            subDoctorList[0].get(i)
                    );
                }
                predicates.add(in);
            } else {
                if (Numbers.isNotNullOrZero(doctorId)) {
                    predicates.add(cb.equal(root.get("doctor").as(Doctor.class), new Doctor(doctorId)));
                }
            }


            if (Numbers.isNotNullOrZero(patientId)) {
                predicates.add(cb.equal(root.get("patient").as(Patient.class), new Patient(patientId)));
            }


            if (!predicates.isEmpty()) {
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
            return cb.conjunction();
        };
    }


    @Override
    public Page<Emr> findCountByPatient(Pageable page, Doctor doctor, String patientName, DateFilter dateFilter,
                                        DateFilter ageFilter, Gender
                                                genderSex, String diagonsisName, Long... subDoctorId) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDateCreate;
        Date startDateBr;
        try {
            startDateCreate = sdf.parse("2000-01-01");
            startDateBr = sdf.parse("1901-01-01");
        } catch (ParseException e) {
            startDateCreate = null;
            startDateBr = null;
        }
        Date endDateCreate = new Date();
        Date endDateBr = new Date();
        if (null != dateFilter && dateFilter.isValid()) {
            startDateCreate = dateFilter.getStartDate();
            endDateCreate = dateFilter.getRealEndDate();
        }
        if (null != ageFilter && ageFilter.isValid()) {
            startDateBr = ageFilter.getStartDate();
            endDateBr = ageFilter.getRealEndDate();
        }

        if (null == diagonsisName) {
            diagonsisName = "";
        }
        if (null == patientName) {
            patientName = "";
        }

        List<Doctor> doctorList = Lists.newArrayList();
        if (doctor.getDoctorType() == Doctor.Doctor_Type.Clinic_Boss) {
            if (subDoctorId.length > 0) {
                if (subDoctorId[0] != null) {
                    doctorList.addAll(
                            doctorService.findSubDoctor(doctor).stream().filter(
                                    doctor1 -> doctor1.getId().equals(subDoctorId[0])
                            ).collect(Collectors.toSet())
                    );
                    if (subDoctorId[0].equals(doctor.getId())) {
                        doctorList.add(doctor);
                    }
                } else {
                    doctorList.addAll(
                            doctorService.findSubDoctor(doctor)
                    );
                    doctorList.add(doctor);
                }

            } else {
                doctorList.addAll(
                        doctorService.findSubDoctor(doctor)
                );
                doctorList.add(doctor);
            }

        } else {
            doctorList.add(doctor);
        }

        if (genderSex != null) {
            return emrDao.findCountByPatientHaveGender(page, startDateCreate,
                    endDateCreate,
                    "%" + diagonsisName + "%",
                    doctorList,
                    startDateBr,
                    endDateBr,
                    "%" + patientName + "%",
                    StatusEntity.Status.Tmp,
                    genderSex);
        }

        return emrDao.findCountByPatientNoGender(
                page, startDateCreate,
                endDateCreate,
                "%" + diagonsisName + "%",
                doctorList,
                startDateBr,
                endDateBr,
                StatusEntity.Status.Tmp,
                "%" + patientName + "%");
    }

//    private Specification<Emr> buildPatienManagerSpecification(final Long doctorId, final String patientName,
//                                                               final DateFilter dateFilter, final DateFilter
//                                                                       ageFilter, final Gender genderSex, final
//                                                               String diagonsisName) {
//        Specification<Emr> spec = (root, query, cb) -> {
//            List<Predicate> predicates = Lists.newArrayList();
//            if (StringUtils.isNotEmpty(patientName)) {
//                predicates.add(cb.like(root.get("patientName").as(String.class), "%" + patientName + "%"));
//            }
//            if (null != dateFilter && dateFilter.isValid()) {
//                predicates.add(cb.between(root.get("createOn").as(Date.class), dateFilter.getStartDate(),
//                        dateFilter.getRealEndDate()));
//            }
//            if (Numbers.isNotNullOrZero(doctorId)) {
//                predicates.add(cb.equal(root.get("doctor").as(Doctor.class), new Doctor(doctorId)));
//            }
//            if (null != diagonsisName && !"".equals(diagonsisName)) {
//                predicates.add(cb.like(root.get(Emr_.diagnosisResult), "%" + diagonsisName + "%"));
//            }
//            if (null != ageFilter && ageFilter.isValid()) {
//                predicates.add(cb.between(root.get(Emr_.patient).get(Patient_.birthday), ageFilter.getStartDate()
//                        , ageFilter.getRealEndDate()));
//            }
//            if (null != genderSex && genderSex != Gender.All) {
//                predicates.add(cb.equal(root.get(Emr_.patient).get(Patient_.gender), genderSex));
//            }
//
//            query.orderBy(cb.desc(root.get(Emr_.createOn)));
//
//
//            if (!predicates.isEmpty()) {
//                query.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
//            }
//
//            query.groupBy(root.get(Emr_.patient), root.get(Emr_.doctor));
//            return query.getGroupRestriction();
//        };
//        return spec;
//    }

    @Override
    @Transactional(readOnly = true)
    public Page<Emr> pageEmr(Pageable page, Long doctorId, String patient, DateFilter dateFilter) {
        return emrDao.findAll(buildSpecification(doctorId, null, patient, null, dateFilter, null), page);
    }

    @Override
    public Page<Emr> pageEmr(PageRequest pageRequest, List<Doctor> doctorId, String patientName, DateFilter dateFilter, Long shouYinId) {
        return emrDao.findAll((root, query, cb) -> {
            List<Predicate> predicates = Lists.newArrayList();
            if (StringUtils.isNotEmpty(patientName)) {
                predicates.add(cb.like(root.get(Emr_.patientName), "%" + patientName + "%"));
            }
            if (null != dateFilter && dateFilter.isValid()) {
//                predicates.add(cb.between(root.get("createOn").as(Date.class), dateFilter.getStartDate(),
//                        dateFilter.getRealEndDate()));
                predicates.add(
                        cb.greaterThanOrEqualTo(
                                root.get(Emr_.createOn),
                                dateFilter.getStartDate()
                        )
                );
                predicates.add(
                        cb.lessThanOrEqualTo(
                                root.get(Emr_.createOn),
                                dateFilter.getEndDate()
                        )
                );
            }
            if (shouYinId != null) {
                predicates.add(
                        cb.equal(
                                root.get(Emr_.cashierId),
                                shouYinId
                        )
                );
            }
            if (doctorId != null && doctorId.size() > 0) {
                CriteriaBuilder.In<Doctor> doctorIn = cb.in(root.get(Emr_.doctor));
                doctorId.forEach(
                        doctorIn::value
                );
                predicates.add(
                        doctorIn
                );
            }
            query.where(predicates.toArray(new Predicate[predicates.size()]));
            query.orderBy(cb.desc(root.get("id").as(Long.class)));

            return query.getRestriction();
        }, pageRequest);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Emr> pageEmrForManagerToList(Pageable page, Long doctorId, String patient, DateFilter dateFilter,
                                             Long patientId, Long... subDoctorId) {
        List<Doctor> doctorList = Lists.newArrayList();
        if (subDoctorId.length > 0) {
            if (subDoctorId[0] != null) {
                doctorList = doctorService.findSubDoctor(
                        new Doctor(doctorId)).
                        stream().filter(
                        doctor -> doctor.getId().equals(subDoctorId[0])).collect(Collectors.toList());

                if (subDoctorId[0].equals(doctorId)) {
                    doctorList.add(new Doctor(doctorId));
                }
            } else {
                doctorList.addAll(doctorService.findSubDoctor(new Doctor(doctorId)));
                doctorList.add(new Doctor(doctorId));
            }

        }
        return emrDao.findAll(buildSpecification(doctorId, null, patient, null, dateFilter, patientId, doctorList), page);
    }

    @Override
    public Page<Emr> pagePatientFromEmr(Pageable page, Long doctorId,
                                        String patientName, DateFilter dateFilter,
                                        DateFilter ageFilter,
                                        Gender genderSex, String diagonsisName) {
        return emrDao.findAll(buildSpecification(doctorId, patientName, dateFilter, ageFilter, genderSex,
                diagonsisName), page);
    }

    private Specification<Emr> buildSpecification(final Long doctorId, final String patientName, final DateFilter
            dateFilter,
                                                  final DateFilter ageFilter,
                                                  final Gender genderSex, final String diagonsisName) {
        return (root, query, cb) -> {
            List<Predicate> predicates = Lists.newArrayList();
            if (Numbers.isNotNullOrZero(doctorId)) {
                predicates.add(cb.equal(root.get("doctor").as(Doctor.class), new Doctor(doctorId)));
            }
            if (StringUtils.isNotEmpty(patientName) && !"".equals(patientName)) {
                predicates.add(cb.like(root.get("patientName").as(String.class), "%" + patientName + "%"));
            }
            if (null != dateFilter && dateFilter.isValid()) {
                predicates.add(cb.between(root.get("createOn").as(Date.class), dateFilter.getStartDate(),
                        dateFilter.getRealEndDate()));
            }

            if (null != ageFilter && ageFilter.isValid()) {
                predicates.add(
                        cb.between(
                                root.join(Emr_.patient).get(Patient_.birthday), ageFilter.getStartDate(),
                                ageFilter.getRealEndDate()
                        )
                );
            }

            if (null != genderSex && genderSex != Gender.All) {
                predicates.add(
                        cb.equal(
                                root.join(Emr_.patient).get(Patient_.gender)
                                , genderSex
                        )
                );
            }

            if (StringUtils.isNoneEmpty(diagonsisName)) {
                predicates.add(cb.like(root.get("diagnosisResult").as(String.class), "%" + diagonsisName + "%"));
            }

            if (!predicates.isEmpty()) {
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
            return cb.conjunction();
        };
    }


    @Override
    @Transactional(readOnly = true)
    public Page<Emr> pageEmrReplied(Pageable page, Long doctorId, String patient, DateFilter dateFilter, Long... subDoctorId) {
        Doctor doctor = doctorService.getDoctor(doctorId);
        List<Doctor> doctorList = Lists.newArrayList();
        if (doctor.getDoctorType() == Doctor.Doctor_Type.Clinic_Boss) {
            if (subDoctorId.length > 0) {
                if (subDoctorId[0] != null) {
                    doctorList.addAll(
                            doctorService.findSubDoctor(doctor).stream().filter(
                                    doctor1 -> doctor1.getId().equals(subDoctorId[0])
                            ).collect(Collectors.toSet())
                    );
                    if (subDoctorId[0].equals(doctor.getId())) {
                        doctorList.add(doctor);
                    }
                } else {
                    doctorList.addAll(
                            doctorService.findSubDoctor(doctor)
                    );
                    doctorList.add(doctor);
                }

            } else {
                doctorList.addAll(
                        doctorService.findSubDoctor(doctor)
                );
                doctorList.add(doctor);
            }

        } else {
            doctorList.add(doctor);
        }

        return emrDao.findAll(buildSpecification(doctorId, null, patient, Boolean.TRUE, dateFilter, null, doctorList), page);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Emr> pageEmrByPatient(Pageable page, String patientUid, DateFilter dateFilter) {
        return emrDao.findAll(buildSpecification(null, patientUid, null, null, dateFilter, null), page);
    }

    @Override
    public List<Emr> listByPatientUidBetween(Doctor doctor, String patientUid, Date start, Date end) {
        return emrDao.findByDoctorAndPatientUidAndCreateOnBetween(doctor, patientUid, start, end);
    }

    @Override
    @Transactional(readOnly = true)
    public Long getPatientCount(Long doctorId) {
        return emrDao.getPatientCount(doctorId);
    }

    @Override
    @Transactional(readOnly = true)
    public Long getPatientCountByCurrentMonth(Long doctorId) {
        return emrDao.getPatientCountByCurrentMonth(doctorId);
    }

    @Override
    public List<Emr> listEmrByDoctor(Doctor doctor) {
        return emrDao.findByDoctor(doctor);
    }

    @Override
    public Long getEmrCount(Long doctorId) {
        return emrDao.getEmrCount(doctorId);
    }

    @Override
    public List<Statistics> findDiagnosisStatisticsData(Pageable page, List<Doctor> doctorList) {
        Page<Statistics> datas = emrDao.findDiagnosisStatisticsData(page, doctorList);
        return datas.getContent();
    }

    @Override
    public List<Statistics> findDiagnosisStatisticsData(Pageable page, Long doctorId) {
        Page<Statistics> datas = emrDao.findDiagnosisStatisticsData(page, doctorId);
        return datas.getContent();
    }

    @Override
    public List<Statistics> findDiseaseStatisticsData(Pageable page, List<Long> doctorList, Date month) {
        Page<Statistics> datas = emrDao.findDiseaseStatisticsData(page, doctorList, month);
        return datas.getContent();
    }

    @Override
    public List<Statistics> findDiseaseStatisticsData(Pageable page, Long doctorId, Date month) {
        Page<Statistics> datas = emrDao.findDiseaseStatisticsData(page, doctorId, month);
        return datas.getContent();
    }

    @Override
    public List<Statistics> findChineseStatisticsData(Pageable page, List<Long> doctorList, Date month) {
        Page<Statistics> datas = emrDao.findChineseStatisticsData(page, doctorList, month);
        return datas.getContent();
    }

    @Override
    public List<Statistics> findChineseStatisticsData(Pageable page, Long doctorId, Date month) {
        Page<Statistics> datas = emrDao.findChineseStatisticsData(page, doctorId, month);
        return datas.getContent();
    }

    @Override
    public List<Statistics> findWesternStatisticsData(Pageable page, List<Long> doctorList, Date month) {
        Page<Statistics> datas = emrDao.findWesternStatisticsData(page, doctorList, month);
        return datas.getContent();
    }

    @Override
    public List<Statistics> findWesternStatisticsData(Pageable page, Long doctorId, Date month) {
        Page<Statistics> datas = emrDao.findWesternStatisticsData(page, doctorId, month);
        return datas.getContent();
    }

    @Override
    public Page<Statistics> findDiseaseDiatels(Pageable page, Long doctorId, DateFilter dateFilter) {
        return emrDao.findDiseaseDiatels(page, doctorId, dateFilter.getStartDate(), dateFilter.getRealEndDate());
    }

    @Override
    public Page<Statistics> findDiseaseDiatels(Pageable page, List<Long> doctorList, DateFilter dateFilter) {
        return emrDao.findDiseaseDiatels(page, doctorList, dateFilter.getStartDate(), dateFilter.getRealEndDate());
    }

    @Override
    public Page<Statistics> findChineseDiatels(Pageable page, Long doctorId, DateFilter dateFilter) {
        return emrDao.findChineseDiatels(page, doctorId, dateFilter.getStartDate(), dateFilter.getRealEndDate());
    }

    @Override
    public Page<Statistics> findChineseDiatels(Pageable page, List<Long> doctorList, DateFilter dateFilter) {
        return emrDao.findChineseDiatels(page, doctorList, dateFilter.getStartDate(), dateFilter.getRealEndDate());
    }


    @Override
    public Page<Statistics> findWesternDiatels(Pageable page, Long doctorId, DateFilter dateFilter) {
        return emrDao.findWesternDiatels(page, doctorId, dateFilter.getStartDate(), dateFilter.getRealEndDate());
    }

    @Override
    public Page<Statistics> findWesternDiatels(Pageable page, List<Long> doctorList, DateFilter dateFilter) {
        return emrDao.findWesternDiatels(page, doctorList, dateFilter.getStartDate(), dateFilter.getRealEndDate());
    }


    @Override
    public List<Emr> getEmrByPatientAndDoctor(Patient patient, Doctor doctor) {

        return emrDao.findAll((root, query, cb) -> {
            List<Predicate> predicateList = Lists.newArrayList();
            predicateList.add(
                    cb.equal(
                            root.get(Emr_.patient),
                            patient
                    )
            );

            if (doctor.getDoctorType() == Doctor.Doctor_Type.Clinic_Boss) {
                CriteriaBuilder.In<Doctor> doctorIn = cb.in(root.get(Emr_.doctor));
                doctorService.findSubDoctor(doctor).forEach(
                        doctorIn::value
                );
                doctorIn.value(doctor);
                predicateList.add(
                        doctorIn
                );
            } else {
                predicateList.add(
                        cb.equal(
                                root.get(Emr_.doctor),
                                doctor
                        )
                );
            }
            query.where(predicateList.toArray(new Predicate[predicateList.size()]));

            query.orderBy(cb.desc(root.get(Emr_.createOn)));

            return query.getRestriction();
        });
//        return emrDao.findByPatientAndDoctorOrderByCreateOnDesc(patient, doctor);
    }

    private void sendWXToOp(Emr emr) {
        Patient op = emr.getPatient();
        Doctor d = emr.getDoctor();
        if (StringUtils.isEmpty(op.getWxId())) {
            return;
        }
        TemplateWX wx = new TemplateWX();
        wx.setTouser(op.getWxId());
        wx.setUrl("http://www.yijiazhen.com/op/emr/" + emr.getId());
        wx.setTemplate_id(WeixinUtil.P_TEMPLAE_ID);
        Map<String, Map> data = Maps.newHashMap();
        Map first = new HashMap();
        first.put("value", "您好，感谢您在本诊所就诊。");
        first.put("color", "#173177");
        data.put("first", first);
        Map keyword1 = new HashMap();
        keyword1.put("value", op.getName());
        keyword1.put("color", "#173177");
        data.put("keyword1", keyword1);
        Map keyword2 = new HashMap();
        keyword2.put("value", d.getOutpatientService());
        keyword2.put("color", "#173177");
        data.put("keyword2", keyword2);
        Map keyword3 = new HashMap();
        keyword3.put("value", emr.getDiagnosisResult());
        keyword3.put("color", "#173177");
        data.put("keyword3", keyword3);
        Map remark = new HashMap();
        remark.put("value", "点击查看更多信息。");
        remark.put("color", "#173177");
        data.put("remark", remark);
        wx.setData(data);
        logger.info("op data:" + JSONObject.toJSONString(wx));
        if (StringUtils.isNotEmpty(WeixinUtil.ACCESS_TOKEN_OP)) {
            String url = WeixinUtil.SEND_MESSAGE_URL.replaceAll("ACCESS_TOKEN", WeixinUtil.ACCESS_TOKEN_OP);
            JSONObject obj = WeixinUtil.httpRequest(url, "POST", JSONObject.toJSONString(wx));
            logger.info("WX op back:" + JSONObject.toJSONString(obj));
        }
    }

    private boolean sendWXToPhar(Emr emr) {
        List<Pharmacist> pList = pharmacistDao.findByDoctorId(emr.getDoctor().getId());
        if (pList.isEmpty()) {
            return false;
        }
        Patient op = emr.getPatient();
//        Doctor d = emr.getDoctor();
        for (int i = 0; i < pList.size(); i++) {
            Pharmacist p = pList.get(i);
            if (StringUtils.isEmpty(p.getWxId())) {
                continue;
            }
            TemplateWX wx = new TemplateWX();
            wx.setTouser(p.getWxId());
            wx.setUrl("http://www.yijiazhen.com/phar/anon/emr/" + emr.getId());
            wx.setTemplate_id(WeixinUtil.H_TEMPLAE_ID);
            Map<String, Map> data = Maps.newHashMap();
            Map first = new HashMap();
            first.put("value", "您有新的药单信息需要核对。");
            first.put("color", "#173177");
            data.put("first", first);
            Map keyword1 = new HashMap();
            keyword1.put("value", DateUtils.date2Str(emr.getCreateOn(), DateUtils.time_sdf));
            keyword1.put("color", "#173177");
            data.put("keyword1", keyword1);
            Map keyword2 = new HashMap();
            keyword2.put("value", emr.getPatientName());
            keyword2.put("color", "#173177");
            data.put("keyword2", keyword2);
            Map keyword3 = new HashMap();
            keyword3.put("value", op.getMobile());
            keyword3.put("color", "#173177");
            data.put("keyword3", keyword3);
            Map keyword4 = new HashMap();
            StringBuffer sb = new StringBuffer();
            List<EmrMedicine> list = emr.getEmrMedicineList();
            for (int j = 0; j < list.size(); j++) {
                if (j <= 1) {
                    EmrMedicine m = list.get(j);
                    sb.append(m.getMedicineName());
                    sb.append(m.getQty());
                    sb.append(m.getUnit() + ";");
                }
            }
            keyword4.put("value", sb.toString());
            keyword4.put("color", "#173177");
            data.put("keyword4", keyword4);
            Map remark = new HashMap();
            remark.put("value", "请核对药单信息是否合理，并及时抓取药品。");
            remark.put("color", "#173177");
            data.put("remark", remark);
            wx.setData(data);
            logger.info("phar data:" + JSONObject.toJSONString(wx));
            if (StringUtils.isNotEmpty(WeixinUtil.ACCESS_TOKEN_PHAR)) {
                if (!WeixinUtil.IS_LOCAL) {
                    //从redis获取微信token
                    WxInfo wxInfo = wxCache.load("wxToken");
                    if (wxInfo == null) {
                        System.out.println("wxToken 是 空的 开始获取token");
                        wxInfo = new WxInfo();
                        wxInfo.setACCESS_TOKEN_PHAR(WeixinUtil.getAccessToken(WeixinUtil.HAPPID, WeixinUtil.HAPPSECRET));
                        wxInfo.setACCESS_TOKEN_OP(WeixinUtil.getAccessToken(WeixinUtil.PAPPID, WeixinUtil.PAPPSECRET));
                        //有效期为 80分钟
                        wxCache.addOrUpdate("wxToken", wxInfo, 80);
                        System.out.println("wxToken 获取成功:" + wxInfo.toString());
                        System.out.println("Task end");
                    }
                    WeixinUtil.ACCESS_TOKEN_PHAR = wxInfo.getACCESS_TOKEN_PHAR();
                    WeixinUtil.ACCESS_TOKEN_OP = wxInfo.getACCESS_TOKEN_OP();
                }

                String url = WeixinUtil.SEND_MESSAGE_URL.replaceAll("ACCESS_TOKEN", WeixinUtil.ACCESS_TOKEN_PHAR);
                JSONObject obj = WeixinUtil.httpRequest(url, "POST", JSONObject.toJSONString(wx));
                logger.info("WX phar back:" + JSONObject.toJSONString(obj));
                if (JSONObject.toJSONString(obj).indexOf("access_token is invalid") > 0) {
                    if (!WeixinUtil.IS_LOCAL) {
                        //从redis获取微信token
                        WxInfo wxInfo = wxCache.load("wxToken");
                        if (wxInfo == null) {
                            System.out.println("wxToken 是 空的 开始获取token");
                            wxInfo = new WxInfo();
                            wxInfo.setACCESS_TOKEN_PHAR(WeixinUtil.getAccessToken(WeixinUtil.HAPPID, WeixinUtil.HAPPSECRET));
                            wxInfo.setACCESS_TOKEN_OP(WeixinUtil.getAccessToken(WeixinUtil.PAPPID, WeixinUtil.PAPPSECRET));
                            //有效期为 80分钟
                            wxCache.addOrUpdate("wxToken", wxInfo, 80);
                            System.out.println("wxToken 获取成功:" + wxInfo.toString());
                            System.out.println("Task end");
                        }
                        WeixinUtil.ACCESS_TOKEN_PHAR = wxInfo.getACCESS_TOKEN_PHAR();
                        WeixinUtil.ACCESS_TOKEN_OP = wxInfo.getACCESS_TOKEN_OP();
                        logger.info("微信出现错误,已经重新获取,重发,time:{}}", new Date().toLocaleString());
                        return false;
//                        sendWXToPhar(emr);
                    }
                }
            }
        }
        return true;
    }

    @Override
    public List<Emr> getEmrListByNotSendIsNeddAutoSend() {

        return emrDao.findAll((root, query, cb) -> {
            List<Predicate> predicateList = Lists.newArrayList();

            predicateList.add(cb.equal(root.get(Emr_.haveSend), Emr.HAVESEND.NO));
            predicateList.add(cb.equal(root.get(Emr_.autoSend), "是"));
            predicateList.add(cb.ge(root.get(Emr_.autoSendDay), 1));

            if (!predicateList.isEmpty()) {
                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
            return cb.conjunction();
        });
    }

    @Override
    public void saveAndPullEmr(Emr emr) {
        emrDao.updateSetIsHaveSend(emr.getId());
    }

    @Override
    public void updateTmpEmrToCommonByPaitent(Patient patient) {
        emrDao.updateTmpEmrToCommonByPaitent(patient, patient.getUid());
    }

    /**
     * 查找 为挂起状态并且是三天
     *
     * @param hangUp
     * @return前开的药方
     */
    @Override
    public List<Emr> findByStatus(StatusEntity.Status hangUp) {
        return emrDao.findAll((root, query, cb) -> cb.and(
                cb.equal(
                        root.get(Emr_.status),
                        hangUp
                ),
                cb.lessThan(
                        root.get(Emr_.createOn),
                        DateUtils.getDayAfterDay(
                                new Date(),
                                3,
                                DateUtils.DATA_UTLIST_TYPE_DO.SUB
                        )
                )

        ));
    }

    @Override
    public void updatePatien(Long oldId, Long newId) {
        emrDao.updatePatien(new Patient(oldId), new Patient(newId));
    }

    @Override
    public List<Emr> findAllByDoctorListAndTime(List<Doctor> doctorList, Date startDate, Date endDate, String... patientName) {
        return emrDao.findAll((root, query, cb) -> {
            List<Predicate> predicateList = Lists.newArrayList();
            CriteriaBuilder.In<Doctor> doctorIn = cb.in(root.get(Emr_.doctor));
            doctorList.forEach(doctorIn::value);
            predicateList.add(doctorIn);

            if (startDate != null) {
                predicateList.add(
                        cb.greaterThan(
                                root.get(Emr_.createOn),
                                startDate
                        )
                );
            }

            if (endDate != null) {
                predicateList.add(
                        cb.lessThan(
                                root.get(Emr_.createOn),
                                endDate
                        )
                );
            }
            if (StringUtils.isNoneEmpty(patientName)) {
                predicateList.add(
                        cb.equal(
                                root.join(Emr_.patient).get(Patient_.name),
                                patientName[0]
                        )
                );
            }

            query.where(predicateList.toArray(new Predicate[predicateList.size()]));
            return query.getRestriction();
        });
    }

    @Override
    public List<Emr> findAllByDoctorListAndTimeAndShouYinId(List<Doctor> doctorList, Date startDate, Date endDate, String patientName, Long shouYinId) {
        return emrDao.findAll((root, query, cb) -> {
            List<Predicate> predicateList = Lists.newArrayList();
            CriteriaBuilder.In<Doctor> doctorIn = cb.in(root.get(Emr_.doctor));
            doctorList.forEach(doctorIn::value);
            predicateList.add(doctorIn);

            if (startDate != null) {
                predicateList.add(
                        cb.greaterThan(
                                root.get(Emr_.createOn),
                                startDate
                        )
                );
            }

            if (endDate != null) {
                predicateList.add(
                        cb.lessThan(
                                root.get(Emr_.createOn),
                                endDate
                        )
                );
            }
            if (StringUtils.isNoneEmpty(patientName)) {
                predicateList.add(
                        cb.equal(
                                root.join(Emr_.patient).get(Patient_.name),
                                patientName
                        )
                );
            }
            if (shouYinId != null) {
                predicateList.add(
                        cb.equal(
                                root.get(Emr_.cashierId),
                                shouYinId
                        )
                );
            }

            query.where(predicateList.toArray(new Predicate[predicateList.size()]));
            return query.getRestriction();
        });
    }

    @Override
    public Map<String, Boolean> isNeedPrint(Long emrId) {
        Map<String, Boolean> isNeedMap = Maps.newHashMap();
        isNeedMap.put("medicine", false);
        isNeedMap.put("therapy", false);
        isNeedMap.put("extCost", false);
        isNeedMap.put("adviceDict", false);
        Optional.ofNullable(emrDao.getOne(emrId)).ifPresent(emr -> {
            //是否包含了中医理疗或者开药了
            emr.getEmrMedicineList().stream().filter(emrMedicine ->
                    emrMedicine.getMedicineType() == Medicine.Type.ChineseTherapy)
                    .findAny()
                    .ifPresent(emrMedicine ->
                            isNeedMap.put("therapy", true)
                    );

            emr.getEmrMedicineList().stream().filter(emrMedicine ->
                    emrMedicine.getMedicineType() == Medicine.Type.Chinese || emrMedicine.getMedicineType() == Medicine.Type.Western)
                    .findAny()
                    .ifPresent(emrMedicine ->
                            isNeedMap.put("medicine", true)
                    );

            if (!emr.getEmrExtCostList().isEmpty()) {
                isNeedMap.put("extCost", true);
            }

            if (!emr.getEmrJClassAdviceDicts().isEmpty()) {
                isNeedMap.put("adviceDict", true);
            }
        });
        return isNeedMap;
    }

    @Override
    public List<Patient> findAllPatientByDoctor(Doctor doctor) {
        return emrDao.findByDoctorGroupByPatien(doctor).stream().filter(
                patient ->
                        StringUtils.isNotEmpty(patient.getName())
                                &&
                                StringUtils.isNotEmpty(patient.getUdid())
        ).collect(Collectors.toList());
    }
}
