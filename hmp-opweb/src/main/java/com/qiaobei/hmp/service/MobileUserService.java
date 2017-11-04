package com.qiaobei.hmp.service;

import com.qiaobei.hmp.modules.entity.MobileUser;
public interface MobileUserService {
    public MobileUser findMobileUser(String phoneNo);

    public void addMobileUser(MobileUser mobileUser);

    public MobileUser findMobileUserByOpenId(String openId);

    void save(MobileUser mobileUser);
}
