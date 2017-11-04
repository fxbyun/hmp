package com.qiaobei.hmp.web;

import com.google.common.collect.Lists;
import com.qiaobei.hmp.modules.entity.*;
import com.qiaobei.hmp.security.SecuritySupport;
import com.qiaobei.hmp.service.*;
import com.qiaobei.hmp.support.Constants;
import com.qiaobei.hmp.support.DateFilter;
import com.qiaobei.hmp.support.Result;
import com.qiaobei.hmp.support.SMSUtil;
import org.joda.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Collections;
import java.util.Date;
import java.util.EnumMap;
import java.util.List;

/**
 * Created by IntelliJ IDEA 15.0
 * User ZW_Teemoer
 * Date 2016/7/28 0028
 * Time 10:56
 */
@Controller
public class OldPatientController extends ConstantsController {
    @Resource
    private OldPatientBingliService oldPatientBingliService;
    @Resource
    private OldPatientService oldPatientService;
    @Resource
    private PatientService patientService;
    @Resource
    private NationService nationService;
    @Resource
    private EmrService emrService;

    @ModelAttribute("genderMap")
    private EnumMap<Gender, String> genderMap(HttpServletRequest request) {
        return GENDER_MAP;
    }


    @RequestMapping(value = "/oldPatient/oldPtDetail/{uid}/emrList", method = {RequestMethod.GET, RequestMethod.POST})
    public String emrList(@PathVariable String uid,
                          @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                          @RequestParam(value = "size", required = false, defaultValue = "3") int size,
                          @RequestParam(value = "refer", required = false, defaultValue = "false") Boolean refer,
                          @RequestParam(required = false) @DateTimeFormat(pattern = Constants.PATTERN_MONTH) Date month,
                          Model model) {
        model.addAttribute("refer", refer);
        model.addAttribute("month", month);
        model.addAttribute("months", getMonths());
        model.addAttribute("medicineUnits", MEDICINE_UNITS);
        DateFilter dateFilter;
        if (month != null) {
            dateFilter = DateFilter.withPlusMonths(month, 1);
        } else {
            dateFilter = null;
        }
        Patient patient = patientService.getPatientByUid(uid);
        Page<OldPatientBingli> patientBingliPage = oldPatientBingliService.getPageByUid(new PageRequest(page, size,
                Sort.Direction.DESC, "id"), patient, dateFilter);

        model.addAttribute("emrPage",
                patientBingliPage);
        return "/oldPatient/sunPage/oldEmrList";
    }

    private List<Date> getMonths() {
        List<Date> months = Lists.newArrayList();
        LocalDate curr = LocalDate.now().withDayOfMonth(1);
        for (int i = 11; i >= 0; i--) {
            months.add(curr.plusMonths(-i).toDate());
        }
        return Collections.unmodifiableList(months);
    }

    @RequestMapping(value = "/oldPatient/binDingCardPatient/save", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("patient") Patient patient, RedirectAttributes redirectAttributes,
                       @RequestParam("oldPtientId") Long oldPtientId,
                       @RequestParam(value = "type", required = false, defaultValue = "old") String type
    ) {
        if (patient.getStatus() == StatusEntity.Status.Unactivated && !"否".equals(SecuritySupport.getDoctor().getAutoSendActivateMsg())) {
            try {
                SMSUtil.sendSMS(SMSUtil.ACTIVE_TEMPLATE, patient.getMobile(), SecuritySupport.getDoctor()
                        .getOutpatientService() + "," + patient.getUid());
            } catch (Exception e) {
                logger.error("激活短信发送失败：" + e.getMessage());
            }
        }


        Patient patientOld = patientService.getPatientByUid(patient.getUid());
        if ("tmp".equals(type)) {
            emrService.updatePatien(patient.getId(), patientOld.getId());
        }

        patient.setPassword(patientOld.getPassword());
        patient.setSalt(patientOld.getSalt());
        patient.setUdid(patientOld.getUdid());
        patient.setId(patientOld.getId());
        patientService.savePatient(patient);
        if (!"tmp".equals(type)) {
            OldPatient oldPatient = oldPatientService.getById(oldPtientId);
            oldPatient.setPatient(patient);
            oldPatient.setStatus(OldPatient.Status.ACTIVATION);
            oldPatientService.save(oldPatient);
        }


        redirectAttributes.addFlashAttribute("msg", "信息成功保存");
        return "redirect:/fragment/patient/update/" + patient.getUid();
    }


