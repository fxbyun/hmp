package com.qiaobei.hmp.repository;


import com.qiaobei.hmp.modules.entity.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserLoginDao extends JpaRepository<UserLogin, Long>, JpaSpecificationExecutor<UserLogin> {

}

