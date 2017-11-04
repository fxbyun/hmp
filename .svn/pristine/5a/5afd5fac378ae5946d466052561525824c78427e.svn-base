package com.qiaobei.hmp.service;

import com.qiaobei.hmp.modules.entity.Card;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CardService {

    /**
     * 卡总数
     */
    Long count();

    /**
     * 按id查找
     */
    Card getCard(Long id);

    /**
     * 更新
     */
    void saveCard(Card card);

    /**
     * 删除
     */
    void delCard(Long id);

    /**
     * card分页、条件查询
     */
    Page<Card> findPage(Pageable page, String cardNo, String udid, Card.Status status);

}
