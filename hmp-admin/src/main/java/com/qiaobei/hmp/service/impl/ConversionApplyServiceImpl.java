package com.qiaobei.hmp.service.impl;

import com.google.common.collect.Lists;
import com.qiaobei.hmp.modules.entity.Card;
import com.qiaobei.hmp.modules.entity.Conversion;
import com.qiaobei.hmp.modules.entity.ConversionApply;
import com.qiaobei.hmp.modules.entity.StatusEntity;
import com.qiaobei.hmp.repository.ConversionApplyDao;
import com.qiaobei.hmp.repository.ConversionDao;
import com.qiaobei.hmp.service.ConversionApplyService;
import org.apache.commons.lang3.StringUtils;
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

@Service("conversionApplyService")
@Transactional
public class ConversionApplyServiceImpl implements ConversionApplyService {

    @Resource
    private ConversionApplyDao conversionApplyDao;
    @Resource
    private ConversionDao conversionDao;

    @Override
    @Transactional(readOnly = true)
    public ConversionApply getConversionApply(Long id) {
        return conversionApplyDao.findOne(id);
    }

    @Override
    public void saveConversionApply(ConversionApply apply) {
        if (ConversionApply.Status.Verified == apply.getStatus()) {
            Conversion conv = apply.getConversion();
            if (conv == null) {
                conv = conversionDao.findByMedicineAndToUnit(apply.getMedicine(), apply.getToUnit());
            }
            if (conv == null) {
                conv = new Conversion();
            }
            conv.setMedicineName(apply.getMedicineName());
            conv.setMedicine(apply.getMedicine());
            conv.setStatus(Conversion.Status.Normal);
            conv.setFromUnit(apply.getFromUnit());
            conv.setToUnit(apply.getToUnit());
            conv.setRate(apply.getRate());
            conversionDao.save(conv);
            apply.setConversion(conv);
        }
        conversionApplyDao.save(apply);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ConversionApply> findPage(Pageable page, String applyName, String name, ConversionApply.Status status) {
        return conversionApplyDao.findAll(buildSpecification(applyName, name, status), page);
    }

    private Specification<ConversionApply> buildSpecification(final String applyName, final String name, final
    ConversionApply.Status status) {
        Specification<ConversionApply> spec = new Specification<ConversionApply>() {
            @Override
            public Predicate toPredicate(Root<ConversionApply> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = Lists.newArrayList();
                if (StringUtils.isNotEmpty(applyName)) {
                    predicates.add(cb.like(root.get("applyBy").as(String.class), "%" + applyName + "%"));
                }
                if (StringUtils.isNotEmpty(name)) {
                    predicates.add(cb.like(root.get("medicineName").as(String.class), "%" + name + "%"));
                }
                if (status != null) {
                    predicates.add(cb.equal(root.get("status").as(ConversionApply.Status.class), status));
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
