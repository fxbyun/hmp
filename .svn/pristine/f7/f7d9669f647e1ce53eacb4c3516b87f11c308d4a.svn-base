package com.qiaobei.hmp.modules.service;

import com.qiaobei.hmp.modules.entity.RetailMedicine;

import java.util.List;
import java.util.Map;


public interface RetailMedicineService {
    RetailMedicine findById(Long retailMedicineId);

    void save(RetailMedicine retailMedicine);

    List<RetailMedicine> findAll();

    void saveList(List<RetailMedicine> retailMedicineList);

    void delRetailMed(RetailMedicine retailMedicine);

    Double getTotalPrice(RetailMedicine retailMedicine);

    //计算该药品的数量
    Double getTotalNum(RetailMedicine retailMedicine);

    //计算retailMedList药品数量
    Map<Long, Double> getTotalNumMap(List<RetailMedicine> redMedList);
}
