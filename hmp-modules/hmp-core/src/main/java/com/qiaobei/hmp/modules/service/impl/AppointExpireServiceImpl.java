package com.qiaobei.hmp.modules.service.impl;

import com.qiaobei.hmp.modules.entity.AppointExpire;
import com.qiaobei.hmp.modules.repository.AppointExpireDao;
import com.qiaobei.hmp.modules.service.AppointExpireService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2016/10/10 0010.
 */
@Service("appointExpireService")
@Transactional
public class AppointExpireServiceImpl implements AppointExpireService {

    @Resource
    private AppointExpireDao appointExpireDao;

    @Override
    public AppointExpire findByOrderId(String orderId) {
        return appointExpireDao.findByOrderId(orderId);
    }

    @Override
    public void save(AppointExpire appointExpire) {
        appointExpireDao.save(appointExpire);
    }
}
