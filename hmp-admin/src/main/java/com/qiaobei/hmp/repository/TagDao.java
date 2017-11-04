package com.qiaobei.hmp.repository;

import com.qiaobei.hmp.modules.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TagDao extends JpaRepository<Tag, Long>, JpaSpecificationExecutor<Tag> {

}

