package com.qiaobei.hmp.web;

import com.google.common.collect.Maps;
import com.qiaobei.hmp.modules.entity.Medicine;
import com.qiaobei.hmp.modules.entity.Prescription;
import com.qiaobei.hmp.modules.entity.StatusEntity;
import com.qiaobei.hmp.service.PrescriptionService;
import com.qiaobei.hmp.support.Constants;
import com.qiaobei.hmp.support.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.EnumMap;

@Controller
@RequestMapping(value = "/rezept/")
public class PrescriptionController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(PrescriptionController.class);
    @Resource
    private PrescriptionService prescriptionService;

    private static final EnumMap<Medicine.Type, String> MEDICINE_TYPES = Maps.newEnumMap(Medicine.Type.class);
    private static final EnumMap<Medicine.Unit, String> MEDICINE_UNITS = Maps.newEnumMap(Medicine.Unit.class);

    static {
        MEDICINE_TYPES.put(Medicine.Type.Western, "西药及中成药");
        MEDICINE_TYPES.put(Medicine.Type.Chinese, "中草药");

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
     * 药方页
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("page", prescriptionService.findPage(Utils.buildPageRequest(1, Constants.PAGZ_SIZE), null, null));
        model.addAttribute("medicineTypes", MEDICINE_TYPES);
        return "rezept/list";
    }

    /**
     * 药方查询
     */
    @RequestMapping(value = "query", method = RequestMethod.POST)
    public String query(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                        @RequestParam(required = false) String name,
                        @RequestParam(required = false) String createBy, Model model) {
        model.addAttribute("page", prescriptionService.findPage(Utils.buildPageRequest(pageNo, Constants.PAGZ_SIZE),
                name, createBy));
        model.addAttribute("name", name);
        model.addAttribute("createBy", createBy);
        model.addAttribute("medicineTypes", MEDICINE_TYPES);
        return "rezept/list";
    }

    /**
     * 药方详情页
     */
    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable Long id, Model model) {
        model.addAttribute("prescription", prescriptionService.getPrescription(id));
        model.addAttribute("medicineTypes", MEDICINE_TYPES);
        model.addAttribute("medicineUnits", MEDICINE_UNITS);
        return "rezept/view";
    }

    /**
     * 共享药方
     */
    @RequestMapping(value = "/share/{id}", method = RequestMethod.GET)
    public String share(@PathVariable Long id, Model model) {
        Prescription p = prescriptionService.getPrescription(id);
        p.setStatus(StatusEntity.Status.Used);
        prescriptionService.sharePrescriptin(p);
        model.addAttribute("prescription", p);
        model.addAttribute("medicineTypes", MEDICINE_TYPES);
        model.addAttribute("medicineUnits", MEDICINE_UNITS);
        return "rezept/view";
    }

    /**
     * 取消共享药方
     */
    @RequestMapping(value = "/cancelShare/{id}", method = RequestMethod.GET)
    public String cancelShare(@PathVariable Long id, Model model) {
        Prescription p = prescriptionService.getPrescription(id);
        p.setStatus(StatusEntity.Status.Normal);
        prescriptionService.cancelSharePrescriptin(p);
        model.addAttribute("prescription", p);
        model.addAttribute("medicineTypes", MEDICINE_TYPES);
        model.addAttribute("medicineUnits", MEDICINE_UNITS);
        return "rezept/view";
    }
}
