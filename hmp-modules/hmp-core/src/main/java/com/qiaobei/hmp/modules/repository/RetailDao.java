package com.qiaobei.hmp.modules.repository;

import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.Patient;
import com.qiaobei.hmp.modules.entity.Pharmacist;
import com.qiaobei.hmp.modules.entity.Retail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/12/28 0028.
 */
public interface RetailDao extends JpaRepository<Retail, Long>, JpaSpecificationExecutor<Retail> {

    @Query(value = "select retail from Retail retail where retail.doctor=?1 and retail.retailStatus = ?2")
    List<Retail> findByRetailStatus(Doctor doctor, Retail.Retail_Status status);

    @Query(value = "select retail from Retail retail where retail.doctor=?1 and retail.retailStatus = ?2 " +
            "and retail.chargeTime between ?3 and ?4")
    List<Retail> findByRetailStatusToday(Doctor doctor, Retail.Retail_Status status, Date startTime, Date endTime);

    @Query(value = "select retail from Retail retail where retail.orderId like ?1 and retail.doctor = ?2 and retail.retailStatus='Save' ")
    Page<Retail> findByOrderIdHasSave(String orderId, Doctor doctor, Pageable pageable);

    @Query(value = "select retail from Retail retail where retail.chargeTime between ?1 and ?2")
    List<Retail> findByTime(Date starTime, Date endTime);

    @Query(value = "select retail from Retail retail where retail.chargeTime between ?1 and ?2 and retail.pharmacist = ?3")
    List<Retail> findByTimeAndPharmacist(Date starTime, Date endTime, Pharmacist pharmacist);


    List<Retail> findByPatient(Patient patient);

    @Query("select retail from Retail retail where retail.patient=?1 and retail.chargeTime between ?2 and ?3 and retail.doctor=?4")
    List<Retail> findByPatientAndChargeTime(Patient patient, Date startTime, Date endTime, Doctor doctor);
}
