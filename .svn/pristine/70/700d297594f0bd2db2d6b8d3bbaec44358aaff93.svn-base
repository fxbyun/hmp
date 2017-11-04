package com.qiaobei.hmp.service.impl;

import com.qiaobei.hmp.modules.entity.Registration;
import com.qiaobei.hmp.modules.support.DateUtils;
import com.qiaobei.hmp.repository.RegistrationDao;
import com.qiaobei.hmp.service.RegistrationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("registrationService")
@Transactional
public class RegistrationServiceImpl implements RegistrationService {

    //    private static Logger logger = LoggerFactory.getLogger(RegistrationServiceImpl.class);
    @Resource
    private RegistrationDao registrationDao;

    @Override
    public List<Registration> getRegistrationList(Long doctorId, Registration.Status stuats) {
        return registrationDao.findByDoctorIdAndStatus(doctorId, stuats);
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
    public Registration getByAppointPatientId(Long appointPatientId) {
        return registrationDao.findByAppointPatientId(appointPatientId);
    }

    @Override
    public void delete(Long id) {
        registrationDao.delete(id);
    }

    @Override
    public String getRegistrationToDayAndTypeEndNo(Registration.RegistrationTypeEnum types) {
        List<Registration> registrationList;
        if (types == Registration.RegistrationTypeEnum.LOCALE) {
            registrationList = registrationDao.getRegistrationToDayAndTypeLocal(DateUtils.getDayStartTime(), DateUtils.getDayEndMaxTime(), types);
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
        if (types == Registration.RegistrationTypeEnum.WECHAT) {
            no = "B" + no;
        } else {
            no = "A" + no;
        }
        return no;
    }
}
