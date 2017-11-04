package com.qiaobei.hmp.service;


import com.qiaobei.hmp.modules.entity.ConversionApply;

public interface ConversionApplyService {

    ConversionApply getConversion(Long id);

    void saveConversionApply(ConversionApply apply);
}
