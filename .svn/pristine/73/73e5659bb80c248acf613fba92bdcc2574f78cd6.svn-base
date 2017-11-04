package com.qiaobei.hmp.repository;

import com.qiaobei.hmp.modules.entity.AppointWeekConfig;
import com.qiaobei.hmp.modules.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Administrator on 2016/8/31 0031.
 */
public interface AppointWeekConfigDao extends JpaRepository<AppointWeekConfig, Long>, JpaSpecificationExecutor<AppointWeekConfig> {
    public List<AppointWeekConfig> findByDoctor(Doctor doctor);

    public AppointWeekConfig findByDoctorAndWeekday(Doctor doctor, AppointWeekConfig.Weekday weekday);

    @Query(value = "select a from AppointWeekConfig a where a.doctor=:doctor order by a.weekday asc ")
    public List<AppointWeekConfig> findByDoctorOrderByWeekdayAsc(@Param("doctor") Doctor doctor);

}
