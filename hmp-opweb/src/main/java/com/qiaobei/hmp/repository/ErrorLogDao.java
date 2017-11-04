package com.qiaobei.hmp.repository;

import com.qiaobei.hmp.modules.entity.ErrorLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by teemoer@cntv.cn on 2016/7/6 0006.
 */
public interface ErrorLogDao extends JpaRepository<ErrorLog, Long>, JpaSpecificationExecutor<ErrorLog> {
}
