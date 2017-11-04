package com.qiaobei.hmp.service.impl;

import com.google.common.collect.Lists;
import com.qiaobei.hmp.modules.entity.*;
import com.qiaobei.hmp.modules.support.DateUtils;
import com.qiaobei.hmp.repository.DoctorDao;
import com.qiaobei.hmp.repository.RegistrationDao;
import com.qiaobei.hmp.service.RegistrationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service("registrationService")
@Transactional
public class RegistrationServiceImpl implements RegistrationService {

    @Resource
    private RegistrationDao registrationDao;
    @Resource
    private DoctorDao doctorDao;

    @Override
    public List<Registration> getRegistrationList(Long doctorId, Registration.Status stuats, Registration.QueueStatus queueStatus) {
        Doctor doctor = doctorDao.getOne(doctorId);
        List<Registration> registrationList;
        if (doctor.getDoctorType() == Doctor.Doctor_Type.Clinic_Boss) {
            List<Long> doctorList = Lists.newArrayList();
            doctorDao.findDocAndSubDoctorPage(doctorId).forEach(doc -> doctorList.add(doc.getId()));

            registrationList = registrationDao.findByDoctorIdAndStatusOrderByCreateOn(doctorList, stuats, queueStatus, DateUtils.getDayEndMaxTime());
        } else {
            registrationList = registrationDao.findByDoctorIdAndStatusOrderByCreateOn(doctorId, stuats, queueStatus, DateUtils.getDayEndMaxTime());
        }


        //现场挂号List
        List<Registration> localeRegList = Lists.newArrayList();
        //正在叫号微信List
        List<Registration> nowCallList = Lists.newArrayList();
        //已经过号微信List
        List<Registration> passWxList = Lists.newArrayList();
        //排队中微信List
        List<Registration> paiDuiWxList = Lists.newArrayList();
        //未签到微信患者
        List<Registration> notSinIdWxList = Lists.newArrayList();

        long nowDate = new Date().getTime();
        registrationList.forEach(registration -> {
            //如果是微信排队
            if (registration.getRegistrationType() == Registration.RegistrationTypeEnum.WECHAT) {
                //  开始时间 >=现在 && 结束时间 <=现在  说明是 正在叫号队列
                if (registration.getCreateOn().getTime() <= nowDate && registration.getCompleteOn().getTime() >= nowDate) {
                    nowCallList.add(registration);
                } else //开始时间 <现在 说明还没到预约时间 进入正常排队队列
                    if (registration.getCreateOn().getTime() > nowDate) {
                        paiDuiWxList.add(registration);
                    } else //结束时间已经小于现在时间  说明 微信预约已经过号 进入过号队列
                        if (registration.getCompleteOn().getTime() < nowDate) {
                            passWxList.add(registration);
                        }
                //如果是现场挂号
            } else {
                localeRegList.add(registration);
            }
        });
        nowCallList.forEach(registration -> {
            if (registration.getHaveSingIn() != Registration.RegistrationTypeEnum.YES) {
                notSinIdWxList.add(registration);
            }
        });
        passWxList.forEach(registration -> {
            if (registration.getHaveSingIn() != Registration.RegistrationTypeEnum.YES) {
                paiDuiWxList.add(registration);
            }
        });
        passWxList.forEach(registration -> {
            if (registration.getHaveSingIn() != Registration.RegistrationTypeEnum.YES) {
                notSinIdWxList.add(registration);
            }
        });

        // 微信排队和现场排队进行时间排序
        paiDuiWxList.addAll(localeRegList);
        paiDuiWxList.addAll(passWxList);
        Collections.sort(paiDuiWxList, (o1, o2) -> {
            if (o1.getCreateOn().getTime() <= o2.getCreateOn().getTime()) {
                return -1;
            }
            return 0;
        });

//        nowCallList.addAll(passWxList);
        nowCallList.addAll(paiDuiWxList);

        return nowCallList.size() > 12 ? nowCallList.subList(0, 12) : nowCallList;
    }

