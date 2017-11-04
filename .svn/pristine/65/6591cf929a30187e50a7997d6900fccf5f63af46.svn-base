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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.List;

/**
 * Created by IntelliJ IDEA 2016.4
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 2016/6/28 0028
 * Time 15:01
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


        return msgRechargeDetailDao.findOne((root, query, cb) -> {
            List<Predicate> predicateList = Lists.newArrayList();
            predicateList.add(cb.equal(root.get(MsgRechargeDetail_.rechargeNum), rechargeNum));
            return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
        });
    }
}
