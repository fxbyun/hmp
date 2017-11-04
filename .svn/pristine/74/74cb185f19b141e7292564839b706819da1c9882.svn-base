package com.qiaobei.hmp.service.impl;

import com.qiaobei.hmp.modules.entity.Card;
import com.qiaobei.hmp.modules.entity.Patient;
import com.qiaobei.hmp.modules.entity.StatusEntity;
import com.qiaobei.hmp.repository.CardDao;
import com.qiaobei.hmp.repository.PatientDao;
import com.qiaobei.hmp.service.PatientService;
import com.qiaobei.hmp.support.Utils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
        if (patientList.size() > 0) {
            return patientList.get(0);
        }
        return null;
    }

    @Override
    public List<Patient> listPatientByMobile(String mobile) {
        return patientDao.findByMobile(mobile);
    }

    @Override
    public List<Patient> queryByMobileNoTmp(String keyword) {
//        return patientDao.findByMobile(keyword);
        return patientDao.findByMobileNoTmp(keyword, StatusEntity.Status.Tmp);
//        return patientDao.findByStatusAndNameHelpCodeLike(keyword + "%");
    }

    @Override
    public List<Patient> queryByMobile(String keyword) {
        String query = keyword + "%";
        return patientDao.findByMobileLike(query);
    }

    @Override
    public List<Patient> getPatientListByUdid(String udid) {
        List<Patient> patientList = patientDao.findListByUdid(udid);
        if (CollectionUtils.isEmpty(patientList)) {
            return Collections.EMPTY_LIST;
        } else {
            return patientList;
        }

    }

    @Override
    public Patient getPatientByUdid(String udid) {
        List<Patient> patientList = patientDao.findByUdid(udid);
        if (patientList.size() >= 1) {
            List<Patient> patientList1 = patientList.stream().filter(patient -> StringUtils.isNotEmpty(patient.getMobile())).collect(Collectors.toList());
            if (patientList1.size() > 0) {
                return patientList1.get(0);
            }
        }
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
    public Patient saveWxPatientBindingCard(Card card, Patient patient) {
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
    public void deletePatien(Patient patient) {

        patientDao.delete(patient.getId());
    }

    @Override
    public void saveAndFlushPatient(Patient patient) {
        patientDao.saveAndFlush(patient);
    }

    @Override
    public Patient findByUid(String patientUid) {
        Patient patient;
        try {
            patient = patientDao.findByUid(patientUid).get(0);
        } catch (Exception e) {
            patient = null;
        }
        return patient;
    }

    @Override
    public Patient findOne(Long id) {
        return patientDao.findOne(id);
    }
}
