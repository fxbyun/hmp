package com.qiaobei.hmp.repository;

import com.qiaobei.hmp.modules.entity.AppointPatient;
import com.qiaobei.hmp.modules.entity.AppointReward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2016/9/18 0018.
 */
public interface AppointRewardDao extends JpaRepository<AppointReward, Long>, JpaSpecificationExecutor<AppointReward> {

    @Query(value = "select app from AppointReward app where app.mobile=?1 order by app.payDate desc ")
    public List<AppointReward> findByMobile(String mobile);

    public AppointReward findByOrderId(String orderId);

    public List<AppointReward> findByAppointPatient(AppointPatient appointPatient);

    @Query(value = "SELECT * FROM appoint_reward ar WHERE to_days(now())-to_days(ar.pay_date)=2", nativeQuery = true)
    public List<AppointReward> findYesterdayList();
}
