package com.qiaobei.hmp.service;


import com.qiaobei.hmp.modules.entity.Card;

public interface CardService {

    /**
     * 按id查找
     */
    Card getCardById(Long id);

    Card getCardByUdid(String udid);

    Card getCardByNo(String cardNo);

}
