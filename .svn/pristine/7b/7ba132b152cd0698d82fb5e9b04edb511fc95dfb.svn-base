package com.qiaobei.hmp.repository;


import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.Medicine;
import com.qiaobei.hmp.modules.entity.MedicinePrivate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;


/**
 * Created by teemoer@cntv.cn on 2016/6/17 0017.
 */
@Repository("medicinePrivateDao")
public interface MedicinePrivateDao extends JpaRepository<MedicinePrivate, Long>,
        JpaSpecificationExecutor<MedicinePrivate> {
    @Query(value = " select mp from MedicinePrivate mp where mp.doctor=:doctor and mp.medicine=:medicine")
    MedicinePrivate findByMedAndDoctor(@Param("medicine") Medicine medicine, @Param("doctor") Doctor doctor);


    @Query(value = " select mp from MedicinePrivate mp where mp.doctor=:doctor and mp.medicine=:medicine")
    List<MedicinePrivate> findByMedAndDoctorList(@Param("medicine") Medicine medicine, @Param("doctor") Doctor doctor);

    @Query(value = "select mp from MedicinePrivate mp inner join mp.medicine m where mp.rate is not null and m" +
            ".id=:medicineId group by mp.rate,m.id order by count(mp.rate) desc")
    List<MedicinePrivate> findRateByMoreUser(@Param("medicineId") Long medicineId, Pageable pageable);

    @Query(value = "select mp from MedicinePrivate mp inner join mp.medicine m where mp.standard is not null and m" +
            ".id=:medicineId group by mp.standard,m.id order by count(mp.standard) desc")
    List<MedicinePrivate> findStandardByMoreUser(@Param("medicineId") Long medicineId, Pageable pageable);

    //    @Query(value = "")
    List<MedicinePrivate> findByDoctorAndMedicine(Doctor doctor, Medicine medicine);

    List<MedicinePrivate> findByHaveManagerAndDoctorAndMedicineIn(MedicinePrivate.HaveManager haveManager,
                                                                  Doctor doctor,
                                                                  Collection<Medicine> medicine);


    @Query(value = "select tag from MedicinePrivate tag inner join tag.medicine m where tag.doctor=?1 and m.helpCode like ?2 and m.type=2")
    Page<MedicinePrivate> findTagTherapy(Doctor doctor, String helpCode, Pageable pageable);

    @Query(value = "select tag from MedicinePrivate tag inner join tag.medicine m where tag.doctor=?1 and m.name like ?2 and m.type=2")
    Page<MedicinePrivate> findTagTherapyByMedName(Doctor doctor, String medName, Pageable pageable);


}