    @Override
    public List<Registration> getRegistrationList(Long doctorId, Registration.Status stuats) {
        Doctor doctor = doctorDao.getOne(doctorId);
        List<Registration> registrationList;
        if (doctor.getDoctorType() == Doctor.Doctor_Type.Clinic_Boss) {
            List<Long> doctorList = Lists.newArrayList();
            doctorDao.findDocAndSubDoctorPage(doctorId).forEach(doc -> doctorList.add(doc.getId()));

            registrationList = registrationDao.findByDoctorIdAndStatusOrderByCreateOn(doctorList, stuats, DateUtils.getDayEndMaxTime());
        } else {
            registrationList = registrationDao.findByDoctorIdAndStatusOrderByCreateOn(doctorId, stuats, DateUtils.getDayEndMaxTime());
        }


        //现场挂号List
        List<Registration> localeRegList = Lists.newArrayList();
        //正在叫号微信List
        List<Registration> nowCallList = Lists.newArrayList();
        //已经过号微信List
        List<Registration> passWxList = Lists.newArrayList();
        //排队中微信List
        List<Registration> paiDuiWxList = Lists.newArrayList();
        //未签到微信患者
        List<Registration> notSinIdWxList = Lists.newArrayList();

        long nowDate = new Date().getTime();
        registrationList.forEach(registration -> {
            //如果是微信排队
            if (registration.getRegistrationType() == Registration.RegistrationTypeEnum.WECHAT) {
                //  开始时间 >=现在 && 结束时间 <=现在  说明是 正在叫号队列
                if (registration.getCreateOn().getTime() <= nowDate && registration.getCompleteOn().getTime() >= nowDate) {
                    nowCallList.add(registration);
                } else //开始时间 <现在 说明还没到预约时间 进入正常排队队列
                    if (registration.getCreateOn().getTime() > nowDate) {
                        paiDuiWxList.add(registration);
                    } else //结束时间已经小于现在时间  说明 微信预约已经过号 进入过号队列
                        if (registration.getCompleteOn().getTime() < nowDate) {
                            passWxList.add(registration);
                        }
                //如果是现场挂号
            } else {
                localeRegList.add(registration);
            }
        });
        nowCallList.forEach(registration -> {
            if (registration.getHaveSingIn() != Registration.RegistrationTypeEnum.YES) {
                notSinIdWxList.add(registration);
            }
        });
        passWxList.forEach(registration -> {
            if (registration.getHaveSingIn() != Registration.RegistrationTypeEnum.YES) {
                paiDuiWxList.add(registration);
            }
        });
        passWxList.forEach(registration -> {
            if (registration.getHaveSingIn() != Registration.RegistrationTypeEnum.YES) {
                notSinIdWxList.add(registration);
            }
        });

        // 微信排队和现场排队进行时间排序
        paiDuiWxList.addAll(localeRegList);
        paiDuiWxList.addAll(passWxList);
        paiDuiWxList.sort((o1, o2) -> {
            if (o1.getCreateOn().getTime() <= o2.getCreateOn().getTime()) {
                return -1;
            }
            return 0;
        });

//        nowCallList.addAll(passWxList);
        nowCallList.addAll(paiDuiWxList);

        return nowCallList.size() > 12 ? nowCallList.subList(0, 12) : nowCallList;
    }

    @Override
    public Page<Registration> getRegistrationWxSinInList(Long doctorId, StatusEntity.Status stuats, int pageNow) {
        Pageable pageable = new PageRequest(pageNow, 5);
        Page<Registration> registrationPage = registrationDao.findByDoctorIdAndStatusAndHaveSinInOrderByCreateOn(
                pageable,
                doctorId,
                stuats,
                DateUtils.getDayEndMaxTime()
//                ,
//                Registration.RegistrationTypeEnum.YES,
//                Registration.RegistrationTypeEnum.WECHAT
        );

        //现场挂号List
//        List<Registration> localeRegList = Lists.newArrayList();
        //正在叫号微信List
        List<Registration> nowCallList = Lists.newArrayList();
        //已经过号微信List
        List<Registration> passWxList = Lists.newArrayList();
        //排队中微信List
        List<Registration> paiDuiWxList = Lists.newArrayList();
        //未签到微信患者
        List<Registration> notSinIdWxList = Lists.newArrayList();

        long nowDate = new Date().getTime();
        registrationPage.getContent().forEach(registration -> {
            //如果是微信排队
            if (registration.getRegistrationType() == Registration.RegistrationTypeEnum.WECHAT) {
                //  开始时间 >=现在 && 结束时间 <=现在  说明是 正在叫号队列
                if (registration.getCreateOn().getTime() <= nowDate && registration.getCompleteOn().getTime() >= nowDate) {
                    nowCallList.add(registration);
                } else //开始时间 <现在 说明还没到预约时间 进入正常排队队列
                    if (registration.getCreateOn().getTime() > nowDate) {
                        paiDuiWxList.add(registration);
                    } else //结束时间已经小于现在时间  说明 微信预约已经过号 进入过号队列
                        if (registration.getCompleteOn().getTime() < nowDate) {
                            passWxList.add(registration);
                        }
                //如果是现场挂号
            } else {
//                localeRegList.add(registration);
            }
        });
        notSinIdWxList.addAll(nowCallList);
        notSinIdWxList.addAll(passWxList);
        notSinIdWxList.addAll(paiDuiWxList);
        Page<Registration> registrationPageEnd = new PageImpl<>(
                notSinIdWxList,
                pageable,
                registrationPage.getTotalElements()
        );
        return registrationPageEnd;
    }

    @Override
    public void setWxSingInTrue(Long id) {
        registrationDao.updateSetWxSingIn(id);
    }

    @Override
    public Registration getRegistrationById(Long id) {
        return registrationDao.findOne(id);
    }

