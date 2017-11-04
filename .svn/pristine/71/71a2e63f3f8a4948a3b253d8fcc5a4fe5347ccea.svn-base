package com.qiaobei.hmp.service.impl;

import com.qiaobei.hmp.modules.entity.ErrorLog;
import com.qiaobei.hmp.modules.entity.ErrorLogService;
import com.qiaobei.hmp.repository.ErrorLogDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by IntelliJ IDEA 2016.4
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 2016/7/6 0006
 * Time 14:59
 */
@Service("errorLogService")
@Transactional
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
