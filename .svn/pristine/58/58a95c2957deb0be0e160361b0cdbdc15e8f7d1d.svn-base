package com.qiaobei.hmp.service.impl;

import com.google.common.collect.Lists;
import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.Notice;
import com.qiaobei.hmp.modules.entity.NoticeItem;
import com.qiaobei.hmp.repository.DoctorDao;
import com.qiaobei.hmp.repository.NoticeDao;
import com.qiaobei.hmp.repository.NoticeItemDao;
import com.qiaobei.hmp.service.NoticeService;
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

@Service("noticeService")
@Transactional
@Monitored
public class NoticeServiceImpl implements NoticeService {

    private static Logger logger = LoggerFactory.getLogger(NoticeServiceImpl.class);

    @Resource
    private NoticeDao noticeDao;
    @Resource
    private NoticeItemDao noticeItemDao;
    @Resource
    private DoctorDao doctorDao;

    @Override
    @Transactional(readOnly = true)
    public Page<Notice> findPage(Pageable page, String subject) {
        return noticeDao.findAll(buildSpecification(subject), page);
    }

    @Override
    public void save(Notice notice) {
        boolean isNew = false;
        if (notice.getId() == null) {
            isNew = true;
        }
        noticeDao.save(notice);
        if (isNew) {
            List<Doctor> doctorList = doctorDao.findAll();
            for (int i = 0; i < doctorList.size(); i++) {
                Doctor d = doctorList.get(i);
                NoticeItem item = new NoticeItem();
                item.setStatus(NoticeItem.Status.Normal);
                item.setNotice(notice);
                item.setDoctorName(d.getName());
                item.setDoctorId(d.getId());
                item.setSendBy(notice.getCreateBy());
                item.setSendById(notice.getCreateById());
                item.setSendOn(notice.getCreateOn());
                noticeItemDao.save(item);
            }
        }
    }

    @Override
    public void delete(Long id) {
        noticeDao.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Notice findById(Long id) {
        return noticeDao.findOne(id);
    }

    private Specification<Notice> buildSpecification(final String subject) {
        Specification<Notice> spec = new Specification<Notice>() {
            @Override
            public Predicate toPredicate(Root<Notice> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = Lists.newArrayList();
                if (StringUtils.isNotEmpty(subject)) {
                    predicates.add(cb.like(root.get("subject").as(String.class), "%" + subject + "%"));
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
