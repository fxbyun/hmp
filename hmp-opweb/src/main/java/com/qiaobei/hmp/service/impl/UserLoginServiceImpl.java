package com.qiaobei.hmp.service.impl;

import com.qiaobei.hmp.modules.entity.UserLogin;
import com.qiaobei.hmp.repository.UserLoginDao;
import com.qiaobei.hmp.service.UserLoginService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.DynamicSpecifications;
import org.springside.modules.persistence.SearchFilter;

import javax.annotation.Resource;
import java.util.Map;

@Service("userLoginService")
public class UserLoginServiceImpl implements UserLoginService {

    @Resource
    private UserLoginDao userLoginDao;

    @Transactional
    public void save(UserLogin userLogin) {
        userLoginDao.save(userLogin);
    }

    @Override
    public Page<UserLogin> findPage(Pageable page, Map<String, Object> searchParams) {
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        Specification<UserLogin> spec = DynamicSpecifications.bySearchFilter(filters.values(), UserLogin.class);
        return userLoginDao.findAll(spec, page);
    }
}
