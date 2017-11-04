package com.qiaobei.hmp.service.impl;

import com.google.common.collect.Lists;
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
@Monitored
public class SuggestServiceImpl implements SuggestService {

    private static Logger logger = LoggerFactory.getLogger(SuggestServiceImpl.class);

    @Resource
    private SuggestDao suggestDao;

    @Override
    public List<Suggest> findByTagName(String tagName) {
        return suggestDao.findByTagName(tagName);
    }
}
