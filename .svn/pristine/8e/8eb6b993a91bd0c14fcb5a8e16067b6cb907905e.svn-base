package com.qiaobei.hmp.modules.service;

import com.qiaobei.hmp.modules.entity.IaiLossDetail;
import com.qiaobei.hmp.modules.entity.MedicineContainer;
import com.qiaobei.hmp.modules.entity.RetailMedicine;

import java.util.Date;
import java.util.List;

/**
 *
 */
public interface MedicineContainerService {

    MedicineContainer convert(RetailMedicine retailMedicine);

    IaiLossDetail convert_retail_medicine(MedicineContainer medicineContainer, Double totalNum, Date validityDate);

    List<MedicineContainer> convertList(List<RetailMedicine> retMedList);

    void insertLossDetail(List<MedicineContainer> medConList);
}
