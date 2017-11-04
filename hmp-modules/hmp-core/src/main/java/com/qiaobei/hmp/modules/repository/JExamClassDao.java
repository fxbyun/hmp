package com.qiaobei.hmp.modules.repository;

import com.qiaobei.hmp.modules.entity.JExamClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Collection;
import java.util.List;

/**
 * Created by IntelliJ IDEA 2016.4
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 2016/11/4 0004
 * Time 11:29
 */
public interface JExamClassDao extends JpaRepository<JExamClass, Long>, JpaSpecificationExecutor<JExamClass> {

    List<JExamClass> findByIdIn(Collection<Long> ids);
}
