package com.qiaobei.hmp.service;

import com.qiaobei.hmp.modules.entity.AppointReward;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/9/18 0018.
 */
public interface AppointRewardService {
    public void save(AppointReward appointReward);


    //public AppointReward findByMobile(String mobile);

    public List<AppointReward> findByMobile(String mobile);

    public AppointReward getById(Long id);

    public AppointReward getByOrderId(String orderId);

    List<AppointReward> findTwoHourBefore(Long doctorId, Long patientId, Date date);
}
