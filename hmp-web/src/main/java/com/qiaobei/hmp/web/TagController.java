package com.qiaobei.hmp.web;

import com.qiaobei.hmp.modules.entity.Department;
import com.qiaobei.hmp.modules.entity.DiagnosisTag;
import com.qiaobei.hmp.modules.entity.SymptomTag;
import com.qiaobei.hmp.security.SecuritySupport;
import com.qiaobei.hmp.service.DepartmentService;
import com.qiaobei.hmp.service.TagService;
import com.qiaobei.hmp.support.Constants;
import com.qiaobei.hmp.support.Result;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by IntelliJ IDEA 2016.4
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 11/4/15
 * Time 14:46
 */
@Controller
public class TagController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(TagController.class);
    @Resource
    private TagService tagService;
    @Resource
    private DepartmentService departmentService;

    @RequestMapping(value = "/fragment/symptomTags", method = RequestMethod.GET)
    public String symptomTags(@RequestParam(value = "page", defaultValue = "0") int page,
                              @RequestParam(value = "name", required = false) String name,
                              Model model) {
        if (StringUtils.isNotEmpty(name)) {
            model.addAttribute("name", name);
        }
        //Pageable pageable = new PageRequest(page, Constants.TAG_PAGE_SIZE, Sort.Direction.DESC, "frequency");
        Pageable pageable = new PageRequest(page, 20, Sort.Direction.DESC, "frequency");
        model.addAttribute("symptomPage", tagService.pageSymptomTags(pageable, SecuritySupport.getDoctorId(), name));
        return "fragment/symptomSelect";
    }

    @RequestMapping(value = "/fragment/symptomTags", method = RequestMethod.POST)
    @ResponseBody
    public Page<SymptomTag> symptomTagsByPost(@RequestParam(value = "page", defaultValue = "0") int page,
                                              @RequestParam(value = "name", required = false) String name,
                                              Model model) {
        if (StringUtils.isNotEmpty(name)) {
            model.addAttribute("name", name);
        }
        //Pageable pageable = new PageRequest(page, Constants.TAG_PAGE_SIZE, Sort.Direction.DESC, "frequency");
        Pageable pageable = new PageRequest(page, 20, Sort.Direction.DESC, "frequency");
        //model.addAttribute("symptomPage", );

        return tagService.pageSymptomTags(pageable, SecuritySupport.getDoctorId(), name);
    }

    @RequestMapping(value = "/fragment/symptomOtherTags", method = RequestMethod.GET)
    public String symptomTagOthers(@RequestParam(value = "page", defaultValue = "0") int page,
                                   @RequestParam(value = "name", required = false) String name,
                                   Model model) {
        if (StringUtils.isNotEmpty(name)) {
            model.addAttribute("name", name);
        }
        //Pageable pageable = new PageRequest(page, Constants.TAG_PAGE_SIZE, Sort.Direction.DESC, "frequency");
        Pageable pageable = new PageRequest(page, 20, Sort.Direction.DESC, "frequency");
        model.addAttribute("symptomPage", tagService.pageSymptomOtherTags(pageable, SecuritySupport.getDoctorId(),
                name));
        return "fragment/symptomOtherSelect";
    }

    @RequestMapping(value = "/fragment/configSymptomTags", method = RequestMethod.GET)
    public String configSymptomSelect(@RequestParam(value = "page", defaultValue = "0") int page,
                                      @RequestParam(value = "name", required = false) String name,
                                      Model model) {
        if (StringUtils.isNotEmpty(name)) {
            model.addAttribute("name", name);
        }
        Pageable pageable = new PageRequest(page, Constants.TAG_PAGE_SIZE, Sort.Direction.DESC, "frequency");
        model.addAttribute("symptomPage", tagService.pageSymptomTags(pageable, SecuritySupport.getDoctorId(), name));
        return "fragment/configSymptomSelect";
    }

    @RequestMapping(value = "/fragment/configSymptomOtherTags", method = RequestMethod.GET)
    public String configSymptomOtherSelect(@RequestParam(value = "page", defaultValue = "0") int page,
                                           @RequestParam(value = "name", required = false) String name,
                                           Model model) {
        if (StringUtils.isNotEmpty(name)) {
            model.addAttribute("name", name);
        }
        Pageable pageable = new PageRequest(page, Constants.TAG_PAGE_SIZE, Sort.Direction.DESC, "frequency");
        model.addAttribute("symptomPage", tagService.pageSymptomOtherTags(pageable, SecuritySupport.getDoctorId(),
                name));
        return "fragment/configSymptomOtherSelect";
    }

    @RequestMapping(value = "/fragment/diagnosisTags", method = RequestMethod.GET)
    public String diagnosisTags(@RequestParam(value = "page", defaultValue = "0") int page,
                                @RequestParam Long departmentId,
                                @RequestParam(value = "name", required = false) String name,
                                Model model) {
        if (StringUtils.isNotEmpty(name)) {
            model.addAttribute("name", name);
        }
        //Pageable pageable = new PageRequest(page, Constants.TAG_PAGE_SIZE, Sort.Direction.DESC, "frequency");
        Pageable pageable = new PageRequest(page, 15, Sort.Direction.DESC, "frequency");
        try {
            model.addAttribute("diagnosisPage",
                    tagService.pageDiagnosisTags(pageable, SecuritySupport.getDoctorId(), departmentId, name));
        } catch (Exception e) {
            model.addAttribute("diagnosisPage",
                    tagService.pageDiagnosisTags(pageable, SecuritySupport.getDoctorId(), departmentId, name));
        }
        return "fragment/diagnosisSelect";
    }

    @RequestMapping(value = "/fragment/diagnosisTags", method = RequestMethod.POST)
    @ResponseBody
    public Page<DiagnosisTag> diagnosisTagsPost(@RequestParam(value = "page", defaultValue = "0") int page,
                                                @RequestParam Long departmentId,
                                                @RequestParam(value = "name", required = false) String name,
                                                Model model) {
        if (StringUtils.isNotEmpty(name)) {
            model.addAttribute("name", name);
        }
        //Pageable pageable = new PageRequest(page, Constants.TAG_PAGE_SIZE, Sort.Direction.DESC, "frequency");
        Pageable pageable = new PageRequest(page, 15, Sort.Direction.DESC, "frequency");

        return tagService.pageDiagnosisTags(pageable, SecuritySupport.getDoctorId(), departmentId, name);
    }

    @RequestMapping(value = "/fragment/diagnosisOtherTags", method = RequestMethod.GET)
    public String diagnosisTagOthers(@RequestParam(value = "page", defaultValue = "0") int page,
                                     @RequestParam Long departmentId,
                                     @RequestParam(value = "name", required = false) String name,
                                     Model model) {
        if (StringUtils.isNotEmpty(name)) {
            model.addAttribute("name", name);
        }
        Pageable pageable = new PageRequest(page, Constants.TAG_PAGE_SIZE, Sort.Direction.DESC, "frequency");
        model.addAttribute("diagnosisPage",
                tagService.pageDiagnosisOtherTags(pageable, SecuritySupport.getDoctorId(), departmentId, name));
        return "fragment/diagnosisOtherSelect";
    }

    @RequestMapping(value = "/tag/addSymptomTag", method = RequestMethod.POST)
    @ResponseBody
    public Result addSymptomTag(@RequestParam("symptomTag") String tag, @RequestParam(value = "helpCode", required =
            false) String helpCode) {
        SymptomTag symptomTag = tagService.getSymptomTag(SecuritySupport.getDoctorId(), tag);
        if (symptomTag == null) {
            symptomTag = new SymptomTag();
            symptomTag.setName(tag);
            symptomTag.setHelpCode(helpCode);
            symptomTag.setDoctor(SecuritySupport.getDoctor());
            symptomTag.setFrequency(0L);
            symptomTag.setCreateOn(new Date());
            tagService.saveSymptomTag(symptomTag);
        }
        Result result = Result.ok();
        result.setData(symptomTag);
        return result;
    }

    @RequestMapping(value = "/tag/removeSymptomTag", method = RequestMethod.POST)
    @ResponseBody
    public Result removeSymptomTag(@RequestParam("tagId") Long tagId) {
        tagService.removeSymptomTag(tagId);
        return Result.ok();
    }

    @RequestMapping(value = "/tag/addDiagnosisTag", method = RequestMethod.POST)
    @ResponseBody
    public Result addDiagnosisTag(@RequestParam Long departmentId,
                                  @RequestParam("diagnosisTag") String tag, @RequestParam(value = "helpCode",
            required = false) String helpCode) {
        DiagnosisTag diagnosisTag = tagService.getDiagnosisTag(SecuritySupport.getDoctorId(), departmentId, tag);
        if (diagnosisTag == null) {
            diagnosisTag = new DiagnosisTag();
            diagnosisTag.setName(tag);
            diagnosisTag.setHelpCode(helpCode);
            diagnosisTag.setDoctor(SecuritySupport.getDoctor());
            diagnosisTag.setFrequency(0L);
            diagnosisTag.setCreateOn(new Date());
            Department department = departmentService.getDepartment(departmentId);
            if (department != null) {
                diagnosisTag.setDepartmentName(department.getName());
            }
            diagnosisTag.setDepartmentId(departmentId);
            tagService.saveDiagnosisTag(diagnosisTag);
        }
        Result result = Result.ok();
        result.setData(diagnosisTag);
        return result;
    }

    @RequestMapping(value = "/fragment/rpDiagnosisTags", method = RequestMethod.GET)
    public String rpDiagnosisTags(@RequestParam(value = "page", defaultValue = "0") int page,
                                  @RequestParam(value = "name", required = false) String name,
                                  Model model) {
        if (StringUtils.isNotEmpty(name)) {
            model.addAttribute("name", name);
        }
        Pageable pageable = new PageRequest(page, Constants.TAG_PAGE_SIZE, Sort.Direction.DESC, "frequency");
        model.addAttribute("diagnosisPage",
                tagService.pageDiagnosisTags(pageable, SecuritySupport.getDoctorId(), 1l, name));
        return "fragment/rpDiagnosisSelect";
    }

    @RequestMapping(value = "/fragment/rpDiagnosisOtherTags", method = RequestMethod.GET)
    public String rpDiagnosisOtherTags(@RequestParam(value = "page", defaultValue = "0") int page,
                                       @RequestParam(value = "name", required = false) String name,
                                       Model model) {
        if (StringUtils.isNotEmpty(name)) {
            model.addAttribute("name", name);
        }
        Pageable pageable = new PageRequest(page, Constants.TAG_PAGE_SIZE, Sort.Direction.DESC, "frequency");
        model.addAttribute("diagnosisPage",
                tagService.pageDiagnosisOtherTags(pageable, SecuritySupport.getDoctorId(), 1l, name));
        return "fragment/rpDiagnosisOtherSelect";
    }

    @RequestMapping(value = "/fragment/configDiagnosisTags", method = RequestMethod.GET)
    public String configDiagnosisSelect(@RequestParam(value = "page", defaultValue = "0") int page,
                                        @RequestParam(value = "name", required = false) String name,
                                        Model model) {
        if (StringUtils.isNotEmpty(name)) {
            model.addAttribute("name", name);
        }
        Pageable pageable = new PageRequest(page, Constants.TAG_PAGE_SIZE, Sort.Direction.DESC, "frequency");
        model.addAttribute("diagnosisPage", tagService.pageDiagnosisTags(pageable, SecuritySupport.getDoctorId(), 1l,
                name));
        return "fragment/configDiagnosisSelect";
    }

    @RequestMapping(value = "/fragment/configDiagnosisOtherTags", method = RequestMethod.GET)
    public String configDiagnosisOtherSelect(@RequestParam(value = "page", defaultValue = "0") int page,
                                             @RequestParam(value = "name", required = false) String name,
                                             Model model) {
        if (StringUtils.isNotEmpty(name)) {
            model.addAttribute("name", name);
        }
        Pageable pageable = new PageRequest(page, Constants.TAG_PAGE_SIZE, Sort.Direction.DESC, "frequency");
        model.addAttribute("diagnosisPage", tagService.pageDiagnosisOtherTags(pageable, SecuritySupport.getDoctorId()
                , 1l, name));
        return "fragment/configDiagnosisOtherSelect";
    }

    @RequestMapping(value = "/tag/removeDiagnosisTag", method = RequestMethod.POST)
    @ResponseBody
    public Result removeDiagnosisTag(@RequestParam("tagId") Long tagId) {
        tagService.removeDiagnosisTag(tagId);
        return Result.ok();
    }
}
