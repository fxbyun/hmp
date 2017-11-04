package com.qiaobei.hmp.web;

import com.qiaobei.hmp.modules.entity.Pharmacist;
import com.qiaobei.hmp.service.DoctorService;
import com.qiaobei.hmp.service.EmrService;
import com.qiaobei.hmp.service.PharmacistService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

@Controller
public class PharmacistController {

    private static Logger logger = LoggerFactory.getLogger(PharmacistController.class);

    @Autowired
    private PharmacistService pharmacistService;
    @Resource
    private DoctorService doctorService;

    @Resource
    private EmrService emrService;

    /**
     * 个人信息
     */
    @RequestMapping(value = "/pf", method = RequestMethod.GET)
    public String profile(Model model) {
        Pharmacist phar = (Pharmacist) SecurityUtils.getSubject().getPrincipal();
        if (phar != null) {
            Pharmacist phar2 = pharmacistService.getPharmacistByAccount(phar.getAccount());
            phar.setPersonType(phar2.getPersonType());
            phar.setDoctorId(phar2.getDoctorId());
            if (phar2.getPersonType() == Pharmacist.PersonType.Nurse) {
                phar.setDoctor(doctorService.getDoctor(phar.getDoctorId()));
                return "redirect:/adv/index";
            }
        }
        if (phar != null && StringUtils.isEmpty(phar.getWxId())) {
            model.addAttribute("error", "请先绑定微信！");
            SecurityUtils.getSubject().logout();
            return "error/error";
        }
        model.addAttribute("phar", phar);
        return "profile";
    }


}
