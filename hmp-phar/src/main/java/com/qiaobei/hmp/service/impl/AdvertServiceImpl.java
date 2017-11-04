package com.qiaobei.hmp.service.impl;

import com.google.common.collect.Lists;
import com.qiaobei.hmp.modules.entity.Advert;
import com.qiaobei.hmp.modules.entity.DataFile;
import com.qiaobei.hmp.modules.entity.StatusEntity;
import com.qiaobei.hmp.repository.AdvertDao;
import com.qiaobei.hmp.repository.DataFileDao;
import com.qiaobei.hmp.service.AdvertService;
import org.javasimon.aop.Monitored;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("advertService")
@Transactional
@Monitored
public class AdvertServiceImpl implements AdvertService {


    @Resource
    private AdvertDao advertDao;
    @Resource
    private DataFileDao fileDao;

    @Override
    public List<Advert> getAdvertByPositionAndStatus(Advert.Position position, StatusEntity.Status status) {
        List<Advert> list = Lists.newArrayList();
        List<Advert> adv = advertDao.findByPositionAndStatus(position, status);
        if (adv != null && !adv.isEmpty()) {
            for (int i = 0; i < adv.size(); i++) {
                Advert ad = adv.get(i);
                if (ad.getIndate() == null || ad.getIndate() == 0 || ad.getRemainDay() > 0) {
                    if (ad.getType().equals(Advert.Type.Pic)) {
                        DataFile file = fileDao.findByLogicIdAndType(ad.getId(), DataFile.Type.Advert);
                        if (file != null) {
                            ad.setFile(file);
                        }
                    }
                    list.add(ad);
                } else {
                    ad.setStatus(StatusEntity.Status.Archived);
                    advertDao.save(adv);
                }
            }
        }
        return list;
    }
}
