/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.qiaobei.hmp.web;

import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.security.SecuritySupport;
import com.qiaobei.hmp.support.Constants;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * LoginController负责打开登录页面(GET请求)和登录出错页面(POST请求)，
 * <p/>
 * 真正登录的POST请求由Filter完成,
 *
 * @author calvin
 */
@Controller
public class LoginController extends BaseController {

    @RequestMapping(value = "/logonPage", method = RequestMethod.GET)
    public String login(@RequestParam(required = false) String msg, Model model) {
        if (StringUtils.isNotEmpty(msg)) {
            model.addAttribute("msg", msg);
        }
        return "login";
    }


    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @param rememberMe
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/logon", method = {RequestMethod.POST, RequestMethod.GET})
    public String login(@RequestParam(value = FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, required = false) String username,
                        @RequestParam(value = FormAuthenticationFilter.DEFAULT_PASSWORD_PARAM, required = false) String password,
                        @RequestParam(value = FormAuthenticationFilter.DEFAULT_REMEMBER_ME_PARAM, defaultValue =
                                "false") boolean rememberMe,
                        Model model, HttpServletRequest request) {
        if (username == null || password == null) {
            return "login";
        }
        Subject subject = SecurityUtils.getSubject();
        if (StringUtils.isEmpty(username)) {
            return "login";
        }

        if (subject.isAuthenticated()) {
            Doctor currentDoctor = SecuritySupport.getDoctor();
            if (StringUtils.equals(currentDoctor.getMobile(), username)
                    || StringUtils.equals(currentDoctor.getEmail(), username)) {
                subject.logout();
            }
        }
        String success = "redirect:/advertising/adv";
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        token.setRememberMe(rememberMe);
        if (!subject.isAuthenticated()) {
            String error = null;
            try {
                subject.login(token);
            } catch (UnknownAccountException | IncorrectCredentialsException e) {
                error = "用户名/密码错误";
            } catch (DisabledAccountException e) {
                error = "帐户未审核或被锁定";
            } catch (AuthenticationException e) {
                //其他错误，比如锁定，如果想单独处理请单独catch处理
                error = "其他未知错误";
            }
            if (StringUtils.isNotEmpty(error)) {
                model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, username);
                model.addAttribute("error", error);
                System.out.println("login error:" + error);
                return "login";
            }
        }
        HttpSession session = request.getSession(true);
        session.setAttribute(Constants.CURRENT_USER, SecuritySupport.getDoctor());
        return success;
    }


}
