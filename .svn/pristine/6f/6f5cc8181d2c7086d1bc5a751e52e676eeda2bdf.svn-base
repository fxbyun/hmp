package com.qiaobei.hmp.service.impl;

import com.qiaobei.hmp.modules.entity.Company;
import com.qiaobei.hmp.modules.entity.IaiIntoDetail;
import com.qiaobei.hmp.modules.entity.IaiLossDetail;
import com.qiaobei.hmp.modules.entity.RetailMedicine;
import com.qiaobei.hmp.repository.CompanyDao;
import com.qiaobei.hmp.service.CompanyService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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
    public Map<Long, String> findCompanyByRetailMedicines(List<RetailMedicine> retailMedicineList) {
        Map<Long, String> companyMap = new HashedMap();
        for (RetailMedicine rm : retailMedicineList) {
            //公司
            Company company = rm.getMedicinePrivate().getDefaultCompany();
            if (company != null) {
                companyMap.put(rm.getId(), company.getName());
            } else {
                companyMap.put(rm.getId(), null);
            }
        }
        return companyMap;
    }

    @Override
    public Map<Long, String> findCompanyByLossDetails(List<IaiLossDetail> lossDetails) {
        Map<Long, String> companyMap = new HashedMap();
        for (IaiLossDetail detail : lossDetails) {
            //公司
            Company company = companyDao.getOne(detail.getCompanyId());
            if (company != null) {
                companyMap.put(detail.getId(), company.getName());
            } else {
                companyMap.put(detail.getId(), null);
            }
        }
        return companyMap;
    }

    @Override
    public Map<Long, String> findCompanyByDetails(List<IaiIntoDetail> details) {
        Map<Long, String> companyMap = new HashedMap();
        for (IaiIntoDetail detail : details) {
            //公司
            Company company = companyDao.getOne(detail.getCompanyId());
            if (company != null) {
                companyMap.put(detail.getId(), company.getName());
            } else {
                companyMap.put(detail.getId(), null);
            }
        }
        return companyMap;
    }


    @Override
    public void save(Company company) {
        companyDao.save(company);
    }
}
