package com.qiaobei.hmp.service.impl;

import com.google.common.collect.Lists;
import com.qiaobei.hmp.modules.entity.*;
import com.qiaobei.hmp.repository.OldPatientBingliDao;
import com.qiaobei.hmp.service.OldPatientBingliService;
import com.qiaobei.hmp.service.OldPatientService;
import com.qiaobei.hmp.support.DateFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.List;

/**
 * Created by IntelliJ IDEA 15.0
 * User ZW_Teemoer
 * Date 2016/7/28 0028
 * Time 10:52
 */
@Service("oldPatientBingliService")
@Transactional
public class OldPatientBingliServiceImpl implements OldPatientBingliService {
    @Resource
    private OldPatientBingliDao oldPatientBingliDao;
    @Resource
    private OldPatientService oldPatientService;

    @Override
    public Page<OldPatientBingli> pageEmrForManagerToList(Pageable pageable, final String patientName, final DateFilter
            dateFilter, final Long patientId) {
        return oldPatientBingliDao.findAll((root, query, cb) -> {
            List<Predicate> predicateList = Lists.newArrayList();
            if (patientId != null) {
                predicateList.add(
                        cb.equal(root.join(OldPatientBingli_.oldPatient), new OldPatient(patientId))
                );
            }
            if (patientName != null && !"".equals(patientName)) {
                predicateList.add(
                        cb.like(root.join(OldPatientBingli_.oldPatient).get(OldPatient_.name), "%" + patientName
                                + "%")
                );
            }
            if (dateFilter != null && dateFilter.isValid()) {
                predicateList.add(
                        cb.between(root.get(OldPatientBingli_.createTime), dateFilter.getStartDate(), dateFilter
                                .getEndDate())
                );
            }
            return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
        }, pageable);
    }

    @Override
    public List<OldPatientBingli> findAllByDoctor(Doctor doctor) {
        return oldPatientBingliDao.findByDoctor(doctor);
    }

    @Override
    public OldPatientBingli getById(Long emrId) {

        return oldPatientBingliDao.findOne(emrId);
    }

    @Override
    public Page<OldPatientBingli> getPageByUid(PageRequest page, Patient patient, final DateFilter dateFilter) {
        final OldPatient oldPatient = oldPatientService.findOneByNewPatient(patient);
        if (oldPatient == null) {
            return null;
        }
        return oldPatientBingliDao.findAll((root, query, cb) -> {
            List<Predicate> predicateList = Lists.newArrayList();

            predicateList.add(
                    cb.equal(root.join(OldPatientBingli_.oldPatient), oldPatient)
            );

            if (dateFilter != null) {
                if (dateFilter.isValid()) {
                    predicateList.add(cb.between(root.get(OldPatientBingli_.createTime), dateFilter.getStartDate(),
                            dateFilter.getEndDate()));
                }
            }

            return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
        }, page);
    }
}
