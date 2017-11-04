package com.qiaobei.hmp.repository;

import com.qiaobei.hmp.modules.entity.ErrorLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by IntelliJ IDEA 2016.4
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 2016/7/6 0006
 * Time 17:00
 */
public interface ErrorLogDao extends JpaRepository<ErrorLog, Long>, JpaSpecificationExecutor<ErrorLog> {
}
