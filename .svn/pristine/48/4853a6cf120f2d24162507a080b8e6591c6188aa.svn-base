package com.qiaobei.hmp.modules.repository;

import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.JClassAdviceDict;
import com.qiaobei.hmp.modules.entity.JDoctorExamLab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by IntelliJ IDEA 2016.4
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 2016/11/4 0004
 * Time 14:29
 */
public interface JDoctorExamLabDao extends JpaRepository<JDoctorExamLab, Long>, JpaSpecificationExecutor<JDoctorExamLab> {
//    JDoctorExamLab findByAdviceNameAndDoctor(String adviceName, Doctor doctor);


    JDoctorExamLab findByClassAdviceDictNameAndDoctorIdAndType(String adviceName, Long id, JClassAdviceDict.Advice_Type adviceType);

}
