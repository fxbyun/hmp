package com.qiaobei.hmp.service;


import com.qiaobei.hmp.modules.entity.Conversion;
import com.qiaobei.hmp.modules.entity.Medicine;

public interface ConversionService {

    Conversion getConversion(Long id);

    Conversion getConversionByMedicineAndToUnit(Medicine medicine, Medicine.Unit toUnit);
}
