package com.qiaobei.hmp.service.impl;

import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.Medicine;
import com.qiaobei.hmp.modules.entity.MedicineCount;
import com.qiaobei.hmp.repository.MedicineCountDao;
import com.qiaobei.hmp.service.MedicineCountService;
import org.javasimon.aop.Monitored;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zw on 2016/5/27 0027.
 */

@Service("medicineCountService")
@Transactional
@Monitored
public class MedicineCountServiceImpl implements MedicineCountService {
    @Resource
    private MedicineCountDao medicineCountDao;

    @Override
    public MedicineCount getMedicineCountByMedicineAndDoctor(Doctor doctor, Medicine medicine) {
        return medicineCountDao.findByDoctorAndMedicine(doctor, medicine);
    }

    @Override
    public MedicineCount getMedicineCountByMedicineAndDoctorAndDiagnsis(Doctor doctor, Medicine medicine, String
            diagnosis) {
        return medicineCountDao.findByDoctorAndMedicineAndDiagosisName(doctor, medicine, diagnosis);
    }
    //@Override
    //public MedicineCount getMedicineCountByMedicineAndDoctorAndEmr(Doctor  doctor, Medicine medicine, Emr emr) {
    //    return medicineCountDao.findByDoctorAndMedicineAndEmr(doctor,medicine,emr);
    //}

    @Override
    public MedicineCount getMedicineByMedicine(Medicine medicine) {
        return medicineCountDao.findByMedicine(medicine);
    }

    @Override
    public MedicineCount getMedicineByMedicineAndDiagName(Medicine medicine, String diagName) {

        return medicineCountDao.findByMedicineAndDiagosisName(medicine, diagName);
    }

    @Override
    public void save(MedicineCount medicineCount) {
        medicineCountDao.save(medicineCount);
    }

    @Override
    public List<MedicineCount> getAllMedCountList() {
        return medicineCountDao.findAll();
    }

    @Override
    public List<MedicineCount> getMedCountListByType(Medicine.Type type) {
        return medicineCountDao.findByMedicineType(type);
    }

    @Override
    public List<MedicineCount> getMedCountListByTypeAndMedNameLike(Medicine.Type type, String medName) {
        return medicineCountDao.findByMedicineTypeAndMedicineNameLike(type, medName);
    }
}
