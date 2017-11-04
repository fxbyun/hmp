package com.qiaobei.hmp.service;

import com.qiaobei.hmp.modules.entity.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CompanyService {

    Company getCompany(Long id);

    List<Company> listCompaniesByName(String name);

    Page<Company> findPage(Pageable page, String name);
}
