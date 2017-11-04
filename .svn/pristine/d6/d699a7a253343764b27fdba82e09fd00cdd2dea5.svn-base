package com.qiaobei.hmp.service.impl;

import com.google.common.collect.Lists;
import com.qiaobei.hmp.modules.entity.DiagnosisTag;
import com.qiaobei.hmp.repository.DiagnosisTagDao;
import com.qiaobei.hmp.service.DiagnosisTagService;
import org.apache.commons.lang3.StringUtils;
import org.javasimon.aop.Monitored;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Service("diagnosisTagService")
@Monitored
public class DiagnosisTagServiceImpl implements DiagnosisTagService {

    private static Logger logger = LoggerFactory.getLogger(DiagnosisTagServiceImpl.class);

    @Resource
    private DiagnosisTagDao diagnosisTagDao;

    @Override
    public Page<DiagnosisTag> findPage(Pageable page, String name) {
        return diagnosisTagDao.findAll(buildSpecification(name), page);
    }

    @Override
    public DiagnosisTag getTag(Long id) {
        return diagnosisTagDao.getOne(id);
    }

    private Specification<DiagnosisTag> buildSpecification(final String name) {
        Specification<DiagnosisTag> spec = new Specification<DiagnosisTag>() {
            @Override
            public Predicate toPredicate(Root<DiagnosisTag> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
