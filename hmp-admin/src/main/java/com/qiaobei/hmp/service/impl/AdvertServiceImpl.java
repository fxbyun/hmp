package com.qiaobei.hmp.service.impl;

import com.google.common.collect.Lists;
import com.qiaobei.hmp.modules.entity.*;
import com.qiaobei.hmp.repository.*;
import com.qiaobei.hmp.service.AdvertService;
import com.qiaobei.hmp.service.NoticeService;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.service.spi.ServiceException;
import org.javasimon.aop.Monitored;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Service("advertService")
@Transactional
@Monitored
public class AdvertServiceImpl implements AdvertService {

    private static Logger logger = LoggerFactory.getLogger(AdvertServiceImpl.class);

    @Resource
    private AdvertDao advertDao;
    @Resource
    private DataFileDao fileDao;

    @Override
    @Transactional(readOnly = true)
    public Page<Advert> findPage(Pageable page) {
        return advertDao.findAll(page);
    }

    @Override
    public void save(Advert advert, MultipartFile file) {
        boolean isNew = false;
        if (advert.getId() == null) {
            isNew = true;
        }
        advertDao.save(advert);
        if (file != null && !file.isEmpty()) {
            if (!isNew) {
                DataFile oldFile = fileDao.findByLogicIdAndType(advert.getId(), DataFile.Type.Advert);
                if (oldFile != null) {
                    fileDao.delete(oldFile);
                }
            }
            DataFile f = new DataFile();
            f.setLogicId(advert.getId());
            f.setType(DataFile.Type.Advert);
            f.setFileName(System.currentTimeMillis() + ".jpg");
            try {
                f.setContent(IOUtils.toByteArray(file.getInputStream()));
                fileDao.save(f);
            } catch (Exception e) {
                throw new ServiceException("文件保存失败！");
            }
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Advert findById(Long id) {
        return advertDao.getOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Advert getAdvertByPosition(Advert.Position position) {
        return advertDao.findByPosition(position);
    }

    @Override
    public void deleteAdvert(Advert advert) {
        DataFile oldFile = fileDao.findByLogicIdAndType(advert.getId(), DataFile.Type.Advert);
        if (oldFile != null) {
            fileDao.delete(oldFile);
        }
        advertDao.delete(advert);
    }
}
