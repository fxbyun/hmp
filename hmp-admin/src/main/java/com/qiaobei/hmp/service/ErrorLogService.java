package com.qiaobei.hmp.service;

import com.qiaobei.hmp.modules.entity.ErrorLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by teemoer@cntv.cn on 2016/7/6 0006.
 */
public interface ErrorLogService {
    void save(ErrorLog errorLog);

    Page<ErrorLog> getErrorLogByPage(Pageable page);
}
