package com.qiaobei.hmp.service.impl;

import com.google.common.collect.Lists;
import com.qiaobei.hmp.modules.entity.DataFile;
import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.Doctor_;
import com.qiaobei.hmp.modules.entity.ProvinceDoctor;
import com.qiaobei.hmp.repository.DataFileDao;
import com.qiaobei.hmp.repository.DoctorDao;
import com.qiaobei.hmp.repository.RoleDao;
import com.qiaobei.hmp.service.DoctorService;
import com.qiaobei.hmp.support.Constants;
import com.qiaobei.hmp.support.Utils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.service.spi.ServiceException;
import org.javasimon.aop.Monitored;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.multipart.MultipartFile;
import org.springside.modules.persistence.DynamicSpecifications;
import org.springside.modules.persistence.SearchFilter;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service("doctorService")
@Transactional
@Monitored
public class DoctorServiceImpl implements DoctorService {

    private static Logger logger = LoggerFactory.getLogger(DoctorServiceImpl.class);
    @Autowired
    HttpServletRequest request;
    @Resource
    private DoctorDao doctorDao;
    @Resource
    private RoleDao roleDao;
    @Resource
    private DataFileDao fileDao;

    @Override
    @Transactional(readOnly = true)
    public Doctor getDoctor(Long id) {
        return doctorDao.findOne(id);
    }

