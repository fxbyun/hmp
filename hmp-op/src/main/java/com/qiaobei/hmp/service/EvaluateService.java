package com.qiaobei.hmp.service;

import com.qiaobei.hmp.modules.entity.Emr;
import com.qiaobei.hmp.modules.entity.Evaluate;

import java.util.List;

public interface EvaluateService {

    void save(Evaluate evaluate);

    List<Evaluate> getByEmr(Emr emr);
}
