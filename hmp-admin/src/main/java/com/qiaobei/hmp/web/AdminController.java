package com.qiaobei.hmp.web;

import com.qiaobei.hmp.modules.entity.Card;
import com.qiaobei.hmp.modules.entity.Patient;
import com.qiaobei.hmp.service.*;
import com.qiaobei.hmp.support.Result;
import com.qiaobei.hmp.support.Utils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class AdminController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private PatientService patientService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private EmrService emrService;
    @Autowired
    private CardService cardService;
    @Autowired
    private NationService nationService;

    /**
     * 管理员首页
     */
    @RequestMapping(value = {"/", "/index"})
    public String index(Model model) {
        model.addAttribute("doctorTotal", doctorService.count());
        model.addAttribute("emrTotal", emrService.count());
        model.addAttribute("patientTotal", patientService.count());
        model.addAttribute("bindTotal", patientService.bindWXCount());
        model.addAttribute("cardTotal", cardService.count());
        return "index";
    }

    @RequestMapping(value = "getNations/{parentId}", method = RequestMethod.GET)
    @ResponseBody
    public Result getNations(@PathVariable("parentId") Integer parentId) {
        return Result.ok(nationService.listNation(parentId));
    }
}
