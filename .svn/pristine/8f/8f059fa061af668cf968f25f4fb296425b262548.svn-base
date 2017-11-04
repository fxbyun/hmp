package com.qiaobei.hmp.modules.repository;

import com.qiaobei.hmp.modules.entity.RemoteUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Administrator on 2017/4/21 0021.
 */
public interface RemoteUserDao extends JpaRepository<RemoteUser, Long>, JpaSpecificationExecutor<RemoteUser> {

    public RemoteUser findByUserName(String userName);

}
