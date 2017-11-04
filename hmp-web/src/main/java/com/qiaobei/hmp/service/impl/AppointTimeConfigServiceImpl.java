package com.qiaobei.hmp.service.impl;

import com.qiaobei.hmp.modules.entity.AppointTimeConfig;
import com.qiaobei.hmp.modules.entity.AppointWeekConfig;
import com.qiaobei.hmp.repository.AppointTimeConfigDao;
import com.qiaobei.hmp.service.AppointTimeConfigService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by IntelliJ IDEA 2016.4
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 2016/8/31 0031
 * Time 14:55
 */
@Service("appointTimeConfigService")
@Transactional
public class AppointTimeConfigServiceImpl implements AppointTimeConfigService {

    @Resource
    private AppointTimeConfigDao appointTimeConfigDao;


//    @Override
//    public AppointTimeConfig findByIdFromList(List<AppointTimeConfig> list,Long id) {
//        AppointTimeConfig time =new AppointTimeConfig();
//        for (AppointTimeConfig temp: list) {
//            if(time.getId().equals(id)){
//                time=temp;
//            }
//        }
//        return time;
//    }

    @Override
    public AppointTimeConfig findById(Long id) {
        return appointTimeConfigDao.findOne(id);
    }

    @Override
    public void save(AppointTimeConfig appointTimeConfig) {
        appointTimeConfigDao.save(appointTimeConfig);
    }

    @Override
    public List<AppointTimeConfig> findByAppointWeekConfig(AppointWeekConfig appointWeekConfig) {
        return appointTimeConfigDao.findByAppointWeekConfig(appointWeekConfig);
    }

    @Override
    public void delTimeListByWeekId(Long weekId) {
        appointTimeConfigDao.deleteByWeekConfigId(weekId);
    }


}
