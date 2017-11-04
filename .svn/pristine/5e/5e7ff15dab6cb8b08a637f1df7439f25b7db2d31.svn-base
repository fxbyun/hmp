package com.qiaobei.hmp.service.impl;

import com.qiaobei.hmp.modules.entity.Patient;
import com.qiaobei.hmp.modules.entity.PatientTag;
import com.qiaobei.hmp.repository.PatientTagDao;
import com.qiaobei.hmp.service.PatientTagService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.utils.Clock;

import javax.annotation.Resource;
import java.util.List;

@Service("patientTagService")
@Transactional
public class PatientTagServiceImpl implements PatientTagService {

    @Resource
    private PatientTagDao patientTagDao;

    @Override
    public PatientTag getPatientTagById(Long id) {
        return patientTagDao.getOne(id);
    }

    @Override
    public void savePatientTag(PatientTag tag) {
        tag.setCreateOn(Clock.DEFAULT.getCurrentDate());
        patientTagDao.save(tag);
    }

    @Override
    public void deletePatientTagById(Long id) {
        patientTagDao.delete(id);
    }

    @Override
    public List<PatientTag> listPatientTagsByPatient(Long patientId) {
        return patientTagDao.findByPatient(new Patient(patientId));
    }

    @Override
    public void delPatienTagByPatient(final Patient patient) {

        patientTagDao.delByPatient(patient);
    }
}
