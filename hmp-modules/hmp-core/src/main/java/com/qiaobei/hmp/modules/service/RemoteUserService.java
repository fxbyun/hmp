package com.qiaobei.hmp.modules.service;

import com.qiaobei.hmp.modules.entity.RemoteRole;
import com.qiaobei.hmp.modules.entity.RemoteUser;

import java.util.List;

/**
 * Created by Administrator on 2017/4/21 0021.
 */
public interface RemoteUserService {

    /**
     * 根据用户名查找用户
     *
     * @param userName
     * @return
     */
    public RemoteUser queryUserByUserName(String userName);


    /**
     * 根据用户名，生成一个的加密密码,
     */
    public RemoteUser getRandomPassword(RemoteUser user);

    /**
     * 生成随机账号名
     *
     * @return
     */
    public String getRandomUserName();

    /**
     * 返回一个随机生成的用户
     *
     * @return
     */
    public RemoteUser getRandomUser();

    /**
     * 添加一个用户，不能有重复的账号名
     * 并且授予角色
     *
     * @param user
     * @return
     */
    public Boolean addRemoteUser(RemoteUser user, List<RemoteRole> roleList);
}
