package com.qiaobei.hmp.service;

import com.qiaobei.hmp.modules.entity.ConversionApply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ConversionApplyService {

    ConversionApply getConversionApply(Long id);

    void saveConversionApply(ConversionApply apply);

    Page<ConversionApply> findPage(Pageable page, String applyName, String name, ConversionApply.Status status);
}
