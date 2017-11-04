package com.qiaobei.hmp.web;

import com.qiaobei.hmp.modules.entity.Company;
import com.qiaobei.hmp.modules.entity.StatusEntity;
import com.qiaobei.hmp.service.CompanyService;
import com.qiaobei.hmp.support.Result;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by IntelliJ IDEA 2016.4
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 11/4/15
 * Time 13:29
 */
@Controller
public class CompanyController extends BaseController {
    @Resource
    private CompanyService companyService;

    @RequestMapping(value = "/adv/fragment/companies", method = RequestMethod.GET)
    public String symptomTags(Model model) {
        model.addAttribute("page", companyService.findPage(new PageRequest(0, 10, Sort.Direction.DESC, "id"), "%"));
        return "fragment/companySelect";
    }

    @RequestMapping(value = "/adv/fragment/companies", method = RequestMethod.POST)
    public String symptomTags(@RequestParam(value = "name", required = false) String name,
                              @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                              Model model) {
        model.addAttribute("page", companyService.findPage(new PageRequest(page, 10, Sort.Direction.DESC, "id"), "%"
                + name + "%"));
        model.addAttribute("name", name);
        return "fragment/companySelect";
    }

    /**
     * 新增 药厂
     */
    @RequestMapping(value = "/adv/fragment/saveCompanies")
    @ResponseBody
    public Result saveCompanies(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "code") String code
    ) {
        Company company = new Company();
        company.setName(name);
        company.setStatus(StatusEntity.Status.Normal);
        company.setCode(code);
        companyService.save(company);
        return Result.ok();
    }

}
