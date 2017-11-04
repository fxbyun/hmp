package com.qiaobei.hmp.repository;

import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.Medicine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MedicineDao extends JpaRepository<Medicine, Long>, JpaSpecificationExecutor<Medicine> {

    Page<Medicine> findPageByNameLike(Pageable pageable, String name);

    Page<Medicine> findPageByType(Pageable pageable, Medicine.Type type);

    @Query("select dm.medicine from DoctorMedicine dm where dm.doctor=:doctor")
    Page<Medicine> findPageByDoctor(Pageable pageable, @Param("doctor") Doctor doctor);

    @Query("select dm.medicine from DoctorMedicine dm where dm.doctor=:doctor and dm.medicine.name like :name")
    Page<Medicine> findPageByDoctorAndNameLike(Pageable pageable, @Param("doctor") Doctor doctor, @Param("name")
            String name);

    @Query("select dm.medicine from DoctorMedicine dm where dm.doctor=:doctor and dm.medicine.type=:type GROUP BY dm" +
            ".medicine.name")
    Page<Medicine> findPageByDoctorAndType(Pageable pageable, @Param("doctor") Doctor doctor, @Param("type") Medicine
            .Type type);

    @Query("select dm.medicine from DoctorMedicine dm where dm.doctor=:doctor and dm.medicine.type=:type and (dm" +
            ".medicine.name like :name  or  dm.medicine.helpCode like:name)")
    Page<Medicine> findPageByDoctorAndTypeAndNameLike(Pageable pageable, @Param("doctor") Doctor doctor, @Param
            ("type") Medicine.Type type, @Param("name") String name);

    /**
     * 根据类型 返回 所有符合的药品   不区分医生
     *
     * @param pageable
     * @param type     中药还是西药
     * @param name
     * @return
     */
    @Query("select m from Medicine m left join m.doctorMedicineList dm " +
            "where   (m.name like :name or m.helpCode " +
            "like:name) and m.type=:type GROUP BY m.name")
    Page<Medicine> findPageByTypeAndNameLike(Pageable pageable, @Param
            ("type") Medicine.Type type, @Param("name") String name);

    @Query("select m.id,m.name from Medicine m left join m.doctorMedicineList dm " +
            "where   (m.name like :name or m.helpCode " +
            "like:name) and m.type=:type GROUP BY m.name")
    Page<Medicine> findPostPageByTypeAndNameLike(Pageable pageable, @Param
            ("type") Medicine.Type type, @Param("name") String name);


    @Query("select m.id,m.name from Medicine m " +
            //"left join m.doctorMedicineList dm " +
            "where   (m.name like :name or m.helpCode " +
            "like:name) and m.type=:type GROUP BY m.name")
    Page<Medicine> findListByTypeAndNameLike(Pageable pageable, @Param
            ("type") Medicine.Type type, @Param("name") String name);

    @Query("select m.id,m.name from Medicine m " +
            //"left join m.doctorMedicineList dm " +
            "where m.type=:type GROUP BY m.name")
    Page<Medicine> findListByType(Pageable pageable, @Param
            ("type") Medicine.Type type);

    @Query("select m from Medicine m " +
            //"left join m.doctorMedicineList dm " +
            "where m.type=:type GROUP BY m.name")
    List<Medicine> findAllMedByType(@Param
                                            ("type") Medicine.Type type);


    @Query(value = "select m from MedicineCount mc inner join mc.medicine m " +
            " where mc.doctor=:doctor")
    List<Medicine> findThisDocMed(@Param("doctor") Doctor doctor);

    @Query("select m from Medicine m " +
            "where   (m.name like :name or m.helpCode " +
            "like:name) and m.type=:type GROUP BY m.name")
    List<Medicine> findAllMedListByTypeAndNameLike(@Param
                                                           ("type") Medicine.Type type, @Param("name") String name);

    @Query("select m from Medicine m where m.name=:name group by m.name")
    Medicine findByName(@Param("name") String name);


    @Query("select m from Medicine m left join m.doctorMedicineList dm " +
            "where (dm.doctor <>:doctor or dm.doctor is null)")
    Page<Medicine> findPageByNotDoctor(Pageable pageable, @Param("doctor") Doctor doctor);

    @Query("select m from Medicine m left join m.doctorMedicineList dm " +
            "where (dm.doctor <>:doctor or dm.doctor is null) and m.name like :name")
    Page<Medicine> findPageByNotDoctorAndNameLike(Pageable pageable, @Param("doctor") Doctor doctor, @Param("name")
            String name);

    @Query("select m from Medicine m left join m.doctorMedicineList dm " +
            "where (dm.doctor <>:doctor or dm.doctor is null) and m.type=:type")
    Page<Medicine> findPageByNotDoctorAndType(Pageable pageable, @Param("doctor") Doctor doctor, @Param("type")
            Medicine.Type type);

    @Query("select m from Medicine m left join m.doctorMedicineList dm " +
            "where (dm.doctor <>:doctor or dm.doctor is null) and m.type=:type and (m.name like :name or m.helpCode " +
            "like:name)")
    Page<Medicine> findPageByNotDoctorAndTypeAndNameLike(Pageable pageable, @Param("doctor") Doctor doctor, @Param
            ("type") Medicine.Type type, @Param("name") String name);

    @Query("select dm.medicine from DoctorMedicine dm where dm.doctor=:doctor and dm.medicine.type=:type and dm" +
            ".medicine.category=:category")
    Page<Medicine> findPageByDoctorAndTypeAndCategory(Pageable pageable, @Param("doctor") Doctor doctor, @Param
            ("type") Medicine.Type type, @Param("category") String category);
}

