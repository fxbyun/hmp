package com.qiaobei.hmp.service.impl;

import com.google.common.collect.Lists;
import com.qiaobei.hmp.modules.entity.MsgSendHistory;
import com.qiaobei.hmp.modules.entity.MsgSendHistoryDetail;
import com.qiaobei.hmp.repository.MsgSendHistoryDetailDao;
import com.qiaobei.hmp.service.MsgSendHistoryDetailService;
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

/**
 * Created by teemoer@cntv.cn on 2016/6/28 0028.
 */
@Service("msgSendHistoryDetailService")
@Transactional
@Monitored
public class MsgSendHistoryDetailServiceImpl implements MsgSendHistoryDetailService {
    @Resource
    private MsgSendHistoryDetailDao msgSendHistoryDetailDao;

    @Override
    public void updateOrSave(MsgSendHistoryDetail msgSendHistoryDetail) {
        msgSendHistoryDetailDao.save(msgSendHistoryDetail);
    }

    @Override
    public Page<MsgSendHistoryDetail> getListByMSHAndName(Pageable pageable, final String name, final Long id) {
        System.out.println(2);
        return msgSendHistoryDetailDao.findAll(new Specification<MsgSendHistoryDetail>() {
            @Override
            public Predicate toPredicate(Root<MsgSendHistoryDetail> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = Lists.newArrayList();

                //predicates.add(
                //        cb.equal(
                //                root.join(MsgSendHistoryDetail_.msgSendHistory).get(MsgSendHistory_.id),
                //                id));
                predicates.add(cb.equal(root.get("msgSendHistory").as(MsgSendHistory.class), new MsgSendHistory(id)));
                //if (name != null && !"".equals(name)) {
                //    predicates.add(cb.like(root.join(MsgSendHistoryDetail_.patient).get(Patient_.name), "%" + name
                // + "%"));
                //}

                if (!predicates.isEmpty()) {
                    return cb.and(predicates.toArray(new Predicate[predicates.size()]));
                }

                return cb.conjunction();
            }
        }, pageable);
    }
}
