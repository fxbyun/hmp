package com.qiaobei.hmp.repository;

import com.qiaobei.hmp.modules.entity.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CompanyDao extends JpaRepository<Company, Long>, JpaSpecificationExecutor<Company> {
    List<Company> findByNameLike(String name);

    Page<Company> findPageByNameLike(Pageable pageable, String name);

    @Query("select   new com.qiaobei.hmp.modules.entity.Company(c.name) from Company c ")
    List<Company> findAllName();
}

