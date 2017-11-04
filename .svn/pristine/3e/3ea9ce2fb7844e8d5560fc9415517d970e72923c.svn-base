package com.qiaobei.hmp.repository;

import com.qiaobei.hmp.modules.entity.AppointPatient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2016/8/31 0031.
 */
public interface AppointPatientDao extends JpaRepository<AppointPatient, Long>, JpaSpecificationExecutor<AppointPatient> {
    public List<AppointPatient> findAppointPatientByAppointListId(Long listId);

    public AppointPatient findById(Long id);

    @Query(value = "SELECT * from appoint_patient ap WHERE ap.appoint_status=1 AND to_days(now())-to_days(ap.date)=1", nativeQuery = true)
    public List<AppointPatient> findYesterday();

    @Query(value = "SELECT ap.* FROM appoint_patient ap INNER JOIN(SELECT id FROM appoint_list al WHERE to_days(now())-to_days(al.date)>2) list on ap.list_id=list.id", nativeQuery = true)
    public List<AppointPatient> findThreeDayBefore();
}
