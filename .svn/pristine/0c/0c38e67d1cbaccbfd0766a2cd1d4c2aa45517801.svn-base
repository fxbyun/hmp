package com.qiaobei.hmp.web;

import com.google.common.collect.Lists;
import com.qiaobei.hmp.modules.entity.*;
import com.qiaobei.hmp.service.AdvertService;
import com.qiaobei.hmp.service.DoctorService;
import com.qiaobei.hmp.service.EmrService;
import com.qiaobei.hmp.support.Result;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springside.modules.utils.Collections3;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yanbin on 11/4/15.
 */
@Controller
public class PublicController extends ConstantsController {
    //    private static Logger logger = LoggerFactory.getLogger(PublicController.class);
    @Resource
    private EmrService emrService;
    @Resource
    private AdvertService advertService;
    @Resource
    private DoctorService doctorService;

    @ModelAttribute("genderMap")
    private EnumMap<Gender, String> genderMap(HttpServletRequest request) {
        return GENDER_MAP;
    }

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

    @ModelAttribute("medicineUnits")
    private EnumMap<Medicine.Unit, String> medicineUnits(HttpServletRequest request) {
        return MEDICINE_UNITS;
    }

    @ModelAttribute("groupArrys")
    private Map<String, String> groupArrys(HttpServletRequest request) {
        return GROUP_ARRYS;
    }

    @ModelAttribute("xwArrys")
    private List<String> xwArrys(HttpServletRequest request) {
        return XW_ARRYS;
    }

    @RequestMapping(value = "/pub/printRp/{id}", method = RequestMethod.GET)
    public String printRp(@PathVariable Long id, Model model, @RequestParam(value = "type", required = false,
            defaultValue = "ALL")
            String printType) {

        Emr emr = emrService.getEmr(id);
        Doctor doctor = emr.getDoctor();
        if (doctor.getNeedAlonePrinTypeStrings() == null || "".equals(doctor.getNeedAlonePrinTypeStrings()))
            doctor.setNeedAlonePrinTypeStrings("233333");

        int printMedSize = 0;
        for (EmrMedicine emrMedicine : emr.getEmrMedicineList()) {
            if (doctor.getNeedAlonePrinTypeStrings().contains(emrMedicine.getUseMode())) {
                printMedSize += 1;
            }
        }
        if (printMedSize == emr.getEmrMedicineList().size()) {
            printType = "alone";
        }

        model.addAttribute("printType", printType);
        model.addAttribute("emr", emr);
        model.addAttribute("doctor", doctor);
        return "advertising/print/printRp"; // 此处为80MM热敏打印页面
    }


    @RequestMapping(value = "adv/pub/printRpA5/{id}", method = RequestMethod.GET)
    public String printRpA5(@PathVariable Long id, Model model,
                            @RequestParam(value = "type", required = false, defaultValue = "ALL") String printType,
                            @RequestParam(value = "printModel", required = false, defaultValue = "快速打印") String
                                    printModel) {
        Emr emr = emrService.getEmr(id);
        Doctor doctor = emr.getDoctor();
        if (doctor.getNeedAlonePrinTypeStrings() == null || "".equals(doctor.getNeedAlonePrinTypeStrings())) {
            doctor.setNeedAlonePrinTypeStrings("233333");
        }
        model.addAttribute("emr", emr);
        model.addAttribute("doctor", doctor);
        model.addAttribute("printType", printType);
        model.addAttribute("printModel", printModel);
        if ("快速打印".equals(printModel)) {
            return "advertising/print/printRpA5";// 此处为A5纸打印页面
        }
        return "advertising/print/printRpPreviewA5";// 此处为A5纸打印页面

    }

    @RequestMapping(value = "adv/pub/printDiagnosis/{id}", method = RequestMethod.GET)
    public String printDiagnosis(@PathVariable Long id, Model model) {
        Emr emr = emrService.getEmr(id);
        model.addAttribute("emr", emr);
        List<Advert> adlist = advertService.getAdvertByPositionAndStatus(Advert.Position.Print, StatusEntity.Status
                .Normal);
        if (!Collections3.isEmpty(adlist)) {
            model.addAttribute("ad", adlist.get(0));
        }
        return "advertising/print/printDiagnosis";
    }

