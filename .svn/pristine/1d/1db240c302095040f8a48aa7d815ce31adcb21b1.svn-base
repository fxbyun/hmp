package com.qiaobei.hmp.repository;


import com.qiaobei.hmp.modules.entity.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("companyDao")
public interface CompanyDao extends JpaRepository<Company, Long>, JpaSpecificationExecutor<Company> {
    List<Company> findByNameLike(String name);

    Page<Company> findPageByNameLike(Pageable pageable, String name);
}

