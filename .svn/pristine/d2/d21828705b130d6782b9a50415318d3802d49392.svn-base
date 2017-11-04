package com.qiaobei.hmp.service;

import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.ProvinceDoctor;
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
     * user分页、条件查询
     */
    Page<Doctor> findPage(Pageable page, String outName, String doctorName, Doctor.Status status);
    Page<Doctor> findPage(Pageable page, String outName, String doctorName, Doctor.Status status,String province ,String city,String area);
    /**根据手机号，找到该手机号最近就诊的医生
     * @param mobile
     * @return
     */
    Doctor findLastGoByMobile(String mobile);

    /**得到医生的头像Url
     * @param doctorId
     * @return
     */
    String findDoctorHeaderUrl(Long doctorId);

    /**得到医生的平均分
     * @param integration
     * @param emrCount
     * @return
     */
    double getAverage(Integer integration, Long emrCount);

    /**查找出各个省份的医生的数量
     * @return
     */
    public List<ProvinceDoctor> findProvinceDocCount();


    /**查找出某个省份的各个市区医生的数量
     * @param provinceName
     * @return
     */
    public List<ProvinceDoctor> findCityDocCount(String provinceName);


    public List<Doctor> findUnknowCity();


    //找出所有的诊所,即主治医生
    List<Doctor> findAllClinicBoss();

    Page<Doctor> findAllClinicBossPage(Pageable page, String outName, String doctorName, Doctor.Status status, String province, String city, String area);


    //查找所有开启预约功能的医生


    List<Doctor> findSubDoctor(Doctor doctor);

    //根据医生查找该医生所在的诊所下所有医生
    List<Doctor> findClinicAllDoctor(Doctor doctor);



}
