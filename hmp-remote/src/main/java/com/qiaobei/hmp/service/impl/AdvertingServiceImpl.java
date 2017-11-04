package com.qiaobei.hmp.service.impl;

import com.google.common.collect.Lists;
import com.qiaobei.hmp.modules.entity.*;
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
            f.setType(DataFile.Type.Advert);
            //如果是图片
            if (file.getOriginalFilename().indexOf("jpg") > 0) {
                f.setFileName(System.currentTimeMillis() + ".jpg");
            } else {
                f.setFileName(System.currentTimeMillis() + ".mp4");
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
    public List<Adverting> findAllByDoctorAndSystem(Doctor doctor, User user) {
        return advertingDao.findAll((root, query, cb) -> {
            List<Predicate> predicateList = Lists.newArrayList();
            predicateList.add(
                    cb.or(
                            cb.equal(root.get(Adverting_.createById), doctor.getId()),
                            cb.equal(root.get(Adverting_.createById), user.getId())
                    )
            );
            return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
        });
    }

    /**
     * 根据 创建用户ID 和 创建 类型  返回 Adverting List
     *
     * @return 返回 Adverting List
     */
    @Override
    public List<Adverting> findAllByCreateUserIdAndType(Long createUserId, Adverting.Position positionType) {
        return advertingDao.findAll((root, query, cb) -> {
            List<Predicate> predicateList = Lists.newArrayList();
            predicateList.add(cb.equal(
                    root.get(Adverting_.createById), createUserId
            ));
            predicateList.add(cb.equal(
                    root.get(Adverting_.position), positionType
            ));
            return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
        });
    }

    @Override
    public Long findAdvertingPicAndTextCount(Doctor doctor, User user) {
        return advertingDao.count((root, query, cb) -> {
            List<Predicate> predicates = Lists.newArrayList();
            predicates.add(
                    cb.or(
                            cb.and(cb.equal(root.get(Adverting_.createById), user.getId())),
                            cb.and(cb.equal(root.get(Adverting_.createById), doctor.getId()))
                    )
            );
            predicates.add(
                    cb.and(
                            cb.or(
                                    cb.equal(root.get(Adverting_.type), Adverting.Type.Pic),
                                    cb.equal(root.get(Adverting_.type), Adverting.Type.Text)
                            )
                    )
            );
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        });
    }
}
