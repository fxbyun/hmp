package com.qiaobei.hmp.service.impl;

import com.qiaobei.hmp.modules.entity.Card;
import com.qiaobei.hmp.modules.entity.Gender;
import com.qiaobei.hmp.modules.entity.Patient;
import com.qiaobei.hmp.modules.entity.StatusEntity;
import com.qiaobei.hmp.repository.CardDao;
import com.qiaobei.hmp.repository.PatientDao;
import com.qiaobei.hmp.service.PatientService;
import com.qiaobei.hmp.support.DateFilter;
import com.qiaobei.hmp.support.Utils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("patientService")
@Transactional
public class PatientServiceImpl implements PatientService {

    @Resource
    private PatientDao patientDao;

    @Resource
    private CardDao cardDao;

    @Override
    public Patient getPatientByWxId(String wxId) {
        return patientDao.findByWxId(wxId);
    }

    @Override
    public Patient getPatientByUid(String uid) {
        List<Patient> patientList = patientDao.findByUid(uid);
        return patientList.size() > 0 ? patientList.get(0) : null;
    }

    @Override
    public List<Patient> listPatientByMobile(String mobile) {
        return patientDao.findByMobile(mobile);
    }

    @Override
    public List<Patient> queryByMobile(String keyword) {
        String query = keyword + "%";
        return patientDao.findByMobileLike(query);
    }

    @Override
    public Patient getPatientByUdid(String udid) {
        List<Patient> patientList = patientDao.findByUdid(udid);
        return patientList.size() > 0 ? patientList.get(0) : null;
    }

    @Override
    public Patient getPatientById(Long id) {

        return patientDao.getOne(id);
    }

    @Override
    public void savePatient(Patient p) {
        if (p.getStatus() == StatusEntity.Status.Unactivated)
            p.setStatus(StatusEntity.Status.Normal);
        patientDao.save(p);
    }

    @Override
    public Patient savePatient4Card(Card card) {
        Patient patient = new Patient();
        patient.setUdid(card.getUdid());
        patient.setUid(card.getCardNo());
        patient.setStatus(StatusEntity.Status.Unactivated);
        patient.setPlainPassword(StringUtils.right(card.getCardNo(), 6));
        Utils.entryptUserPassword(patient);
        patientDao.save(patient);
        card.setStatus(StatusEntity.Status.Used);
        cardDao.save(card);
        return patient;
    }

    @Override
    public Page<Patient> pagePatientFromEmr(Pageable page, Long doctorId, String patientName, DateFilter dateFilter,
                                            DateFilter ageFilter, Gender
                                                    genderSex, String diagonsisName) {


        patientDao.findAll();
        return null;
    }

}
