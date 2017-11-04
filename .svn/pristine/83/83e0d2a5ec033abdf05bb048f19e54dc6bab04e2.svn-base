package com.qiaobei.hmp.repository;


import com.qiaobei.hmp.modules.entity.Nation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface NationDao extends JpaRepository<Nation, Long>, JpaSpecificationExecutor<Nation> {

    public List<Nation> findByParentNo(Integer parentNo);
}

