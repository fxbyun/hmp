package com.qiaobei.hmp.repository;


import com.qiaobei.hmp.modules.entity.Advert;
import com.qiaobei.hmp.modules.entity.StatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface AdvertDao extends JpaRepository<Advert, Long>, JpaSpecificationExecutor<Advert> {

    public List<Advert> findByPositionAndStatus(Advert.Position position, StatusEntity.Status status);
}

