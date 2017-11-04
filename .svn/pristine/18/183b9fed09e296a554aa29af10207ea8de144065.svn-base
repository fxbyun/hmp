package com.qiaobei.hmp.repository;

import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.OldPatientBingli;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


/**
 * Created by IntelliJ IDEA 15.0
 * User ZW_Teemoer
 * Date 2016/7/28 0028
 * Time 10:50
 */
public interface OldPatientBingliDao extends JpaRepository<OldPatientBingli, Long>,
        JpaSpecificationExecutor<OldPatientBingli> {

    @Query("select obl from OldPatientBingli obl left join obl.oldPatient op where op.doctor=:doctor ")
    List<OldPatientBingli> findByDoctor(@Param("doctor") Doctor doctor);


}
