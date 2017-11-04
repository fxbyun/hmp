package com.qiaobei.hmp.web;

import com.qiaobei.hmp.modules.entity.Pharmacist;
import com.qiaobei.hmp.service.PharmacistService;
import com.qiaobei.hmp.support.Constants;
import com.qiaobei.hmp.support.PharSecuritySupport;
import com.qiaobei.hmp.support.Result;
import com.qiaobei.hmp.support.Utils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springside.modules.security.utils.Digests;
import org.springside.modules.utils.Encodes;

import javax.annotation.Resource;

@Controller
public class PersonalController extends ConstantsController {

    @Resource
    PharmacistService pharmacistService;

    //TODO 个人资料 2016-11-2 12:11:36
    @RequestMapping("/adv/personalInfo")
    public String personalInfo(Model model) {
        model.addAttribute("loginUser", PharSecuritySupport.getPharmacist());
        return "/advertising/personalInfo";
    }

    @RequestMapping("/adv/editUsername")
    @ResponseBody
    public Result editUsername(@RequestParam(value = "username") String username) {
        Pharmacist pharmacist = PharSecuritySupport.getPharmacist();
        pharmacist.setName(username);
        pharmacistService.savePharmacist(pharmacist);
        return Result.ok();
    }

    //TODO 修改密码 2016-11-2 14:36:22
    @RequestMapping(value = "/adv/editPassword", method = RequestMethod.GET)
    public String editPassword(Model model) {
        Pharmacist pharmacist = PharSecuritySupport.getPharmacist();
        model.addAttribute("loginUser", pharmacist);
        return "/advertising/editPassword";
    }

    @RequestMapping(value = "/adv/editPassword", method = RequestMethod.POST)
    public String editPwd(@RequestParam(value = "oldPwd", required = false) String oldPwd,
                          @RequestParam(value = "newPwd", required = false) String newPwd,
                          Model model
    ) {
        Pharmacist pharmacist =PharSecuritySupport.getPharmacist();
        model.addAttribute("loginUser", pharmacist);
        System.out.println(Utils.encodePwd(oldPwd, pharmacist.getSalt()) + "--" + pharmacist.getPassword());

        byte[] salt = Encodes.decodeHex(pharmacist.getSalt());
        byte[] hashPwd = Digests.sha1(oldPwd.getBytes(), salt, Constants.PASSWORD_HASH_INTERATIONS);
        //得出的旧密码的加密以后
        String jiaMiOldPwd = Encodes.encodeHex(hashPwd);
        System.out.println(jiaMiOldPwd + "---" + pharmacist.getPassword());

        if (!StringUtils.equals(jiaMiOldPwd, pharmacist.getPassword())) {
            model.addAttribute("error", "旧密码错误！");
        } else {
            byte[] hashPwd1 = Digests.sha1(newPwd.getBytes(), salt, Constants.PASSWORD_HASH_INTERATIONS);
            //得出的旧密码的加密以后
            String jiaMiOldPwd1 = Encodes.encodeHex(hashPwd1);
            pharmacist.setPassword(jiaMiOldPwd1);
            pharmacistService.savePharmacist(pharmacist);
            model.addAttribute("msg", "密码修改成功。");
        }
        return "advertising/editPassword";
    }
}
