package com.qiaobei.hmp.web;

import com.qiaobei.hmp.modules.entity.Company;
import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.Medicine;
import com.qiaobei.hmp.modules.entity.MedicinePrivate;
import com.qiaobei.hmp.security.SecuritySupport;
import com.qiaobei.hmp.service.MedicinePrivateService;
import com.qiaobei.hmp.service.MedicineService;
import com.qiaobei.hmp.support.Result;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by IntelliJ IDEA 2016.4
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 2016/11/8 0008
 * Time 14:46
 */
@Controller
public class TherapyController extends ConstantsController {

    @Resource
    private MedicinePrivateService medPrivateService;

    @Resource
    private MedicineService medicineService;


    //TODO 弹出添加理疗的窗口 2016-10-28 11:17:20
    @RequestMapping(value = "/fragment/popTherapy")
    public String savePhy(Model model) {
        model.addAttribute("therapyUnits", THERAPY_UNITS);
        return "/fragment/savePhy";
    }

    @RequestMapping(value = "/fragment/therapy/updateTherapy")
    public String updateTherapy(Model model, @RequestParam(value = "therapyId") Long therapyId) {
        MedicinePrivate therapy = medPrivateService.getMedPrivate(therapyId);
        model.addAttribute("therapy", therapy);
        model.addAttribute("therapyUnits", THERAPY_UNITS);

        return "/fragment/savePhy";
    }


    /**
     * 保存中医理疗
     */
    @RequestMapping(value = "/therapy/saveTherapy")
    @ResponseBody
    public Result saveTherapy(@RequestParam(value = "therapyName") String therapyName,
                              @RequestParam(value = "therapyPrice") Double therapyPrice,
                              @RequestParam(value = "therapyId") Long therapyId,
                              @RequestParam(value = "helpCode") String helpCode,
                              @RequestParam(value = "therapyUnits") Medicine.Unit therapyUnits) {

        Doctor doctor = SecuritySupport.getDoctor();
        Medicine medicine = medicineService.isThisMedHaveSameInSystem(therapyName);
        if (medicine == null) {
            medicine = new Medicine();
            medicine.setName(therapyName);
            medicine.setUnit(therapyUnits);
            medicine.setUseUnit(therapyUnits);
            medicine.setHelpCode(helpCode);
            medicine.setType(Medicine.Type.ChineseTherapy);
            Long defaultCompanyId = -1L;
            medicine.setDefaultCompany(new Company(defaultCompanyId));
            medicine.getCompanyList().add(new Company(defaultCompanyId));
            medicineService.saveMedicine(medicine, doctor);
        }
        /*为空表示新增的*/
        if (therapyId == null) {
            List<MedicinePrivate> medPrivateList = medPrivateService.isDoctorHasThisTherapy(doctor, medicine);
            if (CollectionUtils.isEmpty(medPrivateList)) {
                MedicinePrivate medPrivate = new MedicinePrivate();
                MedicinePrivate medicinePrivate = medPrivate.medToMedPriveate(medicine);
                medicinePrivate.setDoctor(doctor);
                medicinePrivate.setUseUnit(medicine.getUnit());
                medicinePrivate.setPrice(therapyPrice);
                medicinePrivate.setType(Medicine.Type.ChineseTherapy);
                medPrivateService.save(medicinePrivate);
                return Result.ok("该中医理疗已经保存成功！");
            } else {
                return Result.fail("该中医理疗已经存在！");
            }
        } else {//修改中医理疗
            MedicinePrivate therapy = medPrivateService.getMedPrivate(therapyId);
            if (therapy == null) {
                System.out.println("{saveTherapy}therapy==null");
            } else {
                therapy.setName(therapyName);
                therapy.setHelpCode(helpCode);
                therapy.setUseUnit(therapyUnits);
                therapy.setPrice(therapyPrice);
                medPrivateService.save(therapy);
                return Result.ok("该中医理疗修改成功！");
            }


            return Result.ok("该中医理疗已经修改成功！");
        }


    }

    @RequestMapping(value = "/fragment/therapy/therapyList")
    public String therapyList(Model model,
                              @RequestParam(value = "helpCode", required = false, defaultValue = "") String helpCode,
                              @RequestParam(value = "page", required = false, defaultValue = "0") int page) {

        if (page < 0) {
            page = 0;
        }
        Doctor doctor = SecuritySupport.getDoctor();

        Page<MedicinePrivate> medPrivate = medPrivateService.findTagTherapy(doctor, helpCode, new PageRequest(page, 20, Sort.Direction.DESC, "id"));

        model.addAttribute("doctor", doctor);
        model.addAttribute("page", medPrivate);
        model.addAttribute("therapyList", medPrivate.getContent());
        model.addAttribute("helpCode", helpCode);

        return "/fragment/divTherapyPage";
    }


    @RequestMapping(value = "/fragment/therapy/selectPhy")
    public String selectPhy(Model model,
                            @RequestParam(value = "therapyPrice", required = false) Double therapyPrice,
                            @RequestParam(value = "therapyId") Long therapyId,
                            @RequestParam(value = "useQty", required = false) String useQty,
                            @RequestParam(value = "useUnit", required = false) Medicine.Unit useUnit,
                            @RequestParam(value = "therapyCopy", required = false) String therapyCopy
    ) {
        MedicinePrivate therapy = medPrivateService.getMedPrivate(therapyId);

        if (therapy == null) {
            System.out.println("{therapyColltroller:selectPhy}找不到该therapy！");
        }
        if (therapyPrice != null) {
            assert therapy != null;
            therapy.setPrice(therapyPrice);
        }
        if (useQty != null) {
            assert therapy != null;
            therapy.setUseQty(useQty);
        }
        if (useUnit != null) {
            assert therapy != null;
            therapy.setUnit(useUnit);
            therapy.setUseQty(useQty);
        }

        model.addAttribute("therapy", therapy);
        model.addAttribute("therapyCopy", therapyCopy);
        model.addAttribute("therapyUnits", THERAPY_UNITS);
        return "/fragment/selectPhy";
    }


    @RequestMapping(value = "/therapy/pullTherapy")
    @ResponseBody
    public Result pullTherapy(@RequestParam(value = "therapyPrice") Double therapyPrice,
                              @RequestParam(value = "therapyId") Long therapyId,
                              @RequestParam(value = "useQty") String useQty,
                              @RequestParam(value = "useUnit") Medicine.Unit useUnit,
                              @RequestParam(value = "therapyCopy") String therapyCopy
    ) {

        //先保存私人库
        MedicinePrivate therapy = medPrivateService.getMedPrivate(therapyId);
        therapy.setUnit(useUnit);
        therapy.setPrice(therapyPrice);
        therapy.setUseQty(useQty);
        medPrivateService.save(therapy);

        Result result = new Result();
        result.setData(medPrivateService.therapyHtml(therapy, useQty, useUnit, THERAPY_UNITS.get(useUnit), therapyCopy));
        result.setSuccess(true);

        return result;
    }


}
