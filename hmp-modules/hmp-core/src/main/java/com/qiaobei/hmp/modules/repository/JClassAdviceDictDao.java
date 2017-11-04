package com.qiaobei.hmp.modules.repository;

import com.qiaobei.hmp.modules.entity.JClassAdviceDict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by IntelliJ IDEA 2016.4
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 2016/11/4 0004
 * Time 11:27
 */
public interface JClassAdviceDictDao extends JpaRepository<JClassAdviceDict, Long>, JpaSpecificationExecutor<JClassAdviceDict> {
    List<JClassAdviceDict> findByHelpCodeIsNull();
}
