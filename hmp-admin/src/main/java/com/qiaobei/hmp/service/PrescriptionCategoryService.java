package com.qiaobei.hmp.service;

import com.qiaobei.hmp.modules.entity.PrescriptionCategory;

import java.util.List;

public interface PrescriptionCategoryService {

    PrescriptionCategory getPrescriptionCategory(Long id);

    void savePrescriptionCategory(PrescriptionCategory category);

    void deletePrescriptionCategory(Long id);

    List<PrescriptionCategory> listPrescriptionCategory();


}
