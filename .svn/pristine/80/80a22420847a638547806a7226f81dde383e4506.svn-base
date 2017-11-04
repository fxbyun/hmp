package com.qiaobei.hmp.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.Emr;
import com.qiaobei.hmp.modules.entity.Gender;
import com.qiaobei.hmp.service.DoctorService;
import com.qiaobei.hmp.service.EmrService;
import com.qiaobei.hmp.service.NationService;
import com.qiaobei.hmp.support.Constants;
import com.qiaobei.hmp.support.Result;
import com.qiaobei.hmp.support.SMSUtil;
import com.qiaobei.hmp.support.Utils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springside.modules.utils.Collections3;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.EnumMap;
import java.util.List;

@Controller
@RequestMapping("/anon/")
public class AnonController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(AnonController.class);
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private NationService nationService;
    @Autowired
    private EmrService emrService;

    private static EnumMap<Gender, String> genderMap = new EnumMap<Gender, String>(Gender.class);

    static {
        genderMap.put(Gender.Male, "男");
        genderMap.put(Gender.Female, "女");
    }

    /**
     * 找回密码页
     */
    @RequestMapping("retrieve")
    public String retrieve() {
        return "anon/retrieve";
    }

    /**
     * 重置密码
     */
    @RequestMapping("resetPwd")
    public String resetPwd(@RequestParam("phoneNo") String phoneNo,
                           @RequestParam("newPwd") String newPwd,
                           @RequestParam("authCode") String authCode, HttpServletRequest request,
                           RedirectAttributes redirectAttributes, Model model) {
        String realAuthCode = (String) request.getSession().getAttribute(Constants.AUTH_CODE);
        if (!StringUtils.equals(authCode, realAuthCode)) {
            model.addAttribute("error", "验证码错误！");
            return "anon/retrieve";
        }
        Doctor doctor = doctorService.getDoctorByMobile(phoneNo);
        if (doctor != null) {
            doctor.setPassword(Utils.encodePwd(newPwd, doctor.getSalt()));
            doctorService.saveDoctor(doctor, null, null);
            Subject subject = SecurityUtils.getSubject();
            if (subject.isAuthenticated()) {
                subject.logout();
            }
            redirectAttributes.addFlashAttribute("msg", "您的密码已重置。");
            request.getSession().removeAttribute(Constants.AUTH_CODE);
            return "redirect:/logon";
        } else {
            model.addAttribute("error", "手机号不存在！");
        }
        return "anon/retrieve";
    }

    /**
     * 注册页
     */
    @RequestMapping("register")
    public String register(Model model) {
        Doctor doctor = new Doctor();
        doctor.setGender(Gender.Male);
        model.addAttribute("doctor", doctor);
        model.addAttribute("genderMap", genderMap);
        model.addAttribute("provinceList", nationService.listNation(0));
        return "anon/register";
    }

    @RequestMapping(value = "getNations/{parentId}", method = RequestMethod.GET)
    @ResponseBody
    public Result getNations(@PathVariable("parentId") Integer parentId) {
        return Result.ok(nationService.listNation(parentId));
    }

    /**
     * 发送验证码
     */
    @RequestMapping("sendAuthCode")
    @ResponseBody
    public Result sendAuthCode(@RequestParam("phone") String phone, HttpServletRequest request) {
        String code = Utils.random(4);
        String param = code + "," + Constants.SEND_WAIT_TIME;
        try {
            String callback = SMSUtil.sendSMS(SMSUtil.AUTH_TEMPLATE, phone, param);
            if (StringUtils.isNotEmpty(callback)) {
                JSONObject resp = (JSONObject) JSON.parse(callback);
                JSONObject json = (JSONObject) resp.get("resp");
                if (StringUtils.equals(SMSUtil.SUCCESS_CODE, json.getString("respCode"))) {
                    request.getSession().setAttribute(Constants.AUTH_CODE, code);
                    return Result.ok();
                } else {
                    return Result.fail();
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return Result.fail();
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@ModelAttribute("doctor") Doctor doctor,
                       @RequestParam(value = "file", required = false) MultipartFile file,
                       HttpServletRequest request, RedirectAttributes redirectAttributes, Model model) {
        model.addAttribute("genderMap", genderMap);
        model.addAttribute("provinceList", nationService.listNation(0));
        Doctor d = doctorService.getDoctorByEmailOrMobile(doctor.getEmail(), doctor.getMobile());
        if (d != null) {
            if (d.getStatus() == Doctor.Status.Normal) {
                model.addAttribute("error", "邮箱或手机已注册过！");
                return "anon/register";
            } else {
                List<Emr> emrList = emrService.listEmrByDoctor(d);
                if (Collections3.isEmpty(emrList)) {
                    doctorService.delete(d.getId());
                } else {
                    //有病历信息不能删除医生，不能重复注册
                    model.addAttribute("error", "邮箱或手机已注册过！");
                    return "anon/register";
                }
            }
        }
        if (!StringUtils.isEmpty(doctor.getRecommendMobile())) {
            Doctor dc = doctorService.getDoctorByMobile(doctor.getRecommendMobile());
            if (dc != null) {
                doctor.setRecommender(dc.getName());
            } else {
                model.addAttribute("error", "推荐人不存在！");
                return "anon/register";
            }
        }
        String authCode = (String) request.getSession().getAttribute(Constants.AUTH_CODE);
        if (!StringUtils.equals(doctor.getAuthCode(), authCode)) {
            model.addAttribute("error", "验证码错误！");
            return "anon/register";
        }
        if (file != null && !file.isEmpty()) {
            if (file.getSize() > 2097152) {
                model.addAttribute("error", "上传的文件应不大于2MB！");
                return "anon/register";
            }
            if (!checkFileType(file.getOriginalFilename())) {
                model.addAttribute("error", "请上传jpg格式的图片！");
                return "anon/register";
            }
        }
        doctor.setCreateOn(new Date());
        doctor.setStatus(Doctor.Status.Committed);
        doctor.setPrintType("80mm热敏纸打印"); //默认打印机方式为 80mm热敏纸打印  UPDATE BY zw 2016年5月4日 15:31:33
        try {
            doctorService.saveDoctor(doctor, file, null);
            redirectAttributes.addFlashAttribute("msg", "您的注册信息已提交，工作人员将会在24小时内进行审核，审核结果会以短信通知。");
        } catch (ServiceException e) {
            model.addAttribute("error", e.getMessage());
            return "anon/register";
        }
        request.getSession().removeAttribute(Constants.AUTH_CODE);
        return "redirect:/logon";
    }

    private boolean checkFileType(String fileName) {
        String fileEnd = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        if ("jpg".equals(fileEnd)) {
            return true;
        }
        return false;
    }
}
