package com.qiaobei.hmp.repository;

import com.qiaobei.hmp.modules.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserDao extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    /**
     * 根据手机号查找用户
     */
    User findByMobile(String mobile);

    /**
     * 根据邮箱查找用户
     */
    User findByEmail(String email);

}

