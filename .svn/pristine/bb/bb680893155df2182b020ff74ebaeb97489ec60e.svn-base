package com.qiaobei.hmp.web;

import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.PrescriptionCategory;
import com.qiaobei.hmp.security.SecuritySupport;
import com.qiaobei.hmp.service.MedicinePrivateService;
import com.qiaobei.hmp.service.MedicineService;
import com.qiaobei.hmp.service.PrescriptionCategoryService;
import com.qiaobei.hmp.service.TagService;
import com.qiaobei.hmp.support.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by IntelliJ IDEA 2016.2.1
 * User ZW_Teemoer  teemoer@cntv.cn
 * Date 2016/7/8
 * Time 上午10:21
 */
@Controller
@RequestMapping(value = "/learn/subPage")
public class LearnSubController {

    @Resource
    private TagService tagService;
    @Resource
    private PrescriptionCategoryService prescriptionCategoryService;
    @Resource
    private MedicineService medicineService;
    @Resource
    private MedicinePrivateService medicinePrivateService;

    // TODO 加载患者病症
    @RequestMapping(value = "/symptom")
    public String symptom(Model model,
                          @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                          @RequestParam(value = "name", required = false) String name) {

        if (StringUtils.isNotEmpty(name)) {
            model.addAttribute("name", name);
        }
        Pageable pageable = new PageRequest(page, 8, Sort.Direction.DESC, "frequency");
        model.addAttribute("symptomPage", tagService.pageSymptomTags(pageable, SecuritySupport.getDoctorId(), name));
        return "/learn/subPage/Symptoms";
    }


    // TODO 录入药方 药方对应的诊状
    @RequestMapping(value = "/diagbosis")
    public String diagbosis(Model model,
                            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                            @RequestParam(value = "name", required = false) String name) {
        if (StringUtils.isNotEmpty(name)) {
            model.addAttribute("name", name);
        }
        Pageable pageable = new PageRequest(page, 8, Sort.Direction.DESC, "frequency");


        //1L暂时写死  1L代表的是科室
        model.addAttribute("diagnosisPage",
                tagService.pageDiagnosisTags(pageable, SecuritySupport.getDoctorId(), 1L, name));

        return "/learn/subPage/diagbosis";
    }

    // TODO 录入药方 药方的药方名称和药方的类别
    @RequestMapping(value = "/category")
    public String category(Model model,
                           @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                           @RequestParam(value = "name", required = false) String name) {
        model.addAttribute("categories", prescriptionCategoryService.listPrescriptionCategoryByDoctor(SecuritySupport
                .getDoctorId()));
        return "/learn/subPage/category";
    }

    // TODO  药方类别管理页面
    @RequestMapping(value = "/categoryManage")
    public String categoryManage(Model model) {
        model.addAttribute("categories", prescriptionCategoryService.listPrescriptionCategoryByDoctor(SecuritySupport
                .getDoctorId()));

        return "learn/subPage/sunCategory/categoryManage";
    }

    // TODO  添加药方类别
    @RequestMapping(value = "/category/addCategory")
    @ResponseBody
    public Result addCategory(@RequestParam(value = "name") String name) {
        Doctor doctor = SecuritySupport.getDoctor();
        PrescriptionCategory prescriptionCategory = new PrescriptionCategory();
        prescriptionCategory.setName(name);
        prescriptionCategory.setDoctor(doctor);
        prescriptionCategory.setDoctorId(doctor.getId());
        prescriptionCategory.setDoctorName(doctor.getName());
        prescriptionCategoryService.savePrescriptionCategory(prescriptionCategory);

        return Result.ok();
    }

    // TODO  修改药方类别
    @RequestMapping(value = "/category/editCategory")
    @ResponseBody
    public Result editCategory(@RequestParam(value = "id") Long id, @RequestParam(value = "name") String name) {
        PrescriptionCategory prescriptionCategory = prescriptionCategoryService.getPrescriptionCategory(id);
        prescriptionCategory.setName(name);
        prescriptionCategoryService.savePrescriptionCategory(prescriptionCategory);
        return Result.ok();
    }

    // TODO  获取药方类别List
    @RequestMapping(value = "/category/getCategoryList")
    @ResponseBody
    public Result getCategoryList() {
        List<PrescriptionCategory> prescriptionCategory = prescriptionCategoryService
                .listPrescriptionCategoryByDoctor(SecuritySupport.getDoctorId());
        Result result = new Result();
        result.setData(prescriptionCategory);
        result.setSuccess(true);
        return result;
    }

    @RequestMapping(value = "/category/del/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Result deleteCategory(@PathVariable Long id) {
        prescriptionCategoryService.deletePrescriptionCategory(id);
        return Result.ok();
    }
}
