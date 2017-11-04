package com.qiaobei.hmp.modules.service.impl;

import com.qiaobei.hmp.modules.entity.RemoteRole;
import com.qiaobei.hmp.modules.repository.RemoteRoleDao;
import com.qiaobei.hmp.modules.service.RemoteRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service("remoteRoleService")
@Transactional
public class RemoteRoleServiceImpl implements RemoteRoleService {

    @Autowired
    private RemoteRoleDao remoteRoleDao;


    @Override
    public RemoteRole queryRoleByName(String name) {
        List<RemoteRole> roleList = remoteRoleDao.findRemoteRolesByName(name);
        if (!CollectionUtils.isEmpty(roleList)) {
            return roleList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public Boolean addRemoteRole(RemoteRole role) {
        if (null == queryRoleByName(role.getName())) {
            remoteRoleDao.save(role);
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }
}
