package com.qiaobei.hmp.modules.service;

import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.Emr;
import com.qiaobei.hmp.modules.entity.EmrFile;

import java.util.List;

/**
 * Created by IntelliJ IDEA 2016.4
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 2016/12/19 0019
 * Time 17:00
 */
public interface EmrFileService {
    void save(EmrFile emrFile);

    EmrFile findByFileNameAndDoctorAndFileType(String s, Doctor doctor, EmrFile.EmrFileType emrFileType);

    void delEmrFileByNotInNameList(Emr emr, List<String> emrFileNameList);

    EmrFile findByFileNameAndDoctorAndFileType(String s, Doctor doctor);
}