    @Override
    public void delete(Long id) {
        DataFile file = fileDao.findByLogicIdAndType(id, DataFile.Type.License);
        if (file != null) {
            fileDao.delete(file);
        }
        doctorDao.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Doctor getDoctorByEmail(String email) {
        return doctorDao.findByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public Doctor getDoctorByEmailOrMobile(String email, String mobile) {
        return doctorDao.findByEmailOrMobile(email, mobile);
    }

    @Override
    @Transactional(readOnly = true)
    public Doctor getDoctorByMobile(String mobile) {
        return doctorDao.findByMobile(mobile);
    }

    @Override
    @Transactional(readOnly = true)
    public Doctor getDoctorByWxId(String wxId) {
        return doctorDao.findByWxId(wxId);
    }

    @Override
    public void saveDoctor(Doctor doctor, MultipartFile businessFile, MultipartFile portraitFile) {
        if (doctor.getId() == null) {
            // 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
            if (StringUtils.isNotBlank(doctor.getPlainPassword())) {
                Utils.entryptUserPassword(doctor);
            }
            doctorDao.save(doctor);
            if (!businessFile.isEmpty()) {
                DataFile f = new DataFile();
                f.setLogicId(doctor.getId());
                f.setType(DataFile.Type.License);
                f.setFileName(System.currentTimeMillis() + ".jpg");
                try {
                    f.setContent(IOUtils.toByteArray(businessFile.getInputStream()));
                    fileDao.save(f);
                } catch (Exception e) {
                    throw new ServiceException("文件保存失败！");
                }
            }
        } else {
            doctorDao.save(doctor);
            if (businessFile != null && !businessFile.isEmpty()) {
                DataFile file = fileDao.findByLogicIdAndType(doctor.getId(), DataFile.Type.License);
                if (file != null) {
                    fileDao.delete(file);
                }
                DataFile f = new DataFile();
                f.setLogicId(doctor.getId());
                f.setType(DataFile.Type.License);
                f.setFileName(System.currentTimeMillis() + ".jpg");
                try {
                    f.setContent(IOUtils.toByteArray(businessFile.getInputStream()));
                    fileDao.save(f);
                } catch (Exception e) {
                    throw new ServiceException("营业执照文件保存失败！");
                }
            }
            if (portraitFile != null && !portraitFile.isEmpty()) {
                DataFile file = fileDao.findByLogicIdAndType(doctor.getId(), DataFile.Type.Avatar);
                if (file != null) {
                    fileDao.delete(file);
                }
                DataFile f = new DataFile();
                f.setLogicId(doctor.getId());
                f.setType(DataFile.Type.Avatar);
                f.setFileName(System.currentTimeMillis() + ".jpg");
                try {
                    f.setContent(IOUtils.toByteArray(portraitFile.getInputStream()));
                    fileDao.save(f);
                } catch (Exception e) {
                    throw new ServiceException("头像文件保存失败！");
                }
            }
        }
    }

    /**
     * 判断是否超级管理员.
     */
    private boolean isSupervisor(Doctor user) {
        return ((user.getId() != null) && (user.getId() == Constants.SUPERVISOR_ACCOUNT_ID));
    }


    @Override
    @Transactional(readOnly = true)
    public Page<Doctor> findPage(Pageable page, Map<String, Object> searchParams) {
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        Specification<Doctor> spec = DynamicSpecifications.bySearchFilter(filters.values(), Doctor.class);
        return doctorDao.findAll(spec, page);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Doctor> findPage(Pageable page, String outName, String doctorName, Doctor.Status status) {
        return doctorDao.findAll(buildSpecification(outName, doctorName, status), page);
    }

    @Override
    public Page<Doctor> findPage(Pageable page, String outName, String doctorName, Doctor.Status status, String province,String city,String area) {
        return doctorDao.findAll(buildSpecification(outName, doctorName, status,province,city,area), page);
    }

    @Override
    public Doctor findLastGoByMobile(String mobile) {
        return doctorDao.findLastGoByMobile(mobile);
    }

    @Override
    public String findDoctorHeaderUrl(Long doctorId) {
        DataFile permit = fileDao.findByLogicIdAndType(doctorId,DataFile.Type.Avatar);
        String headUlr = null;
        if (permit != null) {
            byte[] content = permit.getContent();
            String fileName = permit.getFileName();
            ServletContext sc = ContextLoader.getCurrentWebApplicationContext().getServletContext();
            String dir = sc.getRealPath("/temp");
            try {
                File f = new File(dir + "/" + fileName);
                if (f.exists()) {
                    f.delete();
                }
                FileUtils.writeByteArrayToFile(f, content);
                headUlr = fileName;
            } catch (IOException e) {
            }
        }
        return headUlr;
    }

    @Override
    public double getAverage(Integer integration, Long emrCount) {
        if (integration == null||integration==0 || emrCount == null||emrCount==0) {
            return 0;
        }
        BigDecimal integrationValue = new BigDecimal(integration);
        BigDecimal count = new BigDecimal(emrCount);
        BigDecimal average = integrationValue.divide(count, 1, BigDecimal.ROUND_HALF_UP);
        return average.doubleValue();
    }



    private Specification<Doctor> buildSpecification(final String outName, final String doctorName, final Doctor
            .Status status) {
        Specification<Doctor> spec = new Specification<Doctor>() {
            @Override
            public Predicate toPredicate(Root<Doctor> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = Lists.newArrayList();
                if (StringUtils.isNotEmpty(outName)) {
                    predicates.add(cb.like(root.get("outpatientService").as(String.class), "%" + outName + "%"));
                }
                if (StringUtils.isNotEmpty(doctorName)) {
                    predicates.add(cb.like(root.get("name").as(String.class), "%" + doctorName + "%"));
                }
                if (null != status) {
                    predicates.add(cb.equal(root.get("status").as(Doctor.Status.class), status));
                }
                if (!predicates.isEmpty()) {
                    return cb.and(predicates.toArray(new Predicate[predicates.size()]));
                }
                return cb.conjunction();
            }
        };
        return spec;
    }

    private Specification<Doctor> buildSpecification(final String outName, final String doctorName, final Doctor
            .Status status,String province ,String city ,String area) {
        Specification<Doctor> spec = new Specification<Doctor>() {
            @Override
            public Predicate toPredicate(Root<Doctor> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = Lists.newArrayList();
                if (StringUtils.isNotEmpty(outName)) {
                    predicates.add(cb.like(root.get("outpatientService").as(String.class), "%" + outName + "%"));
                }
                if (StringUtils.isNotEmpty(doctorName)) {
                    predicates.add(cb.like(root.get("name").as(String.class), "%" + doctorName + "%"));
                }

                if (StringUtils.isNotEmpty(province)) {
                    predicates.add(cb.like(root.get("province").as(String.class), "%" + province + "%"));
                }

                if (StringUtils.isNotEmpty(city)) {
                    predicates.add(cb.like(root.get("city").as(String.class), "%" + city + "%"));
                }

                if (StringUtils.isNotEmpty(area)) {
                    predicates.add(cb.like(root.get("area").as(String.class), "%" + area + "%"));
                }

                if (null != status) {
                    predicates.add(cb.equal(root.get("status").as(Doctor.Status.class), status));
                }

                predicates.add(
                        cb.equal(
                                root.get("appointStatus").as(Doctor.DoctorAppointStatus.class), Doctor.DoctorAppointStatus.Open
                        )
                );

                if (!predicates.isEmpty()) {
                    return cb.and(predicates.toArray(new Predicate[predicates.size()]));
                }

                return cb.conjunction();
            }
        };
        return spec;
    }







    @Override
    public List<ProvinceDoctor> findProvinceDocCount() {
        return doctorDao.findProvinceDocCount();
    }

    @Override
    public List<ProvinceDoctor> findCityDocCount(String provinceName) {
        return doctorDao.findCityDocCount(provinceName);
    }

    @Override
    public List<Doctor> findSubDoctor(Doctor doctor) {
        return doctorDao.findAll((root, query, cb) -> cb.and(
                cb.equal(
                        root.get(Doctor_.primaryDoctorId),
                        doctor.getId()
                )
        ));
    }


    @Override
    public List<Doctor> findClinicAllDoctor(Doctor doctor) {
        List<Doctor> clinicList = Lists.newArrayList();
        //如果不是主治医生
        if (doctor.getDoctorType() == Doctor.Doctor_Type.Sub_Doctor) {
            Optional.ofNullable(doctorDao.getOne(doctor.getPrimaryDoctorId())).ifPresent(
                    doc -> {
                        clinicList.addAll(findSubDoctor(doc));
                        clinicList.add(doc);
                    }
            );
        } else {
            clinicList.addAll(findSubDoctor(doctor));
            clinicList.add(doctor);
        }

        return clinicList;
    }

    @Override
    public Page<Doctor> findAllClinicBossPage(Pageable page, String outName, String doctorName, Doctor.Status status, String province, String city, String area) {
        return doctorDao.findAll(buildClinicBoss(outName, doctorName, status, province, city, area), page);
    }

    private Specification<Doctor> buildClinicBoss(final String outName, final String doctorName, final Doctor
            .Status status, String province, String city, String area) {
        Specification<Doctor> spec = new Specification<Doctor>() {
            @Override
            public Predicate toPredicate(Root<Doctor> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = Lists.newArrayList();
                if (StringUtils.isNotEmpty(outName)) {
                    predicates.add(cb.like(root.get("outpatientService").as(String.class), "%" + outName + "%"));
                }
                if (StringUtils.isNotEmpty(doctorName)) {
                    predicates.add(cb.like(root.get("name").as(String.class), "%" + doctorName + "%"));
                }

                if (StringUtils.isNotEmpty(province)) {
                    predicates.add(cb.like(root.get("province").as(String.class), "%" + province + "%"));
                }

                if (StringUtils.isNotEmpty(city)) {
                    predicates.add(cb.like(root.get("city").as(String.class), "%" + city + "%"));
                }

                if (StringUtils.isNotEmpty(area)) {
                    predicates.add(cb.like(root.get("area").as(String.class), "%" + area + "%"));
                }

                if (null != status) {
                    predicates.add(cb.equal(root.get("status").as(Doctor.Status.class), status));
                }

                /*predicates.add(
                        cb.notEqual(
                                root.get("doctorType").as(Doctor.Doctor_Type.class), Doctor.Doctor_Type.Sub_Doctor
                        )
                );*/
                //主治医生或者普通医生
                predicates.add(
                        cb.or(
                                cb.notEqual(
                                        root.get("doctorType").as(Doctor.Doctor_Type.class), Doctor.Doctor_Type.Sub_Doctor
                                ),
                                cb.isNull(
                                        root.get("doctorType").as(Doctor.Doctor_Type.class)
                                )
                        )
                );

                //是否开启了预约功能
                predicates.add(
                        cb.equal(
                                root.get("appointStatus").as(Doctor.DoctorAppointStatus.class), Doctor.DoctorAppointStatus.Open
                        )
                );

                if (!predicates.isEmpty()) {
                    return cb.and(predicates.toArray(new Predicate[predicates.size()]));
                }


                return cb.conjunction();
            }
        };
        return spec;
    }


    @Override
    public List<Doctor> findAllClinicBoss() {
        return Optional.ofNullable(doctorDao.findAllClinicBoss()).orElse(Collections.EMPTY_LIST);
    }

    @Override
    public List<Doctor> findUnknowCity() {
        return doctorDao.findUnknownCity();
    }


}
