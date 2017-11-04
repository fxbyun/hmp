package com.qiaobei.hmp.modules.repository;

import com.qiaobei.hmp.modules.entity.ExamLabFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by IntelliJ IDEA 2016.4
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 2016/11/11 0011
 * Time 14:17
 */
public interface ExamLabFileDao extends JpaRepository<ExamLabFile, Long>, JpaSpecificationExecutor<ExamLabFile> {
}
