package com.qiaobei.hmp.repository;

import com.qiaobei.hmp.modules.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CardDao extends JpaRepository<Card, Long>, JpaSpecificationExecutor<Card> {

    Card getByUdid(String udid);

    Card getByCardNo(String cardNo);
}

