package com.qiaobei.hmp.web;

import com.google.common.collect.Maps;
import com.qiaobei.hmp.modules.entity.*;
import com.qiaobei.hmp.service.ConversionApplyService;
import com.qiaobei.hmp.service.MedicineService;
import com.qiaobei.hmp.support.Constants;
import com.qiaobei.hmp.support.Utils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.Date;
import java.util.EnumMap;

@Controller
@RequestMapping(value = "/apply/")
public class ConversionApplyController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(ConversionApplyController.class);
    @Resource
    private ConversionApplyService conversionApplyService;
    @Resource
    private MedicineService medicineService;

    private static final EnumMap<Medicine.Unit, String> MEDICINE_UNITS = Maps.newEnumMap(Medicine.Unit.class);
    private static final EnumMap<ConversionApply.Status, String> APPLY_STATUS = Maps.newEnumMap(ConversionApply
            .Status.class);

    static {
        MEDICINE_UNITS.put(Medicine.Unit.bxs, "盒");
        MEDICINE_UNITS.put(Medicine.Unit.btl, "瓶");
        MEDICINE_UNITS.put(Medicine.Unit.pkg, "包/排");
        MEDICINE_UNITS.put(Medicine.Unit.grs, "粒/片");
        MEDICINE_UNITS.put(Medicine.Unit.pcs, "支");
        MEDICINE_UNITS.put(Medicine.Unit.g, "g");
        MEDICINE_UNITS.put(Medicine.Unit.mg, "mg");
        MEDICINE_UNITS.put(Medicine.Unit.ml, "ml");
        MEDICINE_UNITS.put(Medicine.Unit.oth, "单位");

        APPLY_STATUS.put(ConversionApply.Status.Applied, "未审核");
        APPLY_STATUS.put(ConversionApply.Status.Verified, "已审核");
        APPLY_STATUS.put(ConversionApply.Status.Canceled, "驳回");
    }

    /**
     * 换算申请列表页
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model) {
        Page<ConversionApply> page = conversionApplyService.findPage(Utils.buildPageRequest(1, Constants.PAGZ_SIZE), null, null, null);
        model.addAttribute("page", page);
        model.addAttribute("units", MEDICINE_UNITS);
        model.addAttribute("applyStatus", APPLY_STATUS);
        return "apply/list";
    }

    /**
     * 换算申请查询
     */
    @RequestMapping(value = "query", method = RequestMethod.POST)
    public String query(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                        @RequestParam(required = false) String name,
                        @RequestParam(required = false) String applyName,
                        @RequestParam(required = false) ConversionApply.Status status, Model model) {
        Page<ConversionApply> page = conversionApplyService.findPage(Utils.buildPageRequest(pageNo, Constants
                .PAGZ_SIZE), applyName, name, status);
        model.addAttribute("page", page);
        model.addAttribute("name", name);
        model.addAttribute("status", status);
        model.addAttribute("applyName", applyName);
        model.addAttribute("units", MEDICINE_UNITS);
        model.addAttribute("applyStatus", APPLY_STATUS);
        return "apply/list";
    }

    /**
     * 换算申请通过
     */
    @RequestMapping(value = "pass/{id}", method = RequestMethod.GET)
    public String pass(@PathVariable Long id, Model model) {
        ConversionApply apply = conversionApplyService.getConversionApply(id);
        apply.setStatus(ConversionApply.Status.Verified);
        apply.setVerifyOn(new Date());
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        apply.setVerifyById(user.getId());
        apply.setVerifyBy(user.getName());
        conversionApplyService.saveConversionApply(apply);
        Page<ConversionApply> page = conversionApplyService.findPage(Utils.buildPageRequest(1, Constants.PAGZ_SIZE), null, null, null);
        model.addAttribute("page", page);
        model.addAttribute("units", MEDICINE_UNITS);
        model.addAttribute("applyStatus", APPLY_STATUS);
        return "apply/list";
    }

    /**
     * 换算申请驳回
     */
    @RequestMapping(value = "cancel/{id}", method = RequestMethod.GET)
    public String cancel(@PathVariable Long id, Model model) {
        ConversionApply apply = conversionApplyService.getConversionApply(id);
        apply.setStatus(ConversionApply.Status.Canceled);
        apply.setVerifyOn(new Date());
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        apply.setVerifyById(user.getId());
        apply.setVerifyBy(user.getName());
        conversionApplyService.saveConversionApply(apply);
        Page<ConversionApply> page = conversionApplyService.findPage(Utils.buildPageRequest(1, Constants.PAGZ_SIZE), null, null, null);
        model.addAttribute("page", page);
        model.addAttribute("units", MEDICINE_UNITS);
        model.addAttribute("applyStatus", APPLY_STATUS);
        return "apply/list";
    }

}
