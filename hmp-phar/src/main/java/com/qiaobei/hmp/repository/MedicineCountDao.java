package com.qiaobei.hmp.repository;


import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.Medicine;
import com.qiaobei.hmp.modules.entity.MedicineCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zw on 2016/5/27 0027.
 */
@Repository("medicineCountDao")
public interface MedicineCountDao extends JpaRepository<MedicineCount, Long>, JpaSpecificationExecutor<MedicineCount> {

    //@Query(value = "select m from MedicineCount m where m.")
    MedicineCount findByDoctorAndMedicine(Doctor doctor, Medicine medicine);

    MedicineCount findByDoctorAndMedicineAndDiagosisName(Doctor doctor, Medicine medicine, String diagosisName);

    //MedicineCount findByDoctorAndMedicineAndEmr(Doctor doctor, Medicine medicine , Emr emr);
    MedicineCount findByMedicine(Medicine medicine);

    MedicineCount findByMedicineAndDiagosisName(Medicine medicine, String diagosisName);

    List<MedicineCount> findByMedicineType(Medicine.Type medicineType);

    @Query(value = "select mc from MedicineCount mc where mc.medicine.type=:medicineType " +
            "and (mc.medicine.name like :medicineName or mc.medicine.helpCode like :medicineName )")
    List<MedicineCount> findByMedicineTypeAndMedicineNameLike(@Param("medicineType") Medicine.Type medicineType,
                                                              @Param("medicineName") String medicineName);

    @Query(value = "select mc from MedicineCount mc where (mc.medicine.name like :medicineName or mc.medicine.helpCode like :medicineName )")
    List<MedicineCount> findByMedicineMedicineNameLike(
            @Param("medicineName") String medicineName);

}
