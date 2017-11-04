package com.qiaobei.hmp.modules.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "card")
@DynamicInsert(true)
@DynamicUpdate(true)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Card extends StatusEntity {

    private String cardNo;
    private String udid;

    public Card() {
    }

    public Card(Long id) {
        super(id);
    }

    @Column(name = "card_no", nullable = false, length = 20)
    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    @Column(name = "udid", nullable = false, length = 20)
    public String getUdid() {
        return udid;
    }

    public void setUdid(String udid) {
        this.udid = udid;
    }
}