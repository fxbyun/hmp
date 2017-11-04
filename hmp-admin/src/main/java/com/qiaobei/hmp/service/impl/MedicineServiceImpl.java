package com.qiaobei.hmp.service.impl;

import com.google.common.collect.Lists;
import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.Medicine;
import com.qiaobei.hmp.repository.MedicineDao;
import com.qiaobei.hmp.service.MedicineService;
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
import java.util.List;

@Service("medicineService")
@Monitored
public class MedicineServiceImpl implements MedicineService {
    @Resource
    private MedicineDao medicineDao;

    @Override
    public Medicine getMedicine(Long id) {
        return medicineDao.findOne(id);
    }

    @Override
    public Page<Medicine> pageMedicineTag(Pageable page, Medicine.Type type, String name) {
        return medicineDao.findAll(buildSpecification(name, type), page);
    }

    private Specification<Medicine> buildSpecification(final String name, final Medicine.Type type) {
        Specification<Medicine> spec = new Specification<Medicine>() {
            @Override
            public Predicate toPredicate(Root<Medicine> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = Lists.newArrayList();
                if (StringUtils.isNotEmpty(name)) {
                    predicates.add(cb.like(root.get("name").as(String.class), "%" + name + "%"));
                }
                if (null != type) {
                    predicates.add(cb.equal(root.get("type").as(Medicine.Type.class), type));
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
