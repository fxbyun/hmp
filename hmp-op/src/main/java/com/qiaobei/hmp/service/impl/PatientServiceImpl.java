package com.qiaobei.hmp.service.impl;

import com.qiaobei.hmp.modules.entity.Patient;
import com.qiaobei.hmp.repository.PatientDao;
import com.qiaobei.hmp.service.PatientService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("patientService")
@Transactional
public class PatientServiceImpl implements PatientService {

    @Resource
    private PatientDao patientDao;

    @Override
    public Patient getPatientById(Long id) {
        return patientDao.findOne(id);
    }

    @Override
    public List<Patient> getPatientByWxId(String wxId) {
        if (wxId == null || "".equals(wxId))
            return null;
        return patientDao.findByWxId(wxId);
    }

    @Override
    public Patient getPatientByUid(String uid) {
        return patientDao.findByUid(uid);
    }

    @Override
    public void savePatient(Patient p) {
        patientDao.save(p);
    }
}
