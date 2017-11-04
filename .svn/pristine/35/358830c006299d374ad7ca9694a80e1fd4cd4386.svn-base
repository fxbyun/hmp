package com.qiaobei.hmp.service;

import com.qiaobei.hmp.modules.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by teemoer@cntv.cn on 2016/6/17 0017.
 */
public interface MedicinePrivateService {
    MedicinePrivate getMedPriByMedId(Long id, Doctor doctor);

    List<MedicinePrivate> getMedPriByMedIdList(Long id, Doctor doctor);

    void save(MedicinePrivate medicinePrivate);

    void update(MedicinePrivate medicinePrivate);

    MedicinePrivate getMedPrivate(Long id);

    List<MedicinePrivate> findRateByMoreUser(Long id, Pageable pageable);

    List<MedicinePrivate> findStandardByMoreUser(Long id, Pageable pageable);

    List<MedicinePrivate> findByDoctorAndMedicine(Doctor doctor, Medicine medicine);

    void insertLossDetil(Emr emr);

    Page<MedicinePrivate> findTagTherapy(Doctor doctor, String helpCode, Pageable pageable);

    Page<MedicinePrivate> findTagTherapyByMedName(Doctor doctor, String medName, Pageable pageable);

    public String therapyHtml(MedicinePrivate therapy, String useQty, Medicine.Unit useUnit, String useUnitValue, String therapyCopy);

    List<MedicinePrivate> isDoctorHasThisTherapy(Doctor doctor, Medicine medicine);

    List<MedicinePrivate> findByDoctorAndMedicineName(Doctor doctor, String medName);

    //当数量/单位！=药品换算单位时，计算出1单位的价格
    public Double getPriceAfterUnit(MedicinePrivate mp);

    //根据私有药品的类型、症状、医生
    public Page<MedicinePrivate> getByTypeAndCategoryAndDoctor(Pageable page, Doctor doctor, Medicine.Type type, String category);

    //某种私有药品某个属性医生使用最多的
    String getCategoryMoreDoctorUse(Medicine medicine);

    String getUseModelMoreDoctorUse(Medicine medicine);

    Boolean getUsageFlagMoreDoctorUse(Medicine medicine);

    String getQtyMoreDoctorUse(Medicine medicine);

    Double getRateMoreDoctorUse(Medicine medicine);

    String getStandardMoreDoctorUse(Medicine medicine);

    MedicinePrivate saveDoctorHabit(MedicinePrivate medicinePrivate, MedicineContainer medicineContainer);
}
