package com.qiaobei.hmp.web;

import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.support.Constants;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = "/logon", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/logon", method = RequestMethod.POST)
    public String login(String account, String password, Model model) {
        logger.info("Start login...");
        Subject subject = SecurityUtils.getSubject();
        if (StringUtils.isEmpty(account)) {
            return "login";
        }
        UsernamePasswordToken token = new UsernamePasswordToken(account, password);
        if (!subject.isAuthenticated()) {
            String error = null;
            try {
                subject.login(token);
            } catch (UnknownAccountException e) {
                error = "用户名/密码错误";
            } catch (DisabledAccountException e) {
                error = "帐户未审核或被锁定";
            } catch (IncorrectCredentialsException e) {
                error = "用户名/密码错误";
            } catch (AuthenticationException e) {
                //其他错误，比如锁定，如果想单独处理请单独catch处理
                error = "其他未知错误";
            }
            if (StringUtils.isNotEmpty(error)) {
                model.addAttribute("error", error);
                return "login";
            }
        }
        return "redirect:/index";
    }

}
