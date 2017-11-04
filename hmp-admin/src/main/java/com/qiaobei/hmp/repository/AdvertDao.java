package com.qiaobei.hmp.repository;

import com.qiaobei.hmp.modules.entity.Advert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AdvertDao extends JpaRepository<Advert, Long>, JpaSpecificationExecutor<Advert> {

    public Advert findByPosition(Advert.Position position);
}

