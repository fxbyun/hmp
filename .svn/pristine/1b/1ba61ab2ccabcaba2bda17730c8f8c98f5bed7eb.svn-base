package com.qiaobei.hmp.web;

import com.alibaba.fastjson.JSONObject;
import com.qiaobei.hmp.modules.entity.Patient;
import com.qiaobei.hmp.service.NationService;
import com.qiaobei.hmp.service.PatientService;
import com.qiaobei.hmp.support.Result;
import com.qiaobei.hmp.support.WeixinUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@RequestMapping("/")
public class AnonController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(AnonController.class);
    @Autowired
    private PatientService patientService;
    @Resource
    private NationService nationService;

    /**
     * 患者帐号绑定页
     */
    @RequestMapping("bind")
    public String bind(@RequestParam("code") String code, Model model) {
        String url = WeixinUtil.GET_OPEN_ID_URL.replaceAll("APPID", WeixinUtil.PAPPID).
                replaceAll("SECRET", WeixinUtil.PAPPSECRET).replaceAll("CODE", code);
        JSONObject json = WeixinUtil.httpRequest(url, "GET", null);
        String openId = json.getString("openid");
        logger.info("bind:" + openId);
        model.addAttribute("OPENID", openId);
        return "bind";
    }

    /**
     * 患者绑定微信
     */
    @RequestMapping(value = "anon/bind", method = RequestMethod.POST)
    public String login(@RequestParam String cardNo, Model model,
                        @RequestParam String pwd,
                        @RequestParam String openId) {
        if (StringUtils.isEmpty(StringUtils.trim(cardNo)) ||
                StringUtils.isEmpty(StringUtils.trim(pwd))) {
            model.addAttribute("error", "卡号和密码不能为空！");
            return "bind";
        }
        logger.info("Login by:" + cardNo);
        UsernamePasswordToken token = new UsernamePasswordToken(cardNo, pwd);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            token.clear();
            Patient p = (Patient) subject.getPrincipal();
            //绑定
            p.setWxId(openId);
            patientService.savePatient(p);
            model.addAttribute("op", p);
            model.addAttribute("msg", "成功！");
        } catch (Exception e) {
            model.addAttribute("error", "绑定失败，卡号或密码错误！");
            logger.error(e.getMessage());
        }
        return "bind";
    }

    @RequestMapping(value = "getNations/{parentId}", method = RequestMethod.GET)
    @ResponseBody
    public Result getNations(@PathVariable("parentId") Integer parentId) {
        return Result.ok(nationService.listNation(parentId));
    }


    /**
     * 自动登陆
     */
    private String autoLogin(String account, String pwd, String returnUrl) {
        UsernamePasswordToken token = new UsernamePasswordToken(account, pwd);
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            try {
                subject.login(token);
                token.clear();
            } catch (Exception e) {
                return "login";
            }
        }
        return returnUrl;
    }
}
