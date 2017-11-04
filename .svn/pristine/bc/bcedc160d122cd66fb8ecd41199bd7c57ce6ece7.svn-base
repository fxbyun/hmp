package com.qiaobei.hmp.service.impl;

import com.google.common.collect.Lists;
import com.qiaobei.hmp.modules.entity.NoticeItem;
import com.qiaobei.hmp.repository.NoticeItemDao;
import com.qiaobei.hmp.service.NoticeItemService;
import org.javasimon.aop.Monitored;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.utils.Numbers;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.List;

@Service("noticeItemService")
@Monitored
@Transactional
public class NoticeServiceImpl implements NoticeItemService {

    private static Logger logger = LoggerFactory.getLogger(NoticeServiceImpl.class);

    @Resource
    private NoticeItemDao noticeItemDao;

    @Override
    public void save(NoticeItem item) {
        noticeItemDao.save(item);
    }

    @Override
    public Page<NoticeItem> findPage(Pageable page, Long doctorId) {
        return noticeItemDao.findAll(buildSpecification(doctorId), page);
    }

    @Override
    public Long noReadCount(Long doctorId, NoticeItem.Status status) {
        return noticeItemDao.noReadCount(doctorId, status);
    }

    @Override
    public NoticeItem getNoticeItem(Long id) {
        return noticeItemDao.findOne(id);
    }

    private Specification<NoticeItem> buildSpecification(final Long doctorId) {
        Specification<NoticeItem> spec = (root, query, cb) -> {
            List<Predicate> predicates = Lists.newArrayList();
            if (Numbers.isNotNullOrZero(doctorId)) {
                predicates.add(cb.equal(root.get("doctorId").as(Long.class), doctorId));
            }
            if (!predicates.isEmpty()) {
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
            return cb.conjunction();
        };
        return spec;
    }
}
