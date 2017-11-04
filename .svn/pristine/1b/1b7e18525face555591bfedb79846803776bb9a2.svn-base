package com.qiaobei.hmp.service.impl;

import com.google.common.collect.Lists;
import com.qiaobei.hmp.modules.entity.*;
import com.qiaobei.hmp.repository.PrescriptionDao;
import com.qiaobei.hmp.repository.PrescriptionLibDao;
import com.qiaobei.hmp.service.PrescriptionService;
import org.apache.commons.lang3.StringUtils;
import org.javasimon.aop.Monitored;
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
import java.util.Date;
import java.util.List;

@Service("prescriptionService")
@Transactional
@Monitored
public class PrescriptionServiceImpl implements PrescriptionService {
    @Resource
    private PrescriptionDao prescriptionDao;
    @Resource
    private PrescriptionLibDao prescriptionLibDao;

    @Override
    @Transactional(readOnly = true)
    public Prescription getPrescription(Long id) {
        return prescriptionDao.findOne(id);
    }

    @Override
    public void savePrescriptin(Prescription p) {
        prescriptionDao.save(p);
    }

    @Override
    public void sharePrescriptin(Prescription p) {
        PrescriptionLib ps = new PrescriptionLib();
        ps.setDoctorId(p.getDoctorId());
        ps.setParentId(p.getId());
        ps.setCategoryId(-1L);
        ps.setCategoryName("未分类药方");
        ps.setName(p.getName());
        ps.setMedicineType(p.getMedicineType());
        ps.setRemark(p.getRemark());
        ps.setDiagnosis(p.getDiagnosis());
        ps.setCreateOn(new Date());
        List<PrescriptionLibItem> psList = Lists.newArrayList();
        for (PrescriptionItem item : p.getPrescriptionItemList()) {
            PrescriptionLibItem psItem = new PrescriptionLibItem();
            psItem.setCompanyId(item.getCompanyId());
            psItem.setCompanyName(item.getCompanyName());
            psItem.setMedicineId(item.getMedicineId());
            psItem.setMedicineType(item.getMedicineType());
            psItem.setMedicineName(item.getMedicineName());
            psItem.setQty(item.getQty());
            psItem.setUnit(item.getUnit());
            psItem.setRate(item.getRate());
            psItem.setCopies(item.getCopies());
            psItem.setHasUsage(item.getHasUsage());
            psItem.setUseMode(item.getUseMode());
            psItem.setPrescriptionLib(ps);
            psList.add(psItem);
        }
        ps.setPrescriptionLibItemList(psList);
        prescriptionLibDao.save(ps);
        prescriptionDao.save(p);
    }

    @Override
    public void cancelSharePrescriptin(Prescription p) {
        prescriptionLibDao.delete(prescriptionLibDao.findByParentId(p.getId()));
        prescriptionDao.save(p);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Prescription> findPage(Pageable page, String name, String createBy) {
        return prescriptionDao.findAll(buildSpecification(name, createBy), page);
    }

    private Specification<Prescription> buildSpecification(final String name, final String createBy) {
        Specification<Prescription> spec = new Specification<Prescription>() {
            @Override
            public Predicate toPredicate(Root<Prescription> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = Lists.newArrayList();
                if (StringUtils.isNotEmpty(name)) {
                    predicates.add(cb.like(root.get("name").as(String.class), "%" + name + "%"));
                }
                if (StringUtils.isNotEmpty(createBy)) {
                    predicates.add(cb.like(root.get("doctorName").as(String.class), "%" + createBy + "%"));
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
