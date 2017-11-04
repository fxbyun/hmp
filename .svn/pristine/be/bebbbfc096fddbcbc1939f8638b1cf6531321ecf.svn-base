package com.qiaobei.hmp.service.impl;

import com.google.common.collect.Lists;
import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.Suggest;
import com.qiaobei.hmp.repository.SuggestDao;
import com.qiaobei.hmp.service.SuggestService;
import org.apache.commons.lang3.StringUtils;
import org.javasimon.aop.Monitored;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@Service("suggestService")
@Transactional
@Monitored
public class SuggestServiceImpl implements SuggestService {

    private static Logger logger = LoggerFactory.getLogger(SuggestServiceImpl.class);

    @Resource
    private SuggestDao suggestDao;

    @Override
    @Transactional(readOnly = true)
    public Page<Suggest> findPage(Pageable page, String content, String tagName) {
        return suggestDao.findAll(buildSpecification(content, tagName), page);
    }

    private Specification<Suggest> buildSpecification(final String content, final String tagName) {
        Specification<Suggest> spec = new Specification<Suggest>() {
            @Override
            public Predicate toPredicate(Root<Suggest> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = Lists.newArrayList();
                if (StringUtils.isNotEmpty(content)) {
                    predicates.add(cb.like(root.get("content").as(String.class), "%" + content + "%"));
                }
                if (StringUtils.isNotEmpty(tagName)) {
                    predicates.add(cb.like(root.get("tagName").as(String.class), "%" + tagName + "%"));
                }
                if (!predicates.isEmpty()) {
                    return cb.and(predicates.toArray(new Predicate[predicates.size()]));
                }
                return cb.conjunction();
            }
        };
        return spec;
    }

    @Override
    public void save(Suggest suggest) {
        suggestDao.save(suggest);
    }

    @Override
    @Transactional(readOnly = true)
    public Suggest findById(Long id) {
        return suggestDao.getOne(id);
    }

    @Override
    public void deleteSuggest(Suggest suggest) {
        suggestDao.delete(suggest);
    }
}
