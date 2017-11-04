package com.qiaobei.hmp.repository;

import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.Medicine;
import com.qiaobei.hmp.modules.entity.MedicinePrivate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by teemoer@cntv.cn on 2016/6/17 0017.
 */
public interface MedicinePrivateDao extends JpaRepository<MedicinePrivate, Long>,
        JpaSpecificationExecutor<MedicinePrivate> {
    @Query(value = " select mp from MedicinePrivate mp where mp.doctor=:doctor and mp.medicine=:medicine")
    MedicinePrivate findByMedAndDoctor(@Param("medicine") Medicine medicine, @Param("doctor") Doctor doctor);
}
