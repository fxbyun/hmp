package com.qiaobei.hmp.service;

import com.qiaobei.hmp.modules.entity.UserLogin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface UserLoginService {

    /**
     * 保存用户登陆信息
     *
     * @param userLogin
     */
    public void save(UserLogin userLogin);

    /**
     * userLogin分页、条件查询
     *
     * @param page
     * @param searchParams
     * @return
     */
    public Page<UserLogin> findPage(Pageable page, Map<String, Object> searchParams);
}
