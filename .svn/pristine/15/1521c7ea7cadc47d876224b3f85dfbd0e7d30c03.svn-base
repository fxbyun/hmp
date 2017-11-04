package com.qiaobei.hmp.service.impl;

import com.qiaobei.hmp.modules.entity.ErrorLog;
import com.qiaobei.hmp.repository.ErrorLogDao;
import com.qiaobei.hmp.service.ErrorLogService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by teemoer@cntv.cn on 2016/7/6 0006.
 */
@Service("errorLogService")
public class ErrorLogServiceImpl implements ErrorLogService {
    @Resource
    ErrorLogDao errorLogDao;

    @Override
    public void save(ErrorLog errorLog) {
        errorLogDao.save(errorLog);
    }

    @Override
    public Page<ErrorLog> getErrorLogByPage(Pageable page) {
        return errorLogDao.findAll(page);
    }
}
