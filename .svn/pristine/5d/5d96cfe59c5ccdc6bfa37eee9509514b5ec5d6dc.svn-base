package com.qiaobei.hmp.modules.service.impl;

import com.qiaobei.hmp.modules.entity.JClassAdviceDict;
import com.qiaobei.hmp.modules.repository.JClassAdviceDictDao;
import com.qiaobei.hmp.modules.service.JClassAdviceDictService;
import com.qiaobei.hmp.modules.support.Pinyin;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by IntelliJ IDEA 2016.4
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 2016/11/4 0004
 * Time 11:34
 */
@Service("jClassAdviceDictService")
@Transactional
public class JClassAdviceDictServiceImpl implements JClassAdviceDictService {
    @Resource
    private JClassAdviceDictDao jClassAdviceDictDao;

    @Override
    public List<JClassAdviceDict> findAll() {
        return jClassAdviceDictDao.findAll();
    }

    @Override
    public List<JClassAdviceDict> findBySpecification(Specification<JClassAdviceDict> specification) {
        return jClassAdviceDictDao.findAll(specification);
    }

    @Override
    public JClassAdviceDict findById(Long id) {
        return jClassAdviceDictDao.findOne(id);
    }

    @Override
    public void setAdviceHelpCode() {
        List<JClassAdviceDict> dictList = jClassAdviceDictDao.findByHelpCodeIsNull();
        if (CollectionUtils.isNotEmpty(dictList)) {
            dictList.forEach(dict -> {
                dict.setHelpCode(Pinyin.getPinYinHeadChar(dict.getAdviceName()));
            });
            jClassAdviceDictDao.save(dictList);
        }
    }

    @Override
    public void save(JClassAdviceDict jClassAdviceDict) {
        jClassAdviceDictDao.save(jClassAdviceDict);
    }

}
