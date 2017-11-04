package com.qiaobei.hmp.modules.repository;

import com.qiaobei.hmp.modules.entity.RemoteRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by Administrator on 2017/4/21 0021.
 */
public interface RemoteRoleDao extends JpaRepository<RemoteRole, Long>, JpaSpecificationExecutor<RemoteRole> {

    public List<RemoteRole> findRemoteRolesByName(String name);
}
