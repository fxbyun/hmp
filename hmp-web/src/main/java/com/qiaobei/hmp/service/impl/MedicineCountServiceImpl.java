package com.qiaobei.hmp.service.impl;

import com.google.common.collect.Maps;
import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.Medicine;
import com.qiaobei.hmp.modules.entity.MedicineCount;
import com.qiaobei.hmp.modules.entity.MedicinePrivate;
import com.qiaobei.hmp.repository.MedicineCountDao;
import com.qiaobei.hmp.service.MedicineCountService;
import org.apache.commons.collections.CollectionUtils;
import org.assertj.core.util.Lists;
import org.javasimon.aop.Monitored;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA 2016.4
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 2016/5/27 0027
 * Time 14:59
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

    @Override
    public Map<MedicinePrivate, Long> getDoctorOfCountSize(Doctor doctor, List<MedicinePrivate> medicinePrivateList) {
        if (CollectionUtils.isNotEmpty(medicinePrivateList)) {

            Map<MedicinePrivate, Long> countSizeMap = Maps.newHashMap();
            List<MedicineCount> medicineCountList = Lists.newArrayList();

            //获取私有药品对应的医生使用次数
            medicinePrivateList.forEach(medicinePrivate -> {
                medicineCountList.addAll(medicinePrivate.getMedicine().getMedicineCountList());

                if (CollectionUtils.isEmpty(medicineCountList)) {
                    countSizeMap.put(medicinePrivate, 0L);
                } else {
                    //过滤掉不是同个医生医生
                    Long countSize = medicineCountList.stream().
                            filter(medicineCount -> medicineCount.getDoctor() != doctor)
                            .mapToLong(mc -> mc.getCountSize()).sum();

                    countSizeMap.put(medicinePrivate, countSize);
                }
                //清空临时list
                medicineCountList.clear();
            });

            //map排序
            Map<MedicinePrivate, Long> result = new LinkedHashMap<>();
            countSizeMap.entrySet().stream()
                    .sorted(Map.Entry.<MedicinePrivate, Long>comparingByValue().reversed())
                    .forEachOrdered(x -> result.put(x.getKey(), x.getValue()));

            return result;
        } else {
            return Collections.EMPTY_MAP;
        }
    }

    @Override
    public Long getCountSizeByDoctor(Medicine medicine, Doctor doctor) {
        return medicineCountDao.getCountSizeByDoctorAndMedicine(medicine, doctor);
    }

    @Override
    public List<MedicineCount> getMedCountListByTypeAndMedNameLike(String medName) {
        return medicineCountDao.findByMedicineMedicineNameLike(medName);
    }
}
