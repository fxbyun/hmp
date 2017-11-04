package com.qiaobei.hmp.modules.repository;


import com.qiaobei.hmp.modules.entity.AppointExpire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Administrator on 2016/10/10 0010.
 */
public interface AppointExpireDao extends JpaRepository<AppointExpire, Long>, JpaSpecificationExecutor<AppointExpire> {

    public AppointExpire findByOrderId(String orderId);
}
