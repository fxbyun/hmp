package com.qiaobei.hmp.service.impl;

import com.qiaobei.hmp.modules.entity.AppointReward;
import com.qiaobei.hmp.repository.AppointRewardDao;
import com.qiaobei.hmp.service.AppointRewardService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/9/18 0018.
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
    public List<AppointReward> findTwoHourBefore(Long doctorId, Long patientId,Date date) {
        return appointRewardDao.findTwoHourBefore(doctorId,patientId,date);
    }

    @Override
    public AppointReward getByOrderId(String orderId) {
        return appointRewardDao.findByOrderId(orderId);
    }
}
