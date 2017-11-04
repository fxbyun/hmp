package com.qiaobei.hmp.modules.service;

import com.qiaobei.hmp.modules.entity.RemotePermission;

import java.util.List;

/**
 * Created by Administrator on 2017/4/21 0021.
 */
public interface RemotePermissionService {

    public List<RemotePermission> queryPermissionByUserName(String userName);

    public Boolean addRemotePermission(RemotePermission permission);
}
