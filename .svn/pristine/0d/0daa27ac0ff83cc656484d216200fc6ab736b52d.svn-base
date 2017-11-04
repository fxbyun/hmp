package com.qiaobei.hmp.repository;

import com.qiaobei.hmp.modules.entity.MobileUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MoblieUserDao extends JpaRepository<MobileUser,Long>, JpaSpecificationExecutor<MobileUser> {
    MobileUser findMobileUserByMobile(String mobile);
    MobileUser findMobileUserByOpenId(String openId);
}
