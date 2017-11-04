package com.qiaobei.hmp.web;

import com.qiaobei.hmp.modules.entity.PrescriptionCategory;
import com.qiaobei.hmp.security.SecuritySupport;
import com.qiaobei.hmp.service.PrescriptionCategoryService;
import com.qiaobei.hmp.support.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@Controller
public class PrescriptionCategoryController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(PrescriptionCategoryController.class);
    @Resource
    private PrescriptionCategoryService prescriptionCategoryService;

    /**
     * 药方管理页
     */
    @RequestMapping(value = "/rpCate", method = RequestMethod.GET)
    @ResponseBody
    public List<PrescriptionCategory> rpCate() {
        return prescriptionCategoryService.listPrescriptionCategoryByDoctor(SecuritySupport.getDoctorId());
    }

    @ModelAttribute
    public void getRpCategory(@RequestParam(value = "id", defaultValue = "-1") Long id, Model model) {
        if (id != -1) {
            model.addAttribute("category", prescriptionCategoryService.getPrescriptionCategory(id));
        }
    }

    @RequestMapping(value = "/rpCate/add", method = RequestMethod.GET)
    public String newForm(Model model) {
        PrescriptionCategory category = new PrescriptionCategory();
        model.addAttribute("category", category);
        return "rpCateForm";
    }

    @RequestMapping(value = "/rpCate/edit/{id}", method = RequestMethod.GET)
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("category", prescriptionCategoryService.getPrescriptionCategory(id));
        return "rpCateForm";
    }

    @RequestMapping(value = "/rpCate/save", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("category") PrescriptionCategory category,
                       RedirectAttributes redirectAttributes) {
        category.setDoctor(SecuritySupport.getDoctor());
        prescriptionCategoryService.savePrescriptionCategory(category);
        redirectAttributes.addFlashAttribute("msg", "药方类别已保存");
        return "redirect:/rpCate/edit/" + category.getId();
    }

    //TODO 添加类别管理 2016-6-15 11:17:49
    @RequestMapping(value = "/rpCate/manage", method = RequestMethod.GET)
    public String manageForm(Model model) {
        model.addAttribute("categories", prescriptionCategoryService.listPrescriptionCategoryByDoctor(SecuritySupport
                .getDoctorId()));
        return "rpManageForm";
    }

    @RequestMapping(value = "/rpCate/manage/del/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Result delete(@PathVariable Long id) {
        prescriptionCategoryService.deletePrescriptionCategory(id);
        return Result.ok();
    }
}
