package com.qiaobei.hmp.service.impl;

import com.qiaobei.hmp.modules.entity.Company;
import com.qiaobei.hmp.repository.CompanyDao;
import com.qiaobei.hmp.service.CompanyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("companyService")
@Transactional
public class CompanyServiceImpl implements CompanyService {

    @Resource
    private CompanyDao companyDao;

    @Override
    public Company getCompany(Long id) {
        return companyDao.findOne(id);
    }

    @Override
    public List<Company> listCompaniesByName(String name) {
        return companyDao.findByNameLike(name);
    }

    @Override
    public Page<Company> findPage(Pageable page, String name) {
        return companyDao.findPageByNameLike(page, name);
    }

    @Override
    public List<Company> listCompanies() {
        return companyDao.findAllName();
    }
}
