package com.qiaobei.hmp.service.impl;

import com.google.common.collect.Lists;
import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.Patient;
import com.qiaobei.hmp.modules.entity.Registration;
import com.qiaobei.hmp.modules.entity.StatusEntity;
import com.qiaobei.hmp.modules.support.DateUtils;
import com.qiaobei.hmp.repository.RegistrationDao;
import com.qiaobei.hmp.service.RegistrationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service("registrationService")
@Transactional
public class RegistrationServiceImpl implements RegistrationService {

    //    private static Logger logger = LoggerFactory.getLogger(RegistrationServiceImpl.class);
    @Resource
    private RegistrationDao registrationDao;

    @Override
    public List<Registration> getRegistrationList(Long doctorId, Registration.Status stuats, Registration.QueueStatus queueStatus) {
        Date date = DateUtils.getDayEndMaxTime();
        List<Registration> registrationList = registrationDao.findByDoctorIdAndStatusOrderByCreateOn(doctorId, stuats, queueStatus, date);

        //现场挂号List
        List<Registration> localeRegList = Lists.newArrayList();
        //正在叫号微信List
        List<Registration> nowCallList = Lists.newArrayList();
        //已经过号微信List
        List<Registration> passWxList = Lists.newArrayList();
        //排队中微信List
        List<Registration> paiDuiWxList = Lists.newArrayList();

        long nowDate = new Date().getTime();
        registrationList.forEach(registration -> {
            //如果是微信排队
            if (registration.getRegistrationType() == Registration.RegistrationTypeEnum.WECHAT) {
                //  开始时间 <=现在 && 结束时间 <=现在  说明是 正在叫号队列
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

        return nowCallList;
    }

    @Override
    public List<Registration> getRegistrationList(Long doctorId, Registration.Status stuats) {

        List<Registration> registrationList = registrationDao.findByDoctorIdAndStatusOrderByCreateOn(doctorId, stuats, DateUtils.getDayEndMaxTime());

        //现场挂号List
        List<Registration> localeRegList = Lists.newArrayList();
        //正在叫号微信List
        List<Registration> nowCallList = Lists.newArrayList();
        //已经过号微信List
        List<Registration> passWxList = Lists.newArrayList();
        //排队中微信List
        List<Registration> paiDuiWxList = Lists.newArrayList();

        long nowDate = new Date().getTime();
        registrationList.forEach(registration -> {
            //如果是微信排队
            if (registration.getRegistrationType() == Registration.RegistrationTypeEnum.WECHAT) {
                //  开始时间 <=现在 && 结束时间 <=现在  说明是 正在叫号队列
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

        return nowCallList;
    }

    @Override
    public Registration getRegistrationById(Long id) {
        return registrationDao.findOne(id);
    }

    @Override
    public Registration getRegistration(String uid, Registration.Status stuats, Doctor doctor, Registration.QueueStatus queueStatus) {
        List<Registration> registrationList = registrationDao.findByPatientUidAndStatusAndDoctorIdAndQueueStatus(uid, stuats, doctor.getId(), queueStatus);
        return registrationList != null && registrationList.size() > 0 ? registrationList.get(0) : null;
    }

    @Override
    public void deleteAllRegistration() {
        registrationDao.updateSetStatusNotSingIn(StatusEntity.Status.NotSingIn,
                DateUtils.getDayEndMaxTime());
//        registrationDao.deleteAll();
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
    public void setAllDoNotCallByDoctor(Doctor doctor) {

        registrationDao.updateCallStatusByDoctor(doctor.getId());
    }

    @Override
    public Registration getByAppointPatientId(Long appointPatientId) {
        return registrationDao.findByAppointPatientId(appointPatientId);
    }

    @Override
    public void updatePatienHaveBinDingcard(Patient patient) {
        registrationDao.updateBinDingCardTrue(patient.getId(), patient.getUid());
    }

    @Override
    public String getRegistrationToDayAndTypeEndNo(Registration.RegistrationTypeEnum types, List<Long> doctorList) {
        List<Registration> registrationList;
        if (types == Registration.RegistrationTypeEnum.LOCALE) {
            registrationList = registrationDao.getRegistrationToDayAndTypeLocal(DateUtils.getDayStartTime(), DateUtils.getDayEndMaxTime(), Registration.RegistrationTypeEnum.WECHAT, doctorList);
        } else {
            //这个地方威信挂号也得改
            registrationList = registrationDao.getRegistrationToDayAndType(DateUtils.getDayStartTime(), DateUtils.getDayEndMaxTime(), types, doctorList);
        }

        String no;
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

        String no;
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
