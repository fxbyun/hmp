package com.qiaobei.hmp.modules.service;

import com.qiaobei.hmp.modules.entity.EmrMedicine;

import java.util.List;

/**
 * Created by IntelliJ IDEA 2016.4
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 2016/10/31 0031
 * Time 15:11
 */
public interface EmrMedicineService {
    List<EmrMedicine> findByIdIn(List<Long> longList);


    void updateEmrMedBackByIdIn(List<Long> longList);

    EmrMedicine findById(Long id);

    void save(EmrMedicine emrMedicine);
}
