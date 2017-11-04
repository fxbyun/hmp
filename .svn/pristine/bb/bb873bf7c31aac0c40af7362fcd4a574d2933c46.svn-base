package com.qiaobei.hmp.service.impl;

import com.google.common.collect.Lists;
import com.qiaobei.hmp.modules.entity.Adverting;
import com.qiaobei.hmp.modules.entity.Adverting_;
import com.qiaobei.hmp.modules.entity.DataFile;
import com.qiaobei.hmp.modules.entity.User;
import com.qiaobei.hmp.repository.AdvertingDao;
import com.qiaobei.hmp.repository.DataFileDao;
import com.qiaobei.hmp.service.AdvertingService;
import org.apache.commons.io.IOUtils;
import org.hibernate.service.spi.ServiceException;
import org.javasimon.aop.Monitored;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.List;

@Service("advertingService")
@Transactional
@Monitored
public class AdvertingServiceImpl implements AdvertingService {

    private static Logger logger = LoggerFactory.getLogger(AdvertingServiceImpl.class);

    @Resource
    private AdvertingDao advertingDao;
    @Resource
    private DataFileDao fileDao;

    @Override
    @Transactional(readOnly = true)
    public Page<Adverting> findPage(Pageable page) {
        //return advertingDao.findAll((root, query, cb) -> {
        //    query.orderBy(
        //            cb.asc(root.get(Adverting_.position)),
        //            cb.asc(root.get(Adverting_.orderNo))
        //    );
        //    return query.getRestriction();
        //}, page);
        return advertingDao.findAll(page);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Adverting> findPage(Pageable pageable, User user) {
        return advertingDao.findAll((root, query, cb) -> {
            List<Predicate> predicateList = Lists.newArrayList();
            predicateList.add(
                    cb.equal(root.get(Adverting_.createById), user.getId())
            );
            predicateList.add(
                    cb.and(
                            cb.or(
                                    cb.equal(
                                            root.get(Adverting_.type), Adverting.Type.Text
                                    ),
                                    cb.equal(
                                            root.get(Adverting_.type), Adverting.Type.Pic
                                    )
                            )
                    )
            );

            return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
        }, pageable);
    }

    @Override
    public void save(Adverting advert, MultipartFile file) {
        boolean isNew = false;
        if (advert.getId() == null) {
            isNew = true;
        }
        advertingDao.save(advert);
        if (file != null && !file.isEmpty()) {
            if (!isNew) {
                DataFile oldFile = fileDao.findByLogicIdAndType(advert.getId(), DataFile.Type.Adverting);
                if (oldFile != null) {
                    fileDao.delete(oldFile);
                }
            }
            DataFile f = new DataFile();
            f.setLogicId(advert.getId());
            f.setType(DataFile.Type.Adverting);
            //如果是图片
            if (file.getOriginalFilename().indexOf("jpg") > 0) {
                f.setFileName(System.currentTimeMillis() + ".jpg");
            } else if (file.getOriginalFilename().indexOf("mp4") > 0) {
                f.setFileName(System.currentTimeMillis() + ".mp4");
            } else if (file.getOriginalFilename().indexOf("png") > 0) {
                f.setFileName(System.currentTimeMillis() + ".png");
            }


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
    public Adverting findById(Long id) {
        return advertingDao.getOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Adverting getAdvertByPosition(Adverting.Position position) {
        return advertingDao.findByPosition(position);
    }

    @Override
    public void deleteAdvert(Adverting advert) {
        DataFile oldFile = fileDao.findByLogicIdAndType(advert.getId(), DataFile.Type.Advert);
        if (oldFile != null) {
            fileDao.delete(oldFile);
        }
        advertingDao.delete(advert);
    }

    @Override
    public List<Adverting> findAdvingListNeedDel() {
        return advertingDao.findAll((root, query, cb) -> cb.and(
                cb.greaterThan(
                        root.get(Adverting_.indate), 0
                )
        ));
    }
}
