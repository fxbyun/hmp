package com.qiaobei.hmp.web;

import com.qiaobei.hmp.modules.entity.Gender;
import com.qiaobei.hmp.modules.entity.Patient;
import com.qiaobei.hmp.service.CardService;
import com.qiaobei.hmp.service.NationService;
import com.qiaobei.hmp.service.PatientService;
import com.qiaobei.hmp.support.Constants;
import com.qiaobei.hmp.support.Result;
import com.qiaobei.hmp.support.Utils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.EnumMap;

@Controller
@RequestMapping(value = "/patient/")
public class PatientController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(PatientController.class);
    @Autowired
    private PatientService patientService;
    @Autowired
    private NationService nationService;

    private static EnumMap<Gender, String> genderMap = new EnumMap<Gender, String>(Gender.class);
    static {
        genderMap.put(Gender.Male, "男");
        genderMap.put(Gender.Female, "女");
    }

    /**
     * 患者列表页
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model) {
        Page<Patient> page = patientService.findPage(Utils.buildPageRequest(1, Constants.PAGZ_SIZE), null, null, null);
        model.addAttribute("page", page);
        return "patient/list";
    }

    /**
     * 患者查询
     */
    @RequestMapping(value = "query", method = RequestMethod.POST)
    public String query(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                        @RequestParam(required = false) String name,
                        @RequestParam(required = false) String mobile,
                        @RequestParam(required = false) String uid, Model model) {
        Page<Patient> page = patientService.findPage(Utils.buildPageRequest(pageNo, Constants.PAGZ_SIZE), name, mobile, uid);
        model.addAttribute("page", page);
        model.addAttribute("name", name);
        model.addAttribute("mobile", mobile);
        model.addAttribute("uid", uid);
        return "patient/list";
    }

    /**
     * 编辑患者页
     */
    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Long id, Model model) {
        Patient op = patientService.getPatient(id);
        model.addAttribute("patient", op);
        model.addAttribute("genderMap", genderMap);
        model.addAttribute("provinceList", nationService.listNation(0));
        model.addAttribute("cityList", nationService.listNation(op.getProvinceNo()));
        model.addAttribute("areaList", nationService.listNation(op.getCityNo()));
        return "patient/edit";
    }

    /**
     * 保存患者
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@ModelAttribute Patient patient, Model model) {
        patientService.savePatient(patient);
        model.addAttribute("msg", "患者信息保存成功。");
        model.addAttribute("patient", patient);
        model.addAttribute("genderMap", genderMap);
        model.addAttribute("provinceList", nationService.listNation(0));
        model.addAttribute("cityList", nationService.listNation(patient.getProvinceNo()));
        model.addAttribute("areaList", nationService.listNation(patient.getCityNo()));
        return "patient/edit";
    }

    /**
     * 重置患者密码
     */
    @RequestMapping(value = "retPwd/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Result retPwd(@PathVariable Long id) {
        Patient op = patientService.getPatient(id);
        String defaultPwd = Utils.encodePwd(StringUtils.right(op.getUid(), 6), op.getSalt());
        op.setPassword(defaultPwd);
        patientService.savePatient(op);
        return Result.ok();
    }

    /**
     * 换卡
     */
    @RequestMapping(value = "updateCard/{id}/{uid}", method = RequestMethod.GET)
    @ResponseBody
    public Result updateCard(@PathVariable Long id, @PathVariable String uid) {
        try {
            patientService.updateCard(id, uid);
            return Result.ok();
        } catch (ServiceException e) {
            return Result.fail(e.getMessage());
        }
    }

    @ModelAttribute
    public void getPatient(@RequestParam(value = "id", defaultValue = "-1") Long id, Model model) {
        if (id != -1) {
            model.addAttribute("patient", patientService.getPatient(id));
        }
    }
}