    @RequestMapping("/oldPatient/isThisCardPatientActivation")
    @ResponseBody
    public Result isThisCardPatientActivationByCardNo(@RequestParam("uid") String uid) {
        Patient p = patientService.getPatientByUdid(uid);
        if (p != null && p.getStatus() != Patient.Status.Unactivated) {
            return Result.ok("该卡已经激活");
        }
        return Result.fail("该卡未激活");
    }

    @RequestMapping(value = "/oldPatient/oldPtDetail/binDingCardPatient/{uid}", method = RequestMethod.GET)
    public String binDingCardDate(@PathVariable String uid, Model model,
                                  @RequestParam("oldPatientId") Long oldPatientId,
                                  @RequestParam(value = "type", defaultValue = "old", required = false) String type
    ) {
        Patient p = patientService.getPatientByUid(uid);
        if ("tmp".equals(type)) {
            p = patientService.findOne(oldPatientId);
            p.setUid(uid);
            model.addAttribute("oldPtient", p);
            model.addAttribute("type", type);
        } else {
            OldPatient oldPatient = oldPatientService.getById(oldPatientId);
            p.setBirthday(oldPatient.getBirthday());
            p.setAddress(oldPatient.getAddress());
            p.setGender(oldPatient.getGender());
            p.setName(oldPatient.getName());
            model.addAttribute("oldPtient", oldPatient);
        }


        Doctor d = SecuritySupport.getDoctor();
        if (p.getProvinceNo() == null && d != null) {
            p.setProvinceNo(d.getProvinceNo());
            p.setProvince(d.getProvince());
            p.setCityNo(d.getCityNo());
            p.setCity(d.getCity());
            p.setAreaNo(d.getAreaNo());
            p.setArea(d.getArea());
        }
        model.addAttribute("patient", p);

        model.addAttribute("provinceList", nationService.listNation(0));
        model.addAttribute("cityList", nationService.listNation(p.getProvinceNo()));
        model.addAttribute("areaList", nationService.listNation(p.getCityNo()));
        return "/oldPatient/sunPage/oldPatientForm";
    }

    @RequestMapping(value = "/oldPatient/oldPtDetail/binDingCard", method = RequestMethod.GET)
    public String binDingCard(Model model, @RequestParam("oldPatientId") Long oldPatientId) {
        model.addAttribute("oldPatientId", oldPatientId);
        return "/oldPatient/sunPage/binDingCard";
    }

    @RequestMapping(value = "/oldPatient/isThisPatienHaveBinDingCard", method = RequestMethod.POST)
    @ResponseBody
    public Result isThisPatienHaveBinDingCard(@RequestParam("patientId") Long oldPatientId) {
        OldPatient oldPatient = oldPatientService.getById(oldPatientId);
        if (oldPatient != null && oldPatient.getStatus() == OldPatient.Status.ACTIVATION) {
            return Result.ok("已绑卡");
        }
        return Result.fail("未绑卡");
    }


    @RequestMapping(value = "/oldPatient/oldPtDetail/emrInfo", method = RequestMethod.GET)
    public String ptDetailLoadEmrInfo(@RequestParam("id") Long id, Model model) {

        OldPatientBingli oldPatientBingli = oldPatientBingliService.getById(id);

        oldPatientBingli.setChuFang(oldPatientBingli.getChuFang().replace("\r\n", "<br />"));
        model.addAttribute("emr", oldPatientBingli);

        return "/oldPatient/sunPage/oldEmrInfo";
    }

