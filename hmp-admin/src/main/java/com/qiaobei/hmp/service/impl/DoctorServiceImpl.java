package com.qiaobei.hmp.service.impl;

import com.google.common.collect.Lists;
import com.qiaobei.hmp.modules.entity.DataFile;
import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.MedicinePrivate;
import com.qiaobei.hmp.repository.DataFileDao;
import com.qiaobei.hmp.repository.DoctorDao;
import com.qiaobei.hmp.service.DoctorService;
import com.qiaobei.hmp.service.EmrService;
import com.qiaobei.hmp.service.SysMedicinePrivateService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.service.spi.ServiceException;
import org.javasimon.aop.Monitored;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Service("doctorService")
@Transactional
@Monitored
public class DoctorServiceImpl implements DoctorService {

    private static Logger logger = LoggerFactory.getLogger(DoctorServiceImpl.class);

    @Resource
    private DoctorDao doctorDao;

    @Resource
    private DataFileDao fileDao;

    @Resource
    private EmrService emrService;

    @Resource
    private SysMedicinePrivateService sysMedicinePrivateService;

    @Override
    @Transactional(readOnly = true)
    public Doctor getDoctor(Long id) {
        return doctorDao.getOne(id);
    }

    @Override
    public void saveDoctor(Doctor Doctor) {
        doctorDao.save(Doctor);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Doctor> findPage(Pageable page, String outName, String doctorName, Doctor.Status status) {
        return doctorDao.findAll(buildSpecification(outName, doctorName, status), page);
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

    @Override
    @Transactional(readOnly = true)
    public Long count() {
        return doctorDao.count();
    }

    @Override
    public boolean delSubDoctor(Long doctorId) {
        Doctor doctor = doctorDao.getOne(doctorId);
        //是否是子医生
        if (doctor.getDoctorType() != Doctor.Doctor_Type.Sub_Doctor) {
            return false;
        }
        //该医生是否有保存过病历
        if (emrService.isDoctorHasEmr(doctor)) {
            return false;
        }
        //删除该子医生
        try {
            //删除该医生所有的药品
            List<MedicinePrivate> medicineList = sysMedicinePrivateService.getMedicinePrivateByDoctor(doctor);
            if (CollectionUtils.isNotEmpty(medicineList)) {
                //循环删除药品
                medicineList.forEach(medicinePrivate ->
                        sysMedicinePrivateService.deleteMedicinePrivate(medicinePrivate)
                );
            }

            doctorDao.delete(doctor);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public void saveDoctorPermit(HttpServletRequest request, Doctor doctor, MultipartFile newFile) {
        DataFile oldFile = fileDao.findByLogicIdAndType(doctor.getId(), DataFile.Type.License);
        if (oldFile != null) {
            fileDao.delete(oldFile);
        }
        DataFile file = new DataFile();
        file.setLogicId(doctor.getId());
        file.setType(DataFile.Type.License);
        file.setFileName(System.currentTimeMillis() + ".jpg");
        try {
            file.setContent(IOUtils.toByteArray(newFile.getInputStream()));
            fileDao.save(file);
            if (file != null) {
                byte[] content = file.getContent();
                String fileName = file.getFileName();
                ServletContext sc = ContextLoader.getCurrentWebApplicationContext().getServletContext();
                String dir = sc.getRealPath("/temp");
                try {
                    File f = new File(dir + "/" + fileName);
                    if (f.exists()) {
                        f.delete();
                    }
                    FileUtils.writeByteArrayToFile(f, content);
                } catch (IOException e) {
                }
            }


        } catch (Exception e) {
            throw new ServiceException("营业执照文件保存失败！");
        }
    }
}
