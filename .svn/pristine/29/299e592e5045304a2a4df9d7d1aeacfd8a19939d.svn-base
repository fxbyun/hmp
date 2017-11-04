package com.qiaobei.hmp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.Evaluate;
import com.qiaobei.hmp.modules.entity.Patient;
import com.qiaobei.hmp.repository.DoctorDao;
import com.qiaobei.hmp.repository.EvaluateDao;
import com.qiaobei.hmp.repository.PatientDao;
import com.qiaobei.hmp.service.EvaluateService;
import com.qiaobei.hmp.support.TemplateWX;
import com.qiaobei.hmp.support.WeixinUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("evaluateService")
@Transactional
public class EvaluateServiceImpl implements EvaluateService {

    @Resource
    private EvaluateDao evaluateDao;
    @Resource
    private PatientDao patientDao;
    @Resource
    private DoctorDao doctorDao;

    @Override
    public void save(Evaluate evaluate) {
        evaluateDao.save(evaluate);
        //微信消息推送
        Patient op = patientDao.findByUid(evaluate.getPatientUid()).get(0);
        Doctor d = doctorDao.findOne(evaluate.getDoctorId());
        TemplateWX wx = new TemplateWX();
        wx.setTouser(op.getWxId());
        wx.setUrl("http://dd.qpart.cc/op/emr/" + evaluate.getEmr().getId());
        wx.setTemplate_id(WeixinUtil.P_REPLY_TEMPLAE_ID);
        Map<String, Map> data = new HashMap();
        Map first = new HashMap();
        first.put("value", "你提出的问题有了新的回复");
        first.put("color", "#173177");
        data.put("first", first);

        Map keyword1 = new HashMap();
        keyword1.put("value", d.getName() + "医生");
        keyword1.put("color", "#173177");
        data.put("keyword1", keyword1);

        Map keyword2 = new HashMap();
        keyword2.put("value", evaluate.getContent());
        keyword2.put("color", "#173177");
        data.put("keyword2", keyword2);

        Map remark = new HashMap();
        remark.put("value", "点击查看更多信息。");
        remark.put("color", "#173177");
        data.put("remark", remark);
        wx.setData(data);
        if (!WeixinUtil.IS_LOCAL) {
            String url = WeixinUtil.SEND_MESSAGE_URL.replaceAll("ACCESS_TOKEN", WeixinUtil.ACCESS_TOKEN_OP);
            WeixinUtil.httpRequest(url, "POST", JSONObject.toJSONString(wx));
        }

    }

    @Override
    @Transactional(readOnly = true)
    public List<Evaluate> listNoReadData(Long doctorId) {
        return evaluateDao.findByReadFlagFalseAndDoctorIdAndTypeNot(doctorId, Evaluate.Type.DTO);
    }

    @Override
    public void updateToRead(Long doctorId) {
        List<Evaluate> list = listNoReadData(doctorId);
        for (Evaluate e : list) {
            e.setReadFlag(true);
            evaluateDao.save(e);
        }
    }
}
