package com.qiaobei.hmp.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.MsgMoney;
import com.qiaobei.hmp.modules.entity.MsgSendHistory;
import com.qiaobei.hmp.modules.entity.MsgSendHistory_;
import com.qiaobei.hmp.repository.MsgMoneyDao;
import com.qiaobei.hmp.repository.MsgSendHistoryDao;
import com.qiaobei.hmp.service.MsgSendHistoryService;
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
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by teemoer@cntv.cn on 2016/6/28 0028.
 */
@Service("msgSendHistoryService")
@Transactional
@Monitored
public class MsgSendHistoryServiceImpl implements MsgSendHistoryService {
    @Resource
    MsgSendHistoryDao msgSendHistoryDao;
    @Resource
    MsgMoneyDao msgMoneyDao;

    @Override
    public Map<String, String> getDoctorMsgInfo(Doctor doctor) {

        Map<String, String> map = Maps.newHashMap();
        Long msgSendHistorySize = msgSendHistoryDao.findTotoleSendSizeByDoctor(doctor);
        if (null != msgSendHistorySize) {
            map.put("msgTotal", msgSendHistorySize.toString());
        } else {
            map.put("msgTotal", "0");
        }
        MsgMoney msgMoney = msgMoneyDao.findByDoctor(doctor);

        if (null != msgMoney) {
            map.put("msgMoney", msgMoney.getDeposit().toString());
        } else {
            map.put("msgMoney", "0");
        }
        return map;
    }

    @Override
    public void updateOrSave(MsgSendHistory msgSendHistory) {
        msgSendHistoryDao.save(msgSendHistory);
    }

    @Override
    public Page<MsgSendHistory> getListByDoctor(Pageable page, Doctor doctor) {
        return msgSendHistoryDao.findByDoctor(page, doctor);
    }

    @Override
    public Page<MsgSendHistory> findByDoctorAndCreateDateBetween(Pageable pageable, Doctor doctor, Date month, Date
            endDate) {

        return msgSendHistoryDao.findByDoctorAndCreateDateBetween(pageable, doctor, month, endDate);
    }

    @Override
    public Page<MsgSendHistory> getListByDoctorAndSendType(Pageable pageable, final Doctor doctor, final
    MsgSendHistory.SendType
            sendType) {
        return msgSendHistoryDao.findAll(new Specification<MsgSendHistory>() {
            @Override
            public Predicate toPredicate(Root<MsgSendHistory> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = Lists.newArrayList();
                predicateList.add(cb.equal(root.join(MsgSendHistory_.doctor), doctor));
                predicateList.add(cb.equal(root.get(MsgSendHistory_.msgType), sendType));
                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        }, pageable);
    }
}
