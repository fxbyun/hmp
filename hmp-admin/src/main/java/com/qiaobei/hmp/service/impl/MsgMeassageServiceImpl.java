package com.qiaobei.hmp.service.impl;

import com.google.common.collect.Lists;
import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.MsgMeassage;
import com.qiaobei.hmp.modules.entity.MsgMeassage_;
import com.qiaobei.hmp.repository.MsgMeassageDao;
import com.qiaobei.hmp.service.MsgMeassageService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by IntelliJ IDEA 15.0
 * User ZW_Teemoer
 * Date 2016/7/8 0008
 * Time 16:09
 */
@Service("msgMeassageService")
public class MsgMeassageServiceImpl implements MsgMeassageService {
    @Resource
    private MsgMeassageDao msgMeassageDao;

    @Override
    public MsgMeassage getMeassageByDoctor(final Doctor doctor) {
        return msgMeassageDao.findOne(new Specification<MsgMeassage>() {
            @Override
            public Predicate toPredicate(Root<MsgMeassage> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = Lists.newArrayList();
                predicateList.add(cb.equal(root.get(MsgMeassage_.doctor), doctor));
                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        });
    }

    @Override
    public void delMeassageByDoctor(final Doctor doctor) {
        msgMeassageDao.delete(msgMeassageDao.findAll(new Specification<MsgMeassage>() {
            @Override
            public Predicate toPredicate(Root<MsgMeassage> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = Lists.newArrayList();
                predicateList.add(cb.equal(root.get(MsgMeassage_.doctor), doctor));
                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        }));
    }

    @Override
    public void save(MsgMeassage msgMeassage) {

        msgMeassageDao.save(msgMeassage);
    }

    @Override
    public List<MsgMeassage> getAll() {
        return msgMeassageDao.findAll();
    }


}
