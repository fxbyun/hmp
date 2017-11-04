package com.qiaobei.hmp.service.impl;

import com.google.common.collect.Lists;
import com.qiaobei.hmp.modules.entity.*;
import com.qiaobei.hmp.repository.*;
import com.qiaobei.hmp.service.PrescriptionService;
import org.apache.commons.lang3.StringUtils;
import org.javasimon.aop.Monitored;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.utils.Clock;
import org.springside.modules.utils.Numbers;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
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
    @Resource
    private PrescriptionCategoryDao prescriptionCategoryDao;
    @Resource
    private MedicineDao medicineDao;
    @Resource
    private CompanyDao companyDao;

    @Override
    @Transactional(readOnly = true)
    public Prescription getPrescription(Long id) {
        return prescriptionDao.findOne(id);
    }

    @Override
    public void savePrescription(Prescription rp) {
        if (Numbers.isNullOrZero(rp.getId())) {
            rp.setCreateOn(Clock.DEFAULT.getCurrentDate());
        }
        if (Numbers.isNotNullOrZero(rp.getCategoryId())) {
            PrescriptionCategory category = prescriptionCategoryDao.getOne(rp.getCategoryId());
            rp.setCategoryName(category.getName());
        }
        for (PrescriptionItem item : rp.getPrescriptionItemList()) {
            Medicine medicine = medicineDao.getOne(item.getMedicineId());
            Company company = companyDao.getOne(item.getCompanyId());
            item.setMedicineType(medicine.getType());
            item.setMedicineName(medicine.getName());
            item.setCompanyName(company.getName());
            item.setPrescription(rp);
        }
        prescriptionDao.save(rp);
    }

    @Override
    public void deletePrescription(Long id) {
        prescriptionDao.delete(id);
    }

    private Specification<Prescription> buildSpecification(final Long doctorId, final Long categoryId, final Medicine
            .Type medicineType, final String name) {
        return (root, query, cb) -> {
            List<Predicate> predicates = Lists.newArrayList();
            if (Numbers.isNotNullOrZero(doctorId)) {
                predicates.add(cb.equal(root.get("doctorId").as(Long.class), doctorId));
            }
            if (Numbers.isNotNullOrZero(categoryId)) {
                predicates.add(cb.equal(root.get("categoryId").as(Long.class), categoryId));
            }
            if (medicineType != null) {
                predicates.add(cb.equal(root.get("medicineType").as(Medicine.Type.class), medicineType));
            }
            if (StringUtils.isNotEmpty(name)) {
                predicates.add(cb.like(root.get("name").as(String.class), "%" + name + "%"));
            }
            if (!predicates.isEmpty()) {
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
            return cb.conjunction();
        };
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Prescription> pagePrescription(Pageable page, Long doctorId, Long categoryId, Medicine.Type type,
                                               String name) {
        return prescriptionDao.findAll(buildSpecification(doctorId, categoryId, type, name), page);
    }

    @Override
    public Page<Prescription> pagePrescription(Pageable page, Long doctorId) {
        return prescriptionDao.findAll(buildSpecification(doctorId, null, null, null), page);
    }

    @Override
    public List<Prescription> listPrescription(Long doctorId, String tag) {
        return prescriptionDao.findByDoctorIdAndDiagnosisLike(doctorId, "%" + tag + "%");
    }

    @Override
    public List<Prescription> listRP(Medicine.Type type) {
        return prescriptionDao.findByMedicineType(type);
    }

    @Override
    public List<Prescription> listRP(Long doctorId, Medicine.Type type, Long catId) {
        return prescriptionDao.findByDoctorIdAndMedicineTypeAndCategoryId(doctorId, type, catId);
    }

    @Override
    public void batchAddPrescription(Doctor d, String ids, Long cateId) {
        PrescriptionCategory libCate = prescriptionCategoryDao.getOne(cateId);
        PrescriptionCategory doctorCate = prescriptionCategoryDao.findByDoctorIdAndName(d.getId(), libCate.getName());
        if (doctorCate == null) {
            doctorCate = new PrescriptionCategory();
            doctorCate.setDoctorId(d.getId());
            doctorCate.setDoctorName(d.getName());
            doctorCate.setName(libCate.getName());
            doctorCate.setRemark(libCate.getRemark());
            prescriptionCategoryDao.save(doctorCate);
        }
        String[] arrarIds = ids.split(",");
        for (String id : arrarIds) {
            PrescriptionLib lib = prescriptionLibDao.getOne(Long.parseLong(id));
            Prescription p = new Prescription();
            p.setDoctorId(d.getId());
            p.setDoctorName(d.getName());
            p.setMedicineType(lib.getMedicineType());
            p.setRemark(lib.getRemark());
            p.setCreateOn(new Date());
            p.setName(lib.getName());
            p.setCategoryId(doctorCate.getId());
            p.setCategoryName(doctorCate.getName());
            p.setDiagnosis(lib.getDiagnosis());
            List<PrescriptionItem> list = Lists.newArrayList();
            for (PrescriptionLibItem libItem : lib.getPrescriptionLibItemList()) {
                PrescriptionItem item = new PrescriptionItem();
                item.setCopies(libItem.getCopies());
                item.setHasUsage(libItem.getHasUsage());
                item.setQty(libItem.getQty());
                item.setRate(libItem.getRate());
                item.setUnit(libItem.getUnit());
                item.setUseMode(libItem.getUseMode());
                item.setCompanyId(libItem.getCompanyId());
                item.setCompanyName(libItem.getCompanyName());
                item.setMedicineId(libItem.getMedicineId());
                item.setMedicineType(libItem.getMedicineType());
                item.setMedicineName(libItem.getMedicineName());
                item.setPrescription(p);
                list.add(item);
            }
            p.setPrescriptionItemList(list);
            prescriptionDao.save(p);
        }
    }
}
