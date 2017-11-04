package com.qiaobei.hmp.service;

import com.qiaobei.hmp.modules.entity.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface DoctorService {

    /**
     * 按id查找
     */
    Doctor getDoctor(Long id);

    /**
     * 按id查找
     */
    void delete(Long id);

    /**
     * 按邮箱查找
     */
    Doctor getDoctorByEmail(String email);

    /**
     * 按邮箱或手机号查找
     */
    Doctor getDoctorByEmailOrMobile(String email, String mobile);

    /**
     * 按手机号查找
     */
    Doctor getDoctorByMobile(String mobile);

    /**
     * 按微信号查找
     */
    Doctor getDoctorByWxId(String wxId);

    /**
     * 更新
     */
    void saveDoctor(Doctor doctor, MultipartFile businessFile, MultipartFile portraitFile);

    /**
     * user分页、条件查询
     */
    Page<Doctor> findPage(Pageable page, Map<String, Object> searchParams);

}
