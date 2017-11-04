package com.qiaobei.hmp.modules.repository;

import com.qiaobei.hmp.modules.entity.RemotePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2017/4/21 0021.
 */
public interface RemotePermissionDao extends JpaRepository<RemotePermission, Long>, JpaSpecificationExecutor<RemotePermission> {

    @Query("select RP from RemotePermission RP join RP.roleList roles join roles.userList users where users.userName =?1  ")
    List<RemotePermission> findRemotePermissionsByUserName(String userName);

    List<RemotePermission> findRemotePermissionsByName(String name);
}
