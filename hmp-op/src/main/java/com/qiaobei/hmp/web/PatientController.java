package com.qiaobei.hmp.web;

import com.alibaba.fastjson.JSONObject;
import com.qiaobei.hmp.modules.entity.Emr;
import com.qiaobei.hmp.modules.entity.Evaluate;
import com.qiaobei.hmp.modules.entity.Gender;
import com.qiaobei.hmp.modules.entity.Patient;
import com.qiaobei.hmp.service.EmrService;
import com.qiaobei.hmp.service.EvaluateService;
import com.qiaobei.hmp.service.NationService;
import com.qiaobei.hmp.service.PatientService;
import com.qiaobei.hmp.support.Utils;
import com.qiaobei.hmp.support.WeixinUtil;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;
import java.util.EnumMap;
import java.util.List;

@Controller
@RequestMapping(value = "/")
public class PatientController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(PatientController.class);
    private static EnumMap<Gender, String> genderMap = new EnumMap<Gender, String>(Gender.class);

    static {
        genderMap.put(Gender.Male, "男");
        genderMap.put(Gender.Female, "女");
    }

    @Autowired
    private PatientService patientService;
    @Autowired
    private EmrService emrService;
    @Autowired
    private EvaluateService evaluateService;
    @Resource
    private NationService nationService;

    /**
     * 病历列表
     */
    @RequestMapping(value = "emrList/{id}", method = RequestMethod.GET)
    public String emrList(@PathVariable Long id, Model model) {
        Patient op = patientService.getPatientById(id);
        model.addAttribute("emrList", emrService.findByPatientUid(op.getUid()));
        return "op/emrList";
    }

    /**
     * 病历详情
     */
    @RequestMapping(value = "emr/{id}", method = RequestMethod.GET)
    public String emr(@PathVariable Long id, Model model) {
        System.out.println("微信推送id:" + id);
        model.addAttribute("emr", emrService.findById(id));
        model.addAttribute("eval", new Evaluate());
        model.addAttribute("evaluateList", evaluateService.getByEmr(new Emr(id)));
        return "op/emr";
    }

    /**
     * 病历评价、咨询
     */
    @RequestMapping(value = "eval", method = RequestMethod.POST)
    public String eval(@Valid @ModelAttribute("eval") Evaluate eval, Model model) {
        eval.setCreateTime(new Date());
        eval.setReadFlag(false);
        evaluateService.save(eval);
        Long emrId = eval.getEmr().getId();
        model.addAttribute("emr", emrService.findById(emrId));
        model.addAttribute("eval", new Evaluate());
        model.addAttribute("evaluateList", evaluateService.getByEmr(new Emr(emrId)));
        return "op/emr";
    }

    /**
     * 患者列表
     */
    @RequestMapping(value = "pf", method = RequestMethod.GET)
    public String profile(@RequestParam("code") String code, Model model) {
        String url = WeixinUtil.GET_OPEN_ID_URL.replaceAll("APPID", WeixinUtil.PAPPID).
                replaceAll("SECRET", WeixinUtil.PAPPSECRET).replaceAll("CODE", code);
        JSONObject json = WeixinUtil.httpRequest(url, "GET", null);
        String openId = json.getString("openid");
        List<Patient> opList=null;
        if(!openId.isEmpty()){
            logger.info("profile:" + openId);
            opList = patientService.getPatientByWxId(openId);

        }
        if (Collections.isNullOrEmpty(opList)) {
            model.addAttribute("OPENID", openId);
            return "bind";
        }
        model.addAttribute("opList", opList);
        model.addAttribute("type", "A");
        return "op/opList";
    }

    /**
     * 个人信息列表
     */
    @RequestMapping(value = "pf/{id}", method = RequestMethod.GET)
    public String profile(@PathVariable Long id, Model model) {
        Patient op = patientService.getPatientById(id);
        model.addAttribute("op", op);
        return "op/profile";
    }

    /**
     * 修改个人信息
     */
    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Long id, Model model) {
        Patient op = patientService.getPatientById(id);
        model.addAttribute("op", op);
        model.addAttribute("genderMap", genderMap);
        model.addAttribute("provinceList", nationService.listNation(0));
        model.addAttribute("cityList", nationService.listNation(op.getProvinceNo()));
        model.addAttribute("areaList", nationService.listNation(op.getCityNo()));
        return "op/edit";
    }

    /**
     * 患者帐号解除绑定页
     */
    @RequestMapping(value = "unbind/{id}", method = RequestMethod.GET)
    public String unbind(@PathVariable Long id, Model model) {
        Patient op = patientService.getPatientById(id);
        model.addAttribute("OPENID", op.getWxId());
        op.setWxId(null);
        patientService.savePatient(op);
        return "bind";
    }

    /**
     * 保存个人信息
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String edit(@ModelAttribute("op") Patient op, Model model) {
        patientService.savePatient(op);
        model.addAttribute("op", op);
        return "op/profile";
    }

    /**
     * 患者列表
     */
    @RequestMapping(value = "myEmr", method = RequestMethod.GET)
    public String myEmr(@RequestParam("code") String code, Model model) {
        String url = WeixinUtil.GET_OPEN_ID_URL.replaceAll("APPID", WeixinUtil.PAPPID).
                replaceAll("SECRET", WeixinUtil.PAPPSECRET).replaceAll("CODE", code);
        JSONObject json = WeixinUtil.httpRequest(url, "GET", null);
        String openId = json.getString("openid");
        logger.info("myEmr:" + openId);
        List<Patient> opList = patientService.getPatientByWxId(openId);
        if (Collections.isNullOrEmpty(opList)) {
            model.addAttribute("OPENID", openId);
            return "bind";
        }
        model.addAttribute("opList", patientService.getPatientByWxId(openId));
        model.addAttribute("type", "B");
        return "op/opList";
    }

    /**
     * 修改密码页
     */
    @RequestMapping(value = "pwd/{id}", method = RequestMethod.GET)
    public String updatePwd(@PathVariable Long id, Model model) {
        model.addAttribute("opId", id);
        return "updatePwd";
    }

    /**
     * 保存密码
     */
    @RequestMapping(value = "pwd/save", method = RequestMethod.POST)
    public String updatePwd(@RequestParam String oldPwd,
                            @RequestParam String newPwd,
                            @RequestParam Long opId,
                            Model model) {
        Patient op = patientService.getPatientById(opId);
        String salt = op.getSalt();
        if (!StringUtils.equals(Utils.encodePwd(oldPwd, salt), op.getPassword())) {
            model.addAttribute("error", "旧密码错误！");
        } else {
            op.setPassword(Utils.encodePwd(newPwd, salt));
            patientService.savePatient(op);
            model.addAttribute("msg", "密码修改成功。");
        }
        return "updatePwd";
    }

    @ModelAttribute
    public void getPatient(@RequestParam(value = "id", defaultValue = "-1") Long id, Model model) {
        if (id != -1) {
            model.addAttribute("op", patientService.getPatientById(id));
        }
    }
}