    @Override
    public Registration getRegistration(String uid, Registration.Status stuats) {
        return registrationDao.findByPatientUidAndStatus(uid, stuats);
    }

    @Override
    public void deleteAllRegistration() {
        registrationDao.deleteAll();
    }

    @Override
    public void save(Registration registration) {
        registrationDao.save(registration);
    }

    @Override
    public void delete(Long id) {
        registrationDao.delete(id);
    }

    @Override
    public List<Registration> getRegistrationByPatientUidAndName(String uid, String name) {

        return registrationDao.getRegistrationByPatientUidAndPatientName(uid, name);
    }

    @Override
    public List<Registration> getByPatientAndDoctor(Patient patient, Doctor doctor) {
        return registrationDao.findAll((root, query, cb) -> {
            List<Predicate> predicates = Lists.newArrayList();
            predicates.add(
                    cb.equal(root.get(Registration_.patientName), patient.getName())
            );
            predicates.add(
                    cb.equal(root.get(Registration_.patientUid), patient.getUid())
            );
            predicates.add(
                    cb.equal(root.get(Registration_.doctorId), doctor.getId())
            );
            predicates.add(
                    cb.equal(root.get("status").as(StatusEntity.Status.class), StatusEntity.Status.Normal)
            );
            predicates.add(
                    cb.equal(root.get("queueStatus").as(Registration.QueueStatus.class), Registration.QueueStatus.NotVisit)
            );

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        });
    }

    @Override
    public Long getRegistrationCountByDoctor(Doctor doctor) {
        return registrationDao.count((root, query, cb) ->
                cb.equal(root.get(Registration_.doctorId), doctor.getId()
                ));
    }

    @Override
    public Registration getCallNameByDoctor(Doctor doctor) {
        List<Registration> list = registrationDao.findAll((root, query, cb) -> {
                    List<Predicate> predicateList = Lists.newArrayList();

                    if (doctor.getDoctorType() == Doctor.Doctor_Type.Clinic_Boss) {
                        List<Doctor> doctorList = doctorDao.findDocAndSubDoctorPage(doctor.getId());

                        CriteriaBuilder.In<Long> doctors = cb.in(root.get(Registration_.doctorId));

                        doctorList.forEach(doc -> doctors.value(doc.getId()));

                        predicateList.add(cb.and(
                                cb.equal(
                                        root.get(Registration_.callStatus),
                                        Registration.CallStatus.CALL
                                ),
                                doctors
                        ));

                    } else {
                        predicateList.add(cb.and(
                                cb.equal(
                                        root.get(Registration_.callStatus),
                                        Registration.CallStatus.CALL
                                ),
                                cb.equal(
                                        root.get(Registration_.doctorId),
                                        doctor.getId()
                                )
                        ));
                    }
            return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
                }
        );
        if (list == null || list.size() == 0) {
            return new Registration();
        }
        return list.get(0);
    }

    @Override
    public String getRegistrationToDayAndTypeEndNo(Registration.RegistrationTypeEnum types, List<Long> doctorList) {
        List<Registration> registrationList;
        if (types == Registration.RegistrationTypeEnum.LOCALE) {
            registrationList = registrationDao.getRegistrationToDayAndTypeLocal(DateUtils.getDayStartTime(), DateUtils.getDayEndMaxTime(), Registration.RegistrationTypeEnum.WECHAT, doctorList);
        } else {
            registrationList = registrationDao.getRegistrationToDayAndType(DateUtils.getDayStartTime(), DateUtils.getDayEndMaxTime(), types, doctorList);
        }

        String no = "";
        int size = registrationList.size() + 1;
        if (size < 10) {
            no = "00" + size;
        } else if (size >= 10 && registrationList.size() <= 99) {
            no = "0" + size;
        } else {
            no = size + "";
        }
        if (types == Registration.RegistrationTypeEnum.LOCALE) {
            no = "A" + no;
        } else {
            no = "B" + no;
        }
        return no;
    }

    @Override
    public String getRegistrationToDayAndTypeEndNo(Registration.RegistrationTypeEnum types) {
        List<Registration> registrationList;
        if (types == Registration.RegistrationTypeEnum.LOCALE) {
            registrationList = registrationDao.getRegistrationToDayAndTypeLocal(DateUtils.getDayStartTime(), DateUtils.getDayEndMaxTime(), Registration.RegistrationTypeEnum.WECHAT);
        } else {
            registrationList = registrationDao.getRegistrationToDayAndType(DateUtils.getDayStartTime(), DateUtils.getDayEndMaxTime(), types);
        }

        String no = "";
        int size = registrationList.size() + 1;
        if (size < 10) {
            no = "00" + size;
        } else if (size >= 10 && registrationList.size() <= 99) {
            no = "0" + size;
        } else {
            no = size + "";
        }
        if (types == Registration.RegistrationTypeEnum.LOCALE) {
            no = "A" + no;
        } else {
            no = "B" + no;
        }
        return no;
    }

}
