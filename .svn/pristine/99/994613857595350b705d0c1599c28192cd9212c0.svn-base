package com.qiaobei.hmp.service.impl;

import com.google.common.collect.Lists;
import com.qiaobei.hmp.modules.entity.*;
import com.qiaobei.hmp.repository.CompanyDao;
import com.qiaobei.hmp.repository.MedicineDao;
import com.qiaobei.hmp.repository.PrescriptionCategoryDao;
import com.qiaobei.hmp.repository.PrescriptionLibDao;
import com.qiaobei.hmp.service.PrescriptionLibService;
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
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Service("prescriptionShareService")
@Transactional
@Monitored
public class PrescriptionLibServiceImpl implements PrescriptionLibService {
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
    public PrescriptionLib getPrescriptionLib(Long id) {
        return prescriptionLibDao.findOne(id);
    }

    @Override
    public void savePrescriptinLib(PrescriptionLib lib) {
        if (Numbers.isNullOrZero(lib.getId())) {
            lib.setCreateOn(Clock.DEFAULT.getCurrentDate());
        }
        if (Numbers.isNotNullOrZero(lib.getCategoryId())) {
            PrescriptionCategory category = prescriptionCategoryDao.getOne(lib.getCategoryId());
            lib.setCategoryName(category.getName());
        }
        for (PrescriptionLibItem item : lib.getPrescriptionLibItemList()) {
            Medicine medicine = medicineDao.getOne(item.getMedicineId());
            Company company = companyDao.getOne(item.getCompanyId());
            item.setMedicineType(medicine.getType());
            item.setMedicineName(medicine.getName());
            item.setCompanyName(company.getName());
            item.setPrescriptionLib(lib);
        }
        prescriptionLibDao.save(lib);
    }

    @Override
    public void deletePrescriptinLib(Long id) {
        prescriptionLibDao.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PrescriptionLib> findPage(Pageable page, String name) {
        return prescriptionLibDao.findAll(buildSpecification(name), page);
    }

    private Specification<PrescriptionLib> buildSpecification(final String name) {
        Specification<PrescriptionLib> spec = new Specification<PrescriptionLib>() {
            @Override
            public Predicate toPredicate(Root<PrescriptionLib> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = Lists.newArrayList();
                if (StringUtils.isNotEmpty(name)) {
                    predicates.add(cb.like(root.get("name").as(String.class), "%" + name + "%"));
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
