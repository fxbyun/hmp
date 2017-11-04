package com.qiaobei.hmp.web;

import com.qiaobei.hmp.modules.entity.PrescriptionCategory;
import com.qiaobei.hmp.service.PrescriptionCategoryService;
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

    @RequestMapping(value = "/rpCate", method = RequestMethod.GET)
    @ResponseBody
    public List<PrescriptionCategory> rpCate(Model model) {
        return prescriptionCategoryService.listPrescriptionCategory();
    }

    @ModelAttribute
    public void getRpCategory(@RequestParam(value = "id", defaultValue = "-1") Long id, Model model) {
        if (id != -1) {
            model.addAttribute("category", prescriptionCategoryService.getPrescriptionCategory(id));
        }
    }

    @RequestMapping(value = "/fragment/rpCate/add", method = RequestMethod.GET)
    public String newForm(Model model) {
        PrescriptionCategory category = new PrescriptionCategory();
        model.addAttribute("category", category);
        return "/fragment/rpCateForm";
    }

    @RequestMapping(value = "/fragment/rpCate/edit/{id}", method = RequestMethod.GET)
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("category", prescriptionCategoryService.getPrescriptionCategory(id));
        return "/fragment/rpCateForm";
    }

    @RequestMapping(value = "/rpCate/save", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("category") PrescriptionCategory category,
                       RedirectAttributes redirectAttributes) {
        prescriptionCategoryService.savePrescriptionCategory(category);
        redirectAttributes.addFlashAttribute("msg", "药方类别已保存");
        return "redirect:/fragment/rpCate/edit/" + category.getId();
    }
}
