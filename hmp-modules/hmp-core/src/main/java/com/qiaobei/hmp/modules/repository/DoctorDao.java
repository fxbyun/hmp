package com.qiaobei.hmp.modules.repository;


import com.qiaobei.hmp.modules.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository("doctorDaoCode")
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

}

