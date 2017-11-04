package com.qiaobei.hmp.modules.service;

import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.IaiInto;
import com.qiaobei.hmp.modules.entity.IaiIntoDetail;
import com.qiaobei.hmp.modules.entity.MedicinePrivate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/9/23 0023.
 */
public interface IaiIntoDetailService {

    void save(IaiIntoDetail iaiIntoDetail);

    Page<IaiIntoDetail> findByIaiIntoPage(IaiInto iaiInto, Pageable pageable);

    List<IaiIntoDetail> findByIaiIntoId(Long iaiIntoId);

    IaiIntoDetail findById(Long id);

    void delIaiDetail(Long id);

    Page<IaiIntoDetail> findAllPage(Doctor doctor,String medName,IaiIntoDetail.DetailStatus status,IaiIntoDetail.DetailType detailType,Pageable pageable);

    //过期的
    Page<IaiIntoDetail> findExpireDetail(Long doctorId,String medName,Pageable pageable);
    List<IaiIntoDetail> findExpireDetail(Long doctorId,String medName);
    //快过期
    Page<IaiIntoDetail> findFastExpireDetail(Long doctorId,String medName,Pageable pageable);

    //快过期的药品信息，不重复
    Page<MedicinePrivate> findExpireMedTag(Long doctorId, Pageable pageable);



    //需补充的药品
    Page<IaiIntoDetail> findNotEnoughPage(Long doctorId,Pageable pageable);

    //需补充的药品
    List<IaiIntoDetail> findNotEnoughList(Long doctorId);

    //
    public List<IaiIntoDetail> findByMedicine(MedicinePrivate medicinePrivate);

    public List<IaiIntoDetail> findByCompanyIdAndMedicine(Long companyId,MedicinePrivate medicinePrivate);

    Page<MedicinePrivate> findLossMedTag(Long doctorId,String medName,Pageable pageable);


    //计算该条药品的库存量（库存量=入库量-有效损耗+无效损耗）
    //有效损耗=主动添加损耗数量（包括过期的药品）+ 开药生成的损耗数量
    //无效损耗=退药损耗记录
    Double getStockNum(IaiIntoDetail detail);

    Map<Long,Double> getStockNumMap(List<IaiIntoDetail> details);

    //根据医生查找已经入库,且保存的
    List<IaiIntoDetail> findByDoctorHasSaveAndStorage(Doctor doctor);
}
