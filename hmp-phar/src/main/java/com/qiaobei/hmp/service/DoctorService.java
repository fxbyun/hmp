package com.qiaobei.hmp.service;

import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.ImageWall;
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

    /**
     * 找到所有的医生
     */
    List<Doctor> findALl();

    public void saveImageWall(List<MultipartFile> imageWallFiles, Doctor doctor);

    public Long saveImageWall(MultipartFile imageWallFile, Doctor doctor, ImageWall.ImageLevel level);

    List<Doctor> findSubDoctor(Doctor doctor);

    void saveDoctor(Doctor doctor);

    /**如果doctorId为null，那么返回该当前医生的所有的子账号+该医生的List
     * 如果不为空则返回只有该医生的List
     * @param doctorId
     * @return
     */
    List<Doctor> getDoctorOrSubDoctor(Long doctorId);

    Page<Doctor> findDocAndSubDoctorPage(Long doctorId,Pageable pageable);

    /**得到医生的头像Url
     * @param doctorId
     * @return
     */
    String findDoctorHeaderUrl(Long doctorId);

    List<Doctor> getSubDoctor(Doctor doctor);

    Doctor getPrimaryDoctor(Doctor doctor);
}
