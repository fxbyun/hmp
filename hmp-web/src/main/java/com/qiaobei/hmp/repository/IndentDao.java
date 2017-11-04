package com.qiaobei.hmp.repository;


import com.qiaobei.hmp.modules.entity.Indent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IndentDao extends JpaRepository<Indent, Long>, JpaSpecificationExecutor<Indent> {

}

