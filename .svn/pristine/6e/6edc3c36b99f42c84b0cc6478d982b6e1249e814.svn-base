package com.qiaobei.hmp.service.impl;

import com.google.common.collect.Lists;
import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.MsgRechargeDetail;
import com.qiaobei.hmp.modules.entity.MsgRechargeDetail_;
import com.qiaobei.hmp.modules.entity.PayType;
import com.qiaobei.hmp.repository.MsgRechargeDetailDao;
import com.qiaobei.hmp.service.MsgRechargeDetailService;
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
@Service("msgRechargeDetailService")
@Transactional
@Monitored
public class MsgRechargeDetailServiceImpl implements MsgRechargeDetailService {
    @Resource
    MsgRechargeDetailDao msgRechargeDetailDao;

    @Override
    public Page<MsgRechargeDetail> getPageByDoctor(Pageable page, Doctor doctor, PayType payType) {
        return msgRechargeDetailDao.findByDoctorAndIsPay(page, doctor, payType);
    }

    @Override
    public void saveOrUpdate(MsgRechargeDetail msgRechargeDetail) {

        msgRechargeDetailDao.save(msgRechargeDetail);
    }

    @Override
    public MsgRechargeDetail getMsgRecByRechargeNum(final String rechargeNum) {


        return msgRechargeDetailDao.findOne(new Specification<MsgRechargeDetail>() {
            @Override
            public Predicate toPredicate(Root<MsgRechargeDetail> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = Lists.newArrayList();
                predicateList.add(cb.equal(root.get(MsgRechargeDetail_.rechargeNum), rechargeNum));
                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        });
    }
}
