package com.qiaobei.hmp.repository;

import com.qiaobei.hmp.modules.entity.AppointList;
import com.qiaobei.hmp.modules.entity.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/8/31 0031.
 */
public interface AppointListDao extends JpaRepository<AppointList, Long>, JpaSpecificationExecutor<AppointList> {

    @Query(value = "select appo from AppointList as appo where date_format(appo.date,'%Y-%m-%d')=date_format(:date,'%Y-%m-%d') and appo.doctor=:doctor")
    public List<AppointList> findByDate(@Param("date") Date date, @Param("doctor") Doctor doctor);

    @Query(value = "select appo from AppointList as appo where date_format(appo.date,'%Y-%m-%d')=date_format(:date,'%Y-%m-%d') and appo.doctor=:doctor")
    public Page<AppointList> findByDate(Pageable pageable, @Param("date") Date date,@Param("doctor") Doctor doctor);

    public AppointList findById(Long id);
}
