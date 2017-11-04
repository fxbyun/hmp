package com.qiaobei.hmp.service.impl;

import com.qiaobei.hmp.modules.entity.User;
import com.qiaobei.hmp.repository.UserDao;
import com.qiaobei.hmp.service.UserService;
import com.qiaobei.hmp.support.Constants;
import org.apache.shiro.SecurityUtils;
import org.javasimon.aop.Monitored;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("userService")
@Transactional
@Monitored
public class UserServiceImpl implements UserService {

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private UserDao userDao;

    @Override
    @Transactional(readOnly = true)
    public User getUser(Long id) {
        return userDao.getOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByMobile(String mobile) {
        return userDao.findByMobile(mobile);
    }

    @Override
    public void saveUser(User user) {
        userDao.save(user);
    }

    /**
     * 取出Shiro中的当前用户LoginName.
     */
    public String getCurrentAccount() {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        return user.getName();
    }


    @Override
    public User getCurrentUser() {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        return user;
    }

    /**
     * 判断是否超级管理员.
     */
    public boolean isSupervisor(User user) {
        return ((user.getId() != null) && (user.getId() == Constants.SUPERVISOR_ACCOUNT_ID));
    }

}
