package com.qiaobei.hmp.repository;

import com.qiaobei.hmp.modules.entity.AppointReward;
import com.qiaobei.hmp.modules.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/9/18 0018.
 */
public interface AppointRewardDao extends JpaRepository<AppointReward, Long>, JpaSpecificationExecutor<AppointReward> {

    @Query(value = "select app from AppointReward app where app.mobile=?1 order by app.payDate desc ")
    public List<AppointReward> findByMobile(String mobile);

    public AppointReward findByOrderId(String orderId);

    @Query(value = "SELECT * FROM appoint_reward ar WHERE ar.doctor_id=?1 AND ar.patient_id=?2 AND DATE(ar.appoint_date)=DATE(?3)",nativeQuery = true)
    public List<AppointReward> findTwoHourBefore(Long DoctorId, Long PatientId, Date date);

}
