package com.qiaobei.hmp.web;

import com.google.common.collect.Lists;
import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.Gender;
import com.qiaobei.hmp.modules.entity.Patient;
import com.qiaobei.hmp.modules.entity.StatusEntity;
import com.qiaobei.hmp.security.SecuritySupport;
import com.qiaobei.hmp.service.CardService;
import com.qiaobei.hmp.service.NationService;
import com.qiaobei.hmp.service.PatientService;
import com.qiaobei.hmp.support.PatientLoginResult;
import com.qiaobei.hmp.support.SMSUtil;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

@Controller
public class PatientController extends ConstantsController {

    private static Logger logger = LoggerFactory.getLogger(PatientController.class);
    @Resource
    private PatientService patientService;

    //    @Resource
//    private CardService cardService;
    @Resource
    private NationService nationService;


    @ModelAttribute("groupArrys")
    private Map<String, String> groupArrys(HttpServletRequest request) {
        return GROUP_ARRYS;
    }

    @ModelAttribute("xwArrys")
    private List<String> xwArrys(HttpServletRequest request) {
        return XW_ARRYS;
    }


    @ModelAttribute("genderMap")
    private EnumMap<Gender, String> genderMap(HttpServletRequest request) {
        return GENDER_MAP;
    }


    @RequestMapping(value = "/patient/query", method = RequestMethod.GET)
    @ResponseBody
    public List<Patient> query(@RequestParam String key) {
        List<Patient> list = Lists.newArrayList();
        if (StringUtils.isNotEmpty(key))
            list = patientService.queryByMobile(key);
        return list;
    }


    @RequestMapping(value = "/patient/login4Udid", method = RequestMethod.POST)
    @ResponseBody
    public PatientLoginResult loginByUdid(@RequestParam String udid, Model model) {
//        Patient patient = patientService.getPatientByUdid(udid);
//        if (patient != null) {
//            List<Emr> emrList = getEmrList4Today(patient.getUid());
//            if (Collections3.isNotEmpty(emrList)) {
//                PatientLoginResult result = PatientLoginResult.ok(PatientLoginResult.Status.Used);
//                result.setData(Collections3.getFirst(emrList).getId());
//                return result;
//            }
//            PatientLoginResult result = PatientLoginResult.ok(PatientLoginResult.Status.Passed);
//            result.setData(patient.getUid());
//            return result;
//        } else {
//            Card card = cardService.getCardByUdid(udid);
//            if (card != null) {
//                patient = patientService.savePatient4Card(card);
//                if (patient != null) {
//                    PatientLoginResult result = PatientLoginResult.ok(PatientLoginResult.Status.NewCard);
//                    result.setData(patient.getUid());
//                    return result;
//                }
//            }
//        }
        return PatientLoginResult.ok(PatientLoginResult.Status.Invalid);
    }


    @RequestMapping(value = "/patient/login4Uid", method = RequestMethod.POST)
    @ResponseBody
    public PatientLoginResult loginByUidAndPwd(@RequestParam String uid, @RequestParam String pwd, Model model) {
//        Patient patient = patientService.getPatientByUid(uid);

//        if (patient != null) {
//            if (StringUtils.equals(Utils.encodePwd(pwd, patient.getSalt()), patient.getPassword())) {
//                List<Emr> emrList = getEmrList4Today(uid);
//                if (Collections3.isNotEmpty(emrList)) {
//                    PatientLoginResult result = PatientLoginResult.ok(PatientLoginResult.Status.Used);
//                    result.setData(Collections3.getFirst(emrList).getId());
//                    return result;
//                }
//                PatientLoginResult result = PatientLoginResult.ok(PatientLoginResult.Status.Passed);
//                result.setData(patient.getUid());
//                return result;
//            }
//        } else {
//            Card card = cardService.getCardByNo(uid);
//            if (card != null) {
//                patient = patientService.savePatient4Card(card);
//                if (patient != null) {
//                    PatientLoginResult result = PatientLoginResult.ok(PatientLoginResult.Status.NewCard);
//                    result.setData(patient.getUid());
//                    return result;
//                }
//            }
//        }
        return PatientLoginResult.ok(PatientLoginResult.Status.Invalid);
    }


//    @RequestMapping(value = "/fragment/patient/update/{uid}", method = RequestMethod.GET)
//    public String updateFrom(@PathVariable String uid, Model model) {
//        Patient p = patientService.getPatientByUid(uid);
//        Doctor d = SecuritySupport.getDoctor();
//        if (p.getProvinceNo() == null && d != null) {
//            p.setProvinceNo(d.getProvinceNo());
//            p.setProvince(d.getProvince());
//            p.setCityNo(d.getCityNo());
//            p.setCity(d.getCity());
//            p.setAreaNo(d.getAreaNo());
//            p.setArea(d.getArea());
//        }
//        model.addAttribute("patient", p);
//        model.addAttribute("provinceList", nationService.listNation(0));
//        model.addAttribute("cityList", nationService.listNation(p.getProvinceNo()));
//        model.addAttribute("areaList", nationService.listNation(p.getCityNo()));
//        return "fragment/patientForm";
//    }

    @RequestMapping(value = "/patient/save", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("patient") Patient patient, RedirectAttributes redirectAttributes) {
        if (patient.getStatus() == StatusEntity.Status.Unactivated) {
            try {
                SMSUtil.sendSMS(SMSUtil.ACTIVE_TEMPLATE, patient.getMobile(), SecuritySupport.getDoctor()
                        .getOutpatientService() + "," + patient.getUid());
            } catch (Exception e) {
                logger.error("激活短信发送失败：" + e.getMessage());
            }
        }
        patientService.savePatient(patient);
        redirectAttributes.addFlashAttribute("msg", "信息成功保存");
        return "redirect:/fragment/patient/update/" + patient.getUid();
    }

    private List<Date> getMonths() {
        List<Date> months = Lists.newArrayList();
        LocalDate curr = LocalDate.now().withDayOfMonth(1);
        for (int i = 11; i >= 0; i--) {
            months.add(curr.plusMonths(-i).toDate());
        }
        return Collections.unmodifiableList(months);
    }


    @ModelAttribute
    public void getPatient(@RequestParam(value = "uid", defaultValue = "") String uid, Model model) {
        if (StringUtils.isNotEmpty(uid)) {
            model.addAttribute("patient", patientService.getPatientByUid(uid));
        }
    }


}
