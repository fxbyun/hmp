package com.qiaobei.hmp.web;

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
        return "print/printRp"; // 此处为80MM热敏打印页面
    }


    @RequestMapping(value = "/pub/printRpA5/{id}", method = RequestMethod.GET)
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
        return "print/printRpA5";// 此处为A5纸打印页面
    }

    @RequestMapping(value = "/pub/printDiagnosis/{id}", method = RequestMethod.GET)
    public String printDiagnosis(@PathVariable Long id, Model model) {
        Emr emr = emrService.getEmr(id);
        model.addAttribute("emr", emr);
        List<Advert> adlist = advertService.getAdvertByPositionAndStatus(Advert.Position.Print, StatusEntity.Status
                .Normal);
        if (!Collections3.isEmpty(adlist)) {
            model.addAttribute("ad", adlist.get(0));
        }
        return "print/printDiagnosis";
    }

    @RequestMapping(value = "/pub/printDiagnosisA5/{id}", method = RequestMethod.GET)
    public String printDiagnosisA5(@PathVariable Long id, Model model) {
        Emr emr1 = emrService.getEmr(id);
        model.addAttribute("emr", emr1);
        List<Advert> adlist1 = advertService.getAdvertByPositionAndStatus(Advert.Position.Print, StatusEntity.Status
                .Normal);
        //23
        if (!Collections3.isEmpty(adlist1)) {
            model.addAttribute("ad", adlist1.get(0));
        }
        return "print/printRpDiagnosisA5";
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
}
