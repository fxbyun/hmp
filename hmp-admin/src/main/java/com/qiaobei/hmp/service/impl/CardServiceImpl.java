package com.qiaobei.hmp.service.impl;

import com.google.common.collect.Lists;
import com.qiaobei.hmp.modules.entity.Card;
import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.repository.CardDao;
import com.qiaobei.hmp.service.CardService;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.service.spi.ServiceException;
import org.javasimon.aop.Monitored;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Service("cardService")
@Transactional
@Monitored
public class CardServiceImpl implements CardService {

    private static Logger logger = LoggerFactory.getLogger(CardServiceImpl.class);

    @Resource
    private CardDao cardDao;

    @Override
    @Transactional(readOnly = true)
    public Long count() {
        return cardDao.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Card getCard(Long id) {
        return cardDao.findOne(id);
    }

    @Override
    public void saveCard(Card card) {
        cardDao.save(card);
    }

    @Override
    public void delCard(Long id) {
        cardDao.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Card> findPage(Pageable page, String cardNo, String udid, Card.Status status) {
        return cardDao.findAll(buildSpecification(cardNo, udid, status), page);
    }

    private Specification<Card> buildSpecification(final String cardNo, final String udid, final Card.Status status) {
        Specification<Card> spec = new Specification<Card>() {
            @Override
            public Predicate toPredicate(Root<Card> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = Lists.newArrayList();
                if (StringUtils.isNotEmpty(cardNo)) {
                    predicates.add(cb.like(root.get("cardNo").as(String.class), "%" + cardNo + "%"));
                }
                if (StringUtils.isNotEmpty(udid)) {
                    predicates.add(cb.like(root.get("udid").as(String.class), "%" + udid + "%"));
                }
                if (status != null) {
                    predicates.add(cb.equal(root.get("status").as(Card.Status.class), status));
                }
                if (!predicates.isEmpty()) {
                    return cb.and(predicates.toArray(new Predicate[predicates.size()]));
                }
                return cb.conjunction();
            }
        };
        return spec;
    }
}
