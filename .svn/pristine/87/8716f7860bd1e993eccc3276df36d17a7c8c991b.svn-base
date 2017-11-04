package com.qiaobei.hmp.repository;

import com.qiaobei.hmp.modules.entity.AppointPatient;
import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.Patient;
import com.qiaobei.hmp.modules.support.DateUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Administrator on 2016/8/31 0031.
 */
public interface AppointPatientDao extends JpaRepository<AppointPatient, Long>, JpaSpecificationExecutor<AppointPatient> {
    public List<AppointPatient> findAppointPatientByAppointListId(Long listId);
    public AppointPatient findById(Long id);

    @Query(value = "select min(p.id) from AppointPatient p where p.appointList.id=:listId and (p.appointStatus=0 or p.appointStatus=2 ) ")
    Long findMinIdByListId(@Param("listId") Long listId);

    @Query(value = "select app from AppointPatient app where app.patient.mobile=:mobile ")
    List<AppointPatient> findPatientByMobile(@Param(value = "mobile") String mobile);

}
