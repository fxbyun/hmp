package com.qiaobei.hmp.modules.service;

import com.qiaobei.hmp.modules.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/28 0028.
 */
public interface RetailService {
    void save(Retail retail);

    Retail findById(Long retailId);

    List<Retail> findByRetailStatus(Doctor doctor, Retail.Retail_Status status);

    List<Retail> findByRetailStatusToday(Doctor doctor, Retail.Retail_Status status, Date chargeTime);

    Retail createOne(Patient patient, Pharmacist pharmacist);

    Page<Retail> findAllPage(String orderId, Doctor doctor, Pageable pageable);

    //当日零售
    List<Retail> findByChargeTime(Date chargeTime);

    //当日某个护士的零售列表
    List<Retail> findByChargeTimeAndPharmacist(Date chargeTime, Pharmacist pharmacist);

    void delRetail(Retail retail);

    void delRetailList(List<Retail> retailList);

    //计算药品的合计
    Double getAllMedPrice(Retail retail);

    //计算零售单列表的金额总计
    Double getRetailListPriceSum(List<Retail> retailList);

    //计算该零售单是中药的金额总计
    Double getRetailMedIsChinese(Retail retail);

    //计算该零售单是西药的金额总计
    Double getRetailMedIsWestern(Retail retail);

    //计算该零售列表中所有中药的金额
    Double getRetailListChineseMedPrice(List<Retail> retailList);

    //计算该零售列表中所有西药的金额
    Double getRetailListWesternMedPrice(List<Retail> retailList);

    //查找该患者的购买药品的信息
    List<Retail> getRetailByPatient(Patient patient);

    //按患者、开始时间、结束时间
    List<Retail> getByPatientStartAndEndTime(Patient patient, Date startTime, Date endTime, Doctor doctor);

    //返回该retail的所有中药
    List<RetailMedicine> getChineseMedList(Retail retail);

    //返回该retail的所有西药
    List<RetailMedicine> getWesternMedList(Retail retail);

    //返回该RetailList中所有中药
    Map<Long, List<RetailMedicine>> getChineseMedList(List<Retail> retailList);

    //返回该RetailList中所有西药
    Map<Long, List<RetailMedicine>> getWesternMedList(List<Retail> retailList);
}
