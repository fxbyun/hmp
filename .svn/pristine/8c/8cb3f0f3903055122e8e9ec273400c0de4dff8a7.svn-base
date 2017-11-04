package com.qiaobei.hmp.modules.service;

import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.JClassAdviceDict;
import com.qiaobei.hmp.modules.entity.JDoctorExamLab;
import com.qiaobei.hmp.modules.entity.JDoctorExtCost;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collection;
import java.util.List;

/**
 * Created by IntelliJ IDEA 2016.4
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 2016/11/4 0004
 * Time 14:30
 */
public interface JDoctorExamLabService {
    List<JDoctorExamLab> findAll(Doctor doctor);

    void saveList(Collection<JDoctorExamLab> jDoctorExamLabList);

    void deleteById(Long id);

    JDoctorExamLab findById(Long id);

    void update(JDoctorExamLab jDoctorExamLab);

    JDoctorExamLab findBySpecification(Specification specification);

    void save(JDoctorExamLab jDoctorExamLab);

    JDoctorExamLab findByAdviceNameAndDoctorAndType(String adviceName, Doctor doctor, JClassAdviceDict.Advice_Type adviceType, Long id);
}
