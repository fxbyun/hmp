package com.qiaobei.hmp.modules.service;

import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.EmrJClassAdviceDict;
import com.qiaobei.hmp.modules.entity.Patient;
import com.qiaobei.hmp.modules.entity.StatusEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * Created by IntelliJ IDEA 2016.4
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 2016/11/8 0008
 * Time 16:41
 */
public interface EmrJClassAdviceDictService {
    Page<EmrJClassAdviceDict> findByPatientAndExamLabName(Patient patient, String className, PageRequest pageRequest);

    EmrJClassAdviceDict findById(Long id);

    Page<EmrJClassAdviceDict> findByDoctor(Doctor doctor, StatusEntity.Status status, PageRequest pageRequest);

    Page<EmrJClassAdviceDict> findByDoctorAndStatusCardPwdNamePhoe(Doctor doctor, StatusEntity.Status status, String cardPwd, String name, String phone, PageRequest pageRequest);

    void save(EmrJClassAdviceDict emrJClassAdviceDict);

}
