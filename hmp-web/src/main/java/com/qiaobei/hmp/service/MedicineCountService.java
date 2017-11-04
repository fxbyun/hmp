package com.qiaobei.hmp.service;


import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.Medicine;
import com.qiaobei.hmp.modules.entity.MedicineCount;
import com.qiaobei.hmp.modules.entity.MedicinePrivate;

import java.util.List;
import java.util.Map;

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

    //查找某个医生某个药品的使用次数
    public Long getCountSizeByDoctor(Medicine medicine, Doctor doctor);

    //查找该医生私有药品列表对应的使用次数
    public Map<MedicinePrivate, Long> getDoctorOfCountSize(Doctor doctor, List<MedicinePrivate> medicinePrivateList);
}