    @RequestMapping(value = "/oldPatient/oldPtDetail")
    public String toOldPtDetaile(@RequestParam("emrId") Long emrId, Model
            model) {

        OldPatientBingli oldPatientBingli = oldPatientBingliService.getById(emrId);

        List<OldPatientBingli> oldPatientBingliList = oldPatientBingli.getOldPatient().getOldPatientBinglis();
        model.addAttribute("doctor", SecuritySupport.getDoctor());
        model.addAttribute("emr", oldPatientBingliList);
        model.addAttribute("patient", oldPatientBingli.getOldPatient());
        model.addAttribute("clickEmrId", emrId);
        return null;
    }

    @RequestMapping(value = "/oldPatient/oldPatientManageEmrList")
    public String showThisPatienOldEmr(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                       @RequestParam(value = "size", required = false, defaultValue = "10") int size,
                                       @RequestParam(value = "patient", required = false) String patientName,
                                       @RequestParam(value = "patientId", required = false) Long patientId,
                                       @RequestParam(required = false) @DateTimeFormat(pattern = Constants
                                               .PATTERN_DATE) Date startDate,
                                       @RequestParam(required = false) @DateTimeFormat(pattern = Constants
                                               .PATTERN_DATE) Date endDate, Model model) {

        DateFilter dateFilter = null;
        if (null != startDate && null != endDate) {
            dateFilter = new DateFilter(startDate, endDate);
        }

        Pageable pageable = new PageRequest(page, size, Sort.Direction.DESC, "id");
        Page<OldPatientBingli> emrs = oldPatientBingliService.pageEmrForManagerToList(pageable,
                patientName,
                dateFilter, patientId);

        model.addAttribute("emrPage", emrs);
        model.addAttribute("patientName", patientName);
        model.addAttribute("patientId", patientId);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);

        return "/oldPatient/oldPatientManageEmrList";
    }

    @RequestMapping(value = "/oldPatient/getAllByDoctor")
    public String getAllOldPatientByDoctor(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                           @RequestParam(value = "patient", required = false) String patientName,
                                           @RequestParam(value = "ageTopsString", required = false) String
                                                   ageTopsString,
                                           @RequestParam(value = "ageFlooerString", required = false) String
                                                   ageFlooerString,
                                           @RequestParam(value = "diagonsisName", required = false) String
                                                   diagonsisName,
                                           @RequestParam(value = "genderSex", required = false) Gender genderSex,
                                           @RequestParam(required = false) @DateTimeFormat(pattern = Constants
                                                   .PATTERN_DATE) Date startDate,
                                           @RequestParam(required = false) @DateTimeFormat(pattern = Constants
                                                   .PATTERN_DATE) Date endDate,
                                           @RequestParam(required = false) @DateTimeFormat(pattern = Constants
                                                   .PATTERN_DATE) Date ageTops,
                                           @RequestParam(required = false) @DateTimeFormat(pattern = Constants
                                                   .PATTERN_DATE) Date ageFlooer,
                                           Model model) {
        DateFilter dateFilter = null;
        DateFilter dateAge = null;
        if (null != startDate && null != endDate) {
            dateFilter = new DateFilter(startDate, endDate);
        }
        if (null != ageTops && null != ageFlooer) {
            dateAge = new DateFilter(ageFlooer, ageTops);
        }

        if (",".equals(patientName)) {
            patientName = null;
        }
        Pageable pageable = new PageRequest(page, 10, Sort.Direction.DESC, "createTime");

        //Page<OldPatient> oldPatientPage = oldPatientService.getAllOldPatientByDoctor(SecuritySupport.getDoctor(),
        // pageable);
        //Page<OldPatient> oldPatientPage = oldPatientService.getAllOldPatientByDoctor(new Doctor(107L), pageable);

        Page<OldPatient> patientListEntities = oldPatientService.findCountByPatient(pageable,
                SecuritySupport.getDoctor(), patientName, dateFilter, dateAge
                , genderSex, diagonsisName);


        model.addAttribute("patientPage", patientListEntities);
        model.addAttribute("patientName", patientName);
        model.addAttribute("diagonsisName", diagonsisName);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("ageTopsString", ageTopsString);
        model.addAttribute("ageFlooerString", ageFlooerString);
        model.addAttribute("genderSex", genderSex);

        return "/oldPatient/oldPatientManage";
    }


}
