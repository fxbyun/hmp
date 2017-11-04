package com.qiaobei.hmp.repository;


import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.Medicine;
import com.qiaobei.hmp.modules.entity.MedicineContainer;
import com.qiaobei.hmp.modules.entity.MedicinePrivate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;


/**
 * Created by teemoer@cntv.cn on 2016/6/17 0017.
 */
@Service("sysMedicinePrivateDao")
public interface SysMedicinePrivateDao extends JpaRepository<MedicinePrivate, Long>,
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


    @Query(value = "select tag from MedicinePrivate tag inner join tag.medicine m where tag.doctor=?1 and m.helpCode like ?2 and tag.type=2")
    Page<MedicinePrivate> findTagTherapy(Doctor doctor, String helpCode, Pageable pageable);

    @Query(value = "select tag from MedicinePrivate tag inner join tag.medicine m where tag.doctor=?1 and m.name like ?2 and tag.type=2")
    Page<MedicinePrivate> findTagTherapyByMedName(Doctor doctor, String medName, Pageable pageable);

    List<MedicinePrivate> findByDoctor(Doctor doctor);

    List<MedicinePrivate> findByTypeAndDoctor(Medicine.Type type, Doctor doctor);

    @Query(value = "select mp from MedicinePrivate mp where mp.doctor=?1 and mp.type=?2 and mp.category=?3")
    Page<MedicinePrivate> findByTypeAndCategoryAndDoctor(Pageable page, Doctor doctor, Medicine.Type type, String category);

    //私有药品使用最多
    @Query(value = "select new com.qiaobei.hmp.modules.entity.MedicineContainer(mp.category) from MedicinePrivate mp " +
            "where mp.medicine=?1 and mp.category is not null group by mp.category order by count(mp.category) desc ")
    Page<MedicineContainer> findCategoreMoreDoctorUse(Medicine medicine, Pageable pageable);


    @Query(value = "select new com.qiaobei.hmp.modules.entity.MedicineContainer(mp.useMode) from MedicinePrivate mp " +
            "where mp.medicine=?1 and mp.useMode is not null group by mp.useMode order by count(mp.useMode) desc ")
    Page<MedicineContainer> findUseModelMoreDoctorUse(Medicine medicine, Pageable pageable);

    @Query(value = "select new com.qiaobei.hmp.modules.entity.MedicineContainer(mp.usageFlag) from MedicinePrivate mp " +
            "where mp.medicine=?1 and mp.usageFlag is not null group by mp.usageFlag order by count(mp.usageFlag) desc ")
    Page<MedicineContainer> findUsageFlagMoreDoctorUse(Medicine medicine, Pageable pageable);

    @Query(value = "select new com.qiaobei.hmp.modules.entity.MedicineContainer(mp.realQty) from MedicinePrivate mp " +
            "where mp.medicine=?1 and mp.realQty is not null group by mp.realQty order by count(mp.realQty) desc ")
    Page<MedicineContainer> findQtyMoreDoctorUse(Medicine medicine, Pageable pageable);

    @Query(value = "select new com.qiaobei.hmp.modules.entity.MedicineContainer(mp.rate) from MedicinePrivate mp " +
            "where mp.medicine=?1 and mp.rate is not null group by mp.rate order by count(mp.rate) desc ")
    Page<MedicineContainer> findRateMoreDoctorUse(Medicine medicine, Pageable pageable);


    @Query(value = "select new com.qiaobei.hmp.modules.entity.MedicineContainer(mp.standard) from MedicinePrivate mp " +
            "where mp.medicine=?1 and mp.standard is not null group by mp.standard order by count(mp.standard) desc ")
    Page<MedicineContainer> findstandardMoreDoctorUse(Medicine medicine, Pageable pageable);

    List<MedicinePrivate> findMedicinePrivateByDoctor(Doctor doctor);
}
