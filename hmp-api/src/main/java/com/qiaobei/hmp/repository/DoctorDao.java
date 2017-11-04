package com.qiaobei.hmp.repository;

import com.qiaobei.hmp.modules.entity.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

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



    @Query(value = "select doc from Doctor doc where doc.id=?1 or doc.primaryDoctorId=?1 ")
    Page<Doctor> findDocAndSubDoctorPage(Long doctorId, Pageable pageable);

    @Query(value = "select doc from Doctor doc where doc.id=?1 or doc.primaryDoctorId=?1 ")
    List<Doctor> findDocAndSubDoctorPage(Long doctorId);

    List<Doctor> findByprimaryDoctorId(Long id);
}

