package com.qiaobei.hmp.service;


import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.Medicine;
import com.qiaobei.hmp.modules.entity.MedicineCount;

import java.util.List;

/**
 * Created by zw on 2016/5/27 0027.
 */
public interface MedicineCountService {

    MedicineCount getMedicineCountByMedicineAndDoctor(Doctor doctor, Medicine medicine);

    //MedicineCount getMedicineCountByMedicineAndDoctorAndEmr(Doctor doctor, Medicine medicine, Emr emr);
    MedicineCount getMedicineByMedicine(Medicine medicine);

    MedicineCount getMedicineByMedicineAndDiagName(Medicine medicine, String diagName);

    MedicineCount getMedicineCountByMedicineAndDoctorAndDiagnsis(Doctor doctor, Medicine medicine, String diagnosis);

    void save(MedicineCount medicineCount);


    List<MedicineCount> getAllMedCountList();

    List<MedicineCount> getMedCountListByType(Medicine.Type type);

    List<MedicineCount> getMedCountListByTypeAndMedNameLike(Medicine.Type type, String medName);

    List<MedicineCount> getMedCountListByTypeAndMedNameLike(String medName);
}
