package com.qiaobei.hmp.service.impl;

import com.qiaobei.hmp.modules.entity.Conversion;
import com.qiaobei.hmp.modules.entity.Medicine;
import com.qiaobei.hmp.repository.ConversionDao;
import com.qiaobei.hmp.service.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("conversionService")
public class ConversionServiceImpl implements ConversionService {

    @Resource
    private ConversionDao conversionDao;

    @Override
    public Conversion getConversion(Long id) {
        return conversionDao.findOne(id);
    }

}
