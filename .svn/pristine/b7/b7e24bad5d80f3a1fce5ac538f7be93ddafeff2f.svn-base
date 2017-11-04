package com.qiaobei.hmp.repository;


import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.MsgMoney;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by teemoer@cntv.cn on 2016/6/28 0028.
 */
public interface MsgMoneyDao extends JpaRepository<MsgMoney, Long>, JpaSpecificationExecutor<MsgMoney> {
    MsgMoney findByDoctor(Doctor doctor);
}
