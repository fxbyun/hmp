package com.qiaobei.hmp.service.impl;

import com.google.common.collect.Lists;
import com.qiaobei.hmp.modules.entity.AppointPatient;
import com.qiaobei.hmp.modules.entity.AppointReward;
import com.qiaobei.hmp.modules.entity.AppointReward_;
import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.repository.AppointRewardDao;
import com.qiaobei.hmp.service.AppointRewardService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA 2016.4
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 2016/9/18 0018
 * Time 14:55
 */
@Service("appointRewardService")
@Transactional
public class AppointRewardServiceImpl implements AppointRewardService {

    @Resource
    private AppointRewardDao appointRewardDao;


    @Override
    public void save(AppointReward appointReward) {
        appointRewardDao.save(appointReward);
    }


    @Override
    public List<AppointReward> findByMobile(String mobile) {
        return appointRewardDao.findByMobile(mobile);
    }

    @Override
    public AppointReward getById(Long id) {
        return appointRewardDao.getOne(id);
    }

    @Override
    public AppointReward getByOrderId(String orderId) {
        return appointRewardDao.findByOrderId(orderId);
    }

    @Override
    public List<AppointReward> findYesterdayList() {
        return appointRewardDao.findYesterdayList();
    }

    @Override
    public AppointReward getByAppointPatient(AppointPatient appointPatient) {
        List<AppointReward> rewardList = appointRewardDao.findByAppointPatient(appointPatient);
        if (CollectionUtils.isNotEmpty(rewardList)) {
            return rewardList.get(rewardList.size() - 1);
        } else {
            return null;
        }
    }

    @Override
    public List<AppointReward> findByDoctorAndTimeAndStatus(List<Doctor> doctorList, Date startDate, Date endDate) {
        return appointRewardDao.findAll((root, query, cb) -> {
            List<Predicate> predicateList = Lists.newArrayList();
            CriteriaBuilder.In<Doctor> doctorIn = cb.in(root.get(AppointReward_.doctor));
            doctorList.forEach(doctorIn::value);
            predicateList.add(doctorIn);
            if (startDate != null) {
                predicateList.add(
                        cb.greaterThanOrEqualTo(
                                root.get(AppointReward_.appointDate),
                                startDate
                        )
                );
            }
            if (endDate != null) {
                predicateList.add(
                        cb.lessThanOrEqualTo(
                                root.get(AppointReward_.appointDate),
                                endDate
                        )
                );
            }
            return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
        });
    }
}
