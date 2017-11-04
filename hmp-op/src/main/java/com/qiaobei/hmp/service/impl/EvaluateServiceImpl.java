package com.qiaobei.hmp.service.impl;

import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.Emr;
import com.qiaobei.hmp.modules.entity.Evaluate;
import com.qiaobei.hmp.repository.DoctorDao;
import com.qiaobei.hmp.repository.EmrDao;
import com.qiaobei.hmp.repository.EvaluateDao;
import com.qiaobei.hmp.service.EvaluateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("evaluateService")
@Transactional
public class EvaluateServiceImpl implements EvaluateService {

    @Resource
    private EvaluateDao evaluateDao;
    @Resource
    private EmrDao emrDao;
    @Resource
    private DoctorDao doctorDao;

    @Override
    public void save(Evaluate evaluate) {
        evaluateDao.save(evaluate);
        Emr emr = emrDao.getOne(evaluate.getEmr().getId());
        if (!emr.getReplied()) {
            emr.setReplied(true);
            emrDao.save(emr);
            Doctor doctor = doctorDao.findOne(emr.getDoctor().getId());
            int integration = doctor.getIntegration() == null ? 0 : doctor.getIntegration();
            doctor.setIntegration(integration + Integer.parseInt(evaluate.getGrade()));
            int evaluationCount = doctor.getEvaluationCount() == null ? 0 : doctor.getEvaluationCount();
            doctor.setEvaluationCount(evaluationCount + 1);
            doctorDao.save(doctor);
        }
    }

    @Override
    public List<Evaluate> getByEmr(Emr emr) {
        List<Evaluate> list = evaluateDao.findByEmr(emr);
        for (Evaluate e : list) {
            if (e.getReadFlag() == null) {
                e.setReadFlag(false);
            }
            if (!e.getReadFlag() && e.getType() == Evaluate.Type.DTO) {
                e.setReadFlag(true);
                evaluateDao.save(e);
            }
        }
        return list;
    }
}
