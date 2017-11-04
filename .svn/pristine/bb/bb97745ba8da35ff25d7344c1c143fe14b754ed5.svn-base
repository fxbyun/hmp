package com.qiaobei.hmp.web;

import com.qiaobei.hmp.service.CompanyService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * Created by yanbin on 11/4/15.
 */
@Controller
public class CompanyController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(CompanyController.class);
    @Resource
    private CompanyService companyService;

    @RequestMapping(value = "/fragment/companies", method = RequestMethod.GET)
    public String symptomTags(Model model) {
        model.addAttribute("page", companyService.findPage(new PageRequest(0, 10, Sort.Direction.DESC, "id"), "%"));
        return "fragment/companySelect";
    }

    @RequestMapping(value = "/fragment/companies", method = RequestMethod.POST)
    public String symptomTags(@RequestParam(value = "name", required = false) String name,
                              @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                              @RequestParam(value = "size", required = false, defaultValue = "10") int size,
                              Model model) {
        model.addAttribute("page", companyService.findPage(new PageRequest(page, 10, Sort.Direction.DESC, "id"), "%"
                + name + "%"));
        model.addAttribute("name", name);
        return "fragment/companySelect";
    }

}
