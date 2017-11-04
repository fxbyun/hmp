package com.qiaobei.hmp.repository;

import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.MsgSendHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

/**
 * Created by teemoer@cntv.cn on 2016/6/28 0028.
 */
public interface MsgSendHistoryDao extends JpaRepository<MsgSendHistory, Long>,
        JpaSpecificationExecutor<MsgSendHistory> {

    Page<MsgSendHistory> findByDoctor(Pageable page, Doctor doctor);

    @Query(value = "select sum(m.sendSize) from MsgSendHistory m where m.doctor=:doctor")
    Long findTotoleSendSizeByDoctor(@Param("doctor") Doctor doctor);

    Page<MsgSendHistory> findByDoctorAndCreateDateBetween(Pageable page, Doctor doctor, Date month, Date endDate);

}
