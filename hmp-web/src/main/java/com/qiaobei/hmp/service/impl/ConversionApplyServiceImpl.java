package com.qiaobei.hmp.service.impl;

import com.qiaobei.hmp.modules.entity.ConversionApply;
import com.qiaobei.hmp.repository.ConversionApplyDao;
import com.qiaobei.hmp.service.ConversionApplyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("conversionApplyService")
@Transactional
public class ConversionApplyServiceImpl implements ConversionApplyService {

    @Resource
    private ConversionApplyDao conversionApplyDao;

    @Override
    public ConversionApply getConversion(Long id) {
        return conversionApplyDao.findOne(id);
    }

    @Override
    public void saveConversionApply(ConversionApply apply) {
        conversionApplyDao.save(apply);
    }
}
