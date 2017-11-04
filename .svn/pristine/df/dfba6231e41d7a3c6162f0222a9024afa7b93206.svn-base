package com.qiaobei.hmp.service.impl;

import com.google.common.collect.Lists;
import com.qiaobei.hmp.modules.entity.Company;
import com.qiaobei.hmp.repository.CompanyDao;
import com.qiaobei.hmp.service.CompanyService;
import com.qiaobei.hmp.support.DateFilter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.utils.Numbers;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
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
}
