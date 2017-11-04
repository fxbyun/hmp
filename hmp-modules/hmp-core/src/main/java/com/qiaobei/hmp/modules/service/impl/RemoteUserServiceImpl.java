package com.qiaobei.hmp.modules.service.impl;

import com.qiaobei.hmp.modules.entity.RemoteRole;
import com.qiaobei.hmp.modules.entity.RemoteUser;
import com.qiaobei.hmp.modules.repository.RemoteUserDao;
import com.qiaobei.hmp.modules.service.RemoteUserService;
import com.qiaobei.hmp.modules.support.Constants;
import com.qiaobei.hmp.modules.support.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.security.utils.Digests;
import org.springside.modules.utils.Encodes;

import java.util.Date;
import java.util.List;


@Service("remoteUserService")
@Transactional
public class RemoteUserServiceImpl implements RemoteUserService {

    @Autowired
    private RemoteUserDao remoteUserDao;


    @Override
    public Boolean addRemoteUser(RemoteUser user, List<RemoteRole> roleList) {
        if (null == queryUserByUserName(user.getUserName())) {
            user.setRoleList(roleList);
            remoteUserDao.save(user);
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    @Override
    public RemoteUser getRandomUser() {
        RemoteUser user = new RemoteUser();
        //生成随机名字
        user.setUserName(getRandomUserName());
        //生成默认密码
        user = getRandomPassword(user);
        //默认姓名
        user.setRealName("未设置");
        //创建、修改时间
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        //用户状态
        user.setStatus(RemoteUser.Status.NORMAL);

        return user;
    }

    @Override
    public String getRandomUserName() {
        String userName = Utils.random(8);
        while (true) {
            //判断这个随机生成的用户名是否已经存在
            if (null != queryUserByUserName(userName)) {
                userName = Utils.random(8);
            } else {
                break;
            }
        }
        return userName;
    }

    @Override
    public RemoteUser getRandomPassword(RemoteUser user) {
        //如果这user有问题则重新生成一个
        if (user.getUserName() == null || user.getUserName().length() - 6 < 0) {
            user.setUserName(getRandomUserName());
        }
        //未加密前的明码
        String password = user.getUserName().substring(user.getUserName().length() - 6);

        byte[] salt = Digests.generateSalt(Constants.PASSWORD_SALT_SIZE);
        user.setSalt(Encodes.encodeHex(salt));

        byte[] hashPassword = Digests.sha1(password.getBytes(), salt, Constants.PASSWORD_HASH_INTERATIONS);
        user.setPassword(Encodes.encodeHex(hashPassword));

        return user;
    }

    @Override
    public RemoteUser queryUserByUserName(String userName) {
        return remoteUserDao.findByUserName(userName);
    }
}
