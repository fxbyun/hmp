package com.qiaobei.hmp.modules.service;

import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.JDoctorExtCost;
import com.qiaobei.hmp.modules.entity.JLabClass;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * Created by Administrator on 2016/11/4 0004.
 */
public interface JDoctorExtCostService {

    List<JDoctorExtCost> findAll(Doctor doctor);

    void save(JDoctorExtCost jDoctorExtCost);

    JDoctorExtCost findById(Long doctorId);

    void delete(Long doctorId);

    JDoctorExtCost findBySpecification(Specification specification);

}
