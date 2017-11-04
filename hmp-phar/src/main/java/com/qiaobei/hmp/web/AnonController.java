package com.qiaobei.hmp.web;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.qiaobei.hmp.modules.entity.Emr;
import com.qiaobei.hmp.modules.entity.Medicine;
import com.qiaobei.hmp.modules.entity.Pharmacist;
import com.qiaobei.hmp.modules.entity.VitalSign;
import com.qiaobei.hmp.service.EmrService;
import com.qiaobei.hmp.service.PharmacistService;
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

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.EnumMap;

@Controller
public class AnonController extends ConstantsController {

    private static Logger logger = LoggerFactory.getLogger(AnonController.class);
    @Autowired
    private PharmacistService pharmacistService;
    @Autowired
    private EmrService emrService;

    private static final EnumMap<Medicine.Unit, String> MEDICINE_UNITS = Maps.newEnumMap(Medicine.Unit.class);

    @ModelAttribute("vitalSignLabels")
    private EnumMap<VitalSign.Type, String> vitalSignLabelMap(HttpServletRequest request) {
        return VITAL_SIGN_LABELS;
    }

    @ModelAttribute("vitalSignUnits")
    private EnumMap<VitalSign.Type, String> vitalSignUnitMap(HttpServletRequest request) {
        return VITAL_SIGN_UNITS;
    }

    static {
        MEDICINE_UNITS.put(Medicine.Unit.bxs, "盒");
        MEDICINE_UNITS.put(Medicine.Unit.btl, "瓶");
        MEDICINE_UNITS.put(Medicine.Unit.pkg, "包");
        MEDICINE_UNITS.put(Medicine.Unit.grs, "粒");
        MEDICINE_UNITS.put(Medicine.Unit.pcs, "支");
        MEDICINE_UNITS.put(Medicine.Unit.g, "g");
        MEDICINE_UNITS.put(Medicine.Unit.mg, "mg");
        MEDICINE_UNITS.put(Medicine.Unit.ml, "ml");
        MEDICINE_UNITS.put(Medicine.Unit.oth, "单位");
    }

    /**
     * 药剂师帐号绑定页
     */
    @RequestMapping("/anon/bind")
    public String bind(@RequestParam("code") String code, Model model) {
        String url = WeixinUtil.GET_OPEN_ID_URL.replaceAll("APPID", WeixinUtil.HAPPID).
                replaceAll("SECRET", WeixinUtil.HAPPSECRET).replaceAll("CODE", code);
        JSONObject json = WeixinUtil.httpRequest(url, "GET", null);
        String openId = json.getString("openid");
        logger.info("OpenId:" + openId);
        model.addAttribute("OPENID", openId);
        return "bind";
    }

    /**
     * 药单列表
     */
    @RequestMapping("/anon/emr/list")
    public String list(Model model) {
        model.addAttribute("emr", new ArrayList<Emr>());
        return "emrList";
    }

    /**
     * 查看药单
     */
    @RequestMapping("/anon/emr/{id}")
    public String view(@PathVariable Long id, Model model) {
        model.addAttribute("emr", emrService.getEmr(id));
        model.addAttribute("medicineUnits", MEDICINE_UNITS);
        return "emr";
    }

    /**
     * 药剂师登陆、绑定微信
     */
    @RequestMapping(value = "/anon/bind", method = RequestMethod.POST)
    public String login(@RequestParam String cardNo, Model model,
                        @RequestParam String pwd,
                        @RequestParam String openId) {
        if (StringUtils.isEmpty(StringUtils.trim(cardNo)) ||
                StringUtils.isEmpty(StringUtils.trim(pwd))) {
            model.addAttribute("error", "帐号和密码不能为空！");
            return "bind";
        }
        logger.info("Bind by:" + openId);
        UsernamePasswordToken token = new UsernamePasswordToken(cardNo, pwd);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            token.clear();
            Pharmacist p = (Pharmacist) subject.getPrincipal();
            //绑定
            p.setWxId(openId);
            pharmacistService.savePharmacist(p);
            model.addAttribute("msg", "绑定成功！");
        } catch (Exception e) {
            model.addAttribute("error", "登陆失败，帐号或密码错误！");
            logger.error(e.getMessage());
        }
        return "bind";
    }
}
