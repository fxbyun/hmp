package com.qiaobei.hmp.service.impl;

import com.qiaobei.hmp.modules.entity.PrescriptionCategory;
import com.qiaobei.hmp.repository.PrescriptionCategoryDao;
import com.qiaobei.hmp.service.PrescriptionCategoryService;
import org.javasimon.aop.Monitored;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("prescriptionCategoryService")
@Transactional
@Monitored
public class PrescriptionCategoryServiceImpl implements PrescriptionCategoryService {
    @Resource
    private PrescriptionCategoryDao prescriptionCategoryDao;


    @Override
    public PrescriptionCategory getPrescriptionCategory(Long id) {
        return prescriptionCategoryDao.getOne(id);
    }

    @Override
    public void savePrescriptionCategory(PrescriptionCategory category) {
        prescriptionCategoryDao.save(category);
    }

    @Override
    public void deletePrescriptionCategory(Long id) {
        prescriptionCategoryDao.delete(id);
    }

    @Override
    public List<PrescriptionCategory> listPrescriptionCategory() {
        return prescriptionCategoryDao.findCategory();
    }
}
