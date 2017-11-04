package com.qiaobei.hmp.repository;


import com.qiaobei.hmp.modules.entity.OldPatient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by IntelliJ IDEA 15.0
 * User ZW_Teemoer
 * Date 2016/7/28 0028
 * Time 10:11
 */
public interface OldPatientDao extends JpaRepository<OldPatient, Long>, JpaSpecificationExecutor<OldPatient> {
}