    @RequestMapping(value = "/adv/pub/printDiagnosisA5/{id}", method = RequestMethod.GET)
    public String printDiagnosisA5(@PathVariable Long id, Model model) {
        Emr emr1 = emrService.getEmr(id);
        model.addAttribute("emr", emr1);
        List<Advert> adlist1 = advertService.getAdvertByPositionAndStatus(Advert.Position.Print, StatusEntity.Status
                .Normal);
        //23
        if (!Collections3.isEmpty(adlist1)) {
            model.addAttribute("ad", adlist1.get(0));
        }
        return "advertising/print/printRpDiagnosisA5";
    }

    @RequestMapping(value = "/pub/showLodopDownPage")
    public String showLodopDownPage(Model model, @RequestParam("url") String url) {

        model.addAttribute("url", url);
        return "/print/downLodop";
    }


    @RequestMapping(value = "/pub/isNeedAlonePrint", method = RequestMethod.POST)
    @ResponseBody
    public Result isNeedAlonePrint(@RequestParam("emrId") Long emrId) {
        Emr emr = emrService.getEmr(emrId);
        Doctor doctor = emr.getDoctor();
        String age = emr.getPatient().getAge();
        System.out.println("--------" + age);
        int alenoSize = 0;
        int notAlenSize = 0;
        if (doctor.getNeedAlonePrinTypeStrings() != null) {
            for (EmrMedicine emrMedicine : emr.getEmrMedicineList()) {
                //如果药品的用药方式 和医生的独立打印相同
                if (doctor.getNeedAlonePrinTypeStrings().contains(emrMedicine.getUseMode())) {
                    alenoSize++;
                } else {
                    notAlenSize++;
                }
            }
        }

        if (alenoSize != 0) {

            if (notAlenSize != 0) {
                return Result.ok();
            }

            return Result.fail();
        } else {
            return Result.fail();
        }
    }


    // TODO 项目检查申请表 2016-11-12 11:57:28
    @RequestMapping(value = "adv/pub/printItemsTable")
    public String printItemsTable(
            @RequestParam("emrId") Long emrId,
            Model model
    ) {
        model.addAttribute("emr", emrService.getEmr(emrId));
        model.addAttribute("dateNow", new Date());

        return "advertising/print/printItemsTable";
    }

    // TODO 附加费用缴纳单 2016-11-12 12:34:31
    @RequestMapping(value = "adv/pub/printChargesTable")
    public String printChargesTable(@RequestParam("emrId") Long emrId,
                                    Model model) {
        List<EmrExtCost> emrExtCostList = emrService.getEmr(emrId).getEmrExtCostList();
        if (emrExtCostList.size() < 5) {
            int tmpSize = emrExtCostList.size();
            for (int i = 0; i < 5 - tmpSize; i++) {
                EmrExtCost emrExtCost = new EmrExtCost();
                emrExtCost.setClassName("");
                emrExtCostList.add(
                        emrExtCost
                );
            }
        }
        model.addAttribute("emr", emrService.getEmr(emrId));
        model.addAttribute("emrExtCostList", emrExtCostList);
        model.addAttribute("dateNow", new Date());

        return "advertising/print/printChargesTable";
    }

    // TODO 中医理疗申请单 2016-11-12 12:40:07
    @RequestMapping(value = "adv/pub/printPhyTable")
    public String printPhyTable(Model model, @RequestParam(value = "emrId") Long emrId) {

        Emr emr = emrService.getEmr(emrId);
        List<EmrMedicine> emrMedicineList = emr.getEmrMedicineList();
        List<EmrMedicine> therapyList = Lists.newArrayList();
        emrMedicineList.forEach(ed -> {
            if (ed.getMedicineType() == Medicine.Type.ChineseTherapy) {
                therapyList.add(ed);
            }
        });

        if (therapyList.size() < 4) {
            int tmpSize = therapyList.size();
            for (int i = 0; i < 4 - tmpSize; i++) {
                EmrMedicine emrMedicine = new EmrMedicine();
                emrMedicine.setMedicineName("");
                therapyList.add(emrMedicine);
            }
        }

        model.addAttribute("emr", emr);
        model.addAttribute("therapyList", therapyList);
        model.addAttribute("genderMap", GENDER_MAP);
        model.addAttribute("therapyUnit", THERAPY_UNITS);
        model.addAttribute("patient", emr.getPatient());
        return "advertising/print/printPhyTable";
    }

    // TODO 打印模板设置 2016-12-20 11:08:57
    @RequestMapping(value = "adv/pub/printSet")
    public String printSet() {

        return "advertising/printSet";
    }

    // TODO 打印模板2 2016-12-20 14:58:45
    @RequestMapping(value = "adv/pub/printTempRecipt")
    public String printTempRecipt() {

        return "advertising/print/printTempRecipt";
    }

}
