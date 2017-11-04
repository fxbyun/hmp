package com.qiaobei.hmp.service.impl;

import com.qiaobei.hmp.modules.entity.MobileUser;
import com.qiaobei.hmp.repository.MoblieUserDao;
import com.qiaobei.hmp.service.MobileUserService;
import org.javasimon.aop.Monitored;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("mobileUserService")
@Transactional
@Monitored
public class MobileUserServiceImpl implements MobileUserService {

    @Resource
    private MoblieUserDao moblieUserDao;

    @Override
    public MobileUser findMobileUser(String phoneNo) {
        return moblieUserDao.findMobileUserByMobile(phoneNo);
    }

    @Override
    public void addMobileUser(MobileUser mobileUser) {
        moblieUserDao.save(mobileUser);
    }

    @Override
    public MobileUser findMobileUserByOpenId(String openId) {
        return moblieUserDao.findMobileUserByOpenId(openId);
    }

    @Override
    public void save(MobileUser mobileUser) {
        moblieUserDao.save(mobileUser);
    }
}
