package com.qiaobei.hmp.repository;

import com.qiaobei.hmp.modules.entity.Adverting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AdvertingDao extends JpaRepository<Adverting, Long>, JpaSpecificationExecutor<Adverting> {

    public Adverting findByPosition(Adverting.Position position);
}

