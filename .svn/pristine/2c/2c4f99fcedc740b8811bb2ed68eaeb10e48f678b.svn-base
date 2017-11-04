package com.qiaobei.hmp.service;

import com.qiaobei.hmp.modules.entity.Company;
import com.qiaobei.hmp.modules.entity.IaiIntoDetail;
import com.qiaobei.hmp.modules.entity.IaiLossDetail;
import com.qiaobei.hmp.modules.entity.RetailMedicine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface CompanyService {

    Company getCompany(Long id);

    List<Company> listCompaniesByName(String name);

    Page<Company> findPage(Pageable page, String name);

    void save(Company company);

    Map<Long, String> findCompanyByDetails(List<IaiIntoDetail> details);

    Map<Long, String> findCompanyByLossDetails(List<IaiLossDetail> lossDetails);

    Map<Long, String> findCompanyByRetailMedicines(List<RetailMedicine> retailMedicineList);
}
