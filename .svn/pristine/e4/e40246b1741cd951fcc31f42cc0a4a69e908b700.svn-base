package com.qiaobei.hmp.service;

import com.qiaobei.hmp.modules.entity.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface DoctorService {

    /**
     * 按id查找
     */
    Doctor getDoctor(Long id);

    /**
     * 更新
     */
    void saveDoctor(Doctor doctor);

    /**
     * user分页、条件查询
     */
    Page<Doctor> findPage(Pageable page, String outName, String doctorName, Doctor.Status status);

    /**
     * 医生总数
     */
    Long count();

    /**保存医生的营业执照
     * @param doctor
     * @param file
     */
    void saveDoctorPermit(HttpServletRequest request, Doctor doctor, MultipartFile file);

    boolean delSubDoctor(Long doctorId);

}
