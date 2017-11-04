package com.qiaobei.hmp.web;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.qiaobei.hmp.modules.entity.*;
import com.qiaobei.hmp.modules.service.EmrJClassAdviceDictService;
import com.qiaobei.hmp.modules.support.FileUtilsServer;
import com.qiaobei.hmp.security.SecuritySupport;
import com.qiaobei.hmp.service.*;
import com.qiaobei.hmp.support.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springside.modules.utils.Collections3;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class PatientController extends ConstantsController {

    private static Logger logger = LoggerFactory.getLogger(PatientController.class);
    @Resource
    private PatientService patientService;
    @Resource
    private EmrService emrService;
    @Resource
    private CardService cardService;
    @Resource
    private NationService nationService;
    @Resource
    private EvaluateService evaluateService;
    @Resource
    private RegistrationService registrationService;
    @Resource
    private EmrJClassAdviceDictService emrJClassAdviceDictService;
    @Resource
    private DoctorService doctorService;
    @PersistenceContext
    private EntityManager entityManager;


    @ModelAttribute("vitalSignLabels")
    private EnumMap<VitalSign.Type, String> vitalSignLabelMap(HttpServletRequest request) {
        return VITAL_SIGN_LABELS;
    }

    @ModelAttribute("vitalSignUnits")
    private EnumMap<VitalSign.Type, String> vitalSignUnitMap(HttpServletRequest request) {
        return VITAL_SIGN_UNITS;
    }

    @ModelAttribute("medicineTypes")
    private EnumMap<Medicine.Type, String> medicineTypes(HttpServletRequest request) {
        return MEDICINE_TYPES;
    }

    @ModelAttribute("groupArrys")
    private Map<String, String> groupArrys(HttpServletRequest request) {
        return GROUP_ARRYS;
    }

    @ModelAttribute("xwArrys")
    private List<String> xwArrys(HttpServletRequest request) {
        return XW_ARRYS;
    }

    @ModelAttribute("medicineUnits")
    private EnumMap<Medicine.Unit, String> medicineUnits(HttpServletRequest request) {
        return MEDICINE_UNITS;
    }

    @ModelAttribute("genderMap")
    private EnumMap<Gender, String> genderMap(HttpServletRequest request) {
        return GENDER_MAP;
    }

    @ModelAttribute("subDoctorList")
    private List<Doctor> subDoctorList() {
        return doctorService.findSubDoctor(SecuritySupport.getDoctor());
    }


    @RequestMapping(value = "/patient/query", method = RequestMethod.GET)
    @ResponseBody
    public List<Patient> query(@RequestParam String key) {
        key = key.replace(" ", "");
        //如果输入的是手机号不满11位数就不查询数据
        if (StringUtils.isNumeric(key) && key.length() < 11) {
            return null;
        }

        List<Patient> list;
        if (StringUtils.isNumeric(key)) {
            list = patientService.queryByMobileNoTmp(key);
        } else {
            QEmr qEmr = QEmr.emr;
            JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
            list = jpaQueryFactory
                    .from(qEmr)
                    .select(qEmr.patient)
                    .join(qEmr.patient)
                    .on(
                            qEmr.patient.name.like(key + "%")
                                    .or(
                                            qEmr.patient.helpCode.like(key + "%")
                                    )
                    ).where(
                            qEmr.doctor.eq(
                                    SecuritySupport.getDoctor()
                            )
                    ).distinct().fetch().stream().filter(
                            patient -> patient.getStatus() != StatusEntity.Status.Tmp).collect(Collectors.toList()
                    );
            list.forEach(patient -> patient.setStatus(StatusEntity.Status.DoctorHaveSee));
        }
        return list;
    }


    @RequestMapping(value = "/patient/login4Udid", method = RequestMethod.POST)
    @ResponseBody
    public PatientLoginResult loginByUdid(
            @RequestParam String udid,
            @RequestParam(value = "wxPatienId", required = false) Long wxPatienId) {
        Patient patient = patientService.getPatientByUdid(udid);
        if (patient != null) {
            List<Emr> emrList = getEmrList4Today(patient.getUid());
            if (Collections3.isNotEmpty(emrList)) {
                PatientLoginResult result = PatientLoginResult.ok(PatientLoginResult.Status.Used);
                result.setData(Collections3.getFirst(emrList).getId());
                return result;
            } else if (patient.getStatus() == StatusEntity.Status.Unactivated) {
                PatientLoginResult result = PatientLoginResult.ok(PatientLoginResult.Status.NewCard);
                result.setData(patient.getUid());
                return result;
            }
            PatientLoginResult result = PatientLoginResult.ok(PatientLoginResult.Status.Passed);
            result.setData(patient.getUid());
            return result;
        } else {
            Card card = cardService.getCardByUdid(udid);
            if (card != null) {
                if (wxPatienId != null) {
                    patient = patientService.saveWxPatientBindingCard(card, patientService.getPatientById(wxPatienId));
                    if (patient != null) {
                        PatientLoginResult result = PatientLoginResult.ok(PatientLoginResult.Status.NewCard);
                        result.setData(patient.getUid());
                        registrationService.updatePatienHaveBinDingcard(patient);
                        return result;
                    }
                } else {
                    patient = patientService.savePatient4Card(card);
                    if (patient != null) {
                        PatientLoginResult result = PatientLoginResult.ok(PatientLoginResult.Status.NewCard);
                        result.setData(patient.getUid());
                        return result;
                    }
                }

            }
        }
        return PatientLoginResult.ok(PatientLoginResult.Status.Invalid);
    }

    @RequestMapping(value = "/fragment/patient/{uid}", method = RequestMethod.GET)
    public String getByUid(@PathVariable String uid, Model model) {
        Patient patient = patientService.getPatientByUid(uid);
        if (patient == null) {
            if (uid.indexOf("8") == 0) {
                patient = patientService.getPatientByUid("00" + uid);
            }
        }
        if (patient == null) {
            model.addAttribute("patient", patientService.getPatientById(Long.valueOf(uid)));
        } else {
            model.addAttribute("patient", patient);
        }

        return "fragment/patientView";
    }

    private List<Emr> getEmrList4Today(String patientUid) {
        LocalDate now = LocalDate.now();
        Date start = now.toDate();
        Date end = now.plusDays(1).toDate();
        return emrService.listByPatientUidBetween(SecuritySupport.getDoctor(), patientUid, start, end);
    }

    @RequestMapping(value = "/patient/login4Uid", method = RequestMethod.POST)
    @ResponseBody
    public PatientLoginResult loginByUidAndPwd(@RequestParam String uid, @RequestParam String pwd) {
        Patient patient = patientService.getPatientByUid(uid);

        if (patient != null) {
            if (StringUtils.equals(Utils.encodePwd(pwd, patient.getSalt()), patient.getPassword())) {
                List<Emr> emrList = getEmrList4Today(uid);
                if (Collections3.isNotEmpty(emrList)) {
                    PatientLoginResult result = PatientLoginResult.ok(PatientLoginResult.Status.Used);
                    result.setData(Collections3.getFirst(emrList).getId());
                    return result;
                }
                PatientLoginResult result = PatientLoginResult.ok(PatientLoginResult.Status.Passed);
                result.setData(patient.getUid());
                return result;
            }
        } else {
            Card card = cardService.getCardByNo(uid);
            if (card != null) {
                patient = patientService.savePatient4Card(card);
                if (patient != null) {
                    PatientLoginResult result = PatientLoginResult.ok(PatientLoginResult.Status.NewCard);
                    result.setData(patient.getUid());
                    return result;
                }
            }
        }
        return PatientLoginResult.ok(PatientLoginResult.Status.Invalid);
    }

//    @RequestMapping(value = "/fragment/patient/uid", method = RequestMethod.POST)
//    public String getByUidAndPwd(@RequestParam String uid, @RequestParam String pwd, Model model) {
//        Patient patient = patientService.getPatientByUid(uid);
//        boolean valid = false;
//        if (patient != null) {
//            valid = StringUtils.equals(Utils.encodePwd(pwd, patient.getSalt()), patient.getPassword());
//        } else {
//            Card card = cardService.getCardByNo(uid);
//            if (card != null) {
//                patient = patientService.savePatient4Card(card);
//                valid = (patient != null);
//            }
//        }
//        if (valid) {
//            System.out.println("valid");
//            model.addAttribute("patient", patient);
//        } else {
//            model.addAttribute("patient", null);
//            model.addAttribute("error", "无效的卡号或密码");
//        }
//        return "fragment/patientView";
//    }

    /**
     * 为临时患者绑卡
     */
    @RequestMapping(value = "/patient/tmpPatientBinDingCard")
    @ResponseBody
    public PatientLoginResult binDingTmpPatient(@RequestParam("udid") String udid, @RequestParam("patientId") Long patientId) {
        Patient patient = patientService.getPatientByUdid(udid);
        if (patient != null) {
            if (patient.getStatus().equals(StatusEntity.Status.Unactivated)) {
                System.out.println(1);
                patientService.deletePatien(patient);
            } else {
                return PatientLoginResult.ok(PatientLoginResult.Status.Used);
            }
        }
        Card card = cardService.getCardByUdid(udid);
        if (card != null && card.getStatus().equals(StatusEntity.Status.Used)) {
            return PatientLoginResult.ok(PatientLoginResult.Status.Used);
        }
        if (card != null && card.getStatus().equals(StatusEntity.Status.Normal)) {
            patient = patientService.getPatientById(patientId);
            if (patient != null) {
                patient.setUdid(card.getUdid());
                patient.setUid(card.getCardNo());
                patient.setStatus(StatusEntity.Status.Normal);
                patient.setPlainPassword(StringUtils.right(card.getCardNo(), 6));
                Utils.entryptUserPassword(patient);
                patientService.saveAndFlushPatient(patient);
                card.setStatus(StatusEntity.Status.Used);
                cardService.saveCard(card);
                emrService.updateTmpEmrToCommonByPaitent(patient);
                PatientLoginResult result = PatientLoginResult.ok(PatientLoginResult.Status.NewCard);
                result.setData(patient.getUid());
                return result;
            }
        }
        return PatientLoginResult.ok(PatientLoginResult.Status.Invalid);
    }

    @RequestMapping(value = "/patient/tmpPatientBinDingCardPage")
    public String tmpBinDingCardPage(@RequestParam("id") Long id, Model model) {
        model.addAttribute("id", id);
        return "newPage/sunPage/binDingCard";
    }


    @RequestMapping(value = "/fragment/patient/update/{uid}", method = RequestMethod.GET)
    public String updateFrom(@PathVariable String uid, Model model, @RequestParam(value = "id", required = false, defaultValue = "0") Long id) {
        Patient p = patientService.getPatientByUid(uid);
        if (p == null && id != 0) {
            p = patientService.getPatientById(id);
        }
        Doctor d = SecuritySupport.getDoctor();
        if (p != null) {
            if (p.getProvinceNo() == null && d != null) {
                p.setProvinceNo(d.getProvinceNo());
                p.setProvince(d.getProvince());
                p.setCityNo(d.getCityNo());
                p.setCity(d.getCity());
                p.setAreaNo(d.getAreaNo());
                p.setArea(d.getArea());
            }
        }

        model.addAttribute("patient", p);
        model.addAttribute("provinceList", nationService.listNation(0));
        if (p != null && p.getProvinceNo() != null)
            model.addAttribute("cityList", nationService.listNation(p.getProvinceNo()));
        if (p != null && p.getCityNo() != null)
            model.addAttribute("areaList", nationService.listNation(p.getCityNo()));
        return "fragment/patientForm";
    }

    /**
     * 修改临时 患者
     */
    @RequestMapping(value = "/fragment/patient/update", method = RequestMethod.GET)
    public String updateTmpFrom(Model model, @RequestParam(value = "id") Long id) {
        Patient p = patientService.getPatientById(id);
        System.out.println(p);
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
        if (p.getStatus() == StatusEntity.Status.Tmp) {
            model.addAttribute("types", "tmp");
        }
        model.addAttribute("provinceList", nationService.listNation(0));
        model.addAttribute("cityList", nationService.listNation(p.getProvinceNo()));
        model.addAttribute("areaList", nationService.listNation(p.getCityNo()));
        return "/fragment/patientForm";
    }

    // TODO 临时处方->患者信息页面 2016-8-30 10:16:05
    @RequestMapping(value = "/patient/saveTemprescription", method = RequestMethod.GET)
    public String saveTemp(Model model, @ModelAttribute("patient") Patient patient) {
        Patient patient2 = new Patient();
        model.addAttribute("patient", patient2);
        model.addAttribute("types", "tmp");
        model.addAttribute("provinceList", nationService.listNation(0));
        model.addAttribute("cityList", nationService.listNation(SecuritySupport.getDoctor().getProvinceNo()));
        model.addAttribute("areaList", nationService.listNation(SecuritySupport.getDoctor().getCityNo()));
        return "fragment/patientForm";
    }

    @RequestMapping(value = "/patient/save", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("patient") Patient patient, RedirectAttributes redirectAttributes) {
        if (patient.getStatus() == StatusEntity.Status.Unactivated && !"否".equals(SecuritySupport.getDoctor().getAutoSendActivateMsg())) {
            try {
                SMSUtil.sendSMS(SMSUtil.ACTIVE_TEMPLATE, patient.getMobile(), SecuritySupport.getDoctor()
                        .getOutpatientService() + "," + patient.getUid());
            } catch (Exception e) {
                logger.error("激活短信发送失败：" + e.getMessage());
            }
        }
        if (patient.getStatus() == StatusEntity.Status.Tmp) {
            if (patient.getId() != null) {
                Patient patientTmp = patientService.getPatientById(patient.getId());
                if (patientTmp.getUid() == null) {
                    patient.setUid(String.valueOf(System.currentTimeMillis()));
                }
                if (patientTmp.getUdid() == null) {
                    patient.setUdid(String.valueOf(System.currentTimeMillis()));
                }
            } else {
                patient.setUid(String.valueOf(System.currentTimeMillis()));
                patient.setUdid(String.valueOf(System.currentTimeMillis()));
            }


        }
        patientService.savePatient(patient);
        redirectAttributes.addFlashAttribute("msg", "信息成功保存");
        if (patient.getStatus() == StatusEntity.Status.Tmp) {
            return "redirect:/fragment/patient/update?id=" + patient.getId();
        }
        return "redirect:/fragment/patient/update/" + patient.getUid() + "?id=" + patient.getId();
    }

    private List<Date> getMonths() {
        List<Date> months = Lists.newArrayList();
        LocalDate curr = LocalDate.now().withDayOfMonth(1);
        for (int i = 11; i >= 0; i--) {
            months.add(curr.plusMonths(-i).toDate());
        }
        return Collections.unmodifiableList(months);
    }

    @RequestMapping(value = "/fragment/patient/{uid}/overview", method = RequestMethod.GET)
    public String overview(@PathVariable String uid,
                           @RequestParam(value = "refer", required = false, defaultValue = "false") Boolean refer,
                           @RequestParam(value = "wxPatienId", required = false) Long wxPatienId,
                           Model model) {
        Patient patient = patientService.getPatientByUid(uid);
        if (patient == null) {
            try {
                model.addAttribute("patient", patientService.getPatientById(Long.valueOf(uid)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            if (wxPatienId != null) {
                Doctor d = SecuritySupport.getDoctor();
                if (patient.getProvinceNo() == null && d != null) {
                    patient.setProvinceNo(d.getProvinceNo());
                    patient.setProvince(d.getProvince());
                    patient.setCityNo(d.getCityNo());
                    patient.setCity(d.getCity());
                    patient.setAreaNo(d.getAreaNo());
                    patient.setArea(d.getArea());
                }
            }
            model.addAttribute("patient", patient);
        }

        model.addAttribute("refer", refer);
        return "fragment/patientOverview";
    }


    @RequestMapping(value = "/fragment/patient/{uid}/emrList", method = RequestMethod.GET)
    public String emrList(@PathVariable String uid,
                          @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                          @RequestParam(value = "size", required = false, defaultValue = "3") int size,
                          @RequestParam(value = "refer", required = false, defaultValue = "false") Boolean refer,
                          Model model) {
//        Date month = LocalDate.now().withDayOfMonth(1).toDate();

        model.addAttribute("refer", refer);
//        model.addAttribute("month", month);
        model.addAttribute("months", getMonths());
        model.addAttribute("medicineUnits", MEDICINE_UNITS);
        /*model.addAttribute("emrPage",
                emrService.pageEmrByPatient(new PageRequest(page, size, Sort.Direction.DESC, "id"), uid, null));*/

        Page<Emr> emrPage = emrService.pageEmrByPatient(new PageRequest(page, size, Sort.Direction.DESC, "id"), uid, null);

        model.addAttribute("emrPage", emrPage);
        //从这里查看中医理疗的内容
        model.addAttribute("therapyUnits", THERAPY_UNITS);
        List<Emr> emrList = emrPage.getContent();
        //中医理疗的
        Map<Emr, List<EmrMedicine>> TherapyMap = Maps.newHashMap();
        //检查检验
        Map<Emr, List<EmrJClassAdviceDict>> adviceDictMap = Maps.newHashMap();
        //附件费用
        Map<Emr, List<EmrExtCost>> extCostMap = Maps.newHashMap();
        //医生Map
        Map<Emr, Doctor> doctorMap = Maps.newHashMap();
        FileUtilsServer fileUtil = new FileUtilsServer();
        //开始取出各种数据
        emrList.forEach(emr -> {
            //历史病例--中医理疗
            List<EmrMedicine> therapyList = Lists.newArrayList();
            emr.getEmrMedicineList().stream().filter(emrMedicine -> emrMedicine.getMedicineType() == Medicine.Type.ChineseTherapy).forEach(therapyList::add);
            TherapyMap.put(emr, therapyList);

            //先找到这个病历的医生
            List<EmrJClassAdviceDict> adviceDictList = emr.getEmrJClassAdviceDicts();
            //附件输出
            adviceDictList.forEach(advice -> fileUtil.outPutExamLabFileToDisk(advice.getExamLabFileList(), emr.getDoctor()));
            adviceDictMap.put(emr, adviceDictList);
            doctorMap.put(emr, emr.getDoctor());

            //附加费用
            extCostMap.put(emr, emr.getEmrExtCostList());
        });
        model.addAttribute("patient", patientService.findByUid(uid));
        model.addAttribute("therapyMap", TherapyMap);
        model.addAttribute("adviceDictMap", adviceDictMap);
        model.addAttribute("extCostMap", extCostMap);
        model.addAttribute("doctorMap", doctorMap);

        model.addAttribute("loginDoctor", SecuritySupport.getDoctor());

        return "fragment/emrList";
    }

    @RequestMapping(value = "/fragment/patient/{uid}/emrList", method = RequestMethod.POST)
    public String emrList(@PathVariable String uid,
                          @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                          @RequestParam(value = "size", required = false, defaultValue = "3") int size,
                          @RequestParam(value = "refer", required = false, defaultValue = "false") Boolean refer,
                          @RequestParam(required = false) @DateTimeFormat(pattern = Constants.PATTERN_MONTH) Date month,
                          Model model) {
//        if (month == null) {
//            month = LocalDate.now().withDayOfMonth(1).toDate();
//        }
        model.addAttribute("refer", refer);
        model.addAttribute("months", getMonths());
        model.addAttribute("month", month);
        model.addAttribute("medicineUnits", MEDICINE_UNITS);

        Page<Emr> emrPage = emrService.pageEmrByPatient(new PageRequest(page, size, Sort.Direction.DESC, "id"), uid, DateFilter
                .withPlusMonths(month, 1));

        model.addAttribute("emrPage", emrPage);
        //从这里查看中医理疗的内容
        model.addAttribute("therapyUnits", THERAPY_UNITS);
        List<Emr> emrList = emrPage.getContent();
        //中医理疗的
        Map<Emr, List<EmrMedicine>> TherapyMap = Maps.newHashMap();
        //检查检验
        Map<Emr, List<EmrJClassAdviceDict>> adviceDictMap = Maps.newHashMap();
        //附件费用
        Map<Emr, List<EmrExtCost>> extCostMap = Maps.newHashMap();
        //医生Map
        Map<Emr, Doctor> doctorMap = Maps.newHashMap();
        FileUtilsServer fileUtil = new FileUtilsServer();
        //开始取出各种数据
        emrList.forEach(emr -> {
            //历史病例--中医理疗
            List<EmrMedicine> therapyList = Lists.newArrayList();
            emr.getEmrMedicineList().stream().filter(emrMedicine -> emrMedicine.getMedicineType() == Medicine.Type.ChineseTherapy).forEach(therapyList::add);
            TherapyMap.put(emr, therapyList);

            //先找到这个病历的医生
            List<EmrJClassAdviceDict> adviceDictList = emr.getEmrJClassAdviceDicts();
            //附件输出
            adviceDictList.forEach(advice -> fileUtil.outPutExamLabFileToDisk(advice.getExamLabFileList(), emr.getDoctor()));
            adviceDictMap.put(emr, adviceDictList);
            doctorMap.put(emr, emr.getDoctor());

            //附加费用
            extCostMap.put(emr, emr.getEmrExtCostList());
        });

        model.addAttribute("patient", patientService.findByUid(uid));
        model.addAttribute("therapyMap", TherapyMap);
        model.addAttribute("adviceDictMap", adviceDictMap);
        model.addAttribute("extCostMap", extCostMap);
        model.addAttribute("doctorMap", doctorMap);

        model.addAttribute("loginDoctor", SecuritySupport.getDoctor());

        return "fragment/emrList";
    }

    @ModelAttribute
    public void getPatient(@RequestParam(value = "uid", defaultValue = "") String uid, Model model) {
        if (StringUtils.isNotEmpty(uid)) {
            model.addAttribute("patient", patientService.getPatientByUid(uid));
        }
    }


    @RequestMapping(value = "/patientManage", method = {RequestMethod.POST, RequestMethod.GET})
    public String patientManage(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                @RequestParam(value = "size", required = false, defaultValue = "10") int size,
                                @RequestParam(value = "patient", required = false) String patientName,
                                @RequestParam(value = "subDoctorId", required = false) Long subDoctorId,
                                @RequestParam(value = "ageTopsString", required = false) String ageTopsString,
                                @RequestParam(value = "ageFlooerString", required = false) String ageFlooerString,
                                @RequestParam(value = "diagonsisName", required = false) String diagonsisName,
                                @RequestParam(value = "genderSex", required = false) Gender genderSex,
                                @RequestParam(required = false) @DateTimeFormat(pattern = Constants.PATTERN_DATE)
                                        Date startDate,
                                @RequestParam(required = false) @DateTimeFormat(pattern = Constants.PATTERN_DATE)
                                        Date endDate,
                                @RequestParam(required = false) @DateTimeFormat(pattern = Constants.PATTERN_DATE)
                                        Date ageTops,
                                @RequestParam(required = false) @DateTimeFormat(pattern = Constants.PATTERN_DATE)
                                        Date ageFlooer,
                                Model model) {
        if (page < 0) {
            page = 0;
        }
        model.addAttribute("subDoctorId", subDoctorId);
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

        Page<Emr> patientListEntities = emrService.findCountByPatient(new PageRequest(page, size),
                SecuritySupport.getDoctor(), patientName, dateFilter, dateAge
                , genderSex, diagonsisName, subDoctorId);

        patientListEntities.getContent().forEach(
                emr -> emr.setDoctor(emrService.getEmr(emr.getId()).getDoctor())
        );

        model.addAttribute("patientPage", patientListEntities);
        model.addAttribute("patientName", patientName);
        model.addAttribute("diagonsisName", diagonsisName);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("ageTopsString", ageTopsString);
        model.addAttribute("ageFlooerString", ageFlooerString);
        model.addAttribute("genderSex", genderSex);
        model.addAttribute("doctor", SecuritySupport.getDoctor());
        /*JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);

        QEmr qEmr = QEmr.emr;
        List<String> emrName = jpaQueryFactory.selectFrom(qEmr).select(qEmr.patientName).fetch();*/
        return "/newPage/patientManage";
    }

    @RequestMapping(value = "/patientManageEmrList", method = {RequestMethod.GET, RequestMethod.POST})
    public String patientManageEmrList(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                       @RequestParam(value = "size", required = false, defaultValue = "10") int size,
                                       @RequestParam(value = "patient", required = false) String patientName,
                                       @RequestParam(value = "patientId", required = false) Long patientId,
                                       @RequestParam(value = "subDoctorId", required = false) Long subDoctorId,
                                       @RequestParam(required = false) @DateTimeFormat(pattern = Constants
                                               .PATTERN_DATE) Date startDate,
                                       @RequestParam(required = false) @DateTimeFormat(pattern = Constants
                                               .PATTERN_DATE) Date endDate, Model model) {

        DateFilter dateFilter = null;
        if (null != startDate && null != endDate) {
            dateFilter = new DateFilter(startDate, endDate);
        }
        model.addAttribute("subDoctorId", subDoctorId);
        //当下拉框为全部的时候
        if (subDoctorId != null) {
            if (subDoctorId == 0 || "0".equals(subDoctorId.toString())) {
                subDoctorId = null;
            }
        }

        Page<Emr> emrs = emrService.pageEmrForManagerToList(new PageRequest(page, size, Sort.Direction.DESC, "id"),
                SecuritySupport.getDoctorId(),
                patientName,
                dateFilter, patientId, subDoctorId);
        emrs.getContent().forEach(
                emr -> emr.setDoctor(emrService.getEmr(emr.getId()).getDoctor())
        );
        model.addAttribute("emrPage", emrs);
        model.addAttribute("patientName", patientName);
        model.addAttribute("patientId", patientId);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("doctor", SecuritySupport.getDoctor());
        return "newPage/patientManageEmrList";
    }


    @RequestMapping(value = "/ptConsultation", method = RequestMethod.GET)
    public String ptConsultation(Model model,
                                 @RequestParam(value = "subDoctorId", required = false) Long subDoctorId) {
        Pageable pageabl2e = new PageRequest(0, Constants.EVA_PAGE_SIZE, Sort.Direction.DESC, "id");
        Page<Emr> page23 = emrService.pageEmrReplied(pageabl2e, SecuritySupport.getDoctorId(), null, null, subDoctorId);
        model.addAttribute("emrPage", page23);
        model.addAttribute("subDoctorId", subDoctorId);
        model.addAttribute("doctor", SecuritySupport.getDoctor());
        model.addAttribute("subDoctorList", doctorService.findSubDoctor(SecuritySupport.getDoctor()));

        evaluateService.updateToRead(SecuritySupport.getDoctorId());
        return "newPage/ptConsultation";
    }

    @RequestMapping(value = "/ptConsultation", method = RequestMethod.POST)
    public String search(@RequestParam(required = false) String patient,
                         @RequestParam(value = "page", defaultValue = "0") Integer page,
                         @RequestParam @DateTimeFormat(pattern = Constants.PATTERN_DATE) Date startDate,
                         @RequestParam(value = "subDoctorId", required = false) Long subDoctorId,
                         @RequestParam @DateTimeFormat(pattern = Constants.PATTERN_DATE) Date endDate, Model model) {
        DateFilter dateFilter = null;
        if (null != startDate && null != endDate) {
            dateFilter = new DateFilter(startDate, endDate);
        }
        System.out.println(1);
        model.addAttribute("patient", patient);
        model.addAttribute("dateFilter", dateFilter);
        Pageable pageable = new PageRequest(page, Constants.EVA_PAGE_SIZE, Sort.Direction.DESC, "id");
        Page<Emr> emrPage = emrService.pageEmrReplied(pageable, SecuritySupport.getDoctorId(), patient, dateFilter, subDoctorId);
        model.addAttribute("doctor", SecuritySupport.getDoctor());
        model.addAttribute("emrPage", emrPage);
        model.addAttribute("subDoctorId", subDoctorId);
        return "newPage/ptConsultation";
    }

    @RequestMapping(value = "/ptDetail", method = RequestMethod.GET)
    public String ptDetail(@RequestParam("patientId") Long patientId, @RequestParam("emrId") Long emrId, Model model) {

        Patient patient = patientService.getPatientById(patientId);
        System.out.println(patient.getEmrJClassAdviceDicts());
        Set<String> emrClassSet = patient.getEmrClassList();
        model.addAttribute("patient", patient);

        if (emrClassSet.size() > 0) {
            Page<EmrJClassAdviceDict> emrJClassAdviceDictList = emrJClassAdviceDictService.findByPatientAndExamLabName(
                    patient, emrClassSet.stream().collect(Collectors.toList()).get(0), new PageRequest(0, 5));
            model.addAttribute("emrJClassAdviceDictList", emrJClassAdviceDictList);
            model.addAttribute("className", emrClassSet.stream().collect(Collectors.toList()).get(0));
        }


        model.addAttribute("emrClassSet", emrClassSet);

        List<Emr> emrList = emrService.getEmrByPatientAndDoctor(patient, SecuritySupport.getDoctor());

        model.addAttribute("doctor", SecuritySupport.getDoctor());
        model.addAttribute("emr", emrList);
        model.addAttribute("clickEmrId", emrId);
        //
        return "newPage/ptDetail";
    }


    @RequestMapping(value = "/newPage/sunPage/emrInfo", method = RequestMethod.GET)
    public String ptDetailLoadEmrInfo(@RequestParam("id") Long id, Model model) {

        Emr emrList = emrService.getEmr(id);
        model.addAttribute("emr", emrList);

        //历史病例--中医理疗
        List<EmrMedicine> therapyList = Lists.newArrayList();
        emrList.getEmrMedicineList().stream().filter(emrMedicine -> emrMedicine.getMedicineType() == Medicine.Type.ChineseTherapy).forEach(therapyList::add);
        model.addAttribute("therapyList", therapyList);
        model.addAttribute("therapyUnits", THERAPY_UNITS);

        //先找到这个病历的医生
        List<EmrJClassAdviceDict> adviceDictList = emrList.getEmrJClassAdviceDicts();
        FileUtilsServer fileUtil = new FileUtilsServer();
        //附件输出
        adviceDictList.forEach(advice -> fileUtil.outPutExamLabFileToDisk(advice.getExamLabFileList(), emrList.getDoctor()));
        model.addAttribute("adviceList", adviceDictList);
        model.addAttribute("doctor", emrList.getDoctor());

        //附加费用
        model.addAttribute("extCostList", emrList.getEmrExtCostList());

        //当前登录医生
        model.addAttribute("loginDoctor", SecuritySupport.getDoctor());

        return "newPage/sunPage/emrInfo";
    }

    @RequestMapping(value = "/newPage/sunPage/emrTalkInfo", method = RequestMethod.GET)
    public String ptDetailLoadEmrTalkinfo(@RequestParam("id") Long id, Model model) {

        Emr emrList = emrService.getEmr(id);
        model.addAttribute("emr", emrList);
        //
        return "newPage/sunPage/emrTalk";
    }


    // TODO 互动咨询 2016-6-27 14:21:24
    @RequestMapping(value = "/ptInteractive", method = RequestMethod.GET)
    public String search() {

        return "newPage/ptInteractive";
    }

    // TODO 编辑回访短信弹框 2016-8-30 10:16:05
    @RequestMapping(value = "/patient/editMes", method = RequestMethod.GET)
    public String editCareMes(Model model, @RequestParam(value = "sendMsgInfo", required = false, defaultValue = "") String sendMsgInfo) {
        model.addAttribute("doctor", SecuritySupport.getDoctor());
        model.addAttribute("sendMsgInfo", sendMsgInfo);
        return "msPage/editCareMes";
    }

    // TODO 查看检查项目详情  2016-10-27 11:45:04
    @RequestMapping(value = "/newPage/sunPage/tableResult", method = RequestMethod.GET)
    public String tableResult(Model model,
                              @RequestParam("id") Long id
    ) {
        model.addAttribute("doctor", SecuritySupport.getDoctor());
        EmrJClassAdviceDict emrJClassAdviceDict = emrJClassAdviceDictService.findById(id);
        model.addAttribute("emrJClassAdviceDict", emrJClassAdviceDict);
        new FileUtilsServer().outPutExamLabFileToDisk(emrJClassAdviceDict.getExamLabFileList(), SecuritySupport.getDoctor());
        return "newPage/sunPage/tableResult";
    }

    @RequestMapping(value = "/newPage/sunPage/loadExamLabPage")
    public String sunTable(
            @RequestParam("id") Long id,
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam("className") String className,
            Model model
    ) {

        Page<EmrJClassAdviceDict> emrJClassAdviceDictList = emrJClassAdviceDictService.findByPatientAndExamLabName(
                new Patient(id), className, new PageRequest(page, 5));
        model.addAttribute("emrJClassAdviceDictList", emrJClassAdviceDictList);
        model.addAttribute("className", className);
        model.addAttribute("patient", new Patient(id));
        return "/newPage/sunPage/sunTable";
    }

    //
    @RequestMapping(value = "/patient/adviceDictFileDown")
    public String adviceDictFileDown(HttpServletResponse response, HttpServletRequest request,
                                     @RequestParam(value = "fileUrl") String fileUrl,
                                     @RequestParam(value = "fileName") String fileName) {
        FileUtilsServer fileUtil = new FileUtilsServer();
        boolean flag = fileUtil.dowmFile(fileUrl, fileName, request, response);
        if (flag) {
            //return Result.ok("文件下载成功!");
            return null;
        } else {
            //return Result.ok("文件下载失败!");
            return null;
        }


    }
}
