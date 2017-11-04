package com.qiaobei.hmp.repository;

import com.qiaobei.hmp.modules.entity.AppointTimeConfig;
import com.qiaobei.hmp.modules.entity.AppointWeekConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2016/8/31 0031.
 */
public interface AppointTimeConfigDao extends JpaRepository<AppointTimeConfig, Long>, JpaSpecificationExecutor<AppointTimeConfig> {
    public List<AppointTimeConfig> findByAppointWeekConfig(AppointWeekConfig appointWeekConfig);

    @Query(value = "delete  from AppointTimeConfig time where time.appointWeekConfig.id=?1 ")
    @Modifying
    public void deleteByWeekConfigId(Long weekConfigId);
}
