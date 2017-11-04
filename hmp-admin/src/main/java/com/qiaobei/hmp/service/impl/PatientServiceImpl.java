package com.qiaobei.hmp.service.impl;

import com.google.common.collect.Lists;
import com.qiaobei.hmp.modules.entity.*;
import com.qiaobei.hmp.repository.CardDao;
import com.qiaobei.hmp.repository.DiagnosisDao;
import com.qiaobei.hmp.repository.EmrDao;
import com.qiaobei.hmp.repository.PatientDao;
import com.qiaobei.hmp.service.PatientService;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.service.spi.ServiceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Service("patientService")
@Transactional
public class PatientServiceImpl implements PatientService {

    @Resource
    private PatientDao patientDao;
    @Resource
    private CardDao cardDao;
    @Resource
    private EmrDao emrDao;
    @Resource
    private DiagnosisDao diagnosisDao;

    @Override
    @Transactional(readOnly = true)
    public Patient getPatientByUid(String uid) {
        return patientDao.findByUid(uid);
    }

    @Override
    @Transactional(readOnly = true)
    public Long count() {
        return patientDao.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Patient getPatient(Long id) {
        return patientDao.findOne(id);
    }

    @Override
    public void savePatient(Patient patient) {
        patientDao.save(patient);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Patient> findPage(Pageable page, String name, String mobile, String uid) {
        return patientDao.findAll(buildSpecification(name, mobile, uid), page);
    }

    @Override
    @Transactional(readOnly = true)
    public Long bindWXCount() {
        return patientDao.bindWXCount();
    }

    @Override
    public void updateCard(Long id, String uid) {
        Patient p = patientDao.getOne(id);
        String oldUid = p.getUid();
        if (!StringUtils.equals(oldUid, uid)) {
            Card c = cardDao.findByCardNo(uid);
            if (c != null) {
                if (c.getStatus() == StatusEntity.Status.Used) {
                    throw new ServiceException("卡号" + uid + "已在使用！");
                }
                List<Emr> emrList = emrDao.findByPatientUid(oldUid);
                for (Emr emr : emrList) {
                    emr.setPatientUid(uid);
                    emrDao.save(emr);
                }
                List<Diagnosis> dList = diagnosisDao.findByPatientUid(oldUid);
                for (Diagnosis d : dList) {
                    d.setPatientUid(uid);
                    diagnosisDao.save(d);
                }
                p.setUid(uid);
                p.setUdid(c.getUdid());
                patientDao.save(p);
                c.setStatus(Card.Status.Used);
                cardDao.save(c);
            } else {
                throw new ServiceException("卡号不存在！");
            }
        } else {
            throw new ServiceException("卡号没有改变！");
        }
    }

    private Specification<Patient> buildSpecification(final String patient,
                                                      final String mobile,
                                                      final String uid) {
        Specification<Patient> spec = new Specification<Patient>() {
            @Override
            public Predicate toPredicate(Root<Patient> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = Lists.newArrayList();
                if (StringUtils.isNotEmpty(patient)) {
                    predicates.add(cb.like(root.get("name").as(String.class), "%" + patient + "%"));
                }
                if (StringUtils.isNotEmpty(mobile)) {
                    predicates.add(cb.equal(root.get("mobile").as(String.class), mobile));
                }
                if (StringUtils.isNotEmpty(uid)) {
                    predicates.add(cb.equal(root.get("uid").as(String.class), uid));
                }
                if (!predicates.isEmpty()) {
                    return cb.and(predicates.toArray(new Predicate[predicates.size()]));
                }
                return cb.conjunction();
            }
        };
        return spec;
    }
}
