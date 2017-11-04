package com.qiaobei.hmp.web;

import com.qiaobei.hmp.modules.entity.DiagnosisTag;
import com.qiaobei.hmp.modules.entity.Medicine;
import com.qiaobei.hmp.modules.entity.PrescriptionLib;
import com.qiaobei.hmp.modules.entity.PrescriptionLibItem;
import com.qiaobei.hmp.service.DiagnosisTagService;
import com.qiaobei.hmp.service.PrescriptionCategoryService;
import com.qiaobei.hmp.service.PrescriptionLibService;
import com.qiaobei.hmp.support.Constants;
import com.qiaobei.hmp.support.Result;
import com.qiaobei.hmp.support.Utils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springside.modules.utils.Collections3;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.EnumMap;
import java.util.List;

@Controller
public class PrescriptionLibController extends ConstantsController {
    private static Logger logger = LoggerFactory.getLogger(PrescriptionLibController.class);
    @Resource
    private PrescriptionLibService prescriptionLibService;
    @Resource
    private PrescriptionCategoryService prescriptionCategoryService;
    @Autowired
    private DiagnosisTagService diagnosisTagService;

    @ModelAttribute("medicineTypes")
    private EnumMap<Medicine.Type, String> medicineTypes(HttpServletRequest request) {
        return MEDICINE_TYPES;
    }

    @ModelAttribute("medicineUnits")
    private EnumMap<Medicine.Unit, String> medicineUnits(HttpServletRequest request) {
        return MEDICINE_UNITS;
    }

    @ModelAttribute("medicineUseModes")
    private List<String> medicineUseModes(HttpServletRequest request) {
        return MEDICINE_USE_MODE_LIST;
    }

    @ModelAttribute("medicineUsingTimes")
    private List<String> medicineUsingTimes(HttpServletRequest request) {
        return MEDICINE_USING_TIME_LIST;
    }

    @ModelAttribute("medicineUseTimes")
    private List<String> medicineUseTimes(HttpServletRequest request) {
        return MEDICINE_USE_TIMES_LIST;
    }


    /**
     * 药方库页
     */
    @RequestMapping(value = "/rplib/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("page", prescriptionLibService.findPage(Utils.buildPageRequest(1, Constants.PAGZ_SIZE), null));
        return "rplib/list";
    }

    /**
     * 药方查询
     */
    @RequestMapping(value = "/rplib/query", method = RequestMethod.POST)
    public String query(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                        @RequestParam(required = false) String name, Model model) {
        model.addAttribute("page", prescriptionLibService.findPage(Utils.buildPageRequest(pageNo, Constants.PAGZ_SIZE), name));
        model.addAttribute("name", name);
        return "rplib/list";
    }

    /**
     * 新建药方页
     */
    @RequestMapping(value = "/rplib/add", method = RequestMethod.GET)
    public String add(Model model) {
        PrescriptionLib p = new PrescriptionLib();
        p.setMedicineType(Medicine.Type.Western);
        model.addAttribute("rplib", p);
        model.addAttribute("categories",
                prescriptionCategoryService.listPrescriptionCategory());
        return "rplib/add";
    }

    @RequestMapping(value = "/rplib/edit/{id}", method = RequestMethod.GET)
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("rplib", prescriptionLibService.getPrescriptionLib(id));
        model.addAttribute("categories", prescriptionCategoryService.listPrescriptionCategory());
        return "rplib/add";
    }

    @RequestMapping(value = "/fragment/rplib/diagnosis", method = RequestMethod.GET)
    public String diagnosis() {
        return "rplib/diagnosis";
    }

    @RequestMapping(value = "/fragment/rplib/diagnosisTags", method = RequestMethod.GET)
    public String diagnosisTags(@RequestParam(value = "page", defaultValue = "0") int page,
                                @RequestParam(value = "name", required = false) String name, Model model) {
        if (StringUtils.isNotEmpty(name)) {
            model.addAttribute("name", name);
        }
        Pageable pageable = new PageRequest(page, Constants.TAG_PAGZ_SIZE, Sort.Direction.DESC, "frequency");
        Page<DiagnosisTag> diagnosisPage = diagnosisTagService.findPage(pageable, name);
        model.addAttribute("diagnosisPage", diagnosisPage);
        return "fragment/diagnosisSelect";
    }

    @RequestMapping(value = "/rplib/save", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("rplib") PrescriptionLib rplib,
                       @RequestParam(value = "medicineIds", required = false) List<Long> medicineIds,
                       @RequestParam(value = "companyIds", required = false) List<Long> companyIds,
                       @RequestParam(value = "medicineQty", required = false) List<Double> medicineQty,
                       @RequestParam(value = "medicineRate", required = false) List<Double> medicineRate,
                       @RequestParam(value = "medicineUnit", required = false) List<Medicine.Unit> medicineUnit,
                       @RequestParam(value = "medicineCopies", required = false) List<Double> medicineCopies,
                       @RequestParam(value = "medicineUseModes", required = false) List<String> medicineUseModes,
                       @RequestParam(value = "medicineHasUsages", required = false) List<Boolean> medicineHasUsages,
                       Model model) {
        if (Collections3.isNotEmpty(medicineIds)
                && Collections3.isNotEmpty(companyIds)
                && Collections3.isNotEmpty(medicineQty)
                && Collections3.isNotEmpty(medicineUnit)) {
            rplib.getPrescriptionLibItemList().clear();
            for (int i = 0; i < medicineIds.size(); i++) {
                PrescriptionLibItem item = new PrescriptionLibItem();
                item.setMedicineId(medicineIds.get(i));
                item.setCompanyId(companyIds.get(i));
                item.setQty(medicineQty.get(i));
                item.setRate(medicineRate.get(i));
                item.setUnit(medicineUnit.get(i));
                item.setCopies(medicineCopies.get(i));
                item.setUseMode(medicineUseModes.get(i));
                item.setHasUsage(medicineHasUsages.get(i));
                rplib.getPrescriptionLibItemList().add(item);
            }
        }
        prescriptionLibService.savePrescriptinLib(rplib);
        model.addAttribute("categories",
                prescriptionCategoryService.listPrescriptionCategory());
        model.addAttribute("msg", "药方信息已保存");
        return "rplib/add";
    }

    @RequestMapping(value = "/rplib/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Result delete(@PathVariable Long id) {
        prescriptionLibService.deletePrescriptinLib(id);
        return Result.ok();
    }

}
