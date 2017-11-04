package com.qiaobei.hmp.repository;

import com.qiaobei.hmp.modules.entity.AppointConfig;
import com.qiaobei.hmp.modules.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Administrator on 2016/8/31 0031.
 */
public interface AppointConfigDao extends JpaRepository<AppointConfig, Long>, JpaSpecificationExecutor<AppointConfig> {

    //判断医生是否开启预约按钮
    public AppointConfig findByDoctor(Doctor doctor);

}
