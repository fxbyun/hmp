package com.qiaobei.hmp.repository;

import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.MsgRechargeDetail;
import com.qiaobei.hmp.modules.entity.PayType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by teemoer@cntv.cn on 2016/6/28 0028.
 */
public interface MsgRechargeDetailDao extends JpaRepository<MsgRechargeDetail, Long>,
        JpaSpecificationExecutor<MsgRechargeDetail> {
    @Query(value = "select mr from  MsgRechargeDetail mr inner  join mr.doctor  where mr.doctor=:doctor and mr" +
            ".havePay=:payType")
    Page<MsgRechargeDetail> findByDoctorAndIsPay(Pageable pageable, @Param("doctor") Doctor doctor, @Param("payType")
            PayType payType);

//    @Query(value = "select mr from  MsgRechargeDetail mr where mr.havePay=:payType")
//    Page<MsgRechargeDetail> findAll(Pageable pageable,@Param("payType")PayType payType);

    @Query(value = "select sum(mr.havePay) from MsgRechargeDetail mr where mr.havePay=:payType")
    long count();


}
