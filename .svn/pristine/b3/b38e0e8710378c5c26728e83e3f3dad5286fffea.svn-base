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

    //开始坐诊开药减药的算法
    void insertLossDetil(Emr emr);

    //零售开单卖药的算法
    void insertLossDetail(Retail retail);

    Page<MedicinePrivate> findTagTherapy(Doctor doctor, String helpCode, Pageable pageable);

    Page<MedicinePrivate> findTagTherapyByMedName(Doctor doctor, String medName, Pageable pageable);

    public String therapyHtml(MedicinePrivate therapy, String useQty, Medicine.Unit useUnit, String useUnitValue, String therapyCopy);

    public Page<MedicinePrivate> findPrivateMedTag(Doctor doctor, String medName, String barCode, Pageable pageable);

    //当数量/单位！=药品换算单位时，计算出1单位的价格
    public Double getPriceAfterUnit(MedicinePrivate mp);

}
