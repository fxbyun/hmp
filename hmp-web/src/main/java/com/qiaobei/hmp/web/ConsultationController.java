package com.qiaobei.hmp.web;

import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.Emr;
import com.qiaobei.hmp.modules.entity.Evaluate;
import com.qiaobei.hmp.security.SecuritySupport;
import com.qiaobei.hmp.service.EmrService;
import com.qiaobei.hmp.service.EvaluateService;
import com.qiaobei.hmp.support.Constants;
import com.qiaobei.hmp.support.DateFilter;
import com.qiaobei.hmp.support.Result;
import com.qiaobei.hmp.support.Utils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA 2016.4
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 11/4/15
 * Time 13:30
 */
@Controller
public class ConsultationController extends BaseController {
    @Resource
    private EmrService emrService;
    @Resource
    private EvaluateService evaluateService;

    @RequestMapping(value = "/consultation", method = RequestMethod.GET)
    public String consultation(Model model) {
//        DateFilter dateFilter = DateFilter.withStartPlusWeeks(-1);
//        model.addAttribute("dateFilter", dateFilter);
        Pageable pageable = new PageRequest(0, Constants.EVA_PAGE_SIZE, Sort.Direction.DESC, "id");
        Page<Emr> page = emrService.pageEmrReplied(pageable, SecuritySupport.getDoctorId(), null, null);
        model.addAttribute("emrPage", page);
        evaluateService.updateToRead(SecuritySupport.getDoctorId());//更新为已读
        return "consultation";
    }

    @RequestMapping(value = "/evaluate", method = RequestMethod.GET)
    @ResponseBody
    public Result evaluate() {
        Long doctorId = SecuritySupport.getDoctorId();
        if (doctorId == null) {
            return Result.ok();
        }
        List<Evaluate> list = evaluateService.listNoReadData(doctorId);
        if (list.isEmpty()) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    @RequestMapping(value = "/consultation", method = RequestMethod.POST)
    public String search(@RequestParam(required = false) String patient,
                         @RequestParam(value = "page", defaultValue = "0") Integer page,
                         @RequestParam @DateTimeFormat(pattern = Constants.PATTERN_DATE) Date startDate,
                         @RequestParam @DateTimeFormat(pattern = Constants.PATTERN_DATE) Date endDate, Model model) {
        DateFilter dateFilter = null;
        if (null != startDate && null != endDate) {
            dateFilter = new DateFilter(startDate, endDate);
        }
        model.addAttribute("patient", patient);
        model.addAttribute("dateFilter", dateFilter);
        Pageable pageable = new PageRequest(page, Constants.EVA_PAGE_SIZE, Sort.Direction.DESC, "id");
        Page<Emr> emrPage = emrService.pageEmrReplied(pageable, SecuritySupport.getDoctorId(), patient, dateFilter);
        model.addAttribute("emrPage", emrPage);
        return "consultation";
    }

    @RequestMapping(value = "/eval", method = RequestMethod.POST)
    public String eval(@RequestParam Long emrId,
                       @RequestParam String patientUid,
                       @RequestParam String patientName,
                       @RequestParam(required = false) String patient,
                       @RequestParam Integer pageNo,
                       @RequestParam String content,
                       Model model) {
        Doctor doctor = emrService.getEmr(emrId).getDoctor();
        if (!StringUtils.isEmpty(content)) {
            Evaluate evaluate = new Evaluate();
            evaluate.setEmr(new Emr(emrId));
            evaluate.setPatientName(patientName);
            evaluate.setPatientUid(patientUid);
            evaluate.setDoctorId(doctor.getId());
            evaluate.setDoctorName(doctor.getName());
            evaluate.setType(Evaluate.Type.DTO);
            evaluate.setContent(content);
            evaluate.setCreateTime(new Date());
            evaluate.setReadFlag(false);
            evaluateService.save(evaluate);
        }
        Page<Emr> page = emrService.pageEmrReplied(Utils.buildPageRequest(pageNo), SecuritySupport.getDoctorId(),
                patient, null);
        model.addAttribute("emrPage", page);
        return "newPage/ptConsultation";
    }

    @RequestMapping(value = "/consultation/detail/{id}", method = RequestMethod.GET)
    public String emrDetail(@PathVariable Long id, Model model) {
        Emr emr = emrService.getEmr(id);
        model.addAttribute("emr", emr);
        return "emrDetail";
    }

    @RequestMapping(value = "/emrEval", method = RequestMethod.POST)
    public String emrEval(@RequestParam Long emrId,
                          @RequestParam String patientUid,
                          @RequestParam String patientName,
                          @RequestParam Long doctorId,
                          @RequestParam String doctorName,
                          @RequestParam String content,
                          @RequestParam String patientId) {
        if (!StringUtils.isEmpty(content)) {
            Evaluate evaluate = new Evaluate();
            evaluate.setEmr(new Emr(emrId));
            evaluate.setPatientName(patientName);
            evaluate.setPatientUid(patientUid);
            evaluate.setDoctorId(doctorId);
            evaluate.setDoctorName(doctorName);
            evaluate.setType(Evaluate.Type.DTO);
            evaluate.setContent(content);
            evaluate.setCreateTime(new Date());
            evaluateService.save(evaluate);
        }
        return "redirect:/ptDetail?patientId=" + patientId + "&emrId=" + emrId;
    }
}
