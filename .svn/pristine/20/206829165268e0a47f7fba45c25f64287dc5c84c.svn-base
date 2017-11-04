package com.qiaobei.hmp.service;

import com.qiaobei.hmp.modules.entity.User;

public interface UserService {

    /**
     * 按id查找用户
     */
    User getUser(Long id);

    /**
     * 按邮箱查找用户
     */
    User getUserByEmail(String email);

    /**
     * 按手机号查找用户
     */
    User getUserByMobile(String mobile);

    /**
     * 用户更新
     */
    void saveUser(User user);

    /**
     * 取出Shiro中的当前用户LoginName.
     */
    String getCurrentAccount();

    /**
     * 判断是否超级管理员.
     */
    boolean isSupervisor(User user);


    /**
     * 得到当前用户
     *
     * @return
     */
    User getCurrentUser();

}
