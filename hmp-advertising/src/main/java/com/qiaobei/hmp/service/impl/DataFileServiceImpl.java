package com.qiaobei.hmp.service.impl;

import com.google.common.collect.Lists;
import com.qiaobei.hmp.modules.entity.Adverting;
import com.qiaobei.hmp.modules.entity.DataFile;
import com.qiaobei.hmp.modules.entity.DataFile_;
import com.qiaobei.hmp.modules.entity.User;
import com.qiaobei.hmp.repository.DataFileDao;
import com.qiaobei.hmp.service.AdvertingService;
import com.qiaobei.hmp.service.DataFileService;
import org.javasimon.aop.Monitored;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.stream.Collectors;

@Service("dataFileService")
@Transactional
@Monitored
public class DataFileServiceImpl implements DataFileService {

    private static Logger logger = LoggerFactory.getLogger(DataFileServiceImpl.class);

    @Resource
    private DataFileDao dataFileDao;

    @Resource
    private AdvertingService advertingService;

    @Override
    @Transactional(readOnly = true)
    public DataFile getDataFile(Long logicId, DataFile.Type type) {
        return dataFileDao.findByLogicIdAndType(logicId, type);
    }

    @Override
    public byte[] getImagByUrl(String url) {
        return dataFileDao.findOne((root, query, cb) -> {
            List<Predicate> predicateList = Lists.newArrayList();
            predicateList.add(
                    cb.like(
                            root.get(DataFile_.fileName), "%" + url + "%"
                    )
            );
            return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
        }).getContent();
//        return new byte[0];
    }

    /**
     * 获取 广告机 中间部分 系统设置的  图片
     *
     * @return List<DataFile>
     */
    @Override
    public List<DataFile> findSysCenterImg(User user) {
        List<Adverting> advertings = advertingService.findAllByCreateUserIdAndType(user.getId(), Adverting.Position.SystemCenterImg);

        List<DataFile> dataFileList = Lists.newArrayList();
        dataFileList.addAll(advertings.stream().map(adverting -> dataFileDao.findOne((root, query, cb) -> {
            List<Predicate> predicates = Lists.newArrayList();
            predicates.add(cb.equal(root.get(DataFile_.logicId), adverting.getId()));
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        })).collect(Collectors.toList()));
        return dataFileList;
    }
}
