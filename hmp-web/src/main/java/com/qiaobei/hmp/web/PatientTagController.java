package com.qiaobei.hmp.web;

import com.qiaobei.hmp.modules.entity.Patient;
import com.qiaobei.hmp.modules.entity.PatientTag;
import com.qiaobei.hmp.modules.entity.QPatient;
import com.qiaobei.hmp.security.SecuritySupport;
import com.qiaobei.hmp.service.PatientService;
import com.qiaobei.hmp.service.PatientTagService;
import com.qiaobei.hmp.support.Result;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Controller
public class PatientTagController extends BaseController {

    private final PatientService patientService;
    @Resource
    private PatientTagService patientTagService;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public PatientTagController(PatientService patientServicel) {
        this.patientService = patientServicel;
    }


    @RequestMapping(value = "/patientTag/add", method = RequestMethod.POST)
    @ResponseBody
    public Result addPatientTag(@RequestParam Long patientId, @RequestParam String tag) {
        PatientTag patientTag = new PatientTag();
        patientTag.setName(tag);
        patientTag.setFrequency(0L);
        patientTag.setPatient(new Patient(patientId));
        patientTag.setDoctor(SecuritySupport.getDoctor());
        //先删除
        patientTagService.delPatienTagByPatient(new Patient(patientId));

        patientTagService.savePatientTag(patientTag);
        return Result.ok();
    }

    @RequestMapping(value = "/patientTag/delete", method = RequestMethod.POST)
    @ResponseBody
    public Result deletePatientTag(@RequestParam Long id) {
        try {
            patientTagService.deletePatientTagById(id);
        } catch (ServiceException e) {
            return Result.fail(e.getMessage());
        }
        return Result.ok();
    }

    @RequestMapping(value = "/fragment/patientTags", method = RequestMethod.GET)
    public String symptomTags(@RequestParam Long patientId, Model model) {

        model.addAttribute("patientTagList", patientTagService.listPatientTagsByPatient(patientId));
        return "fragment/patientTagsView";
    }


    @RequestMapping(value = "/fragment/loadPatientInfo")
    @ResponseBody
    public List<Patient> loadPatientInfoSelect2(
            @RequestParam("mobile") String mobile
    ) {

        QPatient qPatient = QPatient.patient;
        List<Patient> patientList = new JPAQueryFactory(entityManager).
                from(qPatient)
                .select(qPatient).
                        where(
                                qPatient.mobile.eq(mobile)
                        )
                .fetch();
        return patientList;
    }


}
