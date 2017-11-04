package com.qiaobei.hmp.modules.repository;

import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.Emr;
import com.qiaobei.hmp.modules.entity.EmrFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by IntelliJ IDEA 2016.4
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 2016/12/19 0019
 * Time 16:59
 */
public interface EmrFileDao extends JpaRepository<EmrFile, Long>, JpaSpecificationExecutor<EmrFile> {
    EmrFile findByFileNameAndDoctorAndFileType(String fileName, Doctor dcotor, EmrFile.EmrFileType emrFileType);

    @Modifying
    @Query(value = "delete from EmrFile e where e.emr=:emrId and e.fileName not in:emrFileNameList")
    void delEmrFileByNotInNameList(@Param("emrId") Emr emrId, @Param("emrFileNameList") List<String> emrFileNameList);
}
