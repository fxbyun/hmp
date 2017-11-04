package com.qiaobei.hmp.service.impl;

import com.google.common.collect.Lists;
import com.qiaobei.hmp.modules.entity.DataFile;
import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.Doctor_;
import com.qiaobei.hmp.modules.entity.ImageWall;
import com.qiaobei.hmp.modules.repository.ImageWallDao;
import com.qiaobei.hmp.repository.DataFileDao;
import com.qiaobei.hmp.repository.DoctorDao;
import com.qiaobei.hmp.repository.RoleDao;
import com.qiaobei.hmp.service.DoctorService;
import com.qiaobei.hmp.support.Constants;
import com.qiaobei.hmp.support.PharSecuritySupport;
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
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
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
    @Resource
    private ImageWallDao imageWallDao;

    @Override
    @Transactional(readOnly = true)
    public Doctor getDoctor(Long id) {
        return doctorDao.findOne(id);
    }

    @Override
    public Doctor getPrimaryDoctor(Doctor doctor) {
        return Optional.ofNullable(doctor).map(doctor1 -> {
            if (doctor1.getDoctorType() == Doctor.Doctor_Type.Sub_Doctor) {
                return doctorDao.getOne(doctor1.getPrimaryDoctorId());
            } else {
                return doctor;
            }
        }).get();
    }

    @Override
    public List<Doctor> getSubDoctor(Doctor doctor) {
        return doctorDao.findAll((root, query, cb) -> cb.and(
                cb.equal(
                        root.get(Doctor_.primaryDoctorId),
                        doctor.getId()
                )
        ));
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
            if (businessFile != null && !businessFile.isEmpty()) {
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
    public List<Doctor> findALl() {
        return doctorDao.findAll();
    }

    @Override
    public void saveImageWall(List<MultipartFile> imageWallFiles, Doctor doctor) {

    }

    @Override
    public Long saveImageWall(MultipartFile imageWallFile, Doctor doctor, ImageWall.ImageLevel level) {
        ImageWall imageWall = new ImageWall();
        imageWall.setDoctorId(doctor.getId());
        imageWall.setLevel(level);
        //原文件的名字
        String fileName = imageWallFile.getOriginalFilename();
        imageWall.setFileName(System.currentTimeMillis() + "." + fileName.substring(fileName.lastIndexOf('.') + 1));
        imageWall.setDate(new Date());
        imageWall.setOutpatientService(doctor.getOutpatientService());
        try {
            imageWall.setContent(IOUtils.toByteArray(imageWallFile.getInputStream()));
            imageWallDao.save(imageWall);
            return imageWall.getId();
        } catch (IOException e) {
            throw new ServiceException("形象墙照片保存失败！");
        }
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
    public String findDoctorHeaderUrl(Long doctorId) {
        DataFile permit = fileDao.findByLogicIdAndType(doctorId, DataFile.Type.Avatar);
        String headUlr = null;
        if (permit != null) {
            byte[] content = permit.getContent();
            String fileName = permit.getFileName();
            ServletContext sc = ContextLoader.getCurrentWebApplicationContext().getServletContext();
            String dir = sc.getRealPath("/temp");
            try {
                File f = new File(dir + "/" + fileName);
                if (!f.exists()) {
                    FileUtils.writeByteArrayToFile(f, content);
                }
                headUlr = fileName;
            } catch (IOException e) {
            }
        }
        return headUlr;
    }

    @Override
    public Page<Doctor> findDocAndSubDoctorPage(Long doctorId, Pageable pageable) {
        return doctorDao.findDocAndSubDoctorPage(doctorId, pageable);
    }

    @Override
    public List<Doctor> getDoctorOrSubDoctor(Long doctorId) {
        List<Doctor> doctorList = Lists.newArrayList();
        if (doctorId == null) {
            doctorList = doctorDao.findByPrimaryDoctorId(PharSecuritySupport.getDoctorId());
            doctorList.add(PharSecuritySupport.getDoctor());
        } else {
            doctorList.add(doctorDao.getOne(doctorId));
        }
        return doctorList;
    }


    @Override
    public void saveDoctor(Doctor doctor) {
        doctorDao.save(doctor);
    }


}
