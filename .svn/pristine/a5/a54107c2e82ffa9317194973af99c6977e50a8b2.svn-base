package com.qiaobei.hmp.repository;

import com.qiaobei.hmp.modules.entity.AppDoctor;
import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.ProvinceDoctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DoctorDao extends JpaRepository<Doctor, Long>, JpaSpecificationExecutor<Doctor> {

    /**
     * 根据手机号查找用户
     */
    Doctor findByMobile(String mobile);

    /**
     * 根据邮箱查找用户
     */
    Doctor findByEmail(String email);

    /**
     * 根据微信号查找用户
     */
    Doctor findByWxId(String wxId);

    Doctor findByEmailOrMobile(String email, String mobile);

    @Query(value="SELECT * from doctor d ,(SELECT e.doctor_id FROM emr e WHERE e.patient_id in(SELECT p.id from patient p WHERE p.mobile=:mobile) ORDER BY e.create_on DESC LIMIT 1) t WHERE d.id =t.doctor_id",nativeQuery=true)
    Doctor findLastGoByMobile(@Param("mobile") String mobile);

    /**查找出各个省份医生的数量,并且是开启了预约挂号的
     * @return
     */
    @Query(value = "select new com.qiaobei.hmp.modules.entity.ProvinceDoctor(doctor.id,doctor.doctorType,doctor.appointStatus,doctor.province,count(doctor) ) from Doctor doctor where doctor.appointStatus=1 group by doctor.province")
    public List<ProvinceDoctor> findProvinceDocCount();

    /**
     * 查找出各个城市医生的数量,并且是开启了预约挂号的
     *
     * @return
     */
    @Query(value = "select new com.qiaobei.hmp.modules.entity.ProvinceDoctor(doctor.province,doctor.city,count(doctor)) from Doctor doctor where doctor.province=:provinceName and doctor.appointStatus=1 group by doctor.city")
    public List<ProvinceDoctor> findCityDocCount(@Param("provinceName") String provinceName);

    /**
     * 查找出未知地区医生的数量,并且是开启了预约挂号的
     *
     * @return
     */
    @Query(value = "select d from Doctor d where d.province IS NULL and d.city IS NULL and d.appointStatus=1 ")
    public List<Doctor> findUnknownCity();

    @Query(value = "SELECT doctor.id,doctor.outpatient_service,doctor.business_addr, fen.fen_avg,e.num, TIMESTAMPDIFF(MONTH,doctor.verify_on,NOW()) AS months FROM doctor LEFT JOIN (SELECT emr.doctor_id,COUNT(emr.doctor_id) num FROM emr GROUP BY emr.doctor_id) AS e " +
            "ON doctor.id = e.doctor_id LEFT JOIN (SELECT e.doctor_id,e.doctor_name,ROUND(SUM(e.grade) / COUNT(e.doctor_id),2) AS fen_avg FROM evaluate e WHERE e.grade IS NOT NULL GROUP BY e.doctor_id) fen " +
            "ON doctor.id = fen.doctor_id WHERE doctor.name like '%?1%' ORDER BY fen.fen_avg DESC", nativeQuery = true)
    public List<AppDoctor> findByNameOrderByAvgFen(String name);

    @Query(value = "select doctor from Doctor doctor where doctor.doctorType='Clinic_Boss'")
    List<Doctor> findAllClinicBoss();


}

