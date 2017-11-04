package com.qiaobei.hmp.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2016/7/8.
 */
@Controller
@RequestMapping(value = "/learn")
public class LearnController {


    // TODO 手机登录页
    @RequestMapping(value = "/login")
    public String goToMobileLogin() {

        return "/learn/login";
    }

    // TODO 录入药方病症b
    @RequestMapping(value = "/index")
    public String goToIndex() {

        return "/learn/index";
    }


    // TODO  模板药方
    @RequestMapping(value = "/templatePrescription")
    public String templatePrescription(Model model) {

        return "/learn/templatePrescription";
    }

    // TODO  我的药方
    @RequestMapping(value = "/myPrescription")
    public String myPrescription(Model model) {

        return "/learn/myPrescription";
    }

    // TODO  添加药方
    @RequestMapping(value = "/addPrescription")
    public String addPrescription(Model model) {

        return "/learn/addPrescription";
    }

    // TODO  修改药方
    @RequestMapping(value = "/editPrescription")
    public String editPrescription(Model model) {

        return "/learn/editPrescription";
    }


    // TODO  药方详情
    @RequestMapping(value = "/detailPrescription")
    public String detailPrescription(Model model) {

        return "/learn/detailPrescription";
    }


}
