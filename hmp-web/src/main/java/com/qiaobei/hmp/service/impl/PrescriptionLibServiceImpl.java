package com.qiaobei.hmp.service.impl;

import com.google.common.collect.Lists;
import com.qiaobei.hmp.modules.entity.Medicine;
import com.qiaobei.hmp.modules.entity.PrescriptionLib;
import com.qiaobei.hmp.repository.PrescriptionLibDao;
import com.qiaobei.hmp.service.PrescriptionLibService;
import org.apache.commons.lang3.StringUtils;
import org.javasimon.aop.Monitored;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.utils.Numbers;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.List;

@Service("prescriptionLibService")
@Monitored
@Transactional
public class PrescriptionLibServiceImpl implements PrescriptionLibService {
    @Resource
    private PrescriptionLibDao prescriptionLibDao;

    @Override
    public PrescriptionLib getPrescriptionShare(Long id) {
        return prescriptionLibDao.findOne(id);
    }

    @Override
    public Page<PrescriptionLib> findPage(Pageable page, String name, Long categoryId, Medicine.Type medicineType) {
        return prescriptionLibDao.findAll(buildSpecification(name, categoryId, medicineType), page);
    }

    @Override
    public List<PrescriptionLib> listRPLib(Medicine.Type type) {
        return prescriptionLibDao.findByMedicineType(type);
    }

    @Override
    public List<PrescriptionLib> listRPLib(Medicine.Type type, Long catId) {
        return prescriptionLibDao.findByMedicineTypeAndCategoryId(type, catId);
    }

    private Specification<PrescriptionLib> buildSpecification(final String name, final Long categoryId, final
    Medicine.Type medicineType) {
        return (root, query, cb) -> {
            List<Predicate> predicates = Lists.newArrayList();
            if (StringUtils.isNotEmpty(name)) {
                predicates.add(cb.like(root.get("name").as(String.class), "%" + name + "%"));
            }
            if (Numbers.isNotNullOrZero(categoryId)) {
                predicates.add(cb.equal(root.get("categoryId").as(Long.class), categoryId));
            }
            if (medicineType != null) {
                predicates.add(cb.equal(root.get("medicineType").as(Medicine.Type.class), medicineType));
            }
            if (!predicates.isEmpty()) {
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
            return cb.conjunction();
        };
    }
}
