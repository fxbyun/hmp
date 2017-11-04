/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.qiaobei.hmp.web;

import com.qiaobei.hmp.modules.entity.MobileUser;
import com.qiaobei.hmp.security.SecuritySupport;
import com.qiaobei.hmp.service.MobileUserService;
import com.qiaobei.hmp.support.Constants;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private MobileUserService mobileUserService;

    @RequestMapping(value = "/logon", method = RequestMethod.GET)
    public String login(@RequestParam(required = false) String msg, Model model,HttpServletRequest request) {
        //获得用户登录微信时拿到的openId;
        Subject subject = SecurityUtils.getSubject();
        
        UsernamePasswordToken authToken=new UsernamePasswordToken("YJZ","YJZ");
        String error = null; 
        if(!subject.isAuthenticated()){
            try {
                subject.login(authToken);
            } catch (IncorrectCredentialsException | ExpiredCredentialsException e) {
                error = e.getMessage();
            } catch (Exception e) {
                error = "{error:LoginController}:未知错误:"+e.getMessage();
            }
        }
        //如果没有任何error说明该用户之前是登录过的,不需要跳转到登录页面
        if(StringUtils.isEmpty(error)){
            //验证成功
            HttpSession session = request.getSession(true);
            session.setAttribute(Constants.CURRENT_USER, SecuritySupport.getMobileUser());
            return "redirect:/outpatient/clinicIndex";
        }

        System.out.println("{LoginController:login}"+error);
        if (StringUtils.isNotEmpty(msg)) {
            model.addAttribute("msg", msg);
        }
        return "login";
    }

    //TODO 首页 2016-8-15 14:29:37
    @RequestMapping(value = "/index")
    public String index() {

        return "index";
    }

    @RequestMapping(value = "/anon/userLogin", method = RequestMethod.POST)
    public String UserLogin(@RequestParam(value = "phoneNo", required = true) String phoneNo,
                        @RequestParam(value = "authCode", required = true) String authCode,
                        Model model, HttpServletRequest request) {

        //判断验证码是否正确
        String error = "";
        String getAuthCode = (String) request.getSession().getAttribute(Constants.AUTH_CODE);
        if (!authCode.equals(getAuthCode)) {
            error = "验证码错误！";
        }

        if (StringUtils.isNotEmpty(error)) {
            model.addAttribute("error", error);
            return "login";
        }
        //判断该用户是否已经存在，不存在的话存进去
        MobileUser isExistUser = mobileUserService.findMobileUser(phoneNo);
        if (isExistUser == null) {
            isExistUser = new MobileUser();
            isExistUser.setMobile(phoneNo);
            mobileUserService.addMobileUser(isExistUser);
        }
        //用户信息存在session中
        MobileUser mobileUser = mobileUserService.findMobileUser(phoneNo);

        model.addAttribute(Constants.CURRENT_USER, mobileUser);
        return "redirect:/outpatient/clinicIndex";

    }

    @RequestMapping(value = "/logon", method = RequestMethod.POST)
    public String login(@RequestParam(value = "phoneNo", required = true) String phoneNo,
                        @RequestParam(value = "authCode", required = true) String authCode,
                        Model model, HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        if (StringUtils.isEmpty(phoneNo)||StringUtils.isEmpty(authCode)) {
            return "login";
        }
        boolean needLogin = !subject.isAuthenticated();
        if (subject.isAuthenticated()) {
            MobileUser currentUser = SecuritySupport.getMobileUser();
            if (StringUtils.equals(currentUser.getMobile(), phoneNo)) {
                subject.logout();
                needLogin = true;
            }
        }
        UsernamePasswordToken authToken=new UsernamePasswordToken(phoneNo,authCode);
        String error=null;
        if(!subject.isAuthenticated()){

            try {
                subject.login(authToken);
            }catch (ExpiredCredentialsException e){
                error=e.getMessage();
            }catch (IncorrectCredentialsException e){
                error=e.getMessage();
            }catch (Exception e) {
                error = "{error:LoginController}:未知错误:"+e.getMessage();
                //error = null;
            }
        }
        if (StringUtils.isNotEmpty(error)) {
            model.addAttribute("userPhoneNo",phoneNo);
            model.addAttribute("error", error);
            System.out.println("login error:" + error);
            return "login";
        }
        //验证成功
        HttpSession session = request.getSession(true);
        session.setAttribute(Constants.CURRENT_USER, SecuritySupport.getMobileUser());
        return "redirect:/outpatient/clinicIndex";
    }
}
