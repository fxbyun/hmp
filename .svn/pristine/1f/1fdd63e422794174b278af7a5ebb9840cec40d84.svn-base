package com.qiaobei.hmp.modules.service.impl;

import com.google.common.collect.Lists;
import com.qiaobei.hmp.modules.entity.*;
import com.qiaobei.hmp.modules.repository.IaiIntoDao;
import com.qiaobei.hmp.modules.repository.IaiIntoDetailDao;
import com.qiaobei.hmp.modules.service.IaiIntoService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by Administrator on 2016/9/23 0023.
 */

@Service("iaiIntoService")
@Transactional
public class IaiIntoServiceImpl implements IaiIntoService {
    @Resource
    private IaiIntoDao iaiIntoDao;
    @Resource
    IaiIntoDetailDao iaiIntoDetailDao;

    @Override
    public long findLast(Long doctorId) {
        Pageable pageable = new PageRequest(0,1, Sort.Direction.DESC,"id");
        List<IaiInto> iaiIntos= iaiIntoDao.findByCreateBy(doctorId,pageable).getContent();
        if(CollectionUtils.isEmpty(iaiIntos)){
            return 0;
        }else {
            return iaiIntos.get(0).getId();
        }
    }

    @Override
    public Page<IaiInto> findByCreateByAndIaiIntoTypeAndStatus(Long createBy, IaiInto.IaiIntoType iaiIntoType, IaiInto.IaiIntoStatus iaiIntoStatus, Pageable pageable) {
        return iaiIntoDao.findByCreateByAndIaiIntoTypeAndStatus(createBy,iaiIntoType,iaiIntoStatus,pageable);
    }

    @Override
    public List<IaiInto> findByCreateByAndIaiIntoTypeAndStatus(Long createBy, IaiInto.IaiIntoType iaiIntoType, IaiInto.IaiIntoStatus iaiIntoStatus) {
        return iaiIntoDao.findByCreateByAndIaiIntoTypeAndStatus(createBy,iaiIntoType,iaiIntoStatus);
    }

    @Override
    public Page<IaiInto> findIaiIntoPageByGoodsNo(Doctor doctor, String goodsNo, IaiInto.IaiIntoType iaiIntoType, Pageable pageable) {
        Page<IaiInto> page = iaiIntoDao.findAll((root, query, cb)->{
            List<Predicate> predicates = Lists.newArrayList();
            predicates.add(
                    cb.equal(root.get(IaiInto_.createBy),doctor.getId())
            );
            predicates.add(
                    cb.equal(root.get("iaiIntoType").as(IaiInto.IaiIntoType.class),iaiIntoType)
            );

            if(StringUtils.isNotEmpty(goodsNo)){
                predicates.add(
                        cb.like(root.get(IaiInto_.goodsNo),"%" + goodsNo + "%")
                );
            }
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        },pageable);

        return page;
    }

    @Override
    public Page<IaiInto> findIaiIntoPageByGoodsNo(Doctor doctor, String goodsNo, IaiInto.IaiIntoStatus status, IaiInto.IaiIntoType iaiIntoType, Pageable pageable) {
        Page<IaiInto> page = iaiIntoDao.findAll((root, query, cb)->{
            List<Predicate> predicates = Lists.newArrayList();
            predicates.add(
                    cb.equal(root.get(IaiInto_.createBy),doctor.getId())
            );
            predicates.add(
                    cb.equal(root.get("status").as(IaiInto.IaiIntoStatus.class),status)
            );
            predicates.add(
                    cb.equal(root.get("iaiIntoType").as(IaiInto.IaiIntoType.class),iaiIntoType)
            );

            if(StringUtils.isNotEmpty(goodsNo)){
                predicates.add(
                        cb.like(root.get(IaiInto_.goodsNo),"%" + goodsNo + "%")
                );
            }
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        },pageable);

        return page;
    }

    @Override
    public void delIaiInto(Long id) {
        iaiIntoDao.delete(id);
    }

    @Override
    public IaiInto findByUuid(String uuid) {
        return iaiIntoDao.findByUuid(uuid);
    }

    @Override
    public Page<IaiInto> findIaiIntoPage(Doctor doctor, String medName, Pageable pageable) {
        Page<IaiInto> page = iaiIntoDao.findAll((root, query, cb)->{
            List<Predicate> predicates = Lists.newArrayList();
            predicates.add(
                    cb.equal(root.get(IaiInto_.createBy),doctor.getId())
            );
            if(StringUtils.isNotEmpty(medName)){
                predicates.add(
                        cb.like(
                                root.join(IaiInto_.intoDetailList).get(IaiIntoDetail_.medicine).get(MedicinePrivate_.name)
                                , "%" + medName + "%"
                        )
                );
            }
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        },pageable);

        return page;
    }

    @Override
    public IaiInto findById(Long iaiIntoId) {
        return iaiIntoDao.getOne(iaiIntoId);
    }

    @Override
    public void save(IaiInto iaiInto) {
        iaiIntoDao.save(iaiInto);
    }

    @Override
    public Page<IaiInto> findIaiIntoPage(String medName, Pageable pageabl2e) {
//

        return iaiIntoDao.findAll((root, query, cb) -> {
            if (StringUtils.isNotEmpty(medName))
                return cb.and(
                        cb.like(
                                root.join(IaiInto_.intoDetailList).get(IaiIntoDetail_.medicine).get(MedicinePrivate_.name)
                                , "%" + medName + "%"
                        )
                );
            return cb.and();
        }, pageabl2e);


    }


    @Override
    public Page<IaiInto> findIaiIntoPage(Doctor doctor, Pageable pageable) {
        return iaiIntoDao.findByCreateBy(doctor.getId(),pageable);
    }
}