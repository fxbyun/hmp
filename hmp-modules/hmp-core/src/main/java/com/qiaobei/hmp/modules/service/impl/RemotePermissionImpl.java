package com.qiaobei.hmp.modules.service.impl;

import com.qiaobei.hmp.modules.entity.RemotePermission;
import com.qiaobei.hmp.modules.repository.RemotePermissionDao;
import com.qiaobei.hmp.modules.service.RemotePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("remotePermissionService")
@Transactional
public class RemotePermissionImpl implements RemotePermissionService {

    @Autowired
    private RemotePermissionDao remotePermissionDao;

    @Override
    public Boolean addRemotePermission(RemotePermission permission) {
        if (null == remotePermissionDao.findRemotePermissionsByName(permission.getName())) {
            remotePermissionDao.save(permission);
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    @Override
    public List<RemotePermission> queryPermissionByUserName(String userName) {
        return remotePermissionDao.findRemotePermissionsByUserName(userName);
    }
}
